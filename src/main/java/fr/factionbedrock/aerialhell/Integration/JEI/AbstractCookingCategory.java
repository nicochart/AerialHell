package fr.factionbedrock.aerialhell.Integration.JEI;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.block.Block;

public abstract class AbstractCookingCategory<T> extends AbstractRecipeCategory<T>
{
    protected final IDrawable machineDrawable;
    protected final Item catalystItem;

    public AbstractCookingCategory(IRecipeType<T> recipeType, IGuiHelper guiHelper, Block icon, String translationKey, Identifier progressIdentifier, Item catalystItem, int width, int height)
    {
        super(recipeType, Component.translatable(translationKey), guiHelper.createDrawableItemLike(icon), width, height);

        this.machineDrawable = guiHelper.createDrawable(progressIdentifier, 57, 36, 13, 13);
        this.catalystItem = catalystItem;
    }

    public AbstractCookingCategory(IRecipeType<T> recipeType, IGuiHelper guiHelper, Block icon, String translationKey, Identifier progressIdentifier, Item catalystItem)
    {
        this(recipeType, guiHelper, icon, translationKey, progressIdentifier, catalystItem, 82, 54);
    }

    protected abstract RecipeDisplay getDisplay(T recipe);

    @Override public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses)
    {
        RecipeDisplay display = getDisplay(recipe);

        if (display instanceof FurnaceRecipeDisplay furnaceRecipeDisplay)
        {
            builder.addInputSlot(1, 1)
                    .setStandardSlotBackground()
                    .add(furnaceRecipeDisplay.ingredient());

            builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 1, 37)
                    .setStandardSlotBackground()
                    .add(new SlotDisplay.ItemSlotDisplay(this.catalystItem));

            builder.addOutputSlot(61, 19)
                    .setOutputSlotBackground()
                    .add(furnaceRecipeDisplay.result());
        }
    }

    @Override public void createRecipeExtras(IRecipeExtrasBuilder builder, T recipe, IFocusGroup focuses)
    {
        builder.addRecipeArrow().setPosition(26, 17);
        builder.addDrawable(this.machineDrawable).setPosition(2, 20);
    }
}