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

public class IronShurikenEntity extends AbstractShurikenEntity
{
	public IronShurikenEntity(EntityType<? extends IronShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public IronShurikenEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.IRON_SHURIKEN.get(), x, y, z, worldIn);
	}

	public IronShurikenEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.IRON_SHURIKEN.get(), shooter, worldIn);
	}

	public IronShurikenEntity(World worldIn)
	{
		super(AerialHellEntities.IRON_SHURIKEN.get(), worldIn);
	}

	public IronShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.IRON_SHURIKEN.get(), worldIn);
	}

	@Override
	protected float getKnifeDamage()
	{
		return 8.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.IRON_SHURIKEN.get();
	}	
}