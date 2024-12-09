package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ShadowAutomatonShadowLayer<T extends AutomatonEntity, M extends EmptyModel<T>> extends FeatureRenderer<T,M>
{
   private final AutomatonModel<T> shadowAutomatonModel;

   public ShadowAutomatonShadowLayer(FeatureRendererContext<T,M> layerParent, AutomatonModel<T> model)
   {
      super(layerParent);
      this.shadowAutomatonModel = model;
   }
   
   public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getContextModel().copyStateTo(this.shadowAutomatonModel);
         this.shadowAutomatonModel.animateModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
         this.shadowAutomatonModel.setAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         VertexConsumer consumer = bufferIn.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entitylivingbaseIn)));
         this.shadowAutomatonModel.render(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlay(entitylivingbaseIn, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
