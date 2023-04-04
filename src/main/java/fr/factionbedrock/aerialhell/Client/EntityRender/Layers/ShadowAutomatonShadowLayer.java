package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Entity.Monster.StellarStoneAutomatonEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShadowAutomatonShadowLayer extends LayerRenderer<StellarStoneAutomatonEntity, AutomatonModel<StellarStoneAutomatonEntity>>
{
   private final AutomatonModel<StellarStoneAutomatonEntity> shadowAutomatonModel = new AutomatonModel<StellarStoneAutomatonEntity>(false);

   public ShadowAutomatonShadowLayer(IEntityRenderer<StellarStoneAutomatonEntity, AutomatonModel<StellarStoneAutomatonEntity>> p_i50923_1_) {super(p_i50923_1_);}
   
   public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, StellarStoneAutomatonEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getEntityModel().copyModelAttributesTo(this.shadowAutomatonModel);
         this.shadowAutomatonModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
         this.shadowAutomatonModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityTranslucent(this.getEntityTexture(entitylivingbaseIn)));
         this.shadowAutomatonModel.render(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getPackedOverlay(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
      }
   }
}
