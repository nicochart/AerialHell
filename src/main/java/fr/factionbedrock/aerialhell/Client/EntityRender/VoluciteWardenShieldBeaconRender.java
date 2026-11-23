package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenShieldBeaconModel;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenShieldBeaconEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class VoluciteWardenShieldBeaconRender extends MobRenderer<VoluciteWardenShieldBeaconEntity, LivingEntityRenderState, VoluciteWardenShieldBeaconModel>
{
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/arm_lights.png"); //arm segment tail texture

    public VoluciteWardenShieldBeaconRender(EntityRendererProvider.Context context) {super(context, new VoluciteWardenShieldBeaconModel(context.bakeLayer(AerialHellModelLayers.VOLUCITE_WARDEN_SHIELD_BEACON)), 0.5F);}

    @Override public LivingEntityRenderState createRenderState() {return new LivingEntityRenderState();}

    @Override public Identifier getTextureLocation(LivingEntityRenderState renderState) {return TEXTURE;}

    @Override protected void setupRotations(LivingEntityRenderState renderState, PoseStack poseStack, float bodyRot, float entityScale) {} //block-like
}
