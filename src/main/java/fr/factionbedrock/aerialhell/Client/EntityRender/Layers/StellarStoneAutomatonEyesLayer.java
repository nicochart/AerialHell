package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AutomatonRenderState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

public class StellarStoneAutomatonEyesLayer<T extends AutomatonRenderState, M extends AutomatonModel<T>> extends EyesFeatureRenderer<T, M>
{
	private static String name = "stellar_stone_automaton";
    private static final RenderLayer TEXTURE = RenderLayer.getEyes(Identifier.of(AerialHell.MODID, "textures/entity/automaton/" + name + "_eyes.png"));

    public StellarStoneAutomatonEyesLayer(FeatureRendererContext<T, M> layerParent) {super(layerParent);}

    @Override public RenderLayer getEyesTexture() {return TEXTURE;}
}
