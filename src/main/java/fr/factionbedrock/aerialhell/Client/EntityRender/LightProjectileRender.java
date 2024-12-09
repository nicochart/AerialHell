package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractLightProjectileEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.FeatureRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;

//see net.minecraft.client.renderer.entity.DragonFireballRenderer
public class LightProjectileRender<T extends AbstractLightProjectileEntity> extends EntityRenderer<T>
{
    public static final Identifier LUNATIC_PROJECTILE = Identifier.of(AerialHell.MODID, "textures/entity/projectile/lunatic_projectile.png");
    public static final Identifier SHADOW_PROJECTILE = Identifier.of(AerialHell.MODID, "textures/entity/projectile/shadow_projectile.png");

    public LightProjectileRender(EntityRendererFactory.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider buffer, int packedLight)
    {
        matrixStack.push();
        matrixStack.scale(2.0F, 2.0F, 2.0F);
        matrixStack.multiply(this.dispatcher.getRotation());
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
        MatrixStack.Entry entry = matrixStack.peek();
        VertexConsumer vertexconsumer = buffer.getBuffer(getFeatureRenderer(this.getTexture(entity)));
        vertex(vertexconsumer, entry, packedLight, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, entry, packedLight, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, entry, packedLight, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, entry, packedLight, 0.0F, 1, 0, 0);
        matrixStack.pop();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    protected int getBlockLight(T entity, BlockPos partialTicks)
    {
        if (entity instanceof LunaticProjectileEntity) {return 15;}
        else {return 13;}
    }

    public static FeatureRenderer getFeatureRenderer(Identifier texture) {return FeatureRenderer.getEntityCutoutNoCull(texture);}

    @Override
    public Identifier getTexture(T entity)
    {
        if (entity instanceof LunaticProjectileEntity) {return LUNATIC_PROJECTILE;}
        else {return SHADOW_PROJECTILE;}
    }

    private static void vertex(VertexConsumer vertexConsumer, MatrixStack.Entry poseStack, int packedLight, float x, int y, int u, int v)
    {
        vertexConsumer.vertex(poseStack, x - 0.5F, (float)y - 0.25F, 0.0F).color(-1).texture((float)u, (float)v).overlay(OverlayTexture.DEFAULT_UV).light(packedLight).normal(poseStack, 0.0F, 1.0F, 0.0F);
    }
}
