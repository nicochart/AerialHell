package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

public class CoreProtectedWallBlock extends WallBlock
{
	//Problem with .isOpaque()
	//public static final BooleanProperty CORE_PROTECTED = BooleanProperty.create("core_protected");
	
	public CoreProtectedWallBlock(Properties properties)
	{
		super(properties);
		//this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
	}
	
	/*public void setProtected(boolean protect)
	{
		this.registerDefaultState(this.stateDefinition.any().setValue(CORE_PROTECTED, protect));
	}
	
	public boolean isProtected(BlockState state)
	{
		return state.getValue(CORE_PROTECTED);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public float getExplosionResistance(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion)
    {
        return isProtected(state) ? 1200.0F : this.asBlock().getExplosionResistance();
    }
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(CORE_PROTECTED);
		super.createBlockStateDefinition(builder);
	}*/

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos)
	{
		float f = state.getDestroySpeed(level, pos);
		if (f == -1.0F /*|| isProtected(state)*/)
		{
			return 0.0F;
		}
		else
		{
			int i = net.neoforged.neoforge.event.EventHooks.doPlayerHarvestCheck(player, state, level, pos) ? 30 : 100;
			return player.getDigSpeed(state, pos) / f / (float)i;
		}
	}

	public WallBlock getCrackedVariant()
	{
		if (this == AerialHellBlocksAndItems.MUD_BRICKS_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_MUD_BRICKS_WALL.get();}
		else if (this == AerialHellBlocksAndItems.LUNATIC_STONE_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_LUNATIC_STONE_WALL.get();}
		else if (this == AerialHellBlocksAndItems.SHADOW_CATACOMBS_BRICKS_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.get();}
		else if (this == AerialHellBlocksAndItems.GOLDEN_NETHER_BRICKS_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_GOLDEN_NETHER_BRICKS_WALL.get();}
		else if (this == AerialHellBlocksAndItems.VOLUCITE_STONE_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_VOLUCITE_STONE_WALL.get();}
		else {return this;}
	}
}