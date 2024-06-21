package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SnakeModel;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.VenomousSnakeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SnakeRender<E extends AbstractSnakeEntity> extends MobRenderer<E, SnakeModel<E>>
{
    private static final ResourceLocation VENOMOUS_HEAD = new ResourceLocation(AerialHell.MODID, "textures/entity/snake/venomous_head.png");
    private static final ResourceLocation VENOMOUS_BODY = new ResourceLocation(AerialHell.MODID, "textures/entity/snake/venomous_body.png");
    private static final ResourceLocation WORM_HEAD = new ResourceLocation(AerialHell.MODID, "textures/entity/snake/worm_head.png");
    private static final ResourceLocation WORM_BODY = new ResourceLocation(AerialHell.MODID, "textures/entity/snake/worm_body.png");

    public SnakeRender(EntityRendererProvider.Context context)
    {
        super(context, new SnakeModel(context.bakeLayer(AerialHellModelLayers.SNAKE)), 0.3f);
    }

    @Override public ResourceLocation getTextureLocation(E entity)
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
}