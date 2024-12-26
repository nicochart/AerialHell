package fr.factionbedrock.aerialhell.World.Features.GiantTree;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.DeadGiantTreeConfig;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class DeadGiantTreeFeature extends AbstractGiantTreeFeature<DeadGiantTreeConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters TRUNK_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public DeadGiantTreeFeature(Codec<DeadGiantTreeConfig> codec) {super(codec);}

    @Override public boolean generate(FeatureContext<DeadGiantTreeConfig> context)
    {
        Random rand = context.getRandom(); DeadGiantTreeConfig config = context.getConfig();
        BlockPos origin = context.getOrigin();

        if (!canPlace(context)) {return false;}
        else
        {
            int maxXZdistance=config.trunkMaxHorizontalOffset(), minYdistance=config.trunkMinVerticalOffset(), maxYdistance=config.trunkMaxVerticalOffset();
            BlockPos trunkStart = origin.down(2);
            int xOffset = rand.nextBetweenExclusive(-maxXZdistance, maxXZdistance), yOffset = rand.nextBetweenExclusive(minYdistance, maxYdistance), zOffset = rand.nextBetweenExclusive(-maxXZdistance, maxXZdistance);
            BlockPos trunkEnd = origin.add(xOffset, yOffset, zOffset);
            if (!FeatureHelper.isBelowMaxBuildHeight(context, context.getOrigin().up(yOffset / 2))) {return false;}
            generateTrunk(context, trunkStart, trunkEnd, false);
            return true;
        }
    }

    protected void generateTrunk(FeatureContext<DeadGiantTreeConfig> context, BlockPos trunkStart, BlockPos trunkEnd, boolean generateDebug)
    {
        DeadGiantTrunk trunkSpline = new DeadGiantTrunk(context, new StraightLine.StraightLineParameters(trunkStart, trunkEnd), 2 + context.getRandom().nextInt(2));
        trunkSpline.generateInsideBorder(false, generateDebug);
        trunkSpline = null;
    }

    @Override protected boolean isValidTreeSupport(BlockState state) {return super.isValidTreeSupport(state) || state.isOf(AerialHellBlocks.SLIPPERY_SAND);}

    private static class DeadGiantTrunk extends ClassicGiantTrunk
    {
        private final boolean largeTrunk;
        public DeadGiantTrunk(FeatureContext<DeadGiantTreeConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, TRUNK_KNOTS_PARAMETERS, () -> context.getConfig().trunkProvider().get(context.getRandom(), context.getOrigin()).getBlock());
            this.largeTrunk = (context.getConfig().trunkMaxVerticalOffset() + context.getConfig().trunkMinVerticalOffset()) / 2 > 12;
        }

        private DeadGiantTreeConfig getContextConfig() {return (DeadGiantTreeConfig) context.getConfig();}

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override protected boolean tryPlacingBlock(BlockPos.Mutable pos)
        {
            if (context.getRandom().nextFloat() < getContextConfig().randomChanceToNotPlaceBlock()) {return false;}
            else {return super.tryPlacingBlock(pos);}
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return ((DeadGiantTreeConfig)context.getConfig()).trunkProvider().get(context.getRandom(), pos);
        }

        @Override public BlockPos generateInsideBorder(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            if (generatePosList == null) {simulateGenerate(stopAtAnyObstacle);}

            int yMeanOffset = (getContextConfig().trunkMinVerticalOffset() + getContextConfig().trunkMaxVerticalOffset()) / 2;
            int yCut = this.straightLineParams.getStart().getY() + yMeanOffset/2 + context.getRandom().nextBetweenExclusive(-yMeanOffset/4, +yMeanOffset/4);
            BlockPos lastPos = this.straightLineParams.getStart();
            if (generatePosList.isEmpty()) {return this.straightLineParams.getStart();}
            else
            {
                for (BlockPos pos : generatePosList)
                {
                    if (isInsideBorder(pos) && pos.getY() <= yCut + context.getRandom().nextInt(2))
                    {
                        tryPlacingBlock(pos.mutableCopy());
                        lastPos = pos;
                    }
                }
            }
            if (generateDebug) {this.generateDebug();}
            return lastPos;
        }
    }
}