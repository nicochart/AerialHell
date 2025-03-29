package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.NaturalFieldConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class NaturalFieldFeature extends Feature<NaturalFieldConfig>
{
    private static int MAX_VERTICAL_OFFSET = 3;
    private static float BASE_PROBABILITY_NOT_TO_PLACE_INDIVIDUAL_WHEAT = 0.1F;
    private static float BASE_PROBABILITY_TO_ACCEPT_OFFSET = 0.9F;

    public NaturalFieldFeature(Codec<NaturalFieldConfig> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<NaturalFieldConfig> context)
    {
        BlockPos blockPos = context.origin(); WorldGenLevel world = context.level();
		boolean canGenerate = isAboveSurfaceBlockPos(world, blockPos) && !BlockHelper.hasAnySolidSurfaceAbove(world, blockPos.above(2), 3);
		boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);
		
        if (canGenerate && !generatesInDungeon)
        {
        	generateField(context);
        	return true;
        }
        return false;
    }
    
    protected void generateField(FeaturePlaceContext<NaturalFieldConfig> context)
    {
        WorldGenLevel world = context.level(); RandomSource rand = context.random(); BlockPos blockPos = context.origin();
    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
    	boolean isBig = rand.nextDouble() > 0.8;
        int radiusX = getRandomRadius(rand, isBig);
        int radiusZ = getRandomRadius(rand, isBig);

        for(int x = -radiusX; x <= radiusX; x++)
        {
            for(int z = -radiusZ; z <= radiusZ; z++)
            {
                BlockPos pos = new BlockPos(x, 0, z);
                if (isPosInsideEllipsis(pos, radiusX, radiusZ) && world.getRandom().nextFloat() < context.config().baseChance())
                {
                    placementPos.set(blockPos.offset(pos));
                    findHeightForPlacement(context, placementPos);
                    if (isValidBlockPosForStellarWheat(world, placementPos))
                    {
                        world.setBlock(placementPos.below(), AerialHellBlocks.STELLAR_FARMLAND.get().defaultBlockState(), 2);
                        BlockState placementState = context.config().cropStateProvider().getState(rand, placementPos);
                        if (placementState.getBlock() instanceof CropBlock)
                        {
                            placementState = placementState.setValue(CropBlock.AGE, world.getRandom().nextInt(4, 8));
                        }
                        world.setBlock(placementPos, placementState, 2);
                    }
                }
            }
        }
    }

    protected static void findHeightForPlacement(FeaturePlaceContext<NaturalFieldConfig> context, BlockPos.MutableBlockPos blockPos)
    {
        WorldGenLevel world = context.level();
        int step = 0;
        while (step < context.config().maxVerticalOffset() && !isAboveSurfaceBlockPos(world, blockPos))
        {
            float probabilityToMove = context.config().acceptOffsetChance() / (step + 1);
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