package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.HellSpiderSpikeModel;
import fr.factionbedrock.aerialhell.Entity.Monster.HellSpiderEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HellSpiderSpikesLayer<T extends SpiderEntity, M extends SpiderModel<T>> extends LayerRenderer<T, M>
{
   private final HellSpiderSpikeModel<T> SpiderSpikeModel = new HellSpiderSpikeModel<T>();
   private static final ResourceLocation HELL_SPIDER_SPIKES = new ResourceLocation(AerialHell.MODID, "textures/entity/hell_spider/spikes.png");
   private static final ResourceLocation CRYSTAL_SPIDER_SPIKES = new ResourceLocation(AerialHell.MODID, "textures/entity/crystal_spider/crystals.png");
   
   public HellSpiderSpikesLayer(IEntityRenderer<T,M> p_i50923_1_)
   {
      super(p_i50923_1_);
   }
   
   private IVertexBuilder getVertex(IRenderTypeBuffer bufferIn, T entity)
   {
	   if (entity instanceof HellSpiderEntity)
	   {
		   return bufferIn.getBuffer(RenderType.getEntityCutout(HELL_SPIDER_SPIKES));
	   }
	   else
	   {
		   return bufferIn.getBuffer(RenderType.getEntityTranslucent(CRYSTAL_SPIDER_SPIKES));
	   }
   }
   
   public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {   
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getEntityModel().copyModelAttributesTo(this.SpiderSpikeModel);
         this.SpiderSpikeModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         IVertexBuilder ivertexbuilder = getVertex(bufferIn, entitylivingbaseIn);
         this.SpiderSpikeModel.render(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getPackedOverlay(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
      }
   }
}
