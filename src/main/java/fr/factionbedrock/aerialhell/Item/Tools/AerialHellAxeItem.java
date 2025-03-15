package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;

import java.util.List;

public class AerialHellAxeItem extends AxeItem
{
	public AerialHellAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Item.Settings settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public AerialHellAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Item.Settings settings)
	{
		super(toolMaterial, 0.0F, 0.0F, settings); //attackDamage and attackSpeed are overridden below

		//copy of super(..) actions to edit this.components to add custom attributes
		//material.applyToolProperties(....)
		Item.Settings toolSettings = ItemHelper.applyToolProperties(settings, toolMaterial, BlockTags.AXE_MINEABLE, attackDamage, attackSpeed, movementSpeed, maxHealth);
		this.components = toolSettings.getValidatedComponents(Text.translatable(this.translationKey), settings.getModelId());
	}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), tooltip);
	}
}
