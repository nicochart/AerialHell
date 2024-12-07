package fr.factionbedrock.aerialhell.Entity.Projectile;

import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public abstract class AbstractShurikenEntity extends ThrownItemEntity
{
	public float shurikenZRot;

	public AbstractShurikenEntity(EntityType<? extends ThrownItemEntity> type, World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
	{
		super(type, shooter, world);
		this.setVelocity(accelX, accelY, accelZ, velocity, inaccuracy);
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
		this.shurikenZRot = -135;
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, double x, double y, double z, World world)
	{
		super(type, x, y, z, world);
		this.shurikenZRot = -135;
	}

	public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, LivingEntity shooter, World world)
	{
		super(type, shooter, world);
		this.shurikenZRot = -135;
	}

	/*public AbstractShurikenEntity(EntityType<? extends AbstractShurikenEntity> type, PlayMessages.SpawnEntity packet, Level world)
	{
		super(type, world);
		this.shurikenZRot = -135;
	}*/
	
	@Override
	public void writeCustomDataToNbt(NbtCompound nbt)
	{
		super.writeCustomDataToNbt(nbt);

		nbt.putShort("shurikenZRot", (short)this.shurikenZRot);
	}
	
	@Override
	public void readCustomDataFromNbt(NbtCompound nbt)
	{
	    super.readCustomDataFromNbt(nbt);
	    if (nbt.contains("shurikenZRot", 99))
	    {
	    	this.shurikenZRot = nbt.getShort("shurikenZRot");
	    }
	}
	
	@Override
	protected void onCollision(HitResult result)
	{
		if (this.getWorld().isClient()) {return;}
		if (result != null && result.getType() != HitResult.Type.MISS && this.getWorld() instanceof ServerWorld && result.getType() == HitResult.Type.ENTITY)
		{
            Entity entity = ((EntityHitResult)result).getEntity();
            entity.damage(AerialHellDamageTypes.getDamageSource(this.getWorld(), AerialHellDamageTypes.SHURIKEN_HIT, this, this.getOwner()), this.getKnifeDamage());
            entity.setVelocity(entity.getVelocity().add(this.getVelocity().x / 2, 0.12F, this.getVelocity().z / 2));
            this.applyEntityImpactEffet(entity);
		}
		this.discard();
	}

	@Override protected double getGravity() {return 0.04F;}
	
	abstract protected float getKnifeDamage();
	abstract protected void applyEntityImpactEffet(Entity entity);
	
	//@Override public Packet<ClientGamePacketListener> getAddEntityPacket() {return ForgeHooks.getEntitySpawnPacket(this);}

	@Override abstract protected Item getDefaultItem();
}
