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

public class GoldShurikenEntity extends AbstractShurikenEntity
{
	public GoldShurikenEntity(EntityType<? extends GoldShurikenEntity> entityTypeIn, Level world)
	{
		super(entityTypeIn, world);
	}

	public GoldShurikenEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.GOLD_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public GoldShurikenEntity(double x, double y, double z, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.GOLD_SHURIKEN, x, y, z, world, itemStack);
	}

	public GoldShurikenEntity(LivingEntity shooter, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.GOLD_SHURIKEN, shooter, world, itemStack);
	}

	public GoldShurikenEntity(Level world)
	{
		super(AerialHellEntities.GOLD_SHURIKEN, world);
	}

	/*public GoldShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.GOLD_SHURIKEN, world);
	}*/

	@Override protected float getKnifeDamage() {return 9.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	@Override protected Item getDefaultItem() {return AerialHellItems.GOLD_SHURIKEN;}
}
