package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemCrystalModel;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class CrystalGolemCrystalLayer<T extends AerialHellGolemEntity, M extends CrystalGolemModel<T>> extends RenderLayer<T,M>
{
   private final CrystalGolemCrystalModel<T> golemModel;
   private static final ResourceLocation CRYSTAL_GOLEM_CRYSTALS = new ResourceLocation(AerialHell.MODID, "textures/entity/crystal_golem/crystal_golem.png");
   
   public CrystalGolemCrystalLayer(RenderLayerParent<T,M> layerParent, CrystalGolemCrystalModel<T> crystalModel)
   {
      super(layerParent);
      golemModel = crystalModel;
   }
   
   public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getParentModel().copyPropertiesTo(this.golemModel);
         this.golemModel.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         VertexConsumer consumer = bufferIn.getBuffer(RenderType.entityCutout(CRYSTAL_GOLEM_CRYSTALS));
         this.golemModel.renderToBuffer(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
      }
   }
}
