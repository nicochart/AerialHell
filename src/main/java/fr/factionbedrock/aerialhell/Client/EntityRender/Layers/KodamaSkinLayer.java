package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.KodamaRenderState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
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

   @Override public void render(MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight, S renderState, float yaw, float pitch)
   {
      if (!renderState.invisible)
      {
         this.kodamaModel.setAngles(renderState);
         VertexConsumer consumer = bufferSource.getBuffer(RenderLayer.getEntityTranslucent(renderState.texture));
         this.kodamaModel.render(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
