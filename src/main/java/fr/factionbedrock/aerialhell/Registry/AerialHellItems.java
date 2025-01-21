package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.Item.*;
import fr.factionbedrock.aerialhell.Item.Armor.ShadowArmorItem;
import fr.factionbedrock.aerialhell.Item.Bucket.RubyBucketItem;
import fr.factionbedrock.aerialhell.Item.Bucket.RubyLiquidOfGodsBucketItem;
import fr.factionbedrock.aerialhell.Item.Bucket.RubyMilkBucketItem;
import fr.factionbedrock.aerialhell.Item.Bucket.RubyWaterBucketItem;
import fr.factionbedrock.aerialhell.Item.Material.AerialHellArmorMaterials;
import fr.factionbedrock.aerialhell.Item.Material.ToolMaterials;
import fr.factionbedrock.aerialhell.Item.Shuriken.*;
import fr.factionbedrock.aerialhell.Item.Tools.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellJukeboxSongs;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellItems
{
    public static void registerCompostableItems()
    {
        ComposterBlock.COMPOSTABLES.put(STELLAR_GRASS.get().asItem(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_TALL_GRASS.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_GRASS_BALL.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_DEAD_BUSH.get().asItem(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(BLUE_FLOWER.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(BLACK_ROSE.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(BELLFLOWER.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(AERIAL_BERRY.get().asItem(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(VIBRANT_AERIAL_BERRY.get().asItem(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(FROZEN_AERIAL_BERRY.get().asItem(), 0.85F);
        ComposterBlock.COMPOSTABLES.put(AERIAL_BERRY_SEEDS.get().asItem(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(VIBRANT_AERIAL_BERRY_SEEDS.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(COPPER_PINE_CONE.get().asItem(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(AERIAL_TREE_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(AERIAL_TREE_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GOLDEN_BEECH_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GOLDEN_BEECH_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(COPPER_PINE_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(COPPER_PINE_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(LAPIS_ROBINIA_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(LAPIS_ROBINIA_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(SHADOW_PINE_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(SHADOW_PINE_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_JUNGLE_TREE_SAPLING.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(STELLAR_JUNGLE_TREE_LEAVES.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(CORTINARIUS_VIOLACEUS.get().asItem(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(SKY_CACTUS_FIBER.get().asItem(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(SKY_CACTUS.get().asItem(), 0.4F);
        ComposterBlock.COMPOSTABLES.put(VIBRANT_SKY_CACTUS_FIBER.get().asItem(), 0.2F);
        ComposterBlock.COMPOSTABLES.put(VIBRANT_SKY_CACTUS.get().asItem(), 0.8F);
    }

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    //portal
    public static final DeferredItem<Item> STELLAR_PORTAL_FRAME_BLOCK = ITEMS.register("stellar_portal_frame_block", () -> new BlockItem(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_PORTAL_FRAME_ORE = ITEMS.register("stellar_portal_frame_ore", () -> new BlockItem(AerialHellBlocks.STELLAR_PORTAL_FRAME_ORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = ITEMS.register("deepslate_stellar_portal_frame_ore", () -> new BlockItem(AerialHellBlocks.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_PORTAL_FRAME_BRICK = ITEMS.register("stellar_portal_frame_brick", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_LIGHTER = ITEMS.register("stellar_lighter", () -> new StellarLighterItem(new Item.Properties().stacksTo(1).durability(4)));

    //torch
    public static final DeferredItem<Item> FLUORITE_TORCH = ITEMS.register("fluorite_torch", () -> new StandingAndWallBlockItem(AerialHellBlocks.FLUORITE_TORCH.get(), AerialHellBlocks.FLUORITE_WALL_TORCH.get(), Direction.DOWN, new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_TORCH = ITEMS.register("volucite_torch", () -> new StandingAndWallBlockItem(AerialHellBlocks.VOLUCITE_TORCH.get(), AerialHellBlocks.VOLUCITE_WALL_TORCH.get(), Direction.DOWN, new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_TORCH = ITEMS.register("shadow_torch", () -> new StandingAndWallBlockItem(AerialHellBlocks.SHADOW_TORCH.get(), AerialHellBlocks.SHADOW_WALL_TORCH.get(), Direction.DOWN, new Item.Properties()));

    //lanterns
    public static final DeferredItem<Item> FLUORITE_LANTERN = ITEMS.register("fluorite_lantern", () -> new BlockItem(AerialHellBlocks.FLUORITE_LANTERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> RUBY_LANTERN = ITEMS.register("ruby_lantern", () -> new BlockItem(AerialHellBlocks.RUBY_LANTERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> RUBY_FLUORITE_LANTERN = ITEMS.register("ruby_fluorite_lantern", () -> new BlockItem(AerialHellBlocks.RUBY_FLUORITE_LANTERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_LANTERN = ITEMS.register("volucite_lantern", () -> new BlockItem(AerialHellBlocks.VOLUCITE_LANTERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_FLUORITE_LANTERN = ITEMS.register("volucite_fluorite_lantern", () -> new BlockItem(AerialHellBlocks.VOLUCITE_FLUORITE_LANTERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_LANTERN = ITEMS.register("lunatic_lantern", () -> new BlockItem(AerialHellBlocks.LUNATIC_LANTERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_LANTERN = ITEMS.register("shadow_lantern", () -> new BlockItem(AerialHellBlocks.SHADOW_LANTERN.get(), new Item.Properties()));

    //chains
    public static final DeferredItem<Item> RUBY_CHAIN = ITEMS.register("ruby_chain", () -> new BlockItem(AerialHellBlocks.RUBY_CHAIN.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_CHAIN = ITEMS.register("volucite_chain", () -> new BlockItem(AerialHellBlocks.VOLUCITE_CHAIN.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_CHAIN = ITEMS.register("lunatic_chain", () -> new BlockItem(AerialHellBlocks.LUNATIC_CHAIN.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CHAIN = ITEMS.register("shadow_chain", () -> new BlockItem(AerialHellBlocks.SHADOW_CHAIN.get(), new Item.Properties()));

    //grass & dirt
    public static final DeferredItem<Item> STELLAR_GRASS_BLOCK = ITEMS.register("stellar_grass_block", () -> new BlockItem(AerialHellBlocks.STELLAR_GRASS_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHISELED_STELLAR_GRASS_BLOCK = ITEMS.register("chiseled_stellar_grass_block", () -> new BlockItem(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_DIRT = ITEMS.register("stellar_dirt", () -> new BlockItem(AerialHellBlocks.STELLAR_DIRT.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_COARSE_DIRT = ITEMS.register("stellar_coarse_dirt", () -> new BlockItem(AerialHellBlocks.STELLAR_COARSE_DIRT.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_FARMLAND = ITEMS.register("stellar_farmland", () -> new BlockItem(AerialHellBlocks.STELLAR_FARMLAND.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_DIRT_PATH = ITEMS.register("stellar_dirt_path", () -> new BlockItem(AerialHellBlocks.STELLAR_DIRT_PATH.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_PODZOL = ITEMS.register("stellar_podzol", () -> new BlockItem(AerialHellBlocks.STELLAR_PODZOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_CRYSTAL_PODZOL = ITEMS.register("stellar_crystal_podzol", () -> new BlockItem(AerialHellBlocks.STELLAR_CRYSTAL_PODZOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHISELED_STELLAR_DIRT = ITEMS.register("chiseled_stellar_dirt", () -> new BlockItem(AerialHellBlocks.CHISELED_STELLAR_DIRT.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_GRASS_BLOCK = ITEMS.register("shadow_grass_block", () -> new BlockItem(AerialHellBlocks.SHADOW_GRASS_BLOCK.get(), new Item.Properties()));

    //slippery sand
    public static final DeferredItem<Item> SLIPPERY_SAND = ITEMS.register("slippery_sand", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE = ITEMS.register("slippery_sand_stone", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS = ITEMS.register("slippery_sand_stone_bricks", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CUT_SLIPPERY_SAND_STONE = ITEMS.register("cut_slippery_sand_stone", () -> new BlockItem(AerialHellBlocks.CUT_SLIPPERY_SAND_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS = ITEMS.register("cracked_slippery_sand_stone_bricks", () -> new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS.get(), new Item.Properties()));

    //giant root
    public static final DeferredItem<Item> GIANT_ROOT = ITEMS.register("giant_root", () -> new BurnableBlockItem(AerialHellBlocks.GIANT_ROOT.get(), new Item.Properties(), 300));

    //aerial_tree
    public static final DeferredItem<Item> AERIAL_TREE_LOG = ITEMS.register("aerial_tree_log", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STRIPPED_AERIAL_TREE_LOG = ITEMS.register("stripped_aerial_tree_log", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_AERIAL_TREE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> AERIAL_TREE_WOOD = ITEMS.register("aerial_tree_wood", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_WOOD.get(), new Item.Properties(), 210));
    public static final DeferredItem<Item> STRIPPED_AERIAL_TREE_WOOD = ITEMS.register("stripped_aerial_tree_wood", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_AERIAL_TREE_WOOD.get(), new Item.Properties(), 210));
    public static final DeferredItem<Item> AERIAL_TREE_LEAVES = ITEMS.register("aerial_tree_leaves", () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> AERIAL_TREE_PLANKS = ITEMS.register("aerial_tree_planks", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_PLANKS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> CHISELED_AERIAL_TREE_PLANKS = ITEMS.register("chiseled_aerial_tree_planks", () -> new BurnableBlockItem(AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> AERIAL_TREE_BOOKSHELF = ITEMS.register("aerial_tree_bookshelf", () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> AERIAL_TREE_SAPLING = ITEMS.register("aerial_tree_sapling", () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_SAPLING.get(), new Item.Properties()));

    //petrified aerial tree
    public static final DeferredItem<Item> PETRIFIED_AERIAL_TREE_LOG = ITEMS.register("petrified_aerial_tree_log", () -> new BurnableBlockItem(AerialHellBlocks.PETRIFIED_AERIAL_TREE_LOG.get(), new Item.Properties(), 600));

    //golden beech
    public static final DeferredItem<Item> GOLDEN_BEECH_LOG = ITEMS.register("golden_beech_log", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STRIPPED_GOLDEN_BEECH_LOG = ITEMS.register("stripped_golden_beech_log", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GOLDEN_BEECH_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_WOOD = ITEMS.register("golden_beech_wood", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_WOOD.get(), new Item.Properties(), 210));
    public static final DeferredItem<Item> STRIPPED_GOLDEN_BEECH_WOOD = ITEMS.register("stripped_golden_beech_wood", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GOLDEN_BEECH_WOOD.get(), new Item.Properties(), 210));
    public static final DeferredItem<Item> GOLDEN_BEECH_PLANKS = ITEMS.register("golden_beech_planks", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_PLANKS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> CHISELED_GOLDEN_BEECH_PLANKS = ITEMS.register("chiseled_golden_beech_planks", () -> new BurnableBlockItem(AerialHellBlocks.CHISELED_GOLDEN_BEECH_PLANKS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_LEAVES = ITEMS.register("golden_beech_leaves", () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_BEECH_BOOKSHELF = ITEMS.register("golden_beech_bookshelf", () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_BEECH_SAPLING = ITEMS.register("golden_beech_sapling", () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_SAPLING.get(), new Item.Properties()));

    //cropper pine
    public static final DeferredItem<Item> COPPER_PINE_LOG = ITEMS.register("copper_pine_log", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STRIPPED_COPPER_PINE_LOG = ITEMS.register("stripped_copper_pine_log", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_COPPER_PINE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> COPPER_PINE_WOOD = ITEMS.register("copper_pine_wood", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_WOOD.get(), new Item.Properties(), 210));
    public static final DeferredItem<Item> STRIPPED_COPPER_PINE_WOOD = ITEMS.register("stripped_copper_pine_wood", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_COPPER_PINE_WOOD.get(), new Item.Properties(), 210));
    public static final DeferredItem<Item> COPPER_PINE_PLANKS = ITEMS.register("copper_pine_planks", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_PLANKS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> COPPER_PINE_LEAVES = ITEMS.register("copper_pine_leaves", () -> new BlockItem(AerialHellBlocks.COPPER_PINE_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PINE_BOOKSHELF = ITEMS.register("copper_pine_bookshelf", () -> new BlockItem(AerialHellBlocks.COPPER_PINE_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PINE_SAPLING = ITEMS.register("copper_pine_sapling", () -> new BlockItem(AerialHellBlocks.COPPER_PINE_SAPLING.get(), new Item.Properties()));

    //lapis robinia
    public static final DeferredItem<Item> LAPIS_ROBINIA_LOG = ITEMS.register("lapis_robinia_log", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_LOG.get(), new Item.Properties(), 400));
    public static final DeferredItem<Item> ENCHANTED_LAPIS_ROBINIA_LOG = ITEMS.register("enchanted_lapis_robinia_log", () -> new BurnableBlockItem(AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG.get(), new Item.Properties(), 400));
    public static final DeferredItem<Item> STRIPPED_LAPIS_ROBINIA_LOG = ITEMS.register("stripped_lapis_robinia_log", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_LOG.get(), new Item.Properties(), 400));
    public static final DeferredItem<Item> LAPIS_ROBINIA_WOOD = ITEMS.register("lapis_robinia_wood", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_WOOD.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STRIPPED_LAPIS_ROBINIA_WOOD = ITEMS.register("stripped_lapis_robinia_wood", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_WOOD.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_LEAVES = ITEMS.register("lapis_robinia_leaves", () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_PLANKS = ITEMS.register("lapis_robinia_planks", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_PLANKS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_BOOKSHELF = ITEMS.register("lapis_robinia_bookshelf", () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_SAPLING = ITEMS.register("lapis_robinia_sapling", () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_SAPLING.get(), new Item.Properties()));

    //shadow_pine
    public static final DeferredItem<Item> SHADOW_PINE_LOG = ITEMS.register("shadow_pine_log", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> EYE_SHADOW_PINE_LOG = ITEMS.register("eye_shadow_pine_log", () -> new BurnableBlockItem(AerialHellBlocks.EYE_SHADOW_PINE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STRIPPED_SHADOW_PINE_LOG = ITEMS.register("stripped_shadow_pine_log", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_SHADOW_PINE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_WOOD = ITEMS.register("shadow_pine_wood", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_WOOD.get(), new Item.Properties(), 210));
    public static final DeferredItem<Item> STRIPPED_SHADOW_PINE_WOOD = ITEMS.register("stripped_shadow_pine_wood", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_SHADOW_PINE_WOOD.get(), new Item.Properties(), 210));
    public static final DeferredItem<Item> SHADOW_PINE_LEAVES = ITEMS.register("shadow_pine_leaves", () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> PURPLE_SHADOW_PINE_LEAVES = ITEMS.register("purple_shadow_pine_leaves", () -> new BlockItem(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_PINE_PLANKS = ITEMS.register("shadow_pine_planks", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_PLANKS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_BOOKSHELF = ITEMS.register("shadow_pine_bookshelf", () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_PINE_SAPLING = ITEMS.register("shadow_pine_sapling", () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_SAPLING.get(), new Item.Properties()));
    public static final DeferredItem<Item> PURPLE_SHADOW_PINE_SAPLING = ITEMS.register("purple_shadow_pine_sapling", () -> new BlockItem(AerialHellBlocks.PURPLE_SHADOW_PINE_SAPLING.get(), new Item.Properties()));

    //stellar jungle tree
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_LOG = ITEMS.register("stellar_jungle_tree_log", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties(), 400));
    public static final DeferredItem<Item> STRIPPED_STELLAR_JUNGLE_TREE_LOG = ITEMS.register("stripped_stellar_jungle_tree_log", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties(), 400));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_WOOD = ITEMS.register("stellar_jungle_tree_wood", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_WOOD.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STRIPPED_STELLAR_JUNGLE_TREE_WOOD = ITEMS.register("stripped_stellar_jungle_tree_wood", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_STELLAR_JUNGLE_TREE_WOOD.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_LEAVES = ITEMS.register("stellar_jungle_tree_leaves", () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_PLANKS = ITEMS.register("stellar_jungle_tree_planks", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_PLANKS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_BOOKSHELF = ITEMS.register("stellar_jungle_tree_bookshelf", () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_SAPLING = ITEMS.register("stellar_jungle_tree_sapling", () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_SAPLING.get(), new Item.Properties()));
    public static final DeferredItem<Item> DEAD_STELLAR_JUNGLE_TREE_LOG = ITEMS.register("dead_stellar_jungle_tree_log", () -> new BurnableBlockItem(AerialHellBlocks.DEAD_STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties(), 300));

    //shroom
    public static final DeferredItem<Item> GIANT_CORTINARIUS_VIOLACEUS_STEM = ITEMS.register("giant_cortinarius_violaceus_stem", () -> new BurnableBlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_STEM.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = ITEMS.register("stripped_giant_cortinarius_violaceus_stem", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = ITEMS.register("giant_cortinarius_violaceus_bark_stem", () -> new BurnableBlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = ITEMS.register("stripped_giant_cortinarius_violaceus_bark_stem", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = ITEMS.register("giant_cortinarius_violaceus_cap_block", () -> new BlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> GIANT_CORTINARIUS_VIOLACEUS_LIGHT = ITEMS.register("giant_cortinarius_violaceus_light", () -> new BlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.get(), new Item.Properties()));
    public static final DeferredItem<Item> CORTINARIUS_VIOLACEUS = ITEMS.register("cortinarius_violaceus", () -> new BlockItem(AerialHellBlocks.CORTINARIUS_VIOLACEUS.get(), new Item.Properties()));

    public static final DeferredItem<Item> GIANT_VERDIGRIS_AGARIC_STEM = ITEMS.register("giant_verdigris_agaric_stem", () -> new BurnableBlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_STEM.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = ITEMS.register("stripped_giant_verdigris_agaric_stem", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> GIANT_VERDIGRIS_AGARIC_BARK_STEM = ITEMS.register("giant_verdigris_agaric_bark_stem", () -> new BurnableBlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_BARK_STEM.get(), new Item.Properties(), 75));
    public static final DeferredItem<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = ITEMS.register("stripped_giant_verdigris_agaric_bark_stem", () -> new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.get(), new Item.Properties(), 75));
    public static final DeferredItem<Item> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = ITEMS.register("giant_verdigris_agaric_cap_block", () -> new BlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> VERDIGRIS_AGARIC = ITEMS.register("verdigris_agaric", () -> new BlockItem(AerialHellBlocks.VERDIGRIS_AGARIC.get(), new Item.Properties()));
    public static final DeferredItem<Item> GLOWING_BOLETUS = ITEMS.register("glowing_boletus", () -> new BlockItem(AerialHellBlocks.GLOWING_BOLETUS.get(), new Item.Properties()));
    public static final DeferredItem<Item> TALL_GLOWING_BOLETUS = ITEMS.register("tall_glowing_boletus", () -> new BlockItem(AerialHellBlocks.TALL_GLOWING_BOLETUS.get(), new Item.Properties()));
    public static final DeferredItem<Item> BLUE_MEANIE_CLUSTER = ITEMS.register("blue_meanie_cluster", () -> new BlockItem(AerialHellBlocks.BLUE_MEANIE_CLUSTER.get(), new Item.Properties()));
    public static final DeferredItem<Item> GIANT_ROOT_SHROOM = ITEMS.register("giant_root_shroom", () -> new BlockItem(AerialHellBlocks.GIANT_ROOT_SHROOM.get(), new Item.Properties()));

    public static final DeferredItem<Item> GIANT_GANODERMA_APPLANATUM_BLOCK = ITEMS.register("giant_ganoderma_applanatum_block", () -> new BlockItem(AerialHellBlocks.GIANT_GANODERMA_APPLANATUM_BLOCK.get(), new Item.Properties()));

    public static final DeferredItem<Item> GRAY_SHROOM_PLANKS = ITEMS.register("gray_shroom_planks", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_PLANKS.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> GRAY_SHROOM_BOOKSHELF = ITEMS.register("gray_shroom_bookshelf", () -> new BlockItem(AerialHellBlocks.GRAY_SHROOM_BOOKSHELF.get(), new Item.Properties()));

    //shadow corrupted / uncorrupted variants
    public static final DeferredItem<Item> SHADOW_AERIAL_TREE_LOG = ITEMS.register("shadow_aerial_tree_log", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_AERIAL_TREE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_GOLDEN_BEECH_LOG = ITEMS.register("shadow_golden_beech_log", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_COPPER_PINE_LOG = ITEMS.register("shadow_copper_pine_log", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_COPPER_PINE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_LAPIS_ROBINIA_LOG = ITEMS.register("shadow_lapis_robinia_log", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_STELLAR_JUNGLE_TREE_LOG = ITEMS.register("shadow_stellar_jungle_tree_log", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> HOLLOW_SHADOW_PINE_LOG = ITEMS.register("hollow_shadow_pine_log", () -> new BurnableBlockItem(AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_AERIAL_TREE_LEAVES = ITEMS.register("shadow_aerial_tree_leaves", () -> new BlockItem(AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_GOLDEN_BEECH_LEAVES = ITEMS.register("shadow_golden_beech_leaves", () -> new BlockItem(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_COPPER_PINE_LEAVES = ITEMS.register("shadow_copper_pine_leaves", () -> new BlockItem(AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_LAPIS_ROBINIA_LEAVES = ITEMS.register("shadow_lapis_robinia_leaves", () -> new BlockItem(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_STELLAR_JUNGLE_TREE_LEAVES = ITEMS.register("shadow_stellar_jungle_tree_leaves", () -> new BlockItem(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> HOLLOW_SHADOW_PINE_LEAVES = ITEMS.register("hollow_shadow_pine_leaves", () -> new BlockItem(AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> HOLLOW_PURPLE_SHADOW_PINE_LEAVES = ITEMS.register("hollow_purple_shadow_pine_leaves", () -> new BlockItem(AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.get(), new Item.Properties()));

    //ladder
    public static final DeferredItem<Item> SKY_LADDER = ITEMS.register("sky_ladder", () -> new BlockItem(AerialHellBlocks.SKY_LADDER.get(), new Item.Properties()));

    //natural blocks and items
    public static final DeferredItem<Item> STELLAR_STONE = ITEMS.register("stellar_stone", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE = ITEMS.register("stellar_cobblestone", () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_STELLAR_STONE = ITEMS.register("mossy_stellar_stone", () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_STELLAR_COBBLESTONE = ITEMS.register("mossy_stellar_cobblestone", () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_CLAY = ITEMS.register("stellar_clay", () -> new BlockItem(AerialHellBlocks.STELLAR_CLAY.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS = ITEMS.register("stellar_stone_bricks", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GLAUCOPHANITE = ITEMS.register("glaucophanite", () -> new BlockItem(AerialHellBlocks.GLAUCOPHANITE.get(), new Item.Properties()));
    public static final DeferredItem<Item> POLISHED_GLAUCOPHANITE = ITEMS.register("polished_glaucophanite", () -> new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_STONE = ITEMS.register("shadow_stone", () -> new BlockItem(AerialHellBlocks.SHADOW_STONE.get(), new Item.Properties()));

    //crystal
    public static final DeferredItem<Item> CRYSTAL_BLOCK = ITEMS.register("crystal_block", () -> new BlockItem(AerialHellBlocks.CRYSTAL_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRYSTAL_BRICKS = ITEMS.register("crystal_bricks", () -> new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRYSTAL_BRICKS_SLAB = ITEMS.register("crystal_bricks_slab", () -> new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRYSTAL_BRICKS_STAIRS = ITEMS.register("crystal_bricks_stairs", () -> new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRYSTAL_BRICKS_WALL = ITEMS.register("crystal_bricks_wall", () -> new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_STONE_CRYSTAL_BLOCK = ITEMS.register("stellar_stone_crystal_block", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CRYSTAL_BLOCK = ITEMS.register("shadow_crystal_block", () -> new BlockItem(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> CRYSTALLIZED_LEAVES = ITEMS.register("crystallized_leaves", () -> new BlockItem(AerialHellBlocks.CRYSTALLIZED_LEAVES.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRYSTAL = ITEMS.register("crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_STONE_CRYSTAL = ITEMS.register("stellar_stone_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CRYSTAL = ITEMS.register("shadow_crystal", () -> new Item(new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));

    //glass and glass pane
    public static final DeferredItem<Item> SLIPPERY_SAND_GLASS = ITEMS.register("slippery_sand_glass", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> RED_SLIPPERY_SAND_GLASS = ITEMS.register("red_slippery_sand_glass", () -> new BlockItem(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> BLACK_SLIPPERY_SAND_GLASS = ITEMS.register("black_slippery_sand_glass", () -> new BlockItem(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> BLUE_SLIPPERY_SAND_GLASS = ITEMS.register("blue_slippery_sand_glass", () -> new BlockItem(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GREEN_SLIPPERY_SAND_GLASS = ITEMS.register("green_slippery_sand_glass", () -> new BlockItem(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_GLASS_PANE = ITEMS.register("slippery_sand_glass_pane", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));
    public static final DeferredItem<Item> RED_SLIPPERY_SAND_GLASS_PANE = ITEMS.register("red_slippery_sand_glass_pane", () -> new BlockItem(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));
    public static final DeferredItem<Item> BLACK_SLIPPERY_SAND_GLASS_PANE = ITEMS.register("black_slippery_sand_glass_pane", () -> new BlockItem(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));
    public static final DeferredItem<Item> BLUE_SLIPPERY_SAND_GLASS_PANE = ITEMS.register("blue_slippery_sand_glass_pane", () -> new BlockItem(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GREEN_SLIPPERY_SAND_GLASS_PANE = ITEMS.register("green_slippery_sand_glass_pane", () -> new BlockItem(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));

    //ghost boat
    public static final DeferredItem<Item> GHOST_BOAT_PLANKS = ITEMS.register("ghost_boat_planks", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_PLANKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_LOG = ITEMS.register("ghost_boat_log", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_LOG.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_WOOD = ITEMS.register("ghost_boat_wood", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_WOOD.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_SLAB = ITEMS.register("ghost_boat_slab", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_STAIRS = ITEMS.register("ghost_boat_stairs", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_FENCE = ITEMS.register("ghost_boat_fence", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_FENCE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_GATE = ITEMS.register("ghost_boat_gate", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_GATE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_DOOR = ITEMS.register("ghost_boat_door", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_DOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_TRAPDOOR = ITEMS.register("ghost_boat_trapdoor", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_TRAPDOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_CHEST = ITEMS.register("ghost_boat_chest", () -> new ChestBlockItem(AerialHellBlocks.GHOST_BOAT_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_WOOL = ITEMS.register("ghost_boat_wool", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_WOOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_STELLAR_COBBLESTONE = ITEMS.register("ghost_stellar_cobblestone", () -> new BlockItem(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_RUBY_BLOCK = ITEMS.register("ghost_ruby_block", () -> new BlockItem(AerialHellBlocks.GHOST_RUBY_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_FLUORITE_BLOCK = ITEMS.register("ghost_fluorite_block", () -> new BlockItem(AerialHellBlocks.GHOST_FLUORITE_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_AZURITE_BLOCK = ITEMS.register("ghost_azurite_block", () -> new BlockItem(AerialHellBlocks.GHOST_AZURITE_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_GOLD_BLOCK = ITEMS.register("ghost_gold_block", () -> new BlockItem(AerialHellBlocks.GHOST_GOLD_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_BARREL = ITEMS.register("ghost_boat_barrel", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_BARREL.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_CRAFTING_TABLE = ITEMS.register("ghost_boat_crafting_table", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_CRAFTING_TABLE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_BOAT_VINE_ROPE_SPOOL = ITEMS.register("ghost_boat_vine_rope_spool", () -> new BlockItem(AerialHellBlocks.GHOST_BOAT_VINE_ROPE_SPOOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_LANTERN = ITEMS.register("ghost_lantern", () -> new BlockItem(AerialHellBlocks.GHOST_LANTERN.get(), new Item.Properties()));

    //reactors
    public static final DeferredItem<Item> WEAK_LIGHT_REACTOR = ITEMS.register("weak_light_reactor", () -> new WithInformationBlockItem(AerialHellBlocks.WEAK_LIGHT_REACTOR.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> HIGH_POWER_LIGHT_REACTOR = ITEMS.register("high_power_light_reactor", () -> new WithInformationBlockItem(AerialHellBlocks.HIGH_POWER_LIGHT_REACTOR.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> WEAK_SHADOW_REACTOR = ITEMS.register("weak_shadow_reactor", () -> new WithInformationBlockItem(AerialHellBlocks.WEAK_SHADOW_REACTOR.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> HIGH_POWER_SHADOW_REACTOR = ITEMS.register("high_power_shadow_reactor", () -> new WithInformationBlockItem(AerialHellBlocks.HIGH_POWER_SHADOW_REACTOR.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));

    public static final DeferredItem<Item> BROKEN_WEAK_LIGHT_REACTOR = ITEMS.register("broken_weak_light_reactor", () -> new WithInformationBlockItem(AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> BROKEN_HIGH_POWER_LIGHT_REACTOR = ITEMS.register("broken_high_power_light_reactor", () -> new WithInformationBlockItem(AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> BROKEN_WEAK_SHADOW_REACTOR = ITEMS.register("broken_weak_shadow_reactor", () -> new WithInformationBlockItem(AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> BROKEN_HIGH_POWER_SHADOW_REACTOR = ITEMS.register("broken_high_power_shadow_reactor", () -> new WithInformationBlockItem(AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));

    //solid_ethers
    public static final DeferredItem<Item> WHITE_SOLID_ETHER = ITEMS.register("white_solid_ether", () -> new BlockItem(AerialHellBlocks.WHITE_SOLID_ETHER.get(), new Item.Properties()));
    public static final DeferredItem<Item> BLUE_SOLID_ETHER = ITEMS.register("blue_solid_ether", () -> new BlockItem(AerialHellBlocks.BLUE_SOLID_ETHER.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_SOLID_ETHER = ITEMS.register("golden_solid_ether", () -> new BlockItem(AerialHellBlocks.GOLDEN_SOLID_ETHER.get(), new Item.Properties()));
    public static final DeferredItem<Item> GREEN_SOLID_ETHER = ITEMS.register("green_solid_ether", () -> new BlockItem(AerialHellBlocks.GREEN_SOLID_ETHER.get(), new Item.Properties()));
    public static final DeferredItem<Item> PURPLE_SOLID_ETHER = ITEMS.register("purple_solid_ether", () -> new BlockItem(AerialHellBlocks.PURPLE_SOLID_ETHER.get(), new Item.Properties()));

    //dungeons blocks
    public static final DeferredItem<Item> MUD_BRICKS = ITEMS.register("mud_bricks", () -> new BlockItem(AerialHellBlocks.MUD_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_MUD_BRICKS = ITEMS.register("cracked_mud_bricks", () -> new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_MUD_BRICKS = ITEMS.register("mossy_mud_bricks", () -> new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHISELED_MUD_BRICKS = ITEMS.register("chiseled_mud_bricks", () -> new BlockItem(AerialHellBlocks.CHISELED_MUD_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_MUD_BRICKS = ITEMS.register("light_mud_bricks", () -> new BlockItem(AerialHellBlocks.LIGHT_MUD_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_LIGHT_MUD_BRICKS = ITEMS.register("cracked_light_mud_bricks", () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_MUD_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_STONE = ITEMS.register("lunatic_stone", () -> new BlockItem(AerialHellBlocks.LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> DARK_LUNATIC_STONE = ITEMS.register("dark_lunatic_stone", () -> new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> ROOF_LUNATIC_STONE = ITEMS.register("roof_lunatic_stone", () -> new BlockItem(AerialHellBlocks.ROOF_LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_LUNATIC_STONE = ITEMS.register("cracked_lunatic_stone", () -> new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHISELED_LUNATIC_STONE = ITEMS.register("chiseled_lunatic_stone", () -> new BlockItem(AerialHellBlocks.CHISELED_LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_LUNATIC_STONE = ITEMS.register("light_lunatic_stone", () -> new BlockItem(AerialHellBlocks.LIGHT_LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> ROOF_LIGHT_LUNATIC_STONE = ITEMS.register("roof_light_lunatic_stone", () -> new BlockItem(AerialHellBlocks.ROOF_LIGHT_LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_LIGHT_LUNATIC_STONE = ITEMS.register("cracked_light_lunatic_stone", () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BRICKS = ITEMS.register("shadow_catacombs_bricks", () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_SHADOW_CATACOMBS_BRICKS = ITEMS.register("cracked_shadow_catacombs_bricks", () -> new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_SHADOW_CATACOMBS_BRICKS = ITEMS.register("mossy_shadow_catacombs_bricks", () -> new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHISELED_SHADOW_CATACOMBS_BRICKS = ITEMS.register("chiseled_shadow_catacombs_bricks", () -> new BlockItem(AerialHellBlocks.CHISELED_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> BONE_SHADOW_CATACOMBS_BRICKS = ITEMS.register("bone_shadow_catacombs_bricks", () -> new BlockItem(AerialHellBlocks.BONE_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SKULL_SHADOW_CATACOMBS_BRICKS = ITEMS.register("skull_shadow_catacombs_bricks", () -> new BlockItem(AerialHellBlocks.SKULL_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_SHADOW_CATACOMBS_BRICKS = ITEMS.register("light_shadow_catacombs_bricks", () -> new BlockItem(AerialHellBlocks.LIGHT_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = ITEMS.register("cracked_light_shadow_catacombs_bricks", () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BRICKS = ITEMS.register("golden_nether_bricks", () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_GOLDEN_NETHER_BRICKS = ITEMS.register("cracked_golden_nether_bricks", () -> new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHISELED_GOLDEN_NETHER_BRICKS = ITEMS.register("chiseled_golden_nether_bricks", () -> new BlockItem(AerialHellBlocks.CHISELED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_GOLDEN_NETHER_BRICKS = ITEMS.register("light_golden_nether_bricks", () -> new BlockItem(AerialHellBlocks.LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = ITEMS.register("cracked_light_golden_nether_bricks", () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_PILLAR = ITEMS.register("lunatic_pillar", () -> new BlockItem(AerialHellBlocks.LUNATIC_PILLAR.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_PILLAR_TOP = ITEMS.register("lunatic_pillar_top", () -> new BlockItem(AerialHellBlocks.LUNATIC_PILLAR_TOP.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_STONE = ITEMS.register("volucite_stone", () -> new BlockItem(AerialHellBlocks.VOLUCITE_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_VOLUCITE_STONE = ITEMS.register("cracked_volucite_stone", () -> new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHISELED_VOLUCITE_STONE = ITEMS.register("chiseled_volucite_stone", () -> new BlockItem(AerialHellBlocks.CHISELED_VOLUCITE_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_VOLUCITE_STONE = ITEMS.register("light_volucite_stone", () -> new BlockItem(AerialHellBlocks.LIGHT_VOLUCITE_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_LIGHT_VOLUCITE_STONE = ITEMS.register("cracked_light_volucite_stone", () -> new BlockItem(AerialHellBlocks.CRACKED_LIGHT_VOLUCITE_STONE.get(), new Item.Properties()));

    //dungeon cores
    public static final DeferredItem<Item> MUD_DUNGEON_CORE = ITEMS.register("mud_dungeon_core", () -> new BlockItem(AerialHellBlocks.MUD_DUNGEON_CORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_DUNGEON_CORE = ITEMS.register("lunatic_dungeon_core", () -> new BlockItem(AerialHellBlocks.LUNATIC_DUNGEON_CORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_DUNGEON_CORE = ITEMS.register("shadow_catacombs_dungeon_core", () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_DUNGEON_CORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_NETHER_DUNGEON_CORE = ITEMS.register("golden_nether_dungeon_core", () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_DUNGEON_CORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_DUNGEON_CORE = ITEMS.register("volucite_dungeon_core", () -> new BlockItem(AerialHellBlocks.VOLUCITE_DUNGEON_CORE.get(), new Item.Properties()));

    //dungeons slabs, stairs & walls
    public static final DeferredItem<Item> MUD_BRICKS_SLAB = ITEMS.register("mud_bricks_slab", () -> new BlockItem(AerialHellBlocks.MUD_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> MUD_BRICKS_STAIRS = ITEMS.register("mud_bricks_stairs", () -> new BlockItem(AerialHellBlocks.MUD_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> MUD_BRICKS_WALL = ITEMS.register("mud_bricks_wall", () -> new BlockItem(AerialHellBlocks.MUD_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_MUD_BRICKS_SLAB = ITEMS.register("cracked_mud_bricks_slab", () -> new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_MUD_BRICKS_STAIRS = ITEMS.register("cracked_mud_bricks_stairs", () -> new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_MUD_BRICKS_WALL = ITEMS.register("cracked_mud_bricks_wall", () -> new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_MUD_BRICKS_SLAB = ITEMS.register("mossy_mud_bricks_slab", () -> new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_MUD_BRICKS_STAIRS = ITEMS.register("mossy_mud_bricks_stairs", () -> new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_MUD_BRICKS_WALL = ITEMS.register("mossy_mud_bricks_wall", () -> new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_STONE_SLAB = ITEMS.register("volucite_stone_slab", () -> new BlockItem(AerialHellBlocks.VOLUCITE_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_STONE_STAIRS = ITEMS.register("volucite_stone_stairs", () -> new BlockItem(AerialHellBlocks.VOLUCITE_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_STONE_WALL = ITEMS.register("volucite_stone_wall", () -> new BlockItem(AerialHellBlocks.VOLUCITE_STONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_VOLUCITE_STONE_SLAB = ITEMS.register("cracked_volucite_stone_slab", () -> new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_VOLUCITE_STONE_STAIRS = ITEMS.register("cracked_volucite_stone_stairs", () -> new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_VOLUCITE_STONE_WALL = ITEMS.register("cracked_volucite_stone_wall", () -> new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_STONE_SLAB = ITEMS.register("lunatic_stone_slab", () -> new BlockItem(AerialHellBlocks.LUNATIC_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_STONE_STAIRS = ITEMS.register("lunatic_stone_stairs", () -> new BlockItem(AerialHellBlocks.LUNATIC_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_STONE_WALL = ITEMS.register("lunatic_stone_wall", () -> new BlockItem(AerialHellBlocks.LUNATIC_STONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> DARK_LUNATIC_STONE_SLAB = ITEMS.register("dark_lunatic_stone_slab", () -> new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> DARK_LUNATIC_STONE_STAIRS = ITEMS.register("dark_lunatic_stone_stairs", () -> new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> DARK_LUNATIC_STONE_WALL = ITEMS.register("dark_lunatic_stone_wall", () -> new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_LUNATIC_STONE_SLAB = ITEMS.register("cracked_lunatic_stone_slab", () -> new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_LUNATIC_STONE_STAIRS = ITEMS.register("cracked_lunatic_stone_stairs", () -> new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_LUNATIC_STONE_WALL = ITEMS.register("cracked_lunatic_stone_wall", () -> new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BRICKS_SLAB = ITEMS.register("shadow_catacombs_bricks_slab", () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BRICKS_STAIRS = ITEMS.register("shadow_catacombs_bricks_stairs", () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BRICKS_WALL = ITEMS.register("shadow_catacombs_bricks_wall", () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = ITEMS.register("cracked_shadow_catacombs_bricks_slab", () -> new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = ITEMS.register("cracked_shadow_catacombs_bricks_stairs", () -> new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = ITEMS.register("cracked_shadow_catacombs_bricks_wall", () -> new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = ITEMS.register("mossy_shadow_catacombs_bricks_slab", () -> new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = ITEMS.register("mossy_shadow_catacombs_bricks_stairs", () -> new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = ITEMS.register("mossy_shadow_catacombs_bricks_wall", () -> new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_BARS = ITEMS.register("shadow_bars", () -> new BlockItem(AerialHellBlocks.SHADOW_BARS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BRICKS_SLAB = ITEMS.register("golden_nether_bricks_slab", () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BRICKS_STAIRS = ITEMS.register("golden_nether_bricks_stairs", () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BRICKS_WALL = ITEMS.register("golden_nether_bricks_wall", () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = ITEMS.register("cracked_golden_nether_bricks_slab", () -> new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = ITEMS.register("cracked_golden_nether_bricks_stairs", () -> new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_GOLDEN_NETHER_BRICKS_WALL = ITEMS.register("cracked_golden_nether_bricks_wall", () -> new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties()));

    //smoky quartz
    public static final DeferredItem<Item> SMOKY_QUARTZ = ITEMS.register("smoky_quartz",() -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SMOKY_QUARTZ_BLOCK = ITEMS.register("smoky_quartz_block", () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> SMOOTH_SMOKY_QUARTZ = ITEMS.register("smooth_smoky_quartz", () -> new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHISELED_SMOKY_QUARTZ_BLOCK = ITEMS.register("chiseled_smoky_quartz_block", () -> new BlockItem(AerialHellBlocks.CHISELED_SMOKY_QUARTZ_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> SMOKY_QUARTZ_BRICKS = ITEMS.register("smoky_quartz_bricks", () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SMOKY_QUARTZ_PILLAR = ITEMS.register("smoky_quartz_pillar", () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_PILLAR.get(), new Item.Properties()));
    public static final DeferredItem<Item> SMOKY_QUARTZ_SLAB = ITEMS.register("smoky_quartz_slab", () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> SMOOTH_SMOKY_QUARTZ_SLAB = ITEMS.register("smooth_smoky_quartz_slab", () -> new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> SMOKY_QUARTZ_STAIRS = ITEMS.register("smoky_quartz_stairs", () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SMOOTH_SMOKY_QUARTZ_STAIRS = ITEMS.register("smooth_smoky_quartz_stairs", () -> new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS.get(), new Item.Properties()));

    //dungeon trapped blocks
    public static final DeferredItem<Item> TRAPPED_MUD_BRICKS = ITEMS.register("trapped_mud_bricks", () -> new BlockItem(AerialHellBlocks.TRAPPED_MUD_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> TRAPPED_LIGHT_MUD_BRICKS = ITEMS.register("trapped_light_mud_bricks", () -> new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_MUD_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> TRAPPED_LUNATIC_STONE = ITEMS.register("trapped_lunatic_stone", () -> new BlockItem(AerialHellBlocks.TRAPPED_LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> TRAPPED_LIGHT_LUNATIC_STONE = ITEMS.register("trapped_light_lunatic_stone", () -> new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_LUNATIC_STONE.get(), new Item.Properties()));
    public static final DeferredItem<Item> TRAPPED_GOLDEN_NETHER_BRICKS = ITEMS.register("trapped_golden_nether_bricks", () -> new BlockItem(AerialHellBlocks.TRAPPED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = ITEMS.register("trapped_light_golden_nether_bricks", () -> new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));

    //dungeon other blocks, loots
    public static final DeferredItem<Item> MUD_BONE_BLOCK = ITEMS.register("mud_bone_block", () -> new BlockItem(AerialHellBlocks.MUD_BONE_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> MUD_BONE_PILE_BLOCK = ITEMS.register("mud_bone_pile_block", () -> new BlockItem(AerialHellBlocks.MUD_BONE_PILE_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> MUD_BONE = ITEMS.register("mud_bone",() -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> THORNY_COBWEB = ITEMS.register("thorny_cobweb", () -> new BlockItem(AerialHellBlocks.THORNY_COBWEB.get(), new Item.Properties()));
    public static final DeferredItem<Item> AERIAL_NETHERRACK = ITEMS.register("aerial_netherrack", () -> new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK.get(), new Item.Properties()));
    public static final DeferredItem<Item> AERIAL_NETHERRACK_SLAB = ITEMS.register("aerial_netherrack_slab", () -> new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> AERIAL_NETHERRACK_STAIRS = ITEMS.register("aerial_netherrack_stairs", () -> new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> AERIAL_NETHERRACK_WALL = ITEMS.register("aerial_netherrack_wall", () -> new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_WALL.get(), new Item.Properties()));

    //dungeon bookshelfs
    public static final DeferredItem<Item> MUD_BOOKSHELF = ITEMS.register("mud_bookshelf", () -> new BlockItem(AerialHellBlocks.MUD_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_BOOKSHELF = ITEMS.register("lunatic_bookshelf", () -> new BlockItem(AerialHellBlocks.LUNATIC_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_NETHER_BOOKSHELF = ITEMS.register("golden_nether_bookshelf", () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_BOOKSHELF = ITEMS.register("shadow_catacombs_bookshelf", () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_BOOKSHELF = ITEMS.register("volucite_bookshelf", () -> new BlockItem(AerialHellBlocks.VOLUCITE_BOOKSHELF.get(), new Item.Properties()));

    //glyph blocks
    public static final DeferredItem<Item> MUD_GLYPH_BLOCK = ITEMS.register("mud_glyph_block", () -> new BlockItem(AerialHellBlocks.MUD_GLYPH_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_GLYPH_BLOCK = ITEMS.register("lunatic_glyph_block", () -> new BlockItem(AerialHellBlocks.LUNATIC_GLYPH_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_NETHER_PRISON_GLYPH_BLOCK = ITEMS.register("golden_nether_prison_glyph_block", () -> new BlockItem(AerialHellBlocks.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_GLYPH_BLOCK = ITEMS.register("volucite_glyph_block", () -> new BlockItem(AerialHellBlocks.VOLUCITE_GLYPH_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_GLYPH_BLOCK = ITEMS.register("shadow_catacombs_glyph_block", () -> new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_GLYPH_BLOCK.get(), new Item.Properties()));

    //trophies
    public static final DeferredItem<Item> MUD_CYCLE_MAGE_TROPHY = ITEMS.register("mud_cycle_mage_trophy", () -> new BlockItem(AerialHellBlocks.MUD_CYCLE_MAGE_TROPHY.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNAR_PRIEST_TROPHY = ITEMS.register("lunar_priest_trophy", () -> new BlockItem(AerialHellBlocks.LUNAR_PRIEST_TROPHY.get(), new Item.Properties()));
    public static final DeferredItem<Item> LILITH_TROPHY = ITEMS.register("lilith_trophy", () -> new BlockItem(AerialHellBlocks.LILITH_TROPHY.get(), new Item.Properties()));
    public static final DeferredItem<Item> CHAINED_GOD_TROPHY = ITEMS.register("chained_god_trophy", () -> new BlockItem(AerialHellBlocks.CHAINED_GOD_TROPHY.get(), new Item.Properties()));

    //ores
    public static final DeferredItem<Item> IRON_STELLAR_ORE = ITEMS.register("iron_stellar_ore", () -> new BlockItem(AerialHellBlocks.IRON_STELLAR_ORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLD_STELLAR_ORE = ITEMS.register("gold_stellar_ore", () -> new BlockItem(AerialHellBlocks.GOLD_STELLAR_ORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_STELLAR_ORE = ITEMS.register("diamond_stellar_ore", () -> new BlockItem(AerialHellBlocks.DIAMOND_STELLAR_ORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> FLUORITE_ORE = ITEMS.register("fluorite_ore", () -> new BlockItem(AerialHellBlocks.FLUORITE_ORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> MAGMATIC_GEL_ORE = ITEMS.register("magmatic_gel_ore", () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_ORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> RUBY_ORE = ITEMS.register("ruby_ore", () -> new BlockItem(AerialHellBlocks.RUBY_ORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> AZURITE_ORE = ITEMS.register("azurite_ore", () -> new BlockItem(AerialHellBlocks.AZURITE_ORE.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_ORE = ITEMS.register("volucite_ore", () -> new BlockItem(AerialHellBlocks.VOLUCITE_ORE.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> OBSIDIAN_ORE = ITEMS.register("obsidian_ore", () -> new BlockItem(AerialHellBlocks.OBSIDIAN_ORE.get(), new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> SMOKY_QUARTZ_ORE = ITEMS.register("smoky_quartz_ore", () -> new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_ORE.get(), new Item.Properties().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> RAW_RUBY_BLOCK = ITEMS.register("raw_ruby_block", () -> new BlockItem(AerialHellBlocks.RAW_RUBY_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> RAW_AZURITE_BLOCK = ITEMS.register("raw_azurite_crystal_block", () -> new BlockItem(AerialHellBlocks.RAW_AZURITE_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> RAW_VOLUCITE_BLOCK = ITEMS.register("raw_volucite_block", () -> new BlockItem(AerialHellBlocks.RAW_VOLUCITE_BLOCK.get(), new Item.Properties()));

    public static final DeferredItem<Item> RAW_RUBY = ITEMS.register("raw_ruby", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.register("raw_azurite_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_VOLUCITE = ITEMS.register("raw_volucite", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FLUORITE_BLOCK = ITEMS.register("fluorite_block", () -> new BlockItem(AerialHellBlocks.FLUORITE_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> MAGMATIC_GEL_BLOCK = ITEMS.register("magmatic_gel_block", () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> RUBY_BLOCK = ITEMS.register("ruby_block", () -> new BlockItem(AerialHellBlocks.RUBY_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> AZURITE_BLOCK = ITEMS.register("azurite_block", () -> new BlockItem(AerialHellBlocks.AZURITE_BLOCK.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_BLOCK = ITEMS.register("volucite_block", () -> new BlockItem(AerialHellBlocks.VOLUCITE_BLOCK.get(), new Item.Properties()));

    public static final DeferredItem<Item> FLUORITE = ITEMS.register("fluorite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MAGMATIC_GEL = ITEMS.register("magmatic_gel",() -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AZURITE_CRYSTAL = ITEMS.register("azurite_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_VIBRANT = ITEMS.register("volucite_vibrant", () -> new VoluciteVibrantItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));

    public static final DeferredItem<Item> OVERHEATED_RUBY = ITEMS.register("overheated_ruby", () -> new WithInformationItem(new Item.Properties()));
    public static final DeferredItem<Item> OVERHEATED_VOLUCITE = ITEMS.register("overheated_volucite", () -> new WithInformationItem(new Item.Properties()));

    //legendary ores
    public static final DeferredItem<Item> ARSONIST_INGOT = ITEMS.register("arsonist_ingot", () -> new Item(new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue()).fireResistant()));
    public static final DeferredItem<Item> LUNATIC_CRYSTAL = ITEMS.register("lunatic_crystal", () -> new Item(new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> CURSED_CRYSAL = ITEMS.register("cursed_crystal", () -> new Item(new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));

    public static final DeferredItem<Item> ARSONIST_BLOCK = ITEMS.register("arsonist_block", () -> new BlockItem(AerialHellBlocks.ARSONIST_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue()).fireResistant()));
    public static final DeferredItem<Item> LUNATIC_CRYSTAL_BLOCK = ITEMS.register("lunatic_crystal_block", () -> new BlockItem(AerialHellBlocks.LUNATIC_CRYSTAL_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> CURSED_CRYSAL_BLOCK = ITEMS.register("cursed_crystal_block", () -> new BlockItem(AerialHellBlocks.CURSED_CRYSAL_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));

    //cactus
    public static final DeferredItem<Item> SKY_CACTUS = ITEMS.register("sky_cactus", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_PLANKS = ITEMS.register("sky_cactus_fiber_planks", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_PLANKS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_BOOKSHELF = ITEMS.register("sky_cactus_fiber_bookshelf", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BOOKSHELF.get(), new Item.Properties()));
    public static final DeferredItem<Item> VIBRANT_SKY_CACTUS = ITEMS.register("vibrant_sky_cactus", () -> new BlockItem(AerialHellBlocks.VIBRANT_SKY_CACTUS.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> VIBRANT_SKY_CACTUS_FIBER_LANTERN = ITEMS.register("vibrant_sky_cactus_fiber_lantern", () -> new BlockItem(AerialHellBlocks.VIBRANT_SKY_CACTUS_FIBER_LANTERN.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));

    //bushes
    public static final DeferredItem<Item> AERIAL_BERRY_SEEDS = ITEMS.register("aerial_berry_seeds",() -> new BlockItem(AerialHellBlocks.AERIAL_BERRY_BUSH.get(), new Item.Properties().useItemDescriptionPrefix()));
    public static final DeferredItem<Item> VIBRANT_AERIAL_BERRY_SEEDS = ITEMS.register("vibrant_aerial_berry_seeds",() -> new BlockItem(AerialHellBlocks.VIBRANT_AERIAL_BERRY_BUSH.get(), new Item.Properties().useItemDescriptionPrefix().rarity(AerialHellRarities.VIBRANT.getValue())));

    //crops
    public static final DeferredItem<Item> STELLAR_WHEAT_SEEDS = ITEMS.register("stellar_wheat_seeds",() -> new BlockItem(AerialHellBlocks.STELLAR_WHEAT.get(), new Item.Properties().useItemDescriptionPrefix()));
    public static final DeferredItem<Item> STELLAR_WHEAT = ITEMS.register("stellar_wheat",() -> new Item(new Item.Properties()));

    //vertical growing plants
    public static final DeferredItem<Item> CLIMBING_VINE = ITEMS.register("climbing_vine",() -> new BlockItem(AerialHellBlocks.CLIMBING_VINE.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_SUGAR_CANE = ITEMS.register("stellar_sugar_cane",() -> new BlockItem(AerialHellBlocks.STELLAR_SUGAR_CANE.get(), new Item.Properties()));

    //chorus like
    public static final DeferredItem<Item> FULL_MOON_FLOWER = ITEMS.register("full_moon_flower",() -> new BlockItem(AerialHellBlocks.FULL_MOON_FLOWER.get(), new Item.Properties()));

    //vines
    public static final DeferredItem<Item> VINE_BLOSSOM = ITEMS.register("vine_blossom",() -> new BlockItem(AerialHellBlocks.BLOSSOMING_VINES.get(), new Item.Properties().useItemDescriptionPrefix()));
    public static final DeferredItem<Item> LAZULI_ROOTS = ITEMS.register("lazuli_roots", () -> new BlockItem(AerialHellBlocks.LAZULI_ROOTS.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_ROOTS = ITEMS.register("stellar_roots", () -> new BlockItem(AerialHellBlocks.STELLAR_ROOTS.get(), new Item.Properties()));
    public static final DeferredItem<Item> DEAD_ROOTS = ITEMS.register("dead_roots", () -> new BlockItem(AerialHellBlocks.DEAD_ROOTS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GLOWING_ROOTS = ITEMS.register("glowing_roots", () -> new BlockItem(AerialHellBlocks.GLOWING_ROOTS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_GLOWING_ROOTS = ITEMS.register("shadow_glowing_roots", () -> new BlockItem(AerialHellBlocks.SHADOW_GLOWING_ROOTS.get(), new Item.Properties()));

    //grass
    public static final DeferredItem<Item> STELLAR_GRASS = ITEMS.register("stellar_grass", () -> new BlockItem(AerialHellBlocks.STELLAR_GRASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_GRASS_BALL = ITEMS.register("stellar_grass_ball", () -> new BlockItem(AerialHellBlocks.STELLAR_GRASS_BALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_FERN = ITEMS.register("stellar_fern", () -> new BlockItem(AerialHellBlocks.STELLAR_FERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_TALL_GRASS = ITEMS.register("stellar_tall_grass", () -> new BlockItem(AerialHellBlocks.STELLAR_TALL_GRASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_TALL_FERN = ITEMS.register("stellar_tall_fern", () -> new BlockItem(AerialHellBlocks.STELLAR_TALL_FERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_VERY_TALL_GRASS = ITEMS.register("stellar_very_tall_grass",() -> new BlockItem(AerialHellBlocks.STELLAR_VERY_TALL_GRASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> BLUISH_FERN = ITEMS.register("bluish_fern", () -> new BlockItem(AerialHellBlocks.BLUISH_FERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> TALL_BLUISH_FERN = ITEMS.register("tall_bluish_fern", () -> new BlockItem(AerialHellBlocks.TALL_BLUISH_FERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> POLYCHROME_FERN = ITEMS.register("polychrome_fern", () -> new BlockItem(AerialHellBlocks.POLYCHROME_FERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> TALL_POLYCHROME_FERN = ITEMS.register("tall_polychrome_fern", () -> new BlockItem(AerialHellBlocks.TALL_POLYCHROME_FERN.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_DEAD_BUSH = ITEMS.register("stellar_dead_bush", () -> new BlockItem(AerialHellBlocks.STELLAR_DEAD_BUSH.get(), new Item.Properties()));
    public static final DeferredItem<Item> BRAMBLES = ITEMS.register("brambles", () -> new BlockItem(AerialHellBlocks.BRAMBLES.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_BRAMBLES = ITEMS.register("shadow_brambles", () -> new BlockItem(AerialHellBlocks.SHADOW_BRAMBLES.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_GRASS = ITEMS.register("shadow_grass", () -> new BlockItem(AerialHellBlocks.SHADOW_GRASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_GRASS_BALL = ITEMS.register("shadow_grass_ball", () -> new BlockItem(AerialHellBlocks.SHADOW_GRASS_BALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> PURPLISH_STELLAR_GRASS = ITEMS.register("purplish_stellar_grass", () -> new BlockItem(AerialHellBlocks.PURPLISH_STELLAR_GRASS.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_CLOVERS = ITEMS.register("stellar_clovers", () -> new BlockItem(AerialHellBlocks.STELLAR_CLOVERS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GLOWING_STELLAR_GRASS = ITEMS.register("glowing_stellar_grass", () -> new BlockItem(AerialHellBlocks.GLOWING_STELLAR_GRASS.get(), new Item.Properties()));

    //flowers
    public static final DeferredItem<Item> BLUE_FLOWER = ITEMS.register("blue_flower", () -> new BlockItem(AerialHellBlocks.BLUE_FLOWER.get(), new Item.Properties()));
    public static final DeferredItem<Item> BLACK_ROSE = ITEMS.register("black_rose", () -> new BlockItem(AerialHellBlocks.BLACK_ROSE.get(), new Item.Properties()));
    public static final DeferredItem<Item> BELLFLOWER = ITEMS.register("bellflower", () -> new BlockItem(AerialHellBlocks.BELLFLOWER.get(), new Item.Properties()));

    //with gui
    public static final DeferredItem<Item> OSCILLATOR = ITEMS.register("oscillator", () -> new BlockItem(AerialHellBlocks.OSCILLATOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> FREEZER = ITEMS.register("freezer", () -> new BlockItem(AerialHellBlocks.FREEZER.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_FURNACE = ITEMS.register("stellar_furnace", () -> new BlockItem(AerialHellBlocks.STELLAR_FURNACE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GHOST_STELLAR_FURNACE = ITEMS.register("ghost_stellar_furnace", () -> new BlockItem(AerialHellBlocks.GHOST_STELLAR_FURNACE.get(), new Item.Properties()));

    //chests
    public static final DeferredItem<Item> AERIAL_TREE_CHEST = ITEMS.register("aerial_tree_chest", () -> new ChestBlockItem(AerialHellBlocks.AERIAL_TREE_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_BEECH_CHEST = ITEMS.register("golden_beech_chest", () -> new ChestBlockItem(AerialHellBlocks.GOLDEN_BEECH_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PINE_CHEST = ITEMS.register("copper_pine_chest", () -> new ChestBlockItem(AerialHellBlocks.COPPER_PINE_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_CHEST = ITEMS.register("lapis_robinia_chest", () -> new ChestBlockItem(AerialHellBlocks.LAPIS_ROBINIA_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_PINE_CHEST = ITEMS.register("shadow_pine_chest", () -> new ChestBlockItem(AerialHellBlocks.SHADOW_PINE_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_CHEST = ITEMS.register("stellar_jungle_tree_chest", () -> new ChestBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_CHEST = ITEMS.register("sky_cactus_fiber_chest", () -> new ChestBlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_CHEST = ITEMS.register("gray_shroom_chest", () -> new ChestBlockItem(AerialHellBlocks.GRAY_SHROOM_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> MUD_CHEST = ITEMS.register("mud_chest", () -> new ChestBlockItem(AerialHellBlocks.MUD_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> LUNATIC_CHEST = ITEMS.register("lunatic_chest", () -> new ChestBlockItem(AerialHellBlocks.LUNATIC_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_CHEST = ITEMS.register("volucite_chest", () -> new ChestBlockItem(AerialHellBlocks.VOLUCITE_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_CATACOMBS_CHEST = ITEMS.register("shadow_catacombs_chest", () -> new ChestBlockItem(AerialHellBlocks.SHADOW_CATACOMBS_CHEST.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_NETHER_CHEST = ITEMS.register("golden_nether_chest", () -> new ChestBlockItem(AerialHellBlocks.GOLDEN_NETHER_CHEST.get(), new Item.Properties()));

    //chest mimics
    public static final DeferredItem<Item> AERIAL_TREE_CHEST_MIMIC = ITEMS.register("aerial_tree_chest_mimic", () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_BEECH_CHEST_MIMIC = ITEMS.register("golden_beech_chest_mimic", () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC.get(), new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PINE_CHEST_MIMIC = ITEMS.register("copper_pine_chest_mimic", () -> new BlockItem(AerialHellBlocks.COPPER_PINE_CHEST_MIMIC.get(), new Item.Properties()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_CHEST_MIMIC = ITEMS.register("sky_cactus_fiber_chest_mimic", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC.get(), new Item.Properties()));

    //barrel mimics
    public static final DeferredItem<Item> SHADOW_PINE_BARREL_MIMIC = ITEMS.register("shadow_pine_barrel_mimic", () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_BARREL_MIMIC.get(), new Item.Properties()));

    //fences, bars or walls
    public static final DeferredItem<Item> AERIAL_TREE_FENCE = ITEMS.register("aerial_tree_fence", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_FENCE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_FENCE = ITEMS.register("golden_beech_fence", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_FENCE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> COPPER_PINE_FENCE = ITEMS.register("copper_pine_fence", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_FENCE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_FENCE = ITEMS.register("lapis_robinia_fence", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_FENCE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_FENCE = ITEMS.register("shadow_pine_fence", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_FENCE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_FENCE = ITEMS.register("stellar_jungle_tree_fence", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_FENCE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_FENCE = ITEMS.register("sky_cactus_fiber_fence", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_FENCE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_FENCE = ITEMS.register("gray_shroom_fence", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_FENCE.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> RUBY_BARS = ITEMS.register("ruby_bars", () -> new BlockItem(AerialHellBlocks.RUBY_BARS.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_STONE_WALL = ITEMS.register("stellar_stone_wall", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_WALL = ITEMS.register("stellar_cobblestone_wall", () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_WALL = ITEMS.register("stellar_stone_bricks_wall", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_STELLAR_STONE_WALL = ITEMS.register("mossy_stellar_stone_wall", () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_STELLAR_COBBLESTONE_WALL = ITEMS.register("mossy_stellar_cobblestone_wall", () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_WALL = ITEMS.register("slippery_sand_stone_wall", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_WALL = ITEMS.register("slippery_sand_stone_bricks_wall", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = ITEMS.register("cracked_slippery_sand_stone_bricks_wall", () -> new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> GLAUCOPHANITE_WALL = ITEMS.register("glaucophanite_wall", () -> new BlockItem(AerialHellBlocks.GLAUCOPHANITE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> POLISHED_GLAUCOPHANITE_WALL = ITEMS.register("polished_glaucophanite_wall", () -> new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_WALL.get(), new Item.Properties()));
    public static final DeferredItem<Item> MAGMATIC_GEL_WALL = ITEMS.register("magmatic_gel_wall", () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_WALL.get(), new Item.Properties()));

    //gates
    public static final DeferredItem<Item> AERIAL_TREE_GATE = ITEMS.register("aerial_tree_gate", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_GATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_GATE = ITEMS.register("golden_beech_gate", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_GATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> COPPER_PINE_GATE = ITEMS.register("copper_pine_gate", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_GATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_GATE = ITEMS.register("lapis_robinia_gate", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_GATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_GATE = ITEMS.register("shadow_pine_gate", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_GATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_GATE = ITEMS.register("stellar_jungle_tree_gate", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_GATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_GATE = ITEMS.register("sky_cactus_fiber_gate", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_GATE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_GATE = ITEMS.register("gray_shroom_gate", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_GATE.get(), new Item.Properties(), 100));

    //doors
    public static final DeferredItem<Item> AERIAL_TREE_DOOR = ITEMS.register("aerial_tree_door", () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_DOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_BEECH_DOOR = ITEMS.register("golden_beech_door", () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_DOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PINE_DOOR = ITEMS.register("copper_pine_door", () -> new BlockItem(AerialHellBlocks.COPPER_PINE_DOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_DOOR = ITEMS.register("lapis_robinia_door", () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_DOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_PINE_DOOR = ITEMS.register("shadow_pine_door", () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_DOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_DOOR = ITEMS.register("stellar_jungle_tree_door", () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_DOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_DOOR = ITEMS.register("sky_cactus_fiber_door", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_DOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_DOOR = ITEMS.register("gray_shroom_door", () -> new BlockItem(AerialHellBlocks.GRAY_SHROOM_DOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> RUBY_DOOR = ITEMS.register("ruby_door", () -> new BlockItem(AerialHellBlocks.RUBY_DOOR.get(), new Item.Properties()));

    //trapdoors
    public static final DeferredItem<Item> AERIAL_TREE_TRAPDOOR = ITEMS.register("aerial_tree_trapdoor", () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_TRAPDOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_BEECH_TRAPDOOR = ITEMS.register("golden_beech_trapdoor", () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_TRAPDOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PINE_TRAPDOOR = ITEMS.register("copper_pine_trapdoor", () -> new BlockItem(AerialHellBlocks.COPPER_PINE_TRAPDOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_TRAPDOOR = ITEMS.register("lapis_robinia_trapdoor", () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_TRAPDOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_PINE_TRAPDOOR = ITEMS.register("shadow_pine_trapdoor", () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_TRAPDOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_TRAPDOOR = ITEMS.register("stellar_jungle_tree_trapdoor", () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_TRAPDOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_TRAPDOOR = ITEMS.register("sky_cactus_fiber_trapdoor", () -> new BlockItem(AerialHellBlocks. SKY_CACTUS_FIBER_TRAPDOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_TRAPDOOR = ITEMS.register("gray_shroom_trapdoor", () -> new BlockItem(AerialHellBlocks.GRAY_SHROOM_TRAPDOOR.get(), new Item.Properties()));
    public static final DeferredItem<Item> RUBY_TRAPDOOR = ITEMS.register("ruby_trapdoor", () -> new BlockItem(AerialHellBlocks.RUBY_TRAPDOOR.get(), new Item.Properties()));

    //buttons
    public static final DeferredItem<Item> STELLAR_STONE_BUTTON = ITEMS.register("stellar_stone_button", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BUTTON.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_BUTTON = ITEMS.register("stellar_cobblestone_button", () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_BUTTON.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_BUTTON = ITEMS.register("stellar_stone_bricks_button", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_BUTTON.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BUTTON = ITEMS.register("slippery_sand_stone_button", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BUTTON.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_BUTTON = ITEMS.register("slippery_sand_stone_bricks_button", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_BUTTON.get(), new Item.Properties()));
    public static final DeferredItem<Item> AERIAL_TREE_BUTTON = ITEMS.register("aerial_tree_button", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_BUTTON.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> GOLDEN_BEECH_BUTTON = ITEMS.register("golden_beech_button", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_BUTTON.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> COPPER_PINE_BUTTON = ITEMS.register("copper_pine_button", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_BUTTON.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> LAPIS_ROBINIA_BUTTON = ITEMS.register("lapis_robinia_button", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_BUTTON.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> SHADOW_PINE_BUTTON = ITEMS.register("shadow_pine_button", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_BUTTON.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_BUTTON = ITEMS.register("stellar_jungle_tree_button", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BUTTON.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_BUTTON = ITEMS.register("sky_cactus_fiber_button", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BUTTON.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_BUTTON = ITEMS.register("gray_shroom_button", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_BUTTON.get(), new Item.Properties(), 30));
    public static final DeferredItem<Item> GLAUCOPHANITE_BUTTON = ITEMS.register("glaucophanite_button", () -> new BlockItem(AerialHellBlocks.GLAUCOPHANITE_BUTTON.get(), new Item.Properties()));

    //pressure plates
    public static final DeferredItem<Item> STELLAR_STONE_PRESSURE_PLATE = ITEMS.register("stellar_stone_pressure_plate", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_PRESSURE_PLATE = ITEMS.register("stellar_cobblestone_pressure_plate", () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_PRESSURE_PLATE = ITEMS.register("stellar_stone_bricks_pressure_plate", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_PRESSURE_PLATE = ITEMS.register("slippery_sand_stone_pressure_plate", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = ITEMS.register("slippery_sand_stone_bricks_pressure_plate", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final DeferredItem<Item> AERIAL_TREE_PRESSURE_PLATE = ITEMS.register("aerial_tree_pressure_plate", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_PRESSURE_PLATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_PRESSURE_PLATE = ITEMS.register("golden_beech_pressure_plate", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_PRESSURE_PLATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> COPPER_PINE_PRESSURE_PLATE = ITEMS.register("copper_pine_pressure_plate", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_PRESSURE_PLATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_PRESSURE_PLATE = ITEMS.register("lapis_robinia_pressure_plate", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_PRESSURE_PLATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_PRESSURE_PLATE = ITEMS.register("shadow_pine_pressure_plate", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_PRESSURE_PLATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_PRESSURE_PLATE = ITEMS.register("stellar_jungle_tree_pressure_plate", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_PRESSURE_PLATE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_PRESSURE_PLATE = ITEMS.register("sky_cactus_fiber_pressure_plate", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_PRESSURE_PLATE = ITEMS.register("gray_shroom_pressure_plate", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_PRESSURE_PLATE.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> GLAUCOPHANITE_PRESSURE_PLATE = ITEMS.register("glaucophanite_pressure_plate", () -> new BlockItem(AerialHellBlocks.GLAUCOPHANITE_PRESSURE_PLATE.get(), new Item.Properties()));

    //slabs
    public static final DeferredItem<Item> AERIAL_TREE_SLAB = ITEMS.register("aerial_tree_slab", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_SLAB.get(), new Item.Properties(), 150));
    public static final DeferredItem<Item> GOLDEN_BEECH_SLAB = ITEMS.register("golden_beech_slab", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_SLAB.get(), new Item.Properties(), 150));
    public static final DeferredItem<Item> COPPER_PINE_SLAB = ITEMS.register("copper_pine_slab", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_SLAB.get(), new Item.Properties(), 150));
    public static final DeferredItem<Item> LAPIS_ROBINIA_SLAB = ITEMS.register("lapis_robinia_slab", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_SLAB.get(), new Item.Properties(), 150));
    public static final DeferredItem<Item> SHADOW_PINE_SLAB = ITEMS.register("shadow_pine_slab", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_SLAB.get(), new Item.Properties(), 150));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_SLAB = ITEMS.register("stellar_jungle_tree_slab", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_SLAB.get(), new Item.Properties(), 150));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_SLAB = ITEMS.register("sky_cactus_fiber_slab", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_SLAB = ITEMS.register("gray_shroom_slab", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_SLAB.get(), new Item.Properties(), 50));
    public static final DeferredItem<Item> STELLAR_STONE_SLAB = ITEMS.register("stellar_stone_slab", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_SLAB = ITEMS.register("stellar_cobblestone_slab", () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_SLAB = ITEMS.register("stellar_stone_bricks_slab", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_STELLAR_STONE_SLAB = ITEMS.register("mossy_stellar_stone_slab", () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_STELLAR_COBBLESTONE_SLAB = ITEMS.register("mossy_stellar_cobblestone_slab", () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_SLAB = ITEMS.register("slippery_sand_stone_slab", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_SLAB = ITEMS.register("slippery_sand_stone_bricks_slab", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = ITEMS.register("cracked_slippery_sand_stone_bricks_slab", () -> new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> POLISHED_GLAUCOPHANITE_SLAB = ITEMS.register("polished_glaucophanite_slab", () -> new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_SLAB.get(), new Item.Properties()));
    public static final DeferredItem<Item> MAGMATIC_GEL_SLAB = ITEMS.register("magmatic_gel_slab", () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_SLAB.get(), new Item.Properties()));

    //stairs
    public static final DeferredItem<Item> AERIAL_TREE_STAIRS = ITEMS.register("aerial_tree_stairs", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_STAIRS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_STAIRS = ITEMS.register("golden_beech_stairs", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_STAIRS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> COPPER_PINE_STAIRS = ITEMS.register("copper_pine_stairs", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_STAIRS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_STAIRS = ITEMS.register("lapis_robinia_stairs", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_STAIRS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_STAIRS = ITEMS.register("shadow_pine_stairs", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_STAIRS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_STAIRS = ITEMS.register("stellar_jungle_tree_stairs", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_STAIRS.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_STAIRS = ITEMS.register("sky_cactus_fiber_stairs", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_STAIRS = ITEMS.register("gray_shroom_stairs", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_STAIRS.get(), new Item.Properties(), 100));
    public static final DeferredItem<Item> STELLAR_STONE_STAIRS = ITEMS.register("stellar_stone_stairs", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_COBBLESTONE_STAIRS = ITEMS.register("stellar_cobblestone_stairs", () -> new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_STONE_BRICKS_STAIRS = ITEMS.register("stellar_stone_bricks_stairs", () -> new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_STELLAR_STONE_STAIRS = ITEMS.register("mossy_stellar_stone_stairs", () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> MOSSY_STELLAR_COBBLESTONE_STAIRS = ITEMS.register("mossy_stellar_cobblestone_stairs", () -> new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_STAIRS = ITEMS.register("slippery_sand_stone_stairs", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SLIPPERY_SAND_STONE_BRICKS_STAIRS = ITEMS.register("slippery_sand_stone_bricks_stairs", () -> new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = ITEMS.register("cracked_slippery_sand_stone_bricks_stairs", () -> new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> POLISHED_GLAUCOPHANITE_STAIRS = ITEMS.register("polished_glaucophanite_stairs", () -> new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_STAIRS.get(), new Item.Properties()));
    public static final DeferredItem<Item> MAGMATIC_GEL_STAIRS = ITEMS.register("magmatic_gel_stairs", () -> new BlockItem(AerialHellBlocks.MAGMATIC_GEL_STAIRS.get(), new Item.Properties()));

    //signs
    public static final DeferredItem<Item> AERIAL_TREE_SIGN = ITEMS.register("aerial_tree_sign", () -> new SignItem(AerialHellBlocks.AERIAL_TREE_WALL_SIGN.get(), AerialHellBlocks.AERIAL_TREE_STANDING_SIGN.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_BEECH_SIGN = ITEMS.register("golden_beech_sign", () -> new SignItem(AerialHellBlocks.GOLDEN_BEECH_WALL_SIGN.get(), AerialHellBlocks.GOLDEN_BEECH_STANDING_SIGN.get(), new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PINE_SIGN = ITEMS.register("copper_pine_sign", () -> new SignItem(AerialHellBlocks.COPPER_PINE_WALL_SIGN.get(), AerialHellBlocks.COPPER_PINE_STANDING_SIGN.get(), new Item.Properties()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_SIGN = ITEMS.register("lapis_robinia_sign", () -> new SignItem(AerialHellBlocks.LAPIS_ROBINIA_WALL_SIGN.get(), AerialHellBlocks.LAPIS_ROBINIA_STANDING_SIGN.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_PINE_SIGN = ITEMS.register("shadow_pine_sign", () -> new SignItem(AerialHellBlocks.SHADOW_PINE_WALL_SIGN.get(), AerialHellBlocks.SHADOW_PINE_STANDING_SIGN.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_SIGN = ITEMS.register("stellar_jungle_tree_sign", () -> new SignItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_SIGN.get(), AerialHellBlocks.STELLAR_JUNGLE_TREE_STANDING_SIGN.get(), new Item.Properties()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_SIGN = ITEMS.register("sky_cactus_fiber_sign", () -> new SignItem(AerialHellBlocks.SKY_CACTUS_FIBER_WALL_SIGN.get(), AerialHellBlocks.SKY_CACTUS_FIBER_STANDING_SIGN.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_SIGN = ITEMS.register("gray_shroom_sign", () -> new SignItem(AerialHellBlocks.GRAY_SHROOM_WALL_SIGN.get(), AerialHellBlocks.GRAY_SHROOM_STANDING_SIGN.get(), new Item.Properties()));

    //hanging signs
    public static final DeferredItem<Item> AERIAL_TREE_HANGING_SIGN = ITEMS.register("aerial_tree_hanging_sign", () -> new HangingSignItem(AerialHellBlocks.AERIAL_TREE_HANGING_SIGN.get(), AerialHellBlocks.AERIAL_TREE_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final DeferredItem<Item> GOLDEN_BEECH_HANGING_SIGN = ITEMS.register("golden_beech_hanging_sign", () -> new HangingSignItem(AerialHellBlocks.GOLDEN_BEECH_HANGING_SIGN.get(), AerialHellBlocks.GOLDEN_BEECH_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final DeferredItem<Item> COPPER_PINE_HANGING_SIGN = ITEMS.register("copper_pine_hanging_sign", () -> new HangingSignItem(AerialHellBlocks.COPPER_PINE_HANGING_SIGN.get(), AerialHellBlocks.COPPER_PINE_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final DeferredItem<Item> LAPIS_ROBINIA_HANGING_SIGN = ITEMS.register("lapis_robinia_hanging_sign", () -> new HangingSignItem(AerialHellBlocks.LAPIS_ROBINIA_HANGING_SIGN.get(), AerialHellBlocks.LAPIS_ROBINIA_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final DeferredItem<Item> SHADOW_PINE_HANGING_SIGN = ITEMS.register("shadow_pine_hanging_sign", () -> new HangingSignItem(AerialHellBlocks.SHADOW_PINE_HANGING_SIGN.get(), AerialHellBlocks.SHADOW_PINE_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_HANGING_SIGN = ITEMS.register("stellar_jungle_tree_hanging_sign", () -> new HangingSignItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_HANGING_SIGN.get(), AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_HANGING_SIGN = ITEMS.register("sky_cactus_fiber_hanging_sign", () -> new HangingSignItem(AerialHellBlocks.SKY_CACTUS_FIBER_HANGING_SIGN.get(), AerialHellBlocks.SKY_CACTUS_FIBER_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final DeferredItem<Item> GRAY_SHROOM_HANGING_SIGN = ITEMS.register("gray_shroom_hanging_sign", () -> new HangingSignItem(AerialHellBlocks.GRAY_SHROOM_HANGING_SIGN.get(), AerialHellBlocks.GRAY_SHROOM_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));

    //crafting tables
    public static final DeferredItem<Item> AERIAL_TREE_CRAFTING_TABLE = ITEMS.register("aerial_tree_crafting_table", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_CRAFTING_TABLE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_CRAFTING_TABLE = ITEMS.register("golden_beech_crafting_table", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_CRAFTING_TABLE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> COPPER_PINE_CRAFTING_TABLE = ITEMS.register("copper_pine_crafting_table", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_CRAFTING_TABLE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_CRAFTING_TABLE = ITEMS.register("lapis_robinia_crafting_table", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_CRAFTING_TABLE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_CRAFTING_TABLE = ITEMS.register("shadow_pine_crafting_table", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_CRAFTING_TABLE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_CRAFTING_TABLE = ITEMS.register("stellar_jungle_tree_crafting_table", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_CRAFTING_TABLE.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_CRAFTING_TABLE = ITEMS.register("sky_cactus_fiber_crafting_table", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CRAFTING_TABLE.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_CRAFTING_TABLE = ITEMS.register("gray_shroom_crafting_table", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_CRAFTING_TABLE.get(), new Item.Properties(), 100));

    //barrels
    public static final DeferredItem<Item> AERIAL_TREE_BARREL = ITEMS.register("aerial_tree_barrel", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_BARREL.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_BARREL = ITEMS.register("golden_beech_barrel", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_BARREL.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> COPPER_PINE_BARREL = ITEMS.register("copper_pine_barrel", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_BARREL.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_BARREL = ITEMS.register("lapis_robinia_barrel", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_BARREL.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_BARREL = ITEMS.register("shadow_pine_barrel", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_BARREL.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_BARREL = ITEMS.register("stellar_jungle_tree_barrel", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BARREL.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_BARREL = ITEMS.register("sky_cactus_fiber_barrel", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BARREL.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_BARREL = ITEMS.register("gray_shroom_barrel", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_BARREL.get(), new Item.Properties(), 100));

    //composters
    public static final DeferredItem<Item> AERIAL_TREE_COMPOSTER = ITEMS.register("aerial_tree_composter", () -> new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_COMPOSTER.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> GOLDEN_BEECH_COMPOSTER = ITEMS.register("golden_beech_composter", () -> new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_COMPOSTER.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> COPPER_PINE_COMPOSTER = ITEMS.register("copper_pine_composter", () -> new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_COMPOSTER.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> LAPIS_ROBINIA_COMPOSTER = ITEMS.register("lapis_robinia_composter", () -> new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_COMPOSTER.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SHADOW_PINE_COMPOSTER = ITEMS.register("shadow_pine_composter", () -> new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_COMPOSTER.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_COMPOSTER = ITEMS.register("stellar_jungle_tree_composter", () -> new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_COMPOSTER.get(), new Item.Properties(), 300));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_COMPOSTER = ITEMS.register("sky_cactus_fiber_composter", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_COMPOSTER.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_COMPOSTER = ITEMS.register("gray_shroom_composter", () -> new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_COMPOSTER.get(), new Item.Properties(), 100));

    //decorative
    public static final DeferredItem<Item> AERIAL_TREE_VINE_ROPE_SPOOL = ITEMS.register("aerial_tree_vine_rope_spool", () -> new BlockItem(AerialHellBlocks.AERIAL_TREE_VINE_ROPE_SPOOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> GOLDEN_BEECH_VINE_ROPE_SPOOL = ITEMS.register("golden_beech_vine_rope_spool", () -> new BlockItem(AerialHellBlocks.GOLDEN_BEECH_VINE_ROPE_SPOOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PINE_VINE_ROPE_SPOOL = ITEMS.register("copper_pine_vine_rope_spool", () -> new BlockItem(AerialHellBlocks.COPPER_PINE_VINE_ROPE_SPOOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> LAPIS_ROBINIA_VINE_ROPE_SPOOL = ITEMS.register("lapis_robinia_vine_rope_spool", () -> new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_VINE_ROPE_SPOOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> SHADOW_PINE_VINE_ROPE_SPOOL = ITEMS.register("shadow_pine_vine_rope_spool", () -> new BlockItem(AerialHellBlocks.SHADOW_PINE_VINE_ROPE_SPOOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = ITEMS.register("stellar_jungle_tree_vine_rope_spool", () -> new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = ITEMS.register("sky_cactus_fiber_vine_rope_spool", () -> new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL.get(), new Item.Properties()));
    public static final DeferredItem<Item> GRAY_SHROOM_VINE_ROPE_SPOOL = ITEMS.register("gray_shroom_vine_rope_spool", () -> new BlockItem(AerialHellBlocks.GRAY_SHROOM_VINE_ROPE_SPOOL.get(), new Item.Properties()));

    //item for crafts
    public static final DeferredItem<Item> SKY_STICK = ITEMS.register("sky_stick",() -> new BurnableItem(new Item.Properties(), 100));
    public static final DeferredItem<Item> SKY_BOWL = ITEMS.register("sky_bowl",() -> new BurnableItem(new Item.Properties(), 200));
    public static final DeferredItem<Item> SHADOW_SHARD = ITEMS.register("shadow_shard",() -> new Item(new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<Item> ROTTEN_LEATHER = ITEMS.register("rotten_leather",() -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VENOMOUS_SNAKE_SKIN = ITEMS.register("venomous_snake_skin",() -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ARSONIST_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("arsonist_upgrade_smithing_template", () -> ItemHelper.SmithingTemplate.createUpgradeTemplate("arsonist"));

    //projectile item
    public static final DeferredItem<Item> STELLAR_EGG = ITEMS.register("stellar_egg",() -> new StellarEggItem(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> DIMENSION_SHATTERER_PROJECTILE = ITEMS.register("dimension_shatterer_projectile",() -> new DimensionShattererProjectileItem(new Item.Properties().stacksTo(16)));

    //shurikens
    public static final DeferredItem<Item> IRON_SHURIKEN = ITEMS.register("iron_shuriken", () -> new IronShurikenItem());
    public static final DeferredItem<Item> GOLD_SHURIKEN = ITEMS.register("gold_shuriken", () -> new GoldShurikenItem());
    public static final DeferredItem<Item> DIAMOND_SHURIKEN = ITEMS.register("diamond_shuriken", () -> new DiamondShurikenItem());
    public static final DeferredItem<Item> NETHERITE_SHURIKEN = ITEMS.register("netherite_shuriken", () -> new NetheriteShurikenItem());
    public static final DeferredItem<Item> RUBY_SHURIKEN = ITEMS.register("ruby_shuriken", () -> new RubyShurikenItem());
    public static final DeferredItem<Item> AZURITE_SHURIKEN = ITEMS.register("azurite_shuriken", () -> new AzuriteShurikenItem());
    public static final DeferredItem<Item> MAGMATIC_GEL_SHURIKEN = ITEMS.register("magmatic_gel_shuriken", () -> new MagmaticGelShurikenItem());
    public static final DeferredItem<Item> VOLUCITE_SHURIKEN = ITEMS.register("volucite_shuriken", () -> new VoluciteShurikenItem());
    public static final DeferredItem<Item> OBSIDIAN_SHURIKEN = ITEMS.register("obsidian_shuriken", () -> new ObsidianShurikenItem());
    public static final DeferredItem<Item> LUNATIC_CRYSTAL_SHURIKEN = ITEMS.register("lunatic_crystal_shuriken", () -> new LunaticCrystalShurikenItem());
    public static final DeferredItem<Item> ARSONIST_SHURIKEN = ITEMS.register("arsonist_shuriken", () -> new ArsonistShurikenItem());
    public static final DeferredItem<Item> LIGHTNING_SHURIKEN = ITEMS.register("lightning_shuriken", () -> new LightningShurikenItem());

    //food
    public static final DeferredItem<Item> AERIAL_BERRY = ITEMS.register("aerial_berry",() -> new Item(new Item.Properties().food(AerialHellFoods.Properties.AERIAL_BERRY)));
    public static final DeferredItem<Item> ROASTED_AERIAL_BERRY = ITEMS.register("roasted_aerial_berry",() -> new Item(new Item.Properties().food(AerialHellFoods.Properties.ROASTED_AERIAL_BERRY)));
    public static final DeferredItem<Item> VIBRANT_AERIAL_BERRY = ITEMS.register("vibrant_aerial_berry",() -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_AERIAL_BERRY)));
    public static final DeferredItem<Item> FROZEN_AERIAL_BERRY = ITEMS.register("frozen_aerial_berry", () -> new Item(new Item.Properties().rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_AERIAL_BERRY, AerialHellFoods.Consumables.FROZEN_AERIAL_BERRY)));
    public static final DeferredItem<Item> STELLAR_BREAD = ITEMS.register("stellar_bread",() -> new Item(new Item.Properties().food(AerialHellFoods.Properties.STELLAR_BREAD)));
    public static final DeferredItem<Item> FROZEN_MUTTON = ITEMS.register("frozen_mutton", () -> new Item(new Item.Properties().rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_MUTTON, AerialHellFoods.Consumables.FROZEN_MUTTON)));
    public static final DeferredItem<Item> VIBRANT_CHICKEN = ITEMS.register("vibrant_chicken", () -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_CHICKEN, AerialHellFoods.Consumables.VIBRANT_CHICKEN)));
    public static final DeferredItem<Item> FROZEN_CHICKEN = ITEMS.register("frozen_chicken", () -> new Item(new Item.Properties().rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_CHICKEN, AerialHellFoods.Consumables.FROZEN_CHICKEN)));
    public static final DeferredItem<Item> RUBY_AERIAL_BERRY = ITEMS.register("ruby_aerial_berry", () -> new Item(new Item.Properties().rarity(Rarity.RARE).food(AerialHellFoods.Properties.RUBY_AERIAL_BERRY, AerialHellFoods.Consumables.RUBY_AERIAL_BERRY)));
    public static final DeferredItem<Item> VOLUCITE_AERIAL_BERRY = ITEMS.register("volucite_aerial_berry", () -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VOLUCITE_AERIAL_BERRY, AerialHellFoods.Consumables.VOLUCITE_AERIAL_BERRY)));
    public static final DeferredItem<Item> GLOWING_STICK_FRUIT = ITEMS.register("glowing_stick_fruit",() -> new BlockItem(AerialHellBlocks.GLOWING_STICK_FRUIT_VINES.get(), new Item.Properties().useItemDescriptionPrefix().food(AerialHellFoods.Properties.GLOWING_STICK_FRUIT)));
    public static final DeferredItem<Item> VIBRANT_GLOWING_STICK_FRUIT = ITEMS.register("vibrant_glowing_stick_fruit", () -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_GLOWING_STICK_FRUIT)));
    public static final DeferredItem<Item> FROZEN_GLOWING_STICK_FRUIT = ITEMS.register("frozen_glowing_stick_fruit", () -> new Item(new Item.Properties().rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_GLOWING_STICK_FRUIT, AerialHellFoods.Consumables.FROZEN_GLOWING_STICK_FRUIT)));
    public static final DeferredItem<Item> CORTINARIUS_VIOLACEUS_PIECE = ITEMS.register("cortinarius_violaceus_piece", () -> new Item(new Item.Properties().rarity(Rarity.COMMON).food(AerialHellFoods.Properties.CORTINARIUS_VIOLACEUS_PIECE, AerialHellFoods.Consumables.CORTINARIUS_VIOLACEUS_PIECE)));
    public static final DeferredItem<Item> GANODERMA_APPLANATUM_PIECE = ITEMS.register("ganoderma_applanatum_piece", () -> new Item(new Item.Properties().rarity(Rarity.COMMON).food(AerialHellFoods.Properties.GANODERMA_APPLANATUM_PIECE, AerialHellFoods.Consumables.GANODERMA_APPLANATUM_PIECE)));
    public static final DeferredItem<Item> DARK_SHADOW_FRUIT = ITEMS.register("dark_shadow_fruit", () -> new Item(new Item.Properties().rarity(Rarity.COMMON).food(AerialHellFoods.Properties.DARK_SHADOW_FRUIT, AerialHellFoods.Consumables.DARK_SHADOW_FRUIT)));
    public static final DeferredItem<Item> PURPLE_SHADOW_FRUIT = ITEMS.register("purple_shadow_fruit", () -> new Item(new Item.Properties().rarity(Rarity.COMMON).food(AerialHellFoods.Properties.PURPLE_SHADOW_FRUIT, AerialHellFoods.Consumables.PURPLE_SHADOW_FRUIT)));
    public static final DeferredItem<Item> SHADOW_FRUIT_STEW = ITEMS.register("shadow_fruit_stew", () -> new SkySoupItem(new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue()).food(AerialHellFoods.Properties.SHADOW_FRUIT_STEW, AerialHellFoods.Consumables.SHADOW_FRUIT_STEW)));
    public static final DeferredItem<Item> SOLID_ETHER_SOUP = ITEMS.register("solid_ether_soup", () -> new SkySoupItem(new Item.Properties().rarity(Rarity.COMMON).food(AerialHellFoods.Properties.SOLID_ETHER_SOUP, AerialHellFoods.Consumables.SOLID_ETHER_SOUP)));
    public static final DeferredItem<Item> VIBRANT_SOLID_ETHER_SOUP = ITEMS.register("vibrant_solid_ether_soup", () -> new SkySoupItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_SOLID_ETHER_SOUP, AerialHellFoods.Consumables.VIBRANT_SOLID_ETHER_SOUP)));
    public static final DeferredItem<Item> FROZEN_SOLID_ETHER_SOUP = ITEMS.register("frozen_solid_ether_soup", () -> new SkySoupItem(new Item.Properties().rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_SOLID_ETHER_SOUP, AerialHellFoods.Consumables.FROZEN_SOLID_ETHER_SOUP)));
    public static final DeferredItem<Item> SHADOW_SPIDER_EYE = ITEMS.register("shadow_spider_eye", () -> new Item(new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue()).food(AerialHellFoods.Properties.SHADOW_SPIDER_EYE, AerialHellFoods.Consumables.SHADOW_SPIDER_EYE)));
    public static final DeferredItem<Item> PHANTOM_MEAT = ITEMS.register("phantom_meat", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.PHANTOM_MEAT, AerialHellFoods.Consumables.PHANTOM_MEAT)));
    public static final DeferredItem<Item> VIBRANT_PHANTOM_MEAT = ITEMS.register("vibrant_phantom_meat", () -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_PHANTOM_MEAT, AerialHellFoods.Consumables.VIBRANT_PHANTOM_MEAT)));
    public static final DeferredItem<Item> FROZEN_PHANTOM_MEAT = ITEMS.register("frozen_phantom_meat", () -> new Item(new Item.Properties().rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_PHANTOM_MEAT, AerialHellFoods.Consumables.FROZEN_PHANTOM_MEAT)));
    public static final DeferredItem<Item> COOKED_PHANTOM_MEAT = ITEMS.register("cooked_phantom_meat",() -> new Item(new Item.Properties().food(AerialHellFoods.Properties.COOKED_PHANTOM_MEAT)));
    public static final DeferredItem<Item> TURTLE_MEAT = ITEMS.register("turtle_meat", () -> new Item(new Item.Properties().food(Foods.BEEF)));
    public static final DeferredItem<Item> VIBRANT_TURTLE_MEAT = ITEMS.register("vibrant_turtle_meat", () -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_TURTLE_MEAT, AerialHellFoods.Consumables.VIBRANT_TURTLE_MEAT)));
    public static final DeferredItem<Item> FROZEN_TURTLE_MEAT = ITEMS.register("frozen_turtle_meat", () -> new Item(new Item.Properties().rarity(AerialHellRarities.FROZEN.getValue()).food(AerialHellFoods.Properties.FROZEN_TURTLE_MEAT, AerialHellFoods.Consumables.FROZEN_TURTLE_MEAT)));
    public static final DeferredItem<Item> COOKED_TURTLE_MEAT = ITEMS.register("cooked_turtle_meat",() -> new Item(new Item.Properties().food(Foods.COOKED_BEEF)));
    public static final DeferredItem<Item> GODS_VOLUCITE_AERIAL_BERRY = ITEMS.register("gods_volucite_aerial_berry", () -> new FoilItem(new Item.Properties().rarity(AerialHellRarities.MYTHICAL.getValue()).food(AerialHellFoods.Properties.GODS_VOLUCITE_AERIAL_BERRY, AerialHellFoods.Consumables.GODS_VOLUCITE_AERIAL_BERRY)));
    public static final DeferredItem<Item> COPPER_PINE_CONE = ITEMS.register("copper_pine_cone",() -> new Item(new Item.Properties().food(AerialHellFoods.Properties.COPPER_PINE_CONE)));
    public static final DeferredItem<Item> AZURITE_COPPER_PINE_CONE = ITEMS.register("azurite_copper_pine_cone", () -> new Item(new Item.Properties().rarity(Rarity.COMMON).food(AerialHellFoods.Properties.AZURITE_COPPER_PINE_CONE, AerialHellFoods.Consumables.AZURITE_COPPER_PINE_CONE)));
    public static final DeferredItem<Item> PHOENIX_FEATHER = ITEMS.register("phoenix_feather", () -> new Item(new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue()).food(AerialHellFoods.Properties.PHOENIX_FEATHER, AerialHellFoods.Consumables.PHOENIX_FEATHER)));
    public static final DeferredItem<Item> SKY_CACTUS_FIBER = ITEMS.register("sky_cactus_fiber",() -> new Item(new Item.Properties().food(AerialHellFoods.Properties.SKY_CACTUS_FIBER, AerialHellFoods.Consumables.SKY_CACTUS_FIBER)));
    public static final DeferredItem<Item> VIBRANT_SKY_CACTUS_FIBER = ITEMS.register("vibrant_sky_cactus_fiber",() -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_SKY_CACTUS_FIBER, AerialHellFoods.Consumables.VIBRANT_SKY_CACTUS_FIBER)));
    public static final DeferredItem<Item> WHITE_SOLID_ETHER_FRAGMENT = ITEMS.register("white_solid_ether_fragment",() -> new Item(new Item.Properties().food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> BLUE_SOLID_ETHER_FRAGMENT = ITEMS.register("blue_solid_ether_fragment", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.BLUE_SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> GOLDEN_SOLID_ETHER_FRAGMENT = ITEMS.register("golden_solid_ether_fragment", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.GOLDEN_SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> GREEN_SOLID_ETHER_FRAGMENT = ITEMS.register("green_solid_ether_fragment", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.GREEN_SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> PURPLE_SOLID_ETHER_FRAGMENT = ITEMS.register("purple_solid_ether_fragment",() -> new Item(new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue()).food(AerialHellFoods.Properties.SOLID_ETHER_FRAGMENT, AerialHellFoods.Consumables.PURPLE_SOLID_ETHER_FRAGMENT)));
    public static final DeferredItem<Item> GOLDEN_NETHER_MEAT_PIECE = ITEMS.register("golden_nether_meat_piece", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.GOLDEN_NETHER_MEAT_PIECE, AerialHellFoods.Consumables.GOLDEN_NETHER_MEAT_PIECE)));
    public static final DeferredItem<Item> GOLDEN_NETHER_STEAK = ITEMS.register("golden_nether_steak", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).food(AerialHellFoods.Properties.GOLDEN_NETHER_STEAK, AerialHellFoods.Consumables.GOLDEN_NETHER_STEAK)));
    public static final DeferredItem<Item> VIBRANT_GOLDEN_NETHER_STEAK = ITEMS.register("vibrant_golden_nether_steak", () -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).food(AerialHellFoods.Properties.VIBRANT_GOLDEN_NETHER_STEAK, AerialHellFoods.Consumables.VIBRANT_GOLDEN_NETHER_STEAK)));

    //buckets
    public static final DeferredItem<Item> IRON_LIQUID_OF_GODS_BUCKET = ITEMS.register("iron_liquid_of_gods_bucket", () -> new BucketItem(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get(), (new Item.Properties()).stacksTo(1)));
    public static final DeferredItem<Item> RUBY_LIQUID_OF_GODS_BUCKET = ITEMS.register("ruby_liquid_of_gods_bucket", () -> new RubyLiquidOfGodsBucketItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> RUBY_BUCKET = ITEMS.register("ruby_bucket", () -> new RubyBucketItem(new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> RUBY_WATER_BUCKET = ITEMS.register("ruby_water_bucket", () -> new RubyWaterBucketItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> RUBY_MILK_BUCKET = ITEMS.register("ruby_milk_bucket", () -> new RubyMilkBucketItem(new Item.Properties().stacksTo(1)));

    //arrows & bows
    public static final DeferredItem<Item> RUBY_BLOWPIPE_ARROW = ITEMS.register("ruby_blowpipe_arrow", () -> new AerialArrowItem(new Item.Properties()));
    public static final DeferredItem<Item> VOLUCITE_BLOWPIPE_ARROW = ITEMS.register("volucite_blowpipe_arrow", () -> new AerialArrowItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));

    public static final DeferredItem<Item> RUBY_BLOWPIPE = ITEMS.register("ruby_blowpipe", () -> new BlowpipeItem(new Item.Properties().stacksTo(1).durability(200), 1.7F));
    public static final DeferredItem<Item> VOLUCITE_BLOWPIPE = ITEMS.register("volucite_blowpipe", () -> new BlowpipeItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()).stacksTo(1).durability(400), 2.4F));

    //music discs
    public static final DeferredItem<Item> MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP = ITEMS.register("music_disc_aerial_hell_theme_tommaup", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.AERIAL_HELL_THEME_TOMMAUP)));
    public static final DeferredItem<Item> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = ITEMS.register("music_disc_sweden_andreas_zoeller", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.SWEDEN_ANDREAS_ZOELLER)));
    public static final DeferredItem<Item> MUSIC_DISC_ENTHUSIAST_TOURS = ITEMS.register("music_disc_enthusiast_tours", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.ENTHUSIAST_TOURS)));
    public static final DeferredItem<Item> MUSIC_DISC_BMINOR_ARULO = ITEMS.register("music_disc_bminor_arulo", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.BMINOR_ARULO)));
    public static final DeferredItem<Item> MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP = ITEMS.register("music_disc_retro_exploration_tommaup", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.RETRO_EXPLORATION_TOMMAUP)));

    //tools
    public static final DeferredItem<PickaxeItem> SKY_WOOD_PICKAXE = ITEMS.register("sky_wood_pickaxe", () -> new PickaxeItem(ToolMaterials.SKY_WOOD, 1.0F, -2.8F, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> STELLAR_STONE_PICKAXE = ITEMS.register("stellar_stone_pickaxe", () -> new PickaxeItem(ToolMaterials.STELLAR_STONE, 1.0F, -2.8F, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new PickaxeItem(ToolMaterials.RUBY, 1.0F, -2.8F, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> AZURITE_PICKAXE = ITEMS.register("azurite_pickaxe", () -> new PickaxeItem(ToolMaterials.AZURITE, 1.0F, -2.8F, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> MAGMATIC_GEL_PICKAXE = ITEMS.register("magmatic_gel_pickaxe", () -> new PickaxeItem(ToolMaterials.MAGMATIC_GEL, 1.0F, -2.8F, new Item.Properties()));
    public static final DeferredItem<PickaxeItem> OBSIDIAN_PICKAXE = ITEMS.register("obsidian_pickaxe", () -> new PickaxeItem(ToolMaterials.OBSIDIAN, 1.0F, -2.8F, new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<PickaxeItem> VOLUCITE_PICKAXE = ITEMS.register("volucite_pickaxe", () -> new EffectPickaxeItem(ToolMaterials.VOLUCITE, 1.0F, -2.8F, new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<PickaxeItem> LUNATIC_PICKAXE = ITEMS.register("lunatic_pickaxe", () -> new PickaxeItem(ToolMaterials.LUNATIC, 1.0F, -2.8F, new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<PickaxeItem> ARSONIST_PICKAXE = ITEMS.register("arsonist_pickaxe", () -> new PickaxeItem(ToolMaterials.ARSONIST, 1.0F, -2.8F, new Item.Properties().fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<PickaxeItem> MAGMA_CUBE_PICKAXE = ITEMS.register("magma_cube_pickaxe", () -> new EffectPickaxeItem(ToolMaterials.OBSIDIAN, 1.0F, -2.8F, new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<PickaxeItem> STELLAR_STONE_BREAKER = ITEMS.register("stellar_stone_breaker", () -> new AerialHellPickaxeItem(ToolMaterials.BREAKER, 1.0F, -2.8F, new Item.Properties().rarity(Rarity.EPIC)));

    public static final DeferredItem<ShovelItem> SKY_WOOD_SHOVEL = ITEMS.register("sky_wood_shovel", () -> new ShovelItem(ToolMaterials.SKY_WOOD, 1.5F, -3.0F, new Item.Properties()));
    public static final DeferredItem<ShovelItem> STELLAR_STONE_SHOVEL = ITEMS.register("stellar_stone_shovel", () -> new ShovelItem(ToolMaterials.STELLAR_STONE, 1.5F, -3.0F, new Item.Properties()));
    public static final DeferredItem<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new ShovelItem(ToolMaterials.RUBY, 1.5F, -3.0F, new Item.Properties()));
    public static final DeferredItem<ShovelItem> AZURITE_SHOVEL = ITEMS.register("azurite_shovel", () -> new ShovelItem(ToolMaterials.AZURITE, 1.5F, -3.0F, new Item.Properties()));
    public static final DeferredItem<ShovelItem> MAGMATIC_GEL_SHOVEL = ITEMS.register("magmatic_gel_shovel", () -> new ShovelItem(ToolMaterials.MAGMATIC_GEL, 1.5F, -3.0F, new Item.Properties()));
    public static final DeferredItem<ShovelItem> OBSIDIAN_SHOVEL = ITEMS.register("obsidian_shovel", () -> new ShovelItem(ToolMaterials.OBSIDIAN, 1.5F, -3.0F, new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<ShovelItem> VOLUCITE_SHOVEL = ITEMS.register("volucite_shovel", () -> new EffectShovelItem(ToolMaterials.VOLUCITE, 1.5F, -3.0F, new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<ShovelItem> LUNATIC_SHOVEL = ITEMS.register("lunatic_shovel", () -> new ShovelItem(ToolMaterials.LUNATIC, 1.5F, -3.0F, new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<ShovelItem> ARSONIST_SHOVEL = ITEMS.register("arsonist_shovel", () -> new ShovelItem(ToolMaterials.ARSONIST, 1.5F, -3.0F, new Item.Properties().fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<ShovelItem> MAGMA_CUBE_SHOVEL = ITEMS.register("magma_cube_shovel", () -> new EffectShovelItem(ToolMaterials.OBSIDIAN, 1.5F, -3.0F, new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));

    public static final DeferredItem<AxeItem> SKY_WOOD_AXE = ITEMS.register("sky_wood_axe", () -> new AxeItem(ToolMaterials.SKY_WOOD, 6.0F, -3.1F, new Item.Properties()));
    public static final DeferredItem<AxeItem> STELLAR_STONE_AXE = ITEMS.register("stellar_stone_axe", () -> new AxeItem(ToolMaterials.STELLAR_STONE, 6.0F, -3.1F, new Item.Properties()));
    public static final DeferredItem<AxeItem> RUBY_AXE = ITEMS.register("ruby_axe", () -> new AxeItem(ToolMaterials.RUBY, 6.0F, -3.1F, new Item.Properties()));
    public static final DeferredItem<AxeItem> AZURITE_AXE = ITEMS.register("azurite_axe", () -> new AxeItem(ToolMaterials.AZURITE, 6.0F, -3.1F, new Item.Properties()));
    public static final DeferredItem<AxeItem> MAGMATIC_GEL_AXE = ITEMS.register("magmatic_gel_axe", () -> new AxeItem(ToolMaterials.MAGMATIC_GEL, 6.0F, -3.1F, new Item.Properties()));
    public static final DeferredItem<AxeItem> OBSIDIAN_AXE = ITEMS.register("obsidian_axe", () -> new AxeItem(ToolMaterials.OBSIDIAN, 6.0F, -3.1F, new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<AxeItem> VOLUCITE_AXE = ITEMS.register("volucite_axe", () -> new EffectAxeItem(ToolMaterials.VOLUCITE, 6.0F, -3.1F, new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<AxeItem> LUNATIC_AXE = ITEMS.register("lunatic_axe", () -> new AxeItem(ToolMaterials.LUNATIC, 6.0F, -3.1F, new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<AxeItem> ARSONIST_AXE = ITEMS.register("arsonist_axe", () -> new AxeItem(ToolMaterials.ARSONIST, 6.0F, -3.1F, new Item.Properties().fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<AxeItem> HEAVY_AXE = ITEMS.register("heavy_axe", () -> new AerialHellAxeItem(ToolMaterials.HEAVY, 6.0F, -3.5F, -0.30F, 0.0F, new Item.Properties().setNoCombineRepair().rarity(Rarity.EPIC)));
    public static final DeferredItem<AxeItem> AXE_OF_LIGHT = ITEMS.register("axe_of_light", () -> new EffectAxeItem(ToolMaterials.LUNATIC, 5.0F, -3.1F, new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<AxeItem> CURSED_AXE = ITEMS.register("cursed_axe", () -> new AerialHellAxeItem(ToolMaterials.SHADOW, 2.0F, -3.2F, new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<AxeItem> BERSERK_AXE = ITEMS.register("berserk_axe", () -> new BerserkAxeItem(ToolMaterials.ARSONIST, 4.0F, -2.5F, new Item.Properties().setNoCombineRepair().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<HoeItem> SKY_WOOD_HOE = ITEMS.register("sky_wood_hoe", () -> new HoeItem(ToolMaterials.SKY_WOOD, 0.0F, -3.0F, new Item.Properties()));
    public static final DeferredItem<HoeItem> STELLAR_STONE_HOE = ITEMS.register("stellar_stone_hoe", () -> new HoeItem(ToolMaterials.STELLAR_STONE, 0.0F, -3.0F, new Item.Properties()));
    public static final DeferredItem<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new HoeItem(ToolMaterials.RUBY, 0.0F, -3.0F, new Item.Properties()));
    public static final DeferredItem<HoeItem> AZURITE_HOE = ITEMS.register("azurite_hoe", () -> new HoeItem(ToolMaterials.AZURITE, 0.0F, -3.0F, new Item.Properties()));
    public static final DeferredItem<HoeItem> MAGMATIC_GEL_HOE = ITEMS.register("magmatic_gel_hoe", () -> new HoeItem(ToolMaterials.MAGMATIC_GEL, 0.0F, -3.0F, new Item.Properties()));
    public static final DeferredItem<HoeItem> OBSIDIAN_HOE = ITEMS.register("obsidian_hoe", () -> new HoeItem(ToolMaterials.OBSIDIAN, 0.0F, -3.0F, new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<HoeItem> VOLUCITE_HOE = ITEMS.register("volucite_hoe", () -> new EffectHoeItem(ToolMaterials.VOLUCITE, 0.0F, -3.0F, new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<HoeItem> LUNATIC_HOE = ITEMS.register("lunatic_hoe", () -> new HoeItem(ToolMaterials.LUNATIC, 0.0F, -1.0F, new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<HoeItem> ARSONIST_HOE = ITEMS.register("arsonist_hoe", () -> new HoeItem(ToolMaterials.ARSONIST, 0.0F, -1.0F, new Item.Properties().fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<HoeItem> REAPER_SCYTHE = ITEMS.register("reaper_scythe", () -> new EffectHoeItem(ToolMaterials.SHADOW, -2.8F, 4.0F, new Item.Properties().rarity(AerialHellRarities.MYTHICAL.getValue())));

    //weapons
    public static final DeferredItem<SwordItem> SKY_WOOD_SWORD = ITEMS.register("sky_wood_sword", () -> new SwordItem(ToolMaterials.SKY_WOOD, 3, -2.4F, new Item.Properties()));
    public static final DeferredItem<SwordItem> STELLAR_STONE_SWORD = ITEMS.register("stellar_stone_sword", () -> new SwordItem(ToolMaterials.STELLAR_STONE, 3, -2.4F, new Item.Properties()));
    public static final DeferredItem<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new SwordItem(ToolMaterials.RUBY, 3, -2.4F, new Item.Properties()));
    public static final DeferredItem<SwordItem> AZURITE_SWORD = ITEMS.register("azurite_sword", () -> new SwordItem(ToolMaterials.AZURITE, 3, -2.4F, new Item.Properties()));
    public static final DeferredItem<SwordItem> MAGMATIC_GEL_SWORD = ITEMS.register("magmatic_gel_sword", () -> new SwordItem(ToolMaterials.MAGMATIC_GEL, 3, -2.4F, new Item.Properties()));
    public static final DeferredItem<SwordItem> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword", () -> new SwordItem(ToolMaterials.OBSIDIAN, 3, -2.4F, new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<SwordItem> VOLUCITE_SWORD = ITEMS.register("volucite_sword", () -> new EffectSwordItem(ToolMaterials.VOLUCITE, 3, -2.4F, new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<SwordItem> LUNATIC_SWORD = ITEMS.register("lunatic_sword", () -> new SwordItem(ToolMaterials.LUNATIC, 3, -2.4F, new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<SwordItem> ARSONIST_SWORD = ITEMS.register("arsonist_sword", () -> new AerialHellSwordItem(ToolMaterials.ARSONIST, 3, -2.4F, new Item.Properties().fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<SwordItem> HEAVY_SWORD = ITEMS.register("heavy_sword", () -> new AerialHellSwordItem(ToolMaterials.HEAVY, 3, -2.7F, -0.30F, 0.0F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<SwordItem> HEALTH_BOOST_SWORD = ITEMS.register("health_boost_sword", () -> new AerialHellSwordItem(ToolMaterials.LUNATIC, 3, -2.4F, 0.0F, 4.0F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<SwordItem> NINJA_SWORD = ITEMS.register("ninja_sword", () -> new EffectSwordItem(ToolMaterials.OBSIDIAN, 2, -1.6F, 0.15F, 0.0F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<SwordItem> NINJA_MASTER_SWORD = ITEMS.register("ninja_master_sword", () -> new EffectSwordItem(ToolMaterials.OBSIDIAN, 4, -2.4F, 0.15F, 0.0F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<SwordItem> GLOUTON_SWORD = ITEMS.register("glouton_sword", () -> new EffectSwordItem(ToolMaterials.RUBY, 3, -2.4F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<SwordItem> RANDOM_SWORD = ITEMS.register("random_sword", () -> new EffectSwordItem(ToolMaterials.RUBY, 2, -2.4F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<SwordItem> DISLOYAL_SWORD = ITEMS.register("disloyal_sword", () -> new AerialHellSwordItem(ToolMaterials.LUNATIC, 2, -2.4F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<SwordItem> CURSED_SWORD = ITEMS.register("cursed_sword", () -> new AerialHellSwordItem(ToolMaterials.SHADOW, 1, -2.5F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<SwordItem> ABSOLUTE_ZERO_SWORD = ITEMS.register("absolute_zero_sword", () -> new AerialHellSwordItem(ToolMaterials.LUNATIC, 2, -2.4F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<SwordItem> SWORD_OF_LIGHT = ITEMS.register("sword_of_light", () -> new EffectSwordItem(ToolMaterials.LUNATIC, 2, -2.4F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<SwordItem> ANTIDOTE_SWORD = ITEMS.register("antidote_sword", () -> new EffectSwordItem(ToolMaterials.RUBY, 4, -2.4F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<SwordItem> NETHERIAN_KING_SWORD = ITEMS.register("netherian_king_sword", () -> new EffectSwordItem(ToolMaterials.OBSIDIAN, 1, -2.4F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<SwordItem> GLASS_CANON_SWORD = ITEMS.register("glass_canon_sword", () -> new EffectSwordItem(ToolMaterials.ARSONIST, 7, -1.6F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<SwordItem> GOD_SWORD = ITEMS.register("god_sword", () -> new EffectSwordItem(ToolMaterials.ARSONIST, 3, -2.4F, new Item.Properties().setNoCombineRepair().fireResistant().rarity(AerialHellRarities.MYTHICAL.getValue())));

    public static final DeferredItem<SwordItem> FORGOTTEN_BATTLE_TRIDENT = ITEMS.register("forgotten_battle_trident", () -> new ForgottenBattleTridentItem(ToolMaterials.VOLUCITE, 3, -2.9F, 0.2F, 0.0F, new Item.Properties().durability(1000).rarity(AerialHellRarities.LEGENDARY.getValue())));

    //armor
    public static final DeferredItem<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(AerialHellArmorMaterials.RUBY, ArmorType.HELMET, (new Item.Properties().durability(ArmorType.HELMET.getDurability(15)))));
    public static final DeferredItem<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.RUBY, ArmorType.CHESTPLATE, (new Item.Properties().durability(ArmorType.CHESTPLATE.getDurability(15)))));
    public static final DeferredItem<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(AerialHellArmorMaterials.RUBY, ArmorType.LEGGINGS, (new Item.Properties()).durability(ArmorType.LEGGINGS.getDurability(15))));
    public static final DeferredItem<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(AerialHellArmorMaterials.RUBY, ArmorType.BOOTS, (new Item.Properties()).durability(ArmorType.BOOTS.getDurability(15))));

    public static final DeferredItem<ArmorItem> AZURITE_HELMET = ITEMS.register("azurite_helmet", () -> new ArmorItem(AerialHellArmorMaterials.AZURITE, ArmorType.HELMET, (new Item.Properties().durability(ArmorType.HELMET.getDurability(10)))));
    public static final DeferredItem<ArmorItem> AZURITE_CHESTPLATE = ITEMS.register("azurite_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.AZURITE, ArmorType.CHESTPLATE, (new Item.Properties().durability(ArmorType.CHESTPLATE.getDurability(10)))));
    public static final DeferredItem<ArmorItem> AZURITE_LEGGINGS = ITEMS.register("azurite_leggings", () -> new ArmorItem(AerialHellArmorMaterials.AZURITE, ArmorType.LEGGINGS, (new Item.Properties().durability(ArmorType.LEGGINGS.getDurability(10)))));
    public static final DeferredItem<ArmorItem> AZURITE_BOOTS = ITEMS.register("azurite_boots", () -> new ArmorItem(AerialHellArmorMaterials.AZURITE, ArmorType.BOOTS, (new Item.Properties().durability(ArmorType.BOOTS.getDurability(10)))));

    public static final DeferredItem<ArmorItem> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet", () -> new ArmorItem(AerialHellArmorMaterials.OBSIDIAN, ArmorType.HELMET, (new Item.Properties().durability(ArmorType.HELMET.getDurability(37))).rarity(Rarity.EPIC)));
    public static final DeferredItem<ArmorItem> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.OBSIDIAN, ArmorType.CHESTPLATE, (new Item.Properties().durability(ArmorType.CHESTPLATE.getDurability(37))).rarity(Rarity.EPIC)));
    public static final DeferredItem<ArmorItem> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings", () -> new ArmorItem(AerialHellArmorMaterials.OBSIDIAN, ArmorType.LEGGINGS, (new Item.Properties().durability(ArmorType.LEGGINGS.getDurability(37))).rarity(Rarity.EPIC)));
    public static final DeferredItem<ArmorItem> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots", () -> new ArmorItem(AerialHellArmorMaterials.OBSIDIAN, ArmorType.BOOTS, (new Item.Properties().durability(ArmorType.BOOTS.getDurability(37))).rarity(Rarity.EPIC)));

    public static final DeferredItem<ArmorItem> VOLUCITE_HELMET = ITEMS.register("volucite_helmet", () -> new ArmorItem(AerialHellArmorMaterials.VOLUCITE, ArmorType.HELMET, (new Item.Properties().durability(ArmorType.HELMET.getDurability(35))).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<ArmorItem> VOLUCITE_CHESTPLATE = ITEMS.register("volucite_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.VOLUCITE, ArmorType.CHESTPLATE, (new Item.Properties().durability(ArmorType.CHESTPLATE.getDurability(35))).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<ArmorItem> VOLUCITE_LEGGINGS = ITEMS.register("volucite_leggings", () -> new ArmorItem(AerialHellArmorMaterials.VOLUCITE, ArmorType.LEGGINGS, (new Item.Properties().durability(ArmorType.LEGGINGS.getDurability(35))).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<ArmorItem> VOLUCITE_BOOTS = ITEMS.register("volucite_boots", () -> new ArmorItem(AerialHellArmorMaterials.VOLUCITE, ArmorType.BOOTS, (new Item.Properties().durability(ArmorType.BOOTS.getDurability(35))).rarity(AerialHellRarities.VIBRANT.getValue())));

    public static final DeferredItem<ArmorItem> MAGMATIC_GEL_HELMET = ITEMS.register("magmatic_gel_helmet", () -> new ArmorItem(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorType.HELMET, (new Item.Properties().durability(ArmorType.HELMET.getDurability(7)))));
    public static final DeferredItem<ArmorItem> MAGMATIC_GEL_CHESTPLATE = ITEMS.register("magmatic_gel_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorType.CHESTPLATE, (new Item.Properties().durability(ArmorType.CHESTPLATE.getDurability(7)))));
    public static final DeferredItem<ArmorItem> MAGMATIC_GEL_LEGGINGS = ITEMS.register("magmatic_gel_leggings", () -> new ArmorItem(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorType.LEGGINGS, (new Item.Properties().durability(ArmorType.LEGGINGS.getDurability(7)))));
    public static final DeferredItem<ArmorItem> MAGMATIC_GEL_BOOTS = ITEMS.register("magmatic_gel_boots", () -> new ArmorItem(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorType.BOOTS, (new Item.Properties().durability(ArmorType.BOOTS.getDurability(7)))));

    public static final DeferredItem<ArmorItem> LUNATIC_HELMET = ITEMS.register("lunatic_helmet", () -> new ArmorItem(AerialHellArmorMaterials.LUNATIC, ArmorType.HELMET, (new Item.Properties().durability(ArmorType.HELMET.getDurability(33))).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<ArmorItem> LUNATIC_CHESTPLATE = ITEMS.register("lunatic_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.LUNATIC, ArmorType.CHESTPLATE, (new Item.Properties().durability(ArmorType.CHESTPLATE.getDurability(33))).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<ArmorItem> LUNATIC_LEGGINGS = ITEMS.register("lunatic_leggings", () -> new ArmorItem(AerialHellArmorMaterials.LUNATIC, ArmorType.LEGGINGS, (new Item.Properties().durability(ArmorType.LEGGINGS.getDurability(33))).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<ArmorItem> LUNATIC_BOOTS = ITEMS.register("lunatic_boots", () -> new ArmorItem(AerialHellArmorMaterials.LUNATIC, ArmorType.BOOTS, (new Item.Properties().durability(ArmorType.BOOTS.getDurability(33))).rarity(AerialHellRarities.LEGENDARY.getValue())));

    public static final DeferredItem<ArmorItem> ARSONIST_HELMET = ITEMS.register("arsonist_helmet", () -> new ArmorItem(AerialHellArmorMaterials.ARSONIST, ArmorType.HELMET, (new Item.Properties().durability(ArmorType.HELMET.getDurability(37))).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));
    public static final DeferredItem<ArmorItem> ARSONIST_CHESTPLATE = ITEMS.register("arsonist_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.ARSONIST, ArmorType.CHESTPLATE, (new Item.Properties().durability(ArmorType.CHESTPLATE.getDurability(37))).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));
    public static final DeferredItem<ArmorItem> ARSONIST_LEGGINGS = ITEMS.register("arsonist_leggings", () -> new ArmorItem(AerialHellArmorMaterials.ARSONIST, ArmorType.LEGGINGS, (new Item.Properties().durability(ArmorType.LEGGINGS.getDurability(37))).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));
    public static final DeferredItem<ArmorItem> ARSONIST_BOOTS = ITEMS.register("arsonist_boots", () -> new ArmorItem(AerialHellArmorMaterials.ARSONIST, ArmorType.BOOTS, (new Item.Properties().durability(ArmorType.BOOTS.getDurability(37))).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));

    public static final DeferredItem<ArmorItem> SHADOW_HELMET = ITEMS.register("shadow_helmet", () -> new ShadowArmorItem(AerialHellArmorMaterials.SHADOW, ArmorType.HELMET, (new Item.Properties().durability(ArmorType.HELMET.getDurability(25))).rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<ArmorItem> SHADOW_CHESTPLATE = ITEMS.register("shadow_chestplate", () -> new ShadowArmorItem(AerialHellArmorMaterials.SHADOW, ArmorType.CHESTPLATE, (new Item.Properties().durability(ArmorType.CHESTPLATE.getDurability(25))).rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<ArmorItem> SHADOW_LEGGINGS = ITEMS.register("shadow_leggings", () -> new ShadowArmorItem(AerialHellArmorMaterials.SHADOW, ArmorType.LEGGINGS, (new Item.Properties().durability(ArmorType.LEGGINGS.getDurability(25))).rarity(AerialHellRarities.CORRUPTED.getValue())));
    public static final DeferredItem<ArmorItem> SHADOW_BOOTS = ITEMS.register("shadow_boots", () -> new ShadowArmorItem(AerialHellArmorMaterials.SHADOW, ArmorType.BOOTS, (new Item.Properties().durability(ArmorType.BOOTS.getDurability(25))).rarity(AerialHellRarities.CORRUPTED.getValue())));

    //effect totems
    public static final DeferredItem<Item> REGENERATION_TOTEM = ITEMS.register("regeneration_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> SPEED_TOTEM = ITEMS.register("speed_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> SPEED_II_TOTEM = ITEMS.register("speed_ii_totem", () -> new EnchantedEffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> NIGHT_VISION_TOTEM = ITEMS.register("night_vision_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.LEGENDARY.getValue())));
    public static final DeferredItem<Item> AGILITY_TOTEM = ITEMS.register("agility_totem", () -> new EnchantedEffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> HERO_TOTEM = ITEMS.register("hero_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> HEAD_IN_THE_CLOUDS_TOTEM = ITEMS.register("head_in_the_clouds_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.VIBRANT.getValue())));
    public static final DeferredItem<Item> GOD_TOTEM = ITEMS.register("god_totem", () -> new EnchantedEffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue()).fireResistant()));
    public static final DeferredItem<Item> CURSED_TOTEM = ITEMS.register("cursed_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL.getValue())));
    public static final DeferredItem<Item> SHADOW_TOTEM = ITEMS.register("shadow_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.CORRUPTED.getValue())));

    //spawn eggs
    public static final DeferredItem<Item> STELLAR_STONE_AUTOMATON_SPAWN_EGG = ITEMS.register("stellar_stone_automaton_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.STELLAR_STONE_AUTOMATON.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT.getValue()), false));
    public static final DeferredItem<Item> EVIL_COW_SPAWN_EGG = ITEMS.register("evil_cow_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.EVIL_COW.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> CORTINARIUS_COW_SPAWN_EGG = ITEMS.register("cortinarius_cow_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CORTINARIUS_COW.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> STELLAR_ENT_SPAWN_EGG = ITEMS.register("stellar_ent_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.STELLAR_ENT.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> VENOMOUS_SNAKE_SPAWN_EGG = ITEMS.register("venomous_snake_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.VENOMOUS_SNAKE.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> WORM_SPAWN_EGG = ITEMS.register("worm_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.WORM.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> STELLAR_CHICKEN_SPAWN_EGG = ITEMS.register("stellar_chicken_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.STELLAR_CHICKEN.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> STELLAR_BOAR_SPAWN_EGG = ITEMS.register("stellar_boar_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.STELLAR_BOAR.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SHROOMBOOM_SPAWN_EGG = ITEMS.register("shroomboom_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHROOMBOOM.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> VERDIGRIS_ZOMBIE_SPAWN_EGG = ITEMS.register("verdigris_zombie_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.VERDIGRIS_ZOMBIE.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> MUMMY_SPAWN_EGG = ITEMS.register("mummy_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.MUMMY.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SLIME_PIRATE_SPAWN_EGG = ITEMS.register("slime_pirate_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SLIME_PIRATE.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SLIME_NINJA_PIRATE_SPAWN_EGG = ITEMS.register("slime_ninja_pirate_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SLIME_NINJA_PIRATE.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> GHOST_SLIME_PIRATE_SPAWN_EGG = ITEMS.register("ghost_slime_pirate_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.GHOST_SLIME_PIRATE.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG = ITEMS.register("ghost_slime_ninja_pirate_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.GHOST_SLIME_NINJA_PIRATE.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SANDY_SHEEP_SPAWN_EGG = ITEMS.register("sandy_sheep_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SANDY_SHEEP.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> GLIDING_TURTLE_SPAWN_EGG = ITEMS.register("gliding_turtle_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.GLIDING_TURTLE.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> FAT_PHANTOM_SPAWN_EGG = ITEMS.register("fat_phantom_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.FAT_PHANTOM.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> KODAMA_SPAWN_EGG = ITEMS.register("kodama_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.KODAMA.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> MUD_GOLEM_SPAWN_EGG = ITEMS.register("mud_golem_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.MUD_GOLEM.get(), new Item.Properties().rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> MUD_SOLDIER_SPAWN_EGG = ITEMS.register("mud_soldier_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.MUD_SOLDIER.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> MUD_CYCLE_MAGE_SPAWN_EGG = ITEMS.register("mud_cycle_mage_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.MUD_CYCLE_MAGE.get(), new Item.Properties().rarity(AerialHellRarities.LEGENDARY.getValue()), true));
    public static final DeferredItem<Item> HELL_SPIDER_SPAWN_EGG = ITEMS.register("hell_spider_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.HELL_SPIDER.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> CRYSTAL_GOLEM_SPAWN_EGG = ITEMS.register("crystal_golem_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CRYSTAL_GOLEM.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> CRYSTAL_SLIME_SPAWN_EGG = ITEMS.register("crystal_slime_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CRYSTAL_SLIME.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> CRYSTAL_SPIDER_SPAWN_EGG = ITEMS.register("crystal_spider_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CRYSTAL_SPIDER.get(), new Item.Properties().rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> LUNATIC_PRIEST_SPAWN_EGG = ITEMS.register("lunatic_priest_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.LUNATIC_PRIEST.get(), new Item.Properties().rarity(AerialHellRarities.MYTHICAL.getValue()), true));
    public static final DeferredItem<Item> CRYSTAL_CATERPILLAR_SPAWN_EGG = ITEMS.register("crystal_caterpillar_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CRYSTAL_CATERPILLAR.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> FOREST_CATERPILLAR_SPAWN_EGG = ITEMS.register("forest_caterpillar_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.FOREST_CATERPILLAR.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> TORN_SPIRIT_SPAWN_EGG = ITEMS.register("torn_spirit_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.TORN_SPIRIT.get(), new Item.Properties().rarity(Rarity.EPIC), false));
    public static final DeferredItem<Item> CHAINED_GOD_SPAWN_EGG = ITEMS.register("chained_god_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.CHAINED_GOD.get(), new Item.Properties().rarity(AerialHellRarities.MYTHICAL.getValue()), true));
    public static final DeferredItem<Item> ICE_SPIRIT_SPAWN_EGG = ITEMS.register("ice_spirit_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.ICE_SPIRIT.get(), new Item.Properties().rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> FIRE_SPIRIT_SPAWN_EGG = ITEMS.register("fire_spirit_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.FIRE_SPIRIT.get(), new Item.Properties().rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> ELECTRO_SPIRIT_SPAWN_EGG = ITEMS.register("electro_spirit_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.ELECTRO_SPIRIT.get(), new Item.Properties().rarity(Rarity.UNCOMMON), false));
    public static final DeferredItem<Item> FLYING_JELLYFISH_SPAWN_EGG = ITEMS.register("flying_jellyfish_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.FLYING_JELLYFISH.get(), new Item.Properties().rarity(Rarity.COMMON), false));
    public static final DeferredItem<Item> SHADOW_FLYING_SKULL_SPAWN_EGG = ITEMS.register("shadow_flying_skull_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHADOW_FLYING_SKULL.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue()), false));
    public static final DeferredItem<Item> SHADOW_TROLL_SPAWN_EGG = ITEMS.register("shadow_troll_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHADOW_TROLL.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue()), false));
    public static final DeferredItem<Item> SHADOW_AUTOMATON_SPAWN_EGG = ITEMS.register("shadow_automaton_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHADOW_AUTOMATON.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue()), false));
    public static final DeferredItem<Item> SHADOW_SPIDER_SPAWN_EGG = ITEMS.register("shadow_spider_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.SHADOW_SPIDER.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue()), false));
    public static final DeferredItem<Item> LILITH_SPAWN_EGG = ITEMS.register("lilith_spawn_egg", () -> new AerialHellSpawnEgg(() -> AerialHellEntities.LILITH.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED.getValue()), true));

    public static final DeferredItem<Item> AERIAL_HELL_PAINTING = ITEMS.register("aerial_hell_painting", () -> new AerialHellHangingEntityItem(() -> AerialHellEntities.AERIAL_HELL_PAINTING.get(), new Item.Properties()));

    //build items
    public static final DeferredItem<Item> BLOCK_UPDATER = ITEMS.register("block_updater", () -> new BlockUpdaterItem(new Item.Properties()));
    public static final DeferredItem<Item> BLOCK_CRACKER = ITEMS.register("block_cracker", () -> new BlockCrackerItem(new Item.Properties()));
}
