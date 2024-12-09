package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.DimensionShattererProjectileEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.PoisonballEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.FeatureRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class FireballLikeProjectileRender<T extends FireballEntity> extends EntityRenderer<T>
{
    public static final Identifier POISONBALL = Identifier.of(AerialHell.MODID, "textures/entity/projectile/poisonball.png");
    public static final Identifier DIMENSION_SHATTERER_PROJECTILE = Identifier.of(AerialHell.MODID, "textures/item/dimension_shatterer_projectile.png");

    public FireballLikeProjectileRender(EntityRendererFactory.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider buffer, int packedLight)
    {
        matrixStack.push();
        if (entity instanceof DimensionShattererProjectileEntity) {matrixStack.scale(2.0F, 2.0F, 2.0F);}
        matrixStack.multiply(this.dispatcher.getRotation());
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
        MatrixStack.Entry entry = matrixStack.peek();
        VertexConsumer vertexconsumer = buffer.getBuffer(FeatureRenderer.getEntityCutoutNoCull(this.getTexture(entity)));
        vertex(vertexconsumer, entry, packedLight, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, entry, packedLight, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, entry, packedLight, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, entry, packedLight, 0.0F, 1, 0, 0);
        matrixStack.pop();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public Identifier getTexture(T entity)
    {
        if (entity instanceof PoisonballEntity) {return POISONBALL;}
        else /*if (entity instanceof DimensionShattererProjectileEntity)*/ {return DIMENSION_SHATTERER_PROJECTILE;}
    }

    private static void vertex(VertexConsumer vertexConsumer, MatrixStack.Entry matrixStack, int packedLight, float x, int y, int u, int v)
    {
        vertexConsumer.vertex(matrixStack, x - 0.5F, (float)y - 0.25F, 0.0F).color(-1).texture((float)u, (float)v).overlay(OverlayTexture.DEFAULT_UV).light(packedLight).normal(matrixStack, 0.0F, 1.0F, 0.0F);
    }
}