package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CortinariusCowShroomModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class CortinariusCowShroomLayer<S extends LivingEntityRenderState, M extends CowModel> extends RenderLayer<S, M>
{
   private final CortinariusCowShroomModel<S> cortinariusCowShroomModel;
   private static final ResourceLocation CORTINARIUS_COW_SHROOM_LAYER = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/cortinarius_cow/cortinarius_cow_shroom.png");
   
   public CortinariusCowShroomLayer(RenderLayerParent<S,M> layerParent, CortinariusCowShroomModel<S> shroomModel)
   {
      super(layerParent);
      this.cortinariusCowShroomModel = shroomModel;
   }

   @Override public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S renderState, float yRot, float xRot)
   {
      if (!renderState.isInvisible)
      {
         this.cortinariusCowShroomModel.setupAnim(renderState);
         VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutout(CORTINARIUS_COW_SHROOM_LAYER));
         this.cortinariusCowShroomModel.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
