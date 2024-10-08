package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralSoldierEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class MudSoldierRender extends SkeletonRenderer
{
	private static String name = "mud_soldier";
	private static final ResourceLocation NORMAL = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	private static final ResourceLocation SPECTRAL = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/mud_spectral_soldier.png");
	
    public MudSoldierRender(EntityRendererProvider.Context context)
	{
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractSkeleton entity)
    {
		if (entity instanceof MudSpectralSoldierEntity) {return SPECTRAL;}
		else {return NORMAL;}
    }
}