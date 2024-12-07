package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ObsidianShurikenEntity extends AbstractShurikenEntity
{
	public ObsidianShurikenEntity(EntityType<? extends ObsidianShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public ObsidianShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy);
	}

	public ObsidianShurikenEntity(double x, double y, double z, World world)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN, x, y, z, world);
	}

	public ObsidianShurikenEntity(LivingEntity shooter, World world)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN, shooter, world);
	}

	public ObsidianShurikenEntity(World world)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN, world);
	}

	/*public ObsidianShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN, world);
	}*/

	@Override protected float getKnifeDamage() {return 11.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	@Override protected Item getDefaultItem() {return AerialHellItems.OBSIDIAN_SHURIKEN;}
}
