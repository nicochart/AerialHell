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

public class LunaticCrystalShurikenEntity extends AbstractShurikenEntity
{
	public LunaticCrystalShurikenEntity(EntityType<? extends LunaticCrystalShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public LunaticCrystalShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), x, y, z, worldIn);
	}

	public LunaticCrystalShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), shooter, worldIn);
	}

	public LunaticCrystalShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), worldIn);
	}

	public LunaticCrystalShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), worldIn);
	}

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
		return AerialHellBlocksAndItems.LUNATIC_CRYSTAL_SHURIKEN.get();
	}	
}
