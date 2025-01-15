package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeBookCategories;

import java.util.List;

public class FurnaceLikeScreen<T extends AbstractFurnaceMenu> extends AbstractFurnaceScreen<T>
{
    private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.smeltable");
    private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
            new RecipeBookComponent.TabInfo(SearchRecipeBookCategory.FURNACE),
            new RecipeBookComponent.TabInfo(Items.PORKCHOP, RecipeBookCategories.FURNACE_FOOD),
            new RecipeBookComponent.TabInfo(Items.STONE, RecipeBookCategories.FURNACE_BLOCKS),
            new RecipeBookComponent.TabInfo(Items.LAVA_BUCKET, Items.EMERALD, RecipeBookCategories.FURNACE_MISC)
    );

    public FurnaceLikeScreen(T container, Inventory inventory, Component name, ResourceLocation texture, ResourceLocation litProgressSprite, ResourceLocation burnProgressSprite)
    {
        super(container, inventory, name, FILTER_NAME, texture, litProgressSprite, burnProgressSprite, TABS);
    }

}
