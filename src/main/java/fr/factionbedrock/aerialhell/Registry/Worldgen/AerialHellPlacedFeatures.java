package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;

public class AerialHellPlacedFeatures
{
    public static final RegistryKey<PlacedFeature> STELLAR_PORTAL_FRAME_ORE = createKey("stellar_portal_frame_ore");
    public static final RegistryKey<PlacedFeature> IRON_STELLAR_ORE = createKey("iron_stellar_ore");
    public static final RegistryKey<PlacedFeature> GOLD_STELLAR_ORE = createKey("gold_stellar_ore");
    public static final RegistryKey<PlacedFeature> DIAMOND_STELLAR_ORE = createKey("diamond_stellar_ore");
    public static final RegistryKey<PlacedFeature> FLUORITE_ORE = createKey("fluorite_ore");
    public static final RegistryKey<PlacedFeature> RUBY_ORE = createKey("ruby_ore");
    public static final RegistryKey<PlacedFeature> MAGMATIC_GEL_ORE = createKey("magmatic_gel_ore");
    public static final RegistryKey<PlacedFeature> AZURITE_ORE = createKey("azurite_ore");
    public static final RegistryKey<PlacedFeature> SMOKY_QUARTZ_ORE = createKey("smoky_quartz_ore");
    public static final RegistryKey<PlacedFeature> VOLUCITE_ORE = createKey("volucite_ore");
    public static final RegistryKey<PlacedFeature> OBSIDIAN_ORE = createKey("obsidian_ore");
    public static final RegistryKey<PlacedFeature> GLAUCOPHANITE_ORE = createKey("glaucophanite_ore");
    public static final RegistryKey<PlacedFeature> STELLAR_DIRT_ORE = createKey("stellar_dirt_ore");
    public static final RegistryKey<PlacedFeature> STELLAR_COARSE_DIRT_ORE = createKey("stellar_coarse_dirt_ore");
    public static final RegistryKey<PlacedFeature> STELLAR_CLAY_ORE = createKey("stellar_clay_ore");

    public static final RegistryKey<PlacedFeature> AERIAL_HELL_WATER_LAKE = createKey("aerial_hell_water_lake");

    public static final RegistryKey<PlacedFeature> PATCH_STELLAR_GRASS = createKey("patch_stellar_grass");
    public static final RegistryKey<PlacedFeature> PATCH_STELLAR_GRASS_BALL = createKey("patch_stellar_grass_ball");
    public static final RegistryKey<PlacedFeature> PATCH_BRAMBLES = createKey("patch_brambles");
    public static final RegistryKey<PlacedFeature> PATCH_STELLAR_TALL_GRASS = createKey("patch_stellar_tall_grass");
    public static final RegistryKey<PlacedFeature> PATCH_STELLAR_FERN = createKey("patch_stellar_fern");
    public static final RegistryKey<PlacedFeature> PATCH_STELLAR_TALL_FERN = createKey("patch_stellar_tall_fern");
    public static final RegistryKey<PlacedFeature> PATCH_STELLAR_DEAD_BUSH = createKey("patch_stellar_dead_bush");
    public static final RegistryKey<PlacedFeature> PATCH_AERIAL_BERRY_BUSH = createKey("patch_aerial_berry_bush");
    public static final RegistryKey<PlacedFeature> PATCH_SHADOW_GRASS = createKey("patch_shadow_grass");
    public static final RegistryKey<PlacedFeature> PATCH_SHADOW_GRASS_BALL = createKey("patch_shadow_grass_ball");
    public static final RegistryKey<PlacedFeature> PATCH_SHADOW_BRAMBLES = createKey("patch_shadow_brambles");
    public static final RegistryKey<PlacedFeature> PATCH_PURPLISH_STELLAR_GRASS = createKey("patch_purplish_stellar_grass");
    public static final RegistryKey<PlacedFeature> PATCH_SKY_CACTUS = createKey("patch_sky_cactus");

    public static final RegistryKey<PlacedFeature> AERIAL_HELL_FLOWERS = createKey("aerial_hell_flowers");
    public static final RegistryKey<PlacedFeature> AERIAL_HELL_BELLFLOWERS = createKey("aerial_hell_bellflowers");

