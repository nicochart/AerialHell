package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EffectAxeItem extends AerialHellAxeItem
{
	public EffectAxeItem(Tier tier, float movementSpeedIn,float maxHealthIn, Properties builderIn)
	{
		super(tier, movementSpeedIn, maxHealthIn, builderIn);
	}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
		ItemStack heldItem = playerIn.getItemInHand(handIn);
		Random rand = new Random();
		
		if (this == AerialHellBlocksAndItems.VOLUCITE_AXE.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, worldIn, playerIn, handIn, rand, true)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.AXE_OF_LIGHT.get())
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, worldIn, playerIn, handIn, rand, 320);
		    return InteractionResultHolder.consume(heldItem);
		}
		else
		{
			return super.use(worldIn, playerIn, handIn);
		}
    }
}
