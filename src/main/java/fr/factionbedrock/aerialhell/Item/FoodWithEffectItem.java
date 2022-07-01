package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;

public class FoodWithEffectItem extends Item
{
    public FoodWithEffectItem(int hungerIn, float saturationIn, ItemGroup group, Rarity rarity, EffectInstance effect)
    {
        super(new Item.Properties().rarity(rarity)
                .food(new Food.Builder().setAlwaysEdible().hunger(hungerIn).saturation(saturationIn).effect(() -> effect, 1.0F).build())
                .group(group));
    }
    
    public FoodWithEffectItem(int hungerIn, float saturationIn, EffectInstance effect) //default group and rarity
    {
        super(new Item.Properties().rarity(AerialHellRarities.VIBRANT)
                .food(new Food.Builder().setAlwaysEdible().hunger(hungerIn).saturation(saturationIn).effect(() -> effect, 1.0F).build())
                .group(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
    }
}