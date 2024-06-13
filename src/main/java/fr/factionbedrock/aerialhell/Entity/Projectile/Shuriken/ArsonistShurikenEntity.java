package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ArsonistShurikenEntity extends AbstractShurikenEntity
{
	public ArsonistShurikenEntity(EntityType<? extends ArsonistShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public ArsonistShurikenEntity(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN.get(), level, shooter, accelX, accelY, accelZ, velocity, inaccuracy);
	}

	public ArsonistShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN.get(), x, y, z, worldIn);
	}

	public ArsonistShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN.get(), shooter, worldIn);
	}

	public ArsonistShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN.get(), worldIn);
	}

	/*public ArsonistShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN.get(), worldIn);
	}*/

	@Override
	protected float getKnifeDamage()
	{
		return 14.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity)
	{
		if (entity instanceof LivingEntity)
        {
        	entity.setSecondsOnFire(5);
        }
	}
	
	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.ARSONIST_SHURIKEN.get();
	}	
}
