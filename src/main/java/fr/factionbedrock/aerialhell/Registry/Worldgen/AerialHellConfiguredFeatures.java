package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;

public class AerialHellConfiguredFeatures
{
    public static final RegistryKey<ConfiguredFeature<?, ?>> AERIAL_HELL_WATER_LAKE = createKey("aerial_hell_water_lake");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_STELLAR_GRASS = createKey("patch_stellar_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_STELLAR_GRASS_BALL = createKey("patch_stellar_grass_ball");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_BRAMBLES = createKey("patch_brambles");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_STELLAR_FERN = createKey("patch_stellar_fern");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_STELLAR_TALL_GRASS = createKey("patch_stellar_tall_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_STELLAR_TALL_FERN = createKey("patch_stellar_tall_fern");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_STELLAR_DEAD_BUSH = createKey("patch_stellar_dead_bush");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_AERIAL_BERRY_BUSH = createKey("patch_aerial_berry_bush");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_SHADOW_GRASS = createKey("patch_shadow_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_SHADOW_GRASS_BALL = createKey("patch_shadow_grass_ball");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_SHADOW_BRAMBLES = createKey("patch_shadow_brambles");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_PURPLISH_STELLAR_GRASS = createKey("patch_purplish_stellar_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_SKY_CACTUS = createKey("patch_sky_cactus");

    public static final RegistryKey<ConfiguredFeature<?, ?>> AERIAL_HELL_FLOWERS = createKey("aerial_hell_flowers");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AERIAL_HELL_BELLFLOWERS = createKey("aerial_hell_bellflowers");

    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_STICK_FRUIT_VINES = createKey("glowing_stick_fruit_vines");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLOSSOMING_VINES = createKey("blossoming_vines");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LAZULI_ROOTS = createKey("lazuli_roots");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_ROOTS = createKey("stellar_roots");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEAD_ROOTS = createKey("dead_roots");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_ROOTS = createKey("glowing_roots");

    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_DEAD_STELLAR_JUNGLE_TREE_LOG = createKey("large_dead_stellar_jungle_tree_log");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FULL_MOON_PLANT = createKey("full_moon_plant");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_CLIMBING_VINE = createKey("patch_climbing_vine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_STELLAR_SUGAR_CANE = createKey("patch_stellar_sugar_cane");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_STELLAR_VERY_TALL_GRASS = createKey("patch_stellar_very_tall_grass");

    public static final RegistryKey<ConfiguredFeature<?, ?>> AERIAL_TREE = createKey("aerial_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FOREST_AERIAL_TREE = createKey("forest_aerial_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COPPER_PINE = createKey("copper_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LAPIS_ROBINIA = createKey("lapis_robinia");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SHADOW_PINE = createKey("shadow_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PURPLE_SHADOW_PINE = createKey("purple_shadow_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_SHADOW_PINE = createKey("mega_shadow_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_PURPLE_SHADOW_PINE = createKey("mega_purple_shadow_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_JUNGLE_TREE = createKey("stellar_jungle_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_STELLAR_JUNGLE_TREE = createKey("mega_stellar_jungle_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GOLDEN_BEECH = createKey("golden_beech");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRYSTALLIZED_TREE = createKey("crystallized_tree");

    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_AERIAL_TREE = createKey("giant_aerial_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_GOLDEN_BEECH = createKey("giant_golden_beech");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_CRYSTALLIZED_TREE = createKey("giant_crystallized_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_COPPER_PINE = createKey("giant_copper_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_COPPER_PINE = createKey("huge_copper_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_LAPIS_ROBINIA = createKey("giant_lapis_robinia");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_SHADOW_PINE = createKey("giant_shadow_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_PURPLE_SHADOW_PINE = createKey("giant_purple_shadow_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_SHADOW_PINE = createKey("huge_shadow_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_SHADOW_PINE = createKey("huge_purple_shadow_pine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_STELLAR_JUNGLE_TREE = createKey("giant_stellar_jungle_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PETRIFIED_GIANT_AERIAL_TREE = createKey("petrified_giant_aerial_tree");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CORTINARIUS_VIOLACEUS_MUSHROOM_CAPS_COLUMN = createKey("cortinarius_violaceus_mushroom_caps_column");

    public static final RegistryKey<ConfiguredFeature<?, ?>> AERIAL_TREE_VEGETATION = createKey("aerial_tree_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FOREST_AERIAL_TREE_VEGETATION = createKey("forest_aerial_tree_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COPPER_PINE_VEGETATION = createKey("copper_pine_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LAPIS_ROBINIA_VEGETATION = createKey("lapis_robinia_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SHADOW_PINE_VEGETATION = createKey("shadow_pine_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PURPLE_SHADOW_PINE_VEGETATION = createKey("purple_shadow_pine_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_SHADOW_PINE_VEGETATION = createKey("mega_shadow_pine_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_PURPLE_SHADOW_PINE_VEGETATION = createKey("mega_purple_shadow_pine_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_JUNGLE_TREE_VEGETATION = createKey("stellar_jungle_tree_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_STELLAR_JUNGLE_TREE_VEGETATION = createKey("mega_stellar_jungle_tree_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GOLDEN_BEECH_VEGETATION = createKey("golden_beech_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRYSTALLIZED_TREE_VEGETATION = createKey("crystallized_tree_vegetation");

    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_CORTINARIUS_VIOLACEUS = createKey("giant_cortinarius_violaceus");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_VERDIGRIS_AGARIC = createKey("giant_verdigris_agaric");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_CORTINARIUS_VIOLACEUS = createKey("patch_cortinarius_violaceus");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_VERDIGRIS_AGARIC = createKey("patch_verdigris_agaric");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_VERDIGRIS_AGARIC = createKey("huge_verdigris_agaric");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_GANODERMA_APPLANATUM = createKey("giant_ganoderma_applanatum");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_RED_MUSHROOM = createKey("giant_red_mushroom");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_GLOWING_BOLETUS = createKey("patch_glowing_boletus");

    public static final RegistryKey<ConfiguredFeature<?, ?>> WHITE_SOLID_ETHER = createKey("white_solid_ether");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUE_SOLID_ETHER = createKey("blue_solid_ether");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GOLDEN_SOLID_ETHER = createKey("golden_solid_ether");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GREEN_SOLID_ETHER = createKey("green_solid_ether");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PURPLE_SOLID_ETHER = createKey("purple_solid_ether");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ROOT_BRIDGE = createKey("root_bridge");

    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_COARSE_FLOOR_IN_DARK_AREAS = createKey("stellar_coarse_floor_in_dark_areas");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_GRASS_IN_SHADOW_GRASS = createKey("stellar_coarse_floor_in_dark_areas");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_CRYSTAL_PODZOL = createKey("stellar_crystal_podzol");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_PODZOL = createKey("stellar_podzol");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MOSSY_STELLAR_COBBLESTONE_ROCK = createKey("mossy_stellar_cobblestone_rock");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DANGLING_CHAIN = createKey("dangling_chain");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SLIPPERY_SAND = createKey("slippery_sand");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLUE_MEANIE_FIELD = createKey("blue_meanie_field");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_WHEAT_FIELD = createKey("stellar_wheat_field");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRYSTAL_BLOB = createKey("crystal_blob");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SHADOW_CRYSTAL_BLOB = createKey("shadow_crystal_blob");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = createKey("stellar_stone_crystal_blob_in_dark_areas");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS = createKey("shadow_crystal_blob_in_dark_areas");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRYSTALLIZED_FIRE = createKey("cristallized_fire");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_CRYSTAL_BLOB = createKey("giant_crystal_blob");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_GLOWING_STELLAR_GRASS = createKey("patch_glowing_stellar_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_ROOT_SHROOM = createKey("giant_root_shroom");

    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_PORTAL_FRAME_ORE = createKey("stellar_portal_frame_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> IRON_STELLAR_ORE = createKey("iron_stellar_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GOLD_STELLAR_ORE = createKey("gold_stellar_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DIAMOND_STELLAR_ORE = createKey("diamond_stellar_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLUORITE_ORE = createKey("fluorite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE = createKey("ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MAGMATIC_GEL_ORE = createKey("magmatic_gel_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AZURITE_ORE = createKey("azurite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMOKY_QUARTZ_ORE = createKey("smoky_quartz_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VOLUCITE_ORE = createKey("volucite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> OBSIDIAN_ORE = createKey("obsidian_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLAUCOPHANITE_ORE = createKey("glaucophanite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_DIRT_ORE = createKey("stellar_dirt_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_COARSE_DIRT_ORE = createKey("stellar_coarse_dirt_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> STELLAR_CLAY_ORE = createKey("stellar_clay_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> SINGLE_PIECE_OF_STELLAR_GRASS = createKey("single_piece_of_stellar_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SINGLE_PIECE_OF_SHADOW_GRASS = createKey("single_piece_of_shadow_grass");

    public static final RegistryKey<ConfiguredFeature<?, ?>> GIANT_CORTINARIUS_VIOLACEUS_PLANTED = createKey("giant_cortinarius_violaceus_planted");

    public static RegistryKey<ConfiguredFeature<?, ?>> createKey(String name) {return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, AerialHell.id(name));}

    public static final class Lists
    {
        //lists of configured features using feature
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> AERIAL_HELL_LAKE_LIST = createList(AERIAL_HELL_WATER_LAKE);

        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> GIANT_GANODERMA_APPLANATUM_LIST = createList(GIANT_GANODERMA_APPLANATUM);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> HUGE_MUSHROOM_LIST = createList(HUGE_VERDIGRIS_AGARIC);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> TWISTING_ROOTS_LIST = createList(LAZULI_ROOTS, STELLAR_ROOTS, GLOWING_ROOTS, DEAD_ROOTS);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> LARGE_DEAD_STELLAR_JUNGLE_TREE_LOG_LIST = createList(LARGE_DEAD_STELLAR_JUNGLE_TREE_LOG);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> FULL_MOON_PLANT_LIST = createList(FULL_MOON_PLANT);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> VERTICAL_GROWING_PLANT_LIST = createList(PATCH_CLIMBING_VINE, PATCH_STELLAR_SUGAR_CANE, PATCH_STELLAR_VERY_TALL_GRASS);

        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> WHITE_SOLID_ETHER_LIST = createList(WHITE_SOLID_ETHER);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> BLUE_SOLID_ETHER_LIST = createList(BLUE_SOLID_ETHER);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> GOLDEN_SOLID_ETHER_LIST = createList(GOLDEN_SOLID_ETHER);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> GREEN_SOLID_ETHER_LIST = createList(GREEN_SOLID_ETHER);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> PURPLE_SOLID_ETHER_LIST = createList(PURPLE_SOLID_ETHER);

        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> ROOT_BRIDGE_LIST = createList(ROOT_BRIDGE);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> CLASSIC_GIANT_TREE_LIST = createList(GIANT_AERIAL_TREE, GIANT_GOLDEN_BEECH, GIANT_CRYSTALLIZED_TREE, GIANT_STELLAR_JUNGLE_TREE);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> FORKING_GIANT_TREE_TREE = createList(GIANT_LAPIS_ROBINIA);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> GIANT_PINE_TREE_LIST = createList(GIANT_COPPER_PINE, GIANT_PURPLE_SHADOW_PINE, GIANT_SHADOW_PINE, HUGE_COPPER_PINE, HUGE_PURPLE_SHADOW_PINE, HUGE_SHADOW_PINE);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> DEAD_GIANT_TREE_TREE_LIST = createList(PETRIFIED_GIANT_AERIAL_TREE);

        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> MUSHROOM_CAPS_COLUMN_LIST = createList(CORTINARIUS_VIOLACEUS_MUSHROOM_CAPS_COLUMN);

        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> FLOOR_TRANSFORMATION_LIST = createList(STELLAR_GRASS_IN_SHADOW_GRASS, STELLAR_COARSE_FLOOR_IN_DARK_AREAS, STELLAR_CRYSTAL_PODZOL, STELLAR_PODZOL);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> MOSSY_STELLAR_COBBLESTONE_ROCK_LIST = createList(MOSSY_STELLAR_COBBLESTONE_ROCK);

        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> DANGLING_CHAIN_LIST = createList(DANGLING_CHAIN);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> SLIPPERY_SAND_LIST = createList(SLIPPERY_SAND);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> NATURAL_FIELD_LIST = createList(BLUE_MEANIE_FIELD, STELLAR_WHEAT_FIELD);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> CRYSTAL_BLOB_LIST = createList(CRYSTAL_BLOB, SHADOW_CRYSTAL_BLOB);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> LITTLE_CRYSTAL_BLOB_IN_DARK_AREAS_LIST = createList(STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS, SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> CRYSTALLIZED_FIRE_LIST = createList(CRYSTALLIZED_FIRE);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> GIANT_CRYSTAL_BLOB_LIST = createList(GIANT_CRYSTAL_BLOB);

        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> RANDOM_PATCH_IN_DARK_AREA_LIST = createList(PATCH_GLOWING_STELLAR_GRASS);
        public static final List<RegistryKey<ConfiguredFeature<?, ?>>> SINGLE_BLOCK_NEEDING_SUPPORT_LIST = createList(GIANT_ROOT_SHROOM);

        public static List<RegistryKey<ConfiguredFeature<?, ?>>> createList(RegistryKey<ConfiguredFeature<?, ?>>... keys) {return List.of(keys);}
    }
}