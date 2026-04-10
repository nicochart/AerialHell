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

public class NetheriteShurikenEntity extends AbstractShurikenEntity
{
	public NetheriteShurikenEntity(EntityType<? extends NetheriteShurikenEntity> entityTypeIn, Level world)
	{
		super(entityTypeIn, world);
	}

	public NetheriteShurikenEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public NetheriteShurikenEntity(double x, double y, double z, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN, x, y, z, world, itemStack);
	}

	public NetheriteShurikenEntity(LivingEntity shooter, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN, shooter, world, itemStack);
	}

	public NetheriteShurikenEntity(Level world)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN, world);
	}

	/*public NetheriteShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN, world);
	}*/

	@Override protected float getKnifeDamage() {return 12.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	@Override protected Item getDefaultItem() {return AerialHellItems.NETHERITE_SHURIKEN;}
}
