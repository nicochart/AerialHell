package fr.factionbedrock.aerialhell.Integration.JEI;

import com.google.common.collect.ImmutableMap;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;
import java.util.Map;

public class JEIHelper
{
    public static List<RecipeHolder<OscillatingRecipe>> createOscillatingRecipeHolderList()
    {
        return OSCILLATING_MAP.entrySet().stream().map(entry -> createOscillatingRecipeHolder(entry.getKey(), entry.getValue())).toList();
    }

    public static List<RecipeHolder<FreezingRecipe>> createFreezingRecipeHolderList()
    {
        return FREEZING_MAP.entrySet().stream().map(entry -> createFreezingRecipeHolder(entry.getKey(), entry.getValue())).toList();
    }

    public static RecipeHolder<OscillatingRecipe> createOscillatingRecipeHolder(List<Item> ingredients, Item result)
    {
        OscillatingRecipe recipe = createOscillatingRecipe(ingredients, result);
        Identifier recipeIdentifier = Identifier.fromNamespaceAndPath(AerialHell.MODID, "oscillating_recipe");
        return new RecipeHolder<>(ResourceKey.create(Registries.RECIPE, recipeIdentifier), recipe);
    }

    public static OscillatingRecipe createOscillatingRecipe(List<Item> ingredient, Item result)
    {
        return new OscillatingRecipe("", null, Ingredient.of(ingredient.stream()), result.getDefaultInstance(), 0, 200);
    }

    public static RecipeHolder<FreezingRecipe> createFreezingRecipeHolder(List<Item> ingredients, Item result)
    {
        FreezingRecipe recipe = createFreezingRecipe(ingredients, result);
        Identifier recipeIdentifier = Identifier.fromNamespaceAndPath(AerialHell.MODID, "freezing_recipe");
        return new RecipeHolder<>(ResourceKey.create(Registries.RECIPE, recipeIdentifier), recipe);
    }

    public static FreezingRecipe createFreezingRecipe(List<Item> ingredient, Item result)
    {
        return new FreezingRecipe("", null, Ingredient.of(ingredient.stream()), result.getDefaultInstance(), 0, 200);
    }

    public static Map<List<Item>, Item> OSCILLATING_MAP = new ImmutableMap.Builder<List<Item>, Item>()
            .put(List.of(AerialHellItems.TURTLE_MEAT.get()), AerialHellItems.VIBRANT_TURTLE_MEAT.get())
            .put(List.of(AerialHellItems.PHANTOM_MEAT.get()), AerialHellItems.VIBRANT_PHANTOM_MEAT.get())
            .put(List.of(AerialHellItems.AZURITE_ORE.get(), AerialHellItems.RAW_AZURITE.get()), AerialHellItems.AZURITE_CRYSTAL.get())
            .put(List.of(AerialHellItems.GREEN_SOLID_ETHER_FRAGMENT.get()), AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT.get())
            .put(List.of(AerialHellItems.STELLAR_STONE_CRYSTAL_BLOCK.get()), AerialHellItems.CRYSTAL_BLOCK.get())
            .put(List.of(AerialHellItems.STELLAR_STONE_CRYSTAL.get()), AerialHellItems.CRYSTAL.get())
            .put(List.of(AerialHellItems.FLUORITE_ORE.get()), AerialHellItems.FLUORITE.get())
            .put(List.of(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT.get()), AerialHellItems.GOLDEN_SOLID_ETHER_FRAGMENT.get())
            .put(List.of(AerialHellItems.ROTTEN_LEATHER.get()), Items.LEATHER)
            .put(List.of(AerialHellItems.RUBY_ORE.get(), AerialHellItems.OVERHEATED_RUBY.get(), AerialHellItems.RAW_RUBY.get()), AerialHellItems.RUBY.get())
            .put(List.of(AerialHellItems.SLIPPERY_SAND.get()), AerialHellItems.SLIPPERY_SAND_GLASS.get())
            .put(List.of(Items.CLAY), AerialHellItems.STELLAR_CLAY.get())
            .put(List.of(AerialHellItems.STELLAR_COBBLESTONE.get()), AerialHellItems.STELLAR_STONE.get())
            .put(List.of(AerialHellItems.AERIAL_BERRY.get()), AerialHellItems.VIBRANT_AERIAL_BERRY.get())
            .put(List.of(Items.CHICKEN), AerialHellItems.VIBRANT_CHICKEN.get())
            .put(List.of(AerialHellItems.GLOWING_STICK_FRUIT.get()), AerialHellItems.VIBRANT_GLOWING_STICK_FRUIT.get())
            .put(List.of(AerialHellItems.GOLDEN_NETHER_STEAK.get()), AerialHellItems.VIBRANT_GOLDEN_NETHER_STEAK.get())
            .put(List.of(AerialHellItems.SKY_CACTUS_FIBER.get()), AerialHellItems.VIBRANT_SKY_CACTUS_FIBER.get())
            .put(List.of(AerialHellItems.SKY_CACTUS.get()), AerialHellItems.VIBRANT_SKY_CACTUS.get())
            .put(List.of(AerialHellItems.SOLID_ETHER_SOUP.get()), AerialHellItems.VIBRANT_SOLID_ETHER_SOUP.get())
            .put(List.of(AerialHellItems.VOLUCITE_ORE.get(), AerialHellItems.OVERHEATED_VOLUCITE.get(), AerialHellItems.RAW_VOLUCITE.get()), AerialHellItems.VOLUCITE_VIBRANT.get())
            .build();

