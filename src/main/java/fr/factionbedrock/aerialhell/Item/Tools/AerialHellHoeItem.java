package fr.factionbedrock.aerialhell.Item.Tools;

import com.mojang.datafixers.util.Pair;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.function.Consumer;

import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

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

	public AerialHellHoeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Item.Properties properties)
	{
		super(toolMaterial, 0.0F, 0.0F, properties); //attackDamage and attackSpeed are overridden below

		//copy of super(..) actions to edit this.components to add custom attributes
		Item.Properties toolProperties = ItemHelper.applyToolProperties(properties, toolMaterial, BlockTags.MINEABLE_WITH_HOE, attackDamage, attackSpeed, movementSpeed, maxHealth);
		DataComponentInitializers.Initializer<Item> componentInitializer = properties.finalizeInitializer(Component.translatable(this.descriptionId), toolProperties.effectiveModel());
		BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.add(properties.itemIdOrThrow(), componentInitializer);
	}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), textConsumer);
	}
}
