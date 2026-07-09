package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CoreProtectedWallBlock extends WallBlock
{
	//Problem with .isOpaque()
	//public static final BooleanProperty CORE_PROTECTED = BooleanProperty.of("core_protected");
	
	public CoreProtectedWallBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		//this.setDefaultState(this.getDefaultState().with(CORE_PROTECTED, false));
	}
	
	/*public void setProtected(boolean protect)
	{
		this.setDefaultState(this.stateManager.getDefaultState().setValue(CORE_PROTECTED, protect));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.get(CORE_PROTECTED);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? 1200.0F : this.asBlock().getExplosionResistance();
    }
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		builder.add(CORE_PROTECTED);
		super.createBlockStateDefinition(builder);
	}*/

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter world, net.minecraft.core.BlockPos pos)
	{
		float f = state.getDestroySpeed(world, pos);
		if (f == -1.0F /*|| isProtected(state)*/)
		{
			return 0.0F;
		}
		else
		{
			int i = player.hasCorrectToolForDrops(state) ? 30 : 100;
			return player.getDestroySpeed(state) / f / (float)i;
		}
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