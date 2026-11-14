#version 330
#extension GL_ARB_separate_shader_objects : require

#include <minecraft:fog.glsl>
#include <minecraft:globals.glsl>

//volucite barrier : depth illusion, like looking through a window at particles far behind the face
//each particle layer is a virtual plane at some depth behind the visible face : the view ray is extended to it, and the particles are read where the ray crosses it
//the layers are anchored in the world : they move with the camera (parallax, deeper layers move less), and slowly drift
//output : premultiplied alpha (see AerialHellRenderPipelines.VOLUCITE_BARRIER) : alpha = how much the dark void hides what is behind, the particles are added on top

uniform sampler2D Sampler0; //particles (textures/entity/volucite_barrier/particles.png, dots of 3 sizes and faint glyphs, shown by the waves, on black : only their brightness is used, the color comes from LAYER_COLORS)
uniform sampler2D Sampler1; //nebula, behind the last layer (vanilla end sky)

layout(location = 0) in vec3 relativePos; //relative to the eye the image is drawn from (view bobbing included, see volucite_barrier.vsh)
layout(location = 1) in vec3 worldPos;
layout(location = 2) in float sphericalVertexDistance;
layout(location = 3) in float cylindricalVertexDistance;

//how much the dark void hides what is behind the barrier. 0.0 : what is behind is fully visible (only particles), 1.0 : opaque
const float VOID_OPACITY = 0.35;
const vec3 VOID_COLOR = vec3(0.01, 0.03, 0.08);
const float PARTICLES_BRIGHTNESS = 1.0;
const float MIN_VIEW_COS = 0.15; //limits the ray length at grazing angles (layers would stretch to infinity)

//particle layers, from the nearest to the farthest
//depth : distance behind the face (blocks). period : texture repeat size (blocks, must divide 1024). bigger period : bigger and sparser particles
//drift : texture repeats per minecraft day (integers, no jump when the day time wraps). 10 with a 16 blocks period : 160 blocks per 20 minutes
#define LAYERS 6
const float LAYER_DEPTHS[LAYERS] = float[](1.0, 2.5, 4.5, 7.0, 10.5, 15.0);
const float LAYER_PERIODS[LAYERS] = float[](16.0, 32.0, 16.0, 32.0, 16.0, 32.0);
const vec2 LAYER_DRIFTS[LAYERS] = vec2[](vec2(14.0, 5.0), vec2(-6.0, 4.0), vec2(9.0, -11.0), vec2(-5.0, -4.0), vec2(6.0, 7.0), vec2(-3.0, 2.0));
const vec3 LAYER_COLORS[LAYERS] = vec3[](
    vec3(0.55, 0.75, 1.00),
    vec3(0.60, 0.62, 1.00),
    vec3(0.40, 0.60, 0.95),
    vec3(0.38, 0.45, 0.85),
    vec3(0.25, 0.38, 0.75),
    vec3(0.20, 0.25, 0.60)
);

const float NEBULA_DEPTH = 22.0;
const float NEBULA_PERIOD = 64.0;
const vec2 NEBULA_DRIFT = vec2(2.0, 1.0);
const vec3 NEBULA_COLOR = vec3(0.10, 0.18, 0.40);

//waves : light fronts going through the particles (3d noise, like the volucite stone glow). particles are dim, and bright when a wave goes through them
//scale : size of the noise patterns (blocks, power of 2 : the noise repeats every 1024 blocks like the world position). cycles : waves across the noise range (more : more and thinner waves)
//waves per day : integer (no jump when the day time wraps), 32 : one wave every 37.5 s at each point. width : part of the cycle a point is lit
const int WAVE_SCALE = 32;
const float WAVE_CYCLES = 4.0;
const float WAVES_PER_DAY = 32.0;
const float WAVE_WIDTH = 0.25;
const float PARTICLES_IDLE = 0.4; //particles brightness outside of the waves
const float PARTICLES_IN_WAVE = 2.0; //particles brightness at the peak of a wave

layout(location = 0) out vec4 fragColor;

//2d coordinates in the plane of the face
vec2 face_coords(vec3 pos, vec3 normal) {
    vec3 n = abs(normal);
    if (n.y >= n.x && n.y >= n.z) {return pos.xz;}
    if (n.x >= n.z) {return pos.zy;}
    return pos.xy;
}

//point of the view ray, depth blocks behind the face
vec3 behind_face(float depth, vec3 viewDir, float viewCos) {
    return worldPos + viewDir * (depth / viewCos);
}

