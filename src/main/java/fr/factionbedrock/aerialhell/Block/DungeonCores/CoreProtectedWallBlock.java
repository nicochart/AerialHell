package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public class CoreProtectedWallBlock extends WallBlock implements CoreProtectedPropertyBlock
{
	public CoreProtectedWallBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, world, pos);
	}

	public WallBlock getCrackedVariant()
	{
		if (this == AerialHellBlocks.MUD_BRICKS_WALL) {return AerialHellBlocks.CRACKED_MUD_BRICKS_WALL;}
		else if (this == AerialHellBlocks.LUNATIC_STONE_WALL) {return AerialHellBlocks.CRACKED_LUNATIC_STONE_WALL;}
		else if (this == AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_WALL) {return AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL;}
		else if (this == AerialHellBlocks.GOLDEN_NETHER_BRICKS_WALL) {return AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_WALL;}
		else if (this == AerialHellBlocks.VOLUCITE_STONE_WALL) {return AerialHellBlocks.CRACKED_VOLUCITE_STONE_WALL;}
		else {return this;}
	}
}
