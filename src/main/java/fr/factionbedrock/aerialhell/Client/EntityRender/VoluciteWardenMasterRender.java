package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Helper.BeamRenderHelper;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenMasterRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartStatus;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.util.List;

public class VoluciteWardenMasterRender extends LivingEntityRenderer<VoluciteWardenEntity, VoluciteWardenMasterRenderState, EmptyModel<VoluciteWardenMasterRenderState>>
{
    private static final Identifier ARM_SEGMENTS_CONNECTION_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/arm_segment_connection.png");
    private static final float ARM_SEGMENTS_CONNECTION_THICKNESS = 1.0F;
    private static final float DEATH_BEAM_LENGTH = 32.0F;
    private static final int DEATH_BEAM_COLOR = new Color(89, 153, 202).getRGB();

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

        state.deathBeamVectors.clear();
        float deathBeamsProgress = entity.getDeathBeamsProgress(partialTick);
        if (deathBeamsProgress > 0.0F) {extractDeathBeamVectors(entity, state.deathBeamVectors, deathBeamsProgress, partialTick);}
    }

    //dying warden : beams from the core, appearing one after another (the last one at 75% progress) and slowly turning around the vertical axis
    //random directions seeded with the warden id : the same beams every frame
    private static void extractDeathBeamVectors(VoluciteWardenEntity entity, List<Vec3> beamVectors, float progress, float partialTick)
    {
        RandomSource random = RandomSource.create(entity.getId());
        float time = entity.timeDying + partialTick;
        int count = 16;
        for (int i = 0; i < count; i++)
        {
            Vec3 direction = new Vec3(random.nextGaussian(), random.nextGaussian(), random.nextGaussian()).normalize(); //uniform on a sphere
            float turnSpeed = Mth.nextFloat(random, -0.02F, 0.02F); //radians per tick
            float lengthFactor = Mth.clamp((progress - 0.75F * i / count) / 0.1F, 0.0F, 1.0F); //full length after 10% progress
            if (lengthFactor > 0.0F) {beamVectors.add(direction.yRot(time * turnSpeed).scale(DEATH_BEAM_LENGTH * lengthFactor));}
        }
    }

    @Override protected AABB getBoundingBoxForCulling(VoluciteWardenEntity entity, float partialTicks)
    {
        AABB box = super.getBoundingBoxForCulling(entity, partialTicks);
        if (entity.getDeathBeamsProgress(partialTicks) <= 0.0F) {return box;}

        Vec3 coreCenter = entity.getPosition(partialTicks).add(0.0D, VoluciteWardenEntity.CORE_CENTER_RELATIVE_HEIGHT, 0.0D); //death beams
        return box.minmax(new AABB(coreCenter, coreCenter).inflate(DEATH_BEAM_LENGTH));
    }

    private void extractArmPositions(List<VoluciteWardenEntity.ArmPartInfo> arm, List<Vec3> stateList, Vec3 masterPos, float partialTick)
    {
        for (VoluciteWardenEntity.ArmPartInfo armSegmentInfo : arm)
        {
            if (armSegmentInfo.getPart() != null && armSegmentInfo.getPart().getSelf() instanceof VoluciteWardenArmSegmentEntity armSegment && this.isSegmentValidForConnection(armSegment))
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

    private boolean isSegmentValidForConnection(VoluciteWardenArmSegmentEntity armSegment)
    {
        return armSegment.isAlive() && armSegment.getPartStatus() == PartStatus.ATTACHED;
    }

    @Override public void submit(VoluciteWardenMasterRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);

        // Récupération de la lumière et de l'overlay de dégâts depuis le state
        int light = renderState.lightCoords;
        int overlay = OverlayTexture.pack(OverlayTexture.u(this.getWhiteOverlayProgress(renderState)), OverlayTexture.v(renderState.hasRedOverlay));

        renderArmConnections(renderState.leftArmPositions, poseStack, submitNodeCollector, light, overlay);
        renderArmConnections(renderState.rightArmPositions, poseStack, submitNodeCollector, light, overlay);

        if (!renderState.deathBeamVectors.isEmpty())
        {
            float deathBeamScale = 3.0F;
            poseStack.pushPose();
            poseStack.translate(0.0F, VoluciteWardenEntity.CORE_CENTER_RELATIVE_HEIGHT, 0.0F);
            for (Vec3 beamVector : renderState.deathBeamVectors) {BeamRenderHelper.renderBeam(poseStack, submitNodeCollector, beamVector, BeamRenderHelper.getBeamTextureLocation(), DEATH_BEAM_COLOR, DEATH_BEAM_LENGTH, deathBeamScale, 0.0F);}
            poseStack.popPose();
        }
    }

    private void renderArmConnections(List<Vec3> positions, PoseStack poseStack, SubmitNodeCollector nodeCollector, int light, int overlay)
    {
        for (int i = 0; i < positions.size() - 1; i++)
        {
            @Nullable Vec3 start = positions.get(i);
            @Nullable Vec3 end = positions.get(i + 1);

            if (start != null && end != null) {renderCuboid(poseStack, nodeCollector, start, end, light, overlay);}
        }
    }

    private void renderCuboid(PoseStack poseStack, SubmitNodeCollector nodeCollector, @NotNull Vec3 start, @NotNull Vec3 end, int light, int overlay)
    {
        Vec3 diff = end.subtract(start);
        float length = (float) diff.length();
        Vec3 dir = diff.normalize();

        poseStack.pushPose();
        poseStack.translate(start.x, start.y, start.z);

        float xRot = (float)Math.acos(dir.y);
        float yRot = ((float)Math.PI / 2F) - (float)Math.atan2(dir.z, dir.x);
        poseStack.rotate(Axis.YP.rotationDegrees(yRot * (180F / (float)Math.PI)));
        poseStack.rotate(Axis.XP.rotationDegrees(xRot * (180F / (float)Math.PI)));

        nodeCollector.submitCustomGeometry(poseStack, RenderTypes.entityCutout(ARM_SEGMENTS_CONNECTION_TEXTURE), (pose, consumer) ->
        {
            float w = ARM_SEGMENTS_CONNECTION_THICKNESS / 2.0F;
            float vMax = length;

            addQuad(consumer, pose, -w, w, 0, length, w, w, vMax, light, overlay);
            addQuad(consumer, pose, w, -w, 0, length, -w, -w, vMax, light, overlay);
            addQuad(consumer, pose, -w, -w, 0, length, -w, w, vMax, light, overlay);
            addQuad(consumer, pose, w, w, 0, length, w, -w, vMax, light, overlay);
        });

        poseStack.popPose();
    }

    private void addQuad(VertexConsumer consumer, PoseStack.Pose pose, float x1, float x2, float yMin, float yMax, float z1, float z2, float vMax, int light, int overlay)
    {
        vertex(consumer, pose, x1, yMin, z1, 0.0F, vMax, light, overlay);
        vertex(consumer, pose, x2, yMin, z2, 1.0F, vMax, light, overlay);
        vertex(consumer, pose, x2, yMax, z2, 1.0F, 0.0F, light, overlay);
        vertex(consumer, pose, x1, yMax, z1, 0.0F, 0.0F, light, overlay);
    }

    private void vertex(VertexConsumer consumer, PoseStack.Pose pose, float x, float y, float z, float u, float v, int light, int overlay)
    {
        consumer.addVertex(pose, x, y, z).setColor(255, 255, 255, 255).setUv(u, v).setOverlay(overlay).setLight(light).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}