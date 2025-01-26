package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowTrollModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowTrollRenderState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import java.awt.*;

public class ShadowTrollShadowLayer extends RenderLayer<ShadowTrollRenderState, ShadowTrollModel>
{
   private final ShadowTrollModel shadowTrollModel;

   public ShadowTrollShadowLayer(RenderLayerParent<ShadowTrollRenderState, ShadowTrollModel> layerParent, ShadowTrollModel model)
   {
      super(layerParent);
      this.shadowTrollModel = model;
   }

   @Override public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ShadowTrollRenderState renderState, float yRot, float xRot)
   {
      if (!renderState.isInvisible)
      {
         this.shadowTrollModel.setupAnim(renderState);
         VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(renderState.texture));
         this.shadowTrollModel.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
