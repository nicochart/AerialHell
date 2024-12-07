package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class IceSpiritEntity extends AbstractElementSpiritEntity
{
	public IceSpiritEntity(EntityType<? extends IceSpiritEntity> type, World world) {super(type, world);}

    public IceSpiritEntity(World world) {this(AerialHellEntities.ICE_SPIRIT, world);}

    @Override public void applyEffect(Entity entityIn)
    {
    	((LivingEntity) entityIn).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 150, 2, true, false));
    }

    @Override public SimpleParticleType getParticleToSpawn() {return ParticleTypes.CLOUD;}

    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_HURT;}
    @Nullable @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_DEATH;}
    @Nullable @Override  protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_ICE_SPIRIT_AMBIENT;}
}
