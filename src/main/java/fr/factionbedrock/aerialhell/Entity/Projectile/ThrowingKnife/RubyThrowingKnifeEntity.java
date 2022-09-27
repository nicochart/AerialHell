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

public class RubyThrowingKnifeEntity extends AbstractThrowingKnifeEntity
{
	public RubyThrowingKnifeEntity(EntityType<? extends RubyThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public RubyThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.RUBY_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public RubyThrowingKnifeEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.RUBY_THROWING_KNIFE.get(), shooter, worldIn);
	}

	public RubyThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.RUBY_THROWING_KNIFE.get(), worldIn);
	}

	public RubyThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.RUBY_THROWING_KNIFE.get(), worldIn);
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
		return AerialHellBlocksAndItems.RUBY_THROWING_KNIFE.get();
	}	
}
