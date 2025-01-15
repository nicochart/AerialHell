package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.HellSpiderSpikeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.HellSpiderRenderState;
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

public class HellSpiderSpikesLayer<S extends HellSpiderRenderState, M extends SpiderModel> extends RenderLayer<S, M>
{
    private final HellSpiderSpikeModel spiderSpikeModel;
    private static final ResourceLocation HELL_SPIDER_SPIKES = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/hell_spider/spikes.png");
    private static final ResourceLocation CRYSTAL_SPIDER_SPIKES = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/crystal_spider/crystals.png");
    private static final ResourceLocation SHADOW_SPIDER_SPIKES = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/shadow_spider/spikes.png");

    public HellSpiderSpikesLayer(RenderLayerParent<S,M> layerParent, HellSpiderSpikeModel spikeModel)
    {
      super(layerParent);
      this.spiderSpikeModel = spikeModel;
    }
   
    private VertexConsumer getBuffer(MultiBufferSource bufferIn, S renderState)
    {
       if (renderState.layer_texture == CRYSTAL_SPIDER_SPIKES)
       {
           return bufferIn.getBuffer(RenderType.entityTranslucent(CRYSTAL_SPIDER_SPIKES));
       }
       else
       {
           return bufferIn.getBuffer(RenderType.entityCutout(renderState.layer_texture));
       }
    }

    @Override public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S renderState, float yRot, float xRot)
    {
        if (!renderState.isInvisible)
        {
            //this.getParentModel().copyPropertiesTo(this.cortinariusCowShroomModel); TODO is it still needed or is it done with renderState ?
            this.spiderSpikeModel.setupAnim(renderState);
            VertexConsumer consumer = this.getBuffer(bufferSource, renderState);
            this.spiderSpikeModel.renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(renderState, 0.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB());
        }
    }
}
