package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.MudSpectralSoldierEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.util.ResourceLocation;

public class MudSoldierRender extends SkeletonRenderer
{
	private static String name = "mud_soldier";
	private static final ResourceLocation NORMAL = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	private static final ResourceLocation SPECTRAL = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/mud_spectral_soldier.png");
	
    public MudSoldierRender(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Override
	public ResourceLocation getEntityTexture(AbstractSkeletonEntity entity)
    {
		if (entity instanceof MudSpectralSoldierEntity)
		{
			return SPECTRAL;
		}
		else
		{
			return NORMAL;
		}
    }
}