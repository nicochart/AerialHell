package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
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
        if (this == AerialHellBlocksAndItems.LAZULI_ROOTS_PLANT.get()) {return AerialHellBlocksAndItems.LAZULI_ROOTS.get();}
        else if (this == AerialHellBlocksAndItems.STELLAR_ROOTS_PLANT.get()) {return AerialHellBlocksAndItems.STELLAR_ROOTS.get();}
        else if (this == AerialHellBlocksAndItems.DEAD_ROOTS_PLANT.get()) {return AerialHellBlocksAndItems.DEAD_ROOTS.get();}
        else if (this == AerialHellBlocksAndItems.GLOWING_ROOTS_PLANT.get()) {return AerialHellBlocksAndItems.GLOWING_ROOTS.get();}
        else /*if (this == AerialHellBlocksAndItems.SHADOW_GLOWING_ROOTS_PLANT.get())*/ {return AerialHellBlocksAndItems.SHADOW_GLOWING_ROOTS.get();}
    }
}
