package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class ArsonistShurikenEntity extends AbstractShurikenEntity
{
	public ArsonistShurikenEntity(EntityType<? extends ArsonistShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public ArsonistShurikenEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN.get(), x, y, z, worldIn);
	}

	public ArsonistShurikenEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN.get(), shooter, worldIn);
	}

	public ArsonistShurikenEntity(World worldIn)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN.get(), worldIn);
	}

	public ArsonistShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.ARSONIST_SHURIKEN.get(), worldIn);
	}

	@Override
	protected float getShurikenDamage()
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
		return AerialHellBlocksAndItems.ARSONIST_SHURIKEN.get();
	}	
}
