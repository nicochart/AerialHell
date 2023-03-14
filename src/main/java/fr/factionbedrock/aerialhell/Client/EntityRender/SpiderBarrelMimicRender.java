package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.SpiderBarrelMimicModel;
import fr.factionbedrock.aerialhell.Entity.AbstractBarrelMimicEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpiderBarrelMimicRender<T extends AbstractBarrelMimicEntity> extends MobRenderer<T, SpiderBarrelMimicModel<T>>
{
	private static final ResourceLocation SHADOW_PINE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/barrel_mimic/shadow_pine.png");

	public SpiderBarrelMimicRender(EntityRendererManager rendererManager)
	{
		super(rendererManager, new SpiderBarrelMimicModel(), 1.0F);
	}

	@Override
	public ResourceLocation getEntityTexture(T mimic)
	{
		//if (mimic instanceof ShadowPineBarrelMimicEntity)
		//{
			return SHADOW_PINE_TEXTURE;
		//}
	}
}