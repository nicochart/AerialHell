package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.BiomeShifterBlock;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

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
            if (blockEntity.isValidBiomeShifterForPos(level, blockpos, pos) && BlockHelper.isCorrupted(level, blockpos))
            {
                if (level instanceof ServerLevel serverlevel)
                {
                    BlockHelper.uncorrupt(serverlevel, blockpos);
                    Vec3 vecpos = new Vec3(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5);
                    serverlevel.sendParticles(AerialHellParticleTypes.OSCILLATOR.get(), vecpos.x(), vecpos.y(), vecpos.z(), 5, 0.0, 0.0, 0.0, 1.0);
                    serverlevel.playSound(null, vecpos.x(), vecpos.y(), vecpos.z(), SoundEvents.WART_BLOCK_HIT, SoundSource.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F);
                }
            }
        }
    }

    default boolean isValidBiomeShifterForPos(LevelReader level, BlockPos posToShift, BlockPos shifterPos)
    {
        return level.getBlockState(shifterPos).getBlock() instanceof BiomeShifterBlock && shifterPos.distSqr(posToShift) < this.getRealFieldSize() * this.getRealFieldSize();
    }
}
