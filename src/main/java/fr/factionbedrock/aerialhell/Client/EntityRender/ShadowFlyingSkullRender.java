package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowFlyingSkullModel;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowFlyingSkullEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShadowFlyingSkullRender<T extends ShadowFlyingSkullEntity> extends MobRenderer<T, ShadowFlyingSkullModel<T>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/flying_skull/shadow_flying_skull.png");

	public ShadowFlyingSkullRender(EntityRendererManager rendererManager)
	{
		super(rendererManager, new ShadowFlyingSkullModel<T>(), 0.1F);
	}
	
	@Override public ResourceLocation getEntityTexture(T entity) {return TEXTURE;}
}