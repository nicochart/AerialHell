package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowTrollModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowTrollRenderState;
import java.awt.*;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class ShadowTrollShadowLayer extends RenderLayer<ShadowTrollRenderState, ShadowTrollModel>
{
   private final ShadowTrollModel shadowTrollModel;

   public ShadowTrollShadowLayer(RenderLayerParent<ShadowTrollRenderState, ShadowTrollModel> layerParent, ShadowTrollModel model)
   {
      super(layerParent);
      this.shadowTrollModel = model;
   }

   @Override public void submit(PoseStack matrices, SubmitNodeCollector queue, int light, ShadowTrollRenderState renderState, float limbAngle, float limbDistance)
   {
      if (!renderState.isInvisible)
      {
         this.shadowTrollModel.setupAnim(renderState);
         queue.submitModel(this.shadowTrollModel, renderState, matrices, RenderTypes.entityTranslucent(renderState.texture), light, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
