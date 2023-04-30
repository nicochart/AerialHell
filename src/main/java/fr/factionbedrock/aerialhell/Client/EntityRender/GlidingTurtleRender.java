package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.GlidingTurtleModel;
import fr.factionbedrock.aerialhell.Entity.Passive.GlidingTurtleEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GlidingTurtleRender extends MobRenderer<GlidingTurtleEntity, GlidingTurtleModel>
{
	private static String name = "gliding_turtle";
    private static final ResourceLocation TURTLE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/"+ name +"/" + name + ".png");

    public GlidingTurtleRender(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new GlidingTurtleModel(), 0.75F);
    }

    @Override
    public ResourceLocation getEntityTexture(GlidingTurtleEntity entity)
    {
        return TURTLE_TEXTURE;
    }
}