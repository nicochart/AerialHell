package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ChainedGodModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ChainedGodRender extends MobRenderer<ChainedGodEntity, ChainedGodModel>
{
	private static String name = "chained_god";
    private static final ResourceLocation CHAINED = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
    private static final ResourceLocation UNCHAINED = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_unchained.png");
	
    public ChainedGodRender(EntityRendererProvider.Context context)
	{
		super(context, new ChainedGodModel(context.bakeLayer(AerialHellModelLayers.CHAINED_GOD)), 1.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(ChainedGodEntity entity)
    {
		if (entity.getPhase() < 2) {return CHAINED;}
		else {return UNCHAINED;}
    }
}