package fr.factionbedrock.aerialhell.Effect;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;

@FunctionalInterface public interface RandomMobEffectInstance {MobEffectInstance get(RandomSource rand);}
