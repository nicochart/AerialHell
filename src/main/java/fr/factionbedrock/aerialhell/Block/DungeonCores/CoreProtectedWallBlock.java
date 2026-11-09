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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBlock.CORE_PROTECTED;

//explosion resistance and piston behavior of protected states are handled by ExplosionBehaviorMixin and PistonIsPushableMixin
public class CoreProtectedWallBlock extends WallBlock
{
	//In 1.21.1, WallBlock caches its shapes in maps built from defaultBlockState() in its constructor, so these maps only contain states with one CORE_PROTECTED value.
	//Shapes are looked up with this value, otherwise the shape of the states with the other value is null (crash on registry bake).
	private final boolean shapesCoreProtectedValue;

	public CoreProtectedWallBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
		this.shapesCoreProtectedValue = this.defaultBlockState().getValue(CORE_PROTECTED);
		this.registerDefaultState(this.defaultBlockState().setValue(CORE_PROTECTED, false));
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

	@Override protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		return super.getShape(state.setValue(CORE_PROTECTED, this.shapesCoreProtectedValue), level, pos, context);
	}

	@Override protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		return super.getCollisionShape(state.setValue(CORE_PROTECTED, this.shapesCoreProtectedValue), level, pos, context);
	}

	@Override
	public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
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
