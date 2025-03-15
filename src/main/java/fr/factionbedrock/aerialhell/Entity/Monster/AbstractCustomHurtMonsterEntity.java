package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.Util.CustomHurtInfo;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public abstract class AbstractCustomHurtMonsterEntity extends HostileEntity
{
    public AbstractCustomHurtMonsterEntity(EntityType<? extends AbstractCustomHurtMonsterEntity> type, World world) {super(type, world);}

    @Override public boolean damage(ServerWorld serverWorld, DamageSource damageSource, float amount)
    {
        return this.customHurt(serverWorld, damageSource, this.getDefaultCustomHurtInfo(amount));
    }

    protected CustomHurtInfo getDefaultCustomHurtInfo(float amount)
    {
        return new CustomHurtInfo(amount, this.defaultKbStrength(), this.shouldPlayHurtOrDeathSoundOnHurt(), this.shouldApplyKbOnHurt());
    }

    protected abstract float defaultKbStrength();
    protected abstract boolean shouldPlayHurtOrDeathSoundOnHurt();
    protected abstract boolean shouldApplyKbOnHurt();

    //copy of net.minecraft.entity.LivingEntity damage(DamageSource source, float amount) method, removing everything non-related to my monsters, and calling other methods, allowing customization in my inheriting classes
    public boolean customHurt(ServerWorld serverWorld, DamageSource source, CustomHurtInfo info)
    {
        float amount = info.amount();
        if (this.isInvulnerableTo(serverWorld, source) || this.getWorld().isClient || this.isDead()) {return false;}
        else if (source.isIn(DamageTypeTags.IS_FIRE) && this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {return false;}
        else
        {
            this.despawnCounter = 0;

            if (source.isIn(DamageTypeTags.IS_FREEZING) && this.getType().isIn(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {amount *= 5.0F;}
            this.limbAnimator.setSpeed(1.5F);

            boolean wasOnHurtCooldown = (float)this.timeUntilRegen > 10.0F && !source.isIn(DamageTypeTags.BYPASSES_COOLDOWN);
            boolean actuallyGotHurt = tryActuallyHurt(serverWorld, source, amount);

            if (!actuallyGotHurt) {return false;}
            //we know this got hurt
            setLastHurtBy(source);

            if (!wasOnHurtCooldown)
            {
                this.getWorld().sendEntityDamage(this, source);
                if (!source.isIn(DamageTypeTags.NO_IMPACT)) {this.scheduleVelocityUpdate();}

                if (info.applyKb()) {tryApplyingKnockback(source, info.kbStrength());}
            }

            boolean died = false;
            if (this.isDead()) {this.customOnDeath(source, info.playSound()); died = true;}

            if (!wasOnHurtCooldown && info.playSound())
            {
                if (died) {/*this.playDeathSound(source);*/} //death sound is now played in customDie(DamageSource, boolean) method
                else {this.playHurtSound(source);}
            }

            this.lastDamageSource = source;
            this.lastDamageTime = this.getWorld().getTime();

            if (source.getAttacker() instanceof ServerPlayerEntity serverPlayerSource)
            {
                Criteria.PLAYER_HURT_ENTITY.trigger(serverPlayerSource, this, source, amount, amount, false);
            }
            return true;
        }
    }

    public void customOnDeath(DamageSource damageSource, boolean playSound)
    {
        if (playSound) {this.playDeathSound(damageSource);}
        super.onDeath(damageSource);
    }

    public boolean tryActuallyHurt(ServerWorld serverWorld, DamageSource damageSource, float amount) //returns true if the entity is actually hurt
    {
        boolean isOnHurtCooldown = (float)this.timeUntilRegen > 10.0F;
        boolean shouldDamageBeReducedDueToHurtCooldown = isOnHurtCooldown && !damageSource.isIn(DamageTypeTags.BYPASSES_COOLDOWN);

        if (shouldDamageBeReducedDueToHurtCooldown)
        {
            //the difference in damage amount is dealt if the amount of new "hurt" is greater than last one
            float reducedAmount = amount - this.lastDamageTaken;
            if (reducedAmount <= 0) {return false;}

            this.applyDamage(serverWorld, damageSource, reducedAmount);
            this.lastDamageTaken = amount;
            return true;
        }
        else
        {
            this.lastDamageTaken = amount;
            this.timeUntilRegen = 20;
            this.applyDamage(serverWorld, damageSource, amount);
            this.maxHurtTime = 10;
            this.hurtTime = this.maxHurtTime;
            return true;
        }
    }

    public void setLastHurtBy(DamageSource damageSource)
    {
        Entity sourceEntity = damageSource.getAttacker();
        if (sourceEntity != null)
        {
            if (sourceEntity instanceof LivingEntity sourceLivingEntity)
            {
                if (!damageSource.isIn(DamageTypeTags.NO_ANGER)) {this.setAttacker(sourceLivingEntity);}
            }

            if (sourceEntity instanceof PlayerEntity sourcePlayerEntity)
            {
                this.playerHitTimer = 100;
                this.attackingPlayer = sourcePlayerEntity;
            }
            else if (sourceEntity instanceof WolfEntity worfEntity)
            {
                if (worfEntity.isTamed())
                {
                    this.playerHitTimer = 100;
                    LivingEntity tamableEntityOwner = worfEntity.getOwner();
                    if (tamableEntityOwner instanceof PlayerEntity playerOwner) {this.attackingPlayer = playerOwner;}
                    else {this.attackingPlayer = null;}
                }
            }
        }
    }

    public boolean tryApplyingKnockback(DamageSource damageSource, float strength)
    {
        Entity sourceEntity = damageSource.getAttacker();
        if (sourceEntity != null && !damageSource.isIn(DamageTypeTags.NO_KNOCKBACK))
        {
            double xKb = sourceEntity.getX() - this.getX();
            double zKb;
            for(zKb = sourceEntity.getZ() - this.getZ(); xKb * xKb + zKb * zKb < 1.0E-4D; zKb = (Math.random() - Math.random()) * 0.01D) {xKb = (Math.random() - Math.random()) * 0.01D;}

            this.takeKnockback(strength, xKb, zKb);
            this.tiltScreen(xKb, zKb);
            return true;
        }
        return false;
    }

    protected void playDeathSound(DamageSource damageSource)
    {
        SoundEvent soundevent = this.getDeathSound();
        if (soundevent != null) {this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());}
    }
}
