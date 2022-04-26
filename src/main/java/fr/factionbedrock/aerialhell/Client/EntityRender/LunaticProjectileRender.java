package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

public class LunaticProjectileRender extends EntityRenderer<LunaticProjectileEntity>
{
    public static final ResourceLocation LUNATIC_PROJECTILE = new ResourceLocation(AerialHell.MODID, "textures/entity/projectile/lunatic_projectile.png");

    public LunaticProjectileRender(EntityRendererManager renderManager)
    {
        super(renderManager);
        this.shadowSize = 0.0F;
    }

    @Override
    public void render(LunaticProjectileEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
    {
        matrixStackIn.push();
        matrixStackIn.rotate(this.renderManager.getCameraOrientation());
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
        matrixStackIn.scale(1.5f, 1.5f, 1.5f);
        IVertexBuilder vertex = bufferIn.getBuffer(RenderType.getEntityCutout(getEntityTexture(entityIn)));
        MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();
        Matrix4f matrix4f = matrixstack$entry.getMatrix();
        Matrix3f matrix3f = matrixstack$entry.getNormal();
        drawVertex(matrix4f, matrix3f, vertex, -0.5F, -0.25F, 0.0F, 0.0F, 0.0F, 0, 1, 0, packedLightIn);
        drawVertex(matrix4f, matrix3f, vertex, 0.5F, -0.25F, 0.0F, 0.0F, 1.0F, 0, 1, 0, packedLightIn);
        drawVertex(matrix4f, matrix3f, vertex, 0.5F, 0.75F, 0.0F, 1.0F, 1.0F, 0, 1, 0, packedLightIn);
        drawVertex(matrix4f, matrix3f, vertex, -0.5F, 0.75F, 0.0F, 1.0F, 0.0F, 0, 1, 0, packedLightIn);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
    
    @Override
    protected int getBlockLight(LunaticProjectileEntity entityIn, BlockPos partialTicks)
    {
    	return 15;
    }

    @Override
    public ResourceLocation getEntityTexture(LunaticProjectileEntity entity)
    {
        return LUNATIC_PROJECTILE;
    }

    public void drawVertex(Matrix4f matrix, Matrix3f normals, IVertexBuilder vertexBuilder, float offsetX, float offsetY, float offsetZ, float textureX, float textureY, int normalX, int normalY, int normalZ, int packedLightIn)
    {
        vertexBuilder.pos(matrix, offsetX, offsetY, offsetZ).color(255, 255, 255, 255).tex(textureX, textureY).overlay(OverlayTexture.NO_OVERLAY).lightmap(packedLightIn).normal(normals, (float)normalX, (float)normalZ, (float)normalY).endVertex();
    }
}
