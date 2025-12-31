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
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.display.FurnaceRecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.block.Block;

//copy of JEI AbstractCookingCategory class, removed some methods
public abstract class AbstractCookingCategory<T extends AbstractCookingRecipe> extends AbstractRecipeCategory<RecipeHolder<T>>
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
        super(recipeType, Component.translatable(translationKey), guiHelper.createDrawableItemLike(icon), width, height);
        this.isOscillating = isOscillating;
        this.recipeType = recipeType;
        this.oscillatingWave = guiHelper.createDrawable(OscillatorScreen.OSCILLATOR_GUI_TEXTURES, 57, 36, 13, 13);
        this.freezingFlake = guiHelper.createDrawable(FreezerScreen.FREEZER_GUI_TEXTURES, 57, 36, 13, 13);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<T> recipeHolder, IFocusGroup focuses)
    {
        T recipe = recipeHolder.value();
        RecipeDisplay display = recipe.display().getFirst();
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


    @Override public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<T> recipeHolder, IFocusGroup focuses)
    {
        builder.addRecipeArrow().setPosition(26, 17);
        builder.addDrawable(this.isOscillating ? oscillatingWave : freezingFlake).setPosition(2, 20);
    }

    @Override public final Codec<RecipeHolder<T>> getCodec(ICodecHelper codecHelper, IRecipeManager recipeManager) {return codecHelper.getRecipeHolderCodec();}
}
