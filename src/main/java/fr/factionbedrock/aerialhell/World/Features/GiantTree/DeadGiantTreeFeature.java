package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.DeadGiantTreeConfig;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.List;

public class DeadGiantTreeFeature extends AbstractGiantTreeFeature<DeadGiantTreeConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public DeadGiantTreeFeature(Codec<DeadGiantTreeConfig> codec) {super(codec);}

    @Override protected List<ResourceKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.DEAD_GIANT_TREE_TREE_LIST;}

    @Override public boolean place(FeaturePlaceContext<DeadGiantTreeConfig> context)
    {
        if (!super.place(context)) {return false;}
        RandomSource rand = context.random(); DeadGiantTreeConfig config = context.config();
        BlockPos origin = context.origin();

        if (!canPlace(context)) {return false;}
        else
        {
            int maxXZdistance=config.trunkMaxHorizontalOffset(), minYdistance=config.trunkMinVerticalOffset(), maxYdistance=config.trunkMaxVerticalOffset();
            BlockPos trunkStart = origin.below(2);
            int xOffset = rand.nextInt(-maxXZdistance, maxXZdistance), yOffset = rand.nextInt(minYdistance, maxYdistance), zOffset = rand.nextInt(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.offset(xOffset, yOffset, zOffset);
            if (!FeatureHelper.isBelowMaxBuildHeight(context, context.origin().above(yOffset / 2))) {return false;}
            generateTrunk(context, trunkStart, trunkEnd, false);
            return true;
        }
    }

    protected void generateTrunk(FeaturePlaceContext<DeadGiantTreeConfig> context, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        DeadGiantTrunk trunkSpline = new DeadGiantTrunk(context, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + context.random().nextInt(2));
        trunkSpline.generateInsideBorder(false, generateDebug);
        trunkSpline = null;
    }

    @Override protected boolean isValidTreeSupport(BlockState state) {return super.isValidTreeSupport(state) || state.is(AerialHellBlocks.SLIPPERY_SAND.get());}

    private static class DeadGiantTrunk extends ClassicGiantTrunk
    {
        private final boolean largeTrunk;
        public DeadGiantTrunk(FeaturePlaceContext<DeadGiantTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.config().trunkProvider().getState(context.random(), context.origin()).getBlock());
            this.largeTrunk = (context.config().trunkMaxVerticalOffset() + context.config().trunkMinVerticalOffset()) / 2 > 12;
        }

        private DeadGiantTreeConfig getContextConfig() {return (DeadGiantTreeConfig) context.config();}

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override protected boolean tryPlacingBlock(BlockPos.MutableBlockPos pos)
        {
            if (context.random().nextFloat() < getContextConfig().randomChanceToNotPlaceBlock()) {return false;}
            else {return super.tryPlacingBlock(pos);}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return ((DeadGiantTreeConfig)context.config()).trunkProvider().getState(context.random(), pos);
        }

        @Override public BlockPos generateInsideBorder(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            if (generatePosList == null) {simulateGenerate(stopAtAnyObstacle);}

            int yMeanOffset = (getContextConfig().trunkMinVerticalOffset() + getContextConfig().trunkMaxVerticalOffset()) / 2;
            int yCut = this.straightLineParams.getStart().getY() + yMeanOffset/2 + context.random().nextInt(-yMeanOffset/4, +yMeanOffset/4);
            BlockPos lastPos = this.straightLineParams.getStart();
            if (generatePosList.isEmpty()) {return this.straightLineParams.getStart();}
            else
            {
                for (BlockPos pos : generatePosList)
                {
                    if (isInsideBorder(pos) && pos.getY() <= yCut + context.random().nextInt(2))
                    {
                        tryPlacingBlock(pos.mutable());
                        lastPos = pos;
                    }
                }
            }
            if (generateDebug) {this.generateDebug();}
            return lastPos;
        }
    }
}