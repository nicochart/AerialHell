package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class StellarStoneAutomatonEntity extends AutomatonEntity
{
    public StellarStoneAutomatonEntity(EntityType<? extends MonsterEntity> type, World world)
    {
        super(type, world);
    }
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 40.0D)
                .createMutableAttribute(Attributes.ARMOR, 3.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D);
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_STELLAR_STONE_AUTOMATON_ACTIVATION.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_STELLAR_STONE_AUTOMATON_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_STELLAR_STONE_AUTOMATON_DEATH.get();}
}