package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ToolAbilities
{
    private static final ToolAbilityModule.Action VOLUCITE_POWER_EFFECT = ToolAbilityModule.Action.onUse((entity, stack, equipmentSlot) ->
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
    private static final ToolAbilityModule.Condition VOLUCITE_POWER_CONDITION = ToolAbilityModule.Condition.onUse(ToolAbilities::canGetVolucitePower);
    private static final ToolAbilityModule.Action.Sound VOLUCITE_POWER_SOUND = ToolAbilityModule.Action.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, canGetFullVolucitePower(entity) ? 1.5F : 1.6F));
    private static final ToolAbilityModule.Action.Particle VOLUCITE_POWER_PARTICLE = ToolAbilityModule.Action.Particle.onUse(ParticleTypes.CLOUD);
    private static final ToolAbilityModule.Action.Cooldown VOLUCITE_POWER_COOLDOWN = ToolAbilityModule.Action.Cooldown.onUse(((entity) -> 250));

    public static final ToolAbility VOLUCITE_POWER = new ToolAbility(VOLUCITE_POWER_EFFECT, VOLUCITE_POWER_CONDITION, VOLUCITE_POWER_SOUND, VOLUCITE_POWER_PARTICLE, VOLUCITE_POWER_COOLDOWN);

    public static boolean canGetVolucitePower(LivingEntity entity) {return EntityHelper.countHeavyStuff(entity) == 0;}
    public static boolean canGetFullVolucitePower(LivingEntity entity) {return EntityHelper.countVoluciteStuff(entity) == 4;}
}
