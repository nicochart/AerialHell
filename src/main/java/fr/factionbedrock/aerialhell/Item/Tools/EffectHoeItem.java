package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class EffectHoeItem extends AerialHellHoeItem
{
	public EffectHoeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public EffectHoeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Settings settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, settings);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {}
	
	@Override
    public ActionResult use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_HOE)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, false)) {return ActionResult.CONSUME;}
			else {return ActionResult.PASS;}
		}
		else if (this == AerialHellItems.REAPER_SCYTHE)
		{
			EffectToolHelper.applyReaperWalkEffect(this, heldItem, world, player, hand, rand, 600);
	        return ActionResult.CONSUME;
		}
		else {return super.use(world, player, hand);}
    }
}
