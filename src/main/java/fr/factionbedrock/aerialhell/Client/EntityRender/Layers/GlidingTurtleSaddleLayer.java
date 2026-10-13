package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.GlidingTurtleModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.GlidingTurtleSaddleModel;
import fr.factionbedrock.aerialhell.Entity.Passive.GlidingTurtleEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class GlidingTurtleSaddleLayer extends RenderLayer<GlidingTurtleEntity, GlidingTurtleModel>
{
   private final GlidingTurtleSaddleModel saddleModel;
   private static final ResourceLocation GLIDING_TURTLE_SADDLE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/gliding_turtle/gliding_turtle_saddle.png");

   public GlidingTurtleSaddleLayer(RenderLayerParent<GlidingTurtleEntity, GlidingTurtleModel> layerParent, GlidingTurtleSaddleModel saddleModel)
   {
      super(layerParent);
      this.saddleModel = saddleModel;
   }

   @Override public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, GlidingTurtleEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
   {
      if (entity.isSaddled() && !entity.isInvisible())
      {
         this.getParentModel().copyPropertiesTo(this.saddleModel);

         this.saddleModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

         VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(GLIDING_TURTLE_SADDLE));

         this.saddleModel.renderToBuffer(poseStack, vertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
      }
   }
}