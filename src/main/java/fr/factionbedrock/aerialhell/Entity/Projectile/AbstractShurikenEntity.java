package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeHooks;

public abstract class AbstractShurikenEntity extends ThrowableItemProjectile
{
	public float shurikenZRot; 
	
	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
		this.shurikenZRot = -135;
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, double x, double y, double z, Level worldIn)
	{
		super(type, x, y, z, worldIn);
		this.shurikenZRot = -135;
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, LivingEntity shooter, Level worldIn)
	{
		super(type, shooter, worldIn);
		this.shurikenZRot = -135;
	}

	/*public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(type, worldIn);
		this.shurikenZRot = -135;
	}*/
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		
		compound.putShort("shurikenZRot", (short)this.shurikenZRot);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
	    super.readAdditionalSaveData(compound);
	    if (compound.contains("shurikenZRot", 99))
	    {
	    	this.shurikenZRot = compound.getShort("shurikenZRot");
	    }
	}
	
	@Override
	protected void onHit(HitResult result)
	{
		if (this.level().isClientSide()) {return;}
		if (result != null && result.getType() != HitResult.Type.MISS && this.level() instanceof ServerLevel && result.getType() == HitResult.Type.ENTITY)
		{
            Entity entity = ((EntityHitResult)result).getEntity();
            entity.hurt(AerialHellDamageTypes.getDamageSource(this.level(), AerialHellDamageTypes.SHURIKEN_HIT), this.getKnifeDamage()); //TODO : Put Shuriken owner in DamageSource (see AbstractArrow onHitEntity method)
            entity.setDeltaMovement(entity.getDeltaMovement().add(this.getDeltaMovement().x / 2, 0.12F, this.getDeltaMovement().z / 2));
            this.applyEntityImpactEffet(entity);
		}
		this.discard();
	}

	@Override protected float getGravity() {return 0.04F;}
	
	abstract protected float getKnifeDamage();
	abstract protected void applyEntityImpactEffet(Entity entity);
	
	//@Override public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);}

	@Override abstract protected Item getDefaultItem();
}
