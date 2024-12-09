package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ForestCaterpillarModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CaterpillarRender<T extends AbstractCaterpillarEntity> extends MobEntityRenderer<T, ForestCaterpillarModel<T>>
{
	private static String name_crystal = "crystal_caterpillar";
	private static final Identifier CRYSTAL = Identifier.of(AerialHell.MODID, "textures/entity/caterpillar/" + name_crystal + ".png");

    public CaterpillarRender(EntityRendererFactory.Context context)
	{
		super(context, new ForestCaterpillarModel<T>(context.getPart(AerialHellModelLayers.CATERPILLAR), false), 0.2f);
	}

    @Override public Identifier getTexture(T entity) {return CRYSTAL;}
}