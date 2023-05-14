package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Inventory.Menu.AerialHellCraftingMenu;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class AerialHellCraftingTableBlock extends CraftingTableBlock
{
	private static final Component CONTAINER_NAME = new TranslatableComponent("container.crafting");

	public AerialHellCraftingTableBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
	{
		if (worldIn.isClientSide())
		{
			return InteractionResult.SUCCESS;
		}
		else
		{
			player.openMenu(state.getMenuProvider(worldIn, pos));
			player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
			return InteractionResult.CONSUME;
		}
	}

	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos)
	{
		return new SimpleMenuProvider((id, inventory, player) -> {return new AerialHellCraftingMenu(id, inventory, ContainerLevelAccess.create(worldIn, pos), this);}, CONTAINER_NAME);
	}
}