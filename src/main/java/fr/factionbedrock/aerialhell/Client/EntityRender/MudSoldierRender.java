package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralSoldierEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.Identifier;

public class MudSoldierRender extends SkeletonEntityRenderer
{
	private static String name = "mud_soldier";
	private static final Identifier NORMAL = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	private static final Identifier SPECTRAL = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/mud_spectral_soldier.png");
	
    public MudSoldierRender(EntityRendererFactory.Context context)
	{
		super(context);
	}

	@Override
	public Identifier getTexture(AbstractSkeletonEntity entity)
    {
		if (entity instanceof MudSpectralSoldierEntity) {return SPECTRAL;}
		else {return NORMAL;}
    }
}