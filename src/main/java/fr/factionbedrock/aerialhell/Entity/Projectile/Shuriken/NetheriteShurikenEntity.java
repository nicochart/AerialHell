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

public class NetheriteShurikenEntity extends AbstractShurikenEntity
{
	public NetheriteShurikenEntity(EntityType<? extends NetheriteShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public NetheriteShurikenEntity(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN.get(), level, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public NetheriteShurikenEntity(double x, double y, double z, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN.get(), x, y, z, worldIn, itemStack);
	}

	public NetheriteShurikenEntity(LivingEntity shooter, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN.get(), shooter, worldIn, itemStack);
	}

	public NetheriteShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN.get(), worldIn);
	}

	/*public NetheriteShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.NETHERITE_SHURIKEN.get(), worldIn);
	}*/

	@Override
	protected float getKnifeDamage()
	{
		return 12.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellItems.NETHERITE_SHURIKEN.get();
	}	
}
