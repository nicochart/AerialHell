package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EffectAxeItem extends AerialHellToolItem
{
	public EffectAxeItem(Properties properties) {super(properties, null, List.of(UseInteractionToolType.AXE));}
	
	@Override public InteractionResult use(Level level, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		
		if (this == AerialHellItems.VOLUCITE_AXE.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, level, player, hand, rand, true)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.AXE_OF_LIGHT.get())
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, level, player, hand, rand, 320);
		    return InteractionResult.CONSUME;
		}
		else
		{
			return super.use(level, player, hand);
		}
    }
}
