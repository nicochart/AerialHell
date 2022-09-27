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

public class ObsidianThrowingKnifeEntity extends AbstractThrowingKnifeEntity
{
	public ObsidianThrowingKnifeEntity(EntityType<? extends ObsidianThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public ObsidianThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public ObsidianThrowingKnifeEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_THROWING_KNIFE.get(), shooter, worldIn);
	}

	public ObsidianThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_THROWING_KNIFE.get(), worldIn);
	}

	public ObsidianThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.OBSIDIAN_THROWING_KNIFE.get(), worldIn);
	}

	@Override
	protected float getKnifeDamage()
	{
		return 11.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.OBSIDIAN_THROWING_KNIFE.get();
	}	
}
