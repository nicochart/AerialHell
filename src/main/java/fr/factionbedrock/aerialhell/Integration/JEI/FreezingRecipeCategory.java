package fr.factionbedrock.aerialhell.Integration.JEI;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.FreezerScreen;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

public class FreezingRecipeCategory extends AbstractCookingCategory<FreezingRecipe>
{
    public static final IRecipeType<FreezingRecipe> FREEZING = IRecipeType.create(AerialHell.MODID, "freezing", FreezingRecipe.class);

    public FreezingRecipeCategory(IGuiHelper guiHelper)
    {
        super(FREEZING, guiHelper, AerialHellBlocks.FREEZER, "block.aerialhell.freezer", FreezerScreen.FREEZER_GUI_TEXTURES, AerialHellItems.MAGMATIC_GEL);
    }

    @Override protected RecipeDisplay getDisplay(FreezingRecipe recipe) {return recipe.display().getFirst();}
}