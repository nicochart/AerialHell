package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Item.Ability.*;
import fr.factionbedrock.aerialhell.Item.Material.AerialHellToolMaterial;
import fr.factionbedrock.aerialhell.Item.Material.AttributeEntry;
import fr.factionbedrock.aerialhell.Item.Material.AttributeEntryList;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

//using interface AerialHellItemInterface instead of this global class
//because for compatibility issues (back in 1.21.1)
//some other mods check if item instanceof SwordItem, AxeItem, PickaxeItem...
public class AerialHellItem extends WithInformationItem implements AerialHellItemInterface
{
	public final int maxUseDuration;
	public final int enchantmentValue;
	public final boolean canDisableShield;
	public final Ingredient repairIngredient;
	public final UseAnim itemUseAnimation;
	@Nullable public final AbilitySelector abilitySelector;
	public final List<UseInteractionType> useInteractionToolTypes;

	public AerialHellItem(Properties properties)
	{
		super(properties);
		this.maxUseDuration = properties.maxUseDuration;
		this.enchantmentValue = properties.enchantmentValue;
		this.canDisableShield = properties.canDisableShield;
		this.repairIngredient = properties.repairIngredient;
		this.itemUseAnimation = properties.itemUseAnimation;
		this.abilitySelector = properties.abilitySelector;
		this.useInteractionToolTypes = properties.useInteractionTypes;
	}

	@Override public AbilitySelector abilitySelector() {return this.abilitySelector;}
	@Override public int maxUseDuration() {return this.maxUseDuration;}
	@Override public List<UseInteractionType> useInteractionToolTypes() {return this.useInteractionToolTypes;}
	@Override public UseAnim itemUseAnimation() {return this.itemUseAnimation;}

