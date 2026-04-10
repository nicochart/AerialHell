package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.TwistingVinesPlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class AerialHellTwistingVinesPlantBlock extends TwistingVinesPlantBlock
{
    public AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(AerialHellBooleanProperties.SHIFTED_RENDER, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AerialHellBooleanProperties.SHIFTED_RENDER);}

    @Override protected GrowingPlantHeadBlock getHeadBlock()
    {
        if (this == AerialHellBlocks.LAZULI_ROOTS_PLANT) {return AerialHellBlocks.LAZULI_ROOTS;}
        else if (this == AerialHellBlocks.STELLAR_ROOTS_PLANT) {return AerialHellBlocks.STELLAR_ROOTS;}
        else if (this == AerialHellBlocks.DEAD_ROOTS_PLANT) {return AerialHellBlocks.DEAD_ROOTS;}
        else if (this == AerialHellBlocks.GLOWING_ROOTS_PLANT) {return AerialHellBlocks.GLOWING_ROOTS;}
        else /*if (this == AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT)*/ {return AerialHellBlocks.SHADOW_GLOWING_ROOTS;}
    }
}
