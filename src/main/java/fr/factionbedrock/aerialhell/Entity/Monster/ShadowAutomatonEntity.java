package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ShadowAutomatonEntity extends AutomatonEntity
{
    public ShadowAutomatonEntity(EntityType<? extends MonsterEntity> type, World world) {super(type, world);}
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.ARMOR, 3.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D);
    }

    @Override
    public boolean attackEntityAsMob(Entity attackedEntity)
    {
        if (super.attackEntityAsMob(attackedEntity))
        {
            if (attackedEntity instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune(((LivingEntity) attackedEntity)))
            {
                ((LivingEntity) attackedEntity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 0));
            }
            return true;
        }
        else {return false;}
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_DEATH.get();}
}