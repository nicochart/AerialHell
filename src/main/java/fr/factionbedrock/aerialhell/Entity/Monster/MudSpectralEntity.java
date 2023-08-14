package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;

public interface MudSpectralEntity
{
    static AttributeSupplier.Builder createSpectralAttributes(double maxHealth, double armor, double attackDamage, double movementSpeed, double followRange)
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, maxHealth)
                .add(Attributes.ARMOR, armor)
                .add(Attributes.ATTACK_DAMAGE, attackDamage)
                .add(Attributes.MOVEMENT_SPEED, movementSpeed)
                .add(Attributes.FOLLOW_RANGE, followRange);
    }

    default void spectralEntityTick(Mob mob)
    {
        if (mob.tickCount > getMaxTicksExisting() - 2) {mob.level().broadcastEntityEvent(mob, (byte)5);}
        if (mob.tickCount > getMaxTicksExisting()) {mob.discard();}
    }

    default void popDisappearingParticles(Mob mob, int count)
    {
        for (int i=0; i<count; i++) {mob.level().addParticle(ParticleTypes.LARGE_SMOKE, mob.getX() + mob.getRandom().nextFloat() - 0.5, mob.getY() + 2 * mob.getRandom().nextFloat(), mob.getZ() + mob.getRandom().nextFloat(), 0.5 * (mob.getRandom().nextFloat()) - 0.5, 0.3D, 0.5 * (mob.getRandom().nextFloat() - 0.5));}
    }

    int getMaxTicksExisting();
}
