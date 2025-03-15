package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.particle.ParticleTypes;

public interface MudSpectralEntity
{
    static DefaultAttributeContainer.Builder createSpectralAttributes(double maxHealth, double armor, double attackDamage, double movementSpeed, double followRange)
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, maxHealth)
                .add(EntityAttributes.ARMOR, armor)
                .add(EntityAttributes.ATTACK_DAMAGE, attackDamage)
                .add(EntityAttributes.MOVEMENT_SPEED, movementSpeed)
                .add(EntityAttributes.FOLLOW_RANGE, followRange);
    }

    default void spectralEntityTick(MobEntity mob)
    {
        if (mob.age > getMaxTicksExisting() - 2) {mob.getWorld().sendEntityStatus(mob, (byte)5);}
        if (mob.age > getMaxTicksExisting()) {mob.discard();}
    }

    default void popDisappearingParticles(MobEntity mob, int count)
    {
        for (int i=0; i<count; i++) {mob.getWorld().addParticle(ParticleTypes.LARGE_SMOKE, mob.getX() + mob.getRandom().nextFloat() - 0.5, mob.getY() + 2 * mob.getRandom().nextFloat(), mob.getZ() + mob.getRandom().nextFloat(), 0.5 * (mob.getRandom().nextFloat()) - 0.5, 0.3D, 0.5 * (mob.getRandom().nextFloat() - 0.5));}
    }

    int getMaxTicksExisting();
}
