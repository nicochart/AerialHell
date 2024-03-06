package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GhostBoatRotatedPillarBlock extends CollisionConditionHalfTransparentBlock
{
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public GhostBoatRotatedPillarBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y));
    }

    public BlockState rotate(BlockState state, Rotation rotation) {return RotatedPillarBlock.rotatePillar(state, rotation);}

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AXIS);}

    public BlockState getStateForPlacement(BlockPlaceContext context) {return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis());}

    @Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToGhostBlockCollision(entity);}
    @Override protected VoxelShape getCollidingShape() {return GhostBoatBlock.FULL_COLLISION_SHAPE;}

}
