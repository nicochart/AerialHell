package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalSlimeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalSlimeRenderState;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class CrystalSlimeGelAndCrystalLayer extends FeatureRenderer<CrystalSlimeRenderState, CrystalSlimeModel>
{
   private final CrystalSlimeModel crystalSlimeModel;

   public CrystalSlimeGelAndCrystalLayer(FeatureRendererContext<CrystalSlimeRenderState, CrystalSlimeModel> layerParent, CrystalSlimeModel model)
   {
      super(layerParent);
      this.crystalSlimeModel = model;
   }

   @Override public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, CrystalSlimeRenderState renderState, float limbAngle, float limbDistance)
   {
      if (!renderState.invisible)
      {
         this.crystalSlimeModel.setAngles(renderState);
         queue.submitModel(this.crystalSlimeModel, renderState, matrices, RenderLayers.entityTranslucent(renderState.texture), light, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
