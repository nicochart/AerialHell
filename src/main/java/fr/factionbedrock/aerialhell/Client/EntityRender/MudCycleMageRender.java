package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudCycleMageModel;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MudCycleMageRender extends MobRenderer<MudCycleMageEntity, MudCycleMageModel>
{
	private static String name = "mud_cycle_mage";
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	
    public MudCycleMageRender(EntityRendererManager renderManagerIn)
	{
    	super(renderManagerIn, new MudCycleMageModel(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(MudCycleMageEntity entity) {return TEXTURE;}
}