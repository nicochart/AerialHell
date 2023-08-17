package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class DoubleShroomBlock extends DoublePlantBlock
{
    public DoubleShroomBlock(Properties prop) {super(prop);}

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos)
    {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER)
        {
            return reader.getRawBrightness(pos, 0) < 13 && reader.getBlockState(pos.below()).canSustainPlant(reader, pos.below(), Direction.UP, this) && super.canSurvive(state, reader, pos);
        }
        return super.canSurvive(state, reader, pos);
    }
}
