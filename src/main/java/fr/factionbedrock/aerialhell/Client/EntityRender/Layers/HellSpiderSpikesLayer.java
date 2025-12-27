package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.HellSpiderSpikeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.HellSpiderRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowSpiderEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.Identifier;

import java.awt.*;

public class HellSpiderSpikesLayer<S extends HellSpiderRenderState, M extends SpiderEntityModel> extends FeatureRenderer<S, M>
{
    private final HellSpiderSpikeModel spiderSpikeModel;
    private static final Identifier HELL_SPIDER_SPIKES = Identifier.of(AerialHell.MODID, "textures/entity/hell_spider/spikes.png");
    private static final Identifier CRYSTAL_SPIDER_SPIKES = Identifier.of(AerialHell.MODID, "textures/entity/crystal_spider/crystals.png");
    private static final Identifier SHADOW_SPIDER_SPIKES = Identifier.of(AerialHell.MODID, "textures/entity/shadow_spider/spikes.png");

    public HellSpiderSpikesLayer(FeatureRendererContext<S,M> layerParent, HellSpiderSpikeModel spikeModel)
    {
      super(layerParent);
      this.spiderSpikeModel = spikeModel;
    }
   
    private RenderLayer getLayer(S renderState)
    {
       if (renderState.layer_texture == CRYSTAL_SPIDER_SPIKES)
       {
           return RenderLayers.entityTranslucent(CRYSTAL_SPIDER_SPIKES);
       }
       else
       {
           return RenderLayers.entityCutout(renderState.layer_texture);
       }
    }

    public static <T extends SpiderEntity> Identifier getTexture(T entity)
    {
        if (entity instanceof HellSpiderEntity) {return HELL_SPIDER_SPIKES;}
        else if (entity instanceof ShadowSpiderEntity) {return CRYSTAL_SPIDER_SPIKES;}
        else {return SHADOW_SPIDER_SPIKES;}
    }

    @Override public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, S renderState, float limbAngle, float limbDistance)
    {
        if (!renderState.invisible)
        {
            this.spiderSpikeModel.setAngles(renderState);
            queue.submitModel(this.spiderSpikeModel, renderState, matrices, getLayer(renderState), light, LivingEntityRenderer.getOverlay(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB(), null, renderState.outlineColor, null);
        }
    }
}
