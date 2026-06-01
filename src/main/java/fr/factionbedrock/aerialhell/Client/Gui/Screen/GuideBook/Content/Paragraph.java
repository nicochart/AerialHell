package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

import fr.factionbedrock.aerialhell.Client.Util.ClientHelper;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;

import java.util.List;

public record Paragraph(int startLineIndex, int lastLineIndex, int lineWidth, Alignment alignment, int color, String key) implements PageElement
{
    @Override public void render(Font font, GuiGraphicsExtractor graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        String paragraphText = Language.getInstance().getOrDefault(this.key);
        int currentLineIndex = this.startLineIndex;

        List<String> textLines = ClientHelper.wrapTextForBook(paragraphText, font, (int) (this.lineWidth / scale));
        for (int i = 0; i < textLines.size() && currentLineIndex < this.lastLineIndex + 1; i++)
        {
            int startX = switch (this.alignment())
            {
                case LEFT -> lines.get(currentLineIndex).startX();
                case CENTER -> lines.get(currentLineIndex).centerX(textLines.get(i), font, scale);
                case RIGHT -> lines.get(currentLineIndex).rightX(textLines.get(i), font, scale);
            };

            ClientHelper.renderText(font, graphics, Component.literal(textLines.get(i)), startX, lines.get(currentLineIndex).startY(), this.color, scale);
            currentLineIndex++;
        }
    }
}
