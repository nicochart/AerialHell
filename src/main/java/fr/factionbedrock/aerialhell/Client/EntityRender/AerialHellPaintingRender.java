package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

//copy of net.minecraft.client.render.entity.PaintingEntityRenderer but for AerialHellPaintingEntity

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.PaintingRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.level.Level;

public class AerialHellPaintingRender extends EntityRenderer<AerialHellPaintingEntity, PaintingRenderState>
{
    private static final Identifier BACK_TEXTURE = Identifier.withDefaultNamespace("back");
    private final TextureAtlas paintingAtlases;
    public AerialHellPaintingRender(EntityRendererProvider.Context context)
    {
        super(context);
        this.paintingAtlases = context.getAtlas(AtlasIds.PAINTINGS);
    }

    @Override public void submit(PaintingRenderState paintingEntityRenderState, PoseStack matrixStack, SubmitNodeCollector orderedRenderCommandQueue, CameraRenderState cameraRenderState)
    {
        PaintingVariant paintingVariant = paintingEntityRenderState.variant;
        if (paintingVariant != null)
        {
            matrixStack.pushPose();
            matrixStack.mulPose(Axis.YP.rotationDegrees((float)(180 - paintingEntityRenderState.direction.get2DDataValue() * 90)));
            TextureAtlasSprite sprite = this.paintingAtlases.getSprite(paintingVariant.assetId());
            TextureAtlasSprite sprite2 = this.paintingAtlases.getSprite(BACK_TEXTURE);
            this.renderPainting(matrixStack, orderedRenderCommandQueue, RenderTypes.entitySolidZOffsetForward(sprite2.atlasLocation()), paintingEntityRenderState.lightCoordsPerBlock, paintingVariant.width(), paintingVariant.height(), sprite, sprite2);
            matrixStack.popPose();
            super.submit(paintingEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
        }
    }

    @Override public PaintingRenderState createRenderState() {return new PaintingRenderState();}

    @Override public void extractRenderState(AerialHellPaintingEntity entity, final PaintingRenderState state, final float partialTicks)
    {
        super.extractRenderState(entity, state, partialTicks);
        Direction direction = entity.getDirection();
        PaintingVariant variant = (PaintingVariant)entity.getVariant().value();
        state.direction = direction;
        state.variant = variant;
        int width = variant.width();
        int height = variant.height();
        if (state.lightCoordsPerBlock.length != width * height) {state.lightCoordsPerBlock = new int[width * height];}

        float offsetX = (float)(-width) / 2.0F;
        float offsetY = (float)(-height) / 2.0F;
        Level level = entity.level();

        for(int segmentY = 0; segmentY < height; ++segmentY)
        {
            for(int segmentX = 0; segmentX < width; ++segmentX)
            {
                float segmentOffsetX = (float)segmentX + offsetX + 0.5F;
                float segmentOffsetY = (float)segmentY + offsetY + 0.5F;
                int x = entity.getBlockX();
                int y = Mth.floor(entity.getY() + (double)segmentOffsetY);
                int z = entity.getBlockZ();
                switch (direction)
                {
                    case NORTH -> x = Mth.floor(entity.getX() + (double)segmentOffsetX);
                    case WEST -> z = Mth.floor(entity.getZ() - (double)segmentOffsetX);
                    case SOUTH -> x = Mth.floor(entity.getX() - (double)segmentOffsetX);
                    case EAST -> z = Mth.floor(entity.getZ() + (double)segmentOffsetX);
                }

                state.lightCoordsPerBlock[segmentX + segmentY * width] = LightCoordsUtil.getLightCoords(level, new BlockPos(x, y, z));
            }
        }
    }

    private void renderPainting(PoseStack matrices, SubmitNodeCollector queue, RenderType renderLayer, int[] lightmapCoordinates, int width, int height, TextureAtlasSprite front, TextureAtlasSprite backSprite)
    {
        queue.submitCustomGeometry(matrices, renderLayer, (matricesEntry, vertexConsumer) ->
        {
            float f = (float)(-width) / 2.0F;
            float g = (float)(-height) / 2.0F;
            float h = 0.03125F;
            float k = backSprite.getU0();
            float l = backSprite.getU1();
            float m = backSprite.getV0();
            float n = backSprite.getV1();
            float o = backSprite.getU0();
            float p = backSprite.getU1();
            float q = backSprite.getV0();
            float r = backSprite.getV(0.0625F);
            float s = backSprite.getU0();
            float t = backSprite.getU(0.0625F);
            float u = backSprite.getV0();
            float v = backSprite.getV1();
            double d = (double)1.0F / (double)width;
            double e = (double)1.0F / (double)height;

            for(int w = 0; w < width; ++w)
            {
                for(int x = 0; x < height; ++x)
                {
                    float y = f + (float)(w + 1);
                    float z = f + (float)w;
                    float aa = g + (float)(x + 1);
                    float ab = g + (float)x;
                    int ac = lightmapCoordinates[w + x * width];
                    float ad = front.getU((float)(d * (double)(width - w)));
                    float ae = front.getU((float)(d * (double)(width - (w + 1))));
                    float af = front.getV((float)(e * (double)(height - x)));
                    float ag = front.getV((float)(e * (double)(height - (x + 1))));
                    this.vertex(matricesEntry, vertexConsumer, y, ab, ae, af, -0.03125F, 0, 0, -1, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, ab, ad, af, -0.03125F, 0, 0, -1, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, aa, ad, ag, -0.03125F, 0, 0, -1, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, aa, ae, ag, -0.03125F, 0, 0, -1, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, aa, l, m, 0.03125F, 0, 0, 1, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, aa, k, m, 0.03125F, 0, 0, 1, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, ab, k, n, 0.03125F, 0, 0, 1, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, ab, l, n, 0.03125F, 0, 0, 1, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, aa, o, q, -0.03125F, 0, 1, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, aa, p, q, -0.03125F, 0, 1, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, aa, p, r, 0.03125F, 0, 1, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, aa, o, r, 0.03125F, 0, 1, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, ab, o, q, 0.03125F, 0, -1, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, ab, p, q, 0.03125F, 0, -1, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, ab, p, r, -0.03125F, 0, -1, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, ab, o, r, -0.03125F, 0, -1, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, aa, t, u, 0.03125F, -1, 0, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, ab, t, v, 0.03125F, -1, 0, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, ab, s, v, -0.03125F, -1, 0, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, y, aa, s, u, -0.03125F, -1, 0, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, aa, t, u, -0.03125F, 1, 0, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, ab, t, v, -0.03125F, 1, 0, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, ab, s, v, 0.03125F, 1, 0, 0, ac);
                    this.vertex(matricesEntry, vertexConsumer, z, aa, s, u, 0.03125F, 1, 0, 0, ac);
                }
            }
        });
    }

    private void vertex(PoseStack.Pose matrix, VertexConsumer vertexConsumer, float x, float y, float u, float v, float z, int normalX, int normalY, int normalZ, int light)
    {
        vertexConsumer.addVertex(matrix, x, y, z).setColor(-1).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(matrix, (float)normalX, (float)normalY, (float)normalZ);
    }
}
