package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public class CoreProtectedStairsBlock extends StairBlock implements CoreProtectedPropertyBlock
{
	public CoreProtectedStairsBlock(BlockState state, BlockBehaviour.Properties settings)
	{
		super(state, settings);
		this.registerDefaultState(this.stateDefinition.any().setValue(CORE_PROTECTED, false));
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

	public Block getCrackedVariant()
	{
		if (this == AerialHellBlocks.MUD_BRICKS_STAIRS) {return AerialHellBlocks.CRACKED_MUD_BRICKS_STAIRS;}
		else if (this == AerialHellBlocks.LUNATIC_STONE_STAIRS) {return AerialHellBlocks.CRACKED_LUNATIC_STONE_STAIRS;}
		else if (this == AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_STAIRS) {return AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS;}
		else if (this == AerialHellBlocks.GOLDEN_NETHER_BRICKS_STAIRS) {return AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS;}
		else if (this == AerialHellBlocks.VOLUCITE_STONE_STAIRS) {return AerialHellBlocks.CRACKED_VOLUCITE_STONE_STAIRS;}
		else {return this;}
	}
}

