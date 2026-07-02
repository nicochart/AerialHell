package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
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

//copy of net.minecraft.client.renderer.entity.PaintingRenderer but for AerialHellPaintingEntity
public class AerialHellPaintingRender extends EntityRenderer<AerialHellPaintingEntity, PaintingRenderState>
{
    private static final Identifier BACK_SPRITE_LOCATION = Identifier.withDefaultNamespace("back");
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
            this.renderPainting(poseStack, submitNodeCollector, RenderTypes.entitySolidZOffsetForward(textureatlassprite1.atlasLocation()), renderState.lightCoordsPerBlock, paintingvariant.width(), paintingvariant.height(), textureatlassprite, textureatlassprite1);
            poseStack.popPose();
            super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);
        }
    }

    @Override public PaintingRenderState createRenderState() {return new PaintingRenderState();}

    @Override public void extractRenderState(AerialHellPaintingEntity entity, PaintingRenderState state, float partialTicks)
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
