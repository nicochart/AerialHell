package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StellarStoneAutomatonEyesLayer<T extends AutomatonEntity, M extends AutomatonModel<T>> extends EyesLayer<T, M>
{
	private static String name = "stellar_stone_automaton";
    private static final RenderType TEXTURE = RenderType.eyes(new ResourceLocation(AerialHell.MODID, "textures/entity/automaton/" + name + "_eyes.png"));

    public StellarStoneAutomatonEyesLayer(RenderLayerParent<T, M> layerParent) {super(layerParent);}

    @Override public RenderType renderType() {return TEXTURE;}
}
