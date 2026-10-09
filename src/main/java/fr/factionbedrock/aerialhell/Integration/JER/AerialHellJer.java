package fr.factionbedrock.aerialhell.Integration.JER;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import jeresources.api.IJERAPI;
import jeresources.api.IJERPlugin;
import jeresources.api.JERPlugin;
import jeresources.api.distributions.DistributionSquare;
import jeresources.api.drop.LootDrop;
import jeresources.api.restrictions.BiomeRestriction;
import jeresources.api.restrictions.DimensionRestriction;
import jeresources.api.restrictions.Restriction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

@JERPlugin
public class AerialHellJer implements IJERPlugin
{
    public static final DimensionRestriction AERIAL_HELL_DIMENSION = new DimensionRestriction(AerialHellDimensions.AERIAL_HELL_DIMENSION);
    public static final BiomeRestriction SHADOW_BIOMES = new BiomeRestriction(AerialHellBiomes.SHADOW_PLAIN, AerialHellBiomes.SHADOW_FOREST);

    @Override public void receive(IJERAPI ijerapi)
    {
        registerOreDistributions(ijerapi);
        registerDungeonsLootTables(ijerapi);
    }

    private static void registerOreDistributions(IJERAPI api)
    {
        registerClassicOreDistribution(api, AerialHellBlocks.IRON_STELLAR_ORE, Items.IRON_NUGGET, 20, 160, 7, 12);
        registerClassicOreDistribution(api, AerialHellBlocks.GOLD_STELLAR_ORE, Items.GOLD_NUGGET, 20, 160, 10, 7);
        registerClassicOreDistribution(api, AerialHellBlocks.DIAMOND_STELLAR_ORE, Items.GOLD_NUGGET, 10, 120, 4, 6);
        registerClassicOreDistribution(api, AerialHellBlocks.FLUORITE_ORE, AerialHellItems.FLUORITE, 20, 256, 40, 12);
        registerClassicOreDistribution(api, AerialHellBlocks.MAGMATIC_GEL_ORE, AerialHellItems.MAGMATIC_GEL, 0, 70, 12, 12);
        registerClassicOreDistribution(api, AerialHellBlocks.RUBY_ORE, AerialHellItems.RAW_RUBY, 20, 256, 34, 7);
        registerClassicOreDistribution(api, AerialHellBlocks.AZURITE_ORE, AerialHellItems.RAW_AZURITE, 20, 170, 10, 5);
        registerClassicOreDistribution(api, AerialHellBlocks.VOLUCITE_ORE, AerialHellItems.RAW_VOLUCITE, 160, 256, 3, 7);
        registerClassicOreDistribution(api, AerialHellBlocks.OBSIDIAN_ORE, AerialHellItems.OBSIDIAN_SHARD, 10, 70, 2, 5);

        registerShadowOreDistribution(api, AerialHellBlocks.SMOKY_QUARTZ_ORE, AerialHellItems.SMOKY_QUARTZ, 20, 200, 16, 14);
    }

    private static void registerClassicOreDistribution(IJERAPI api, DeferredBlock<? extends Block> oreBlock, DeferredItem<? extends Item> oreDrop, int minY, int maxY, int count, int maxVeinSize) {registerClassicOreDistribution(api, oreBlock, oreDrop.get(), minY, maxY, count, maxVeinSize);}
    private static void registerClassicOreDistribution(IJERAPI api, DeferredBlock<? extends Block> oreBlock, Item oreDrop, int minY, int maxY, int count, int maxVeinSize)
    {
        api.getWorldGenRegistry().register(
                new ItemStack(oreBlock.get().asItem()),
                new DistributionSquare(minY, maxY, calculateOreChance(minY, maxY, count, maxVeinSize)),
                new Restriction(BiomeRestriction.NO_RESTRICTION, AERIAL_HELL_DIMENSION),
                new LootDrop(oreDrop.getDefaultInstance())
        );
    }

    private static void registerShadowOreDistribution(IJERAPI api, DeferredBlock<? extends Block> oreBlock, DeferredItem<? extends Item> oreDrop, int minY, int maxY, int count, int maxVeinSize)
    {
        api.getWorldGenRegistry().register(
                new ItemStack(oreBlock.get().asItem()),
                new DistributionSquare(minY, maxY, calculateOreChance(minY, maxY, count, maxVeinSize)),
                new Restriction(SHADOW_BIOMES, AERIAL_HELL_DIMENSION),
                new LootDrop(oreDrop.get().getDefaultInstance())
        );
    }

