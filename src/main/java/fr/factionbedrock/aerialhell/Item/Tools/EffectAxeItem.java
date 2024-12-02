package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EffectAxeItem extends AerialHellAxeItem
{
	public EffectAxeItem(ToolMaterial toolMaterial, Item.Settings settings)
	{
		super(toolMaterial, settings);
	}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();
		
		if (this == AerialHellItems.VOLUCITE_AXE)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, true)) {return TypedActionResult.consume(heldItem);}
			else {return TypedActionResult.pass(heldItem);}
		}
		else if (this == AerialHellItems.AXE_OF_LIGHT)
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, world, player, hand, rand, 320);
		    return TypedActionResult.consume(heldItem);
		}
		else
		{
			return super.use(world, player, hand);
		}
    }
}
