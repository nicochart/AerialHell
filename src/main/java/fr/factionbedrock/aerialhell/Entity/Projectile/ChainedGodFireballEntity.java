package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ChainedGodFireballEntity extends FireballEntity
{
	private final static int explosion_power = 0;
	public ChainedGodFireballEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(world,shooter,new Vec3d(accelX,accelY,accelZ),explosion_power);
	}
}
