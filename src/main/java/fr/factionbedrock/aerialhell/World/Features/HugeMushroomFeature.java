package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Util.Ellipsoid;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class HugeMushroomFeature extends Feature<HugeMushroomFeatureConfiguration>
{
    public HugeMushroomFeature(Codec<HugeMushroomFeatureConfiguration> config) {super(config);}

    @Override public boolean place(FeaturePlaceContext<HugeMushroomFeatureConfiguration> context)
    {
        BlockPos pos = context.origin(); WorldGenLevel world = context.level(); RandomSource rand = context.random(); HugeMushroomFeatureConfiguration config = context.config();
        int stemSize = (rand.nextInt(6) == 0) ? rand.nextInt(8) + 5 : rand.nextInt(5) + 8; //shroom y size
        int capRadius = 3 + rand.nextInt(2); //horizontal width
        float yCapFactor = (rand.nextInt(2) == 0) ? 0.5F : 0.6F; //cap vertical size (0.0F : 0 block, no cap ; 1.0F : full size cap, the cap touches the floor)
        if (!this.canGrow(config, world, pos, stemSize, capRadius)) {return false;}
        else
        {
            this.generateCap(context, pos, stemSize, yCapFactor, capRadius);
            this.generateStem(config, world, rand, pos, stemSize);
            return true;
        }
    }
    
    protected void generateCap(FeaturePlaceContext<HugeMushroomFeatureConfiguration> context, BlockPos blockPos, int stemSize, float yCapFactor, int capRadius)
    {
        HugeMushroomFeatureConfiguration config = context.config();
        int capHeight = (int) (stemSize * yCapFactor);
        int yCap = (int) (stemSize * (1.0F - yCapFactor));
        BlockPos centerPos = blockPos.offset(0, yCap, 0);
        GiantCap cap = new GiantCap(context, createEllipsoidParameters(capRadius, capHeight, 1), config.capProvider, centerPos);
        cap.generateOutsideBorder();
    }
    
    protected void generateStem(HugeMushroomFeatureConfiguration config, LevelAccessor world, RandomSource rand, BlockPos blockPos, int stemSize)
    {
        BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
        for(int y = 0; y < stemSize; ++y)
        {
            for (int x = 0; x < 2; x++)
            {
                for (int z = 0; z < 2; z++)
                {
                    placementPos.set(blockPos).move(x, y, z);
                    if (FeatureHelper.isReplaceableByLogOrLeavesFeature(world, placementPos, true) || world.getBlockState(placementPos).isOf(AerialHellBlocks.VERDIGRIS_AGARIC.get()))
                    {
                        this.setBlockState(world, placementPos, config.stemProvider.getState(rand, blockPos));
                    }
                }
            }
        }
    }
    
    protected boolean canGrow(HugeMushroomFeatureConfiguration config, LevelAccessor world, BlockPos blockPos, int stemSize, int capRadius)
    {
        return this.mayPlaceOn(world, blockPos)
               && canPlaceStem(config, world, blockPos, stemSize)
                && blockPos.getY() >= blockPos.getY() && blockPos.getY() + stemSize + 1 < world.getHeight();
    }
    
    protected boolean canPlaceStem(HugeMushroomFeatureConfiguration config, LevelAccessor world, BlockPos blockPos, int stemSize)
    {
        BlockPos.MutableBlockPos placementBlockPos = new BlockPos.MutableBlockPos();
        for (int x = 0; x < 2; x++)
        {
            for (int z = 0; z < 2; z++)
            {
                for(int y = 0; y <= stemSize; y++) {
                    placementBlockPos.set(blockPos).move(Direction.UP, y);
                    if (!FeatureHelper.isReplaceableByLogOrLeavesFeature(world, placementBlockPos, true)) {return false;}
                }
            }
        }
        return true;
    }
    
    protected boolean mayPlaceOn(LevelAccessor world, BlockPos pos)
    {
        BlockState blockState;
        for (int x = 0; x < 2; x++)
        {
            for (int z = 0; z < 2; z++)
            {
                blockState = world.getBlockState(pos.offset(x, -1, z));
                if (!(blockState.isIn(BlockTags.MUSHROOM_GROW_BLOCK))) {return false;}
            }
        }
        return true;
    }

    private Ellipsoid.EllipsoidParameters createEllipsoidParameters(int xzSize, int ySize, int bonus)
    {
        return new Ellipsoid.EllipsoidParameters(xzSize, ySize, xzSize, - xzSize - bonus, xzSize + bonus, 0, ySize + bonus, - xzSize - bonus, xzSize + bonus);
    }

    private static class GiantCap extends Ellipsoid
    {
        private final BlockStateProvider capProvider;

        public GiantCap(FeaturePlaceContext<?> context, Ellipsoid.EllipsoidParameters parameters, BlockStateProvider capProvider, BlockPos centerPos)
        {
            super(context, () -> capProvider.getState(context.random(), FeatureHelper.getFeatureCenter(context)).getBlock(), parameters, centerPos, Ellipsoid.Types.CENTER_2x2);
            this.capProvider = capProvider;
        }

        @Override public BlockState getStateForPlacement(BlockPos pos)
        {
            int x= pos.getX(), y=pos.getY(), z= pos.getZ();
            boolean isUpCap = isPosAtEllipsoidOutsideBorder(x, y + 1, z);
            boolean southInEll = isPosInsideEllipsoid(x, y, z + 1);
            boolean northInEll = isPosInsideEllipsoid(x, y, z - 1);
            boolean eastInEll = isPosInsideEllipsoid(x + 1, y, z);
            boolean westInEll = isPosInsideEllipsoid(x - 1, y, z);

            return this.capProvider.getState(context.random(), pos)
                    .setValue(HugeMushroomBlock.NORTH, !northInEll)
                    .setValue(HugeMushroomBlock.SOUTH, !southInEll)
                    .setValue(HugeMushroomBlock.WEST, !westInEll)
                    .setValue(HugeMushroomBlock.EAST, !eastInEll)
                    .setValue(HugeMushroomBlock.UP, !isUpCap);
        }
    }
}