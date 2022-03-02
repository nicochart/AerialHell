package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.TornSpiritModel;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TornSpiritRender extends MobRenderer<TornSpiritEntity, TornSpiritModel>
{
	private static String name = "torn_spirit";
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	
    public TornSpiritRender(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new TornSpiritModel(), 0.3F);
	}

	@Override
	public ResourceLocation getEntityTexture(TornSpiritEntity entity)
    {
		return TEXTURE;
    }
}