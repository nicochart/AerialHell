package fr.factionbedrock.aerialhell.Entity.Monster.Shadow;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public class ShadowFlyingSkullEntity extends Vex
{
    public float jawOpeningAmplitude = 0.2F;
    public float jawOpeningFrequencyMalus = 7.0F;
    public ShadowFlyingSkullEntity(EntityType<? extends Vex> type, Level level) {super(type, level); this.setLimitedLife(700); this.SetRandomJawOpeningAmplitudeAndFrequency();}

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.33D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D);
    }

    @Override public boolean doHurtTarget(Entity attackedEntity)
    {
        if (super.doHurtTarget(attackedEntity))
        {
            if (attackedEntity instanceof LivingEntity)
            {
                LivingEntity attackedLiving = ((LivingEntity) attackedEntity);
                if (!EntityHelper.isLivingEntityShadowImmune(attackedLiving))
                {
                    attackedLiving.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 80, 0));
                    attackedLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 1));
                }
                else //attacked entity is shadow immune
                {
                    attackedLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 0));
                }
            }
            return true;
        }
        else {return false;}
    }

    public void SetRandomJawOpeningAmplitudeAndFrequency()
    {
        float min_amplitude = 0.1F; float max_amplitude = 0.4F;
        this.jawOpeningAmplitude = min_amplitude + (random.nextFloat() * (max_amplitude - min_amplitude));
        float min_frequency_malus = 5.0F; float max_frequency_malus = 10.0F;
        this.jawOpeningFrequencyMalus = min_frequency_malus + (random.nextFloat() * (max_frequency_malus - min_frequency_malus));
    }

    @Override public void playSound(SoundEvent soundIn, float volume, float pitch)
    {
        if (soundIn == SoundEvents.VEX_CHARGE && !this.isSilent())
        {
            this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.EVOKER_FANGS_ATTACK, this.getSoundSource(), volume, pitch);
        }
        else {super.playSound(soundIn, volume, pitch);}
    }

    @Override protected SoundEvent getAmbientSound() {return SoundEvents.WITHER_SKELETON_AMBIENT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.WITHER_SKELETON_DEATH;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.WITHER_SKELETON_HURT;}
}