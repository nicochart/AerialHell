package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;

public class EffectHoeItem extends AerialHellHoeItem
{	
	public EffectHoeItem(Tier toolMaterial, Item.Properties settings)
	{
		super(toolMaterial, settings);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_HOE)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, false)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellItems.REAPER_SCYTHE)
		{
			EffectToolHelper.applyReaperWalkEffect(this, heldItem, world, player, hand, rand, 600);
	        return InteractionResultHolder.consume(heldItem);
		}
		else {return super.use(world, player, hand);}
    }
}
