package fr.factionbedrock.aerialhell.Integration.JEI;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.OscillatorScreen;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

public class OscillatingRecipeCategory extends AbstractCookingCategory<OscillatingRecipe>
{
    public static final IRecipeType<OscillatingRecipe> OSCILLATING = IRecipeType.create(AerialHell.MODID, "oscillating", OscillatingRecipe.class);

    public OscillatingRecipeCategory(IGuiHelper guiHelper)
    {
        super(OSCILLATING, guiHelper, AerialHellBlocks.OSCILLATOR, "block.aerialhell.oscillator", OscillatorScreen.OSCILLATOR_GUI_TEXTURES, AerialHellItems.FLUORITE);
    }

    @Override protected RecipeDisplay getDisplay(OscillatingRecipe recipe) {return recipe.display().getFirst();}
}