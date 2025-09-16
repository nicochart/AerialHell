package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class AerialHellPickaxeItem extends Item
{
	public AerialHellPickaxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Item.Settings settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public AerialHellPickaxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Item.Settings settings)
	{
		super(settings.pickaxe(toolMaterial, 0.0F, 0.0F)); //attackDamage and attackSpeed are overridden below

		//copy of super(..) actions to edit this.components to add custom attributes
		//material.applyToolProperties(....)
		Item.Settings toolSettings = ItemHelper.applyToolProperties(settings, toolMaterial, BlockTags.PICKAXE_MINEABLE, attackDamage, attackSpeed, movementSpeed, maxHealth);
		this.components = toolSettings.getValidatedComponents(Text.translatable(this.translationKey), settings.getModelId());
	}

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), textConsumer);
	}
}
