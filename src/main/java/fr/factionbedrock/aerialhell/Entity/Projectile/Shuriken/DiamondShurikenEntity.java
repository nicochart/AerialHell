package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class DiamondShurikenEntity extends AbstractShurikenEntity
{
	public DiamondShurikenEntity(EntityType<? extends DiamondShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public DiamondShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN.get(), x, y, z, worldIn);
	}

	public DiamondShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN.get(), shooter, worldIn);
	}

	public DiamondShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN.get(), worldIn);
	}

	/*public DiamondShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN.get(), worldIn);
	}*/

	@Override
	protected float getKnifeDamage()
	{
		return 11.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.DIAMOND_SHURIKEN.get();
	}	
}
