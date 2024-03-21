package fr.factionbedrock.aerialhell.Registry.CreativeModeTabs;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BuildContentsEvent
{
    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event)
    {
        List<Item> itemsToAdd = new ArrayList<Item>();

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_LIGHTER.get());
        }

        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_BLOCKS.getKey())
        {
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.FLUORITE_TORCH_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_TORCH_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_TORCH_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FLUORITE_LANTERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_LANTERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_FLUORITE_LANTERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_LANTERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_FLUORITE_LANTERN_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_CHAIN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_CHAIN_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.GIANT_ROOT_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STRIPPED_AERIAL_TREE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_LEAVES_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_AERIAL_TREE_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_SAPLING_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.PETRIFIED_AERIAL_TREE_LOG_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STRIPPED_AERIAL_TREE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_LEAVES_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_GOLDEN_BEECH_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_SAPLING_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STRIPPED_COPPER_PINE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_LEAVES_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_SAPLING_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ENCHANTED_LAPIS_ROBINIA_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STRIPPED_LAPIS_ROBINIA_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_LEAVES_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_SAPLING_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.EYE_SHADOW_PINE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STRIPPED_SHADOW_PINE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_LEAVES_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_LEAVES_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_SAPLING_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_SAPLING_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STRIPPED_STELLAR_JUNGLE_TREE_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_LEAVES_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_SAPLING_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DEAD_STELLAR_JUNGLE_TREE_LOG_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_STEM_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GIANT_CORTINARIUS_VIOLACEUS_LIGHT_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CORTINARIUS_VIOLACEUS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLOWING_BOLETUS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TALL_GLOWING_BOLETUS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BLUE_MEANIE_CLUSTER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GIANT_ROOT_SHROOM_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.GIANT_VERDIGRIS_AGARIC_STEM_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VERDIGRIS_AGARIC_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.GIANT_GANODERMA_APPLANATUM_BLOCK_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_BOOKSHELF_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SKY_LADDER_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_CLAY_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLAUCOPHANITE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.POLISHED_GLAUCOPHANITE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CRYSTAL_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTALLIZED_LEAVES_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_STELLAR_GRASS_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_DIRT_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_COARSE_DIRT_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_FARMLAND_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_DIRT_PATH_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_PODZOL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_CRYSTAL_PODZOL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_STELLAR_DIRT_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CUT_SLIPPERY_SAND_STONE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_GLASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RED_SLIPPERY_SAND_GLASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BLACK_SLIPPERY_SAND_GLASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BLUE_SLIPPERY_SAND_GLASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GREEN_SLIPPERY_SAND_GLASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_GLASS_PANE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RED_SLIPPERY_SAND_GLASS_PANE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BLACK_SLIPPERY_SAND_GLASS_PANE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BLUE_SLIPPERY_SAND_GLASS_PANE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GREEN_SLIPPERY_SAND_GLASS_PANE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_LOG_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_FENCE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_GATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_DOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_TRAPDOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_WOOL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_RUBY_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_FLUORITE_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_BARREL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_BOAT_CRAFTING_TABLE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.WHITE_SOLID_ETHER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BLUE_SOLID_ETHER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GREEN_SOLID_ETHER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.PURPLE_SOLID_ETHER_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SMOKY_QUARTZ_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SMOOTH_SMOKY_QUARTZ_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_SMOKY_QUARTZ_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SMOKY_QUARTZ_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SMOKY_QUARTZ_PILLAR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SMOKY_QUARTZ_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SMOOTH_SMOKY_QUARTZ_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SMOKY_QUARTZ_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SMOOTH_SMOKY_QUARTZ_STAIRS_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.IRON_STELLAR_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLD_STELLAR_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DIAMOND_STELLAR_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FLUORITE_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_ORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SMOKY_QUARTZ_ORE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.FLUORITE_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_BLOCK_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.RAW_RUBY_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RAW_AZURITE_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RAW_VOLUCITE_BLOCK_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_CRYSTAL_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CURSED_CRYSAL_BLOCK_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_PLANKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS_FIBER_LANTERN_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.CLIMBING_VINE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_SUGAR_CANE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.FULL_MOON_FLOWER_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.VINE_BLOSSOM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAZULI_ROOTS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_ROOTS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DEAD_ROOTS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLOWING_ROOTS_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_GRASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_GRASS_BALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_FERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_TALL_GRASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_TALL_FERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_VERY_TALL_GRASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BLUISH_FERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TALL_BLUISH_FERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.POLYCHROME_FERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TALL_POLYCHROME_FERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_DEAD_BUSH_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BRAMBLES_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_BRAMBLES_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_GRASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_GRASS_BALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.PURPLISH_STELLAR_GRASS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_CLOVERS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLOWING_STELLAR_GRASS_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.BLUE_FLOWER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BLACK_ROSE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BELLFLOWER_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.OSCILLATOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FREEZER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_FURNACE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_CHEST_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_FENCE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_FENCE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_FENCE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_FENCE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_FENCE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_FENCE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_FENCE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_FENCE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_BARS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLAUCOPHANITE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.POLISHED_GLAUCOPHANITE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_WALL_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_GATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_GATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_GATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_GATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_GATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_GATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_GATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_GATE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_DOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_DOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_DOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_DOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_DOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_DOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_DOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_DOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_DOOR_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_TRAPDOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_TRAPDOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_TRAPDOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_TRAPDOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_TRAPDOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_TRAPDOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_TRAPDOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_TRAPDOOR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_TRAPDOOR_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_BRICKS_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_BUTTON_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLAUCOPHANITE_BUTTON_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_BRICKS_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_PRESSURE_PLATE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLAUCOPHANITE_PRESSURE_PLATE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.POLISHED_GLAUCOPHANITE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_SLAB_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_COBBLESTONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_STELLAR_STONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_STELLAR_COBBLESTONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.POLISHED_GLAUCOPHANITE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_STAIRS_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_SIGN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_SIGN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_SIGN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_SIGN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_SIGN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_SIGN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_SIGN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_SIGN_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_CRAFTING_TABLE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_CRAFTING_TABLE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_CRAFTING_TABLE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_CRAFTING_TABLE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_CRAFTING_TABLE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_CRAFTING_TABLE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CRAFTING_TABLE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_CRAFTING_TABLE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_BARREL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_BARREL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_BARREL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_BARREL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_BARREL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_BARREL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_BARREL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_BARREL_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_COMPOSTER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_COMPOSTER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_COMPOSTER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LAPIS_ROBINIA_COMPOSTER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_COMPOSTER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_COMPOSTER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_COMPOSTER_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GRAY_SHROOM_COMPOSTER_ITEM.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_DUNGEON_BLOCKS.getKey())
        {
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_LANTERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_LANTERN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_CHAIN_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CHAIN_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MUD_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_MUD_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_MUD_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LIGHT_MUD_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_LIGHT_MUD_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DARK_LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ROOF_LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LIGHT_LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ROOF_LIGHT_LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_LIGHT_LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CATACOMBS_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_SHADOW_CATACOMBS_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_SHADOW_CATACOMBS_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BONE_SHADOW_CATACOMBS_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKULL_SHADOW_CATACOMBS_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LIGHT_SHADOW_CATACOMBS_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_NETHER_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_GOLDEN_NETHER_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_GOLDEN_NETHER_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LIGHT_GOLDEN_NETHER_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_PILLAR_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_PILLAR_TOP_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_VOLUCITE_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHISELED_VOLUCITE_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LIGHT_VOLUCITE_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_LIGHT_VOLUCITE_STONE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MUD_DUNGEON_CORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_DUNGEON_CORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CATACOMBS_DUNGEON_CORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_NETHER_DUNGEON_CORE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_DUNGEON_CORE_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MUD_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUD_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUD_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_MUD_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_MUD_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_MUD_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_MUD_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_STONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_STONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_STONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_VOLUCITE_STONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_VOLUCITE_STONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_VOLUCITE_STONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_STONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_STONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_STONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DARK_LUNATIC_STONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DARK_LUNATIC_STONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DARK_LUNATIC_STONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_LUNATIC_STONE_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_LUNATIC_STONE_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_LUNATIC_STONE_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CATACOMBS_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CATACOMBS_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_BARS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_NETHER_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_NETHER_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_NETHER_BRICKS_WALL_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_GOLDEN_NETHER_BRICKS_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRACKED_GOLDEN_NETHER_BRICKS_WALL_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.TRAPPED_MUD_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TRAPPED_LIGHT_MUD_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TRAPPED_LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TRAPPED_LIGHT_LUNATIC_STONE_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TRAPPED_GOLDEN_NETHER_BRICKS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MUD_BONE_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUD_BONE_PILE_BLOCK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.THORNY_COBWEB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_NETHERRACK_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_NETHERRACK_SLAB_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_NETHERRACK_STAIRS_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_NETHERRACK_WALL_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MUD_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_NETHER_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CATACOMBS_BOOKSHELF_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_BOOKSHELF_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MUD_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CATACOMBS_CHEST_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_NETHER_CHEST_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MUD_CYCLE_MAGE_TROPHY_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNAR_PRIEST_TROPHY_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LILITH_TROPHY_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHAINED_GOD_TROPHY_ITEM.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_TOOLS.getKey())
        {
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_WOOD_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMA_CUBE_PICKAXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_BREAKER.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SKY_WOOD_SHOVEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_SHOVEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_SHOVEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_SHOVEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_SHOVEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_SHOVEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_SHOVEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_SHOVEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_SHOVEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMA_CUBE_SHOVEL.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SKY_WOOD_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_AXE.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SKY_WOOD_HOE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_HOE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_HOE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_HOE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_HOE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_HOE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_HOE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_HOE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_HOE.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_COMBAT.getKey())
        {
            itemsToAdd.add(AerialHellBlocksAndItems.IRON_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLD_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DIAMOND_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.NETHERITE_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_CRYSTAL_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_SHURIKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LIGHTNING_SHURIKEN.get());

            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_BLOWPIPE_ARROW.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_BLOWPIPE_ARROW.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_BLOWPIPE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_BLOWPIPE.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SKY_WOOD_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_SWORD.get());

            itemsToAdd.add(AerialHellBlocksAndItems.HEAVY_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.HEALTH_BOOST_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.NINJA_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.NINJA_MASTER_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLOUTON_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RANDOM_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DISLOYAL_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CURSED_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ABSOLUTE_ZERO_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SWORD_OF_LIGHT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ANTIDOTE_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.NETHERIAN_KING_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLASS_CANON_SWORD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOD_SWORD.get());

            itemsToAdd.add(AerialHellBlocksAndItems.FORGOTTEN_BATTLE_TRIDENT.get());

            itemsToAdd.add(AerialHellBlocksAndItems.HEAVY_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AXE_OF_LIGHT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CURSED_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BERSERK_AXE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.REAPER_SCYTHE.get());

            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_HELMET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_CHESTPLATE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_LEGGINGS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_BOOTS.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_HELMET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_CHESTPLATE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_LEGGINGS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_BOOTS.get());

            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_HELMET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_CHESTPLATE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_LEGGINGS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_BOOTS.get());

            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_HELMET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_CHESTPLATE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_LEGGINGS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_BOOTS.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_HELMET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_CHESTPLATE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_LEGGINGS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL_BOOTS.get());

            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_HELMET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_CHESTPLATE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_LEGGINGS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_BOOTS.get());

            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_HELMET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_CHESTPLATE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_LEGGINGS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_BOOTS.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_HELMET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CHESTPLATE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_LEGGINGS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_BOOTS.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_FOODSTUFFS.getKey())
        {
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ROASTED_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FROZEN_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GODS_VOLUCITE_AERIAL_BERRY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_BREAD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FROZEN_MUTTON.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_CHICKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FROZEN_CHICKEN.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLOWING_STICK_FRUIT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_GLOWING_STICK_FRUIT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FROZEN_GLOWING_STICK_FRUIT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CORTINARIUS_VIOLACEUS_PIECE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GANODERMA_APPLANATUM_PIECE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.DARK_SHADOW_FRUIT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.PURPLE_SHADOW_FRUIT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_FRUIT_STEW.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SOLID_ETHER_SOUP.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_SOLID_ETHER_SOUP.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FROZEN_SOLID_ETHER_SOUP.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_SPIDER_EYE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.PHANTOM_MEAT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_PHANTOM_MEAT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FROZEN_PHANTOM_MEAT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COOKED_PHANTOM_MEAT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TURTLE_MEAT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_TURTLE_MEAT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FROZEN_TURTLE_MEAT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COOKED_TURTLE_MEAT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_CONE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_COPPER_PINE_CONE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.PHOENIX_FEATHER.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_SKY_CACTUS_FIBER.get());
            itemsToAdd.add(AerialHellBlocksAndItems.WHITE_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.BLUE_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GREEN_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.PURPLE_SOLID_ETHER_FRAGMENT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_NETHER_MEAT_PIECE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_NETHER_STEAK.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_GOLDEN_NETHER_STEAK.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_MISCELLANEOUS.getKey())
        {
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BRICK.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_LIGHTER.get());

            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_CRYSTAL.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SMOKY_QUARTZ.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MUD_BONE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_EGG.get());

            itemsToAdd.add(AerialHellBlocksAndItems.FLUORITE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MAGMATIC_GEL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AZURITE_CRYSTAL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VOLUCITE_VIBRANT.get());

            itemsToAdd.add(AerialHellBlocksAndItems.RAW_RUBY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RAW_AZURITE.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RAW_VOLUCITE.get());

            itemsToAdd.add(AerialHellBlocksAndItems.OVERHEATED_RUBY.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OVERHEATED_VOLUCITE.get());

            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_INGOT.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_CRYSTAL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.OBSIDIAN_SHARD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CURSED_CRYSAL.get());

            itemsToAdd.add(AerialHellBlocksAndItems.ARSONIST_UPGRADE_SMITHING_TEMPLATE.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_BERRY_SEEDS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY_SEEDS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_WHEAT_SEEDS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_WHEAT_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.SKY_STICK.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_BOWL.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_SHARD.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ROTTEN_LEATHER.get());

            itemsToAdd.add(AerialHellBlocksAndItems.IRON_LIQUID_OF_GODS_BUCKET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_LIQUID_OF_GODS_BUCKET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_BUCKET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_WATER_BUCKET.get());
            itemsToAdd.add(AerialHellBlocksAndItems.RUBY_MILK_BUCKET.get());

            itemsToAdd.add(AerialHellBlocksAndItems.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUSIC_DISC_ENTHUSIAST_TOURS.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUSIC_DISC_BMINOR_ARULO.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP.get());

            itemsToAdd.add(AerialHellBlocksAndItems.REGENERATION_TOTEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SPEED_TOTEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SPEED_II_TOTEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.NIGHT_VISION_TOTEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.AGILITY_TOTEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.HERO_TOTEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.HEAD_IN_THE_CLOUDS_TOTEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOD_TOTEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CURSED_TOTEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_TOTEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_HELL_PAINTING.get());
        }
        if (event.getTabKey() == AerialHellCreativeModeTabs.AERIAL_HELL_SPAWN_EGGS.getKey())
        {
            itemsToAdd.add(AerialHellBlocksAndItems.AERIAL_TREE_CHEST_MIMIC_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST_MIMIC_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.COPPER_PINE_CHEST_MIMIC_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_MIMIC_ITEM.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_PINE_BARREL_MIMIC_ITEM.get());

            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_STONE_AUTOMATON_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.EVIL_COW_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CORTINARIUS_COW_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_ENT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_CHICKEN_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.STELLAR_BOAR_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHROOMBOOM_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.VERDIGRIS_ZOMBIE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIME_PIRATE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SLIME_NINJA_PIRATE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GHOST_SLIME_PIRATE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SANDY_SHEEP_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.GLIDING_TURTLE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FAT_PHANTOM_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.KODAMA_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUD_GOLEM_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUD_SOLDIER_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.MUD_CYCLE_MAGE_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.HELL_SPIDER_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL_GOLEM_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL_SLIME_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL_SPIDER_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LUNATIC_PRIEST_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CRYSTAL_CATERPILLAR_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FOREST_CATERPILLAR_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.TORN_SPIRIT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.CHAINED_GOD_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ICE_SPIRIT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FIRE_SPIRIT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.ELECTRO_SPIRIT_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.FLYING_JELLYFISH_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_FLYING_SKULL_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_TROLL_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_AUTOMATON_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.SHADOW_SPIDER_SPAWN_EGG.get());
            itemsToAdd.add(AerialHellBlocksAndItems.LILITH_SPAWN_EGG.get());
        }

        addAllItemsToTab(event, itemsToAdd);
    }

    private static void addAllItemsToTab(BuildCreativeModeTabContentsEvent event, List<Item> itemList)
    {
        for (Item item : itemList)
        {
            event.getEntries().put(item.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
