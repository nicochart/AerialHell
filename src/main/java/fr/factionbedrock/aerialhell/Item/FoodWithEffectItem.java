package fr.factionbedrock.aerialhell.Item;

import java.util.function.Supplier;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;

public class FoodWithEffectItem extends Item
{	
	public FoodWithEffectItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<MobEffectInstance> effectIn1, Supplier<MobEffectInstance> effectIn2)
	{
		super(new Item.Properties().rarity(rarity)
                .food(new FoodProperties.Builder().alwaysEat().nutrition(hungerIn).saturationMod(saturationIn).effect(effectIn1, 1.0F).effect(effectIn2, 1.0F).build()));
	}
	
	public FoodWithEffectItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<MobEffectInstance> effectIn)
	{
		super(new Item.Properties().rarity(rarity)
                .food(new FoodProperties.Builder().alwaysEat().nutrition(hungerIn).saturationMod(saturationIn).effect(effectIn, 1.0F).build()));
	}
    
    public FoodWithEffectItem(int hungerIn, float saturationIn, Supplier<MobEffectInstance> effectIn) //default rarity
    {
        super(new Item.Properties().rarity(Rarity.COMMON)
                .food(new FoodProperties.Builder().alwaysEat().nutrition(hungerIn).saturationMod(saturationIn).effect(effectIn, 1.0F).build()));
    }
}