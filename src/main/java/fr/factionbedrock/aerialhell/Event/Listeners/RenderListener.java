package fr.factionbedrock.aerialhell.Event.Listeners;

import com.mojang.blaze3d.vertex.*;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.client.event.RenderGameOverlayEvent;
//import net.minecraftforge.client.event.RenderGuiOverlayEvent;
//import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;

public class RenderListener
{
    //TODO work on that
    private static final ResourceLocation VULNERABLE_OVERLAY = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/misc/vulnerability_blur.png");
    private static final ResourceLocation VULNERABLE_HEART = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/vulnerability_hearts.png");
    private static final ResourceLocation VULNERABLE_HEART_WITH_BORDER = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/vulnerability_hearts_with_border.png");
    private static final ResourceLocation VULNERABLE_HALF_HEART = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/vulnerability_half_hearts.png");
    private static final ResourceLocation VULNERABLE_HALF_HEART_WITH_BORDER = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/vulnerability_half_hearts_with_border.png");
    private static final ResourceLocation VULNERABLE_EMPTY_HEART = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/vulnerability_empty_hearts.png");
    private static final ResourceLocation VULNERABLE_EMPTY_HEART_WITH_BORDER = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/vulnerability_empty_hearts_with_border.png");

    private static final int HEART_ICON_WIDTH = 9;
    private static final int HEART_ICON_HEIGHT = 9;

    /* @SubscribeEvent TODO
    public static void onRenderOverlayPost(RenderGuiOverlayEvent.Post event)
    {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player != null && EntityHelper.isLivingEntityVulnerable(player))
        {
            if (event.getOverlay().id() == VanillaGuiOverlay.FROSTBITE.id() && mc.options.getCameraType().isFirstPerson())
            {
                float alpha = Math.min(20, player.getEffect(AerialHellMobEffects.VULNERABILITY.get()).getDuration()) / 20.0F;
                renderTextureOverlay(event.getGuiGraphics(), VULNERABLE_OVERLAY, alpha);
            }
        }
    }*/

    /* @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && player != null && EntityHelper.isLivingEntityVulnerable(player)) //TODO : ElementType.ALL is ok ? before is was ElementType.HEALTH
        {
            boolean lowHealth = player.getHealth() <= 4;
            if (lowHealth) {event.setCanceled(true);}
            PoseStack matrixStack = event.getMatrixStack();

            int x = event.getWindow().getScreenWidth() / 2 - 91;
            int y = event.getWindow().getScreenHeight() - 39;

            renderVulnerableHearts(matrixStack, player, x, y, lowHealth);
        }
    }*/

    //Copy of Gui.renderTextureOverlay
    //
    public static void renderTextureOverlay(GuiGraphics graphics, ResourceLocation textureLocation, float alpha)
    {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        graphics.setColor(1.0F, 1.0F, 1.0F, alpha);
        graphics.blit(textureLocation, 0, 0, -90, 0.0F, 0.0F, graphics.guiWidth(), graphics.guiHeight(), graphics.guiWidth(), graphics.guiHeight());
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /*
    private static void renderVulnerableHearts(PoseStack matrixStack, Player player, int x, int y, boolean lowHealth)
    {
        int maxHalfHearts = (int)player.getMaxHealth(), maxHearts  = maxHalfHearts/2;
        int halfHearts = (int) player.getHealth(), hearts = halfHearts / 2;
        int playerActualHeartNumber = Math.min(hearts, maxHearts);
        int yOffset = getHealthBarYOffset(player), xOffset = (halfHearts % 20)/2;

        if (yOffset < 8 || lowHealth) {RenderSystem.setShaderTexture(0, VULNERABLE_EMPTY_HEART_WITH_BORDER);}
        else {RenderSystem.setShaderTexture(0, VULNERABLE_EMPTY_HEART);}
        blitAllEntireHearts(matrixStack, x, y, maxHearts, yOffset);

        if (yOffset < 8 || lowHealth) {RenderSystem.setShaderTexture(0, VULNERABLE_HALF_HEART_WITH_BORDER);}
        else {RenderSystem.setShaderTexture(0, VULNERABLE_HALF_HEART);}
        if (halfHearts%2 != 0)
        {
            int yTotalOffset = - yOffset * (halfHearts/20);
            int xTotalOffset = xOffset * (HEART_ICON_WIDTH - 1);
            blitSingleHeartIcon(matrixStack, x + xTotalOffset, y + yTotalOffset);
        }

        if (yOffset < 8 || lowHealth) {RenderSystem.setShaderTexture(0, VULNERABLE_HEART_WITH_BORDER);}
        else {RenderSystem.setShaderTexture(0, VULNERABLE_HEART);}
        blitAllEntireHearts(matrixStack, x, y, playerActualHeartNumber, yOffset);

        //Vanilla expected texture
        RenderSystem.setShaderTexture(0, Gui.GUI_ICONS_LOCATION);
    }

    private static void blitAllEntireHearts(PoseStack matrixStack, int x, int y, int number, int yOffset)
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

    private static void blitSingleHeartIcon(PoseStack matrixStack, int x, int y) //texture must be binded before calling
    {
        Gui.blit(matrixStack, x, y, 0, 0, HEART_ICON_WIDTH, HEART_ICON_HEIGHT, 9, 9);
    }

    private static int getHealthBarYOffset(Player player)
    {
        int maxHalfHearts = (int)player.getMaxHealth();
        if (player.hasEffect(MobEffects.ABSORPTION)) {maxHalfHearts += 4 * (player.getEffect(MobEffects.ABSORPTION).getAmplifier() + 1);}
        if (maxHalfHearts <= 40) {return 10;}
        else if (maxHalfHearts <= 60) {return 9;}
        else if (maxHalfHearts <= 80) {return 8;}
        else if (maxHalfHearts <= 100) {return 7;}
        else if (maxHalfHearts <= 120) {return 6;}
        else if (maxHalfHearts <= 140) {return 5;}
        else if (maxHalfHearts <= 160) {return 4;}
        else {return 3;}
    }*/
}
