package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.GoldenWalkerModel;
import fr.factionbedrock.aerialhell.Entity.Monster.GoldenWalkerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldenWalkerEyesLayer<T extends GoldenWalkerEntity, M extends GoldenWalkerModel<T>> extends AbstractEyesLayer<T, M>
{
	private static String name = "golden_walker";
    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + "_eyes.png"));

    public GoldenWalkerEyesLayer(IEntityRenderer<T, M> rendererIn)
    {
        super(rendererIn);
    }

    @Override
    public RenderType getRenderType()
    {return TEXTURE;}
}
