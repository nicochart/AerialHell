package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ForestCaterpillarModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CaterpillarRenderState;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import java.awt.*;

public class CaterpillarRender<T extends AbstractCaterpillarEntity> extends MobEntityRenderer<T, CaterpillarRenderState, ForestCaterpillarModel<CaterpillarRenderState>>
{
	private static String name_crystal = "crystal_caterpillar";
	private static final Identifier CRYSTAL = Identifier.of(AerialHell.MODID, "textures/entity/caterpillar/" + name_crystal + ".png");

    public CaterpillarRender(EntityRendererFactory.Context context)
	{
		super(context, new ForestCaterpillarModel<CaterpillarRenderState>(context.getPart(AerialHellModelLayers.CATERPILLAR), false), 0.2f);
	}

	@Override public CaterpillarRenderState createRenderState() {return new CaterpillarRenderState();}

	@Override public Identifier getTexture(CaterpillarRenderState renderState) {return CRYSTAL;}

	@Override public void updateRenderState(T entity, CaterpillarRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);
		//Crystal caterpillar is not colored
		renderState.grassARGB = 0;//new Color(BiomeColors.getAverageGrassColor(entity.level(), entity.getOnPos())).getRGB();
		renderState.foliageARGB = 0;//new Color(BiomeColors.getAverageFoliageColor(entity.level(), entity.getOnPos())).getRGB();
	}
}