package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

import net.minecraft.client.gui.Font;

public record Line(int index, int lineHeight, int startX, int centerX, int rightX, int startY)
{
    public int centerX(String textToCenter, Font font, float textScale) {return this.centerX - (int) (font.width(textToCenter) * textScale / 2.0F);}

    public int rightX(String text, Font font, float textScale) {return this.rightX - (int) (font.width(text) * textScale);}
}
