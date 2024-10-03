package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public interface ShadowSpreaderBlock
{
    BooleanProperty CAN_SPREAD = BooleanProperty.create("can_spread");

    static void trySpreading(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {trySpreading(state, level, pos, rand, false);}

    static void trySpreading(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand, boolean lookForAboveGrassBlock)
    {
        if (BlockHelper.isCorrupted(level, pos) && BlockHelper.surroundingsPreventCorruption(level, pos, BlockHelper.CorruptionType.STONE))
        {
            if (!level.isAreaLoaded(pos, 3)) {return;}
            BlockHelper.uncorrupt(level, pos);
            return;
        }
        else
        {
            for(int i = 0; i < 4; ++i)
            {
                BlockPos blockpos = pos.offset(rand.nextInt(3) - 1, rand.nextInt(3) - 1, rand.nextInt(3) - 1);
                if (!BlockHelper.isCorrupted(level, blockpos))
                {
                    if (lookForAboveGrassBlock && level.getBlockState(blockpos).is(AerialHellBlocksAndItems.STELLAR_DIRT))
                    {
                        @Nullable BlockPos maybeGrassPos = lookForAboveGrassBlock(level, blockpos);
                        if (maybeGrassPos != null && BlockHelper.tryCorrupt(level, maybeGrassPos, rand, 1.2F)) {return;}
                    }
                    else if (BlockHelper.tryCorrupt(level, blockpos, rand)) {return;}
                }
                else //isCorrupted
                {
                    if (BlockHelper.surroundingsPreventCorruption(level, blockpos, BlockHelper.CorruptionType.ANY)) {BlockHelper.uncorrupt(level, blockpos);}
                }
            }
        }

        if (BlockHelper.isSurroundingCorrupted(level, pos))
        {
            level.setBlock(pos, state.setValue(CAN_SPREAD, false), 2);
        }
    }

    @Nullable private static BlockPos lookForAboveGrassBlock(LevelReader level, BlockPos origin)
    {
        BlockPos blockpos = origin;
        int j=0;
        while(j++ < 4 && level.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_DIRT)) {blockpos = blockpos.above();}

        if (level.getBlockState(blockpos).isAir() && level.getBlockState(blockpos.below()).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK)) {return blockpos.below();}
        else {return null;}
    }
}
