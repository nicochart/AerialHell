package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class RenderHudOverlayMixin
{
    private static final Identifier VULNERABLE_OVERLAY = AerialHell.id("textures/misc/vulnerability_blur.png");
    private static final Identifier LIQUID_OF_THE_GODS_OVERLAY = AerialHell.id("textures/block/liquid_of_the_gods_overlay.png");

    @Inject(method = "renderMiscOverlays", at = @At("HEAD"), cancellable = true)
    private void renderMiscOverlays(DrawContext context, RenderTickCounter tickCounter, CallbackInfo callbackInfo)
    {
        InGameHud hud = (InGameHud) (Object) this;
        PlayerEntity player = hud.client.player;

        if (player == null) {return;}

        if (EntityHelper.isLivingEntityVulnerable(player))
        {
            float alpha = Math.min(20, player.getStatusEffect(AerialHellMobEffects.VULNERABILITY).getDuration()) / 20.0F;
            renderOverlay(context, VULNERABLE_OVERLAY, alpha);
        }
        FluidState fluidState = EntityHelper.getInLiquidFluidState(player);
        if (fluidState != null && fluidState.isIn(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
        {
            renderOverlay(context, LIQUID_OF_THE_GODS_OVERLAY, 1.0F);
        }
    }

    //copy of net.minecraft.client.gui.hud.InGameHud method of same name
    private void renderOverlay(DrawContext context, Identifier texture, float opacity)
    {
        int i = ColorHelper.getWhite(opacity);
        context.drawTexture(RenderPipelines.GUI_TEXTURED, texture, 0, 0, 0.0F, 0.0F, context.getScaledWindowWidth(), context.getScaledWindowHeight(), context.getScaledWindowWidth(), context.getScaledWindowHeight(), i);
    }
}
