package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.MudSoldierRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudSpectralSoldierEntity;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class MudSoldierRender<E extends MudSoldierEntity> extends AbstractSkeletonEntityRenderer<E, MudSoldierRenderState>
{
	private static String name = "mud_soldier";
	private static final Identifier NORMAL = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	private static final Identifier SPECTRAL = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/mud_spectral_soldier.png");
	
    public MudSoldierRender(EntityRendererFactory.Context context)
	{
		super(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
	}

	@Override public MudSoldierRenderState createRenderState() {return new MudSoldierRenderState();}

	@Override public void updateRenderState(E entity, MudSoldierRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.texture = getTexture(entity);
	}

	@Override public Identifier getTexture(MudSoldierRenderState renderState) {return renderState.texture;}

	public Identifier getTexture(E entity)
    {
		if (entity instanceof MudSpectralSoldierEntity) {return SPECTRAL;}
		else {return NORMAL;}
    }
}