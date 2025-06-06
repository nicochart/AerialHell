package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowTrollModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowTrollShadowLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowTrollRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;

public class ShadowTrollRender extends MobRenderer<ShadowTrollEntity, ShadowTrollRenderState, ShadowTrollModel>
{	
	private static final ResourceLocation TEXTURE_NORMAL = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/shadow_troll/shadow_troll.png");
	private static final ResourceLocation TEXTURE_INVERT = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/shadow_troll/shadow_troll_invert.png");

	public ShadowTrollRender(EntityRendererProvider.Context context)
	{
		super(context, new ShadowTrollModel(context.bakeLayer(AerialHellModelLayers.SHADOW_TROLL),true), 0.3F);
		this.addLayer(new ShadowTrollShadowLayer(this, new ShadowTrollModel(context.bakeLayer(AerialHellModelLayers.SHADOW_TROLL),false)));
	}

	@Override public ShadowTrollRenderState createRenderState() {return new ShadowTrollRenderState();}

	@Override public void extractRenderState(ShadowTrollEntity entity, ShadowTrollRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.texture = getTextureLocation(entity);
		renderState.isDisappearing = entity.isDisappearing();
	}

	public ResourceLocation getTextureLocation(ShadowTrollEntity entity)
	{
		if (Minecraft.getInstance().player.hasEffect(MobEffects.NIGHT_VISION)) {return TEXTURE_INVERT;}
		else {return TEXTURE_NORMAL;}
	}

	@Override public ResourceLocation getTextureLocation(ShadowTrollRenderState renderState) {return renderState.texture;}
}