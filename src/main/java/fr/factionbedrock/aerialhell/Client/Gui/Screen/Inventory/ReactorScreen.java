package fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class ReactorScreen extends AbstractContainerScreen<ReactorMenu>
{
    private static final Identifier REACTOR_BACKGROUND = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/container/reactor.png");
    private static final Identifier LIGHT_PROGRESS = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/sprites/container/reactor/light_progress.png");
    private static final Identifier SHADOW_PROGRESS = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/sprites/container/reactor/shadow_progress.png");

    //progress zone
    private static final int CENTER_X = 88;
    private static final int CENTER_Y = 45;
    private static final int WIDTH = 140;
    private static final int HEIGHT = 50;
    private static final int HALF_WIDTH = WIDTH / 2;
    private static final int HALF_HEIGHT = HEIGHT / 2;

    public ReactorScreen(ReactorMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title, 176, 166);
    }

    @Override public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick)
    {
        int i = (this.width  - this.imageWidth)  / 2;
        int j = (this.height - this.imageHeight) / 2;

        this.extractTransparentBackground(graphics);

        graphics.blit(RenderPipelines.GUI_TEXTURED, REACTOR_BACKGROUND, i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

        float proportion = this.menu.getActivePercent() / 100.0F;
        Identifier progressIdentifier = this.menu.isLightReactor() ? LIGHT_PROGRESS : SHADOW_PROGRESS;

        float displayedProportion;

        if (proportion <= 0.01F) {displayedProportion = 0.0F;}
        else if (proportion >= 1.0F) {displayedProportion = 1.0F;}
        else //[0.01 ; 1.0] -> [0.15 ; 1.0] because animation is visible starting at 0.19
        {
            float t = (proportion - 0.01F) / (0.99F);
            displayedProportion = 0.15F + t * (0.85F);
        }

        int fillX = (int) (HALF_WIDTH * displayedProportion);
        int fillY = (int) (HALF_HEIGHT * displayedProportion);

        if (fillX > 0 && fillY > 0)
        {
            graphics.blit(RenderPipelines.GUI_TEXTURED, progressIdentifier, i + CENTER_X - fillX, j + CENTER_Y - fillY, HALF_WIDTH - fillX, HALF_HEIGHT - fillY, fillX * 2, fillY * 2, 140, 50);
        }
    }
}