package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TallShroomBlock extends TallFlowerBlock
{
    public TallShroomBlock(Properties prop) {super(prop);}

    @Override
    public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state)
    {
        if (rand.nextInt(3) == 0)
        {
            popResource(level, pos, new ItemStack(this));
        }
        else {}
    }
}