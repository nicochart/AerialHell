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

public class AzuriteThrowingKnifeEntity extends AbstractThrowingKnifeEntity
{
	public AzuriteThrowingKnifeEntity(EntityType<? extends AzuriteThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public AzuriteThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.AZURITE_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public AzuriteThrowingKnifeEntity(LivingEntity owner, World worldIn)
	{
		super(AerialHellEntities.AZURITE_THROWING_KNIFE.get(), owner, worldIn);
	}

	public AzuriteThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.AZURITE_THROWING_KNIFE.get(), worldIn);
	}

	public AzuriteThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.AZURITE_THROWING_KNIFE.get(), worldIn);
	}

	@Override
	protected float getKnifeDamage()
	{
		return 9.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.AZURITE_THROWING_KNIFE.get();
	}	
}
