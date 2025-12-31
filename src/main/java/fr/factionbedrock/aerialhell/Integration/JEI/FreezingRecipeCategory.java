package fr.factionbedrock.aerialhell.Integration.JEI;

import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.types.IRecipeHolderType;

public class FreezingRecipeCategory extends AbstractCookingCategory<FreezingRecipe>
{
    public static final IRecipeHolderType<FreezingRecipe> FREEZING = IRecipeHolderType.create(AerialHellRecipes.RecipeTypes.FREEZING.getId());

	public FreezingRecipeCategory(IGuiHelper guiHelper)
    {
        super(guiHelper, FREEZING, AerialHellBlocks.FREEZER.get(), "block.aerialhell.freezer", false);
    }
}
