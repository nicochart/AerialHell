package fr.factionbedrock.aerialhell.Item;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;

public class SkySoupItem extends Item //copy of net.minecraft.item.SoupItem but with a constructor with custom Properties (food builder) and with sky bowl
{
	public SkySoupItem(Item.Properties builder) {super(builder);}
	
	public SkySoupItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<MobEffectInstance> effectIn1, Supplier<MobEffectInstance> effectIn2, Supplier<MobEffectInstance> effectIn3)
	{
		super(new Item.Properties().rarity(rarity).stacksTo(1)
                .food(new FoodProperties.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).effect(effectIn1.get(), 1.0F).effect(effectIn2.get(), 1.0F).effect(effectIn3.get(), 1.0F).build()));
	}
	
	public SkySoupItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<MobEffectInstance> effectIn1, Supplier<MobEffectInstance> effectIn2)
	{
		super(new Item.Properties().rarity(rarity).stacksTo(1)
                .food(new FoodProperties.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).effect(effectIn1.get(), 1.0F).effect(effectIn2.get(), 1.0F).build()));
	}
	
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving)
	{
		if (stack.is(AerialHellBlocksAndItems.SHADOW_FRUIT_STEW) && !level.isClientSide) {entityLiving.removeEffectsCuredBy(AerialHellMobEffects.Cures.SHADOW_FRUIT_STEW);}
		ItemStack itemstack = super.finishUsingItem(stack, level, entityLiving);
		return entityLiving instanceof Player && ((Player)entityLiving).getAbilities().instabuild ? itemstack : new ItemStack(AerialHellBlocksAndItems.SKY_BOWL.get());
	}
}