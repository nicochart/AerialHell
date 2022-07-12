package fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbstractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class IronThrowingKnifeEntity extends AbstractThrowingKnifeEntity
{
	public IronThrowingKnifeEntity(EntityType<? extends IronThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public IronThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.IRON_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public IronThrowingKnifeEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.IRON_THROWING_KNIFE.get(), shooter, worldIn);
	}

	public IronThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.IRON_THROWING_KNIFE.get(), worldIn);
	}

	public IronThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.IRON_THROWING_KNIFE.get(), worldIn);
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
		return AerialHellBlocksAndItems.IRON_THROWING_KNIFE.get();
	}	
}
