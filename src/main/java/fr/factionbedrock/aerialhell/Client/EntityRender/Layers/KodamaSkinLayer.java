package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.KodamaRenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import java.awt.*;

public class KodamaSkinLayer<S extends KodamaRenderState> extends RenderLayer<S, KodamaModel<S>>
{
   private final KodamaModel<S> kodamaModel;

   public KodamaSkinLayer(RenderLayerParent<S, KodamaModel<S>> layerParent, KodamaModel<S> model)
   {
      super(layerParent);
      this.kodamaModel = model;
   }

   @Override public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, S renderState, float yRot, float xRot)
   {
      if (!renderState.isInvisible)
      {
         this.kodamaModel.setupAnim(renderState);
         submitNodeCollector.submitModel(this.kodamaModel, renderState, poseStack, RenderType.entityTranslucent(renderState.texture), packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
