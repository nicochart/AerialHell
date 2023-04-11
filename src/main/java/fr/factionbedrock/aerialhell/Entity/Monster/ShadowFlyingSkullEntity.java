package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ShadowFlyingSkullEntity extends VexEntity
{
    public float jawOpeningAmplitude = 0.2F;
    public float jawOpeningFrequencyMalus = 7.0F;
    public ShadowFlyingSkullEntity(EntityType<? extends VexEntity> p_i50190_1_, World p_i50190_2_) {super(p_i50190_1_, p_i50190_2_); this.setLimitedLife(700); this.SetRandomJawOpeningAmplitudeAndFrequency();}

    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 24.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.33D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
    }

    @Override public boolean attackEntityAsMob(Entity attackedEntity)
    {
        if (super.attackEntityAsMob(attackedEntity))
        {
            if (attackedEntity instanceof LivingEntity)
            {
                LivingEntity attackedLiving = ((LivingEntity) attackedEntity);
                if (!EntityHelper.isLivingEntityShadowImmune(attackedLiving))
                {
                    attackedLiving.addPotionEffect(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 80, 0));
                    attackedLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 80, 1));
                }
                else //attacked entity is shadow immune
                {
                    attackedLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 80, 0));
                }
            }
            return true;
        }
        else {return false;}
    }

    public void SetRandomJawOpeningAmplitudeAndFrequency()
    {
        float min_amplitude = 0.1F; float max_amplitude = 0.4F;
        this.jawOpeningAmplitude = min_amplitude + (rand.nextFloat() * (max_amplitude - min_amplitude));
        float min_frequency_malus = 5.0F; float max_frequency_malus = 10.0F;
        this.jawOpeningFrequencyMalus = min_frequency_malus + (rand.nextFloat() * (max_frequency_malus - min_frequency_malus));
    }

    @Override public void playSound(SoundEvent soundIn, float volume, float pitch)
    {
        if (soundIn == SoundEvents.ENTITY_VEX_CHARGE && !this.isSilent())
        {
            this.world.playSound((PlayerEntity)null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_EVOKER_FANGS_ATTACK, this.getSoundCategory(), volume, pitch);
        }
        else {super.playSound(soundIn, volume, pitch);}
    }

    @Override protected SoundEvent getAmbientSound() {return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return SoundEvents.ENTITY_WITHER_SKELETON_HURT;}
}