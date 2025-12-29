package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AutomatonRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;

import java.awt.*;

public class ShadowAutomatonShadowLayer<S extends AutomatonRenderState, M extends EmptyModel<S>> extends RenderLayer<S,M>
{
   private final AutomatonModel<S> shadowAutomatonModel;

   public ShadowAutomatonShadowLayer(RenderLayerParent<S,M> layerParent, AutomatonModel<S> model)
   {
      super(layerParent);
      this.shadowAutomatonModel = model;
   }

   @Override public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, S renderState, float yRot, float xRot)
   {
      if (!renderState.isInvisible)
      {
         this.shadowAutomatonModel.setupAnim(renderState);
         submitNodeCollector.submitModel(this.shadowAutomatonModel, renderState, poseStack, RenderTypes.entityTranslucent(renderState.texture), packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
