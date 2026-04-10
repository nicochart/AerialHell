package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public interface ShadowSpreaderBlock
{
    BooleanProperty CAN_SPREAD = BooleanProperty.create("can_spread");

    static void trySpreading(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {trySpreading(state, world, pos, rand, false);}

    static void trySpreading(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand, boolean lookForAboveGrassBlock)
    {
        if (BlockHelper.isCorrupted(world, pos) && BlockHelper.surroundingsPreventCorruption(world, pos, BlockHelper.CorruptionType.OTHER))
        {
            BlockHelper.uncorrupt(world, pos);
            return;
        }
        else
        {
            for(int i = 0; i < 4; ++i)
            {
                BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(3) - 1, rand.nextInt(3) - 1);
                if (!BlockHelper.isCorrupted(world, blockpos))
                {
                    if (lookForAboveGrassBlock && world.getBlockState(blockpos).is(AerialHellBlocks.STELLAR_DIRT))
                    {
                        @Nullable BlockPos maybeGrassPos = lookForAboveGrassBlock(world, blockpos);
                        if (maybeGrassPos != null && BlockHelper.tryCorrupt(world, maybeGrassPos, rand, 1.2F)) {return;}
                    }
                    else if (BlockHelper.tryCorrupt(world, blockpos, rand)) {return;}
                }
                else //isCorrupted
                {
                    if (BlockHelper.surroundingsPreventCorruption(world, blockpos, BlockHelper.CorruptionType.ANY)) {BlockHelper.uncorrupt(world, blockpos);}
                }
            }
        }

        if (BlockHelper.isSurroundingCorrupted(world, pos))
        {
            world.setBlock(pos, state.setValue(CAN_SPREAD, false), 2);
        }
    }

    @Nullable private static BlockPos lookForAboveGrassBlock(LevelReader level, BlockPos origin)
    {
        BlockPos blockpos = origin;
        int j=0;
        while(j++ < 4 && level.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_DIRT)) {blockpos = blockpos.above();}

        if (level.getBlockState(blockpos).isAir() && level.getBlockState(blockpos.below()).is(AerialHellBlocks.STELLAR_GRASS_BLOCK)) {return blockpos.below();}
        else {return null;}
    }
}
