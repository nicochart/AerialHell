package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

import fr.factionbedrock.aerialhell.Client.Util.TextureInfo;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.List;

public record TextureDisplay(int lineIndex, Alignment alignment, float scale, TextureInfo textureInfo, String key) implements PageElement
{
    @Override public void render(Font font, GuiGraphicsExtractor graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        Line line = lines.get(this.lineIndex());

        int scaledWidth = (int)(this.textureInfo.width() * this.scale());
        int scaledHeight = (int)(this.textureInfo.height() * this.scale());

        int startX = switch (this.alignment())
        {
            case LEFT -> line.startX();
            case CENTER -> line.centerX() - scaledWidth / 2;
            case RIGHT -> line.rightX() - scaledWidth;
        };

        int startY = line.startY();

        graphics.pose().pushMatrix();

        graphics.pose().translate(startX, startY);
        graphics.pose().scale(this.scale(), this.scale());

        graphics.blit(RenderPipelines.GUI_TEXTURED, this.textureInfo.texture(), 0, 0, this.textureInfo.u(), this.textureInfo.v(), this.textureInfo.width(), this.textureInfo.height(), this.textureInfo.textureWidth(), this.textureInfo.textureHeight());

        graphics.pose().popMatrix();

        boolean isHovered = mouseX >= startX && mouseX <= startX + scaledWidth && mouseY >= startY && mouseY <= startY + scaledHeight;

        if (isHovered && !this.key.isEmpty())
        {
            graphics.setTooltipForNextFrame(font, Component.translatable(this.key), mouseX, mouseY);
        }
    }
}
