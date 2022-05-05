package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/* Made with Blockbench 3.8.3
   Exported for Minecraft version 1.15 - 1.16 */


public class ForestCaterpillarModel<T extends AbstractCaterpillarEntity> extends EntityModel<T>
{
	private final ModelRenderer bodyPart_0;
	private final ModelRenderer bodyPart_1;
	private final ModelRenderer bodyPart_2;
	private final ModelRenderer bodyPart_3;

	public ForestCaterpillarModel()
	{
		textureWidth = 64;
		textureHeight = 64;

		bodyPart_0 = new ModelRenderer(this);
		bodyPart_0.setRotationPoint(0.0F, 24.0F, -3.5F);
		bodyPart_0.setTextureOffset(5, 26).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 3.0F, 0.0F, true);
		bodyPart_0.setTextureOffset(8, 23).addBox(-1.0F, -4.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);

		bodyPart_1 = new ModelRenderer(this);
		bodyPart_1.setRotationPoint(0.0F, 24.0F, 0.0F);
		bodyPart_1.setTextureOffset(17, 14).addBox(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 6.0F, 0.0F, true);
		bodyPart_1.setTextureOffset(11, 24).addBox(-5.0F, 0.0F, -4.5F, 10.0F, 0.0F, 8.0F, 0.0F, true);
		bodyPart_1.setTextureOffset(21, 7).addBox(-1.0F, -5.0F, -3.5F, 2.0F, 1.0F, 6.0F, 0.0F, true);
		bodyPart_1.setTextureOffset(25, 4).addBox(-1.0F, -6.0F, -1.5F, 2.0F, 1.0F, 2.0F, 0.0F, true);
		bodyPart_1.setTextureOffset(45, 7).addBox(0.0F, -11.0F, -3.5F, 0.0F, 5.0F, 6.0F, 0.0F, true);
		bodyPart_1.setTextureOffset(47, 8).addBox(-2.0F, -11.0F, -0.5F, 4.0F, 5.0F, 0.0F, 0.0F, true);

		bodyPart_2 = new ModelRenderer(this);
		bodyPart_2.setRotationPoint(0.0F, 24.0F, 3.0F);
		bodyPart_2.setTextureOffset(39, 27).addBox(-1.5F, -3.0F, -0.5F, 3.0F, 3.0F, 2.0F, 0.0F, true);

		bodyPart_3 = new ModelRenderer(this);
		bodyPart_3.setRotationPoint(0.0F, 24.0F, 4.0F);
		bodyPart_3.setTextureOffset(41, 23).addBox(-0.5F, -2.0F, 0.5F, 1.0F, 2.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.bodyPart_1.rotateAngleY = MathHelper.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
        this.bodyPart_2.rotateAngleY = MathHelper.cos(ageInTicks/2 * 0.9F + (float)2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(2 - 2));
        this.bodyPart_3.rotateAngleY = MathHelper.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(3 - 2));
        this.bodyPart_0.rotateAngleY = MathHelper.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(0 - 2));
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		bodyPart_0.render(matrixStack, buffer, packedLight, packedOverlay);
		bodyPart_1.render(matrixStack, buffer, packedLight, packedOverlay);
		bodyPart_2.render(matrixStack, buffer, packedLight, packedOverlay);
		bodyPart_3.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
        /*modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;*/
	}
}