package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PartEntity extends Monster
{
    private static final EntityDataAccessor<Integer> OWNER_ID = SynchedEntityData.defineId(PartEntity.class, EntityDataSerializers.INT);
    private LivingEntity owner;
    protected int ticksInInvalidSituation;

    public PartEntity(EntityType<? extends Monster> type, Level level)
    {
        super(type, level);
        this.owner = null;
        this.xpReward = 0;
        this.ticksInInvalidSituation = 0;
    }

    @Nullable public LivingEntity getOwner() {return this.owner;}
    public boolean setOwner(LivingEntity owner)
    {
        boolean canSet = this.owner == null;
        if (canSet)
        {
            this.owner = owner;
            this.setOwnerID(this.owner.getId());
        }
        return canSet;
    }

    @Override public void push(Entity other)
    {
        if (other.is(this.owner)) {return;}
        super.push(other);
    }

    @Override public void tick()
    {
        if (this.owner == null) {this.owner = this.getOwnerByID();}
        if (this.owner == null || this.owner.isDeadOrDying() || this.owner.isRemoved() || !this.owner.is(this))
        {
            this.ticksInInvalidSituation++;
            if (this.ticksInInvalidSituation > 5) {this.killPart();}
        }
        super.tick();
    }

    public void killPart()
    {
        //TODO fix bug when part is killed just after a non-killing hit
        if (this.level() instanceof ServerLevel serverLevel)
        {
            this.hurtPart(serverLevel, this.damageSources().fellOutOfWorld(), this.getMaxHealth(), true);
        }
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(OWNER_ID, 0);
    }

    @Override public boolean isPickable() {return true;}

    public final boolean hurtPart(ServerLevel level, DamageSource source, float amount, boolean forceLocalDamage)
    {
        if (forceLocalDamage) {return super.hurtServer(level, source, amount);}
        else {return this.hurtServer(level, source, amount);}
    }

    @Override public final boolean hurtServer(ServerLevel level, DamageSource source, float amount)
    {
        if (this.owner == null || this.owner.isDeadOrDying()) {return super.hurtServer(level, source, amount);}
        if (this.isInvulnerable()) {return false;}
        boolean damaged = this.isInvulnerableToBase(source) ? false : this.owner.hurtServer(level, source, amount);
        return damaged;
    }

    @Override public boolean isInWall() {return false;}

    @Override public boolean is(Entity entity) {return this == entity || this.owner == entity;}

    @Override public boolean isAttackable() {return true;}

    public int getOwnerID() {return this.getEntityData().get(OWNER_ID);}
    public void setOwnerID(int id) {this.getEntityData().set(OWNER_ID, id);}

    @Nullable public LivingEntity getOwnerByID()
    {
        Entity entity = this.level().getEntity(this.getOwnerID());
        return entity instanceof LivingEntity livingEntity ? livingEntity : null;
    }

    @Override public boolean causeFallDamage(double fallDistance, float damageMultiplier, DamageSource damageSource) {return false;}

    @Override public boolean isNoGravity() {return !this.isDeadOrDying();}
}
