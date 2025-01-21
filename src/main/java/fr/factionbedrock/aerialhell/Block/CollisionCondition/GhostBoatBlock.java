package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GhostBoatBlock extends CollisionConditionHalfTransparentBlock
{
	public GhostBoatBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override public void livingEntityInside(BlockState state, Level level, BlockPos pos, LivingEntity entity)
	{
		if (!EntityHelper.isFeatheryEntity(entity)) {super.livingEntityInside(state, level, pos, entity);}
	}

	@Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToGhostBlockCollision(entity);}
	@Override protected VoxelShape getCollidingShape() {return FULL_COLLISION_SHAPE;}

	@Override public boolean skipRendering(BlockState state1, BlockState state2, Direction direction)
	{
		if (state1.is(AerialHellBlocks.GHOST_BOAT_PLANKS.get()) && state2.is(AerialHellBlocks.GHOST_BOAT_SLAB.get())) {return state2.getValue(SlabBlock.TYPE) == SlabType.DOUBLE;}
		else {return super.skipRendering(state1, state2, direction);}
	}
}