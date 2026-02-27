package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenArmModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class VoluciteWardenArmRender extends MobRenderer<VoluciteWardenArmEntity, VoluciteWardenRenderState, VoluciteWardenArmModel>
{
	private static String name = "volucite_warden";
	private static String part_name = "arm";
    private static final Identifier VOLUCITE_WARDEN = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + part_name + ".png");

    public VoluciteWardenArmRender(EntityRendererProvider.Context context)
	{
		super(context, new VoluciteWardenArmModel(context.bakeLayer(AerialHellModelLayers.VOLUCITE_WARDEN_ARM)), 1.0F);
	}

	@Override public VoluciteWardenRenderState createRenderState() {return new VoluciteWardenRenderState();}

	@Override public void extractRenderState(VoluciteWardenArmEntity entity, VoluciteWardenRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		renderState.texture = getTextureLocation(entity);
	}

	private Identifier getTextureLocation(VoluciteWardenArmEntity entity)
    {
		return VOLUCITE_WARDEN;
    }

	@Override public Identifier getTextureLocation(VoluciteWardenRenderState renderState) {return renderState.texture;}
}