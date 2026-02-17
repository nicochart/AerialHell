package fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem;

import fr.factionbedrock.aerialhell.Entity.AI.*;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.PartEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class VoluciteGolemEntity extends AerialHellGolemEntity
{
    private VoluciteGolemHeadEntity head;

    public VoluciteGolemEntity(EntityType<? extends Monster> type, Level level)
    {
        super(type, level);
        this.xpReward = 16;
        this.head = this.summonHead();
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
        if (damaged)
        {
            //attacking other parts just for attack animation (red overlay)
            falseAttackForRedAnimation(this.head, level, source);
        }
        return damaged;
    }

    private static void falseAttackForRedAnimation(@Nullable PartEntity part, ServerLevel level, DamageSource source)
    {
        if (part != null)
        {
            part.hurtPart(level, source, 0.5F, true);
            part.heal(0.5F);
        }
    }

    private VoluciteGolemHeadEntity summonHead()
    {
        float x = 0.0F;
        float z = 0.0F;
        VoluciteGolemHeadEntity entity = AerialHellEntities.VOLUCITE_GOLEM_HEAD.get().create(this.level(), EntitySpawnReason.NATURAL);
        if (entity != null)
        {
            if (this.isPersistenceRequired()) {entity.setPersistenceRequired();}
            entity.setCustomName(this.getCustomName());
            entity.setInvulnerable(this.isInvulnerable());
            entity.setOwner(this);
            entity.snapTo(this.getX() + (double) x, this.getY(), this.getZ() + (double) z, this.random.nextFloat() * 360.0F, 0.0F);
            this.level().addFreshEntity(entity);
        }
        return entity;
    }

    @Override public void aiStep()
    {
        super.aiStep();
        this.tickHeadMovement();
    }

    private void tickHeadMovement()
    {
        if (this.head == null) {return;}
        this.head.xo = this.head.position().x; this.head.yo = this.head.position().y; this.head.zo = this.head.position().z;
        this.head.setPos(this.getX(), this.getY() + 2.15F, this.getZ());
        if (!this.head.isBeaming())
        {
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

    @Override public void push(Entity other)
    {
        if (other.is(this)) {return;}
        super.push(other);
    }

    @Override public float getYMotionOnAttack() {return 0.4F;}
    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
	@Override public boolean updateTargetOnHurtByLivingEntity() {return true;}
}