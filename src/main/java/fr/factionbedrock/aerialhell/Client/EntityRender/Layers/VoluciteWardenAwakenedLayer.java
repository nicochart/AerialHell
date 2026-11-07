package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenPartModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Helper.EmissiveRenderHelper;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenPartRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;

//overlay texture of a part, only rendered once the warden has awakened (dormant warden : parts look like volucite stone)
public class VoluciteWardenAwakenedLayer extends RenderLayer<VoluciteWardenPartRenderState, VoluciteWardenPartModel>
{
    private final Identifier texture;
    private final EmissiveRenderHelper.Mode mode;
    private final int order; //render order over the part model (base model is 0). semi emissive mode uses order and order + 1

    public VoluciteWardenAwakenedLayer(RenderLayerParent<VoluciteWardenPartRenderState, VoluciteWardenPartModel> layerParent, String textureName, EmissiveRenderHelper.Mode mode, int order)
    {
        super(layerParent);
        this.texture = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/" + textureName + ".png");
        this.mode = mode;
        this.order = order;
    }

    @Override public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, VoluciteWardenPartRenderState renderState, float yRot, float xRot)
    {
        if (renderState.isDormant) {return;}
        EmissiveRenderHelper.submit(this.mode, this.getParentModel(), this.texture, poseStack, submitNodeCollector, packedLight, renderState, this.order);
    }
}
