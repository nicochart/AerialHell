package fr.factionbedrock.aerialhell.Registry;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.List;

import static net.minecraft.component.type.ConsumableComponents.food;

public class AerialHellFoods
{
    public static class Properties
    {
        public static final FoodComponent AERIAL_BERRY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodComponent ROASTED_AERIAL_BERRY = new FoodComponent.Builder().nutrition(3).saturationModifier(0.3F).build();
        public static final FoodComponent VIBRANT_AERIAL_BERRY = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodComponent FROZEN_AERIAL_BERRY = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodComponent STELLAR_BREAD = new FoodComponent.Builder().nutrition(5).saturationModifier(0.6F).build();
        public static final FoodComponent FROZEN_MUTTON = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodComponent VIBRANT_CHICKEN = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodComponent FROZEN_CHICKEN = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodComponent RUBY_AERIAL_BERRY = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodComponent VOLUCITE_AERIAL_BERRY = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodComponent GLOWING_STICK_FRUIT = new FoodComponent.Builder().nutrition(3).saturationModifier(0.3F).build();
        public static final FoodComponent VIBRANT_GLOWING_STICK_FRUIT = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodComponent FROZEN_GLOWING_STICK_FRUIT = new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F).build();
        public static final FoodComponent CORTINARIUS_VIOLACEUS_PIECE = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodComponent GANODERMA_APPLANATUM_PIECE = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodComponent DARK_SHADOW_FRUIT = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodComponent PURPLE_SHADOW_FRUIT = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodComponent SHADOW_FRUIT_STEW = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodComponent SOLID_ETHER_SOUP = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodComponent VIBRANT_SOLID_ETHER_SOUP = new FoodComponent.Builder().nutrition(4).saturationModifier(0.2F).build();
        public static final FoodComponent FROZEN_SOLID_ETHER_SOUP = new FoodComponent.Builder().nutrition(4).saturationModifier(0.2F).build();
        public static final FoodComponent SHADOW_SPIDER_EYE = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2F).build();
        public static final FoodComponent PHANTOM_MEAT = new FoodComponent.Builder().nutrition(5).saturationModifier(0.8F).build();
        public static final FoodComponent VIBRANT_PHANTOM_MEAT = new FoodComponent.Builder().nutrition(8).saturationModifier(0.8F).build();
        public static final FoodComponent FROZEN_PHANTOM_MEAT = new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F).build();
        public static final FoodComponent COOKED_PHANTOM_MEAT = new FoodComponent.Builder().nutrition(10).saturationModifier(0.9F).build();
        public static final FoodComponent VIBRANT_TURTLE_MEAT = new FoodComponent.Builder().nutrition(7).saturationModifier(0.7F).build();
        public static final FoodComponent FROZEN_TURTLE_MEAT = new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F).build();
        public static final FoodComponent GODS_VOLUCITE_AERIAL_BERRY = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodComponent COPPER_PINE_CONE = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodComponent AZURITE_COPPER_PINE_CONE = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodComponent PHOENIX_FEATHER = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
        public static final FoodComponent SKY_CACTUS_FIBER = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodComponent VIBRANT_SKY_CACTUS_FIBER = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
        public static final FoodComponent SOLID_ETHER_FRAGMENT = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodComponent GOLDEN_NETHER_MEAT_PIECE = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1F).build();
        public static final FoodComponent GOLDEN_NETHER_STEAK = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build();
        public static final FoodComponent VIBRANT_GOLDEN_NETHER_STEAK = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
    }

    public static class Consumables
    {
        public static final ConsumableComponent FROZEN_AERIAL_BERRY = defautEffectConsumableComponent(effectList(StatusEffects.SLOWNESS, 310, 0, StatusEffects.RESISTANCE, 210, 0));
        public static final ConsumableComponent FROZEN_MUTTON = defautEffectConsumableComponent(effectList(StatusEffects.SLOWNESS, 310, 0, StatusEffects.RESISTANCE, 210, 0));
        public static final ConsumableComponent VIBRANT_CHICKEN = defautEffectConsumableComponent(effectList(StatusEffects.SLOW_FALLING, 60, 0));
        public static final ConsumableComponent FROZEN_CHICKEN = defautEffectConsumableComponent(effectList(StatusEffects.SLOWNESS, 310, 0, StatusEffects.RESISTANCE, 210, 0, StatusEffects.HUNGER, 80, 0));
        public static final ConsumableComponent RUBY_AERIAL_BERRY = defautEffectConsumableComponent(effectList(StatusEffects.HEALTH_BOOST, 2400, 0));
        public static final ConsumableComponent VOLUCITE_AERIAL_BERRY = defautEffectConsumableComponent(effectList(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 5000, 1));
        public static final ConsumableComponent FROZEN_GLOWING_STICK_FRUIT = defautEffectConsumableComponent(effectList(StatusEffects.SLOWNESS, 120, 0, StatusEffects.RESISTANCE, 180, 0));
        public static final ConsumableComponent CORTINARIUS_VIOLACEUS_PIECE = defautEffectConsumableComponent(effectList(StatusEffects.NAUSEA, 100, 0));
        public static final ConsumableComponent GANODERMA_APPLANATUM_PIECE = defautEffectConsumableComponent(effectList(StatusEffects.HUNGER, 100, 0));
        public static final ConsumableComponent DARK_SHADOW_FRUIT = defautEffectConsumableComponent(effectList(StatusEffects.BLINDNESS, 20, 0, StatusEffects.NIGHT_VISION, 120, 0));
        public static final ConsumableComponent PURPLE_SHADOW_FRUIT = defautEffectConsumableComponent(effectList(StatusEffects.BLINDNESS, 20, 0, AerialHellMobEffects.SHADOW_IMMUNITY, 80, 0));
        public static final ConsumableComponent SHADOW_FRUIT_STEW = defautEffectConsumableComponent(effectList(StatusEffects.BLINDNESS, 20, 0, AerialHellMobEffects.SHADOW_IMMUNITY, 1200, 0));
        public static final ConsumableComponent SOLID_ETHER_SOUP = defautEffectConsumableComponent(effectList(StatusEffects.SLOW_FALLING, 160, 0, AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 160, 0));
        public static final ConsumableComponent VIBRANT_SOLID_ETHER_SOUP = defautEffectConsumableComponent(effectList(StatusEffects.SLOW_FALLING, 160, 0, AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 160, 1));
        public static final ConsumableComponent FROZEN_SOLID_ETHER_SOUP = defautEffectConsumableComponent(effectList(StatusEffects.SLOW_FALLING, 180, 0, AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 160, 0, StatusEffects.RESISTANCE, 210, 0));
        public static final ConsumableComponent SHADOW_SPIDER_EYE = defautEffectConsumableComponent(effectList(StatusEffects.BLINDNESS, 20, 0, StatusEffects.SLOWNESS, 120, 0));
        public static final ConsumableComponent PHANTOM_MEAT = defautEffectConsumableComponent(effectList(StatusEffects.RESISTANCE, 120, 0));
        public static final ConsumableComponent VIBRANT_PHANTOM_MEAT = defautEffectConsumableComponent(effectList(StatusEffects.RESISTANCE, 400, 1));
        public static final ConsumableComponent FROZEN_PHANTOM_MEAT = defautEffectConsumableComponent(effectList(StatusEffects.RESISTANCE, 800, 1));
        public static final ConsumableComponent VIBRANT_TURTLE_MEAT = defautEffectConsumableComponent(effectList(StatusEffects.SLOW_FALLING, 400, 0));
        public static final ConsumableComponent FROZEN_TURTLE_MEAT = defautEffectConsumableComponent(effectList(StatusEffects.RESISTANCE, 400, 0));
        public static final ConsumableComponent GODS_VOLUCITE_AERIAL_BERRY = godsVoluciteAerialBerry();
        public static final ConsumableComponent AZURITE_COPPER_PINE_CONE = defautEffectConsumableComponent(effectList(StatusEffects.HASTE, 400, 0));
        public static final ConsumableComponent PHOENIX_FEATHER = defautEffectConsumableComponent(effectList(StatusEffects.SLOW_FALLING, 1200, 0, StatusEffects.FIRE_RESISTANCE, 1200, 0));
        public static final ConsumableComponent SKY_CACTUS_FIBER = fastToEat();
        public static final ConsumableComponent VIBRANT_SKY_CACTUS_FIBER = fastToEat();
        public static final ConsumableComponent BLUE_SOLID_ETHER_FRAGMENT = defautEffectConsumableComponent(effectList(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 90, 0));
        public static final ConsumableComponent GOLDEN_SOLID_ETHER_FRAGMENT = defautEffectConsumableComponent(effectList(StatusEffects.SLOW_FALLING, 110, 0));
        public static final ConsumableComponent GREEN_SOLID_ETHER_FRAGMENT = defautEffectConsumableComponent(effectList(StatusEffects.JUMP_BOOST, 90, 1));
        public static final ConsumableComponent PURPLE_SOLID_ETHER_FRAGMENT = defautEffectConsumableComponent(effectList(AerialHellMobEffects.SHADOW_IMMUNITY, 90, 1));
        public static final ConsumableComponent GOLDEN_NETHER_MEAT_PIECE = defautEffectConsumableComponent(effectList(StatusEffects.FIRE_RESISTANCE, 110, 0));
        public static final ConsumableComponent GOLDEN_NETHER_STEAK = defautEffectConsumableComponent(effectList(StatusEffects.FIRE_RESISTANCE, 500, 0));
        public static final ConsumableComponent VIBRANT_GOLDEN_NETHER_STEAK = defautEffectConsumableComponent(effectList(StatusEffects.FIRE_RESISTANCE, 1000, 0));

        private static ConsumableComponent godsVoluciteAerialBerry()
        {
            return defautEffectConsumableComponent(List.of(
                    new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2400, 2),
                    new StatusEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 2000, 2),
                    new StatusEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 2400, 2),
                    new StatusEffectInstance(AerialHellMobEffects.GOD, 6000, 0),
                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, 2400, 0),
                    new StatusEffectInstance(StatusEffects.STRENGTH, 2400, 1),
                    new StatusEffectInstance(StatusEffects.HASTE, 2400, 1),
                    new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3)
            ));
        }

        private static ConsumableComponent defautEffectConsumableComponent(List<StatusEffectInstance> effectList)
        {
            return food().consumeEffect(new ApplyEffectsConsumeEffect(effectList)).build();
        }

        private static ConsumableComponent fastToEat()
        {
            return food().consumeSeconds(0.8F).build();
        }

        private static List<StatusEffectInstance> effectList(RegistryEntry<StatusEffect> effect, int duration, int amplifier)
        {
            return List.of(new StatusEffectInstance(effect, duration, amplifier));
        }

        private static List<StatusEffectInstance> effectList(RegistryEntry<StatusEffect> effect1, int duration1, int amplifier1, RegistryEntry<StatusEffect> effect2, int duration2, int amplifier2)
        {
            return List.of(new StatusEffectInstance(effect1, duration1, amplifier1), new StatusEffectInstance(effect2, duration2, amplifier2));
        }

        private static List<StatusEffectInstance> effectList(RegistryEntry<StatusEffect> effect1, int duration1, int amplifier1, RegistryEntry<StatusEffect> effect2, int duration2, int amplifier2, RegistryEntry<StatusEffect> effect3, int duration3, int amplifier3)
        {
            return List.of(new StatusEffectInstance(effect1, duration1, amplifier1), new StatusEffectInstance(effect2, duration2, amplifier2), new StatusEffectInstance(effect3, duration3, amplifier3));
        }
    }
}
