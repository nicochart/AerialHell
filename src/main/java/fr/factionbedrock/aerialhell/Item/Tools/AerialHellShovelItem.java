package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

public class AerialHellShovelItem extends ShovelItem
{
	static
	{
		FLATTENABLES.put(AerialHellBlocks.STELLAR_DIRT, AerialHellBlocks.STELLAR_DIRT_PATH.defaultBlockState());
		FLATTENABLES.put(AerialHellBlocks.STELLAR_COARSE_DIRT, AerialHellBlocks.STELLAR_DIRT_PATH.defaultBlockState());
		FLATTENABLES.put(AerialHellBlocks.STELLAR_GRASS_BLOCK, AerialHellBlocks.STELLAR_DIRT_PATH.defaultBlockState());
		FLATTENABLES.put(AerialHellBlocks.CHISELED_STELLAR_DIRT, AerialHellBlocks.STELLAR_DIRT_PATH.defaultBlockState());
		FLATTENABLES.put(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK, AerialHellBlocks.STELLAR_DIRT_PATH.defaultBlockState());
	}

	public AerialHellShovelItem(Tier toolMaterial, Item.Properties settings) {super(toolMaterial, settings);}

	@Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), tooltip);
	}
}
