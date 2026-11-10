#version 330
#extension GL_ARB_separate_shader_objects : require

#include <minecraft:fog.glsl>
#include <minecraft:globals.glsl>
#include <minecraft:projection.glsl>
#include <minecraft:dynamictransforms.glsl>

//volucite barrier : gives the fragment shader the view ray direction (camera relative position) and the world position of the face (see volucite_barrier.fsh)

layout(location = 0) in vec3 Position; //camera relative (block entity renderer)

layout(location = 0) out vec3 relativePos;
layout(location = 1) out vec3 worldPos;
layout(location = 2) out float sphericalVertexDistance;
layout(location = 3) out float cylindricalVertexDistance;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    relativePos = Position;
    //camera pos = CameraBlockPos - CameraOffset. world position wrapped every 1024 blocks (float precision far from the world center) : layer periods must divide 1024
    worldPos = Position + vec3(CameraBlockPos & 1023) - CameraOffset;
    sphericalVertexDistance = fog_spherical_distance(Position);
    cylindricalVertexDistance = fog_cylindrical_distance(Position);
}
