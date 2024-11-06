package fr.factionbedrock.aerialhell.Block.Plants.Vines;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.TwistingVinesPlantBlock;

public class AerialHellTwistingVinesPlantBlock extends TwistingVinesPlantBlock
{
    public AerialHellTwistingVinesPlantBlock(Properties properties) {super(properties);}

    @Override protected GrowingPlantHeadBlock getHeadBlock()
    {
        if (this == AerialHellBlocksAndItems.LAZULI_ROOTS_PLANT.get()) {return AerialHellBlocksAndItems.LAZULI_ROOTS.get();}
        else if (this == AerialHellBlocksAndItems.STELLAR_ROOTS_PLANT.get()) {return AerialHellBlocksAndItems.STELLAR_ROOTS.get();}
        else if (this == AerialHellBlocksAndItems.DEAD_ROOTS_PLANT.get()) {return AerialHellBlocksAndItems.DEAD_ROOTS.get();}
        else if (this == AerialHellBlocksAndItems.GLOWING_ROOTS_PLANT.get()) {return AerialHellBlocksAndItems.GLOWING_ROOTS.get();}
        else /*if (this == AerialHellBlocksAndItems.SHADOW_GLOWING_ROOTS_PLANT.get())*/ {return AerialHellBlocksAndItems.SHADOW_GLOWING_ROOTS.get();}
    }
}
