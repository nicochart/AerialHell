package fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers;

import fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden.VoluciteWardenArmBeamAttackGoal;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack.ArmBeamAttackHandler;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack.ArmBeamAttackPhaseType;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.AttackHandlers.ArmBeamAttack.SegmentBeamTargetManager;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//general handler, including right arm handler and left arm handler
public class ArmsBeamAttackHandler
{
    private final VoluciteWardenEntity warden;
    private int ticksSinceLastBeamSound = this.getBeamSoundLength();

    private final ArmBeamAttackHandler rightArmHandler;
    private final ArmBeamAttackHandler leftArmHandler;

    private final SegmentBeamTargetManager targetManager;

    public ArmsBeamAttackHandler(VoluciteWardenEntity warden, List<VoluciteWardenEntity.ArmPartInfo> rightArm, VoluciteWardenArmBeamAttackGoal rightGoal, List<VoluciteWardenEntity.ArmPartInfo> leftArm, VoluciteWardenArmBeamAttackGoal leftGoal)
    {
        this.warden = warden;
        this.rightArmHandler = new ArmBeamAttackHandler(warden, rightArm, rightGoal, this);
        this.leftArmHandler = new ArmBeamAttackHandler(warden, leftArm, leftGoal, this);

        this.targetManager = new SegmentBeamTargetManager(this.warden, entity -> entity instanceof LivingEntity living && !living.isRemoved() && living.isAlive() && this.warden.canAttack(living) && !entity.is(this.warden));
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

        this.ticksSinceLastBeamSound++;
    }

    public void onArmBeamPhaseFinish(ArmBeamAttackPhaseType currentPhaseType, ArmBeamAttackPhaseType nextPhaseType, boolean isRightArm) //when arm stayed at target pos for long enough so the sequence updates to next phase
    {
        if (nextPhaseType == ArmBeamAttackPhaseType.BEAM)
        {
            //enabling beam
            setArmBeam(isRightArm, true);
        }

        if (nextPhaseType == ArmBeamAttackPhaseType.RECOVERY)
        {
            //disabling beam
            setArmBeam(isRightArm, false);
        }
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

    //copy of methods from BeamAttackEntity, edited for arm segments. arms segments beam sound is emitted from master handler to avoid multiple beam sound to play at once
    public void makeBeamStartSound() {this.makeBeamSound(true);}
    public void makeBeamSound() {this.makeBeamSound(false);}
    public void makeBeamSound(boolean beamStart) {if (this.shouldPlayBeamSound()) {this.playBeamSound(beamStart);}}
    public void playBeamSound(boolean start) //volume = 1.5F * this.getMaxBeamLength() / 16.0F because this.getMaxBeamLength() / 16.0F = can be hear up to laser max length. adding 50%
    {
        this.ticksSinceLastBeamSound = 0;
        this.warden.getLevel().playSound(null, this.warden.getX(), this.warden.getY(), this.warden.getZ(), this.getArmBeamSound(start), this.warden.getSelf().getSoundSource(), 0.09375F * VoluciteWardenArmSegmentEntity.MAX_BEAM_LENGTH, 1.0F);
    }
    public boolean shouldPlayBeamSound() {return this.ticksSinceLastBeamSound >= this.getBeamSoundLength();}
    public int getBeamSoundLength() {return 35;}
    public SoundEvent getArmBeamSound(boolean beamStart) {return beamStart ? AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_START.get() : AerialHellSoundEvents.ENTITY_VOLUCITE_GOLEM_BEAM_LOOP.get();}
}