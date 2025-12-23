package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.PaintingRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.Level;

//copy of net.minecraft.client.renderer.entity.PaintingRenderer but for AerialHellPaintingEntity
public class AerialHellPaintingRender extends EntityRenderer<AerialHellPaintingEntity, PaintingRenderState>
{
    private static final ResourceLocation BACK_SPRITE_LOCATION = ResourceLocation.withDefaultNamespace("back");
    private final TextureAtlas paintingsAtlas;
    public AerialHellPaintingRender(EntityRendererProvider.Context context)
    {
        super(context);
        this.paintingsAtlas = context.getAtlas(AtlasIds.PAINTINGS);
    }

    @Override public void submit(PaintingRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        PaintingVariant paintingvariant = renderState.variant;
        if (paintingvariant != null) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees((float)(180 - renderState.direction.get2DDataValue() * 90)));
            TextureAtlasSprite textureatlassprite = this.paintingsAtlas.getSprite(paintingvariant.assetId());
            TextureAtlasSprite textureatlassprite1 = this.paintingsAtlas.getSprite(BACK_SPRITE_LOCATION);
            this.renderPainting(poseStack, submitNodeCollector, RenderType.entitySolidZOffsetForward(textureatlassprite1.atlasLocation()), renderState.lightCoordsPerBlock, paintingvariant.width(), paintingvariant.height(), textureatlassprite, textureatlassprite1);
            poseStack.popPose();
            super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);
        }
    }

    @Override public PaintingRenderState createRenderState() {return new PaintingRenderState();}

    @Override public void extractRenderState(AerialHellPaintingEntity painting, PaintingRenderState renderState, float fl)
    {
        super.extractRenderState(painting, renderState, fl);
        Direction direction = painting.getDirection();
        PaintingVariant paintingvariant = painting.getVariant().value();
        renderState.direction = direction;
        renderState.variant = paintingvariant;
        int width = paintingvariant.width();
        int height = paintingvariant.height();
        if (renderState.lightCoordsPerBlock.length != width * height) {renderState.lightCoordsPerBlock = new int[width * height];}

        float f = (float)(-width) / 2.0F;
        float f1 = (float)(-height) / 2.0F;
        Level level = painting.level();

        for (int k = 0; k < height; k++)
        {
            for (int l = 0; l < width; l++)
            {
                float f2 = (float)l + f + 0.5F;
                float f3 = (float)k + f1 + 0.5F;
                int i1 = painting.getBlockX();
                int j1 = Mth.floor(painting.getY() + (double)f3);
                int k1 = painting.getBlockZ();
                switch (direction)
                {
                    case NORTH:
                        i1 = Mth.floor(painting.getX() + (double)f2);
                        break;
                    case WEST:
                        k1 = Mth.floor(painting.getZ() - (double)f2);
                        break;
                    case SOUTH:
                        i1 = Mth.floor(painting.getX() - (double)f2);
                        break;
                    case EAST:
                        k1 = Mth.floor(painting.getZ() + (double)f2);
                }

                renderState.lightCoordsPerBlock[l + k * width] = LevelRenderer.getLightColor(level, new BlockPos(i1, j1, k1));
            }
        }
    }

    private void renderPainting(PoseStack poseStack, SubmitNodeCollector nodeCollector, RenderType renderType, int[] packedLightPerBlock, int width, int height, TextureAtlasSprite frontSprite, TextureAtlasSprite backSprite)
    {
        nodeCollector.submitCustomGeometry(poseStack, renderType, (posestack$pose, buffer) ->
        {
            float f = (float)(-width) / 2.0F;
            float f1 = (float)(-height) / 2.0F;
            float f2 = 0.03125F;
            float f3 = backSprite.getU0();
            float f4 = backSprite.getU1();
            float f5 = backSprite.getV0();
            float f6 = backSprite.getV1();
            float f7 = backSprite.getU0();
            float f8 = backSprite.getU1();
            float f9 = backSprite.getV0();
            float f10 = backSprite.getV(0.0625F);
            float f11 = backSprite.getU0();
            float f12 = backSprite.getU(0.0625F);
            float f13 = backSprite.getV0();
            float f14 = backSprite.getV1();
            double d0 = (double)1.0F / (double)width;
            double d1 = (double)1.0F / (double)height;

            for(int i = 0; i < width; ++i)
            {
                for(int j = 0; j < height; ++j)
                {
                    float f15 = f + (float)(i + 1);
                    float f16 = f + (float)i;
                    float f17 = f1 + (float)(j + 1);
                    float f18 = f1 + (float)j;
                    int k = packedLightPerBlock[i + j * width];
                    float f19 = frontSprite.getU((float)(d0 * (double)(width - i)));
                    float f20 = frontSprite.getU((float)(d0 * (double)(width - (i + 1))));
                    float f21 = frontSprite.getV((float)(d1 * (double)(height - j)));
                    float f22 = frontSprite.getV((float)(d1 * (double)(height - (j + 1))));
                    this.vertex(posestack$pose, buffer, f15, f18, f20, f21, -0.03125F, 0, 0, -1, k);
                    this.vertex(posestack$pose, buffer, f16, f18, f19, f21, -0.03125F, 0, 0, -1, k);
                    this.vertex(posestack$pose, buffer, f16, f17, f19, f22, -0.03125F, 0, 0, -1, k);
                    this.vertex(posestack$pose, buffer, f15, f17, f20, f22, -0.03125F, 0, 0, -1, k);
                    this.vertex(posestack$pose, buffer, f15, f17, f4, f5, 0.03125F, 0, 0, 1, k);
                    this.vertex(posestack$pose, buffer, f16, f17, f3, f5, 0.03125F, 0, 0, 1, k);
                    this.vertex(posestack$pose, buffer, f16, f18, f3, f6, 0.03125F, 0, 0, 1, k);
                    this.vertex(posestack$pose, buffer, f15, f18, f4, f6, 0.03125F, 0, 0, 1, k);
                    this.vertex(posestack$pose, buffer, f15, f17, f7, f9, -0.03125F, 0, 1, 0, k);
                    this.vertex(posestack$pose, buffer, f16, f17, f8, f9, -0.03125F, 0, 1, 0, k);
                    this.vertex(posestack$pose, buffer, f16, f17, f8, f10, 0.03125F, 0, 1, 0, k);
                    this.vertex(posestack$pose, buffer, f15, f17, f7, f10, 0.03125F, 0, 1, 0, k);
                    this.vertex(posestack$pose, buffer, f15, f18, f7, f9, 0.03125F, 0, -1, 0, k);
                    this.vertex(posestack$pose, buffer, f16, f18, f8, f9, 0.03125F, 0, -1, 0, k);
                    this.vertex(posestack$pose, buffer, f16, f18, f8, f10, -0.03125F, 0, -1, 0, k);
                    this.vertex(posestack$pose, buffer, f15, f18, f7, f10, -0.03125F, 0, -1, 0, k);
                    this.vertex(posestack$pose, buffer, f15, f17, f12, f13, 0.03125F, -1, 0, 0, k);
                    this.vertex(posestack$pose, buffer, f15, f18, f12, f14, 0.03125F, -1, 0, 0, k);
                    this.vertex(posestack$pose, buffer, f15, f18, f11, f14, -0.03125F, -1, 0, 0, k);
                    this.vertex(posestack$pose, buffer, f15, f17, f11, f13, -0.03125F, -1, 0, 0, k);
                    this.vertex(posestack$pose, buffer, f16, f17, f12, f13, -0.03125F, 1, 0, 0, k);
                    this.vertex(posestack$pose, buffer, f16, f18, f12, f14, -0.03125F, 1, 0, 0, k);
                    this.vertex(posestack$pose, buffer, f16, f18, f11, f14, 0.03125F, 1, 0, 0, k);
                    this.vertex(posestack$pose, buffer, f16, f17, f11, f13, 0.03125F, 1, 0, 0, k);
                }
            }
        });
    }

    private void vertex(PoseStack.Pose pose, VertexConsumer consumer, float x, float y, float u, float v, float z, int normalX, int normalY, int normalZ, int packedLight)
    {
        consumer.addVertex(pose, x, y, z).setColor(-1).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, (float)normalX, (float)normalY, (float)normalZ);
    }
}