    public static final RegistryKey<PlacedFeature> GLOWING_STICK_FRUIT_VINES_RARE = createKey("glowing_stick_fruit_vines_rare");
    public static final RegistryKey<PlacedFeature> GLOWING_STICK_FRUIT_VINES = createKey("glowing_stick_fruit_vines");
    public static final RegistryKey<PlacedFeature> BLOSSOMING_VINES_RARE = createKey("blossoming_vines_rare");
    public static final RegistryKey<PlacedFeature> BLOSSOMING_VINES = createKey("blossoming_vines");
    public static final RegistryKey<PlacedFeature> LAZULI_ROOTS = createKey("lazuli_roots");
    public static final RegistryKey<PlacedFeature> STELLAR_ROOTS = createKey("stellar_roots");
    public static final RegistryKey<PlacedFeature> DEAD_ROOTS = createKey("dead_roots");

    public static final RegistryKey<PlacedFeature> AERIAL_TREE_CHECKED = createKey("aerial_tree_checked");
    public static final RegistryKey<PlacedFeature> FOREST_AERIAL_TREE_CHECKED = createKey("forest_aerial_tree_checked");
    public static final RegistryKey<PlacedFeature> COPPER_PINE_CHECKED = createKey("copper_pine_checked");
    public static final RegistryKey<PlacedFeature> LAPIS_ROBINIA_CHECKED = createKey("lapis_robinia_checked");
    public static final RegistryKey<PlacedFeature> GOLDEN_BEECH_CHECKED = createKey("golden_beech_checked");
    public static final RegistryKey<PlacedFeature> SHADOW_PINE_CHECKED = createKey("shadow_pine_checked");
    public static final RegistryKey<PlacedFeature> PURPLE_SHADOW_PINE_CHECKED = createKey("purple_shadow_pine_checked");
    public static final RegistryKey<PlacedFeature> MEGA_SHADOW_PINE_CHECKED = createKey("mega_shadow_pine_checked");
    public static final RegistryKey<PlacedFeature> MEGA_PURPLE_SHADOW_PINE_CHECKED = createKey("mega_purple_shadow_pine_checked");
    public static final RegistryKey<PlacedFeature> STELLAR_JUNGLE_TREE_CHECKED = createKey("stellar_jungle_tree_checked");
    public static final RegistryKey<PlacedFeature> MEGA_STELLAR_JUNGLE_TREE_CHECKED = createKey("mega_stellar_jungle_tree_checked");
    public static final RegistryKey<PlacedFeature> CRYSTALLIZED_TREE_CHECKED = createKey("crystallized_tree_checked");

    public static final RegistryKey<PlacedFeature> AERIAL_TREE_VEGETATION = createKey("aerial_tree_vegetation");
    public static final RegistryKey<PlacedFeature> FOREST_AERIAL_TREE_VEGETATION = createKey("forest_aerial_tree_vegetation");
    public static final RegistryKey<PlacedFeature> COPPER_PINE_VEGETATION = createKey("copper_pine_vegetation");
    public static final RegistryKey<PlacedFeature> COPPER_PINE_RARE_VEGETATION = createKey("copper_pine_rare_vegetation");
    public static final RegistryKey<PlacedFeature> LAPIS_ROBINIA_VEGETATION = createKey("lapis_robinia_vegetation");
    public static final RegistryKey<PlacedFeature> LAPIS_ROBINIA_RARE_VEGETATION = createKey("lapis_robinia_rare_vegetation");
    public static final RegistryKey<PlacedFeature> GOLDEN_BEECH_VEGETATION = createKey("golden_beech_vegetation");
    public static final RegistryKey<PlacedFeature> GOLDEN_BEECH_RARE_VEGETATION = createKey("golden_beech_rare_vegetation");
    public static final RegistryKey<PlacedFeature> SHADOW_PINE_VEGETATION = createKey("shadow_pine_vegetation");
    public static final RegistryKey<PlacedFeature> PURPLE_SHADOW_PINE_VEGETATION = createKey("purple_shadow_pine_vegetation");
    public static final RegistryKey<PlacedFeature> MEGA_SHADOW_PINE_VEGETATION = createKey("mega_shadow_pine_vegetation");
    public static final RegistryKey<PlacedFeature> MEGA_PURPLE_SHADOW_PINE_VEGETATION = createKey("mega_purple_shadow_pine_vegetation");
    public static final RegistryKey<PlacedFeature> STELLAR_JUNGLE_TREE_VEGETATION = createKey("stellar_jungle_tree_vegetation");
    public static final RegistryKey<PlacedFeature> MEGA_STELLAR_JUNGLE_TREE_VEGETATION = createKey("mega_stellar_jungle_tree_vegetation");
    public static final RegistryKey<PlacedFeature> CRYSTALLIZED_TREE_VEGETATION = createKey("crystallized_tree_vegetation");

