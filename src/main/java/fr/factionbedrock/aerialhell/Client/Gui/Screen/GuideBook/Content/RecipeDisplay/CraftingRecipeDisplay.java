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
        int slotSize = (int)(16 * this.scale());
        int slotSpacing = (int)(17 * this.scale());

        int startX = switch (this.alignment())
        {
            case LEFT -> line.startX();
            case CENTER -> line.centerX() - recipeWidth / 2;
            case RIGHT -> line.rightX() - recipeWidth;
        };

        int startY = line.startY();

        //background (2x2 grid)
        graphics.pose().pushMatrix();

        graphics.pose().translate(startX, startY);
        graphics.pose().scale(this.scale(), this.scale());

        graphics.blit(RenderPipelines.GUI_TEXTURED, CRAFTING_GRID_TEXTURE.texture(), 0, 0, CRAFTING_GRID_TEXTURE.u(), CRAFTING_GRID_TEXTURE.v(), CRAFTING_GRID_TEXTURE.width(), CRAFTING_GRID_TEXTURE.height(), CRAFTING_GRID_TEXTURE.textureWidth(), CRAFTING_GRID_TEXTURE.textureHeight());

        graphics.pose().popMatrix();

        //ingredients
        for (int i = 0; i < ingredients.get().size(); i++)
        {
            Item item = ingredients.get().get(i).get();

            if (item == null) {continue;}

            int row = i / 2;
            int col = i % 2;

            int itemX = startX + (int)(2 * this.scale()) + col * slotSpacing;
            int itemY = startY + (int)(2 * this.scale()) + row * slotSpacing;

            boolean hovered = mouseX >= itemX && mouseX <= itemX + slotSize && mouseY >= itemY && mouseY <= itemY + slotSize;

            graphics.pose().pushMatrix();

            graphics.pose().translate(itemX, itemY);
            graphics.pose().scale(this.scale(), this.scale());

            graphics.fakeItem(item.getDefaultInstance(), 0, 0);

            graphics.pose().popMatrix();

            if (hovered && this.displayTooltip()) {graphics.setTooltipForNextFrame(font, item.getDefaultInstance(), mouseX, mouseY);}
        }

        // result
        int resultX = startX + (int)(64 * this.scale());
        int resultY = startY + (int)(10 * this.scale());

        boolean hovered = mouseX >= resultX && mouseX <= resultX + slotSize && mouseY >= resultY && mouseY <= resultY + slotSize;

        graphics.pose().pushMatrix();

        graphics.pose().translate(resultX, resultY);
        graphics.pose().scale(this.scale(), this.scale());

        graphics.fakeItem(resultItemStack, 0, 0);
        if (resultItemStack.getCount() > 1)
        {
            //copy of net.minecraft.client.gui.GuiGraphicsExtractor method itemCount(..)
            String amount = String.valueOf(resultItemStack.getCount());
            graphics.text(font, amount, 17 - font.width(amount), 9, 0xFF7A5C3A, false);
        }

        graphics.pose().popMatrix();

        if (hovered && this.displayTooltip()) {graphics.setTooltipForNextFrame(font, resultItemStack, mouseX, mouseY);}
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
