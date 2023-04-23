package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EffectHoeItem extends AerialHellHoeItem
{	
	public EffectHoeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float movementSpeedIn, float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, maxHealthIn, builderIn);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		Random rand = new Random();
		if (this == AerialHellBlocksAndItems.VOLUCITE_HOE.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, worldIn, playerIn, rand, false)) {return ActionResult.resultConsume(heldItem);}
			else {return ActionResult.resultPass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.REAPER_SCYTHE.get())
		{
			EffectToolHelper.applyReaperWalkEffect(this, heldItem, worldIn, playerIn, rand, 600);
	        return ActionResult.resultConsume(heldItem);
		}
		else {return super.onItemRightClick(worldIn, playerIn, handIn);}
    }
}
