package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.MudSoldierRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralSoldierEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MudSoldierRender<E extends MudSoldierEntity> extends AbstractSkeletonRenderer<E, MudSoldierRenderState>
{
	private static String name = "mud_soldier";
	private static final ResourceLocation NORMAL = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	private static final ResourceLocation SPECTRAL = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/mud_spectral_soldier.png");
	
    public MudSoldierRender(EntityRendererProvider.Context context)
	{
		super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
	}

	@Override public MudSoldierRenderState createRenderState() {return new MudSoldierRenderState();}

	@Override public void extractRenderState(E entity, MudSoldierRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.texture = getTextureLocation(entity);
	}

	@Override public ResourceLocation getTextureLocation(MudSoldierRenderState renderState) {return renderState.texture;}

	public ResourceLocation getTextureLocation(E entity)
    {
		if (entity instanceof MudSpectralSoldierEntity) {return SPECTRAL;}
		else {return NORMAL;}
    }
}