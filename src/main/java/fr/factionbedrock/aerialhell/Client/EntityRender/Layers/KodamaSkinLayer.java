package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.KodamaRenderState;
import java.awt.*;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class KodamaSkinLayer<S extends KodamaRenderState> extends RenderLayer<S, KodamaModel<S>>
{
   private final KodamaModel<S> kodamaModel;

   public KodamaSkinLayer(RenderLayerParent<S, KodamaModel<S>> layerParent, KodamaModel<S> model)
   {
      super(layerParent);
      this.kodamaModel = model;
   }

   @Override public void submit(PoseStack matrices, SubmitNodeCollector queue, int light, S renderState, float limbAngle, float limbDistance)
   {
      if (!renderState.isInvisible)
      {
         this.kodamaModel.setupAnim(renderState);
         queue.submitModel(this.kodamaModel, renderState, matrices, RenderTypes.entityTranslucent(renderState.texture), light, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
