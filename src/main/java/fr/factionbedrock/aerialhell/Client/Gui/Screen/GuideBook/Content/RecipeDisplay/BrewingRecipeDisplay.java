package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay;

import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.Alignment;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class BrewingRecipeDisplay extends SmeltingLikeRecipeDisplay
{
    private static final StationInfo BREWING_STAND_INFO = new StationInfo(new TextureInfo(Identifier.withDefaultNamespace("textures/item/brewing_stand.png"), 16,16), "block.minecraft.brewing_stand");

    public BrewingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<ItemStack> basePotion, Supplier<ItemStack> ingredient, Supplier<ItemStack> resultPotion, boolean displayTooltip)
    {
        super(lineIndex, alignment, scale, basePotion, resultPotion, ingredient, BREWING_STAND_INFO, displayTooltip);
    }
}
