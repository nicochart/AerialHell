package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Helper.BeamRenderHelper;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenMasterRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VoluciteWardenMasterRender extends LivingEntityRenderer<VoluciteWardenEntity, VoluciteWardenMasterRenderState, EmptyModel<VoluciteWardenMasterRenderState>>
{
    private static final Identifier ARM_SEGMENTS_CONNECTION_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/arm_segment_connection.png");
    private static final float ARM_SEGMENTS_CONNECTION_THICKNESS = 1.0F;

    //--- things from EmptyRender ---
    public VoluciteWardenMasterRender(EntityRendererProvider.Context context)
    {
        super(context, new EmptyModel<>(context.bakeLayer(AerialHellModelLayers.EMPTY)), 0.0F);
    }

    @Override @NotNull public Identifier getTextureLocation(VoluciteWardenMasterRenderState renderState) {return MissingTextureAtlasSprite.getLocation();}
    //-------------------------------

    @Override protected void submitNameDisplay(VoluciteWardenMasterRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {}

    @Override public VoluciteWardenMasterRenderState createRenderState() {return new VoluciteWardenMasterRenderState();}

    @Override public void extractRenderState(VoluciteWardenEntity entity, VoluciteWardenMasterRenderState state, float partialTick)
    {
        super.extractRenderState(entity, state, partialTick);

        state.leftArmPositions.clear();
        state.rightArmPositions.clear();

        double masterX = Mth.lerp(partialTick, entity.xo, entity.getX());
        double masterY = Mth.lerp(partialTick, entity.yo, entity.getY());
        double masterZ = Mth.lerp(partialTick, entity.zo, entity.getZ());
        Vec3 masterPos = new Vec3(masterX, masterY, masterZ);

        extractArmPositions(entity.getLeftArm(), state.leftArmPositions, masterPos, partialTick);
        extractArmPositions(entity.getRightArm(), state.rightArmPositions, masterPos, partialTick);
    }

    private void extractArmPositions(List<VoluciteWardenEntity.ArmPartInfo> arm, List<Vec3> stateList, Vec3 masterPos, float partialTick)
    {
        for (VoluciteWardenEntity.ArmPartInfo armSegmentInfo : arm)
        {
            if (armSegmentInfo.getPart() != null && armSegmentInfo.getPart().getSelf() instanceof VoluciteWardenArmEntity armSegment && this.isSegmentValidForConnection(armSegment))
            {
                double x = Mth.lerp(partialTick, armSegment.xo, armSegment.getX());
                double y = Mth.lerp(partialTick, armSegment.yo, armSegment.getY());
                double z = Mth.lerp(partialTick, armSegment.zo, armSegment.getZ());

                y += armSegment.getBbHeight() / 2.0f;

                stateList.add(new Vec3(x, y, z).subtract(masterPos));
                continue;
            }
            stateList.add(null);
        }
    }

    private boolean isSegmentValidForConnection(VoluciteWardenArmEntity armSegment)
    {
        return armSegment.isAlive();
    }

    @Override public void submit(VoluciteWardenMasterRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);

        int light = renderState.lightCoords;

        renderArmConnections(renderState.leftArmPositions, poseStack, submitNodeCollector, light);
        renderArmConnections(renderState.rightArmPositions, poseStack, submitNodeCollector, light);
    }

    private void renderArmConnections(List<Vec3> positions, PoseStack poseStack, SubmitNodeCollector nodeCollector, int light)
    {
        for (int i = 0; i < positions.size() - 1; i++)
        {
            @Nullable Vec3 start = positions.get(i);
            @Nullable Vec3 end = positions.get(i + 1);

            if (start != null && end != null) {renderCuboid(poseStack, nodeCollector, start, end, light);}
        }
    }

    private void renderCuboid(PoseStack poseStack, SubmitNodeCollector nodeCollector, @NotNull Vec3 start, @NotNull Vec3 end, int light)
    {
        Vec3 diff = end.subtract(start);
        float length = (float) diff.length();
        Vec3 dir = diff.normalize();

        poseStack.pushPose();
        poseStack.translate(start.x, start.y, start.z);

        float xRot = (float)Math.acos(dir.y);
        float yRot = ((float)Math.PI / 2F) - (float)Math.atan2(dir.z, dir.x);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot * (180F / (float)Math.PI)));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot * (180F / (float)Math.PI)));

        nodeCollector.submitCustomGeometry(poseStack, BeamRenderHelper.getBeamRenderType(ARM_SEGMENTS_CONNECTION_TEXTURE), (pose, consumer) ->
        {
            float w = ARM_SEGMENTS_CONNECTION_THICKNESS / 2.0F;

            float vMax = length;

            addQuad(consumer, pose, -w, w, 0, length, w, w, vMax, light);
            addQuad(consumer, pose, w, -w, 0, length, -w, -w, vMax, light);
            addQuad(consumer, pose, -w, -w, 0, length, -w, w, vMax, light);
            addQuad(consumer, pose, w, w, 0, length, w, -w, vMax, light);
        });

        poseStack.popPose();
    }

    private void addQuad(VertexConsumer consumer, PoseStack.Pose pose, float x1, float x2, float yMin, float yMax, float z1, float z2, float vMax, int light)
    {
        vertex(consumer, pose, x1, yMin, z1, 0.0F, vMax, light);
        vertex(consumer, pose, x2, yMin, z2, 1.0F, vMax, light);
        vertex(consumer, pose, x2, yMax, z2, 1.0F, 0.0F, light);
        vertex(consumer, pose, x1, yMax, z1, 0.0F, 0.0F, light);
    }

    private void vertex(VertexConsumer consumer, PoseStack.Pose pose, float x, float y, float z, float u, float v, int light)
    {
        consumer.addVertex(pose, x, y, z).setColor(255, 255, 255, 255).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}