package fr.factionbedrock.aerialhell.Client;

import java.awt.Color;

import com.google.common.base.Supplier;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
@OnlyIn(Dist.CLIENT)
public class AerialHellRendering
{
	public static void registerBlockRenderLayers()
	{
		RenderType translucent = RenderType.translucent();
		RenderType cutout = RenderType.cutout();
		RenderType cutout_mipped = RenderType.cutoutMipped();
		
		render(() -> AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get(), translucent);
		render(() -> AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get(), translucent);
		render(() -> AerialHellBlocksAndItems.CRYSTAL_BLOCK.get(), translucent);
		render(() -> AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL_BLOCK.get(), translucent);
		render(() -> AerialHellBlocksAndItems.SHADOW_CRYSTAL_BLOCK.get(), translucent);
		render(() -> AerialHellBlocksAndItems.CRYSTALLIZED_LEAVES.get(), translucent);
		render(() -> AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get(), cutout);
		render(() -> AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER.get(), translucent);
		render(() -> AerialHellBlocksAndItems.GREEN_SOLID_ETHER.get(), translucent);
		render(() -> AerialHellBlocksAndItems.PURPLE_SOLID_ETHER.get(), translucent);
		render(() -> AerialHellBlocksAndItems.AERIAL_BERRY_BUSH.get(), cutout);
		render(() -> AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY_BUSH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.BLUE_FLOWER.get(), cutout);
        render(() -> AerialHellBlocksAndItems.BLACK_ROSE.get(), cutout);
        render(() -> AerialHellBlocksAndItems.BELLFLOWER.get(), cutout);
        render(() -> AerialHellBlocksAndItems.MOSSY_STELLAR_STONE.get(), cutout);
        render(() -> AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE.get(), cutout);
        render(() -> AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_SLAB.get(), cutout);
        render(() -> AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_SLAB.get(), cutout);
        render(() -> AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_STAIRS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_STAIRS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_WALL.get(), cutout);
        render(() -> AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_WALL.get(), cutout);
		render(() -> AerialHellBlocksAndItems.MOSSY_MUD_BRICKS.get(), cutout);
		render(() -> AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS.get(), cutout);
		render(() -> AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_SLAB.get(), cutout);
		render(() -> AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_STAIRS.get(), cutout);
		render(() -> AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_WALL.get(), cutout);
		render(() -> AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.get(), cutout);
		render(() -> AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get(), cutout);
		render(() -> AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get(), cutout_mipped);
        render(() -> AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK.get(), cutout_mipped);
        render(() -> AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get(), cutout_mipped);
        render(() -> AerialHellBlocksAndItems.STELLAR_GRASS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_FERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.BRAMBLES.get(), cutout);
        render(() -> AerialHellBlocksAndItems.SHADOW_BRAMBLES.get(), cutout);
        render(() -> AerialHellBlocksAndItems.SHADOW_GRASS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.SHADOW_GRASS_BALL.get(), cutout);
        render(() -> AerialHellBlocksAndItems.PURPLISH_STELLAR_GRASS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.THORNY_COBWEB.get(), cutout);
        render(() -> AerialHellBlocksAndItems.AERIAL_TREE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.GOLDEN_BEECH_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.COPPER_PINE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.LAPIS_ROBINIA_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.SHADOW_PINE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.CORTINARIUS_VIOLACEUS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.VERDIGRIS_AGARIC.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_TALL_FERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_DEAD_BUSH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_BLUE_FLOWER.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_BLACK_ROSE.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_BELLFLOWER.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_STELLAR_FERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_STELLAR_DEAD_BUSH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_SKY_CACTUS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_VIBRANT_SKY_CACTUS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_AERIAL_TREE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_GOLDEN_BEECH_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_COPPER_PINE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_LAPIS_ROBINIA_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_SHADOW_PINE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_CORTINARIUS_VIOLACEUS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_VERDIGRIS_AGARIC.get(), cutout);
        render(() -> AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get(), translucent);
        render(() -> AerialHellBlocksAndItems.SLIPPERY_SAND_GLASS.get(), translucent);
        render(() -> AerialHellBlocksAndItems.RED_SLIPPERY_SAND_GLASS.get(), translucent);
        render(() -> AerialHellBlocksAndItems.BLACK_SLIPPERY_SAND_GLASS.get(), translucent);
        render(() -> AerialHellBlocksAndItems.BLUE_SLIPPERY_SAND_GLASS.get(), translucent);
        render(() -> AerialHellBlocksAndItems.GREEN_SLIPPERY_SAND_GLASS.get(), translucent);
        render(() -> AerialHellBlocksAndItems.SLIPPERY_SAND_GLASS_PANE.get(), translucent);
        render(() -> AerialHellBlocksAndItems.RED_SLIPPERY_SAND_GLASS_PANE.get(), translucent);
        render(() -> AerialHellBlocksAndItems.BLACK_SLIPPERY_SAND_GLASS_PANE.get(), translucent);
        render(() -> AerialHellBlocksAndItems.BLUE_SLIPPERY_SAND_GLASS_PANE.get(), translucent);
        render(() -> AerialHellBlocksAndItems.GREEN_SLIPPERY_SAND_GLASS_PANE.get(), translucent);
        render(() -> AerialHellBlocksAndItems.MAGMATIC_GEL_BLOCK.get(), translucent);
        render(() -> AerialHellBlocksAndItems.MAGMATIC_GEL_SLAB.get(), translucent);
        render(() -> AerialHellBlocksAndItems.MAGMATIC_GEL_STAIRS.get(), translucent);
        render(() -> AerialHellBlocksAndItems.MAGMATIC_GEL_WALL.get(), translucent);
        render(() -> AerialHellBlocksAndItems.AERIAL_TREE_DOOR.get(), cutout);
        render(() -> AerialHellBlocksAndItems.GOLDEN_BEECH_DOOR.get(), cutout);
        render(() -> AerialHellBlocksAndItems.COPPER_PINE_DOOR.get(), cutout);
        render(() -> AerialHellBlocksAndItems.LAPIS_ROBINIA_DOOR.get(), cutout);
        render(() -> AerialHellBlocksAndItems.SHADOW_PINE_DOOR.get(), cutout);
        render(() -> AerialHellBlocksAndItems.SKY_CACTUS_FIBER_DOOR.get(), cutout);
        render(() -> AerialHellBlocksAndItems.GRAY_SHROOM_DOOR.get(), cutout);
        render(() -> AerialHellBlocksAndItems.RUBY_DOOR.get(), cutout);
        render(() -> AerialHellBlocksAndItems.AERIAL_TREE_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.LAPIS_ROBINIA_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.GOLDEN_BEECH_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.COPPER_PINE_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.SHADOW_PINE_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.SKY_CACTUS_FIBER_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.GRAY_SHROOM_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.RUBY_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.RUBY_BARS.get(), translucent);
		render(() -> AerialHellBlocksAndItems.SHADOW_BARS.get(), translucent);
        render(() -> AerialHellBlocksAndItems.SKY_CACTUS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.FLUORITE_TORCH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.FLUORITE_WALL_TORCH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.RUBY_LANTERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.RUBY_FLUORITE_LANTERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.VOLUCITE_LANTERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.VOLUCITE_FLUORITE_LANTERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.FLUORITE_LANTERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.LUNATIC_LANTERN.get(), cutout);
		render(() -> AerialHellBlocksAndItems.SHADOW_LANTERN.get(), cutout);
		render(() -> AerialHellBlocksAndItems.RUBY_CHAIN.get(), cutout_mipped);
		render(() -> AerialHellBlocksAndItems.VOLUCITE_CHAIN.get(), cutout_mipped);
		render(() -> AerialHellBlocksAndItems.LUNATIC_CHAIN.get(), cutout_mipped);
		render(() -> AerialHellBlocksAndItems.SHADOW_CHAIN.get(), cutout_mipped);
		render(() -> AerialHellBlocksAndItems.SHADOW_TORCH.get(), cutout);
		render(() -> AerialHellBlocksAndItems.SHADOW_WALL_TORCH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.VOLUCITE_TORCH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.VOLUCITE_WALL_TORCH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.SKY_LADDER.get(), cutout);
	}
	
	private static void render(Supplier<? extends Block> block, RenderType render)
	{
        ItemBlockRenderTypes.setRenderLayer(block.get(), render);
    }

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
	
	public static void registerScreensMenus()
	{
        MenuScreens.register(AerialHellMenuTypes.OSCILLATOR.get(), OscillatorScreen::new);
        MenuScreens.register(AerialHellMenuTypes.FREEZER.get(), FreezerScreen::new);
        MenuScreens.register(AerialHellMenuTypes.STELLAR_FURNACE.get(), StellarFurnaceScreen::new);
    }
}
