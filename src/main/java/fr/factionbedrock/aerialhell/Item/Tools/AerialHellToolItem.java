package fr.factionbedrock.aerialhell.Item.Tools;

import com.mojang.datafixers.util.Pair;
import fr.factionbedrock.aerialhell.Item.WithInformationItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

// Inspired of vanilla AxeItem, HoeItem and ShovelItem, but only takes Item.Properties as constructor parameter.
// AxeItem, HoeItem and ShovelItem interaction abilities are all managed.
// Unlike vanilla, this class does not call properties.sword(...), properties.pickaxe(...), properties.axe(...), properties.hoe(...), properties.shovel(...) internally.
// To behave like a tool, the properties must call one of them before passing the properties to the constructor.
public class AerialHellToolItem extends WithInformationItem
{
	@Nullable public final ToolAbility toolAbility;
	public final List<UseInteractionToolType> useInteractionToolTypes;

	public AerialHellToolItem(Properties properties) {this(properties, null, List.of());}
	public AerialHellToolItem(Properties properties, ToolAbility toolAbility) {this(properties, toolAbility, List.of());}
	public AerialHellToolItem(Properties properties, List<UseInteractionToolType> useInteractionToolTypes) {this(properties, null, useInteractionToolTypes);}
	public AerialHellToolItem(Properties properties, @Nullable ToolAbility toolAbility, List<UseInteractionToolType> useInteractionToolTypes)
	{
		super(properties);
		this.toolAbility = toolAbility;
		this.useInteractionToolTypes = useInteractionToolTypes;
	}

	//applying tick (passive) tool ability modules
	@Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (this.toolAbility != null && entity instanceof LivingEntity livingEntity) {this.toolAbility.tryApplyPassiveModules(livingEntity, stack, slot);}
	}

	//applying use tool ability modules
	@Override public InteractionResult use(Level level, Player player, InteractionHand hand)
	{
		ItemStack heldItemStack = player.getItemInHand(hand);
		boolean used = false;
		if (this.toolAbility != null) {used = this.toolAbility.tryApplyOnUseModules(player, heldItemStack, hand.asEquipmentSlot());}
		return used ? InteractionResult.CONSUME : super.use(level, player, hand);
	}

	//applying hurtEnemy (semi-passive) tool ability modules
	@Override public void hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
	{
		if (this.toolAbility != null) {this.toolAbility.tryApplyOnHurtEnemyModules(attacker, itemStack, null);}
		super.hurtEnemy(itemStack, target, attacker);
	}

	//inspired of vanilla ShovelItem, AxeItem and HoeItem
	@Override public InteractionResult useOn(UseOnContext context)
	{
		InteractionResult result = InteractionResult.PASS;
		if (this.useInteractionToolTypes.contains(UseInteractionToolType.AXE))
		{
			result = this.useAxeOn(context);
			if (result != InteractionResult.PASS) {return result;}
		}

		if (this.useInteractionToolTypes.contains(UseInteractionToolType.HOE))
		{
			result = this.useHoeOn(context);
			if (result != InteractionResult.PASS) {return result;}
		}

		if (this.useInteractionToolTypes.contains(UseInteractionToolType.SHOVEL))
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
	@Override public boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility)
	{
		boolean canPerformAxeAction = this.useInteractionToolTypes.contains(UseInteractionToolType.AXE) && ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility);
		boolean canPerformHoeAction = this.useInteractionToolTypes.contains(UseInteractionToolType.HOE) && ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
		boolean canPerformShovelAction = this.useInteractionToolTypes.contains(UseInteractionToolType.SHOVEL) && ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);

		return canPerformAxeAction || canPerformHoeAction || canPerformShovelAction;
	}

	//tool types that can be used with right click
	public enum UseInteractionToolType{AXE, HOE, SHOVEL}
}
