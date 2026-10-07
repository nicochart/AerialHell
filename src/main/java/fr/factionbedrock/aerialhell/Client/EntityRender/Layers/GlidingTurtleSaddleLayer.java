package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.GlidingTurtleModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.GlidingTurtleSaddleModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.GlidingTurtleRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

import java.awt.*;

public class GlidingTurtleSaddleLayer extends RenderLayer<GlidingTurtleRenderState, GlidingTurtleModel>
{
   private final GlidingTurtleSaddleModel saddleModel;
   private static final Identifier GLIDING_TURTLE_SADDLE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/gliding_turtle/gliding_turtle_saddle.png");

   public GlidingTurtleSaddleLayer(RenderLayerParent<GlidingTurtleRenderState, GlidingTurtleModel> layerParent, GlidingTurtleSaddleModel saddleModel)
   {
      super(layerParent);
      this.saddleModel = saddleModel;
   }

   @Override public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, GlidingTurtleRenderState renderState, float yRot, float xRot)
   {
      if (renderState.isSaddled && !renderState.isInvisible)
      {
         this.saddleModel.setupAnim(renderState);
         submitNodeCollector.submitModel(this.saddleModel, renderState, poseStack, RenderTypes.entityCutout(GLIDING_TURTLE_SADDLE), packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
