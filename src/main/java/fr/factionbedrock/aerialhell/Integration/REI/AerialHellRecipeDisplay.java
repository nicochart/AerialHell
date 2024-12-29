package fr.factionbedrock.aerialhell.Integration.REI;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AerialHellRecipeDisplay extends BasicDisplay
{
    private final CategoryIdentifier<AerialHellRecipeDisplay> categoryIdentifier;

    public AerialHellRecipeDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, CategoryIdentifier<AerialHellRecipeDisplay> category)
    {
        super(inputs, outputs);
        this.categoryIdentifier = category;
    }

    public AerialHellRecipeDisplay(RecipeEntry<? extends AbstractCookingRecipe> recipe, CategoryIdentifier<AerialHellRecipeDisplay> category)
    {
        super(getInputList(recipe.value()), List.of(EntryIngredient.of(EntryStacks.of(recipe.value().getResult(null)))));
        this.categoryIdentifier = category;
    }

    private static <R extends AbstractCookingRecipe> List<EntryIngredient> getInputList(R recipe)
    {
        if(recipe == null) return Collections.emptyList();
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(0)));
        return list;
    }

    @Override public CategoryIdentifier<?> getCategoryIdentifier() {return this.categoryIdentifier;}
}
