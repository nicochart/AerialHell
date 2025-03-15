package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.LilithModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.LilithRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class LilithRender extends MobEntityRenderer<LilithEntity, LilithRenderState, LilithModel>
{
	private static String name = "lilith";
    private static final Identifier LILITH = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
    private static final Identifier EVIL_LILITH = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_evil.png");
	
    public LilithRender(EntityRendererFactory.Context context)
	{
		super(context, new LilithModel(context.getPart(AerialHellModelLayers.LILITH)), 0.5F);
	}

	@Override public LilithRenderState createRenderState() {return new LilithRenderState();}

	@Override public void updateRenderState(LilithEntity entity, LilithRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.texture = getTexture(entity);
		renderState.isTransforming = entity.isTransforming();
		renderState.attackTimer = entity.attackTimer;
	}

	@Override public Identifier getTexture(LilithRenderState renderState) {return renderState.texture;}

	public Identifier getTexture(LilithEntity entity)
    {
		if (entity.isTransformed()) {return EVIL_LILITH;}
		else {return LILITH;}
    }
}