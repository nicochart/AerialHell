package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.FlyingJellyfishModel;
import fr.factionbedrock.aerialhell.Entity.Monster.Flying.FlyingJellyfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
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
	
	public FlyingJellyfishRender(EntityRendererProvider.Context context)
	{
		super(context, new FlyingJellyfishModel<J>(context.bakeLayer(AerialHellModelLayers.FLYING_JELLYFISH)), 1.0F);
	}
	
	@Override
	protected void scale(FlyingJellyfishEntity jellyfish, PoseStack matrixStackIn, float partialTickTime)
	{
	      matrixStackIn.scale(5.0F, 5.0F, 5.0F);
	}
	
	@Override
	public ResourceLocation getTextureLocation(J jellyFish)
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