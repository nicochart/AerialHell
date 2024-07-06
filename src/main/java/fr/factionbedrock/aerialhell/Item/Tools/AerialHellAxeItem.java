package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class AerialHellAxeItem extends AxeItem
{
	public static final ResourceLocation BASE_MOVEMENT_SPEED_ID = ResourceLocation.withDefaultNamespace("base_movement_speed"); //TODO test. aerial hell namespace ?
	public static final ResourceLocation BASE_MAX_HEALTH_ID = ResourceLocation.withDefaultNamespace("base_movement_speed");

	private final Supplier<ItemAttributeModifiers> defaultModifiers;

	public AerialHellAxeItem(Tier tier, float movementSpeedIn, float maxHealthIn, Properties builderIn)
	{
		super(tier, builderIn);
		this.defaultModifiers = Suppliers.memoize(() ->
			{
				ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
				if (movementSpeedIn != 0) {builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_MOVEMENT_SPEED_ID, movementSpeedIn, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND);}
				if (maxHealthIn != 0) {builder.add(Attributes.MAX_HEALTH, new AttributeModifier(BASE_MAX_HEALTH_ID, maxHealthIn, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);}
				return builder.build();
			}
		);
	}

	@Override public ItemAttributeModifiers getDefaultAttributeModifiers() {return this.defaultModifiers.get();}

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
