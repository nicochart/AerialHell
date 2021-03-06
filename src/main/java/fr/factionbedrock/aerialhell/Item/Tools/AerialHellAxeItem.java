package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
	
	@Override @OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(this.getDescription().mergeStyle(TextFormatting.GRAY));
	}

	@OnlyIn(Dist.CLIENT)
	public IFormattableTextComponent getDescription()
	{
		return new TranslationTextComponent(this.getTranslationKey() + ".desc");
	}
}
