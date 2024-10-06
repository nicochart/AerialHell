package fr.factionbedrock.aerialhell.Block.CorruptionProtectors;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifterBlockEntity;
import fr.factionbedrock.aerialhell.BlockEntity.ReactorBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BiomeShifterBlock extends BaseEntityBlock
{
    public final int fieldSize;
    protected final BiomeShifter.ShiftType shiftType;
    @Nullable private final Supplier<Block> shiftedOrBrokenVariant;
    public static final MapCodec<BiomeShifterBlock> CODEC = simpleCodec(BiomeShifterBlock::new);

    private BiomeShifterBlock(Properties prop) {this(prop, ReactorBlockEntity.MAX_PROTECTION_DISTANCE, BiomeShifter.ShiftType.UNCORRUPT, null);}

    public BiomeShifterBlock(Properties prop, int fieldSize, BiomeShifter.ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(prop);
        this.fieldSize = fieldSize;
        this.shiftType = shiftType;
        this.shiftedOrBrokenVariant = shiftedOrBrokenVariant;
    }

    @Override protected MapCodec<? extends BiomeShifterBlock> codec() {return CODEC;}
    @Nullable public Supplier<Block> getShiftedOrBrokenVariant() {return this.shiftedOrBrokenVariant;}
    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new BiomeShifterBlockEntity(pos, state, this.fieldSize, this.shiftType, this.shiftedOrBrokenVariant);}

    @Override protected RenderShape getRenderShape(BlockState pState) {return RenderShape.MODEL;}

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, AerialHellBlockEntities.BIOME_SHIFTER.get(), BiomeShifterBlockEntity::tick);
    }
}
