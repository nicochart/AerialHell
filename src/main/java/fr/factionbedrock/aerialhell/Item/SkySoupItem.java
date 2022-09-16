package fr.factionbedrock.aerialhell.Item;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class SkySoupItem extends Item //copy of net.minecraft.item.SoupItem but with a constructor with custom Properties (food builder) and with sky bowl
{
	public SkySoupItem(Item.Properties builder) {super(builder);}
	
	public SkySoupItem(int hungerIn, float saturationIn, Rarity rarity, ItemGroup group, Supplier<EffectInstance> effectIn1, Supplier<EffectInstance> effectIn2)
	{
		super(new Item.Properties().rarity(rarity).maxStackSize(1)
                .food(new Food.Builder().setAlwaysEdible().hunger(hungerIn).saturation(saturationIn).effect(effectIn1, 1.0F).effect(effectIn2, 1.0F).build())
                .group(group));
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving)
	{
		ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
		return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.isCreativeMode ? itemstack : new ItemStack(AerialHellBlocksAndItems.SKY_BOWL.get());
	}
}