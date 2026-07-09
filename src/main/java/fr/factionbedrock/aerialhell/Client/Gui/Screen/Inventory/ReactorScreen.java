package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class ReactorScreen extends AbstractContainerScreen<ReactorMenu>
{
    private static final ResourceLocation TEXTURE =  AerialHell.id("textures/gui/container/reactor.png");

    public ReactorScreen(ReactorMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override protected void renderBg(@NotNull GuiGraphics context, float delta, int mouseX, int mouseY)
    {
        renderTransparentBackground(context);
        context.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override public void render(@NotNull GuiGraphics context, int pMouseX, int pMouseY, float pPartialTick)
    {
        super.render(context, pMouseX, pMouseY, pPartialTick);
        renderTooltip(context, pMouseX, pMouseY);
    }
}
