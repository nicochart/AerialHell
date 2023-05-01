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

public class ObsidianShurikenEntity extends AbstractShurikenEntity
{
	public ObsidianShurikenEntity(EntityType<? extends ObsidianShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public ObsidianShurikenEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), x, y, z, worldIn);
	}

	public ObsidianShurikenEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), shooter, worldIn);
	}

	public ObsidianShurikenEntity(World worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), worldIn);
	}

	public ObsidianShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), worldIn);
	}

	@Override
	protected float getShurikenDamage()
	{
		return 11.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.OBSIDIAN_SHURIKEN.get();
	}	
}
