package fr.factionbedrock.aerialhell.Integration.REI;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

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

    public <R extends AbstractCookingRecipe> AerialHellRecipeDisplay(R recipe, CategoryIdentifier<AerialHellRecipeDisplay> category)
    {
        super(getInputList(recipe), List.of(EntryIngredient.of(EntryStacks.of(recipe.result()))));
        this.categoryIdentifier = category;
    }

    private static <R extends AbstractCookingRecipe> List<EntryIngredient> getInputList(R recipe)
    {
        if(recipe == null) return Collections.emptyList();
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.input()));
        return list;
    }

    @Override public CategoryIdentifier<?> getCategoryIdentifier() {return this.categoryIdentifier;}

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return null;
    }
}
