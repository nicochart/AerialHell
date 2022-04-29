package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellRarities;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FrozenAerialBerryItem extends Item
{
    public FrozenAerialBerryItem(ItemGroup group)
    {
        super(new Item.Properties().rarity(AerialHellRarities.FROZEN)
                .food(new Food.Builder().setAlwaysEdible().hunger(8).saturation(0.6F).effect(() -> new EffectInstance(Effects.SLOWNESS, 310, 0), 1.0F).effect(() -> new EffectInstance(Effects.RESISTANCE, 210, 0), 1.0F).build())
                .group(group));
    }
}