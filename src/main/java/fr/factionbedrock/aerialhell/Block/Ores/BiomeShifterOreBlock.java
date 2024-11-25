package fr.factionbedrock.aerialhell.Block.Ores;

import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.BiomeShifterBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BiomeShifterOreBlock extends BiomeShifterBlock
{
    private final UniformIntProvider xpRange;

    public BiomeShifterOreBlock(int minExpDropped, int maxExpDropped, AbstractBlock.Settings settings, int fieldSize, BiomeShifter.ShiftType shiftType, @Nullable Supplier<Block> shiftedOrBrokenVariant) {super(settings, fieldSize, shiftType, shiftedOrBrokenVariant); this.xpRange = UniformIntProvider.create(minExpDropped, maxExpDropped);}

    //copy of net.minecraft.block.ExperienceDroppingBlock class
    @Override
    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience)
    {
        if (dropExperience)
        {
            this.dropExperienceWhenMined(world, pos, tool, xpRange);
        }
    }
}
