package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudCycleMageModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.MudCycleMageRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Monster;

public class MudCycleMageRender<E extends Monster> extends MobRenderer<E, MudCycleMageRenderState, MudCycleMageModel>
{
	private static String name = "mud_cycle_mage";
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	
    public MudCycleMageRender(EntityRendererProvider.Context context)
	{
    	super(context, new MudCycleMageModel(context.bakeLayer(AerialHellModelLayers.MUD_CYCLE_MAGE)), 0.5F);
	}

	@Override public MudCycleMageRenderState createRenderState() {return new MudCycleMageRenderState();}

	@Override public void extractRenderState(E entity, MudCycleMageRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.deadOrDyingPhase = entity instanceof MudCycleMageEntity mudCycleMage && mudCycleMage.isInDeadOrDyingPhase();
	}

	@Override public Identifier getTextureLocation(MudCycleMageRenderState renderState) {return TEXTURE;}
}