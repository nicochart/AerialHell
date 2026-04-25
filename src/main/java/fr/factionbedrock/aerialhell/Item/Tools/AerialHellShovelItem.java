package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
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

// Copy of vanilla ShovelItem, but only takes Item.Properties as constructor parameter.
// Unlike vanilla, this class does not call properties.shovel(...) internally.
// To behave like an shovel, the properties must have shovel(...) called on them before being passed to the constructor.
public class AerialHellShovelItem extends Item
{
	public AerialHellShovelItem(Properties properties) {super(properties);}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		tooltipAdder.accept(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	public MutableComponent getDescription() {return Component.translatable(this.getDescriptionId() + ".desc");}

	//copy of vanilla ShovelItem with no modification
	@Override public InteractionResult useOn(UseOnContext context)
	{
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState blockState = level.getBlockState(pos);
		if (context.getClickedFace() == Direction.DOWN) {return InteractionResult.PASS;}
		else
		{
			Player player = context.getPlayer();
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

	@Override public boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {return ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);}
}
