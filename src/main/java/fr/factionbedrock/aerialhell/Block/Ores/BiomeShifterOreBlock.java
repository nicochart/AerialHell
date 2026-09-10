package fr.factionbedrock.aerialhell.Block.Ores;

import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.BiomeShifterBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BiomeShifterOreBlock extends BiomeShifterBlock
{
    private final IntProvider xpRange;

    public BiomeShifterOreBlock(int minExpDropped, int maxExpDropped, Properties prop, int fieldSize, BiomeShifter.ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant) {super(prop, fieldSize, shiftType, shiftedOrBrokenVariant); this.xpRange = UniformInt.of(minExpDropped, maxExpDropped);}

    //copy of vanilla DropExperienceBlock class method
    @Override public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel)
    {
        return silkTouchLevel == 0 ? this.xpRange.sample(randomSource) : 0;
    }
}
