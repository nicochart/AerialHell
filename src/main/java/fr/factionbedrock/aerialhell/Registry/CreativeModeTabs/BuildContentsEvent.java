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
            entries.add(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK_ITEM);
            entries.add(AerialHellItems.STELLAR_LIGHTER);
        });

        ItemGroupEvents.modifyEntriesEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_BLOCKS)).register(entries ->
        {
            entries.add(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK_ITEM);
            entries.add(AerialHellItems.STELLAR_LIGHTER);

            entries.add(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK_ITEM);
            entries.add(AerialHellItems.STELLAR_PORTAL_FRAME_ORE_ITEM);
            entries.add(AerialHellItems.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE_ITEM);

            entries.add(AerialHellItems.FLUORITE_TORCH_ITEM);
            entries.add(AerialHellItems.VOLUCITE_TORCH_ITEM);
            entries.add(AerialHellItems.SHADOW_TORCH_ITEM);
            entries.add(AerialHellItems.FLUORITE_LANTERN_ITEM);
            entries.add(AerialHellItems.RUBY_LANTERN_ITEM);
            entries.add(AerialHellItems.RUBY_FLUORITE_LANTERN_ITEM);
            entries.add(AerialHellItems.VOLUCITE_LANTERN_ITEM);
            entries.add(AerialHellItems.VOLUCITE_FLUORITE_LANTERN_ITEM);

            entries.add(AerialHellItems.RUBY_CHAIN_ITEM);
            entries.add(AerialHellItems.VOLUCITE_CHAIN_ITEM);

            entries.add(AerialHellItems.GIANT_ROOT_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_LOG_ITEM);
            entries.add(AerialHellItems.STRIPPED_AERIAL_TREE_LOG_ITEM);
            entries.add(AerialHellItems.AERIAL_TREE_WOOD_ITEM);
            entries.add(AerialHellItems.STRIPPED_AERIAL_TREE_WOOD_ITEM);
            entries.add(AerialHellItems.AERIAL_TREE_LEAVES_ITEM);
            entries.add(AerialHellItems.AERIAL_TREE_PLANKS_ITEM);
            entries.add(AerialHellItems.CHISELED_AERIAL_TREE_PLANKS_ITEM);
            entries.add(AerialHellItems.AERIAL_TREE_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.AERIAL_TREE_SAPLING_ITEM);

            entries.add(AerialHellItems.PETRIFIED_AERIAL_TREE_LOG_ITEM);

            entries.add(AerialHellItems.GOLDEN_BEECH_LOG_ITEM);
            entries.add(AerialHellItems.STRIPPED_GOLDEN_BEECH_LOG_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_WOOD_ITEM);
            entries.add(AerialHellItems.STRIPPED_GOLDEN_BEECH_WOOD_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_LEAVES_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_PLANKS_ITEM);
            entries.add(AerialHellItems.CHISELED_GOLDEN_BEECH_PLANKS_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_SAPLING_ITEM);

            entries.add(AerialHellItems.COPPER_PINE_LOG_ITEM);
            entries.add(AerialHellItems.STRIPPED_COPPER_PINE_LOG_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_WOOD_ITEM);
            entries.add(AerialHellItems.STRIPPED_COPPER_PINE_WOOD_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_PLANKS_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_LEAVES_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_SAPLING_ITEM);

            entries.add(AerialHellItems.LAPIS_ROBINIA_LOG_ITEM);
            entries.add(AerialHellItems.ENCHANTED_LAPIS_ROBINIA_LOG_ITEM);
            entries.add(AerialHellItems.STRIPPED_LAPIS_ROBINIA_LOG_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_WOOD_ITEM);
            entries.add(AerialHellItems.STRIPPED_LAPIS_ROBINIA_WOOD_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_LEAVES_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_PLANKS_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_SAPLING_ITEM);

            entries.add(AerialHellItems.SHADOW_PINE_LOG_ITEM);
            entries.add(AerialHellItems.EYE_SHADOW_PINE_LOG_ITEM);
            entries.add(AerialHellItems.STRIPPED_SHADOW_PINE_LOG_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_WOOD_ITEM);
            entries.add(AerialHellItems.STRIPPED_SHADOW_PINE_WOOD_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_LEAVES_ITEM);
            entries.add(AerialHellItems.PURPLE_SHADOW_PINE_LEAVES_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_PLANKS_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_SAPLING_ITEM);
            entries.add(AerialHellItems.PURPLE_SHADOW_PINE_SAPLING_ITEM);

            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_LOG_ITEM);
            entries.add(AerialHellItems.STRIPPED_STELLAR_JUNGLE_TREE_LOG_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_WOOD_ITEM);
            entries.add(AerialHellItems.STRIPPED_STELLAR_JUNGLE_TREE_WOOD_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_LEAVES_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_PLANKS_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_SAPLING_ITEM);
            entries.add(AerialHellItems.DEAD_STELLAR_JUNGLE_TREE_LOG_ITEM);

            entries.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_STEM_ITEM);
            entries.add(AerialHellItems.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM_ITEM);
            entries.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM_ITEM);
            entries.add(AerialHellItems.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM_ITEM);
            entries.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK_ITEM);
            entries.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_LIGHT_ITEM);
            entries.add(AerialHellItems.CORTINARIUS_VIOLACEUS_ITEM);
            entries.add(AerialHellItems.GLOWING_BOLETUS_ITEM);
            entries.add(AerialHellItems.TALL_GLOWING_BOLETUS_ITEM);
            entries.add(AerialHellItems.BLUE_MEANIE_CLUSTER_ITEM);
            entries.add(AerialHellItems.GIANT_ROOT_SHROOM_ITEM);

            entries.add(AerialHellItems.GIANT_VERDIGRIS_AGARIC_STEM_ITEM);
            entries.add(AerialHellItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM_ITEM);
            entries.add(AerialHellItems.GIANT_VERDIGRIS_AGARIC_BARK_STEM_ITEM);
            entries.add(AerialHellItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM_ITEM);
            entries.add(AerialHellItems.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK_ITEM);
            entries.add(AerialHellItems.VERDIGRIS_AGARIC_ITEM);

            entries.add(AerialHellItems.GIANT_GANODERMA_APPLANATUM_BLOCK_ITEM);

            entries.add(AerialHellItems.GRAY_SHROOM_PLANKS_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_BOOKSHELF_ITEM);

            entries.add(AerialHellItems.SHADOW_AERIAL_TREE_LOG_ITEM);
            entries.add(AerialHellItems.SHADOW_GOLDEN_BEECH_LOG_ITEM);
            entries.add(AerialHellItems.SHADOW_COPPER_PINE_LOG_ITEM);
            entries.add(AerialHellItems.SHADOW_LAPIS_ROBINIA_LOG_ITEM);
            entries.add(AerialHellItems.SHADOW_STELLAR_JUNGLE_TREE_LOG_ITEM);
            entries.add(AerialHellItems.HOLLOW_SHADOW_PINE_LOG_ITEM);
            entries.add(AerialHellItems.SHADOW_AERIAL_TREE_LEAVES_ITEM);
            entries.add(AerialHellItems.SHADOW_GOLDEN_BEECH_LEAVES_ITEM);
            entries.add(AerialHellItems.SHADOW_COPPER_PINE_LEAVES_ITEM);
            entries.add(AerialHellItems.SHADOW_LAPIS_ROBINIA_LEAVES_ITEM);
            entries.add(AerialHellItems.SHADOW_STELLAR_JUNGLE_TREE_LEAVES_ITEM);
            entries.add(AerialHellItems.HOLLOW_SHADOW_PINE_LEAVES_ITEM);
            entries.add(AerialHellItems.HOLLOW_PURPLE_SHADOW_PINE_LEAVES_ITEM);

            entries.add(AerialHellItems.SKY_LADDER_ITEM);

            entries.add(AerialHellItems.STELLAR_STONE_ITEM);
            entries.add(AerialHellItems.SHADOW_STONE_ITEM);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_ITEM);
            entries.add(AerialHellItems.MOSSY_STELLAR_STONE_ITEM);
            entries.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_ITEM);
            entries.add(AerialHellItems.STELLAR_CLAY_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_ITEM);
            entries.add(AerialHellItems.GLAUCOPHANITE_ITEM);
            entries.add(AerialHellItems.POLISHED_GLAUCOPHANITE_ITEM);

            entries.add(AerialHellItems.CRYSTAL_BLOCK_ITEM);
            entries.add(AerialHellItems.CRYSTAL_BRICKS_ITEM);
            entries.add(AerialHellItems.CRYSTAL_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.CRYSTAL_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.CRYSTAL_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_CRYSTAL_BLOCK_ITEM);
            entries.add(AerialHellItems.SHADOW_CRYSTAL_BLOCK_ITEM);
            entries.add(AerialHellItems.CRYSTALLIZED_LEAVES_ITEM);

            entries.add(AerialHellItems.STELLAR_GRASS_BLOCK_ITEM);
            entries.add(AerialHellItems.CHISELED_STELLAR_GRASS_BLOCK_ITEM);
            entries.add(AerialHellItems.STELLAR_DIRT_ITEM);
            entries.add(AerialHellItems.STELLAR_COARSE_DIRT_ITEM);
            entries.add(AerialHellItems.STELLAR_FARMLAND_ITEM);
            entries.add(AerialHellItems.STELLAR_DIRT_PATH_ITEM);
            entries.add(AerialHellItems.STELLAR_PODZOL_ITEM);
            entries.add(AerialHellItems.STELLAR_CRYSTAL_PODZOL_ITEM);
            entries.add(AerialHellItems.CHISELED_STELLAR_DIRT_ITEM);
            entries.add(AerialHellItems.SHADOW_GRASS_BLOCK_ITEM);

            entries.add(AerialHellItems.SLIPPERY_SAND_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_ITEM);
            entries.add(AerialHellItems.CUT_SLIPPERY_SAND_STONE_ITEM);
            entries.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_ITEM);

            entries.add(AerialHellItems.SLIPPERY_SAND_GLASS_ITEM);
            entries.add(AerialHellItems.RED_SLIPPERY_SAND_GLASS_ITEM);
            entries.add(AerialHellItems.BLACK_SLIPPERY_SAND_GLASS_ITEM);
            entries.add(AerialHellItems.BLUE_SLIPPERY_SAND_GLASS_ITEM);
            entries.add(AerialHellItems.GREEN_SLIPPERY_SAND_GLASS_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_GLASS_PANE_ITEM);
            entries.add(AerialHellItems.RED_SLIPPERY_SAND_GLASS_PANE_ITEM);
            entries.add(AerialHellItems.BLACK_SLIPPERY_SAND_GLASS_PANE_ITEM);
            entries.add(AerialHellItems.BLUE_SLIPPERY_SAND_GLASS_PANE_ITEM);
            entries.add(AerialHellItems.GREEN_SLIPPERY_SAND_GLASS_PANE_ITEM);

            entries.add(AerialHellItems.GHOST_BOAT_PLANKS_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_LOG_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_WOOD_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_SLAB_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_STAIRS_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_FENCE_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_GATE_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_DOOR_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_TRAPDOOR_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_CHEST_ITEM);
            entries.add(AerialHellItems.GHOST_STELLAR_COBBLESTONE_ITEM);
            entries.add(AerialHellItems.GHOST_STELLAR_FURNACE_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_WOOL_ITEM);
            entries.add(AerialHellItems.GHOST_RUBY_BLOCK_ITEM);
            entries.add(AerialHellItems.GHOST_FLUORITE_BLOCK_ITEM);
            entries.add(AerialHellItems.GHOST_AZURITE_BLOCK_ITEM);
            entries.add(AerialHellItems.GHOST_GOLD_BLOCK_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_BARREL_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_CRAFTING_TABLE_ITEM);
            entries.add(AerialHellItems.GHOST_BOAT_VINE_ROPE_SPOOL_ITEM);
            entries.add(AerialHellItems.GHOST_LANTERN_ITEM);

            entries.add(AerialHellItems.WEAK_LIGHT_REACTOR_ITEM);
            entries.add(AerialHellItems.HIGH_POWER_LIGHT_REACTOR_ITEM);
            entries.add(AerialHellItems.WEAK_SHADOW_REACTOR_ITEM);
            entries.add(AerialHellItems.HIGH_POWER_SHADOW_REACTOR_ITEM);
            entries.add(AerialHellItems.BROKEN_WEAK_LIGHT_REACTOR_ITEM);
            entries.add(AerialHellItems.BROKEN_HIGH_POWER_LIGHT_REACTOR_ITEM);
            entries.add(AerialHellItems.BROKEN_WEAK_SHADOW_REACTOR_ITEM);
            entries.add(AerialHellItems.BROKEN_HIGH_POWER_SHADOW_REACTOR_ITEM);

            entries.add(AerialHellItems.WHITE_SOLID_ETHER_ITEM);
            entries.add(AerialHellItems.BLUE_SOLID_ETHER_ITEM);
            entries.add(AerialHellItems.GOLDEN_SOLID_ETHER_ITEM);
            entries.add(AerialHellItems.GREEN_SOLID_ETHER_ITEM);
            entries.add(AerialHellItems.PURPLE_SOLID_ETHER_ITEM);

            entries.add(AerialHellItems.SMOKY_QUARTZ_BLOCK_ITEM);
            entries.add(AerialHellItems.SMOOTH_SMOKY_QUARTZ_ITEM);
            entries.add(AerialHellItems.CHISELED_SMOKY_QUARTZ_BLOCK_ITEM);
            entries.add(AerialHellItems.SMOKY_QUARTZ_BRICKS_ITEM);
            entries.add(AerialHellItems.SMOKY_QUARTZ_PILLAR_ITEM);
            entries.add(AerialHellItems.SMOKY_QUARTZ_SLAB_ITEM);
            entries.add(AerialHellItems.SMOOTH_SMOKY_QUARTZ_SLAB_ITEM);
            entries.add(AerialHellItems.SMOKY_QUARTZ_STAIRS_ITEM);
            entries.add(AerialHellItems.SMOOTH_SMOKY_QUARTZ_STAIRS_ITEM);

            entries.add(AerialHellItems.IRON_STELLAR_ORE_ITEM);
            entries.add(AerialHellItems.GOLD_STELLAR_ORE_ITEM);
            entries.add(AerialHellItems.DIAMOND_STELLAR_ORE_ITEM);
            entries.add(AerialHellItems.FLUORITE_ORE_ITEM);
            entries.add(AerialHellItems.MAGMATIC_GEL_ORE_ITEM);
            entries.add(AerialHellItems.RUBY_ORE_ITEM);
            entries.add(AerialHellItems.AZURITE_ORE_ITEM);
            entries.add(AerialHellItems.VOLUCITE_ORE_ITEM);
            entries.add(AerialHellItems.OBSIDIAN_ORE_ITEM);
            entries.add(AerialHellItems.SMOKY_QUARTZ_ORE_ITEM);

            entries.add(AerialHellItems.FLUORITE_BLOCK_ITEM);
            entries.add(AerialHellItems.MAGMATIC_GEL_BLOCK_ITEM);
            entries.add(AerialHellItems.RUBY_BLOCK_ITEM);
            entries.add(AerialHellItems.AZURITE_BLOCK_ITEM);
            entries.add(AerialHellItems.VOLUCITE_BLOCK_ITEM);

            entries.add(AerialHellItems.RAW_RUBY_BLOCK_ITEM);
            entries.add(AerialHellItems.RAW_AZURITE_BLOCK_ITEM);
            entries.add(AerialHellItems.RAW_VOLUCITE_BLOCK_ITEM);

            entries.add(AerialHellItems.ARSONIST_BLOCK_ITEM);
            entries.add(AerialHellItems.LUNATIC_CRYSTAL_BLOCK_ITEM);
            entries.add(AerialHellItems.CURSED_CRYSAL_BLOCK_ITEM);

            entries.add(AerialHellItems.SKY_CACTUS_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_PLANKS_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.VIBRANT_SKY_CACTUS_ITEM);
            entries.add(AerialHellItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN_ITEM);

            entries.add(AerialHellItems.CLIMBING_VINE_ITEM);
            entries.add(AerialHellItems.STELLAR_SUGAR_CANE_ITEM);

            entries.add(AerialHellItems.FULL_MOON_FLOWER_ITEM);

            entries.add(AerialHellItems.VINE_BLOSSOM);
            entries.add(AerialHellItems.LAZULI_ROOTS_ITEM);
            entries.add(AerialHellItems.STELLAR_ROOTS_ITEM);
            entries.add(AerialHellItems.DEAD_ROOTS_ITEM);
            entries.add(AerialHellItems.GLOWING_ROOTS_ITEM);
            entries.add(AerialHellItems.SHADOW_GLOWING_ROOTS_ITEM);

            entries.add(AerialHellItems.STELLAR_GRASS_ITEM);
            entries.add(AerialHellItems.STELLAR_GRASS_BALL_ITEM);
            entries.add(AerialHellItems.STELLAR_FERN_ITEM);
            entries.add(AerialHellItems.STELLAR_TALL_GRASS_ITEM);
            entries.add(AerialHellItems.STELLAR_TALL_FERN_ITEM);
            entries.add(AerialHellItems.STELLAR_VERY_TALL_GRASS_ITEM);
            entries.add(AerialHellItems.BLUISH_FERN_ITEM);
            entries.add(AerialHellItems.TALL_BLUISH_FERN_ITEM);
            entries.add(AerialHellItems.POLYCHROME_FERN_ITEM);
            entries.add(AerialHellItems.TALL_POLYCHROME_FERN_ITEM);
            entries.add(AerialHellItems.STELLAR_DEAD_BUSH_ITEM);
            entries.add(AerialHellItems.BRAMBLES_ITEM);
            entries.add(AerialHellItems.SHADOW_BRAMBLES_ITEM);
            entries.add(AerialHellItems.SHADOW_GRASS_ITEM);
            entries.add(AerialHellItems.SHADOW_GRASS_BALL_ITEM);
            entries.add(AerialHellItems.PURPLISH_STELLAR_GRASS_ITEM);
            entries.add(AerialHellItems.STELLAR_CLOVERS_ITEM);
            entries.add(AerialHellItems.GLOWING_STELLAR_GRASS_ITEM);

            entries.add(AerialHellItems.BLUE_FLOWER_ITEM);
            entries.add(AerialHellItems.BLACK_ROSE_ITEM);
            entries.add(AerialHellItems.BELLFLOWER_ITEM);

            entries.add(AerialHellItems.OSCILLATOR_ITEM);
            entries.add(AerialHellItems.FREEZER_ITEM);
            entries.add(AerialHellItems.STELLAR_FURNACE_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_CHEST_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_CHEST_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_CHEST_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_CHEST_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_CHEST_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_CHEST_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_CHEST_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_CHEST_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_FENCE_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_FENCE_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_FENCE_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_FENCE_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_FENCE_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_FENCE_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_FENCE_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_FENCE_ITEM);
            entries.add(AerialHellItems.RUBY_BARS_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_WALL_ITEM);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_WALL_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.MOSSY_STELLAR_STONE_WALL_ITEM);
            entries.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_WALL_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_WALL_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.GLAUCOPHANITE_WALL_ITEM);
            entries.add(AerialHellItems.POLISHED_GLAUCOPHANITE_WALL_ITEM);
            entries.add(AerialHellItems.MAGMATIC_GEL_WALL_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_GATE_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_GATE_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_GATE_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_GATE_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_GATE_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_GATE_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_GATE_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_GATE_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_DOOR_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_DOOR_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_DOOR_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_DOOR_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_DOOR_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_DOOR_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_DOOR_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_DOOR_ITEM);
            entries.add(AerialHellItems.RUBY_DOOR_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_TRAPDOOR_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_TRAPDOOR_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_TRAPDOOR_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_TRAPDOOR_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_TRAPDOOR_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_TRAPDOOR_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_TRAPDOOR_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_TRAPDOOR_ITEM);
            entries.add(AerialHellItems.RUBY_TRAPDOOR_ITEM);

            entries.add(AerialHellItems.STELLAR_STONE_BUTTON_ITEM);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_BUTTON_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_BUTTON_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BUTTON_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_BUTTON_ITEM);
            entries.add(AerialHellItems.AERIAL_TREE_BUTTON_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_BUTTON_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_BUTTON_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_BUTTON_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_BUTTON_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_BUTTON_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_BUTTON_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_BUTTON_ITEM);
            entries.add(AerialHellItems.GLAUCOPHANITE_BUTTON_ITEM);

            entries.add(AerialHellItems.STELLAR_STONE_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.AERIAL_TREE_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_PRESSURE_PLATE_ITEM);
            entries.add(AerialHellItems.GLAUCOPHANITE_PRESSURE_PLATE_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_SLAB_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_SLAB_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_SLAB_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_SLAB_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_SLAB_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_SLAB_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_SLAB_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_SLAB_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_SLAB_ITEM);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_SLAB_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.MOSSY_STELLAR_STONE_SLAB_ITEM);
            entries.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_SLAB_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_SLAB_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.POLISHED_GLAUCOPHANITE_SLAB_ITEM);
            entries.add(AerialHellItems.MAGMATIC_GEL_SLAB_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_STAIRS_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_STAIRS_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_STAIRS_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_STAIRS_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_STAIRS_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_STAIRS_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_STAIRS_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_STAIRS_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_STAIRS_ITEM);
            entries.add(AerialHellItems.STELLAR_COBBLESTONE_STAIRS_ITEM);
            entries.add(AerialHellItems.STELLAR_STONE_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.MOSSY_STELLAR_STONE_STAIRS_ITEM);
            entries.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_STAIRS_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_STAIRS_ITEM);
            entries.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.POLISHED_GLAUCOPHANITE_STAIRS_ITEM);
            entries.add(AerialHellItems.MAGMATIC_GEL_STAIRS_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_SIGN_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_SIGN_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_SIGN_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_SIGN_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_SIGN_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_SIGN_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_SIGN_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_SIGN_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_HANGING_SIGN_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_HANGING_SIGN_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_HANGING_SIGN_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_HANGING_SIGN_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_HANGING_SIGN_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_HANGING_SIGN_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_HANGING_SIGN_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_HANGING_SIGN_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_CRAFTING_TABLE_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_CRAFTING_TABLE_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_CRAFTING_TABLE_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_CRAFTING_TABLE_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_CRAFTING_TABLE_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_CRAFTING_TABLE_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_CRAFTING_TABLE_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_CRAFTING_TABLE_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_BARREL_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_BARREL_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_BARREL_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_BARREL_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_BARREL_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_BARREL_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_BARREL_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_BARREL_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_COMPOSTER_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_COMPOSTER_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_COMPOSTER_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_COMPOSTER_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_COMPOSTER_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_COMPOSTER_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_COMPOSTER_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_COMPOSTER_ITEM);

            entries.add(AerialHellItems.AERIAL_TREE_VINE_ROPE_SPOOL_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_VINE_ROPE_SPOOL_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_VINE_ROPE_SPOOL_ITEM);
            entries.add(AerialHellItems.LAPIS_ROBINIA_VINE_ROPE_SPOOL_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_VINE_ROPE_SPOOL_ITEM);
            entries.add(AerialHellItems.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL_ITEM);
            entries.add(AerialHellItems.GRAY_SHROOM_VINE_ROPE_SPOOL_ITEM);
        });

        ItemGroupEvents.modifyEntriesEvent(AerialHellCreativeModeTabs.getItemGroupKey(AerialHellCreativeModeTabs.AERIAL_HELL_DUNGEON_BLOCKS)).register(entries ->
        {
            entries.add(AerialHellItems.LUNATIC_LANTERN_ITEM);
            entries.add(AerialHellItems.SHADOW_LANTERN_ITEM);
            entries.add(AerialHellItems.LUNATIC_CHAIN_ITEM);
            entries.add(AerialHellItems.SHADOW_CHAIN_ITEM);

            entries.add(AerialHellItems.MUD_BRICKS_ITEM);
            entries.add(AerialHellItems.CRACKED_MUD_BRICKS_ITEM);
            entries.add(AerialHellItems.MOSSY_MUD_BRICKS_ITEM);
            entries.add(AerialHellItems.CHISELED_MUD_BRICKS_ITEM);
            entries.add(AerialHellItems.LIGHT_MUD_BRICKS_ITEM);
            entries.add(AerialHellItems.CRACKED_LIGHT_MUD_BRICKS_ITEM);
            entries.add(AerialHellItems.LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.DARK_LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.ROOF_LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.CRACKED_LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.CHISELED_LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.LIGHT_LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.ROOF_LIGHT_LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.CRACKED_LIGHT_LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_ITEM);
            entries.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_ITEM);
            entries.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_ITEM);
            entries.add(AerialHellItems.CHISELED_SHADOW_CATACOMBS_BRICKS_ITEM);
            entries.add(AerialHellItems.BONE_SHADOW_CATACOMBS_BRICKS_ITEM);
            entries.add(AerialHellItems.SKULL_SHADOW_CATACOMBS_BRICKS_ITEM);
            entries.add(AerialHellItems.LIGHT_SHADOW_CATACOMBS_BRICKS_ITEM);
            entries.add(AerialHellItems.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS_ITEM);
            entries.add(AerialHellItems.GOLDEN_NETHER_BRICKS_ITEM);
            entries.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_ITEM);
            entries.add(AerialHellItems.CHISELED_GOLDEN_NETHER_BRICKS_ITEM);
            entries.add(AerialHellItems.LIGHT_GOLDEN_NETHER_BRICKS_ITEM);
            entries.add(AerialHellItems.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM);
            entries.add(AerialHellItems.LUNATIC_PILLAR_ITEM);
            entries.add(AerialHellItems.LUNATIC_PILLAR_TOP_ITEM);
            entries.add(AerialHellItems.VOLUCITE_STONE_ITEM);
            entries.add(AerialHellItems.CRACKED_VOLUCITE_STONE_ITEM);
            entries.add(AerialHellItems.CHISELED_VOLUCITE_STONE_ITEM);
            entries.add(AerialHellItems.LIGHT_VOLUCITE_STONE_ITEM);
            entries.add(AerialHellItems.CRACKED_LIGHT_VOLUCITE_STONE_ITEM);

            entries.add(AerialHellItems.MUD_DUNGEON_CORE_ITEM);
            entries.add(AerialHellItems.LUNATIC_DUNGEON_CORE_ITEM);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_DUNGEON_CORE_ITEM);
            entries.add(AerialHellItems.GOLDEN_NETHER_DUNGEON_CORE_ITEM);
            entries.add(AerialHellItems.VOLUCITE_DUNGEON_CORE_ITEM);

            entries.add(AerialHellItems.MUD_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.MUD_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.MUD_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.CRACKED_MUD_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.CRACKED_MUD_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.CRACKED_MUD_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.MOSSY_MUD_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.MOSSY_MUD_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.MOSSY_MUD_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.VOLUCITE_STONE_SLAB_ITEM);
            entries.add(AerialHellItems.VOLUCITE_STONE_STAIRS_ITEM);
            entries.add(AerialHellItems.VOLUCITE_STONE_WALL_ITEM);
            entries.add(AerialHellItems.CRACKED_VOLUCITE_STONE_SLAB_ITEM);
            entries.add(AerialHellItems.CRACKED_VOLUCITE_STONE_STAIRS_ITEM);
            entries.add(AerialHellItems.CRACKED_VOLUCITE_STONE_WALL_ITEM);
            entries.add(AerialHellItems.LUNATIC_STONE_SLAB_ITEM);
            entries.add(AerialHellItems.LUNATIC_STONE_STAIRS_ITEM);
            entries.add(AerialHellItems.LUNATIC_STONE_WALL_ITEM);
            entries.add(AerialHellItems.DARK_LUNATIC_STONE_SLAB_ITEM);
            entries.add(AerialHellItems.DARK_LUNATIC_STONE_STAIRS_ITEM);
            entries.add(AerialHellItems.DARK_LUNATIC_STONE_WALL_ITEM);
            entries.add(AerialHellItems.CRACKED_LUNATIC_STONE_SLAB_ITEM);
            entries.add(AerialHellItems.CRACKED_LUNATIC_STONE_STAIRS_ITEM);
            entries.add(AerialHellItems.CRACKED_LUNATIC_STONE_WALL_ITEM);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.SHADOW_BARS_ITEM);
            entries.add(AerialHellItems.GOLDEN_NETHER_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.GOLDEN_NETHER_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.GOLDEN_NETHER_BRICKS_WALL_ITEM);
            entries.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_SLAB_ITEM);
            entries.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS_ITEM);
            entries.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_WALL_ITEM);

            entries.add(AerialHellItems.TRAPPED_MUD_BRICKS_ITEM);
            entries.add(AerialHellItems.TRAPPED_LIGHT_MUD_BRICKS_ITEM);
            entries.add(AerialHellItems.TRAPPED_LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.TRAPPED_LIGHT_LUNATIC_STONE_ITEM);
            entries.add(AerialHellItems.TRAPPED_GOLDEN_NETHER_BRICKS_ITEM);
            entries.add(AerialHellItems.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM);

            entries.add(AerialHellItems.MUD_BONE_BLOCK_ITEM);
            entries.add(AerialHellItems.MUD_BONE_PILE_BLOCK_ITEM);
            entries.add(AerialHellItems.THORNY_COBWEB_ITEM);
            entries.add(AerialHellItems.AERIAL_NETHERRACK_ITEM);
            entries.add(AerialHellItems.AERIAL_NETHERRACK_SLAB_ITEM);
            entries.add(AerialHellItems.AERIAL_NETHERRACK_STAIRS_ITEM);
            entries.add(AerialHellItems.AERIAL_NETHERRACK_WALL_ITEM);

            entries.add(AerialHellItems.MUD_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.LUNATIC_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.GOLDEN_NETHER_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_BOOKSHELF_ITEM);
            entries.add(AerialHellItems.VOLUCITE_BOOKSHELF_ITEM);

            entries.add(AerialHellItems.MUD_GLYPH_BLOCK_ITEM);
            entries.add(AerialHellItems.LUNATIC_GLYPH_BLOCK_ITEM);
            entries.add(AerialHellItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK_ITEM);
            entries.add(AerialHellItems.VOLUCITE_GLYPH_BLOCK_ITEM);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_GLYPH_BLOCK_ITEM);

            entries.add(AerialHellItems.MUD_CHEST_ITEM);
            entries.add(AerialHellItems.LUNATIC_CHEST_ITEM);
            entries.add(AerialHellItems.VOLUCITE_CHEST_ITEM);
            entries.add(AerialHellItems.SHADOW_CATACOMBS_CHEST_ITEM);
            entries.add(AerialHellItems.GOLDEN_NETHER_CHEST_ITEM);

            entries.add(AerialHellItems.MUD_CYCLE_MAGE_TROPHY_ITEM);
            entries.add(AerialHellItems.LUNAR_PRIEST_TROPHY_ITEM);
            entries.add(AerialHellItems.LILITH_TROPHY_ITEM);
            entries.add(AerialHellItems.CHAINED_GOD_TROPHY_ITEM);
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
            entries.add(AerialHellItems.STELLAR_WHEAT_ITEM);

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
            entries.add(AerialHellItems.AERIAL_TREE_CHEST_MIMIC_ITEM);
            entries.add(AerialHellItems.GOLDEN_BEECH_CHEST_MIMIC_ITEM);
            entries.add(AerialHellItems.COPPER_PINE_CHEST_MIMIC_ITEM);
            entries.add(AerialHellItems.SKY_CACTUS_FIBER_CHEST_MIMIC_ITEM);
            entries.add(AerialHellItems.SHADOW_PINE_BARREL_MIMIC_ITEM);

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
