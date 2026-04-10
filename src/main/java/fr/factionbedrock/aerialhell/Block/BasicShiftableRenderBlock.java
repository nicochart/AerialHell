package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class BasicShiftableRenderBlock extends Block
{
    public BasicShiftableRenderBlock(BlockBehaviour.Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(AerialHellBooleanProperties.SHIFTED_RENDER, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AerialHellBooleanProperties.SHIFTED_RENDER);}
}
