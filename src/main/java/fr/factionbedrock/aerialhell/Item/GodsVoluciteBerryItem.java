package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class GodsVoluciteBerryItem extends Item
{
    public GodsVoluciteBerryItem(ItemGroup group)
    {
        super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL)
                .food(new Food.Builder().setAlwaysEdible().hunger(6).saturation(0.8F)
                		.effect(() -> new EffectInstance(Effects.SLOW_FALLING, 2400, 2), 1.0F)
                		.effect(() -> new EffectInstance(Effects.LEVITATION, 100, 4), 1.0F)
                		.effect(() -> new EffectInstance(AerialHellPotionEffects.GOD.get(), 6000, 0), 1.0F)
                		.effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0), 1.0F)
                		.effect(() -> new EffectInstance(Effects.RESISTANCE, 2400, 0), 1.0F)
                		.effect(() -> new EffectInstance(Effects.STRENGTH, 2400, 1), 1.0F)
                		.effect(() -> new EffectInstance(Effects.HASTE, 2400, 1), 1.0F)
                		.effect(() -> new EffectInstance(Effects.ABSORPTION, 2400, 3), 1.0F)
                .build()).group(group));
    }
    
    public GodsVoluciteBerryItem() //default group and rarity
    {
        super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL)
                .food(new Food.Builder().setAlwaysEdible().hunger(6).saturation(0.8F)
                		.effect(() -> new EffectInstance(Effects.SLOW_FALLING, 2400, 2), 1.0F)
                		.effect(() -> new EffectInstance(Effects.LEVITATION, 100, 4), 1.0F)
                		.effect(() -> new EffectInstance(AerialHellPotionEffects.GOD.get(), 6000, 0), 1.0F)
                		.effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0), 1.0F)
                		.effect(() -> new EffectInstance(Effects.RESISTANCE, 2400, 0), 1.0F)
                		.effect(() -> new EffectInstance(Effects.STRENGTH, 2400, 1), 1.0F)
                		.effect(() -> new EffectInstance(Effects.HASTE, 2400, 1), 1.0F)
                		.effect(() -> new EffectInstance(Effects.ABSORPTION, 2400, 3), 1.0F)
                .build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
    	return true;
    }
}
