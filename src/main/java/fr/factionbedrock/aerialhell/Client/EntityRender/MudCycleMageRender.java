package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudCycleMageModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

public class MudCycleMageRender extends MobEntityRenderer<MobEntity, MudCycleMageModel>
{
	private static String name = "mud_cycle_mage";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	
    public MudCycleMageRender(EntityRendererFactory.Context context)
	{
    	super(context, new MudCycleMageModel(context.getPart(AerialHellModelLayers.MUD_CYCLE_MAGE)), 0.5F);
	}

	@Override
	public Identifier getTexture(MobEntity entity) {return TEXTURE;}
}