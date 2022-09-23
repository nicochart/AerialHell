package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EffectAxeItem extends AerialHellAxeItem
{
	public EffectAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, float movementSpeedIn,float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, maxHealthIn, builderIn);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		Random rand = new Random();
		
		if (this == AerialHellBlocksAndItems.VOLUCITE_AXE.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, worldIn, playerIn, rand, true)) {return ActionResult.resultConsume(heldItem);}
			else {return ActionResult.resultPass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.AXE_OF_LIGHT.get())
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, worldIn, playerIn, rand, 320);
		    return ActionResult.resultConsume(heldItem);
		}
		else
		{
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
    }
}
