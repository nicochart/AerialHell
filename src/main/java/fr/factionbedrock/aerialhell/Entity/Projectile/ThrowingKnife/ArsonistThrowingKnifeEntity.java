package fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class ArsonistThrowingKnifeEntity extends AbstractThrowingKnifeEntity
{
	public ArsonistThrowingKnifeEntity(EntityType<? extends ArsonistThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public ArsonistThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.ARSONIST_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public ArsonistThrowingKnifeEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.ARSONIST_THROWING_KNIFE.get(), shooter, worldIn);
	}

	public ArsonistThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.ARSONIST_THROWING_KNIFE.get(), worldIn);
	}

	public ArsonistThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.ARSONIST_THROWING_KNIFE.get(), worldIn);
	}

	@Override
	protected float getKnifeDamage()
	{
		return 14.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity)
	{
		if (entity instanceof LivingEntity)
        {
        	((LivingEntity) entity).setFire(5);
        }
	}
	
	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.ARSONIST_THROWING_KNIFE.get();
	}	
}
