package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AutomatonRenderState;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ShadowAutomatonShadowLayer<S extends AutomatonRenderState, M extends EmptyModel<S>> extends FeatureRenderer<S,M>
{
   private final AutomatonModel<S> shadowAutomatonModel;

   public ShadowAutomatonShadowLayer(FeatureRendererContext<S,M> layerParent, AutomatonModel<S> model)
   {
      super(layerParent);
      this.shadowAutomatonModel = model;
   }

   @Override public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, S renderState, float limbAngle, float limbDistance)
   {
      if (!renderState.invisible)
      {
         this.shadowAutomatonModel.setAngles(renderState);
         queue.submitModel(this.shadowAutomatonModel, renderState, matrices, RenderLayers.entityTranslucent(renderState.texture), light, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
