package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ForestCaterpillarModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CaterpillarRenderState;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class CaterpillarRender<T extends AbstractCaterpillarEntity> extends MobRenderer<T, CaterpillarRenderState, ForestCaterpillarModel<CaterpillarRenderState>>
{
	private static String name_crystal = "crystal_caterpillar";
	private static final ResourceLocation CRYSTAL = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/caterpillar/" + name_crystal + ".png");

    public CaterpillarRender(EntityRendererProvider.Context context)
	{
		super(context, new ForestCaterpillarModel<CaterpillarRenderState>(context.bakeLayer(AerialHellModelLayers.CATERPILLAR), false), 0.2f);
	}

	@Override public CaterpillarRenderState createRenderState() {return new CaterpillarRenderState();}

	@Override public ResourceLocation getTextureLocation(CaterpillarRenderState renderState) {return CRYSTAL;}

	@Override public void extractRenderState(T entity, CaterpillarRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		//Crystal caterpillar is not colored
		renderState.grassARGB = 0;//new Color(BiomeColors.getAverageGrassColor(entity.level(), entity.getOnPos())).getRGB();
		renderState.foliageARGB = 0;//new Color(BiomeColors.getAverageFoliageColor(entity.level(), entity.getOnPos())).getRGB();
	}
}