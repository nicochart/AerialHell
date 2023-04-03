package fr.factionbedrock.aerialhell.Event.Listeners;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RenderListener
{
    private static final ResourceLocation VULNERABLE_HEART = new ResourceLocation(AerialHell.MODID, "textures/gui/vulnerability_hearts.png");
    private static final ResourceLocation VULNERABLE_HALF_HEART = new ResourceLocation(AerialHell.MODID, "textures/gui/vulnerability_half_hearts.png");
    private static final ResourceLocation VULNERABLE_EMPTY_HEART = new ResourceLocation(AerialHell.MODID, "textures/gui/vulnerability_empty_hearts.png");

    private static final int HEART_ICON_WIDTH = 9;
    private static final int HEART_ICON_HEIGHT = 9;

    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;

        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH && player != null && EntityHelper.isLivingEntityVulnerable(player))
        {
            MatrixStack matrixStack = event.getMatrixStack();

            int x = event.getWindow().getScaledWidth() / 2 - 91;
            int y = event.getWindow().getScaledHeight() - 39;

            renderVulnerableHearts(matrixStack, player, x, y);
        }
    }

    private static void renderVulnerableHearts(MatrixStack matrixStack, PlayerEntity player, int x, int y)
    {
        int maxHearts = 20, maxHalfHearts = 40;
        int hearts = (int) player.getHealth() / 2;
        int playerEntireHeart = Math.min(hearts, maxHearts);
        int playerBonusHalfHeart = player.getHealth() % 2 == 0 ? 0 : 1;
        int playerHalfHeart = Math.min(playerBonusHalfHeart, maxHalfHearts - playerEntireHeart * 2);

        Minecraft.getInstance().getTextureManager().bindTexture(VULNERABLE_EMPTY_HEART);
        for (int i = 0; i < maxHearts / 2; i++)
        {
            AbstractGui.blit(matrixStack, x + i * (HEART_ICON_WIDTH - 1), y, 0, 0, HEART_ICON_WIDTH, HEART_ICON_HEIGHT, 9, 9);
        }

        Minecraft.getInstance().getTextureManager().bindTexture(VULNERABLE_HEART);
        for (int i = 0; i < playerEntireHeart; i++)
        {
            AbstractGui.blit(matrixStack, x + i * (HEART_ICON_WIDTH - 1), y, 0, 0, HEART_ICON_WIDTH, HEART_ICON_HEIGHT, 9, 9);
        }

        Minecraft.getInstance().getTextureManager().bindTexture(VULNERABLE_HALF_HEART);
        for (int i = 0; i < playerHalfHeart; i++)
        {
            AbstractGui.blit(matrixStack, x + playerEntireHeart * (HEART_ICON_WIDTH - 1), y, 0, 0, HEART_ICON_WIDTH, HEART_ICON_HEIGHT, 9, 9);
        }

        //Vanilla expected texture
        Minecraft.getInstance().getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
    }
}
