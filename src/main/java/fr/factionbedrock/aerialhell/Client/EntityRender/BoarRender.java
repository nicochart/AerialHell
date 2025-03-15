package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.BoarModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.BoarEntityRenderState;
import fr.factionbedrock.aerialhell.Entity.Neutral.BoarEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BoarRender<T extends BoarEntity> extends MobEntityRenderer<T, BoarEntityRenderState, BoarModel<BoarEntityRenderState>>
{
	private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/boar/stellar_boar.png");

    public BoarRender(EntityRendererFactory.Context context)
	{
		super(context, new BoarModel<>(context.getPart(AerialHellModelLayers.BOAR)), 0.7f);
	}

	@Override public BoarEntityRenderState createRenderState() {return new BoarEntityRenderState();}

	@Override public Identifier getTexture(BoarEntityRenderState renderState) {return renderState.texture;}

	@Override public void updateRenderState(T entity, BoarEntityRenderState renderState, float flap)
	{
		super.updateRenderState(entity, renderState, flap);
		renderState.texture = TEXTURE;
		renderState.baby = entity.isBaby();
	}
}
