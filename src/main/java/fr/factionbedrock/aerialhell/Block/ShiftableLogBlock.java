package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ShiftableLogBlock extends PillarBlock
{
    private final Supplier<ShiftableLogBlock> shiftedVariant;
    private final BiomeShifter.ShiftType shiftType;
    public static final BooleanProperty SHIFTED_RENDER = AerialHellBooleanProperties.SHIFTED_RENDER; //only used for render purposes

    public ShiftableLogBlock(Settings settings, Supplier<ShiftableLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
    {
        super(settings);
        this.shiftedVariant = shiftedVariant;
        this.shiftType = shiftType;
        this.setDefaultState(this.getDefaultState().with(SHIFTED_RENDER, false));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);
        builder.add(SHIFTED_RENDER);
    }
    public Supplier<ShiftableLogBlock> getShiftedVariant() {return this.shiftedVariant;}
    public BiomeShifter.ShiftType getShiftType() {return this.shiftType;}

    @Nullable public static BlockState getShiftedState(BlockState beforeState)
    {
        if (beforeState.getBlock() instanceof ShiftableLogBlock beforeLogBlock)
        {
            if (beforeLogBlock.getShiftedVariant().get() instanceof ShiftableLogBlock nextLogBlock)
            {
                return nextLogBlock.getDefaultState().with(AXIS, beforeState.get(AXIS));
            }
            return beforeLogBlock.getShiftedVariant().get().getDefaultState();
        }
        return null;
    }
}
