package fr.factionbedrock.aerialhell.Client.Gui.Screen.GuideBook.Content;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Supplier;

public record ItemDisplay(ElementPositionInfo positionInfo, float scale, Supplier<ItemStack> itemStack, boolean displayTooltip) implements PageElement
{
    @Override public void render(Font font, GuiGraphics graphics, float scale, List<Line> lines, int bookLeft, int bookTop, int mouseX, int mouseY)
    {
        ItemStack itemStack = this.itemStack.get();
        if (itemStack == null) {return;}

        Line line = lines.get(this.positionInfo.lineIndex());

        int itemSize = (int)(16 * this.scale());

        int startX = switch (this.positionInfo.alignment())
        {
            case LEFT -> line.startX() + this.positionInfo.horizontalOffset();
            case CENTER -> line.centerX() - itemSize / 2 + this.positionInfo.horizontalOffset();
            case RIGHT -> line.rightX() - itemSize + this.positionInfo.horizontalOffset();
        };

        int lineHeight = line.lineHeight();
        int startY = line.startY() + this.positionInfo.verticalOffset() + (this.positionInfo.centerVerticallyOnLine() ? - 1 + (lineHeight - itemSize) / 2 : 0);

        boolean hovered = mouseX >= startX && mouseX <= startX + itemSize && mouseY >= startY && mouseY <= startY + itemSize;

        graphics.pose().pushMatrix();

        graphics.pose().translate(startX, startY);
        graphics.pose().scale(this.scale(), this.scale());

        graphics.renderFakeItem(itemStack, 0, 0);

        graphics.pose().popMatrix();

        if (hovered && this.displayTooltip()) {graphics.setTooltipForNextFrame(font, itemStack, mouseX, mouseY);}
    }
}
