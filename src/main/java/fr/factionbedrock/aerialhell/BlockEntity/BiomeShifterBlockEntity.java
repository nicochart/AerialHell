package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
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

    public static void tick(Level level, BlockPos pos, BlockState state, BiomeShifterBlockEntity blockEntity)
    {
        BiomeShifter.transformRandomBlocks(level, pos, state, blockEntity);
    }

    @Override protected void saveAdditional(ValueOutput valueOutput)
    {
        super.saveAdditional(valueOutput);
        valueOutput.putInt("field_size", fieldSize);
        boolean isShadow = this.shiftType == ShiftType.CORRUPT;
        valueOutput.putBoolean("is_shadow", isShadow);
    }

    @Override protected void loadAdditional(ValueInput valueInput)
    {
        super.loadAdditional(valueInput);
        this.fieldSize = valueInput.getInt("field_size").get();
        boolean isShadow = valueInput.getBooleanOr("is_shadow", false);
        this.shiftType = isShadow ? ShiftType.CORRUPT : ShiftType.UNCORRUPT;
    }
}
