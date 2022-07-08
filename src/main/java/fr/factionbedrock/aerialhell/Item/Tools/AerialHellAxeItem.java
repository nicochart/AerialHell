package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;

public class AerialHellAxeItem extends AxeItem
{
	private final Multimap<Attribute, AttributeModifier> axeAttributes;
	protected static final UUID MOVEMENT_SPEED_MODIFIER = UUID.fromString("7107DE5E-7CE8-4030-940E-514C1F160890");
	protected static final UUID MAX_HEALTH_MODIFIER = UUID.fromString("5D6F0BA2-1186-46AC-B896-C61C5CEE99CC");
	
	public AerialHellAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, float movementSpeedIn, float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double)getAttackDamage(), AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)attackSpeedIn, AttributeModifier.Operation.ADDITION));
	    if (movementSpeedIn != 0) {builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_MODIFIER, "Weapon modifier", (double)movementSpeedIn, AttributeModifier.Operation.MULTIPLY_TOTAL));}
	    if (maxHealthIn != 0) {builder.put(Attributes.MAX_HEALTH, new AttributeModifier(MAX_HEALTH_MODIFIER, "Weapon modifier", (double)maxHealthIn, AttributeModifier.Operation.ADDITION));}
	    this.axeAttributes = builder.build();
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		return equipmentSlot == EquipmentSlotType.MAINHAND ? axeAttributes : super.getAttributeModifiers(equipmentSlot);
	}
	
	public Multimap<Attribute, AttributeModifier> getBaseAxeAttributes()
	{
		return axeAttributes;
	}
}
