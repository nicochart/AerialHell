package fr.factionbedrock.aerialhell.Item;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;

public class AercloudFragmentItem extends Item
{
	public AercloudFragmentItem(ItemGroup group, Supplier<EffectInstance> effectIn)
	{
		super(new Item.Properties().rarity(Rarity.UNCOMMON)
                .food(new Food.Builder().setAlwaysEdible().hunger(1).saturation(0.1F).effect(effectIn, 1.0F).build())
                .group(group));
	}
    
    public AercloudFragmentItem(Supplier<EffectInstance> effectIn) //default group
    {
        super(new Item.Properties().rarity(Rarity.UNCOMMON)
                .food(new Food.Builder().setAlwaysEdible().hunger(1).saturation(0.1F).effect(effectIn, 1.0F).build())
                .group(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
    }
}