package fr.factionbedrock.aerialhell.Registry;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class AerialHellFoods
{
    public static class Properties
    {
        public static final FoodProperties AERIAL_BERRY = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodProperties ROASTED_AERIAL_BERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).build();
        public static final FoodProperties VIBRANT_AERIAL_BERRY = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodProperties FROZEN_AERIAL_BERRY = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodProperties STELLAR_BREAD = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();
        public static final FoodProperties FROZEN_MUTTON = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodProperties VIBRANT_CHICKEN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodProperties FROZEN_CHICKEN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodProperties RUBY_AERIAL_BERRY = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodProperties VOLUCITE_AERIAL_BERRY = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodProperties GLOWING_STICK_FRUIT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).build();
        public static final FoodProperties VIBRANT_GLOWING_STICK_FRUIT = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodProperties FROZEN_GLOWING_STICK_FRUIT = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).build();
        public static final FoodProperties CORTINARIUS_VIOLACEUS_PIECE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodProperties GANODERMA_APPLANATUM_PIECE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodProperties DARK_SHADOW_FRUIT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodProperties PURPLE_SHADOW_FRUIT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodProperties SHADOW_FRUIT_STEW = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodProperties SOLID_ETHER_SOUP = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodProperties VIBRANT_SOLID_ETHER_SOUP = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).build();
        public static final FoodProperties FROZEN_SOLID_ETHER_SOUP = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).build();
        public static final FoodProperties SHADOW_SPIDER_EYE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodProperties PHANTOM_MEAT = new FoodProperties.Builder().nutrition(5).saturationModifier(0.8F).build();
        public static final FoodProperties VIBRANT_PHANTOM_MEAT = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build();
        public static final FoodProperties FROZEN_PHANTOM_MEAT = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).build();
        public static final FoodProperties COOKED_PHANTOM_MEAT = new FoodProperties.Builder().nutrition(10).saturationModifier(0.9F).build();
        public static final FoodProperties VIBRANT_TURTLE_MEAT = new FoodProperties.Builder().nutrition(7).saturationModifier(0.7F).build();
        public static final FoodProperties FROZEN_TURTLE_MEAT = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).build();
        public static final FoodProperties GODS_VOLUCITE_AERIAL_BERRY = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodProperties COPPER_PINE_CONE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodProperties AZURITE_COPPER_PINE_CONE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodProperties PHOENIX_FEATHER = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodProperties SKY_CACTUS_FIBER = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodProperties VIBRANT_SKY_CACTUS_FIBER = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build();
        public static final FoodProperties SOLID_ETHER_FRAGMENT = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodProperties GOLDEN_NETHER_MEAT_PIECE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodProperties GOLDEN_NETHER_STEAK = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodProperties VIBRANT_GOLDEN_NETHER_STEAK = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
    }

    public static class Consumables
    {
        public static final Consumable FROZEN_AERIAL_BERRY = defautEffectConsumable(effectList(MobEffects.MOVEMENT_SLOWDOWN, 310, 0, MobEffects.DAMAGE_RESISTANCE, 210, 0));
        public static final Consumable FROZEN_MUTTON = defautEffectConsumable(effectList(MobEffects.MOVEMENT_SLOWDOWN, 310, 0, MobEffects.DAMAGE_RESISTANCE, 210, 0));
        public static final Consumable VIBRANT_CHICKEN = defautEffectConsumable(effectList(MobEffects.SLOW_FALLING, 60, 0));
        public static final Consumable FROZEN_CHICKEN = defautEffectConsumable(effectList(MobEffects.MOVEMENT_SLOWDOWN, 310, 0, MobEffects.DAMAGE_RESISTANCE, 210, 0, MobEffects.HUNGER, 80, 0));
        public static final Consumable RUBY_AERIAL_BERRY = defautEffectConsumable(effectList(MobEffects.HEALTH_BOOST, 2400, 0));
        public static final Consumable VOLUCITE_AERIAL_BERRY = defautEffectConsumable(effectList(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 5000, 1));
        public static final Consumable FROZEN_GLOWING_STICK_FRUIT = defautEffectConsumable(effectList(MobEffects.MOVEMENT_SLOWDOWN, 120, 0, MobEffects.DAMAGE_RESISTANCE, 180, 0));
        public static final Consumable CORTINARIUS_VIOLACEUS_PIECE = defautEffectConsumable(effectList(MobEffects.CONFUSION, 100, 0));
        public static final Consumable GANODERMA_APPLANATUM_PIECE = defautEffectConsumable(effectList(MobEffects.HUNGER, 100, 0));
        public static final Consumable DARK_SHADOW_FRUIT = defautEffectConsumable(effectList(MobEffects.BLINDNESS, 20, 0, MobEffects.NIGHT_VISION, 120, 0));
        public static final Consumable PURPLE_SHADOW_FRUIT = defautEffectConsumable(effectList(MobEffects.BLINDNESS, 20, 0, AerialHellMobEffects.SHADOW_IMMUNITY, 80, 0));
        public static final Consumable SHADOW_FRUIT_STEW = defautEffectConsumable(effectList(MobEffects.BLINDNESS, 20, 0, AerialHellMobEffects.SHADOW_IMMUNITY, 1200, 0));
        public static final Consumable SOLID_ETHER_SOUP = defautEffectConsumable(effectList(MobEffects.SLOW_FALLING, 160, 0, AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 160, 0));
        public static final Consumable VIBRANT_SOLID_ETHER_SOUP = defautEffectConsumable(effectList(MobEffects.SLOW_FALLING, 160, 0, AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 160, 1));
        public static final Consumable FROZEN_SOLID_ETHER_SOUP = defautEffectConsumable(effectList(MobEffects.SLOW_FALLING, 180, 0, AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 160, 0, MobEffects.DAMAGE_RESISTANCE, 210, 0));
        public static final Consumable SHADOW_SPIDER_EYE = defautEffectConsumable(effectList(MobEffects.BLINDNESS, 20, 0, MobEffects.MOVEMENT_SLOWDOWN, 120, 0));
        public static final Consumable PHANTOM_MEAT = defautEffectConsumable(effectList(MobEffects.DAMAGE_RESISTANCE, 120, 0));
        public static final Consumable VIBRANT_PHANTOM_MEAT = defautEffectConsumable(effectList(MobEffects.DAMAGE_RESISTANCE, 400, 1));
        public static final Consumable FROZEN_PHANTOM_MEAT = defautEffectConsumable(effectList(MobEffects.DAMAGE_RESISTANCE, 800, 1));
        public static final Consumable VIBRANT_TURTLE_MEAT = defautEffectConsumable(effectList(MobEffects.SLOW_FALLING, 400, 0));
        public static final Consumable FROZEN_TURTLE_MEAT = defautEffectConsumable(effectList(MobEffects.DAMAGE_RESISTANCE, 400, 0));
        public static final Consumable GODS_VOLUCITE_AERIAL_BERRY = godsVoluciteAerialBerry();
        public static final Consumable AZURITE_COPPER_PINE_CONE = defautEffectConsumable(effectList(MobEffects.DIG_SPEED, 400, 0));
        public static final Consumable PHOENIX_FEATHER = defautEffectConsumable(effectList(MobEffects.SLOW_FALLING, 1200, 0, MobEffects.FIRE_RESISTANCE, 1200, 0));
        public static final Consumable SKY_CACTUS_FIBER = fastToEat();
        public static final Consumable VIBRANT_SKY_CACTUS_FIBER = fastToEat();
        public static final Consumable BLUE_SOLID_ETHER_FRAGMENT = defautEffectConsumable(effectList(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 90, 0));
        public static final Consumable GOLDEN_SOLID_ETHER_FRAGMENT = defautEffectConsumable(effectList(MobEffects.SLOW_FALLING, 110, 0));
        public static final Consumable GREEN_SOLID_ETHER_FRAGMENT = defautEffectConsumable(effectList(MobEffects.JUMP, 90, 1));
        public static final Consumable PURPLE_SOLID_ETHER_FRAGMENT = defautEffectConsumable(effectList(AerialHellMobEffects.SHADOW_IMMUNITY, 90, 1));
        public static final Consumable GOLDEN_NETHER_MEAT_PIECE = defautEffectConsumable(effectList(MobEffects.FIRE_RESISTANCE, 110, 0));
        public static final Consumable GOLDEN_NETHER_STEAK = defautEffectConsumable(effectList(MobEffects.FIRE_RESISTANCE, 500, 0));
        public static final Consumable VIBRANT_GOLDEN_NETHER_STEAK = defautEffectConsumable(effectList(MobEffects.FIRE_RESISTANCE, 1000, 0));

        private static Consumable godsVoluciteAerialBerry()
        {
            return defautEffectConsumable(List.of(
                    new MobEffectInstance(MobEffects.SLOW_FALLING, 2400, 2),
                    new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 2000, 2),
                    new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 2400, 2),
                    new MobEffectInstance(AerialHellMobEffects.GOD, 6000, 0),
                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0),
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 0),
                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1),
                    new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 1),
                    new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)
            ));
        }

        private static Consumable defautEffectConsumable(List<MobEffectInstance> effectList)
        {
            return defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(effectList)).build();
        }

        private static Consumable fastToEat()
        {
            return defaultFood().consumeSeconds(0.8F).build();
        }

        private static List<MobEffectInstance> effectList(Holder<MobEffect> effect, int duration, int amplifier)
        {
            return List.of(new MobEffectInstance(effect, duration, amplifier));
        }

        private static List<MobEffectInstance> effectList(Holder<MobEffect> effect1, int duration1, int amplifier1, Holder<MobEffect> effect2, int duration2, int amplifier2)
        {
            return List.of(new MobEffectInstance(effect1, duration1, amplifier1), new MobEffectInstance(effect2, duration2, amplifier2));
        }

        private static List<MobEffectInstance> effectList(Holder<MobEffect> effect1, int duration1, int amplifier1, Holder<MobEffect> effect2, int duration2, int amplifier2, Holder<MobEffect> effect3, int duration3, int amplifier3)
        {
            return List.of(new MobEffectInstance(effect1, duration1, amplifier1), new MobEffectInstance(effect2, duration2, amplifier2), new MobEffectInstance(effect3, duration3, amplifier3));
        }
    }
}
