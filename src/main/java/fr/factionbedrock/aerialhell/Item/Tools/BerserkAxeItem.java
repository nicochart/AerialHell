package fr.factionbedrock.aerialhell.Item.Tools;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BerserkAxeItem extends EffectAxeItem
{
	private int weight;
	private Multimap<Attribute, AttributeModifier> berserkAxeAttributes;
	
	public BerserkAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, float movementSpeedIn,	float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, maxHealthIn, builderIn);
		this.berserkAxeAttributes = getBaseAxeAttributes();
		this.weight = 0;
	}
	
	public int getBerserkAttackDamage(int weight)
	{
		if (weight == 0)
		{
			return 11;
		}
		else if (weight <= 200)
		{
			return 9;
		}
		else if (weight <= 600)
		{
			return 7;
		}
		else
		{
			return 6;
		}
	}
	
	public float getBerserkAttackSpeed(int weight)
	{
		if (weight == 0)
		{
			return -2.3F;
		}
		else if (weight <= 200)
		{
			return -2.7F;
		}
		else if (weight <= 600)
		{
			return -3.1F;
		}
		else
		{
			return -3.4F;
		}
	}
	
	protected void updateBerserkAttributes()
	{
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double)getBerserkAttackDamage(this.weight), AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double)getBerserkAttackSpeed(this.weight), AttributeModifier.Operation.ADDITION));
	    builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_MODIFIER, "Weapon modifier", 0.05D, AttributeModifier.Operation.MULTIPLY_TOTAL));
	    this.berserkAxeAttributes = builder.build();
	}
	
	protected void increaseWeight()
	{
		if (weight <= 200)
		{
			weight += 80;
		}
		else if (weight <= 2400)
		{
			weight += 160;
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		if (weight <= 600)
		{
			this.weight = 200;
			this.updateBerserkAttributes();
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		else
		{
			return ActionResult.resultPass(playerIn.getHeldItem(handIn));
		}
    }
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		
		this.increaseWeight();
		this.updateBerserkAttributes();
		return super.hitEntity(stack, target, attacker);
	}
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		return equipmentSlot == EquipmentSlotType.MAINHAND ? berserkAxeAttributes : super.getAttributeModifiers(equipmentSlot);
	}
	
	@Override
	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player)
	{
		return !player.isCreative();
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (this.weight > 0)
		{
			this.weight--;
		}
		if (weight == 0 || weight == 200 || weight == 600)
		{
			this.updateBerserkAttributes();
		}
	}
}
