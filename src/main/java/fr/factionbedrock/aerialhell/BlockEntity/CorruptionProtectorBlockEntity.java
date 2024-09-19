package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.CorruptionProtectorBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Util.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CorruptionProtectorBlockEntity extends BlockEntity
{
    public static int MAX_PROTECTION_DISTANCE = 100;
    private int protection_distance;

    public CorruptionProtectorBlockEntity(BlockPos pos, BlockState blockState, int protectionDistance)
    {
        super(AerialHellBlockEntities.CORRUPTION_PROTECTOR_BLOCK.get(), pos, blockState);
        this.protection_distance = protectionDistance;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CorruptionProtectorBlockEntity blockEntity)
    {
        int protection_distance = blockEntity.getProtectionDistance(); RandomSource rand = level.random;
        int try_number = (int) (protection_distance * protection_distance * protection_distance * 1.0F/8.0F);
        for (int i=0; i<try_number; i++)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(-protection_distance, protection_distance), rand.nextInt(-protection_distance, protection_distance), rand.nextInt(-protection_distance, protection_distance));
            if (blockEntity.isValidProtectorForPos(level, blockpos, pos) && BlockHelper.isCorrupted(level, blockpos) && level instanceof ServerLevel serverlevel)
            {
                BlockHelper.uncorrupt(serverlevel, blockpos);
            }
        }
    }

    public boolean isValidProtectorForPos(LevelReader level, BlockPos posToProtect, BlockPos protectorPos)
    {
        return level.getBlockState(protectorPos).getBlock() instanceof CorruptionProtectorBlock && protectorPos.distSqr(posToProtect) < getProtectionDistance() * getProtectionDistance();
    }

    public int getProtectionDistance() {return Math.min(Math.max(0, protection_distance), MAX_PROTECTION_DISTANCE);}

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        tag.putInt("protection_distance", protection_distance);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        this.protection_distance = tag.getInt("protection_distance");
    }
}
