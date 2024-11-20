package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.TwistingVinesBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TwistingVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class AerialHellTwistingVinesBlock extends TwistingVinesBlock
{
    public AerialHellTwistingVinesBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(AerialHellGrassBlock.SHIFTED_RENDER, false));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {super.createBlockStateDefinition(builder); builder.add(AerialHellGrassBlock.SHIFTED_RENDER);}

    @Override protected Block getBodyBlock()
    {
        if (this == AerialHellBlocksAndItems.LAZULI_ROOTS.get()) {return AerialHellBlocksAndItems.LAZULI_ROOTS_PLANT.get();}
        else if (this == AerialHellBlocksAndItems.STELLAR_ROOTS.get()) {return AerialHellBlocksAndItems.STELLAR_ROOTS_PLANT.get();}
        else if (this == AerialHellBlocksAndItems.DEAD_ROOTS.get()) {return AerialHellBlocksAndItems.DEAD_ROOTS_PLANT.get();}
        else if (this == AerialHellBlocksAndItems.GLOWING_ROOTS.get()) {return AerialHellBlocksAndItems.GLOWING_ROOTS_PLANT.get();}
        else /*if (this == AerialHellBlocksAndItems.SHADOW_GLOWING_ROOTS.get())*/ {return AerialHellBlocksAndItems.SHADOW_GLOWING_ROOTS_PLANT.get();}
    }
}
