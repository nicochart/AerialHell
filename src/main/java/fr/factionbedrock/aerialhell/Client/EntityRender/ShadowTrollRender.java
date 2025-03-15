package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowTrollModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowTrollShadowLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowTrollRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;

public class ShadowTrollRender extends MobEntityRenderer<ShadowTrollEntity, ShadowTrollRenderState, ShadowTrollModel>
{	
	private static final Identifier TEXTURE_NORMAL = Identifier.of(AerialHell.MODID, "textures/entity/shadow_troll/shadow_troll.png");
	private static final Identifier TEXTURE_INVERT = Identifier.of(AerialHell.MODID, "textures/entity/shadow_troll/shadow_troll_invert.png");

	public ShadowTrollRender(EntityRendererFactory.Context context)
	{
		super(context, new ShadowTrollModel(context.getPart(AerialHellModelLayers.SHADOW_TROLL),true), 0.3F);
		this.addFeature(new ShadowTrollShadowLayer(this, new ShadowTrollModel(context.getPart(AerialHellModelLayers.SHADOW_TROLL),false)));
	}

	@Override public ShadowTrollRenderState createRenderState() {return new ShadowTrollRenderState();}

	@Override public void updateRenderState(ShadowTrollEntity entity, ShadowTrollRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.texture = getTexture(entity);
		renderState.isDisappearing = entity.isDisappearing();
	}

	public Identifier getTexture(ShadowTrollEntity entity)
	{
		if (MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {return TEXTURE_INVERT;}
		else {return TEXTURE_NORMAL;}
	}

	@Override public Identifier getTexture(ShadowTrollRenderState renderState) {return renderState.texture;}
}