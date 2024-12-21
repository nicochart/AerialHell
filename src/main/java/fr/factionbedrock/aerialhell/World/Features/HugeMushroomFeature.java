package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Util.Ellipsoid;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class HugeMushroomFeature extends Feature<HugeMushroomFeatureConfig>
{
    public HugeMushroomFeature(Codec<HugeMushroomFeatureConfig> config) {super(config);}

    @Override public boolean generate(FeatureContext<HugeMushroomFeatureConfig> context)
    {
        BlockPos pos = context.getOrigin(); StructureWorldAccess world = context.getWorld(); Random rand = context.getRandom(); HugeMushroomFeatureConfig config = context.getConfig();
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
    
    protected void generateCap(FeatureContext<HugeMushroomFeatureConfig> context, BlockPos blockPos, int stemSize, float yCapFactor, int capRadius)
    {
        HugeMushroomFeatureConfig config = context.getConfig();
        int capHeight = (int) (stemSize * yCapFactor);
        int yCap = (int) (stemSize * (1.0F - yCapFactor));
        BlockPos centerPos = blockPos.add(0, yCap, 0);
        GiantCap cap = new GiantCap(context, createEllipsoidParameters(capRadius, capHeight, 1), config.capProvider, centerPos);
        cap.generateOutsideBorder();
    }
    
    protected void generateStem(HugeMushroomFeatureConfig config, WorldAccess world, Random rand, BlockPos blockPos, int stemSize)
    {
        BlockPos.Mutable placementPos = new BlockPos.Mutable();
        for(int y = 0; y < stemSize; ++y)
        {
            for (int x = 0; x < 2; x++)
            {
                for (int z = 0; z < 2; z++)
                {
                    placementPos.set(blockPos).move(x, y, z);
                    if (FeatureHelper.isReplaceableByLogOrLeavesFeature(world, placementPos, true) || world.getBlockState(placementPos).isOf(AerialHellBlocks.VERDIGRIS_AGARIC))
                    {
                        this.setBlockState(world, placementPos, config.stemProvider.get(rand, blockPos));
                    }
                }
            }
        }
    }
    
    protected boolean canGrow(HugeMushroomFeatureConfig config, WorldAccess world, BlockPos blockPos, int stemSize, int capRadius)
    {
        return this.mayPlaceOn(world, blockPos)
               && canPlaceStem(config, world, blockPos, stemSize)
                && blockPos.getY() >= blockPos.getY() && blockPos.getY() + stemSize + 1 < world.getHeight();
    }
    
    protected boolean canPlaceStem(HugeMushroomFeatureConfig config, WorldAccess world, BlockPos blockPos, int stemSize)
    {
        BlockPos.Mutable placementBlockPos = new BlockPos.Mutable();
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
    
    protected boolean mayPlaceOn(WorldAccess world, BlockPos pos)
    {
        BlockState blockState;
        for (int x = 0; x < 2; x++)
        {
            for (int z = 0; z < 2; z++)
            {
                blockState = world.getBlockState(pos.add(x, -1, z));
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

        public GiantCap(FeatureContext<?> context, Ellipsoid.EllipsoidParameters parameters, BlockStateProvider capProvider, BlockPos centerPos)
        {
            super(context, () -> capProvider.get(context.getRandom(), FeatureHelper.getFeatureCenter(context)).getBlock(), parameters, centerPos, Ellipsoid.Types.CENTER_2x2);
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

            return this.capProvider.get(context.getRandom(), pos)
                    .with(MushroomBlock.NORTH, !northInEll)
                    .with(MushroomBlock.SOUTH, !southInEll)
                    .with(MushroomBlock.WEST, !westInEll)
                    .with(MushroomBlock.EAST, !eastInEll)
                    .with(MushroomBlock.UP, !isUpCap);
        }
    }
}