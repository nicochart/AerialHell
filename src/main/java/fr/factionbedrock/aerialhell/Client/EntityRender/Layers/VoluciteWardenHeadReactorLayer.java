package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenPartModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenPartRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;

//reactor under the head, only visible when the head is detached (final phase). texture is transparent except the bottom face reactor
public class VoluciteWardenHeadReactorLayer extends RenderLayer<VoluciteWardenPartRenderState, VoluciteWardenPartModel>
{
    private static final Identifier REACTOR_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/head_reactor.png");

    public VoluciteWardenHeadReactorLayer(RenderLayerParent<VoluciteWardenPartRenderState, VoluciteWardenPartModel> layerParent) {super(layerParent);}

    @Override public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, VoluciteWardenPartRenderState renderState, float yRot, float xRot)
    {
        if (!renderState.shouldRenderReactor) {return;}
        coloredCutoutModelCopyLayerRender(this.getParentModel(), REACTOR_TEXTURE, poseStack, submitNodeCollector, packedLight, renderState, -1, 1);
    }
}
