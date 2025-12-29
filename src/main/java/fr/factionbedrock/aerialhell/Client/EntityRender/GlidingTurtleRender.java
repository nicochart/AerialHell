package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.GlidingTurtleModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.GlidingTurtleRenderState;
import fr.factionbedrock.aerialhell.Entity.Passive.GlidingTurtleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class GlidingTurtleRender extends MobRenderer<GlidingTurtleEntity, GlidingTurtleRenderState, GlidingTurtleModel>
{
	private static String name = "gliding_turtle";
    private static final Identifier TURTLE_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/"+ name +"/" + name + ".png");

    public GlidingTurtleRender(EntityRendererProvider.Context context)
    {
        super(context, new GlidingTurtleModel(context.bakeLayer(AerialHellModelLayers.GLIDING_TURTLE)), 0.75F);
    }

    @Override public void extractRenderState(GlidingTurtleEntity entity, GlidingTurtleRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.isGliding = entity.isGliding();
    }

    @Override public GlidingTurtleRenderState createRenderState() {return new GlidingTurtleRenderState();}

    @Override public Identifier getTextureLocation(GlidingTurtleRenderState renderState) {return TURTLE_TEXTURE;}
}