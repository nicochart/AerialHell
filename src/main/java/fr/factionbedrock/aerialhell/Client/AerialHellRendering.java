package fr.factionbedrock.aerialhell.Client;

import java.awt.Color;

import com.google.common.base.Supplier;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.*;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.*;
import fr.factionbedrock.aerialhell.Client.TileEntityRenderer.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellContainerTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
@OnlyIn(Dist.CLIENT)
public class AerialHellRendering
{
	public static void registerBlockRenderLayers()
	{
		RenderType translucent = RenderType.getTranslucent();
		RenderType cutout = RenderType.getCutout();
		RenderType cutout_mipped = RenderType.getCutoutMipped();
		
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
	
	public static void registerTileEntityRenderLayers()
	{
		ClientRegistry.bindTileEntityRenderer(AerialHellTileEntityTypes.CHEST_MIMIC.get(), AerialHellChestMimicTileEntityRenderer::new);
		ClientRegistry.bindTileEntityRenderer(AerialHellTileEntityTypes.CHEST.get(), AerialHellChestTileEntityRenderer::new);
		ClientRegistry.bindTileEntityRenderer(AerialHellTileEntityTypes.SIGN.get(), AerialHellSignTileEntityRenderer::new);
	}
	
	private static void render(Supplier<? extends Block> block, RenderType render)
	{
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }
	
	public static void registerEntityRenderers(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.STELLAR_STONE_AUTOMATON.get(), StellarStoneAutomatonRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.MUD_GOLEM.get(), MudGolemRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.MUD_SPECTRAL_GOLEM.get(), MudGolemRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.CRYSTAL_GOLEM.get(), CrystalGolemRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.LUNATIC_PRIEST.get(), LunaticPriestRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.EVIL_COW.get(), EvilCowRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.CORTINARIUS_COW.get(), CortinariusCowRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.SHROOMBOOM.get(), ShroomBoomRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.VERDIGRIS_ZOMBIE.get(), VerdigrisZombieRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.SANDY_SHEEP.get(), SandySheepRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.GLIDING_TURTLE.get(), GlidingTurtleRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.FAT_PHANTOM.get(), FatPhantomRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.CRYSTAL_SLIME.get(), CrystalSlimeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.MUD_SOLDIER.get(), MudSoldierRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.MUD_SPECTRAL_SOLDIER.get(), MudSoldierRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.MUD_CYCLE_MAGE.get(), MudCycleMageRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.TORN_SPIRIT.get(), TornSpiritRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.ICE_SPIRIT.get(), ElementSpiritRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.FIRE_SPIRIT.get(), ElementSpiritRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.ELECTRO_SPIRIT.get(), ElementSpiritRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.CHAINED_GOD.get(), ChainedGodRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.FLYING_JELLYFISH.get(), FlyingJellyfishRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.SHADOW_TROLL.get(), ShadowTrollRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.SHADOW_AUTOMATON.get(), ShadowAutomatonRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.AERIAL_TREE_MIMIC.get(), ChestMimicRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.GOLDEN_BEECH_MIMIC.get(), ChestMimicRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC.get(), ChestMimicRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.COPPER_PINE_MIMIC.get(), ChestMimicRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.SHADOW_PINE_MIMIC.get(), SpiderBarrelMimicRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.HELL_SPIDER.get(), HellSpiderRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.CRYSTAL_SPIDER.get(), HellSpiderRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.SHADOW_SPIDER.get(), HellSpiderRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.LILITH.get(), LilithRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.FOREST_CATERPILLAR.get(), CaterpillarRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.CRYSTAL_CATERPILLAR.get(), CaterpillarRender::new);
		
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.IRON_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.GOLD_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.DIAMOND_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.NETHERITE_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.RUBY_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.AZURITE_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.VOLUCITE_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.ARSONIST_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.LIGHTNING_SHURIKEN.get(), ShurikenRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.POISONBALL.get(), PoisonballProjectileRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW.get(), AerialArrowRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.RUBY_BLOWPIPE_ARROW.get(), AerialArrowRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.LUNATIC_PROJECTILE.get(), LunaticProjectileRender::new);
	}
	
	public static void registerBlockColors()
	{
        Minecraft.getInstance().getBlockColors().register((state, world, pos, tint) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : new Color(12, 35, 26).getRGB(),
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
	
	public static void registerItemColors()
	{
        Minecraft.getInstance().getItemColors().register((stack, color) -> new Color(50, 140, 102).getRGB(),
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
	
	public static void registerGuiFactories()
	{
        ScreenManager.registerFactory(AerialHellContainerTypes.OSCILLATOR.get(), OscillatorScreen::new);
        ScreenManager.registerFactory(AerialHellContainerTypes.FREEZER.get(), FreezerScreen::new);
        ScreenManager.registerFactory(AerialHellContainerTypes.STELLAR_FURNACE.get(), StellarFurnaceScreen::new);
    }
}
