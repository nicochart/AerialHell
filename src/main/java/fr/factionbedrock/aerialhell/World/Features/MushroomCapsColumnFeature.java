package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.MushroomCapsColumnConfig;
import fr.factionbedrock.aerialhell.World.Features.GiantTree.AbstractGiantTreeFeature;
import fr.factionbedrock.aerialhell.World.Features.Util.Ellipsoid;
import fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.ClassicGiantTrunk;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnots;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedStraightLine;
import fr.factionbedrock.aerialhell.World.Features.Util.StraightLine;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MushroomCapsColumnFeature extends AbstractGiantTreeFeature<MushroomCapsColumnConfig>
{
    private static final SplineKnotsDeformedStraightLine.KnotsParameters STEM_KNOTS_PARAMETERS = new SplineKnots.KnotsParameters(8, 16, 0.3F, 5, 20);

    public MushroomCapsColumnFeature(Codec<MushroomCapsColumnConfig> codec) {super(codec);}

    @Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.MUSHROOM_CAPS_COLUMN_LIST;}

    @Override public boolean generate(FeatureContext<MushroomCapsColumnConfig> context)
    {
        Random rand = context.getRandom(); MushroomCapsColumnConfig config = context.getConfig();
        BlockPos origin = context.getOrigin();

        if (!canPlace(context) || !this.isDungeonSensitiveValid(context)) {return false;}
        else
        {
            int maxXZdistance=config.stemMaxHorizontalOffset(), minYdistance=config.stemMinVerticalOffset(), maxYdistance=config.stemMaxVerticalOffset();
            BlockPos trunkStart = origin.down(2);
            int xOffset = rand.nextBetweenExclusive(-maxXZdistance, maxXZdistance), yOffset = rand.nextBetweenExclusive(minYdistance, maxYdistance), zOffset = rand.nextBetweenExclusive(-maxXZdistance, maxXZdistance);
            if (!FeatureHelper.isBelowMaxBuildHeight(context, context.getOrigin().up(yOffset))) {return false;}
            BlockPos trunkEnd = origin.add(xOffset, yOffset, zOffset);
            generate(context, trunkStart, trunkEnd, false);
            return true;
        }
    }

    protected void generate(FeatureContext<MushroomCapsColumnConfig> context, BlockPos startPos, BlockPos endPos, boolean generateDebug)
    {
        GiantPineTree pineTree = new GiantPineTree(context, new StraightLine.StraightLineParameters(startPos, endPos), 2 + context.getRandom().nextInt(2));
        pineTree.generate(false, generateDebug);
        pineTree = null;
    }

    private static class GiantPineTree extends ClassicGiantTrunk
    {
        private final boolean largeTrunk;
        public GiantPineTree(FeatureContext<MushroomCapsColumnConfig> context, StraightLineParameters straightLineParams, int knotsNumber)
        {
            super(context, straightLineParams, knotsNumber, STEM_KNOTS_PARAMETERS, () -> context.getConfig().stemProvider().get(context.getRandom(), context.getOrigin()).getBlock());
            this.largeTrunk = (context.getConfig().stemMaxVerticalOffset() + context.getConfig().stemMinVerticalOffset()) / 2 > 30;
        }

        private FeatureContext<MushroomCapsColumnConfig> getContext() {return (FeatureContext<MushroomCapsColumnConfig>)context;}

        @Override protected boolean isLarge() {return this.largeTrunk;}

        @Override public BlockPos generate(boolean stopAtAnyObstacle, boolean generateDebug)
        {
            int i = 0, maxAbsOffset = FeatureHelper.getMaxAbsoluteXYZOffset(this.straightLineParams.getStart(), this.straightLineParams.getEnd());
            int capCooldown = getContext().getConfig().verticalCapSeparation() * 2, capDistance = getContext().getConfig().verticalCapSeparation();

            BlockPos.Mutable placementPos = this.straightLineParams.getStart().mutableCopy();
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

            MushroomCapsColumnConfig config = (MushroomCapsColumnConfig) context.getConfig();
            int capRadius = (int) (factor * config.capMeanSize());
            int capHeight = capRadius / 2;
            GiantCap cap = new GiantCap(getContext(), createEllipsoidParameters(capRadius, capHeight, 1), pos);
            cap.generateOutsideBorder();
            cap.generateLight(1);
            cap = null;
        }

        private Ellipsoid.EllipsoidParameters createEllipsoidParameters(int xzSize, int ySize, int bonus)
        {
            return new Ellipsoid.EllipsoidParameters(xzSize, ySize, xzSize, - xzSize - bonus, xzSize + bonus, 0, ySize + bonus, - xzSize - bonus, xzSize + bonus);
        }

        @Override public BlockState getStateForPlacement(BlockPos pos) {return ((MushroomCapsColumnConfig)context.getConfig()).stemProvider().get(context.getRandom(), pos);}
    }

    private static class GiantCap extends Ellipsoid
    {
        public GiantCap(FeatureContext<MushroomCapsColumnConfig> context, Ellipsoid.EllipsoidParameters parameters, BlockPos centerPos)
        {
            super(context, () -> context.getConfig().capProvider().get(context.getRandom(), FeatureHelper.getFeatureCenter(context)).getBlock(), parameters, centerPos, Ellipsoid.Types.CENTER_1x1);
        }

        public void generateLight(int number)
        {
            for (int i=0; i<number; i++)
            {
                BlockPos offset = findLightPos(25);
                if (offset != null)
                {
                    BlockPos generatePos = this.centerPos.add(offset);
                    tryPlacingLightBlock(generatePos.mutableCopy(), offset);
                }
            }
        }

        @Nullable public BlockPos findLightPos(int maxTries)
        {
            Random rand = context.getRandom();
            boolean foundPos = false; int i=0;
            while(!foundPos && i++ <= maxTries)
            {
                int x = rand.nextBetweenExclusive(ellipsoidParams.xForMin(), ellipsoidParams.xForMax());
                int y = rand.nextBetweenExclusive(ellipsoidParams.yForMin(), ellipsoidParams.yForMax());
                int z = rand.nextBetweenExclusive(ellipsoidParams.zForMin(), ellipsoidParams.zForMax());
                if (this.isPosAtEllipsoidInsideBorder(x, y, z))
                {
                    return new BlockPos(x, y, z);
                }
            }
            return null;
        }

        protected boolean tryPlacingLightBlock(BlockPos.Mutable placementPos, BlockPos ellipsoidPos) //ellipsoidPos = offset from centerPos
        {
            StructureWorldAccess level = context.getWorld(); boolean isPlaceable = isReplaceable(level, placementPos);
            if (isPlaceable) {level.setBlockState(placementPos, this.getLightStateForPlacement(ellipsoidPos), 2);}
            return isPlaceable;
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            return ((MushroomCapsColumnConfig) context.getConfig()).capProvider().get(context.getRandom(), pos);
        }

        public BlockState getLightStateForPlacement(BlockPos pos)
        {
            return ((MushroomCapsColumnConfig) context.getConfig()).lightProvider().get(context.getRandom(), pos);
        }
    }
}