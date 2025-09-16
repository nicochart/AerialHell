package fr.factionbedrock.aerialhell.Item.Tools;

import com.mojang.datafixers.util.Pair;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;

import java.util.function.Consumer;

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

	public AerialHellHoeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Item.Settings settings)
	{
		super(toolMaterial, 0.0F, 0.0F, settings); //attackDamage and attackSpeed are overridden below

		//copy of super(..) actions to edit this.components to add custom attributes
		//material.applyToolProperties(....)
		Item.Settings toolSettings = ItemHelper.applyToolProperties(settings, toolMaterial, BlockTags.HOE_MINEABLE, attackDamage, attackSpeed, movementSpeed, maxHealth);
		this.components = toolSettings.getValidatedComponents(Text.translatable(this.translationKey), settings.getModelId());
	}

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), textConsumer);
	}
}
