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
import net.minecraft.util.CommonColors;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.PaintingVariant;

//copy of net.minecraft.client.render.entity.PaintingEntityRenderer but for AerialHellPaintingEntity

public class AerialHellPaintingRender extends EntityRenderer<AerialHellPaintingEntity>
{
    public AerialHellPaintingRender(EntityRendererProvider.Context context) {super(context);}

    @Override public void render(AerialHellPaintingEntity paintingEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i)
    {
        matrixStack.pushPose();
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F - f));
        PaintingVariant paintingVariant = paintingEntity.getVariant().value();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entitySolid(this.getTextureLocation(paintingEntity)));
        PaintingTextureManager paintingManager = Minecraft.getInstance().getPaintingTextures();
        this.renderPainting(matrixStack,vertexConsumer,paintingEntity,paintingVariant.width(),paintingVariant.height(),paintingManager.get(paintingVariant), paintingManager.getBackSprite());
        matrixStack.popPose();
        super.render(paintingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override public ResourceLocation getTextureLocation(AerialHellPaintingEntity entity) {return Minecraft.getInstance().getPaintingTextures().getBackSprite().atlasLocation();}

    private void renderPainting(PoseStack matrices, VertexConsumer vertexConsumer, AerialHellPaintingEntity entity, int width, int height, TextureAtlasSprite paintingSprite, TextureAtlasSprite backSprite)
    {
        PoseStack.Pose entry = matrices.last();
        float f = (float)(-width) / 2.0F;
        float g = (float)(-height) / 2.0F;
        float h = 0.03125F;
        float i = backSprite.getU0();
        float j = backSprite.getU1();
        float k = backSprite.getV0();
        float l = backSprite.getV1();
        float m = backSprite.getU0();
        float n = backSprite.getU1();
        float o = backSprite.getV0();
        float p = backSprite.getV(0.0625F);
        float q = backSprite.getU0();
        float r = backSprite.getU(0.0625F);
        float s = backSprite.getV0();
        float t = backSprite.getV1();
        double d = 1.0 / (double)width;
        double e = 1.0 / (double)height;

        for (int u = 0; u < width; u++)
        {
            for (int v = 0; v < height; v++)
            {
                float w = f + (float)(u + 1);
                float x = f + (float)u;
                float y = g + (float)(v + 1);
                float z = g + (float)v;
                int aa = entity.getBlockX();
                int ab = Mth.floor(entity.getY() + (double)((y + z) / 2.0F));
                int ac = entity.getBlockZ();
                Direction direction = entity.getDirection();
                if (direction == Direction.NORTH) {aa = Mth.floor(entity.getX() + (double)((w + x) / 2.0F));}
                if (direction == Direction.WEST) {ac = Mth.floor(entity.getZ() - (double)((w + x) / 2.0F));}
                if (direction == Direction.SOUTH) {aa = Mth.floor(entity.getX() - (double)((w + x) / 2.0F));}
                if (direction == Direction.EAST) {ac = Mth.floor(entity.getZ() + (double)((w + x) / 2.0F));}
                int ad = LevelRenderer.getLightColor(entity.level(), new BlockPos(aa, ab, ac));
                float ae = paintingSprite.getU((float)(d * (double)(width - u)));
                float af = paintingSprite.getU((float)(d * (double)(width - (u + 1))));
                float ag = paintingSprite.getV((float)(e * (double)(height - v)));
                float ah = paintingSprite.getV((float)(e * (double)(height - (v + 1))));
                this.vertex(entry, vertexConsumer, w, z, af, ag, -0.03125F, 0, 0, -1, ad);
                this.vertex(entry, vertexConsumer, x, z, ae, ag, -0.03125F, 0, 0, -1, ad);
                this.vertex(entry, vertexConsumer, x, y, ae, ah, -0.03125F, 0, 0, -1, ad);
                this.vertex(entry, vertexConsumer, w, y, af, ah, -0.03125F, 0, 0, -1, ad);
                this.vertex(entry, vertexConsumer, w, y, j, k, 0.03125F, 0, 0, 1, ad);
                this.vertex(entry, vertexConsumer, x, y, i, k, 0.03125F, 0, 0, 1, ad);
                this.vertex(entry, vertexConsumer, x, z, i, l, 0.03125F, 0, 0, 1, ad);
                this.vertex(entry, vertexConsumer, w, z, j, l, 0.03125F, 0, 0, 1, ad);
                this.vertex(entry, vertexConsumer, w, y, m, o, -0.03125F, 0, 1, 0, ad);
                this.vertex(entry, vertexConsumer, x, y, n, o, -0.03125F, 0, 1, 0, ad);
                this.vertex(entry, vertexConsumer, x, y, n, p, 0.03125F, 0, 1, 0, ad);
                this.vertex(entry, vertexConsumer, w, y, m, p, 0.03125F, 0, 1, 0, ad);
                this.vertex(entry, vertexConsumer, w, z, m, o, 0.03125F, 0, -1, 0, ad);
                this.vertex(entry, vertexConsumer, x, z, n, o, 0.03125F, 0, -1, 0, ad);
                this.vertex(entry, vertexConsumer, x, z, n, p, -0.03125F, 0, -1, 0, ad);
                this.vertex(entry, vertexConsumer, w, z, m, p, -0.03125F, 0, -1, 0, ad);
                this.vertex(entry, vertexConsumer, w, y, r, s, 0.03125F, -1, 0, 0, ad);
                this.vertex(entry, vertexConsumer, w, z, r, t, 0.03125F, -1, 0, 0, ad);
                this.vertex(entry, vertexConsumer, w, z, q, t, -0.03125F, -1, 0, 0, ad);
                this.vertex(entry, vertexConsumer, w, y, q, s, -0.03125F, -1, 0, 0, ad);
                this.vertex(entry, vertexConsumer, x, y, r, s, -0.03125F, 1, 0, 0, ad);
                this.vertex(entry, vertexConsumer, x, z, r, t, -0.03125F, 1, 0, 0, ad);
                this.vertex(entry, vertexConsumer, x, z, q, t, 0.03125F, 1, 0, 0, ad);
                this.vertex(entry, vertexConsumer, x, y, q, s, 0.03125F, 1, 0, 0, ad);
            }
        }
    }

    private void vertex(PoseStack.Pose matrix, VertexConsumer vertexConsumer, float x, float y, float u, float v, float z, int normalX, int normalY, int normalZ, int light)
    {
        vertexConsumer.addVertex(matrix, x, y, z).setColor(CommonColors.WHITE).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(matrix, (float)normalX, (float)normalY, (float)normalZ);
    }
}
