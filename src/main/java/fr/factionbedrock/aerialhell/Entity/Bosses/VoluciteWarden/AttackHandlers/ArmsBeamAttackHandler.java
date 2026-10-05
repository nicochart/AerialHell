package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmBeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm.ArmBeamAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.Arm.SegmentBeamTargetManager;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.PhaseType;
import fr.factionbedrock.aerialhell.Entity.AI.Phase.InactivePhase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//general handler, including right arm handler and left arm handler
public class ArmsBeamAttackHandler
{
    private final VoluciteWardenEntity warden;
    private int ticksSinceLastBeamLoopSound = this.getBeamLoopSoundRepeatInterval();

    private final ArmBeamAttackHandler rightArmHandler;
    private final ArmBeamAttackHandler leftArmHandler;

    private final SegmentBeamTargetManager targetManager;

    public ArmsBeamAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> rightArm, VoluciteWardenArmBeamAttackGoal rightGoal, List<VoluciteWardenEntity.ArmPartInfo> leftArm, VoluciteWardenArmBeamAttackGoal leftGoal)
    {
        this.warden = warden;
        this.rightArmHandler = new ArmBeamAttackHandler(warden, rightArm, rightGoal, this);
        this.leftArmHandler = new ArmBeamAttackHandler(warden, leftArm, leftGoal, this);

        this.targetManager = new SegmentBeamTargetManager(this.warden, entity -> entity instanceof LivingEntity living && !living.is(AerialHellTags.Entities.VOLUCITE) && !living.isRemoved() && living.isAlive() && this.warden.canAttack(living) && !entity.is(this.warden));
    }

    public boolean isClientSide() {return this.warden.level().isClientSide();}

    @Nullable public ArmBeamAttackHandler getOtherArmHandler(ArmBeamAttackHandler askingHandler)
    {
        if (askingHandler == this.leftArmHandler) {return this.rightArmHandler;}
        else if (askingHandler == this.rightArmHandler) {return this.leftArmHandler;}
        return null;
    }

    public void tick()
    {
        this.rightArmHandler.tick();
        this.leftArmHandler.tick();

        if (!this.isClientSide())
        {
            this.targetManager.tick();
        }

        this.ticksSinceLastBeamLoopSound++;
    }

    public void setArmBeam(boolean isRightArm, boolean enable)
    {
        if (isRightArm)
        {
            if (enable)
            {
                this.rightArmHandler.enableBeam();
            }
            else
            {
                this.rightArmHandler.disableBeam();
                this.targetManager.clearArmTargets(true);
            }
        }
        else //isLeftArm
        {
            if (enable)
            {
                this.leftArmHandler.enableBeam();
            }
            else
            {
                this.leftArmHandler.disableBeam();
                this.targetManager.clearArmTargets(false);
            }
        }
    }

    public List<Phase> getAttackSequence(boolean isRightArm)
    {
        return isRightArm ? this.rightArmHandler.attackSequence : this.leftArmHandler.attackSequence;
    }

    public List<Phase> createAttackSequence(List<VoluciteWardenEntity.ArmPartInfo> arm)
    {
        boolean isRightArm = arm.getFirst().isRightArm;
        int sideFactor = isRightArm ? 1 : -1;
        return List.of(
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos0(sideFactor), 1.0D, 1),
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos1(sideFactor), 1.0D, 1),
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos2(sideFactor), 1.0D, 1),
                new Phase(PhaseType.PREPARE, () -> this.getRelativePreparePos3(sideFactor), 1.0D, 1),
                new Phase(PhaseType.ACTION, () -> this.getRelativeBeamingPos(sideFactor), 2.0D, VoluciteWardenArmSegmentEntity.BEAMING_TOTAL_DURATION + 20)
                        .onStart((goal) -> this.setArmBeam(isRightArm, true))
                        .onEnd((goal) -> this.setArmBeam(isRightArm, false))
                        .onForceEnd((goal) -> this.setArmBeam(isRightArm, false)),
                new Phase(PhaseType.RECOVERY, () -> this.getRelativeBeamRecoveryPos(sideFactor), 0.4D, 1),
                new InactivePhase()
        );
    }

    private Vec3 getRelativePreparePos0(int sideFactor) {return new Vec3(sideFactor * 12.0F, 10.0F, 0.0F);}
    private Vec3 getRelativePreparePos1(int sideFactor) {return new Vec3(sideFactor * 18.0F, 17.0F, 2.0F);}
    private Vec3 getRelativePreparePos2(int sideFactor) {return new Vec3(sideFactor * 24.0F, 23.0F, 4.0F);}
    private Vec3 getRelativePreparePos3(int sideFactor) {return new Vec3(sideFactor * 28.0F, 26.5F, 5.0F);}
    private Vec3 getRelativeBeamingPos(int sideFactor) {return this.getRelativePreparePos3(sideFactor);}
    private Vec3 getRelativeBeamRecoveryPos(int sideFactor) {return new Vec3(sideFactor * 9.5F, 5.5F, 0.0F);}

    //copy of methods from BeamAttackEntity, edited for arm segments. arms segments beam sound is emitted from master handler to avoid multiple beam loop sound to play at once
    public void tickBeamSounds(int currentBeamingTime, int loadDuration, int totalDuration)
    {
        if (this.isBeamSilent()) {return;}

        if (currentBeamingTime == 1) //beam load
        {
            this.playBeamSound(this.getBeamLoadSound());
        }
        else if (currentBeamingTime == loadDuration) //beam start
        {
            this.playBeamSound(this.getBeamStartSound());
            if (this.shouldPlayBeamLoopSound()) //loop sound control (avoid multiple plays at a time)
            {
                this.ticksSinceLastBeamLoopSound = 0;
                this.playBeamSound(this.getBeamLoopSound());
            }
        }
        else if (currentBeamingTime > loadDuration && currentBeamingTime + this.getBeamLoopSoundRepeatInterval() <= totalDuration) //beam loop
        {
            if (this.shouldPlayBeamLoopSound()) //loop sound control (avoid multiple plays at a time)
            {
                int timeInLoop = currentBeamingTime - loadDuration;
                if (timeInLoop % this.getBeamLoopSoundRepeatInterval() == 0)
                {
                    this.ticksSinceLastBeamLoopSound = 0;
                    this.playBeamSound(this.getBeamLoopSound());
                }
            }
        }
    }

    public void playBeamSound(@Nullable SoundEvent sound)
    {
        if (sound == null) return;

        //volume = 1.5F * this.getMaxBeamLength() / 16.0F because this.getMaxBeamLength() / 16.0F = can be hear up to laser max length. adding 50%
        float volume = 0.09375F * VoluciteWardenArmSegmentEntity.MAX_BEAM_LENGTH;

        this.warden.getLevel().playSound(null, this.warden.getX(), this.warden.getY(), this.warden.getZ(), sound, this.warden.getSelf().getSoundSource(), volume, 1.0F);
    }

    public boolean shouldPlayBeamLoopSound() {return this.ticksSinceLastBeamLoopSound >= this.getBeamLoopSoundRepeatInterval();}

    public SoundEvent getBeamLoadSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_LOAD.get();}
    public SoundEvent getBeamStartSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_START.get();}
    public SoundEvent getBeamLoopSound() {return AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_LOOP.get();}
    public int getBeamLoopSoundRepeatInterval() {return 60;}
    public int getBeamLoopSoundOverlap() {return 18;}

    public boolean isBeamSilent() {return false;}
}