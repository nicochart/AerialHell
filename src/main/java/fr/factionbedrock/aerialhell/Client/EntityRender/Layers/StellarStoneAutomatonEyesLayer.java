package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Entity.Monster.StellarStoneAutomatonEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StellarStoneAutomatonEyesLayer<T extends StellarStoneAutomatonEntity, M extends AutomatonModel<T>> extends AbstractEyesLayer<T, M>
{
	private static String name = "stellar_stone_automaton";
    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + "_eyes.png"));

    public StellarStoneAutomatonEyesLayer(IEntityRenderer<T, M> rendererIn) {super(rendererIn);}

    @Override
    public RenderType getRenderType() {return TEXTURE;}
}
