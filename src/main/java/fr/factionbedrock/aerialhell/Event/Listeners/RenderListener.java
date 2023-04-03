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
        int maxHalfHearts = (int)player.getMaxHealth(), maxHearts  = maxHalfHearts/2;
        int halfHearts = (int) player.getHealth(), hearts = halfHearts / 2;
        int playerActualHeartNumber = Math.min(hearts, maxHearts);
        int yOffset = getHealthBarYOffset(player), xOffset = (halfHearts % 20)/2;

        Minecraft.getInstance().getTextureManager().bindTexture(VULNERABLE_EMPTY_HEART);
        blitAllEntireHearts(matrixStack, x, y, maxHearts, yOffset);

        Minecraft.getInstance().getTextureManager().bindTexture(VULNERABLE_HALF_HEART);
        if (halfHearts%2 != 0)
        {
            int yTotalOffset = - yOffset * (halfHearts/20);
            int xTotalOffset = xOffset * (HEART_ICON_WIDTH - 1);
            blitSingleHeartIcon(matrixStack, x + xTotalOffset, y + yTotalOffset);
        }

        Minecraft.getInstance().getTextureManager().bindTexture(VULNERABLE_HEART);
        blitAllEntireHearts(matrixStack, x, y, playerActualHeartNumber, yOffset);

        //Vanilla expected texture
        Minecraft.getInstance().getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
    }

    private static void blitAllEntireHearts(MatrixStack matrixStack, int x, int y, int number, int yOffset)
    {
        int i, heartToBlit, yTotalOffset = number%10 != 0 ? (number/10 + 1) * yOffset : (number/10) * yOffset, remainingHearts = number;
        while (remainingHearts > 0)
        {
            yTotalOffset -= yOffset;
            heartToBlit = 10;
            if (remainingHearts % 10 != 0) {heartToBlit = remainingHearts % 10;}

            for (i = 0; i < heartToBlit; i++)
            {
                blitSingleHeartIcon(matrixStack, x + i * (HEART_ICON_WIDTH - 1), y - yTotalOffset);
            }
            remainingHearts -= heartToBlit;
        }
    }

    private static void blitSingleHeartIcon(MatrixStack matrixStack, int x, int y) //texture must be binded before calling
    {
        AbstractGui.blit(matrixStack, x, y, 0, 0, HEART_ICON_WIDTH, HEART_ICON_HEIGHT, 9, 9);
    }

    private static int getHealthBarYOffset(PlayerEntity player)
    {
        int maxHalfHearts = (int)player.getMaxHealth();
        if (maxHalfHearts <= 40) {return 10;}
        else if (maxHalfHearts <= 60) {return 9;}
        else if (maxHalfHearts <= 80) {return 8;}
        else if (maxHalfHearts <= 100) {return 7;}
        else if (maxHalfHearts <= 120) {return 6;}
        else if (maxHalfHearts <= 140) {return 5;}
        else if (maxHalfHearts <= 160) {return 4;}
        else {return 3;}
    }
}
