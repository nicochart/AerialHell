package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EffectHoeItem extends AerialHellHoeItem
{	
	public EffectHoeItem(ToolMaterial toolMaterial, Item.Settings settings)
	{
		super(toolMaterial, settings);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_HOE)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, false)) {return TypedActionResult.consume(heldItem);}
			else {return TypedActionResult.pass(heldItem);}
		}
		else if (this == AerialHellItems.REAPER_SCYTHE)
		{
			EffectToolHelper.applyReaperWalkEffect(this, heldItem, world, player, hand, rand, 600);
	        return TypedActionResult.consume(heldItem);
		}
		else {return super.use(world, player, hand);}
    }
}
