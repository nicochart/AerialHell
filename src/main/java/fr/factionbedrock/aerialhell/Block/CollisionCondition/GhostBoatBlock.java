package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

public class GhostBoatBlock extends CollisionConditionHalfTransparentBlock
{
	public GhostBoatBlock(AbstractBlock.Settings settings)
	{
		super(settings);
	}

	@Override public void livingEntityInside(BlockState state, World world, BlockPos pos, LivingEntity entity)
	{
		if (!EntityHelper.isFeatheryEntity(entity)) {super.livingEntityInside(state, world, pos, entity);}
	}

	@Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToGhostBlockCollision(entity);}
	@Override protected VoxelShape getCollidingShape() {return FULL_COLLISION_SHAPE;}

	@Override protected boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side)
	{
		if (state.isOf(AerialHellBlocks.GHOST_BOAT_PLANKS) && adjacentBlockState.isOf(AerialHellBlocks.GHOST_BOAT_SLAB)) {return adjacentBlockState.get(SlabBlock.TYPE) == SlabType.DOUBLE;}
		else {return super.isSideInvisible(state, adjacentBlockState, side);}
	}
}