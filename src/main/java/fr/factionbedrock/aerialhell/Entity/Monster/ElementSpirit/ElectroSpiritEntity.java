package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Entity.AbstractElementSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ElectroSpiritEntity extends AbstractElementSpiritEntity
{
	public ElectroSpiritEntity(EntityType<? extends ElectroSpiritEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public ElectroSpiritEntity(World worldIn)
    {
        this(AerialHellEntities.ELECTRO_SPIRIT.get(), worldIn);
    }
    
    @Override
    public void attackSuicide(Entity entityIn)
    {
    	if (this.world instanceof ServerWorld)
    	{
    		LightningBoltEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(this.world);
			lightningBolt.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
			this.world.addEntity(lightningBolt);
    	}
    	super.attackSuicide(entityIn);
    }
    
    public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).addPotionEffect(new EffectInstance(new EffectInstance(Effects.GLOWING, 70, 2, true, false)));
    }
    
    public BasicParticleType getParticleToSpawn()
    {
    	return ParticleTypes.WITCH;
    }
    
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound()
    {
        return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_AMBIENT.get();
    }
}
