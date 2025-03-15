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

public class NetheriteShurikenEntity extends AbstractShurikenEntity
{
	public NetheriteShurikenEntity(EntityType<? extends NetheriteShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public NetheriteShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public NetheriteShurikenEntity(double x, double y, double z, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN, x, y, z, world, itemStack);
	}

	public NetheriteShurikenEntity(LivingEntity shooter, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN, shooter, world, itemStack);
	}

	public NetheriteShurikenEntity(World world)
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
