package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.LilithModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.LilithRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class LilithRender extends MobRenderer<LilithEntity, LilithRenderState, LilithModel>
{
	private static String name = "lilith";
    private static final Identifier LILITH = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
    private static final Identifier EVIL_LILITH = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_evil.png");
	
    public LilithRender(EntityRendererProvider.Context context)
	{
		super(context, new LilithModel(context.bakeLayer(AerialHellModelLayers.LILITH)), 0.5F);
	}

	@Override public LilithRenderState createRenderState() {return new LilithRenderState();}

	@Override public void extractRenderState(LilithEntity entity, LilithRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.texture = getTextureLocation(entity);
		renderState.isTransforming = entity.isTransforming();
		renderState.attackTimer = entity.attackTimer;
	}

	@Override public Identifier getTextureLocation(LilithRenderState renderState) {return renderState.texture;}

	public Identifier getTextureLocation(LilithEntity entity)
    {
		if (entity.isTransformed()) {return EVIL_LILITH;}
		else {return LILITH;}
    }
}