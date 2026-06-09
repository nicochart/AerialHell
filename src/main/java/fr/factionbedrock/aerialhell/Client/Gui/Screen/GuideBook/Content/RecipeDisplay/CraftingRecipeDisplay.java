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

public record CraftingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Ingredients ingredients, Supplier<ItemStack> result, boolean displayTooltip) implements PageElement
{
    private static final TextureInfo CRAFTING_GRID_TEXTURE = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/recipe/crafting_grid_display.png"), 82, 37);

    @Override public void render(Font font, GuiGraphicsExtractor graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        ItemStack resultItemStack = this.result.get();
        if (resultItemStack == null) {return;}

        Line line = lines.get(this.lineIndex());

        int recipeWidth = (int)(CRAFTING_GRID_TEXTURE.width() * this.scale());

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

        //background (2x2 grid)
        graphics.blit(RenderPipelines.GUI_TEXTURED, CRAFTING_GRID_TEXTURE.texture(), 0, 0, CRAFTING_GRID_TEXTURE.u(), CRAFTING_GRID_TEXTURE.v(), CRAFTING_GRID_TEXTURE.width(), CRAFTING_GRID_TEXTURE.height(), CRAFTING_GRID_TEXTURE.textureWidth(), CRAFTING_GRID_TEXTURE.textureHeight());

        //render ingredients
        //17 = 16 (item size) + 1 separator
        for (int i = 0; i < ingredients.get().size(); i++)
        {
            Item item = ingredients.get().get(i).get();
            if (item == null) {continue;}

            int row = i / 2;
            int col = i % 2;

            int itemLocalX = 2 + col * 17;
            int itemLocalY = 2 + row * 17;

            graphics.item(item.getDefaultInstance(), itemLocalX, itemLocalY);

            int itemScreenX = startX + (int)(itemLocalX * this.scale());
            int itemScreenY = startY + (int)(itemLocalY * this.scale());
            int slotSize = (int)(16 * this.scale());
            boolean hovered = mouseX >= itemScreenX && mouseX <= itemScreenX + slotSize && mouseY >= itemScreenY && mouseY <= itemScreenY + slotSize;

            //hover
            if (hovered && this.displayTooltip())
            {
                graphics.setTooltipForNextFrame(font, item.getDefaultInstance(), mouseX, mouseY);
            }
        }

        // render result item
        int resultLocalX = 64;
        int resultLocalY = 10;

        graphics.item(resultItemStack, resultLocalX, resultLocalY);
        if (resultItemStack.getCount() > 1)
        {
            String amount = String.valueOf(resultItemStack.getCount());
            graphics.text(font, amount, resultLocalX + 17 - font.width(amount), resultLocalY + 9, 0xFF7A5C3A, false);
        }

        //hover
        int resultScreenX = startX + (int)(resultLocalX * this.scale());
        int resultScreenY = startY + (int)(resultLocalY * this.scale());
        int slotSize = (int)(16 * this.scale());
        boolean hovered = mouseX >= resultScreenX && mouseX <= resultScreenX + slotSize && mouseY >= resultScreenY && mouseY <= resultScreenY + slotSize;
        if (hovered && this.displayTooltip()) {graphics.setTooltipForNextFrame(font, resultItemStack, mouseX, mouseY);}

        graphics.pose().popMatrix();
    }

    public static class Ingredients
    {
        public final ArrayList<Supplier<Item>> ingredientList;

        public Ingredients(Supplier<Item> slot0, Supplier<Item> slot1, Supplier<Item> slot2, Supplier<Item> slot3)
        {
            ingredientList = new ArrayList<>();
            ingredientList.add(slot0);
            ingredientList.add(slot1);
            ingredientList.add(slot2);
            ingredientList.add(slot3);
        }

        public ArrayList<Supplier<Item>> get() {return this.ingredientList;}
    }
}
