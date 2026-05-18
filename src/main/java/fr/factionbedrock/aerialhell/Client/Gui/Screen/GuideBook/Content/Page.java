package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.CraftingTableRecipeDisplay;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.FreezingRecipeDisplay;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.OscillatingRecipeDisplay;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.SmeltingRecipeDisplay;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Page
{
    private final String pageName;
    private final int pageIndex;
    private final TextureInfo backgroundTexture;
    List<PageElement> pageElements;

    public Page(String pageName, TextureInfo backgroundTexture, int pageIndex)
    {
        this.pageName = pageName;
        this.backgroundTexture = backgroundTexture;
        this.pageIndex = pageIndex;
        this.pageElements = new ArrayList<>();
    }

    public void render(Font font, GuiGraphicsExtractor graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        graphics.blit(RenderPipelines.GUI_TEXTURED, this.backgroundTexture.texture(), bookLeft, bookTop, this.backgroundTexture.u(), this.backgroundTexture.v(), this.backgroundTexture.width(), this.backgroundTexture.height(), this.backgroundTexture.textureWidth(), this.backgroundTexture.textureHeight());

        for (PageElement pageElement : this.pageElements)
        {
            pageElement.render(font, graphics, scale, lines, bookLeft, bookTop, mouseX, mouseY);
        }
    }

    public int pageIndex() {return this.pageIndex;}

    public Page addParagraph(int startLineIndex, int lastLineIndex, int lineWidth, Alignment alignment, String paragraphName)
    {
        this.pageElements.add(new Paragraph(startLineIndex, lastLineIndex, lineWidth, alignment, 0xFF7A5C3A, "aerialhell.guide_book."+ this.pageName +"."+paragraphName));
        return this;
    }

    public Page addParagraph(int startLineIndex, int lastLineIndex, int lineWidth, Alignment alignment, int color, String paragraphName)
    {
        this.pageElements.add(new Paragraph(startLineIndex, lastLineIndex, lineWidth, alignment, color, "aerialhell.guide_book."+ this.pageName +"."+paragraphName));
        return this;
    }

    public Page addItemTexture(int lineIndex, Alignment alignment, float scale, Supplier<Item> item, boolean displayTooltip)
    {
        this.pageElements.add(new ItemDisplay(lineIndex, alignment, scale, item, displayTooltip));
        return this;
    }

    public Page addTextureDisplay(int lineIndex, Alignment alignment, float scale, String path, int width, int height)
    {
        return this.addTextureDisplay(lineIndex, alignment, scale, path, width, height, "");
    }

    public Page addTextureDisplay(int lineIndex, Alignment alignment, float scale, String path, int width, int height, String tooltipKey)
    {
        this.pageElements.add(new TextureDisplay(lineIndex, alignment, scale, new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/"+path+".png"), width, height), tooltipKey));
        return this;
    }

    public Page addTextureDisplay(int lineIndex, Alignment alignment, float scale, String path, float u, float v, int width, int height, int textureWidth, int textureHeight, String tooltipKey)
    {
        this.pageElements.add(new TextureDisplay(lineIndex, alignment, scale, new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/"+path+".png"), u, v, width, height, textureWidth, textureHeight), tooltipKey));
        return this;
    }

    public Page addCraftingTableRecipeDisplay(int lineIndex, Alignment alignment, float scale, CraftingTableRecipeDisplay.Ingredients ingredients, Supplier<Item> result, boolean displayTooltip)
    {
        this.pageElements.add(new CraftingTableRecipeDisplay(lineIndex, alignment, scale, ingredients, result, displayTooltip));
        return this;
    }

    public Page addOscillatingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<Item> ingredient, Supplier<Item> result, boolean displayTooltip)
    {
        this.pageElements.add(new OscillatingRecipeDisplay(lineIndex, alignment, scale, ingredient, result, displayTooltip));
        return this;
    }

    public Page addFreezingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<Item> ingredient, Supplier<Item> result, boolean displayTooltip)
    {
        this.pageElements.add(new FreezingRecipeDisplay(lineIndex, alignment, scale, ingredient, result, displayTooltip));
        return this;
    }

    public Page addSmeltingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<Item> ingredient, Supplier<Item> result, boolean displayTooltip)
    {
        this.pageElements.add(new SmeltingRecipeDisplay(lineIndex, alignment, scale, ingredient, result, displayTooltip));
        return this;
    }
}