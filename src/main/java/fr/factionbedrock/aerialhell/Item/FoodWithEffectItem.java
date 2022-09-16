package fr.factionbedrock.aerialhell.Item;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellItemGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;

public class FoodWithEffectItem extends Item
{
	public FoodWithEffectItem(int hungerIn, float saturationIn, Rarity rarity, ItemGroup group, Supplier<EffectInstance> effectIn1, Supplier<EffectInstance> effectIn2, int maxStackSize)
	{
		super(new Item.Properties().rarity(rarity).maxStackSize(maxStackSize)
                .food(new Food.Builder().setAlwaysEdible().hunger(hungerIn).saturation(saturationIn).effect(effectIn1, 1.0F).effect(effectIn2, 1.0F).build())
                .group(group));
	}
	
	public FoodWithEffectItem(int hungerIn, float saturationIn, Rarity rarity, ItemGroup group, Supplier<EffectInstance> effectIn1, Supplier<EffectInstance> effectIn2)
	{
		super(new Item.Properties().rarity(rarity)
                .food(new Food.Builder().setAlwaysEdible().hunger(hungerIn).saturation(saturationIn).effect(effectIn1, 1.0F).effect(effectIn2, 1.0F).build())
                .group(group));
	}
	
	public FoodWithEffectItem(int hungerIn, float saturationIn, Rarity rarity, ItemGroup group, Supplier<EffectInstance> effectIn)
	{
		super(new Item.Properties().rarity(rarity)
                .food(new Food.Builder().setAlwaysEdible().hunger(hungerIn).saturation(saturationIn).effect(effectIn, 1.0F).build())
                .group(group));
	}
    
    public FoodWithEffectItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<EffectInstance> effectIn) //default group
    {
        super(new Item.Properties().rarity(rarity)
                .food(new Food.Builder().setAlwaysEdible().hunger(hungerIn).saturation(saturationIn).effect(effectIn, 1.0F).build())
                .group(AerialHellItemGroups.AERIAL_HELL_BLOCKS));
    }
    
    public FoodWithEffectItem(int hungerIn, float saturationIn, Supplier<EffectInstance> effectIn) //default rarity & group
    {
        super(new Item.Properties().rarity(Rarity.COMMON)
                .food(new Food.Builder().setAlwaysEdible().hunger(hungerIn).saturation(saturationIn).effect(effectIn, 1.0F).build())
                .group(AerialHellItemGroups.AERIAL_HELL_BLOCKS));
    }
}