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
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class IceSpiritEntity extends AbstractElementSpiritEntity
{
	public IceSpiritEntity(EntityType<? extends IceSpiritEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public IceSpiritEntity(World worldIn)
    {
        this(AerialHellEntities.ICE_SPIRIT.get(), worldIn);
    }
    
    public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 150, 2, true, false)));
    }
    
    public BasicParticleType getParticleToSpawn()
    {
    	return ParticleTypes.CLOUD;
    }
    
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound()
    {
        return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_AMBIENT.get();
    }
}
