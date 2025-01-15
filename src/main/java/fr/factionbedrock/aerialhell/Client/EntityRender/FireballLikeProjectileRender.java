package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.FireballLikeProjectileRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.DimensionShattererProjectileEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.PoisonballEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Fireball;

public class FireballLikeProjectileRender<T extends Fireball> extends EntityRenderer<T, FireballLikeProjectileRenderState>
{
    public static final ResourceLocation POISONBALL = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/poisonball.png");
    public static final ResourceLocation DIMENSION_SHATTERER_PROJECTILE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/dimension_shatterer_projectile.png");

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

    @Override public void render(FireballLikeProjectileRenderState renderState, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
    {
        poseStack.pushPose();
        poseStack.scale(renderState.scale, renderState.scale, renderState.scale);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose posestack$pose = poseStack.last();
        VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(renderState.texture));
        vertex(vertexconsumer, posestack$pose, packedLight, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, posestack$pose, packedLight, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, posestack$pose, packedLight, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, posestack$pose, packedLight, 0.0F, 1, 0, 0);
        poseStack.popPose();
        super.render(renderState, poseStack, buffer, packedLight);
    }

    public ResourceLocation getTextureLocation(T entity)
    {
        if (entity instanceof PoisonballEntity) {return POISONBALL;}
        else /*if (entity instanceof DimensionShattererProjectileEntity)*/ {return DIMENSION_SHATTERER_PROJECTILE;}
    }

    private static void vertex(VertexConsumer vertexConsumer, PoseStack.Pose poseStack, int packedLight, float x, int y, int u, int v)
    {
        vertexConsumer.addVertex(poseStack, x - 0.5F, (float)y - 0.25F, 0.0F).setColor(-1).setUv((float)u, (float)v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(poseStack, 0.0F, 1.0F, 0.0F);
    }
}