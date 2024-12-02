package fr.factionbedrock.aerialhell.Item;

import java.util.function.Supplier;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class FoodWithEffectItem extends Item
{
	public FoodWithEffectItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<StatusEffectInstance> effectIn1, Supplier<StatusEffectInstance> effectIn2, Supplier<StatusEffectInstance> effectIn3)
	{
		super(new Item.Settings().rarity(rarity)
				.food(new FoodComponent.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).statusEffect(effectIn1.get(), 1.0F).statusEffect(effectIn2.get(), 1.0F).statusEffect(effectIn3.get(), 1.0F).build()));
	}

	public FoodWithEffectItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<StatusEffectInstance> effectIn1, Supplier<StatusEffectInstance> effectIn2)
	{
		super(new Item.Settings().rarity(rarity)
                .food(new FoodComponent.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).statusEffect(effectIn1.get(), 1.0F).statusEffect(effectIn2.get(), 1.0F).build()));
	}

	public FoodWithEffectItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<StatusEffectInstance> effectIn)
	{
		super(new Item.Settings().rarity(rarity)
                .food(new FoodComponent.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).statusEffect(effectIn.get(), 1.0F).build()));
	}
    
    public FoodWithEffectItem(int hungerIn, float saturationIn, Supplier<StatusEffectInstance> effectIn) //default rarity
    {
        super(new Item.Settings().rarity(Rarity.COMMON)
                .food(new FoodComponent.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).statusEffect(effectIn.get(), 1.0F).build()));
    }
}