	//applying tick (passive) tool ability modules
	@Override public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected)
	{
		this.ahInventoryTick(stack, level, entity, slotId, isSelected);
	}

	//applying use tool ability modules
	@Override public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {return this.ahUse(level, player, hand, super::use);}

	@Override public int getUseDuration(ItemStack itemStack, LivingEntity user) {return this.ahGetUseDuration(itemStack, user, super::getUseDuration);}

	@Override public UseAnim getUseAnimation(ItemStack itemStack) {return this.ahGetUseAnimation(itemStack, super::getUseAnimation);}

	//applying releaseUsing tool ability modules
	@Override public void releaseUsing(ItemStack itemStack, Level level, LivingEntity itemOwner, int remainingTime)
	{
		this.ahReleaseUsing(itemStack, level, itemOwner, remainingTime);
	}

	@Override public int getEnchantmentValue() {return this.enchantmentValue;}

	@Override public boolean isValidRepairItem(ItemStack toRepair, ItemStack repairIngredient) {return this.repairIngredient.test(repairIngredient) || super.isValidRepairItem(toRepair, repairIngredient);}

	@Override public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {return this.canDisableShield;}

	//inspired of vanilla ShovelItem, AxeItem and HoeItem
	@Override public InteractionResult useOn(UseOnContext context) {return this.ahUseOn(context);}

	//inspired of AxeItem, HoeItem and ShovelItem methods of same name
	@Override public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {return this.ahCanPerformAction(stack, itemAbility);}

	public static class Properties extends Item.Properties
	{
		private int maxUseDuration;
		private int enchantmentValue;
		private boolean canDisableShield;
		private Ingredient repairIngredient;
		private UseAnim itemUseAnimation;
		@Nullable private AbilitySelector abilitySelector;
		private List<UseInteractionType> useInteractionTypes;
		@Nullable private AerialHellToolMaterial toolMaterial;
		private boolean lockedComponents;
		public Properties() {super(); this.maxUseDuration = 0; this.enchantmentValue = 0; this.canDisableShield = false; this.repairIngredient = Ingredient.of(); this.itemUseAnimation = UseAnim.NONE; this.useInteractionTypes = new ArrayList<>(); this.lockedComponents = false;}

		public int maxUseDuration() {return this.maxUseDuration;}
		public int enchantmentValue() {return this.enchantmentValue;}
		public boolean canDisableShield() {return this.canDisableShield;}
		public Ingredient repairIngredient() {return this.repairIngredient;}
		public UseAnim itemUseAnimation() {return this.itemUseAnimation;}
		@Nullable public AbilitySelector abilitySelector() {return this.abilitySelector;}
		public List<UseInteractionType> useInteractionTypes() {return this.useInteractionTypes;}
		@Nullable public AerialHellToolMaterial toolMaterial() {return this.toolMaterial;}

		public Properties tool(AerialHellToolMaterial material, TagKey<Block> minesEfficiently, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			this.toolMaterial = material;
			return material.applyToolProperties(this, minesEfficiently, attackDamage, attackSpeed, additionalAttributes).lockComponents();
		}

		public Properties pickaxe(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.pickaxe(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public Properties pickaxe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.pickaxe(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public Properties pickaxe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			return this.tool(material, BlockTags.MINEABLE_WITH_PICKAXE, attackDamage, attackSpeed, additionalAttributes);
		}

		public Properties axe(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.axe(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public Properties axe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.axe(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public Properties axe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			this.canDisableShield = true;
			return this.tool(material, BlockTags.MINEABLE_WITH_AXE, attackDamage, attackSpeed, additionalAttributes);
		}

		public Properties hoe(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.hoe(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public Properties hoe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.hoe(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public Properties hoe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			return this.tool(material, BlockTags.MINEABLE_WITH_HOE, attackDamage, attackSpeed, additionalAttributes);
		}

		public Properties shovel(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.shovel(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public Properties shovel(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.shovel(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public Properties shovel(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			return this.tool(material, BlockTags.MINEABLE_WITH_SHOVEL, attackDamage, attackSpeed, additionalAttributes);
		}

		public Properties sword(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.sword(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public Properties sword(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.sword(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public Properties sword(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			this.toolMaterial = material;
			return material.applySwordProperties(this, attackDamage, attackSpeed, additionalAttributes).lockComponents();
		}

		public Properties maxUseDuration(int useDuration) {this.maxUseDuration = useDuration; return this;}

		public Properties useAnimation(UseAnim itemUseAnimation) {this.itemUseAnimation = itemUseAnimation; return this;}

		public Properties abilitySelector(AbilitySelector abilitySelector) {this.abilitySelector = abilitySelector; return this;}

		public Properties useInteraction(UseInteractionType useInteractionType) {this.useInteractionTypes = new ArrayList<>(); this.useInteractionTypes.add(useInteractionType); return this;}

		public Properties useInteractions(UseInteractionType... useInteractionTypes) {this.useInteractionTypes = new ArrayList<>(List.of(useInteractionTypes)); return this;}

		@Override public Properties rarity(Rarity rarity) {return (Properties) super.rarity(rarity);}

		@Override public Properties durability(int maxDamage) {return (Properties) super.durability(maxDamage);}

		@Override public Properties fireResistant() {return (Properties) super.fireResistant();}

		@Override public Properties stacksTo(int max) {return (Properties) super.stacksTo(max);}

		public Properties enchantable(int value) {this.enchantmentValue = value; return this;}

		public Properties repairable(Ingredient ingredient) {this.repairIngredient = ingredient; return this;}

		public Properties lockComponents() {this.lockedComponents = true; return this;}

		@Override public <T> Properties component(DataComponentType<T> componentType, T value)
		{
			if (this.lockedComponents) {return this;} //if components are locked, new components are ignored. Used to avoid classes such as SwordItem to edit components internally (upon calling super)
			else
			{
				return (Properties) super.component(componentType, value);
			}
		}
	}

	//"tool types" that can be used with right click
	public enum UseInteractionType {AXE, HOE, SHOVEL}
}
