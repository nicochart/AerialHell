package fr.factionbedrock.aerialhell.Client.EntityRender.Helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.AI.BeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem.VoluciteGolemHeadEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class BeamRenderHelper
{
    private static final Identifier BEAM_NORMAL = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/attack/beam.png");
    private static final Identifier BEAM_LOAD = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/attack/beam_load.png");
    private static final Identifier BEAM_OVERHEAT = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/attack/beam_overheat.png");

    public static RenderType getBeamRenderType(Identifier textureLocation)
    {
        return RenderTypes.entityCutout(textureLocation);
    }

    public static Vec3 getBeamTargetPosition(Vec3 targetPos, Vec3 prevTargetPos, float partialTick)
    {
        double d0 = Mth.lerp(partialTick, prevTargetPos.x, targetPos.x);
        double d1 = Mth.lerp(partialTick, prevTargetPos.y, targetPos.y);
        double d2 = Mth.lerp(partialTick, prevTargetPos.z, targetPos.z);
        return new Vec3(d0, d1, d2);
    }

    @Nullable public static Identifier getBeamTextureLocation(int beamingPhase)
    {
        return switch (beamingPhase)
        {
            case 1 -> BEAM_LOAD;
            case 2 -> BEAM_NORMAL;
            case 3 -> BEAM_OVERHEAT;
            default -> null;
        };
    }

    public static void renderBeam(PoseStack poseStack, SubmitNodeCollector nodeCollector, Vec3 beamVector, Identifier textureLocation, float maxBeamLength)
    {
        float y = (float)(beamVector.length());
        beamVector = beamVector.normalize();
        float xRotFactor = (float)Math.acos(beamVector.y);
        float yRotFactor = ((float)Math.PI / 2F) - (float)Math.atan2(beamVector.z, beamVector.x);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRotFactor * (180F / (float)Math.PI)));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRotFactor * (180F / (float)Math.PI)));
        int r = 255, g = 255, b = 255;
        float scale = textureLocation == BEAM_LOAD ? 0.3F : 1.0F;

        nodeCollector.submitCustomGeometry(poseStack, getBeamRenderType(textureLocation), (pose, consumer) ->
        {
            float rectangleOffset = 0.05F * scale;
            float size = 0.2F * scale;

            float segmentPerUnit = 1.0F;
            int maxSegments = (int) (maxBeamLength * segmentPerUnit);
            int segmentCount = Mth.clamp((int)(y * segmentPerUnit), 1, maxSegments);
            double segmentLength = y / segmentCount;

            for (int i = 0; i < segmentCount; i++)
            {
                double yMin = i * segmentLength;
                double yMax = (i + 1) * segmentLength;
                Vec3 horizontalMinCoords = new Vec3(-size, yMin, 0.0F);
                Vec3 horizontalMaxCoords = new Vec3(size, yMax, 0.0F);
                Vec3 verticalMinCoords = new Vec3(0.0F, yMin, -size);
                Vec3 verticalMaxCoords = new Vec3(0.0F, yMax, size);

                //horizontal bottom
                rectangle(consumer, pose, horizontalMinCoords.add(0.0D, 0.0D, -rectangleOffset), horizontalMaxCoords.add(0.0D, 0.0D, -rectangleOffset), r, g, b);
                //horizontal top
                rectangle(consumer, pose, horizontalMinCoords.add(0.0D, 0.0D, rectangleOffset), horizontalMaxCoords.add(0.0D, 0.0D, rectangleOffset), r, g, b);
                //vertical right
                rectangle(consumer, pose, verticalMinCoords.add(-rectangleOffset, 0.0D, 0.0D), verticalMaxCoords.add(-rectangleOffset, 0.0D, 0.0D), r, g, b);
                //vertical left
                rectangle(consumer, pose, verticalMinCoords.add(rectangleOffset, 0.0D, 0.0D), verticalMaxCoords.add(rectangleOffset, 0.0D, 0.0D), r, g, b);
            }
        });
    }

    public static AABB calculateBeamCullingBox(AABB entityBox, @NotNull Vec3 beamStart, @NotNull Vec3 beamEnd)
    {
        AABB beamBox = new AABB(beamStart, beamEnd).inflate(1.0);
        return entityBox.minmax(beamBox);
    }

    private static void rectangle(VertexConsumer consumer, PoseStack.Pose pose, Vec3 minCoords, Vec3 maxCoords, int r, int g, int b)
    {
        vertex(consumer, pose, (float) minCoords.x, (float) maxCoords.y, (float) minCoords.z, r, g, b, 1.0F, 0.0F);
        vertex(consumer, pose, (float) minCoords.x, (float) minCoords.y, (float) minCoords.z, r, g, b, 1.0F, 1.0F);
        vertex(consumer, pose, (float) maxCoords.x, (float) minCoords.y, (float) maxCoords.z, r, g, b, 0.0F, 1.0F);
        vertex(consumer, pose, (float) maxCoords.x, (float) maxCoords.y, (float) maxCoords.z, r, g, b, 0.0F, 0.0F);
    }

    private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, float x, float y, float z, int red, int green, int blue, float u, float v)
    {
        consumer.addVertex(pose, x, y, z).setColor(red, green, blue, 255).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightCoordsUtil.FULL_BRIGHT).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}