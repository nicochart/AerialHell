package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;

public class AerialHellHoeItem extends HoeItem
{
	public AerialHellHoeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Properties properties)
	{
		super(toolMaterial, 0.0F, 0.0F, properties); //attackDamage and attackSpeed are overridden below

		//copy of super(..) actions to edit this.components to add custom attributes
		//material.applyToolProperties(....)
		Item.Properties toolProperties = ItemHelper.applyToolProperties(properties, toolMaterial, BlockTags.MINEABLE_WITH_HOE, attackDamage, attackSpeed, movementSpeed, maxHealth);
		this.components = toolProperties.buildAndValidateComponents(Component.translatable(this.descriptionId), properties.effectiveModel());
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag)
	{
		components.add(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	public MutableComponent getDescription()
	{
		return Component.translatable(this.getDescriptionId() + ".desc");
	}
}
