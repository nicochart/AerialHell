package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.BoarModel;
import fr.factionbedrock.aerialhell.Entity.Neutral.BoarEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BoarRender<T extends BoarEntity> extends MobRenderer<T, BoarModel<T>>
{
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/boar/stellar_boar.png");

    public BoarRender(EntityRendererProvider.Context context)
	{
		super(context, new BoarModel<T>(context.bakeLayer(AerialHellModelLayers.BOAR)), 0.7f);
	}

    @Override public ResourceLocation getTextureLocation(T entity) {return TEXTURE;}
}
