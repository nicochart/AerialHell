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

public class DiamondShurikenEntity extends AbstractShurikenEntity
{
	public DiamondShurikenEntity(EntityType<? extends DiamondShurikenEntity> entityTypeIn, Level world)
	{
		super(entityTypeIn, world);
	}

	public DiamondShurikenEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public DiamondShurikenEntity(double x, double y, double z, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN, x, y, z, world, itemStack);
	}

	public DiamondShurikenEntity(LivingEntity shooter, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN, shooter, world, itemStack);
	}

	public DiamondShurikenEntity(Level world)
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
