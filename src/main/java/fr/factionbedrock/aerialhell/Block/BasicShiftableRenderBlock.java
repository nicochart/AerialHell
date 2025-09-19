package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;

public class BasicShiftableRenderBlock extends Block
{
    public BasicShiftableRenderBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(AerialHellBooleanProperties.SHIFTED_RENDER, false));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(AerialHellBooleanProperties.SHIFTED_RENDER);}
}
