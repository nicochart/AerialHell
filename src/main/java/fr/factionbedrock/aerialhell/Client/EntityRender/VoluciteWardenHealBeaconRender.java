package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenHealBeaconModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Helper.BeamRenderHelper;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.VoluciteWardenHealBeaconStateLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenHealBeaconRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHealBeaconEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class VoluciteWardenHealBeaconRender extends MobRenderer<VoluciteWardenHealBeaconEntity, VoluciteWardenHealBeaconRenderState, VoluciteWardenHealBeaconModel>
{
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/heal_beacon/heal_beacon.png");
    private static final float HEAL_BEAM_SCALE = 1.5F;

    public VoluciteWardenHealBeaconRender(EntityRendererProvider.Context context)
    {
        super(context, new VoluciteWardenHealBeaconModel(context.bakeLayer(AerialHellModelLayers.VOLUCITE_WARDEN_HEAL_BEACON)), 1.5F);
        this.addLayer(new VoluciteWardenHealBeaconStateLayer(this));
    }

    @Override public VoluciteWardenHealBeaconRenderState createRenderState() {return new VoluciteWardenHealBeaconRenderState();}

    @Override public void extractRenderState(VoluciteWardenHealBeaconEntity entity, VoluciteWardenHealBeaconRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.beaconState = entity.getBeaconState();

        @Nullable Vec3 healBeamTargetPos = getHealBeamTargetPos(entity, partialTick);
        renderState.healBeamVector = healBeamTargetPos != null ? healBeamTargetPos.subtract(entity.getCoreCenterPos(partialTick)) : null;
    }

    @Override public Identifier getTextureLocation(VoluciteWardenHealBeaconRenderState renderState) {return TEXTURE;}

    @Override protected void setupRotations(VoluciteWardenHealBeaconRenderState renderState, PoseStack poseStack, float bodyRot, float scale) {} //block-like : no body rotation, no death fall

    @Override public void submit(VoluciteWardenHealBeaconRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);

        //heal beam, from beacon core to warden core
        if (renderState.healBeamVector != null)
        {
            poseStack.pushPose();
            poseStack.translate(0.0F, VoluciteWardenHealBeaconEntity.CORE_RELATIVE_HEIGHT, 0.0F);
            BeamRenderHelper.renderBeam(poseStack, submitNodeCollector, renderState.healBeamVector, BeamRenderHelper.getBeamTextureLocation(), VoluciteWardenHealBeaconEntity.HEALING_COLOR, VoluciteWardenHealBeaconEntity.HEAL_RANGE, HEAL_BEAM_SCALE, 0.0F);
            poseStack.popPose();
        }
    }

    @Override protected AABB getBoundingBoxForCulling(VoluciteWardenHealBeaconEntity entity, float partialTicks)
    {
        AABB box = super.getBoundingBoxForCulling(entity, partialTicks);

        //-- adapting culling bounding box to heal beam --
        @Nullable Vec3 healBeamTargetPos = getHealBeamTargetPos(entity, partialTicks);
        if (healBeamTargetPos != null) {return BeamRenderHelper.calculateBeamCullingBox(box, entity.getCoreCenterPos(partialTicks), healBeamTargetPos, HEAL_BEAM_SCALE);}
        else {return box;}
    }

    //warden core, if the beacon is healing it
    @Nullable private static Vec3 getHealBeamTargetPos(VoluciteWardenHealBeaconEntity entity, float partialTick)
    {
        return entity.isBeaconHealing() && entity.getSyncedTarget() instanceof VoluciteWardenEntity warden ? VoluciteWardenHealBeaconEntity.getWardenCoreCenterPos(warden, partialTick) : null;
    }
}
