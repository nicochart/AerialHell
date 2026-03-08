package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.Util.ImplodingEntityInfo;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public interface ImplodingEntity extends BaseMobEntityInterface
{
    /* ---------------------------------------------------- */
    /* ---------- Methods needing implementation ---------- */
    /* ---------------------------------------------------- */
    //You will also need to implement getSelf() from BaseEntityInterface
    ImplodingEntityInfo getImplodingEntityInfo();
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */
    /* ---------------------------------------------------- */

    /* ----------------------------------------------- */
    /* -------- Delegate methods needing call -------- */
    /* ----------------------------------------------- */
    default void implodingAddAdditionalSaveData(ValueOutput valueOutput) //call in addAdditionalSaveData(valueOutput)
    {
        valueOutput.putBoolean("is_imploding", this.isImploding());
        valueOutput.putInt("imploding_cooldown_ticks", this.getImplodingCooldownTicks());
        valueOutput.putInt("imploding_cast_ticks", this.getImplodingCooldownTicks());
    }

    default void implodingReadAdditionalSaveData(ValueInput valueInput) //call in readAdditionalSaveData(valueInput)
    {
        this.setImploding(valueInput.getBooleanOr("is_imploding", false));
        this.setImplodingCooldownTicks(valueInput.getIntOr("imploding_cooldown_ticks", 0));
        this.setImplodingCastTicks(valueInput.getIntOr("imploding_cast_ticks", 0));
    }

    default void implodingTick() //OPTIONAL - call in tick() if you want onImplodingCastTick() to be client-sided too
    {
        if (this.getLevel().isClientSide() && this.isImploding()) {this.onImplodingCastTick();}
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* -------------------------------------------------------------- */
    /* -------- Other utility methods to eventually override -------- */
    /* -------------------------------------------------------------- */
    default boolean doesImmobilizeWithSlownessEffectOnImplodingStart() {return true;}

    default void onImplodingStart() //server side
    {
        if (!this.doesImmobilizeWithSlownessEffectOnImplodingStart() && !this.getLevel().isClientSide())
        {
            this.getSelf().addEffect(new MobEffectInstance(MobEffects.SLOWNESS, this.getImplodingCastDuration(), 10, true, false));
        }
    }

    default void onImplodingCastTick() {} //called each tick during imploding cast (see below for side)
    //server side call by goal (by default)
    //client side call optional - by calling implodingTick() in entity tick() (see implodingTick() delegate method)
    //so if you want to do things client side on tick (for example, display particles), see and use implodingTick() delegate method
    //but if your usage is only server side, you won't need to use implodingTick() delegate method
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
    default void incrementImplodingCastTicks() {this.setImplodingCastTicks(this.getImplodingCastTicks() + 1);}
    default void setImplodingCastTicks(int value) {this.getImplodingEntityInfo().setCastTicks(value);}
    default int getImplodingCastTicks() {return this.getImplodingEntityInfo().getCastTicks();}
    default void incrementImplodingCooldownTicks() {this.setImplodingCooldownTicks(this.getImplodingCooldownTicks() + 1);}
    default void setImplodingCooldownTicks(int value) {this.getImplodingEntityInfo().setCooldownTicks(value);}
    default int getImplodingCooldownTicks() {return this.getImplodingEntityInfo().getCooldownTicks();}

    default int getImplodingCastDuration() {return this.getImplodingEntityInfo().getCastDuration();}
    default int getImplodingCooldownDuration() {return this.getImplodingEntityInfo().getCooldownDuration();}
    default int getImplodingSoundOffset() {return this.getImplodingEntityInfo().getSoundOffset();}
    default void playImplodingSound() {this.getImplodingEntityInfo().playImplodingSound(this.getSelf());}

    default void implode()
    {
        Level level = this.getLevel();
        if (!level.isClientSide())
        {
            level.explode(this.getSelf(), this.getX(), this.getY(), this.getZ(), (float)5, Level.ExplosionInteraction.MOB);
        }
        this.spawnImplosionParticle();
    }

    default void spawnImplosionParticle()
    {
        Level level = this.getLevel();
        if (level.isClientSide())
        {
            for(int i = 0; i < 30; ++i)
            {
                RandomSource rand = this.getRandom(); double d0 = rand.nextGaussian() * 0.02D; double d1 = rand.nextGaussian() * 0.02D; double d2 = rand.nextGaussian() * 0.02D;
                level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(1.0D) - d0 * 10.0D, this.getRandomY() - d1 * 10.0D, this.getRandomZ(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
            }
        }
        else {level.broadcastEntityEvent(this.getSelf(), (byte)20);} //spawnAnim, poof particles
    }

    default void setImploding(boolean isImploding) {this.getEntityData().set(this.getImplodingDataAccessor(), isImploding);}
    default boolean isImploding() {return this.getEntityData().get(this.getImplodingDataAccessor());}

    default EntityDataAccessor<Boolean> getImplodingDataAccessor() {return this.getImplodingEntityInfo().getImplodingDataAccessor();}

    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
}
