package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArsonistShurikenEntity extends AbstractShurikenEntity
{
	public ArsonistShurikenEntity(EntityType<? extends ArsonistShurikenEntity> entityTypeIn, Level world)
	{
		super(entityTypeIn, world);
	}

	public ArsonistShurikenEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public ArsonistShurikenEntity(double x, double y, double z, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN, x, y, z, world, itemStack);
	}

	public ArsonistShurikenEntity(LivingEntity shooter, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN, shooter, world, itemStack);
	}

	public ArsonistShurikenEntity(Level world)
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
			entity.igniteForSeconds(5);
		}
	}
	@Override protected Item getDefaultItem() {return AerialHellItems.ARSONIST_SHURIKEN;}
}
