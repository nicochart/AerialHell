package fr.factionbedrock.aerialhell.Integration.REI;

import com.google.common.collect.ImmutableMap;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Map;

public class REIHelper
{
    public static Map<Item, Item> OSCILLATING_MAP = new ImmutableMap.Builder<Item, Item>()
            .put(AerialHellItems.TURTLE_MEAT, AerialHellItems.VIBRANT_TURTLE_MEAT)
            .put(AerialHellItems.PHANTOM_MEAT, AerialHellItems.VIBRANT_PHANTOM_MEAT)
            .put(AerialHellItems.AZURITE_ORE, AerialHellItems.AZURITE_CRYSTAL)
            .put(AerialHellItems.RAW_AZURITE, AerialHellItems.AZURITE_CRYSTAL)
            .put(AerialHellItems.GREEN_SOLID_ETHER_FRAGMENT, AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT)
            .put(AerialHellItems.STELLAR_STONE_CRYSTAL_BLOCK, AerialHellItems.CRYSTAL_BLOCK)
            .put(AerialHellItems.STELLAR_STONE_CRYSTAL, AerialHellItems.CRYSTAL)
            .put(AerialHellItems.FLUORITE_ORE, AerialHellItems.FLUORITE)
            .put(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT, AerialHellItems.GOLDEN_SOLID_ETHER_FRAGMENT)
            .put(AerialHellItems.ROTTEN_LEATHER, Items.LEATHER)
            .put(AerialHellItems.RUBY_ORE, AerialHellItems.RUBY)
            .put(AerialHellItems.OVERHEATED_RUBY, AerialHellItems.RUBY)
            .put(AerialHellItems.RAW_RUBY, AerialHellItems.RUBY)
            .put(AerialHellItems.SLIPPERY_SAND, AerialHellItems.SLIPPERY_SAND_GLASS)
            .put(Items.CLAY, AerialHellItems.STELLAR_CLAY)
            .put(AerialHellItems.STELLAR_COBBLESTONE, AerialHellItems.STELLAR_STONE)
            .put(AerialHellItems.AERIAL_BERRY, AerialHellItems.VIBRANT_AERIAL_BERRY)
            .put(Items.CHICKEN, AerialHellItems.VIBRANT_CHICKEN)
            .put(AerialHellItems.GLOWING_STICK_FRUIT, AerialHellItems.VIBRANT_GLOWING_STICK_FRUIT)
            .put(AerialHellItems.GOLDEN_NETHER_STEAK, AerialHellItems.VIBRANT_GOLDEN_NETHER_STEAK)
            .put(AerialHellItems.SKY_CACTUS_FIBER, AerialHellItems.VIBRANT_SKY_CACTUS_FIBER)
            .put(AerialHellItems.SKY_CACTUS, AerialHellItems.VIBRANT_SKY_CACTUS)
            .put(AerialHellItems.SOLID_ETHER_SOUP, AerialHellItems.VIBRANT_SOLID_ETHER_SOUP)
            .put(AerialHellItems.VOLUCITE_ORE, AerialHellItems.VOLUCITE_VIBRANT)
            .put(AerialHellItems.OVERHEATED_VOLUCITE, AerialHellItems.VOLUCITE_VIBRANT)
            .put(AerialHellItems.RAW_VOLUCITE, AerialHellItems.VOLUCITE_VIBRANT)
            .build();

    public static Map<Item, Item> FREEZING_MAP = new ImmutableMap.Builder<Item, Item>()
            .put(Items.CHICKEN, AerialHellItems.FROZEN_CHICKEN)
            .put(Items.MUTTON, AerialHellItems.FROZEN_MUTTON)
            .put(Items.WATER_BUCKET, Items.ICE)
            .put(Items.ICE, Items.PACKED_ICE)
            .put(Items.LAVA_BUCKET, AerialHellItems.CRYSTAL_BLOCK)
            .put(AerialHellItems.RUBY_WATER_BUCKET, Items.ICE)
            .put(AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET, Items.OBSIDIAN)
            .put(AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET, Items.OBSIDIAN)
            .put(AerialHellItems.AERIAL_BERRY, AerialHellItems.FROZEN_AERIAL_BERRY)
            .put(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT, AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT)
            .put(AerialHellItems.WHITE_SOLID_ETHER, AerialHellItems.BLUE_SOLID_ETHER)
            .put(AerialHellItems.OVERHEATED_RUBY, AerialHellItems.RUBY)
            .put(AerialHellItems.OVERHEATED_VOLUCITE, AerialHellItems.VOLUCITE_VIBRANT)
            .put(AerialHellItems.VOLUCITE_VIBRANT, Items.DIAMOND)
            .put(AerialHellItems.AZURITE_CRYSTAL, Items.QUARTZ)
            .put(AerialHellItems.SOLID_ETHER_SOUP, AerialHellItems.FROZEN_SOLID_ETHER_SOUP)
            .put(AerialHellItems.TURTLE_MEAT, AerialHellItems.FROZEN_TURTLE_MEAT)
            .put(AerialHellItems.PHANTOM_MEAT, AerialHellItems.FROZEN_PHANTOM_MEAT)
            .put(AerialHellItems.GLOWING_STICK_FRUIT, AerialHellItems.FROZEN_GLOWING_STICK_FRUIT)
            .build();
}
