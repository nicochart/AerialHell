package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AI.KodamaRattleGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class KodamaEntity extends AerialHellAnimalEntity
{
    private static final EntityDataAccessor<Integer> FACE_ID = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SIZE_ID = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_RATTLING = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> RATTLING_TILT_ANGLE = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    public int timeForceInvisible;
    public int rattleTimerMalus;
    public float rattleHeadRotZAmplitude;

    public KodamaEntity(EntityType<? extends KodamaEntity> type, Level world) {super(type, world); this.setRandomRattleTimerMalusAndHeadRotAmplitude();}

    public static boolean canSpawn(EntityType<? extends AerialHellAnimalEntity> type, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource randomIn)
    {
        return world.getBlockState(pos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT);
    }

    @Override public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverWorldAccess, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData spawnGroupData)
    {
        this.setRandomFaceAndSize();
        this.setRattling(false);
        return super.finalizeSpawn(serverWorldAccess, difficulty, reason, spawnGroupData);
    }

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new KodamaRattleGoal(this));
        super.registerGoals();
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(FACE_ID, 1);
        builder.define(SIZE_ID, 1);
        builder.define(IS_RATTLING, false);
        builder.define(RATTLING_TILT_ANGLE, 0);
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.TEMPT_RANGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26);
    }

    @Override public boolean hurtServer(ServerLevel serverWorld, DamageSource damageSource, float amount)
    {
        this.timeForceInvisible = getMaxTimeForceInvisible();
        return super.hurtServer(serverWorld, damageSource, amount);
    }

    @Nullable @Override public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob mob)
    {
        return AerialHellEntities.KODAMA.create(this.level(), EntitySpawnReason.BREEDING);
    }

    public int getFaceId() {return this.getEntityData().get(FACE_ID);}
    public void setFaceId(int id) {this.getEntityData().set(FACE_ID, id);}
    public int getSizeId() {return this.getEntityData().get(SIZE_ID);}
    public void setSizeId(int id) {this.getEntityData().set(SIZE_ID, id);}
    public boolean isRattling() {return this.getEntityData().get(IS_RATTLING);}
    public void setRattling(boolean b) {this.getEntityData().set(IS_RATTLING, b);}
    public int getRattlingTiltAngle() {return this.getEntityData().get(RATTLING_TILT_ANGLE);}
    public void setRattlingTiltAngle(int value) {this.getEntityData().set(RATTLING_TILT_ANGLE, value);}

    public int getMaxRattlingTiltAngle() {return 20;}
    public int getMaxTimeForceInvisible() {return 200;}

    private void setRandomFaceAndSize()
    {
        this.setFaceId(this.random.nextInt(7)+1);
        this.setSizeId(this.random.nextInt(5)+1);
    }

    private void setRandomRattleTimerMalusAndHeadRotAmplitude()
    {
        this.rattleTimerMalus = random.nextInt(300, 600);
        int negative_or_positive = this.random.nextInt(2) == 0 ? -1 : 1;
        this.rattleHeadRotZAmplitude = negative_or_positive * (5 + this.random.nextInt(5)) / 10.0F;
    }

    @Override public void tick()
    {
        if (this.tickCount % 6000 == 0 && !this.isRattling()) {this.setRandomRattleTimerMalusAndHeadRotAmplitude();}
        if (this.timeForceInvisible > 0) {this.timeForceInvisible--;}
        else if (detectNearbyDanger()) {this.timeForceInvisible = getMaxTimeForceInvisible();}
        super.tick();
    }

    protected boolean detectNearbyDanger() //return true if there is any nearby danger
    {
        List<Entity> nearbyEntities = this.level().getEntities(this, this.getBoundingBox().inflate(10), EntitySelector.withinDistance(this.getX(), this.getY(), this.getZ(), 5));
        for (Entity entity : nearbyEntities)
        {
            if (entity instanceof Player player && !EntityHelper.isCreaOrSpecPlayer(player)) {return true;}
        }
        return false;
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_KODAMA_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_KODAMA_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_KODAMA_DEATH;}
    protected SoundEvent getRattleSound() {return AerialHellSoundEvents.ENTITY_KODAMA_RATTLE;}

    @Override public int getAmbientSoundInterval() {return 420;}

    public void playRattleSound()
    {
        SoundEvent soundevent = this.getRattleSound();
        if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());}
    }

    @Override protected void addAdditionalSaveData(ValueOutput view)
    {
        super.addAdditionalSaveData(view);
        view.putInt("FaceId", this.getFaceId());
        view.putInt("SizeId", this.getSizeId());
        view.putBoolean("IsRattling", this.isRattling());
        view.putInt("TiltAngle", this.getRattlingTiltAngle());
    }

    @Override protected void readAdditionalSaveData(ValueInput view)
    {
        super.readAdditionalSaveData(view);
        if (view.getInt("FaceId").isPresent()) {this.setFaceId(view.getInt("FaceId").get());}
        if (view.getInt("SizeId").isPresent()) {this.setSizeId(view.getInt("SizeId").get());}
        this.setRattling(view.getBooleanOr("IsRattling", false));
        if (view.getInt("TiltAngle").isPresent()) {this.setRattlingTiltAngle(view.getInt("TiltAngle").get());}
    }
}
