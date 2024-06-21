package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowAutomatonShadowLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;

public class ShadowAutomatonRender<T extends AutomatonEntity, M extends EmptyModel<T>> extends MobRenderer<T,M>
{
	private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(AerialHell.MODID, "textures/entity/automaton/shadow_automaton.png");
	private static final ResourceLocation TEXTURE_INVERT = new ResourceLocation(AerialHell.MODID, "textures/entity/automaton/shadow_automaton_invert.png");

	public ShadowAutomatonRender(EntityRendererProvider.Context context)
	{
		super(context, (M) new EmptyModel<T>(), 0.3F);
		this.addLayer(new ShadowAutomatonShadowLayer<T,M>(this, new AutomatonModel<>(context.bakeLayer(AerialHellModelLayers.AUTOMATON))));
	}

	@Override protected void scale(T entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
	{
		float f = 0.9F; matrixStackIn.scale(f, f, f);
	}
	
	@Override public ResourceLocation getTextureLocation(AutomatonEntity entity)
	{
		if (Minecraft.getInstance().player.hasEffect(MobEffects.NIGHT_VISION)) {return TEXTURE_INVERT;}
		else {return TEXTURE_NORMAL;}
	}
}