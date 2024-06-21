package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ForestCaterpillarModel;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ForestCaterpillarRender<T extends AbstractCaterpillarEntity> extends MobRenderer<T, ForestCaterpillarModel<T>>
{
	private static String name_forest = "forest_caterpillar";
	private static final ResourceLocation FOREST = new ResourceLocation(AerialHell.MODID, "textures/entity/caterpillar/" + name_forest + ".png");

    public ForestCaterpillarRender(EntityRendererProvider.Context context)
	{
		super(context, new ForestCaterpillarModel<T>(context.bakeLayer(AerialHellModelLayers.CATERPILLAR), true), 0.2f);
	}

    @Override public ResourceLocation getTextureLocation(T entity) {return FOREST;}
}
