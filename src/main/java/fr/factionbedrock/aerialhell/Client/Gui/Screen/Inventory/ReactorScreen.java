package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ReactorScreen extends HandledScreen<ReactorMenu>
{
    private static final Identifier TEXTURE =  AerialHell.id("textures/gui/container/reactor.png");

    public ReactorScreen(ReactorMenu menu, PlayerInventory playerInventory, Text title)
    {
        super(menu, playerInventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    @Override protected void drawBackground(@NotNull DrawContext context, float delta, int mouseX, int mouseY)
    {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        renderInGameBackground(context);
        context.drawTexture(RenderLayer::getGuiTextured, TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
    }

    @Override public void render(@NotNull DrawContext context, int pMouseX, int pMouseY, float pPartialTick)
    {
        super.render(context, pMouseX, pMouseY, pPartialTick);
        drawMouseoverTooltip(context, pMouseX, pMouseY);
    }
}
