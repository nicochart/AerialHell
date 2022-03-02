package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class GreenAercloudFragmentItem extends Item
{
    public GreenAercloudFragmentItem(ItemGroup group)
    {
        super(new Item.Properties()
                .food(new Food.Builder().setAlwaysEdible().hunger(1).effect(() -> new EffectInstance(AerialHellPotionEffects.HEAD_IN_THE_CLOUDS.get(), 90, 0), 1.0F).effect(() -> new EffectInstance(Effects.SLOW_FALLING, 110, 0), 1.0F).build())
                .group(group));
    }
    
    public GreenAercloudFragmentItem() //default group
    {
        super(new Item.Properties()
                .food(new Food.Builder().setAlwaysEdible().hunger(1).effect(() -> new EffectInstance(AerialHellPotionEffects.HEAD_IN_THE_CLOUDS.get(), 90, 0), 1.0F).effect(() -> new EffectInstance(Effects.SLOW_FALLING, 110, 0), 1.0F).build())
                .group(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
    }
}