    public static final RegistryKey<PlacedFeature> GIANT_CORTINARIUS_VIOLACEUS = createKey("giant_cortinarius_violaceus");
    public static final RegistryKey<PlacedFeature> GIANT_VERDIGRIS_AGARIC = createKey("giant_verdigris_agaric");
    public static final RegistryKey<PlacedFeature> PATCH_CORTINARIUS_VIOLACEUS_RARE = createKey("cortinarius_violaceus_rare");
    public static final RegistryKey<PlacedFeature> PATCH_CORTINARIUS_VIOLACEUS_FOREST = createKey("cortinarius_violaceus_forest");
    public static final RegistryKey<PlacedFeature> PATCH_VERDIGRIS_AGARIC_RARE = createKey("verdigris_agaric_rare");
    public static final RegistryKey<PlacedFeature> PATCH_VERDIGRIS_AGARIC_FOREST = createKey("verdigris_agaric_forest");
    public static final RegistryKey<PlacedFeature> HUGE_VERDIGRIS_AGARIC = createKey("huge_verdigris_agaric");
    public static final RegistryKey<PlacedFeature> GIANT_GANODERMA_APPLANATUM = createKey("giant_ganoderma_applanatum");
    public static final RegistryKey<PlacedFeature> GIANT_RED_MUSHROOM = createKey("giant_red_mushroom");
    public static final RegistryKey<PlacedFeature> PATCH_GLOWING_BOLETUS = createKey("patch_glowing_boletus");

    public static final RegistryKey<PlacedFeature> WHITE_SOLID_ETHER = createKey("white_solid_ether");
    public static final RegistryKey<PlacedFeature> BLUE_SOLID_ETHER = createKey("blue_solid_ether");
    public static final RegistryKey<PlacedFeature> GOLDEN_SOLID_ETHER = createKey("golden_solid_ether");
    public static final RegistryKey<PlacedFeature> GREEN_SOLID_ETHER = createKey("green_solid_ether");
    public static final RegistryKey<PlacedFeature> PURPLE_SOLID_ETHER = createKey("purple_solid_ether");

    public static final RegistryKey<PlacedFeature> STELLAR_COARSE_FLOOR_IN_DARK_AREAS = createKey("stellar_coarse_floor_in_dark_areas");
    public static final RegistryKey<PlacedFeature> STELLAR_GRASS_IN_SHADOW_GRASS = createKey("stellar_grass_in_shadow_grass");
    public static final RegistryKey<PlacedFeature> MOSSY_STELLAR_COBBLESTONE_ROCK = createKey("mossy_stellar_cobblestone_rock");
    public static final RegistryKey<PlacedFeature> SLIPPERY_SAND = createKey("slippery_sand");
    public static final RegistryKey<PlacedFeature> DANGLING_CHAIN_RARE = createKey("dangling_chain_rare");
    public static final RegistryKey<PlacedFeature> DANGLING_CHAIN = createKey("dangling_chain");
    public static final RegistryKey<PlacedFeature> CRYSTAL_BLOB = createKey("crystal_blob");
    public static final RegistryKey<PlacedFeature> SHADOW_CRYSTAL_BLOB = createKey("shadow_crystal_blob");
    public static final RegistryKey<PlacedFeature> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = createKey("stellar_stone_crystal_blob_in_dark_areas");
    public static final RegistryKey<PlacedFeature> SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS = createKey("shadow_crystal_blob_in_dark_areas");
    public static final RegistryKey<PlacedFeature> CRYSTALLIZED_FIRE = createKey("cristallized_fire");
    public static final RegistryKey<PlacedFeature> GIANT_CRYSTAL_BLOB = createKey("giant_crystal_blob");

    public static final RegistryKey<PlacedFeature> STELLAR_GRASS_BONEMEAL = createKey("stellar_grass_bonemeal");
    public static final RegistryKey<PlacedFeature> SHADOW_GRASS_BONEMEAL = createKey("shadow_grass_bonemeal");

    public static RegistryKey<PlacedFeature> createKey(String name) {return RegistryKey.of(RegistryKeys.PLACED_FEATURE, AerialHell.id(name));}
}
