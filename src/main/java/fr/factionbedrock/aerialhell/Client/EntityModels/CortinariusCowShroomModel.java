package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

public class CortinariusCowShroomModel<T extends EvilCowEntity> extends CowModel<T>
{
	private final ModelRenderer body;
	private final ModelRenderer shroom_r1;
	private final ModelRenderer head;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;

	public CortinariusCowShroomModel()
	{
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 5.0F, 2.0F);
		
		shroom_r1 = new ModelRenderer(this);
		shroom_r1.setRotationPoint(0.0F, -1.0F, -2.0F);
		body.addChild(shroom_r1);
		setRotationAngle(shroom_r1, 1.5708F, 0.0F, 0.0F);
		shroom_r1.setTextureOffset(28, 18).addBox(4.0F, 1.0F, -9.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);
		shroom_r1.setTextureOffset(28, 24).addBox(4.0F, -10.0F, -6.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);
		shroom_r1.setTextureOffset(26, 10).addBox(-7.0F, -10.0F, -6.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);
		shroom_r1.setTextureOffset(4, 24).addBox(-7.0F, 6.5F, -5.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);
		shroom_r1.setTextureOffset(24, 0).addBox(2.0F, 5.5F, -2.0F, 5.0F, 5.0F, 5.0F, 0.0F, true);
		shroom_r1.setTextureOffset(0, 12).addBox(-7.0F, -1.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, true);
		shroom_r1.setTextureOffset(0, 0).addBox(2.0F, -6.0F, -2.0F, 6.0F, 6.0F, 6.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 4.0F, -8.0F);
		

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(4.0F, 12.0F, 7.0F);
		leg0.setTextureOffset(45, 12).addBox(0.0F, 2.0F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-4.0F, 12.0F, 7.0F);
		leg1.setTextureOffset(45, 18).addBox(-3.0F, 5.0F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(4.0F, 12.0F, -6.0F);
		leg2.setTextureOffset(45, 0).addBox(0.0F, 6.0F, -2.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-4.0F, 12.0F, -6.0F);
		leg3.setTextureOffset(45, 6).addBox(-3.0F, 2.0F, -2.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg2.render(matrixStack, buffer, packedLight, packedOverlay);
		leg3.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
	    this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	    this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	    this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	    this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}