package fr.factionbedrock.aerialhell.Block.ShadowSpreader;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public interface ShadowSpreaderBlock
{
    BooleanProperty CAN_SPREAD = BooleanProperty.of("can_spread");

    static void trySpreading(BlockState state, ServerWorld world, BlockPos pos, Random rand) {trySpreading(state, world, pos, rand, false);}

    static void trySpreading(BlockState state, ServerWorld world, BlockPos pos, Random rand, boolean lookForAboveGrassBlock)
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
                BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(3) - 1, rand.nextInt(3) - 1);
                if (!BlockHelper.isCorrupted(world, blockpos))
                {
                    if (lookForAboveGrassBlock && world.getBlockState(blockpos).isOf(AerialHellBlocks.STELLAR_DIRT))
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
            world.setBlockState(pos, state.with(CAN_SPREAD, false), 2);
        }
    }

    @Nullable private static BlockPos lookForAboveGrassBlock(WorldView level, BlockPos origin)
    {
        BlockPos blockpos = origin;
        int j=0;
        while(j++ < 4 && level.getBlockState(blockpos).isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {blockpos = blockpos.up();}

        if (level.getBlockState(blockpos).isAir() && level.getBlockState(blockpos.down()).isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK)) {return blockpos.down();}
        else {return null;}
    }
}
