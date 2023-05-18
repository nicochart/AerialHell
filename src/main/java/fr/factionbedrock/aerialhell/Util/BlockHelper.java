package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockHelper
{
    public static boolean isAerialHellPortalFrameBlock(BlockState blockstate)
    {
        return blockstate.is(AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK.get());
    }

    public static Block getAerialHellPortalFrameBlock()
    {
        return AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK.get();
    }
}
