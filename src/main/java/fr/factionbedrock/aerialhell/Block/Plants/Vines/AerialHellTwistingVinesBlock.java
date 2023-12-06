package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TwistingVinesBlock;

public class AerialHellTwistingVinesBlock extends TwistingVinesBlock
{
    public AerialHellTwistingVinesBlock(Properties properties) {super(properties);}

    @Override protected Block getBodyBlock()
    {
        if (this == AerialHellBlocksAndItems.LAZULI_ROOTS.get()) {return AerialHellBlocksAndItems.LAZULI_ROOTS_PLANT.get();}
        else if (this == AerialHellBlocksAndItems.STELLAR_ROOTS.get()) {return AerialHellBlocksAndItems.STELLAR_ROOTS_PLANT.get();}
        else if (this == AerialHellBlocksAndItems.DEAD_ROOTS.get()) {return AerialHellBlocksAndItems.DEAD_ROOTS_PLANT.get();}
        else /*if (this == AerialHellBlocksAndItems.GLOWING_ROOTS.get())*/ {return AerialHellBlocksAndItems.GLOWING_ROOTS_PLANT.get();}
    }
}
