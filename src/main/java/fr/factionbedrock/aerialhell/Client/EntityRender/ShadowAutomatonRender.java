package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowAutomatonShadowLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShadowAutomatonRender<T extends AutomatonEntity, M extends EmptyModel<T>> extends MobRenderer<T,M>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/automaton/shadow_automaton.png");

	public ShadowAutomatonRender(EntityRendererManager rendererManager)
	{
		super(rendererManager, (M) new EmptyModel<T>(), 0.3F);
		this.addLayer(new ShadowAutomatonShadowLayer<T,M>(this));
	}

	@Override protected void preRenderCallback(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		float f = 0.9F; matrixStackIn.scale(f, f, f);
	}
	
	@Override public ResourceLocation getEntityTexture(AutomatonEntity entity) {return TEXTURE;}
}