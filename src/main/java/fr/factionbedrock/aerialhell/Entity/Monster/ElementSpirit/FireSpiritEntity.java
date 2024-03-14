package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public class FireSpiritEntity extends AbstractElementSpiritEntity
{
	public FireSpiritEntity(EntityType<? extends FireSpiritEntity> type, Level worldIn)
    {
        super(type, worldIn);
    }

    public FireSpiritEntity(Level worldIn)
    {
        this(AerialHellEntities.FIRE_SPIRIT.get(), worldIn);
    }
    
    public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).setSecondsOnFire(6);
    }
    
    public SimpleParticleType getParticleToSpawn()
    {
    	return ParticleTypes.FLAME;
    }
    
    @Override public boolean fireImmune() {return true;}
	@Override public boolean displayFireAnimation() {return false;}
	
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return AerialHellSoundEvents.ENTITY_FIRE_SPIRIT_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.GENERIC_EXTINGUISH_FIRE;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.FURNACE_FIRE_CRACKLE;
    }
}
