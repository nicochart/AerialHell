package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

public class CustomBrewingRecipe
{
    public static void addBrewingRecipes(PotionBrewing.Builder builder)
    {
        builder.addMix(Potions.AWKWARD, AerialHellItems.SHADOW_SPIDER_EYE, Potions.POISON);
        builder.addMix(Potions.AWKWARD, AerialHellItems.VENOMOUS_SNAKE_SKIN, Potions.HEALING);
    }
}
