package fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class LunaticCrystalThrowingKnifeEntity extends AbstractThrowingKnifeEntity
{
	public LunaticCrystalThrowingKnifeEntity(EntityType<? extends LunaticCrystalThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public LunaticCrystalThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public LunaticCrystalThrowingKnifeEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_THROWING_KNIFE.get(), shooter, worldIn);
	}

	public LunaticCrystalThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_THROWING_KNIFE.get(), worldIn);
	}

	public LunaticCrystalThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_THROWING_KNIFE.get(), worldIn);
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
		return AerialHellBlocksAndItems.LUNATIC_CRYSTAL_THROWING_KNIFE.get();
	}	
}
