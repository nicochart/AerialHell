package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenHealBeaconModel;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHealBeaconEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;

public class VoluciteWardenHealBeaconRender extends EntityRenderer<VoluciteWardenHealBeaconEntity, EntityRenderState>
{
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/heal_beacon.png");
    private final VoluciteWardenHealBeaconModel model;

    public VoluciteWardenHealBeaconRender(EntityRendererProvider.Context context)
    {
        super(context);
        this.shadowRadius = 1.5F;
        this.model = new VoluciteWardenHealBeaconModel(context.bakeLayer(AerialHellModelLayers.VOLUCITE_WARDEN_HEAL_BEACON));
    }

    @Override public EntityRenderState createRenderState() {return new EntityRenderState();}

    @Override public void submit(EntityRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        poseStack.pushPose();
        //blockbench modded entity export : x & y axis flipped, ground at y = 24 (like LivingEntityRenderer)
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.translate(0.0F, -1.5F, 0.0F);

        RenderType renderType = this.model.renderType(TEXTURE);
        submitNodeCollector.submitModelPart(this.model.frame, poseStack, renderType, renderState.lightCoords, OverlayTexture.NO_OVERLAY, null, -1, renderState.outlineColor);
        submitNodeCollector.submitModelPart(this.model.core, poseStack, renderType, LightCoordsUtil.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, null, -1, renderState.outlineColor); //glowing core (light volucite stone)
        poseStack.popPose();

        super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);
    }
}
