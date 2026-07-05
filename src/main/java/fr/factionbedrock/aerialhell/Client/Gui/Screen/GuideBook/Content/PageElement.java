package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.List;

public interface PageElement
{
    void render(Font font, GuiGraphicsExtractor graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY);
}
