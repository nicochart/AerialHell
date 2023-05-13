package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class GoldShurikenEntity extends AbstractShurikenEntity
{
	public GoldShurikenEntity(EntityType<? extends GoldShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public GoldShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.GOLD_SHURIKEN.get(), x, y, z, worldIn);
	}

	public GoldShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.GOLD_SHURIKEN.get(), shooter, worldIn);
	}

	public GoldShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.GOLD_SHURIKEN.get(), worldIn);
	}

	public GoldShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.GOLD_SHURIKEN.get(), worldIn);
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
		return AerialHellBlocksAndItems.GOLD_SHURIKEN.get();
	}	
}
