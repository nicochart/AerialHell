package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.PaintingVariant;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

//copy of net.minecraft.client.renderer.entity.PaintingRenderer but for AerialHellPaintingEntity

public class AerialHellPaintingRender extends EntityRenderer<AerialHellPaintingEntity>
{
    public AerialHellPaintingRender(EntityRendererProvider.Context context) {super(context);}

    public void render(AerialHellPaintingEntity entity, float p_115553_, float p_115554_, PoseStack poseStack, MultiBufferSource bufferSource, int p_115557_)
    {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - p_115553_));
        PaintingVariant paintingvariant = entity.getVariant().value();
        float f = 0.0625F;
        poseStack.scale(0.0625F, 0.0625F, 0.0625F);
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entitySolid(this.getTextureLocation(entity)));
        PaintingTextureManager paintingtexturemanager = Minecraft.getInstance().getPaintingTextures();
        this.renderPainting(poseStack, vertexconsumer, entity, paintingvariant.width(), paintingvariant.height(), paintingtexturemanager.get(paintingvariant), paintingtexturemanager.getBackSprite());
        poseStack.popPose();
        super.render(entity, p_115553_, p_115554_, poseStack, bufferSource, p_115557_);
    }

    public ResourceLocation getTextureLocation(AerialHellPaintingEntity entity) {return Minecraft.getInstance().getPaintingTextures().getBackSprite().atlasLocation();}

    private void renderPainting(PoseStack poseStack, VertexConsumer consumer, AerialHellPaintingEntity entity, int p_115562_, int p_115563_, TextureAtlasSprite textureAtlasSprite1, TextureAtlasSprite textureAtlasSprite2)
    {
        PoseStack.Pose posestack$pose = poseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        float f = (float)(-p_115562_) / 2.0F;
        float f1 = (float)(-p_115563_) / 2.0F;
        float f2 = 0.5F;
        float f3 = textureAtlasSprite2.getU0();
        float f4 = textureAtlasSprite2.getU1();
        float f5 = textureAtlasSprite2.getV0();
        float f6 = textureAtlasSprite2.getV1();
        float f7 = textureAtlasSprite2.getU0();
        float f8 = textureAtlasSprite2.getU1();
        float f9 = textureAtlasSprite2.getV0();
        float f10 = textureAtlasSprite2.getV(0.0625F);
        float f11 = textureAtlasSprite2.getU0();
        float f12 = textureAtlasSprite2.getU(0.0625F);
        float f13 = textureAtlasSprite2.getV0();
        float f14 = textureAtlasSprite2.getV1();
        int i = p_115562_ / 16;
        int j = p_115563_ / 16;
        double d0 = 1.0D / (double)i;
        double d1 = 1.0D / (double)j;

        for(int k = 0; k < i; ++k)
        {
            for(int l = 0; l < j; ++l)
            {
                float f15 = f + (float)((k + 1) * 16);
                float f16 = f + (float)(k * 16);
                float f17 = f1 + (float)((l + 1) * 16);
                float f18 = f1 + (float)(l * 16);
                int i1 = entity.getBlockX();
                int j1 = MathHelper.floor(entity.getY() + (double)((f17 + f18) / 2.0F / 16.0F));
                int k1 = entity.getBlockZ();
                Direction direction = entity.getDirection();
                if (direction == Direction.NORTH) {i1 = MathHelper.floor(entity.getX() + (double)((f15 + f16) / 2.0F / 16.0F));}
                if (direction == Direction.WEST) {k1 = MathHelper.floor(entity.getZ() - (double)((f15 + f16) / 2.0F / 16.0F));}
                if (direction == Direction.SOUTH) {i1 = MathHelper.floor(entity.getX() - (double)((f15 + f16) / 2.0F / 16.0F));}
                if (direction == Direction.EAST) {k1 = MathHelper.floor(entity.getZ() + (double)((f15 + f16) / 2.0F / 16.0F));}

                int l1 = LevelRenderer.getLightColor(entity.level(), new BlockPos(i1, j1, k1));
                float f19 = textureAtlasSprite1.getU((float)(d0 * (double)(i - k)));
                float f20 = textureAtlasSprite1.getU((float)(d0 * (double)(i - (k + 1))));
                float f21 = textureAtlasSprite1.getV((float)(d1 * (double)(j - l)));
                float f22 = textureAtlasSprite1.getV((float)(d1 * (double)(j - (l + 1))));
                this.vertex(posestack$pose, consumer, f15, f18, f20, f21, -0.5F, 0, 0, -1, l1);
                this.vertex(posestack$pose, consumer, f16, f18, f19, f21, -0.5F, 0, 0, -1, l1);
                this.vertex(posestack$pose, consumer, f16, f17, f19, f22, -0.5F, 0, 0, -1, l1);
                this.vertex(posestack$pose, consumer, f15, f17, f20, f22, -0.5F, 0, 0, -1, l1);
                this.vertex(posestack$pose, consumer, f15, f17, f4, f5, 0.5F, 0, 0, 1, l1);
                this.vertex(posestack$pose, consumer, f16, f17, f3, f5, 0.5F, 0, 0, 1, l1);
                this.vertex(posestack$pose, consumer, f16, f18, f3, f6, 0.5F, 0, 0, 1, l1);
                this.vertex(posestack$pose, consumer, f15, f18, f4, f6, 0.5F, 0, 0, 1, l1);
                this.vertex(posestack$pose, consumer, f15, f17, f7, f9, -0.5F, 0, 1, 0, l1);
                this.vertex(posestack$pose, consumer, f16, f17, f8, f9, -0.5F, 0, 1, 0, l1);
                this.vertex(posestack$pose, consumer, f16, f17, f8, f10, 0.5F, 0, 1, 0, l1);
                this.vertex(posestack$pose, consumer, f15, f17, f7, f10, 0.5F, 0, 1, 0, l1);
                this.vertex(posestack$pose, consumer, f15, f18, f7, f9, 0.5F, 0, -1, 0, l1);
                this.vertex(posestack$pose, consumer, f16, f18, f8, f9, 0.5F, 0, -1, 0, l1);
                this.vertex(posestack$pose, consumer, f16, f18, f8, f10, -0.5F, 0, -1, 0, l1);
                this.vertex(posestack$pose, consumer, f15, f18, f7, f10, -0.5F, 0, -1, 0, l1);
                this.vertex(posestack$pose, consumer, f15, f17, f12, f13, 0.5F, -1, 0, 0, l1);
                this.vertex(posestack$pose, consumer, f15, f18, f12, f14, 0.5F, -1, 0, 0, l1);
                this.vertex(posestack$pose, consumer, f15, f18, f11, f14, -0.5F, -1, 0, 0, l1);
                this.vertex(posestack$pose, consumer, f15, f17, f11, f13, -0.5F, -1, 0, 0, l1);
                this.vertex(posestack$pose, consumer, f16, f17, f12, f13, -0.5F, 1, 0, 0, l1);
                this.vertex(posestack$pose, consumer, f16, f18, f12, f14, -0.5F, 1, 0, 0, l1);
                this.vertex(posestack$pose, consumer, f16, f18, f11, f14, 0.5F, 1, 0, 0, l1);
                this.vertex(posestack$pose, consumer, f16, f17, f11, f13, 0.5F, 1, 0, 0, l1);
            }
        }
    }

    private void vertex(PoseStack.Pose pose, VertexConsumer vertexConsumer, float x, float y, float u, float v, float z, int normalX, int normalY, int normalZ, int packedLight)
    {
        vertexConsumer.addVertex(pose, x, y, z).setColor(-1).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, (float)normalX, (float)normalY, (float)normalZ);
    }
}
