package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IronShurikenEntity extends AbstractShurikenEntity
{
	public IronShurikenEntity(EntityType<? extends IronShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public IronShurikenEntity(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.IRON_SHURIKEN.get(), level, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public IronShurikenEntity(double x, double y, double z, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.IRON_SHURIKEN.get(), x, y, z, worldIn, itemStack);
	}

	public IronShurikenEntity(LivingEntity shooter, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.IRON_SHURIKEN.get(), shooter, worldIn, itemStack);
	}

	public IronShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.IRON_SHURIKEN.get(), worldIn);
	}

	/*public IronShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.IRON_SHURIKEN.get(), worldIn);
	}*/

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
