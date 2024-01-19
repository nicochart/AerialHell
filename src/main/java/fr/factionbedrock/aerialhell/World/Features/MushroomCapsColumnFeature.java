package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.MushroomCapsColumnConfig;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.AbstractGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.Util.Ellipsoid;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class MushroomCapsColumnFeature extends AbstractGiantTreeFeature<MushroomCapsColumnConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters STEM_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public MushroomCapsColumnFeature(Codec<MushroomCapsColumnConfig> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<MushroomCapsColumnConfig> context)
    {
        RandomSource rand = context.random(); MushroomCapsColumnConfig config = context.config();
        BlockPos origin = context.origin();

        if (!canPlace(context)) {return false;}
        else
        {
            int maxXZdistance=config.stemMaxHorizontalOffset(), minYdistance=config.stemMinVerticalOffset(), maxYdistance=config.stemMaxVerticalOffset();
            BlockPos trunkStart = origin.below(2);
            int xOffset = rand.nextInt(-maxXZdistance, maxXZdistance), yOffset = rand.nextInt(minYdistance, maxYdistance), zOffset = rand.nextInt(-maxXZdistance, maxXZdistance);
            if (!FeatureHelper.isBelowMaxBuildHeight(context, context.origin().above(yOffset))) {return false;}
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            generate(context, trunkStart, trunkEnd, false);
            return true;
        }
    }

    protected void generate(FeaturePlaceContext<MushroomCapsColumnConfig> context, BlockPos startPos, BlockPos endPos, boolean generateDebug)
    {
        GiantPineTree pineTree = new GiantPineTree(context, new StraightLine.StraightLineParameters(startPos, endPos), 2 + context.random().nextInt(2));
        pineTree.generate(false, generateDebug);
        pineTree = null;
    }

    private static class GiantPineTree extends ClassicGiantTrunk
    {
        private final boolean largeTrunk;
        public GiantPineTree(FeaturePlaceContext<MushroomCapsColumnConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, STEM_KNOTS_PARAMETERS, () -> context.config().stemProvider().getState(context.random(), context.origin()).getBlock());
            this.largeTrunk = (context.config().stemMaxVerticalOffset() + context.config().stemMinVerticalOffset()) / 2 > 30;
        }

        private FeaturePlaceContext<MushroomCapsColumnConfig> getContext() {return (FeaturePlaceContext<MushroomCapsColumnConfig>)context;}

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override public BlockPos generate(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());
            int capCooldown = getContext().config().verticalCapSeparation() * 2, capDistance = getContext().config().verticalCapSeparation();

            BlockPos.MutableBlockPos placementPos = this.straightLineParams.getStart().mutable();
            while(!placementPos.equals(this.straightLineParams.getEnd()) && i <= maxAbsOffset * straightLineParams.getPrecisionMultiplicator())
            {
                placementPos.set(getKnotsDeformedPos(getOffsetPosFromStart(i), this.getKnots(), this.getKnotsNumber(), this.getKnotsParameters()));
                boolean onePlaced = tryPlacingBlocks(placementPos, i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());

                if (capCooldown-- <= 0)
                {
                    generateCap(new BlockPos(placementPos), i, maxAbsOffset * straightLineParams.getPrecisionMultiplicator());
                    capCooldown = capDistance;
                }

                if (stopAtAnyObstacle && !onePlaced) {return placementPos;}
                i++;
            }
            if (generateDebug) {this.generateDebug();}
            return new BlockPos(placementPos);
        }

        private void generateCap(BlockPos pos, int step, int maxStep)
        {
            float factor = 0.6F + 0.9F * (maxStep - step) / maxStep;

            MushroomCapsColumnConfig config = (MushroomCapsColumnConfig) context.config();
            int capRadius = (int) (factor * config.capMeanSize());
            int capHeight = capRadius / 2;
            GiantCap cap = new GiantCap(getContext(), createEllipsoidParameters(capRadius, capHeight, 1), pos);
            cap.generateOutsideBorder();
        }

        private Ellipsoid.EllipsoidParameters createEllipsoidParameters(int xzSize, int ySize, int bonus)
        {
            return new Ellipsoid.EllipsoidParameters(xzSize, ySize, xzSize, - xzSize - bonus, xzSize + bonus, 0, ySize + bonus, - xzSize - bonus, xzSize + bonus);
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((MushroomCapsColumnConfig)context.config()).stemProvider().getState(context.random(), pos);}
    }

    private static class GiantCap extends Ellipsoid
    {
        public GiantCap(FeaturePlaceContext<MushroomCapsColumnConfig> context, Ellipsoid.EllipsoidParameters parameters, BlockPos centerPos)
        {
            super(context, () -> context.config().capProvider().getState(context.random(), FeatureHelper.getFeatureCenter(context)).getBlock(), parameters, centerPos, Ellipsoid.Types.CENTER_1x1);
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return ((MushroomCapsColumnConfig) context.config()).capProvider().getState(context.random(), pos);
        }
    }
}