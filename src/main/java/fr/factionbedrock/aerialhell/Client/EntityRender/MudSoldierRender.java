package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.MudSoldierRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralSoldierEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MudSoldierRender<E extends MudSoldierEntity> extends AbstractSkeletonRenderer<E, MudSoldierRenderState>
{
	private static String name = "mud_soldier";
	private static final Identifier NORMAL = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	private static final Identifier SPECTRAL = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/mud_spectral_soldier.png");
	
    public MudSoldierRender(EntityRendererProvider.Context context)
	{
		super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_ARMOR);
	}

	@Override public MudSoldierRenderState createRenderState() {return new MudSoldierRenderState();}

	@Override public void extractRenderState(E entity, MudSoldierRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.texture = getTextureLocation(entity);
	}

	@Override public Identifier getTextureLocation(MudSoldierRenderState renderState) {return renderState.texture;}

	public Identifier getTextureLocation(E entity)
    {
		if (entity instanceof MudSpectralSoldierEntity) {return SPECTRAL;}
		else {return NORMAL;}
    }
}