package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ToFloatFunction;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class Tab
{
    private final String key;
    private final Identifier texture;
    private final Supplier<Integer> bookLeft;
    private final Supplier<Integer> bookTop;
    private final ToIntFunction<Boolean> width; //tab x dimension. the boolean is "isPulledOut"
    private final ToIntFunction<Boolean> height; //tab y dimension. the boolean is "isPulledOut"
    private final ToIntFunction<Boolean> relativeXPos; //relative to book left. the boolean is "isPulledOut"
    private final ToIntFunction<Boolean> relativeYPos; //relative to book top. the boolean is "isPulledOut"
    private final ToFloatFunction<Boolean> blitU; //blit texture start on x coordinate (u). the boolean is "isPulledOut"
    private final ToFloatFunction<Boolean> blitV; //blit texture start on x coordinate (u). the boolean is "isPulledOut"
    private final int startPageIndex;
    private final int endPageIndex;

    public Tab(String name, ToIntFunction<Boolean> width, ToIntFunction<Boolean> height, Supplier<Integer> bookLeft, Supplier<Integer> bookTop, ToIntFunction<Boolean> relativeXPos, ToIntFunction<Boolean> relativeYPos, ToFloatFunction<Boolean> blitU, ToFloatFunction<Boolean> blitV, int startPageIndex, int endPageIndex)
    {
        this.key = "aerialhell.guide_book.tab." + name;
        this.texture = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/guide_book/tab/"+name+".png");
        this.bookLeft = bookLeft;
        this.bookTop = bookTop;
        this.width = width;
        this.height = height;
        this.relativeXPos = relativeXPos;
        this.relativeYPos = relativeYPos;
        this.blitU = blitU;
        this.blitV = blitV;
        this.startPageIndex = startPageIndex;
        this.endPageIndex = endPageIndex;
    }

    public int startPageIndex() {return this.startPageIndex;}

    private int[] getBasePos(int bookLeft, int bookTop)
    {
        return new int[]{bookLeft + this.relativeXPos.applyAsInt(false), bookTop + this.relativeYPos.applyAsInt(false)};
    }

    private int[] getRenderPos(boolean isPulledOut)
    {
        return new int[]{this.bookLeft.get() + this.relativeXPos.applyAsInt(isPulledOut), this.bookTop.get() + this.relativeYPos.applyAsInt(isPulledOut)};
    }

    public boolean isHovered(double mouseX, double mouseY)
    {
        int[] pos = this.getBasePos(this.bookLeft.get(), this.bookTop.get());
        return mouseX >= pos[0] && mouseX <= pos[0] + this.width.applyAsInt(false) && mouseY >= pos[1] && mouseY <= pos[1] + this.height.applyAsInt(false);
    }

    private boolean isActive(int currentPageIndex)
    {
        return currentPageIndex >= this.startPageIndex && currentPageIndex <= this.endPageIndex;
    }

    public void render(GuiGraphics graphics, Font font, int mouseX, int mouseY, int currentPageIndex)
    {
        boolean isHovered = this.isHovered(mouseX, mouseY);
        boolean isPulledOut = isHovered || this.isActive(currentPageIndex);
        int[] pos = this.getRenderPos(isPulledOut);
        int xDraw = pos[0];
        int yDraw = pos[1];
        int tabWidth = this.width.applyAsInt(isPulledOut);
        int tabHeight = this.height.applyAsInt(isPulledOut);

        graphics.blit(RenderPipelines.GUI_TEXTURED, this.texture, xDraw, yDraw, this.blitU.applyAsFloat(isPulledOut), this.blitV.applyAsFloat(isPulledOut), tabWidth, tabHeight, this.width.applyAsInt(true), this.height.applyAsInt(true));

        //border
        int borderColor = isPulledOut ? 0xFFC8A24A : 0xFF1A1A1A;
        graphics.fill(xDraw, yDraw, xDraw + tabWidth, yDraw + 1, borderColor);
        graphics.fill(xDraw, yDraw + tabHeight - 1, xDraw + tabWidth, yDraw + tabHeight, borderColor);
        graphics.fill(xDraw - 1, yDraw, xDraw, yDraw + tabHeight, borderColor);
        graphics.fill(xDraw + tabWidth, yDraw, xDraw + tabWidth + 1, yDraw + tabHeight, borderColor);

        //hover text
        if (isHovered)
        {
            graphics.setTooltipForNextFrame(font, Component.translatable(this.key), mouseX, mouseY);
        }
    }
}