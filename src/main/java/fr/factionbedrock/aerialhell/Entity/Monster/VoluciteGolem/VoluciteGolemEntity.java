package fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.PartEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class VoluciteGolemEntity extends AerialHellGolemEntity
{
    @Nullable private VoluciteGolemHeadEntity head;
    @Nullable private String headStringUUID;
    protected int timeInInvalidSituation;

    public VoluciteGolemEntity(EntityType<? extends Monster> type, Level level)
    {
        super(type, level);
        this.xpReward = 16;
        this.timeInInvalidSituation = 0;
    }

    public @Nullable VoluciteGolemHeadEntity getHead() {return this.head;}

    @Override @Nullable public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyIn, EntitySpawnReason reason, @Nullable SpawnGroupData spawnDataIn)
    {
        this.head = this.summonHead();
        return spawnDataIn;
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_DEATH.get();}

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.ATTACK_DAMAGE, 17.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    @Override public final boolean hurtServer(ServerLevel level, DamageSource source, float amount)
    {
        boolean damaged = super.hurtServer(level, source, amount);
        if (damaged && this.isDeadOrDying()) {this.onHurtCausingDeath();}
        else if (damaged)
        {
            //attacking other parts just for attack animation (red overlay)
            falseAttackForRedAnimation(this.head, level, source);
        }
        return damaged;
    }

    private void onHurtCausingDeath()
    {
        if (this.head != null)
        {
            this.head.setBeamingPhaseToOff();
            this.head.killPart();
        }
    }

    private static void falseAttackForRedAnimation(@Nullable PartEntity part, ServerLevel level, DamageSource source)
    {
        if (part != null)
        {
            part.hurtPart(level, source, 0.5F, true);
            part.heal(0.5F);
        }
    }

    private void respawnHead()
    {
        if (this.head != null) {this.head.killPart();}
        this.head = this.summonHead();
    }

    private VoluciteGolemHeadEntity summonHead()
    {
        float yOffset = 2.15F;
        VoluciteGolemHeadEntity entity = AerialHellEntities.VOLUCITE_GOLEM_HEAD.get().create(this.level(), EntitySpawnReason.NATURAL);
        if (entity != null)
        {
            if (this.isPersistenceRequired()) {entity.setPersistenceRequired();}
            entity.setCustomName(this.getCustomName());
            entity.setInvulnerable(this.isInvulnerable());
            entity.setOwner(this);
            entity.snapTo(this.getX(), this.getY() + yOffset, this.getZ(), this.getYRot(), this.getXRot());
            this.level().addFreshEntity(entity);
        }
        return entity;
    }

    @Override public void tick()
    {
        super.tick();
        if (this.head == null)
        {
            this.timeInInvalidSituation++;
            this.tryToFindBackHead();
        }
        else {this.timeInInvalidSituation = 0;}
    }

    private void tryToFindBackHead()
    {
        this.head = this.getHeadByUUID(this.headStringUUID);
        if (this.head != null)
        {
            this.head.setOwner(this);
        }
    }

    @Nullable public VoluciteGolemHeadEntity getHeadByUUID(@Nullable String stringUUID)
    {
        if (stringUUID == null) {return null;}

        Entity entity = this.level().getEntity(UUID.fromString(stringUUID));
        return entity instanceof VoluciteGolemHeadEntity foundHead ? foundHead : null;
    }

    @Override public void aiStep()
    {
        super.aiStep();
        this.tickHeadRotation();
    }

    private void tickHeadRotation()
    {
        if (this.head == null) {return;}
        if (!this.head.isBeaming())
        {
            //it actually works, but after a disconnect-reconnect, the head starts moving wrongly
            this.head.yBodyRotO = this.head.yBodyRot;
            this.head.yBodyRot = this.yHeadRot; //the whole "body" is head
            this.head.yHeadRotO = this.head.yHeadRot;
            this.head.yHeadRot = this.yHeadRot;
            this.head.setXRot(this.getXRot());
            this.head.setYRot(this.getYRot());
        }
        else
        {
            this.head.yBodyRot = this.head.yHeadRot;
            this.head.setXRot(0.0F);
            this.head.setYRot(this.head.yHeadRot);
        }
    }

    @Override public void setPos(double x, double y, double z)
    {
        float yOffset = 2.15F;
        super.setPos(x, y, z);
        if (this.head != null) {this.head.setPos(x, y + yOffset, z);}
    }

    @Override public void setXRot(float xRot)
    {
        super.setXRot(xRot);
        if (this.head != null) {this.head.setXRot(xRot);}
    }

    @Override public void setYRot(float yRot)
    {
        super.setYRot(yRot);
        if (this.head != null) {this.head.setXRot(yRot);}
    }

    @Override protected void registerGoals()
    {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        //super.registerGoals(); removed super registerGoals because need to remove MeleeAttackGoal to make it work (atm)
        this.goalSelector.addGoal(5, new ActiveMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(6, new ActiveWaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new ActiveLookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new ActiveRandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MudSoldierEntity.class, true));
    }

    @Override public void addAdditionalSaveData(ValueOutput valueOutput)
    {
        super.addAdditionalSaveData(valueOutput);
        if (this.head != null)
        {
            valueOutput.putString("head_uuid", this.head.getStringUUID());
        }
    }

    @Override public void readAdditionalSaveData(ValueInput valueInput)
    {
        super.readAdditionalSaveData(valueInput);
        if (valueInput.getString("head_uuid").isPresent())
        {
            this.headStringUUID = valueInput.getString("head_uuid").get();
        }
    }

    @Override public void push(Entity other)
    {
        if (other.is(this)) {return;}
        super.push(other);
    }

    @Override public boolean is(Entity other)
    {
        return super.is(other) || other == this.head;
    }

    @Override public double getEyeY() {return this.position().y + 2.40F;}

    @Override public float getYMotionOnAttack() {return 0.4F;}
    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
	@Override public boolean updateTargetOnHurtByLivingEntity() {return true;}
}