package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ArsonistShurikenEntity extends AbstractShurikenEntity
{
	public ArsonistShurikenEntity(EntityType<? extends ArsonistShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public ArsonistShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy);
	}

	public ArsonistShurikenEntity(double x, double y, double z, World world)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN, x, y, z, world);
	}

	public ArsonistShurikenEntity(LivingEntity shooter, World world)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN, shooter, world);
	}

	public ArsonistShurikenEntity(World world)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN, world);
	}

	/*public ArsonistShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN, world);
	}*/

	@Override protected float getKnifeDamage() {return 14.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity)
	{
		if (entity instanceof LivingEntity)
		{
			entity.setOnFireFor(5);
		}
	}
	@Override protected Item getDefaultItem() {return AerialHellItems.ARSONIST_SHURIKEN;}
}
