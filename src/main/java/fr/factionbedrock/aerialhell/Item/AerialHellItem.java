package fr.factionbedrock.aerialhell.Item;

import com.mojang.datafixers.util.Pair;
import fr.factionbedrock.aerialhell.Item.Ability.AbilitySelector;
import fr.factionbedrock.aerialhell.Item.Ability.AbilityUseSituation;
import fr.factionbedrock.aerialhell.Item.Ability.DamageUseSituationInfo;
import fr.factionbedrock.aerialhell.Item.Ability.MiningUseSituationInfo;
import fr.factionbedrock.aerialhell.Item.Material.AerialHellArmorMaterial;
import fr.factionbedrock.aerialhell.Item.Material.AerialHellToolMaterial;
import fr.factionbedrock.aerialhell.Item.Material.AttributeEntry;
import fr.factionbedrock.aerialhell.Item.Material.AttributeEntryList;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

// For Items with AerialHellToolMaterials : inspired of vanilla Item class, with custom AerialhellItem.Properties properties
// For Tools : Inspired of vanilla AxeItem, HoeItem and ShovelItem, but only takes Item.Properties as constructor parameter.
// AxeItem, HoeItem and ShovelItem interaction abilities are all managed.
// To make the item behave like a tool, call properties.sword(...), properties.pickaxe(...), properties.axe(...), properties.hoe(...), properties.shovel(...) in properties before passing them to the constructor.
// To manage AxeItem, HoeItem and ShovelItem interaction abilities, think about calling .useInteraction(...) if you want your tool to be able to strip, flatten or till.
public class AerialHellItem extends WithInformationItem
{
	@Nullable public final AbilitySelector abilitySelector;
	public final List<UseInteractionType> useInteractionToolTypes;

	public AerialHellItem(AerialHellItem.Properties properties)
	{
		super(properties);
		this.abilitySelector = properties.abilitySelector;
		this.useInteractionToolTypes = properties.useInteractionTypes;
	}

