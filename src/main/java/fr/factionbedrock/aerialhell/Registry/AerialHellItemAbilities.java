package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Item.Ability.*;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ActionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.ConditionModule;
import fr.factionbedrock.aerialhell.Item.Ability.Module.SideEffectModule;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class AerialHellItemAbilities
{
    private static final ActionModule VOLUCITE_POWER_EFFECT = ActionModule.onUse((entity, stack, equipmentSlot) ->
    {
        if (canGetFullVolucitePower(entity))
        {
            entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 0));
            entity.addEffect(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate(), 100, 1));
        }
        else
        {
            entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 80, 0));
        }
    });
    private static final ConditionModule VOLUCITE_POWER_CONDITION = ConditionModule.onUse(AerialHellItemAbilities::canGetVolucitePower);
    private static final ActionModule.Sound VOLUCITE_POWER_SOUND = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, canGetFullVolucitePower(entity) ? 1.5F : 1.6F));
    private static final ActionModule.Particle VOLUCITE_POWER_PARTICLE = ActionModule.Particle.onUse(ParticleTypes.CLOUD);
    private static final SideEffectModule.Cooldown VOLUCITE_POWER_COOLDOWN = SideEffectModule.Cooldown.onUse(((entity) -> 250));
    private static final SideEffectModule.DamageItem VOLUCITE_POWER_DAMAGE = SideEffectModule.DamageItem.onUse();

    public static final ItemAbility VOLUCITE_POWER = new ItemAbility(VOLUCITE_POWER_EFFECT, VOLUCITE_POWER_CONDITION, VOLUCITE_POWER_SOUND, VOLUCITE_POWER_PARTICLE, VOLUCITE_POWER_COOLDOWN, VOLUCITE_POWER_DAMAGE);

    public static boolean canGetVolucitePower(LivingEntity entity) {return EntityHelper.countHeavyStuff(entity) == 0;}
    public static boolean canGetFullVolucitePower(LivingEntity entity) {return EntityHelper.countVoluciteStuff(entity) == 4;}
}
