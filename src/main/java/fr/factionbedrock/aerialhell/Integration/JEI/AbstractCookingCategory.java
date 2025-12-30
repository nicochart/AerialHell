package fr.factionbedrock.aerialhell.Integration.JEI;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.FreezerScreen;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.OscillatorScreen;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import net.minecraft.block.Block;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.display.FurnaceRecipeDisplay;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.text.Text;

//copy of JEI AbstractCookingCategory class, removed some methods
public abstract class AbstractCookingCategory<T extends AbstractCookingRecipe> extends AbstractRecipeCategory<RecipeEntry<T>>
{
    protected final boolean isOscillating;
    public final IRecipeHolderType<T> recipeType;
    protected final IDrawable oscillatingWave;
    protected final IDrawable freezingFlake;

    public AbstractCookingCategory(IGuiHelper guiHelper, IRecipeHolderType<T> recipeType, Block icon, String translationKey, boolean isOscillating)
    {
        this(guiHelper, recipeType, icon, translationKey, isOscillating, 82, 54);
    }

    public AbstractCookingCategory(IGuiHelper guiHelper, IRecipeHolderType<T> recipeType, Block icon, String translationKey, boolean isOscillating, int width, int height)
    {
        super(recipeType, Text.translatable(translationKey), guiHelper.createDrawableItemLike(icon), width, height);
        this.isOscillating = isOscillating;
        this.recipeType = recipeType;
        this.oscillatingWave = guiHelper.createDrawable(OscillatorScreen.OSCILLATOR_GUI_TEXTURES, 57, 36, 13, 13);
        this.freezingFlake = guiHelper.createDrawable(FreezerScreen.FREEZER_GUI_TEXTURES, 57, 36, 13, 13);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeEntry<T> recipeHolder, IFocusGroup focuses)
    {
        T recipe = recipeHolder.value();
        RecipeDisplay display = recipe.getDisplays().getFirst();
        if (display instanceof FurnaceRecipeDisplay furnaceRecipeDisplay)
        {
            builder.addInputSlot(1, 1)
                    .setStandardSlotBackground()
                    .add(furnaceRecipeDisplay.ingredient());

            builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 1, 37)
                    .setStandardSlotBackground()
                    .add(new SlotDisplay.ItemSlotDisplay(AerialHellItems.FLUORITE));

            builder.addOutputSlot(61, 19)
                    .setOutputSlotBackground()
                    .add(furnaceRecipeDisplay.result());
        }
    }


    @Override public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeEntry<T> recipeHolder, IFocusGroup focuses)
    {
        builder.addRecipeArrow().setPosition(26, 17);
        builder.addDrawable(this.isOscillating ? oscillatingWave : freezingFlake).setPosition(2, 20);
    }

    @Override public final Codec<RecipeEntry<T>> getCodec(ICodecHelper codecHelper, IRecipeManager recipeManager) {return codecHelper.getRecipeHolderCodec();}
}
