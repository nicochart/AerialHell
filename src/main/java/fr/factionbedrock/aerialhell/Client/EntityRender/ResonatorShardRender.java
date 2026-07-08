package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.ResonatorShard.RubyShardEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

//copy of ArrowRenderer
@OnlyIn(Dist.CLIENT)
public class ResonatorShardRender<T extends AbstractArrow> extends EntityRenderer<T>
{
    public static final ResourceLocation VOLUCITE_SHARD_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/shard/volucite.png");
    public static final ResourceLocation RUBY_SHARD_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/shard/ruby.png");

    public ResonatorShardRender(EntityRendererProvider.Context pContext) {super(pContext);}

    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight)
    {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot())));
        int i = 0;
        float f = 0.0F;
        float f1 = 0.5F;
        float f2 = 0.0F;
        float f3 = 0.15625F;
        float f4 = 0.0F;
        float f5 = 0.15625F;
        float f6 = 0.15625F;
        float f7 = 0.3125F;
        float f8 = 0.05625F;
        float f9 = (float)pEntity.shakeTime - pPartialTicks;
        if (f9 > 0.0F)
        {
            float f10 = -Mth.sin(f9 * 3.0F) * f9;
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(f10));
        }

        pPoseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
        pPoseStack.scale(0.05625F, 0.05625F, 0.05625F);
        pPoseStack.translate(-4.0F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(pEntity)));
        PoseStack.Pose posestack$pose = pPoseStack.last();
        int edited_back_pos = 1; //original -7
        this.vertex(posestack$pose, vertexconsumer, edited_back_pos, -2, -2, 0.0F, 0.15625F, -1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, edited_back_pos, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, edited_back_pos, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, edited_back_pos, 2, -2, 0.0F, 0.3125F, -1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, edited_back_pos, 2, -2, 0.0F, 0.15625F, 1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, edited_back_pos, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, edited_back_pos, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, pPackedLight);
        this.vertex(posestack$pose, vertexconsumer, edited_back_pos, -2, -2, 0.0F, 0.3125F, 1, 0, 0, pPackedLight);

        for (int j = 0; j < 4; j++)
        {
            pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            this.vertex(posestack$pose, vertexconsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, pPackedLight);
            this.vertex(posestack$pose, vertexconsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, pPackedLight);
            this.vertex(posestack$pose, vertexconsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, pPackedLight);
            this.vertex(posestack$pose, vertexconsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, pPackedLight);
        }

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    public void vertex(PoseStack.Pose pPose, VertexConsumer pConsumer, int pX, int pY, int pZ, float pU, float pV, int pNormalX, int pNormalY, int pNormalZ, int pPackedLight)
    {
        pConsumer.addVertex(pPose, (float)pX, (float)pY, (float)pZ).setColor(-1).setUv(pU, pV).setOverlay(OverlayTexture.NO_OVERLAY).setLight(pPackedLight).setNormal(pPose, (float)pNormalX, (float)pNormalZ, (float)pNormalY);
    }

    public ResourceLocation getTextureLocation(T entity)
    {
        if (entity instanceof RubyShardEntity) {return RUBY_SHARD_TEXTURE;}
        else {return VOLUCITE_SHARD_TEXTURE;}
    }
}
