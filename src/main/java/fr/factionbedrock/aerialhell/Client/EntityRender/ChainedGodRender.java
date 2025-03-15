package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ChainedGodModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ChainedGodRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ChainedGodRender extends MobEntityRenderer<ChainedGodEntity, ChainedGodRenderState, ChainedGodModel>
{
	private static String name = "chained_god";
    private static final Identifier CHAINED = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
    private static final Identifier UNCHAINED = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_unchained.png");
	
    public ChainedGodRender(EntityRendererFactory.Context context)
	{
		super(context, new ChainedGodModel(context.getPart(AerialHellModelLayers.CHAINED_GOD)), 1.0F);
	}

	@Override public ChainedGodRenderState createRenderState() {return new ChainedGodRenderState();}

	@Override public void updateRenderState(ChainedGodEntity entity, ChainedGodRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);
		renderState.texture = getTexture(entity);
		renderState.attackTimer = entity.attackTimer;
		renderState.freelyMoving = entity.isFreelyMoving();
	}

	private Identifier getTexture(ChainedGodEntity entity)
    {
		if (entity.isInAnyPhaseBeforeSecondPhase()) {return CHAINED;}
		else {return UNCHAINED;}
    }

	@Override public Identifier getTexture(ChainedGodRenderState renderState) {return renderState.texture;}
}