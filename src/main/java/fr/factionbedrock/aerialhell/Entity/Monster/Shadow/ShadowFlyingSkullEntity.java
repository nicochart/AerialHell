package fr.factionbedrock.aerialhell.Entity.Monster.Shadow;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ShadowFlyingSkullEntity extends VexEntity
{
    public float jawOpeningAmplitude = 0.2F;
    public float jawOpeningFrequencyMalus = 7.0F;
    public ShadowFlyingSkullEntity(EntityType<? extends VexEntity> type, World world) {super(type, world); this.setLifeTicks(700); this.SetRandomJawOpeningAmplitudeAndFrequency();}

    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 20.0D)
                .add(EntityAttributes.FOLLOW_RANGE, 24.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.33D)
                .add(EntityAttributes.ATTACK_DAMAGE, 6.0D);
    }

    @Override public boolean tryAttack(ServerWorld serverWorld, Entity attackedEntity)
    {
        if (super.tryAttack(serverWorld, attackedEntity))
        {
            if (attackedEntity instanceof LivingEntity)
            {
                LivingEntity attackedLiving = ((LivingEntity) attackedEntity);
                if (!EntityHelper.isLivingEntityShadowImmune(attackedLiving))
                {
                    attackedLiving.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 80, 0));
                    attackedLiving.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 1));
                }
                else //attacked entity is shadow immune
                {
                    attackedLiving.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 0));
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
        if (soundIn == SoundEvents.ENTITY_VEX_CHARGE && !this.isSilent())
        {
            this.getWorld().playSound((PlayerEntity) null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_EVOKER_FANGS_ATTACK, this.getSoundCategory(), volume, pitch);
        }
        else {super.playSound(soundIn, volume, pitch);}
    }

    @Override protected SoundEvent getAmbientSound() {return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.ENTITY_WITHER_SKELETON_HURT;}
}