package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.Alignment;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class FreezingRecipeDisplay extends SmeltingLikeRecipeDisplay
{
    private static final StationInfo FREEZING_STATION_INFO = new StationInfo(new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/block/freezer_side_on.png"), 0.0F, 0.0F, 16, 16, 16,48), "block.aerialhell.freezer");

    public FreezingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<Item> ingredient, Supplier<Item> result, boolean displayTooltip)
    {
        super(lineIndex, alignment, scale, () -> ingredient.get().getDefaultInstance(), () -> result.get().getDefaultInstance(), () -> AerialHellItems.MAGMATIC_GEL.get().getDefaultInstance(), FREEZING_STATION_INFO, displayTooltip);
    }
}
