package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import org.jetbrains.annotations.Nullable;

public class IceSpiritEntity extends AbstractElementSpiritEntity
{
	public IceSpiritEntity(EntityType<? extends IceSpiritEntity> type, Level world) {super(type, world);}

    public IceSpiritEntity(Level world) {this(AerialHellEntities.ICE_SPIRIT, world);}

    @Override public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 150, 2, true, false));
    }

    @Override public SimpleParticleType getParticleToSpawn() {return ParticleTypes.CLOUD;}

    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_HURT;}
    @Nullable @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_DEATH;}
    @Nullable @Override  protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_AMBIENT;}
}
