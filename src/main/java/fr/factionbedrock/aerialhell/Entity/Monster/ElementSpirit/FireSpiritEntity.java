package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Entity.AbstractElementSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class FireSpiritEntity extends AbstractElementSpiritEntity
{
	public FireSpiritEntity(EntityType<? extends FireSpiritEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public FireSpiritEntity(World worldIn)
    {
        this(AerialHellEntities.FIRE_SPIRIT.get(), worldIn);
    }
    
    public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).setFire(6);
    }
    
    public BasicParticleType getParticleToSpawn()
    {
    	return ParticleTypes.FLAME;
    }
    
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
        return SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE;
    }
}
