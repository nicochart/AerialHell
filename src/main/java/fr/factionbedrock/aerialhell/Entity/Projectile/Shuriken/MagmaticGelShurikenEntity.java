package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MagmaticGelShurikenEntity extends AbstractShurikenEntity
{
	public MagmaticGelShurikenEntity(EntityType<? extends MagmaticGelShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public MagmaticGelShurikenEntity(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), level, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public MagmaticGelShurikenEntity(double x, double y, double z, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), x, y, z, worldIn, itemStack);
	}

	public MagmaticGelShurikenEntity(LivingEntity shooter, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), shooter, worldIn, itemStack);
	}

	public MagmaticGelShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), worldIn);
	}

	/*public MagmaticGelShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), worldIn);
	}*/

	@Override
	protected float getKnifeDamage()
	{
		return 9.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity)
	{
		if (entity instanceof LivingEntity)
        {
        	((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 1, true, false));
        }
	}
	
	@Override
	protected Item getDefaultItem()
	{
		return AerialHellItems.MAGMATIC_GEL_SHURIKEN.get();
	}	
}
