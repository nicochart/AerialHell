package fr.factionbedrock.aerialhell.Integration.JEI;

import com.google.common.collect.ImmutableMap;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public class JEIHelper
{
    public static List<RecipeEntry<OscillatingRecipe>> createOscillatingRecipeEntryList()
    {
        return OSCILLATING_MAP.entrySet().stream().map(entry -> createOscillatingRecipeEntry(entry.getKey(), entry.getValue())).toList();
    }

    public static List<RecipeEntry<FreezingRecipe>> createFreezingRecipeEntryList()
    {
        return FREEZING_MAP.entrySet().stream().map(entry -> createFreezingRecipeEntry(entry.getKey(), entry.getValue())).toList();
    }

    public static RecipeEntry<OscillatingRecipe> createOscillatingRecipeEntry(List<Item> ingredients, Item result)
    {
        OscillatingRecipe recipe = createOscillatingRecipe(ingredients, result);
        Identifier recipeIdentifier = AerialHell.id("oscillating_recipe");
        return new RecipeEntry<>(RegistryKey.of(RegistryKeys.RECIPE, recipeIdentifier), recipe);
    }

    public static OscillatingRecipe createOscillatingRecipe(List<Item> ingredient, Item result)
    {
        return new OscillatingRecipe("", null, Ingredient.ofItems(ingredient.stream()), result.getDefaultStack(), 0, 200);
    }

    public static RecipeEntry<FreezingRecipe> createFreezingRecipeEntry(List<Item> ingredients, Item result)
    {
        FreezingRecipe recipe = createFreezingRecipe(ingredients, result);
        Identifier recipeIdentifier = AerialHell.id("freezing_recipe");
        return new RecipeEntry<>(RegistryKey.of(RegistryKeys.RECIPE, recipeIdentifier), recipe);
    }

    public static FreezingRecipe createFreezingRecipe(List<Item> ingredient, Item result)
    {
        return new FreezingRecipe("", null, Ingredient.ofItems(ingredient.stream()), result.getDefaultStack(), 0, 200);
    }

    public static Map<List<Item>, Item> OSCILLATING_MAP = new ImmutableMap.Builder<List<Item>, Item>()
            .put(List.of(AerialHellItems.TURTLE_MEAT), AerialHellItems.VIBRANT_TURTLE_MEAT)
            .put(List.of(AerialHellItems.PHANTOM_MEAT), AerialHellItems.VIBRANT_PHANTOM_MEAT)
            .put(List.of(AerialHellItems.AZURITE_ORE, AerialHellItems.RAW_AZURITE), AerialHellItems.AZURITE_CRYSTAL)
            .put(List.of(AerialHellItems.GREEN_SOLID_ETHER_FRAGMENT), AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT)
            .put(List.of(AerialHellItems.STELLAR_STONE_CRYSTAL_BLOCK), AerialHellItems.CRYSTAL_BLOCK)
            .put(List.of(AerialHellItems.STELLAR_STONE_CRYSTAL), AerialHellItems.CRYSTAL)
            .put(List.of(AerialHellItems.FLUORITE_ORE), AerialHellItems.FLUORITE)
            .put(List.of(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT), AerialHellItems.GOLDEN_SOLID_ETHER_FRAGMENT)
            .put(List.of(AerialHellItems.ROTTEN_LEATHER), Items.LEATHER)
            .put(List.of(AerialHellItems.RUBY_ORE, AerialHellItems.OVERHEATED_RUBY, AerialHellItems.RAW_RUBY), AerialHellItems.RUBY)
            .put(List.of(AerialHellItems.SLIPPERY_SAND), AerialHellItems.SLIPPERY_SAND_GLASS)
            .put(List.of(Items.CLAY), AerialHellItems.STELLAR_CLAY)
            .put(List.of(AerialHellItems.STELLAR_COBBLESTONE), AerialHellItems.STELLAR_STONE)
            .put(List.of(AerialHellItems.AERIAL_BERRY), AerialHellItems.VIBRANT_AERIAL_BERRY)
            .put(List.of(Items.CHICKEN), AerialHellItems.VIBRANT_CHICKEN)
            .put(List.of(AerialHellItems.GLOWING_STICK_FRUIT), AerialHellItems.VIBRANT_GLOWING_STICK_FRUIT)
            .put(List.of(AerialHellItems.GOLDEN_NETHER_STEAK), AerialHellItems.VIBRANT_GOLDEN_NETHER_STEAK)
            .put(List.of(AerialHellItems.SKY_CACTUS_FIBER), AerialHellItems.VIBRANT_SKY_CACTUS_FIBER)
            .put(List.of(AerialHellItems.SKY_CACTUS), AerialHellItems.VIBRANT_SKY_CACTUS)
            .put(List.of(AerialHellItems.SOLID_ETHER_SOUP), AerialHellItems.VIBRANT_SOLID_ETHER_SOUP)
            .put(List.of(AerialHellItems.VOLUCITE_ORE, AerialHellItems.OVERHEATED_VOLUCITE, AerialHellItems.RAW_VOLUCITE), AerialHellItems.VOLUCITE_VIBRANT)
            .build();

    public static Map<List<Item>, Item> FREEZING_MAP = new ImmutableMap.Builder<List<Item>, Item>()
            .put(List.of(Items.CHICKEN), AerialHellItems.FROZEN_CHICKEN)
            .put(List.of(Items.MUTTON), AerialHellItems.FROZEN_MUTTON)
            .put(List.of(Items.WATER_BUCKET, AerialHellItems.RUBY_WATER_BUCKET), Items.ICE)
            .put(List.of(Items.ICE), Items.PACKED_ICE)
            .put(List.of(Items.LAVA_BUCKET), AerialHellItems.CRYSTAL_BLOCK)
            .put(List.of(AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET, AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET), Items.OBSIDIAN)
            .put(List.of(AerialHellItems.AERIAL_BERRY), AerialHellItems.FROZEN_AERIAL_BERRY)
            .put(List.of(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT), AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT)
            .put(List.of(AerialHellItems.WHITE_SOLID_ETHER), AerialHellItems.BLUE_SOLID_ETHER)
            .put(List.of(AerialHellItems.OVERHEATED_RUBY), AerialHellItems.RUBY)
            .put(List.of(AerialHellItems.OVERHEATED_VOLUCITE), AerialHellItems.VOLUCITE_VIBRANT)
            .put(List.of(AerialHellItems.VOLUCITE_VIBRANT), Items.DIAMOND)
            .put(List.of(AerialHellItems.AZURITE_CRYSTAL), Items.QUARTZ)
            .put(List.of(AerialHellItems.SOLID_ETHER_SOUP), AerialHellItems.FROZEN_SOLID_ETHER_SOUP)
            .put(List.of(AerialHellItems.TURTLE_MEAT), AerialHellItems.FROZEN_TURTLE_MEAT)
            .put(List.of(AerialHellItems.PHANTOM_MEAT), AerialHellItems.FROZEN_PHANTOM_MEAT)
            .put(List.of(AerialHellItems.GLOWING_STICK_FRUIT), AerialHellItems.FROZEN_GLOWING_STICK_FRUIT)
            .build();
}
