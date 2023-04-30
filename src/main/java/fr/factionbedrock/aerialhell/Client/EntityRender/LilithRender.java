package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.LilithModel;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LilithRender extends MobRenderer<LilithEntity, LilithModel>
{
	private static String name = "lilith";
    private static final ResourceLocation LILITH = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
    private static final ResourceLocation EVIL_LILITH = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_evil.png");
	
    public LilithRender(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new LilithModel(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(LilithEntity entity)
    {
		if (entity.isTransformed()) {return EVIL_LILITH;}
		else {return LILITH;}
    }
}