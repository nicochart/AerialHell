package fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem;

import com.google.common.collect.Maps;
import fr.factionbedrock.aerialhell.Entity.AI.ConditionalGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.MasterPartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class VoluciteGolemEntity extends AerialHellGolemEntity implements MasterPartEntity
{
    /* -- MasterPartEntity fields -- */
    public final Map<String, PartInfo> PARTS_MAP = Maps.newHashMap();
    private static final EntityDataAccessor<Integer> HEAD_ID = SynchedEntityData.defineId(VoluciteGolemEntity.class, EntityDataSerializers.INT);
    private final PartInfo HEAD = new PartInfo(AerialHellEntities.VOLUCITE_GOLEM_HEAD.get(), "head", HEAD_ID, new Vec3(0.0F, 2.15F, 0.0F), true, PARTS_MAP);
    /* ----------------------------- */

    public VoluciteGolemEntity(EntityType<? extends Monster> type, Level level)
    {
        super(type, level);
        this.xpReward = 16;
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);

        /* -- MasterPartEntity synched data -- */
        builder.define(HEAD_ID, 0);
        /* ----------------------------------- */
    }

    /* ------------------------------------------------------------------------- */
    /* ---------- MasterPartEntity : Interface methods implementation ---------- */
    /* ------------------------------------------------------------------------- */
    @Override public Map<String, PartInfo> getPartInfoMap() {return this.PARTS_MAP;}
    /* ------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------- */
    /* ---------- MasterPartEntity : Interface methods Overridden for specific behavior ---------- */
    /* ------------------------------------------------------------------------------------------- */
    @Override public void tickHeadPartRotation(PartInfo partInfo, @NotNull PartEntity headPartEntity)
    {
        if (headPartEntity instanceof VoluciteGolemHeadEntity headPart && headPart.isBeaming())
        {
            float xRot = 0.0F;
            if (headPart.getBeamTargetPos() != null)
            {
                xRot = this.calculateXRotFromOriginToTarget(this.getEyePosition(), headPart.getBeamTargetPos());
            }

            headPart.yBodyRot = headPart.yHeadRot;
            headPart.setXRot(xRot);
            headPart.setYRot(headPart.yHeadRot);
        }
        else {MasterPartEntity.super.tickHeadPartRotation(partInfo, headPartEntity);}
    }
    /* ------------------------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------------------------- */

    /* ----------------------------------------------------------------------------------------------- */
    /* ---------- MasterPartEntity : Superclass methods Overridden to delegate to interface ---------- */
    /* ----------------------------------------------------------------------------------------------- */
    @Override @Nullable public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyIn, EntitySpawnReason reason, @Nullable SpawnGroupData spawnDataIn)
    {
        return this.finalizePartSpawn(level, difficultyIn, reason, spawnDataIn);
    }

    @Override public final boolean hurtServer(ServerLevel level, DamageSource source, float amount)
    {
        boolean superDamaged = super.hurtServer(level, source, amount);
        this.partHurtServer(superDamaged, level, source, amount);
        return superDamaged;
    }

    @Override public void tick()
    {
        super.tick();
        this.partEntityTick();
    }

    @Override public void aiStep()
    {
        super.aiStep();
        this.partAiStep();
    }

    @Override public void addAdditionalSaveData(ValueOutput valueOutput)
    {
        super.addAdditionalSaveData(valueOutput);
        this.partAddAdditionalSaveData(valueOutput);
    }

    @Override public void readAdditionalSaveData(ValueInput valueInput)
    {
        super.readAdditionalSaveData(valueInput);
        this.partReadAdditionalSaveData(valueInput);
    }

    @Override public void push(Entity other)
    {
        if (!this.partCanBePushedBy(other)) {return;}
        super.push(other);
    }

    @Override public boolean is(Entity other) {return super.is(other) || this.recognizesChildPart(other);}
    /* ----------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------- */

    /* --------------------------------------------------------------------------------------------------- */
    /* ----------- MasterPartEntity : Superclass methods Overridden for part-specific behavior ----------- */
    /* --------------------------------------------------------------------------------------------------- */
    @Override public double getEyeY() {return this.position().y + 2.40F;}

    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) {return false;}
    /* --------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------- */

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

    @Override protected void registerGoals()
    {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));

        //super.registerGoals(); removed super registerGoals because need to remove MeleeAttackGoal to make it work (atm)
        this.goalSelector.addGoal(5, new ConditionalGoal(this, new MeleeAttackGoal(this, 1.25D, false)));
        this.goalSelector.addGoal(6, new ConditionalGoal(this, new WaterAvoidingRandomStrollGoal(this, 0.6D)));
        this.goalSelector.addGoal(7, new ConditionalGoal(this, new LookAtPlayerGoal(this, Player.class, 8.0F)));
        this.goalSelector.addGoal(8, new ConditionalGoal(this, new RandomLookAroundGoal(this)));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MudSoldierEntity.class, true));
    }

    @Override public float getYMotionOnAttack() {return 0.4F;}
	@Override public boolean updateTargetOnHurtByLivingEntity() {return true;}
}