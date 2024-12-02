package fr.factionbedrock.aerialhell.Item;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class SkySoupItem extends Item //copy of net.minecraft.item.SoupItem but with a constructor with custom Properties (food builder) and with sky bowl
{
	public SkySoupItem(Item.Settings settings) {super(settings);}
	
	public SkySoupItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<StatusEffectInstance> effectIn1, Supplier<StatusEffectInstance> effectIn2, Supplier<StatusEffectInstance> effectIn3)
	{
		super(new Item.Settings().rarity(rarity).maxCount(1)
                .food(new FoodComponent.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).statusEffect(effectIn1.get(), 1.0F).statusEffect(effectIn2.get(), 1.0F).statusEffect(effectIn3.get(), 1.0F).build()));
	}
	
	public SkySoupItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<StatusEffectInstance> effectIn1, Supplier<StatusEffectInstance> effectIn2)
	{
		super(new Item.Settings().rarity(rarity).maxCount(1)
                .food(new FoodComponent.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).statusEffect(effectIn1.get(), 1.0F).statusEffect(effectIn2.get(), 1.0F).build()));
	}
	
	@Override public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entityLiving)
	{
		if (stack.isOf(AerialHellItems.SHADOW_FRUIT_STEW) && !world.isClient) {/*entityLiving.removeEffectsCuredBy(AerialHellMobEffects.Cures.SHADOW_FRUIT_STEW); TODO*/}
		ItemStack itemstack = super.finishUsing(stack, world, entityLiving);
		return entityLiving instanceof PlayerEntity player && (player.getAbilities().creativeMode) ? itemstack : new ItemStack(AerialHellItems.SKY_BOWL);
	}
}