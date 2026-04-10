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

public class IronShurikenEntity extends AbstractShurikenEntity
{
	public IronShurikenEntity(EntityType<? extends IronShurikenEntity> entityTypeIn, Level world)
	{
		super(entityTypeIn, world);
	}

	public IronShurikenEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.IRON_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public IronShurikenEntity(double x, double y, double z, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.IRON_SHURIKEN, x, y, z, world, itemStack);
	}

	public IronShurikenEntity(LivingEntity shooter, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.IRON_SHURIKEN, shooter, world, itemStack);
	}

	public IronShurikenEntity(Level world)
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
