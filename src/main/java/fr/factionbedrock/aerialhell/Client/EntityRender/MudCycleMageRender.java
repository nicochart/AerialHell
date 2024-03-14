package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudCycleMageModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class MudCycleMageRender extends MobRenderer<Mob, MudCycleMageModel>
{
	private static String name = "mud_cycle_mage";
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	
    public MudCycleMageRender(EntityRendererProvider.Context context)
	{
    	super(context, new MudCycleMageModel(context.bakeLayer(AerialHellModelLayers.MUD_CYCLE_MAGE)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(Mob entity) {return TEXTURE;}
}