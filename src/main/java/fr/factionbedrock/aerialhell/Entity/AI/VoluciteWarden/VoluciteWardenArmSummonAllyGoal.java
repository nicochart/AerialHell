package fr.factionbedrock.aerialhell.Entity.AI.VoluciteWarden;

import fr.factionbedrock.aerialhell.Entity.AI.Phase.Phase;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.ElectroSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem.VoluciteGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteGlyphParticles;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Supplier;

//summons a volucite golem below the hand, with electro spirits on each side of it. all of them slowly fall to the ground (slow falling)
public class VoluciteWardenArmSummonAllyGoal extends VoluciteWardenArmGoal
{
    private static final int MIN_SPIRITS = 2, MAX_SPIRITS = 4; //electro spirits summoned around the golem
    private static final float SPIRITS_SPAWN_DISTANCE = 2.5F; //horizontal distance between the golem and each spirit
    private static final double SPIRITS_SPAWN_SPEED = 0.3D; //outward horizontal speed of the spirits, at spawn
    private static final float SLOW_FALLING_TERMINAL_SPEED = 0.49F; //blocks per tick (slow falling gravity 0.01, air drag 0.98)
    private static final int SLOW_FALLING_EXTRA_DURATION = 60; //ticks : acceleration to terminal speed & margin
    private static final int SUMMON_GLYPHS_COUNT = 24; //glyph particles from the hand, downward, when summoning
    private static final float SUMMON_GLYPHS_SPREAD = 4.0F, SUMMON_GLYPHS_MIN_DEPTH = 6.0F, SUMMON_GLYPHS_MAX_DEPTH = 14.0F;

    public VoluciteWardenArmSummonAllyGoal(VoluciteWardenEntity entity, Supplier<List<VoluciteWardenEntity.ArmPartInfo>> arm, float distanceOffsetTolerance) {super(entity, arm, distanceOffsetTolerance);}

    @Override public List<Phase> getPhases() {return this.goalOwner.armsSummonAllyHandler.getAttackSequence(this.arm.get().getFirst().isRightArm);}

    @Override public boolean shouldTrigger()
    {
        return this.goalOwner.getTarget() != null && this.goalOwner.shouldTriggerArmSummonAlly();
    }

    public void summonAlly()
    {
        if (!(this.goalOwner.level() instanceof ServerLevel level)) {return;}

        Vec3 handPos = this.goalOwner.fromUnrotatedRelativeToLevelPos(this.getCachedUnrotatedRelativePos());
        Vec3 spawnPos = handPos.subtract(0.0D, 1.0D, 0.0D);
        int slowFallingDuration = this.getSlowFallingDuration(spawnPos);

        Entity entity = AerialHellEntities.VOLUCITE_GOLEM.get().create(level, EntitySpawnReason.MOB_SUMMONED);
        if (entity instanceof VoluciteGolemEntity golem)
        {
            golem.snapTo(spawnPos.x, spawnPos.y, spawnPos.z, this.goalOwner.getYRot(), 0.0F);
            golem.finalizeSpawn(level, level.getCurrentDifficultyAt(golem.blockPosition()), EntitySpawnReason.MOB_SUMMONED, null); //summons golem parts (without them, the golem would be reset, losing its slow falling)
            addSlowFalling(golem, slowFallingDuration);
            level.addFreshEntity(golem);
        }
        this.summonElectroSpirits(level, spawnPos, slowFallingDuration);

        VoluciteGlyphParticles.sendDownward(level, handPos, SUMMON_GLYPHS_SPREAD, SUMMON_GLYPHS_MIN_DEPTH, SUMMON_GLYPHS_MAX_DEPTH, SUMMON_GLYPHS_COUNT);
        level.playSound(null, BlockPos.containing(spawnPos), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.HOSTILE, 1.0F, 4.0F);
    }

    //on each side of the golem, evenly spread around it and moving outward (like mud cycle mage summoned entities)
    private void summonElectroSpirits(ServerLevel level, Vec3 golemPos, int slowFallingDuration)
    {
        RandomSource random = this.goalOwner.getRandom();
        int count = MIN_SPIRITS + random.nextInt(MAX_SPIRITS - MIN_SPIRITS + 1);
        float startAngle = random.nextFloat() * Mth.TWO_PI;
        for (int i = 0; i < count; i++)
        {
            Entity entity = AerialHellEntities.ELECTRO_SPIRIT.get().create(level, EntitySpawnReason.MOB_SUMMONED);
            if (entity instanceof ElectroSpiritEntity spirit)
            {
                float angle = startAngle + i * Mth.TWO_PI / count;
                Vec3 outward = new Vec3(Mth.cos(angle), 0.0D, Mth.sin(angle));
                Vec3 pos = golemPos.add(outward.scale(SPIRITS_SPAWN_DISTANCE));
                spirit.snapTo(pos.x, pos.y, pos.z, this.goalOwner.getYRot(), 0.0F);
                spirit.setDeltaMovement(outward.scale(SPIRITS_SPAWN_SPEED));
                addSlowFalling(spirit, slowFallingDuration);
                level.addFreshEntity(spirit);
            }
        }
    }

    //time to fall from spawnPos to the warden feet height, with slow falling
    private int getSlowFallingDuration(Vec3 spawnPos)
    {
        double fallHeight = Math.max(0.0D, spawnPos.y - this.goalOwner.getY());
        return Mth.ceil(fallHeight / SLOW_FALLING_TERMINAL_SPEED) + SLOW_FALLING_EXTRA_DURATION;
    }

    private static void addSlowFalling(LivingEntity entity, int duration) {entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, duration, 0, false, false));} //hidden particles
}
