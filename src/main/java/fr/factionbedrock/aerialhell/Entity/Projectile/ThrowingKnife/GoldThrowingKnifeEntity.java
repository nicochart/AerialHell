package fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbtractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GoldThrowingKnifeEntity extends AbtractThrowingKnifeEntity
{
	public GoldThrowingKnifeEntity(EntityType<? extends GoldThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public GoldThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.GOLD_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public GoldThrowingKnifeEntity(LivingEntity owner, World worldIn)
	{
		super(AerialHellEntities.GOLD_THROWING_KNIFE.get(), owner, worldIn);
	}

	public GoldThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.GOLD_THROWING_KNIFE.get(), worldIn);
	}

	public GoldThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.GOLD_THROWING_KNIFE.get(), worldIn);
	}

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
		return AerialHellBlocksAndItems.GOLD_THROWING_KNIFE.get();
	}	
}
