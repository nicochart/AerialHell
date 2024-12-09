package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.HellSpiderSpikeModel;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowSpiderEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.Identifier;

import java.awt.*;

public class HellSpiderSpikesLayer<T extends SpiderEntity, M extends SpiderEntityModel<T>> extends FeatureRenderer<T, M>
{
   private final HellSpiderSpikeModel<T> spiderSpikeModel;
   private static final Identifier HELL_SPIDER_SPIKES = Identifier.of(AerialHell.MODID, "textures/entity/hell_spider/spikes.png");
   private static final Identifier CRYSTAL_SPIDER_SPIKES = Identifier.of(AerialHell.MODID, "textures/entity/crystal_spider/crystals.png");
   private static final Identifier SHADOW_SPIDER_SPIKES = Identifier.of(AerialHell.MODID, "textures/entity/shadow_spider/spikes.png");
   
   public HellSpiderSpikesLayer(FeatureRendererContext<T,M> layerParent, HellSpiderSpikeModel<T> spikeModel)
   {
      super(layerParent);
      this.spiderSpikeModel = spikeModel;
   }
   
   private VertexConsumer getBuffer(VertexConsumerProvider bufferIn, T entity)
   {
	   if (entity instanceof HellSpiderEntity)
	   {
		   return bufferIn.getBuffer(RenderLayer.getEntityCutout(HELL_SPIDER_SPIKES));
	   }
	   else if (entity instanceof ShadowSpiderEntity)
	   {
		   return bufferIn.getBuffer(RenderLayer.getEntityCutout(SHADOW_SPIDER_SPIKES));
	   }
	   else
	   {
		   return bufferIn.getBuffer(RenderLayer.getEntityTranslucent(CRYSTAL_SPIDER_SPIKES));
	   }
   }
   
   public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {   
      if (!entitylivingbaseIn.isInvisible())
      {
          this.getContextModel().copyStateTo(this.spiderSpikeModel);
          this.spiderSpikeModel.setAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
          VertexConsumer consumer = getBuffer(bufferIn, entitylivingbaseIn);
          this.spiderSpikeModel.render(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlay(entitylivingbaseIn, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
