package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class SkySoupItem extends Item //copy of net.minecraft.item.SoupItem but with a constructor with custom Properties (food builder) and with sky bowl
{
	public SkySoupItem(Properties builder) {super(builder);}
	
	public SkySoupItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<MobEffectInstance> effectIn1, Supplier<MobEffectInstance> effectIn2, Supplier<MobEffectInstance> effectIn3)
	{
		super(new Properties().rarity(rarity).stacksTo(16)
                .food(new FoodProperties.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).effect(effectIn1.get(), 1.0F).effect(effectIn2.get(), 1.0F).effect(effectIn3.get(), 1.0F).build()));
	}
	
	public SkySoupItem(int hungerIn, float saturationIn, Rarity rarity, Supplier<MobEffectInstance> effectIn1, Supplier<MobEffectInstance> effectIn2)
	{
		super(new Properties().rarity(rarity).stacksTo(16)
                .food(new FoodProperties.Builder().alwaysEdible().nutrition(hungerIn).saturationModifier(saturationIn).effect(effectIn1.get(), 1.0F).effect(effectIn2.get(), 1.0F).build()));
	}
	
	@Override public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving)
	{
		if (stack.is(AerialHellItems.SHADOW_FRUIT_STEW) && !level.isClientSide) {ItemHelper.removeEffectCuredBy(entityLiving, stack);}
		if (entityLiving instanceof Player player && !player.getAbilities().instabuild)
		{
			if (!player.getInventory().add(AerialHellItems.SKY_BOWL.getDefaultInstance())) {player.drop(AerialHellItems.SKY_BOWL.getDefaultInstance(), false);}
		}
		return super.finishUsingItem(stack, level, entityLiving);
	}
}