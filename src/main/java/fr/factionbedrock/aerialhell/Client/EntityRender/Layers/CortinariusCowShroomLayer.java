package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CortinariusCowShroomModel;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;

public class CortinariusCowShroomLayer<T extends EvilCowEntity, M extends CowEntityModel<T>> extends FeatureRenderer<T, M>
{
   private final CortinariusCowShroomModel<T> cortinariusCowShroomModel;
   private static final Identifier CORTINARIUS_COW_SHROOM_LAYER = Identifier.of(AerialHell.MODID, "textures/entity/cortinarius_cow/cortinarius_cow_shroom.png");
   
   public CortinariusCowShroomLayer(FeatureRendererContext<T,M> layerParent, CortinariusCowShroomModel<T> shroomModel)
   {
      super(layerParent);
      this.cortinariusCowShroomModel = shroomModel;
   }
   
   public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {   
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getContextModel().copyStateTo(this.cortinariusCowShroomModel);
         this.cortinariusCowShroomModel.setAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         VertexConsumer consumer = bufferIn.getBuffer(RenderLayer.getEntityCutout(CORTINARIUS_COW_SHROOM_LAYER));
         this.cortinariusCowShroomModel.render(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlay(entitylivingbaseIn, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
