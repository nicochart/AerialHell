package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ForestCaterpillarModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CaterpillarRenderState;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

import java.awt.*;

public class ForestCaterpillarRender<T extends AbstractCaterpillarEntity> extends MobRenderer<T, CaterpillarRenderState, ForestCaterpillarModel<CaterpillarRenderState>>
{
	private static String name_forest = "forest_caterpillar";
	private static final Identifier FOREST = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/caterpillar/" + name_forest + ".png");

    public ForestCaterpillarRender(EntityRendererProvider.Context context)
	{
		super(context, new ForestCaterpillarModel<>(context.bakeLayer(AerialHellModelLayers.CATERPILLAR), true), 0.2f);
	}

	@Override
	public CaterpillarRenderState createRenderState() {return new CaterpillarRenderState();}

	@Override public Identifier getTextureLocation(CaterpillarRenderState renderState) {return FOREST;}

	@Override public void extractRenderState(T entity, CaterpillarRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		renderState.grassARGB = new Color(BiomeColors.getAverageGrassColor(entity.level(), entity.getOnPos())).getRGB();
		renderState.foliageARGB = new Color(BiomeColors.getAverageFoliageColor(entity.level(), entity.getOnPos())).getRGB();
	}
}
