package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialHellGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolemEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class VoluciteGolemRender extends MobRenderer<VoluciteGolemEntity, AerialHellGolemRenderState, VoluciteGolemModel>
{
	private static String name = "volucite_golem";
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");
    private static final Identifier GUARDIAN_BEAM_LOCATION = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/beam.png");
    private static final RenderType BEAM_RENDER_TYPE = RenderTypes.entityCutoutNoCull(GUARDIAN_BEAM_LOCATION);

    public VoluciteGolemRender(EntityRendererProvider.Context context)
    {
        super(context, new VoluciteGolemModel(context.bakeLayer(AerialHellModelLayers.VOLUCITE_GOLEM)), 0.6f);
    }

    @Override public AerialHellGolemRenderState createRenderState() {return new AerialHellGolemRenderState();}

    @Override public void extractRenderState(VoluciteGolemEntity entity, AerialHellGolemRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.attackTimer;
        renderState.eyePosition = entity.getEyePosition(partialTick);

        LivingEntity target = entity.getActiveAttackTarget();
        if (entity.isBeaming() && target != null && entity.getBeamEndPos() != null && entity.getPrevBeamEndPos() != null)
        {
            renderState.attackScale = 1.0F; //entity.getAttackAnimationScale(partialTick);
            renderState.attackTime = 1.0F; //entity.getClientSideAttackTime() + partialTick;
            renderState.beamTargetPosition = this.getPosition(entity.getBeamEndPos(), entity.getPrevBeamEndPos(), partialTick);
        }
        else
        {
            renderState.beamTargetPosition = null;
        }
    }

    private Vec3 getPosition(Vec3 targetPos, Vec3 prevTargetPos, float partialTick)
    {
        double d0 = Mth.lerp(partialTick, prevTargetPos.x, targetPos.x);
        double d1 = Mth.lerp(partialTick, prevTargetPos.y, targetPos.y);
        double d2 = Mth.lerp(partialTick, prevTargetPos.z, targetPos.z);
        return new Vec3(d0, d1, d2);
    }

    @Override public Identifier getTextureLocation(AerialHellGolemRenderState renderState) {return TEXTURE;}

    public void submit(AerialHellGolemRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState)
    {
        super.submit(renderState, poseStack, nodeCollector, cameraRenderState);
        Vec3 vec3 = renderState.beamTargetPosition;
        if (vec3 != null)
        {
            float f = renderState.attackTime * 0.5F % 1.0F;
            poseStack.pushPose();
            poseStack.translate(0.0F, renderState.eyeHeight, 0.0F);
            renderBeam(poseStack, nodeCollector, vec3.subtract(renderState.eyePosition), renderState.attackTime, renderState.attackScale, f);
            poseStack.popPose();
        }
    }

    private static void renderBeam(PoseStack poseStack, SubmitNodeCollector nodeCollector, Vec3 beamVector, float attackTime, float scale, float animationTime)
    {
        float y = (float)(beamVector.length() + (double)1.0F);
        beamVector = beamVector.normalize();
        float xRotFactor = (float)Math.acos(beamVector.y);
        float yRotFactor = ((float)Math.PI / 2F) - (float)Math.atan2(beamVector.z, beamVector.x);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRotFactor * (180F / (float)Math.PI)));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRotFactor * (180F / (float)Math.PI)));
        float attackTimeFactor = attackTime * 0.05F * -1.5F;
        int r = 255, g = 255, b = 255;
        //horizontal coords
        float hx1 = Mth.cos((double)(attackTimeFactor + (float)Math.PI)) * 0.2F;
        float hz1 = Mth.sin((double)(attackTimeFactor + (float)Math.PI)) * 0.2F;
        float hx2 = Mth.cos((double)(attackTimeFactor + 0.0F)) * 0.2F;
        float hz2 = Mth.sin((double)(attackTimeFactor + 0.0F)) * 0.2F;
        //vertical coords
        float vx1 = Mth.cos((double)(attackTimeFactor + ((float)Math.PI / 2F))) * 0.2F;
        float vz1 = Mth.sin((double)(attackTimeFactor + ((float)Math.PI / 2F))) * 0.2F;
        float vx2 = Mth.cos((double)(attackTimeFactor + ((float)Math.PI * 1.5F))) * 0.2F;
        float vz2 = Mth.sin((double)(attackTimeFactor + ((float)Math.PI * 1.5F))) * 0.2F;

        float pixelSize = 0.0625F;

        nodeCollector.submitCustomGeometry(poseStack, BEAM_RENDER_TYPE, (pose, consumer) ->
        {
            float offset = pixelSize / 1.3F;
            Vec3 horizontalMinCoords = new Vec3(hx1, 0.0F, hz1);
            Vec3 horizontalMaxCoords = new Vec3(hx2, y, hz2);
            Vec3 verticalMinCoords = new Vec3(vx1, 0.0F, vz1);
            Vec3 verticalMaxCoords = new Vec3(vx2, y, vz2);

            //horizontal bottom
            rectangle(consumer, pose, horizontalMinCoords.add(0.0D, 0.0D, -offset), horizontalMaxCoords.add(0.0D, 0.0D, -offset), r, g, b);
            //horizontal top
            rectangle(consumer, pose, horizontalMinCoords.add(0.0D, 0.0D, offset), horizontalMaxCoords.add(0.0D, 0.0D, offset), r, g, b);
            //vertical right
            rectangle(consumer, pose, verticalMinCoords.add(-offset, 0.0D, 0.0D), verticalMaxCoords.add(-offset, 0.0D, 0.0D), r, g, b);
            //vertical left
            rectangle(consumer, pose, verticalMinCoords.add(offset, 0.0D, 0.0D), verticalMaxCoords.add(offset, 0.0D, 0.0D), r, g, b);
        });
    }

    @Override public AABB getBoundingBoxForCulling(VoluciteGolemEntity entity)
    {
        AABB box = super.getBoundingBoxForCulling(entity);

        if (entity.isBeaming() && entity.getBeamEndPos() != null)
        {
            Vec3 beamStart = entity.getBeamStartPos();
            Vec3 beamEnd = entity.getBeamEndPos();

            AABB beamBox = new AABB(beamStart, beamEnd).inflate(1.0);
            return box.minmax(beamBox);
        }
        return box;
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
        consumer.addVertex(pose, x, y, z).setColor(red, green, blue, 255).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}