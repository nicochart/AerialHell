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

public class RubyShurikenEntity extends AbstractShurikenEntity
{
	public RubyShurikenEntity(EntityType<? extends RubyShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public RubyShurikenEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.RUBY_SHURIKEN.get(), x, y, z, worldIn);
	}

	public RubyShurikenEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.RUBY_SHURIKEN.get(), shooter, worldIn);
	}

	public RubyShurikenEntity(World worldIn)
	{
		super(AerialHellEntities.RUBY_SHURIKEN.get(), worldIn);
	}

	public RubyShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.RUBY_SHURIKEN.get(), worldIn);
	}

	@Override
	protected float getShurikenDamage()
	{
		return 9.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.RUBY_SHURIKEN.get();
	}	
}
