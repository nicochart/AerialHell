package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public class CoreProtectedSlabBlock extends SlabBlock implements CoreProtectedPropertyBlock
{
	public CoreProtectedSlabBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(CORE_PROTECTED, false));
	}
	
	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		super.appendProperties(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, world, pos);
	}

	public Block getCrackedVariant()
	{
		if (this == AerialHellBlocks.MUD_BRICKS_SLAB) {return AerialHellBlocks.CRACKED_MUD_BRICKS_SLAB;}
		else if (this == AerialHellBlocks.LUNATIC_STONE_SLAB) {return AerialHellBlocks.CRACKED_LUNATIC_STONE_SLAB;}
		else if (this == AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_SLAB) {return AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB;}
		else if (this == AerialHellBlocks.GOLDEN_NETHER_BRICKS_SLAB) {return AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_SLAB;}
		else if (this == AerialHellBlocks.VOLUCITE_STONE_SLAB) {return AerialHellBlocks.CRACKED_VOLUCITE_STONE_SLAB;}
		else {return this;}
	}
}