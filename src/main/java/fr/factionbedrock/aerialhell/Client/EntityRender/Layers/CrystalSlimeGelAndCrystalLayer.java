package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalSlimeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalSlimeRenderState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class CrystalSlimeGelAndCrystalLayer extends FeatureRenderer<CrystalSlimeRenderState, CrystalSlimeModel>
{
   private final CrystalSlimeModel crystalSlimeModel;

   public CrystalSlimeGelAndCrystalLayer(FeatureRendererContext<CrystalSlimeRenderState, CrystalSlimeModel> layerParent, CrystalSlimeModel model)
   {
      super(layerParent);
      this.crystalSlimeModel = model;
   }

   @Override public void render(MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight, CrystalSlimeRenderState renderState, float yaw, float pitch)
   {
      if (!renderState.invisible)
      {
         this.crystalSlimeModel.setAngles(renderState);
         VertexConsumer consumer = bufferSource.getBuffer(RenderLayer.getEntityTranslucent(renderState.texture));
         this.crystalSlimeModel.render(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
