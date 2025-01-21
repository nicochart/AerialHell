package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;


public class CustomBrewingRecipe
{
    public static void addBrewingRecipes(RegisterBrewingRecipesEvent event)
    {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, AerialHellItems.SHADOW_SPIDER_EYE.get(), Potions.POISON);
        builder.addMix(Potions.AWKWARD, AerialHellItems.VENOMOUS_SNAKE_SKIN.get(), Potions.HEALING);
    }
}
