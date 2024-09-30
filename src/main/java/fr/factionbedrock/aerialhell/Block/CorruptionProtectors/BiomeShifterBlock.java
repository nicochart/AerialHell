package fr.factionbedrock.aerialhell.Block.CorruptionProtectors;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifterBlockEntity;
import fr.factionbedrock.aerialhell.BlockEntity.ReactorBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BiomeShifterBlock extends BaseEntityBlock
{
    public final int fieldSize;
    protected final BiomeShifter.ShiftType shiftType;
    public static final MapCodec<BiomeShifterBlock> CODEC = simpleCodec(BiomeShifterBlock::new);

    private BiomeShifterBlock(Properties prop) {this(prop, ReactorBlockEntity.MAX_PROTECTION_DISTANCE, BiomeShifter.ShiftType.UNCORRUPT);}

    public BiomeShifterBlock(Properties prop, int fieldSize, BiomeShifter.ShiftType shiftType)
    {
        super(prop);
        this.fieldSize = fieldSize;
        this.shiftType = shiftType;
    }

    @Override protected MapCodec<? extends BiomeShifterBlock> codec() {return CODEC;}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new BiomeShifterBlockEntity(pos, state, this.fieldSize, this.shiftType);}

    @Override protected RenderShape getRenderShape(BlockState pState) {return RenderShape.MODEL;}

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : createTickerHelper(type, AerialHellBlockEntities.BIOME_SHIFTER.get(), BiomeShifterBlockEntity::tick);
    }
}
