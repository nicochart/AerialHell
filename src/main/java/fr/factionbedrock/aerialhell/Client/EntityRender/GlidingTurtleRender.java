package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.GlidingTurtleModel;
import fr.factionbedrock.aerialhell.Entity.Passive.GlidingTurtleEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GlidingTurtleRender extends MobEntityRenderer<GlidingTurtleEntity, GlidingTurtleModel>
{
	private static String name = "gliding_turtle";
    private static final Identifier TURTLE_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/"+ name +"/" + name + ".png");

    public GlidingTurtleRender(EntityRendererFactory.Context context)
    {
        super(context, new GlidingTurtleModel(context.getPart(AerialHellModelLayers.GLIDING_TURTLE)), 0.75F);
    }

    @Override
    public Identifier getTexture(GlidingTurtleEntity entity)
    {
        return TURTLE_TEXTURE;
    }
}