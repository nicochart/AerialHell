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

import java.util.List;
import java.util.function.Supplier;

public class SmeltingLikeRecipeDisplay implements PageElement
{
    private static final TextureInfo FURNACE_LIKE_DISPLAY = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/recipe/furnace_like_display.png"), 66, 54);

    private final int lineIndex;
    private final Alignment alignment;
    private final float scale;
    private final Supplier<Item> ingredient;
    private final Supplier<Item> result;
    private final Supplier<Item> fuel;
    private final TextureInfo stationTexture;
    private final boolean displayTooltip;

    public SmeltingLikeRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<Item> ingredient, Supplier<Item> result, Supplier<Item> fuel, TextureInfo stationTexture, boolean displayTooltip)
    {
        this.lineIndex = lineIndex;
        this.alignment = alignment;
        this.scale = scale;
        this.ingredient = ingredient;
        this.result = result;
        this.fuel = fuel;
        this.stationTexture = stationTexture;
        this.displayTooltip = displayTooltip;
    }

    @Override public void render(Font font, GuiGraphicsExtractor graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        Item ingredientItem = this.ingredient.get();
        Item resultItem = this.result.get();
        Item fuelItem = this.fuel.get();

        if (ingredientItem == null || resultItem == null) {return;}

        Line line = lines.get(this.lineIndex);

        int displayWidth = (int)(FURNACE_LIKE_DISPLAY.width() * this.scale);
        int slotSize = (int)(16 * this.scale);

        int startX = switch (this.alignment)
        {
            case LEFT -> line.startX();
            case CENTER -> line.centerX() - displayWidth / 2;
            case RIGHT -> line.rightX() - displayWidth;
        };

        int startY = line.startY();

        //background
        graphics.pose().pushMatrix();

        graphics.pose().translate(startX, startY);
        graphics.pose().scale(this.scale, this.scale);

        graphics.blit(RenderPipelines.GUI_TEXTURED, FURNACE_LIKE_DISPLAY.texture(), 0, 0, FURNACE_LIKE_DISPLAY.u(), FURNACE_LIKE_DISPLAY.v(), FURNACE_LIKE_DISPLAY.width(), FURNACE_LIKE_DISPLAY.height(), FURNACE_LIKE_DISPLAY.textureWidth(), FURNACE_LIKE_DISPLAY.textureHeight());

        graphics.pose().popMatrix();

        //progress texture
        graphics.pose().pushMatrix();

        graphics.pose().translate(startX + (int)(25 * this.scale), startY + (int)(2 * this.scale));

        graphics.pose().scale(this.scale, this.scale);

        graphics.blit(RenderPipelines.GUI_TEXTURED, this.stationTexture.texture(), 0, 0, this.stationTexture.u(), this.stationTexture.v(), this.stationTexture.width(), this.stationTexture.height(), this.stationTexture.textureWidth(), this.stationTexture.textureHeight());

        graphics.pose().popMatrix();

        //ingredient
        renderItem(graphics, font, ingredientItem, startX + (int)(2 * this.scale), startY + (int)(19 * this.scale), slotSize, mouseX, mouseY);

        //result
        renderItem(graphics, font, resultItem, startX + (int)(48 * this.scale), startY + (int)(19 * this.scale), slotSize, mouseX, mouseY);

        //fuel
        if (fuelItem != null)
        {
            renderItem(graphics, font, fuelItem, startX + (int)(25 * this.scale), startY + (int)(36 * this.scale), slotSize, mouseX, mouseY);
        }
    }

    private void renderItem(GuiGraphicsExtractor graphics, Font font, Item item, int x, int y, int slotSize, int mouseX, int mouseY)
    {
        boolean hovered = mouseX >= x && mouseX <= x + slotSize && mouseY >= y && mouseY <= y + slotSize;

        graphics.pose().pushMatrix();

        graphics.pose().translate(x, y);
        graphics.pose().scale(this.scale, this.scale);

        graphics.fakeItem(item.getDefaultInstance(), 0, 0);

        graphics.pose().popMatrix();

        if (hovered && this.displayTooltip) {graphics.setTooltipForNextFrame(font, item.getDefaultInstance(), mouseX, mouseY);}
    }
}
