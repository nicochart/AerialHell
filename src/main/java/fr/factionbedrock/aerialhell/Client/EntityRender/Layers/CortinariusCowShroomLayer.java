package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CortinariusCowShroomModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;

public class CortinariusCowShroomLayer<S extends LivingEntityRenderState, M extends CowEntityModel> extends FeatureRenderer<S, M>
{
   private final CortinariusCowShroomModel<S> cortinariusCowShroomModel;
   private static final Identifier CORTINARIUS_COW_SHROOM_LAYER = Identifier.of(AerialHell.MODID, "textures/entity/cortinarius_cow/cortinarius_cow_shroom.png");

   public CortinariusCowShroomLayer(FeatureRendererContext<S,M> layerParent, CortinariusCowShroomModel<S> shroomModel)
   {
      super(layerParent);
      this.cortinariusCowShroomModel = shroomModel;
   }

   @Override public void render(MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight, S renderState, float yaw, float pitch)
   {
      if (!renderState.invisible)
      {
         this.cortinariusCowShroomModel.setAngles(renderState);
         VertexConsumer consumer = bufferSource.getBuffer(RenderLayer.getEntityCutout(CORTINARIUS_COW_SHROOM_LAYER));
         this.cortinariusCowShroomModel.render(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
