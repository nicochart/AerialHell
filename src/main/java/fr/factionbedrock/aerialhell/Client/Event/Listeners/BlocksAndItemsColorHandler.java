package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlocksAndItemsColorHandler
{
    @SubscribeEvent
    public static void handleBlockColors(ColorHandlerEvent.Block event)
    {
        event.getBlockColors().register((state, world, pos, tint) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : new Color(12, 35, 26).getRGB(),
                AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get(),
                AerialHellBlocksAndItems.STELLAR_FERN.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_FERN.get(),
                AerialHellBlocksAndItems.BRAMBLES.get(),
                AerialHellBlocksAndItems.PURPLISH_STELLAR_GRASS.get(),
                AerialHellBlocksAndItems.BLACK_ROSE.get(),
                AerialHellBlocksAndItems.BLUE_FLOWER.get(),
                AerialHellBlocksAndItems.BELLFLOWER.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_WALL.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_WALL.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_SLAB.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_SLAB.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_STAIRS.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_STAIRS.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_WALL.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_SLAB.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_STAIRS.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get()
        );
    }

    @SubscribeEvent
    public static void handleItemColors(ColorHandlerEvent.Item event)
    {
        event.getItemColors().register((stack, color) -> new Color(50, 140, 102).getRGB(),
                AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_GRASS_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_GRASS_BALL_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_FERN_ITEM.get(),
                AerialHellBlocksAndItems.STELLAR_TALL_FERN_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_WALL_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_WALL_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_SLAB_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_SLAB_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_STAIRS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_STAIRS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_WALL_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_SLAB_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_STAIRS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM.get(),
                AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM.get()
        );
    }
}