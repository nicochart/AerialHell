package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
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
import net.minecraft.block.ComposterBlock;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;

public class AerialHellItems
{
    public static void registerCompostableItems()
    {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_GRASS_ITEM, 0.1F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_TALL_GRASS_ITEM, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_GRASS_BALL_ITEM, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_DEAD_BUSH_ITEM, 0.1F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLUE_FLOWER_ITEM, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLACK_ROSE_ITEM, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BELLFLOWER_ITEM, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(AERIAL_BERRY, 0.5F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(VIBRANT_AERIAL_BERRY, 0.85F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(FROZEN_AERIAL_BERRY, 0.85F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(AERIAL_BERRY_SEEDS, 0.1F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(VIBRANT_AERIAL_BERRY_SEEDS, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(COPPER_PINE_CONE, 0.5F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(AERIAL_TREE_LEAVES_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(AERIAL_TREE_SAPLING_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(GOLDEN_BEECH_LEAVES_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(GOLDEN_BEECH_SAPLING_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(COPPER_PINE_LEAVES_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(COPPER_PINE_SAPLING_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(LAPIS_ROBINIA_SAPLING_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(LAPIS_ROBINIA_LEAVES_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(SHADOW_PINE_SAPLING_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(SHADOW_PINE_LEAVES_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_JUNGLE_TREE_SAPLING_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(STELLAR_JUNGLE_TREE_LEAVES_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(CORTINARIUS_VIOLACEUS_ITEM, 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK_ITEM, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(SKY_CACTUS_FIBER, 0.1F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(SKY_CACTUS_ITEM, 0.4F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(VIBRANT_SKY_CACTUS_FIBER, 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(VIBRANT_SKY_CACTUS_ITEM, 0.8F);
    }

    //portal
    public static final Item STELLAR_PORTAL_FRAME_BLOCK_ITEM = register("stellar_portal_frame_block", new BlockItem(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK, new Item.Settings()));
    public static final Item STELLAR_PORTAL_FRAME_ORE_ITEM = register("stellar_portal_frame_ore", new BlockItem(AerialHellBlocks.STELLAR_PORTAL_FRAME_ORE, new Item.Settings()));
    public static final Item DEEPSLATE_STELLAR_PORTAL_FRAME_ORE_ITEM = register("deepslate_stellar_portal_frame_ore", new BlockItem(AerialHellBlocks.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE, new Item.Settings()));
    public static final Item STELLAR_PORTAL_FRAME_BRICK = register("stellar_portal_frame_brick", new Item(new Item.Settings()));
    public static final Item STELLAR_LIGHTER = register("stellar_lighter", new Item(new Item.Settings().maxCount(1).maxDamage(4)));

    //torch
    public static final Item FLUORITE_TORCH_ITEM = register("fluorite_torch", new VerticallyAttachableBlockItem(AerialHellBlocks.FLUORITE_TORCH, AerialHellBlocks.FLUORITE_WALL_TORCH, new Item.Settings(), Direction.DOWN));
    public static final Item VOLUCITE_TORCH_ITEM = register("volucite_torch", new VerticallyAttachableBlockItem(AerialHellBlocks.VOLUCITE_TORCH, AerialHellBlocks.VOLUCITE_WALL_TORCH, new Item.Settings(), Direction.DOWN));
    public static final Item SHADOW_TORCH_ITEM = register("shadow_torch", new VerticallyAttachableBlockItem(AerialHellBlocks.SHADOW_TORCH, AerialHellBlocks.SHADOW_WALL_TORCH, new Item.Settings(), Direction.DOWN));

    //lanterns
    public static final Item FLUORITE_LANTERN_ITEM = register("fluorite_lantern", new BlockItem(AerialHellBlocks.FLUORITE_LANTERN, new Item.Settings()));
    public static final Item RUBY_LANTERN_ITEM = register("ruby_lantern", new BlockItem(AerialHellBlocks.RUBY_LANTERN, new Item.Settings()));
    public static final Item RUBY_FLUORITE_LANTERN_ITEM = register("ruby_fluorite_lantern", new BlockItem(AerialHellBlocks.RUBY_FLUORITE_LANTERN, new Item.Settings()));
    public static final Item VOLUCITE_LANTERN_ITEM = register("volucite_lantern", new BlockItem(AerialHellBlocks.VOLUCITE_LANTERN, new Item.Settings()));
    public static final Item VOLUCITE_FLUORITE_LANTERN_ITEM = register("volucite_fluorite_lantern", new BlockItem(AerialHellBlocks.VOLUCITE_FLUORITE_LANTERN, new Item.Settings()));
    public static final Item LUNATIC_LANTERN_ITEM = register("lunatic_lantern", new BlockItem(AerialHellBlocks.LUNATIC_LANTERN, new Item.Settings()));
    public static final Item SHADOW_LANTERN_ITEM = register("shadow_lantern", new BlockItem(AerialHellBlocks.SHADOW_LANTERN, new Item.Settings()));

    //chains
    public static final Item RUBY_CHAIN_ITEM = register("ruby_chain", new BlockItem(AerialHellBlocks.RUBY_CHAIN, new Item.Settings()));
    public static final Item VOLUCITE_CHAIN_ITEM = register("volucite_chain", new BlockItem(AerialHellBlocks.VOLUCITE_CHAIN, new Item.Settings()));
    public static final Item LUNATIC_CHAIN_ITEM = register("lunatic_chain", new BlockItem(AerialHellBlocks.LUNATIC_CHAIN, new Item.Settings()));
    public static final Item SHADOW_CHAIN_ITEM = register("shadow_chain", new BlockItem(AerialHellBlocks.SHADOW_CHAIN, new Item.Settings()));

    //grass & dirt
    public static final Item STELLAR_GRASS_BLOCK_ITEM = register("stellar_grass_block", new BlockItem(AerialHellBlocks.STELLAR_GRASS_BLOCK, new Item.Settings()));
    public static final Item CHISELED_STELLAR_GRASS_BLOCK_ITEM = register("chiseled_stellar_grass_block", new BlockItem(AerialHellBlocks.CHISELED_STELLAR_GRASS_BLOCK, new Item.Settings()));
    public static final Item STELLAR_DIRT_ITEM = register("stellar_dirt", new BlockItem(AerialHellBlocks.STELLAR_DIRT, new Item.Settings()));
    public static final Item STELLAR_COARSE_DIRT_ITEM = register("stellar_coarse_dirt", new BlockItem(AerialHellBlocks.STELLAR_COARSE_DIRT, new Item.Settings()));
    public static final Item STELLAR_FARMLAND_ITEM = register("stellar_farmland", new BlockItem(AerialHellBlocks.STELLAR_FARMLAND, new Item.Settings()));
    public static final Item STELLAR_DIRT_PATH_ITEM = register("stellar_dirt_path", new BlockItem(AerialHellBlocks.STELLAR_DIRT_PATH, new Item.Settings()));
    public static final Item STELLAR_PODZOL_ITEM = register("stellar_podzol", new BlockItem(AerialHellBlocks.STELLAR_PODZOL, new Item.Settings()));
    public static final Item STELLAR_CRYSTAL_PODZOL_ITEM = register("stellar_crystal_podzol", new BlockItem(AerialHellBlocks.STELLAR_CRYSTAL_PODZOL, new Item.Settings()));
    public static final Item CHISELED_STELLAR_DIRT_ITEM = register("chiseled_stellar_dirt", new BlockItem(AerialHellBlocks.CHISELED_STELLAR_DIRT, new Item.Settings()));
    public static final Item SHADOW_GRASS_BLOCK_ITEM = register("shadow_grass_block", new BlockItem(AerialHellBlocks.SHADOW_GRASS_BLOCK, new Item.Settings()));

    //slippery sand
    public static final Item SLIPPERY_SAND_ITEM = register("slippery_sand", new BlockItem(AerialHellBlocks.SLIPPERY_SAND, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_ITEM = register("slippery_sand_stone", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_ITEM = register("slippery_sand_stone_bricks", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS, new Item.Settings()));
    public static final Item CUT_SLIPPERY_SAND_STONE_ITEM = register("cut_slippery_sand_stone", new BlockItem(AerialHellBlocks.CUT_SLIPPERY_SAND_STONE, new Item.Settings()));
    public static final Item CRACKED_SLIPPERY_SAND_STONE_BRICKS_ITEM = register("cracked_slippery_sand_stone_bricks", new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS, new Item.Settings()));

    //giant root
    public static final Item GIANT_ROOT_ITEM = register("giant_root", new BurnableBlockItem(AerialHellBlocks.GIANT_ROOT, new Item.Settings(), 300));

    //aerial_tree
    public static final Item AERIAL_TREE_LOG_ITEM = register("aerial_tree_log", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_LOG, new Item.Settings(), 300));
    public static final Item STRIPPED_AERIAL_TREE_LOG_ITEM = register("stripped_aerial_tree_log", new BurnableBlockItem(AerialHellBlocks.STRIPPED_AERIAL_TREE_LOG, new Item.Settings(), 300));
    public static final Item AERIAL_TREE_WOOD_ITEM = register("aerial_tree_wood", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_WOOD, new Item.Settings(), 210));
    public static final Item STRIPPED_AERIAL_TREE_WOOD_ITEM = register("stripped_aerial_tree_wood", new BurnableBlockItem(AerialHellBlocks.STRIPPED_AERIAL_TREE_WOOD, new Item.Settings(), 210));
    public static final Item AERIAL_TREE_LEAVES_ITEM = register("aerial_tree_leaves", new BlockItem(AerialHellBlocks.AERIAL_TREE_LEAVES, new Item.Settings()));
    public static final Item AERIAL_TREE_PLANKS_ITEM = register("aerial_tree_planks", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_PLANKS, new Item.Settings(), 300));
    public static final Item CHISELED_AERIAL_TREE_PLANKS_ITEM = register("chiseled_aerial_tree_planks", new BurnableBlockItem(AerialHellBlocks.CHISELED_AERIAL_TREE_PLANKS, new Item.Settings(), 300));
    public static final Item AERIAL_TREE_BOOKSHELF_ITEM = register("aerial_tree_bookshelf", new BlockItem(AerialHellBlocks.AERIAL_TREE_BOOKSHELF, new Item.Settings()));
    public static final Item AERIAL_TREE_SAPLING_ITEM = register("aerial_tree_sapling", new BlockItem(AerialHellBlocks.AERIAL_TREE_SAPLING, new Item.Settings()));
    
    //petrified aerial tree
    public static final Item PETRIFIED_AERIAL_TREE_LOG_ITEM = register("petrified_aerial_tree_log", new BurnableBlockItem(AerialHellBlocks.PETRIFIED_AERIAL_TREE_LOG, new Item.Settings(), 600));

    //golden beech
    public static final Item GOLDEN_BEECH_LOG_ITEM = register("golden_beech_log", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_LOG, new Item.Settings(), 300));
    public static final Item STRIPPED_GOLDEN_BEECH_LOG_ITEM = register("stripped_golden_beech_log", new BurnableBlockItem(AerialHellBlocks.STRIPPED_GOLDEN_BEECH_LOG, new Item.Settings(), 300));
    public static final Item GOLDEN_BEECH_WOOD_ITEM = register("golden_beech_wood", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_WOOD, new Item.Settings(), 210));
    public static final Item STRIPPED_GOLDEN_BEECH_WOOD_ITEM = register("stripped_golden_beech_wood", new BurnableBlockItem(AerialHellBlocks.STRIPPED_GOLDEN_BEECH_WOOD, new Item.Settings(), 210));
    public static final Item GOLDEN_BEECH_PLANKS_ITEM = register("golden_beech_planks", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_PLANKS, new Item.Settings(), 300));
    public static final Item CHISELED_GOLDEN_BEECH_PLANKS_ITEM = register("chiseled_golden_beech_planks", new BurnableBlockItem(AerialHellBlocks.CHISELED_GOLDEN_BEECH_PLANKS, new Item.Settings(), 300));
    public static final Item GOLDEN_BEECH_LEAVES_ITEM = register("golden_beech_leaves", new BlockItem(AerialHellBlocks.GOLDEN_BEECH_LEAVES, new Item.Settings()));
    public static final Item GOLDEN_BEECH_BOOKSHELF_ITEM = register("golden_beech_bookshelf", new BlockItem(AerialHellBlocks.GOLDEN_BEECH_BOOKSHELF, new Item.Settings()));
    public static final Item GOLDEN_BEECH_SAPLING_ITEM = register("golden_beech_sapling", new BlockItem(AerialHellBlocks.GOLDEN_BEECH_SAPLING, new Item.Settings()));

    //copper pine
    public static final Item COPPER_PINE_LOG_ITEM = register("copper_pine_log", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_LOG, new Item.Settings(), 300));
    public static final Item STRIPPED_COPPER_PINE_LOG_ITEM = register("stripped_copper_pine_log", new BurnableBlockItem(AerialHellBlocks.STRIPPED_COPPER_PINE_LOG, new Item.Settings(), 300));
    public static final Item COPPER_PINE_WOOD_ITEM = register("copper_pine_wood", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_WOOD, new Item.Settings(), 210));
    public static final Item STRIPPED_COPPER_PINE_WOOD_ITEM = register("stripped_copper_pine_wood", new BurnableBlockItem(AerialHellBlocks.STRIPPED_COPPER_PINE_WOOD, new Item.Settings(), 210));
    public static final Item COPPER_PINE_PLANKS_ITEM = register("copper_pine_planks", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_PLANKS, new Item.Settings(), 300));
    public static final Item COPPER_PINE_LEAVES_ITEM = register("copper_pine_leaves", new BlockItem(AerialHellBlocks.COPPER_PINE_LEAVES, new Item.Settings()));
    public static final Item COPPER_PINE_BOOKSHELF_ITEM = register("copper_pine_bookshelf", new BlockItem(AerialHellBlocks.COPPER_PINE_BOOKSHELF, new Item.Settings()));
    public static final Item COPPER_PINE_SAPLING_ITEM = register("copper_pine_sapling", new BlockItem(AerialHellBlocks.COPPER_PINE_SAPLING, new Item.Settings()));

    //lapis robinia
    public static final Item LAPIS_ROBINIA_LOG_ITEM = register("lapis_robinia_log", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_LOG, new Item.Settings(), 400));
    public static final Item ENCHANTED_LAPIS_ROBINIA_LOG_ITEM = register("enchanted_lapis_robinia_log", new BurnableBlockItem(AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG, new Item.Settings(), 400));
    public static final Item STRIPPED_LAPIS_ROBINIA_LOG_ITEM = register("stripped_lapis_robinia_log", new BurnableBlockItem(AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_LOG, new Item.Settings(), 400));
    public static final Item LAPIS_ROBINIA_WOOD_ITEM = register("lapis_robinia_wood", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_WOOD, new Item.Settings(), 300));
    public static final Item STRIPPED_LAPIS_ROBINIA_WOOD_ITEM = register("stripped_lapis_robinia_wood", new BurnableBlockItem(AerialHellBlocks.STRIPPED_LAPIS_ROBINIA_WOOD, new Item.Settings(), 300));
    public static final Item LAPIS_ROBINIA_LEAVES_ITEM = register("lapis_robinia_leaves", new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_LEAVES, new Item.Settings()));
    public static final Item LAPIS_ROBINIA_PLANKS_ITEM = register("lapis_robinia_planks", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_PLANKS, new Item.Settings(), 300));
    public static final Item LAPIS_ROBINIA_BOOKSHELF_ITEM = register("lapis_robinia_bookshelf", new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_BOOKSHELF, new Item.Settings()));
    public static final Item LAPIS_ROBINIA_SAPLING_ITEM = register("lapis_robinia_sapling", new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_SAPLING, new Item.Settings()));

    //shadow pine
    public static final Item SHADOW_PINE_LOG_ITEM = register("shadow_pine_log", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_LOG, new Item.Settings(), 300));
    public static final Item EYE_SHADOW_PINE_LOG_ITEM = register("eye_shadow_pine_log", new BurnableBlockItem(AerialHellBlocks.EYE_SHADOW_PINE_LOG, new Item.Settings(), 300));
    public static final Item STRIPPED_SHADOW_PINE_LOG_ITEM = register("stripped_shadow_pine_log", new BurnableBlockItem(AerialHellBlocks.STRIPPED_SHADOW_PINE_LOG, new Item.Settings(), 300));
    public static final Item SHADOW_PINE_WOOD_ITEM = register("shadow_pine_wood", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_WOOD, new Item.Settings(), 210));
    public static final Item STRIPPED_SHADOW_PINE_WOOD_ITEM = register("stripped_shadow_pine_wood", new BurnableBlockItem(AerialHellBlocks.STRIPPED_SHADOW_PINE_WOOD, new Item.Settings(), 210));
    public static final Item SHADOW_PINE_LEAVES_ITEM = register("shadow_pine_leaves", new BlockItem(AerialHellBlocks.SHADOW_PINE_LEAVES, new Item.Settings()));
    public static final Item PURPLE_SHADOW_PINE_LEAVES_ITEM = register("purple_shadow_pine_leaves", new BlockItem(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES, new Item.Settings()));
    public static final Item SHADOW_PINE_PLANKS_ITEM = register("shadow_pine_planks", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_PLANKS, new Item.Settings(), 300));
    public static final Item SHADOW_PINE_BOOKSHELF_ITEM = register("shadow_pine_bookshelf", new BlockItem(AerialHellBlocks.SHADOW_PINE_BOOKSHELF, new Item.Settings()));
    public static final Item SHADOW_PINE_SAPLING_ITEM = register("shadow_pine_sapling", new BlockItem(AerialHellBlocks.SHADOW_PINE_SAPLING, new Item.Settings()));
    public static final Item PURPLE_SHADOW_PINE_SAPLING_ITEM = register("purple_shadow_pine_sapling", new BlockItem(AerialHellBlocks.PURPLE_SHADOW_PINE_SAPLING, new Item.Settings()));

    //stellar jungle tree
    public static final Item STELLAR_JUNGLE_TREE_LOG_ITEM = register("stellar_jungle_tree_log", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_LOG, new Item.Settings(), 400));
    public static final Item STRIPPED_STELLAR_JUNGLE_TREE_LOG_ITEM = register("stripped_stellar_jungle_tree_log", new BurnableBlockItem(AerialHellBlocks.STRIPPED_STELLAR_JUNGLE_TREE_LOG, new Item.Settings(), 400));
    public static final Item STELLAR_JUNGLE_TREE_WOOD_ITEM = register("stellar_jungle_tree_wood", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_WOOD, new Item.Settings(), 300));
    public static final Item STRIPPED_STELLAR_JUNGLE_TREE_WOOD_ITEM = register("stripped_stellar_jungle_tree_wood", new BurnableBlockItem(AerialHellBlocks.STRIPPED_STELLAR_JUNGLE_TREE_WOOD, new Item.Settings(), 300));
    public static final Item STELLAR_JUNGLE_TREE_LEAVES_ITEM = register("stellar_jungle_tree_leaves", new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES, new Item.Settings()));
    public static final Item STELLAR_JUNGLE_TREE_PLANKS_ITEM = register("stellar_jungle_tree_planks", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_PLANKS, new Item.Settings(), 300));
    public static final Item STELLAR_JUNGLE_TREE_BOOKSHELF_ITEM = register("stellar_jungle_tree_bookshelf", new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BOOKSHELF, new Item.Settings()));
    public static final Item STELLAR_JUNGLE_TREE_SAPLING_ITEM = register("stellar_jungle_tree_sapling", new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_SAPLING, new Item.Settings()));
    public static final Item DEAD_STELLAR_JUNGLE_TREE_LOG_ITEM = register("dead_stellar_jungle_tree_log", new BurnableBlockItem(AerialHellBlocks.DEAD_STELLAR_JUNGLE_TREE_LOG, new Item.Settings(), 300));

    //shroom
    public static final Item GIANT_CORTINARIUS_VIOLACEUS_STEM_ITEM = register("giant_cortinarius_violaceus_stem", new BurnableBlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_STEM, new Item.Settings(), 100));
    public static final Item STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM_ITEM = register("stripped_giant_cortinarius_violaceus_stem", new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM, new Item.Settings(), 100));
    public static final Item GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM_ITEM = register("giant_cortinarius_violaceus_bark_stem", new BurnableBlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM, new Item.Settings(), 100));
    public static final Item STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM_ITEM = register("stripped_giant_cortinarius_violaceus_bark_stem", new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM, new Item.Settings(), 100));
    public static final Item GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK_ITEM = register("giant_cortinarius_violaceus_cap_block", new BlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK, new Item.Settings()));
    public static final Item GIANT_CORTINARIUS_VIOLACEUS_LIGHT_ITEM = register("giant_cortinarius_violaceus_light", new BlockItem(AerialHellBlocks.GIANT_CORTINARIUS_VIOLACEUS_LIGHT, new Item.Settings()));
    public static final Item CORTINARIUS_VIOLACEUS_ITEM = register("cortinarius_violaceus", new BlockItem(AerialHellBlocks.CORTINARIUS_VIOLACEUS, new Item.Settings()));

    public static final Item GIANT_VERDIGRIS_AGARIC_STEM_ITEM = register("giant_verdigris_agaric_stem", new BurnableBlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_STEM, new Item.Settings(), 100));
    public static final Item STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM_ITEM = register("stripped_giant_verdigris_agaric_stem", new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM, new Item.Settings(), 100));
    public static final Item GIANT_VERDIGRIS_AGARIC_BARK_STEM_ITEM = register("giant_verdigris_agaric_bark_stem", new BurnableBlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_BARK_STEM, new Item.Settings(), 75));
    public static final Item STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM_ITEM = register("stripped_giant_verdigris_agaric_bark_stem", new BurnableBlockItem(AerialHellBlocks.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM, new Item.Settings(), 75));
    public static final Item GIANT_VERDIGRIS_AGARIC_CAP_BLOCK_ITEM = register("giant_verdigris_agaric_cap_block", new BlockItem(AerialHellBlocks.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK, new Item.Settings()));
    public static final Item VERDIGRIS_AGARIC_ITEM = register("verdigris_agaric", new BlockItem(AerialHellBlocks.VERDIGRIS_AGARIC, new Item.Settings()));
    public static final Item GLOWING_BOLETUS_ITEM = register("glowing_boletus", new BlockItem(AerialHellBlocks.GLOWING_BOLETUS, new Item.Settings()));
    public static final Item TALL_GLOWING_BOLETUS_ITEM = register("tall_glowing_boletus", new BlockItem(AerialHellBlocks.TALL_GLOWING_BOLETUS, new Item.Settings()));
    public static final Item BLUE_MEANIE_CLUSTER_ITEM = register("blue_meanie_cluster", new BlockItem(AerialHellBlocks.BLUE_MEANIE_CLUSTER, new Item.Settings()));
    public static final Item GIANT_ROOT_SHROOM_ITEM = register("giant_root_shroom", new BlockItem(AerialHellBlocks.GIANT_ROOT_SHROOM, new Item.Settings()));

    public static final Item GIANT_GANODERMA_APPLANATUM_BLOCK_ITEM = register("giant_ganoderma_applanatum_block", new BlockItem(AerialHellBlocks.GIANT_GANODERMA_APPLANATUM_BLOCK, new Item.Settings()));

    public static final Item GRAY_SHROOM_PLANKS_ITEM = register("gray_shroom_planks", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_PLANKS, new Item.Settings(), 100));
    public static final Item GRAY_SHROOM_BOOKSHELF_ITEM = register("gray_shroom_bookshelf", new BlockItem(AerialHellBlocks.GRAY_SHROOM_BOOKSHELF, new Item.Settings()));

    public static final Item SHADOW_AERIAL_TREE_LOG_ITEM = register("shadow_aerial_tree_log", new BurnableBlockItem(AerialHellBlocks.SHADOW_AERIAL_TREE_LOG, new Item.Settings(), 300));
    public static final Item SHADOW_GOLDEN_BEECH_LOG_ITEM = register("shadow_golden_beech_log", new BurnableBlockItem(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG, new Item.Settings(), 300));
    public static final Item SHADOW_COPPER_PINE_LOG_ITEM = register("shadow_copper_pine_log", new BurnableBlockItem(AerialHellBlocks.SHADOW_COPPER_PINE_LOG, new Item.Settings(), 300));
    public static final Item SHADOW_LAPIS_ROBINIA_LOG_ITEM = register("shadow_lapis_robinia_log", new BurnableBlockItem(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, new Item.Settings(), 300));
    public static final Item SHADOW_STELLAR_JUNGLE_TREE_LOG_ITEM = register("shadow_stellar_jungle_tree_log", new BurnableBlockItem(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG, new Item.Settings(), 300));
    public static final Item HOLLOW_SHADOW_PINE_LOG_ITEM = register("hollow_shadow_pine_log", new BurnableBlockItem(AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, new Item.Settings(), 300));
    public static final Item SHADOW_AERIAL_TREE_LEAVES_ITEM = register("shadow_aerial_tree_leaves", new BlockItem(AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, new Item.Settings()));
    public static final Item SHADOW_GOLDEN_BEECH_LEAVES_ITEM = register("shadow_golden_beech_leaves", new BlockItem(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES, new Item.Settings()));
    public static final Item SHADOW_COPPER_PINE_LEAVES_ITEM = register("shadow_copper_pine_leaves", new BlockItem(AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES, new Item.Settings()));
    public static final Item SHADOW_LAPIS_ROBINIA_LEAVES_ITEM = register("shadow_lapis_robinia_leaves", new BlockItem(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES, new Item.Settings()));
    public static final Item SHADOW_STELLAR_JUNGLE_TREE_LEAVES_ITEM = register("shadow_stellar_jungle_tree_leaves", new BlockItem(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES, new Item.Settings()));
    public static final Item HOLLOW_SHADOW_PINE_LEAVES_ITEM = register("hollow_shadow_pine_leaves", new BlockItem(AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES, new Item.Settings()));
    public static final Item HOLLOW_PURPLE_SHADOW_PINE_LEAVES_ITEM = register("hollow_purple_shadow_pine_leaves", new BlockItem(AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES, new Item.Settings()));

    //ladder
    public static final Item SKY_LADDER_ITEM = register("sky_ladder", new BlockItem(AerialHellBlocks.SKY_LADDER, new Item.Settings()));

    //natural blocks and items
    public static final Item STELLAR_STONE_ITEM = register("stellar_stone", new BlockItem(AerialHellBlocks.STELLAR_STONE, new Item.Settings()));
    public static final Item STELLAR_COBBLESTONE_ITEM = register("stellar_cobblestone", new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE, new Item.Settings()));
    public static final Item MOSSY_STELLAR_STONE_ITEM = register("mossy_stellar_stone", new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE, new Item.Settings()));
    public static final Item MOSSY_STELLAR_COBBLESTONE_ITEM = register("mossy_stellar_cobblestone", new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE, new Item.Settings()));
    public static final Item STELLAR_CLAY_ITEM = register("stellar_clay", new BlockItem(AerialHellBlocks.STELLAR_CLAY, new Item.Settings()));
    public static final Item STELLAR_STONE_BRICKS_ITEM = register("stellar_stone_bricks", new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS, new Item.Settings()));
    public static final Item GLAUCOPHANITE_ITEM = register("glaucophanite", new BlockItem(AerialHellBlocks.GLAUCOPHANITE, new Item.Settings()));
    public static final Item POLISHED_GLAUCOPHANITE_ITEM = register("polished_glaucophanite", new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE, new Item.Settings()));
    public static final Item SHADOW_STONE_ITEM = register("shadow_stone", new BlockItem(AerialHellBlocks.SHADOW_STONE, new Item.Settings()));

    //crystal
    public static final Item CRYSTAL_BLOCK_ITEM = register("crystal_block", new BlockItem(AerialHellBlocks.CRYSTAL_BLOCK, new Item.Settings()));
    public static final Item CRYSTAL_BRICKS_ITEM = register("crystal_bricks", new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS, new Item.Settings()));
    public static final Item CRYSTAL_BRICKS_SLAB_ITEM = register("crystal_bricks_slab", new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_SLAB, new Item.Settings()));
    public static final Item CRYSTAL_BRICKS_STAIRS_ITEM = register("crystal_bricks_stairs", new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_STAIRS, new Item.Settings()));
    public static final Item CRYSTAL_BRICKS_WALL_ITEM = register("crystal_bricks_wall", new BlockItem(AerialHellBlocks.CRYSTAL_BRICKS_WALL, new Item.Settings()));
    public static final Item STELLAR_STONE_CRYSTAL_BLOCK_ITEM = register("stellar_stone_crystal_block", new BlockItem(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK, new Item.Settings()));
    public static final Item SHADOW_CRYSTAL_BLOCK_ITEM = register("shadow_crystal_block", new BlockItem(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item CRYSTALLIZED_LEAVES_ITEM = register("crystallized_leaves", new BlockItem(AerialHellBlocks.CRYSTALLIZED_LEAVES, new Item.Settings()));
    public static final Item CRYSTAL = register("crystal", new Item(new Item.Settings()));
    public static final Item STELLAR_STONE_CRYSTAL = register("stellar_stone_crystal", new Item(new Item.Settings()));
    public static final Item SHADOW_CRYSTAL = register("shadow_crystal", new Item(new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));

    //glass and glass pane
    public static final Item SLIPPERY_SAND_GLASS_ITEM = register("slippery_sand_glass", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_GLASS, new Item.Settings()));
    public static final Item RED_SLIPPERY_SAND_GLASS_ITEM = register("red_slippery_sand_glass", new BlockItem(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS, new Item.Settings()));
    public static final Item BLACK_SLIPPERY_SAND_GLASS_ITEM = register("black_slippery_sand_glass", new BlockItem(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS, new Item.Settings()));
    public static final Item BLUE_SLIPPERY_SAND_GLASS_ITEM = register("blue_slippery_sand_glass", new BlockItem(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS, new Item.Settings()));
    public static final Item GREEN_SLIPPERY_SAND_GLASS_ITEM = register("green_slippery_sand_glass", new BlockItem(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS, new Item.Settings()));
    public static final Item SLIPPERY_SAND_GLASS_PANE_ITEM = register("slippery_sand_glass_pane", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_GLASS_PANE, new Item.Settings()));
    public static final Item RED_SLIPPERY_SAND_GLASS_PANE_ITEM = register("red_slippery_sand_glass_pane", new BlockItem(AerialHellBlocks.RED_SLIPPERY_SAND_GLASS_PANE, new Item.Settings()));
    public static final Item BLACK_SLIPPERY_SAND_GLASS_PANE_ITEM = register("black_slippery_sand_glass_pane", new BlockItem(AerialHellBlocks.BLACK_SLIPPERY_SAND_GLASS_PANE, new Item.Settings()));
    public static final Item BLUE_SLIPPERY_SAND_GLASS_PANE_ITEM = register("blue_slippery_sand_glass_pane", new BlockItem(AerialHellBlocks.BLUE_SLIPPERY_SAND_GLASS_PANE, new Item.Settings()));
    public static final Item GREEN_SLIPPERY_SAND_GLASS_PANE_ITEM = register("green_slippery_sand_glass_pane", new BlockItem(AerialHellBlocks.GREEN_SLIPPERY_SAND_GLASS_PANE, new Item.Settings()));

    //ghost boat
    public static final Item GHOST_BOAT_PLANKS_ITEM = register("ghost_boat_planks", new BlockItem(AerialHellBlocks.GHOST_BOAT_PLANKS, new Item.Settings()));
    public static final Item GHOST_BOAT_LOG_ITEM = register("ghost_boat_log", new BlockItem(AerialHellBlocks.GHOST_BOAT_LOG, new Item.Settings()));
    public static final Item GHOST_BOAT_WOOD_ITEM = register("ghost_boat_wood", new BlockItem(AerialHellBlocks.GHOST_BOAT_WOOD, new Item.Settings()));
    public static final Item GHOST_BOAT_SLAB_ITEM = register("ghost_boat_slab", new BlockItem(AerialHellBlocks.GHOST_BOAT_SLAB, new Item.Settings()));
    public static final Item GHOST_BOAT_STAIRS_ITEM = register("ghost_boat_stairs", new BlockItem(AerialHellBlocks.GHOST_BOAT_STAIRS, new Item.Settings()));
    public static final Item GHOST_BOAT_FENCE_ITEM = register("ghost_boat_fence", new BlockItem(AerialHellBlocks.GHOST_BOAT_FENCE, new Item.Settings()));
    public static final Item GHOST_BOAT_GATE_ITEM = register("ghost_boat_gate", new BlockItem(AerialHellBlocks.GHOST_BOAT_GATE, new Item.Settings()));
    public static final Item GHOST_BOAT_DOOR_ITEM = register("ghost_boat_door", new BlockItem(AerialHellBlocks.GHOST_BOAT_DOOR, new Item.Settings()));
    public static final Item GHOST_BOAT_TRAPDOOR_ITEM = register("ghost_boat_trapdoor", new BlockItem(AerialHellBlocks.GHOST_BOAT_TRAPDOOR, new Item.Settings()));
    public static final Item GHOST_BOAT_CHEST_ITEM = register("ghost_boat_chest", new BlockItem(AerialHellBlocks.GHOST_BOAT_CHEST, new Item.Settings()));
    public static final Item GHOST_BOAT_WOOL_ITEM = register("ghost_boat_wool", new BlockItem(AerialHellBlocks.GHOST_BOAT_WOOL, new Item.Settings()));
    public static final Item GHOST_STELLAR_COBBLESTONE_ITEM = register("ghost_stellar_cobblestone", new BlockItem(AerialHellBlocks.GHOST_STELLAR_COBBLESTONE, new Item.Settings()));
    public static final Item GHOST_RUBY_BLOCK_ITEM = register("ghost_ruby_block", new BlockItem(AerialHellBlocks.GHOST_RUBY_BLOCK, new Item.Settings()));
    public static final Item GHOST_FLUORITE_BLOCK_ITEM = register("ghost_fluorite_block", new BlockItem(AerialHellBlocks.GHOST_FLUORITE_BLOCK, new Item.Settings()));
    public static final Item GHOST_AZURITE_BLOCK_ITEM = register("ghost_azurite_block", new BlockItem(AerialHellBlocks.GHOST_AZURITE_BLOCK, new Item.Settings()));
    public static final Item GHOST_GOLD_BLOCK_ITEM = register("ghost_gold_block", new BlockItem(AerialHellBlocks.GHOST_GOLD_BLOCK, new Item.Settings()));
    public static final Item GHOST_BOAT_BARREL_ITEM = register("ghost_boat_barrel", new BlockItem(AerialHellBlocks.GHOST_BOAT_BARREL, new Item.Settings()));
    public static final Item GHOST_BOAT_CRAFTING_TABLE_ITEM = register("ghost_boat_crafting_table", new BlockItem(AerialHellBlocks.GHOST_BOAT_CRAFTING_TABLE, new Item.Settings()));
    public static final Item GHOST_BOAT_VINE_ROPE_SPOOL_ITEM = register("ghost_boat_vine_rope_spool", new BlockItem(AerialHellBlocks.GHOST_BOAT_VINE_ROPE_SPOOL, new Item.Settings()));
    public static final Item GHOST_LANTERN_ITEM = register("ghost_lantern", new BlockItem(AerialHellBlocks.GHOST_LANTERN, new Item.Settings()));

    //reactors
    public static final Item WEAK_LIGHT_REACTOR_ITEM = register("weak_light_reactor", new WithInformationBlockItem(AerialHellBlocks.WEAK_LIGHT_REACTOR, new Item.Settings().rarity(AerialHellRarities.VIBRANT)));
    public static final Item HIGH_POWER_LIGHT_REACTOR_ITEM = register("high_power_light_reactor", new WithInformationBlockItem(AerialHellBlocks.HIGH_POWER_LIGHT_REACTOR, new Item.Settings().rarity(AerialHellRarities.VIBRANT)));
    public static final Item WEAK_SHADOW_REACTOR_ITEM = register("weak_shadow_reactor", new WithInformationBlockItem(AerialHellBlocks.WEAK_SHADOW_REACTOR, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item HIGH_POWER_SHADOW_REACTOR_ITEM = register("high_power_shadow_reactor", new WithInformationBlockItem(AerialHellBlocks.HIGH_POWER_SHADOW_REACTOR, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));

    public static final Item BROKEN_WEAK_LIGHT_REACTOR_ITEM = register("broken_weak_light_reactor", new WithInformationBlockItem(AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR, new Item.Settings().rarity(AerialHellRarities.VIBRANT)));
    public static final Item BROKEN_HIGH_POWER_LIGHT_REACTOR_ITEM = register("broken_high_power_light_reactor", new WithInformationBlockItem(AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR, new Item.Settings().rarity(AerialHellRarities.VIBRANT)));
    public static final Item BROKEN_WEAK_SHADOW_REACTOR_ITEM = register("broken_weak_shadow_reactor", new WithInformationBlockItem(AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item BROKEN_HIGH_POWER_SHADOW_REACTOR_ITEM = register("broken_high_power_shadow_reactor", new WithInformationBlockItem(AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));

    //solid_ethers
    public static final Item WHITE_SOLID_ETHER_ITEM = register("white_solid_ether", new BlockItem(AerialHellBlocks.WHITE_SOLID_ETHER, new Item.Settings()));
    public static final Item BLUE_SOLID_ETHER_ITEM = register("blue_solid_ether", new BlockItem(AerialHellBlocks.BLUE_SOLID_ETHER, new Item.Settings()));
    public static final Item GOLDEN_SOLID_ETHER_ITEM = register("golden_solid_ether", new BlockItem(AerialHellBlocks.GOLDEN_SOLID_ETHER, new Item.Settings()));
    public static final Item GREEN_SOLID_ETHER_ITEM = register("green_solid_ether", new BlockItem(AerialHellBlocks.GREEN_SOLID_ETHER, new Item.Settings()));
    public static final Item PURPLE_SOLID_ETHER_ITEM = register("purple_solid_ether", new BlockItem(AerialHellBlocks.PURPLE_SOLID_ETHER, new Item.Settings()));

    //dungeons blocks
    public static final Item MUD_BRICKS_ITEM = register("mud_bricks", new BlockItem(AerialHellBlocks.MUD_BRICKS, new Item.Settings()));
    public static final Item CRACKED_MUD_BRICKS_ITEM = register("cracked_mud_bricks", new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS, new Item.Settings()));
    public static final Item MOSSY_MUD_BRICKS_ITEM = register("mossy_mud_bricks", new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS, new Item.Settings()));
    public static final Item CHISELED_MUD_BRICKS_ITEM = register("chiseled_mud_bricks", new BlockItem(AerialHellBlocks.CHISELED_MUD_BRICKS, new Item.Settings()));
    public static final Item LIGHT_MUD_BRICKS_ITEM = register("light_mud_bricks", new BlockItem(AerialHellBlocks.LIGHT_MUD_BRICKS, new Item.Settings()));
    public static final Item CRACKED_LIGHT_MUD_BRICKS_ITEM = register("cracked_light_mud_bricks", new BlockItem(AerialHellBlocks.CRACKED_LIGHT_MUD_BRICKS, new Item.Settings()));
    public static final Item LUNATIC_STONE_ITEM = register("lunatic_stone", new BlockItem(AerialHellBlocks.LUNATIC_STONE, new Item.Settings()));
    public static final Item DARK_LUNATIC_STONE_ITEM = register("dark_lunatic_stone", new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE, new Item.Settings()));
    public static final Item ROOF_LUNATIC_STONE_ITEM = register("roof_lunatic_stone", new BlockItem(AerialHellBlocks.ROOF_LUNATIC_STONE, new Item.Settings()));
    public static final Item CRACKED_LUNATIC_STONE_ITEM = register("cracked_lunatic_stone", new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE, new Item.Settings()));
    public static final Item CHISELED_LUNATIC_STONE_ITEM = register("chiseled_lunatic_stone", new BlockItem(AerialHellBlocks.CHISELED_LUNATIC_STONE, new Item.Settings()));
    public static final Item LIGHT_LUNATIC_STONE_ITEM = register("light_lunatic_stone", new BlockItem(AerialHellBlocks.LIGHT_LUNATIC_STONE, new Item.Settings()));
    public static final Item ROOF_LIGHT_LUNATIC_STONE_ITEM = register("roof_light_lunatic_stone", new BlockItem(AerialHellBlocks.ROOF_LIGHT_LUNATIC_STONE, new Item.Settings()));
    public static final Item CRACKED_LIGHT_LUNATIC_STONE_ITEM = register("cracked_light_lunatic_stone", new BlockItem(AerialHellBlocks.CRACKED_LIGHT_LUNATIC_STONE, new Item.Settings()));
    public static final Item SHADOW_CATACOMBS_BRICKS_ITEM = register("shadow_catacombs_bricks", new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS, new Item.Settings()));
    public static final Item CRACKED_SHADOW_CATACOMBS_BRICKS_ITEM = register("cracked_shadow_catacombs_bricks", new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS, new Item.Settings()));
    public static final Item MOSSY_SHADOW_CATACOMBS_BRICKS_ITEM = register("mossy_shadow_catacombs_bricks", new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS, new Item.Settings()));
    public static final Item CHISELED_SHADOW_CATACOMBS_BRICKS_ITEM = register("chiseled_shadow_catacombs_bricks", new BlockItem(AerialHellBlocks.CHISELED_SHADOW_CATACOMBS_BRICKS, new Item.Settings()));
    public static final Item BONE_SHADOW_CATACOMBS_BRICKS_ITEM = register("bone_shadow_catacombs_bricks", new BlockItem(AerialHellBlocks.BONE_SHADOW_CATACOMBS_BRICKS, new Item.Settings()));
    public static final Item SKULL_SHADOW_CATACOMBS_BRICKS_ITEM = register("skull_shadow_catacombs_bricks", new BlockItem(AerialHellBlocks.SKULL_SHADOW_CATACOMBS_BRICKS, new Item.Settings()));
    public static final Item LIGHT_SHADOW_CATACOMBS_BRICKS_ITEM = register("light_shadow_catacombs_bricks", new BlockItem(AerialHellBlocks.LIGHT_SHADOW_CATACOMBS_BRICKS, new Item.Settings()));
    public static final Item CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS_ITEM = register("cracked_light_shadow_catacombs_bricks", new BlockItem(AerialHellBlocks.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS, new Item.Settings()));
    public static final Item GOLDEN_NETHER_BRICKS_ITEM = register("golden_nether_bricks", new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS, new Item.Settings()));
    public static final Item CRACKED_GOLDEN_NETHER_BRICKS_ITEM = register("cracked_golden_nether_bricks", new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS, new Item.Settings()));
    public static final Item CHISELED_GOLDEN_NETHER_BRICKS_ITEM = register("chiseled_golden_nether_bricks", new BlockItem(AerialHellBlocks.CHISELED_GOLDEN_NETHER_BRICKS, new Item.Settings()));
    public static final Item LIGHT_GOLDEN_NETHER_BRICKS_ITEM = register("light_golden_nether_bricks", new BlockItem(AerialHellBlocks.LIGHT_GOLDEN_NETHER_BRICKS, new Item.Settings()));
    public static final Item CRACKED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM = register("cracked_light_golden_nether_bricks", new BlockItem(AerialHellBlocks.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS, new Item.Settings()));
    public static final Item LUNATIC_PILLAR_ITEM = register("lunatic_pillar", new BlockItem(AerialHellBlocks.LUNATIC_PILLAR, new Item.Settings()));
    public static final Item LUNATIC_PILLAR_TOP_ITEM = register("lunatic_pillar_top", new BlockItem(AerialHellBlocks.LUNATIC_PILLAR_TOP, new Item.Settings()));
    public static final Item VOLUCITE_STONE_ITEM = register("volucite_stone", new BlockItem(AerialHellBlocks.VOLUCITE_STONE, new Item.Settings()));
    public static final Item CRACKED_VOLUCITE_STONE_ITEM = register("cracked_volucite_stone", new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE, new Item.Settings()));
    public static final Item CHISELED_VOLUCITE_STONE_ITEM = register("chiseled_volucite_stone", new BlockItem(AerialHellBlocks.CHISELED_VOLUCITE_STONE, new Item.Settings()));
    public static final Item LIGHT_VOLUCITE_STONE_ITEM = register("light_volucite_stone", new BlockItem(AerialHellBlocks.LIGHT_VOLUCITE_STONE, new Item.Settings()));
    public static final Item CRACKED_LIGHT_VOLUCITE_STONE_ITEM = register("cracked_light_volucite_stone", new BlockItem(AerialHellBlocks.CRACKED_LIGHT_VOLUCITE_STONE, new Item.Settings()));

    //dungeon cores
    public static final Item MUD_DUNGEON_CORE_ITEM = register("mud_dungeon_core", new BlockItem(AerialHellBlocks.MUD_DUNGEON_CORE, new Item.Settings()));
    public static final Item LUNATIC_DUNGEON_CORE_ITEM = register("lunatic_dungeon_core", new BlockItem(AerialHellBlocks.LUNATIC_DUNGEON_CORE, new Item.Settings()));
    public static final Item SHADOW_CATACOMBS_DUNGEON_CORE_ITEM = register("shadow_catacombs_dungeon_core", new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_DUNGEON_CORE, new Item.Settings()));
    public static final Item GOLDEN_NETHER_DUNGEON_CORE_ITEM = register("golden_nether_dungeon_core", new BlockItem(AerialHellBlocks.GOLDEN_NETHER_DUNGEON_CORE, new Item.Settings()));
    public static final Item VOLUCITE_DUNGEON_CORE_ITEM = register("volucite_dungeon_core", new BlockItem(AerialHellBlocks.VOLUCITE_DUNGEON_CORE, new Item.Settings()));

    //dungeons slabs, stairs & walls
    public static final Item MUD_BRICKS_SLAB_ITEM = register("mud_bricks_slab", new BlockItem(AerialHellBlocks.MUD_BRICKS_SLAB, new Item.Settings()));
    public static final Item MUD_BRICKS_STAIRS_ITEM = register("mud_bricks_stairs", new BlockItem(AerialHellBlocks.MUD_BRICKS_STAIRS, new Item.Settings()));
    public static final Item MUD_BRICKS_WALL_ITEM = register("mud_bricks_wall", new BlockItem(AerialHellBlocks.MUD_BRICKS_WALL, new Item.Settings()));
    public static final Item CRACKED_MUD_BRICKS_SLAB_ITEM = register("cracked_mud_bricks_slab", new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_SLAB, new Item.Settings()));
    public static final Item CRACKED_MUD_BRICKS_STAIRS_ITEM = register("cracked_mud_bricks_stairs", new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_STAIRS, new Item.Settings()));
    public static final Item CRACKED_MUD_BRICKS_WALL_ITEM = register("cracked_mud_bricks_wall", new BlockItem(AerialHellBlocks.CRACKED_MUD_BRICKS_WALL, new Item.Settings()));
    public static final Item MOSSY_MUD_BRICKS_SLAB_ITEM = register("mossy_mud_bricks_slab", new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_SLAB, new Item.Settings()));
    public static final Item MOSSY_MUD_BRICKS_STAIRS_ITEM = register("mossy_mud_bricks_stairs", new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_STAIRS, new Item.Settings()));
    public static final Item MOSSY_MUD_BRICKS_WALL_ITEM = register("mossy_mud_bricks_wall", new BlockItem(AerialHellBlocks.MOSSY_MUD_BRICKS_WALL, new Item.Settings()));
    public static final Item VOLUCITE_STONE_SLAB_ITEM = register("volucite_stone_slab", new BlockItem(AerialHellBlocks.VOLUCITE_STONE_SLAB, new Item.Settings()));
    public static final Item VOLUCITE_STONE_STAIRS_ITEM = register("volucite_stone_stairs", new BlockItem(AerialHellBlocks.VOLUCITE_STONE_STAIRS, new Item.Settings()));
    public static final Item VOLUCITE_STONE_WALL_ITEM = register("volucite_stone_wall", new BlockItem(AerialHellBlocks.VOLUCITE_STONE_WALL, new Item.Settings()));
    public static final Item CRACKED_VOLUCITE_STONE_SLAB_ITEM = register("cracked_volucite_stone_slab", new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_SLAB, new Item.Settings()));
    public static final Item CRACKED_VOLUCITE_STONE_STAIRS_ITEM = register("cracked_volucite_stone_stairs", new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_STAIRS, new Item.Settings()));
    public static final Item CRACKED_VOLUCITE_STONE_WALL_ITEM = register("cracked_volucite_stone_wall", new BlockItem(AerialHellBlocks.CRACKED_VOLUCITE_STONE_WALL, new Item.Settings()));
    public static final Item LUNATIC_STONE_SLAB_ITEM = register("lunatic_stone_slab", new BlockItem(AerialHellBlocks.LUNATIC_STONE_SLAB, new Item.Settings()));
    public static final Item LUNATIC_STONE_STAIRS_ITEM = register("lunatic_stone_stairs", new BlockItem(AerialHellBlocks.LUNATIC_STONE_STAIRS, new Item.Settings()));
    public static final Item LUNATIC_STONE_WALL_ITEM = register("lunatic_stone_wall", new BlockItem(AerialHellBlocks.LUNATIC_STONE_WALL, new Item.Settings()));
    public static final Item DARK_LUNATIC_STONE_SLAB_ITEM = register("dark_lunatic_stone_slab", new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_SLAB, new Item.Settings()));
    public static final Item DARK_LUNATIC_STONE_STAIRS_ITEM = register("dark_lunatic_stone_stairs", new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_STAIRS, new Item.Settings()));
    public static final Item DARK_LUNATIC_STONE_WALL_ITEM = register("dark_lunatic_stone_wall", new BlockItem(AerialHellBlocks.DARK_LUNATIC_STONE_WALL, new Item.Settings()));
    public static final Item CRACKED_LUNATIC_STONE_SLAB_ITEM = register("cracked_lunatic_stone_slab", new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_SLAB, new Item.Settings()));
    public static final Item CRACKED_LUNATIC_STONE_STAIRS_ITEM = register("cracked_lunatic_stone_stairs", new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_STAIRS, new Item.Settings()));
    public static final Item CRACKED_LUNATIC_STONE_WALL_ITEM = register("cracked_lunatic_stone_wall", new BlockItem(AerialHellBlocks.CRACKED_LUNATIC_STONE_WALL, new Item.Settings()));
    public static final Item SHADOW_CATACOMBS_BRICKS_SLAB_ITEM = register("shadow_catacombs_bricks_slab", new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_SLAB, new Item.Settings()));
    public static final Item SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM = register("shadow_catacombs_bricks_stairs", new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_STAIRS, new Item.Settings()));
    public static final Item SHADOW_CATACOMBS_BRICKS_WALL_ITEM = register("shadow_catacombs_bricks_wall", new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BRICKS_WALL, new Item.Settings()));
    public static final Item CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM = register("cracked_shadow_catacombs_bricks_slab", new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB, new Item.Settings()));
    public static final Item CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM = register("cracked_shadow_catacombs_bricks_stairs", new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS, new Item.Settings()));
    public static final Item CRACKED_SHADOW_CATACOMBS_BRICKS_WALL_ITEM = register("cracked_shadow_catacombs_bricks_wall", new BlockItem(AerialHellBlocks.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL, new Item.Settings()));
    public static final Item MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM = register("mossy_shadow_catacombs_bricks_slab", new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB, new Item.Settings()));
    public static final Item MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM = register("mossy_shadow_catacombs_bricks_stairs", new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS, new Item.Settings()));
    public static final Item MOSSY_SHADOW_CATACOMBS_BRICKS_WALL_ITEM = register("mossy_shadow_catacombs_bricks_wall", new BlockItem(AerialHellBlocks.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL, new Item.Settings()));
    public static final Item SHADOW_BARS_ITEM = register("shadow_bars", new BlockItem(AerialHellBlocks.SHADOW_BARS, new Item.Settings()));
    public static final Item GOLDEN_NETHER_BRICKS_SLAB_ITEM = register("golden_nether_bricks_slab", new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_SLAB, new Item.Settings()));
    public static final Item GOLDEN_NETHER_BRICKS_STAIRS_ITEM = register("golden_nether_bricks_stairs", new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_STAIRS, new Item.Settings()));
    public static final Item GOLDEN_NETHER_BRICKS_WALL_ITEM = register("golden_nether_bricks_wall", new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BRICKS_WALL, new Item.Settings()));
    public static final Item CRACKED_GOLDEN_NETHER_BRICKS_SLAB_ITEM = register("cracked_golden_nether_bricks_slab", new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_SLAB, new Item.Settings()));
    public static final Item CRACKED_GOLDEN_NETHER_BRICKS_STAIRS_ITEM = register("cracked_golden_nether_bricks_stairs", new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS, new Item.Settings()));
    public static final Item CRACKED_GOLDEN_NETHER_BRICKS_WALL_ITEM = register("cracked_golden_nether_bricks_wall", new BlockItem(AerialHellBlocks.CRACKED_GOLDEN_NETHER_BRICKS_WALL, new Item.Settings()));

    //smoky quartz
    public static final Item SMOKY_QUARTZ = register("smoky_quartz", new Item(new Item.Settings()));
    public static final Item SMOKY_QUARTZ_BLOCK_ITEM = register("smoky_quartz_block", new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_BLOCK, new Item.Settings()));
    public static final Item SMOOTH_SMOKY_QUARTZ_ITEM = register("smooth_smoky_quartz", new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ, new Item.Settings()));
    public static final Item CHISELED_SMOKY_QUARTZ_BLOCK_ITEM = register("chiseled_smoky_quartz_block", new BlockItem(AerialHellBlocks.CHISELED_SMOKY_QUARTZ_BLOCK, new Item.Settings()));
    public static final Item SMOKY_QUARTZ_BRICKS_ITEM = register("smoky_quartz_bricks", new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_BRICKS, new Item.Settings()));
    public static final Item SMOKY_QUARTZ_PILLAR_ITEM = register("smoky_quartz_pillar", new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_PILLAR, new Item.Settings()));
    public static final Item SMOKY_QUARTZ_SLAB_ITEM = register("smoky_quartz_slab", new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_SLAB, new Item.Settings()));
    public static final Item SMOOTH_SMOKY_QUARTZ_SLAB_ITEM = register("smooth_smoky_quartz_slab", new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_SLAB, new Item.Settings()));
    public static final Item SMOKY_QUARTZ_STAIRS_ITEM = register("smoky_quartz_stairs", new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_STAIRS, new Item.Settings()));
    public static final Item SMOOTH_SMOKY_QUARTZ_STAIRS_ITEM = register("smooth_smoky_quartz_stairs", new BlockItem(AerialHellBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS, new Item.Settings()));

    //dungeon trapped blocks
    public static final Item TRAPPED_MUD_BRICKS_ITEM = register("trapped_mud_bricks", new BlockItem(AerialHellBlocks.TRAPPED_MUD_BRICKS, new Item.Settings()));
    public static final Item TRAPPED_LIGHT_MUD_BRICKS_ITEM = register("trapped_light_mud_bricks", new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_MUD_BRICKS, new Item.Settings()));
    public static final Item TRAPPED_LUNATIC_STONE_ITEM = register("trapped_lunatic_stone", new BlockItem(AerialHellBlocks.TRAPPED_LUNATIC_STONE, new Item.Settings()));
    public static final Item TRAPPED_LIGHT_LUNATIC_STONE_ITEM = register("trapped_light_lunatic_stone", new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_LUNATIC_STONE, new Item.Settings()));
    public static final Item TRAPPED_GOLDEN_NETHER_BRICKS_ITEM = register("trapped_golden_nether_bricks", new BlockItem(AerialHellBlocks.TRAPPED_GOLDEN_NETHER_BRICKS, new Item.Settings()));
    public static final Item TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM = register("trapped_light_golden_nether_bricks", new BlockItem(AerialHellBlocks.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS, new Item.Settings()));

    //dungeon other blocks, loots
    public static final Item MUD_BONE_BLOCK_ITEM = register("mud_bone_block", new BlockItem(AerialHellBlocks.MUD_BONE_BLOCK, new Item.Settings()));
    public static final Item MUD_BONE_PILE_BLOCK_ITEM = register("mud_bone_pile_block", new BlockItem(AerialHellBlocks.MUD_BONE_PILE_BLOCK, new Item.Settings()));
    public static final Item MUD_BONE = register("mud_bone", new Item(new Item.Settings()));
    public static final Item THORNY_COBWEB_ITEM = register("thorny_cobweb", new BlockItem(AerialHellBlocks.THORNY_COBWEB, new Item.Settings()));
    public static final Item AERIAL_NETHERRACK_ITEM = register("aerial_netherrack", new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK, new Item.Settings()));
    public static final Item AERIAL_NETHERRACK_SLAB_ITEM = register("aerial_netherrack_slab", new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_SLAB, new Item.Settings()));
    public static final Item AERIAL_NETHERRACK_STAIRS_ITEM = register("aerial_netherrack_stairs", new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_STAIRS, new Item.Settings()));
    public static final Item AERIAL_NETHERRACK_WALL_ITEM = register("aerial_netherrack_wall", new BlockItem(AerialHellBlocks.AERIAL_NETHERRACK_WALL, new Item.Settings()));

    //dungeon bookshelfs
    public static final Item MUD_BOOKSHELF_ITEM = register("mud_bookshelf", new BlockItem(AerialHellBlocks.MUD_BOOKSHELF, new Item.Settings()));
    public static final Item LUNATIC_BOOKSHELF_ITEM = register("lunatic_bookshelf", new BlockItem(AerialHellBlocks.LUNATIC_BOOKSHELF, new Item.Settings()));
    public static final Item GOLDEN_NETHER_BOOKSHELF_ITEM = register("golden_nether_bookshelf", new BlockItem(AerialHellBlocks.GOLDEN_NETHER_BOOKSHELF, new Item.Settings()));
    public static final Item SHADOW_CATACOMBS_BOOKSHELF_ITEM = register("shadow_catacombs_bookshelf", new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_BOOKSHELF, new Item.Settings()));
    public static final Item VOLUCITE_BOOKSHELF_ITEM = register("volucite_bookshelf", new BlockItem(AerialHellBlocks.VOLUCITE_BOOKSHELF, new Item.Settings()));

    //glyph blocks
    public static final Item MUD_GLYPH_BLOCK_ITEM = register("mud_glyph_block", new BlockItem(AerialHellBlocks.MUD_GLYPH_BLOCK, new Item.Settings()));
    public static final Item LUNATIC_GLYPH_BLOCK_ITEM = register("lunatic_glyph_block", new BlockItem(AerialHellBlocks.LUNATIC_GLYPH_BLOCK, new Item.Settings()));
    public static final Item GOLDEN_NETHER_PRISON_GLYPH_BLOCK_ITEM = register("golden_nether_prison_glyph_block", new BlockItem(AerialHellBlocks.GOLDEN_NETHER_PRISON_GLYPH_BLOCK, new Item.Settings()));
    public static final Item VOLUCITE_GLYPH_BLOCK_ITEM = register("volucite_glyph_block", new BlockItem(AerialHellBlocks.VOLUCITE_GLYPH_BLOCK, new Item.Settings()));
    public static final Item SHADOW_CATACOMBS_GLYPH_BLOCK_ITEM = register("shadow_catacombs_glyph_block", new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_GLYPH_BLOCK, new Item.Settings()));

    //trophies
    public static final Item MUD_CYCLE_MAGE_TROPHY_ITEM = register("mud_cycle_mage_trophy", new BlockItem(AerialHellBlocks.MUD_CYCLE_MAGE_TROPHY, new Item.Settings()));
    public static final Item LUNAR_PRIEST_TROPHY_ITEM = register("lunar_priest_trophy", new BlockItem(AerialHellBlocks.LUNAR_PRIEST_TROPHY, new Item.Settings()));
    public static final Item LILITH_TROPHY_ITEM = register("lilith_trophy", new BlockItem(AerialHellBlocks.LILITH_TROPHY, new Item.Settings()));
    public static final Item CHAINED_GOD_TROPHY_ITEM = register("chained_god_trophy", new BlockItem(AerialHellBlocks.CHAINED_GOD_TROPHY, new Item.Settings()));

    //ores
    public static final Item IRON_STELLAR_ORE_ITEM = register("iron_stellar_ore", new BlockItem(AerialHellBlocks.IRON_STELLAR_ORE, new Item.Settings()));
    public static final Item GOLD_STELLAR_ORE_ITEM = register("gold_stellar_ore", new BlockItem(AerialHellBlocks.GOLD_STELLAR_ORE, new Item.Settings()));
    public static final Item DIAMOND_STELLAR_ORE_ITEM = register("diamond_stellar_ore", new BlockItem(AerialHellBlocks.DIAMOND_STELLAR_ORE, new Item.Settings()));
    public static final Item FLUORITE_ORE_ITEM = register("fluorite_ore", new BlockItem(AerialHellBlocks.FLUORITE_ORE, new Item.Settings()));
    public static final Item MAGMATIC_GEL_ORE_ITEM = register("magmatic_gel_ore", new BlockItem(AerialHellBlocks.MAGMATIC_GEL_ORE, new Item.Settings()));
    public static final Item RUBY_ORE_ITEM = register("ruby_ore", new BlockItem(AerialHellBlocks.RUBY_ORE, new Item.Settings()));
    public static final Item AZURITE_ORE_ITEM = register("azurite_ore", new BlockItem(AerialHellBlocks.AZURITE_ORE, new Item.Settings()));
    public static final Item VOLUCITE_ORE_ITEM = register("volucite_ore", new BlockItem(AerialHellBlocks.VOLUCITE_ORE, new Item.Settings().rarity(AerialHellRarities.VIBRANT)));
    public static final Item OBSIDIAN_ORE_ITEM = register("obsidian_ore", new BlockItem(AerialHellBlocks.OBSIDIAN_ORE, new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item SMOKY_QUARTZ_ORE_ITEM = register("smoky_quartz_ore", new BlockItem(AerialHellBlocks.SMOKY_QUARTZ_ORE, new Item.Settings().rarity(Rarity.EPIC)));

    public static final Item RAW_RUBY_BLOCK_ITEM = register("raw_ruby_block", new BlockItem(AerialHellBlocks.RAW_RUBY_BLOCK, new Item.Settings()));
    public static final Item RAW_AZURITE_BLOCK_ITEM = register("raw_azurite_crystal_block", new BlockItem(AerialHellBlocks.RAW_AZURITE_BLOCK, new Item.Settings()));
    public static final Item RAW_VOLUCITE_BLOCK_ITEM = register("raw_volucite_block", new BlockItem(AerialHellBlocks.RAW_VOLUCITE_BLOCK, new Item.Settings()));

    public static final Item RAW_RUBY = register("raw_ruby", new Item(new Item.Settings()));
    public static final Item RAW_AZURITE = register("raw_azurite_crystal", new Item(new Item.Settings()));
    public static final Item RAW_VOLUCITE = register("raw_volucite", new Item(new Item.Settings()));

    public static final Item FLUORITE_BLOCK_ITEM = register("fluorite_block", new BlockItem(AerialHellBlocks.FLUORITE_BLOCK, new Item.Settings()));
    public static final Item MAGMATIC_GEL_BLOCK_ITEM = register("magmatic_gel_block", new BlockItem(AerialHellBlocks.MAGMATIC_GEL_BLOCK, new Item.Settings()));
    public static final Item RUBY_BLOCK_ITEM = register("ruby_block", new BlockItem(AerialHellBlocks.RUBY_BLOCK, new Item.Settings()));
    public static final Item AZURITE_BLOCK_ITEM = register("azurite_block", new BlockItem(AerialHellBlocks.AZURITE_BLOCK, new Item.Settings()));
    public static final Item VOLUCITE_BLOCK_ITEM = register("volucite_block", new BlockItem(AerialHellBlocks.VOLUCITE_BLOCK, new Item.Settings()));

    public static final Item FLUORITE = register("fluorite", new Item(new Item.Settings()));
    public static final Item MAGMATIC_GEL = register("magmatic_gel", new Item(new Item.Settings()));
    public static final Item RUBY = register("ruby", new Item(new Item.Settings()));
    public static final Item AZURITE_CRYSTAL = register("azurite_crystal", new Item(new Item.Settings()));
    public static final Item VOLUCITE_VIBRANT = register("volucite_vibrant", new VoluciteVibrantItem(new Item.Settings().rarity(AerialHellRarities.VIBRANT)));

    public static final Item OVERHEATED_RUBY = register("overheated_ruby", new WithInformationItem(new Item.Settings()));
    public static final Item OVERHEATED_VOLUCITE = register("overheated_volucite", new WithInformationItem(new Item.Settings()));

    //legendary ores
    public static final Item ARSONIST_INGOT = register("arsonist_ingot", new Item(new Item.Settings().rarity(AerialHellRarities.LEGENDARY).fireproof()));
    public static final Item LUNATIC_CRYSTAL = register("lunatic_crystal", new Item(new Item.Settings().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item OBSIDIAN_SHARD = register("obsidian_shard", new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item CURSED_CRYSAL = register("cursed_crystal", new Item(new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item ARSONIST_BLOCK_ITEM = register("arsonist_block", new BlockItem(AerialHellBlocks.ARSONIST_BLOCK, new Item.Settings().rarity(AerialHellRarities.LEGENDARY).fireproof()));
    public static final Item LUNATIC_CRYSTAL_BLOCK_ITEM = register("lunatic_crystal_block", new BlockItem(AerialHellBlocks.LUNATIC_CRYSTAL_BLOCK, new Item.Settings().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item CURSED_CRYSAL_BLOCK_ITEM = register("cursed_crystal_block", new BlockItem(AerialHellBlocks.CURSED_CRYSAL_BLOCK, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));

    //cactus
    public static final Item SKY_CACTUS_ITEM = register("sky_cactus", new BlockItem(AerialHellBlocks.SKY_CACTUS, new Item.Settings()));
    public static final Item SKY_CACTUS_FIBER_PLANKS_ITEM = register("sky_cactus_fiber_planks", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_PLANKS, new Item.Settings()));
    public static final Item SKY_CACTUS_FIBER_BOOKSHELF_ITEM = register("sky_cactus_fiber_bookshelf", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BOOKSHELF, new Item.Settings()));
    public static final Item VIBRANT_SKY_CACTUS_ITEM = register("vibrant_sky_cactus", new BlockItem(AerialHellBlocks.VIBRANT_SKY_CACTUS, new Item.Settings().rarity(AerialHellRarities.VIBRANT)));
    public static final Item VIBRANT_SKY_CACTUS_FIBER_LANTERN_ITEM = register("vibrant_sky_cactus_fiber_lantern", new BlockItem(AerialHellBlocks.VIBRANT_SKY_CACTUS_FIBER_LANTERN, new Item.Settings().rarity(AerialHellRarities.VIBRANT)));

    //bushes
    public static final Item AERIAL_BERRY_SEEDS = register("aerial_berry_seeds", new AliasedBlockItem(AerialHellBlocks.AERIAL_BERRY_BUSH, new Item.Settings()));
    public static final Item VIBRANT_AERIAL_BERRY_SEEDS = register("vibrant_aerial_berry_seeds", new AliasedBlockItem(AerialHellBlocks.VIBRANT_AERIAL_BERRY_BUSH, new Item.Settings().rarity(AerialHellRarities.VIBRANT)));

    //crops
    public static final Item STELLAR_WHEAT_SEEDS = register("stellar_wheat_seeds", new AliasedBlockItem(AerialHellBlocks.STELLAR_WHEAT, new Item.Settings()));
    public static final Item STELLAR_WHEAT_ITEM = register("stellar_wheat", new Item(new Item.Settings()));

    //vertical growing plants
    public static final Item CLIMBING_VINE_ITEM = register("climbing_vine", new BlockItem(AerialHellBlocks.CLIMBING_VINE, new Item.Settings()));
    public static final Item STELLAR_SUGAR_CANE_ITEM = register("stellar_sugar_cane", new BlockItem(AerialHellBlocks.STELLAR_SUGAR_CANE, new Item.Settings()));

    //chorus like
    public static final Item FULL_MOON_FLOWER_ITEM = register("full_moon_flower", new BlockItem(AerialHellBlocks.FULL_MOON_FLOWER, new Item.Settings()));

    //vines
    public static final Item VINE_BLOSSOM = register("vine_blossom", new AliasedBlockItem(AerialHellBlocks.BLOSSOMING_VINES, new Item.Settings()));
    public static final Item LAZULI_ROOTS_ITEM = register("lazuli_roots", new BlockItem(AerialHellBlocks.LAZULI_ROOTS, new Item.Settings()));
    public static final Item STELLAR_ROOTS_ITEM = register("stellar_roots", new BlockItem(AerialHellBlocks.STELLAR_ROOTS, new Item.Settings()));
    public static final Item DEAD_ROOTS_ITEM = register("dead_roots", new BlockItem(AerialHellBlocks.DEAD_ROOTS, new Item.Settings()));
    public static final Item GLOWING_ROOTS_ITEM = register("glowing_roots", new BlockItem(AerialHellBlocks.GLOWING_ROOTS, new Item.Settings()));
    public static final Item SHADOW_GLOWING_ROOTS_ITEM = register("shadow_glowing_roots", new BlockItem(AerialHellBlocks.SHADOW_GLOWING_ROOTS, new Item.Settings()));

    //grass
    public static final Item STELLAR_GRASS_ITEM = register("stellar_grass", new BlockItem(AerialHellBlocks.STELLAR_GRASS, new Item.Settings()));
    public static final Item STELLAR_GRASS_BALL_ITEM = register("stellar_grass_ball", new BlockItem(AerialHellBlocks.STELLAR_GRASS_BALL, new Item.Settings()));
    public static final Item STELLAR_FERN_ITEM = register("stellar_fern", new BlockItem(AerialHellBlocks.STELLAR_FERN, new Item.Settings()));
    public static final Item STELLAR_TALL_GRASS_ITEM = register("stellar_tall_grass", new BlockItem(AerialHellBlocks.STELLAR_TALL_GRASS, new Item.Settings()));
    public static final Item STELLAR_TALL_FERN_ITEM = register("stellar_tall_fern", new BlockItem(AerialHellBlocks.STELLAR_TALL_FERN, new Item.Settings()));
    public static final Item STELLAR_VERY_TALL_GRASS_ITEM = register("stellar_very_tall_grass", new BlockItem(AerialHellBlocks.STELLAR_VERY_TALL_GRASS, new Item.Settings()));
    public static final Item BLUISH_FERN_ITEM = register("bluish_fern", new BlockItem(AerialHellBlocks.BLUISH_FERN, new Item.Settings()));
    public static final Item TALL_BLUISH_FERN_ITEM = register("tall_bluish_fern", new BlockItem(AerialHellBlocks.TALL_BLUISH_FERN, new Item.Settings()));
    public static final Item POLYCHROME_FERN_ITEM = register("polychrome_fern", new BlockItem(AerialHellBlocks.POLYCHROME_FERN, new Item.Settings()));
    public static final Item TALL_POLYCHROME_FERN_ITEM = register("tall_polychrome_fern", new BlockItem(AerialHellBlocks.TALL_POLYCHROME_FERN, new Item.Settings()));
    public static final Item STELLAR_DEAD_BUSH_ITEM = register("stellar_dead_bush", new BlockItem(AerialHellBlocks.STELLAR_DEAD_BUSH, new Item.Settings()));
    public static final Item BRAMBLES_ITEM = register("brambles", new BlockItem(AerialHellBlocks.BRAMBLES, new Item.Settings()));
    public static final Item SHADOW_BRAMBLES_ITEM = register("shadow_brambles", new BlockItem(AerialHellBlocks.SHADOW_BRAMBLES, new Item.Settings()));
    public static final Item SHADOW_GRASS_ITEM = register("shadow_grass", new BlockItem(AerialHellBlocks.SHADOW_GRASS, new Item.Settings()));
    public static final Item SHADOW_GRASS_BALL_ITEM = register("shadow_grass_ball", new BlockItem(AerialHellBlocks.SHADOW_GRASS_BALL, new Item.Settings()));
    public static final Item PURPLISH_STELLAR_GRASS_ITEM = register("purplish_stellar_grass", new BlockItem(AerialHellBlocks.PURPLISH_STELLAR_GRASS, new Item.Settings()));
    public static final Item STELLAR_CLOVERS_ITEM = register("stellar_clovers", new BlockItem(AerialHellBlocks.STELLAR_CLOVERS, new Item.Settings()));
    public static final Item GLOWING_STELLAR_GRASS_ITEM = register("glowing_stellar_grass", new BlockItem(AerialHellBlocks.GLOWING_STELLAR_GRASS, new Item.Settings()));

    //flowers
    public static final Item BLUE_FLOWER_ITEM = register("blue_flower", new BlockItem(AerialHellBlocks.BLUE_FLOWER, new Item.Settings()));
    public static final Item BLACK_ROSE_ITEM = register("black_rose", new BlockItem(AerialHellBlocks.BLACK_ROSE, new Item.Settings()));
    public static final Item BELLFLOWER_ITEM = register("bellflower", new BlockItem(AerialHellBlocks.BELLFLOWER, new Item.Settings()));

    //with gui
    public static final Item OSCILLATOR_ITEM = register("oscillator", new BlockItem(AerialHellBlocks.OSCILLATOR, new Item.Settings()));
    public static final Item FREEZER_ITEM = register("freezer", new BlockItem(AerialHellBlocks.FREEZER, new Item.Settings()));
    public static final Item STELLAR_FURNACE_ITEM = register("stellar_furnace", new BlockItem(AerialHellBlocks.STELLAR_FURNACE, new Item.Settings()));
    public static final Item GHOST_STELLAR_FURNACE_ITEM = register("ghost_stellar_furnace", new BlockItem(AerialHellBlocks.GHOST_STELLAR_FURNACE, new Item.Settings()));

    //chests
    public static final Item AERIAL_TREE_CHEST_ITEM = register("aerial_tree_chest", new BlockItem(AerialHellBlocks.AERIAL_TREE_CHEST, new Item.Settings()));
    public static final Item GOLDEN_BEECH_CHEST_ITEM = register("golden_beech_chest", new BlockItem(AerialHellBlocks.GOLDEN_BEECH_CHEST, new Item.Settings()));
    public static final Item COPPER_PINE_CHEST_ITEM = register("copper_pine_chest", new BlockItem(AerialHellBlocks.COPPER_PINE_CHEST, new Item.Settings()));
    public static final Item LAPIS_ROBINIA_CHEST_ITEM = register("lapis_robinia_chest", new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_CHEST, new Item.Settings()));
    public static final Item SHADOW_PINE_CHEST_ITEM = register("shadow_pine_chest", new BlockItem(AerialHellBlocks.SHADOW_PINE_CHEST, new Item.Settings()));
    public static final Item STELLAR_JUNGLE_TREE_CHEST_ITEM = register("stellar_jungle_tree_chest", new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_CHEST, new Item.Settings()));
    public static final Item SKY_CACTUS_FIBER_CHEST_ITEM = register("sky_cactus_fiber_chest", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CHEST, new Item.Settings()));
    public static final Item GRAY_SHROOM_CHEST_ITEM = register("gray_shroom_chest", new BlockItem(AerialHellBlocks.GRAY_SHROOM_CHEST, new Item.Settings()));
    public static final Item MUD_CHEST_ITEM = register("mud_chest", new BlockItem(AerialHellBlocks.MUD_CHEST, new Item.Settings()));
    public static final Item LUNATIC_CHEST_ITEM = register("lunatic_chest", new BlockItem(AerialHellBlocks.LUNATIC_CHEST, new Item.Settings()));
    public static final Item VOLUCITE_CHEST_ITEM = register("volucite_chest", new BlockItem(AerialHellBlocks.VOLUCITE_CHEST, new Item.Settings()));
    public static final Item SHADOW_CATACOMBS_CHEST_ITEM = register("shadow_catacombs_chest", new BlockItem(AerialHellBlocks.SHADOW_CATACOMBS_CHEST, new Item.Settings()));
    public static final Item GOLDEN_NETHER_CHEST_ITEM = register("golden_nether_chest", new BlockItem(AerialHellBlocks.GOLDEN_NETHER_CHEST, new Item.Settings()));

    //chest mimics
    public static final Item AERIAL_TREE_CHEST_MIMIC_ITEM = register("aerial_tree_chest_mimic", new BlockItem(AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC, new Item.Settings()));
    public static final Item GOLDEN_BEECH_CHEST_MIMIC_ITEM = register("golden_beech_chest_mimic", new BlockItem(AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC, new Item.Settings()));
    public static final Item COPPER_PINE_CHEST_MIMIC_ITEM = register("copper_pine_chest_mimic", new BlockItem(AerialHellBlocks.COPPER_PINE_CHEST_MIMIC, new Item.Settings()));
    public static final Item SKY_CACTUS_FIBER_CHEST_MIMIC_ITEM = register("sky_cactus_fiber_chest_mimic", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC, new Item.Settings()));

    //barrel mimics
    public static final Item SHADOW_PINE_BARREL_MIMIC_ITEM = register("shadow_pine_barrel_mimic", new BlockItem(AerialHellBlocks.SHADOW_PINE_BARREL_MIMIC, new Item.Settings()));

    //fences, bars or walls
    public static final Item AERIAL_TREE_FENCE_ITEM = register("aerial_tree_fence", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_FENCE, new Item.Settings(), 300));
    public static final Item GOLDEN_BEECH_FENCE_ITEM = register("golden_beech_fence", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_FENCE, new Item.Settings(), 300));
    public static final Item COPPER_PINE_FENCE_ITEM = register("copper_pine_fence", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_FENCE, new Item.Settings(), 300));
    public static final Item LAPIS_ROBINIA_FENCE_ITEM = register("lapis_robinia_fence", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_FENCE, new Item.Settings(), 300));
    public static final Item SHADOW_PINE_FENCE_ITEM = register("shadow_pine_fence", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_FENCE, new Item.Settings(), 300));
    public static final Item STELLAR_JUNGLE_TREE_FENCE_ITEM = register("stellar_jungle_tree_fence", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_FENCE, new Item.Settings(), 300));
    public static final Item SKY_CACTUS_FIBER_FENCE_ITEM = register("sky_cactus_fiber_fence", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_FENCE, new Item.Settings()));
    public static final Item GRAY_SHROOM_FENCE_ITEM = register("gray_shroom_fence", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_FENCE, new Item.Settings(), 100));
    public static final Item RUBY_BARS_ITEM = register("ruby_bars", new BlockItem(AerialHellBlocks.RUBY_BARS, new Item.Settings()));
    public static final Item STELLAR_STONE_WALL_ITEM = register("stellar_stone_wall", new BlockItem(AerialHellBlocks.STELLAR_STONE_WALL, new Item.Settings()));
    public static final Item STELLAR_COBBLESTONE_WALL_ITEM = register("stellar_cobblestone_wall", new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_WALL, new Item.Settings()));
    public static final Item STELLAR_STONE_BRICKS_WALL_ITEM = register("stellar_stone_bricks_wall", new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_WALL, new Item.Settings()));
    public static final Item MOSSY_STELLAR_STONE_WALL_ITEM = register("mossy_stellar_stone_wall", new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_WALL, new Item.Settings()));
    public static final Item MOSSY_STELLAR_COBBLESTONE_WALL_ITEM = register("mossy_stellar_cobblestone_wall", new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_WALL, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_WALL_ITEM = register("slippery_sand_stone_wall", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_WALL, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_WALL_ITEM = register("slippery_sand_stone_bricks_wall", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_WALL, new Item.Settings()));
    public static final Item CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL_ITEM = register("cracked_slippery_sand_stone_bricks_wall", new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL, new Item.Settings()));
    public static final Item GLAUCOPHANITE_WALL_ITEM = register("glaucophanite_wall", new BlockItem(AerialHellBlocks.GLAUCOPHANITE_WALL, new Item.Settings()));
    public static final Item POLISHED_GLAUCOPHANITE_WALL_ITEM = register("polished_glaucophanite_wall", new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_WALL, new Item.Settings()));
    public static final Item MAGMATIC_GEL_WALL_ITEM = register("magmatic_gel_wall", new BlockItem(AerialHellBlocks.MAGMATIC_GEL_WALL, new Item.Settings()));

    //gates
    public static final Item AERIAL_TREE_GATE_ITEM = register("aerial_tree_gate", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_GATE, new Item.Settings(), 300));
    public static final Item GOLDEN_BEECH_GATE_ITEM = register("golden_beech_gate", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_GATE, new Item.Settings(), 300));
    public static final Item COPPER_PINE_GATE_ITEM = register("copper_pine_gate", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_GATE, new Item.Settings(), 300));
    public static final Item LAPIS_ROBINIA_GATE_ITEM = register("lapis_robinia_gate", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_GATE, new Item.Settings(), 300));
    public static final Item SHADOW_PINE_GATE_ITEM = register("shadow_pine_gate", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_GATE, new Item.Settings(), 300));
    public static final Item STELLAR_JUNGLE_TREE_GATE_ITEM = register("stellar_jungle_tree_gate", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_GATE, new Item.Settings(), 300));
    public static final Item SKY_CACTUS_FIBER_GATE_ITEM = register("sky_cactus_fiber_gate", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_GATE, new Item.Settings()));
    public static final Item GRAY_SHROOM_GATE_ITEM = register("gray_shroom_gate", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_GATE, new Item.Settings(), 100));

    //doors
    public static final Item AERIAL_TREE_DOOR_ITEM = register("aerial_tree_door", new BlockItem(AerialHellBlocks.AERIAL_TREE_DOOR, new Item.Settings()));
    public static final Item GOLDEN_BEECH_DOOR_ITEM = register("golden_beech_door", new BlockItem(AerialHellBlocks.GOLDEN_BEECH_DOOR, new Item.Settings()));
    public static final Item COPPER_PINE_DOOR_ITEM = register("copper_pine_door", new BlockItem(AerialHellBlocks.COPPER_PINE_DOOR, new Item.Settings()));
    public static final Item LAPIS_ROBINIA_DOOR_ITEM = register("lapis_robinia_door", new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_DOOR, new Item.Settings()));
    public static final Item SHADOW_PINE_DOOR_ITEM = register("shadow_pine_door", new BlockItem(AerialHellBlocks.SHADOW_PINE_DOOR, new Item.Settings()));
    public static final Item STELLAR_JUNGLE_TREE_DOOR_ITEM = register("stellar_jungle_tree_door", new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_DOOR, new Item.Settings()));
    public static final Item SKY_CACTUS_FIBER_DOOR_ITEM = register("sky_cactus_fiber_door", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_DOOR, new Item.Settings()));
    public static final Item GRAY_SHROOM_DOOR_ITEM = register("gray_shroom_door", new BlockItem(AerialHellBlocks.GRAY_SHROOM_DOOR, new Item.Settings()));
    public static final Item RUBY_DOOR_ITEM = register("ruby_door", new BlockItem(AerialHellBlocks.RUBY_DOOR, new Item.Settings()));

    //trapdoors
    public static final Item AERIAL_TREE_TRAPDOOR_ITEM = register("aerial_tree_trapdoor", new BlockItem(AerialHellBlocks.AERIAL_TREE_TRAPDOOR, new Item.Settings()));
    public static final Item GOLDEN_BEECH_TRAPDOOR_ITEM = register("golden_beech_trapdoor", new BlockItem(AerialHellBlocks.GOLDEN_BEECH_TRAPDOOR, new Item.Settings()));
    public static final Item COPPER_PINE_TRAPDOOR_ITEM = register("copper_pine_trapdoor", new BlockItem(AerialHellBlocks.COPPER_PINE_TRAPDOOR, new Item.Settings()));
    public static final Item LAPIS_ROBINIA_TRAPDOOR_ITEM = register("lapis_robinia_trapdoor", new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_TRAPDOOR, new Item.Settings()));
    public static final Item SHADOW_PINE_TRAPDOOR_ITEM = register("shadow_pine_trapdoor", new BlockItem(AerialHellBlocks.SHADOW_PINE_TRAPDOOR, new Item.Settings()));
    public static final Item STELLAR_JUNGLE_TREE_TRAPDOOR_ITEM = register("stellar_jungle_tree_trapdoor", new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_TRAPDOOR, new Item.Settings()));
    public static final Item SKY_CACTUS_FIBER_TRAPDOOR_ITEM = register("sky_cactus_fiber_trapdoor", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_TRAPDOOR, new Item.Settings()));
    public static final Item GRAY_SHROOM_TRAPDOOR_ITEM = register("gray_shroom_trapdoor", new BlockItem(AerialHellBlocks.GRAY_SHROOM_TRAPDOOR, new Item.Settings()));
    public static final Item RUBY_TRAPDOOR_ITEM = register("ruby_trapdoor", new BlockItem(AerialHellBlocks.RUBY_TRAPDOOR, new Item.Settings()));

    //buttons
    public static final Item STELLAR_STONE_BUTTON_ITEM = register("stellar_stone_button", new BlockItem(AerialHellBlocks.STELLAR_STONE_BUTTON, new Item.Settings()));
    public static final Item STELLAR_COBBLESTONE_BUTTON_ITEM = register("stellar_cobblestone_button", new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_BUTTON, new Item.Settings()));
    public static final Item STELLAR_STONE_BRICKS_BUTTON_ITEM = register("stellar_stone_bricks_button", new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_BUTTON, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_BUTTON_ITEM = register("slippery_sand_stone_button", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BUTTON, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_BUTTON_ITEM = register("slippery_sand_stone_bricks_button", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_BUTTON, new Item.Settings()));
    public static final Item AERIAL_TREE_BUTTON_ITEM = register("aerial_tree_button", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_BUTTON, new Item.Settings(), 100));
    public static final Item GOLDEN_BEECH_BUTTON_ITEM = register("golden_beech_button", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_BUTTON, new Item.Settings(), 100));
    public static final Item COPPER_PINE_BUTTON_ITEM = register("copper_pine_button", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_BUTTON, new Item.Settings(), 100));
    public static final Item LAPIS_ROBINIA_BUTTON_ITEM = register("lapis_robinia_button", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_BUTTON, new Item.Settings(), 100));
    public static final Item SHADOW_PINE_BUTTON_ITEM = register("shadow_pine_button", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_BUTTON, new Item.Settings(), 100));
    public static final Item STELLAR_JUNGLE_TREE_BUTTON_ITEM = register("stellar_jungle_tree_button", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BUTTON, new Item.Settings(), 100));
    public static final Item SKY_CACTUS_FIBER_BUTTON_ITEM = register("sky_cactus_fiber_button", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BUTTON, new Item.Settings()));
    public static final Item GRAY_SHROOM_BUTTON_ITEM = register("gray_shroom_button", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_BUTTON, new Item.Settings(), 30));
    public static final Item GLAUCOPHANITE_BUTTON_ITEM = register("glaucophanite_button", new BlockItem(AerialHellBlocks.GLAUCOPHANITE_BUTTON, new Item.Settings()));

    //pressure plates
    public static final Item STELLAR_STONE_PRESSURE_PLATE_ITEM = register("stellar_stone_pressure_plate", new BlockItem(AerialHellBlocks.STELLAR_STONE_PRESSURE_PLATE, new Item.Settings()));
    public static final Item STELLAR_COBBLESTONE_PRESSURE_PLATE_ITEM = register("stellar_cobblestone_pressure_plate", new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_PRESSURE_PLATE, new Item.Settings()));
    public static final Item STELLAR_STONE_BRICKS_PRESSURE_PLATE_ITEM = register("stellar_stone_bricks_pressure_plate", new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_PRESSURE_PLATE, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_PRESSURE_PLATE_ITEM = register("slippery_sand_stone_pressure_plate", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_PRESSURE_PLATE, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE_ITEM = register("slippery_sand_stone_bricks_pressure_plate", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE, new Item.Settings()));
    public static final Item AERIAL_TREE_PRESSURE_PLATE_ITEM = register("aerial_tree_pressure_plate", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_PRESSURE_PLATE, new Item.Settings(), 300));
    public static final Item GOLDEN_BEECH_PRESSURE_PLATE_ITEM = register("golden_beech_pressure_plate", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_PRESSURE_PLATE, new Item.Settings(), 300));
    public static final Item COPPER_PINE_PRESSURE_PLATE_ITEM = register("copper_pine_pressure_plate", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_PRESSURE_PLATE, new Item.Settings(), 300));
    public static final Item LAPIS_ROBINIA_PRESSURE_PLATE_ITEM = register("lapis_robinia_pressure_plate", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_PRESSURE_PLATE, new Item.Settings(), 300));
    public static final Item SHADOW_PINE_PRESSURE_PLATE_ITEM = register("shadow_pine_pressure_plate", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_PRESSURE_PLATE, new Item.Settings(), 300));
    public static final Item STELLAR_JUNGLE_TREE_PRESSURE_PLATE_ITEM = register("stellar_jungle_tree_pressure_plate", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_PRESSURE_PLATE, new Item.Settings(), 300));
    public static final Item SKY_CACTUS_FIBER_PRESSURE_PLATE_ITEM = register("sky_cactus_fiber_pressure_plate", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_PRESSURE_PLATE, new Item.Settings()));
    public static final Item GRAY_SHROOM_PRESSURE_PLATE_ITEM = register("gray_shroom_pressure_plate", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_PRESSURE_PLATE, new Item.Settings(), 100));
    public static final Item GLAUCOPHANITE_PRESSURE_PLATE_ITEM = register("glaucophanite_pressure_plate", new BlockItem(AerialHellBlocks.GLAUCOPHANITE_PRESSURE_PLATE, new Item.Settings()));

    //slabs
    public static final Item AERIAL_TREE_SLAB_ITEM = register("aerial_tree_slab", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_SLAB, new Item.Settings(), 150));
    public static final Item GOLDEN_BEECH_SLAB_ITEM = register("golden_beech_slab", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_SLAB, new Item.Settings(), 150));
    public static final Item COPPER_PINE_SLAB_ITEM = register("copper_pine_slab", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_SLAB, new Item.Settings(), 150));
    public static final Item LAPIS_ROBINIA_SLAB_ITEM = register("lapis_robinia_slab", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_SLAB, new Item.Settings(), 150));
    public static final Item SHADOW_PINE_SLAB_ITEM = register("shadow_pine_slab", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_SLAB, new Item.Settings(), 150));
    public static final Item STELLAR_JUNGLE_TREE_SLAB_ITEM = register("stellar_jungle_tree_slab", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_SLAB, new Item.Settings(), 150));
    public static final Item SKY_CACTUS_FIBER_SLAB_ITEM = register("sky_cactus_fiber_slab", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_SLAB, new Item.Settings()));
    public static final Item GRAY_SHROOM_SLAB_ITEM = register("gray_shroom_slab", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_SLAB, new Item.Settings(), 50));
    public static final Item STELLAR_STONE_SLAB_ITEM = register("stellar_stone_slab", new BlockItem(AerialHellBlocks.STELLAR_STONE_SLAB, new Item.Settings()));
    public static final Item STELLAR_COBBLESTONE_SLAB_ITEM = register("stellar_cobblestone_slab", new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_SLAB, new Item.Settings()));
    public static final Item STELLAR_STONE_BRICKS_SLAB_ITEM = register("stellar_stone_bricks_slab", new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_SLAB, new Item.Settings()));
    public static final Item MOSSY_STELLAR_STONE_SLAB_ITEM = register("mossy_stellar_stone_slab", new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_SLAB, new Item.Settings()));
    public static final Item MOSSY_STELLAR_COBBLESTONE_SLAB_ITEM = register("mossy_stellar_cobblestone_slab", new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_SLAB, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_SLAB_ITEM = register("slippery_sand_stone_slab", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_SLAB, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_SLAB_ITEM = register("slippery_sand_stone_bricks_slab", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_SLAB, new Item.Settings()));
    public static final Item CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB_ITEM = register("cracked_slippery_sand_stone_bricks_slab", new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB, new Item.Settings()));
    public static final Item POLISHED_GLAUCOPHANITE_SLAB_ITEM = register("polished_glaucophanite_slab", new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_SLAB, new Item.Settings()));
    public static final Item MAGMATIC_GEL_SLAB_ITEM = register("magmatic_gel_slab", new BlockItem(AerialHellBlocks.MAGMATIC_GEL_SLAB, new Item.Settings()));

    //stairs
    public static final Item AERIAL_TREE_STAIRS_ITEM = register("aerial_tree_stairs", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_STAIRS, new Item.Settings(), 300));
    public static final Item GOLDEN_BEECH_STAIRS_ITEM = register("golden_beech_stairs", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_STAIRS, new Item.Settings(), 300));
    public static final Item COPPER_PINE_STAIRS_ITEM = register("copper_pine_stairs", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_STAIRS, new Item.Settings(), 300));
    public static final Item LAPIS_ROBINIA_STAIRS_ITEM = register("lapis_robinia_stairs", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_STAIRS, new Item.Settings(), 300));
    public static final Item SHADOW_PINE_STAIRS_ITEM = register("shadow_pine_stairs", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_STAIRS, new Item.Settings(), 300));
    public static final Item STELLAR_JUNGLE_TREE_STAIRS_ITEM = register("stellar_jungle_tree_stairs", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_STAIRS, new Item.Settings(), 300));
    public static final Item SKY_CACTUS_FIBER_STAIRS_ITEM = register("sky_cactus_fiber_stairs", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_STAIRS, new Item.Settings()));
    public static final Item GRAY_SHROOM_STAIRS_ITEM = register("gray_shroom_stairs", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_STAIRS, new Item.Settings(), 100));
    public static final Item STELLAR_STONE_STAIRS_ITEM = register("stellar_stone_stairs", new BlockItem(AerialHellBlocks.STELLAR_STONE_STAIRS, new Item.Settings()));
    public static final Item STELLAR_COBBLESTONE_STAIRS_ITEM = register("stellar_cobblestone_stairs", new BlockItem(AerialHellBlocks.STELLAR_COBBLESTONE_STAIRS, new Item.Settings()));
    public static final Item STELLAR_STONE_BRICKS_STAIRS_ITEM = register("stellar_stone_bricks_stairs", new BlockItem(AerialHellBlocks.STELLAR_STONE_BRICKS_STAIRS, new Item.Settings()));
    public static final Item MOSSY_STELLAR_STONE_STAIRS_ITEM = register("mossy_stellar_stone_stairs", new BlockItem(AerialHellBlocks.MOSSY_STELLAR_STONE_STAIRS, new Item.Settings()));
    public static final Item MOSSY_STELLAR_COBBLESTONE_STAIRS_ITEM = register("mossy_stellar_cobblestone_stairs", new BlockItem(AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE_STAIRS, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_STAIRS_ITEM = register("slippery_sand_stone_stairs", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_STAIRS, new Item.Settings()));
    public static final Item SLIPPERY_SAND_STONE_BRICKS_STAIRS_ITEM = register("slippery_sand_stone_bricks_stairs", new BlockItem(AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_STAIRS, new Item.Settings()));
    public static final Item CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS_ITEM = register("cracked_slippery_sand_stone_bricks_stairs", new BlockItem(AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS, new Item.Settings()));
    public static final Item POLISHED_GLAUCOPHANITE_STAIRS_ITEM = register("polished_glaucophanite_stairs", new BlockItem(AerialHellBlocks.POLISHED_GLAUCOPHANITE_STAIRS, new Item.Settings()));
    public static final Item MAGMATIC_GEL_STAIRS_ITEM = register("magmatic_gel_stairs", new BlockItem(AerialHellBlocks.MAGMATIC_GEL_STAIRS, new Item.Settings()));

    //signs
    public static final Item AERIAL_TREE_SIGN_ITEM = register("aerial_tree_sign", new SignItem(new Item.Settings(), AerialHellBlocks.AERIAL_TREE_STANDING_SIGN, AerialHellBlocks.AERIAL_TREE_WALL_SIGN));
    public static final Item GOLDEN_BEECH_SIGN_ITEM = register("golden_beech_sign", new SignItem(new Item.Settings(), AerialHellBlocks.GOLDEN_BEECH_STANDING_SIGN, AerialHellBlocks.GOLDEN_BEECH_WALL_SIGN));
    public static final Item COPPER_PINE_SIGN_ITEM = register("copper_pine_sign", new SignItem(new Item.Settings(), AerialHellBlocks.COPPER_PINE_STANDING_SIGN, AerialHellBlocks.COPPER_PINE_WALL_SIGN));
    public static final Item LAPIS_ROBINIA_SIGN_ITEM = register("lapis_robinia_sign", new SignItem(new Item.Settings(), AerialHellBlocks.LAPIS_ROBINIA_STANDING_SIGN, AerialHellBlocks.LAPIS_ROBINIA_WALL_SIGN));
    public static final Item SHADOW_PINE_SIGN_ITEM = register("shadow_pine_sign", new SignItem(new Item.Settings(), AerialHellBlocks.SHADOW_PINE_STANDING_SIGN, AerialHellBlocks.SHADOW_PINE_WALL_SIGN));
    public static final Item STELLAR_JUNGLE_TREE_SIGN_ITEM = register("stellar_jungle_tree_sign", new SignItem(new Item.Settings(), AerialHellBlocks.STELLAR_JUNGLE_TREE_STANDING_SIGN, AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_SIGN));
    public static final Item SKY_CACTUS_FIBER_SIGN_ITEM = register("sky_cactus_fiber_sign", new SignItem(new Item.Settings(), AerialHellBlocks.SKY_CACTUS_FIBER_STANDING_SIGN, AerialHellBlocks.SKY_CACTUS_FIBER_WALL_SIGN));
    public static final Item GRAY_SHROOM_SIGN_ITEM = register("gray_shroom_sign", new SignItem(new Item.Settings(), AerialHellBlocks.GRAY_SHROOM_STANDING_SIGN, AerialHellBlocks.GRAY_SHROOM_WALL_SIGN));

    //hanging signs
    public static final Item AERIAL_TREE_HANGING_SIGN_ITEM = register("aerial_tree_hanging_sign", new HangingSignItem(AerialHellBlocks.AERIAL_TREE_HANGING_SIGN, AerialHellBlocks.AERIAL_TREE_WALL_HANGING_SIGN, (new Item.Settings()).maxCount(16)));
    public static final Item GOLDEN_BEECH_HANGING_SIGN_ITEM = register("golden_beech_hanging_sign", new HangingSignItem(AerialHellBlocks.GOLDEN_BEECH_HANGING_SIGN, AerialHellBlocks.GOLDEN_BEECH_WALL_HANGING_SIGN, (new Item.Settings()).maxCount(16)));
    public static final Item COPPER_PINE_HANGING_SIGN_ITEM = register("copper_pine_hanging_sign", new HangingSignItem(AerialHellBlocks.COPPER_PINE_HANGING_SIGN, AerialHellBlocks.COPPER_PINE_WALL_HANGING_SIGN, (new Item.Settings()).maxCount(16)));
    public static final Item LAPIS_ROBINIA_HANGING_SIGN_ITEM = register("lapis_robinia_hanging_sign", new HangingSignItem(AerialHellBlocks.LAPIS_ROBINIA_HANGING_SIGN, AerialHellBlocks.LAPIS_ROBINIA_WALL_HANGING_SIGN, (new Item.Settings()).maxCount(16)));
    public static final Item SHADOW_PINE_HANGING_SIGN_ITEM = register("shadow_pine_hanging_sign", new HangingSignItem(AerialHellBlocks.SHADOW_PINE_HANGING_SIGN, AerialHellBlocks.SHADOW_PINE_WALL_HANGING_SIGN, (new Item.Settings()).maxCount(16)));
    public static final Item STELLAR_JUNGLE_TREE_HANGING_SIGN_ITEM = register("stellar_jungle_tree_hanging_sign", new HangingSignItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_HANGING_SIGN, AerialHellBlocks.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN, (new Item.Settings()).maxCount(16)));
    public static final Item SKY_CACTUS_FIBER_HANGING_SIGN_ITEM = register("sky_cactus_fiber_hanging_sign", new HangingSignItem(AerialHellBlocks.SKY_CACTUS_FIBER_HANGING_SIGN, AerialHellBlocks.SKY_CACTUS_FIBER_WALL_HANGING_SIGN, (new Item.Settings()).maxCount(16)));
    public static final Item GRAY_SHROOM_HANGING_SIGN_ITEM = register("gray_shroom_hanging_sign", new HangingSignItem(AerialHellBlocks.GRAY_SHROOM_HANGING_SIGN, AerialHellBlocks.GRAY_SHROOM_WALL_HANGING_SIGN, (new Item.Settings()).maxCount(16)));

    //crafting tables
    public static final Item AERIAL_TREE_CRAFTING_TABLE_ITEM = register("aerial_tree_crafting_table", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_CRAFTING_TABLE, new Item.Settings(), 300));
    public static final Item GOLDEN_BEECH_CRAFTING_TABLE_ITEM = register("golden_beech_crafting_table", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_CRAFTING_TABLE, new Item.Settings(), 300));
    public static final Item COPPER_PINE_CRAFTING_TABLE_ITEM = register("copper_pine_crafting_table", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_CRAFTING_TABLE, new Item.Settings(), 300));
    public static final Item LAPIS_ROBINIA_CRAFTING_TABLE_ITEM = register("lapis_robinia_crafting_table", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_CRAFTING_TABLE, new Item.Settings(), 300));
    public static final Item SHADOW_PINE_CRAFTING_TABLE_ITEM = register("shadow_pine_crafting_table", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_CRAFTING_TABLE, new Item.Settings(), 300));
    public static final Item STELLAR_JUNGLE_TREE_CRAFTING_TABLE_ITEM = register("stellar_jungle_tree_crafting_table", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_CRAFTING_TABLE, new Item.Settings(), 300));
    public static final Item SKY_CACTUS_FIBER_CRAFTING_TABLE_ITEM = register("sky_cactus_fiber_crafting_table", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_CRAFTING_TABLE, new Item.Settings()));
    public static final Item GRAY_SHROOM_CRAFTING_TABLE_ITEM = register("gray_shroom_crafting_table", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_CRAFTING_TABLE, new Item.Settings(), 100));

    //barrels
    public static final Item AERIAL_TREE_BARREL_ITEM = register("aerial_tree_barrel", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_BARREL, new Item.Settings(), 300));
    public static final Item GOLDEN_BEECH_BARREL_ITEM = register("golden_beech_barrel", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_BARREL, new Item.Settings(), 300));
    public static final Item COPPER_PINE_BARREL_ITEM = register("copper_pine_barrel", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_BARREL, new Item.Settings(), 300));
    public static final Item LAPIS_ROBINIA_BARREL_ITEM = register("lapis_robinia_barrel", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_BARREL, new Item.Settings(), 300));
    public static final Item SHADOW_PINE_BARREL_ITEM = register("shadow_pine_barrel", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_BARREL, new Item.Settings(), 300));
    public static final Item STELLAR_JUNGLE_TREE_BARREL_ITEM = register("stellar_jungle_tree_barrel", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_BARREL, new Item.Settings(), 300));
    public static final Item SKY_CACTUS_FIBER_BARREL_ITEM = register("sky_cactus_fiber_barrel", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_BARREL, new Item.Settings()));
    public static final Item GRAY_SHROOM_BARREL_ITEM = register("gray_shroom_barrel", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_BARREL, new Item.Settings(), 100));

    //composters
    public static final Item AERIAL_TREE_COMPOSTER_ITEM = register("aerial_tree_composter", new BurnableBlockItem(AerialHellBlocks.AERIAL_TREE_COMPOSTER, new Item.Settings(), 300));
    public static final Item GOLDEN_BEECH_COMPOSTER_ITEM = register("golden_beech_composter", new BurnableBlockItem(AerialHellBlocks.GOLDEN_BEECH_COMPOSTER, new Item.Settings(), 300));
    public static final Item COPPER_PINE_COMPOSTER_ITEM = register("copper_pine_composter", new BurnableBlockItem(AerialHellBlocks.COPPER_PINE_COMPOSTER, new Item.Settings(), 300));
    public static final Item LAPIS_ROBINIA_COMPOSTER_ITEM = register("lapis_robinia_composter", new BurnableBlockItem(AerialHellBlocks.LAPIS_ROBINIA_COMPOSTER, new Item.Settings(), 300));
    public static final Item SHADOW_PINE_COMPOSTER_ITEM = register("shadow_pine_composter", new BurnableBlockItem(AerialHellBlocks.SHADOW_PINE_COMPOSTER, new Item.Settings(), 300));
    public static final Item STELLAR_JUNGLE_TREE_COMPOSTER_ITEM = register("stellar_jungle_tree_composter", new BurnableBlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_COMPOSTER, new Item.Settings(), 300));
    public static final Item SKY_CACTUS_FIBER_COMPOSTER_ITEM = register("sky_cactus_fiber_composter", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_COMPOSTER, new Item.Settings()));
    public static final Item GRAY_SHROOM_COMPOSTER_ITEM = register("gray_shroom_composter", new BurnableBlockItem(AerialHellBlocks.GRAY_SHROOM_COMPOSTER, new Item.Settings(), 100));

    //decorative
    public static final Item AERIAL_TREE_VINE_ROPE_SPOOL_ITEM = register("aerial_tree_vine_rope_spool", new BlockItem(AerialHellBlocks.AERIAL_TREE_VINE_ROPE_SPOOL, new Item.Settings()));
    public static final Item GOLDEN_BEECH_VINE_ROPE_SPOOL_ITEM = register("golden_beech_vine_rope_spool", new BlockItem(AerialHellBlocks.GOLDEN_BEECH_VINE_ROPE_SPOOL, new Item.Settings()));
    public static final Item COPPER_PINE_VINE_ROPE_SPOOL_ITEM = register("copper_pine_vine_rope_spool", new BlockItem(AerialHellBlocks.COPPER_PINE_VINE_ROPE_SPOOL, new Item.Settings()));
    public static final Item LAPIS_ROBINIA_VINE_ROPE_SPOOL_ITEM = register("lapis_robinia_vine_rope_spool", new BlockItem(AerialHellBlocks.LAPIS_ROBINIA_VINE_ROPE_SPOOL, new Item.Settings()));
    public static final Item SHADOW_PINE_VINE_ROPE_SPOOL_ITEM = register("shadow_pine_vine_rope_spool", new BlockItem(AerialHellBlocks.SHADOW_PINE_VINE_ROPE_SPOOL, new Item.Settings()));
    public static final Item STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL_ITEM = register("stellar_jungle_tree_vine_rope_spool", new BlockItem(AerialHellBlocks.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL, new Item.Settings()));
    public static final Item SKY_CACTUS_FIBER_VINE_ROPE_SPOOL_ITEM = register("sky_cactus_fiber_vine_rope_spool", new BlockItem(AerialHellBlocks.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL, new Item.Settings()));
    public static final Item GRAY_SHROOM_VINE_ROPE_SPOOL_ITEM = register("gray_shroom_vine_rope_spool", new BlockItem(AerialHellBlocks.GRAY_SHROOM_VINE_ROPE_SPOOL, new Item.Settings()));

    //item for crafts
    public static final Item SKY_STICK = register("sky_stick", new BurnableItem(new Item.Settings(), 100));
    public static final Item SKY_BOWL = register("sky_bowl", new BurnableItem(new Item.Settings(), 200));
    public static final Item SHADOW_SHARD = register("shadow_shard", new Item(new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item ROTTEN_LEATHER = register("rotten_leather", new Item(new Item.Settings()));
    public static final Item VENOMOUS_SNAKE_SKIN = register("venomous_snake_skin", new Item(new Item.Settings()));
    public static final Item ARSONIST_UPGRADE_SMITHING_TEMPLATE = register("arsonist_upgrade_smithing_template", ItemHelper.SmithingTemplate.createUpgradeTemplate("arsonist"));

    //projectile item
    public static final Item STELLAR_EGG = register("stellar_egg", new StellarEggItem(new Item.Settings().maxCount(16)));
    public static final Item DIMENSION_SHATTERER_PROJECTILE = register("dimension_shatterer_projectile", new DimensionShattererProjectileItem(new Item.Settings().maxCount(16)));

    //shurikens
    public static final Item IRON_SHURIKEN = register("iron_shuriken", new IronShurikenItem());
    public static final Item GOLD_SHURIKEN = register("gold_shuriken", new GoldShurikenItem());
    public static final Item DIAMOND_SHURIKEN = register("diamond_shuriken", new DiamondShurikenItem());
    public static final Item NETHERITE_SHURIKEN = register("netherite_shuriken", new NetheriteShurikenItem());
    public static final Item RUBY_SHURIKEN = register("ruby_shuriken", new RubyShurikenItem());
    public static final Item AZURITE_SHURIKEN = register("azurite_shuriken", new AzuriteShurikenItem());
    public static final Item MAGMATIC_GEL_SHURIKEN = register("magmatic_gel_shuriken", new MagmaticGelShurikenItem());
    public static final Item VOLUCITE_SHURIKEN = register("volucite_shuriken", new VoluciteShurikenItem());
    public static final Item OBSIDIAN_SHURIKEN = register("obsidian_shuriken", new ObsidianShurikenItem());
    public static final Item LUNATIC_CRYSTAL_SHURIKEN = register("lunatic_crystal_shuriken", new LunaticCrystalShurikenItem());
    public static final Item ARSONIST_SHURIKEN = register("arsonist_shuriken", new ArsonistShurikenItem());
    public static final Item LIGHTNING_SHURIKEN = register("lightning_shuriken", new LightningShurikenItem());

    //food
    public static final Item AERIAL_BERRY = register("aerial_berry", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.2F).build())));
    public static final Item ROASTED_AERIAL_BERRY = register("roasted_aerial_berry", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build())));
    public static final Item VIBRANT_AERIAL_BERRY = register("vibrant_aerial_berry", new Item(new Item.Settings().rarity(AerialHellRarities.VIBRANT).food(new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F).build())));
    public static final Item FROZEN_AERIAL_BERRY = register("frozen_aerial_berry", new FoodWithEffectItem(6, 0.8F, AerialHellRarities.FROZEN, () -> new StatusEffectInstance(StatusEffects.SLOWNESS, 310, 0), () -> new StatusEffectInstance(StatusEffects.RESISTANCE, 210, 0)));
    public static final Item STELLAR_BREAD = register("stellar_bread", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.6F).build())));
    public static final Item FROZEN_MUTTON = register("frozen_mutton", new FoodWithEffectItem(6, 0.8F, AerialHellRarities.FROZEN, () -> new StatusEffectInstance(StatusEffects.SLOWNESS, 310, 0), () -> new StatusEffectInstance(StatusEffects.RESISTANCE, 210, 0)));
    public static final Item VIBRANT_CHICKEN = register("vibrant_chicken", new FoodWithEffectItem(6, 0.8F, AerialHellRarities.VIBRANT, () -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 60, 0)));
    public static final Item FROZEN_CHICKEN = register("frozen_chicken", new FoodWithEffectItem(6, 0.8F, AerialHellRarities.FROZEN, () -> new StatusEffectInstance(StatusEffects.SLOWNESS, 310, 0), () -> new StatusEffectInstance(StatusEffects.RESISTANCE, 210, 1), () -> new StatusEffectInstance(StatusEffects.HUNGER, 80, 0)));
    public static final Item RUBY_AERIAL_BERRY = register("ruby_aerial_berry", new FoodWithEffectItem(6, 0.8F, Rarity.RARE, () -> new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2400, 0)));
    public static final Item VOLUCITE_AERIAL_BERRY = register("volucite_aerial_berry", new FoodWithEffectItem(6, 0.8F, AerialHellRarities.VIBRANT, () -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2400, 2)));
    public static final Item GLOWING_STICK_FRUIT = register("glowing_stick_fruit", new AliasedBlockItem(AerialHellBlocks.GLOWING_STICK_FRUIT_VINES, new Item.Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.2F).build())));
    public static final Item VIBRANT_GLOWING_STICK_FRUIT = register("vibrant_glowing_stick_fruit", new Item(new Item.Settings().rarity(AerialHellRarities.VIBRANT).food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build())));
    public static final Item FROZEN_GLOWING_STICK_FRUIT = register("frozen_glowing_stick_fruit", new FoodWithEffectItem(4, 0.4F, AerialHellRarities.FROZEN, () -> new StatusEffectInstance(StatusEffects.SLOWNESS, 120, 0), () -> new StatusEffectInstance(StatusEffects.RESISTANCE, 180, 0)));
    public static final Item CORTINARIUS_VIOLACEUS_PIECE = register("cortinarius_violaceus_piece", new FoodWithEffectItem(1, 0.1F, Rarity.COMMON, () -> new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0)));
    public static final Item GANODERMA_APPLANATUM_PIECE = register("ganoderma_applanatum_piece", new FoodWithEffectItem(1, 0.1F, Rarity.COMMON, () -> new StatusEffectInstance(StatusEffects.HUNGER, 100, 0)));
    public static final Item DARK_SHADOW_FRUIT = register("dark_shadow_fruit", new FoodWithEffectItem(2, 0.2F, Rarity.COMMON, () -> new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0), () -> new StatusEffectInstance(StatusEffects.NIGHT_VISION, 120, 0)));
    public static final Item PURPLE_SHADOW_FRUIT = register("purple_shadow_fruit", new FoodWithEffectItem(2, 0.2F, Rarity.COMMON, () -> new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0), () -> new StatusEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 80, 0)));
    public static final Item SHADOW_FRUIT_STEW = register("shadow_fruit_stew", new SkySoupItem(2, 0.2F, AerialHellRarities.CORRUPTED, () -> new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0), () -> new StatusEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 1200, 0)));
    public static final Item SOLID_ETHER_SOUP = register("solid_ether_soup", new SkySoupItem(2, 0.2F, Rarity.COMMON, () -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 160, 0), () -> new StatusEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 160, 0)));
    public static final Item VIBRANT_SOLID_ETHER_SOUP = register("vibrant_solid_ether_soup", new SkySoupItem(4, 0.2F, AerialHellRarities.VIBRANT, () -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 160, 0), () -> new StatusEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 160, 1)));
    public static final Item FROZEN_SOLID_ETHER_SOUP = register("frozen_solid_ether_soup", new SkySoupItem(4, 0.2F, AerialHellRarities.FROZEN, () -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 180, 0), () -> new StatusEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 160, 0), () -> new StatusEffectInstance(StatusEffects.RESISTANCE, 210, 0)));
    public static final Item SHADOW_SPIDER_EYE = register("shadow_spider_eye", new FoodWithEffectItem(2, 0.2F, AerialHellRarities.CORRUPTED, () -> new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0), () -> new StatusEffectInstance(StatusEffects.SLOWNESS, 120, 0)));
    public static final Item PHANTOM_MEAT = register("phantom_meat", new FoodWithEffectItem(5, 0.8F, Rarity.UNCOMMON, () -> new StatusEffectInstance(StatusEffects.RESISTANCE, 120, 0)));
    public static final Item VIBRANT_PHANTOM_MEAT = register("vibrant_phantom_meat", new FoodWithEffectItem(8, 0.8F, AerialHellRarities.VIBRANT, () -> new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 1)));
    public static final Item FROZEN_PHANTOM_MEAT = register("frozen_phantom_meat", new FoodWithEffectItem(6, 0.6F, AerialHellRarities.FROZEN, () -> new StatusEffectInstance(StatusEffects.RESISTANCE, 800, 1)));
    public static final Item COOKED_PHANTOM_MEAT = register("cooked_phantom_meat", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(10).saturationModifier(0.9F).build())));
    public static final Item TURTLE_MEAT = register("turtle_meat", new Item(new Item.Settings().food(FoodComponents.BEEF)));
    public static final Item VIBRANT_TURTLE_MEAT = register("vibrant_turtle_meat", new FoodWithEffectItem(7, 0.7F, AerialHellRarities.VIBRANT, () -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 400, 0)));
    public static final Item FROZEN_TURTLE_MEAT = register("frozen_turtle_meat", new FoodWithEffectItem(6, 0.6F, AerialHellRarities.FROZEN, () -> new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 0)));
    public static final Item COOKED_TURTLE_MEAT = register("cooked_turtle_meat", new Item(new Item.Settings().food(FoodComponents.COOKED_BEEF)));
    public static final Item GODS_VOLUCITE_AERIAL_BERRY = register("gods_volucite_aerial_berry", new GodsVoluciteBerryItem());
    public static final Item COPPER_PINE_CONE = register("copper_pine_cone", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build())));
    public static final Item AZURITE_COPPER_PINE_CONE = register("azurite_copper_pine_cone", new FoodWithEffectItem(4, 0.4F, Rarity.COMMON, () -> new StatusEffectInstance(StatusEffects.HASTE, 400, 0)));
    public static final Item PHOENIX_FEATHER = register("phoenix_feather", new FoodWithEffectItem(6, 0.8F, AerialHellRarities.LEGENDARY, () -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 1200, 0), () -> new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0)));
    public static final Item SKY_CACTUS_FIBER = register("sky_cactus_fiber", new Item(new Item.Settings().food(new FoodComponent.Builder().snack().nutrition(1).saturationModifier(0.1F).build())));
    public static final Item VIBRANT_SKY_CACTUS_FIBER = register("vibrant_sky_cactus_fiber", new Item(new Item.Settings().rarity(AerialHellRarities.VIBRANT).food(new FoodComponent.Builder().snack().nutrition(4).saturationModifier(0.3F).build())));
    public static final Item WHITE_SOLID_ETHER_FRAGMENT = register("white_solid_ether_fragment", new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.1F).build())));
    public static final Item BLUE_SOLID_ETHER_FRAGMENT = register("blue_solid_ether_fragment", new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, () -> new StatusEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 90, 0)));
    public static final Item GOLDEN_SOLID_ETHER_FRAGMENT = register("golden_solid_ether_fragment", new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, () -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 110, 0)));
    public static final Item GREEN_SOLID_ETHER_FRAGMENT = register("green_solid_ether_fragment", new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, () -> new StatusEffectInstance(StatusEffects.JUMP_BOOST, 90, 1)));
    public static final Item PURPLE_SOLID_ETHER_FRAGMENT = register("purple_solid_ether_fragment", new FoodWithEffectItem(1, 0.1F, AerialHellRarities.CORRUPTED, () -> new StatusEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 90, 1)));
    public static final Item GOLDEN_NETHER_MEAT_PIECE = register("golden_nether_meat_piece", new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, () -> new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 110, 0)));
    public static final Item GOLDEN_NETHER_STEAK = register("golden_nether_steak", new FoodWithEffectItem(4, 0.4F, Rarity.UNCOMMON, () -> new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 500, 0)));
    public static final Item VIBRANT_GOLDEN_NETHER_STEAK = register("vibrant_golden_nether_steak", new FoodWithEffectItem(6, 0.8F, AerialHellRarities.VIBRANT, () -> new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1000, 0)));

    //buckets
    public static final Item IRON_LIQUID_OF_GODS_BUCKET = register("iron_liquid_of_gods_bucket", new BucketItem(AerialHellFluids.LIQUID_OF_THE_GODS_STILL, (new Item.Settings()).maxCount(1)));
    public static final Item RUBY_LIQUID_OF_GODS_BUCKET = register("ruby_liquid_of_gods_bucket", new RubyLiquidOfGodsBucketItem(new Item.Settings().maxCount(1)));
    public static final Item RUBY_BUCKET = register("ruby_bucket", new RubyBucketItem(new Item.Settings().maxCount(16)));
    public static final Item RUBY_WATER_BUCKET = register("ruby_water_bucket", new RubyWaterBucketItem(new Item.Settings().maxCount(1)));
    public static final Item RUBY_MILK_BUCKET = register("ruby_milk_bucket", new RubyMilkBucketItem(new Item.Settings().maxCount(1)));

    //arrows & bows
    public static final Item RUBY_BLOWPIPE_ARROW = register("ruby_blowpipe_arrow", new AerialArrowItem(new Item.Settings()));
    public static final Item VOLUCITE_BLOWPIPE_ARROW = register("volucite_blowpipe_arrow", new AerialArrowItem(new Item.Settings().rarity(AerialHellRarities.VIBRANT)));

    public static final Item RUBY_BLOWPIPE = register("ruby_blowpipe", new BlowpipeItem(new Item.Settings().maxCount(1).maxDamage(200), 1.7F));
    public static final Item VOLUCITE_BLOWPIPE = register("volucite_blowpipe", new BlowpipeItem(new Item.Settings().rarity(AerialHellRarities.VIBRANT).maxCount(1).maxDamage(400), 2.4F));

    //music discs
    public static final Item MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP = register("music_disc_aerial_hell_theme_tommaup", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.AERIAL_HELL_THEME_TOMMAUP)));
    public static final Item MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = register("music_disc_sweden_andreas_zoeller", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.SWEDEN_ANDREAS_ZOELLER)));
    public static final Item MUSIC_DISC_ENTHUSIAST_TOURS = register("music_disc_enthusiast_tours", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.ENTHUSIAST_TOURS)));
    public static final Item MUSIC_DISC_BMINOR_ARULO = register("music_disc_bminor_arulo", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.BMINOR_ARULO)));
    public static final Item MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP = register("music_disc_retro_exploration_tommaup", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(AerialHellJukeboxSongs.RETRO_EXPLORATION_TOMMAUP)));

    //tools
    public static final PickaxeItem SKY_WOOD_PICKAXE = register("sky_wood_pickaxe", new PickaxeItem(ToolMaterials.SKY_WOOD, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.SKY_WOOD, 1.0F, -2.8F))));
    public static final PickaxeItem STELLAR_STONE_PICKAXE = register("stellar_stone_pickaxe", new PickaxeItem(ToolMaterials.STELLAR_STONE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.STELLAR_STONE, 1.0F, -2.8F))));
    public static final PickaxeItem RUBY_PICKAXE = register("ruby_pickaxe", new PickaxeItem(ToolMaterials.RUBY, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.RUBY, 1.0F, -2.8F))));
    public static final PickaxeItem AZURITE_PICKAXE = register("azurite_pickaxe", new PickaxeItem(ToolMaterials.AZURITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.AZURITE, 1.0F, -2.8F))));
    public static final PickaxeItem MAGMATIC_GEL_PICKAXE = register("magmatic_gel_pickaxe", new PickaxeItem(ToolMaterials.MAGMATIC_GEL, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.MAGMATIC_GEL, 1.0F, -2.8F))));
    public static final PickaxeItem OBSIDIAN_PICKAXE = register("obsidian_pickaxe", new PickaxeItem(ToolMaterials.OBSIDIAN, new Item.Settings().rarity(Rarity.EPIC).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 1.0F, -2.8F))));
    public static final PickaxeItem VOLUCITE_PICKAXE = register("volucite_pickaxe", new EffectPickaxeItem(ToolMaterials.VOLUCITE, new Item.Settings().rarity(AerialHellRarities.VIBRANT).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.VOLUCITE, 1.0F, -2.8F))));
    public static final PickaxeItem LUNATIC_PICKAXE = register("lunatic_pickaxe", new PickaxeItem(ToolMaterials.LUNATIC, new Item.Settings().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.LUNATIC, 1.0F, -2.8F))));
    public static final PickaxeItem ARSONIST_PICKAXE = register("arsonist_pickaxe", new PickaxeItem(ToolMaterials.ARSONIST, new Item.Settings().fireproof().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.ARSONIST, 1.0F, -2.8F))));

    public static final PickaxeItem MAGMA_CUBE_PICKAXE = register("magma_cube_pickaxe", new EffectPickaxeItem(ToolMaterials.OBSIDIAN, new Item.Settings().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 1.0F, -2.8F))));
    public static final PickaxeItem STELLAR_STONE_BREAKER = register("stellar_stone_breaker", new AerialHellPickaxeItem(ToolMaterials.BREAKER, new Item.Settings().rarity(Rarity.EPIC).attributeModifiers(PickaxeItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 1.0F, -2.8F))));

    public static final ShovelItem SKY_WOOD_SHOVEL = register("sky_wood_shovel", new ShovelItem(ToolMaterials.SKY_WOOD, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.SKY_WOOD, 1.5F, -3.0F))));
    public static final ShovelItem STELLAR_STONE_SHOVEL = register("stellar_stone_shovel", new ShovelItem(ToolMaterials.STELLAR_STONE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.STELLAR_STONE, 1.5F, -3.0F))));
    public static final ShovelItem RUBY_SHOVEL = register("ruby_shovel", new ShovelItem(ToolMaterials.RUBY, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.RUBY, 1.5F, -3.0F))));
    public static final ShovelItem AZURITE_SHOVEL = register("azurite_shovel", new ShovelItem(ToolMaterials.AZURITE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.AZURITE, 1.5F, -3.0F))));
    public static final ShovelItem MAGMATIC_GEL_SHOVEL = register("magmatic_gel_shovel", new ShovelItem(ToolMaterials.MAGMATIC_GEL, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.MAGMATIC_GEL, 1.5F, -3.0F))));
    public static final ShovelItem OBSIDIAN_SHOVEL = register("obsidian_shovel", new ShovelItem(ToolMaterials.OBSIDIAN, new Item.Settings().rarity(Rarity.EPIC).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 1.5F, -3.0F))));
    public static final ShovelItem VOLUCITE_SHOVEL = register("volucite_shovel", new EffectShovelItem(ToolMaterials.VOLUCITE, new Item.Settings().rarity(AerialHellRarities.VIBRANT).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 1.5F, -3.0F))));
    public static final ShovelItem LUNATIC_SHOVEL = register("lunatic_shovel", new ShovelItem(ToolMaterials.LUNATIC, new Item.Settings().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.LUNATIC, 1.5F, -3.0F))));
    public static final ShovelItem ARSONIST_SHOVEL = register("arsonist_shovel", new ShovelItem(ToolMaterials.ARSONIST, new Item.Settings().fireproof().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.ARSONIST, 1.5F, -3.0F))));

    public static final ShovelItem MAGMA_CUBE_SHOVEL = register("magma_cube_shovel", new EffectShovelItem(ToolMaterials.OBSIDIAN, new Item.Settings().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(ShovelItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 1.5F, -3.0F))));

    public static final AxeItem SKY_WOOD_AXE = register("sky_wood_axe", new AxeItem(ToolMaterials.SKY_WOOD, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.SKY_WOOD, 6.0F, -3.1F))));
    public static final AxeItem STELLAR_STONE_AXE = register("stellar_stone_axe", new AxeItem(ToolMaterials.STELLAR_STONE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.STELLAR_STONE, 6.0F, -3.1F))));
    public static final AxeItem RUBY_AXE = register("ruby_axe", new AxeItem(ToolMaterials.RUBY, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.RUBY, 6.0F, -3.1F))));
    public static final AxeItem AZURITE_AXE = register("azurite_axe", new AxeItem(ToolMaterials.AZURITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.AZURITE, 6.0F, -3.1F))));
    public static final AxeItem MAGMATIC_GEL_AXE = register("magmatic_gel_axe", new AxeItem(ToolMaterials.MAGMATIC_GEL, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.MAGMATIC_GEL, 6.0F, -3.1F))));
    public static final AxeItem OBSIDIAN_AXE = register("obsidian_axe", new AxeItem(ToolMaterials.OBSIDIAN, new Item.Settings().rarity(Rarity.EPIC).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 6.0F, -3.1F))));
    public static final AxeItem VOLUCITE_AXE = register("volucite_axe", new EffectAxeItem(ToolMaterials.VOLUCITE, new Item.Settings().rarity(AerialHellRarities.VIBRANT).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.VOLUCITE, 6.0F, -3.1F))));
    public static final AxeItem LUNATIC_AXE = register("lunatic_axe", new AxeItem(ToolMaterials.LUNATIC, new Item.Settings().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.LUNATIC, 6.0F, -3.1F))));
    public static final AxeItem ARSONIST_AXE = register("arsonist_axe", new AxeItem(ToolMaterials.ARSONIST, new Item.Settings().fireproof().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.ARSONIST, 6.0F, -3.1F))));

    public static final AxeItem HEAVY_AXE = register("heavy_axe", new AerialHellAxeItem(ToolMaterials.HEAVY, new Item.Settings().rarity(Rarity.EPIC).attributeModifiers(ItemHelper.createAerialHellToolOrWeaponAttributes(ToolMaterials.HEAVY, 6.0F, -3.5F, -0.30F, 0.0F))));
    public static final AxeItem AXE_OF_LIGHT = register("axe_of_light", new EffectAxeItem(ToolMaterials.LUNATIC, new Item.Settings().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.LUNATIC, 5.0F, -3.1F))));
    public static final AxeItem CURSED_AXE = register("cursed_axe", new AerialHellAxeItem(ToolMaterials.SHADOW,  new Item.Settings().rarity(AerialHellRarities.CORRUPTED).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.SHADOW, 2.0F, -3.2F))));
    public static final AxeItem BERSERK_AXE = register("berserk_axe", new BerserkAxeItem(ToolMaterials.ARSONIST,  new Item.Settings().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.ARSONIST, 4.0F, -2.5F))));

    public static final HoeItem SKY_WOOD_HOE = register("sky_wood_hoe", new HoeItem(ToolMaterials.SKY_WOOD, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.SKY_WOOD, 0.0F, -3.0F))));
    public static final HoeItem STELLAR_STONE_HOE = register("stellar_stone_hoe", new HoeItem(ToolMaterials.STELLAR_STONE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.STELLAR_STONE, 0.0F, -3.0F))));
    public static final HoeItem RUBY_HOE = register("ruby_hoe", new HoeItem(ToolMaterials.RUBY, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.RUBY, 0.0F, -3.0F))));
    public static final HoeItem AZURITE_HOE = register("azurite_hoe", new HoeItem(ToolMaterials.AZURITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.AZURITE, 0.0F, -3.0F))));
    public static final HoeItem MAGMATIC_GEL_HOE = register("magmatic_gel_hoe", new HoeItem(ToolMaterials.MAGMATIC_GEL, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.MAGMATIC_GEL, 0.0F, -3.0F))));
    public static final HoeItem OBSIDIAN_HOE = register("obsidian_hoe", new HoeItem(ToolMaterials.OBSIDIAN, new Item.Settings().rarity(Rarity.EPIC).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 0.0F, -3.0F))));
    public static final HoeItem VOLUCITE_HOE = register("volucite_hoe", new EffectHoeItem(ToolMaterials.VOLUCITE, new Item.Settings().rarity(AerialHellRarities.VIBRANT).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.VOLUCITE, 0.0F, -3.0F))));
    public static final HoeItem LUNATIC_HOE = register("lunatic_hoe", new HoeItem(ToolMaterials.LUNATIC, new Item.Settings().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.LUNATIC, 0.0F, -1.0F))));
    public static final HoeItem ARSONIST_HOE = register("arsonist_hoe", new HoeItem(ToolMaterials.ARSONIST, new Item.Settings().fireproof().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.ARSONIST, 0.0F, -1.0F))));

    public static final HoeItem REAPER_SCYTHE = register("reaper_scythe", new EffectHoeItem(ToolMaterials.SHADOW, new Item.Settings().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.SHADOW, -2.8F, 4.0F))));

    //weapons
    public static final SwordItem SKY_WOOD_SWORD = register("sky_wood_sword", new SwordItem(ToolMaterials.SKY_WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.SKY_WOOD, 3, -2.4F))));
    public static final SwordItem STELLAR_STONE_SWORD = register("stellar_stone_sword", new SwordItem(ToolMaterials.STELLAR_STONE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.STELLAR_STONE, 3, -2.4F))));
    public static final SwordItem RUBY_SWORD = register("ruby_sword", new SwordItem(ToolMaterials.RUBY, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.RUBY, 3, -2.4F))));
    public static final SwordItem AZURITE_SWORD = register("azurite_sword", new SwordItem(ToolMaterials.AZURITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.AZURITE, 3, -2.4F))));
    public static final SwordItem MAGMATIC_GEL_SWORD = register("magmatic_gel_sword", new SwordItem(ToolMaterials.MAGMATIC_GEL, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.MAGMATIC_GEL, 3, -2.4F))));
    public static final SwordItem OBSIDIAN_SWORD = register("obsidian_sword", new SwordItem(ToolMaterials.OBSIDIAN, new Item.Settings().rarity(Rarity.EPIC).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 3, -2.4F))));
    public static final SwordItem VOLUCITE_SWORD = register("volucite_sword", new EffectSwordItem(ToolMaterials.VOLUCITE, new Item.Settings().rarity(AerialHellRarities.VIBRANT).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.VOLUCITE, 3, -2.4F))));
    public static final SwordItem LUNATIC_SWORD = register("lunatic_sword", new SwordItem(ToolMaterials.LUNATIC, new Item.Settings().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.LUNATIC, 3, -2.4F))));
    public static final SwordItem ARSONIST_SWORD = register("arsonist_sword", new AerialHellSwordItem(ToolMaterials.ARSONIST, new Item.Settings().fireproof().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.ARSONIST, 3, -2.4F))));

    public static final SwordItem HEAVY_SWORD = register("heavy_sword", new AerialHellSwordItem(ToolMaterials.HEAVY, new Item.Settings().fireproof().rarity(Rarity.EPIC).attributeModifiers(ItemHelper.createAerialHellToolOrWeaponAttributes(ToolMaterials.HEAVY, 3, -2.7F, -0.30F, 0.0F))));
    public static final SwordItem HEALTH_BOOST_SWORD = register("health_boost_sword", new AerialHellSwordItem(ToolMaterials.LUNATIC, new Item.Settings().fireproof().rarity(Rarity.EPIC).attributeModifiers(ItemHelper.createAerialHellToolOrWeaponAttributes(ToolMaterials.LUNATIC, 3, -2.4F, 0.0F, 4.0F))));
    public static final SwordItem NINJA_SWORD = register("ninja_sword", new EffectSwordItem(ToolMaterials.OBSIDIAN, new Item.Settings().fireproof().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(ItemHelper.createAerialHellToolOrWeaponAttributes(ToolMaterials.OBSIDIAN, 2, -1.6F, 0.15F, 0.0F))));
    public static final SwordItem NINJA_MASTER_SWORD = register("ninja_master_sword", new EffectSwordItem(ToolMaterials.OBSIDIAN, new Item.Settings().fireproof().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(ItemHelper.createAerialHellToolOrWeaponAttributes(ToolMaterials.OBSIDIAN, 4, -2.4F, 0.15F, 0.0F))));
    public static final SwordItem GLOUTON_SWORD = register("glouton_sword", new EffectSwordItem(ToolMaterials.RUBY, new Item.Settings().fireproof().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.RUBY, 3, -2.4F))));
    public static final SwordItem RANDOM_SWORD = register("random_sword", new EffectSwordItem(ToolMaterials.RUBY, new Item.Settings().fireproof().rarity(Rarity.EPIC).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.RUBY, 2, -2.4F))));
    public static final SwordItem DISLOYAL_SWORD = register("disloyal_sword", new AerialHellSwordItem(ToolMaterials.LUNATIC, new Item.Settings().fireproof().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.LUNATIC, 2, -2.4F))));
    public static final SwordItem CURSED_SWORD = register("cursed_sword", new AerialHellSwordItem(ToolMaterials.SHADOW, new Item.Settings().fireproof().rarity(AerialHellRarities.CORRUPTED).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.SHADOW, 1, -2.5F))));
    public static final SwordItem ABSOLUTE_ZERO_SWORD = register("absolute_zero_sword", new AerialHellSwordItem(ToolMaterials.LUNATIC, new Item.Settings().fireproof().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.LUNATIC, 2, -2.4F))));
    public static final SwordItem SWORD_OF_LIGHT = register("sword_of_light", new EffectSwordItem(ToolMaterials.LUNATIC, new Item.Settings().fireproof().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.LUNATIC, 2, -2.4F))));
    public static final SwordItem ANTIDOTE_SWORD = register("antidote_sword", new EffectSwordItem(ToolMaterials.RUBY, new Item.Settings().fireproof().rarity(Rarity.EPIC).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.RUBY, 4, -2.4F))));
    public static final SwordItem NETHERIAN_KING_SWORD = register("netherian_king_sword", new EffectSwordItem(ToolMaterials.OBSIDIAN, new Item.Settings().fireproof().rarity(AerialHellRarities.LEGENDARY).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.OBSIDIAN, 1, -2.4F))));
    public static final SwordItem GLASS_CANON_SWORD = register("glass_canon_sword", new EffectSwordItem(ToolMaterials.ARSONIST, new Item.Settings().fireproof().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.ARSONIST, 7, -1.6F))));
    public static final SwordItem GOD_SWORD = register("god_sword", new EffectSwordItem(ToolMaterials.ARSONIST, new Item.Settings().fireproof().rarity(AerialHellRarities.MYTHICAL).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.ARSONIST, 3, -2.4F))));

    public static final SwordItem FORGOTTEN_BATTLE_TRIDENT = register("forgotten_battle_trident", new ForgottenBattleTridentItem(ToolMaterials.VOLUCITE, new Item.Settings().maxDamage(1000).rarity(AerialHellRarities.LEGENDARY).attributeModifiers(ItemHelper.createAerialHellToolOrWeaponAttributes(ToolMaterials.VOLUCITE, 3, -2.9F, 0.2F, 0.0F))));

    //armor
    public static final ArmorItem RUBY_HELMET = register("ruby_helmet", new ArmorItem(AerialHellArmorMaterials.RUBY, ArmorItem.Type.HELMET, (new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15)))));
    public static final ArmorItem RUBY_CHESTPLATE = register("ruby_chestplate", new ArmorItem(AerialHellArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, (new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15)))));
    public static final ArmorItem RUBY_LEGGINGS = register("ruby_leggings", new ArmorItem(AerialHellArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, (new Item.Settings()).maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));
    public static final ArmorItem RUBY_BOOTS = register("ruby_boots", new ArmorItem(AerialHellArmorMaterials.RUBY, ArmorItem.Type.BOOTS, (new Item.Settings()).maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));

    public static final ArmorItem AZURITE_HELMET = register("azurite_helmet", new ArmorItem(AerialHellArmorMaterials.AZURITE, ArmorItem.Type.HELMET, (new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(10)))));
    public static final ArmorItem AZURITE_CHESTPLATE = register("azurite_chestplate", new ArmorItem(AerialHellArmorMaterials.AZURITE, ArmorItem.Type.CHESTPLATE, (new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(10)))));
    public static final ArmorItem AZURITE_LEGGINGS = register("azurite_leggings", new ArmorItem(AerialHellArmorMaterials.AZURITE, ArmorItem.Type.LEGGINGS, (new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(10)))));
    public static final ArmorItem AZURITE_BOOTS = register("azurite_boots", new ArmorItem(AerialHellArmorMaterials.AZURITE, ArmorItem.Type.BOOTS, (new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(10)))));

    public static final ArmorItem OBSIDIAN_HELMET = register("obsidian_helmet", new ArmorItem(AerialHellArmorMaterials.OBSIDIAN, ArmorItem.Type.HELMET, (new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))).rarity(Rarity.EPIC)));
    public static final ArmorItem OBSIDIAN_CHESTPLATE = register("obsidian_chestplate", new ArmorItem(AerialHellArmorMaterials.OBSIDIAN, ArmorItem.Type.CHESTPLATE, (new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(37))).rarity(Rarity.EPIC)));
    public static final ArmorItem OBSIDIAN_LEGGINGS = register("obsidian_leggings", new ArmorItem(AerialHellArmorMaterials.OBSIDIAN, ArmorItem.Type.LEGGINGS, (new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(37))).rarity(Rarity.EPIC)));
    public static final ArmorItem OBSIDIAN_BOOTS = register("obsidian_boots", new ArmorItem(AerialHellArmorMaterials.OBSIDIAN, ArmorItem.Type.BOOTS, (new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(37))).rarity(Rarity.EPIC)));

    public static final ArmorItem VOLUCITE_HELMET = register("volucite_helmet", new ArmorItem(AerialHellArmorMaterials.VOLUCITE, ArmorItem.Type.HELMET, (new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(35))).rarity(AerialHellRarities.VIBRANT)));
    public static final ArmorItem VOLUCITE_CHESTPLATE = register("volucite_chestplate", new ArmorItem(AerialHellArmorMaterials.VOLUCITE, ArmorItem.Type.CHESTPLATE, (new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(35))).rarity(AerialHellRarities.VIBRANT)));
    public static final ArmorItem VOLUCITE_LEGGINGS = register("volucite_leggings", new ArmorItem(AerialHellArmorMaterials.VOLUCITE, ArmorItem.Type.LEGGINGS, (new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(35))).rarity(AerialHellRarities.VIBRANT)));
    public static final ArmorItem VOLUCITE_BOOTS = register("volucite_boots", new ArmorItem(AerialHellArmorMaterials.VOLUCITE, ArmorItem.Type.BOOTS, (new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(35))).rarity(AerialHellRarities.VIBRANT)));

    public static final ArmorItem MAGMATIC_GEL_HELMET = register("magmatic_gel_helmet", new ArmorItem(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorItem.Type.HELMET, (new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(7)))));
    public static final ArmorItem MAGMATIC_GEL_CHESTPLATE = register("magmatic_gel_chestplate", new ArmorItem(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorItem.Type.CHESTPLATE, (new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(7)))));
    public static final ArmorItem MAGMATIC_GEL_LEGGINGS = register("magmatic_gel_leggings", new ArmorItem(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorItem.Type.LEGGINGS, (new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(7)))));
    public static final ArmorItem MAGMATIC_GEL_BOOTS = register("magmatic_gel_boots", new ArmorItem(AerialHellArmorMaterials.MAGMATIC_GEL, ArmorItem.Type.BOOTS, (new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(7)))));

    public static final ArmorItem LUNATIC_HELMET = register("lunatic_helmet", new ArmorItem(AerialHellArmorMaterials.LUNATIC, ArmorItem.Type.HELMET, (new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(33))).rarity(AerialHellRarities.LEGENDARY)));
    public static final ArmorItem LUNATIC_CHESTPLATE = register("lunatic_chestplate", new ArmorItem(AerialHellArmorMaterials.LUNATIC, ArmorItem.Type.CHESTPLATE, (new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(33))).rarity(AerialHellRarities.LEGENDARY)));
    public static final ArmorItem LUNATIC_LEGGINGS = register("lunatic_leggings", new ArmorItem(AerialHellArmorMaterials.LUNATIC, ArmorItem.Type.LEGGINGS, (new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(33))).rarity(AerialHellRarities.LEGENDARY)));
    public static final ArmorItem LUNATIC_BOOTS = register("lunatic_boots", new ArmorItem(AerialHellArmorMaterials.LUNATIC, ArmorItem.Type.BOOTS, (new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(33))).rarity(AerialHellRarities.LEGENDARY)));

    public static final ArmorItem ARSONIST_HELMET = register("arsonist_helmet", new ArmorItem(AerialHellArmorMaterials.ARSONIST, ArmorItem.Type.HELMET, (new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(37))).rarity(AerialHellRarities.MYTHICAL).fireproof()));
    public static final ArmorItem ARSONIST_CHESTPLATE = register("arsonist_chestplate", new ArmorItem(AerialHellArmorMaterials.ARSONIST, ArmorItem.Type.CHESTPLATE, (new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(37))).rarity(AerialHellRarities.MYTHICAL).fireproof()));
    public static final ArmorItem ARSONIST_LEGGINGS = register("arsonist_leggings", new ArmorItem(AerialHellArmorMaterials.ARSONIST, ArmorItem.Type.LEGGINGS, (new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(37))).rarity(AerialHellRarities.MYTHICAL).fireproof()));
    public static final ArmorItem ARSONIST_BOOTS = register("arsonist_boots", new ArmorItem(AerialHellArmorMaterials.ARSONIST, ArmorItem.Type.BOOTS, (new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(37))).rarity(AerialHellRarities.MYTHICAL).fireproof()));

    public static final ArmorItem SHADOW_HELMET = register("shadow_helmet", new ShadowArmorItem(AerialHellArmorMaterials.SHADOW, ArmorItem.Type.HELMET, (new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(25))).rarity(AerialHellRarities.CORRUPTED)));
    public static final ArmorItem SHADOW_CHESTPLATE = register("shadow_chestplate", new ShadowArmorItem(AerialHellArmorMaterials.SHADOW, ArmorItem.Type.CHESTPLATE, (new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(25))).rarity(AerialHellRarities.CORRUPTED)));
    public static final ArmorItem SHADOW_LEGGINGS = register("shadow_leggings", new ShadowArmorItem(AerialHellArmorMaterials.SHADOW, ArmorItem.Type.LEGGINGS, (new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(25))).rarity(AerialHellRarities.CORRUPTED)));
    public static final ArmorItem SHADOW_BOOTS = register("shadow_boots", new ShadowArmorItem(AerialHellArmorMaterials.SHADOW, ArmorItem.Type.BOOTS, (new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(25))).rarity(AerialHellRarities.CORRUPTED)));

    //effect totems
    public static final Item REGENERATION_TOTEM = register("regeneration_totem", new EffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item SPEED_TOTEM = register("speed_totem", new EffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item SPEED_II_TOTEM = register("speed_ii_totem", new EnchantedEffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item NIGHT_VISION_TOTEM = register("night_vision_totem", new EffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.LEGENDARY)));
    public static final Item AGILITY_TOTEM = register("agility_totem", new EnchantedEffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item HERO_TOTEM = register("hero_totem", new EffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item HEAD_IN_THE_CLOUDS_TOTEM = register("head_in_the_clouds_totem", new EffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.VIBRANT)));
    public static final Item GOD_TOTEM = register("god_totem", new EnchantedEffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.MYTHICAL).fireproof()));
    public static final Item CURSED_TOTEM = register("cursed_totem", new EffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final Item SHADOW_TOTEM = register("shadow_totem", new EffectTotemItem(new Item.Settings().maxCount(1).rarity(AerialHellRarities.CORRUPTED)));

    //spawn eggs
    public static final Item STELLAR_STONE_AUTOMATON_SPAWN_EGG = register("stellar_stone_automaton_spawn_egg", new SpawnEggItem(AerialHellEntities.STELLAR_STONE_AUTOMATON, -1, -1, new Item.Settings().rarity(AerialHellRarities.VIBRANT)));
    public static final Item EVIL_COW_SPAWN_EGG = register("evil_cow_spawn_egg", new SpawnEggItem(AerialHellEntities.EVIL_COW, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item CORTINARIUS_COW_SPAWN_EGG = register("cortinarius_cow_spawn_egg", new SpawnEggItem(AerialHellEntities.CORTINARIUS_COW, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item STELLAR_ENT_SPAWN_EGG = register("stellar_ent_spawn_egg", new SpawnEggItem(AerialHellEntities.STELLAR_ENT, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item VENOMOUS_SNAKE_SPAWN_EGG = register("venomous_snake_spawn_egg", new SpawnEggItem(AerialHellEntities.VENOMOUS_SNAKE, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item WORM_SPAWN_EGG = register("worm_spawn_egg", new SpawnEggItem(AerialHellEntities.WORM, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item STELLAR_CHICKEN_SPAWN_EGG = register("stellar_chicken_spawn_egg", new SpawnEggItem(AerialHellEntities.STELLAR_CHICKEN, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item STELLAR_BOAR_SPAWN_EGG = register("stellar_boar_spawn_egg", new SpawnEggItem(AerialHellEntities.STELLAR_BOAR, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item SHROOMBOOM_SPAWN_EGG = register("shroomboom_spawn_egg", new SpawnEggItem(AerialHellEntities.SHROOMBOOM, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item VERDIGRIS_ZOMBIE_SPAWN_EGG = register("verdigris_zombie_spawn_egg", new SpawnEggItem(AerialHellEntities.VERDIGRIS_ZOMBIE, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item MUMMY_SPAWN_EGG = register("mummy_spawn_egg", new SpawnEggItem(AerialHellEntities.MUMMY, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item SLIME_PIRATE_SPAWN_EGG = register("slime_pirate_spawn_egg", new SpawnEggItem(AerialHellEntities.SLIME_PIRATE, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item SLIME_NINJA_PIRATE_SPAWN_EGG = register("slime_ninja_pirate_spawn_egg", new SpawnEggItem(AerialHellEntities.SLIME_NINJA_PIRATE, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item GHOST_SLIME_PIRATE_SPAWN_EGG = register("ghost_slime_pirate_spawn_egg", new SpawnEggItem(AerialHellEntities.GHOST_SLIME_PIRATE, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG = register("ghost_slime_ninja_pirate_spawn_egg", new SpawnEggItem(AerialHellEntities.GHOST_SLIME_NINJA_PIRATE, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item SANDY_SHEEP_SPAWN_EGG = register("sandy_sheep_spawn_egg", new SpawnEggItem(AerialHellEntities.SANDY_SHEEP, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item GLIDING_TURTLE_SPAWN_EGG = register("gliding_turtle_spawn_egg", new SpawnEggItem(AerialHellEntities.GLIDING_TURTLE, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item FAT_PHANTOM_SPAWN_EGG = register("fat_phantom_spawn_egg", new SpawnEggItem(AerialHellEntities.FAT_PHANTOM, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item KODAMA_SPAWN_EGG = register("kodama_spawn_egg", new SpawnEggItem(AerialHellEntities.KODAMA, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item MUD_GOLEM_SPAWN_EGG = register("mud_golem_spawn_egg", new SpawnEggItem(AerialHellEntities.MUD_GOLEM, -1, -1, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item MUD_SOLDIER_SPAWN_EGG = register("mud_soldier_spawn_egg", new SpawnEggItem(AerialHellEntities.MUD_SOLDIER, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item MUD_CYCLE_MAGE_SPAWN_EGG = register("mud_cycle_mage_spawn_egg", new BossSpawnEggItem(AerialHellEntities.MUD_CYCLE_MAGE, -1, -1, new Item.Settings().rarity(AerialHellRarities.LEGENDARY)));
    public static final Item HELL_SPIDER_SPAWN_EGG = register("hell_spider_spawn_egg", new SpawnEggItem(AerialHellEntities.HELL_SPIDER, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item CRYSTAL_GOLEM_SPAWN_EGG = register("crystal_golem_spawn_egg", new SpawnEggItem(AerialHellEntities.CRYSTAL_GOLEM, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item CRYSTAL_SLIME_SPAWN_EGG = register("crystal_slime_spawn_egg", new SpawnEggItem(AerialHellEntities.CRYSTAL_SLIME, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item CRYSTAL_SPIDER_SPAWN_EGG = register("crystal_spider_spawn_egg", new SpawnEggItem(AerialHellEntities.CRYSTAL_SPIDER, -1, -1, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item LUNATIC_PRIEST_SPAWN_EGG = register("lunatic_priest_spawn_egg", new BossSpawnEggItem(AerialHellEntities.LUNATIC_PRIEST, -1, -1, new Item.Settings().rarity(AerialHellRarities.MYTHICAL)));
    public static final Item CRYSTAL_CATERPILLAR_SPAWN_EGG = register("crystal_caterpillar_spawn_egg", new SpawnEggItem(AerialHellEntities.CRYSTAL_CATERPILLAR, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item FOREST_CATERPILLAR_SPAWN_EGG = register("forest_caterpillar_spawn_egg", new SpawnEggItem(AerialHellEntities.FOREST_CATERPILLAR, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item TORN_SPIRIT_SPAWN_EGG = register("torn_spirit_spawn_egg", new SpawnEggItem(AerialHellEntities.TORN_SPIRIT, -1, -1, new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item CHAINED_GOD_SPAWN_EGG = register("chained_god_spawn_egg", new BossSpawnEggItem(AerialHellEntities.CHAINED_GOD, -1, -1, new Item.Settings().rarity(AerialHellRarities.MYTHICAL)));
    public static final Item ICE_SPIRIT_SPAWN_EGG = register("ice_spirit_spawn_egg", new SpawnEggItem(AerialHellEntities.ICE_SPIRIT, -1, -1, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FIRE_SPIRIT_SPAWN_EGG = register("fire_spirit_spawn_egg", new SpawnEggItem(AerialHellEntities.FIRE_SPIRIT, -1, -1, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item ELECTRO_SPIRIT_SPAWN_EGG = register("electro_spirit_spawn_egg", new SpawnEggItem(AerialHellEntities.ELECTRO_SPIRIT, -1, -1, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FLYING_JELLYFISH_SPAWN_EGG = register("flying_jellyfish_spawn_egg", new SpawnEggItem(AerialHellEntities.FLYING_JELLYFISH, -1, -1, new Item.Settings().rarity(Rarity.COMMON)));
    public static final Item SHADOW_FLYING_SKULL_SPAWN_EGG = register("shadow_flying_skull_spawn_egg", new SpawnEggItem(AerialHellEntities.SHADOW_FLYING_SKULL, -1, -1, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item SHADOW_TROLL_SPAWN_EGG = register("shadow_troll_spawn_egg", new SpawnEggItem(AerialHellEntities.SHADOW_TROLL, -1, -1, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item SHADOW_AUTOMATON_SPAWN_EGG = register("shadow_automaton_spawn_egg", new SpawnEggItem(AerialHellEntities.SHADOW_AUTOMATON, -1, -1, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item SHADOW_SPIDER_SPAWN_EGG = register("shadow_spider_spawn_egg", new SpawnEggItem(AerialHellEntities.SHADOW_SPIDER, -1, -1, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));
    public static final Item LILITH_SPAWN_EGG = register("lilith_spawn_egg", new BossSpawnEggItem(AerialHellEntities.LILITH, -1, -1, new Item.Settings().rarity(AerialHellRarities.CORRUPTED)));

    public static final Item AERIAL_HELL_PAINTING = register("aerial_hell_painting", new AerialHellHangingEntityItem(AerialHellEntities.AERIAL_HELL_PAINTING, new Item.Settings()));

    //build items
    public static final Item BLOCK_UPDATER = register("block_updater", new BlockUpdaterItem(new Item.Settings()));
    public static final Item BLOCK_CRACKER = register("block_cracker", new BlockCrackerItem(new Item.Settings()));

    public static <T extends Item> T register(String name, T item) {return Registry.register(Registries.ITEM, AerialHell.id(name), item);}

    public static void load() {}
}
