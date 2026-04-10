package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ElectroSpiritEntity extends AbstractElementSpiritEntity
{
	public ElectroSpiritEntity(EntityType<? extends ElectroSpiritEntity> type, Level world) {super(type, world);}

    public ElectroSpiritEntity(Level world) {this(AerialHellEntities.ELECTRO_SPIRIT, world);}
    
    @Override public void attackSuicide()
    {
    	if (this.level() instanceof ServerLevel)
    	{
    		LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level(), EntitySpawnReason.TRIGGERED);
			lightningBolt.setPosRaw(this.getX(), this.getY(), this.getZ());
			this.level().addFreshEntity(lightningBolt);
    	}
    	super.attackSuicide();
    }
    
    @Override public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.GLOWING, 70, 2, true, false));
    }
    
    public SimpleParticleType getParticleToSpawn()
    {
    	return ParticleTypes.WITCH;
    }
    
    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_HURT;}
    @Nullable @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_DEATH;}
    @Nullable @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_AMBIENT;}
}
