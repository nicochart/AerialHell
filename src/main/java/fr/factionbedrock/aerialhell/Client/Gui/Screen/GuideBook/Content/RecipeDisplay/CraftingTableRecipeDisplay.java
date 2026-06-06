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
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record CraftingTableRecipeDisplay(int lineIndex, Alignment alignment, float scale, Ingredients ingredients, Supplier<ItemStack> result, boolean displayTooltip) implements PageElement
{
    private static final TextureInfo CRAFTING_TABLE_GRID_TEXTURE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/recipe/crafting_table_grid_display.png"), 100, 54);

    @Override public void render(Font font, GuiGraphicsExtractor graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        ItemStack resultItemStack = this.result.get();
        if (resultItemStack == null) {return;}

        Line line = lines.get(this.lineIndex());

        int recipeWidth = (int)(CRAFTING_TABLE_GRID_TEXTURE.width() * this.scale());
        int slotSize = (int)(16 * this.scale());

        int startX = switch (this.alignment())
        {
            case LEFT -> line.startX();
            case CENTER -> line.centerX() - recipeWidth / 2;
            case RIGHT -> line.rightX() - recipeWidth;
        };

        int startY = line.startY();

        graphics.pose().pushMatrix();
        graphics.pose().translate(startX, startY);
        graphics.pose().scale(this.scale(), this.scale());

        //rendercrafting grid background
        graphics.blit(RenderPipelines.GUI_TEXTURED, CRAFTING_TABLE_GRID_TEXTURE.texture(), 0, 0, CRAFTING_TABLE_GRID_TEXTURE.u(), CRAFTING_TABLE_GRID_TEXTURE.v(), CRAFTING_TABLE_GRID_TEXTURE.width(), CRAFTING_TABLE_GRID_TEXTURE.height(), CRAFTING_TABLE_GRID_TEXTURE.textureWidth(), CRAFTING_TABLE_GRID_TEXTURE.textureHeight());

        //render ingredients
        for (int i = 0; i < ingredients.get().size(); i++)
        {
            Item item = ingredients.get().get(i).get();
            if (item == null) {continue;}

            int row = i / 3;
            int col = i % 3;

            //1 px outer margin + 1 px separator before first slot
            //17 is slot size + 1 pixel separator
            int itemLocalX = 2 + col * 17;
            int itemLocalY = 2 + row * 17;

            graphics.item(item.getDefaultInstance(), itemLocalX, itemLocalY);

            //hover
            int itemScreenX = startX + (int)(itemLocalX * this.scale());
            int itemScreenY = startY + (int)(itemLocalY * this.scale());
            boolean hovered = mouseX >= itemScreenX && mouseX <= itemScreenX + slotSize && mouseY >= itemScreenY && mouseY <= itemScreenY + slotSize;

            if (hovered && this.displayTooltip())
            {
                graphics.setTooltipForNextFrame(font, item.getDefaultInstance(), mouseX, mouseY);
            }
        }

        //render result item
        int resultLocalX = 82;
        int resultLocalY = 19;

        graphics.item(resultItemStack, resultLocalX, resultLocalY);
        if (resultItemStack.getCount() > 1)
        {
            String amount = String.valueOf(resultItemStack.getCount());
            graphics.text(font, amount, resultLocalX + 17 - font.width(amount), resultLocalY + 9, 0xFF7A5C3A, false);
        }

        //hover
        int resultScreenX = startX + (int)(resultLocalX * this.scale());
        int resultScreenY = startY + (int)(resultLocalY * this.scale());
        boolean hovered = mouseX >= resultScreenX && mouseX <= resultScreenX + slotSize && mouseY >= resultScreenY && mouseY <= resultScreenY + slotSize;

        if (hovered && this.displayTooltip()) {graphics.setTooltipForNextFrame(font, resultItemStack, mouseX, mouseY);}

        graphics.pose().popMatrix();
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
