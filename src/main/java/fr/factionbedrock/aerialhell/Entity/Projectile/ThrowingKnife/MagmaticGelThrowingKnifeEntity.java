package fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbstractThrowingKnifeEntity;
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

public class MagmaticGelThrowingKnifeEntity extends AbstractThrowingKnifeEntity
{
	public MagmaticGelThrowingKnifeEntity(EntityType<? extends MagmaticGelThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public MagmaticGelThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public MagmaticGelThrowingKnifeEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_THROWING_KNIFE.get(), shooter, worldIn);
	}

	public MagmaticGelThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_THROWING_KNIFE.get(), worldIn);
	}

	public MagmaticGelThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_THROWING_KNIFE.get(), worldIn);
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
		return AerialHellBlocksAndItems.MAGMATIC_GEL_THROWING_KNIFE.get();
	}	
}
