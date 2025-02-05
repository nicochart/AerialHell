package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public class ElectroSpiritEntity extends AbstractElementSpiritEntity
{
	public ElectroSpiritEntity(EntityType<? extends ElectroSpiritEntity> type, Level worldIn) {super(type, worldIn);}

    public ElectroSpiritEntity(Level worldIn) {this(AerialHellEntities.ELECTRO_SPIRIT.get(), worldIn);}
    
    @Override public void attackSuicide()
    {
    	if (this.level() instanceof ServerLevel)
    	{
    		LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level(), EntitySpawnReason.TRIGGERED);
			lightningBolt.setPos(this.getX(), this.getY(), this.getZ());
			this.level().addFreshEntity(lightningBolt);
    	}
    	super.attackSuicide();
    }
    
    @Override public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.GLOWING, 70, 2, true, false));
    }

    @Override public SimpleParticleType getParticleToSpawn() {return ParticleTypes.WITCH;}
    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_HURT.get();}
    @Nullable @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_DEATH.get();}
    @Nullable @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_AMBIENT.get();}
}
