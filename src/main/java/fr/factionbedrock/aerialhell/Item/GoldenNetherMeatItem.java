package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class GoldenNetherMeatItem extends Item
{
    public GoldenNetherMeatItem(int hunger, float saturation, int duration_in_tinks, ItemGroup group, Rarity rarity)
    {
    	super(new Item.Properties().rarity(Rarity.UNCOMMON)
                .food(new Food.Builder().setAlwaysEdible().hunger(hunger).saturation(saturation).effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, duration_in_tinks, 0), 1.0F).build())
                .group(group).isImmuneToFire());
    }
    
    public GoldenNetherMeatItem(int hunger, int duration_in_tinks) //default group
    {
    	super(new Item.Properties().rarity(Rarity.UNCOMMON)
                .food(new Food.Builder().setAlwaysEdible().hunger(hunger).effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, duration_in_tinks, 0), 1.0F).build())
                .group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire());
    }
}