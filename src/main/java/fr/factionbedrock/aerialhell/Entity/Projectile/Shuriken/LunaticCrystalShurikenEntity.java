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

public class LunaticCrystalShurikenEntity extends AbstractShurikenEntity
{
	public LunaticCrystalShurikenEntity(EntityType<? extends LunaticCrystalShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public LunaticCrystalShurikenEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), x, y, z, worldIn);
	}

	public LunaticCrystalShurikenEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), shooter, worldIn);
	}

	public LunaticCrystalShurikenEntity(World worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), worldIn);
	}

	public LunaticCrystalShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
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
