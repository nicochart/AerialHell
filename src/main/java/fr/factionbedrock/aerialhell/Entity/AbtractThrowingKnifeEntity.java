package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbtractThrowingKnifeEntity extends ProjectileItemEntity
{
	public float throwingKnifeZRot; 
	
	public AbtractThrowingKnifeEntity(EntityType<? extends AbtractThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
		this.throwingKnifeZRot = -135;
	}

	public AbtractThrowingKnifeEntity(EntityType<? extends AbtractThrowingKnifeEntity> type, double x, double y, double z, World worldIn)
	{
		super(type, x, y, z, worldIn);
		this.throwingKnifeZRot = -135;
	}

	public AbtractThrowingKnifeEntity(EntityType<? extends AbtractThrowingKnifeEntity> type, LivingEntity owner, World worldIn)
	{
		super(type, owner, worldIn);
		this.throwingKnifeZRot = -135;
	}

	public AbtractThrowingKnifeEntity(EntityType<? extends AbtractThrowingKnifeEntity> type, FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(type, worldIn);
		this.throwingKnifeZRot = -135;
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
