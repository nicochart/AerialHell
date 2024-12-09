package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowAutomatonShadowLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;

public class ShadowAutomatonRender<T extends AutomatonEntity, M extends EmptyModel<T>> extends MobEntityRenderer<T,M>
{
	private static final Identifier TEXTURE_NORMAL = Identifier.of(AerialHell.MODID, "textures/entity/automaton/shadow_automaton.png");
	private static final Identifier TEXTURE_INVERT = Identifier.of(AerialHell.MODID, "textures/entity/automaton/shadow_automaton_invert.png");

	public ShadowAutomatonRender(EntityRendererFactory.Context context)
	{
		super(context, (M) new EmptyModel<T>(), 0.3F);
		this.addFeature(new ShadowAutomatonShadowLayer<T,M>(this, new AutomatonModel<>(context.getPart(AerialHellModelLayers.AUTOMATON))));
	}

	@Override protected void scale(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		float f = 0.9F; matrixStackIn.scale(f, f, f);
	}
	
	@Override public Identifier getTexture(AutomatonEntity entity)
	{
		if (MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {return TEXTURE_INVERT;}
		else {return TEXTURE_NORMAL;}
	}
}