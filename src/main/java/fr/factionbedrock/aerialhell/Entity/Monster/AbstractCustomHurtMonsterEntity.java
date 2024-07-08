package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.Util.CustomHurtInfo;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class AbstractCustomHurtMonsterEntity extends Monster
{
    public AbstractCustomHurtMonsterEntity(EntityType<? extends AbstractCustomHurtMonsterEntity> type, Level world) {super(type, world);}

    @Override public boolean hurt(DamageSource damageSource, float amount)
    {
        return this.customHurt(damageSource, this.getDefaultCustomHurtInfo(amount));
    }

    protected CustomHurtInfo getDefaultCustomHurtInfo(float amount)
    {
        return new CustomHurtInfo(amount, this.defaultKbStrength(), this.shouldPlayHurtOrDeathSoundOnHurt(), this.shouldApplyKbOnHurt());
    }

    protected abstract float defaultKbStrength();
    protected abstract boolean shouldPlayHurtOrDeathSoundOnHurt();
    protected abstract boolean shouldApplyKbOnHurt();

    //copy of net.minecraft.world.entity.LivingEntity hurt(DamageSource source, float amount) method, removing everything non-related to my monsters, and calling other methods, allowing customization in my inheriting classes
    public boolean customHurt(DamageSource source, CustomHurtInfo info)
    {
        float amount = info.amount();
        if (this.isInvulnerableTo(source) || this.level().isClientSide || this.isDeadOrDying()) {return false;}
        else if (source.is(DamageTypeTags.IS_FIRE) && this.hasEffect(MobEffects.FIRE_RESISTANCE)) {return false;}
        else if (net.neoforged.neoforge.common.CommonHooks.onEntityIncomingDamage(this, this.damageContainers.peek())) {return false;}
        else
        {
            this.noActionTime = 0;

            if (source.is(DamageTypeTags.IS_FREEZING) && this.getType().is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {amount *= 5.0F;}
            this.walkAnimation.setSpeed(1.5F);

            boolean wasOnHurtCooldown = (float)this.invulnerableTime > 10.0F && !source.is(DamageTypeTags.BYPASSES_COOLDOWN);
            boolean actuallyGotHurt = tryActuallyHurt(source, amount);

            if (!actuallyGotHurt) {return false;}
            //we know this got hurt
            setLastHurtBy(source);

            if (!wasOnHurtCooldown)
            {
                this.level().broadcastDamageEvent(this, source);
                if (!source.is(DamageTypeTags.NO_IMPACT)) {this.markHurt();}

                if (info.applyKb()) {tryApplyingKnockback(source, info.kbStrength());}
            }

            boolean died = false;
            if (this.isDeadOrDying()) {this.customDie(source, info.playSound()); died = true;}

            if (!wasOnHurtCooldown && info.playSound())
            {
                if (died) {/*this.playDeathSound(source);*/} //death sound is now played in customDie(DamageSource, boolean) method
                else {this.playHurtSound(source);}
            }

            this.lastDamageSource = source;
            this.lastDamageStamp = this.level().getGameTime();

            if (source.getEntity() instanceof ServerPlayer serverPlayerSource)
            {
                CriteriaTriggers.PLAYER_HURT_ENTITY.trigger(serverPlayerSource, this, source, amount, amount, false);
            }

            return true;
        }
    }

    public void customDie(DamageSource damageSource, boolean playSound)
    {
        if (playSound) {this.playDeathSound(damageSource);}
        super.die(damageSource);
    }

    public boolean tryActuallyHurt(DamageSource damageSource, float amount) //returns true if the entity is actually hurt
    {
        boolean isOnHurtCooldown = (float)this.invulnerableTime > 10.0F;
        boolean shouldDamageBeReducedDueToHurtCooldown = isOnHurtCooldown && !damageSource.is(DamageTypeTags.BYPASSES_COOLDOWN);

        if (shouldDamageBeReducedDueToHurtCooldown)
        {
            //the difference in damage amount is dealt if the amount of new "hurt" is greater than last one
            float reducedAmount = amount - this.lastHurt;
            if (reducedAmount <= 0) {return false;}

            this.actuallyHurt(damageSource, reducedAmount);
            this.lastHurt = amount;
            return true;
        }
        else
        {
            this.lastHurt = amount;
            this.invulnerableTime = 20;
            this.actuallyHurt(damageSource, amount);
            this.hurtDuration = 10;
            this.hurtTime = this.hurtDuration;
            return true;
        }
    }

    public void setLastHurtBy(DamageSource damageSource)
    {
        Entity sourceEntity = damageSource.getEntity();
        if (sourceEntity != null)
        {
            if (sourceEntity instanceof LivingEntity sourceLivingEntity)
            {
                if (!damageSource.is(DamageTypeTags.NO_ANGER)) {this.setLastHurtByMob(sourceLivingEntity);}
            }

            if (sourceEntity instanceof Player sourcePlayerEntity)
            {
                this.lastHurtByPlayerTime = 100;
                this.lastHurtByPlayer = sourcePlayerEntity;
            }
            else if (sourceEntity instanceof TamableAnimal tamableEntity)
            {
                if (tamableEntity.isTame())
                {
                    this.lastHurtByPlayerTime = 100;
                    LivingEntity tamableEntityOwner = tamableEntity.getOwner();
                    if (tamableEntityOwner instanceof Player playerOwner) {this.lastHurtByPlayer = playerOwner;}
                    else {this.lastHurtByPlayer = null;}
                }
            }
        }
    }

    public boolean tryApplyingKnockback(DamageSource damageSource, float strength)
    {
        Entity sourceEntity = damageSource.getEntity();
        if (sourceEntity != null && !damageSource.is(DamageTypeTags.NO_KNOCKBACK))
        {
            double xKb = sourceEntity.getX() - this.getX();
            double zKb;
            for(zKb = sourceEntity.getZ() - this.getZ(); xKb * xKb + zKb * zKb < 1.0E-4D; zKb = (Math.random() - Math.random()) * 0.01D) {xKb = (Math.random() - Math.random()) * 0.01D;}

            this.knockback(strength, xKb, zKb);
            this.indicateDamage(xKb, zKb);
            return true;
        }
        return false;
    }

    protected void playDeathSound(DamageSource damageSource)
    {
        SoundEvent soundevent = this.getDeathSound();
        if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());}
    }
}
