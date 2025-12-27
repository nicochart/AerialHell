package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowTrollModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowTrollRenderState;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ShadowTrollShadowLayer extends FeatureRenderer<ShadowTrollRenderState, ShadowTrollModel>
{
   private final ShadowTrollModel shadowTrollModel;

   public ShadowTrollShadowLayer(FeatureRendererContext<ShadowTrollRenderState, ShadowTrollModel> layerParent, ShadowTrollModel model)
   {
      super(layerParent);
      this.shadowTrollModel = model;
   }

   @Override public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, ShadowTrollRenderState renderState, float limbAngle, float limbDistance)
   {
      if (!renderState.invisible)
      {
         this.shadowTrollModel.setAngles(renderState);
         queue.submitModel(this.shadowTrollModel, renderState, matrices, RenderLayers.entityTranslucent(renderState.texture), light, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