    public static Map<List<Item>, Item> FREEZING_MAP = new ImmutableMap.Builder<List<Item>, Item>()
            .put(List.of(Items.CHICKEN), AerialHellItems.FROZEN_CHICKEN.get())
            .put(List.of(Items.MUTTON), AerialHellItems.FROZEN_MUTTON.get())
            .put(List.of(Items.WATER_BUCKET, AerialHellItems.RUBY_WATER_BUCKET.get()), Items.ICE)
            .put(List.of(Items.ICE), Items.PACKED_ICE)
            .put(List.of(Items.LAVA_BUCKET), AerialHellItems.CRYSTAL_BLOCK.get())
            .put(List.of(AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET.get(), AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET.get()), Items.OBSIDIAN)
            .put(List.of(AerialHellItems.AERIAL_BERRY.get()), AerialHellItems.FROZEN_AERIAL_BERRY.get())
            .put(List.of(AerialHellItems.WHITE_SOLID_ETHER_FRAGMENT.get()), AerialHellItems.BLUE_SOLID_ETHER_FRAGMENT.get())
            .put(List.of(AerialHellItems.WHITE_SOLID_ETHER.get()), AerialHellItems.BLUE_SOLID_ETHER.get())
            .put(List.of(AerialHellItems.OVERHEATED_RUBY.get()), AerialHellItems.RUBY.get())
            .put(List.of(AerialHellItems.OVERHEATED_VOLUCITE.get()), AerialHellItems.VOLUCITE_VIBRANT.get())
            .put(List.of(AerialHellItems.VOLUCITE_VIBRANT.get()), Items.DIAMOND)
            .put(List.of(AerialHellItems.AZURITE_CRYSTAL.get()), Items.QUARTZ)
            .put(List.of(AerialHellItems.SOLID_ETHER_SOUP.get()), AerialHellItems.FROZEN_SOLID_ETHER_SOUP.get())
            .put(List.of(AerialHellItems.TURTLE_MEAT.get()), AerialHellItems.FROZEN_TURTLE_MEAT.get())
            .put(List.of(AerialHellItems.PHANTOM_MEAT.get()), AerialHellItems.FROZEN_PHANTOM_MEAT.get())
            .put(List.of(AerialHellItems.GLOWING_STICK_FRUIT.get()), AerialHellItems.FROZEN_GLOWING_STICK_FRUIT.get())
            .build();
}
