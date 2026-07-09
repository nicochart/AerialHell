package fr.factionbedrock.aerialhell.Block.Ores;

import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.BiomeShifterBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class BiomeShifterOreBlock extends BiomeShifterBlock
{
    private final UniformInt xpRange;

    public BiomeShifterOreBlock(int minExpDropped, int maxExpDropped, BlockBehaviour.Properties settings, int fieldSize, BiomeShifter.ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant) {super(settings, fieldSize, shiftType, shiftedOrBrokenVariant); this.xpRange = UniformInt.of(minExpDropped, maxExpDropped);}

    //copy of net.minecraft.block.ExperienceDroppingBlock class
    @Override
    protected void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack tool, boolean dropExperience)
    {
        if (dropExperience)
        {
            this.tryDropExperience(world, pos, tool, xpRange);
        }
    }
}
