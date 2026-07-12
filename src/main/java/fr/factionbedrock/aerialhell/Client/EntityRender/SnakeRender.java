package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SnakeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.SnakeRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.VenomousSnakeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SnakeRender<E extends AbstractSnakeEntity> extends MobRenderer<E, SnakeRenderState, SnakeModel>
{
    private static final ResourceLocation VENOMOUS_HEAD = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/snake/venomous_head.png");
    private static final ResourceLocation VENOMOUS_BODY = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/snake/venomous_body.png");
    private static final ResourceLocation WORM_HEAD = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/snake/worm_head.png");
    private static final ResourceLocation WORM_BODY = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/snake/worm_body.png");

    public SnakeRender(EntityRendererProvider.Context context)
    {
        super(context, new SnakeModel(context.bakeLayer(AerialHellModelLayers.SNAKE)), 0.3f);
    }

    @Override public SnakeRenderState createRenderState() {return new SnakeRenderState();}

    @Override public void extractRenderState(E entity, SnakeRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.texture = getTextureLocation(entity);
    }

    public ResourceLocation getTextureLocation(E entity)
    {
        if (entity instanceof VenomousSnakeEntity)
        {
            return entity.isHead() ? VENOMOUS_HEAD : VENOMOUS_BODY;
        }
        else //if (entity instanceof WormEntity)
        {
            return entity.isHead() ? WORM_HEAD : WORM_BODY;
        }
    }

    @Override public ResourceLocation getTextureLocation(SnakeRenderState renderState) {return renderState.texture;}
}