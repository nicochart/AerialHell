package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowTrollModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowTrollRenderState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ShadowTrollShadowLayer extends FeatureRenderer<ShadowTrollRenderState, ShadowTrollModel>
{
   private final ShadowTrollModel shadowTrollModel;

   public ShadowTrollShadowLayer(FeatureRendererContext<ShadowTrollRenderState, ShadowTrollModel> layerParent, ShadowTrollModel model)
   {
      super(layerParent);
      this.shadowTrollModel = model;
   }

   @Override public void render(MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight, ShadowTrollRenderState renderState, float yaw, float pitch)
   {
      if (!renderState.invisible)
      {
         this.shadowTrollModel.setAngles(renderState);
         VertexConsumer consumer = bufferSource.getBuffer(RenderLayer.getEntityTranslucent(renderState.texture));
         this.shadowTrollModel.render(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
