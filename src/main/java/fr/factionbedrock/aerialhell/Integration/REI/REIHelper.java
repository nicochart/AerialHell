package fr.factionbedrock.aerialhell.Integration.REI;

import com.google.common.collect.ImmutableMap;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;

public class REIHelper
{
    public static Map<Item, Item> OSCILLATING_MAP = new ImmutableMap.Builder<Item, Item>()
            .put(AerialHellItems.TURTLE_MEAT.get(), AerialHellItems.VIBRANT_TURTLE_MEAT.get())
            .put(AerialHellItems.PHANTOM_MEAT.get(), AerialHellItems.VIBRANT_PHANTOM_MEAT.get())
            .put(AerialHellItems.AZURITE_ORE.get(), AerialHellItems.AZURITE_CRYSTAL.get())
            .put(AerialHellItems.RAW_AZURITE.get(), AerialHellItems.AZURITE_CRYSTAL.get())
            .put(AerialHellItems.GREEN_SOLID_ETHER_FRAGMENT.get(), AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT.get())
            .put(AerialHellItems.STELLAR_STONE_CRYSTAL_BLOCK.get(), AerialHellItems.CRYSTAL_BLOCK.get())
            .put(AerialHellItems.STELLAR_STONE_CRYSTAL.get(), AerialHellItems.CRYSTAL.get())
            .put(AerialHellItems.FLUORITE_ORE.get(), AerialHellItems.FLUORITE.get())
            .put(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT.get(), AerialHellItems.GOLDEN_SOLID_ETHER_FRAGMENT.get())
            .put(AerialHellItems.ROTTEN_LEATHER.get(), Items.LEATHER)
            .put(AerialHellItems.RUBY_ORE.get(), AerialHellItems.RUBY.get())
            .put(AerialHellItems.OVERHEATED_RUBY.get(), AerialHellItems.RUBY.get())
            .put(AerialHellItems.RAW_RUBY.get(), AerialHellItems.RUBY.get())
            .put(AerialHellItems.SLIPPERY_SAND.get(), AerialHellItems.SLIPPERY_SAND_GLASS.get())
            .put(Items.CLAY, AerialHellItems.STELLAR_CLAY.get())
            .put(AerialHellItems.STELLAR_COBBLESTONE.get(), AerialHellItems.STELLAR_STONE.get())
            .put(AerialHellItems.AERIAL_BERRY.get(), AerialHellItems.VIBRANT_AERIAL_BERRY.get())
            .put(Items.CHICKEN, AerialHellItems.VIBRANT_CHICKEN.get())
            .put(AerialHellItems.GLOWING_STICK_FRUIT.get(), AerialHellItems.VIBRANT_GLOWING_STICK_FRUIT.get())
            .put(AerialHellItems.GOLDEN_NETHER_STEAK.get(), AerialHellItems.VIBRANT_GOLDEN_NETHER_STEAK.get())
            .put(AerialHellItems.SKY_CACTUS_FIBER.get(), AerialHellItems.VIBRANT_SKY_CACTUS_FIBER.get())
            .put(AerialHellItems.SKY_CACTUS.get(), AerialHellItems.VIBRANT_SKY_CACTUS.get())
            .put(AerialHellItems.SOLID_ETHER_SOUP.get(), AerialHellItems.VIBRANT_SOLID_ETHER_SOUP.get())
            .put(AerialHellItems.VOLUCITE_ORE.get(), AerialHellItems.VOLUCITE_VIBRANT.get())
            .put(AerialHellItems.OVERHEATED_VOLUCITE.get(), AerialHellItems.VOLUCITE_VIBRANT.get())
            .put(AerialHellItems.RAW_VOLUCITE.get(), AerialHellItems.VOLUCITE_VIBRANT.get())
            .build();

    public static Map<Item, Item> FREEZING_MAP = new ImmutableMap.Builder<Item, Item>()
            .put(Items.CHICKEN, AerialHellItems.FROZEN_CHICKEN.get())
            .put(Items.MUTTON, AerialHellItems.FROZEN_MUTTON.get())
            .put(Items.WATER_BUCKET, Items.ICE)
            .put(Items.ICE, Items.PACKED_ICE)
            .put(Items.LAVA_BUCKET, AerialHellItems.CRYSTAL_BLOCK.get())
            .put(AerialHellItems.RUBY_WATER_BUCKET.get(), Items.ICE)
            .put(AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET.get(), Items.OBSIDIAN)
            .put(AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET.get(), Items.OBSIDIAN)
            .put(AerialHellItems.AERIAL_BERRY.get(), AerialHellItems.FROZEN_AERIAL_BERRY.get())
            .put(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT.get(), AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT.get())
            .put(AerialHellItems.WHITE_SOLID_ETHER.get(), AerialHellItems.BLUE_SOLID_ETHER.get())
            .put(AerialHellItems.OVERHEATED_RUBY.get(), AerialHellItems.RUBY.get())
            .put(AerialHellItems.OVERHEATED_VOLUCITE.get(), AerialHellItems.VOLUCITE_VIBRANT.get())
            .put(AerialHellItems.VOLUCITE_VIBRANT.get(), Items.DIAMOND)
            .put(AerialHellItems.AZURITE_CRYSTAL.get(), Items.QUARTZ)
            .put(AerialHellItems.SOLID_ETHER_SOUP.get(), AerialHellItems.FROZEN_SOLID_ETHER_SOUP.get())
            .put(AerialHellItems.TURTLE_MEAT.get(), AerialHellItems.FROZEN_TURTLE_MEAT.get())
            .put(AerialHellItems.PHANTOM_MEAT.get(), AerialHellItems.FROZEN_PHANTOM_MEAT.get())
            .put(AerialHellItems.GLOWING_STICK_FRUIT.get(), AerialHellItems.FROZEN_GLOWING_STICK_FRUIT.get())
            .build();
}
