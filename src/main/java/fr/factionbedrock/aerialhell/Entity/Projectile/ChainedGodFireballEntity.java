package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChainedGodFireballEntity extends LargeFireball
{
	private final static int explosion_power = 0;
	public ChainedGodFireballEntity(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(worldIn,shooter,new Vec3(accelX,accelY,accelZ),explosion_power);
	}
}
