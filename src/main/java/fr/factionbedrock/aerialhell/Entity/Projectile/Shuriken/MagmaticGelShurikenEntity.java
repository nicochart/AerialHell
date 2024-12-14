package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class MagmaticGelShurikenEntity extends AbstractShurikenEntity
{
	public MagmaticGelShurikenEntity(EntityType<? extends MagmaticGelShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public MagmaticGelShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy);
	}

	public MagmaticGelShurikenEntity(double x, double y, double z, World world)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN, x, y, z, world);
	}

	public MagmaticGelShurikenEntity(LivingEntity shooter, World world)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN, shooter, world);
	}

	public MagmaticGelShurikenEntity(World world)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN, world);
	}

	/*public MagmaticGelShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN), world);
	}*/

	@Override protected float getKnifeDamage() {return 9.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity)
	{
		if (entity instanceof LivingEntity)
        {
        	((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 1, true, false));
        }
	}
	@Override protected Item getDefaultItem() {return AerialHellItems.MAGMATIC_GEL_SHURIKEN;}
}
