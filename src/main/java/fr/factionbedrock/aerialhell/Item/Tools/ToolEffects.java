package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class ToolEffects
{
    private static final ToolEffectInfo HALF_VOLUCITE_POWER_EFFECT = ToolEffectInfo.Use.fixed(new MobEffectInstance(MobEffects.SLOW_FALLING, 80, 0), ToolEffects::canGetHalfVolucitePower, 250, new PlaySoundHelper(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.6F), ParticleTypes.CLOUD);
    private static final ToolEffectInfo FULL_VOLUCITE_POWER_EFFECT = ToolEffectInfo.Use.fixed(List.of(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate(), 100, 1), new MobEffectInstance(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 0))), ToolEffects::canGetFullVolucitePower, 250, new PlaySoundHelper(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.5F), ParticleTypes.CLOUD);

    public static boolean canGetHalfVolucitePower(LivingEntity entity) {return EntityHelper.countVoluciteStuff(entity) < 4 && EntityHelper.countHeavyStuff(entity) == 0;}
    public static boolean canGetFullVolucitePower(LivingEntity entity) {return EntityHelper.countVoluciteStuff(entity) == 4;}

    public static final List<ToolEffectInfo> VOLUCITE_POWER = List.of(HALF_VOLUCITE_POWER_EFFECT, FULL_VOLUCITE_POWER_EFFECT);
}
