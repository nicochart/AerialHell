package fr.factionbedrock.aerialhell.Entity.Passive;

import fr.factionbedrock.aerialhell.Entity.AI.KodamaRattleGoal;
import fr.factionbedrock.aerialhell.Entity.AerialHellAnimalEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class KodamaEntity extends AerialHellAnimalEntity
{
    private static final TrackedData<Integer> FACE_ID = DataTracker.registerData(KodamaEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> SIZE_ID = DataTracker.registerData(KodamaEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> IS_RATTLING = DataTracker.registerData(KodamaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> RATTLING_TILT_ANGLE = DataTracker.registerData(KodamaEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public int timeForceInvisible;
    public int rattleTimerMalus;
    public float rattleHeadRotZAmplitude;

    public KodamaEntity(EntityType<? extends KodamaEntity> type, World world) {super(type, world); this.setRandomRattleTimerMalusAndHeadRotAmplitude();}

    public static boolean canSpawn(EntityType<? extends AerialHellAnimalEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random randomIn)
    {
        return world.getBlockState(pos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT);
    }

    @Override public EntityData initialize(ServerWorldAccess serverWorldAccess, LocalDifficulty difficulty, SpawnReason reason, @Nullable EntityData spawnGroupData)
    {
        this.setRandomFaceAndSize();
        this.setRattling(false);
        return super.initialize(serverWorldAccess, difficulty, reason, spawnGroupData);
    }

    @Override protected void initGoals()
    {
        this.goalSelector.add(0, new KodamaRattleGoal(this));
        super.initGoals();
    }

    @Override protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(FACE_ID, 1);
        builder.add(SIZE_ID, 1);
        builder.add(IS_RATTLING, false);
        builder.add(RATTLING_TILT_ANGLE, 0);
    }

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return AerialHellAnimalEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.26);
    }

    @Override public boolean damage(DamageSource damageSource, float amount)
    {
        this.timeForceInvisible = getMaxTimeForceInvisible();
        return super.damage(damageSource, amount);
    }

    @Nullable @Override public PassiveEntity createChild(ServerWorld serverWorld, PassiveEntity mob)
    {
        return AerialHellEntities.KODAMA.create(this.getWorld());
    }

    public int getFaceId() {return this.getDataTracker().get(FACE_ID);}
    public void setFaceId(int id) {this.getDataTracker().set(FACE_ID, id);}
    public int getSizeId() {return this.getDataTracker().get(SIZE_ID);}
    public void setSizeId(int id) {this.getDataTracker().set(SIZE_ID, id);}
    public boolean isRattling() {return this.getDataTracker().get(IS_RATTLING);}
    public void setRattling(boolean b) {this.getDataTracker().set(IS_RATTLING, b);}
    public int getRattlingTiltAngle() {return this.getDataTracker().get(RATTLING_TILT_ANGLE);}
    public void setRattlingTiltAngle(int value) {this.getDataTracker().set(RATTLING_TILT_ANGLE, value);}

    public int getMaxRattlingTiltAngle() {return 20;}
    public int getMaxTimeForceInvisible() {return 200;}

    private void setRandomFaceAndSize()
    {
        this.setFaceId(this.random.nextInt(7)+1);
        this.setSizeId(this.random.nextInt(5)+1);
    }

    private void setRandomRattleTimerMalusAndHeadRotAmplitude()
    {
        this.rattleTimerMalus = random.nextBetweenExclusive(300, 600);
        int negative_or_positive = this.random.nextInt(2) == 0 ? -1 : 1;
        this.rattleHeadRotZAmplitude = negative_or_positive * (5 + this.random.nextInt(5)) / 10.0F;
    }

    @Override public void tick()
    {
        if (this.age % 6000 == 0 && !this.isRattling()) {this.setRandomRattleTimerMalusAndHeadRotAmplitude();}
        if (this.timeForceInvisible > 0) {this.timeForceInvisible--;}
        else if (detectNearbyDanger()) {this.timeForceInvisible = getMaxTimeForceInvisible();}
        super.tick();
    }

    protected boolean detectNearbyDanger() //return true if there is any nearby danger
    {
        List<Entity> nearbyEntities = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(10), EntityPredicates.maxDistance(this.getX(), this.getY(), this.getZ(), 5));
        for (Entity entity : nearbyEntities)
        {
            if (entity instanceof PlayerEntity player && !EntityHelper.isCreaOrSpecPlayer(player)) {return true;}
        }
        return false;
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_KODAMA_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_KODAMA_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_KODAMA_DEATH;}
    protected SoundEvent getRattleSound() {return AerialHellSoundEvents.ENTITY_KODAMA_RATTLE;}

    @Override public int getMinAmbientSoundDelay() {return 420;}

    public void playRattleSound()
    {
        SoundEvent soundevent = this.getRattleSound();
        if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());}
    }

    @Override public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("FaceId", this.getFaceId());
        nbt.putInt("SizeId", this.getSizeId());
        nbt.putBoolean("IsRattling", this.isRattling());
        nbt.putInt("TiltAngle", this.getRattlingTiltAngle());
    }

    @Override public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        this.setFaceId(nbt.getInt("FaceId"));
        this.setSizeId(nbt.getInt("SizeId"));
        this.setRattling(nbt.getBoolean("IsRattling"));
        this.setRattlingTiltAngle(nbt.getInt("TiltAngle"));
    }
}
