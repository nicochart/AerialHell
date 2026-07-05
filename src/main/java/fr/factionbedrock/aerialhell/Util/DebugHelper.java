package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class DebugHelper
{
    public static void sendDebugMessage(Level level, String message, boolean includeDefaultInfo)
    {
        long time = level.getGameTime();
        String chatMessage = includeDefaultInfo ? "[DEBUG - "+(level.isClientSide() ? "Client" : "Server")+" side - "+time+"] " + message : "[DEBUG] " + message;

        if (level.isClientSide())
        {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(chatMessage));
        }
        else if (level.getServer() != null)
        {
            level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(chatMessage), false);
        }
    }

    public static void generateFeatureDebug(FeaturePlaceContext<?> context)
    {
        WorldGenLevel reader = context.level();
        BlockPos centerOfFeature = FeatureHelper.getFeatureCenter(context);
        for (int dy = -50; dy <= 50; dy++)
        {
            reader.setBlock(centerOfFeature.offset(0, dy, 0), AerialHellBlocks.RED_SLIPPERY_SAND_GLASS.defaultBlockState(), 0);
        }
        for (int dxz = -24; dxz <= 24; dxz++)
        {
            reader.setBlock(centerOfFeature.offset(dxz, 0, 0), AerialHellBlocks.RED_SLIPPERY_SAND_GLASS.defaultBlockState(), 0);
            reader.setBlock(centerOfFeature.offset(0, 0, dxz), AerialHellBlocks.RED_SLIPPERY_SAND_GLASS.defaultBlockState(), 0);
        }

        //feature center
        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++)
            {
                for (int z = -1; z <= 1; z++)
                {
                    reader.setBlock(centerOfFeature.offset(x, y, z), AerialHellBlocks.ARSONIST_BLOCK.defaultBlockState(), 0);
                }
            }
        }

        //feature origin
        reader.setBlock(context.origin(), AerialHellBlocks.CRYSTAL_BRICKS.defaultBlockState(), 0);
    }
}
