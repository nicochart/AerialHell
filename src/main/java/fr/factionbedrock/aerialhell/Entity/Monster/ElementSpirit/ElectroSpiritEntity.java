package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteGlyphParticles;
import fr.factionbedrock.aerialhell.Entity.Util.VoluciteLightningHelper;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class ElectroSpiritEntity extends AbstractElementSpiritEntity
{
	private static final int LIGHTNING_COUNT = 2;
	private static final float LIGHTNING_DAMAGE = 5.0F; //same as vanilla lightning bolt
	private static final float LIGHTNING_HIT_RADIUS = 1.5F; //entities this close to the lightning impact are hurt
	private static final float GLYPHS_MIN_DISTANCE = 1.0F, GLYPHS_MAX_DISTANCE = 3.0F; //glyph particles burst from the spirit, when it disappears
	private static final int GLYPHS_COUNT = 30;

	public ElectroSpiritEntity(EntityType<? extends ElectroSpiritEntity> type, Level worldIn) {super(type, worldIn);}

    public ElectroSpiritEntity(Level worldIn) {this(AerialHellEntities.ELECTRO_SPIRIT.get(), worldIn);}
    
    //volucite lightning from the spirit (last position) to its target
    @Override public void attackSuicide()
    {
    	if (this.level() instanceof ServerLevel level && this.getTarget() != null)
    	{
			VoluciteLightningHelper.LightningInfo lightningInfo = VoluciteLightningHelper.voluciteMobLightningInfo(this, LIGHTNING_DAMAGE, LIGHTNING_HIT_RADIUS);
			Vec3 startPos = this.getBoundingBox().getCenter();
			for (int i = 0; i < LIGHTNING_COUNT; i++) {VoluciteLightningHelper.strikeLightningAtTarget(level, startPos, this.getTarget(), lightningInfo);}
    	}
    	super.attackSuicide();
    }
    
    @Override public void applyEffect(Entity entityIn)
    {
        //no longer inflicts glowing
    	//((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.GLOWING, 70, 2, true, false));
    }

    //glyph particles need a destination (speed parameters = offset to destination) : burst from the spirit center instead of default particles with tiny speeds
    @Override public void spawnParticle() {VoluciteGlyphParticles.spawnOutward(this.level(), this.getBoundingBox().getCenter(), GLYPHS_MIN_DISTANCE, GLYPHS_MAX_DISTANCE, GLYPHS_COUNT);}

    @Override public boolean canAttack(LivingEntity target) {return !EntityHelper.isVoluciteEntity(target) && super.canAttack(target);} //also used by Mob.setTarget and target goals

    @Override public SimpleParticleType getParticleToSpawn() {return AerialHellParticleTypes.VOLUCITE_GLYPH.get();}
    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_HURT.get();}
    @Nullable @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_DEATH.get();}
    @Nullable @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_AMBIENT.get();}
}
