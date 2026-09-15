package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteGolemHeadModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Helper.BeamRenderHelper;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem.VoluciteGolemHeadEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class VoluciteGolemHeadRender extends MobRenderer<VoluciteGolemHeadEntity, VoluciteGolemRenderState, VoluciteGolemHeadModel>
{
	private static String name = "volucite_golem";
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public VoluciteGolemHeadRender(EntityRendererProvider.Context context)
    {
        super(context, new VoluciteGolemHeadModel(context.bakeLayer(AerialHellModelLayers.VOLUCITE_GOLEM_HEAD)), 0.1f);
    }

    @Override public VoluciteGolemRenderState createRenderState() {return new VoluciteGolemRenderState();}

    @Override public void extractRenderState(VoluciteGolemHeadEntity entity, VoluciteGolemRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.getMaster() != null ? entity.getMaster().attackTimer : 0;
        renderState.eyePosition = entity.getEyePosition(partialTick);

        LivingEntity target = entity.getBeamAttackTarget();
        if (entity.isBeaming() && target != null && entity.getBeamEndPos() != null && entity.getPrevBeamEndPos() != null)
        {
            renderState.beamTargetPosition = BeamRenderHelper.getBeamTargetPosition(entity.getBeamEndPos(), entity.getPrevBeamEndPos(), partialTick);
            renderState.beamTexture = BeamRenderHelper.getBeamTextureLocation(entity.getBeamingPhase());
            renderState.maxBeamLength = entity.getMaxBeamLength();
        }
        else
        {
            renderState.beamTargetPosition = null;
            renderState.beamTexture = null;
            renderState.maxBeamLength = 0.0F;
        }
    }

    @Override public Identifier getTextureLocation(VoluciteGolemRenderState renderState) {return TEXTURE;}

    @Override public void submit(VoluciteGolemRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState)
    {
        super.submit(renderState, poseStack, nodeCollector, cameraRenderState);
        Vec3 vec3 = renderState.beamTargetPosition;
        if (vec3 != null)
        {
            poseStack.pushPose();
            poseStack.translate(0.0F, renderState.eyeHeight, 0.0F);

            BeamRenderHelper.renderBeam(poseStack, nodeCollector, vec3.subtract(renderState.eyePosition), renderState.beamTexture, renderState.maxBeamLength);
            poseStack.popPose();
        }
    }

    @Override public AABB getBoundingBoxForCulling(VoluciteGolemHeadEntity entity)
    {
        AABB box = super.getBoundingBoxForCulling(entity);
        if (entity.isBeaming() && entity.getBeamEndPos() != null)
        {
            return BeamRenderHelper.calculateBeamCullingBox(box, entity.getBeamStartPos(), entity.getBeamEndPos());
        }
        return box;
    }
}