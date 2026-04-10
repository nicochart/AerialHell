package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TwistingVinesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class AerialHellTwistingVinesBlock extends TwistingVinesBlock
{
    public AerialHellTwistingVinesBlock(BlockBehaviour.Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(AerialHellBooleanProperties.SHIFTED_RENDER, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {super.createBlockStateDefinition(builder); builder.add(AerialHellBooleanProperties.SHIFTED_RENDER);}

    @Override protected Block getBodyBlock()
    {
        if (this == AerialHellBlocks.LAZULI_ROOTS) {return AerialHellBlocks.LAZULI_ROOTS_PLANT;}
        else if (this == AerialHellBlocks.STELLAR_ROOTS) {return AerialHellBlocks.STELLAR_ROOTS_PLANT;}
        else if (this == AerialHellBlocks.DEAD_ROOTS) {return AerialHellBlocks.DEAD_ROOTS_PLANT;}
        else if (this == AerialHellBlocks.GLOWING_ROOTS) {return AerialHellBlocks.GLOWING_ROOTS_PLANT;}
        else /*if (this == AerialHellBlocks.SHADOW_GLOWING_ROOTS)*/ {return AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT;}
    }
}
