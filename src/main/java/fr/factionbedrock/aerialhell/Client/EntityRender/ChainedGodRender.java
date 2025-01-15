package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ChainedGodModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ChainedGodRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ChainedGodRender extends MobRenderer<ChainedGodEntity, ChainedGodRenderState, ChainedGodModel>
{
	private static String name = "chained_god";
    private static final ResourceLocation CHAINED = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
    private static final ResourceLocation UNCHAINED = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_unchained.png");
	
    public ChainedGodRender(EntityRendererProvider.Context context)
	{
		super(context, new ChainedGodModel(context.bakeLayer(AerialHellModelLayers.CHAINED_GOD)), 1.0F);
	}

	@Override public ChainedGodRenderState createRenderState() {return new ChainedGodRenderState();}

	@Override public void extractRenderState(ChainedGodEntity entity, ChainedGodRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		renderState.texture = getTextureLocation(entity);
		renderState.attackTimer = entity.attackTimer;
		renderState.freelyMoving = entity.isFreelyMoving();
	}

	private ResourceLocation getTextureLocation(ChainedGodEntity entity)
    {
		if (entity.isInAnyPhaseBeforeSecondPhase()) {return CHAINED;}
		else {return UNCHAINED;}
    }

	@Override public ResourceLocation getTextureLocation(ChainedGodRenderState renderState) {return renderState.texture;}
}