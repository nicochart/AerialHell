package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

@FunctionalInterface public interface MobEffectInstanceProvider {MobEffectInstance create(LivingEntity livingEntity);}