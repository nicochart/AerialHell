package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CortinariusCowShroomModel;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CortinariusCowShroomLayer<T extends EvilCowEntity, M extends CowModel<T>> extends LayerRenderer<T, M>
{
   private final CortinariusCowShroomModel<T> cortinariusCowShroomModel = new CortinariusCowShroomModel<T>();
   private static final ResourceLocation CORTINARIUS_COW_SHROOM_LAYER = new ResourceLocation(AerialHell.MODID, "textures/entity/cortinarius_cow/cortinarius_cow_shroom.png");
   
   public CortinariusCowShroomLayer(IEntityRenderer<T,M> entityRender)
   {
      super(entityRender);
   }
   
   private IVertexBuilder getVertex(IRenderTypeBuffer bufferIn, T entity)
   {
	   return bufferIn.getBuffer(RenderType.getEntityCutout(CORTINARIUS_COW_SHROOM_LAYER));
   }
   
   public void render(PoseStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {   
      if (!entitylivingbaseIn.isInvisible())
      {
         this.getEntityModel().copyModelAttributesTo(this.cortinariusCowShroomModel);
         this.cortinariusCowShroomModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
         IVertexBuilder ivertexbuilder = getVertex(bufferIn, entitylivingbaseIn);
         this.cortinariusCowShroomModel.render(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getPackedOverlay(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
      }
   }
}
