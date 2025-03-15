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

public class DiamondShurikenEntity extends AbstractShurikenEntity
{
	public DiamondShurikenEntity(EntityType<? extends DiamondShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public DiamondShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public DiamondShurikenEntity(double x, double y, double z, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN, x, y, z, world, itemStack);
	}

	public DiamondShurikenEntity(LivingEntity shooter, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN, shooter, world, itemStack);
	}

	public DiamondShurikenEntity(World world)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN, world);
	}

	/*public DiamondShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN, world);
	}*/

	@Override protected float getKnifeDamage() {return 11.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	@Override protected Item getDefaultItem() {return AerialHellItems.DIAMOND_SHURIKEN;}
}
