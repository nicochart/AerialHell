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

public class NetheriteThrowingKnifeEntity extends AbstractThrowingKnifeEntity
{
	public NetheriteThrowingKnifeEntity(EntityType<? extends NetheriteThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public NetheriteThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.NETHERITE_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public NetheriteThrowingKnifeEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.NETHERITE_THROWING_KNIFE.get(), shooter, worldIn);
	}

	public NetheriteThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.NETHERITE_THROWING_KNIFE.get(), worldIn);
	}

	public NetheriteThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.NETHERITE_THROWING_KNIFE.get(), worldIn);
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
		return AerialHellBlocksAndItems.NETHERITE_THROWING_KNIFE.get();
	}	
}
