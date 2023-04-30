package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MagmaticGelShurikenEntity extends AbstractShurikenEntity
{
	public MagmaticGelShurikenEntity(EntityType<? extends MagmaticGelShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public MagmaticGelShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), x, y, z, worldIn);
	}

	public MagmaticGelShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), shooter, worldIn);
	}

	public MagmaticGelShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), worldIn);
	}

	public MagmaticGelShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), worldIn);
	}

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
        	((LivingEntity) entity).addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 1, true, false)));
        }
	}
	
	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.MAGMATIC_GEL_SHURIKEN.get();
	}	
}
