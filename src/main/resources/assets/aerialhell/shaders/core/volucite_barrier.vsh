#version 330
#extension GL_ARB_separate_shader_objects : require

#include <minecraft:fog.glsl>
#include <minecraft:globals.glsl>
#include <minecraft:projection.glsl>
#include <minecraft:dynamictransforms.glsl>

//volucite barrier : gives the fragment shader the view ray direction (position relative to the rendered eye) and the world position of the face (see volucite_barrier.fsh)

layout(location = 0) in vec3 Position; //camera relative (block entity renderer)

layout(location = 0) out vec3 relativePos; //relative to the eye the image is drawn from (see rendered_eye)
layout(location = 1) out vec3 worldPos;
layout(location = 2) out float sphericalVertexDistance;
layout(location = 3) out float cylindricalVertexDistance;

//camera relative position of the eye the image is really drawn from. it is not always the camera position : the projection matrix also holds the view bobbing (it moves the eye a little), the hurt tilt and the nausea
//the eye is the only point sent to x = y = w = 0 in clip space : found by inverting the whole transform, whatever it holds
vec3 rendered_eye() {
    vec4 eye = inverse(ProjMat * ModelViewMat) * vec4(0.0, 0.0, 1.0, 0.0);
    return abs(eye.w) > 1.0e-6 ? eye.xyz / eye.w : vec3(0.0); //w = 0 : orthographic projection, no eye point, the camera position is kept
}

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    relativePos = Position - rendered_eye(); //else the particle layers follow the view bobbing instead of staying behind the face
    //camera pos = CameraBlockPos - CameraOffset. world position wrapped every 1024 blocks (float precision far from the world center) : layer periods must divide 1024
    worldPos = Position + vec3(CameraBlockPos & 1023) - CameraOffset;
    sphericalVertexDistance = fog_spherical_distance(Position);
    cylindricalVertexDistance = fog_cylindrical_distance(Position);
}
