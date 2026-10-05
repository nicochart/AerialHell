package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem.VoluciteGolemEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Supplier;

public class VoluciteWardenArmSummonAllyGoal extends VoluciteWardenArmGoal
{
    public VoluciteWardenArmSummonAllyGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, float distanceOffsetTolerance) {super(entity, arm, distanceOffsetTolerance);}

    @Override public List<Phase> getPhases() {return this.goalOwner.armsSummonAllyHandler.getAttackSequence(this.arm.get().getFirst().isRightArm);}

    @Override public boolean shouldTrigger()
    {
        return this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerArmSummonAlly();
    }

    public void summonAlly()
    {
        if (this.goalOwner.level() instanceof ServerLevel level)
        {
            Entity entity = AerialHellEntities.VOLUCITE_GOLEM.get().create(level, EntitySpawnReason.MOB_SUMMONED);
            if (entity instanceof VoluciteGolemEntity golem)
            {
                Vec3 spawnPos = this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCachedUnrotatedRelativePos());
                golem.snapTo(spawnPos.x, spawnPos.y - 1.0D, spawnPos.z, this.goalOwner.getYRot(), 0.0F);
                level.addFreshEntity(golem);

                level.playSound(null, BlockPos.containing(spawnPos), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.HOSTILE, 1.0F, 4.0F);
            }
        }
    }
}