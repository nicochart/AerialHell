package fr.factionbedrock.aerialhell.Registry.CreativeModeTabs;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class BuildContentsEvent
{
    public static void buildContents()
    {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries ->
        {
            entries.accept(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK);
            entries.accept(AerialHellItems.STELLAR_LIGHTER);
        });

        CreativeModeTabEvents.modifyOutputEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_BLOCKS)).register(entries ->
        {
            entries.accept(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK);
            entries.accept(AerialHellItems.STELLAR_LIGHTER);

            entries.accept(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK);
            entries.accept(AerialHellItems.STELLAR_PORTAL_FRAME_ORE);
            entries.accept(AerialHellItems.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE);

            entries.accept(AerialHellItems.CRYSTALLIZED_TORCH);
            entries.accept(AerialHellItems.FLUORITE_TORCH);
            entries.accept(AerialHellItems.VOLUCITE_TORCH);
            entries.accept(AerialHellItems.SHADOW_TORCH);
            entries.accept(AerialHellItems.CRYSTALLIZED_LANTERN);
            entries.accept(AerialHellItems.FLUORITE_LANTERN);
            entries.accept(AerialHellItems.RUBY_LANTERN);
            entries.accept(AerialHellItems.RUBY_CRYSTALLIZED_LANTERN);
            entries.accept(AerialHellItems.RUBY_FLUORITE_LANTERN);
            entries.accept(AerialHellItems.VOLUCITE_LANTERN);
            entries.accept(AerialHellItems.VOLUCITE_CRYSTALLIZED_LANTERN);
            entries.accept(AerialHellItems.VOLUCITE_FLUORITE_LANTERN);

            entries.accept(AerialHellItems.RUBY_CHAIN);
            entries.accept(AerialHellItems.VOLUCITE_CHAIN);

            entries.accept(AerialHellItems.GIANT_ROOT);

            entries.accept(AerialHellItems.AERIAL_TREE_LOG);
            entries.accept(AerialHellItems.STRIPPED_AERIAL_TREE_LOG);
            entries.accept(AerialHellItems.AERIAL_TREE_WOOD);
            entries.accept(AerialHellItems.STRIPPED_AERIAL_TREE_WOOD);
            entries.accept(AerialHellItems.AERIAL_TREE_LEAVES);
            entries.accept(AerialHellItems.AERIAL_TREE_PLANKS);
            entries.accept(AerialHellItems.CHISELED_AERIAL_TREE_PLANKS);
            entries.accept(AerialHellItems.AERIAL_TREE_BOOKSHELF);
            entries.accept(AerialHellItems.AERIAL_TREE_SAPLING);

            entries.accept(AerialHellItems.PETRIFIED_AERIAL_TREE_LOG);

            entries.accept(AerialHellItems.GOLDEN_BEECH_LOG);
            entries.accept(AerialHellItems.STRIPPED_GOLDEN_BEECH_LOG);
            entries.accept(AerialHellItems.GOLDEN_BEECH_WOOD);
            entries.accept(AerialHellItems.STRIPPED_GOLDEN_BEECH_WOOD);
            entries.accept(AerialHellItems.GOLDEN_BEECH_LEAVES);
            entries.accept(AerialHellItems.GOLDEN_BEECH_PLANKS);
            entries.accept(AerialHellItems.CHISELED_GOLDEN_BEECH_PLANKS);
            entries.accept(AerialHellItems.GOLDEN_BEECH_BOOKSHELF);
            entries.accept(AerialHellItems.GOLDEN_BEECH_SAPLING);

            entries.accept(AerialHellItems.COPPER_PINE_LOG);
            entries.accept(AerialHellItems.STRIPPED_COPPER_PINE_LOG);
            entries.accept(AerialHellItems.COPPER_PINE_WOOD);
            entries.accept(AerialHellItems.STRIPPED_COPPER_PINE_WOOD);
            entries.accept(AerialHellItems.COPPER_PINE_PLANKS);
            entries.accept(AerialHellItems.COPPER_PINE_LEAVES);
            entries.accept(AerialHellItems.COPPER_PINE_BOOKSHELF);
            entries.accept(AerialHellItems.COPPER_PINE_SAPLING);

            entries.accept(AerialHellItems.LAPIS_ROBINIA_LOG);
            entries.accept(AerialHellItems.ENCHANTED_LAPIS_ROBINIA_LOG);
            entries.accept(AerialHellItems.STRIPPED_LAPIS_ROBINIA_LOG);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_WOOD);
            entries.accept(AerialHellItems.STRIPPED_LAPIS_ROBINIA_WOOD);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_LEAVES);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_PLANKS);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_BOOKSHELF);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_SAPLING);

            entries.accept(AerialHellItems.SHADOW_PINE_LOG);
            entries.accept(AerialHellItems.EYE_SHADOW_PINE_LOG);
            entries.accept(AerialHellItems.STRIPPED_SHADOW_PINE_LOG);
            entries.accept(AerialHellItems.SHADOW_PINE_WOOD);
            entries.accept(AerialHellItems.STRIPPED_SHADOW_PINE_WOOD);
            entries.accept(AerialHellItems.SHADOW_PINE_LEAVES);
            entries.accept(AerialHellItems.PURPLE_SHADOW_PINE_LEAVES);
            entries.accept(AerialHellItems.SHADOW_PINE_PLANKS);
            entries.accept(AerialHellItems.SHADOW_PINE_BOOKSHELF);
            entries.accept(AerialHellItems.SHADOW_PINE_SAPLING);
            entries.accept(AerialHellItems.PURPLE_SHADOW_PINE_SAPLING);

            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_LOG);
            entries.accept(AerialHellItems.STRIPPED_STELLAR_JUNGLE_TREE_LOG);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_WOOD);
            entries.accept(AerialHellItems.STRIPPED_STELLAR_JUNGLE_TREE_WOOD);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_LEAVES);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_PLANKS);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_BOOKSHELF);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_SAPLING);
            entries.accept(AerialHellItems.DEAD_STELLAR_JUNGLE_TREE_LOG);

            entries.accept(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_STEM);
            entries.accept(AerialHellItems.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM);
            entries.accept(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM);
            entries.accept(AerialHellItems.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM);
            entries.accept(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK);
            entries.accept(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_LIGHT);
            entries.accept(AerialHellItems.CORTINARIUS_VIOLACEUS);
            entries.accept(AerialHellItems.GLOWING_BOLETUS);
            entries.accept(AerialHellItems.TALL_GLOWING_BOLETUS);
            entries.accept(AerialHellItems.BLUE_MEANIE_CLUSTER);
            entries.accept(AerialHellItems.GIANT_ROOT_SHROOM);

            entries.accept(AerialHellItems.GIANT_VERDIGRIS_AGARIC_STEM);
            entries.accept(AerialHellItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM);
            entries.accept(AerialHellItems.GIANT_VERDIGRIS_AGARIC_BARK_STEM);
            entries.accept(AerialHellItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM);
            entries.accept(AerialHellItems.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK);
            entries.accept(AerialHellItems.VERDIGRIS_AGARIC);

            entries.accept(AerialHellItems.GIANT_GANODERMA_APPLANATUM_BLOCK);

            entries.accept(AerialHellItems.GRAY_SHROOM_PLANKS);
            entries.accept(AerialHellItems.GRAY_SHROOM_BOOKSHELF);

            entries.accept(AerialHellItems.SHADOW_AERIAL_TREE_LOG);
            entries.accept(AerialHellItems.SHADOW_GOLDEN_BEECH_LOG);
            entries.accept(AerialHellItems.SHADOW_COPPER_PINE_LOG);
            entries.accept(AerialHellItems.SHADOW_LAPIS_ROBINIA_LOG);
            entries.accept(AerialHellItems.SHADOW_STELLAR_JUNGLE_TREE_LOG);
            entries.accept(AerialHellItems.HOLLOW_SHADOW_PINE_LOG);
            entries.accept(AerialHellItems.SHADOW_AERIAL_TREE_LEAVES);
            entries.accept(AerialHellItems.SHADOW_GOLDEN_BEECH_LEAVES);
            entries.accept(AerialHellItems.SHADOW_COPPER_PINE_LEAVES);
            entries.accept(AerialHellItems.SHADOW_LAPIS_ROBINIA_LEAVES);
            entries.accept(AerialHellItems.SHADOW_STELLAR_JUNGLE_TREE_LEAVES);
            entries.accept(AerialHellItems.HOLLOW_SHADOW_PINE_LEAVES);
            entries.accept(AerialHellItems.HOLLOW_PURPLE_SHADOW_PINE_LEAVES);

            entries.accept(AerialHellItems.SKY_LADDER);

            entries.accept(AerialHellItems.STELLAR_STONE);
            entries.accept(AerialHellItems.SHADOW_STONE);
            entries.accept(AerialHellItems.STELLAR_COBBLESTONE);
            entries.accept(AerialHellItems.MOSSY_STELLAR_STONE);
            entries.accept(AerialHellItems.MOSSY_STELLAR_COBBLESTONE);
            entries.accept(AerialHellItems.STELLAR_CLAY);
            entries.accept(AerialHellItems.STELLAR_STONE_BRICKS);
            entries.accept(AerialHellItems.GLAUCOPHANITE);
            entries.accept(AerialHellItems.POLISHED_GLAUCOPHANITE);

            entries.accept(AerialHellItems.CRYSTAL_BLOCK);
            entries.accept(AerialHellItems.CRYSTAL_BRICKS);
            entries.accept(AerialHellItems.CRYSTAL_BRICKS_SLAB);
            entries.accept(AerialHellItems.CRYSTAL_BRICKS_STAIRS);
            entries.accept(AerialHellItems.CRYSTAL_BRICKS_WALL);
            entries.accept(AerialHellItems.STELLAR_STONE_CRYSTAL_BLOCK);
            entries.accept(AerialHellItems.SHADOW_CRYSTAL_BLOCK);
            entries.accept(AerialHellItems.CRYSTALLIZED_LEAVES);

            entries.accept(AerialHellItems.STELLAR_GRASS_BLOCK);
            entries.accept(AerialHellItems.CHISELED_STELLAR_GRASS_BLOCK);
            entries.accept(AerialHellItems.STELLAR_DIRT);
            entries.accept(AerialHellItems.STELLAR_COARSE_DIRT);
            entries.accept(AerialHellItems.STELLAR_FARMLAND);
            entries.accept(AerialHellItems.STELLAR_DIRT_PATH);
            entries.accept(AerialHellItems.STELLAR_PODZOL);
            entries.accept(AerialHellItems.STELLAR_CRYSTAL_PODZOL);
            entries.accept(AerialHellItems.CHISELED_STELLAR_DIRT);
            entries.accept(AerialHellItems.SHADOW_GRASS_BLOCK);

            entries.accept(AerialHellItems.SLIPPERY_SAND);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS);
            entries.accept(AerialHellItems.CUT_SLIPPERY_SAND_STONE);
            entries.accept(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS);

            entries.accept(AerialHellItems.SLIPPERY_SAND_GLASS);
            entries.accept(AerialHellItems.RED_SLIPPERY_SAND_GLASS);
            entries.accept(AerialHellItems.BLACK_SLIPPERY_SAND_GLASS);
            entries.accept(AerialHellItems.BLUE_SLIPPERY_SAND_GLASS);
            entries.accept(AerialHellItems.GREEN_SLIPPERY_SAND_GLASS);
            entries.accept(AerialHellItems.SLIPPERY_SAND_GLASS_PANE);
            entries.accept(AerialHellItems.RED_SLIPPERY_SAND_GLASS_PANE);
            entries.accept(AerialHellItems.BLACK_SLIPPERY_SAND_GLASS_PANE);
            entries.accept(AerialHellItems.BLUE_SLIPPERY_SAND_GLASS_PANE);
            entries.accept(AerialHellItems.GREEN_SLIPPERY_SAND_GLASS_PANE);

            entries.accept(AerialHellItems.GHOST_BOAT_PLANKS);
            entries.accept(AerialHellItems.GHOST_BOAT_LOG);
            entries.accept(AerialHellItems.GHOST_BOAT_WOOD);
            entries.accept(AerialHellItems.GHOST_BOAT_SLAB);
            entries.accept(AerialHellItems.GHOST_BOAT_STAIRS);
            entries.accept(AerialHellItems.GHOST_BOAT_FENCE);
            entries.accept(AerialHellItems.GHOST_BOAT_GATE);
            entries.accept(AerialHellItems.GHOST_BOAT_DOOR);
            entries.accept(AerialHellItems.GHOST_BOAT_TRAPDOOR);
            entries.accept(AerialHellItems.GHOST_BOAT_CHEST);
            entries.accept(AerialHellItems.GHOST_STELLAR_COBBLESTONE);
            entries.accept(AerialHellItems.GHOST_STELLAR_FURNACE);
            entries.accept(AerialHellItems.GHOST_BOAT_WOOL);
            entries.accept(AerialHellItems.GHOST_RUBY_BLOCK);
            entries.accept(AerialHellItems.GHOST_FLUORITE_BLOCK);
            entries.accept(AerialHellItems.GHOST_AZURITE_BLOCK);
            entries.accept(AerialHellItems.GHOST_GOLD_BLOCK);
            entries.accept(AerialHellItems.GHOST_BOAT_BARREL);
            entries.accept(AerialHellItems.GHOST_BOAT_CRAFTING_TABLE);
            entries.accept(AerialHellItems.GHOST_BOAT_VINE_ROPE_SPOOL);
            entries.accept(AerialHellItems.GHOST_LANTERN);

            entries.accept(AerialHellItems.WEAK_LIGHT_REACTOR);
            entries.accept(AerialHellItems.HIGH_POWER_LIGHT_REACTOR);
            entries.accept(AerialHellItems.WEAK_SHADOW_REACTOR);
            entries.accept(AerialHellItems.HIGH_POWER_SHADOW_REACTOR);
            entries.accept(AerialHellItems.BROKEN_WEAK_LIGHT_REACTOR);
            entries.accept(AerialHellItems.BROKEN_HIGH_POWER_LIGHT_REACTOR);
            entries.accept(AerialHellItems.BROKEN_WEAK_SHADOW_REACTOR);
            entries.accept(AerialHellItems.BROKEN_HIGH_POWER_SHADOW_REACTOR);

            entries.accept(AerialHellItems.WHITE_SOLID_ETHER);
            entries.accept(AerialHellItems.BLUE_SOLID_ETHER);
            entries.accept(AerialHellItems.GOLDEN_SOLID_ETHER);
            entries.accept(AerialHellItems.GREEN_SOLID_ETHER);
            entries.accept(AerialHellItems.PURPLE_SOLID_ETHER);

            entries.accept(AerialHellItems.SMOKY_QUARTZ_BLOCK);
            entries.accept(AerialHellItems.SMOOTH_SMOKY_QUARTZ);
            entries.accept(AerialHellItems.CHISELED_SMOKY_QUARTZ_BLOCK);
            entries.accept(AerialHellItems.SMOKY_QUARTZ_BRICKS);
            entries.accept(AerialHellItems.SMOKY_QUARTZ_PILLAR);
            entries.accept(AerialHellItems.SMOKY_QUARTZ_SLAB);
            entries.accept(AerialHellItems.SMOOTH_SMOKY_QUARTZ_SLAB);
            entries.accept(AerialHellItems.SMOKY_QUARTZ_STAIRS);
            entries.accept(AerialHellItems.SMOOTH_SMOKY_QUARTZ_STAIRS);

            entries.accept(AerialHellItems.IRON_STELLAR_ORE);
            entries.accept(AerialHellItems.GOLD_STELLAR_ORE);
            entries.accept(AerialHellItems.DIAMOND_STELLAR_ORE);
            entries.accept(AerialHellItems.FLUORITE_ORE);
            entries.accept(AerialHellItems.MAGMATIC_GEL_ORE);
            entries.accept(AerialHellItems.RUBY_ORE);
            entries.accept(AerialHellItems.AZURITE_ORE);
            entries.accept(AerialHellItems.VOLUCITE_ORE);
            entries.accept(AerialHellItems.OBSIDIAN_ORE);
            entries.accept(AerialHellItems.SMOKY_QUARTZ_ORE);

            entries.accept(AerialHellItems.FLUORITE_BLOCK);
            entries.accept(AerialHellItems.MAGMATIC_GEL_BLOCK);
            entries.accept(AerialHellItems.RUBY_BLOCK);
            entries.accept(AerialHellItems.AZURITE_BLOCK);
            entries.accept(AerialHellItems.VOLUCITE_BLOCK);

            entries.accept(AerialHellItems.RAW_RUBY_BLOCK);
            entries.accept(AerialHellItems.RAW_AZURITE_BLOCK);
            entries.accept(AerialHellItems.RAW_VOLUCITE_BLOCK);

            entries.accept(AerialHellItems.ARSONIST_BLOCK);
            entries.accept(AerialHellItems.LUNATIC_CRYSTAL_BLOCK);
            entries.accept(AerialHellItems.CURSED_CRYSAL_BLOCK);

            entries.accept(AerialHellItems.SKY_CACTUS);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_PLANKS);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_BOOKSHELF);
            entries.accept(AerialHellItems.VIBRANT_SKY_CACTUS);
            entries.accept(AerialHellItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN);

            entries.accept(AerialHellItems.CLIMBING_VINE);
            entries.accept(AerialHellItems.STELLAR_SUGAR_CANE);

            entries.accept(AerialHellItems.FULL_MOON_FLOWER);

            entries.accept(AerialHellItems.VINE_BLOSSOM);
            entries.accept(AerialHellItems.LAZULI_ROOTS);
            entries.accept(AerialHellItems.STELLAR_ROOTS);
            entries.accept(AerialHellItems.DEAD_ROOTS);
            entries.accept(AerialHellItems.GLOWING_ROOTS);
            entries.accept(AerialHellItems.SHADOW_GLOWING_ROOTS);

            entries.accept(AerialHellItems.STELLAR_GRASS);
            entries.accept(AerialHellItems.STELLAR_GRASS_BALL);
            entries.accept(AerialHellItems.STELLAR_FERN);
            entries.accept(AerialHellItems.STELLAR_TALL_GRASS);
            entries.accept(AerialHellItems.STELLAR_TALL_FERN);
            entries.accept(AerialHellItems.STELLAR_VERY_TALL_GRASS);
            entries.accept(AerialHellItems.BLUISH_FERN);
            entries.accept(AerialHellItems.TALL_BLUISH_FERN);
            entries.accept(AerialHellItems.POLYCHROME_FERN);
            entries.accept(AerialHellItems.TALL_POLYCHROME_FERN);
            entries.accept(AerialHellItems.STELLAR_DEAD_BUSH);
            entries.accept(AerialHellItems.BRAMBLES);
            entries.accept(AerialHellItems.SHADOW_BRAMBLES);
            entries.accept(AerialHellItems.SHADOW_GRASS);
            entries.accept(AerialHellItems.SHADOW_GRASS_BALL);
            entries.accept(AerialHellItems.PURPLISH_STELLAR_GRASS);
            entries.accept(AerialHellItems.STELLAR_CLOVERS);
            entries.accept(AerialHellItems.GLOWING_STELLAR_GRASS);

            entries.accept(AerialHellItems.BLUE_FLOWER);
            entries.accept(AerialHellItems.BLACK_ROSE);
            entries.accept(AerialHellItems.BELLFLOWER);

            entries.accept(AerialHellItems.OSCILLATOR);
            entries.accept(AerialHellItems.FREEZER);
            entries.accept(AerialHellItems.STELLAR_FURNACE);

            entries.accept(AerialHellItems.AERIAL_TREE_CHEST);
            entries.accept(AerialHellItems.GOLDEN_BEECH_CHEST);
            entries.accept(AerialHellItems.COPPER_PINE_CHEST);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_CHEST);
            entries.accept(AerialHellItems.SHADOW_PINE_CHEST);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_CHEST);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_CHEST);
            entries.accept(AerialHellItems.GRAY_SHROOM_CHEST);

            entries.accept(AerialHellItems.AERIAL_TREE_FENCE);
            entries.accept(AerialHellItems.GOLDEN_BEECH_FENCE);
            entries.accept(AerialHellItems.COPPER_PINE_FENCE);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_FENCE);
            entries.accept(AerialHellItems.SHADOW_PINE_FENCE);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_FENCE);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_FENCE);
            entries.accept(AerialHellItems.GRAY_SHROOM_FENCE);
            entries.accept(AerialHellItems.RUBY_BARS);
            entries.accept(AerialHellItems.STELLAR_STONE_WALL);
            entries.accept(AerialHellItems.STELLAR_COBBLESTONE_WALL);
            entries.accept(AerialHellItems.STELLAR_STONE_BRICKS_WALL);
            entries.accept(AerialHellItems.MOSSY_STELLAR_STONE_WALL);
            entries.accept(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_WALL);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_WALL);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_WALL);
            entries.accept(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL);
            entries.accept(AerialHellItems.GLAUCOPHANITE_WALL);
            entries.accept(AerialHellItems.POLISHED_GLAUCOPHANITE_WALL);
            entries.accept(AerialHellItems.MAGMATIC_GEL_WALL);

            entries.accept(AerialHellItems.AERIAL_TREE_GATE);
            entries.accept(AerialHellItems.GOLDEN_BEECH_GATE);
            entries.accept(AerialHellItems.COPPER_PINE_GATE);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_GATE);
            entries.accept(AerialHellItems.SHADOW_PINE_GATE);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_GATE);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_GATE);
            entries.accept(AerialHellItems.GRAY_SHROOM_GATE);

            entries.accept(AerialHellItems.AERIAL_TREE_DOOR);
            entries.accept(AerialHellItems.GOLDEN_BEECH_DOOR);
            entries.accept(AerialHellItems.COPPER_PINE_DOOR);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_DOOR);
            entries.accept(AerialHellItems.SHADOW_PINE_DOOR);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_DOOR);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_DOOR);
            entries.accept(AerialHellItems.GRAY_SHROOM_DOOR);
            entries.accept(AerialHellItems.RUBY_DOOR);

            entries.accept(AerialHellItems.AERIAL_TREE_TRAPDOOR);
            entries.accept(AerialHellItems.GOLDEN_BEECH_TRAPDOOR);
            entries.accept(AerialHellItems.COPPER_PINE_TRAPDOOR);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_TRAPDOOR);
            entries.accept(AerialHellItems.SHADOW_PINE_TRAPDOOR);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_TRAPDOOR);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_TRAPDOOR);
            entries.accept(AerialHellItems.GRAY_SHROOM_TRAPDOOR);
            entries.accept(AerialHellItems.RUBY_TRAPDOOR);

            entries.accept(AerialHellItems.STELLAR_STONE_BUTTON);
            entries.accept(AerialHellItems.STELLAR_COBBLESTONE_BUTTON);
            entries.accept(AerialHellItems.STELLAR_STONE_BRICKS_BUTTON);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_BUTTON);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_BUTTON);
            entries.accept(AerialHellItems.AERIAL_TREE_BUTTON);
            entries.accept(AerialHellItems.GOLDEN_BEECH_BUTTON);
            entries.accept(AerialHellItems.COPPER_PINE_BUTTON);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_BUTTON);
            entries.accept(AerialHellItems.SHADOW_PINE_BUTTON);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_BUTTON);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_BUTTON);
            entries.accept(AerialHellItems.GRAY_SHROOM_BUTTON);
            entries.accept(AerialHellItems.GLAUCOPHANITE_BUTTON);

            entries.accept(AerialHellItems.STELLAR_STONE_PRESSURE_PLATE);
            entries.accept(AerialHellItems.STELLAR_COBBLESTONE_PRESSURE_PLATE);
            entries.accept(AerialHellItems.STELLAR_STONE_BRICKS_PRESSURE_PLATE);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_PRESSURE_PLATE);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE);
            entries.accept(AerialHellItems.AERIAL_TREE_PRESSURE_PLATE);
            entries.accept(AerialHellItems.GOLDEN_BEECH_PRESSURE_PLATE);
            entries.accept(AerialHellItems.COPPER_PINE_PRESSURE_PLATE);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_PRESSURE_PLATE);
            entries.accept(AerialHellItems.SHADOW_PINE_PRESSURE_PLATE);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_PRESSURE_PLATE);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_PRESSURE_PLATE);
            entries.accept(AerialHellItems.GRAY_SHROOM_PRESSURE_PLATE);
            entries.accept(AerialHellItems.GLAUCOPHANITE_PRESSURE_PLATE);

            entries.accept(AerialHellItems.AERIAL_TREE_SLAB);
            entries.accept(AerialHellItems.GOLDEN_BEECH_SLAB);
            entries.accept(AerialHellItems.COPPER_PINE_SLAB);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_SLAB);
            entries.accept(AerialHellItems.SHADOW_PINE_SLAB);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_SLAB);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_SLAB);
            entries.accept(AerialHellItems.GRAY_SHROOM_SLAB);
            entries.accept(AerialHellItems.STELLAR_STONE_SLAB);
            entries.accept(AerialHellItems.STELLAR_COBBLESTONE_SLAB);
            entries.accept(AerialHellItems.STELLAR_STONE_BRICKS_SLAB);
            entries.accept(AerialHellItems.MOSSY_STELLAR_STONE_SLAB);
            entries.accept(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_SLAB);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_SLAB);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_SLAB);
            entries.accept(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB);
            entries.accept(AerialHellItems.POLISHED_GLAUCOPHANITE_SLAB);
            entries.accept(AerialHellItems.MAGMATIC_GEL_SLAB);

            entries.accept(AerialHellItems.AERIAL_TREE_STAIRS);
            entries.accept(AerialHellItems.GOLDEN_BEECH_STAIRS);
            entries.accept(AerialHellItems.COPPER_PINE_STAIRS);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_STAIRS);
            entries.accept(AerialHellItems.SHADOW_PINE_STAIRS);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_STAIRS);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_STAIRS);
            entries.accept(AerialHellItems.GRAY_SHROOM_STAIRS);
            entries.accept(AerialHellItems.STELLAR_STONE_STAIRS);
            entries.accept(AerialHellItems.STELLAR_COBBLESTONE_STAIRS);
            entries.accept(AerialHellItems.STELLAR_STONE_BRICKS_STAIRS);
            entries.accept(AerialHellItems.MOSSY_STELLAR_STONE_STAIRS);
            entries.accept(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_STAIRS);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_STAIRS);
            entries.accept(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_STAIRS);
            entries.accept(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS);
            entries.accept(AerialHellItems.POLISHED_GLAUCOPHANITE_STAIRS);
            entries.accept(AerialHellItems.MAGMATIC_GEL_STAIRS);

            entries.accept(AerialHellItems.AERIAL_TREE_SIGN);
            entries.accept(AerialHellItems.GOLDEN_BEECH_SIGN);
            entries.accept(AerialHellItems.COPPER_PINE_SIGN);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_SIGN);
            entries.accept(AerialHellItems.SHADOW_PINE_SIGN);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_SIGN);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_SIGN);
            entries.accept(AerialHellItems.GRAY_SHROOM_SIGN);

            entries.accept(AerialHellItems.AERIAL_TREE_HANGING_SIGN);
            entries.accept(AerialHellItems.GOLDEN_BEECH_HANGING_SIGN);
            entries.accept(AerialHellItems.COPPER_PINE_HANGING_SIGN);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_HANGING_SIGN);
            entries.accept(AerialHellItems.SHADOW_PINE_HANGING_SIGN);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_HANGING_SIGN);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_HANGING_SIGN);
            entries.accept(AerialHellItems.GRAY_SHROOM_HANGING_SIGN);

            entries.accept(AerialHellItems.AERIAL_TREE_CRAFTING_TABLE);
            entries.accept(AerialHellItems.GOLDEN_BEECH_CRAFTING_TABLE);
            entries.accept(AerialHellItems.COPPER_PINE_CRAFTING_TABLE);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_CRAFTING_TABLE);
            entries.accept(AerialHellItems.SHADOW_PINE_CRAFTING_TABLE);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_CRAFTING_TABLE);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_CRAFTING_TABLE);
            entries.accept(AerialHellItems.GRAY_SHROOM_CRAFTING_TABLE);

            entries.accept(AerialHellItems.AERIAL_TREE_BARREL);
            entries.accept(AerialHellItems.GOLDEN_BEECH_BARREL);
            entries.accept(AerialHellItems.COPPER_PINE_BARREL);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_BARREL);
            entries.accept(AerialHellItems.SHADOW_PINE_BARREL);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_BARREL);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_BARREL);
            entries.accept(AerialHellItems.GRAY_SHROOM_BARREL);

            entries.accept(AerialHellItems.AERIAL_TREE_COMPOSTER);
            entries.accept(AerialHellItems.GOLDEN_BEECH_COMPOSTER);
            entries.accept(AerialHellItems.COPPER_PINE_COMPOSTER);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_COMPOSTER);
            entries.accept(AerialHellItems.SHADOW_PINE_COMPOSTER);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_COMPOSTER);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_COMPOSTER);
            entries.accept(AerialHellItems.GRAY_SHROOM_COMPOSTER);

            entries.accept(AerialHellItems.AERIAL_TREE_VINE_ROPE_SPOOL);
            entries.accept(AerialHellItems.GOLDEN_BEECH_VINE_ROPE_SPOOL);
            entries.accept(AerialHellItems.COPPER_PINE_VINE_ROPE_SPOOL);
            entries.accept(AerialHellItems.LAPIS_ROBINIA_VINE_ROPE_SPOOL);
            entries.accept(AerialHellItems.SHADOW_PINE_VINE_ROPE_SPOOL);
            entries.accept(AerialHellItems.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL);
            entries.accept(AerialHellItems.GRAY_SHROOM_VINE_ROPE_SPOOL);
        });

        CreativeModeTabEvents.modifyOutputEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_DUNGEON_BLOCKS)).register(entries ->
        {
            entries.accept(AerialHellItems.LUNATIC_LANTERN);
            entries.accept(AerialHellItems.SHADOW_LANTERN);
            entries.accept(AerialHellItems.LUNATIC_CHAIN);
            entries.accept(AerialHellItems.SHADOW_CHAIN);

            entries.accept(AerialHellItems.MUD_BRICKS);
            entries.accept(AerialHellItems.CRACKED_MUD_BRICKS);
            entries.accept(AerialHellItems.MOSSY_MUD_BRICKS);
            entries.accept(AerialHellItems.CHISELED_MUD_BRICKS);
            entries.accept(AerialHellItems.LIGHT_MUD_BRICKS);
            entries.accept(AerialHellItems.CRACKED_LIGHT_MUD_BRICKS);
            entries.accept(AerialHellItems.LUNATIC_STONE);
            entries.accept(AerialHellItems.DARK_LUNATIC_STONE);
            entries.accept(AerialHellItems.ROOF_LUNATIC_STONE);
            entries.accept(AerialHellItems.CRACKED_LUNATIC_STONE);
            entries.accept(AerialHellItems.CHISELED_LUNATIC_STONE);
            entries.accept(AerialHellItems.LIGHT_LUNATIC_STONE);
            entries.accept(AerialHellItems.ROOF_LIGHT_LUNATIC_STONE);
            entries.accept(AerialHellItems.CRACKED_LIGHT_LUNATIC_STONE);
            entries.accept(AerialHellItems.SHADOW_CATACOMBS_BRICKS);
            entries.accept(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS);
            entries.accept(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS);
            entries.accept(AerialHellItems.CHISELED_SHADOW_CATACOMBS_BRICKS);
            entries.accept(AerialHellItems.BONE_SHADOW_CATACOMBS_BRICKS);
            entries.accept(AerialHellItems.SKULL_SHADOW_CATACOMBS_BRICKS);
            entries.accept(AerialHellItems.LIGHT_SHADOW_CATACOMBS_BRICKS);
            entries.accept(AerialHellItems.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS);
            entries.accept(AerialHellItems.GOLDEN_NETHER_BRICKS);
            entries.accept(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS);
            entries.accept(AerialHellItems.CHISELED_GOLDEN_NETHER_BRICKS);
            entries.accept(AerialHellItems.LIGHT_GOLDEN_NETHER_BRICKS);
            entries.accept(AerialHellItems.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS);
            entries.accept(AerialHellItems.LUNATIC_PILLAR);
            entries.accept(AerialHellItems.LUNATIC_PILLAR_TOP);
            entries.accept(AerialHellItems.VOLUCITE_STONE);
            entries.accept(AerialHellItems.CRACKED_VOLUCITE_STONE);
            entries.accept(AerialHellItems.CHISELED_VOLUCITE_STONE);
            entries.accept(AerialHellItems.LIGHT_VOLUCITE_STONE);
            entries.accept(AerialHellItems.CRACKED_LIGHT_VOLUCITE_STONE);

            entries.accept(AerialHellItems.MUD_DUNGEON_CORE);
            entries.accept(AerialHellItems.LUNATIC_DUNGEON_CORE);
            entries.accept(AerialHellItems.SHADOW_CATACOMBS_DUNGEON_CORE);
            entries.accept(AerialHellItems.GOLDEN_NETHER_DUNGEON_CORE);
            entries.accept(AerialHellItems.VOLUCITE_DUNGEON_CORE);

            entries.accept(AerialHellItems.MUD_BRICKS_SLAB);
            entries.accept(AerialHellItems.MUD_BRICKS_STAIRS);
            entries.accept(AerialHellItems.MUD_BRICKS_WALL);
            entries.accept(AerialHellItems.CRACKED_MUD_BRICKS_SLAB);
            entries.accept(AerialHellItems.CRACKED_MUD_BRICKS_STAIRS);
            entries.accept(AerialHellItems.CRACKED_MUD_BRICKS_WALL);
            entries.accept(AerialHellItems.MOSSY_MUD_BRICKS_SLAB);
            entries.accept(AerialHellItems.MOSSY_MUD_BRICKS_STAIRS);
            entries.accept(AerialHellItems.MOSSY_MUD_BRICKS_WALL);
            entries.accept(AerialHellItems.VOLUCITE_STONE_SLAB);
            entries.accept(AerialHellItems.VOLUCITE_STONE_STAIRS);
            entries.accept(AerialHellItems.VOLUCITE_STONE_WALL);
            entries.accept(AerialHellItems.CRACKED_VOLUCITE_STONE_SLAB);
            entries.accept(AerialHellItems.CRACKED_VOLUCITE_STONE_STAIRS);
            entries.accept(AerialHellItems.CRACKED_VOLUCITE_STONE_WALL);
            entries.accept(AerialHellItems.LUNATIC_STONE_SLAB);
            entries.accept(AerialHellItems.LUNATIC_STONE_STAIRS);
            entries.accept(AerialHellItems.LUNATIC_STONE_WALL);
            entries.accept(AerialHellItems.DARK_LUNATIC_STONE_SLAB);
            entries.accept(AerialHellItems.DARK_LUNATIC_STONE_STAIRS);
            entries.accept(AerialHellItems.DARK_LUNATIC_STONE_WALL);
            entries.accept(AerialHellItems.CRACKED_LUNATIC_STONE_SLAB);
            entries.accept(AerialHellItems.CRACKED_LUNATIC_STONE_STAIRS);
            entries.accept(AerialHellItems.CRACKED_LUNATIC_STONE_WALL);
            entries.accept(AerialHellItems.SHADOW_CATACOMBS_BRICKS_SLAB);
            entries.accept(AerialHellItems.SHADOW_CATACOMBS_BRICKS_STAIRS);
            entries.accept(AerialHellItems.SHADOW_CATACOMBS_BRICKS_WALL);
            entries.accept(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB);
            entries.accept(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS);
            entries.accept(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL);
            entries.accept(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB);
            entries.accept(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS);
            entries.accept(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL);
            entries.accept(AerialHellItems.SHADOW_BARS);
            entries.accept(AerialHellItems.GOLDEN_NETHER_BRICKS_SLAB);
            entries.accept(AerialHellItems.GOLDEN_NETHER_BRICKS_STAIRS);
            entries.accept(AerialHellItems.GOLDEN_NETHER_BRICKS_WALL);
            entries.accept(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_SLAB);
            entries.accept(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS);
            entries.accept(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_WALL);

            entries.accept(AerialHellItems.TRAPPED_MUD_BRICKS);
            entries.accept(AerialHellItems.TRAPPED_LIGHT_MUD_BRICKS);
            entries.accept(AerialHellItems.TRAPPED_LUNATIC_STONE);
            entries.accept(AerialHellItems.TRAPPED_LIGHT_LUNATIC_STONE);
            entries.accept(AerialHellItems.TRAPPED_GOLDEN_NETHER_BRICKS);
            entries.accept(AerialHellItems.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS);

            entries.accept(AerialHellItems.MUD_BONE_BLOCK);
            entries.accept(AerialHellItems.MUD_BONE_PILE_BLOCK);
            entries.accept(AerialHellItems.THORNY_COBWEB);
            entries.accept(AerialHellItems.AERIAL_NETHERRACK);
            entries.accept(AerialHellItems.AERIAL_NETHERRACK_SLAB);
            entries.accept(AerialHellItems.AERIAL_NETHERRACK_STAIRS);
            entries.accept(AerialHellItems.AERIAL_NETHERRACK_WALL);

            entries.accept(AerialHellItems.MUD_BOOKSHELF);
            entries.accept(AerialHellItems.LUNATIC_BOOKSHELF);
            entries.accept(AerialHellItems.GOLDEN_NETHER_BOOKSHELF);
            entries.accept(AerialHellItems.SHADOW_CATACOMBS_BOOKSHELF);
            entries.accept(AerialHellItems.VOLUCITE_BOOKSHELF);

            entries.accept(AerialHellItems.MUD_GLYPH_BLOCK);
            entries.accept(AerialHellItems.LUNATIC_GLYPH_BLOCK);
            entries.accept(AerialHellItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK);
            entries.accept(AerialHellItems.VOLUCITE_GLYPH_BLOCK);
            entries.accept(AerialHellItems.SHADOW_CATACOMBS_GLYPH_BLOCK);

            entries.accept(AerialHellItems.MUD_CHEST);
            entries.accept(AerialHellItems.LUNATIC_CHEST);
            entries.accept(AerialHellItems.VOLUCITE_CHEST);
            entries.accept(AerialHellItems.SHADOW_CATACOMBS_CHEST);
            entries.accept(AerialHellItems.GOLDEN_NETHER_CHEST);

            entries.accept(AerialHellItems.MUD_CYCLE_MAGE_TROPHY);
            entries.accept(AerialHellItems.LUNAR_PRIEST_TROPHY);
            entries.accept(AerialHellItems.LILITH_TROPHY);
            entries.accept(AerialHellItems.CHAINED_GOD_TROPHY);
        });
        CreativeModeTabEvents.modifyOutputEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_TOOLS)).register(entries ->
        {
            entries.accept(AerialHellItems.SKY_WOOD_PICKAXE);
            entries.accept(AerialHellItems.STELLAR_STONE_PICKAXE);
            entries.accept(AerialHellItems.RUBY_PICKAXE);
            entries.accept(AerialHellItems.AZURITE_PICKAXE);
            entries.accept(AerialHellItems.MAGMATIC_GEL_PICKAXE);
            entries.accept(AerialHellItems.OBSIDIAN_PICKAXE);
            entries.accept(AerialHellItems.VOLUCITE_PICKAXE);
            entries.accept(AerialHellItems.LUNATIC_PICKAXE);
            entries.accept(AerialHellItems.ARSONIST_PICKAXE);
            entries.accept(AerialHellItems.MAGMA_CUBE_PICKAXE);
            entries.accept(AerialHellItems.STELLAR_STONE_BREAKER);

            entries.accept(AerialHellItems.SKY_WOOD_SHOVEL);
            entries.accept(AerialHellItems.STELLAR_STONE_SHOVEL);
            entries.accept(AerialHellItems.RUBY_SHOVEL);
            entries.accept(AerialHellItems.AZURITE_SHOVEL);
            entries.accept(AerialHellItems.MAGMATIC_GEL_SHOVEL);
            entries.accept(AerialHellItems.OBSIDIAN_SHOVEL);
            entries.accept(AerialHellItems.VOLUCITE_SHOVEL);
            entries.accept(AerialHellItems.LUNATIC_SHOVEL);
            entries.accept(AerialHellItems.ARSONIST_SHOVEL);
            entries.accept(AerialHellItems.MAGMA_CUBE_SHOVEL);

            entries.accept(AerialHellItems.SKY_WOOD_AXE);
            entries.accept(AerialHellItems.STELLAR_STONE_AXE);
            entries.accept(AerialHellItems.RUBY_AXE);
            entries.accept(AerialHellItems.AZURITE_AXE);
            entries.accept(AerialHellItems.MAGMATIC_GEL_AXE);
            entries.accept(AerialHellItems.OBSIDIAN_AXE);
            entries.accept(AerialHellItems.VOLUCITE_AXE);
            entries.accept(AerialHellItems.LUNATIC_AXE);
            entries.accept(AerialHellItems.ARSONIST_AXE);

            entries.accept(AerialHellItems.SKY_WOOD_HOE);
            entries.accept(AerialHellItems.STELLAR_STONE_HOE);
            entries.accept(AerialHellItems.RUBY_HOE);
            entries.accept(AerialHellItems.AZURITE_HOE);
            entries.accept(AerialHellItems.MAGMATIC_GEL_HOE);
            entries.accept(AerialHellItems.OBSIDIAN_HOE);
            entries.accept(AerialHellItems.VOLUCITE_HOE);
            entries.accept(AerialHellItems.LUNATIC_HOE);
            entries.accept(AerialHellItems.ARSONIST_HOE);
        });

        CreativeModeTabEvents.modifyOutputEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_COMBAT)).register(entries ->
        {
            entries.accept(AerialHellItems.IRON_SHURIKEN);
            entries.accept(AerialHellItems.GOLD_SHURIKEN);
            entries.accept(AerialHellItems.DIAMOND_SHURIKEN);
            entries.accept(AerialHellItems.NETHERITE_SHURIKEN);
            entries.accept(AerialHellItems.RUBY_SHURIKEN);
            entries.accept(AerialHellItems.AZURITE_SHURIKEN);
            entries.accept(AerialHellItems.MAGMATIC_GEL_SHURIKEN);
            entries.accept(AerialHellItems.VOLUCITE_SHURIKEN);
            entries.accept(AerialHellItems.OBSIDIAN_SHURIKEN);
            entries.accept(AerialHellItems.LUNATIC_CRYSTAL_SHURIKEN);
            entries.accept(AerialHellItems.ARSONIST_SHURIKEN);
            entries.accept(AerialHellItems.LIGHTNING_SHURIKEN);

            entries.accept(AerialHellItems.RUBY_BLOWPIPE_ARROW);
            entries.accept(AerialHellItems.VOLUCITE_BLOWPIPE_ARROW);
            entries.accept(AerialHellItems.RUBY_BLOWPIPE);
            entries.accept(AerialHellItems.VOLUCITE_BLOWPIPE);

            entries.accept(AerialHellItems.SKY_WOOD_SWORD);
            entries.accept(AerialHellItems.STELLAR_STONE_SWORD);
            entries.accept(AerialHellItems.RUBY_SWORD);
            entries.accept(AerialHellItems.AZURITE_SWORD);
            entries.accept(AerialHellItems.MAGMATIC_GEL_SWORD);
            entries.accept(AerialHellItems.OBSIDIAN_SWORD);
            entries.accept(AerialHellItems.VOLUCITE_SWORD);
            entries.accept(AerialHellItems.LUNATIC_SWORD);
            entries.accept(AerialHellItems.ARSONIST_SWORD);

            entries.accept(AerialHellItems.HEAVY_SWORD);
            entries.accept(AerialHellItems.HEALTH_BOOST_SWORD);
            entries.accept(AerialHellItems.NINJA_SWORD);
            entries.accept(AerialHellItems.NINJA_MASTER_SWORD);
            entries.accept(AerialHellItems.GLOUTON_SWORD);
            entries.accept(AerialHellItems.RANDOM_SWORD);
            entries.accept(AerialHellItems.DISLOYAL_SWORD);
            entries.accept(AerialHellItems.CURSED_SWORD);
            entries.accept(AerialHellItems.ABSOLUTE_ZERO_SWORD);
            entries.accept(AerialHellItems.SWORD_OF_LIGHT);
            entries.accept(AerialHellItems.ANTIDOTE_SWORD);
            entries.accept(AerialHellItems.NETHERIAN_KING_SWORD);
            entries.accept(AerialHellItems.GLASS_CANON_SWORD);
            entries.accept(AerialHellItems.GOD_SWORD);

            entries.accept(AerialHellItems.FORGOTTEN_BATTLE_TRIDENT);

            entries.accept(AerialHellItems.HEAVY_AXE);
            entries.accept(AerialHellItems.AXE_OF_LIGHT);
            entries.accept(AerialHellItems.CURSED_AXE);
            entries.accept(AerialHellItems.BERSERK_AXE);
            entries.accept(AerialHellItems.REAPER_SCYTHE);

            entries.accept(AerialHellItems.RUBY_HELMET);
            entries.accept(AerialHellItems.RUBY_CHESTPLATE);
            entries.accept(AerialHellItems.RUBY_LEGGINGS);
            entries.accept(AerialHellItems.RUBY_BOOTS);

            entries.accept(AerialHellItems.AZURITE_HELMET);
            entries.accept(AerialHellItems.AZURITE_CHESTPLATE);
            entries.accept(AerialHellItems.AZURITE_LEGGINGS);
            entries.accept(AerialHellItems.AZURITE_BOOTS);

            entries.accept(AerialHellItems.OBSIDIAN_HELMET);
            entries.accept(AerialHellItems.OBSIDIAN_CHESTPLATE);
            entries.accept(AerialHellItems.OBSIDIAN_LEGGINGS);
            entries.accept(AerialHellItems.OBSIDIAN_BOOTS);

            entries.accept(AerialHellItems.VOLUCITE_HELMET);
            entries.accept(AerialHellItems.VOLUCITE_CHESTPLATE);
            entries.accept(AerialHellItems.VOLUCITE_LEGGINGS);
            entries.accept(AerialHellItems.VOLUCITE_BOOTS);

            entries.accept(AerialHellItems.MAGMATIC_GEL_HELMET);
            entries.accept(AerialHellItems.MAGMATIC_GEL_CHESTPLATE);
            entries.accept(AerialHellItems.MAGMATIC_GEL_LEGGINGS);
            entries.accept(AerialHellItems.MAGMATIC_GEL_BOOTS);

            entries.accept(AerialHellItems.LUNATIC_HELMET);
            entries.accept(AerialHellItems.LUNATIC_CHESTPLATE);
            entries.accept(AerialHellItems.LUNATIC_LEGGINGS);
            entries.accept(AerialHellItems.LUNATIC_BOOTS);

            entries.accept(AerialHellItems.ARSONIST_HELMET);
            entries.accept(AerialHellItems.ARSONIST_CHESTPLATE);
            entries.accept(AerialHellItems.ARSONIST_LEGGINGS);
            entries.accept(AerialHellItems.ARSONIST_BOOTS);

            entries.accept(AerialHellItems.SHADOW_HELMET);
            entries.accept(AerialHellItems.SHADOW_CHESTPLATE);
            entries.accept(AerialHellItems.SHADOW_LEGGINGS);
            entries.accept(AerialHellItems.SHADOW_BOOTS);
        });

        CreativeModeTabEvents.modifyOutputEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_FOODSTUFFS)).register(entries ->
        {
            entries.accept(AerialHellItems.AERIAL_BERRY);
            entries.accept(AerialHellItems.ROASTED_AERIAL_BERRY);
            entries.accept(AerialHellItems.VIBRANT_AERIAL_BERRY);
            entries.accept(AerialHellItems.FROZEN_AERIAL_BERRY);
            entries.accept(AerialHellItems.RUBY_AERIAL_BERRY);
            entries.accept(AerialHellItems.VOLUCITE_AERIAL_BERRY);
            entries.accept(AerialHellItems.GODS_VOLUCITE_AERIAL_BERRY);
            entries.accept(AerialHellItems.STELLAR_BREAD);
            entries.accept(AerialHellItems.FROZEN_MUTTON);
            entries.accept(AerialHellItems.VIBRANT_CHICKEN);
            entries.accept(AerialHellItems.FROZEN_CHICKEN);
            entries.accept(AerialHellItems.GLOWING_STICK_FRUIT);
            entries.accept(AerialHellItems.VIBRANT_GLOWING_STICK_FRUIT);
            entries.accept(AerialHellItems.FROZEN_GLOWING_STICK_FRUIT);
            entries.accept(AerialHellItems.CORTINARIUS_VIOLACEUS_PIECE);
            entries.accept(AerialHellItems.GANODERMA_APPLANATUM_PIECE);
            entries.accept(AerialHellItems.DARK_SHADOW_FRUIT);
            entries.accept(AerialHellItems.PURPLE_SHADOW_FRUIT);
            entries.accept(AerialHellItems.SHADOW_FRUIT_STEW);
            entries.accept(AerialHellItems.SOLID_ETHER_SOUP);
            entries.accept(AerialHellItems.VIBRANT_SOLID_ETHER_SOUP);
            entries.accept(AerialHellItems.FROZEN_SOLID_ETHER_SOUP);
            entries.accept(AerialHellItems.SHADOW_SPIDER_EYE);
            entries.accept(AerialHellItems.PHANTOM_MEAT);
            entries.accept(AerialHellItems.VIBRANT_PHANTOM_MEAT);
            entries.accept(AerialHellItems.FROZEN_PHANTOM_MEAT);
            entries.accept(AerialHellItems.COOKED_PHANTOM_MEAT);
            entries.accept(AerialHellItems.TURTLE_MEAT);
            entries.accept(AerialHellItems.VIBRANT_TURTLE_MEAT);
            entries.accept(AerialHellItems.FROZEN_TURTLE_MEAT);
            entries.accept(AerialHellItems.COOKED_TURTLE_MEAT);
            entries.accept(AerialHellItems.COPPER_PINE_CONE);
            entries.accept(AerialHellItems.AZURITE_COPPER_PINE_CONE);
            entries.accept(AerialHellItems.PHOENIX_FEATHER);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER);
            entries.accept(AerialHellItems.VIBRANT_SKY_CACTUS_FIBER);
            entries.accept(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT);
            entries.accept(AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT);
            entries.accept(AerialHellItems.GOLDEN_SOLID_ETHER_FRAGMENT);
            entries.accept(AerialHellItems.GREEN_SOLID_ETHER_FRAGMENT);
            entries.accept(AerialHellItems.PURPLE_SOLID_ETHER_FRAGMENT);
            entries.accept(AerialHellItems.GOLDEN_NETHER_MEAT_PIECE);
            entries.accept(AerialHellItems.GOLDEN_NETHER_STEAK);
            entries.accept(AerialHellItems.VIBRANT_GOLDEN_NETHER_STEAK);
        });

        CreativeModeTabEvents.modifyOutputEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_MISCELLANEOUS)).register(entries ->
        {
            entries.accept(AerialHellItems.STELLAR_PORTAL_FRAME_BRICK);
            entries.accept(AerialHellItems.STELLAR_LIGHTER);

            entries.accept(AerialHellItems.CRYSTAL);
            entries.accept(AerialHellItems.STELLAR_STONE_CRYSTAL);
            entries.accept(AerialHellItems.SHADOW_CRYSTAL);

            entries.accept(AerialHellItems.SMOKY_QUARTZ);

            entries.accept(AerialHellItems.MUD_BONE);
            entries.accept(AerialHellItems.STELLAR_EGG);
            entries.accept(AerialHellItems.DIMENSION_SHATTERER_PROJECTILE);

            entries.accept(AerialHellItems.FLUORITE);
            entries.accept(AerialHellItems.MAGMATIC_GEL);
            entries.accept(AerialHellItems.RUBY);
            entries.accept(AerialHellItems.AZURITE_CRYSTAL);
            entries.accept(AerialHellItems.VOLUCITE_VIBRANT);

            entries.accept(AerialHellItems.RAW_RUBY);
            entries.accept(AerialHellItems.RAW_AZURITE);
            entries.accept(AerialHellItems.RAW_VOLUCITE);

            entries.accept(AerialHellItems.OVERHEATED_RUBY);
            entries.accept(AerialHellItems.OVERHEATED_VOLUCITE);

            entries.accept(AerialHellItems.ARSONIST_INGOT);
            entries.accept(AerialHellItems.LUNATIC_CRYSTAL);
            entries.accept(AerialHellItems.OBSIDIAN_SHARD);
            entries.accept(AerialHellItems.CURSED_CRYSAL);

            entries.accept(AerialHellItems.ARSONIST_UPGRADE_SMITHING_TEMPLATE);

            entries.accept(AerialHellItems.AERIAL_BERRY_SEEDS);
            entries.accept(AerialHellItems.VIBRANT_AERIAL_BERRY_SEEDS);
            entries.accept(AerialHellItems.STELLAR_WHEAT_SEEDS);
            entries.accept(AerialHellItems.STELLAR_WHEAT);
            entries.accept(AerialHellItems.BLUE_MEANIE_SPORES);
            entries.accept(AerialHellItems.BLUE_MEANIE_CAP);

            entries.accept(AerialHellItems.SKY_STICK);
            entries.accept(AerialHellItems.SKY_BOWL);
            entries.accept(AerialHellItems.SHADOW_SHARD);
            entries.accept(AerialHellItems.ROTTEN_LEATHER);
            entries.accept(AerialHellItems.VENOMOUS_SNAKE_SKIN);

            entries.accept(AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET);
            entries.accept(AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET);
            entries.accept(AerialHellItems.RUBY_BUCKET);
            entries.accept(AerialHellItems.RUBY_WATER_BUCKET);
            entries.accept(AerialHellItems.RUBY_MILK_BUCKET);

            entries.accept(AerialHellItems.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP);
            entries.accept(AerialHellItems.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER);
            entries.accept(AerialHellItems.MUSIC_DISC_ENTHUSIAST_TOURS);
            entries.accept(AerialHellItems.MUSIC_DISC_BMINOR_ARULO);
            entries.accept(AerialHellItems.MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP);

            entries.accept(AerialHellItems.REGENERATION_TOTEM);
            entries.accept(AerialHellItems.SPEED_TOTEM);
            entries.accept(AerialHellItems.SPEED_II_TOTEM);
            entries.accept(AerialHellItems.NIGHT_VISION_TOTEM);
            entries.accept(AerialHellItems.AGILITY_TOTEM);
            entries.accept(AerialHellItems.HERO_TOTEM);
            entries.accept(AerialHellItems.HEAD_IN_THE_CLOUDS_TOTEM);
            entries.accept(AerialHellItems.GOD_TOTEM);
            entries.accept(AerialHellItems.CURSED_TOTEM);
            entries.accept(AerialHellItems.SHADOW_TOTEM);

            entries.accept(AerialHellItems.AERIAL_HELL_PAINTING);
        });

        CreativeModeTabEvents.modifyOutputEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_SPAWN_EGGS)).register(entries ->
        {
            entries.accept(AerialHellItems.AERIAL_TREE_CHEST_MIMIC);
            entries.accept(AerialHellItems.GOLDEN_BEECH_CHEST_MIMIC);
            entries.accept(AerialHellItems.COPPER_PINE_CHEST_MIMIC);
            entries.accept(AerialHellItems.SKY_CACTUS_FIBER_CHEST_MIMIC);
            entries.accept(AerialHellItems.SHADOW_PINE_BARREL_MIMIC);

            entries.accept(AerialHellItems.STELLAR_STONE_AUTOMATON_SPAWN_EGG);
            entries.accept(AerialHellItems.EVIL_COW_SPAWN_EGG);
            entries.accept(AerialHellItems.CORTINARIUS_COW_SPAWN_EGG);
            entries.accept(AerialHellItems.STELLAR_ENT_SPAWN_EGG);
            entries.accept(AerialHellItems.VENOMOUS_SNAKE_SPAWN_EGG);
            entries.accept(AerialHellItems.WORM_SPAWN_EGG);
            entries.accept(AerialHellItems.STELLAR_CHICKEN_SPAWN_EGG);
            entries.accept(AerialHellItems.STELLAR_BOAR_SPAWN_EGG);
            entries.accept(AerialHellItems.SHROOMBOOM_SPAWN_EGG);
            entries.accept(AerialHellItems.VERDIGRIS_ZOMBIE_SPAWN_EGG);
            entries.accept(AerialHellItems.MUMMY_SPAWN_EGG);
            entries.accept(AerialHellItems.SLIME_PIRATE_SPAWN_EGG);
            entries.accept(AerialHellItems.SLIME_NINJA_PIRATE_SPAWN_EGG);
            entries.accept(AerialHellItems.GHOST_SLIME_PIRATE_SPAWN_EGG);
            entries.accept(AerialHellItems.GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG);
            entries.accept(AerialHellItems.SANDY_SHEEP_SPAWN_EGG);
            entries.accept(AerialHellItems.GLIDING_TURTLE_SPAWN_EGG);
            entries.accept(AerialHellItems.FAT_PHANTOM_SPAWN_EGG);
            entries.accept(AerialHellItems.KODAMA_SPAWN_EGG);
            entries.accept(AerialHellItems.MUD_GOLEM_SPAWN_EGG);
            entries.accept(AerialHellItems.MUD_SOLDIER_SPAWN_EGG);
            entries.accept(AerialHellItems.MUD_CYCLE_MAGE_SPAWN_EGG);
            entries.accept(AerialHellItems.HELL_SPIDER_SPAWN_EGG);
            entries.accept(AerialHellItems.CRYSTAL_GOLEM_SPAWN_EGG);
            entries.accept(AerialHellItems.CRYSTAL_SLIME_SPAWN_EGG);
            entries.accept(AerialHellItems.CRYSTAL_SPIDER_SPAWN_EGG);
            entries.accept(AerialHellItems.LUNATIC_PRIEST_SPAWN_EGG);
            entries.accept(AerialHellItems.CRYSTAL_CATERPILLAR_SPAWN_EGG);
            entries.accept(AerialHellItems.FOREST_CATERPILLAR_SPAWN_EGG);
            entries.accept(AerialHellItems.TORN_SPIRIT_SPAWN_EGG);
            entries.accept(AerialHellItems.CHAINED_GOD_SPAWN_EGG);
            entries.accept(AerialHellItems.ICE_SPIRIT_SPAWN_EGG);
            entries.accept(AerialHellItems.FIRE_SPIRIT_SPAWN_EGG);
            entries.accept(AerialHellItems.ELECTRO_SPIRIT_SPAWN_EGG);
            entries.accept(AerialHellItems.FLYING_JELLYFISH_SPAWN_EGG);
            entries.accept(AerialHellItems.SHADOW_FLYING_SKULL_SPAWN_EGG);
            entries.accept(AerialHellItems.SHADOW_TROLL_SPAWN_EGG);
            entries.accept(AerialHellItems.SHADOW_AUTOMATON_SPAWN_EGG);
            entries.accept(AerialHellItems.SHADOW_SPIDER_SPAWN_EGG);
            entries.accept(AerialHellItems.LILITH_SPAWN_EGG);
        });
    }
}
