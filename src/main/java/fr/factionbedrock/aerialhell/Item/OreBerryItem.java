package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;

public class OreBerryItem extends Item
{
    public OreBerryItem(ItemGroup group, Rarity rarity, EffectInstance effect)
    {
        super(new Item.Properties().rarity(rarity)
                .food(new Food.Builder().setAlwaysEdible().hunger(6).effect(() -> effect, 1.0F).build())
                .group(group));
    }
    
    public OreBerryItem(EffectInstance effect) //default group and rarity
    {
        super(new Item.Properties().rarity(AerialHellRarities.VIBRANT)
                .food(new Food.Builder().setAlwaysEdible().hunger(6).effect(() -> effect, 1.0F).build())
                .group(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
    }
}