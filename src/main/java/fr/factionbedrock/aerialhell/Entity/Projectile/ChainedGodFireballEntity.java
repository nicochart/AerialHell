package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.World;

public class ChainedGodFireballEntity extends FireballEntity
{
	public ChainedGodFireballEntity(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ)
	{
		super(worldIn,shooter,accelX,accelY,accelZ);
		this.explosionPower = 0;
	}
}
