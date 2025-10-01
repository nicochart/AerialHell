package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

import static fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties.CORE_PROTECTED;

public class CoreProtectedWallBlock extends WallBlock //implements CoreProtectedPropertyBlock
{
	//Problem with .isOpaque()
	
	public CoreProtectedWallBlock(Properties properties)
	{
		super(properties);
		//this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}

	/*@Override public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
    {
        return this.getModifiedExplosionResistance(state, world, pos, explosion);
    }

	@Override public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos)
	{
		return this.getModifiedDestroyProgress(state, player, level, pos);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(CORE_PROTECTED);
		super.createBlockStateDefinition(builder);
	}*/

	public WallBlock getCrackedVariant()
	{
		if (this == AerialHellBlocks.MUD_BRICKS_WALL.get()) {return AerialHellBlocks.CRACKED_MUD_BRICKS_WALL.get();}
		else if (this == AerialHellBlocks.LUNATIC_STONE_WALL.get()) {return AerialHellBlocks.CRACKED_LUNATIC_STONE_WALL.get();}
		else if (this == AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_WALL.get()) {return AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.get();}
		else if (this == AerialHellBlocks.GOLDEN_NETHER_BRICKS_WALL.get()) {return AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_WALL.get();}
		else if (this == AerialHellBlocks.VOLUCITE_STONE_WALL.get()) {return AerialHellBlocks.CRACKED_VOLUCITE_STONE_WALL.get();}
		else {return this;}
	}
}