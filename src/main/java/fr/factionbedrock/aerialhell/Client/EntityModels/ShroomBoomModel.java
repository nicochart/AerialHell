package fr.factionbedrock.aerialhell.Client.EntityModels;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.ShroomBoomEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ShroomBoomModel extends EntityModel<ShroomBoomEntity>
{
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;

	public ShroomBoomModel()
	{
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(16, 49).addBox(-4.0F, -18.0F, -2.0F, 8.0F, 11.0F, 4.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 6.0F, 0.0F);
		head.setTextureOffset(12, 33).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
		head.setTextureOffset(8, 17).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 6.0F, 10.0F, 0.0F, true);
		head.setTextureOffset(16, 9).addBox(-3.0F, -12.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, true);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(2.0F, 18.0F, 4.0F);
		leg0.setTextureOffset(0, 53).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-2.0F, 18.0F, 4.0F);
		leg1.setTextureOffset(0, 53).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(2.0F, 18.0F, -4.0F);
		leg2.setTextureOffset(0, 53).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, true);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-2.0F, 18.0F, -4.0F);
		leg3.setTextureOffset(0, 53).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, true);
		
		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-4.0F, 7.0F, 0.0F);
		rightArm.setTextureOffset(50, 53).addBox(-3.0F, 1.0F, -2.0F, 3.0F, 7.0F, 4.0F, 0.0F, true);
		rightArm.setTextureOffset(42, 17).addBox(-4.0F, -1.0F, -3.0F, 5.0F, 3.0F, 6.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(4.0F, 7.0F, 0.0F);
		leftArm.setTextureOffset(50, 42).addBox(0.0F, 1.0F, -2.0F, 3.0F, 7.0F, 4.0F, 0.0F, true);
		leftArm.setTextureOffset(42, 8).addBox(-1.0F, -1.0F, -3.0F, 5.0F, 3.0F, 6.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(ShroomBoomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		ModelHelper.func_239105_a_(this.leftArm, this.rightArm, this.isAggressive(entity), this.swingProgress, ageInTicks);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	    this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
	    this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	    this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	    this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	    this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
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
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public boolean isAggressive(ShroomBoomEntity entityIn) {return entityIn.isAggressive();}
}