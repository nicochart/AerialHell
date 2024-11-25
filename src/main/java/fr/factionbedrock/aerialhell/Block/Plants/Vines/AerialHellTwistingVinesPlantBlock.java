package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.*;
import net.minecraft.state.StateManager;

public class AerialHellTwistingVinesPlantBlock extends TwistingVinesPlantBlock
{
    public AerialHellTwistingVinesPlantBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(AerialHellGrassBlock.SHIFTED_RENDER, false));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(AerialHellGrassBlock.SHIFTED_RENDER);}

    @Override protected AbstractPlantStemBlock getStem()
    {
        if (this == AerialHellBlocks.LAZULI_ROOTS_PLANT) {return AerialHellBlocks.LAZULI_ROOTS;}
        else if (this == AerialHellBlocks.STELLAR_ROOTS_PLANT) {return AerialHellBlocks.STELLAR_ROOTS;}
        else if (this == AerialHellBlocks.DEAD_ROOTS_PLANT) {return AerialHellBlocks.DEAD_ROOTS;}
        else if (this == AerialHellBlocks.GLOWING_ROOTS_PLANT) {return AerialHellBlocks.GLOWING_ROOTS;}
        else /*if (this == AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT)*/ {return AerialHellBlocks.SHADOW_GLOWING_ROOTS;}
    }
}
