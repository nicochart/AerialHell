package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowTrollModel;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ShadowTrollShadowLayer extends FeatureRenderer<ShadowTrollEntity, ShadowTrollModel>
{
   private final ShadowTrollModel shadowTrollModel;

   public ShadowTrollShadowLayer(FeatureRendererContext<ShadowTrollEntity, ShadowTrollModel> layerParent, ShadowTrollModel model)
   {
      super(layerParent);
      this.shadowTrollModel = model;
   }
   
   public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, ShadowTrollEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getContextModel().copyStateTo(this.shadowTrollModel);
         this.shadowTrollModel.animateModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
         this.shadowTrollModel.setAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         VertexConsumer consumer = bufferIn.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entitylivingbaseIn)));
         this.shadowTrollModel.render(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlay(entitylivingbaseIn, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
