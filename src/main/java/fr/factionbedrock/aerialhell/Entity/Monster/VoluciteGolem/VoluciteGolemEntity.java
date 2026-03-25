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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class VoluciteGolemEntity extends AerialHellGolemEntity implements MasterPartEntity
{
    /* -- MasterPartEntity fields -- */
    public final Map<String, PartInfo> PARTS_MAP = Maps.newHashMap();
    private static final TrackedData<Integer> HEAD_ID = DataTracker.registerData(VoluciteGolemEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private final PartInfo HEAD = new PartInfo(AerialHellEntities.VOLUCITE_GOLEM_HEAD, "head", HEAD_ID, new Vec3d(0.0F, 2.15F, 0.0F), true, PARTS_MAP);
    /* ----------------------------- */

    public VoluciteGolemEntity(EntityType<? extends HostileEntity> type, World world)
    {
        super(type, world);
        this.experiencePoints = 16;
    }

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);

        /* -- MasterPartEntity synched data -- */
        builder.add(HEAD_ID, 0);
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
    @Override public void tickHeadPartRotation(PartInfo partInfo)
    {
        if (partInfo.getPart() == null) {return;}
        if (partInfo.getPart().getSelf() instanceof VoluciteGolemHeadEntity headPart && headPart.isBeaming())
        {
            float pitch = 0.0F;
            if (headPart.getBeamTargetPos() != null)
            {
                pitch = this.calculatePitchFromOriginToTarget(this.getEyePos(), headPart.getBeamTargetPos());
            }

            headPart.bodyYaw  = headPart.headYaw;
            headPart.setPitch(pitch);
            headPart.setYaw(headPart.headYaw);
        }
        else {MasterPartEntity.super.tickHeadPartRotation(partInfo);}
    }
    /* ------------------------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------------------------- */
    /* ------------------------------------------------------------------------------------------- */

    /* ----------------------------------------------------------------------------------------------- */
    /* ---------- MasterPartEntity : Superclass methods Overridden to delegate to interface ---------- */
    /* ----------------------------------------------------------------------------------------------- */

    @Override @Nullable public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData)
    {
        return this.initializePart(world, difficulty, spawnReason, entityData);
    }

    @Override public final boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
    {
        boolean superDamaged = super.damage(serverWorld, source, amount);
        this.partDamage(superDamaged, serverWorld, source, amount);
        return superDamaged;
    }

    @Override public void tick()
    {
        super.tick();
        this.partEntityTick();
    }

    @Override public void tickMovement()
    {
        super.tickMovement();
        this.partEntityTickMovement();
    }

    @Override protected void writeCustomData(WriteView view)
    {
        super.writeCustomData(view);
        this.partWriteCustomData(view);
    }

    @Override protected void readCustomData(ReadView view)
    {
        super.readCustomData(view);
        this.partReadCustomData(view);
    }

    @Override public void pushAwayFrom(Entity other)
    {
        if (!this.partCanBePushedAwayBy(other)) {return;}
        super.pushAwayFrom(other);
    }

    @Override public boolean isPartOf(Entity other) {return super.isPartOf(other) || this.recognizesChildPart(other);}
    /* ----------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------------------- */


    /* --------------------------------------------------------------------------------------------------- */
    /* ----------- MasterPartEntity : Superclass methods Overridden for part-specific behavior ----------- */
    /* --------------------------------------------------------------------------------------------------- */
    @Override public double getEyeY() {return this.getEntityPos().y + 2.40F;}

    @Override public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {return false;}
    /* --------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------- */

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_WARDEN_VOLUCITE_GOLEM_DEATH;}

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 100.0D)
                .add(EntityAttributes.ARMOR, 3.0D)
                .add(EntityAttributes.ATTACK_DAMAGE, 17.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.23D)
                .add(EntityAttributes.FOLLOW_RANGE, 48.0D);
    }

    @Override protected void initGoals()
    {
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));

        //super.initGoals(); removed super initGoals because need to remove MeleeAttackGoal to make it work (atm)
        this.goalSelector.add(5, new ConditionalGoal(this, new MeleeAttackGoal(this, 1.25D, false)));
        this.goalSelector.add(6, new ConditionalGoal(this, new WanderAroundFarGoal(this, 0.6D)));
        this.goalSelector.add(7, new ConditionalGoal(this, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F)));
        this.goalSelector.add(8, new ConditionalGoal(this, new LookAroundGoal(this)));
        this.targetSelector.add(0, new RevengeGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, MudSoldierEntity.class, true));
    }

    @Override public float getYMotionOnAttack() {return 0.4F;}
	@Override public boolean updateTargetOnHurtByLivingEntity() {return true;}
}