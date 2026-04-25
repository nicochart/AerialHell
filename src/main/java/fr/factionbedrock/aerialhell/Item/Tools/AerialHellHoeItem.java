package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.function.Consumer;
import java.util.function.Predicate;

import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

// Copy of vanilla HoeItem, but only takes Item.Properties as constructor parameter.
// Unlike vanilla, this class does not call properties.hoe(...) internally.
// To behave like an hoe, the properties must have hoe(...) called on them before being passed to the constructor.
public class AerialHellHoeItem extends Item
{
	public AerialHellHoeItem(Properties properties) {super(properties);}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		tooltipAdder.accept(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	public MutableComponent getDescription() {return Component.translatable(this.getDescriptionId() + ".desc");}

	//copy of vanilla HoeItem with no modification
	@Override public InteractionResult useOn(UseOnContext context)
	{
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState toolModifiedState = level.getBlockState(pos).getToolModifiedState(context, ItemAbilities.HOE_TILL, false);
		Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> logicPair = toolModifiedState == null ? null : Pair.of((Predicate)(ctx) -> true, changeIntoState(toolModifiedState));
		if (logicPair == null) {return InteractionResult.PASS;}
		else
		{
			Predicate<UseOnContext> predicate = (Predicate)logicPair.getFirst();
			Consumer<UseOnContext> action = (Consumer)logicPair.getSecond();
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

	public static Consumer<UseOnContext> changeIntoState(BlockState state)
	{
		return (context) ->
		{
			context.getLevel().setBlock(context.getClickedPos(), state, 11);
			context.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, context.getClickedPos(), GameEvent.Context.of(context.getPlayer(), state));
		};
	}

	@Override public boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {return ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);}
}
