package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.KodamaRenderState;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class KodamaSkinLayer<S extends KodamaRenderState> extends FeatureRenderer<S, KodamaModel<S>>
{
   private final KodamaModel<S> kodamaModel;

   public KodamaSkinLayer(FeatureRendererContext<S, KodamaModel<S>> layerParent, KodamaModel<S> model)
   {
      super(layerParent);
      this.kodamaModel = model;
   }

   @Override public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, S renderState, float limbAngle, float limbDistance)
   {
      if (!renderState.invisible)
      {
         this.kodamaModel.setAngles(renderState);
         queue.submitModel(this.kodamaModel, renderState, matrices, RenderLayers.entityTranslucent(renderState.texture), light, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
