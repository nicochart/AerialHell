package fr.factionbedrock.aerialhell.Block.CorruptionProtectors;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifterBlockEntity;
import fr.factionbedrock.aerialhell.BlockEntity.ReactorBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BiomeShifterBlock extends BlockWithEntity
{
    public final int fieldSize;
    protected final BiomeShifter.ShiftType shiftType;
    @Nullable private final Supplier<Block> shiftedOrBrokenVariant;
    public static final MapCodec<BiomeShifterBlock> CODEC = createCodec(BiomeShifterBlock::new);

    private BiomeShifterBlock(AbstractBlock.Settings settings) {this(settings, ReactorBlockEntity.MAX_PROTECTION_DISTANCE, BiomeShifter.ShiftType.UNCORRUPT, null);}

    public BiomeShifterBlock(AbstractBlock.Settings settings, int fieldSize, BiomeShifter.ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant)
    {
        super(settings);
        this.fieldSize = fieldSize;
        this.shiftType = shiftType;
        this.shiftedOrBrokenVariant = shiftedOrBrokenVariant;
    }

    @Override protected MapCodec<? extends BiomeShifterBlock> getCodec() {return CODEC;}
    @Nullable public Supplier<Block> getShiftedOrBrokenVariant() {return this.shiftedOrBrokenVariant;}
    @Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new BiomeShifterBlockEntity(pos, state, this.fieldSize, this.shiftType, this.shiftedOrBrokenVariant);}

    @Override protected BlockRenderType getRenderType(BlockState pState) {return BlockRenderType.MODEL;}

    @Nullable @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClient() ? null : validateTicker(type, AerialHellBlockEntities.BIOME_SHIFTER, BiomeShifterBlockEntity::tick);
    }
}
