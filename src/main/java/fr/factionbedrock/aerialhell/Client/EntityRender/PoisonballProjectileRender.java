package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.PoisonballEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class PoisonballProjectileRender extends EntityRenderer<PoisonballEntity>
{
    public static final ResourceLocation POISONBALL = new ResourceLocation(AerialHell.MODID, "textures/entity/projectile/poisonball.png");

    public PoisonballProjectileRender(EntityRendererProvider.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override
    public void render(PoisonballEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStackIn, MultiBufferSource bufferIn, int packedLightIn)
    {
        poseStackIn.pushPose();
        poseStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStackIn.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStackIn.scale(0.5F, 0.5F, 0.5F);
        VertexConsumer vertex = bufferIn.getBuffer(RenderType.entityCutout(getTextureLocation(entityIn)));
        PoseStack.Pose posestack$pose = poseStackIn.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        drawVertex(matrix4f, matrix3f, vertex, -0.5F, -0.25F, 0.0F, 0.0F, 0.0F, 0, 1, 0, packedLightIn);
        drawVertex(matrix4f, matrix3f, vertex, 0.5F, -0.25F, 0.0F, 0.0F, 1.0F, 0, 1, 0, packedLightIn);
        drawVertex(matrix4f, matrix3f, vertex, 0.5F, 0.75F, 0.0F, 1.0F, 1.0F, 0, 1, 0, packedLightIn);
        drawVertex(matrix4f, matrix3f, vertex, -0.5F, 0.75F, 0.0F, 1.0F, 0.0F, 0, 1, 0, packedLightIn);
        poseStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, poseStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(PoisonballEntity entity)
    {
        return POISONBALL;
    }

    public void drawVertex(Matrix4f matrix, Matrix3f normals, VertexConsumer vertexBuilder, float offsetX, float offsetY, float offsetZ, float textureX, float textureY, int normalX, int normalY, int normalZ, int packedLightIn)
    {
        vertexBuilder.vertex(matrix, offsetX, offsetY, offsetZ).color(255, 255, 255, 255).uv(textureX, textureY).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLightIn).normal(normals, (float)normalX, (float)normalZ, (float)normalY).endVertex();
    }
}