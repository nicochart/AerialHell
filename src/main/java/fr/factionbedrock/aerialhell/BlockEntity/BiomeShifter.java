package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.BiomeShifterBlock;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public interface BiomeShifter
{
    int MIN_PROTECTION_DISTANCE = 2;
    int MAX_PROTECTION_DISTANCE = 100;

    enum ShiftType{CORRUPT, UNCORRUPT}

    int getFieldSize();
    ShiftType getShiftType();
    @Nullable Supplier<Block> getShiftedOrBrokenVariant();
    private int getRealFieldSize() {return Math.min(Math.max(MIN_PROTECTION_DISTANCE, this.getFieldSize()), MAX_PROTECTION_DISTANCE);}

    static void transformRandomBlocks(Level level, BlockPos pos, BlockState state, BiomeShifter blockEntity)
    {
        ShiftType shiftType = blockEntity.getShiftType();
        int fieldSize = blockEntity.getRealFieldSize(); RandomSource rand = level.random;
        int tryNumber = (int) (fieldSize * fieldSize * fieldSize * 1.0F/8.0F);
        boolean transformed; int transformedCount = 0;
        for (int i=0; i<tryNumber; i++)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(-fieldSize, fieldSize), rand.nextInt(-fieldSize, fieldSize), rand.nextInt(-fieldSize, fieldSize));
            if (!areSameBlockpos(pos, blockpos) && blockEntity.isValidBiomeShifterForPos(level, blockpos, pos) && level instanceof ServerLevel serverlevel)
            {
                transformed = false;
                if (level.getBlockEntity(blockpos) instanceof BiomeShifter otherBiomeShifter && shiftType != otherBiomeShifter.getShiftType() && blockEntity.getFieldSize() >= otherBiomeShifter.getFieldSize())
                {
                    BlockHelper.shiftBiomeShifterBlock(serverlevel, blockpos, shiftType);
                }

                if (shiftType == ShiftType.UNCORRUPT && BlockHelper.isCorrupted(level, blockpos))
                {
                    if (BlockHelper.uncorrupt(serverlevel, blockpos)) {transformed = true;}
                }
                else if (shiftType == ShiftType.CORRUPT && !BlockHelper.isCorrupted(level, blockpos) && BlockHelper.canBeCorrupted(level, blockpos, BlockHelper.CorruptionType.ANY))
                {
                    if (BlockHelper.corrupt(serverlevel, blockpos, BlockHelper.CorruptionType.ANY)) {transformed = true;}
                }

                if (transformed && transformedCount++ < 6) {sendTransformEffect(serverlevel, blockpos, shiftType);}
            }
        }
    }

    private static boolean areSameBlockpos(BlockPos pos1, BlockPos pos2) {return pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY() && pos1.getZ() == pos2.getZ();}

    private static void sendTransformEffect(ServerLevel serverlevel, BlockPos blockpos, ShiftType type)
    {
        RandomSource rand = serverlevel.random;
        ParticleOptions particle = type == ShiftType.CORRUPT ? AerialHellParticleTypes.SHADOW_LIGHT.get() : AerialHellParticleTypes.OSCILLATOR.get();
        SoundEvent sound = type == ShiftType.CORRUPT ? SoundEvents.LODESTONE_HIT : SoundEvents.WART_BLOCK_HIT;
        Vec3 vecpos = new Vec3(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5);
        serverlevel.sendParticles(particle, vecpos.x(), vecpos.y(), vecpos.z(), 5, 0.0, 0.0, 0.0, 1.0);
        serverlevel.playSound(null, vecpos.x(), vecpos.y(), vecpos.z(), sound, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F);
    }

    default boolean isValidBiomeShifterForPos(LevelReader level, BlockPos posToShift, BlockPos shifterPos)
    {
        return level.getBlockState(shifterPos).getBlock() instanceof BiomeShifterBlock && shifterPos.distSqr(posToShift) < this.getRealFieldSize() * this.getRealFieldSize();
    }
}
