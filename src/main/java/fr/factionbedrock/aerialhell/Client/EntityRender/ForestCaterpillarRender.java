package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ForestCaterpillarModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CaterpillarRenderState;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

import java.awt.*;

public class ForestCaterpillarRender<T extends AbstractCaterpillarEntity> extends MobEntityRenderer<T, CaterpillarRenderState, ForestCaterpillarModel<CaterpillarRenderState>>
{
	private static String name_forest = "forest_caterpillar";
	private static final Identifier FOREST = Identifier.of(AerialHell.MODID, "textures/entity/caterpillar/" + name_forest + ".png");

    public ForestCaterpillarRender(EntityRendererFactory.Context context)
	{
		super(context, new ForestCaterpillarModel<>(context.getPart(AerialHellModelLayers.CATERPILLAR), true), 0.2f);
	}

	@Override
	public CaterpillarRenderState createRenderState() {return new CaterpillarRenderState();}

	@Override public Identifier getTexture(CaterpillarRenderState renderState) {return FOREST;}

	@Override public void updateRenderState(T entity, CaterpillarRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);
		renderState.grassARGB = new Color(BiomeColors.getGrassColor(entity.getWorld(), entity.getSteppingPos())).getRGB();
		renderState.foliageARGB = new Color(BiomeColors.getFoliageColor(entity.getWorld(), entity.getSteppingPos())).getRGB();
	}
}
