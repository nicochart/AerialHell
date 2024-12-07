package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.BiomeShifterBlock;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
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

    static void transformRandomBlocks(World world, BlockPos pos, BlockState state, BiomeShifter blockEntity)
    {
        ShiftType shiftType = blockEntity.getShiftType();
        int fieldSize = blockEntity.getRealFieldSize(); Random rand = world.random;
        int tryNumber = (int) (fieldSize * fieldSize * fieldSize * 1.0F/8.0F);
        boolean transformed; int transformedCount = 0;
        for (int i=0; i<tryNumber; i++)
        {
            BlockPos blockpos = pos.add(rand.nextBetween(-fieldSize, fieldSize), rand.nextBetween(-fieldSize, fieldSize), rand.nextBetween(-fieldSize, fieldSize));
            if (!areSameBlockpos(pos, blockpos) && blockEntity.isValidBiomeShifterForPos(world, blockpos, pos) && world instanceof ServerWorld serverworld)
            {
                transformed = false;
                if (world.getBlockEntity(blockpos) instanceof BiomeShifter otherBiomeShifter && shiftType != otherBiomeShifter.getShiftType() && blockEntity.getFieldSize() >= otherBiomeShifter.getFieldSize())
                {
                    BlockHelper.shiftBiomeShifterBlock(serverworld, blockpos, shiftType);
                }

                if (shiftType == ShiftType.UNCORRUPT && BlockHelper.isCorrupted(world, blockpos))
                {
                    if (BlockHelper.uncorrupt(serverworld, blockpos)) {transformed = true;}
                }
                else if (shiftType == ShiftType.CORRUPT && !BlockHelper.isCorrupted(world, blockpos) && BlockHelper.canBeCorrupted(world, blockpos, BlockHelper.CorruptionType.ANY))
                {
                    if (BlockHelper.corrupt(serverworld, blockpos, BlockHelper.CorruptionType.ANY)) {transformed = true;}
                }

                if (transformed && transformedCount++ < 6) {sendTransformEffect(serverworld, blockpos, shiftType);}
            }
        }
    }

    private static boolean areSameBlockpos(BlockPos pos1, BlockPos pos2) {return pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY() && pos1.getZ() == pos2.getZ();}

    private static void sendTransformEffect(ServerWorld serverworld, BlockPos blockpos, ShiftType type)
    {
        Random rand = serverworld.random;
        ParticleEffect particle = type == ShiftType.CORRUPT ? AerialHellParticleTypes.SHADOW_LIGHT : AerialHellParticleTypes.OSCILLATOR;
        SoundEvent sound = type == ShiftType.CORRUPT ? SoundEvents.BLOCK_LODESTONE_HIT : SoundEvents.BLOCK_WART_BLOCK_HIT;
        Vec3d vecpos = new Vec3d(blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5);
        serverworld.spawnParticles(particle, vecpos.getX(), vecpos.getY(), vecpos.getZ(), 5, 0.0, 0.0, 0.0, 1.0);
        serverworld.playSound(null, vecpos.getX(), vecpos.getY(), vecpos.getZ(), sound, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F);
    }

    default boolean isValidBiomeShifterForPos(WorldView world, BlockPos posToShift, BlockPos shifterPos)
    {
        return world.getBlockState(shifterPos).getBlock() instanceof BiomeShifterBlock && shifterPos.getSquaredDistance(posToShift) < this.getRealFieldSize() * this.getRealFieldSize();
    }
}
