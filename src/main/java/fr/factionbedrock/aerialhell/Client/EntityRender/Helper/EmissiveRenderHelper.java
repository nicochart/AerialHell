package fr.factionbedrock.aerialhell.Client.EntityRender.Helper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;

//overlay textures rendered over a model (same uv), with 3 lighting modes. translucent render types : textures can have semi transparent pixels
public class EmissiveRenderHelper
{
    //semi emissive textures minimum brightness, as a fraction of full brightness (even in complete darkness). 0.0F = normal (lit) texture, 1.0F = fully emissive texture
    public static final float SEMI_EMISSIVE_MIN_BRIGHTNESS = 0.5F;

    public enum Mode
    {
        NORMAL, //lit by the world, like the model
        SEMI_EMISSIVE, //brightness never goes below SEMI_EMISSIVE_MIN_BRIGHTNESS. uses 2 orders : order and order + 1
        EMISSIVE //always fully bright, like vanilla eyes
    }

    public static <S extends LivingEntityRenderState> void submit(Mode mode, Model<? super S> model, Identifier texture, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, int order)
    {
        if (state.isInvisible) {return;}
        switch (mode)
        {
            case NORMAL -> submitLit(model, texture, poseStack, submitNodeCollector, lightCoords, state, order);
            case SEMI_EMISSIVE -> submitSemiEmissive(model, texture, poseStack, submitNodeCollector, lightCoords, state, order, SEMI_EMISSIVE_MIN_BRIGHTNESS);
            case EMISSIVE -> submitEmissive(model, texture, poseStack, submitNodeCollector, state, order);
        }
    }

    public static <S extends LivingEntityRenderState> void submitLit(Model<? super S> model, Identifier texture, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, int order)
    {
        submitNodeCollector.order(order).submitModel(model, state, poseStack, RenderTypes.entityTranslucent(texture), lightCoords, LivingEntityRenderer.getOverlayCoords(state, 0.0F), -1, null, state.outlineColor);
    }

    //lit texture, then the same texture fully bright on top, with minBrightness opacity : brightness = minBrightness + (1 - minBrightness) * world light
    public static <S extends LivingEntityRenderState> void submitSemiEmissive(Model<? super S> model, Identifier texture, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, int order, float minBrightness)
    {
        submitLit(model, texture, poseStack, submitNodeCollector, lightCoords, state, order);
        int emissiveColor = ARGB.white(Mth.clamp(minBrightness, 0.0F, 1.0F));
        submitNodeCollector.order(order + 1).submitModel(model, state, poseStack, RenderTypes.entityTranslucentEmissive(texture), LightCoordsUtil.FULL_BRIGHT, LivingEntityRenderer.getOverlayCoords(state, 0.0F), emissiveColor, null, state.outlineColor);
    }

    public static <S extends LivingEntityRenderState> void submitEmissive(Model<? super S> model, Identifier texture, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, S state, int order)
    {
        submitNodeCollector.order(order).submitModel(model, state, poseStack, RenderTypes.entityTranslucentEmissive(texture), LightCoordsUtil.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor);
    }
}
