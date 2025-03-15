package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SnakeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.SnakeRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.VenomousSnakeEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class SnakeRender<E extends AbstractSnakeEntity> extends MobEntityRenderer<E, SnakeRenderState, SnakeModel>
{
    private static final Identifier VENOMOUS_HEAD = Identifier.of(AerialHell.MODID, "textures/entity/snake/venomous_head.png");
    private static final Identifier VENOMOUS_BODY = Identifier.of(AerialHell.MODID, "textures/entity/snake/venomous_body.png");
    private static final Identifier WORM_HEAD = Identifier.of(AerialHell.MODID, "textures/entity/snake/worm_head.png");
    private static final Identifier WORM_BODY = Identifier.of(AerialHell.MODID, "textures/entity/snake/worm_body.png");

    public SnakeRender(EntityRendererFactory.Context context)
    {
        super(context, new SnakeModel(context.getPart(AerialHellModelLayers.SNAKE)), 0.3f);
    }

    @Override public SnakeRenderState createRenderState() {return new SnakeRenderState();}

    @Override public void updateRenderState(E entity, SnakeRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.texture = getTexture(entity);
    }

    public Identifier getTexture(E entity)
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

    @Override public Identifier getTexture(SnakeRenderState renderState) {return renderState.texture;}
}