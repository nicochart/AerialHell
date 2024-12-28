package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class AerialHellShovelItem extends ShovelItem
{
	static
	{
		PATH_STATES.put(AerialHellBlocks.STELLAR_DIRT, AerialHellBlocks.STELLAR_DIRT_PATH.getDefaultState());
		PATH_STATES.put(AerialHellBlocks.STELLAR_COARSE_DIRT, AerialHellBlocks.STELLAR_DIRT_PATH.getDefaultState());
		PATH_STATES.put(AerialHellBlocks.STELLAR_GRASS_BLOCK, AerialHellBlocks.STELLAR_DIRT_PATH.getDefaultState());
		PATH_STATES.put(AerialHellBlocks.CHISELED_STELLAR_DIRT, AerialHellBlocks.STELLAR_DIRT_PATH.getDefaultState());
		PATH_STATES.put(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK, AerialHellBlocks.STELLAR_DIRT_PATH.getDefaultState());
	}

	public AerialHellShovelItem(ToolMaterial toolMaterial, Item.Settings settings) {super(toolMaterial, settings);}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), tooltip);
	}
}
