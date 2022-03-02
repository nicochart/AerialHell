package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class GoldenAercloudFragmentItem extends Item
{
    public GoldenAercloudFragmentItem(ItemGroup group)
    {
        super(new Item.Properties().rarity(Rarity.UNCOMMON)
                .food(new Food.Builder().setAlwaysEdible().hunger(1).effect(() -> new EffectInstance(Effects.SLOW_FALLING, 110, 0), 1.0F).build())
                .group(group));
    }
    
    public GoldenAercloudFragmentItem() //default group
    {
        super(new Item.Properties().rarity(Rarity.UNCOMMON)
                .food(new Food.Builder().setAlwaysEdible().hunger(1).effect(() -> new EffectInstance(Effects.SLOW_FALLING, 110, 0), 1.0F).build())
                .group(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
    }
}