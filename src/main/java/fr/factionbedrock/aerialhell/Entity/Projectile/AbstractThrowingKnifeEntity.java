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

public abstract class AbstractThrowingKnifeEntity extends ProjectileItemEntity
{
	public float throwingKnifeZRot; 
	
	public AbstractThrowingKnifeEntity(EntityType<? extends AbstractThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
		this.throwingKnifeZRot = -135;
	}

	public AbstractThrowingKnifeEntity(EntityType<? extends AbstractThrowingKnifeEntity> type, double x, double y, double z, World worldIn)
	{
		super(type, x, y, z, worldIn);
		this.throwingKnifeZRot = -135;
	}

	public AbstractThrowingKnifeEntity(EntityType<? extends AbstractThrowingKnifeEntity> type, LivingEntity shooter, World worldIn)
	{
		super(type, shooter, worldIn);
		this.throwingKnifeZRot = -135;
	}

	public AbstractThrowingKnifeEntity(EntityType<? extends AbstractThrowingKnifeEntity> type, FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(type, worldIn);
		this.throwingKnifeZRot = -135;
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		
		compound.putShort("knifeZRot", (short)this.throwingKnifeZRot);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
	    super.readAdditional(compound);
	    if (compound.contains("knifeZRot", 99))
	    {
	    	this.throwingKnifeZRot = compound.getShort("knifeZRot");
	    }
	}
	
	@Override
	protected void onImpact(RayTraceResult result)
	{
		if (this.world.isRemote) {return;}
		if (result != null && result.getType() != RayTraceResult.Type.MISS && this.world instanceof ServerWorld && result.getType() == RayTraceResult.Type.ENTITY)
		{
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(new IndirectEntityDamageSource("throwing_knife_hit", this, this.func_234616_v_()).setProjectile(), this.getKnifeDamage());
            entity.setMotion(entity.getMotion().add(this.getMotion().x / 2, 0.12F, this.getMotion().z / 2));
            this.applyEntityImpactEffet(entity);
		}
		
		this.remove();
	}
	
	abstract protected float getKnifeDamage();
	abstract protected void applyEntityImpactEffet(Entity entity);
	
	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	abstract protected Item getDefaultItem();
}
