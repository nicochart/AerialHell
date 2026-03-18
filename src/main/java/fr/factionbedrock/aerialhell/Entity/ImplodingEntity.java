package fr.factionbedrock.aerialhell.Entity;

import fr.factionbedrock.aerialhell.Entity.Util.ImplodingEntityInfo;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

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
    default void implodingWriteCustomData(WriteView view) //call in writeCustomData(valueOutput)
    {
        view.putBoolean("is_imploding", this.isImploding());
        view.putInt("imploding_cooldown_ticks", this.getImplodingCooldownTicks());
        view.putInt("imploding_cast_ticks", this.getImplodingCastTicks());
    }

    default void implodingReadCustomData(ReadView view) //call in readCustomData(valueInput)
    {
        this.setImploding(view.getBoolean("is_imploding", false));
        this.setImplodingCooldownTicks(view.getInt("imploding_cooldown_ticks", 0));
        this.setImplodingCastTicks(view.getInt("imploding_cast_ticks", 0));
    }

    default void implodingTick() //OPTIONAL - call in tick() if you want onImplodingCastTick() to be client-sided too
    {
        if (this.getLevel().isClient() && this.isImploding()) {this.onImplodingCastTick();}
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* -------------------------------------------------------------- */
    /* -------- Other utility methods to eventually override -------- */
    /* -------------------------------------------------------------- */
    default ImplodingTargetPolicy getImplodingTargetPolicy() {return new ImplodingTargetPolicy(true, true, 40);}
    //Tip: If ImplodingCooldownResetsOnTargetLoss is true, set a cooldownResetDelayThreshold (ticks tolerated without a target) of 20 or more to prevent the ImplodingEntity from resetting its cooldown if a player disconnects and reconnects.
    //cooldownResetDelayThreshold is unused if implodingCooldownResetsOnTargetLoss is false

    default boolean doesImmobilizeWithSlownessEffectOnImplodingStart() {return true;}

    default void onImplodingStart() //server side
    {
        if (!this.doesImmobilizeWithSlownessEffectOnImplodingStart() && !this.getLevel().isClient())
        {
            this.getSelf().addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, this.getImplodingCastDuration(), 10, true, false));
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
        World world = this.getLevel();
        if (!world.isClient())
        {
            world.createExplosion(this.getSelf(), this.getX(), this.getY(), this.getZ(), (float)5, World.ExplosionSourceType.MOB);
        }
        this.spawnImplosionParticle();
    }

    default void spawnImplosionParticle()
    {
        World world = this.getLevel();
        if (world.isClient())
        {
            for(int i = 0; i < 30; ++i)
            {
                Random rand = this.getRandom(); double d0 = rand.nextGaussian() * 0.02D; double d1 = rand.nextGaussian() * 0.02D; double d2 = rand.nextGaussian() * 0.02D;
                world.addParticleClient(ParticleTypes.LARGE_SMOKE, this.getParticleX(1.0D) - d0 * 10.0D, this.getRandomBodyY() - d1 * 10.0D, this.getParticleZ(1.0D) - d2 * 10.0D, 2 * d0, d1, 2 * d2);
            }
        }
        else {world.sendEntityStatus(this.getSelf(), (byte)20);} //spawnAnim, poof particles
    }

    default void setImploding(boolean isImploding) {this.getEntityData().set(this.getImplodingDataAccessor(), isImploding);}
    default boolean isImploding() {return this.getEntityData().get(this.getImplodingDataAccessor());}

    default TrackedData<Boolean> getImplodingDataAccessor() {return this.getImplodingEntityInfo().getImplodingDataAccessor();}

    default boolean needsTargetToStartImploding() {return this.getImplodingTargetPolicy().needsTargetToStartImploding;}
    default boolean implodingCooldownResetsOnTargetLoss() {return this.getImplodingTargetPolicy().implodingCooldownResetsOnTargetLoss;}
    default int implodingCooldownResetThreshold() {return this.getImplodingTargetPolicy().cooldownResetDelayThreshold;}

    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */
    /* ----------------------------------------------------------- */

    record ImplodingTargetPolicy(boolean needsTargetToStartImploding, boolean implodingCooldownResetsOnTargetLoss, int cooldownResetDelayThreshold) {}
}
