package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Supplier;

public record ItemDisplay(int lineIndex, Alignment alignment, int xOffset, boolean centerVerticallyOnLine, float scale, Supplier<ItemStack> itemStack, boolean displayTooltip) implements PageElement
{
    @Override public void render(Font font, GuiGraphicsExtractor graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        ItemStack itemStack = this.itemStack.get();
        if (itemStack == null) {return;}

        Line line = lines.get(this.lineIndex());

        int itemSize = (int)(16 * this.scale());

        int startX = switch (this.alignment())
        {
            case LEFT -> line.startX() + this.xOffset;
            case CENTER -> line.centerX() - itemSize / 2 + this.xOffset;
            case RIGHT -> line.rightX() - itemSize + this.xOffset;
        };

        int startY = line.startY() - (this.centerVerticallyOnLine ? itemSize / 3 : 0);

        if (this.centerVerticallyOnLine)
        {
            int lineHeight = 16;
            //startY = line.startY() + (int)((lineHeight - itemSize) / 2.0f);
            //startY+=20;
        }

        boolean hovered = mouseX >= startX && mouseX <= startX + itemSize && mouseY >= startY && mouseY <= startY + itemSize;

        graphics.pose().pushMatrix();

        graphics.pose().translate(startX, startY);
        graphics.pose().scale(this.scale(), this.scale());

        graphics.fakeItem(itemStack, 0, 0);

        graphics.pose().popMatrix();

        if (hovered && this.displayTooltip()) {graphics.setTooltipForNextFrame(font, itemStack, mouseX, mouseY);}
    }
}
