package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class RenderHudOverlayMixin
{
    private static final Identifier VULNERABLE_OVERLAY = AerialHell.id("textures/misc/vulnerability_blur.png");
    private static final Identifier AERIAL_HELL_PORTAL_OVERLAY = AerialHell.id("textures/misc/aerial_hell_portal_overlay.png");
    private static final Identifier LIQUID_OF_THE_GODS_OVERLAY = AerialHell.id("textures/block/liquid_of_the_gods_overlay.png");

    @Inject(method = "renderCameraOverlays", at = @At("HEAD"), cancellable = true)
    private void renderMiscOverlays(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo callbackInfo)
    {
        Gui hud = (Gui) (Object) this;
        Player player = hud.minecraft.player;

        if (player == null) {return;}

        if (EntityHelper.isLivingEntityVulnerable(player))
        {
            float alpha = Math.min(20, player.getEffect(AerialHellMobEffects.VULNERABILITY).getDuration()) / 20.0F;
            renderOverlay(context, VULNERABLE_OVERLAY, alpha);
        }
        if (EntityHelper.isLivingEntityUnderAerialHellPortalEffect(player))
        {
            int duration = player.getEffect(AerialHellMobEffects.AERIAL_HELL_PORTAL).getDuration();
            float alphaToSub = Mth.sin(duration / 5.0F) * 0.2F + 0.2F;
            float baseAlpha = Math.min(20, duration) / 20.0F;
            float alpha = Math.max(0.0F, baseAlpha - alphaToSub);
            renderOverlay(context, AERIAL_HELL_PORTAL_OVERLAY, alpha);
        }
        FluidState fluidState = EntityHelper.getInLiquidFluidState(player);
        if (fluidState != null && fluidState.is(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
        {
            renderOverlay(context, LIQUID_OF_THE_GODS_OVERLAY, 1.0F);
        }
    }

    //copy of net.minecraft.client.gui.hud.InGameHud method of same name
    private void renderOverlay(GuiGraphics context, Identifier texture, float opacity)
    {
        int i = ARGB.white(opacity);
        context.blit(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0.0F, 0.0F, context.guiWidth(), context.guiHeight(), context.guiWidth(), context.guiHeight(), i);
    }
}
