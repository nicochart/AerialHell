package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;

public class EffectSwordItem extends AerialHellSwordItem
{
	private int timer;
	
	public EffectSwordItem(Tier toolMaterial, Item.Properties settings)
	{
		super(toolMaterial, settings);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (!world.isClientSide() && timer <= 0)
		{
			if (entity instanceof LivingEntity livingEntity)
			{
				if (livingEntity.getMainHandItem().getItem() == this || livingEntity.getOffhandItem().getItem() == this)
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
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_SWORD)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, true)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellItems.NINJA_SWORD)
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, world, player, rand, hand,400);
	        return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellItems.NINJA_MASTER_SWORD)
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, world, player, rand, hand, 340);
	        return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellItems.RANDOM_SWORD)
		{
			EffectToolHelper.applyRandomEffect(this, heldItem, world, player, hand, rand);
	        return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellItems.SWORD_OF_LIGHT)
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, world, player, hand, rand, 160);
		    return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellItems.ANTIDOTE_SWORD)
		{
			if (EffectToolHelper.tryRemovingPoisonAndWitherEffect(this, heldItem, world, player, hand, rand)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellItems.GLOUTON_SWORD)
		{
			if (EffectToolHelper.tryEatingTool(this, heldItem, world, player, hand, rand)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellItems.NETHERIAN_KING_SWORD)
		{
			EffectToolHelper.applyFireResistanceEffect(this, heldItem, world, player, hand, rand, 200, 600);
		    return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellItems.GLASS_CANON_SWORD)
		{
			EffectToolHelper.PlayerLiftoff(this, heldItem, world, player, hand, rand);
	        return InteractionResultHolder.consume(heldItem);
		}
		else {return super.use(world, player, hand);}
    }
}
