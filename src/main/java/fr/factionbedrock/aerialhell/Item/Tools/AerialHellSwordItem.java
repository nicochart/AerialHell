package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AerialHellSwordItem extends SwordItem
{
	protected static final UUID BASE_MOVEMENT_SPEED_UUID = UUID.fromString("7107DE5E-7CE8-4030-940E-514C1F160890");
	protected static final UUID BASE_MAX_HEALTH_UUID = UUID.fromString("5D6F0BA2-1186-46AC-B896-C61C5CEE99CC");
	private final Supplier<ItemAttributeModifiers> defaultModifiers;

	public AerialHellSwordItem(Tier tier, float movementSpeedIn, float maxHealthIn, Properties builderIn)
	{
		super(tier, builderIn);
		this.defaultModifiers = Suppliers.memoize(() ->
			{
				ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
				if (movementSpeedIn != 0) {builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_MOVEMENT_SPEED_UUID, "Weapon modifier", movementSpeedIn, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND);}
				if (maxHealthIn != 0) {builder.add(Attributes.MAX_HEALTH, new AttributeModifier(BASE_MAX_HEALTH_UUID, "Weapon modifier", maxHealthIn, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);}
				return builder.build();
			}
		);
	}

	@Override public ItemAttributeModifiers getDefaultAttributeModifiers() {return this.defaultModifiers.get();}

	@Override @OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag)
	{
		components.add(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	@OnlyIn(Dist.CLIENT)
	public MutableComponent getDescription()
	{
		return Component.translatable(this.getDescriptionId() + ".desc");
	}
}
