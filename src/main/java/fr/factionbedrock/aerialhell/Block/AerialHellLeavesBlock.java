package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class AerialHellLeavesBlock extends LeavesBlock
{
    private final Supplier<AerialHellLeavesBlock> shiftedVariant;
    private final BiomeShifter.ShiftType shiftType;

    public AerialHellLeavesBlock(Properties prop, Supplier<AerialHellLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
    {
        super(prop);
        this.shiftedVariant = shiftedVariant;
        this.shiftType = shiftType;
    }

    public Supplier<AerialHellLeavesBlock> getShiftedVariant() {return this.shiftedVariant;}
    public BiomeShifter.ShiftType getShiftType() {return this.shiftType;}

    @Nullable
    public static BlockState getShiftedState(BlockState beforeState)
    {
        if (beforeState.getBlock() instanceof AerialHellLeavesBlock beforeLeavesBlock)
        {
            if (beforeLeavesBlock.getShiftedVariant().get() instanceof AerialHellLeavesBlock nextLeavesBlock)
            {
                return nextLeavesBlock.defaultBlockState().setValue(DISTANCE, beforeState.getValue(DISTANCE)).setValue(PERSISTENT, beforeState.getValue(PERSISTENT)).setValue(WATERLOGGED, beforeState.getValue(WATERLOGGED));
            }
            return beforeLeavesBlock.getShiftedVariant().get().defaultBlockState();
        }
        return null;
    }
}
