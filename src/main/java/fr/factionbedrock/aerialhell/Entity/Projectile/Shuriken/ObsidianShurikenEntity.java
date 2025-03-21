package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ObsidianShurikenEntity extends AbstractShurikenEntity
{
	public ObsidianShurikenEntity(EntityType<? extends ObsidianShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public ObsidianShurikenEntity(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), level, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public ObsidianShurikenEntity(double x, double y, double z, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), x, y, z, worldIn, itemStack);
	}

	public ObsidianShurikenEntity(LivingEntity shooter, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), shooter, worldIn, itemStack);
	}

	public ObsidianShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), worldIn);
	}

	/*public ObsidianShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), worldIn);
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
		return AerialHellItems.OBSIDIAN_SHURIKEN.get();
	}	
}
