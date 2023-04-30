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

public class ObsidianShurikenEntity extends AbstractShurikenEntity
{
	public ObsidianShurikenEntity(EntityType<? extends ObsidianShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public ObsidianShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), x, y, z, worldIn);
	}

	public ObsidianShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), shooter, worldIn);
	}

	public ObsidianShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), worldIn);
	}

	public ObsidianShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), worldIn);
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
		return AerialHellBlocksAndItems.OBSIDIAN_SHURIKEN.get();
	}	
}
