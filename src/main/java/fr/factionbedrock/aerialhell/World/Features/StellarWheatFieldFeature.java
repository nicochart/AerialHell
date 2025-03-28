package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class StellarWheatFieldFeature extends Feature<NoneFeatureConfiguration>
{
    private static int MAX_VERTICAL_OFFSET = 3;
    private static float BASE_PROBABILITY_NOT_TO_PLACE_INDIVIDUAL_WHEAT = 0.1F;
    private static float BASE_PROBABILITY_TO_ACCEPT_OFFSET = 0.9F;

    public StellarWheatFieldFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        BlockPos blockPos = context.origin(); WorldGenLevel world = context.level(); RandomSource rand = context.random();
		boolean canGenerate = isAboveSurfaceBlockPos(world, blockPos) && !BlockHelper.hasAnySolidSurfaceAbove(world, blockPos.above(2), 3);
		boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);
		
        if (canGenerate && !generatesInDungeon)
        {
        	generateField(world, rand, blockPos);
        	return true;
        }
        return false;
    }
    
    protected void generateField(WorldGenLevel world, RandomSource rand, BlockPos blockPos)
    {
    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
    	boolean isBig = rand.nextDouble() > 0.8;
        int radiusX = getRandomRadius(rand, isBig);
        int radiusZ = getRandomRadius(rand, isBig);

        for(int x = -radiusX; x <= radiusX; x++)
        {
            for(int z = -radiusZ; z <= radiusZ; z++)
            {
                BlockPos pos = new BlockPos(x, 0, z);
                if (isPosInsideEllipsis(pos, radiusX, radiusZ) && world.getRandom().nextFloat() > BASE_PROBABILITY_NOT_TO_PLACE_INDIVIDUAL_WHEAT)
                {
                    placementPos.set(blockPos.offset(pos));
                    findHeightForPlacement(world, placementPos);
                    if (isValidBlockPosForStellarWheat(world, placementPos))
                    {
                        world.setBlock(placementPos.below(), AerialHellBlocks.STELLAR_FARMLAND.get().defaultBlockState(), 2);
                        world.setBlock(placementPos, AerialHellBlocks.STELLAR_WHEAT.get().defaultBlockState().setValue(CropBlock.AGE, world.getRandom().nextInt(4, 8)), 2);
                    }
                }
            }
        }
    }

    protected static void findHeightForPlacement(WorldGenLevel world, BlockPos.MutableBlockPos blockPos)
    {
        int step = 0;
        while (step < MAX_VERTICAL_OFFSET && !isAboveSurfaceBlockPos(world, blockPos))
        {
            float probabilityToMove = BASE_PROBABILITY_TO_ACCEPT_OFFSET / (step + 1);
            if (world.getRandom().nextFloat() > probabilityToMove) {return;}

            if (world.getBlockState(blockPos).isAir()) {blockPos.move(Direction.DOWN);}
            else {blockPos.move(Direction.UP);}
            step++;
        }
    }

    private static boolean isAboveSurfaceBlockPos(WorldGenLevel world, BlockPos blockPos)
    {
        return world.getBlockState(blockPos).isAir() && !world.getBlockState(blockPos.below()).isAir();
    }

    private static boolean isValidBlockPosForStellarWheat(WorldGenLevel world, BlockPos blockPos)
    {
        return world.getBlockState(blockPos).isAir() && world.getBlockState(blockPos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT);
    }
    
    private int getRandomRadius(RandomSource rand, boolean isBig)
    {
    	return isBig ? (int) (5 + rand.nextFloat() * 5) : (int) (3 + rand.nextFloat() * 4);
    }
    
    private boolean isPosInsideEllipsis(BlockPos pos, float a, float c)
    {
        float x = pos.getX() - 0.5F;
        float z = pos.getZ() - 0.5F;
        return x*x/(a*a) + z*z/(c*c) < 1.0F;
    }
}