	//applying tick (passive) tool ability modules
	@Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (this.abilitySelector != null && entity instanceof LivingEntity itemOwner && entity.tickCount % 10 == 0) {this.abilitySelector.tryUseAbility(new AbilityUseSituation.Tick(stack, itemOwner, slot));}
	}

	//applying use tool ability modules
	@Override public InteractionResult use(Level level, Player player, InteractionHand hand)
	{
		ItemStack heldItemStack = player.getItemInHand(hand);
		boolean used = false;
		if (this.abilitySelector != null) {used = this.abilitySelector.tryUseAbility(new AbilityUseSituation.OnUse(heldItemStack, player, hand.asEquipmentSlot()));}
		return used ? InteractionResult.CONSUME : super.use(level, player, hand);
	}

	//applying onDealDamage (semi-passive) tool ability modules
	//enemy entity (stored in damageInfo) is taking damage from item owner
	public void onDealDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot slot, DamageUseSituationInfo damageInfo)
	{
		if (this.abilitySelector != null) {this.abilitySelector.tryUseAbility(new AbilityUseSituation.OnDealDamage(itemStack, itemOwner, slot, damageInfo));}
	}

	//applying onTakeDamage (semi-passive) tool ability modules
	//item owner is taking damage from enemy attacker (stored in damageInfo)
	public void onTakeDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot slot, DamageUseSituationInfo damageInfo)
	{
		if (this.abilitySelector != null) {this.abilitySelector.tryUseAbility(new AbilityUseSituation.OnTakeDamage(itemStack, itemOwner, slot, damageInfo));}
	}

	//applying onMining (semi-passive) tool ability modules
	public void onMining(ItemStack itemStack, LivingEntity itemOwner, MiningUseSituationInfo miningInfo)
	{
		if (this.abilitySelector != null) {this.abilitySelector.tryUseAbility(new AbilityUseSituation.OnMining(itemStack, itemOwner, miningInfo));}
	}

	@Override public void appendAbilityDescriptionHoverText(Item.TooltipContext context, Consumer<Component> tooltipAdder)
	{
		if (this.abilitySelector == null) {return;}
		for (String descId : this.abilitySelector.getAbilitiesDescIds())
		{
			if (descId.isEmpty()) {continue;}
			this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc", ChatFormatting.GRAY);
			this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_2", ChatFormatting.GRAY);
			this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_3", ChatFormatting.GRAY);
			this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_4", ChatFormatting.GRAY);
			this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_5", ChatFormatting.GRAY);
			this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_6", ChatFormatting.GRAY);
			this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".condition.desc", ChatFormatting.GRAY);
			this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".cooldown.desc", ChatFormatting.GRAY);
		}
	}

	//inspired of vanilla ShovelItem, AxeItem and HoeItem
	@Override public InteractionResult useOn(UseOnContext context)
	{
		InteractionResult result = InteractionResult.PASS;
		if (this.useInteractionToolTypes.contains(UseInteractionType.AXE))
		{
			result = this.useAxeOn(context);
			if (result != InteractionResult.PASS) {return result;}
		}

		if (this.useInteractionToolTypes.contains(UseInteractionType.HOE))
		{
			result = this.useHoeOn(context);
			if (result != InteractionResult.PASS) {return result;}
		}

		if (this.useInteractionToolTypes.contains(UseInteractionType.SHOVEL))
		{
			result = this.useShovelOn(context);
			if (result != InteractionResult.PASS) {return result;}
		}

		return result;
	}

	//AxeItem useOn
	public InteractionResult useAxeOn(UseOnContext context)
	{
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Player player = context.getPlayer();
		if (playerHasBlockingItemUseIntent(context)) {return InteractionResult.PASS;}
		else
		{
			Optional<BlockState> newBlock = this.axeEvaluateNewBlockState(level, pos, player, level.getBlockState(pos), context);
			if (newBlock.isEmpty()) {return InteractionResult.PASS;}
			else
			{
				ItemStack itemInHand = context.getItemInHand();
				if (player instanceof ServerPlayer) {CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, itemInHand);}

				level.setBlock(pos, newBlock.get(), 11);
				level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newBlock.get()));
				if (player != null) {itemInHand.hurtAndBreak(1, player, context.getHand().asEquipmentSlot());}

				return InteractionResult.SUCCESS;
			}
		}
	}

	//HoeItem useOn
	public InteractionResult useHoeOn(UseOnContext context)
	{
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState toolModifiedState = level.getBlockState(pos).getToolModifiedState(context, ItemAbilities.HOE_TILL, false);
		Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> logicPair = toolModifiedState == null ? null : Pair.of((Predicate)(ctx) -> true, hoeTillChangeIntoState(toolModifiedState));
		if (logicPair == null) {return InteractionResult.PASS;}
		else
		{
			Predicate<UseOnContext> predicate = logicPair.getFirst();
			Consumer<UseOnContext> action = logicPair.getSecond();
			if (predicate.test(context))
			{
				Player player = context.getPlayer();
				level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				if (!level.isClientSide())
				{
					action.accept(context);
					if (player != null) {context.getItemInHand().hurtAndBreak(1, player, context.getHand().asEquipmentSlot());}
				}
				return InteractionResult.SUCCESS;
			}
			else {return InteractionResult.PASS;}
		}
	}

	//ShovelItem useOn
	public InteractionResult useShovelOn(UseOnContext context)
	{
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState blockState = level.getBlockState(pos);
		Player player = context.getPlayer();
		if (context.getClickedFace() == Direction.DOWN) {return InteractionResult.PASS;}
		else
		{
			BlockState newState = blockState.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
			BlockState updatedState = null;
			if (newState != null && level.getBlockState(pos.above()).isAir())
			{
				level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
				updatedState = newState;
			}
			else if ((updatedState = blockState.getToolModifiedState(context, ItemAbilities.SHOVEL_DOUSE, false)) != null && !level.isClientSide())
			{
				level.levelEvent((Entity)null, 1009, pos, 0);
			}

			if (updatedState != null)
			{
				if (!level.isClientSide())
				{
					level.setBlock(pos, updatedState, 11);
					level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, updatedState));
					if (player != null) {context.getItemInHand().hurtAndBreak(1, player, context.getHand().asEquipmentSlot());}
				}

				return InteractionResult.SUCCESS;
			}
			else {return InteractionResult.PASS;}
		}
	}

	//copy of AxeItem method of same name
	private static boolean playerHasBlockingItemUseIntent(UseOnContext context)
	{
		Player player = context.getPlayer();
		return context.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().has(DataComponents.BLOCKS_ATTACKS) && !player.isSecondaryUseActive();
	}

	//copy of AxeItem evaluateNewBlockState method
	private Optional<BlockState> axeEvaluateNewBlockState(Level level, BlockPos pos, @Nullable Player player, BlockState oldState, UseOnContext context)
	{
		Optional<BlockState> strippedBlock = Optional.ofNullable(oldState.getToolModifiedState(context, ItemAbilities.AXE_STRIP, false));
		if (strippedBlock.isPresent())
		{
			level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
			return strippedBlock;
		}
		else
		{
			Optional<BlockState> scrapedBlock = Optional.ofNullable(oldState.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false));
			if (scrapedBlock.isPresent())
			{
				axeSpawnSoundAndParticle(level, pos, player, oldState, SoundEvents.AXE_SCRAPE, 3005);
				return scrapedBlock;
			}
			else
			{
				Optional<BlockState> waxoffBlock = Optional.ofNullable(oldState.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false));
				if (waxoffBlock.isPresent())
				{
					axeSpawnSoundAndParticle(level, pos, player, oldState, SoundEvents.AXE_WAX_OFF, 3004);
					return waxoffBlock;
				}
				else {return Optional.empty();}
			}
		}
	}

	//copy of AxeItem spawnSoundAndParticle method
	private static void axeSpawnSoundAndParticle(Level level, BlockPos pos, @Nullable Player player, BlockState oldState, SoundEvent soundEvent, int particle)
	{
		level.playSound(player, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
		level.levelEvent(player, particle, pos, 0);
		if (oldState.getBlock() instanceof ChestBlock && oldState.getValue(ChestBlock.TYPE) != ChestType.SINGLE)
		{
			BlockPos neighborPos = ChestBlock.getConnectedBlockPos(pos, oldState);
			level.gameEvent(GameEvent.BLOCK_CHANGE, neighborPos, GameEvent.Context.of(player, level.getBlockState(neighborPos)));
			level.levelEvent(player, particle, neighborPos, 0);
		}
	}

	//copy of HoeItem changeIntoState method
	public static Consumer<UseOnContext> hoeTillChangeIntoState(BlockState state)
	{
		return (context) ->
		{
			context.getLevel().setBlock(context.getClickedPos(), state, 11);
			context.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, context.getClickedPos(), GameEvent.Context.of(context.getPlayer(), state));
		};
	}

	//inspired of AxeItem, HoeItem and ShovelItem methods of same name
	@Override public boolean canPerformAction(ItemInstance stack, net.neoforged.neoforge.common.ItemAbility itemAbility)
	{
		boolean canPerformAxeAction = this.useInteractionToolTypes.contains(UseInteractionType.AXE) && ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility);
		boolean canPerformHoeAction = this.useInteractionToolTypes.contains(UseInteractionType.HOE) && ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
		boolean canPerformShovelAction = this.useInteractionToolTypes.contains(UseInteractionType.SHOVEL) && ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);

		return canPerformAxeAction || canPerformHoeAction || canPerformShovelAction;
	}

	public static class Properties extends Item.Properties
	{
		@Nullable private AbilitySelector abilitySelector;
		private List<AerialHellItem.UseInteractionType> useInteractionTypes;
		public Properties() {super(); this.useInteractionTypes = new ArrayList<>();}

		public AerialHellItem.Properties humanoidArmor(AerialHellArmorMaterial material, ArmorType type) {return this.humanoidArmor(material, type, new AttributeEntryList());}
		public AerialHellItem.Properties humanoidArmor(AerialHellArmorMaterial material, ArmorType type, AttributeEntry attributeEntry) {return this.humanoidArmor(material, type, new AttributeEntryList().add(attributeEntry));}
		public AerialHellItem.Properties humanoidArmor(AerialHellArmorMaterial material, ArmorType type, AttributeEntryList additionalAttributes)
		{
			return (AerialHellItem.Properties) this.durability(type.getDurability(material.durability())).attributes(material.createAttributes(type, additionalAttributes)).enchantable(material.enchantmentValue()).component(DataComponents.EQUIPPABLE, Equippable.builder(type.getSlot()).setEquipSound(material.equipSound()).setAsset(material.assetId()).build()).repairable(material.repairIngredient());
		}

		public AerialHellItem.Properties tool(AerialHellToolMaterial material, TagKey<Block> minesEfficiently, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes, float disableBlockingSeconds)
		{
			return material.applyToolProperties(this, minesEfficiently, attackDamage, attackSpeed, additionalAttributes, disableBlockingSeconds);
		}

		public AerialHellItem.Properties pickaxe(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.pickaxe(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public AerialHellItem.Properties pickaxe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.pickaxe(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public AerialHellItem.Properties pickaxe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			return this.tool(material, BlockTags.MINEABLE_WITH_PICKAXE, attackDamage, attackSpeed, additionalAttributes, 0.0F);
		}

		public AerialHellItem.Properties axe(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.axe(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public AerialHellItem.Properties axe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.axe(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public AerialHellItem.Properties axe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			return this.tool(material, BlockTags.MINEABLE_WITH_AXE, attackDamage, attackSpeed, additionalAttributes, 5.0F);
		}

		public AerialHellItem.Properties hoe(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.hoe(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public AerialHellItem.Properties hoe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.hoe(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public AerialHellItem.Properties hoe(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			return this.tool(material, BlockTags.MINEABLE_WITH_HOE, attackDamage, attackSpeed, additionalAttributes, 0.0F);
		}

		public AerialHellItem.Properties shovel(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.shovel(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public AerialHellItem.Properties shovel(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.shovel(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public AerialHellItem.Properties shovel(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			return this.tool(material, BlockTags.MINEABLE_WITH_SHOVEL, attackDamage, attackSpeed, additionalAttributes, 0.0F);
		}

		public AerialHellItem.Properties sword(AerialHellToolMaterial material, float attackDamage, float attackSpeed) {return this.sword(material, attackDamage, attackSpeed, new AttributeEntryList());}
		public AerialHellItem.Properties sword(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntry attributeEntry) {return this.sword(material, attackDamage, attackSpeed, new AttributeEntryList().add(attributeEntry));}
		public AerialHellItem.Properties sword(AerialHellToolMaterial material, float attackDamage, float attackSpeed, AttributeEntryList additionalAttributes)
		{
			return material.applySwordProperties(this, attackDamage, attackSpeed, additionalAttributes);
		}

		public AerialHellItem.Properties abilitySelector(AbilitySelector abilitySelector) {this.abilitySelector = abilitySelector; return this;}

		public AerialHellItem.Properties useInteraction(AerialHellItem.UseInteractionType useInteractionType) {this.useInteractionTypes = new ArrayList<>(); this.useInteractionTypes.add(useInteractionType); return this;}

		public AerialHellItem.Properties useInteractions(AerialHellItem.UseInteractionType... useInteractionTypes) {this.useInteractionTypes = new ArrayList<>(List.of(useInteractionTypes)); return this;}

		@Override public AerialHellItem.Properties setId(ResourceKey<Item> id) {return (AerialHellItem.Properties) super.setId(id);}

		@Override public AerialHellItem.Properties rarity(Rarity rarity) {return (AerialHellItem.Properties) super.rarity(rarity);}

		@Override public AerialHellItem.Properties durability(int maxDamage) {return (AerialHellItem.Properties) super.durability(maxDamage);}

		@Override public AerialHellItem.Properties fireResistant() {return (AerialHellItem.Properties) super.fireResistant();}

		@Override public AerialHellItem.Properties trimMaterial(ResourceKey<TrimMaterial> material) {return (AerialHellItem.Properties) this.delayedHolderComponent(DataComponents.PROVIDES_TRIM_MATERIAL, material);}

		@Override public AerialHellItem.Properties stacksTo(int max) {return (AerialHellItem.Properties) super.stacksTo(max);}

		@Override public <T> AerialHellItem.Properties component(DataComponentType<T> type, T value) {return (AerialHellItem.Properties) super.component(type, value);}
	}

	//"tool types" that can be used with right click
	public enum UseInteractionType {AXE, HOE, SHOVEL}
}
