package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Entity.Passive.KodamaEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class KodamaSkinLayer<T extends KodamaEntity> extends FeatureRenderer<T, KodamaModel<T>>
{
   private final KodamaModel kodamaModel;

   public KodamaSkinLayer(FeatureRendererContext<T, KodamaModel<T>> layerParent, KodamaModel model)
   {
      super(layerParent);
      this.kodamaModel = model;
   }
   
   @Override public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getContextModel().copyStateTo(this.kodamaModel);
         this.kodamaModel.animateModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
         this.kodamaModel.setAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         VertexConsumer consumer = bufferIn.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entitylivingbaseIn)));
         this.kodamaModel.render(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlay(entitylivingbaseIn, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
