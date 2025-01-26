package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalSlimeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalSlimeRenderState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import java.awt.*;

public class CrystalSlimeGelAndCrystalLayer extends RenderLayer<CrystalSlimeRenderState, CrystalSlimeModel>
{
   private final CrystalSlimeModel crystalSlimeModel;

   public CrystalSlimeGelAndCrystalLayer(RenderLayerParent<CrystalSlimeRenderState, CrystalSlimeModel> layerParent, CrystalSlimeModel model)
   {
      super(layerParent);
      this.crystalSlimeModel = model;
   }

   @Override public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, CrystalSlimeRenderState renderState, float yRot, float xRot)
   {
      if (!renderState.isInvisible)
      {
         this.crystalSlimeModel.setupAnim(renderState);
         VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(renderState.texture));
         this.crystalSlimeModel.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
