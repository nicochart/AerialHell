package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class VoluciteWardenRender extends MobRenderer<VoluciteWardenEntity, VoluciteWardenRenderState, VoluciteWardenModel>
{
	private static String name = "volucite_warden";
    private static final Identifier VOLUCITE_WARDEN = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");

    public VoluciteWardenRender(EntityRendererProvider.Context context)
	{
		super(context, new VoluciteWardenModel(context.bakeLayer(AerialHellModelLayers.VOLUCITE_WARDEN)), 1.0F);
	}

	@Override public VoluciteWardenRenderState createRenderState() {return new VoluciteWardenRenderState();}

	@Override public void extractRenderState(VoluciteWardenEntity entity, VoluciteWardenRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		renderState.texture = getTextureLocation(entity);
	}

	private Identifier getTextureLocation(VoluciteWardenEntity entity)
    {
		return VOLUCITE_WARDEN;
    }

	@Override public Identifier getTextureLocation(VoluciteWardenRenderState renderState) {return renderState.texture;}
}