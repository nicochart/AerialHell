package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AI.KodamaRattleGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;
import java.util.List;

public class KodamaEntity extends AerialHellAnimalEntity
{
    private static final EntityDataAccessor<Integer> FACE_ID = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SIZE_ID = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_RATTLING = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> RATTLING_TILT_ANGLE = SynchedEntityData.defineId(KodamaEntity.class, EntityDataSerializers.INT);
    public int timeForceInvisible;
    public int rattleTimerMalus;
    public float rattleHeadRotZAmplitude;

    public KodamaEntity(EntityType<? extends KodamaEntity> type, Level worldIn) {super(type, worldIn); this.setRandomRattleTimerMalusAndHeadRotAmplitude();}

    public static boolean canSpawn(EntityType<? extends AerialHellAnimalEntity> entityType, LevelAccessor worldIn, EntitySpawnReason spawnType, BlockPos pos, RandomSource random)
    {
        return worldIn.getBlockState(pos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT);
    }

    @Override public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficulty, EntitySpawnReason mobSpawnType, @Nullable SpawnGroupData spawnGroupData)
    {
        this.setRandomFaceAndSize();
        this.setRattling(false);
        return super.finalizeSpawn(serverLevelAccessor, difficulty, mobSpawnType, spawnGroupData);
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

    @Override public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float amount)
    {
        this.timeForceInvisible = getMaxTimeForceInvisible();
        return super.hurtServer(serverLevel, damageSource, amount);
    }

    @Nullable @Override public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mob)
    {
        return AerialHellEntities.KODAMA.get().create(world, EntitySpawnReason.BREEDING);
    }

    public int getFaceId() {return this.entityData.get(FACE_ID);}
    public void setFaceId(int id) {this.entityData.set(FACE_ID, id);}
    public int getSizeId() {return this.entityData.get(SIZE_ID);}
    public void setSizeId(int id) {this.entityData.set(SIZE_ID, id);}
    public boolean isRattling() {return this.entityData.get(IS_RATTLING);}
    public void setRattling(boolean b) {this.entityData.set(IS_RATTLING, b);}
    public int getRattlingTiltAngle() {return this.entityData.get(RATTLING_TILT_ANGLE);}
    public void setRattlingTiltAngle(int value) {this.entityData.set(RATTLING_TILT_ANGLE, value);}

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

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_KODAMA_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_KODAMA_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_KODAMA_DEATH.get();}
    protected SoundEvent getRattleSound() {return AerialHellSoundEvents.ENTITY_KODAMA_RATTLE.get();}

    @Override public int getAmbientSoundInterval() {return 420;}

    public void playRattleSound()
    {
        SoundEvent soundevent = this.getRattleSound();
        if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());}
    }

    @Override public void addAdditionalSaveData(ValueOutput valueOutput)
    {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putInt("FaceId", this.getFaceId());
        valueOutput.putInt("SizeId", this.getSizeId());
        valueOutput.putBoolean("IsRattling", this.isRattling());
        valueOutput.putInt("TiltAngle", this.getRattlingTiltAngle());
    }

    @Override public void readAdditionalSaveData(ValueInput valueInput)
    {
        super.readAdditionalSaveData(valueInput);
        if (valueInput.getInt("FaceId").isPresent()) {this.setFaceId(valueInput.getInt("FaceId").get());}
        if (valueInput.getInt("SizeId").isPresent()) {this.setSizeId(valueInput.getInt("SizeId").get());}
        this.setRattling(valueInput.getBooleanOr("IsRattling", false));
        if (valueInput.getInt("TiltAngle").isPresent()) {this.setRattlingTiltAngle(valueInput.getInt("TiltAngle").get());}
    }
}
