package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class AerialHellPlacedFeatures
{
    public static final ResourceKey<PlacedFeature> STELLAR_PORTAL_FRAME_ORE = createKey("stellar_portal_frame_ore");
    public static final ResourceKey<PlacedFeature> IRON_STELLAR_ORE = createKey("iron_stellar_ore");
    public static final ResourceKey<PlacedFeature> GOLD_STELLAR_ORE = createKey("gold_stellar_ore");
    public static final ResourceKey<PlacedFeature> DIAMOND_STELLAR_ORE = createKey("diamond_stellar_ore");
    public static final ResourceKey<PlacedFeature> FLUORITE_ORE = createKey("fluorite_ore");
    public static final ResourceKey<PlacedFeature> RUBY_ORE = createKey("ruby_ore");
    public static final ResourceKey<PlacedFeature> MAGMATIC_GEL_ORE = createKey("magmatic_gel_ore");
    public static final ResourceKey<PlacedFeature> AZURITE_ORE = createKey("azurite_ore");
    public static final ResourceKey<PlacedFeature> SMOKY_QUARTZ_ORE = createKey("smoky_quartz_ore");
    public static final ResourceKey<PlacedFeature> VOLUCITE_ORE = createKey("volucite_ore");
    public static final ResourceKey<PlacedFeature> OBSIDIAN_ORE = createKey("obsidian_ore");
    public static final ResourceKey<PlacedFeature> GLAUCOPHANITE_ORE = createKey("glaucophanite_ore");
    public static final ResourceKey<PlacedFeature> STELLAR_DIRT_ORE = createKey("stellar_dirt_ore");
    public static final ResourceKey<PlacedFeature> STELLAR_COARSE_DIRT_ORE = createKey("stellar_coarse_dirt_ore");
    public static final ResourceKey<PlacedFeature> STELLAR_CLAY_ORE = createKey("stellar_clay_ore");

    public static final ResourceKey<PlacedFeature> AERIAL_HELL_WATER_LAKE = createKey("aerial_hell_water_lake");

    public static final ResourceKey<PlacedFeature> PATCH_STELLAR_GRASS = createKey("patch_stellar_grass");
    public static final ResourceKey<PlacedFeature> PATCH_STELLAR_GRASS_BALL = createKey("patch_stellar_grass_ball");
    public static final ResourceKey<PlacedFeature> PATCH_BRAMBLES = createKey("patch_brambles");
    public static final ResourceKey<PlacedFeature> PATCH_STELLAR_TALL_GRASS = createKey("patch_stellar_tall_grass");
    public static final ResourceKey<PlacedFeature> PATCH_STELLAR_FERN = createKey("patch_stellar_fern");
    public static final ResourceKey<PlacedFeature> PATCH_STELLAR_TALL_FERN = createKey("patch_stellar_tall_fern");
    public static final ResourceKey<PlacedFeature> PATCH_STELLAR_DEAD_BUSH = createKey("patch_stellar_dead_bush");
    public static final ResourceKey<PlacedFeature> PATCH_AERIAL_BERRY_BUSH = createKey("patch_aerial_berry_bush");
    public static final ResourceKey<PlacedFeature> PATCH_SHADOW_GRASS = createKey("patch_shadow_grass");
    public static final ResourceKey<PlacedFeature> PATCH_SHADOW_GRASS_BALL = createKey("patch_shadow_grass_ball");
    public static final ResourceKey<PlacedFeature> PATCH_SHADOW_BRAMBLES = createKey("patch_shadow_brambles");
    public static final ResourceKey<PlacedFeature> PATCH_PURPLISH_STELLAR_GRASS = createKey("patch_purplish_stellar_grass");
    public static final ResourceKey<PlacedFeature> PATCH_SKY_CACTUS = createKey("patch_sky_cactus");

    public static final ResourceKey<PlacedFeature> AERIAL_HELL_FLOWERS = createKey("aerial_hell_flowers");
    public static final ResourceKey<PlacedFeature> AERIAL_HELL_BELLFLOWERS = createKey("aerial_hell_bellflowers");

    public static final ResourceKey<PlacedFeature> GLOWING_STICK_FRUIT_VINES_RARE = createKey("glowing_stick_fruit_vines_rare");
    public static final ResourceKey<PlacedFeature> GLOWING_STICK_FRUIT_VINES = createKey("glowing_stick_fruit_vines");
    public static final ResourceKey<PlacedFeature> BLOSSOMING_VINES_RARE = createKey("blossoming_vines_rare");
    public static final ResourceKey<PlacedFeature> BLOSSOMING_VINES = createKey("blossoming_vines");
    public static final ResourceKey<PlacedFeature> LAZULI_ROOTS = createKey("lazuli_roots");
    public static final ResourceKey<PlacedFeature> STELLAR_ROOTS = createKey("stellar_roots");
    public static final ResourceKey<PlacedFeature> DEAD_ROOTS = createKey("dead_roots");

    public static final ResourceKey<PlacedFeature> AERIAL_TREE_CHECKED = createKey("aerial_tree_checked");
    public static final ResourceKey<PlacedFeature> FOREST_AERIAL_TREE_CHECKED = createKey("forest_aerial_tree_checked");
    public static final ResourceKey<PlacedFeature> COPPER_PINE_CHECKED = createKey("copper_pine_checked");
    public static final ResourceKey<PlacedFeature> LAPIS_ROBINIA_CHECKED = createKey("lapis_robinia_checked");
    public static final ResourceKey<PlacedFeature> GOLDEN_BEECH_CHECKED = createKey("golden_beech_checked");
    public static final ResourceKey<PlacedFeature> SHADOW_PINE_CHECKED = createKey("shadow_pine_checked");
    public static final ResourceKey<PlacedFeature> PURPLE_SHADOW_PINE_CHECKED = createKey("purple_shadow_pine_checked");
    public static final ResourceKey<PlacedFeature> MEGA_SHADOW_PINE_CHECKED = createKey("mega_shadow_pine_checked");
    public static final ResourceKey<PlacedFeature> MEGA_PURPLE_SHADOW_PINE_CHECKED = createKey("mega_purple_shadow_pine_checked");
    public static final ResourceKey<PlacedFeature> STELLAR_JUNGLE_TREE_CHECKED = createKey("stellar_jungle_tree_checked");
    public static final ResourceKey<PlacedFeature> MEGA_STELLAR_JUNGLE_TREE_CHECKED = createKey("mega_stellar_jungle_tree_checked");
    public static final ResourceKey<PlacedFeature> CRYSTALLIZED_TREE_CHECKED = createKey("crystallized_tree_checked");

    public static final ResourceKey<PlacedFeature> AERIAL_TREE_VEGETATION = createKey("aerial_tree_vegetation");
    public static final ResourceKey<PlacedFeature> FOREST_AERIAL_TREE_VEGETATION = createKey("forest_aerial_tree_vegetation");
    public static final ResourceKey<PlacedFeature> COPPER_PINE_VEGETATION = createKey("copper_pine_vegetation");
    public static final ResourceKey<PlacedFeature> COPPER_PINE_RARE_VEGETATION = createKey("copper_pine_rare_vegetation");
    public static final ResourceKey<PlacedFeature> LAPIS_ROBINIA_VEGETATION = createKey("lapis_robinia_vegetation");
    public static final ResourceKey<PlacedFeature> LAPIS_ROBINIA_RARE_VEGETATION = createKey("lapis_robinia_rare_vegetation");
    public static final ResourceKey<PlacedFeature> GOLDEN_BEECH_VEGETATION = createKey("golden_beech_vegetation");
    public static final ResourceKey<PlacedFeature> GOLDEN_BEECH_RARE_VEGETATION = createKey("golden_beech_rare_vegetation");
    public static final ResourceKey<PlacedFeature> SHADOW_PINE_VEGETATION = createKey("shadow_pine_vegetation");
    public static final ResourceKey<PlacedFeature> PURPLE_SHADOW_PINE_VEGETATION = createKey("purple_shadow_pine_vegetation");
    public static final ResourceKey<PlacedFeature> MEGA_SHADOW_PINE_VEGETATION = createKey("mega_shadow_pine_vegetation");
    public static final ResourceKey<PlacedFeature> MEGA_PURPLE_SHADOW_PINE_VEGETATION = createKey("mega_purple_shadow_pine_vegetation");
    public static final ResourceKey<PlacedFeature> STELLAR_JUNGLE_TREE_VEGETATION = createKey("stellar_jungle_tree_vegetation");
    public static final ResourceKey<PlacedFeature> MEGA_STELLAR_JUNGLE_TREE_VEGETATION = createKey("mega_stellar_jungle_tree_vegetation");
    public static final ResourceKey<PlacedFeature> CRYSTALLIZED_TREE_VEGETATION = createKey("crystallized_tree_vegetation");

    public static final ResourceKey<PlacedFeature> GIANT_CORTINARIUS_VIOLACEUS = createKey("giant_cortinarius_violaceus");
    public static final ResourceKey<PlacedFeature> GIANT_VERDIGRIS_AGARIC = createKey("giant_verdigris_agaric");
    public static final ResourceKey<PlacedFeature> PATCH_CORTINARIUS_VIOLACEUS_RARE = createKey("cortinarius_violaceus_rare");
    public static final ResourceKey<PlacedFeature> PATCH_CORTINARIUS_VIOLACEUS_FOREST = createKey("cortinarius_violaceus_forest");
    public static final ResourceKey<PlacedFeature> PATCH_VERDIGRIS_AGARIC_RARE = createKey("verdigris_agaric_rare");
    public static final ResourceKey<PlacedFeature> PATCH_VERDIGRIS_AGARIC_FOREST = createKey("verdigris_agaric_forest");
    public static final ResourceKey<PlacedFeature> HUGE_VERDIGRIS_AGARIC = createKey("huge_verdigris_agaric");
    public static final ResourceKey<PlacedFeature> GIANT_GANODERMA_APPLANATUM = createKey("giant_ganoderma_applanatum");
    public static final ResourceKey<PlacedFeature> GIANT_RED_MUSHROOM = createKey("giant_red_mushroom");
    public static final ResourceKey<PlacedFeature> PATCH_GLOWING_BOLETUS = createKey("patch_glowing_boletus");

    public static final ResourceKey<PlacedFeature> WHITE_SOLID_ETHER = createKey("white_solid_ether");
    public static final ResourceKey<PlacedFeature> BLUE_SOLID_ETHER = createKey("blue_solid_ether");
    public static final ResourceKey<PlacedFeature> GOLDEN_SOLID_ETHER = createKey("golden_solid_ether");
    public static final ResourceKey<PlacedFeature> GREEN_SOLID_ETHER = createKey("green_solid_ether");
    public static final ResourceKey<PlacedFeature> PURPLE_SOLID_ETHER = createKey("purple_solid_ether");

    public static final ResourceKey<PlacedFeature> STELLAR_COARSE_FLOOR_IN_DARK_AREAS = createKey("stellar_coarse_floor_in_dark_areas");
    public static final ResourceKey<PlacedFeature> STELLAR_GRASS_IN_SHADOW_GRASS = createKey("stellar_grass_in_shadow_grass");
    public static final ResourceKey<PlacedFeature> MOSSY_STELLAR_COBBLESTONE_ROCK = createKey("mossy_stellar_cobblestone_rock");
    public static final ResourceKey<PlacedFeature> SLIPPERY_SAND = createKey("slippery_sand");
    public static final ResourceKey<PlacedFeature> DANGLING_CHAIN_RARE = createKey("dangling_chain_rare");
    public static final ResourceKey<PlacedFeature> DANGLING_CHAIN = createKey("dangling_chain");
    public static final ResourceKey<PlacedFeature> CRYSTAL_BLOB = createKey("crystal_blob");
    public static final ResourceKey<PlacedFeature> SHADOW_CRYSTAL_BLOB = createKey("shadow_crystal_blob");
    public static final ResourceKey<PlacedFeature> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = createKey("stellar_stone_crystal_blob_in_dark_areas");
    public static final ResourceKey<PlacedFeature> SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS = createKey("shadow_crystal_blob_in_dark_areas");
    public static final ResourceKey<PlacedFeature> CRYSTALLIZED_FIRE = createKey("cristallized_fire");
    public static final ResourceKey<PlacedFeature> GIANT_CRYSTAL_BLOB = createKey("giant_crystal_blob");

    public static final ResourceKey<PlacedFeature> STELLAR_GRASS_BONEMEAL = createKey("stellar_grass_bonemeal");
    public static final ResourceKey<PlacedFeature> SHADOW_GRASS_BONEMEAL = createKey("shadow_grass_bonemeal");

    public static ResourceKey<PlacedFeature> createKey(String name) {return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(AerialHell.MODID, name));}

    /*public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, AerialHell.MODID);

    public static final RegistryObject<PlacedFeature> STELLAR_PORTAL_FRAME_ORE = PLACED_FEATURES.register("stellar_portal_frame_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.STELLAR_PORTAL_FRAME_ORE.getDelegate(), GenAerialHellOres.OrePlacements.STELLAR_PORTAL_FRAME_ORE));
    public static final RegistryObject<PlacedFeature> IRON_STELLAR_ORE = PLACED_FEATURES.register("iron_stellar_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.IRON_STELLAR_ORE.getDelegate(), GenAerialHellOres.OrePlacements.IRON_STELLAR_ORE));
    public static final RegistryObject<PlacedFeature> GOLD_STELLAR_ORE = PLACED_FEATURES.register("gold_stellar_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.GOLD_STELLAR_ORE.getDelegate(), GenAerialHellOres.OrePlacements.GOLD_STELLAR_ORE));
    public static final RegistryObject<PlacedFeature> DIAMOND_STELLAR_ORE = PLACED_FEATURES.register("diamond_stellar_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.DIAMOND_STELLAR_ORE.getDelegate(), GenAerialHellOres.OrePlacements.DIAMOND_STELLAR_ORE));
    public static final RegistryObject<PlacedFeature> FLUORITE_ORE = PLACED_FEATURES.register("fluorite_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.FLUORITE_ORE.getDelegate(), GenAerialHellOres.OrePlacements.FLUORITE_ORE));
    public static final RegistryObject<PlacedFeature> RUBY_ORE = PLACED_FEATURES.register("ruby_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.RUBY_ORE.getDelegate(), GenAerialHellOres.OrePlacements.RUBY_ORE));
    public static final RegistryObject<PlacedFeature> MAGMATIC_GEL_ORE = PLACED_FEATURES.register("magmatic_gel_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.MAGMATIC_GEL_ORE.getDelegate(), GenAerialHellOres.OrePlacements.MAGMATIC_GEL_ORE));
    public static final RegistryObject<PlacedFeature> AZURITE_ORE = PLACED_FEATURES.register("azurite_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.AZURITE_ORE.getDelegate(), GenAerialHellOres.OrePlacements.AZURITE_ORE));
    public static final RegistryObject<PlacedFeature> SMOKY_QUARTZ_ORE = PLACED_FEATURES.register("smoky_quartz_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.SMOKY_QUARTZ_ORE.getDelegate(), GenAerialHellOres.OrePlacements.SMOKY_QUARTZ_ORE));
    public static final RegistryObject<PlacedFeature> VOLUCITE_ORE = PLACED_FEATURES.register("volucite_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.VOLUCITE_ORE.getDelegate(), GenAerialHellOres.OrePlacements.VOLUCITE_ORE));
    public static final RegistryObject<PlacedFeature> OBSIDIAN_ORE = PLACED_FEATURES.register("obsidian_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.OBSIDIAN_ORE.getDelegate(), GenAerialHellOres.OrePlacements.OBSIDIAN_ORE));
    public static final RegistryObject<PlacedFeature> GLAUCOPHANITE_ORE = PLACED_FEATURES.register("glaucophanite_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.GLAUCOPHANITE_ORE.getDelegate(), GenAerialHellOres.OrePlacements.GLAUCOPHANITE_ORE));
    public static final RegistryObject<PlacedFeature> STELLAR_DIRT_ORE = PLACED_FEATURES.register("stellar_dirt_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.STELLAR_DIRT_ORE.getDelegate(), GenAerialHellOres.OrePlacements.STELLAR_DIRT_ORE));
    public static final RegistryObject<PlacedFeature> STELLAR_COARSE_DIRT_ORE = PLACED_FEATURES.register("stellar_coarse_dirt_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.STELLAR_COARSE_DIRT_ORE.getDelegate(), GenAerialHellOres.OrePlacements.STELLAR_COARSE_DIRT_ORE));
    public static final RegistryObject<PlacedFeature> STELLAR_CLAY_ORE = PLACED_FEATURES.register("stellar_clay_ore", () -> new PlacedFeature(AerialHellConfiguredFeatures.STELLAR_CLAY_ORE.getDelegate(), GenAerialHellOres.OrePlacements.STELLAR_CLAY_ORE));

    public static final RegistryObject<PlacedFeature> AERIAL_HELL_WATER_LAKE = PLACED_FEATURES.register("aerial_hell_water_lake", () -> new PlacedFeature(AerialHellConfiguredFeatures.AERIAL_HELL_WATER_LAKE.getDelegate(), List.of(CountPlacement.of(1), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(60), VerticalAnchor.top())), EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE), BlockPredicate.insideWorld(new BlockPos(0, -5, 0))), 32), BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_STELLAR_GRASS = PLACED_FEATURES.register("patch_stellar_grass", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_STELLAR_GRASS.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_STELLAR_GRASS_BALL = PLACED_FEATURES.register("patch_stellar_grass_ball", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_STELLAR_GRASS_BALL.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_BRAMBLES = PLACED_FEATURES.register("patch_brambles", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_BRAMBLES.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_STELLAR_TALL_GRASS = PLACED_FEATURES.register("patch_stellar_tall_grass", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_STELLAR_TALL_GRASS.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_STELLAR_FERN = PLACED_FEATURES.register("patch_stellar_fern", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_STELLAR_FERN.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_STELLAR_TALL_FERN = PLACED_FEATURES.register("patch_stellar_tall_fern", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_STELLAR_TALL_FERN.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_STELLAR_DEAD_BUSH = PLACED_FEATURES.register("patch_stellar_dead_bush", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_STELLAR_DEAD_BUSH.getDelegate(), List.of(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_AERIAL_BERRY_BUSH = PLACED_FEATURES.register("patch_aerial_berry_bush", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_AERIAL_BERRY_BUSH.getDelegate(), List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_SHADOW_GRASS = PLACED_FEATURES.register("patch_shadow_grass", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_SHADOW_GRASS.getDelegate(), List.of(CountPlacement.of(80), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_SHADOW_GRASS_BALL = PLACED_FEATURES.register("patch_shadow_grass_ball", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_SHADOW_GRASS_BALL.getDelegate(), List.of(CountPlacement.of(80), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_SHADOW_BRAMBLES = PLACED_FEATURES.register("patch_shadow_brambles", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_SHADOW_BRAMBLES.getDelegate(), List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_PURPLISH_STELLAR_GRASS = PLACED_FEATURES.register("patch_purplish_stellar_grass", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_PURPLISH_STELLAR_GRASS.getDelegate(), List.of(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_SKY_CACTUS = PLACED_FEATURES.register("patch_sky_cactus", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_SKY_CACTUS.getDelegate(), List.of(CountPlacement.of(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> AERIAL_HELL_FLOWERS = PLACED_FEATURES.register("aerial_hell_flowers", () -> new PlacedFeature(AerialHellConfiguredFeatures.AERIAL_HELL_FLOWERS.getDelegate(), List.of(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> AERIAL_HELL_BELLFLOWERS = PLACED_FEATURES.register("aerial_hell_bellflowers", () -> new PlacedFeature(AerialHellConfiguredFeatures.AERIAL_HELL_BELLFLOWERS.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> GLOWING_STICK_FRUIT_VINES_RARE = PLACED_FEATURES.register("glowing_stick_fruit_vines_rare", () -> new PlacedFeature(AerialHellConfiguredFeatures.GLOWING_STICK_FRUIT_VINES.getDelegate(), createVinesPlacementModifiers(10)));
    public static final RegistryObject<PlacedFeature> GLOWING_STICK_FRUIT_VINES = PLACED_FEATURES.register("glowing_stick_fruit_vines", () -> new PlacedFeature(AerialHellConfiguredFeatures.GLOWING_STICK_FRUIT_VINES.getDelegate(), createVinesPlacementModifiers(60)));
    public static final RegistryObject<PlacedFeature> BLOSSOMING_VINES_RARE = PLACED_FEATURES.register("blossoming_vines_rare", () -> new PlacedFeature(AerialHellConfiguredFeatures.BLOSSOMING_VINES.getDelegate(), createVinesPlacementModifiers(40)));
    public static final RegistryObject<PlacedFeature> BLOSSOMING_VINES = PLACED_FEATURES.register("blossoming_vines", () -> new PlacedFeature(AerialHellConfiguredFeatures.BLOSSOMING_VINES.getDelegate(), createVinesPlacementModifiers(100)));
    public static final RegistryObject<PlacedFeature> LAZULI_ROOTS = PLACED_FEATURES.register("lazuli_roots", () -> new PlacedFeature(AerialHellConfiguredFeatures.LAZULI_ROOTS.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> STELLAR_ROOTS = PLACED_FEATURES.register("stellar_roots", () -> new PlacedFeature(AerialHellConfiguredFeatures.STELLAR_ROOTS.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> DEAD_ROOTS = PLACED_FEATURES.register("dead_roots", () -> new PlacedFeature(AerialHellConfiguredFeatures.DEAD_ROOTS.getDelegate(), List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> AERIAL_TREE_CHECKED = PLACED_FEATURES.register("aerial_tree", () -> new PlacedFeature(AerialHellConfiguredFeatures.AERIAL_TREE.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.AERIAL_TREE_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> FOREST_AERIAL_TREE_CHECKED = PLACED_FEATURES.register("forest_aerial_tree", () -> new PlacedFeature(AerialHellConfiguredFeatures.FOREST_AERIAL_TREE.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.AERIAL_TREE_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> COPPER_PINE_CHECKED = PLACED_FEATURES.register("copper_pine", () -> new PlacedFeature(AerialHellConfiguredFeatures.COPPER_PINE.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.COPPER_PINE_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> LAPIS_ROBINIA_CHECKED = PLACED_FEATURES.register("lapis_robinia", () -> new PlacedFeature(AerialHellConfiguredFeatures.LAPIS_ROBINIA.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.LAPIS_ROBINIA_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> GOLDEN_BEECH_CHECKED = PLACED_FEATURES.register("golden_beech", () -> new PlacedFeature(AerialHellConfiguredFeatures.GOLDEN_BEECH.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.GOLDEN_BEECH_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> SHADOW_PINE_CHECKED = PLACED_FEATURES.register("shadow_pine", () -> new PlacedFeature(AerialHellConfiguredFeatures.SHADOW_PINE.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.SHADOW_PINE_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> PURPLE_SHADOW_PINE_CHECKED = PLACED_FEATURES.register("purple_shadow_pine", () -> new PlacedFeature(AerialHellConfiguredFeatures.PURPLE_SHADOW_PINE.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> MEGA_SHADOW_PINE_CHECKED = PLACED_FEATURES.register("mega_shadow_pine", () -> new PlacedFeature(AerialHellConfiguredFeatures.MEGA_SHADOW_PINE.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.SHADOW_PINE_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> MEGA_PURPLE_SHADOW_PINE_CHECKED = PLACED_FEATURES.register("mega_purple_shadow_pine", () -> new PlacedFeature(AerialHellConfiguredFeatures.MEGA_PURPLE_SHADOW_PINE.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_SAPLING.get()))));
    public static final RegistryObject<PlacedFeature> CRYSTALLIZED_TREE_CHECKED = PLACED_FEATURES.register("crystallized_tree", () -> new PlacedFeature(AerialHellConfiguredFeatures.CRYSTALLIZED_TREE.getDelegate(), List.of(PlacementUtils.filteredByBlockSurvival(AerialHellBlocksAndItems.AERIAL_TREE_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> AERIAL_TREE_VEGETATION = PLACED_FEATURES.register("aerial_tree_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.AERIAL_TREE_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 2))));
    public static final RegistryObject<PlacedFeature> FOREST_AERIAL_TREE_VEGETATION = PLACED_FEATURES.register("forest_aerial_tree_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.FOREST_AERIAL_TREE_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.1f, 2))));
    public static final RegistryObject<PlacedFeature> COPPER_PINE_VEGETATION = PLACED_FEATURES.register("copper_pine_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.COPPER_PINE_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.1f, 2))));
    public static final RegistryObject<PlacedFeature> COPPER_PINE_RARE_VEGETATION = PLACED_FEATURES.register("copper_pine_rare_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.COPPER_PINE_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(16))));
    public static final RegistryObject<PlacedFeature> LAPIS_ROBINIA_VEGETATION = PLACED_FEATURES.register("lapis_robinia_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.LAPIS_ROBINIA_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 2))));
    public static final RegistryObject<PlacedFeature> LAPIS_ROBINIA_RARE_VEGETATION = PLACED_FEATURES.register("lapis_robinia_rare_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.LAPIS_ROBINIA_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(16))));
    public static final RegistryObject<PlacedFeature> GOLDEN_BEECH_VEGETATION = PLACED_FEATURES.register("golden_beech_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.GOLDEN_BEECH_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 1))));
    public static final RegistryObject<PlacedFeature> GOLDEN_BEECH_RARE_VEGETATION = PLACED_FEATURES.register("golden_beech_rare_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.GOLDEN_BEECH_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(16))));
    public static final RegistryObject<PlacedFeature> SHADOW_PINE_VEGETATION = PLACED_FEATURES.register("shadow_pine_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.SHADOW_PINE_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2))));
    public static final RegistryObject<PlacedFeature> PURPLE_SHADOW_PINE_VEGETATION = PLACED_FEATURES.register("purple_shadow_pine_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.PURPLE_SHADOW_PINE_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2))));
    public static final RegistryObject<PlacedFeature> MEGA_SHADOW_PINE_VEGETATION = PLACED_FEATURES.register("mega_shadow_pine_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.MEGA_SHADOW_PINE_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 2))));
    public static final RegistryObject<PlacedFeature> MEGA_PURPLE_SHADOW_PINE_VEGETATION = PLACED_FEATURES.register("mega_purple_shadow_pine_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.MEGA_PURPLE_SHADOW_PINE_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 2))));
    public static final RegistryObject<PlacedFeature> CRYSTALLIZED_TREE_VEGETATION = PLACED_FEATURES.register("crystallized_tree_vegetation", () -> new PlacedFeature(AerialHellConfiguredFeatures.CRYSTALLIZED_TREE_VEGETATION.getDelegate(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2))));

    public static final RegistryObject<PlacedFeature> GIANT_CORTINARIUS_VIOLACEUS = PLACED_FEATURES.register("giant_cortinarius_violaceus", () -> new PlacedFeature(AerialHellConfiguredFeatures.GIANT_CORTINARIUS_VIOLACEUS.getDelegate(), List.of(CountOnEveryLayerPlacement.of(5), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> GIANT_VERDIGRIS_AGARIC = PLACED_FEATURES.register("giant_verdigris_agaric", () -> new PlacedFeature(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC.getDelegate(), List.of(CountOnEveryLayerPlacement.of(1), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_CORTINARIUS_VIOLACEUS_RARE = PLACED_FEATURES.register("cortinarius_violaceus_rare", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_CORTINARIUS_VIOLACEUS.getDelegate(), List.of(CountPlacement.of(48), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_CORTINARIUS_VIOLACEUS_FOREST = PLACED_FEATURES.register("cortinarius_violaceus_forest", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_CORTINARIUS_VIOLACEUS.getDelegate(), List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_VERDIGRIS_AGARIC_RARE = PLACED_FEATURES.register("verdigris_agaric_rare", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_VERDIGRIS_AGARIC.getDelegate(), List.of(CountPlacement.of(24), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PATCH_VERDIGRIS_AGARIC_FOREST = PLACED_FEATURES.register("verdigris_agaric_forest", () -> new PlacedFeature(AerialHellConfiguredFeatures.PATCH_VERDIGRIS_AGARIC.getDelegate(), List.of(CountPlacement.of(32), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> HUGE_VERDIGRIS_AGARIC = PLACED_FEATURES.register("huge_verdigris_agaric", () -> new PlacedFeature(AerialHellConfiguredFeatures.HUGE_VERDIGRIS_AGARIC.getDelegate(), List.of(CountOnEveryLayerPlacement.of(2), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> GIANT_GANODERMA_APPLANATUM = PLACED_FEATURES.register("giant_ganoderma_applanatum", () -> new PlacedFeature(AerialHellConfiguredFeatures.GIANT_GANODERMA_APPLANATUM.getDelegate(), List.of(CountPlacement.of(64), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(50), VerticalAnchor.absolute(180))), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> GIANT_RED_MUSHROOM = PLACED_FEATURES.register("giant_red_mushroom", () -> new PlacedFeature(AerialHellConfiguredFeatures.GIANT_RED_MUSHROOM.getDelegate(), List.of(CountOnEveryLayerPlacement.of(4), BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> WHITE_SOLID_ETHER = PLACED_FEATURES.register("white_solid_ether", () -> new PlacedFeature(AerialHellConfiguredFeatures.WHITE_SOLID_ETHER.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(WhiteSolidEtherCloudFeature.getMinGenHeigh()), VerticalAnchor.absolute(WhiteSolidEtherCloudFeature.getMaxGenHeigh()))), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> BLUE_SOLID_ETHER = PLACED_FEATURES.register("blue_solid_ether", () -> new PlacedFeature(AerialHellConfiguredFeatures.BLUE_SOLID_ETHER.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(14), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(BlueSolidEtherCloudFeature.getMinGenHeigh()), VerticalAnchor.absolute(BlueSolidEtherCloudFeature.getMaxGenHeigh()))), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> GOLDEN_SOLID_ETHER = PLACED_FEATURES.register("golden_solid_ether", () -> new PlacedFeature(AerialHellConfiguredFeatures.GOLDEN_SOLID_ETHER.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(GoldenSolidEtherCloudFeature.getMinGenHeigh()), VerticalAnchor.absolute(GoldenSolidEtherCloudFeature.getMaxGenHeigh()))), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> GREEN_SOLID_ETHER = PLACED_FEATURES.register("green_solid_ether", () -> new PlacedFeature(AerialHellConfiguredFeatures.GREEN_SOLID_ETHER.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(22), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(GreenSolidEtherCloudFeature.getMinGenHeigh()), VerticalAnchor.absolute(GreenSolidEtherCloudFeature.getMaxGenHeigh()))), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> PURPLE_SOLID_ETHER = PLACED_FEATURES.register("purple_solid_ether", () -> new PlacedFeature(AerialHellConfiguredFeatures.PURPLE_SOLID_ETHER.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(20), VerticalAnchor.absolute(80))), BiomeFilter.biome()))); //because shadow biomes are not generating at high altitude, but purple solid ether clouds are supposed to generate at high altitude

    public static final RegistryObject<PlacedFeature> STELLAR_COARSE_FLOOR_IN_DARK_AREAS = PLACED_FEATURES.register("stellar_coarse_floor_in_dark_areas", () -> new PlacedFeature(AerialHellConfiguredFeatures.STELLAR_COARSE_FLOOR_IN_DARK_AREAS.getDelegate(), List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> STELLAR_GRASS_IN_SHADOW_GRASS = PLACED_FEATURES.register("stellar_grass_in_shadow_grass", () -> new PlacedFeature(AerialHellConfiguredFeatures.STELLAR_GRASS_IN_SHADOW_GRASS.getDelegate(), List.of(CountPlacement.of(48), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> MOSSY_STELLAR_COBBLESTONE_ROCK = PLACED_FEATURES.register("mossy_stellar_cobblestone_rock", () -> new PlacedFeature(AerialHellConfiguredFeatures.MOSSY_STELLAR_COBBLESTONE_ROCK.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> SLIPPERY_SAND = PLACED_FEATURES.register("slippery_sand", () -> new PlacedFeature(AerialHellConfiguredFeatures.SLIPPERY_SAND.getDelegate(), List.of(CountPlacement.of(28), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> DANGLING_CHAIN_RARE = PLACED_FEATURES.register("dangling_chain_rare", () -> new PlacedFeature(AerialHellConfiguredFeatures.DANGLING_CHAIN.getDelegate(), List.of(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> DANGLING_CHAIN = PLACED_FEATURES.register("dangling_chain", () -> new PlacedFeature(AerialHellConfiguredFeatures.DANGLING_CHAIN.getDelegate(), List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> CRYSTAL_BLOB = PLACED_FEATURES.register("crystal_blob", () -> new PlacedFeature(AerialHellConfiguredFeatures.CRYSTAL_BLOB.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> SHADOW_CRYSTAL_BLOB = PLACED_FEATURES.register("shadow_crystal_blob", () -> new PlacedFeature(AerialHellConfiguredFeatures.SHADOW_CRYSTAL_BLOB.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS = PLACED_FEATURES.register("stellar_stone_crystal_blob_in_dark_areas", () -> new PlacedFeature(AerialHellConfiguredFeatures.STELLAR_STONE_CRYSTAL_BLOB_IN_DARK_AREAS.getDelegate(), List.of(CountPlacement.of(5), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(10), VerticalAnchor.absolute(180))), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS = PLACED_FEATURES.register("shadow_crystal_blob_in_dark_areas", () -> new PlacedFeature(AerialHellConfiguredFeatures.SHADOW_CRYSTAL_BLOB_IN_DARK_AREAS.getDelegate(), List.of(CountPlacement.of(8), InSquarePlacement.spread(), HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(10), VerticalAnchor.absolute(180))), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> CRYSTALLIZED_FIRE = PLACED_FEATURES.register("cristallized_fire", () -> new PlacedFeature(AerialHellConfiguredFeatures.CRYSTALLIZED_FIRE.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> GIANT_CRYSTAL_BLOB = PLACED_FEATURES.register("giant_crystal_blob", () -> new PlacedFeature(AerialHellConfiguredFeatures.GIANT_CRYSTAL_BLOB.getDelegate(), List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    private static List<PlacementModifier> createVinesPlacementModifiers(int countPlacement)
    {
        return List.of(CountPlacement.of(countPlacement), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(AerialHellBlocksAndItems.BLOSSOMING_VINES.get().defaultBlockState(), BlockPos.ZERO)), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());
    }*/
}
