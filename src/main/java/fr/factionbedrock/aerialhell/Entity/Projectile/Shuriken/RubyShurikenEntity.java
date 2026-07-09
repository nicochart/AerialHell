package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class RubyShurikenEntity extends AbstractShurikenEntity
{
	public RubyShurikenEntity(EntityType<? extends RubyShurikenEntity> entityTypeIn, Level world)
	{
		super(entityTypeIn, world);
	}

	public RubyShurikenEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
	{
		super(AerialHellEntities.RUBY_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy);
	}

	public RubyShurikenEntity(double x, double y, double z, Level world)
	{
		super(AerialHellEntities.RUBY_SHURIKEN, x, y, z, world);
	}

	public RubyShurikenEntity(LivingEntity shooter, Level world)
	{
		super(AerialHellEntities.RUBY_SHURIKEN, shooter, world);
	}

	public RubyShurikenEntity(Level world)
	{
		super(AerialHellEntities.RUBY_SHURIKEN, world);
	}

	/*public RubyShurikenEntity(PlayMessages.SpawnEntity packet, Level world)
	{
		super(AerialHellEntities.RUBY_SHURIKEN, world);
	}*/

	@Override protected float getKnifeDamage() {return 9.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	@Override protected Item getDefaultItem() {return AerialHellItems.RUBY_SHURIKEN;}
}
