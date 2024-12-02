package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EffectSwordItem extends AerialHellSwordItem
{
	private int timer;
	
	public EffectSwordItem(ToolMaterial toolMaterial, Item.Settings settings)
	{
		super(toolMaterial, settings);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (!world.isClient() && timer <= 0)
		{
			if (entity instanceof LivingEntity livingEntity)
			{
				if (livingEntity.getMainHandStack().getItem() == this || livingEntity.getOffHandStack().getItem() == this)
				{
					if (this == AerialHellItems.GOD_SWORD) {EffectToolHelper.PassiveEffects.applyGodEffect((LivingEntity)entity);}
				}
			}
			timer = 200;
		}
		else if (timer > -10)
		{
			timer--;
		}
	}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_SWORD)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, true)) {return TypedActionResult.consume(heldItem);}
			else {return TypedActionResult.pass(heldItem);}
		}
		else if (this == AerialHellItems.NINJA_SWORD)
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, world, player, rand, hand,400);
	        return TypedActionResult.consume(heldItem);
		}
		else if (this == AerialHellItems.NINJA_MASTER_SWORD)
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, world, player, rand, hand, 340);
	        return TypedActionResult.consume(heldItem);
		}
		else if (this == AerialHellItems.RANDOM_SWORD)
		{
			EffectToolHelper.applyRandomEffect(this, heldItem, world, player, hand, rand);
	        return TypedActionResult.consume(heldItem);
		}
		else if (this == AerialHellItems.SWORD_OF_LIGHT)
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, world, player, hand, rand, 160);
		    return TypedActionResult.consume(heldItem);
		}
		else if (this == AerialHellItems.ANTIDOTE_SWORD)
		{
			if (EffectToolHelper.tryRemovingPoisonAndWitherEffect(this, heldItem, world, player, hand, rand)) {return TypedActionResult.consume(heldItem);}
			else {return TypedActionResult.pass(heldItem);}
		}
		else if (this == AerialHellItems.GLOUTON_SWORD)
		{
			if (EffectToolHelper.tryEatingTool(this, heldItem, world, player, hand, rand)) {return TypedActionResult.consume(heldItem);}
			else {return TypedActionResult.pass(heldItem);}
		}
		else if (this == AerialHellItems.NETHERIAN_KING_SWORD)
		{
			EffectToolHelper.applyFireResistanceEffect(this, heldItem, world, player, hand, rand, 200, 600);
		    return TypedActionResult.consume(heldItem);
		}
		else if (this == AerialHellItems.GLASS_CANON_SWORD)
		{
			EffectToolHelper.PlayerLiftoff(this, heldItem, world, player, hand, rand);
	        return TypedActionResult.consume(heldItem);
		}
		else {return super.use(world, player, hand);}
    }
}
