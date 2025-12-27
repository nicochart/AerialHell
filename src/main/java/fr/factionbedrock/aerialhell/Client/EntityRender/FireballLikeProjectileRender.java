package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.FireballLikeProjectileRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.DimensionShattererProjectileEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.PoisonballEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class FireballLikeProjectileRender<T extends AbstractFireballEntity> extends EntityRenderer<T, FireballLikeProjectileRenderState>
{
    public static final Identifier POISONBALL = Identifier.of(AerialHell.MODID, "textures/entity/projectile/poisonball.png");
    public static final Identifier DIMENSION_SHATTERER_PROJECTILE = Identifier.of(AerialHell.MODID, "textures/item/dimension_shatterer_projectile.png");

    public FireballLikeProjectileRender(EntityRendererFactory.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override public FireballLikeProjectileRenderState createRenderState() {return new FireballLikeProjectileRenderState();}

    @Override public void updateRenderState(T entity, FireballLikeProjectileRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.scale = entity instanceof DimensionShattererProjectileEntity ? 2.0F : 1.0F;
        renderState.texture = this.getTexture(entity);
    }

    @Override public void render(FireballLikeProjectileRenderState renderState, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState)
    {
        matrices.push();
        matrices.scale(renderState.scale, renderState.scale, renderState.scale);
        matrices.multiply(cameraState.orientation);
        RenderLayer layer = RenderLayers.entityCutoutNoCull(renderState.texture);
        queue.submitCustom(matrices, layer, (matricesEntry, vertexConsumer) ->
        {
            produceVertex(vertexConsumer, matricesEntry, renderState.light, 0.0F, 0, 0, 1);
            produceVertex(vertexConsumer, matricesEntry, renderState.light, 1.0F, 0, 1, 1);
            produceVertex(vertexConsumer, matricesEntry, renderState.light, 1.0F, 1, 1, 0);
            produceVertex(vertexConsumer, matricesEntry, renderState.light, 0.0F, 1, 0, 0);
        });
        matrices.pop();
        super.render(renderState, matrices, queue, cameraState);
    }

    public Identifier getTexture(T entity)
    {
        if (entity instanceof PoisonballEntity) {return POISONBALL;}
        else /*if (entity instanceof DimensionShattererProjectileEntity)*/ {return DIMENSION_SHATTERER_PROJECTILE;}
    }

    private static void produceVertex(VertexConsumer vertexConsumer, MatrixStack.Entry matrix, int light, float x, int z, int textureU, int textureV)
    {
        vertexConsumer.vertex(matrix, x - 0.5F, (float)z - 0.25F, 0.0F).color(-1).texture((float)textureU, (float)textureV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix, 0.0F, 1.0F, 0.0F);
    }
}