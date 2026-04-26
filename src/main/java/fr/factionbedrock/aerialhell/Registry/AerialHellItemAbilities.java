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

public class AerialHellItemAbilities
{
    private static final ActionModule.MobEffect HALF_VOLUCITE_POWER_ON_USE = ActionModule.MobEffect.onUse(new MobEffectInstance(MobEffects.SLOW_FALLING, 80, 0));
    private static final ActionModule.MobEffect FULL_VOLUCITE_POWER_ON_USE = ActionModule.MobEffect.onUse(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 0), new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate(), 100, 1));

    private static final ConditionModule HAS_NO_HEAVY_STUFF_ON_USE = ConditionModule.onUse(EntityHelper::hasNoHeavyStuff);
    private static final ConditionModule HAS_FULL_VOLUCITE_STUFF_ON_USE = ConditionModule.onUse(EntityHelper::hasFullVoluciteStuff);
    private static final SideEffectModule.DamageItem DAMAGE_ON_USE = SideEffectModule.DamageItem.onUse();
    private static final SideEffectModule.Cooldown COOLDOWN_250_ON_USE = SideEffectModule.Cooldown.onUse(((entity) -> 250));
    private static final ActionModule.Sound ILLUSIONER_CAST_SPELL_SOUND_ON_USE = ActionModule.Sound.onUse((entity) -> new PlaySoundHelper(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.5F));
    private static final ActionModule.Particle CLOUD_PARTICLES_ON_USE = ActionModule.Particle.onUse(ParticleTypes.CLOUD);

    public static final ItemAbility VOLUCITE_POWER_COMMON = new ItemAbility(HAS_NO_HEAVY_STUFF_ON_USE, ILLUSIONER_CAST_SPELL_SOUND_ON_USE, CLOUD_PARTICLES_ON_USE, COOLDOWN_250_ON_USE, DAMAGE_ON_USE);
    public static final ItemAbility HALF_VOLUCITE_POWER = VOLUCITE_POWER_COMMON.copy().addModule(HALF_VOLUCITE_POWER_ON_USE);
    public static final ItemAbility FULL_VOLUCITE_POWER = VOLUCITE_POWER_COMMON.copy().addModules(HAS_FULL_VOLUCITE_STUFF_ON_USE, FULL_VOLUCITE_POWER_ON_USE).setFallback(HALF_VOLUCITE_POWER);
}
