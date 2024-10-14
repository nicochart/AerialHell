package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ShiftableLeavesBlock extends LeavesBlock
{
    private final Supplier<ShiftableLeavesBlock> shiftedVariant;
    private final BiomeShifter.ShiftType shiftType;
    public static final BooleanProperty SHIFTED_RENDER = BooleanProperty.create("shifted_render"); //only used for render purposes

    public ShiftableLeavesBlock(Properties prop, Supplier<ShiftableLeavesBlock> shiftedVariant, BiomeShifter.ShiftType shiftType)
    {
        super(prop);
        this.shiftedVariant = shiftedVariant;
        this.shiftType = shiftType;
        this.registerDefaultState(this.defaultBlockState().setValue(SHIFTED_RENDER, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(SHIFTED_RENDER);
    }

    public Supplier<ShiftableLeavesBlock> getShiftedVariant() {return this.shiftedVariant;}
    public BiomeShifter.ShiftType getShiftType() {return this.shiftType;}

    @Nullable
    public static BlockState getShiftedState(BlockState beforeState)
    {
        if (beforeState.getBlock() instanceof ShiftableLeavesBlock beforeLeavesBlock)
        {
            if (beforeLeavesBlock.getShiftedVariant().get() instanceof ShiftableLeavesBlock nextLeavesBlock)
            {
                return nextLeavesBlock.defaultBlockState().setValue(DISTANCE, beforeState.getValue(DISTANCE)).setValue(PERSISTENT, beforeState.getValue(PERSISTENT)).setValue(WATERLOGGED, beforeState.getValue(WATERLOGGED));
            }
            return beforeLeavesBlock.getShiftedVariant().get().defaultBlockState();
        }
        return null;
    }
}
