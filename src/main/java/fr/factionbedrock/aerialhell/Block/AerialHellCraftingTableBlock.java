package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Inventory.Menu.AerialHellCraftingMenu;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AerialHellCraftingTableBlock extends CraftingTableBlock
{
	private static final Text CONTAINER_NAME = Text.translatable("container.crafting");;

	public AerialHellCraftingTableBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
	{
		if (world.isClient()) {return ActionResult.SUCCESS;}
		else
		{
			player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
			player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
			return ActionResult.CONSUME;
		}
	}

	@Override
	protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos)
	{
		return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new AerialHellCraftingMenu(syncId, inventory, ScreenHandlerContext.create(world, pos), this), CONTAINER_NAME);
	}
}