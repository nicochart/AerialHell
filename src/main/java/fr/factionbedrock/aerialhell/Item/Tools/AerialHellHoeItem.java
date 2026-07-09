package fr.factionbedrock.aerialhell.Item.Tools;

import com.mojang.datafixers.util.Pair;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

public class AerialHellHoeItem extends HoeItem
{
	static
	{
		TILLABLES.put(AerialHellBlocks.STELLAR_DIRT, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(AerialHellBlocks.STELLAR_FARMLAND.defaultBlockState())));
		TILLABLES.put(AerialHellBlocks.STELLAR_COARSE_DIRT, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(AerialHellBlocks.STELLAR_FARMLAND.defaultBlockState())));
		TILLABLES.put(AerialHellBlocks.STELLAR_GRASS_BLOCK, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(AerialHellBlocks.STELLAR_FARMLAND.defaultBlockState())));
		TILLABLES.put(AerialHellBlocks.CHISELED_STELLAR_DIRT, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(AerialHellBlocks.STELLAR_FARMLAND.defaultBlockState())));
		TILLABLES.put(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK, Pair.of(HoeItem::onlyIfAirAbove, changeIntoState(AerialHellBlocks.STELLAR_FARMLAND.defaultBlockState())));
	}

	public AerialHellHoeItem(Tier toolMaterial, Item.Properties settings) {super(toolMaterial, settings);}

	@Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), tooltip);
	}
}
