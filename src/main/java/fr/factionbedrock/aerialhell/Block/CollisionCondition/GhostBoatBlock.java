package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GhostBoatBlock extends CollisionConditionHalfTransparentBlock
{
	public GhostBoatBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
	}

	@Override public void livingEntityInside(BlockState state, Level world, BlockPos pos, LivingEntity entity)
	{
		if (!EntityHelper.isFeatheryEntity(entity)) {super.livingEntityInside(state, world, pos, entity);}
	}

	@Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToGhostBlockCollision(entity);}
	@Override protected VoxelShape getCollidingShape() {return FULL_COLLISION_SHAPE;}

	@Override protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side)
	{
		if (state.is(AerialHellBlocks.GHOST_BOAT_PLANKS) && adjacentBlockState.is(AerialHellBlocks.GHOST_BOAT_SLAB)) {return adjacentBlockState.getValue(SlabBlock.TYPE) == SlabType.DOUBLE;}
		else {return super.skipRendering(state, adjacentBlockState, side);}
	}
}