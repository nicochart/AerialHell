package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.function.Consumer;

import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public class AerialHellPickaxeItem extends Item
{
	public AerialHellPickaxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Item.Properties settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public AerialHellPickaxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Item.Properties properties)
	{
		super(properties.pickaxe(toolMaterial, 0.0F, 0.0F)); //attackDamage and attackSpeed are overridden below

		//copy of super(..) actions to edit this.components to add custom attributes
		//material.applyToolProperties(....)
		Item.Properties toolProperties = ItemHelper.applyToolProperties(properties, toolMaterial, BlockTags.MINEABLE_WITH_PICKAXE, attackDamage, attackSpeed, movementSpeed, maxHealth);
		DataComponentInitializers.Initializer<Item> componentInitializer = properties.finalizeInitializer(Component.translatable(this.descriptionId), toolProperties.effectiveModel());
		BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.add(properties.itemIdOrThrow(), componentInitializer);
	}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), textConsumer);
	}
}
