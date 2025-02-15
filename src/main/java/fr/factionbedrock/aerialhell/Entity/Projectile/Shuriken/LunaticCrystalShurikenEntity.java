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

public class LunaticCrystalShurikenEntity extends AbstractShurikenEntity
{
	public LunaticCrystalShurikenEntity(EntityType<? extends LunaticCrystalShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public LunaticCrystalShurikenEntity(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), level, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public LunaticCrystalShurikenEntity(double x, double y, double z, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), x, y, z, worldIn, itemStack);
	}

	public LunaticCrystalShurikenEntity(LivingEntity shooter, Level worldIn, ItemStack itemStack)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), shooter, worldIn, itemStack);
	}

	public LunaticCrystalShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), worldIn);
	}

	/*public LunaticCrystalShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), worldIn);
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
		return AerialHellItems.LUNATIC_CRYSTAL_SHURIKEN.get();
	}	
}
