package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudCycleMageModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.MudCycleMageRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;

public class MudCycleMageRender<E extends HostileEntity> extends MobEntityRenderer<E, MudCycleMageRenderState, MudCycleMageModel>
{
	private static String name = "mud_cycle_mage";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	
    public MudCycleMageRender(EntityRendererFactory.Context context)
	{
    	super(context, new MudCycleMageModel(context.getPart(AerialHellModelLayers.MUD_CYCLE_MAGE)), 0.5F);
	}

	@Override public MudCycleMageRenderState createRenderState() {return new MudCycleMageRenderState();}

	@Override public void updateRenderState(E entity, MudCycleMageRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.deadOrDyingPhase = entity instanceof MudCycleMageEntity mudCycleMage && mudCycleMage.isInDeadOrDyingPhase();
	}

	@Override public Identifier getTexture(MudCycleMageRenderState renderState) {return TEXTURE;}
}