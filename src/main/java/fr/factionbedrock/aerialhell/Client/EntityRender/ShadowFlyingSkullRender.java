package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowFlyingSkullModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowFlyingSkullRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowFlyingSkullEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class ShadowFlyingSkullRender<T extends ShadowFlyingSkullEntity> extends MobEntityRenderer<T, ShadowFlyingSkullRenderState, ShadowFlyingSkullModel>
{
	private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/flying_skull/shadow_flying_skull.png");

	public ShadowFlyingSkullRender(EntityRendererFactory.Context context)
	{
		super(context, new ShadowFlyingSkullModel(context.getPart(AerialHellModelLayers.SHADOW_FLYING_SKULL)), 0.1F);
	}

	@Override public ShadowFlyingSkullRenderState createRenderState() {return new ShadowFlyingSkullRenderState();}

	@Override public void updateRenderState(T entity, ShadowFlyingSkullRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.jawOpeningAmplitude = entity.jawOpeningAmplitude;
		renderState.jawOpeningFrequencyMalus = entity.jawOpeningFrequencyMalus;
	}

	@Override public Identifier getTexture(ShadowFlyingSkullRenderState renderState) {return TEXTURE;}
}