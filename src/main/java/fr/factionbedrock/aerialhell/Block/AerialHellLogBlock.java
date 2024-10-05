package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class AerialHellLogBlock extends RotatedPillarBlock
{
    private final Supplier<AerialHellLogBlock> shiftedVariant;
    private final BiomeShifter.ShiftType shiftType;

    public AerialHellLogBlock(Properties prop, Supplier<AerialHellLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
    {
        super(prop);
        this.shiftedVariant = shiftedVariant;
        this.shiftType = shiftType;
    }

    public Supplier<AerialHellLogBlock> getShiftedVariant() {return this.shiftedVariant;}
    public BiomeShifter.ShiftType getShiftType() {return this.shiftType;}

    @Nullable public static BlockState getShiftedState(BlockState beforeState)
    {
        if (beforeState.getBlock() instanceof AerialHellLogBlock beforeLogBlock)
        {
            if (beforeLogBlock.getShiftedVariant().get() instanceof AerialHellLogBlock nextLogBlock)
            {
                return nextLogBlock.defaultBlockState().setValue(AXIS, beforeState.getValue(AXIS));
            }
            return beforeLogBlock.getShiftedVariant().get().defaultBlockState();
        }
        return null;
    }
}
