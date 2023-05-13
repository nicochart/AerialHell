package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellCreativeModeTabs;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;

public class GodsVoluciteBerryItem extends Item
{
    public GodsVoluciteBerryItem(CreativeModeTab group)
    {
        super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL)
                .food(new FoodProperties.Builder().alwaysEat().nutrition(6).saturationMod(0.8F)
                		.effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 2400, 2), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 100, 4), 1.0F)
                		.effect(() -> new MobEffectInstance(AerialHellMobEffects.GOD.get(), 6000, 0), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 0), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 1), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F)
                .build()).tab(group));
    }
    
    public GodsVoluciteBerryItem() //default group and rarity
    {
        super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL)
                .food(new FoodProperties.Builder().alwaysEat().nutrition(6).saturationMod(0.8F)
                		.effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 2400, 2), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 100, 4), 1.0F)
                		.effect(() -> new MobEffectInstance(AerialHellMobEffects.GOD.get(), 6000, 0), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 0), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 1), 1.0F)
                		.effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F)
                .build()).tab(AerialHellCreativeModeTabs.AERIAL_HELL_BLOCKS));
    }
    
    @Override
    public boolean isFoil(ItemStack stack) {return true;}
}
