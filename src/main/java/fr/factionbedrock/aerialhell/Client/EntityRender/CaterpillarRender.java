package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ForestCaterpillarModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CaterpillarRender<T extends AbstractCaterpillarEntity> extends MobRenderer<T, ForestCaterpillarModel<T>>
{
	private static String name_crystal = "crystal_caterpillar";
	private static final ResourceLocation CRYSTAL = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/caterpillar/" + name_crystal + ".png");

    public CaterpillarRender(EntityRendererProvider.Context context)
	{
		super(context, new ForestCaterpillarModel<T>(context.bakeLayer(AerialHellModelLayers.CATERPILLAR), false), 0.2f);
	}

    @Override public ResourceLocation getTextureLocation(T entity) {return CRYSTAL;}
}