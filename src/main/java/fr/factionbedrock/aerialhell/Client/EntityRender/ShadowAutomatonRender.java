package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowAutomatonShadowLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShadowAutomatonRender<T extends AutomatonEntity, M extends EmptyModel<T>> extends MobRenderer<T,M>
{
	private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(AerialHell.MODID, "textures/entity/automaton/shadow_automaton.png");
	private static final ResourceLocation TEXTURE_INVERT = new ResourceLocation(AerialHell.MODID, "textures/entity/automaton/shadow_automaton_invert.png");

	public ShadowAutomatonRender(EntityRendererManager rendererManager)
	{
		super(rendererManager, (M) new EmptyModel<T>(), 0.3F);
		this.addLayer(new ShadowAutomatonShadowLayer<T,M>(this));
	}

	@Override protected void preRenderCallback(T entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
	{
		float f = 0.9F; matrixStackIn.scale(f, f, f);
	}
	
	@Override public ResourceLocation getEntityTexture(AutomatonEntity entity)
	{
		if (Minecraft.getInstance().player.hasEffect(Effects.NIGHT_VISION)) {return TEXTURE_INVERT;}
		else {return TEXTURE_NORMAL;}
	}
}