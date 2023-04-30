package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;

public class ChainedGodFireballEntity extends LargeFireball
{
	private final static int explosion_power = 0;
	public ChainedGodFireballEntity(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(worldIn,shooter,accelX,accelY,accelZ,explosion_power);
	}
}
