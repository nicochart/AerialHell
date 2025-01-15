package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowAutomatonShadowLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AutomatonRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;

public class ShadowAutomatonRender<T extends AutomatonEntity, M extends EmptyModel<AutomatonRenderState>> extends MobRenderer<T, AutomatonRenderState, M>
{
	private static final ResourceLocation TEXTURE_NORMAL = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/automaton/shadow_automaton.png");
	private static final ResourceLocation TEXTURE_INVERT = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/automaton/shadow_automaton_invert.png");

	public ShadowAutomatonRender(EntityRendererProvider.Context context)
	{
		super(context, (M) new EmptyModel<AutomatonRenderState>(context.bakeLayer(AerialHellModelLayers.EMPTY)), 0.3F);
		this.addLayer(new ShadowAutomatonShadowLayer<AutomatonRenderState,M>(this, new AutomatonModel<>(context.bakeLayer(AerialHellModelLayers.AUTOMATON))));
	}

	@Override public AutomatonRenderState createRenderState() {return new AutomatonRenderState();}

	@Override public void extractRenderState(T entity, AutomatonRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.texture = getTextureLocation(entity);
		renderState.scale = 0.9F;
		renderState.attackTimer = entity.attackTimer;
	}

	@Override protected void scale(AutomatonRenderState renderState, PoseStack poseStack)
	{
		poseStack.scale(renderState.scale, renderState.scale, renderState.scale);
	}

	@Override public ResourceLocation getTextureLocation(AutomatonRenderState renderState) {return renderState.texture;}

	public ResourceLocation getTextureLocation(AutomatonEntity entity)
	{
		if (Minecraft.getInstance().player.hasEffect(MobEffects.NIGHT_VISION)) {return TEXTURE_INVERT;}
		else {return TEXTURE_NORMAL;}
	}
}