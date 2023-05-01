package fr.factionbedrock.aerialhell.Entity.Projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbstractShurikenEntity extends ProjectileItemEntity
{
	public float shurikenZRot; 
	
	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
		this.shurikenZRot = -135;
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, double x, double y, double z, World worldIn)
	{
		super(type, x, y, z, worldIn);
		this.shurikenZRot = -135;
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, LivingEntity shooter, World worldIn)
	{
		super(type, shooter, worldIn);
		this.shurikenZRot = -135;
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(type, worldIn);
		this.shurikenZRot = -135;
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		
		compound.putShort("shurikenZRot", (short)this.shurikenZRot);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
	    super.readAdditional(compound);
	    if (compound.contains("shurikenZRot", 99))
	    {
	    	this.shurikenZRot = compound.getShort("shurikenZRot");
	    }
	}
	
	@Override
	protected void onImpact(RayTraceResult result)
	{
		if (this.world.isRemote) {return;}
		if (result != null && result.getType() != RayTraceResult.Type.MISS && this.world instanceof ServerWorld && result.getType() == RayTraceResult.Type.ENTITY)
		{
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(new IndirectEntityDamageSource("shuriken_hit", this, this.func_234616_v_()).setProjectile(), this.getShurikenDamage());
            entity.setMotion(entity.getMotion().add(this.getMotion().x / 2, 0.12F, this.getMotion().z / 2));
            this.applyEntityImpactEffet(entity);
		}
		this.remove();
	}

	@Override protected float getGravityVelocity() {return 0.04F;}
	
	abstract protected float getShurikenDamage();
	abstract protected void applyEntityImpactEffet(Entity entity);
	
	@Override public IPacket<?> createSpawnPacket() {return NetworkHooks.getEntitySpawningPacket(this);}

	@Override abstract protected Item getDefaultItem();
}
