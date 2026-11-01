package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenHealBeaconModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenHealBeaconRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenHealBeaconEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import org.jspecify.annotations.Nullable;

//glowing core of the heal beacon, depending on its state (no layer when inactive)
public class VoluciteWardenHealBeaconStateLayer extends RenderLayer<VoluciteWardenHealBeaconRenderState, VoluciteWardenHealBeaconModel>
{
    private static final Identifier ACTIVE_TEXTURE = getTexture("active");
    private static final Identifier HEALING_TEXTURE = getTexture("healing");
    private static final Identifier ATTACKING_TEXTURE = getTexture("attacking");

    public VoluciteWardenHealBeaconStateLayer(RenderLayerParent<VoluciteWardenHealBeaconRenderState, VoluciteWardenHealBeaconModel> layerParent) {super(layerParent);}

    @Override public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, VoluciteWardenHealBeaconRenderState renderState, float yRot, float xRot)
    {
        @Nullable Identifier texture = getStateTexture(renderState.beaconState);
        if (texture == null) {return;}
        submitNodeCollector.order(1).submitModel(this.getParentModel(), renderState, poseStack, RenderTypes.entityTranslucentEmissive(texture), LightCoordsUtil.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, renderState.outlineColor);
    }

    @Nullable private static Identifier getStateTexture(int beaconState)
    {
        return switch (beaconState)
        {
            case VoluciteWardenHealBeaconEntity.ACTIVE -> ACTIVE_TEXTURE;
            case VoluciteWardenHealBeaconEntity.HEALING -> HEALING_TEXTURE;
            case VoluciteWardenHealBeaconEntity.ATTACKING -> ATTACKING_TEXTURE;
            default -> null;
        };
    }

    private static Identifier getTexture(String name) {return Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/heal_beacon/" + name + ".png");}
}
