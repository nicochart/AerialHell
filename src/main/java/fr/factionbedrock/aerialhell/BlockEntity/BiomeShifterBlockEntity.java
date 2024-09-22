package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BiomeShifterBlockEntity extends BlockEntity implements BiomeShifter
{
    private int fieldSize;

    public BiomeShifterBlockEntity(BlockPos pos, BlockState blockState, int fieldSize)
    {
        super(AerialHellBlockEntities.BIOME_SHIFTER.get(), pos, blockState);
        this.fieldSize = fieldSize;
    }

    @Override public int getFieldSize() {return this.fieldSize;}

    public static void tick(Level level, BlockPos pos, BlockState state, BiomeShifterBlockEntity blockEntity)
    {
        BiomeShifter.transformRandomBlocks(level, pos, state, blockEntity);
    }

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        tag.putInt("field_size", fieldSize);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        this.fieldSize = tag.getInt("field_size");
    }
}
