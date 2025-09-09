package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class IceSpiritEntity extends AbstractElementSpiritEntity
{
	public IceSpiritEntity(EntityType<? extends IceSpiritEntity> type, Level worldIn) {super(type, worldIn);}

    public IceSpiritEntity(Level worldIn) {this(AerialHellEntities.ICE_SPIRIT.get(), worldIn);}

    @Override public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 150, 2, true, false));
    }

    @Override public SimpleParticleType getParticleToSpawn()
    {
    	return ParticleTypes.CLOUD;
    }

    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_HURT.get();}
    @Nullable @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_DEATH.get();}
    @Nullable @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_AMBIENT.get();}
}
