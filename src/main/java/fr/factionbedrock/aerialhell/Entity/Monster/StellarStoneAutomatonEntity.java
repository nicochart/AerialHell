package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class StellarStoneAutomatonEntity extends AutomatonEntity
{
    public StellarStoneAutomatonEntity(EntityType<? extends Monster> type, Level world)
    {
        super(type, world);
    }
    
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D);
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_STELLAR_STONE_AUTOMATON_ACTIVATION.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_STELLAR_STONE_AUTOMATON_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_STELLAR_STONE_AUTOMATON_DEATH.get();}
}