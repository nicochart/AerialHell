package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EffectHoeItem extends AerialHellToolItem
{
	public EffectHoeItem(Properties properties) {super(properties, null, List.of(UseInteractionToolType.HOE));}
	
	@Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {}
	
	@Override public InteractionResult use(Level level, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_HOE.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, level, player, hand, rand, false)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.REAPER_SCYTHE.get())
		{
			EffectToolHelper.applyReaperWalkEffect(this, heldItem, level, player, hand, rand, 600);
	        return InteractionResult.CONSUME;
		}
		else {return super.use(level, player, hand);}
    }
}
