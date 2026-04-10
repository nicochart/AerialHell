package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.BoarModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.BoarEntityRenderState;
import fr.factionbedrock.aerialhell.Entity.Neutral.BoarEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class BoarRender<T extends BoarEntity> extends MobRenderer<T, BoarEntityRenderState, BoarModel<BoarEntityRenderState>>
{
	private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/boar/stellar_boar.png");

    public BoarRender(EntityRendererProvider.Context context)
	{
		super(context, new BoarModel<>(context.bakeLayer(AerialHellModelLayers.BOAR)), 0.7f);
	}

	@Override public BoarEntityRenderState createRenderState() {return new BoarEntityRenderState();}

	@Override public Identifier getTextureLocation(BoarEntityRenderState renderState) {return renderState.texture;}

	@Override public void extractRenderState(T entity, BoarEntityRenderState renderState, float flap)
	{
		super.extractRenderState(entity, renderState, flap);
		renderState.texture = TEXTURE;
		renderState.isBaby = entity.isBaby();
	}
}