    private static final String overworld_abandonned_portal = "aerialhell.structure.overworld_abandonned_portal";
    private static final String aerial_hell_abandonned_portal = "aerialhell.structure.aerial_hell_abandonned_portal";
    private static final String copper_pine_cottage = "aerialhell.structure.copper_pine_cottage";
    private static final String lapis_robinia_hut = "aerialhell.structure.lapis_robinia_hut";
    private static final String shadow_pine_tower = "aerialhell.structure.shadow_pine_tower";
    private static final String stellar_stone_bricks_tower = "aerialhell.dungeon.stellar_stone_bricks_tower";
    private static final String upside_down_pyramid = "aerialhell.dungeon.upside_down_pyramid";
    private static final String floating_boat = "aerialhell.dungeon.floating_boat";
    private static final String mud_dungeon = "aerialhell.dungeon.mud_dungeon";
    private static final String lunatic_temple = "aerialhell.dungeon.lunatic_temple";
    private static final String golden_nether_prison = "aerialhell.dungeon.golden_nether_prison";
    private static final String shadow_catacombs = "aerialhell.dungeon.shadow_catacombs";

    private static void registerDungeonsLootTables(IJERAPI api)
    {
        registerDungeonCategory(api, overworld_abandonned_portal);
        registerDungeonLootTable(api, overworld_abandonned_portal, "chests/overworld_abandonned_portal_chest");

        registerDungeonCategory(api, aerial_hell_abandonned_portal);
        registerDungeonLootTable(api, aerial_hell_abandonned_portal, "chests/aerial_hell_abandonned_portal_chest");

        registerDungeonCategory(api, copper_pine_cottage);
        registerDungeonLootTable(api, copper_pine_cottage, "chests/copper_pine_cottage_loot");

        registerDungeonCategory(api, lapis_robinia_hut);
        registerDungeonLootTable(api, lapis_robinia_hut, "chests/lapis_robinia_hut_chest");

        registerDungeonCategory(api, shadow_pine_tower);
        registerDungeonLootTable(api, shadow_pine_tower, "chests/shadow_pine_tower_chest");

        registerDungeonCategory(api, stellar_stone_bricks_tower);
        registerDungeonLootTable(api, stellar_stone_bricks_tower, "chests/stellar_stone_bricks_tower_chest");
        registerDungeonLootTable(api, stellar_stone_bricks_tower, "chests/paper_farm_chest");

        registerDungeonCategory(api, upside_down_pyramid);
        registerDungeonLootTable(api, upside_down_pyramid, "chests/pyramid_chest");
        registerDungeonLootTable(api, upside_down_pyramid, "chests/pyramid_treasure");

        registerDungeonCategory(api, floating_boat);
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/azurite_treasure");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/berth_deck");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/ghost_lower_deck_weapon");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/ghost_quarterdeck_treasure");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/gold_treasure");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/hold");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/lower_deck_food");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/lower_deck_weapon");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/orlop_deck");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/quarterdeck_treasure");
        registerDungeonLootTable(api, floating_boat, "chests/floating_boat/ruby_treasure");

        registerDungeonCategory(api, mud_dungeon);
        registerDungeonLootTable(api, mud_dungeon, "chests/mud_dungeon_chest");
        registerDungeonLootTable(api, mud_dungeon, "chests/mud_dungeon_treasure");

        registerDungeonCategory(api, lunatic_temple);
        registerDungeonLootTable(api, lunatic_temple, "chests/lunatic_chest");
        registerDungeonLootTable(api, lunatic_temple, "chests/lunatic_treasure");

        registerDungeonCategory(api, golden_nether_prison);
        registerDungeonLootTable(api, golden_nether_prison, "chests/golden_nether_chest");
        registerDungeonLootTable(api, golden_nether_prison, "chests/golden_nether_treasure");

        registerDungeonCategory(api, shadow_catacombs);
        registerDungeonLootTable(api, shadow_catacombs, "chests/shadow_chest");
        registerDungeonLootTable(api, shadow_catacombs, "chests/shadow_treasure");
    }

    private static void registerDungeonCategory(IJERAPI api, String category)
    {
        api.getDungeonRegistry().registerCategory(category, category);
    }

    private static void registerDungeonLootTable(IJERAPI api, String category, String lootTable)
    {
        api.getDungeonRegistry().registerChest(category, createLootTableKey(lootTable));
    }

    private static float calculateOreChance(int minY, int maxY, int count, int maxVeinSize)
    {
        //number of ore blocks / total number of blocks
        return (float) (count * maxVeinSize) / ((maxY - minY) * 256.0f);
    }

    private static ResourceKey<LootTable> createLootTableKey(String name)
    {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(AerialHell.MODID, name));
    }
}
