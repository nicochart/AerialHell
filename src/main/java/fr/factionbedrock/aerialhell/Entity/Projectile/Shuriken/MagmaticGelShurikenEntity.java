package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class MagmaticGelShurikenEntity extends AbstractShurikenEntity
{
	public MagmaticGelShurikenEntity(EntityType<? extends MagmaticGelShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public MagmaticGelShurikenEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), x, y, z, worldIn);
	}

	public MagmaticGelShurikenEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), shooter, worldIn);
	}

	public MagmaticGelShurikenEntity(World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), worldIn);
	}

	public MagmaticGelShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
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
        	((LivingEntity) entity).addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 80, 1, true, false)));
        }
	}
	
	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.MAGMATIC_GEL_SHURIKEN.get();
	}	
}
