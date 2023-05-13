package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.PlayMessages;

public class LightningShurikenEntity extends AbstractShurikenEntity
{
	public LightningShurikenEntity(EntityType<? extends LightningShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public LightningShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN.get(), x, y, z, worldIn);
	}

	public LightningShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN.get(), shooter, worldIn);
	}

	public LightningShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN.get(), worldIn);
	}

	public LightningShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN.get(), worldIn);
	}
	
	@Override
	protected float getKnifeDamage()
	{
		return 2.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}
	
	@Override
	protected void onHit(HitResult result)
	{
		if (this.level.isClientSide()) {return;}
		
		if (result.getType() != HitResult.Type.MISS && this.level instanceof ServerLevel)
		{
			LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level);
			lightningBolt.setPos(this.getX(), this.getY(), this.getZ());
			this.level.addFreshEntity(lightningBolt);
		}
		super.onHit(result);
	}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.LIGHTNING_SHURIKEN.get();
	}
}
