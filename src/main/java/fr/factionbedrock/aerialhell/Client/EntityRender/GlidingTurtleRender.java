package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.GlidingTurtleModel;
import fr.factionbedrock.aerialhell.Entity.Passive.GlidingTurtleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GlidingTurtleRender extends MobRenderer<GlidingTurtleEntity, GlidingTurtleModel>
{
	private static String name = "gliding_turtle";
    private static final ResourceLocation TURTLE_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/"+ name +"/" + name + ".png");

    public GlidingTurtleRender(EntityRendererProvider.Context context)
    {
        super(context, new GlidingTurtleModel(context.bakeLayer(AerialHellModelLayers.GLIDING_TURTLE)), 0.75F);
    }

    @Override
    public ResourceLocation getTextureLocation(GlidingTurtleEntity entity)
    {
        return TURTLE_TEXTURE;
    }
}