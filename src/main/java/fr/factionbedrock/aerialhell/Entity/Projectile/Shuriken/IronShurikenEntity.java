package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class IronShurikenEntity extends AbstractShurikenEntity
{
	public IronShurikenEntity(EntityType<? extends IronShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public IronShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.IRON_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public IronShurikenEntity(double x, double y, double z, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.IRON_SHURIKEN, x, y, z, world, itemStack);
	}

	public IronShurikenEntity(LivingEntity shooter, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.IRON_SHURIKEN, shooter, world, itemStack);
	}

	public IronShurikenEntity(World world)
	{
		super(AerialHellEntities.IRON_SHURIKEN, world);
	}

	/*public IronShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.IRON_SHURIKEN, world);
	}*/

	@Override protected float getKnifeDamage() {return 8.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	@Override protected Item getDefaultItem() {return AerialHellItems.IRON_SHURIKEN;}
}
