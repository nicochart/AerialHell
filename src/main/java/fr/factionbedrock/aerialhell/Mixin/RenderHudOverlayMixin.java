package fr.factionbedrock.aerialhell.Mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class RenderHudOverlayMixin
{
    private static final Identifier VULNERABLE_OVERLAY = AerialHell.id("textures/misc/vulnerability_blur.png");

    @Inject(method = "renderMiscOverlays", at = @At("HEAD"), cancellable = true)
    private void renderMiscOverlays(DrawContext context, RenderTickCounter tickCounter, CallbackInfo callbackInfo)
    {
        InGameHud hud = (InGameHud) (Object) this;
        PlayerEntity player = hud.client.player;

        if (player != null && EntityHelper.isLivingEntityVulnerable(player))
        {
            float alpha = Math.min(20, player.getStatusEffect(AerialHellMobEffects.VULNERABILITY).getDuration()) / 20.0F;
            renderOverlay(context, VULNERABLE_OVERLAY, alpha);
        }
    }

    //copy of net.minecraft.client.gui.hud.InGameHud method of same name
    private void renderOverlay(DrawContext context, Identifier texture, float opacity)
    {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        context.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
        context.drawTexture(texture, 0, 0, -90, 0.0F, 0.0F, context.getScaledWindowWidth(), context.getScaledWindowHeight(), context.getScaledWindowWidth(), context.getScaledWindowHeight());
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
