package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractLightProjectileEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

//see net.minecraft.client.renderer.entity.DragonFireballRenderer
public class LightProjectileRender<T extends AbstractLightProjectileEntity> extends EntityRenderer<T>
{
    public static final ResourceLocation LUNATIC_PROJECTILE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/lunatic_projectile.png");
    public static final ResourceLocation SHADOW_PROJECTILE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/shadow_projectile.png");

    public LightProjectileRender(EntityRendererProvider.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight)
    {
        matrixStack.pushPose();
        matrixStack.scale(2.0F, 2.0F, 2.0F);
        matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose entry = matrixStack.last();
        VertexConsumer vertexconsumer = buffer.getBuffer(getFeatureRenderer(this.getTextureLocation(entity)));
        vertex(vertexconsumer, entry, packedLight, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, entry, packedLight, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, entry, packedLight, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, entry, packedLight, 0.0F, 1, 0, 0);
        matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    protected int getBlockLightLevel(T entity, BlockPos partialTicks)
    {
        if (entity instanceof LunaticProjectileEntity) {return 15;}
        else {return 13;}
    }

    public static RenderType getFeatureRenderer(ResourceLocation texture) {return RenderType.entityCutoutNoCull(texture);}

    @Override
    public ResourceLocation getTextureLocation(T entity)
    {
        if (entity instanceof LunaticProjectileEntity) {return LUNATIC_PROJECTILE;}
        else {return SHADOW_PROJECTILE;}
    }

    private static void vertex(VertexConsumer vertexConsumer, PoseStack.Pose poseStack, int packedLight, float x, int y, int u, int v)
    {
        vertexConsumer.addVertex(poseStack, x - 0.5F, (float)y - 0.25F, 0.0F).setColor(-1).setUv((float)u, (float)v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(poseStack, 0.0F, 1.0F, 0.0F);
    }
}
