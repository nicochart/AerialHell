package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import org.jetbrains.annotations.Nullable;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

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

    @Override protected void saveAdditional(ValueOutput view)
    {
        super.saveAdditional(view);
        view.putInt("field_size", fieldSize);
        boolean isShadow = this.shiftType == ShiftType.CORRUPT;
        view.putBoolean("is_shadow", isShadow);
    }

    @Override protected void loadAdditional(ValueInput view)
    {
        super.loadAdditional(view);
        this.fieldSize = view.getInt("field_size").get();
        boolean isShadow = view.getBooleanOr("is_shadow", false);
        this.shiftType = isShadow ? ShiftType.CORRUPT : ShiftType.UNCORRUPT;
    }
}