float luminance(vec3 color) {return dot(color, vec3(1.0 / 3.0));}

//random gradient of a noise lattice point, the lattice repeats every 1024 blocks
vec3 lattice_gradient(ivec3 cell) {
    uvec3 c = uvec3(cell & ivec3(1024 / WAVE_SCALE - 1));
    uint h = (c.x * 0x8da6b343u) ^ (c.y * 0xd8163841u) ^ (c.z * 0xcb1ab31fu);
    h ^= h >> 15u; h *= 0x2c1b3c6du; h ^= h >> 12u; h *= 0x297a2d39u; h ^= h >> 15u;
    return vec3(uvec3(h, h >> 10u, h >> 20u) & 1023u) / 511.5 - 1.0;
}

float corner(ivec3 cell, ivec3 offset, vec3 f) {return dot(lattice_gradient(cell + offset), f - vec3(offset));}

//perlin noise, mostly between -0.5 and 0.5
float perlin_noise(vec3 p) {
    vec3 cellPos = floor(p);
    ivec3 cell = ivec3(cellPos);
    vec3 f = p - cellPos;
    vec3 u = f * f * f * (f * (f * 6.0 - 15.0) + 10.0);
    float x00 = mix(corner(cell, ivec3(0, 0, 0), f), corner(cell, ivec3(1, 0, 0), f), u.x);
    float x10 = mix(corner(cell, ivec3(0, 1, 0), f), corner(cell, ivec3(1, 1, 0), f), u.x);
    float x01 = mix(corner(cell, ivec3(0, 0, 1), f), corner(cell, ivec3(1, 0, 1), f), u.x);
    float x11 = mix(corner(cell, ivec3(0, 1, 1), f), corner(cell, ivec3(1, 1, 1), f), u.x);
    return mix(mix(x00, x10, u.y), mix(x01, x11, u.y), u.z);
}

//0 to 1 : how much a wave lights this point now (quick rise, slower fade)
float wave_light(vec3 point) {
    float progress = fract(GameTime * WAVES_PER_DAY - perlin_noise(point / float(WAVE_SCALE)) * WAVE_CYCLES) / WAVE_WIDTH; //0 to 1 while the wave goes through the point
    return progress < 1.0 ? smoothstep(0.0, 0.2, progress) * (1.0 - smoothstep(0.2, 1.0, progress)) : 0.0;
}

void main() {
    vec3 viewDir = normalize(relativePos);
    vec3 normal = normalize(cross(dFdx(relativePos), dFdy(relativePos)));
    if (dot(normal, viewDir) > 0.0) {normal = -normal;} //outward normal, toward the camera
    float viewCos = max(dot(viewDir, -normal), MIN_VIEW_COS); //1.0 : looking straight at the face

    vec3 particles = vec3(0.0);
    for (int i = 0; i < LAYERS; i++) {
        vec3 layerPoint = behind_face(LAYER_DEPTHS[i], viewDir, viewCos);
        vec2 uv = face_coords(layerPoint, normal) / LAYER_PERIODS[i] + LAYER_DRIFTS[i] * GameTime;
        float brightness = mix(PARTICLES_IDLE, PARTICLES_IN_WAVE, wave_light(layerPoint)); //the wave is read in the layer : it goes through the depth too
        particles += luminance(texture(Sampler0, uv).rgb) * LAYER_COLORS[i] * brightness;
    }

    vec2 nebulaUv = face_coords(behind_face(NEBULA_DEPTH, viewDir, viewCos), normal) / NEBULA_PERIOD + NEBULA_DRIFT * GameTime;
    vec3 voidColor = VOID_COLOR + luminance(texture(Sampler1, nebulaUv).rgb) * NEBULA_COLOR;

    //premultiplied alpha : the void partly hides what is behind, the particles are added on top
    vec3 color = voidColor * VOID_OPACITY + particles * PARTICLES_BRIGHTNESS;

    //fog : toward the fog color, with the same opacity as the void
    float fogValue = total_fog_value(sphericalVertexDistance, cylindricalVertexDistance, FogEnvironmentalStart, FogEnvironmentalEnd, FogRenderDistanceStart, FogRenderDistanceEnd);
    color = mix(color, FogColor.rgb * VOID_OPACITY, fogValue * FogColor.a);

    fragColor = vec4(color, VOID_OPACITY);
}
