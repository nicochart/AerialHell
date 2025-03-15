package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.recipebook.RecipeBookType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.util.Identifier;

import java.util.List;

public class FurnaceLikeScreen<T extends AbstractFurnaceScreenHandler> extends AbstractFurnaceScreen<T>
{
    private static final Text FILTER_NAME = Text.translatable("gui.recipebook.toggleRecipes.smeltable");
    private static final List<RecipeBookWidget.Tab> TABS = List.of(
            new RecipeBookWidget.Tab(RecipeBookType.FURNACE),
            new RecipeBookWidget.Tab(Items.PORKCHOP, RecipeBookCategories.FURNACE_FOOD),
            new RecipeBookWidget.Tab(Items.STONE, RecipeBookCategories.FURNACE_BLOCKS),
            new RecipeBookWidget.Tab(Items.LAVA_BUCKET, Items.EMERALD, RecipeBookCategories.FURNACE_MISC)
    );

    public FurnaceLikeScreen(T container, PlayerInventory inventory, Text name, Identifier texture, Identifier litProgressSprite, Identifier burnProgressSprite)
    {
        super(container, inventory, name, FILTER_NAME, texture, litProgressSprite, burnProgressSprite, TABS);
    }

}
