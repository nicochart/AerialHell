package fr.factionbedrock.aerialhell.Block.CollisionCondition;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public class GhostBoatRotatedPillarBlock extends CollisionConditionHalfTransparentBlock
{
    public static final MapCodec<GhostBoatRotatedPillarBlock> CODEC = createCodec(GhostBoatRotatedPillarBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public GhostBoatRotatedPillarBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y));
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {return PillarBlock.changeRotation(state, rotation);}

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(AXIS);}

    public BlockState getPlacementState(ItemPlacementContext ctx) {return this.getDefaultState().with(AXIS, ctx.getSide().getAxis());}

    @Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToGhostBlockCollision(entity);}
    @Override protected VoxelShape getCollidingShape() {return GhostBoatBlock.FULL_COLLISION_SHAPE;}

}
