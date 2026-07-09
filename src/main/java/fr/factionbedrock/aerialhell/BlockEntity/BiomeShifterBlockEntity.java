package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import org.jetbrains.annotations.Nullable;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BiomeShifterBlockEntity extends BlockEntity implements BiomeShifter
{
    private int fieldSize;
    private ShiftType shiftType;
    @Nullable private final Supplier<Block> shiftedOrBrokenVariant;

    public BiomeShifterBlockEntity(BlockPos pos, BlockState blockState, int fieldSize, ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(AerialHellBlockEntities.BIOME_SHIFTER, pos, blockState);
        this.fieldSize = fieldSize;
        this.shiftType = shiftType;
        this.shiftedOrBrokenVariant = shiftedOrBrokenVariant;
    }

    @Override public int getFieldSize() {return this.fieldSize;}
    @Override public ShiftType getShiftType() {return this.shiftType;}
    @Override @Nullable public Supplier<Block> getShiftedOrBrokenVariant() {return this.shiftedOrBrokenVariant;}

    public static void tick(Level world, BlockPos pos, BlockState state, BiomeShifterBlockEntity blockEntity)
    {
        BiomeShifter.transformRandomBlocks(world, pos, state, blockEntity);
    }

    @Override protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.saveAdditional(nbt, registryLookup);
        nbt.putInt("field_size", fieldSize);
        boolean isShadow = this.shiftType == ShiftType.CORRUPT;
        nbt.putBoolean("is_shadow", isShadow);
    }

    @Override protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.loadAdditional(nbt, registryLookup);
        this.fieldSize = nbt.getInt("field_size");
        boolean isShadow = nbt.getBoolean("is_shadow");
        this.shiftType = isShadow ? ShiftType.CORRUPT : ShiftType.UNCORRUPT;
    }
}
