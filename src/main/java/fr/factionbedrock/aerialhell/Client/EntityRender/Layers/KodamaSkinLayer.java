package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.KodamaRenderState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
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

   @Override public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S renderState, float yRot, float xRot)
   {
      if (!renderState.isInvisible)
      {
         //this.getParentModel().copyPropertiesTo(this.kodamaModel); TODO is it still needed or is it done with renderState ?
         this.kodamaModel.setupAnim(renderState);
         VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(renderState.texture));
         this.kodamaModel.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
