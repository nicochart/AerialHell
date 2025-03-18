package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestMimicBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.EntityModels.*;
import fr.factionbedrock.aerialhell.Client.EntityRender.*;
import fr.factionbedrock.aerialhell.Client.Util.ShiftedModelRenderHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.ModelNameSupplier;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBaker;
import net.minecraft.client.render.model.ModelRotation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.SpriteIdentifier;

import java.util.ArrayList;
import java.util.List;

public class RenderRegistrationListener
{
    public static final List<BlockState> TO_BAKE_LIST = new ArrayList<>();

    public static void registerBlockRenderLayers()
    {
        RenderLayer translucent = RenderLayer.getTranslucent();
        RenderLayer cutout = RenderLayer.getCutout();
        RenderLayer cutout_mipped = RenderLayer.getCutoutMipped();

        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.AERIAL_HELL_PORTAL, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_CHEST, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_PLANKS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_LOG, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_WOOD, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_SLAB, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_STAIRS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_FENCE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_GATE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_DOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_TRAPDOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_STELLAR_FURNACE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_WOOL, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_RUBY_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_FLUORITE_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_AZURITE_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_GOLD_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_BARREL, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_CRAFTING_TABLE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_BOAT_VINE_ROPE_SPOOL, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GHOST_LANTERN, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.WHITE_SOLID_ETHER, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLUE_SOLID_ETHER, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.CRYSTAL_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.CRYSTALLIZED_LEAVES, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.CRYSTALLIZED_FIRE, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GOLDEN_SOLID_ETHER, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GREEN_SOLID_ETHER, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.PURPLE_SOLID_ETHER, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GLOWING_STICK_FRUIT_VINES, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GLOWING_STICK_FRUIT_VINES_PLANT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLOSSOMING_VINES, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLOSSOMING_VINES_PLANT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.CLIMBING_VINE, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_SUGAR_CANE, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.FULL_MOON_FLOWER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.LAZULI_ROOTS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.LAZULI_ROOTS_PLANT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_ROOTS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_ROOTS_PLANT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.DEAD_ROOTS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.DEAD_ROOTS_PLANT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GLOWING_ROOTS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GLOWING_ROOTS_PLANT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_GLOWING_ROOTS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.AERIAL_BERRY_BUSH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.VIBRANT_AERIAL_BERRY_BUSH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_WHEAT, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLUE_FLOWER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLACK_ROSE, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BELLFLOWER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_STELLAR_STONE, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_STELLAR_STONE_SLAB, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_SLAB, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_STELLAR_STONE_STAIRS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_STAIRS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_STELLAR_STONE_WALL, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_WALL, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_MUD_BRICKS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_MUD_BRICKS_SLAB, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_MUD_BRICKS_STAIRS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_MUD_BRICKS_WALL, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MUD_GLYPH_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.LUNATIC_GLYPH_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GOLDEN_NETHER_PRISON_GLYPH_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.VOLUCITE_GLYPH_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_CATACOMBS_GLYPH_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_GRASS_BLOCK, cutout_mipped);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_PODZOL, cutout_mipped);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK, cutout_mipped);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_GRASS_BLOCK, cutout_mipped);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_GRASS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_GRASS_BALL, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_FERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BRAMBLES, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_BRAMBLES, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_GRASS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_GRASS_BALL, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.PURPLISH_STELLAR_GRASS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_CLOVERS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GLOWING_STELLAR_GRASS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.THORNY_COBWEB, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.AERIAL_TREE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GOLDEN_BEECH_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.COPPER_PINE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.LAPIS_ROBINIA_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_PINE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_JUNGLE_TREE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.PURPLE_SHADOW_PINE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.CORTINARIUS_VIOLACEUS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.VERDIGRIS_AGARIC, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GLOWING_BOLETUS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.TALL_GLOWING_BOLETUS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLUE_MEANIE_CLUSTER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GIANT_ROOT_SHROOM, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_TALL_GRASS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_TALL_FERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_VERY_TALL_GRASS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLUISH_FERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.TALL_BLUISH_FERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POLYCHROME_FERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.TALL_POLYCHROME_FERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.STELLAR_DEAD_BUSH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_BLUE_FLOWER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_BLACK_ROSE, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_BELLFLOWER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_STELLAR_FERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_STELLAR_DEAD_BUSH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_SKY_CACTUS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_VIBRANT_SKY_CACTUS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_AERIAL_TREE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_GOLDEN_BEECH_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_COPPER_PINE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_LAPIS_ROBINIA_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_SHADOW_PINE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_PURPLE_SHADOW_PINE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_STELLAR_JUNGLE_TREE_SAPLING, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_CORTINARIUS_VIOLACEUS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_VERDIGRIS_AGARIC, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_VINE_BLOSSOM, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.POTTED_GLOWING_BOLETUS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SLIPPERY_SAND_GLASS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SLIPPERY_SAND_GLASS_PANE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS_PANE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS_PANE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS_PANE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS_PANE, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MAGMATIC_GEL_BLOCK, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MAGMATIC_GEL_SLAB, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MAGMATIC_GEL_STAIRS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MAGMATIC_GEL_WALL, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.AERIAL_TREE_DOOR, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GOLDEN_BEECH_DOOR, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.COPPER_PINE_DOOR, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.LAPIS_ROBINIA_DOOR, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_PINE_DOOR, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SKY_CACTUS_FIBER_DOOR, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GRAY_SHROOM_DOOR, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.RUBY_DOOR, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.AERIAL_TREE_TRAPDOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.LAPIS_ROBINIA_TRAPDOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GOLDEN_BEECH_TRAPDOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.COPPER_PINE_TRAPDOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_PINE_TRAPDOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SKY_CACTUS_FIBER_TRAPDOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.GRAY_SHROOM_TRAPDOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.RUBY_TRAPDOOR, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.RUBY_BARS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_BARS, translucent);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SKY_CACTUS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.VIBRANT_SKY_CACTUS, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.FLUORITE_TORCH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.FLUORITE_WALL_TORCH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.RUBY_LANTERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.RUBY_FLUORITE_LANTERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.VOLUCITE_LANTERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.VOLUCITE_FLUORITE_LANTERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.FLUORITE_LANTERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.LUNATIC_LANTERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_LANTERN, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.RUBY_CHAIN, cutout_mipped);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.VOLUCITE_CHAIN, cutout_mipped);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.LUNATIC_CHAIN, cutout_mipped);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_CHAIN, cutout_mipped);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_TORCH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SHADOW_WALL_TORCH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.VOLUCITE_TORCH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.VOLUCITE_WALL_TORCH, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.SKY_LADDER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.MUD_CYCLE_MAGE_TROPHY, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.CHAINED_GOD_TROPHY, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(AerialHellBlocks.INTANGIBLE_TEMPORARY_BLOCK, translucent);
    }

    public static void registerRenderers()
    {
        EntityRendererRegistry.register(AerialHellEntities.STELLAR_STONE_AUTOMATON, StellarStoneAutomatonRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_GOLEM, MudGolemRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_SPECTRAL_GOLEM, MudGolemRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CRYSTAL_GOLEM, CrystalGolemRender::new);
        EntityRendererRegistry.register(AerialHellEntities.LUNATIC_PRIEST, LunaticPriestRender::new);
        EntityRendererRegistry.register(AerialHellEntities.EVIL_COW, EvilCowRender::new);
        EntityRendererRegistry.register(AerialHellEntities.STELLAR_ENT, EntRender::new);
        EntityRendererRegistry.register(AerialHellEntities.VENOMOUS_SNAKE, SnakeRender::new);
        EntityRendererRegistry.register(AerialHellEntities.WORM, SnakeRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CORTINARIUS_COW, CortinariusCowRender::new);
        EntityRendererRegistry.register(AerialHellEntities.STELLAR_CHICKEN, StellarChickenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.STELLAR_BOAR, BoarRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHROOMBOOM, ShroomBoomRender::new);
        EntityRendererRegistry.register(AerialHellEntities.VERDIGRIS_ZOMBIE, VerdigrisZombieRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUMMY, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SLIME_PIRATE, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SLIME_NINJA_PIRATE, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GHOST_SLIME_PIRATE, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GHOST_SLIME_NINJA_PIRATE, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SANDY_SHEEP, SandySheepRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GLIDING_TURTLE, GlidingTurtleRender::new);
        EntityRendererRegistry.register(AerialHellEntities.FAT_PHANTOM, FatPhantomRender::new);
        EntityRendererRegistry.register(AerialHellEntities.KODAMA, KodamaRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CRYSTAL_SLIME, CrystalSlimeRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_SOLDIER, MudSoldierRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_SPECTRAL_SOLDIER, MudSoldierRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_CYCLE_MAGE, MudCycleMageRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_SPECTRAL_CYCLE_MAGE, MudCycleMageRender::new);
        EntityRendererRegistry.register(AerialHellEntities.TORN_SPIRIT, TornSpiritRender::new);
        EntityRendererRegistry.register(AerialHellEntities.ICE_SPIRIT, ElementSpiritRender::new);
        EntityRendererRegistry.register(AerialHellEntities.FIRE_SPIRIT, ElementSpiritRender::new);
        EntityRendererRegistry.register(AerialHellEntities.ELECTRO_SPIRIT, ElementSpiritRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CHAINED_GOD, ChainedGodRender::new);
        EntityRendererRegistry.register(AerialHellEntities.FLYING_JELLYFISH, FlyingJellyfishRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_FLYING_SKULL, ShadowFlyingSkullRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_TROLL, ShadowTrollRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_AUTOMATON, ShadowAutomatonRender::new);
        EntityRendererRegistry.register(AerialHellEntities.AERIAL_TREE_MIMIC, ChestMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GOLDEN_BEECH_MIMIC, ChestMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC, ChestMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.COPPER_PINE_MIMIC, ChestMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_PINE_MIMIC, SpiderBarrelMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.HELL_SPIDER, HellSpiderRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CRYSTAL_SPIDER, HellSpiderRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_SPIDER, HellSpiderRender::new);
        EntityRendererRegistry.register(AerialHellEntities.LILITH, LilithRender::new);
        EntityRendererRegistry.register(AerialHellEntities.FOREST_CATERPILLAR, ForestCaterpillarRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CRYSTAL_CATERPILLAR, CaterpillarRender::new);

        EntityRendererRegistry.register(AerialHellEntities.THROWN_STELLAR_EGG, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(AerialHellEntities.IRON_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GOLD_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.DIAMOND_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.NETHERITE_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.RUBY_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.AZURITE_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MAGMATIC_GEL_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.VOLUCITE_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.OBSIDIAN_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.ARSONIST_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.LIGHTNING_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.POISONBALL, FireballLikeProjectileRender::new);
        EntityRendererRegistry.register(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE, FireballLikeProjectileRender::new);
        EntityRendererRegistry.register(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW, AerialArrowRenderer::new);
        EntityRendererRegistry.register(AerialHellEntities.RUBY_BLOWPIPE_ARROW, AerialArrowRenderer::new);
        EntityRendererRegistry.register(AerialHellEntities.LUNATIC_PROJECTILE, LightProjectileRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_PROJECTILE, LightProjectileRender::new);
        EntityRendererRegistry.register(AerialHellEntities.AERIAL_HELL_PAINTING, AerialHellPaintingRender::new);

        BlockEntityRendererFactories.register(AerialHellBlockEntities.CHEST, AerialHellChestBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(AerialHellBlockEntities.CHEST, AerialHellChestBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(AerialHellBlockEntities.CHEST_MIMIC, AerialHellChestMimicBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(AerialHellBlockEntities.SIGN, SignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(AerialHellBlockEntities.HANGING_SIGN, HangingSignBlockEntityRenderer::new);
    }

    public static void registerLayerDefinitions()
    {
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CATERPILLAR, ForestCaterpillarModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.BOAR, BoarModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.ENT, EntModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SNAKE, SnakeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.STELLAR_CHICKEN, StellarChickenModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CHAINED_GOD, ChainedGodModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CHEST_MIMIC, ChestMimicModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CRYSTAL_GOLEM, CrystalGolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CRYSTAL_SLIME, CrystalSlimeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.ELEMENT_SPIRIT, ElementSpiritModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.FLYING_JELLYFISH, FlyingJellyfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.GLIDING_TURTLE, GlidingTurtleModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.KODAMA, KodamaModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.LILITH, LilithModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.LUNATIC_PRIEST, LunaticPriestModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.MUD_CYCLE_MAGE, MudCycleMageModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.MUD_GOLEM, MudGolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SANDY_SHEEP, SandySheepModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SHADOW_FLYING_SKULL, ShadowFlyingSkullModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SHADOW_TROLL, ShadowTrollModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SHROOMBOOM, ShroomBoomModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SPIDER_BARREL_MIMIC, SpiderBarrelMimicModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.AUTOMATON, AutomatonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.TORN_SPIRIT, TornSpiritModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.VERDIGRIS_ZOMBIE, VerdigrisZombieModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SLIME_PIRATE, HumanoidTwoLayerModel::createBodyLayer);

        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CORTINARIUS_COW_SHROOM, CortinariusCowShroomModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CRYSTAL_GOLEM_CRYSTAL, CrystalGolemCrystalModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SPIDER_SPIKE, HellSpiderSpikeModel::createBodyLayer);

        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.EMPTY, EmptyModel::createBodyLayer);
    }

    public static void registerShiftingBakedModels()
    {
        initialiseToBakeList();

        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            Baker baker = context.baker();
            //if (context.id().toString().contains("stellar_grass_block")
            if (context.id().toString().equals("aerialhell:block/stellar_grass_block") && !context.baker().getModelNameSupplier().get().contains("shifted_render=true"))
            {
                AerialHell.LOGGER.info(context.baker().getModelNameSupplier().get());

                BakedModel shiftedModel = baker.bake(AerialHell.id("block/shifted_stellar_grass_block"), ModelRotation.X0_Y0);
                BakedModel editedModel = new ShiftingBlockBakedModel(original, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
                return editedModel;
            }
            else if (context.id().toString().equals("aerialhell:block/shadow_grass_block") && !context.baker().getModelNameSupplier().get().contains("shifted_render=true"))
            {
                BakedModel shiftedModel = baker.bake(AerialHell.id("block/shifted_shadow_grass_block"), ModelRotation.X0_Y0);
                BakedModel editedModel = new ShiftingBlockBakedModel(original, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
                return editedModel;
            }
            else {return original;}

            /*if (TO_BAKE_LIST.isEmpty()) {return original;}
            List<BlockState> bakedList = new ArrayList<>();
            for (BlockState blockstate : TO_BAKE_LIST)
            {
                ModelIdentifier initialIdentifier = BlockModels.getModelId(blockstate);
                ModelIdentifier shiftedIdentifier = BlockModels.getModelId(blockstate.with(AerialHellBooleanProperties.SHIFTED_RENDER, true));
                //if (context.baker() instanceof ModelBaker modelBaker)
                //{
                BakedModel initialModel = context.loader().getBakedModelMap().get(initialIdentifier);
                BakedModel shiftedModel = context.loader().getBakedModelMap().get(shiftedIdentifier);
                if (initialModel != null && shiftedModel != null)
                {
                    BakedModel editedModel = new ShiftingBlockBakedModel(initialModel, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
                    context.loader().getBakedModelMap().put(initialIdentifier, editedModel);
                    bakedList.add(blockstate);
                }
                else {TO_BAKE_LIST.removeAll(bakedList); return original;}
                //}
            }
            TO_BAKE_LIST.removeAll(bakedList);
            return original;*/
        }));
    }

    public static void initialiseToBakeList()
    {
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK);
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK);
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.STELLAR_GRASS);
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.SHADOW_GRASS);
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.STELLAR_GRASS_BALL);
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.SHADOW_GRASS_BALL);
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.BRAMBLES);
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.SHADOW_BRAMBLES);
        ShiftedModelRenderHelper.addToBakeAbstractPlantStemBlock(AerialHellBlocks.GLOWING_ROOTS);
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.GLOWING_ROOTS_PLANT);
        ShiftedModelRenderHelper.addToBakeAbstractPlantStemBlock(AerialHellBlocks.SHADOW_GLOWING_ROOTS);
        ShiftedModelRenderHelper.addToBakeDefaultBlockstate(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT);
        ShiftedModelRenderHelper.addToBakeGrassBlock(AerialHellBlocks.STELLAR_GRASS_BLOCK);
        ShiftedModelRenderHelper.addToBakeGrassBlock(AerialHellBlocks.SHADOW_GRASS_BLOCK);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.AERIAL_TREE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.GOLDEN_BEECH_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.COPPER_PINE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.LAPIS_ROBINIA_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.SHADOW_PINE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLeavesBlock(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.AERIAL_TREE_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.SHADOW_AERIAL_TREE_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.GOLDEN_BEECH_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.COPPER_PINE_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.SHADOW_COPPER_PINE_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.LAPIS_ROBINIA_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.SHADOW_PINE_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.EYE_SHADOW_PINE_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.STELLAR_JUNGLE_TREE_LOG);
        ShiftedModelRenderHelper.addToBakeLogBlock(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG);
    }
}
