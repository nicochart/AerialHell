package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.function.Consumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public class AerialHellSwordItem extends Item
{
	public AerialHellSwordItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Item.Properties settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public AerialHellSwordItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Item.Properties settings)
	{
		super(settings.sword(toolMaterial, 0.0F, 0.0F)); //attackDamage and attackSpeed are overridden below

		//copy of super(..) actions to edit this.components to add custom attributes
		//material.applyToolProperties(....)
		Item.Properties toolSettings = ItemHelper.applySwordProperties(settings, toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth);
		this.components = toolSettings.buildAndValidateComponents(Component.translatable(this.descriptionId), settings.effectiveModel());
	}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), textConsumer);
	}
}
