package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AerialHellShovelItem extends ShovelItem
{
	protected static final UUID BASE_MOVEMENT_SPEED_UUID = UUID.fromString("7107DE5E-7CE8-4030-940E-514C1F160890");
	protected static final UUID BASE_MAX_HEALTH_UUID = UUID.fromString("5D6F0BA2-1186-46AC-B896-C61C5CEE99CC");
	
	public AerialHellShovelItem(Tier tier, float attackDamageIn, float attackSpeedIn, float movementSpeedIn, float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", (double)attackDamageIn + tier.getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", (double)attackSpeedIn, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_MOVEMENT_SPEED_UUID, "Weapon modifier", (double)movementSpeedIn, AttributeModifier.Operation.MULTIPLY_TOTAL));
		builder.put(Attributes.MAX_HEALTH, new AttributeModifier(BASE_MAX_HEALTH_UUID, "Weapon modifier", (double)maxHealthIn, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
	}
	
	@Override @OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		tooltip.add(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	@OnlyIn(Dist.CLIENT)
	public TranslatableComponent getDescription()
	{
		return new TranslatableComponent(this.getDescriptionId() + ".desc");
	}
}
