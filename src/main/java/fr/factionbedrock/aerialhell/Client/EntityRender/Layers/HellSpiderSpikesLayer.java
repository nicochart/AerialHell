package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.HellSpiderSpikeModel;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowSpiderEntity;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class HellSpiderSpikesLayer<T extends Spider, M extends SpiderModel<T>> extends RenderLayer<T, M>
{
   private final HellSpiderSpikeModel<T> spiderSpikeModel;
   private static final ResourceLocation HELL_SPIDER_SPIKES = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/hell_spider/spikes.png");
   private static final ResourceLocation CRYSTAL_SPIDER_SPIKES = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/crystal_spider/crystals.png");
   private static final ResourceLocation SHADOW_SPIDER_SPIKES = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/shadow_spider/spikes.png");
   
   public HellSpiderSpikesLayer(RenderLayerParent<T,M> layerParent, HellSpiderSpikeModel<T> spikeModel)
   {
      super(layerParent);
      this.spiderSpikeModel = spikeModel;
   }
   
   private VertexConsumer getBuffer(MultiBufferSource bufferIn, T entity)
   {
	   if (entity instanceof HellSpiderEntity)
	   {
		   return bufferIn.getBuffer(RenderType.entityCutout(HELL_SPIDER_SPIKES));
	   }
	   else if (entity instanceof ShadowSpiderEntity)
	   {
		   return bufferIn.getBuffer(RenderType.entityCutout(SHADOW_SPIDER_SPIKES));
	   }
	   else
	   {
		   return bufferIn.getBuffer(RenderType.entityTranslucent(CRYSTAL_SPIDER_SPIKES));
	   }
   }
   
   public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {   
      if (!entitylivingbaseIn.isInvisible())
      {
          this.getParentModel().copyPropertiesTo(this.spiderSpikeModel);
          this.spiderSpikeModel.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
          VertexConsumer consumer = getBuffer(bufferIn, entitylivingbaseIn);
          this.spiderSpikeModel.renderToBuffer(matrixStackIn, consumer, packedLightIn, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}
