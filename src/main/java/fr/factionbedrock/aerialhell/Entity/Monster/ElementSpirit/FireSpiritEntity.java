package fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FireSpiritEntity extends AbstractElementSpiritEntity
{
	public FireSpiritEntity(EntityType<? extends FireSpiritEntity> type, World world) {super(type, world);}

    public FireSpiritEntity(World world) {this(AerialHellEntities.FIRE_SPIRIT, world);}

    @Override public void applyEffect(Entity entityIn) {((LivingEntity) entityIn).setOnFireFor(6);}

    @Override public SimpleParticleType getParticleToSpawn() {return ParticleTypes.FLAME;}
    
    @Override public boolean isFireImmune() {return true;}
	@Override public boolean doesRenderOnFire() {return false;}

    @Nullable @Override protected SoundEvent getHurtSound(DamageSource damageSourceIn) {return AerialHellSoundEvents.ENTITY_FIRE_SPIRIT_HURT;}
    @Nullable @Override protected SoundEvent getDeathSound() {return SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE;}
    @Nullable @Override  protected SoundEvent getAmbientSound() {return SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE;}
}
