package fr.factionbedrock.aerialhell.Client.EntityRender;

//copy of net.minecraft.client.render.entity.PaintingEntityRenderer but for AerialHellPaintingEntity

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.PaintingEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.Atlases;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;

public class AerialHellPaintingRender extends EntityRenderer<AerialHellPaintingEntity, PaintingEntityRenderState>
{
    private static final Identifier BACK_TEXTURE = Identifier.ofVanilla("back");
    private final SpriteAtlasTexture paintingAtlases;
    public AerialHellPaintingRender(EntityRendererFactory.Context context)
    {
        super(context);
        this.paintingAtlases = context.getSpriteAtlasTexture(Atlases.PAINTINGS);
    }

    @Override public void render(PaintingEntityRenderState paintingEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState)
    {
        PaintingVariant paintingVariant = paintingEntityRenderState.variant;
        if (paintingVariant != null)
        {
            matrixStack.push();
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)(180 - paintingEntityRenderState.facing.getHorizontalQuarterTurns() * 90)));
            Sprite sprite = this.paintingAtlases.getSprite(paintingVariant.assetId());
            Sprite sprite2 = this.paintingAtlases.getSprite(BACK_TEXTURE);
            this.renderPainting(matrixStack, orderedRenderCommandQueue, RenderLayers.entitySolidZOffsetForward(sprite2.getAtlasId()), paintingEntityRenderState.lightmapCoordinates, paintingVariant.width(), paintingVariant.height(), sprite, sprite2);
            matrixStack.pop();
            super.render(paintingEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
        }
    }

    @Override public PaintingEntityRenderState createRenderState() {return new PaintingEntityRenderState();}

    @Override public void updateRenderState(AerialHellPaintingEntity paintingEntity, PaintingEntityRenderState paintingEntityRenderState, float f)
    {
        super.updateRenderState(paintingEntity, paintingEntityRenderState, f);
        Direction direction = paintingEntity.getHorizontalFacing();
        PaintingVariant paintingVariant = (PaintingVariant)paintingEntity.getVariant().value();
        paintingEntityRenderState.facing = direction;
        paintingEntityRenderState.variant = paintingVariant;
        int i = paintingVariant.width();
        int j = paintingVariant.height();
        if (paintingEntityRenderState.lightmapCoordinates.length != i * j) {paintingEntityRenderState.lightmapCoordinates = new int[i * j];}

        float g = (float)(-i) / 2.0F;
        float h = (float)(-j) / 2.0F;
        World world = paintingEntity.getEntityWorld();

        for(int k = 0; k < j; ++k)
        {
            for(int l = 0; l < i; ++l)
            {
                float m = (float)l + g + 0.5F;
                float n = (float)k + h + 0.5F;
                int o = paintingEntity.getBlockX();
                int p = MathHelper.floor(paintingEntity.getY() + (double)n);
                int q = paintingEntity.getBlockZ();
                switch (direction)
                {
                    case NORTH -> o = MathHelper.floor(paintingEntity.getX() + (double)m);
                    case WEST -> q = MathHelper.floor(paintingEntity.getZ() - (double)m);
                    case SOUTH -> o = MathHelper.floor(paintingEntity.getX() - (double)m);
                    case EAST -> q = MathHelper.floor(paintingEntity.getZ() + (double)m);
                }

                paintingEntityRenderState.lightmapCoordinates[l + k * i] = WorldRenderer.getLightmapCoordinates(world, new BlockPos(o, p, q));
            }
        }
    }

    private void renderPainting(MatrixStack matrices, OrderedRenderCommandQueue queue, RenderLayer renderLayer, int[] lightmapCoordinates, int width, int height, Sprite front, Sprite backSprite)
    {
        queue.submitCustom(matrices, renderLayer, (matricesEntry, vertexConsumer) ->
        {
            float f = (float)(-width) / 2.0F;
            float g = (float)(-height) / 2.0F;
            float h = 0.03125F;
            float k = backSprite.getMinU();
            float l = backSprite.getMaxU();
            float m = backSprite.getMinV();
            float n = backSprite.getMaxV();
            float o = backSprite.getMinU();
            float p = backSprite.getMaxU();
            float q = backSprite.getMinV();
            float r = backSprite.getFrameV(0.0625F);
            float s = backSprite.getMinU();
            float t = backSprite.getFrameU(0.0625F);
            float u = backSprite.getMinV();
            float v = backSprite.getMaxV();
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
                    float ad = front.getFrameU((float)(d * (double)(width - w)));
                    float ae = front.getFrameU((float)(d * (double)(width - (w + 1))));
                    float af = front.getFrameV((float)(e * (double)(height - x)));
                    float ag = front.getFrameV((float)(e * (double)(height - (x + 1))));
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

    private void vertex(MatrixStack.Entry matrix, VertexConsumer vertexConsumer, float x, float y, float u, float v, float z, int normalX, int normalY, int normalZ, int light)
    {
        vertexConsumer.vertex(matrix, x, y, z).color(-1).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix, (float)normalX, (float)normalY, (float)normalZ);
    }
}
