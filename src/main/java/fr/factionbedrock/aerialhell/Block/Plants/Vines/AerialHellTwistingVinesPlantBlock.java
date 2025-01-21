package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.TwistingVinesPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class AerialHellTwistingVinesPlantBlock extends TwistingVinesPlantBlock
{
    public AerialHellTwistingVinesPlantBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AerialHellGrassBlock.SHIFTED_RENDER, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AerialHellGrassBlock.SHIFTED_RENDER);}

    @Override protected GrowingPlantHeadBlock getHeadBlock()
    {
        if (this == AerialHellBlocks.LAZULI_ROOTS_PLANT.get()) {return AerialHellBlocks.LAZULI_ROOTS.get();}
        else if (this == AerialHellBlocks.STELLAR_ROOTS_PLANT.get()) {return AerialHellBlocks.STELLAR_ROOTS.get();}
        else if (this == AerialHellBlocks.DEAD_ROOTS_PLANT.get()) {return AerialHellBlocks.DEAD_ROOTS.get();}
        else if (this == AerialHellBlocks.GLOWING_ROOTS_PLANT.get()) {return AerialHellBlocks.GLOWING_ROOTS.get();}
        else /*if (this == AerialHellBlocksAndItems.SHADOW_GLOWING_ROOTS_PLANT.get())*/ {return AerialHellBlocks.SHADOW_GLOWING_ROOTS.get();}
    }
}
