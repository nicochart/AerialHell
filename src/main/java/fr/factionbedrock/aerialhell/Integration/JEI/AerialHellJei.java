package fr.factionbedrock.aerialhell.Integration.JEI;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.FreezerScreen;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.OscillatorScreen;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;

@JeiPlugin
public class AerialHellJei implements IModPlugin
{
    public AerialHellJei() {}

    @Override public void registerCategories(IRecipeCategoryRegistration registration)
    {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registration.addRecipeCategories(new OscillatingRecipeCategory(guiHelper));
        registration.addRecipeCategories(new FreezingRecipeCategory(guiHelper));
    }

    @Override public void registerRecipes(IRecipeRegistration registration)
    {
        registration.getJeiHelpers().getGuiHelper().createDrawable(OscillatorScreen.OSCILLATOR_GUI_TEXTURES, 57, 36, 13, 13);
        registration.getJeiHelpers().getGuiHelper().createDrawable(FreezerScreen.FREEZER_GUI_TEXTURES, 57, 36, 13, 13);

        registration.addRecipes(OscillatingRecipeCategory.OSCILLATING, JEIHelper.createOscillatingRecipeHolderList());
        registration.addRecipes(FreezingRecipeCategory.FREEZING, JEIHelper.createFreezingRecipeHolderList());
    }

    @Override public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addCraftingStation(OscillatingRecipeCategory.OSCILLATING, AerialHellItems.OSCILLATOR);
        registration.addCraftingStation(FreezingRecipeCategory.FREEZING, AerialHellItems.FREEZER);
    }

    @Override public Identifier getPluginUid() {return Identifier.fromNamespaceAndPath(AerialHell.MODID, "jei");}
}