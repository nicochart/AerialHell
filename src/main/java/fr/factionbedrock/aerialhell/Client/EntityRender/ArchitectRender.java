package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ArchitectModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ArchitectRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.ArchitectEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class ArchitectRender extends MobRenderer<ArchitectEntity, ArchitectRenderState, ArchitectModel>
{
	private static String name = "architect";
    private static final Identifier ARCHITECT = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");

    public ArchitectRender(EntityRendererProvider.Context context)
	{
		super(context, new ArchitectModel(context.bakeLayer(AerialHellModelLayers.ARCHITECT)), 1.0F);
	}

	@Override public ArchitectRenderState createRenderState() {return new ArchitectRenderState();}

	@Override public void extractRenderState(ArchitectEntity entity, ArchitectRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		renderState.texture = getTextureLocation(entity);
	}

	private Identifier getTextureLocation(ArchitectEntity entity)
    {
		return ARCHITECT;
    }

	@Override public Identifier getTextureLocation(ArchitectRenderState renderState) {return renderState.texture;}
}