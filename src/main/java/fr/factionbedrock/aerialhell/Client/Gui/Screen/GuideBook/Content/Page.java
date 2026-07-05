package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content.RecipeDisplay.*;
import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

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

    public Page addSimpleText(int startLineIndex, int lastLineIndex, int lineWidth, Alignment alignment, String text)
    {
        this.pageElements.add(new ParagraphFromSimpleText(startLineIndex, lastLineIndex, 0, lineWidth, alignment, 0xFF7A5C3A, text));
        return this;
    }

    public Page addParagraph(int startLineIndex, int lastLineIndex, int lineStartOffset, int lineWidth, Alignment alignment, String paragraphName)
    {
        this.pageElements.add(new ParagraphFromKey(startLineIndex, lastLineIndex, lineStartOffset, lineWidth, alignment, 0xFF7A5C3A, "aerialhell.guide_book."+ this.pageName +"."+paragraphName));
        return this;
    }

    public Page addParagraph(int startLineIndex, int lastLineIndex, int lineWidth, Alignment alignment, String paragraphName)
    {
        this.pageElements.add(new ParagraphFromKey(startLineIndex, lastLineIndex, 0, lineWidth, alignment, 0xFF7A5C3A, "aerialhell.guide_book."+ this.pageName +"."+paragraphName));
        return this;
    }

    public Page addParagraph(int startLineIndex, int lastLineIndex, int lineWidth, Alignment alignment, int color, String paragraphName)
    {
        this.pageElements.add(new ParagraphFromKey(startLineIndex, lastLineIndex, 0, lineWidth, alignment, color, "aerialhell.guide_book."+ this.pageName +"."+paragraphName));
        return this;
    }

    public Page addItemTexture(ElementPositionInfo positionInfo, float scale, Supplier<Item> item, boolean displayTooltip) {return this.addItemStackTexture(positionInfo, scale, () -> item.get().getDefaultInstance(), displayTooltip);}
    public Page addItemStackTexture(ElementPositionInfo positionInfo, float scale, Supplier<ItemStack> itemstack, boolean displayTooltip)
    {
        this.pageElements.add(new ItemDisplay(positionInfo, scale, itemstack, displayTooltip));
        return this;
    }

    public Page addTextureDisplay(ElementPositionInfo positionInfo, float scale, String path, float u, float v, int width, int height, int textureWidth, int textureHeight, String tooltipKey) {return this.addTextureDisplay(positionInfo, scale, new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/"+path+".png"), u, v, width, height, textureWidth, textureHeight), tooltipKey);}
    public Page addTextureDisplay(ElementPositionInfo positionInfo, float scale, String path, int width, int height) {return this.addTextureDisplay(positionInfo, scale, path, width, height, "");}
    public Page addTextureDisplay(ElementPositionInfo positionInfo, float scale, String path, int width, int height, String tooltipKey) {return this.addTextureDisplay(positionInfo, scale, new TextureInfo(Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/"+path+".png"), width, height), tooltipKey);}
    public Page addTextureDisplay(ElementPositionInfo positionInfo, float scale, TextureInfo textureInfo) {return this.addTextureDisplay(positionInfo, scale, textureInfo, "");}
    public Page addTextureDisplay(ElementPositionInfo positionInfo, float scale, TextureInfo textureInfo, String tooltipKey)
    {
        this.pageElements.add(new TextureDisplay(positionInfo, scale, textureInfo, tooltipKey));
        return this;
    }

    public Page addCraftingRecipeDisplay(int lineIndex, Alignment alignment, float scale, CraftingRecipeDisplay.Ingredients ingredients, Supplier<ItemStack> result, boolean displayTooltip)
    {
        this.pageElements.add(new CraftingRecipeDisplay(lineIndex, alignment, scale, ingredients, result, displayTooltip));
        return this;
    }

    public Page addCraftingTableRecipeDisplay(int lineIndex, Alignment alignment, float scale, CraftingTableRecipeDisplay.Ingredients ingredients, Supplier<ItemStack> result, boolean displayTooltip)
    {
        this.pageElements.add(new CraftingTableRecipeDisplay(lineIndex, alignment, scale, ingredients, result, displayTooltip));
        return this;
    }

    public Page addSingleIngredientCraftingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<Item> ingredient, Supplier<ItemStack> result, boolean displayTooltip)
    {
        this.pageElements.add(new SingleIngredientCraftingRecipeDisplay(lineIndex, alignment, scale, ingredient, result, displayTooltip));
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
        this.pageElements.add(new SmeltingRecipeDisplay(lineIndex, alignment, scale, ingredient, result, () -> Items.CHARCOAL, displayTooltip));
        return this;
    }

    public Page addSmeltingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<Item> ingredient, Supplier<Item> result, Supplier<Item> fuel, boolean displayTooltip)
    {
        this.pageElements.add(new SmeltingRecipeDisplay(lineIndex, alignment, scale, ingredient, result, fuel, displayTooltip));
        return this;
    }

    public Page addBrewingRecipeDisplay(int lineIndex, Alignment alignment, float scale, Supplier<ItemStack> basePotion, Supplier<Item> ingredient, Supplier<ItemStack> resultPotion, boolean displayTooltip)
    {
        this.pageElements.add(new BrewingRecipeDisplay(lineIndex, alignment, scale, basePotion, () -> ingredient.get().getDefaultInstance(), resultPotion, displayTooltip));
        return this;
    }
}