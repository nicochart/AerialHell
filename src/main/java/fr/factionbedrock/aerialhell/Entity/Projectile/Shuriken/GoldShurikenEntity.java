package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class GoldShurikenEntity extends AbstractShurikenEntity
{
	public GoldShurikenEntity(EntityType<? extends GoldShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public GoldShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
	{
		super(AerialHellEntities.GOLD_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy);
	}

	public GoldShurikenEntity(double x, double y, double z, World world)
	{
		super(AerialHellEntities.GOLD_SHURIKEN, x, y, z, world);
	}

	public GoldShurikenEntity(LivingEntity shooter, World world)
	{
		super(AerialHellEntities.GOLD_SHURIKEN, shooter, world);
	}

	public GoldShurikenEntity(World world)
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
