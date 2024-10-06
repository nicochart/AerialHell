package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ShiftableLogBlock extends RotatedPillarBlock
{
    private final Supplier<ShiftableLogBlock> shiftedVariant;
    private final BiomeShifter.ShiftType shiftType;

    public ShiftableLogBlock(Properties prop, Supplier<ShiftableLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
    {
        super(prop);
        this.shiftedVariant = shiftedVariant;
        this.shiftType = shiftType;
    }

    public Supplier<ShiftableLogBlock> getShiftedVariant() {return this.shiftedVariant;}
    public BiomeShifter.ShiftType getShiftType() {return this.shiftType;}

    @Nullable public static BlockState getShiftedState(BlockState beforeState)
    {
        if (beforeState.getBlock() instanceof ShiftableLogBlock beforeLogBlock)
        {
            if (beforeLogBlock.getShiftedVariant().get() instanceof ShiftableLogBlock nextLogBlock)
            {
                return nextLogBlock.defaultBlockState().setValue(AXIS, beforeState.getValue(AXIS));
            }
            return beforeLogBlock.getShiftedVariant().get().defaultBlockState();
        }
        return null;
    }
}
