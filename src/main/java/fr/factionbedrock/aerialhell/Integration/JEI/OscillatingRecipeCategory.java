package fr.factionbedrock.aerialhell.Integration.JEI;

import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.types.IRecipeHolderType;

public class OscillatingRecipeCategory extends AbstractCookingCategory<OscillatingRecipe>
{
    public static final IRecipeHolderType<OscillatingRecipe> OSCILLATING = IRecipeHolderType.create(AerialHellRecipes.RecipeTypes.OSCILLATING.getId());

	public OscillatingRecipeCategory(IGuiHelper guiHelper)
    {
        super(guiHelper, OSCILLATING, AerialHellBlocks.OSCILLATOR.get(), "block.aerialhell.oscillator", true);
    }
}
