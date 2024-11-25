package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TwistingVinesBlock;
import net.minecraft.state.StateManager;

public class AerialHellTwistingVinesBlock extends TwistingVinesBlock
{
    public AerialHellTwistingVinesBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(AerialHellGrassBlock.SHIFTED_RENDER, false));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {super.appendProperties(builder); builder.add(AerialHellGrassBlock.SHIFTED_RENDER);}

    @Override protected Block getPlant()
    {
        if (this == AerialHellBlocks.LAZULI_ROOTS) {return AerialHellBlocks.LAZULI_ROOTS_PLANT;}
        else if (this == AerialHellBlocks.STELLAR_ROOTS) {return AerialHellBlocks.STELLAR_ROOTS_PLANT;}
        else if (this == AerialHellBlocks.DEAD_ROOTS) {return AerialHellBlocks.DEAD_ROOTS_PLANT;}
        else if (this == AerialHellBlocks.GLOWING_ROOTS) {return AerialHellBlocks.GLOWING_ROOTS_PLANT;}
        else /*if (this == AerialHellBlocks.SHADOW_GLOWING_ROOTS)*/ {return AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT;}
    }
}
