package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Inventory.Menu.AerialHellCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AerialHellCraftingTableBlock extends CraftingTableBlock
{
	private static final Component CONTAINER_NAME = Component.translatable("container.crafting");;

	public AerialHellCraftingTableBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
	}

	@Override protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
	{
		if (world.isClientSide()) {return InteractionResult.SUCCESS;}
		else
		{
			player.openMenu(state.getMenuProvider(world, pos));
			player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
			return InteractionResult.CONSUME;
		}
	}

	@Override
	protected MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos)
	{
		return new SimpleMenuProvider((syncId, inventory, player) -> new AerialHellCraftingMenu(syncId, inventory, ContainerLevelAccess.create(world, pos), this), CONTAINER_NAME);
	}
}