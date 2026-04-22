package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.function.Consumer;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.TooltipDisplay;

public class AerialHellAxeItem extends AxeItem
{
	public AerialHellAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties properties)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, properties);
	}

	public AerialHellAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Properties properties)
	{
		super(toolMaterial, 0.0F, 0.0F, properties); //attackDamage and attackSpeed are overridden below

		//TODO does it work ? needed two access transformers (finalizeInitializer and itemIdOrThrow) for that
		//copy of super(..) actions to edit this.components to add custom attributes
		Item.Properties toolProperties = ItemHelper.applyToolProperties(properties, toolMaterial, BlockTags.MINEABLE_WITH_AXE, attackDamage, attackSpeed, movementSpeed, maxHealth);
		DataComponentInitializers.Initializer<Item> componentInitializer = properties.finalizeInitializer(Component.translatable(this.descriptionId), toolProperties.effectiveModel());
		BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.add(properties.itemIdOrThrow(), componentInitializer);
	}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		tooltipAdder.accept(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	public MutableComponent getDescription()
	{
		return Component.translatable(this.getDescriptionId() + ".desc");
	}
}
