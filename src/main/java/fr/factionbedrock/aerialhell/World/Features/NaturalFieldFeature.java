package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.NaturalFieldConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class NaturalFieldFeature extends Feature<NaturalFieldConfig> implements DungeonSensitiveFeatureCheck
{
    public NaturalFieldFeature(Codec<NaturalFieldConfig> codec) {super(codec);}

    @Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.NATURAL_FIELD_LIST;}

    @Override public boolean generate(FeatureContext<NaturalFieldConfig> context)
    {
        BlockPos blockPos = context.getOrigin(); StructureWorldAccess world = context.getWorld();
		boolean canGenerate = isAboveSurfaceBlockPos(world, blockPos) && !BlockHelper.hasAnySolidSurfaceAbove(world, blockPos.up(2), 3);
		
        if (canGenerate && this.isDungeonSensitiveValid(context))
        {
        	generateField(context);
        	return true;
        }
        return false;
    }
    
    protected void generateField(FeatureContext<NaturalFieldConfig> context)
    {
        StructureWorldAccess world = context.getWorld(); Random rand = context.getRandom(); BlockPos blockPos = context.getOrigin();
    	BlockPos.Mutable placementPos = new BlockPos.Mutable();
    	boolean isBig = rand.nextDouble() > 0.8;
        int radiusX = getRandomRadius(rand, isBig);
        int radiusZ = getRandomRadius(rand, isBig);

        for(int x = -radiusX; x <= radiusX; x++)
        {
            for(int z = -radiusZ; z <= radiusZ; z++)
            {
                BlockPos pos = new BlockPos(x, 0, z);
                if (isPosInsideEllipsis(pos, radiusX, radiusZ) && world.getRandom().nextFloat() < context.getConfig().baseChance())
                {
                    placementPos.set(blockPos.add(pos));
                    findHeightForPlacement(context, placementPos);
                    if (isValidBlockPosForStellarWheat(world, placementPos))
                    {
                        world.setBlockState(placementPos.down(), AerialHellBlocks.STELLAR_FARMLAND.getDefaultState(), 2);
                        BlockState placementState = context.getConfig().cropStateProvider().get(rand, placementPos);
                        if (placementState.getBlock() instanceof CropBlock)
                        {
                            placementState = placementState.with(CropBlock.AGE, world.getRandom().nextBetweenExclusive(4, 8));
                        }
                        world.setBlockState(placementPos, placementState, 2);
                    }
                }
            }
        }
    }

    protected static void findHeightForPlacement(FeatureContext<NaturalFieldConfig> context, BlockPos.Mutable blockPos)
    {
        StructureWorldAccess world = context.getWorld();
        int step = 0;
        while (step < context.getConfig().maxVerticalOffset() && !isAboveSurfaceBlockPos(world, blockPos))
        {
            float probabilityToMove = context.getConfig().acceptOffsetChance() / (step + 1);
            if (world.getRandom().nextFloat() > probabilityToMove) {return;}

            if (world.getBlockState(blockPos).isAir()) {blockPos.move(Direction.DOWN);}
            else {blockPos.move(Direction.UP);}
            step++;
        }
    }

    private static boolean isAboveSurfaceBlockPos(StructureWorldAccess world, BlockPos blockPos)
    {
        return world.getBlockState(blockPos).isAir() && !world.getBlockState(blockPos.down()).isAir();
    }

    private static boolean isValidBlockPosForStellarWheat(StructureWorldAccess world, BlockPos blockPos)
    {
        return world.getBlockState(blockPos).isAir() && world.getBlockState(blockPos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT);
    }
    
    private int getRandomRadius(Random rand, boolean isBig)
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