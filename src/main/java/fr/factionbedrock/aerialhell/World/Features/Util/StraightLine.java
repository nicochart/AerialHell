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

    public BlockPos generate(boolean generateDebug) //returns last placed block pos
    {
        int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());

        BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
        placementPos.set(this.straightLineParams.getStart());
        while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.precisionMultiplicator)
        {
            placementPos.set(getOffsetPosFromStart(i));
            tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.precisionMultiplicator);
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

    protected void tryPlacingBlocks(BlockPos.MutableBlockPos pos, int step, int maxStep)
    {
        this.tryPlacingBlocksCross(pos);
    }

    protected void tryPlacingBlocksSphere(BlockPos.MutableBlockPos pos, int radius)
    {
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
                        tryPlacingBlock(context, placementPos);
                    }
                }
            }
        }
    }

    protected void tryPlacingBlocksCross(BlockPos.MutableBlockPos pos)
    {
        tryPlacingBlock(context, pos);
        pos.move(1, 0, 0);
        tryPlacingBlock(context, pos);
        pos.move(-2, 0, 0);
        tryPlacingBlock(context, pos);
        pos.move(1, 1, 0);
        tryPlacingBlock(context, pos);
        pos.move(0, -2, 0);
        tryPlacingBlock(context, pos);
        pos.move(0, 1, 1);
        tryPlacingBlock(context, pos);
        pos.move(0, 0, -2);
        tryPlacingBlock(context, pos);
        pos.move(0, 0, 1);
    }

    protected void tryPlacingBlock(FeaturePlaceContext<?> context, BlockPos.MutableBlockPos pos)
    {
        WorldGenLevel reader = context.level();
        if (isReplaceable(reader, pos)) {reader.setBlock(pos, getStateForPlacement(pos), 0);}
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
