package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class ShiftableLogBlock extends RotatedPillarBlock
{
    private final Supplier<ShiftableLogBlock> shiftedVariant;
    private final BiomeShifter.ShiftType shiftType;
    public static final BooleanProperty SHIFTED_RENDER = AerialHellBooleanProperties.SHIFTED_RENDER; //only used for render purposes

    public ShiftableLogBlock(Properties settings, Supplier<ShiftableLogBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
    {
        super(settings);
        this.shiftedVariant = shiftedVariant;
        this.shiftType = shiftType;
        this.registerDefaultState(this.defaultBlockState().setValue(SHIFTED_RENDER, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
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
                return nextLogBlock.defaultBlockState().setValue(AXIS, beforeState.getValue(AXIS));
            }
            return beforeLogBlock.getShiftedVariant().get().defaultBlockState();
        }
        return null;
    }
}
