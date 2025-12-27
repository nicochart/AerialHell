package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemCrystalModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalGolemRenderState;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemModel;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;

public class CrystalGolemCrystalLayer<T extends CrystalGolemRenderState, M extends CrystalGolemModel<CrystalGolemRenderState>> extends FeatureRenderer<T,M>
{
   private final CrystalGolemCrystalModel<T> golemModel;
   private static final Identifier CRYSTAL_GOLEM_CRYSTALS = Identifier.of(AerialHell.MODID, "textures/entity/crystal_golem/crystal_golem.png");
   
   public CrystalGolemCrystalLayer(FeatureRendererContext<T,M> layerParent, CrystalGolemCrystalModel<T> crystalModel)
   {
      super(layerParent);
      golemModel = crystalModel;
   }

   @Override public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, T renderState, float limbAngle, float limbDistance)
   {
      if (!renderState.invisible)
      {
         this.golemModel.setAngles(renderState);
         queue.submitModel(this.golemModel, renderState, matrices, RenderLayers.entityTranslucent(CRYSTAL_GOLEM_CRYSTALS), light, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
      }
   }
}
