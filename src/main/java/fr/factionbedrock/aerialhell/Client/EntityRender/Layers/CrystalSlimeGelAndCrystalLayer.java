package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalSlimeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalSlimeRenderState;
import java.awt.*;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class CrystalSlimeGelAndCrystalLayer extends RenderLayer<CrystalSlimeRenderState, CrystalSlimeModel>
{
   private final CrystalSlimeModel crystalSlimeModel;

   public CrystalSlimeGelAndCrystalLayer(RenderLayerParent<CrystalSlimeRenderState, CrystalSlimeModel> layerParent, CrystalSlimeModel model)
   {
      super(layerParent);
      this.crystalSlimeModel = model;
   }

   @Override public void submit(PoseStack matrices, SubmitNodeCollector queue, int light, CrystalSlimeRenderState renderState, float limbAngle, float limbDistance)
   {
      if (!renderState.isInvisible)
      {
         this.crystalSlimeModel.setupAnim(renderState);
         queue.submitModel(this.crystalSlimeModel, renderState, matrices, RenderTypes.entityTranslucent(renderState.texture), light, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
