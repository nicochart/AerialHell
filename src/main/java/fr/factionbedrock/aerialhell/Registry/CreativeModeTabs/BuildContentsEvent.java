package fr.factionbedrock.aerialhell.Registry.CreativeModeTabs;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

public class BuildContentsEvent
{
    public static void buildContents()
    {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries ->
        {
            entries.add(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK);
            entries.add(AerialHellItems.STELLAR_LIGHTER);
        });

        ItemGroupEvents.modifyEntriesEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_BLOCKS)).register(entries ->
        {
            entries.add(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK);
            entries.add(AerialHellItems.STELLAR_LIGHTER);

            entries.add(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK);
            entries.add(AerialHellItems.STELLAR_PORTAL_FRAME_ORE);
            entries.add(AerialHellItems.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE);

            entries.add(AerialHellItems.FLUORITE_TORCH);
            entries.add(AerialHellItems.VOLUCITE_TORCH);
            entries.add(AerialHellItems.SHADOW_TORCH);
            entries.add(AerialHellItems.FLUORITE_LANTERN);
            entries.add(AerialHellItems.RUBY_LANTERN);
            entries.add(AerialHellItems.RUBY_FLUORITE_LANTERN);
            entries.add(AerialHellItems.VOLUCITE_LANTERN);
            entries.add(AerialHellItems.VOLUCITE_FLUORITE_LANTERN);

            entries.add(AerialHellItems.RUBY_CHAIN);
            entries.add(AerialHellItems.VOLUCITE_CHAIN);

            entries.add(AerialHellItems.GIANT_ROOT);

            entries.add(AerialHellItems.AERIAL_TREE_LOG);
            entries.add(AerialHellItems.STRIPPED_AERIAL_TREE_LOG);
            entries.add(AerialHellItems.AERIAL_TREE_WOOD);
            entries.add(AerialHellItems.STRIPPED_AERIAL_TREE_WOOD);
            entries.add(AerialHellItems.AERIAL_TREE_LEAVES);
            entries.add(AerialHellItems.AERIAL_TREE_PLANKS);
            entries.add(AerialHellItems.CHISELED_AERIAL_TREE_PLANKS);
            entries.add(AerialHellItems.AERIAL_TREE_BOOKSHELF);
            entries.add(AerialHellItems.AERIAL_TREE_SAPLING);

            entries.add(AerialHellItems.PETRIFIED_AERIAL_TREE_LOG);

            entries.add(AerialHellItems.GOLDEN_BEECH_LOG);
            entries.add(AerialHellItems.STRIPPED_GOLDEN_BEECH_LOG);
            entries.add(AerialHellItems.GOLDEN_BEECH_WOOD);
            entries.add(AerialHellItems.STRIPPED_GOLDEN_BEECH_WOOD);
            entries.add(AerialHellItems.GOLDEN_BEECH_LEAVES);
            entries.add(AerialHellItems.GOLDEN_BEECH_PLANKS);
            entries.add(AerialHellItems.CHISELED_GOLDEN_BEECH_PLANKS);
            entries.add(AerialHellItems.GOLDEN_BEECH_BOOKSHELF);
            entries.add(AerialHellItems.GOLDEN_BEECH_SAPLING);

            entries.add(AerialHellItems.COPPER_PINE_LOG);
            entries.add(AerialHellItems.STRIPPED_COPPER_PINE_LOG);
            entries.add(AerialHellItems.COPPER_PINE_WOOD);
            entries.add(AerialHellItems.STRIPPED_COPPER_PINE_WOOD);
            entries.add(AerialHellItems.COPPER_PINE_PLANKS);
            entries.add(AerialHellItems.COPPER_PINE_LEAVES);
            entries.add(AerialHellItems.COPPER_PINE_BOOKSHELF);
            entries.add(AerialHellItems.COPPER_PINE_SAPLING);

            entries.add(AerialHellItems.LAPIS_ROBINIA_LOG);
            entries.add(AerialHellItems.ENCHANTED_LAPIS_ROBINIA_LOG);
            entries.add(AerialHellItems.STRIPPED_LAPIS_ROBINIA_LOG);
            entries.add(AerialHellItems.LAPIS_ROBINIA_WOOD);
            entries.add(AerialHellItems.STRIPPED_LAPIS_ROBINIA_WOOD);
            entries.add(AerialHellItems.LAPIS_ROBINIA_LEAVES);
            entries.add(AerialHellItems.LAPIS_ROBINIA_PLANKS);
            entries.add(AerialHellItems.LAPIS_ROBINIA_BOOKSHELF);
            entries.add(AerialHellItems.LAPIS_ROBINIA_SAPLING);

            entries.add(AerialHellItems.SHADOW_PINE_LOG);
            entries.add(AerialHellItems.EYE_SHADOW_PINE_LOG);
            entries.add(AerialHellItems.STRIPPED_SHADOW_PINE_LOG);
            entries.add(AerialHellItems.SHADOW_PINE_WOOD);
            entries.add(AerialHellItems.STRIPPED_SHADOW_PINE_WOOD);
            entries.add(AerialHellItems.SHADOW_PINE_LEAVES);
            entries.add(AerialHellItems.PURPLE_SHADOW_PINE_LEAVES);
            entries.add(AerialHellItems.SHADOW_PINE_PLANKS);
            entries.add(AerialHellItems.SHADOW_PINE_BOOKSHELF);
            entries.add(AerialHellItems.SHADOW_PINE_SAPLING);
            entries.add(AerialHellItems.PURPLE_SHADOW_PINE_SAPLING);

            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_LOG);
            entries.add(AerialHellItems.STRIPPED_STELLAR_JUNGLE_TREE_LOG);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_WOOD);
            entries.add(AerialHellItems.STRIPPED_STELLAR_JUNGLE_TREE_WOOD);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_LEAVES);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_PLANKS);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_BOOKSHELF);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_SAPLING);
            entries.add(AerialHellItems.DEAD_STELLAR_JUNGLE_TREE_LOG);

            entries.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_STEM);
            entries.add(AerialHellItems.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM);
            entries.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM);
            entries.add(AerialHellItems.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM);
            entries.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK);
            entries.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_LIGHT);
            entries.add(AerialHellItems.CORTINARIUS_VIOLACEUS);
            entries.add(AerialHellItems.GLOWING_BOLETUS);
            entries.add(AerialHellItems.TALL_GLOWING_BOLETUS);
            entries.add(AerialHellItems.BLUE_MEANIE_CLUSTER);
            entries.add(AerialHellItems.GIANT_ROOT_SHROOM);

            entries.add(AerialHellItems.GIANT_VERDIGRIS_AGARIC_STEM);
            entries.add(AerialHellItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM);
            entries.add(AerialHellItems.GIANT_VERDIGRIS_AGARIC_BARK_STEM);
            entries.add(AerialHellItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM);
            entries.add(AerialHellItems.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK);
            entries.add(AerialHellItems.VERDIGRIS_AGARIC);

            entries.add(AerialHellItems.GIANT_GANODERMA_APPLANATUM_BLOCK);

            entries.add(AerialHellItems.GRAY_SHROOM_PLANKS);
            entries.add(AerialHellItems.GRAY_SHROOM_BOOKSHELF);

            entries.add(AerialHellItems.SHADOW_AERIAL_TREE_LOG);
            entries.add(AerialHellItems.SHADOW_GOLDEN_BEECH_LOG);
            entries.add(AerialHellItems.SHADOW_COPPER_PINE_LOG);
            entries.add(AerialHellItems.SHADOW_LAPIS_ROBINIA_LOG);
            entries.add(AerialHellItems.SHADOW_STELLAR_JUNGLE_TREE_LOG);
            entries.add(AerialHellItems.HOLLOW_SHADOW_PINE_LOG);
            entries.add(AerialHellItems.SHADOW_AERIAL_TREE_LEAVES);
            entries.add(AerialHellItems.SHADOW_GOLDEN_BEECH_LEAVES);
            entries.add(AerialHellItems.SHADOW_COPPER_PINE_LEAVES);
            entries.add(AerialHellItems.SHADOW_LAPIS_ROBINIA_LEAVES);
            entries.add(AerialHellItems.SHADOW_STELLAR_JUNGLE_TREE_LEAVES);
            entries.add(AerialHellItems.HOLLOW_SHADOW_PINE_LEAVES);
            entries.add(AerialHellItems.HOLLOW_PURPLE_SHADOW_PINE_LEAVES);

            entries.add(AerialHellItems.SKY_LADDER);

            entries.add(AerialHellItems.STELLAR_STONE);
            entries.add(AerialHellItems.SHADOW_STONE);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE);
            entries.add(AerialHellItems.MOSSY_STELLAR_STONE);
            entries.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE);
            entries.add(AerialHellItems.STELLAR_CLAY);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS);
            entries.add(AerialHellItems.GLAUCOPHANITE);
            entries.add(AerialHellItems.POLISHED_GLAUCOPHANITE);

            entries.add(AerialHellItems.CRYSTAL_BLOCK);
            entries.add(AerialHellItems.CRYSTAL_BRICKS);
            entries.add(AerialHellItems.CRYSTAL_BRICKS_SLAB);
            entries.add(AerialHellItems.CRYSTAL_BRICKS_STAIRS);
            entries.add(AerialHellItems.CRYSTAL_BRICKS_WALL);
            entries.add(AerialHellItems.STELLAR_STONE_CRYSTAL_BLOCK);
            entries.add(AerialHellItems.SHADOW_CRYSTAL_BLOCK);
            entries.add(AerialHellItems.CRYSTALLIZED_LEAVES);

            entries.add(AerialHellItems.STELLAR_GRASS_BLOCK);
            entries.add(AerialHellItems.CHISELED_STELLAR_GRASS_BLOCK);
            entries.add(AerialHellItems.STELLAR_DIRT);
            entries.add(AerialHellItems.STELLAR_COARSE_DIRT);
            entries.add(AerialHellItems.STELLAR_FARMLAND);
            entries.add(AerialHellItems.STELLAR_DIRT_PATH);
            entries.add(AerialHellItems.STELLAR_PODZOL);
            entries.add(AerialHellItems.STELLAR_CRYSTAL_PODZOL);
            entries.add(AerialHellItems.CHISELED_STELLAR_DIRT);
            entries.add(AerialHellItems.SHADOW_GRASS_BLOCK);

            entries.add(AerialHellItems.SLIPPERY_SAND);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS);
            entries.add(AerialHellItems.CUT_SLIPPERY_SAND_STONE);
            entries.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS);

            entries.add(AerialHellItems.SLIPPERY_SAND_GLASS);
            entries.add(AerialHellItems.RED_SLIPPERY_SAND_GLASS);
            entries.add(AerialHellItems.BLACK_SLIPPERY_SAND_GLASS);
            entries.add(AerialHellItems.BLUE_SLIPPERY_SAND_GLASS);
            entries.add(AerialHellItems.GREEN_SLIPPERY_SAND_GLASS);
            entries.add(AerialHellItems.SLIPPERY_SAND_GLASS_PANE);
            entries.add(AerialHellItems.RED_SLIPPERY_SAND_GLASS_PANE);
            entries.add(AerialHellItems.BLACK_SLIPPERY_SAND_GLASS_PANE);
            entries.add(AerialHellItems.BLUE_SLIPPERY_SAND_GLASS_PANE);
            entries.add(AerialHellItems.GREEN_SLIPPERY_SAND_GLASS_PANE);

            entries.add(AerialHellItems.GHOST_BOAT_PLANKS);
            entries.add(AerialHellItems.GHOST_BOAT_LOG);
            entries.add(AerialHellItems.GHOST_BOAT_WOOD);
            entries.add(AerialHellItems.GHOST_BOAT_SLAB);
            entries.add(AerialHellItems.GHOST_BOAT_STAIRS);
            entries.add(AerialHellItems.GHOST_BOAT_FENCE);
            entries.add(AerialHellItems.GHOST_BOAT_GATE);
            entries.add(AerialHellItems.GHOST_BOAT_DOOR);
            entries.add(AerialHellItems.GHOST_BOAT_TRAPDOOR);
            entries.add(AerialHellItems.GHOST_BOAT_CHEST);
            entries.add(AerialHellItems.GHOST_STELLAR_COBBLESTONE);
            entries.add(AerialHellItems.GHOST_STELLAR_FURNACE);
            entries.add(AerialHellItems.GHOST_BOAT_WOOL);
            entries.add(AerialHellItems.GHOST_RUBY_BLOCK);
            entries.add(AerialHellItems.GHOST_FLUORITE_BLOCK);
            entries.add(AerialHellItems.GHOST_AZURITE_BLOCK);
            entries.add(AerialHellItems.GHOST_GOLD_BLOCK);
            entries.add(AerialHellItems.GHOST_BOAT_BARREL);
            entries.add(AerialHellItems.GHOST_BOAT_CRAFTING_TABLE);
            entries.add(AerialHellItems.GHOST_BOAT_VINE_ROPE_SPOOL);
            entries.add(AerialHellItems.GHOST_LANTERN);

            entries.add(AerialHellItems.WEAK_LIGHT_REACTOR);
            entries.add(AerialHellItems.HIGH_POWER_LIGHT_REACTOR);
            entries.add(AerialHellItems.WEAK_SHADOW_REACTOR);
            entries.add(AerialHellItems.HIGH_POWER_SHADOW_REACTOR);
            entries.add(AerialHellItems.BROKEN_WEAK_LIGHT_REACTOR);
            entries.add(AerialHellItems.BROKEN_HIGH_POWER_LIGHT_REACTOR);
            entries.add(AerialHellItems.BROKEN_WEAK_SHADOW_REACTOR);
            entries.add(AerialHellItems.BROKEN_HIGH_POWER_SHADOW_REACTOR);

            entries.add(AerialHellItems.WHITE_SOLID_ETHER);
            entries.add(AerialHellItems.BLUE_SOLID_ETHER);
            entries.add(AerialHellItems.GOLDEN_SOLID_ETHER);
            entries.add(AerialHellItems.GREEN_SOLID_ETHER);
            entries.add(AerialHellItems.PURPLE_SOLID_ETHER);

            entries.add(AerialHellItems.SMOKY_QUARTZ_BLOCK);
            entries.add(AerialHellItems.SMOOTH_SMOKY_QUARTZ);
            entries.add(AerialHellItems.CHISELED_SMOKY_QUARTZ_BLOCK);
            entries.add(AerialHellItems.SMOKY_QUARTZ_BRICKS);
            entries.add(AerialHellItems.SMOKY_QUARTZ_PILLAR);
            entries.add(AerialHellItems.SMOKY_QUARTZ_SLAB);
            entries.add(AerialHellItems.SMOOTH_SMOKY_QUARTZ_SLAB);
            entries.add(AerialHellItems.SMOKY_QUARTZ_STAIRS);
            entries.add(AerialHellItems.SMOOTH_SMOKY_QUARTZ_STAIRS);

            entries.add(AerialHellItems.IRON_STELLAR_ORE);
            entries.add(AerialHellItems.GOLD_STELLAR_ORE);
            entries.add(AerialHellItems.DIAMOND_STELLAR_ORE);
            entries.add(AerialHellItems.FLUORITE_ORE);
            entries.add(AerialHellItems.MAGMATIC_GEL_ORE);
            entries.add(AerialHellItems.RUBY_ORE);
            entries.add(AerialHellItems.AZURITE_ORE);
            entries.add(AerialHellItems.VOLUCITE_ORE);
            entries.add(AerialHellItems.OBSIDIAN_ORE);
            entries.add(AerialHellItems.SMOKY_QUARTZ_ORE);

            entries.add(AerialHellItems.FLUORITE_BLOCK);
            entries.add(AerialHellItems.MAGMATIC_GEL_BLOCK);
            entries.add(AerialHellItems.RUBY_BLOCK);
            entries.add(AerialHellItems.AZURITE_BLOCK);
            entries.add(AerialHellItems.VOLUCITE_BLOCK);

            entries.add(AerialHellItems.RAW_RUBY_BLOCK);
            entries.add(AerialHellItems.RAW_AZURITE_BLOCK);
            entries.add(AerialHellItems.RAW_VOLUCITE_BLOCK);

            entries.add(AerialHellItems.ARSONIST_BLOCK);
            entries.add(AerialHellItems.LUNATIC_CRYSTAL_BLOCK);
            entries.add(AerialHellItems.CURSED_CRYSAL_BLOCK);

            entries.add(AerialHellItems.SKY_CACTUS);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_PLANKS);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_BOOKSHELF);
            entries.add(AerialHellItems.VIBRANT_SKY_CACTUS);
            entries.add(AerialHellItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN);

            entries.add(AerialHellItems.CLIMBING_VINE);
            entries.add(AerialHellItems.STELLAR_SUGAR_CANE);

            entries.add(AerialHellItems.FULL_MOON_FLOWER);

            entries.add(AerialHellItems.VINE_BLOSSOM);
            entries.add(AerialHellItems.LAZULI_ROOTS);
            entries.add(AerialHellItems.STELLAR_ROOTS);
            entries.add(AerialHellItems.DEAD_ROOTS);
            entries.add(AerialHellItems.GLOWING_ROOTS);
            entries.add(AerialHellItems.SHADOW_GLOWING_ROOTS);

            entries.add(AerialHellItems.STELLAR_GRASS);
            entries.add(AerialHellItems.STELLAR_GRASS_BALL);
            entries.add(AerialHellItems.STELLAR_FERN);
            entries.add(AerialHellItems.STELLAR_TALL_GRASS);
            entries.add(AerialHellItems.STELLAR_TALL_FERN);
            entries.add(AerialHellItems.STELLAR_VERY_TALL_GRASS);
            entries.add(AerialHellItems.BLUISH_FERN);
            entries.add(AerialHellItems.TALL_BLUISH_FERN);
            entries.add(AerialHellItems.POLYCHROME_FERN);
            entries.add(AerialHellItems.TALL_POLYCHROME_FERN);
            entries.add(AerialHellItems.STELLAR_DEAD_BUSH);
            entries.add(AerialHellItems.BRAMBLES);
            entries.add(AerialHellItems.SHADOW_BRAMBLES);
            entries.add(AerialHellItems.SHADOW_GRASS);
            entries.add(AerialHellItems.SHADOW_GRASS_BALL);
            entries.add(AerialHellItems.PURPLISH_STELLAR_GRASS);
            entries.add(AerialHellItems.STELLAR_CLOVERS);
            entries.add(AerialHellItems.GLOWING_STELLAR_GRASS);

            entries.add(AerialHellItems.BLUE_FLOWER);
            entries.add(AerialHellItems.BLACK_ROSE);
            entries.add(AerialHellItems.BELLFLOWER);

            entries.add(AerialHellItems.OSCILLATOR);
            entries.add(AerialHellItems.FREEZER);
            entries.add(AerialHellItems.STELLAR_FURNACE);

            entries.add(AerialHellItems.AERIAL_TREE_CHEST);
            entries.add(AerialHellItems.GOLDEN_BEECH_CHEST);
            entries.add(AerialHellItems.COPPER_PINE_CHEST);
            entries.add(AerialHellItems.LAPIS_ROBINIA_CHEST);
            entries.add(AerialHellItems.SHADOW_PINE_CHEST);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_CHEST);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_CHEST);
            entries.add(AerialHellItems.GRAY_SHROOM_CHEST);

            entries.add(AerialHellItems.AERIAL_TREE_FENCE);
            entries.add(AerialHellItems.GOLDEN_BEECH_FENCE);
            entries.add(AerialHellItems.COPPER_PINE_FENCE);
            entries.add(AerialHellItems.LAPIS_ROBINIA_FENCE);
            entries.add(AerialHellItems.SHADOW_PINE_FENCE);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_FENCE);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_FENCE);
            entries.add(AerialHellItems.GRAY_SHROOM_FENCE);
            entries.add(AerialHellItems.RUBY_BARS);
            entries.add(AerialHellItems.STELLAR_STONE_WALL);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_WALL);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_WALL);
            entries.add(AerialHellItems.MOSSY_STELLAR_STONE_WALL);
            entries.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_WALL);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_WALL);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_WALL);
            entries.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL);
            entries.add(AerialHellItems.GLAUCOPHANITE_WALL);
            entries.add(AerialHellItems.POLISHED_GLAUCOPHANITE_WALL);
            entries.add(AerialHellItems.MAGMATIC_GEL_WALL);

            entries.add(AerialHellItems.AERIAL_TREE_GATE);
            entries.add(AerialHellItems.GOLDEN_BEECH_GATE);
            entries.add(AerialHellItems.COPPER_PINE_GATE);
            entries.add(AerialHellItems.LAPIS_ROBINIA_GATE);
            entries.add(AerialHellItems.SHADOW_PINE_GATE);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_GATE);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_GATE);
            entries.add(AerialHellItems.GRAY_SHROOM_GATE);

            entries.add(AerialHellItems.AERIAL_TREE_DOOR);
            entries.add(AerialHellItems.GOLDEN_BEECH_DOOR);
            entries.add(AerialHellItems.COPPER_PINE_DOOR);
            entries.add(AerialHellItems.LAPIS_ROBINIA_DOOR);
            entries.add(AerialHellItems.SHADOW_PINE_DOOR);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_DOOR);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_DOOR);
            entries.add(AerialHellItems.GRAY_SHROOM_DOOR);
            entries.add(AerialHellItems.RUBY_DOOR);

            entries.add(AerialHellItems.AERIAL_TREE_TRAPDOOR);
            entries.add(AerialHellItems.GOLDEN_BEECH_TRAPDOOR);
            entries.add(AerialHellItems.COPPER_PINE_TRAPDOOR);
            entries.add(AerialHellItems.LAPIS_ROBINIA_TRAPDOOR);
            entries.add(AerialHellItems.SHADOW_PINE_TRAPDOOR);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_TRAPDOOR);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_TRAPDOOR);
            entries.add(AerialHellItems.GRAY_SHROOM_TRAPDOOR);
            entries.add(AerialHellItems.RUBY_TRAPDOOR);

            entries.add(AerialHellItems.STELLAR_STONE_BUTTON);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_BUTTON);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_BUTTON);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BUTTON);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_BUTTON);
            entries.add(AerialHellItems.AERIAL_TREE_BUTTON);
            entries.add(AerialHellItems.GOLDEN_BEECH_BUTTON);
            entries.add(AerialHellItems.COPPER_PINE_BUTTON);
            entries.add(AerialHellItems.LAPIS_ROBINIA_BUTTON);
            entries.add(AerialHellItems.SHADOW_PINE_BUTTON);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_BUTTON);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_BUTTON);
            entries.add(AerialHellItems.GRAY_SHROOM_BUTTON);
            entries.add(AerialHellItems.GLAUCOPHANITE_BUTTON);

            entries.add(AerialHellItems.STELLAR_STONE_PRESSURE_PLATE);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_PRESSURE_PLATE);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_PRESSURE_PLATE);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_PRESSURE_PLATE);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE);
            entries.add(AerialHellItems.AERIAL_TREE_PRESSURE_PLATE);
            entries.add(AerialHellItems.GOLDEN_BEECH_PRESSURE_PLATE);
            entries.add(AerialHellItems.COPPER_PINE_PRESSURE_PLATE);
            entries.add(AerialHellItems.LAPIS_ROBINIA_PRESSURE_PLATE);
            entries.add(AerialHellItems.SHADOW_PINE_PRESSURE_PLATE);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_PRESSURE_PLATE);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_PRESSURE_PLATE);
            entries.add(AerialHellItems.GRAY_SHROOM_PRESSURE_PLATE);
            entries.add(AerialHellItems.GLAUCOPHANITE_PRESSURE_PLATE);

            entries.add(AerialHellItems.AERIAL_TREE_SLAB);
            entries.add(AerialHellItems.GOLDEN_BEECH_SLAB);
            entries.add(AerialHellItems.COPPER_PINE_SLAB);
            entries.add(AerialHellItems.LAPIS_ROBINIA_SLAB);
            entries.add(AerialHellItems.SHADOW_PINE_SLAB);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_SLAB);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_SLAB);
            entries.add(AerialHellItems.GRAY_SHROOM_SLAB);
            entries.add(AerialHellItems.STELLAR_STONE_SLAB);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_SLAB);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_SLAB);
            entries.add(AerialHellItems.MOSSY_STELLAR_STONE_SLAB);
            entries.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_SLAB);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_SLAB);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_SLAB);
            entries.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB);
            entries.add(AerialHellItems.POLISHED_GLAUCOPHANITE_SLAB);
            entries.add(AerialHellItems.MAGMATIC_GEL_SLAB);

            entries.add(AerialHellItems.AERIAL_TREE_STAIRS);
            entries.add(AerialHellItems.GOLDEN_BEECH_STAIRS);
            entries.add(AerialHellItems.COPPER_PINE_STAIRS);
            entries.add(AerialHellItems.LAPIS_ROBINIA_STAIRS);
            entries.add(AerialHellItems.SHADOW_PINE_STAIRS);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_STAIRS);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_STAIRS);
            entries.add(AerialHellItems.GRAY_SHROOM_STAIRS);
            entries.add(AerialHellItems.STELLAR_STONE_STAIRS);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_STAIRS);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_STAIRS);
            entries.add(AerialHellItems.MOSSY_STELLAR_STONE_STAIRS);
            entries.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_STAIRS);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_STAIRS);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_STAIRS);
            entries.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS);
            entries.add(AerialHellItems.POLISHED_GLAUCOPHANITE_STAIRS);
            entries.add(AerialHellItems.MAGMATIC_GEL_STAIRS);

            entries.add(AerialHellItems.AERIAL_TREE_SIGN);
            entries.add(AerialHellItems.GOLDEN_BEECH_SIGN);
            entries.add(AerialHellItems.COPPER_PINE_SIGN);
            entries.add(AerialHellItems.LAPIS_ROBINIA_SIGN);
            entries.add(AerialHellItems.SHADOW_PINE_SIGN);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_SIGN);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_SIGN);
            entries.add(AerialHellItems.GRAY_SHROOM_SIGN);

            entries.add(AerialHellItems.AERIAL_TREE_HANGING_SIGN);
            entries.add(AerialHellItems.GOLDEN_BEECH_HANGING_SIGN);
            entries.add(AerialHellItems.COPPER_PINE_HANGING_SIGN);
            entries.add(AerialHellItems.LAPIS_ROBINIA_HANGING_SIGN);
            entries.add(AerialHellItems.SHADOW_PINE_HANGING_SIGN);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_HANGING_SIGN);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_HANGING_SIGN);
            entries.add(AerialHellItems.GRAY_SHROOM_HANGING_SIGN);

            entries.add(AerialHellItems.AERIAL_TREE_CRAFTING_TABLE);
            entries.add(AerialHellItems.GOLDEN_BEECH_CRAFTING_TABLE);
            entries.add(AerialHellItems.COPPER_PINE_CRAFTING_TABLE);
            entries.add(AerialHellItems.LAPIS_ROBINIA_CRAFTING_TABLE);
            entries.add(AerialHellItems.SHADOW_PINE_CRAFTING_TABLE);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_CRAFTING_TABLE);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_CRAFTING_TABLE);
            entries.add(AerialHellItems.GRAY_SHROOM_CRAFTING_TABLE);

            entries.add(AerialHellItems.AERIAL_TREE_BARREL);
            entries.add(AerialHellItems.GOLDEN_BEECH_BARREL);
            entries.add(AerialHellItems.COPPER_PINE_BARREL);
            entries.add(AerialHellItems.LAPIS_ROBINIA_BARREL);
            entries.add(AerialHellItems.SHADOW_PINE_BARREL);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_BARREL);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_BARREL);
            entries.add(AerialHellItems.GRAY_SHROOM_BARREL);

            entries.add(AerialHellItems.AERIAL_TREE_COMPOSTER);
            entries.add(AerialHellItems.GOLDEN_BEECH_COMPOSTER);
            entries.add(AerialHellItems.COPPER_PINE_COMPOSTER);
            entries.add(AerialHellItems.LAPIS_ROBINIA_COMPOSTER);
            entries.add(AerialHellItems.SHADOW_PINE_COMPOSTER);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_COMPOSTER);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_COMPOSTER);
            entries.add(AerialHellItems.GRAY_SHROOM_COMPOSTER);

            entries.add(AerialHellItems.AERIAL_TREE_VINE_ROPE_SPOOL);
            entries.add(AerialHellItems.GOLDEN_BEECH_VINE_ROPE_SPOOL);
            entries.add(AerialHellItems.COPPER_PINE_VINE_ROPE_SPOOL);
            entries.add(AerialHellItems.LAPIS_ROBINIA_VINE_ROPE_SPOOL);
            entries.add(AerialHellItems.SHADOW_PINE_VINE_ROPE_SPOOL);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL);
            entries.add(AerialHellItems.GRAY_SHROOM_VINE_ROPE_SPOOL);
        });

        ItemGroupEvents.modifyEntriesEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_DUNGEON_BLOCKS)).register(entries ->
        {
            entries.add(AerialHellItems.LUNATIC_LANTERN);
            entries.add(AerialHellItems.SHADOW_LANTERN);
            entries.add(AerialHellItems.LUNATIC_CHAIN);
            entries.add(AerialHellItems.SHADOW_CHAIN);

            entries.add(AerialHellItems.MUD_BRICKS);
            entries.add(AerialHellItems.CRACKED_MUD_BRICKS);
            entries.add(AerialHellItems.MOSSY_MUD_BRICKS);
            entries.add(AerialHellItems.CHISELED_MUD_BRICKS);
            entries.add(AerialHellItems.LIGHT_MUD_BRICKS);
            entries.add(AerialHellItems.CRACKED_LIGHT_MUD_BRICKS);
            entries.add(AerialHellItems.LUNATIC_STONE);
            entries.add(AerialHellItems.DARK_LUNATIC_STONE);
            entries.add(AerialHellItems.ROOF_LUNATIC_STONE);
            entries.add(AerialHellItems.CRACKED_LUNATIC_STONE);
            entries.add(AerialHellItems.CHISELED_LUNATIC_STONE);
            entries.add(AerialHellItems.LIGHT_LUNATIC_STONE);
            entries.add(AerialHellItems.ROOF_LIGHT_LUNATIC_STONE);
            entries.add(AerialHellItems.CRACKED_LIGHT_LUNATIC_STONE);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS);
            entries.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS);
            entries.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS);
            entries.add(AerialHellItems.CHISELED_SHADOW_CATACOMBS_BRICKS);
            entries.add(AerialHellItems.BONE_SHADOW_CATACOMBS_BRICKS);
            entries.add(AerialHellItems.SKULL_SHADOW_CATACOMBS_BRICKS);
            entries.add(AerialHellItems.LIGHT_SHADOW_CATACOMBS_BRICKS);
            entries.add(AerialHellItems.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS);
            entries.add(AerialHellItems.GOLDEN_NETHER_BRICKS);
            entries.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS);
            entries.add(AerialHellItems.CHISELED_GOLDEN_NETHER_BRICKS);
            entries.add(AerialHellItems.LIGHT_GOLDEN_NETHER_BRICKS);
            entries.add(AerialHellItems.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS);
            entries.add(AerialHellItems.LUNATIC_PILLAR);
            entries.add(AerialHellItems.LUNATIC_PILLAR_TOP);
            entries.add(AerialHellItems.VOLUCITE_STONE);
            entries.add(AerialHellItems.CRACKED_VOLUCITE_STONE);
            entries.add(AerialHellItems.CHISELED_VOLUCITE_STONE);
            entries.add(AerialHellItems.LIGHT_VOLUCITE_STONE);
            entries.add(AerialHellItems.CRACKED_LIGHT_VOLUCITE_STONE);

            entries.add(AerialHellItems.MUD_DUNGEON_CORE);
            entries.add(AerialHellItems.LUNATIC_DUNGEON_CORE);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_DUNGEON_CORE);
            entries.add(AerialHellItems.GOLDEN_NETHER_DUNGEON_CORE);
            entries.add(AerialHellItems.VOLUCITE_DUNGEON_CORE);

            entries.add(AerialHellItems.MUD_BRICKS_SLAB);
            entries.add(AerialHellItems.MUD_BRICKS_STAIRS);
            entries.add(AerialHellItems.MUD_BRICKS_WALL);
            entries.add(AerialHellItems.CRACKED_MUD_BRICKS_SLAB);
            entries.add(AerialHellItems.CRACKED_MUD_BRICKS_STAIRS);
            entries.add(AerialHellItems.CRACKED_MUD_BRICKS_WALL);
            entries.add(AerialHellItems.MOSSY_MUD_BRICKS_SLAB);
            entries.add(AerialHellItems.MOSSY_MUD_BRICKS_STAIRS);
            entries.add(AerialHellItems.MOSSY_MUD_BRICKS_WALL);
            entries.add(AerialHellItems.VOLUCITE_STONE_SLAB);
            entries.add(AerialHellItems.VOLUCITE_STONE_STAIRS);
            entries.add(AerialHellItems.VOLUCITE_STONE_WALL);
            entries.add(AerialHellItems.CRACKED_VOLUCITE_STONE_SLAB);
            entries.add(AerialHellItems.CRACKED_VOLUCITE_STONE_STAIRS);
            entries.add(AerialHellItems.CRACKED_VOLUCITE_STONE_WALL);
            entries.add(AerialHellItems.LUNATIC_STONE_SLAB);
            entries.add(AerialHellItems.LUNATIC_STONE_STAIRS);
            entries.add(AerialHellItems.LUNATIC_STONE_WALL);
            entries.add(AerialHellItems.DARK_LUNATIC_STONE_SLAB);
            entries.add(AerialHellItems.DARK_LUNATIC_STONE_STAIRS);
            entries.add(AerialHellItems.DARK_LUNATIC_STONE_WALL);
            entries.add(AerialHellItems.CRACKED_LUNATIC_STONE_SLAB);
            entries.add(AerialHellItems.CRACKED_LUNATIC_STONE_STAIRS);
            entries.add(AerialHellItems.CRACKED_LUNATIC_STONE_WALL);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_SLAB);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_STAIRS);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_WALL);
            entries.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB);
            entries.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS);
            entries.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL);
            entries.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB);
            entries.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS);
            entries.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL);
            entries.add(AerialHellItems.SHADOW_BARS);
            entries.add(AerialHellItems.GOLDEN_NETHER_BRICKS_SLAB);
            entries.add(AerialHellItems.GOLDEN_NETHER_BRICKS_STAIRS);
            entries.add(AerialHellItems.GOLDEN_NETHER_BRICKS_WALL);
            entries.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_SLAB);
            entries.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS);
            entries.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_WALL);

            entries.add(AerialHellItems.TRAPPED_MUD_BRICKS);
            entries.add(AerialHellItems.TRAPPED_LIGHT_MUD_BRICKS);
            entries.add(AerialHellItems.TRAPPED_LUNATIC_STONE);
            entries.add(AerialHellItems.TRAPPED_LIGHT_LUNATIC_STONE);
            entries.add(AerialHellItems.TRAPPED_GOLDEN_NETHER_BRICKS);
            entries.add(AerialHellItems.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS);

            entries.add(AerialHellItems.MUD_BONE_BLOCK);
            entries.add(AerialHellItems.MUD_BONE_PILE_BLOCK);
            entries.add(AerialHellItems.THORNY_COBWEB);
            entries.add(AerialHellItems.AERIAL_NETHERRACK);
            entries.add(AerialHellItems.AERIAL_NETHERRACK_SLAB);
            entries.add(AerialHellItems.AERIAL_NETHERRACK_STAIRS);
            entries.add(AerialHellItems.AERIAL_NETHERRACK_WALL);

            entries.add(AerialHellItems.MUD_BOOKSHELF);
            entries.add(AerialHellItems.LUNATIC_BOOKSHELF);
            entries.add(AerialHellItems.GOLDEN_NETHER_BOOKSHELF);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BOOKSHELF);
            entries.add(AerialHellItems.VOLUCITE_BOOKSHELF);

            entries.add(AerialHellItems.MUD_GLYPH_BLOCK);
            entries.add(AerialHellItems.LUNATIC_GLYPH_BLOCK);
            entries.add(AerialHellItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK);
            entries.add(AerialHellItems.VOLUCITE_GLYPH_BLOCK);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_GLYPH_BLOCK);

            entries.add(AerialHellItems.MUD_CHEST);
            entries.add(AerialHellItems.LUNATIC_CHEST);
            entries.add(AerialHellItems.VOLUCITE_CHEST);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_CHEST);
            entries.add(AerialHellItems.GOLDEN_NETHER_CHEST);

            entries.add(AerialHellItems.MUD_CYCLE_MAGE_TROPHY);
            entries.add(AerialHellItems.LUNAR_PRIEST_TROPHY);
            entries.add(AerialHellItems.LILITH_TROPHY);
            entries.add(AerialHellItems.CHAINED_GOD_TROPHY);
        });
        ItemGroupEvents.modifyEntriesEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_TOOLS)).register(entries ->
        {
            entries.add(AerialHellItems.SKY_WOOD_PICKAXE);
            entries.add(AerialHellItems.STELLAR_STONE_PICKAXE);
            entries.add(AerialHellItems.RUBY_PICKAXE);
            entries.add(AerialHellItems.AZURITE_PICKAXE);
            entries.add(AerialHellItems.MAGMATIC_GEL_PICKAXE);
            entries.add(AerialHellItems.OBSIDIAN_PICKAXE);
            entries.add(AerialHellItems.VOLUCITE_PICKAXE);
            entries.add(AerialHellItems.LUNATIC_PICKAXE);
            entries.add(AerialHellItems.ARSONIST_PICKAXE);
            entries.add(AerialHellItems.MAGMA_CUBE_PICKAXE);
            entries.add(AerialHellItems.STELLAR_STONE_BREAKER);

            entries.add(AerialHellItems.SKY_WOOD_SHOVEL);
            entries.add(AerialHellItems.STELLAR_STONE_SHOVEL);
            entries.add(AerialHellItems.RUBY_SHOVEL);
            entries.add(AerialHellItems.AZURITE_SHOVEL);
            entries.add(AerialHellItems.MAGMATIC_GEL_SHOVEL);
            entries.add(AerialHellItems.OBSIDIAN_SHOVEL);
            entries.add(AerialHellItems.VOLUCITE_SHOVEL);
            entries.add(AerialHellItems.LUNATIC_SHOVEL);
            entries.add(AerialHellItems.ARSONIST_SHOVEL);
            entries.add(AerialHellItems.MAGMA_CUBE_SHOVEL);

            entries.add(AerialHellItems.SKY_WOOD_AXE);
            entries.add(AerialHellItems.STELLAR_STONE_AXE);
            entries.add(AerialHellItems.RUBY_AXE);
            entries.add(AerialHellItems.AZURITE_AXE);
            entries.add(AerialHellItems.MAGMATIC_GEL_AXE);
            entries.add(AerialHellItems.OBSIDIAN_AXE);
            entries.add(AerialHellItems.VOLUCITE_AXE);
            entries.add(AerialHellItems.LUNATIC_AXE);
            entries.add(AerialHellItems.ARSONIST_AXE);

            entries.add(AerialHellItems.SKY_WOOD_HOE);
            entries.add(AerialHellItems.STELLAR_STONE_HOE);
            entries.add(AerialHellItems.RUBY_HOE);
            entries.add(AerialHellItems.AZURITE_HOE);
            entries.add(AerialHellItems.MAGMATIC_GEL_HOE);
            entries.add(AerialHellItems.OBSIDIAN_HOE);
            entries.add(AerialHellItems.VOLUCITE_HOE);
            entries.add(AerialHellItems.LUNATIC_HOE);
            entries.add(AerialHellItems.ARSONIST_HOE);
        });

        ItemGroupEvents.modifyEntriesEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_COMBAT)).register(entries ->
        {
            entries.add(AerialHellItems.IRON_SHURIKEN);
            entries.add(AerialHellItems.GOLD_SHURIKEN);
            entries.add(AerialHellItems.DIAMOND_SHURIKEN);
            entries.add(AerialHellItems.NETHERITE_SHURIKEN);
            entries.add(AerialHellItems.RUBY_SHURIKEN);
            entries.add(AerialHellItems.AZURITE_SHURIKEN);
            entries.add(AerialHellItems.MAGMATIC_GEL_SHURIKEN);
            entries.add(AerialHellItems.VOLUCITE_SHURIKEN);
            entries.add(AerialHellItems.OBSIDIAN_SHURIKEN);
            entries.add(AerialHellItems.LUNATIC_CRYSTAL_SHURIKEN);
            entries.add(AerialHellItems.ARSONIST_SHURIKEN);
            entries.add(AerialHellItems.LIGHTNING_SHURIKEN);

            entries.add(AerialHellItems.RUBY_BLOWPIPE_ARROW);
            entries.add(AerialHellItems.VOLUCITE_BLOWPIPE_ARROW);
            entries.add(AerialHellItems.RUBY_BLOWPIPE);
            entries.add(AerialHellItems.VOLUCITE_BLOWPIPE);

            entries.add(AerialHellItems.SKY_WOOD_SWORD);
            entries.add(AerialHellItems.STELLAR_STONE_SWORD);
            entries.add(AerialHellItems.RUBY_SWORD);
            entries.add(AerialHellItems.AZURITE_SWORD);
            entries.add(AerialHellItems.MAGMATIC_GEL_SWORD);
            entries.add(AerialHellItems.OBSIDIAN_SWORD);
            entries.add(AerialHellItems.VOLUCITE_SWORD);
            entries.add(AerialHellItems.LUNATIC_SWORD);
            entries.add(AerialHellItems.ARSONIST_SWORD);

            entries.add(AerialHellItems.HEAVY_SWORD);
            entries.add(AerialHellItems.HEALTH_BOOST_SWORD);
            entries.add(AerialHellItems.NINJA_SWORD);
            entries.add(AerialHellItems.NINJA_MASTER_SWORD);
            entries.add(AerialHellItems.GLOUTON_SWORD);
            entries.add(AerialHellItems.RANDOM_SWORD);
            entries.add(AerialHellItems.DISLOYAL_SWORD);
            entries.add(AerialHellItems.CURSED_SWORD);
            entries.add(AerialHellItems.ABSOLUTE_ZERO_SWORD);
            entries.add(AerialHellItems.SWORD_OF_LIGHT);
            entries.add(AerialHellItems.ANTIDOTE_SWORD);
            entries.add(AerialHellItems.NETHERIAN_KING_SWORD);
            entries.add(AerialHellItems.GLASS_CANON_SWORD);
            entries.add(AerialHellItems.GOD_SWORD);

            entries.add(AerialHellItems.FORGOTTEN_BATTLE_TRIDENT);

            entries.add(AerialHellItems.HEAVY_AXE);
            entries.add(AerialHellItems.AXE_OF_LIGHT);
            entries.add(AerialHellItems.CURSED_AXE);
            entries.add(AerialHellItems.BERSERK_AXE);
            entries.add(AerialHellItems.REAPER_SCYTHE);

            entries.add(AerialHellItems.RUBY_HELMET);
            entries.add(AerialHellItems.RUBY_CHESTPLATE);
            entries.add(AerialHellItems.RUBY_LEGGINGS);
            entries.add(AerialHellItems.RUBY_BOOTS);

            entries.add(AerialHellItems.AZURITE_HELMET);
            entries.add(AerialHellItems.AZURITE_CHESTPLATE);
            entries.add(AerialHellItems.AZURITE_LEGGINGS);
            entries.add(AerialHellItems.AZURITE_BOOTS);

            entries.add(AerialHellItems.OBSIDIAN_HELMET);
            entries.add(AerialHellItems.OBSIDIAN_CHESTPLATE);
            entries.add(AerialHellItems.OBSIDIAN_LEGGINGS);
            entries.add(AerialHellItems.OBSIDIAN_BOOTS);

            entries.add(AerialHellItems.VOLUCITE_HELMET);
            entries.add(AerialHellItems.VOLUCITE_CHESTPLATE);
            entries.add(AerialHellItems.VOLUCITE_LEGGINGS);
            entries.add(AerialHellItems.VOLUCITE_BOOTS);

            entries.add(AerialHellItems.MAGMATIC_GEL_HELMET);
            entries.add(AerialHellItems.MAGMATIC_GEL_CHESTPLATE);
            entries.add(AerialHellItems.MAGMATIC_GEL_LEGGINGS);
            entries.add(AerialHellItems.MAGMATIC_GEL_BOOTS);

            entries.add(AerialHellItems.LUNATIC_HELMET);
            entries.add(AerialHellItems.LUNATIC_CHESTPLATE);
            entries.add(AerialHellItems.LUNATIC_LEGGINGS);
            entries.add(AerialHellItems.LUNATIC_BOOTS);

            entries.add(AerialHellItems.ARSONIST_HELMET);
            entries.add(AerialHellItems.ARSONIST_CHESTPLATE);
            entries.add(AerialHellItems.ARSONIST_LEGGINGS);
            entries.add(AerialHellItems.ARSONIST_BOOTS);

            entries.add(AerialHellItems.SHADOW_HELMET);
            entries.add(AerialHellItems.SHADOW_CHESTPLATE);
            entries.add(AerialHellItems.SHADOW_LEGGINGS);
            entries.add(AerialHellItems.SHADOW_BOOTS);
        });

        ItemGroupEvents.modifyEntriesEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_FOODSTUFFS)).register(entries ->
        {
            entries.add(AerialHellItems.AERIAL_BERRY);
            entries.add(AerialHellItems.ROASTED_AERIAL_BERRY);
            entries.add(AerialHellItems.VIBRANT_AERIAL_BERRY);
            entries.add(AerialHellItems.FROZEN_AERIAL_BERRY);
            entries.add(AerialHellItems.RUBY_AERIAL_BERRY);
            entries.add(AerialHellItems.VOLUCITE_AERIAL_BERRY);
            entries.add(AerialHellItems.GODS_VOLUCITE_AERIAL_BERRY);
            entries.add(AerialHellItems.STELLAR_BREAD);
            entries.add(AerialHellItems.FROZEN_MUTTON);
            entries.add(AerialHellItems.VIBRANT_CHICKEN);
            entries.add(AerialHellItems.FROZEN_CHICKEN);
            entries.add(AerialHellItems.GLOWING_STICK_FRUIT);
            entries.add(AerialHellItems.VIBRANT_GLOWING_STICK_FRUIT);
            entries.add(AerialHellItems.FROZEN_GLOWING_STICK_FRUIT);
            entries.add(AerialHellItems.CORTINARIUS_VIOLACEUS_PIECE);
            entries.add(AerialHellItems.GANODERMA_APPLANATUM_PIECE);
            entries.add(AerialHellItems.DARK_SHADOW_FRUIT);
            entries.add(AerialHellItems.PURPLE_SHADOW_FRUIT);
            entries.add(AerialHellItems.SHADOW_FRUIT_STEW);
            entries.add(AerialHellItems.SOLID_ETHER_SOUP);
            entries.add(AerialHellItems.VIBRANT_SOLID_ETHER_SOUP);
            entries.add(AerialHellItems.FROZEN_SOLID_ETHER_SOUP);
            entries.add(AerialHellItems.SHADOW_SPIDER_EYE);
            entries.add(AerialHellItems.PHANTOM_MEAT);
            entries.add(AerialHellItems.VIBRANT_PHANTOM_MEAT);
            entries.add(AerialHellItems.FROZEN_PHANTOM_MEAT);
            entries.add(AerialHellItems.COOKED_PHANTOM_MEAT);
            entries.add(AerialHellItems.TURTLE_MEAT);
            entries.add(AerialHellItems.VIBRANT_TURTLE_MEAT);
            entries.add(AerialHellItems.FROZEN_TURTLE_MEAT);
            entries.add(AerialHellItems.COOKED_TURTLE_MEAT);
            entries.add(AerialHellItems.COPPER_PINE_CONE);
            entries.add(AerialHellItems.AZURITE_COPPER_PINE_CONE);
            entries.add(AerialHellItems.PHOENIX_FEATHER);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER);
            entries.add(AerialHellItems.VIBRANT_SKY_CACTUS_FIBER);
            entries.add(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT);
            entries.add(AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT);
            entries.add(AerialHellItems.GOLDEN_SOLID_ETHER_FRAGMENT);
            entries.add(AerialHellItems.GREEN_SOLID_ETHER_FRAGMENT);
            entries.add(AerialHellItems.PURPLE_SOLID_ETHER_FRAGMENT);
            entries.add(AerialHellItems.GOLDEN_NETHER_MEAT_PIECE);
            entries.add(AerialHellItems.GOLDEN_NETHER_STEAK);
            entries.add(AerialHellItems.VIBRANT_GOLDEN_NETHER_STEAK);
        });

        ItemGroupEvents.modifyEntriesEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_MISCELLANEOUS)).register(entries ->
        {
            entries.add(AerialHellItems.STELLAR_PORTAL_FRAME_BRICK);
            entries.add(AerialHellItems.STELLAR_LIGHTER);

            entries.add(AerialHellItems.CRYSTAL);
            entries.add(AerialHellItems.STELLAR_STONE_CRYSTAL);
            entries.add(AerialHellItems.SHADOW_CRYSTAL);

            entries.add(AerialHellItems.SMOKY_QUARTZ);

            entries.add(AerialHellItems.MUD_BONE);
            entries.add(AerialHellItems.STELLAR_EGG);
            entries.add(AerialHellItems.DIMENSION_SHATTERER_PROJECTILE);

            entries.add(AerialHellItems.FLUORITE);
            entries.add(AerialHellItems.MAGMATIC_GEL);
            entries.add(AerialHellItems.RUBY);
            entries.add(AerialHellItems.AZURITE_CRYSTAL);
            entries.add(AerialHellItems.VOLUCITE_VIBRANT);

            entries.add(AerialHellItems.RAW_RUBY);
            entries.add(AerialHellItems.RAW_AZURITE);
            entries.add(AerialHellItems.RAW_VOLUCITE);

            entries.add(AerialHellItems.OVERHEATED_RUBY);
            entries.add(AerialHellItems.OVERHEATED_VOLUCITE);

            entries.add(AerialHellItems.ARSONIST_INGOT);
            entries.add(AerialHellItems.LUNATIC_CRYSTAL);
            entries.add(AerialHellItems.OBSIDIAN_SHARD);
            entries.add(AerialHellItems.CURSED_CRYSAL);

            entries.add(AerialHellItems.ARSONIST_UPGRADE_SMITHING_TEMPLATE);

            entries.add(AerialHellItems.AERIAL_BERRY_SEEDS);
            entries.add(AerialHellItems.VIBRANT_AERIAL_BERRY_SEEDS);
            entries.add(AerialHellItems.STELLAR_WHEAT_SEEDS);
            entries.add(AerialHellItems.STELLAR_WHEAT);
            entries.add(AerialHellItems.BLUE_MEANIE_SPORES);
            entries.add(AerialHellItems.BLUE_MEANIE_CAP);

            entries.add(AerialHellItems.SKY_STICK);
            entries.add(AerialHellItems.SKY_BOWL);
            entries.add(AerialHellItems.SHADOW_SHARD);
            entries.add(AerialHellItems.ROTTEN_LEATHER);
            entries.add(AerialHellItems.VENOMOUS_SNAKE_SKIN);

            entries.add(AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET);
            entries.add(AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET);
            entries.add(AerialHellItems.RUBY_BUCKET);
            entries.add(AerialHellItems.RUBY_WATER_BUCKET);
            entries.add(AerialHellItems.RUBY_MILK_BUCKET);

            entries.add(AerialHellItems.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP);
            entries.add(AerialHellItems.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER);
            entries.add(AerialHellItems.MUSIC_DISC_ENTHUSIAST_TOURS);
            entries.add(AerialHellItems.MUSIC_DISC_BMINOR_ARULO);
            entries.add(AerialHellItems.MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP);

            entries.add(AerialHellItems.REGENERATION_TOTEM);
            entries.add(AerialHellItems.SPEED_TOTEM);
            entries.add(AerialHellItems.SPEED_II_TOTEM);
            entries.add(AerialHellItems.NIGHT_VISION_TOTEM);
            entries.add(AerialHellItems.AGILITY_TOTEM);
            entries.add(AerialHellItems.HERO_TOTEM);
            entries.add(AerialHellItems.HEAD_IN_THE_CLOUDS_TOTEM);
            entries.add(AerialHellItems.GOD_TOTEM);
            entries.add(AerialHellItems.CURSED_TOTEM);
            entries.add(AerialHellItems.SHADOW_TOTEM);

            entries.add(AerialHellItems.AERIAL_HELL_PAINTING);
        });

        ItemGroupEvents.modifyEntriesEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_SPAWN_EGGS)).register(entries ->
        {
            entries.add(AerialHellItems.AERIAL_TREE_CHEST_MIMIC);
            entries.add(AerialHellItems.GOLDEN_BEECH_CHEST_MIMIC);
            entries.add(AerialHellItems.COPPER_PINE_CHEST_MIMIC);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_CHEST_MIMIC);
            entries.add(AerialHellItems.SHADOW_PINE_BARREL_MIMIC);

            entries.add(AerialHellItems.STELLAR_STONE_AUTOMATON_SPAWN_EGG);
            entries.add(AerialHellItems.EVIL_COW_SPAWN_EGG);
            entries.add(AerialHellItems.CORTINARIUS_COW_SPAWN_EGG);
            entries.add(AerialHellItems.STELLAR_ENT_SPAWN_EGG);
            entries.add(AerialHellItems.VENOMOUS_SNAKE_SPAWN_EGG);
            entries.add(AerialHellItems.WORM_SPAWN_EGG);
            entries.add(AerialHellItems.STELLAR_CHICKEN_SPAWN_EGG);
            entries.add(AerialHellItems.STELLAR_BOAR_SPAWN_EGG);
            entries.add(AerialHellItems.SHROOMBOOM_SPAWN_EGG);
            entries.add(AerialHellItems.VERDIGRIS_ZOMBIE_SPAWN_EGG);
            entries.add(AerialHellItems.MUMMY_SPAWN_EGG);
            entries.add(AerialHellItems.SLIME_PIRATE_SPAWN_EGG);
            entries.add(AerialHellItems.SLIME_NINJA_PIRATE_SPAWN_EGG);
            entries.add(AerialHellItems.GHOST_SLIME_PIRATE_SPAWN_EGG);
            entries.add(AerialHellItems.GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG);
            entries.add(AerialHellItems.SANDY_SHEEP_SPAWN_EGG);
            entries.add(AerialHellItems.GLIDING_TURTLE_SPAWN_EGG);
            entries.add(AerialHellItems.FAT_PHANTOM_SPAWN_EGG);
            entries.add(AerialHellItems.KODAMA_SPAWN_EGG);
            entries.add(AerialHellItems.MUD_GOLEM_SPAWN_EGG);
            entries.add(AerialHellItems.MUD_SOLDIER_SPAWN_EGG);
            entries.add(AerialHellItems.MUD_CYCLE_MAGE_SPAWN_EGG);
            entries.add(AerialHellItems.HELL_SPIDER_SPAWN_EGG);
            entries.add(AerialHellItems.CRYSTAL_GOLEM_SPAWN_EGG);
            entries.add(AerialHellItems.CRYSTAL_SLIME_SPAWN_EGG);
            entries.add(AerialHellItems.CRYSTAL_SPIDER_SPAWN_EGG);
            entries.add(AerialHellItems.LUNATIC_PRIEST_SPAWN_EGG);
            entries.add(AerialHellItems.CRYSTAL_CATERPILLAR_SPAWN_EGG);
            entries.add(AerialHellItems.FOREST_CATERPILLAR_SPAWN_EGG);
            entries.add(AerialHellItems.TORN_SPIRIT_SPAWN_EGG);
            entries.add(AerialHellItems.CHAINED_GOD_SPAWN_EGG);
            entries.add(AerialHellItems.ICE_SPIRIT_SPAWN_EGG);
            entries.add(AerialHellItems.FIRE_SPIRIT_SPAWN_EGG);
            entries.add(AerialHellItems.ELECTRO_SPIRIT_SPAWN_EGG);
            entries.add(AerialHellItems.FLYING_JELLYFISH_SPAWN_EGG);
            entries.add(AerialHellItems.SHADOW_FLYING_SKULL_SPAWN_EGG);
            entries.add(AerialHellItems.SHADOW_TROLL_SPAWN_EGG);
            entries.add(AerialHellItems.SHADOW_AUTOMATON_SPAWN_EGG);
            entries.add(AerialHellItems.SHADOW_SPIDER_SPAWN_EGG);
            entries.add(AerialHellItems.LILITH_SPAWN_EGG);
        });
    }
}
