package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenPartModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenPartEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class VoluciteWardenPartRender extends MobRenderer<VoluciteWardenPartEntity, VoluciteWardenRenderState, VoluciteWardenPartModel>
{
	private final VoluciteWardenPartModel.Part part;

    public VoluciteWardenPartRender(EntityRendererProvider.Context context, VoluciteWardenPartModel.Part part)
	{
		super(context, new VoluciteWardenPartModel(context.bakeLayer(part.getModelLayerLocation()), part), 1.0F);
        this.part = part;
    }

	@Override public VoluciteWardenRenderState createRenderState() {return new VoluciteWardenRenderState();}

	@Override public void extractRenderState(VoluciteWardenPartEntity entity, VoluciteWardenRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		renderState.texture = getTextureLocation(entity, this.part);
	}

	private Identifier getTextureLocation(VoluciteWardenPartEntity entity, VoluciteWardenPartModel.Part part)
    {
		return getPartTextureLocation(part);
    }

	private Identifier getPartTextureLocation(VoluciteWardenPartModel.Part part) {return getPartTextureLocation(part, "");}
	private Identifier getPartTextureLocation(VoluciteWardenPartModel.Part part, String variant)
	{
		String variantString = variant.isEmpty() ? "" : "_" + variant;
		return Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/" + part.getName() + variantString + ".png");
	}

	@Override public Identifier getTextureLocation(VoluciteWardenRenderState renderState) {return renderState.texture;}
}