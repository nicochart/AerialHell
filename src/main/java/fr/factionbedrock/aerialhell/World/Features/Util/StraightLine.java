package fr.factionbedrock.aerialhell.World.Features.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class StraightLine
{
    protected final FeaturePlaceContext<?> context;
    protected final StraightLineParameters straightLineParams;
    protected final Vector3f straightLineGenStepMoveVec;
    public final Supplier<Block> block;

    public StraightLine(FeaturePlaceContext<?> context, StraightLineParameters parameters, Supplier<Block> block)
    {
        this.context = context; this.straightLineParams = parameters;
        this.straightLineGenStepMoveVec = getStraightLineGenerationStepMoveVector();
        this.block = block;
    }

    public BlockPos generate(boolean stopAtAnyObstacle, boolean generateDebug) //returns last placed block pos
    {
        int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());

        BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
        placementPos.set(this.straightLineParams.getStart());
        while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.precisionMultiplicator)
        {
            placementPos.set(getOffsetPosFromStart(i));
            boolean onePlaced = tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.precisionMultiplicator);
            if (stopAtAnyObstacle && !onePlaced) {return placementPos;}
            i++;
        }
        if (generateDebug) {this.generateDebug();}
        return placementPos;
    }

    public void generateDebug()
    {
        FeatureHelper.generateDebug(this.context);
        WorldGenLevel level = context.level();
        //start and end position
        level.setBlock(this.straightLineParams.getStart(), AerialHellBlocksAndItems.ARSONIST_BLOCK.get().defaultBlockState(), 0);
        level.setBlock(this.straightLineParams.getEnd(), AerialHellBlocksAndItems.ARSONIST_BLOCK.get().defaultBlockState(), 0);
    }

    private Vector3f getStraightLineGenerationStepMoveVector()
    {
        int maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(straightLineParams.getStart(), straightLineParams.getEnd());

        float xStep = (float) (straightLineParams.getEnd().getX() - straightLineParams.getStart().getX()) / (maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
        float yStep = (float) (straightLineParams.getEnd().getY() - straightLineParams.getStart().getY()) / (maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
        float zStep = (float) (straightLineParams.getEnd().getZ() - straightLineParams.getStart().getZ()) / (maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
        return new Vector3f(xStep, yStep, zStep);
    }

    public Vector3f getRandomOrthogonalToStraightLineNormalizedVector(int normalizationFactor)
    {
        return FeatureHelper.getRandomOrthogonalVectorToLineDefinedWith2Points(this.straightLineParams.getStart(), this.straightLineParams.getEnd(), context.random()).normalize(normalizationFactor);
    }

    public BlockPos getOffsetPosFromStart(int step)
    {
        return this.straightLineParams.getStart().offset((int) (step * this.straightLineGenStepMoveVec.x), (int) (step * this.straightLineGenStepMoveVec.y), (int) (step * this.straightLineGenStepMoveVec.z));
    }

    public BlockPos getOffsetPosFromEnd(int step)
    {
        return this.straightLineParams.getEnd().offset((int) (- step * this.straightLineGenStepMoveVec.x), (int) (- step * this.straightLineGenStepMoveVec.y), (int) (- step * this.straightLineGenStepMoveVec.z));
    }

    protected boolean tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
    {
        return this.tryPlacingBlocksCross(pos);
    }

    protected boolean tryPlacingBlocksSphere(BlockPos.MutableBlockPos pos, int radius) //returns true if one of the blocks is placed
    {
        boolean onePlaced = false;
        BlockPos.MutableBlockPos placementPos = pos.mutable();
        for (int x=-radius; x<=radius; x++)
        {
            for (int y=-radius; y<=radius; y++)
            {
                for (int z=-radius; z<=radius; z++)
                {
                    if (x*x + y*y + z*z <= radius*radius)
                    {
                        placementPos.set(pos.offset(x,y,z));
                        onePlaced = tryPlacingBlock(placementPos) || onePlaced;
                    }
                }
            }
        }
        return onePlaced;
    }

    protected boolean tryPlacingBlocksCross(BlockPos.MutableBlockPos pos) //returns true if one of the blocks is placed
    {
        boolean onePlaced = tryPlacingBlock(pos);
        pos.move(1, 0, 0);
        onePlaced = tryPlacingBlock(pos) || onePlaced;
        pos.move(-2, 0, 0);
        onePlaced = tryPlacingBlock(pos) || onePlaced;
        pos.move(1, 1, 0);
        onePlaced = tryPlacingBlock(pos) || onePlaced;
        pos.move(0, -2, 0);
        onePlaced = tryPlacingBlock(pos) || onePlaced;
        pos.move(0, 1, 1);
        onePlaced = tryPlacingBlock(pos) || onePlaced;
        pos.move(0, 0, -2);
        onePlaced = tryPlacingBlock(pos) || onePlaced;
        pos.move(0, 0, 1);
        return onePlaced;
    }

    protected boolean tryPlacingBlock(BlockPos.MutableBlockPos pos) //returns true if the block is placed
    {
        WorldGenLevel level = context.level();
        if (isReplaceable(level, pos)) {level.setBlock(pos, getStateForPlacement(pos), 2); return true;}
        else {return false;}
    }

    public BlockState getStateForPlacement(BlockPos pos) {return block.get().defaultBlockState();}

    protected boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
    {
        BlockState previousBlock = reader.getBlockState(blockPos);
        return previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE);
    }

    public static class StraightLineParameters
    {
        private final BlockPos start;
        private final BlockPos end;
        private final int precisionMultiplicator;

        public StraightLineParameters(BlockPos start, BlockPos end, int precisionMultiplicator) {this.start = start; this.end = end; this.precisionMultiplicator = precisionMultiplicator;}
        public StraightLineParameters(BlockPos start, BlockPos end) {this.start = start; this.end = end; this.precisionMultiplicator = 1;}

        public BlockPos getStart() {return start;}
        public BlockPos getEnd() {return end;}
        public int getPrecisionMultiplicator() {return precisionMultiplicator;}
    }
}
