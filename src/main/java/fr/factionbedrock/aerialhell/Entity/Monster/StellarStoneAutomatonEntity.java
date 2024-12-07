package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class StellarStoneAutomatonEntity extends AutomatonEntity
{
    public StellarStoneAutomatonEntity(EntityType<? extends HostileEntity> type, World world)
    {
        super(type, world);
    }
    
    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 3.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D);
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_STELLAR_STONE_AUTOMATON_ACTIVATION;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_STELLAR_STONE_AUTOMATON_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_STELLAR_STONE_AUTOMATON_DEATH;}
}