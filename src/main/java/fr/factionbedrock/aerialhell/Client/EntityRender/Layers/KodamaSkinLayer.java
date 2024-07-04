package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Entity.Passive.KodamaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import java.awt.*;

public class KodamaSkinLayer<T extends KodamaEntity> extends RenderLayer<T, KodamaModel<T>>
{
   private final KodamaModel kodamaModel;

   public KodamaSkinLayer(RenderLayerParent<T, KodamaModel<T>> layerParent, KodamaModel model)
   {
      super(layerParent);
      this.kodamaModel = model;
   }
   
   @Override public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getParentModel().copyPropertiesTo(this.kodamaModel);
         this.kodamaModel.prepareMobModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
         this.kodamaModel.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         VertexConsumer consumer = bufferIn.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entitylivingbaseIn)));
         this.kodamaModel.renderToBuffer(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
