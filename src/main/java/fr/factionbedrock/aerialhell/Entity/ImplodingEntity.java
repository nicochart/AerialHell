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
    }

    default void implodingReadAdditionalSaveData(ValueInput valueInput) //call in readAdditionalSaveData(valueInput)
    {
        this.setImploding(valueInput.getBooleanOr("is_imploding", false));
    }
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */
    /* ----------------------------------------------- */

    /* -------------------------------------------------------------- */
    /* -------- Other utility methods to eventually override -------- */
    /* -------------------------------------------------------------- */
    default boolean doesImmobilizeWithSlownessEffectOnImplodingStart() {return true;}

    default void onImplodingStart()
    {
        if (!this.doesImmobilizeWithSlownessEffectOnImplodingStart() && !this.getLevel().isClientSide())
        {
            this.getSelf().addEffect(new MobEffectInstance(MobEffects.SLOWNESS, this.getImplodingCastDuration(), 10, true, false));
        }
    }
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */
    /* -------------------------------------------------------------- */

    /* ----------------------------------------------------------- */
    /* -------- Other utility methods (for the interface) -------- */
    /* ----------------------------------------------------------- */
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
