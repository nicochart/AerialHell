package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemCrystalModel;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;

public class CrystalGolemCrystalLayer<T extends AerialHellGolemEntity, M extends CrystalGolemModel<T>> extends FeatureRenderer<T,M>
{
   private final CrystalGolemCrystalModel<T> golemModel;
   private static final Identifier CRYSTAL_GOLEM_CRYSTALS = Identifier.of(AerialHell.MODID, "textures/entity/crystal_golem/crystal_golem.png");
   
   public CrystalGolemCrystalLayer(FeatureRendererContext<T,M> layerParent, CrystalGolemCrystalModel<T> crystalModel)
   {
      super(layerParent);
      golemModel = crystalModel;
   }
   
   public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getContextModel().copyStateTo(this.golemModel);
         this.golemModel.setAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         VertexConsumer consumer = bufferIn.getBuffer(RenderLayer.getEntityCutout(CRYSTAL_GOLEM_CRYSTALS));
         this.golemModel.render(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlay(entitylivingbaseIn, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
