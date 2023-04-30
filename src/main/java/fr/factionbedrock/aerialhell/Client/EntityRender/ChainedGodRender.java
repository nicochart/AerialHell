package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ChainedGodModel;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ChainedGodRender extends MobRenderer<ChainedGodEntity, ChainedGodModel>
{
	private static String name = "chained_god";
    private static final ResourceLocation CHAINED = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
    private static final ResourceLocation UNCHAINED = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_unchained.png");
	
    public ChainedGodRender(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ChainedGodModel(), 1.0F);
	}

	@Override
	public ResourceLocation getEntityTexture(ChainedGodEntity entity)
    {
		if (entity.getHealth() > entity.getMaxHealth() / 3) {return CHAINED;}
		else {return UNCHAINED;}
    }
}