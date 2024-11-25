package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import java.util.function.Supplier;

public class BiomeShifterBlockEntity extends BlockEntity implements BiomeShifter
{
    private int fieldSize;
    private ShiftType shiftType;
    @Nullable private final Supplier<Block> shiftedOrBrokenVariant;

    public BiomeShifterBlockEntity(BlockPos pos, BlockState blockState, int fieldSize, ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(AerialHellBlockEntities.BIOME_SHIFTER.get(), pos, blockState);
        this.fieldSize = fieldSize;
        this.shiftType = shiftType;
        this.shiftedOrBrokenVariant = shiftedOrBrokenVariant;
    }

    @Override public int getFieldSize() {return this.fieldSize;}
    @Override public ShiftType getShiftType() {return this.shiftType;}
    @Override @Nullable public Supplier<Block> getShiftedOrBrokenVariant() {return this.shiftedOrBrokenVariant;}

    public static void tick(World world, BlockPos pos, BlockState state, BiomeShifterBlockEntity blockEntity)
    {
        BiomeShifter.transformRandomBlocks(world, pos, state, blockEntity);
    }

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        tag.putInt("field_size", fieldSize);
        boolean isShadow = this.shiftType == ShiftType.CORRUPT;
        tag.putBoolean("is_shadow", isShadow);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        this.fieldSize = tag.getInt("field_size");
        boolean isShadow = tag.getBoolean("is_shadow");
        this.shiftType = isShadow ? ShiftType.CORRUPT : ShiftType.UNCORRUPT;
    }
}
