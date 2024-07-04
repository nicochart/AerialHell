package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CortinariusCowShroomModel;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class CortinariusCowShroomLayer<T extends EvilCowEntity, M extends CowModel<T>> extends RenderLayer<T, M>
{
   private final CortinariusCowShroomModel<T> cortinariusCowShroomModel;
   private static final ResourceLocation CORTINARIUS_COW_SHROOM_LAYER = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/cortinarius_cow/cortinarius_cow_shroom.png");
   
   public CortinariusCowShroomLayer(RenderLayerParent<T,M> layerParent, CortinariusCowShroomModel<T> shroomModel)
   {
      super(layerParent);
      this.cortinariusCowShroomModel = shroomModel;
   }
   
   public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {   
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getParentModel().copyPropertiesTo(this.cortinariusCowShroomModel);
         this.cortinariusCowShroomModel.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         VertexConsumer consumer = bufferIn.getBuffer(RenderType.entityCutout(CORTINARIUS_COW_SHROOM_LAYER));
         this.cortinariusCowShroomModel.renderToBuffer(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
