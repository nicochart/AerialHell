package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.AerialHellTwistingVinesConfig;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

//copy of TwistingVinesFeature class ; editing placeWeepingVinesColumn and isInvalidPlacementLocation to adapt to Aerial Hell
public class AerialHellTwistingVinesFeature extends Feature<AerialHellTwistingVinesConfig> implements DungeonSensitiveFeatureCheck
{
    public AerialHellTwistingVinesFeature(Codec<AerialHellTwistingVinesConfig> codec) {super(codec);}

    @Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.TWISTING_ROOTS_LIST;}

    public boolean generate(FeatureContext<AerialHellTwistingVinesConfig> context)
    {
        boolean needsRoof =  context.getConfig().needsRoof().equals("true");
        StructureWorldAccess worldgenworld = context.getWorld(); BlockPos blockpos = context.getOrigin();
        if (isInvalidPlacementLocation(worldgenworld, blockpos, needsRoof) || !this.isDungeonSensitiveValid(context)) {return false;}
        else
        {
            Random random = context.getRandom();
            AerialHellTwistingVinesConfig twistingvinesconfig = context.getConfig();
            int i = twistingvinesconfig.spreadWidth();
            int j = twistingvinesconfig.spreadHeight();
            int k = twistingvinesconfig.maxHeight();
            BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

            for(int l = 0; l < i * i; ++l)
            {
                blockpos$mutableblockpos.set(blockpos).move(MathHelper.nextInt(random, -i, i), MathHelper.nextInt(random, -j, j), MathHelper.nextInt(random, -i, i));
                if (findFirstAirBlockAboveGround(worldgenworld, blockpos$mutableblockpos) && !isInvalidPlacementLocation(worldgenworld, blockpos$mutableblockpos, false))
                {
                    int i1 = MathHelper.nextInt(random, 1, k);
                    if (random.nextInt(6) == 0) {i1 *= 2;}
                    if (random.nextInt(5) == 0) {i1 = 1;}
                    placeWeepingVinesColumn(worldgenworld, random, blockpos$mutableblockpos, i1, 17, 25, twistingvinesconfig.headBlockProvider(), twistingvinesconfig.bodyBlockProvider());
                }
            }
            return true;
        }
    }

    private static boolean findFirstAirBlockAboveGround(WorldAccess world, BlockPos.Mutable mutablePos)
    {
        do {mutablePos.move(0, -1, 0); if (world.isOutOfHeightLimit(mutablePos)) {return false;}} while(world.getBlockState(mutablePos).isAir());
        mutablePos.move(0, 1, 0);
        return true;
    }

    public static void placeWeepingVinesColumn(WorldAccess world, Random rand, BlockPos.Mutable mutablePos, int height, int minAge, int maxAge, BlockStateProvider headProvider, BlockStateProvider bodyProvider)
    {
        for(int i = 1; i <= height; ++i)
        {
            if (world.isAir(mutablePos))
            {
                if (i == height || !world.isAir(mutablePos.up())) {world.setBlockState(mutablePos, headProvider.get(rand, mutablePos).with(AbstractPlantStemBlock.AGE, Integer.valueOf(MathHelper.nextInt(rand, minAge, maxAge))), 2); break;}
                world.setBlockState(mutablePos, bodyProvider.get(rand, mutablePos), 2);
            }
            mutablePos.move(Direction.UP);
        }
    }

    private static boolean isInvalidPlacementLocation(WorldView world, BlockPos pos, boolean needsRoof)
    {
        if (!world.isAir(pos)) {return true;}
        else
        {
            if (needsRoof && !BlockHelper.hasAnySolidSurfaceAbove(world, pos, 3)) {return true;}
            BlockState blockstate = world.getBlockState(pos.down());
            return !blockstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT) && !blockstate.isOf(AerialHellBlocks.SLIPPERY_SAND) && !blockstate.isOf(Blocks.NETHERRACK) && !blockstate.isOf(Blocks.WARPED_NYLIUM) && !blockstate.isOf(Blocks.WARPED_WART_BLOCK);
        }
    }
}
