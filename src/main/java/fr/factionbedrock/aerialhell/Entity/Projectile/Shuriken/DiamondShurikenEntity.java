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

public class DiamondShurikenEntity extends AbstractShurikenEntity
{
	public DiamondShurikenEntity(EntityType<? extends DiamondShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public DiamondShurikenEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN.get(), x, y, z, worldIn);
	}

	public DiamondShurikenEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN.get(), shooter, worldIn);
	}

	public DiamondShurikenEntity(World worldIn)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN.get(), worldIn);
	}

	public DiamondShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.DIAMOND_SHURIKEN.get(), worldIn);
	}

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
