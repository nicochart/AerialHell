package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.FlyingJellyfishModel;
import fr.factionbedrock.aerialhell.Entity.Monster.FlyingJellyfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
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
		super(rendererManager, new FlyingJellyfishModel<J>(), 1.0F);
	}
	
	@Override
	protected void preRenderCallback(FlyingJellyfishEntity jellyfish, PoseStack matrixStackIn, float partialTickTime)
	{
	      matrixStackIn.scale(5.0F, 5.0F, 5.0F);
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