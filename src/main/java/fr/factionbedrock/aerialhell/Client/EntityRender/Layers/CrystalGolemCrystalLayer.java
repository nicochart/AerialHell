package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemCrystalModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalGolemRenderState;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class CrystalGolemCrystalLayer<T extends CrystalGolemRenderState, M extends CrystalGolemModel<CrystalGolemRenderState>> extends RenderLayer<T,M>
{
   private final CrystalGolemCrystalModel<T> golemModel;
   private static final ResourceLocation CRYSTAL_GOLEM_CRYSTALS = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/crystal_golem/crystal_golem.png");
   
   public CrystalGolemCrystalLayer(RenderLayerParent<T,M> layerParent, CrystalGolemCrystalModel<T> crystalModel)
   {
      super(layerParent);
      golemModel = crystalModel;
   }

   @Override public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T renderState, float yRot, float xRot)
   {
      if (!renderState.isInvisible)
      {
         //this.getParentModel().copyPropertiesTo(this.golemModel); TODO is it still needed or is it done with renderState ?
         this.golemModel.setupAnim(renderState);
         VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutout(CRYSTAL_GOLEM_CRYSTALS));
         this.golemModel.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
