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
        float f1 = (float)Math.acos(beamVector.y);
        float f2 = ((float)Math.PI / 2F) - (float)Math.atan2(beamVector.z, beamVector.x);
        poseStack.mulPose(Axis.YP.rotationDegrees(f2 * (180F / (float)Math.PI)));
        poseStack.mulPose(Axis.XP.rotationDegrees(f1 * (180F / (float)Math.PI)));
        float f3 = attackTime * 0.05F * -1.5F;
        float f4 = scale * scale;
        int r = 255;//64 + (int)(f4 * 191.0F);
        int g = 255;//32 + (int)(f4 * 191.0F);
        int b = 255;//128 - (int)(f4 * 64.0F);
        float f5 = 0.2F;
        float f6 = 0.282F;
        float f7 = Mth.cos((double)(f3 + 2.3561945F)) * 0.282F;
        float f8 = Mth.sin((double)(f3 + 2.3561945F)) * 0.282F;
        float f9 = Mth.cos((double)(f3 + ((float)Math.PI / 4F))) * 0.282F;
        float f10 = Mth.sin((double)(f3 + ((float)Math.PI / 4F))) * 0.282F;
        float f11 = Mth.cos((double)(f3 + 3.926991F)) * 0.282F;
        float f12 = Mth.sin((double)(f3 + 3.926991F)) * 0.282F;
        float f13 = Mth.cos((double)(f3 + 5.4977875F)) * 0.282F;
        float f14 = Mth.sin((double)(f3 + 5.4977875F)) * 0.282F;
        float x1 = Mth.cos((double)(f3 + (float)Math.PI)) * 0.2F;
        float z1 = Mth.sin((double)(f3 + (float)Math.PI)) * 0.2F;
        float x2 = Mth.cos((double)(f3 + 0.0F)) * 0.2F;
        float z2 = Mth.sin((double)(f3 + 0.0F)) * 0.2F;
        float x3 = Mth.cos((double)(f3 + ((float)Math.PI / 2F))) * 0.2F;
        float z3 = Mth.sin((double)(f3 + ((float)Math.PI / 2F))) * 0.2F;
        float x4 = Mth.cos((double)(f3 + ((float)Math.PI * 1.5F))) * 0.2F;
        float z4 = Mth.sin((double)(f3 + ((float)Math.PI * 1.5F))) * 0.2F;
        float f23 = 0.0F;
        float f24 = 0.4999F;
        float f25 = -1.0F + animationTime;
        float f26 = f25 + y * 2.5F;

        float pixelSize = 0.0625F;

        nodeCollector.submitCustomGeometry(poseStack, BEAM_RENDER_TYPE, (pose, consumer) ->
        {
            float offset = pixelSize;
            //horizontal bottom
            vertex(consumer, pose, x1, y, z1 - offset, r, g, b, 0.5F, f26);
            vertex(consumer, pose, x1, 0.0F, z1 - offset, r, g, b, 0.5F, f25);
            vertex(consumer, pose, x2, 0.0F, z2 - offset, r, g, b, 0.0F, f25);
            vertex(consumer, pose, x2, y, z2 - offset, r, g, b, 0.0F, f26);
            //horizontal top
            vertex(consumer, pose, x1, y, z1 + offset, r, g, b, 0.5F, f26);
            vertex(consumer, pose, x1, 0.0F, z1 + offset, r, g, b, 0.5F, f25);
            vertex(consumer, pose, x2, 0.0F, z2 + offset, r, g, b, 0.0F, f25);
            vertex(consumer, pose, x2, y, z2 + offset, r, g, b, 0.0F, f26);
            //vertical right
            vertex(consumer, pose, x3 - offset, y, z3, r, g, b, 0.5F, f26);
            vertex(consumer, pose, x3 - offset, 0.0F, z3, r, g, b, 0.5F, f25);
            vertex(consumer, pose, x4 - offset, 0.0F, z4, r, g, b, 0.0F, f25);
            vertex(consumer, pose, x4 - offset, y, z4, r, g, b, 0.0F, f26);
            //vertical left
            vertex(consumer, pose, x3 + offset, y, z3, r, g, b, 0.5F, f26);
            vertex(consumer, pose, x3 + offset, 0.0F, z3, r, g, b, 0.5F, f25);
            vertex(consumer, pose, x4 + offset, 0.0F, z4, r, g, b, 0.0F, f25);
            vertex(consumer, pose, x4 + offset, y, z4, r, g, b, 0.0F, f26);

            //beam end
            //float f27 = Mth.floor(attackTime) % 2 == 0 ? 0.5F : 0.0F;
            //vertex(p_433082_, p_432958_, f7, f, f8, r, g, b, 0.5F, f27 + 0.5F);
            //vertex(p_433082_, p_432958_, f9, f, f10, r, g, b, 1.0F, f27 + 0.5F);
            //vertex(p_433082_, p_432958_, f13, f, f14, r, g, b, 1.0F, f27);
            //vertex(p_433082_, p_432958_, f11, f, f12, r, g, b, 0.5F, f27);
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

    private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, float x, float y, float z, int red, int green, int blue, float u, float v)
    {
        consumer.addVertex(pose, x, y, z).setColor(red, green, blue, 255).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}