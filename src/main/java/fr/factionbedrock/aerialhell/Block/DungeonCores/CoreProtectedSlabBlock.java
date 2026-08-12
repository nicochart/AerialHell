package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import static fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBlock.CORE_PROTECTED;

public class CoreProtectedSlabBlock extends SlabBlock
{
	public CoreProtectedSlabBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.registerDefaultState(this.stateDefinition.any().setValue(CORE_PROTECTED, false));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.getValue(CORE_PROTECTED);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter world, net.minecraft.core.BlockPos pos)
	{
		float f = state.getDestroySpeed(world, pos);
		if (f == -1.0F || isProtected(state))
		{
			return 0.0F;
		}
		else
		{
			int i = player.hasCorrectToolForDrops(state) ? 30 : 100;
			return player.getDestroySpeed(state) / f / (float)i;
		}
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