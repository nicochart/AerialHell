package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.RubyArrowEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.Mth;
import net.minecraft.util.math.vector.Matrix3f;
import com.mojang.math.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

public class AerialArrowRenderer<T extends AbstractAerialArrowEntity> extends EntityRenderer<T>
{
	public static final ResourceLocation VOLUCITE_ARROW_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/projectile/arrow/volucite_blowpipe_arrow.png");
	public static final ResourceLocation RUBY_ARROW_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/projectile/arrow/ruby_blowpipe_arrow.png");
    
    public AerialArrowRenderer(EntityRendererManager renderManager)
    {
        super(renderManager);
        this.shadowSize = 0.0F;
    }

    @Override
    public void render(T arrow, float entityYaw, float partialTicks, PoseStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
    {
        if (arrow.isInvisible())
        {
            return;
        }
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(arrow.rotationYaw - 90.0F));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(arrow.rotationPitch));
        float textureX_3 = 0.0F;
        float textureX_4 = 0.5F;
        float textureY_3 = 0.0F;
        float textureY_4 = 0.15625F;
        float textureX_1 = 0.0F;
        float textureX_2 = 0.15625F;
        float textureY_1 = 0.15625F;
        float textureY_2 = 0.3125F;
        
        float shake = (float) arrow.arrowShake - partialTicks;
        if (shake > 0.0F)
        {
        	float rot = -Mth.sin(shake * 3.0F) * shake;
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(rot));
        }

        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(45.0F));
        matrixStackIn.scale(0.05625F, 0.05625F, 0.05625F);
        matrixStackIn.translate(-4.0D, 0.0D, 0.0D);
        
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(arrow)));
        PoseStack.Entry matrixstack$entry = matrixStackIn.getLast();
        Matrix4f matrix4f = matrixstack$entry.getMatrix();
        Matrix3f matrix3f = matrixstack$entry.getNormal();
        
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, -2, -2, textureX_1, textureY_1, -1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, -2, 2, textureX_2, textureY_1, -1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, 2, 2, textureX_2, textureY_2, -1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, 2, -2, textureX_1, textureY_2, -1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, 2, -2, textureX_1, textureY_1, 1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, 2, 2, textureX_2, textureY_1, 1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, -2, 2, textureX_2, textureY_2, 1, 0, 0, packedLightIn);
        this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -7, -2, -2, textureX_1, textureY_2, 1, 0, 0, packedLightIn);

        for(int j = 0; j < 8; ++j)
        {
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(45.0F));
            this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -8, -2, 0, textureX_3, textureY_3, 0, 1, 0, packedLightIn);
            this.drawVertex(matrix4f, matrix3f, ivertexbuilder, 8, -2, 0, textureX_4, textureY_3, 0, 1, 0, packedLightIn);
            this.drawVertex(matrix4f, matrix3f, ivertexbuilder, 8, 2, 0, textureX_4, textureY_4, 0, 1, 0, packedLightIn);
            this.drawVertex(matrix4f, matrix3f, ivertexbuilder, -8, 2, 0, textureX_3, textureY_4, 0, 1, 0, packedLightIn);
        }

         matrixStackIn.pop();
         super.render(arrow, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity)
    {
        if (entity instanceof RubyArrowEntity)
        {
        	return RUBY_ARROW_TEXTURE;
        }
        else //if (entity instanceof VoluciteArrowEntity)
        {
        	return VOLUCITE_ARROW_TEXTURE;
        }
    }

    public void drawVertex(Matrix4f matrix, Matrix3f normals, IVertexBuilder vertexBuilder, int offsetX, int offsetY, int offsetZ, float textureX, float textureY, int normalX, int normalY, int normalZ, int packedLightIn)
    {
        vertexBuilder.pos(matrix, (float)offsetX, (float)offsetY, (float)offsetZ).color(255, 255, 255, 255).tex(textureX, textureY).overlay(OverlayTexture.NO_OVERLAY).lightmap(packedLightIn).normal(normals, (float)normalX, (float)normalZ, (float)normalY).endVertex();
    }

}