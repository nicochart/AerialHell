package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.FlyingJellyfishModel;
import fr.factionbedrock.aerialhell.Entity.Monster.FlyingJellyfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlyingJellyfishRender<J extends FlyingJellyfishEntity> extends MobRenderer<J, FlyingJellyfishModel<J>>
{	
	private static String name = "flying_jellyfish";
	private static final ResourceLocation JELLYFISH = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	private static final ResourceLocation JELLYFISH_SHOOTING = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_shooting.png");
	
	public FlyingJellyfishRender(EntityRendererManager rendererManager)
	{
		super(rendererManager, new FlyingJellyfishModel(), 1.0F);
	}
	
	@Override
	protected void preRenderCallback(FlyingJellyfishEntity jellyfish, MatrixStack matrixStackIn, float partialTickTime)
	{
	      matrixStackIn.scale(2.5F, 2.5F, 2.5F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(J jellyFish)
	{
		if (jellyFish.isAttacking())
		{
			return JELLYFISH_SHOOTING;
		}
		else
		{
			return JELLYFISH;
		}
	}
}