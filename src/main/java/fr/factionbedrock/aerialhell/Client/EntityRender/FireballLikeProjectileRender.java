package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.FireballLikeProjectileRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.DimensionShattererProjectileEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.PoisonballEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;

public class FireballLikeProjectileRender<T extends Fireball> extends EntityRenderer<T, FireballLikeProjectileRenderState>
{
    public static final Identifier POISONBALL = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/poisonball.png");
    public static final Identifier DIMENSION_SHATTERER_PROJECTILE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/dimension_shatterer_projectile.png");

    public FireballLikeProjectileRender(EntityRendererProvider.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override public FireballLikeProjectileRenderState createRenderState() {return new FireballLikeProjectileRenderState();}

    @Override public void extractRenderState(T entity, FireballLikeProjectileRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.scale = entity instanceof DimensionShattererProjectileEntity ? 2.0F : 1.0F;
        renderState.texture = this.getTextureLocation(entity);
    }

    @Override public void submit(FireballLikeProjectileRenderState renderState, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState)
    {
        matrices.pushPose();
        matrices.scale(renderState.scale, renderState.scale, renderState.scale);
        matrices.mulPose(cameraState.orientation);
        RenderType layer = RenderTypes.entityCutout(renderState.texture);
        queue.submitCustomGeometry(matrices, layer, (matricesEntry, vertexConsumer) ->
        {
            produceVertex(vertexConsumer, matricesEntry, renderState.lightCoords, 0.0F, 0, 0, 1);
            produceVertex(vertexConsumer, matricesEntry, renderState.lightCoords, 1.0F, 0, 1, 1);
            produceVertex(vertexConsumer, matricesEntry, renderState.lightCoords, 1.0F, 1, 1, 0);
            produceVertex(vertexConsumer, matricesEntry, renderState.lightCoords, 0.0F, 1, 0, 0);
        });
        matrices.popPose();
        super.submit(renderState, matrices, queue, cameraState);
    }

    public Identifier getTextureLocation(T entity)
    {
        if (entity instanceof PoisonballEntity) {return POISONBALL;}
        else /*if (entity instanceof DimensionShattererProjectileEntity)*/ {return DIMENSION_SHATTERER_PROJECTILE;}
    }

    private static void produceVertex(VertexConsumer vertexConsumer, PoseStack.Pose matrix, int light, float x, int z, int textureU, int textureV)
    {
        vertexConsumer.addVertex(matrix, x - 0.5F, (float)z - 0.25F, 0.0F).setColor(-1).setUv((float)textureU, (float)textureV).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(matrix, 0.0F, 1.0F, 0.0F);
    }
}