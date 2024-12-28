package fr.factionbedrock.aerialhell.Item.Tools;

import com.mojang.datafixers.util.Pair;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class AerialHellHoeItem extends HoeItem
{
	static
	{
		TILLING_ACTIONS.put(AerialHellBlocks.STELLAR_DIRT, Pair.of(HoeItem::canTillFarmland, createTillAction(AerialHellBlocks.STELLAR_FARMLAND.getDefaultState())));
		TILLING_ACTIONS.put(AerialHellBlocks.STELLAR_COARSE_DIRT, Pair.of(HoeItem::canTillFarmland, createTillAction(AerialHellBlocks.STELLAR_FARMLAND.getDefaultState())));
		TILLING_ACTIONS.put(AerialHellBlocks.STELLAR_GRASS_BLOCK, Pair.of(HoeItem::canTillFarmland, createTillAction(AerialHellBlocks.STELLAR_FARMLAND.getDefaultState())));
		TILLING_ACTIONS.put(AerialHellBlocks.CHISELED_STELLAR_DIRT, Pair.of(HoeItem::canTillFarmland, createTillAction(AerialHellBlocks.STELLAR_FARMLAND.getDefaultState())));
		TILLING_ACTIONS.put(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK, Pair.of(HoeItem::canTillFarmland, createTillAction(AerialHellBlocks.STELLAR_FARMLAND.getDefaultState())));
	}

	public AerialHellHoeItem(ToolMaterial toolMaterial, Item.Settings settings) {super(toolMaterial, settings);}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), tooltip);
	}
}
