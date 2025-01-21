package fr.factionbedrock.aerialhell.Block.DungeonCores;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;

public class CoreProtectedStairsBlock extends StairBlock
{
	public static final BooleanProperty CORE_PROTECTED = BooleanProperty.create("core_protected");
	
	public CoreProtectedStairsBlock(BlockState state, BlockBehaviour.Properties properties)
	{
		super(state, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(CORE_PROTECTED, false));
	}
	
	public void setProtected(boolean protect)
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
		super.createBlockStateDefinition(builder);
		builder.add(CORE_PROTECTED);
	}

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos)
	{
		float f = state.getDestroySpeed(level, pos);
		if (f == -1.0F || isProtected(state))
		{
			return 0.0F;
		}
		else
		{
			int i = net.neoforged.neoforge.event.EventHooks.doPlayerHarvestCheck(player, state, level, pos) ? 30 : 100;
			return player.getDestroySpeed(state, pos) / f / (float)i;
		}
	}

	public Block getCrackedVariant()
	{
		if (this == AerialHellBlocks.MUD_BRICKS_STAIRS.get()) {return AerialHellBlocks.CRACKED_MUD_BRICKS_STAIRS.get();}
		else if (this == AerialHellBlocks.LUNATIC_STONE_STAIRS.get()) {return AerialHellBlocks.CRACKED_LUNATIC_STONE_STAIRS.get();}
		else if (this == AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_STAIRS.get()) {return AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.get();}
		else if (this == AerialHellBlocks.GOLDEN_NETHER_BRICKS_STAIRS.get()) {return AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.get();}
		else if (this == AerialHellBlocks.VOLUCITE_STONE_STAIRS.get()) {return AerialHellBlocks.CRACKED_VOLUCITE_STONE_STAIRS.get();}
		else {return this;}
	}
}

