package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class TallShroomBlock extends TallFlowerBlock
{
    public TallShroomBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override
    public void grow(ServerWorld level, Random rand, BlockPos pos, BlockState state)
    {
        if (rand.nextInt(3) == 0)
        {
            dropStack(level, pos, new ItemStack(this));
        }
        else {}
    }
}