package fr.factionbedrock.aerialhell.Client;

import com.google.common.base.Supplier;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.MudGolemRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.ChainedGodRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.CrystalSlimeRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.AerialArrowRenderer;
import fr.factionbedrock.aerialhell.Client.EntityRender.EvilCowRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.FatPhantomRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.FlyingJellyfishRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.CaterpillarRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.GoldenWalkerRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.HellSpiderRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.LunaticPriestRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.LunaticProjectileRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.ElementSpiritRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.MudCycleMageRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.MudSoldierRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.PoisonballProjectileRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.ThrowingKnifeRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.TornSpiritRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.ChestMimicRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.CrystalGolemRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.SandySheepRender;
import fr.factionbedrock.aerialhell.Client.EntityRender.ShadowTrollRender;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.VibratorScreen;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.FreezerScreen;
import fr.factionbedrock.aerialhell.Client.TileEntityRenderer.AerialHellChestMimicTileEntityRenderer;
import fr.factionbedrock.aerialhell.Client.TileEntityRenderer.AerialHellChestTileEntityRenderer;
import fr.factionbedrock.aerialhell.Client.TileEntityRenderer.AerialHellSignTileEntityRenderer;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellContainerTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
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
		render(() -> AerialHellBlocksAndItems.SLIPPERY_SAND_SOLID_ETHER.get(), translucent);
		render(() -> AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get(), translucent);
		render(() -> AerialHellBlocksAndItems.CRYSTAL_BLOCK.get(), translucent);
		render(() -> AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL_BLOCK.get(), translucent);
		render(() -> AerialHellBlocksAndItems.CRYSTALLIZED_LEAVES.get(), translucent);
		render(() -> AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get(), cutout);
		render(() -> AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER.get(), translucent);
		render(() -> AerialHellBlocksAndItems.GREEN_SOLID_ETHER.get(), translucent);
		render(() -> AerialHellBlocksAndItems.AERIAL_BERRY_BUSH.get(), cutout);
		render(() -> AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY_BUSH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.BLUE_FLOWER.get(), cutout);
        render(() -> AerialHellBlocksAndItems.BLACK_ROSE.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get(), cutout_mipped);
        render(() -> AerialHellBlocksAndItems.STELLAR_GRASS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_FERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.BRAMBLES.get(), cutout);
        render(() -> AerialHellBlocksAndItems.THORNY_COBWEB.get(), cutout);
        render(() -> AerialHellBlocksAndItems.AERIAL_TREE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.GOLDEN_BEECH_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.COPPER_PINE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.LAPIS_ROBINIA_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_TALL_FERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.STELLAR_DEAD_BUSH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_BLUE_FLOWER.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_BLACK_ROSE.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_STELLAR_FERN.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_STELLAR_DEAD_BUSH.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_SKY_CACTUS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_VIBRANT_SKY_CACTUS.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_AERIAL_TREE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_GOLDEN_BEECH_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_COPPER_PINE_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.POTTED_LAPIS_ROBINIA_SAPLING.get(), cutout);
        render(() -> AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get(), translucent);
        render(() -> AerialHellBlocksAndItems.SLIPPERY_SAND_GLASS.get(), translucent);
        render(() -> AerialHellBlocksAndItems.MAGMATIC_GEL_BLOCK.get(), translucent);
        render(() -> AerialHellBlocksAndItems.MAGMATIC_GEL_SLAB.get(), translucent);
        render(() -> AerialHellBlocksAndItems.MAGMATIC_GEL_STAIRS.get(), translucent);
        render(() -> AerialHellBlocksAndItems.MAGMATIC_GEL_WALL.get(), translucent);
        render(() -> AerialHellBlocksAndItems.AERIAL_TREE_DOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.GOLDEN_BEECH_DOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.COPPER_PINE_DOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.LAPIS_ROBINIA_DOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.SKY_CACTUS_FIBER_DOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.RUBY_DOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.AERIAL_TREE_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.LAPIS_ROBINIA_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.GOLDEN_BEECH_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.SKY_CACTUS_FIBER_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.RUBY_TRAPDOOR.get(), translucent);
        render(() -> AerialHellBlocksAndItems.RUBY_BARS.get(), translucent);
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
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.GOLDEN_WALKER.get(), GoldenWalkerRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.MUD_GOLEM.get(), MudGolemRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.MUD_SPECTRAL_GOLEM.get(), MudGolemRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.CRYSTAL_GOLEM.get(), CrystalGolemRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.LUNATIC_PRIEST.get(), LunaticPriestRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.EVIL_COW.get(), EvilCowRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.SANDY_SHEEP.get(), SandySheepRender::new);
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
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.AERIAL_TREE_MIMIC.get(), ChestMimicRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.GOLDEN_BEECH_MIMIC.get(), ChestMimicRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC.get(), ChestMimicRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.COPPER_PINE_MIMIC.get(), ChestMimicRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.HELL_SPIDER.get(), HellSpiderRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.CRYSTAL_SPIDER.get(), HellSpiderRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.FOREST_CATERPILLAR.get(), CaterpillarRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.CRYSTAL_CATERPILLAR.get(), CaterpillarRender::new);
		
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.IRON_THROWING_KNIFE.get(), ThrowingKnifeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.GOLD_THROWING_KNIFE.get(), ThrowingKnifeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.DIAMOND_THROWING_KNIFE.get(), ThrowingKnifeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.NETHERITE_THROWING_KNIFE.get(), ThrowingKnifeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.RUBY_THROWING_KNIFE.get(), ThrowingKnifeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.AZURITE_THROWING_KNIFE.get(), ThrowingKnifeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.MAGMATIC_GEL_THROWING_KNIFE.get(), ThrowingKnifeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.VOLUCITE_THROWING_KNIFE.get(), ThrowingKnifeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.LIGHTNING_THROWING_KNIFE.get(), ThrowingKnifeRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.POISONBALL.get(), PoisonballProjectileRender::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW.get(), AerialArrowRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.RUBY_BLOWPIPE_ARROW.get(), AerialArrowRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(AerialHellEntities.LUNATIC_PROJECTILE.get(), LunaticProjectileRender::new);
	}
	
	public static void registerGuiFactories()
	{
        ScreenManager.registerFactory(AerialHellContainerTypes.VIBRATOR.get(), VibratorScreen::new);
        ScreenManager.registerFactory(AerialHellContainerTypes.FREEZER.get(), FreezerScreen::new);
    }
}
