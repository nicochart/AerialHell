package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.TornSpiritModel;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TornSpiritRender extends MobRenderer<TornSpiritEntity, TornSpiritModel>
{
	private static String name = "torn_spirit";
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	
    public TornSpiritRender(EntityRendererProvider.Context context)
	{
		super(context, new TornSpiritModel(context.bakeLayer(AerialHellModelLayers.TORN_SPIRIT)), 0.3F);
	}

	@Override public ResourceLocation getTextureLocation(TornSpiritEntity entity)
    {
		return TEXTURE;
    }
}