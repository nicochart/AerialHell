package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.BiomeShifterBlock;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public interface BiomeShifter
{
    int MIN_PROTECTION_DISTANCE = 5;
    int MAX_PROTECTION_DISTANCE = 100;

    int getFieldSize();
    private int getRealFieldSize() {return Math.min(Math.max(MIN_PROTECTION_DISTANCE, this.getFieldSize()), MAX_PROTECTION_DISTANCE);}

    static void transformRandomBlocks(Level level, BlockPos pos, BlockState state, BiomeShifter blockEntity)
    {
        int fieldSize = blockEntity.getRealFieldSize(); RandomSource rand = level.random;
        int tryNumber = (int) (fieldSize * fieldSize * fieldSize * 1.0F/8.0F);
        for (int i=0; i<tryNumber; i++)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(-fieldSize, fieldSize), rand.nextInt(-fieldSize, fieldSize), rand.nextInt(-fieldSize, fieldSize));
            if (blockEntity.isValidBiomeShifterForPos(level, blockpos, pos) && BlockHelper.isCorrupted(level, blockpos) && level instanceof ServerLevel serverlevel)
            {
                BlockHelper.uncorrupt(serverlevel, blockpos);
            }
        }
    }

    default boolean isValidBiomeShifterForPos(LevelReader level, BlockPos posToShift, BlockPos shifterPos)
    {
        return level.getBlockState(shifterPos).getBlock() instanceof BiomeShifterBlock && shifterPos.distSqr(posToShift) < this.getRealFieldSize() * this.getRealFieldSize();
    }
}
