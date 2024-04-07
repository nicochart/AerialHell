package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SnakeModel;
import fr.factionbedrock.aerialhell.Entity.Monster.Snake.VenomousSnakeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnakeRender<E extends VenomousSnakeEntity> extends MobRenderer<E, SnakeModel<E>>
{
    private static final ResourceLocation HEAD = new ResourceLocation(AerialHell.MODID, "textures/entity/snake/venomous_head.png");
    private static final ResourceLocation BODY = new ResourceLocation(AerialHell.MODID, "textures/entity/snake/venomous_body.png");

    public SnakeRender(EntityRendererProvider.Context context)
    {
        super(context, new SnakeModel(context.bakeLayer(AerialHellModelLayers.SNAKE)), 0.3f);
    }

    @Override public ResourceLocation getTextureLocation(E entity)
    {
        return entity.isHead() ? HEAD : BODY;
    }
}