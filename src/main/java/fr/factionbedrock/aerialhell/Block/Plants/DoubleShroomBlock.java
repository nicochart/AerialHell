package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Util.BlockHelper;
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
            boolean brightnessFlag = reader.getRawBrightness(pos, 0) < 13 && reader.getBlockState(pos.below()).canSustainPlant(reader, pos.below(), Direction.UP, this);
            boolean solidSurfaceAbove = BlockHelper.hasAnySolidBlockAbove(reader, pos) && BlockHelper.hasAnySolidBlockAbove(reader, pos.offset(3, 0, 3)) && BlockHelper.hasAnySolidBlockAbove(reader, pos.offset(3, 0, -3)) && BlockHelper.hasAnySolidBlockAbove(reader, pos.offset(-3, 0, 3)) && BlockHelper.hasAnySolidBlockAbove(reader, pos.offset(-3, 0, -3));
            return solidSurfaceAbove && brightnessFlag && super.canSurvive(state, reader, pos);
        }
        return super.canSurvive(state, reader, pos);
    }
}
