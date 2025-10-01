package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import static fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties.CORE_PROTECTED;

public class CoreProtectedBlock extends Block implements CoreProtectedPropertyBlock
{
	public CoreProtectedBlock(AbstractBlock.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(CORE_PROTECTED, false));
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(CORE_PROTECTED);}
	
	@Override public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, world, pos);
	}

	public Block getCrackedVariant()
	{
		if (this == AerialHellBlocks.MUD_BRICKS) {return AerialHellBlocks.CRACKED_MUD_BRICKS;}
		else if (this == AerialHellBlocks.LIGHT_MUD_BRICKS) {return AerialHellBlocks.CRACKED_LIGHT_MUD_BRICKS;}
		else if (this == AerialHellBlocks.LUNATIC_STONE) {return AerialHellBlocks.CRACKED_LUNATIC_STONE;}
		else if (this == AerialHellBlocks.LIGHT_LUNATIC_STONE) {return AerialHellBlocks.CRACKED_LIGHT_LUNATIC_STONE;}
		else if (this == AerialHellBlocks.SHADOW_CATACOMBS_BRICKS) {return AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS;}
		else if (this == AerialHellBlocks.LIGHT_SHADOW_CATACOMBS_BRICKS) {return AerialHellBlocks.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS;}
		else if (this == AerialHellBlocks.GOLDEN_NETHER_BRICKS) {return AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS;}
		else if (this == AerialHellBlocks.LIGHT_GOLDEN_NETHER_BRICKS) {return AerialHellBlocks.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS;}
		else if (this == AerialHellBlocks.VOLUCITE_STONE) {return AerialHellBlocks.CRACKED_VOLUCITE_STONE;}
		else if (this == AerialHellBlocks.LIGHT_VOLUCITE_STONE) {return AerialHellBlocks.CRACKED_LIGHT_VOLUCITE_STONE;}
		else {return this;}
	}
}
