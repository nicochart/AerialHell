package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ElectroSpiritEntity extends AbstractElementSpiritEntity
{
	public ElectroSpiritEntity(EntityType<? extends ElectroSpiritEntity> type, World world) {super(type, world);}

    public ElectroSpiritEntity(World world) {this(AerialHellEntities.ELECTRO_SPIRIT, world);}
    
    @Override public void attackSuicide()
    {
    	if (this.getEntityWorld() instanceof ServerWorld)
    	{
    		LightningEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(this.getEntityWorld(), SpawnReason.TRIGGERED);
			lightningBolt.setPos(this.getX(), this.getY(), this.getZ());
			this.getEntityWorld().spawnEntity(lightningBolt);
    	}
    	super.attackSuicide();
    }
    
    @Override public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 70, 2, true, false));
    }
    
    public SimpleParticleType getParticleToSpawn()
    {
    	return ParticleTypes.WITCH;
    }
    
    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_HURT;}
    @Nullable @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_DEATH;}
    @Nullable @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_ELECTRO_SPIRIT_AMBIENT;}
}
