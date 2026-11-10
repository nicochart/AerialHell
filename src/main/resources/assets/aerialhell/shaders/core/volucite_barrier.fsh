#version 330
#extension GL_ARB_separate_shader_objects : require

#include <minecraft:fog.glsl>
#include <minecraft:globals.glsl>

//volucite barrier : depth illusion, like looking through a window at particles far behind the face
//each particle layer is a virtual plane at some depth behind the visible face : the view ray is extended to it, and the particles are read where the ray crosses it
//the layers are anchored in the world : they move with the camera (parallax, deeper layers move less), and slowly drift
//output : premultiplied alpha (see AerialHellRenderPipelines.VOLUCITE_BARRIER) : alpha = how much the dark void hides what is behind, the particles are added on top

uniform sampler2D Sampler0; //particles (vanilla end portal stars : sparse 1 pixel dots)
uniform sampler2D Sampler1; //nebula, behind the last layer (vanilla end sky)

layout(location = 0) in vec3 relativePos;
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

void main() {
    vec3 viewDir = normalize(relativePos);
    vec3 normal = normalize(cross(dFdx(relativePos), dFdy(relativePos)));
    if (dot(normal, viewDir) > 0.0) {normal = -normal;} //outward normal, toward the camera
    float viewCos = max(dot(viewDir, -normal), MIN_VIEW_COS); //1.0 : looking straight at the face

    vec3 particles = vec3(0.0);
    for (int i = 0; i < LAYERS; i++) {
        vec2 uv = face_coords(behind_face(LAYER_DEPTHS[i], viewDir, viewCos), normal) / LAYER_PERIODS[i] + LAYER_DRIFTS[i] * GameTime;
        particles += luminance(texture(Sampler0, uv).rgb) * LAYER_COLORS[i];
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
