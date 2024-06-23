package fr.factionbedrock.aerialhell.Recipe;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;


public class CustomBrewingRecipe
{
    public static void addBrewingRecipes(RegisterBrewingRecipesEvent event)
    {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, AerialHellBlocksAndItems.SHADOW_SPIDER_EYE.get(), Potions.POISON);
        builder.addMix(Potions.AWKWARD, AerialHellBlocksAndItems.VENOMOUS_SNAKE_SKIN.get(), Potions.HEALING);
    }
}
