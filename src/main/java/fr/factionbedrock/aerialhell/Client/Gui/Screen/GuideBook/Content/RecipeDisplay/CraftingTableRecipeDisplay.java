package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.Alignment;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.Line;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.PageElement;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record CraftingTableRecipeDisplay(int lineIndex, Alignment alignment, float scale, Ingredients ingredients, Supplier<Item> result, boolean displayTooltip) implements PageElement
{
    private static final TextureInfo CRAFTING_GRID_TEXTURE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/recipe/crafting_grid_display.png"), 100, 54);

    @Override public void render(Font font, GuiGraphicsExtractor graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        Item resultItem = this.result.get();
        if (resultItem == null) {return;}

        Line line = lines.get(this.lineIndex());

        int recipeWidth = (int)(CRAFTING_GRID_TEXTURE.width() * this.scale());
        int slotSize = (int)(16 * this.scale());
        int slotSpacing = (int)(17 * this.scale()); //16 slot + 1 separator

        int startX = switch (this.alignment())
        {
            case LEFT -> line.startX();
            case CENTER -> line.centerX() - recipeWidth / 2;
            case RIGHT -> line.rightX() - recipeWidth;
        };

        int startY = line.startY();

        //render crafting grid background
        graphics.pose().pushMatrix();

        graphics.pose().translate(startX, startY);
        graphics.pose().scale(this.scale(), this.scale());

        graphics.blit(RenderPipelines.GUI_TEXTURED, CRAFTING_GRID_TEXTURE.texture(), 0, 0, CRAFTING_GRID_TEXTURE.u(), CRAFTING_GRID_TEXTURE.v(), CRAFTING_GRID_TEXTURE.width(), CRAFTING_GRID_TEXTURE.height(), CRAFTING_GRID_TEXTURE.textureWidth(), CRAFTING_GRID_TEXTURE.textureHeight());

        graphics.pose().popMatrix();

        //render ingredients
        for (int i = 0; i < ingredients.get().size(); i++)
        {
            Item item = ingredients.get().get(i).get();

            if (item == null) {continue;}

            int row = i / 3;
            int col = i % 3;

            //1 px outer margin + 1 px separator before first slot
            int itemX = startX + (int)(2 * this.scale()) + col * slotSpacing;
            int itemY = startY + (int)(2 * this.scale()) + row * slotSpacing;

            boolean hovered = mouseX >= itemX && mouseX <= itemX + slotSize && mouseY >= itemY && mouseY <= itemY + slotSize;

            graphics.pose().pushMatrix();

            graphics.pose().translate(itemX, itemY);
            graphics.pose().scale(this.scale(), this.scale());

            graphics.fakeItem(item.getDefaultInstance(), 0, 0);

            graphics.pose().popMatrix();

            if (hovered && this.displayTooltip())
            {
                graphics.setTooltipForNextFrame(font, item.getDefaultInstance(), mouseX, mouseY);
            }
        }

        //render result item
        int resultX = startX + (int)(82 * this.scale());
        int resultY = startY + (int)(19 * this.scale());

        boolean hovered = mouseX >= resultX && mouseX <= resultX + slotSize && mouseY >= resultY && mouseY <= resultY + slotSize;

        graphics.pose().pushMatrix();

        graphics.pose().translate(resultX, resultY);
        graphics.pose().scale(this.scale(), this.scale());

        graphics.fakeItem(resultItem.getDefaultInstance(), 0, 0);

        graphics.pose().popMatrix();

        if (hovered && this.displayTooltip()) {graphics.setTooltipForNextFrame(font, resultItem.getDefaultInstance(), mouseX, mouseY);}
    }

    public static class Ingredients
    {
        public final ArrayList<Supplier<Item>> ingredientList;

        public Ingredients(Supplier<Item> slot0, Supplier<Item> slot1, Supplier<Item> slot2, Supplier<Item> slot3, Supplier<Item> slot4, Supplier<Item> slot5, Supplier<Item> slot6, Supplier<Item> slot7, Supplier<Item> slot8)
        {
            ingredientList = new ArrayList<>();
            ingredientList.add(slot0);
            ingredientList.add(slot1);
            ingredientList.add(slot2);
            ingredientList.add(slot3);
            ingredientList.add(slot4);
            ingredientList.add(slot5);
            ingredientList.add(slot6);
            ingredientList.add(slot7);
            ingredientList.add(slot8);
        }

        public ArrayList<Supplier<Item>> get() {return this.ingredientList;}
    }
}
