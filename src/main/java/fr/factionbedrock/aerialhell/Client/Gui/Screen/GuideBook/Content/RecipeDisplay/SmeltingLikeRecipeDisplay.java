package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.Alignment;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.Line;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.PageElement;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Supplier;

public class SmeltingLikeRecipeDisplay implements PageElement
{
    private static final TextureInfo FURNACE_LIKE_DISPLAY = new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/recipe/furnace_like_display.png"), 66, 54);

    private final int lineIndex;
    private final Alignment alignment;
    private final float scale;
    private final Supplier<ItemStack> ingredient;
    private final Supplier<ItemStack> result;
    private final Supplier<ItemStack> fuel;
    private final StationInfo stationInfo;
    private final boolean displayTooltip;

    public SmeltingLikeRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<ItemStack> ingredient, Supplier<ItemStack> result, Supplier<ItemStack> fuel, StationInfo stationInfo, boolean displayTooltip)
    {
        this.lineIndex = lineIndex;
        this.alignment = alignment;
        this.scale = scale;
        this.ingredient = ingredient;
        this.result = result;
        this.fuel = fuel;
        this.stationInfo = stationInfo;
        this.displayTooltip = displayTooltip;
    }

    @Override public void render(Font font, GuiGraphics graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        ItemStack ingredientItem = this.ingredient.get();
        ItemStack resultItem = this.result.get();
        ItemStack fuelItem = this.fuel.get();

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
        TextureInfo stationTexture = stationInfo.texture;
        int stationX = startX + (int)(25 * this.scale);
        int stationY = startY + (int)(2 * this.scale);

        int stationWidth = (int)(stationTexture.width() * this.scale);
        int stationHeight = (int)(stationTexture.height() * this.scale);

        graphics.pose().pushMatrix();

        graphics.pose().translate(stationX, stationY);

        graphics.pose().scale(this.scale, this.scale);

        graphics.blit(RenderPipelines.GUI_TEXTURED, stationTexture.texture(), 0, 0, stationTexture.u(), stationTexture.v(), stationTexture.width(), stationTexture.height(), stationTexture.textureWidth(), stationTexture.textureHeight());

        graphics.pose().popMatrix();

        boolean isHovered = mouseX >= stationX && mouseX <= stationX + stationWidth && mouseY >= stationY && mouseY <= stationY + stationHeight;

        if (isHovered && !this.stationInfo.tooltipKey.isEmpty())
        {
            graphics.setTooltipForNextFrame(font, Component.translatable(this.stationInfo.tooltipKey), mouseX, mouseY);
        }

        //ingredient
        renderItemStack(graphics, font, ingredientItem, startX + (int)(2 * this.scale), startY + (int)(19 * this.scale), slotSize, mouseX, mouseY);

        //result
        renderItemStack(graphics, font, resultItem, startX + (int)(48 * this.scale), startY + (int)(19 * this.scale), slotSize, mouseX, mouseY);

        //fuel
        if (fuelItem != null)
        {
            renderItemStack(graphics, font, fuelItem, startX + (int)(25 * this.scale), startY + (int)(36 * this.scale), slotSize, mouseX, mouseY);
        }
    }

    private void renderItemStack(GuiGraphics graphics, Font font, ItemStack itemStack, int x, int y, int slotSize, int mouseX, int mouseY)
    {
        boolean hovered = mouseX >= x && mouseX <= x + slotSize && mouseY >= y && mouseY <= y + slotSize;

        graphics.pose().pushMatrix();

        graphics.pose().translate(x, y);
        graphics.pose().scale(this.scale, this.scale);

        graphics.renderFakeItem(itemStack, 0, 0);
        if (itemStack.getCount() > 1)
        {
            //copy of net.minecraft.client.gui.GuiGraphics method itemCount(..)
            String amount = String.valueOf(itemStack.getCount());
            graphics.drawString(font, amount, 17 - font.width(amount), 9, 0xFF7A5C3A, false);
        }

        graphics.pose().popMatrix();

        if (hovered && this.displayTooltip) {graphics.setTooltipForNextFrame(font, itemStack, mouseX, mouseY);}
    }

    public record StationInfo(TextureInfo texture, String tooltipKey) {}
}
