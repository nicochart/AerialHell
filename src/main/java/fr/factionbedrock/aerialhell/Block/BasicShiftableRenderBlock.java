package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellStateProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class BasicShiftableRenderBlock extends Block
{
    public BasicShiftableRenderBlock(Properties prop)
    {
        super(prop);
        this.registerDefaultState(this.defaultBlockState().setValue(AerialHellStateProperties.SHIFTED_RENDER, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AerialHellStateProperties.SHIFTED_RENDER);}

}
