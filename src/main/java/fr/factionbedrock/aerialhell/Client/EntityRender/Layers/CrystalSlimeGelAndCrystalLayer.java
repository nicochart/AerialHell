package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalSlimeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalSlimeRenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import java.awt.*;

public class CrystalSlimeGelAndCrystalLayer extends RenderLayer<CrystalSlimeRenderState, CrystalSlimeModel>
{
   private final CrystalSlimeModel crystalSlimeModel;

   public CrystalSlimeGelAndCrystalLayer(RenderLayerParent<CrystalSlimeRenderState, CrystalSlimeModel> layerParent, CrystalSlimeModel model)
   {
      super(layerParent);
      this.crystalSlimeModel = model;
   }

   @Override public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, CrystalSlimeRenderState renderState, float yRot, float xRot)
   {
      if (!renderState.isInvisible)
      {
         this.crystalSlimeModel.setupAnim(renderState);
         submitNodeCollector.submitModel(this.crystalSlimeModel, renderState, poseStack, RenderType.entityTranslucent(renderState.texture), packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
