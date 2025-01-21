package fr.factionbedrock.aerialhell.Registry.CreativeModeTabs;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.List;

public class BuildContentsEvent
{
    public static void buildContents(BuildCreativeModeTabContentsEvent event)
    {
        List<Item> itemsToAdd = new ArrayList<Item>();

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            itemsToAdd.add(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK.get());
            itemsToAdd.add(AerialHellItems.STELLAR_LIGHTER.get());
        }

        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_BLOCKS.getKey())
        {
            itemsToAdd.add(AerialHellItems.STELLAR_PORTAL_FRAME_BLOCK.get());
            itemsToAdd.add(AerialHellItems.STELLAR_PORTAL_FRAME_ORE.get());
            itemsToAdd.add(AerialHellItems.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE.get());

            itemsToAdd.add(AerialHellItems.FLUORITE_TORCH.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_TORCH.get());
            itemsToAdd.add(AerialHellItems.SHADOW_TORCH.get());
            itemsToAdd.add(AerialHellItems.FLUORITE_LANTERN.get());
            itemsToAdd.add(AerialHellItems.RUBY_LANTERN.get());
            itemsToAdd.add(AerialHellItems.RUBY_FLUORITE_LANTERN.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_LANTERN.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_FLUORITE_LANTERN.get());

            itemsToAdd.add(AerialHellItems.RUBY_CHAIN.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_CHAIN.get());

            itemsToAdd.add(AerialHellItems.GIANT_ROOT.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_LOG.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_AERIAL_TREE_LOG.get());
            itemsToAdd.add(AerialHellItems.AERIAL_TREE_WOOD.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_AERIAL_TREE_WOOD.get());
            itemsToAdd.add(AerialHellItems.AERIAL_TREE_LEAVES.get());
            itemsToAdd.add(AerialHellItems.AERIAL_TREE_PLANKS.get());
            itemsToAdd.add(AerialHellItems.CHISELED_AERIAL_TREE_PLANKS.get());
            itemsToAdd.add(AerialHellItems.AERIAL_TREE_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.AERIAL_TREE_SAPLING.get());

            itemsToAdd.add(AerialHellItems.PETRIFIED_AERIAL_TREE_LOG.get());

            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_LOG.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_GOLDEN_BEECH_LOG.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_WOOD.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_GOLDEN_BEECH_WOOD.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_LEAVES.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_PLANKS.get());
            itemsToAdd.add(AerialHellItems.CHISELED_GOLDEN_BEECH_PLANKS.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_SAPLING.get());

            itemsToAdd.add(AerialHellItems.COPPER_PINE_LOG.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_COPPER_PINE_LOG.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_WOOD.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_COPPER_PINE_WOOD.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_PLANKS.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_LEAVES.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_SAPLING.get());

            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_LOG.get());
            itemsToAdd.add(AerialHellItems.ENCHANTED_LAPIS_ROBINIA_LOG.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_LAPIS_ROBINIA_LOG.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_WOOD.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_LAPIS_ROBINIA_WOOD.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_LEAVES.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_PLANKS.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_SAPLING.get());

            itemsToAdd.add(AerialHellItems.SHADOW_PINE_LOG.get());
            itemsToAdd.add(AerialHellItems.EYE_SHADOW_PINE_LOG.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_SHADOW_PINE_LOG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_WOOD.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_SHADOW_PINE_WOOD.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_LEAVES.get());
            itemsToAdd.add(AerialHellItems.PURPLE_SHADOW_PINE_LEAVES.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_PLANKS.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_SAPLING.get());
            itemsToAdd.add(AerialHellItems.PURPLE_SHADOW_PINE_SAPLING.get());

            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_LOG.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_STELLAR_JUNGLE_TREE_LOG.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_WOOD.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_STELLAR_JUNGLE_TREE_WOOD.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_LEAVES.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_PLANKS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_SAPLING.get());
            itemsToAdd.add(AerialHellItems.DEAD_STELLAR_JUNGLE_TREE_LOG.get());

            itemsToAdd.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_STEM.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.get());
            itemsToAdd.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get());
            itemsToAdd.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get());
            itemsToAdd.add(AerialHellItems.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.get());
            itemsToAdd.add(AerialHellItems.CORTINARIUS_VIOLACEUS.get());
            itemsToAdd.add(AerialHellItems.GLOWING_BOLETUS.get());
            itemsToAdd.add(AerialHellItems.TALL_GLOWING_BOLETUS.get());
            itemsToAdd.add(AerialHellItems.BLUE_MEANIE_CLUSTER.get());
            itemsToAdd.add(AerialHellItems.GIANT_ROOT_SHROOM.get());

            itemsToAdd.add(AerialHellItems.GIANT_VERDIGRIS_AGARIC_STEM.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.get());
            itemsToAdd.add(AerialHellItems.GIANT_VERDIGRIS_AGARIC_BARK_STEM.get());
            itemsToAdd.add(AerialHellItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.get());
            itemsToAdd.add(AerialHellItems.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.get());
            itemsToAdd.add(AerialHellItems.VERDIGRIS_AGARIC.get());

            itemsToAdd.add(AerialHellItems.GIANT_GANODERMA_APPLANATUM_BLOCK.get());

            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_PLANKS.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_BOOKSHELF.get());

            itemsToAdd.add(AerialHellItems.SHADOW_AERIAL_TREE_LOG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_GOLDEN_BEECH_LOG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_COPPER_PINE_LOG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_LAPIS_ROBINIA_LOG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_STELLAR_JUNGLE_TREE_LOG.get());
            itemsToAdd.add(AerialHellItems.HOLLOW_SHADOW_PINE_LOG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_AERIAL_TREE_LEAVES.get());
            itemsToAdd.add(AerialHellItems.SHADOW_GOLDEN_BEECH_LEAVES.get());
            itemsToAdd.add(AerialHellItems.SHADOW_COPPER_PINE_LEAVES.get());
            itemsToAdd.add(AerialHellItems.SHADOW_LAPIS_ROBINIA_LEAVES.get());
            itemsToAdd.add(AerialHellItems.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.get());
            itemsToAdd.add(AerialHellItems.HOLLOW_SHADOW_PINE_LEAVES.get());
            itemsToAdd.add(AerialHellItems.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.get());

            itemsToAdd.add(AerialHellItems.SKY_LADDER.get());

            itemsToAdd.add(AerialHellItems.STELLAR_STONE.get());
            itemsToAdd.add(AerialHellItems.SHADOW_STONE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_COBBLESTONE.get());
            itemsToAdd.add(AerialHellItems.MOSSY_STELLAR_STONE.get());
            itemsToAdd.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_CLAY.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_BRICKS.get());
            itemsToAdd.add(AerialHellItems.GLAUCOPHANITE.get());
            itemsToAdd.add(AerialHellItems.POLISHED_GLAUCOPHANITE.get());

            itemsToAdd.add(AerialHellItems.CRYSTAL_BLOCK.get());
            itemsToAdd.add(AerialHellItems.CRYSTAL_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CRYSTAL_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.CRYSTAL_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.CRYSTAL_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_CRYSTAL_BLOCK.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CRYSTAL_BLOCK.get());
            itemsToAdd.add(AerialHellItems.CRYSTALLIZED_LEAVES.get());

            itemsToAdd.add(AerialHellItems.STELLAR_GRASS_BLOCK.get());
            itemsToAdd.add(AerialHellItems.CHISELED_STELLAR_GRASS_BLOCK.get());
            itemsToAdd.add(AerialHellItems.STELLAR_DIRT.get());
            itemsToAdd.add(AerialHellItems.STELLAR_COARSE_DIRT.get());
            itemsToAdd.add(AerialHellItems.STELLAR_FARMLAND.get());
            itemsToAdd.add(AerialHellItems.STELLAR_DIRT_PATH.get());
            itemsToAdd.add(AerialHellItems.STELLAR_PODZOL.get());
            itemsToAdd.add(AerialHellItems.STELLAR_CRYSTAL_PODZOL.get());
            itemsToAdd.add(AerialHellItems.CHISELED_STELLAR_DIRT.get());
            itemsToAdd.add(AerialHellItems.SHADOW_GRASS_BLOCK.get());

            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CUT_SLIPPERY_SAND_STONE.get());
            itemsToAdd.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS.get());

            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_GLASS.get());
            itemsToAdd.add(AerialHellItems.RED_SLIPPERY_SAND_GLASS.get());
            itemsToAdd.add(AerialHellItems.BLACK_SLIPPERY_SAND_GLASS.get());
            itemsToAdd.add(AerialHellItems.BLUE_SLIPPERY_SAND_GLASS.get());
            itemsToAdd.add(AerialHellItems.GREEN_SLIPPERY_SAND_GLASS.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_GLASS_PANE.get());
            itemsToAdd.add(AerialHellItems.RED_SLIPPERY_SAND_GLASS_PANE.get());
            itemsToAdd.add(AerialHellItems.BLACK_SLIPPERY_SAND_GLASS_PANE.get());
            itemsToAdd.add(AerialHellItems.BLUE_SLIPPERY_SAND_GLASS_PANE.get());
            itemsToAdd.add(AerialHellItems.GREEN_SLIPPERY_SAND_GLASS_PANE.get());

            itemsToAdd.add(AerialHellItems.GHOST_BOAT_PLANKS.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_LOG.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_WOOD.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_SLAB.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_STAIRS.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_FENCE.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_GATE.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_DOOR.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_TRAPDOOR.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_CHEST.get());
            itemsToAdd.add(AerialHellItems.GHOST_STELLAR_COBBLESTONE.get());
            itemsToAdd.add(AerialHellItems.GHOST_STELLAR_FURNACE.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_WOOL.get());
            itemsToAdd.add(AerialHellItems.GHOST_RUBY_BLOCK.get());
            itemsToAdd.add(AerialHellItems.GHOST_FLUORITE_BLOCK.get());
            itemsToAdd.add(AerialHellItems.GHOST_AZURITE_BLOCK.get());
            itemsToAdd.add(AerialHellItems.GHOST_GOLD_BLOCK.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_BARREL.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_CRAFTING_TABLE.get());
            itemsToAdd.add(AerialHellItems.GHOST_BOAT_VINE_ROPE_SPOOL.get());
            itemsToAdd.add(AerialHellItems.GHOST_LANTERN.get());

            itemsToAdd.add(AerialHellItems.WEAK_LIGHT_REACTOR.get());
            itemsToAdd.add(AerialHellItems.HIGH_POWER_LIGHT_REACTOR.get());
            itemsToAdd.add(AerialHellItems.WEAK_SHADOW_REACTOR.get());
            itemsToAdd.add(AerialHellItems.HIGH_POWER_SHADOW_REACTOR.get());
            itemsToAdd.add(AerialHellItems.BROKEN_WEAK_LIGHT_REACTOR.get());
            itemsToAdd.add(AerialHellItems.BROKEN_HIGH_POWER_LIGHT_REACTOR.get());
            itemsToAdd.add(AerialHellItems.BROKEN_WEAK_SHADOW_REACTOR.get());
            itemsToAdd.add(AerialHellItems.BROKEN_HIGH_POWER_SHADOW_REACTOR.get());

            itemsToAdd.add(AerialHellItems.WHITE_SOLID_ETHER.get());
            itemsToAdd.add(AerialHellItems.BLUE_SOLID_ETHER.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_SOLID_ETHER.get());
            itemsToAdd.add(AerialHellItems.GREEN_SOLID_ETHER.get());
            itemsToAdd.add(AerialHellItems.PURPLE_SOLID_ETHER.get());

            itemsToAdd.add(AerialHellItems.SMOKY_QUARTZ_BLOCK.get());
            itemsToAdd.add(AerialHellItems.SMOOTH_SMOKY_QUARTZ.get());
            itemsToAdd.add(AerialHellItems.CHISELED_SMOKY_QUARTZ_BLOCK.get());
            itemsToAdd.add(AerialHellItems.SMOKY_QUARTZ_BRICKS.get());
            itemsToAdd.add(AerialHellItems.SMOKY_QUARTZ_PILLAR.get());
            itemsToAdd.add(AerialHellItems.SMOKY_QUARTZ_SLAB.get());
            itemsToAdd.add(AerialHellItems.SMOOTH_SMOKY_QUARTZ_SLAB.get());
            itemsToAdd.add(AerialHellItems.SMOKY_QUARTZ_STAIRS.get());
            itemsToAdd.add(AerialHellItems.SMOOTH_SMOKY_QUARTZ_STAIRS.get());

            itemsToAdd.add(AerialHellItems.IRON_STELLAR_ORE.get());
            itemsToAdd.add(AerialHellItems.GOLD_STELLAR_ORE.get());
            itemsToAdd.add(AerialHellItems.DIAMOND_STELLAR_ORE.get());
            itemsToAdd.add(AerialHellItems.FLUORITE_ORE.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_ORE.get());
            itemsToAdd.add(AerialHellItems.RUBY_ORE.get());
            itemsToAdd.add(AerialHellItems.AZURITE_ORE.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_ORE.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_ORE.get());
            itemsToAdd.add(AerialHellItems.SMOKY_QUARTZ_ORE.get());

            itemsToAdd.add(AerialHellItems.FLUORITE_BLOCK.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_BLOCK.get());
            itemsToAdd.add(AerialHellItems.RUBY_BLOCK.get());
            itemsToAdd.add(AerialHellItems.AZURITE_BLOCK.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_BLOCK.get());

            itemsToAdd.add(AerialHellItems.RAW_RUBY_BLOCK.get());
            itemsToAdd.add(AerialHellItems.RAW_AZURITE_BLOCK.get());
            itemsToAdd.add(AerialHellItems.RAW_VOLUCITE_BLOCK.get());

            itemsToAdd.add(AerialHellItems.ARSONIST_BLOCK.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_CRYSTAL_BLOCK.get());
            itemsToAdd.add(AerialHellItems.CURSED_CRYSAL_BLOCK.get());

            itemsToAdd.add(AerialHellItems.SKY_CACTUS.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_PLANKS.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_SKY_CACTUS.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN.get());

            itemsToAdd.add(AerialHellItems.CLIMBING_VINE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_SUGAR_CANE.get());

            itemsToAdd.add(AerialHellItems.FULL_MOON_FLOWER.get());

            itemsToAdd.add(AerialHellItems.VINE_BLOSSOM.get());
            itemsToAdd.add(AerialHellItems.LAZULI_ROOTS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_ROOTS.get());
            itemsToAdd.add(AerialHellItems.DEAD_ROOTS.get());
            itemsToAdd.add(AerialHellItems.GLOWING_ROOTS.get());
            itemsToAdd.add(AerialHellItems.SHADOW_GLOWING_ROOTS.get());

            itemsToAdd.add(AerialHellItems.STELLAR_GRASS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_GRASS_BALL.get());
            itemsToAdd.add(AerialHellItems.STELLAR_FERN.get());
            itemsToAdd.add(AerialHellItems.STELLAR_TALL_GRASS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_TALL_FERN.get());
            itemsToAdd.add(AerialHellItems.STELLAR_VERY_TALL_GRASS.get());
            itemsToAdd.add(AerialHellItems.BLUISH_FERN.get());
            itemsToAdd.add(AerialHellItems.TALL_BLUISH_FERN.get());
            itemsToAdd.add(AerialHellItems.POLYCHROME_FERN.get());
            itemsToAdd.add(AerialHellItems.TALL_POLYCHROME_FERN.get());
            itemsToAdd.add(AerialHellItems.STELLAR_DEAD_BUSH.get());
            itemsToAdd.add(AerialHellItems.BRAMBLES.get());
            itemsToAdd.add(AerialHellItems.SHADOW_BRAMBLES.get());
            itemsToAdd.add(AerialHellItems.SHADOW_GRASS.get());
            itemsToAdd.add(AerialHellItems.SHADOW_GRASS_BALL.get());
            itemsToAdd.add(AerialHellItems.PURPLISH_STELLAR_GRASS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_CLOVERS.get());
            itemsToAdd.add(AerialHellItems.GLOWING_STELLAR_GRASS.get());

            itemsToAdd.add(AerialHellItems.BLUE_FLOWER.get());
            itemsToAdd.add(AerialHellItems.BLACK_ROSE.get());
            itemsToAdd.add(AerialHellItems.BELLFLOWER.get());

            itemsToAdd.add(AerialHellItems.OSCILLATOR.get());
            itemsToAdd.add(AerialHellItems.FREEZER.get());
            itemsToAdd.add(AerialHellItems.STELLAR_FURNACE.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_CHEST.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_CHEST.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_CHEST.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_CHEST.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_CHEST.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_CHEST.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_CHEST.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_CHEST.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_FENCE.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_FENCE.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_FENCE.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_FENCE.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_FENCE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_FENCE.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_FENCE.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_FENCE.get());
            itemsToAdd.add(AerialHellItems.RUBY_BARS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_WALL.get());
            itemsToAdd.add(AerialHellItems.STELLAR_COBBLESTONE_WALL.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.MOSSY_STELLAR_STONE_WALL.get());
            itemsToAdd.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_WALL.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_WALL.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.GLAUCOPHANITE_WALL.get());
            itemsToAdd.add(AerialHellItems.POLISHED_GLAUCOPHANITE_WALL.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_WALL.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_GATE.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_GATE.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_GATE.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_GATE.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_GATE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_GATE.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_GATE.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_GATE.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_DOOR.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_DOOR.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_DOOR.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_DOOR.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_DOOR.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_DOOR.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_DOOR.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_DOOR.get());
            itemsToAdd.add(AerialHellItems.RUBY_DOOR.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_TRAPDOOR.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_TRAPDOOR.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_TRAPDOOR.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_TRAPDOOR.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_TRAPDOOR.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_TRAPDOOR.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_TRAPDOOR.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_TRAPDOOR.get());
            itemsToAdd.add(AerialHellItems.RUBY_TRAPDOOR.get());

            itemsToAdd.add(AerialHellItems.STELLAR_STONE_BUTTON.get());
            itemsToAdd.add(AerialHellItems.STELLAR_COBBLESTONE_BUTTON.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_BRICKS_BUTTON.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_BUTTON.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_BUTTON.get());
            itemsToAdd.add(AerialHellItems.AERIAL_TREE_BUTTON.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_BUTTON.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_BUTTON.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_BUTTON.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_BUTTON.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_BUTTON.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_BUTTON.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_BUTTON.get());
            itemsToAdd.add(AerialHellItems.GLAUCOPHANITE_BUTTON.get());

            itemsToAdd.add(AerialHellItems.STELLAR_STONE_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_COBBLESTONE_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_BRICKS_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.AERIAL_TREE_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_PRESSURE_PLATE.get());
            itemsToAdd.add(AerialHellItems.GLAUCOPHANITE_PRESSURE_PLATE.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_SLAB.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_SLAB.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_SLAB.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_SLAB.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_SLAB.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_SLAB.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_SLAB.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_SLAB.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.STELLAR_COBBLESTONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.MOSSY_STELLAR_STONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.POLISHED_GLAUCOPHANITE_SLAB.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_SLAB.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_STAIRS.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_STAIRS.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_STAIRS.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_STAIRS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_COBBLESTONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.MOSSY_STELLAR_STONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.MOSSY_STELLAR_COBBLESTONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.SLIPPERY_SAND_STONE_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.POLISHED_GLAUCOPHANITE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_STAIRS.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_SIGN.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_SIGN.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_SIGN.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_SIGN.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_SIGN.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_SIGN.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_SIGN.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_SIGN.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_HANGING_SIGN.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_HANGING_SIGN.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_HANGING_SIGN.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_HANGING_SIGN.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_HANGING_SIGN.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_HANGING_SIGN.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_HANGING_SIGN.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_HANGING_SIGN.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_CRAFTING_TABLE.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_CRAFTING_TABLE.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_CRAFTING_TABLE.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_CRAFTING_TABLE.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_CRAFTING_TABLE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_CRAFTING_TABLE.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_CRAFTING_TABLE.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_CRAFTING_TABLE.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_BARREL.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_BARREL.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_BARREL.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_BARREL.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_BARREL.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_BARREL.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_BARREL.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_BARREL.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_COMPOSTER.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_COMPOSTER.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_COMPOSTER.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_COMPOSTER.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_COMPOSTER.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_COMPOSTER.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_COMPOSTER.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_COMPOSTER.get());

            itemsToAdd.add(AerialHellItems.AERIAL_TREE_VINE_ROPE_SPOOL.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_VINE_ROPE_SPOOL.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_VINE_ROPE_SPOOL.get());
            itemsToAdd.add(AerialHellItems.LAPIS_ROBINIA_VINE_ROPE_SPOOL.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_VINE_ROPE_SPOOL.get());
            itemsToAdd.add(AerialHellItems.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL.get());
            itemsToAdd.add(AerialHellItems.GRAY_SHROOM_VINE_ROPE_SPOOL.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_DUNGEON_BLOCKS.getKey())
        {
            itemsToAdd.add(AerialHellItems.LUNATIC_LANTERN.get());
            itemsToAdd.add(AerialHellItems.SHADOW_LANTERN.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_CHAIN.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CHAIN.get());

            itemsToAdd.add(AerialHellItems.MUD_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_MUD_BRICKS.get());
            itemsToAdd.add(AerialHellItems.MOSSY_MUD_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CHISELED_MUD_BRICKS.get());
            itemsToAdd.add(AerialHellItems.LIGHT_MUD_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_LIGHT_MUD_BRICKS.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.DARK_LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.ROOF_LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.CRACKED_LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.CHISELED_LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.LIGHT_LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.ROOF_LIGHT_LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.CRACKED_LIGHT_LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS.get());
            itemsToAdd.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CHISELED_SHADOW_CATACOMBS_BRICKS.get());
            itemsToAdd.add(AerialHellItems.BONE_SHADOW_CATACOMBS_BRICKS.get());
            itemsToAdd.add(AerialHellItems.SKULL_SHADOW_CATACOMBS_BRICKS.get());
            itemsToAdd.add(AerialHellItems.LIGHT_SHADOW_CATACOMBS_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CHISELED_GOLDEN_NETHER_BRICKS.get());
            itemsToAdd.add(AerialHellItems.LIGHT_GOLDEN_NETHER_BRICKS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_PILLAR.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_PILLAR_TOP.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_STONE.get());
            itemsToAdd.add(AerialHellItems.CRACKED_VOLUCITE_STONE.get());
            itemsToAdd.add(AerialHellItems.CHISELED_VOLUCITE_STONE.get());
            itemsToAdd.add(AerialHellItems.LIGHT_VOLUCITE_STONE.get());
            itemsToAdd.add(AerialHellItems.CRACKED_LIGHT_VOLUCITE_STONE.get());

            itemsToAdd.add(AerialHellItems.MUD_DUNGEON_CORE.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_DUNGEON_CORE.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CATACOMBS_DUNGEON_CORE.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_DUNGEON_CORE.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_DUNGEON_CORE.get());

            itemsToAdd.add(AerialHellItems.MUD_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.MUD_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.MUD_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.CRACKED_MUD_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.CRACKED_MUD_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_MUD_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.MOSSY_MUD_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.MOSSY_MUD_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.MOSSY_MUD_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_STONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_STONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_STONE_WALL.get());
            itemsToAdd.add(AerialHellItems.CRACKED_VOLUCITE_STONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.CRACKED_VOLUCITE_STONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_VOLUCITE_STONE_WALL.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_STONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_STONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_STONE_WALL.get());
            itemsToAdd.add(AerialHellItems.DARK_LUNATIC_STONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.DARK_LUNATIC_STONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.DARK_LUNATIC_STONE_WALL.get());
            itemsToAdd.add(AerialHellItems.CRACKED_LUNATIC_STONE_SLAB.get());
            itemsToAdd.add(AerialHellItems.CRACKED_LUNATIC_STONE_STAIRS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_LUNATIC_STONE_WALL.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CATACOMBS_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.SHADOW_BARS.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_BRICKS_WALL.get());
            itemsToAdd.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_SLAB.get());
            itemsToAdd.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.get());
            itemsToAdd.add(AerialHellItems.CRACKED_GOLDEN_NETHER_BRICKS_WALL.get());

            itemsToAdd.add(AerialHellItems.TRAPPED_MUD_BRICKS.get());
            itemsToAdd.add(AerialHellItems.TRAPPED_LIGHT_MUD_BRICKS.get());
            itemsToAdd.add(AerialHellItems.TRAPPED_LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.TRAPPED_LIGHT_LUNATIC_STONE.get());
            itemsToAdd.add(AerialHellItems.TRAPPED_GOLDEN_NETHER_BRICKS.get());
            itemsToAdd.add(AerialHellItems.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.get());

            itemsToAdd.add(AerialHellItems.MUD_BONE_BLOCK.get());
            itemsToAdd.add(AerialHellItems.MUD_BONE_PILE_BLOCK.get());
            itemsToAdd.add(AerialHellItems.THORNY_COBWEB.get());
            itemsToAdd.add(AerialHellItems.AERIAL_NETHERRACK.get());
            itemsToAdd.add(AerialHellItems.AERIAL_NETHERRACK_SLAB.get());
            itemsToAdd.add(AerialHellItems.AERIAL_NETHERRACK_STAIRS.get());
            itemsToAdd.add(AerialHellItems.AERIAL_NETHERRACK_WALL.get());

            itemsToAdd.add(AerialHellItems.MUD_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CATACOMBS_BOOKSHELF.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_BOOKSHELF.get());

            itemsToAdd.add(AerialHellItems.MUD_GLYPH_BLOCK.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_GLYPH_BLOCK.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_GLYPH_BLOCK.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CATACOMBS_GLYPH_BLOCK.get());

            itemsToAdd.add(AerialHellItems.MUD_CHEST.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_CHEST.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_CHEST.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CATACOMBS_CHEST.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_CHEST.get());

            itemsToAdd.add(AerialHellItems.MUD_CYCLE_MAGE_TROPHY.get());
            itemsToAdd.add(AerialHellItems.LUNAR_PRIEST_TROPHY.get());
            itemsToAdd.add(AerialHellItems.LILITH_TROPHY.get());
            itemsToAdd.add(AerialHellItems.CHAINED_GOD_TROPHY.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_TOOLS.getKey())
        {
            itemsToAdd.add(AerialHellItems.SKY_WOOD_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.RUBY_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.AZURITE_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.ARSONIST_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.MAGMA_CUBE_PICKAXE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_BREAKER.get());

            itemsToAdd.add(AerialHellItems.SKY_WOOD_SHOVEL.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_SHOVEL.get());
            itemsToAdd.add(AerialHellItems.RUBY_SHOVEL.get());
            itemsToAdd.add(AerialHellItems.AZURITE_SHOVEL.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_SHOVEL.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_SHOVEL.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_SHOVEL.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_SHOVEL.get());
            itemsToAdd.add(AerialHellItems.ARSONIST_SHOVEL.get());
            itemsToAdd.add(AerialHellItems.MAGMA_CUBE_SHOVEL.get());

            itemsToAdd.add(AerialHellItems.SKY_WOOD_AXE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_AXE.get());
            itemsToAdd.add(AerialHellItems.RUBY_AXE.get());
            itemsToAdd.add(AerialHellItems.AZURITE_AXE.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_AXE.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_AXE.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_AXE.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_AXE.get());
            itemsToAdd.add(AerialHellItems.ARSONIST_AXE.get());

            itemsToAdd.add(AerialHellItems.SKY_WOOD_HOE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_HOE.get());
            itemsToAdd.add(AerialHellItems.RUBY_HOE.get());
            itemsToAdd.add(AerialHellItems.AZURITE_HOE.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_HOE.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_HOE.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_HOE.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_HOE.get());
            itemsToAdd.add(AerialHellItems.ARSONIST_HOE.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_COMBAT.getKey())
        {
            itemsToAdd.add(AerialHellItems.IRON_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.GOLD_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.DIAMOND_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.NETHERITE_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.RUBY_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.AZURITE_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_CRYSTAL_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.ARSONIST_SHURIKEN.get());
            itemsToAdd.add(AerialHellItems.LIGHTNING_SHURIKEN.get());

            itemsToAdd.add(AerialHellItems.RUBY_BLOWPIPE_ARROW.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_BLOWPIPE_ARROW.get());
            itemsToAdd.add(AerialHellItems.RUBY_BLOWPIPE.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_BLOWPIPE.get());

            itemsToAdd.add(AerialHellItems.SKY_WOOD_SWORD.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_SWORD.get());
            itemsToAdd.add(AerialHellItems.RUBY_SWORD.get());
            itemsToAdd.add(AerialHellItems.AZURITE_SWORD.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_SWORD.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_SWORD.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_SWORD.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_SWORD.get());
            itemsToAdd.add(AerialHellItems.ARSONIST_SWORD.get());

            itemsToAdd.add(AerialHellItems.HEAVY_SWORD.get());
            itemsToAdd.add(AerialHellItems.HEALTH_BOOST_SWORD.get());
            itemsToAdd.add(AerialHellItems.NINJA_SWORD.get());
            itemsToAdd.add(AerialHellItems.NINJA_MASTER_SWORD.get());
            itemsToAdd.add(AerialHellItems.GLOUTON_SWORD.get());
            itemsToAdd.add(AerialHellItems.RANDOM_SWORD.get());
            itemsToAdd.add(AerialHellItems.DISLOYAL_SWORD.get());
            itemsToAdd.add(AerialHellItems.CURSED_SWORD.get());
            itemsToAdd.add(AerialHellItems.ABSOLUTE_ZERO_SWORD.get());
            itemsToAdd.add(AerialHellItems.SWORD_OF_LIGHT.get());
            itemsToAdd.add(AerialHellItems.ANTIDOTE_SWORD.get());
            itemsToAdd.add(AerialHellItems.NETHERIAN_KING_SWORD.get());
            itemsToAdd.add(AerialHellItems.GLASS_CANON_SWORD.get());
            itemsToAdd.add(AerialHellItems.GOD_SWORD.get());

            itemsToAdd.add(AerialHellItems.FORGOTTEN_BATTLE_TRIDENT.get());

            itemsToAdd.add(AerialHellItems.HEAVY_AXE.get());
            itemsToAdd.add(AerialHellItems.AXE_OF_LIGHT.get());
            itemsToAdd.add(AerialHellItems.CURSED_AXE.get());
            itemsToAdd.add(AerialHellItems.BERSERK_AXE.get());
            itemsToAdd.add(AerialHellItems.REAPER_SCYTHE.get());

            itemsToAdd.add(AerialHellItems.RUBY_HELMET.get());
            itemsToAdd.add(AerialHellItems.RUBY_CHESTPLATE.get());
            itemsToAdd.add(AerialHellItems.RUBY_LEGGINGS.get());
            itemsToAdd.add(AerialHellItems.RUBY_BOOTS.get());

            itemsToAdd.add(AerialHellItems.AZURITE_HELMET.get());
            itemsToAdd.add(AerialHellItems.AZURITE_CHESTPLATE.get());
            itemsToAdd.add(AerialHellItems.AZURITE_LEGGINGS.get());
            itemsToAdd.add(AerialHellItems.AZURITE_BOOTS.get());

            itemsToAdd.add(AerialHellItems.OBSIDIAN_HELMET.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_CHESTPLATE.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_LEGGINGS.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_BOOTS.get());

            itemsToAdd.add(AerialHellItems.VOLUCITE_HELMET.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_CHESTPLATE.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_LEGGINGS.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_BOOTS.get());

            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_HELMET.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_CHESTPLATE.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_LEGGINGS.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL_BOOTS.get());

            itemsToAdd.add(AerialHellItems.LUNATIC_HELMET.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_CHESTPLATE.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_LEGGINGS.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_BOOTS.get());

            itemsToAdd.add(AerialHellItems.ARSONIST_HELMET.get());
            itemsToAdd.add(AerialHellItems.ARSONIST_CHESTPLATE.get());
            itemsToAdd.add(AerialHellItems.ARSONIST_LEGGINGS.get());
            itemsToAdd.add(AerialHellItems.ARSONIST_BOOTS.get());

            itemsToAdd.add(AerialHellItems.SHADOW_HELMET.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CHESTPLATE.get());
            itemsToAdd.add(AerialHellItems.SHADOW_LEGGINGS.get());
            itemsToAdd.add(AerialHellItems.SHADOW_BOOTS.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_FOODSTUFFS.getKey())
        {
            itemsToAdd.add(AerialHellItems.AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellItems.ROASTED_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellItems.FROZEN_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellItems.RUBY_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellItems.GODS_VOLUCITE_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellItems.STELLAR_BREAD.get());
            itemsToAdd.add(AerialHellItems.FROZEN_MUTTON.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_CHICKEN.get());
            itemsToAdd.add(AerialHellItems.FROZEN_CHICKEN.get());
            itemsToAdd.add(AerialHellItems.GLOWING_STICK_FRUIT.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_GLOWING_STICK_FRUIT.get());
            itemsToAdd.add(AerialHellItems.FROZEN_GLOWING_STICK_FRUIT.get());
            itemsToAdd.add(AerialHellItems.CORTINARIUS_VIOLACEUS_PIECE.get());
            itemsToAdd.add(AerialHellItems.GANODERMA_APPLANATUM_PIECE.get());
            itemsToAdd.add(AerialHellItems.DARK_SHADOW_FRUIT.get());
            itemsToAdd.add(AerialHellItems.PURPLE_SHADOW_FRUIT.get());
            itemsToAdd.add(AerialHellItems.SHADOW_FRUIT_STEW.get());
            itemsToAdd.add(AerialHellItems.SOLID_ETHER_SOUP.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_SOLID_ETHER_SOUP.get());
            itemsToAdd.add(AerialHellItems.FROZEN_SOLID_ETHER_SOUP.get());
            itemsToAdd.add(AerialHellItems.SHADOW_SPIDER_EYE.get());
            itemsToAdd.add(AerialHellItems.PHANTOM_MEAT.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_PHANTOM_MEAT.get());
            itemsToAdd.add(AerialHellItems.FROZEN_PHANTOM_MEAT.get());
            itemsToAdd.add(AerialHellItems.COOKED_PHANTOM_MEAT.get());
            itemsToAdd.add(AerialHellItems.TURTLE_MEAT.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_TURTLE_MEAT.get());
            itemsToAdd.add(AerialHellItems.FROZEN_TURTLE_MEAT.get());
            itemsToAdd.add(AerialHellItems.COOKED_TURTLE_MEAT.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_CONE.get());
            itemsToAdd.add(AerialHellItems.AZURITE_COPPER_PINE_CONE.get());
            itemsToAdd.add(AerialHellItems.PHOENIX_FEATHER.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_SKY_CACTUS_FIBER.get());
            itemsToAdd.add(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellItems.GREEN_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellItems.PURPLE_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_MEAT_PIECE.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_NETHER_STEAK.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_GOLDEN_NETHER_STEAK.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_MISCELLANEOUS.getKey())
        {
            itemsToAdd.add(AerialHellItems.STELLAR_PORTAL_FRAME_BRICK.get());
            itemsToAdd.add(AerialHellItems.STELLAR_LIGHTER.get());

            itemsToAdd.add(AerialHellItems.CRYSTAL.get());
            itemsToAdd.add(AerialHellItems.STELLAR_STONE_CRYSTAL.get());
            itemsToAdd.add(AerialHellItems.SHADOW_CRYSTAL.get());

            itemsToAdd.add(AerialHellItems.SMOKY_QUARTZ.get());

            itemsToAdd.add(AerialHellItems.MUD_BONE.get());
            itemsToAdd.add(AerialHellItems.STELLAR_EGG.get());
            itemsToAdd.add(AerialHellItems.DIMENSION_SHATTERER_PROJECTILE.get());

            itemsToAdd.add(AerialHellItems.FLUORITE.get());
            itemsToAdd.add(AerialHellItems.MAGMATIC_GEL.get());
            itemsToAdd.add(AerialHellItems.RUBY.get());
            itemsToAdd.add(AerialHellItems.AZURITE_CRYSTAL.get());
            itemsToAdd.add(AerialHellItems.VOLUCITE_VIBRANT.get());

            itemsToAdd.add(AerialHellItems.RAW_RUBY.get());
            itemsToAdd.add(AerialHellItems.RAW_AZURITE.get());
            itemsToAdd.add(AerialHellItems.RAW_VOLUCITE.get());

            itemsToAdd.add(AerialHellItems.OVERHEATED_RUBY.get());
            itemsToAdd.add(AerialHellItems.OVERHEATED_VOLUCITE.get());

            itemsToAdd.add(AerialHellItems.ARSONIST_INGOT.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_CRYSTAL.get());
            itemsToAdd.add(AerialHellItems.OBSIDIAN_SHARD.get());
            itemsToAdd.add(AerialHellItems.CURSED_CRYSAL.get());

            itemsToAdd.add(AerialHellItems.ARSONIST_UPGRADE_SMITHING_TEMPLATE.get());

            itemsToAdd.add(AerialHellItems.AERIAL_BERRY_SEEDS.get());
            itemsToAdd.add(AerialHellItems.VIBRANT_AERIAL_BERRY_SEEDS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_WHEAT_SEEDS.get());
            itemsToAdd.add(AerialHellItems.STELLAR_WHEAT.get());

            itemsToAdd.add(AerialHellItems.SKY_STICK.get());
            itemsToAdd.add(AerialHellItems.SKY_BOWL.get());
            itemsToAdd.add(AerialHellItems.SHADOW_SHARD.get());
            itemsToAdd.add(AerialHellItems.ROTTEN_LEATHER.get());
            itemsToAdd.add(AerialHellItems.VENOMOUS_SNAKE_SKIN.get());

            itemsToAdd.add(AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET.get());
            itemsToAdd.add(AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET.get());
            itemsToAdd.add(AerialHellItems.RUBY_BUCKET.get());
            itemsToAdd.add(AerialHellItems.RUBY_WATER_BUCKET.get());
            itemsToAdd.add(AerialHellItems.RUBY_MILK_BUCKET.get());

            itemsToAdd.add(AerialHellItems.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP.get());
            itemsToAdd.add(AerialHellItems.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER.get());
            itemsToAdd.add(AerialHellItems.MUSIC_DISC_ENTHUSIAST_TOURS.get());
            itemsToAdd.add(AerialHellItems.MUSIC_DISC_BMINOR_ARULO.get());
            itemsToAdd.add(AerialHellItems.MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP.get());

            itemsToAdd.add(AerialHellItems.REGENERATION_TOTEM.get());
            itemsToAdd.add(AerialHellItems.SPEED_TOTEM.get());
            itemsToAdd.add(AerialHellItems.SPEED_II_TOTEM.get());
            itemsToAdd.add(AerialHellItems.NIGHT_VISION_TOTEM.get());
            itemsToAdd.add(AerialHellItems.AGILITY_TOTEM.get());
            itemsToAdd.add(AerialHellItems.HERO_TOTEM.get());
            itemsToAdd.add(AerialHellItems.HEAD_IN_THE_CLOUDS_TOTEM.get());
            itemsToAdd.add(AerialHellItems.GOD_TOTEM.get());
            itemsToAdd.add(AerialHellItems.CURSED_TOTEM.get());
            itemsToAdd.add(AerialHellItems.SHADOW_TOTEM.get());

            itemsToAdd.add(AerialHellItems.AERIAL_HELL_PAINTING.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_SPAWN_EGGS.getKey())
        {
            itemsToAdd.add(AerialHellItems.AERIAL_TREE_CHEST_MIMIC.get());
            itemsToAdd.add(AerialHellItems.GOLDEN_BEECH_CHEST_MIMIC.get());
            itemsToAdd.add(AerialHellItems.COPPER_PINE_CHEST_MIMIC.get());
            itemsToAdd.add(AerialHellItems.SKY_CACTUS_FIBER_CHEST_MIMIC.get());
            itemsToAdd.add(AerialHellItems.SHADOW_PINE_BARREL_MIMIC.get());

            itemsToAdd.add(AerialHellItems.STELLAR_STONE_AUTOMATON_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.EVIL_COW_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.CORTINARIUS_COW_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.STELLAR_ENT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.VENOMOUS_SNAKE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.WORM_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.STELLAR_CHICKEN_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.STELLAR_BOAR_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.SHROOMBOOM_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.VERDIGRIS_ZOMBIE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.MUMMY_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.SLIME_PIRATE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.SLIME_NINJA_PIRATE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.GHOST_SLIME_PIRATE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.SANDY_SHEEP_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.GLIDING_TURTLE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.FAT_PHANTOM_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.KODAMA_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.MUD_GOLEM_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.MUD_SOLDIER_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.MUD_CYCLE_MAGE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.HELL_SPIDER_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.CRYSTAL_GOLEM_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.CRYSTAL_SLIME_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.CRYSTAL_SPIDER_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.LUNATIC_PRIEST_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.CRYSTAL_CATERPILLAR_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.FOREST_CATERPILLAR_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.TORN_SPIRIT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.CHAINED_GOD_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.ICE_SPIRIT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.FIRE_SPIRIT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.ELECTRO_SPIRIT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.FLYING_JELLYFISH_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_FLYING_SKULL_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_TROLL_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_AUTOMATON_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.SHADOW_SPIDER_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellItems.LILITH_SPAWN_EGG.get());
        }

        addAllItemsToTab(event, itemsToAdd);
    }

    private static void addAllItemsToTab(BuildCreativeModeTabContentsEvent event, List<Item> itemList)
    {
        for (Item item : itemList)
        {
            event.accept(item.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
