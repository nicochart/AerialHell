package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowAutomatonShadowLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AutomatonRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;

public class ShadowAutomatonRender<T extends AutomatonEntity, M extends EmptyModel<AutomatonRenderState>> extends MobEntityRenderer<T, AutomatonRenderState, M>
{
	private static final Identifier TEXTURE_NORMAL = Identifier.of(AerialHell.MODID, "textures/entity/automaton/shadow_automaton.png");
	private static final Identifier TEXTURE_INVERT = Identifier.of(AerialHell.MODID, "textures/entity/automaton/shadow_automaton_invert.png");

	public ShadowAutomatonRender(EntityRendererFactory.Context context)
	{
		super(context, (M) new EmptyModel<AutomatonRenderState>(context.getPart(AerialHellModelLayers.EMPTY)), 0.3F);
		this.addFeature(new ShadowAutomatonShadowLayer<AutomatonRenderState,M>(this, new AutomatonModel<>(context.getPart(AerialHellModelLayers.AUTOMATON))));
	}

	@Override public AutomatonRenderState createRenderState() {return new AutomatonRenderState();}

	@Override public void updateRenderState(T entity, AutomatonRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.texture = getTexture(entity);
		renderState.scale = 0.9F;
		renderState.attackTimer = entity.attackTimer;
	}

	@Override protected void scale(AutomatonRenderState renderState, MatrixStack matrixStack)
	{
		matrixStack.scale(renderState.scale, renderState.scale, renderState.scale);
	}

	@Override public Identifier getTexture(AutomatonRenderState renderState) {return renderState.texture;}

	public Identifier getTexture(AutomatonEntity entity)
	{
		if (MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {return TEXTURE_INVERT;}
		else {return TEXTURE_NORMAL;}
	}
}