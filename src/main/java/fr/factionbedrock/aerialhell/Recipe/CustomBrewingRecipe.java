package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;

public class CustomBrewingRecipe
{
    public static void addBrewingRecipes(BrewingRecipeRegistry.Builder builder)
    {
        builder.registerPotionRecipe(Potions.AWKWARD, AerialHellItems.SHADOW_SPIDER_EYE, Potions.POISON);
        builder.registerPotionRecipe(Potions.AWKWARD, AerialHellItems.VENOMOUS_SNAKE_SKIN, Potions.HEALING);
    }
}
