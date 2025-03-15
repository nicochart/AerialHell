package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class EffectSwordItem extends AerialHellSwordItem
{
	private int timer;

	public EffectSwordItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public EffectSwordItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Settings settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, settings);
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
    public ActionResult use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_SWORD)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, true)) {return ActionResult.CONSUME;}
			else {return ActionResult.PASS;}
		}
		else if (this == AerialHellItems.NINJA_SWORD)
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, world, player, rand, hand,400);
	        return ActionResult.CONSUME;
		}
		else if (this == AerialHellItems.NINJA_MASTER_SWORD)
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, world, player, rand, hand, 340);
	        return ActionResult.CONSUME;
		}
		else if (this == AerialHellItems.RANDOM_SWORD)
		{
			EffectToolHelper.applyRandomEffect(this, heldItem, world, player, hand, rand);
	        return ActionResult.CONSUME;
		}
		else if (this == AerialHellItems.SWORD_OF_LIGHT)
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, world, player, hand, rand, 160);
		    return ActionResult.CONSUME;
		}
		else if (this == AerialHellItems.ANTIDOTE_SWORD)
		{
			if (EffectToolHelper.tryRemovingPoisonAndWitherEffect(this, heldItem, world, player, hand, rand)) {return ActionResult.CONSUME;}
			else {return ActionResult.PASS;}
		}
		else if (this == AerialHellItems.GLOUTON_SWORD)
		{
			if (EffectToolHelper.tryEatingTool(this, heldItem, world, player, hand, rand)) {return ActionResult.CONSUME;}
			else {return ActionResult.PASS;}
		}
		else if (this == AerialHellItems.NETHERIAN_KING_SWORD)
		{
			EffectToolHelper.applyFireResistanceEffect(this, heldItem, world, player, hand, rand, 200, 600);
		    return ActionResult.CONSUME;
		}
		else if (this == AerialHellItems.GLASS_CANON_SWORD)
		{
			EffectToolHelper.PlayerLiftoff(this, heldItem, world, player, hand, rand);
	        return ActionResult.CONSUME;
		}
		else {return super.use(world, player, hand);}
    }
}
