package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.Alignment;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class SingleIngredientCraftingRecipeDisplay extends SmeltingLikeRecipeDisplay
{
    private static final StationInfo CRAFTING_TABLE_STATION_INFO = new StationInfo(new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/block/aerial_tree_crafting_table_top.png"), 16,16), "block.aerialhell.aerial_tree_crafting_table");

    public SingleIngredientCraftingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<Item> ingredient, Supplier<ItemStack> result, boolean displayTooltip)
    {
        super(lineIndex, alignment, scale, () -> ingredient.get().getDefaultInstance(), result, () -> null, CRAFTING_TABLE_STATION_INFO, displayTooltip);
    }
}
