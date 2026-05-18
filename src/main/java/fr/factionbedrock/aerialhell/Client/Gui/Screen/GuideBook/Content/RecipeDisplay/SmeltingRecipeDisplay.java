package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.Alignment;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public class SmeltingRecipeDisplay extends SmeltingLikeRecipeDisplay
{
    private static final SmeltingLikeRecipeDisplay.StationInfo STELLAR_FURNACE_STATION_INFO = new StationInfo(new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/block/stellar_furnace_front_on.png"), 16,16), "block.aerialhell.stellar_furnace");

    public SmeltingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<Item> ingredient, Supplier<Item> result, boolean displayTooltip)
    {
        super(lineIndex, alignment, scale, () -> ingredient.get().getDefaultInstance(), () -> result.get().getDefaultInstance(), Items.CHARCOAL::getDefaultInstance, STELLAR_FURNACE_STATION_INFO, displayTooltip);
    }
}
