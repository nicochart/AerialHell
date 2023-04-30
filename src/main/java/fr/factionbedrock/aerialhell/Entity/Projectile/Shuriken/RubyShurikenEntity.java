package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class RubyShurikenEntity extends AbstractShurikenEntity
{
	public RubyShurikenEntity(EntityType<? extends RubyShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public RubyShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.RUBY_SHURIKEN.get(), x, y, z, worldIn);
	}

	public RubyShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.RUBY_SHURIKEN.get(), shooter, worldIn);
	}

	public RubyShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.RUBY_SHURIKEN.get(), worldIn);
	}

	public RubyShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.RUBY_SHURIKEN.get(), worldIn);
	}

	@Override
	protected float getKnifeDamage()
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
