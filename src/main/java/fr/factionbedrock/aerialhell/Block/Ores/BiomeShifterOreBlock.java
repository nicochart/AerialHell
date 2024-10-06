package fr.factionbedrock.aerialhell.Block.Ores;

import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.BiomeShifterBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BiomeShifterOreBlock extends BiomeShifterBlock
{
    private final IntProvider xpRange;

    public BiomeShifterOreBlock(int minExpDropped, int maxExpDropped, Properties prop, int fieldSize, BiomeShifter.ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant) {super(prop, fieldSize, shiftType, shiftedOrBrokenVariant); this.xpRange = UniformInt.of(minExpDropped, maxExpDropped);}

    //copy of vanilla DropExperienceBlock class method
    @Override public int getExpDrop(BlockState state, LevelAccessor level, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity breaker, ItemStack tool)
    {
        return this.xpRange.sample(level.getRandom());
    }
}
