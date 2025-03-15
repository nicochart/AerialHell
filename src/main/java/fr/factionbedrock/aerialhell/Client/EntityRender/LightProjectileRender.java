package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.LightProjectileRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractLightProjectileEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;

//see net.minecraft.client.renderer.entity.DragonFireballRenderer
public class LightProjectileRender<T extends AbstractLightProjectileEntity> extends EntityRenderer<T, LightProjectileRenderState>
{
    public static final Identifier LUNATIC_PROJECTILE = Identifier.of(AerialHell.MODID, "textures/entity/projectile/lunatic_projectile.png");
    public static final Identifier SHADOW_PROJECTILE = Identifier.of(AerialHell.MODID, "textures/entity/projectile/shadow_projectile.png");

    public LightProjectileRender(EntityRendererFactory.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override public void render(LightProjectileRenderState renderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int packedLight)
    {
        matrixStack.push();
        matrixStack.scale(2.0F, 2.0F, 2.0F);
        matrixStack.multiply(this.dispatcher.getRotation());
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
        MatrixStack.Entry entry = matrixStack.peek();
        VertexConsumer vertexconsumer = vertexConsumers.getBuffer(getRenderLayer(renderState.texture));
        vertex(vertexconsumer, entry, packedLight, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, entry, packedLight, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, entry, packedLight, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, entry, packedLight, 0.0F, 1, 0, 0);
        matrixStack.pop();
        super.render(renderState, matrixStack, vertexConsumers, packedLight);
    }

    @Override protected int getBlockLight(T entity, BlockPos partialTicks)
    {
        if (entity instanceof LunaticProjectileEntity) {return 15;}
        else {return 13;}
    }

    @Override public LightProjectileRenderState createRenderState() {return new LightProjectileRenderState();}

    @Override public void updateRenderState(T entity, LightProjectileRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.texture = getTexture(entity);
    }

    public static RenderLayer getRenderLayer(Identifier texture) {return RenderLayer.getEntityCutoutNoCull(texture);}

    public Identifier getTexture(T entity)
    {
        if (entity instanceof LunaticProjectileEntity) {return LUNATIC_PROJECTILE;}
        else {return SHADOW_PROJECTILE;}
    }

    private static void vertex(VertexConsumer vertexConsumer, MatrixStack.Entry matrix, int packedLight, float x, int y, int u, int v)
    {
        vertexConsumer.vertex(matrix, x - 0.5F, (float)y - 0.25F, 0.0F).color(-1).texture((float)u, (float)v).overlay(OverlayTexture.DEFAULT_UV).light(packedLight).normal(matrix, 0.0F, 1.0F, 0.0F);
    }
}
