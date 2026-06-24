package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.GuideBookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ClientHelper
{
    public static Player getLocalPlayer() {return Minecraft.getInstance().player;}

    public static void openAerialHellGuideBookScreen()
    {
        Minecraft.getInstance().setScreen(new GuideBookScreen());
    }

    public static void renderText(Font font, GuiGraphicsExtractor graphics, Component text, int x, int y, int color, float scale)
    {
        var pose = graphics.pose();
        pose.pushMatrix();
        pose.translate(x, y);
        pose.scale(scale, scale);
        renderText(font, graphics, text, 0, 0, color);
        pose.popMatrix();
    }

    public static void renderText(Font font, GuiGraphicsExtractor graphics, Component text, int x, int y, int color)
    {
        graphics.text(font, text, x, y, color, false);
    }

    public static List<String> wrapTextForBook(String text, Font font, int maxLineWidth)
    {
        List<String> lines = new ArrayList<>();
        for (String paragraph : text.split("\n", -1))
        {
            if (paragraph.isEmpty()) {lines.add(""); continue; }

            String[] words = paragraph.split(" ");
            StringBuilder currentLine = new StringBuilder();
            for (String word : words)
            {
                int wordWidth = getTextWidth(word, font);
                if (wordWidth > maxLineWidth) //word longer than the entire line
                {
                    //if there is already a word with no space at the end in the currentLine (i.e. the current line finishes with anything else than a space
                    if (!currentLine.isEmpty() && currentLine.charAt(currentLine.length() - 1) != ' ')
                    {
                        if (!appendIfFits(currentLine, " ", font, maxLineWidth)) //trying to append a space
                        {
                            lines.add(currentLine.toString());
                            currentLine = new StringBuilder();
                        }
                    }

                    StringBuilder wordChunk = new StringBuilder();

                    int charAdded = 0;
                    for (char character : word.toCharArray())
                    {
                        if (fits(currentLine, wordChunk + String.valueOf(character), font, maxLineWidth)) {charAdded++;}
                        else
                        {
                            if (charAdded > 3) //4 is the minimum chunk that can be added at the end of a line
                            {
                                currentLine.append(wordChunk);
                                lines.add(currentLine.toString());
                                wordChunk = new StringBuilder();
                            }
                            else
                            {
                                lines.add(currentLine.toString());
                            }
                            currentLine = new StringBuilder();
                        }
                        wordChunk.append(character);
                    }
                    currentLine.append(wordChunk);
                }
                else
                {
                    if (!appendWordIfFits(currentLine, word, font, maxLineWidth))
                    {
                        lines.add(currentLine.toString());
                        currentLine = new StringBuilder(word);
                    }
                }
            }
            if (!currentLine.isEmpty()) lines.add(currentLine.toString());
        }
        return lines;
    }

    public static boolean appendWordIfFits(StringBuilder builder, String word, Font font, int maxLineWidth)
    {
        String processedWord = word.replace("\t", "    ");
        return appendIfFits(builder, builder.isEmpty() ? processedWord : " " + processedWord, font, maxLineWidth);
    }

    public static boolean appendIfFits(StringBuilder builder, String toAppend, Font font, int maxLineWidth)
    {
        if (fits(builder, toAppend, font, maxLineWidth))
        {
            builder.append(toAppend);
            return true;
        }
        return false;
    }

    public static boolean fits(StringBuilder builder, String toAppend, Font font, int maxLineWidth)
    {
        return getTextWidth(builder + toAppend, font) <= maxLineWidth;
    }

    public static int getTextWidth(String text, Font font)
    {
        return font.width(text);
    }
}
