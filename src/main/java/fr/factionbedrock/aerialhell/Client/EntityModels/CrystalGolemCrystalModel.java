package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Entity.Monster.AerialHellGolemEntity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

public class CrystalGolemCrystalModel<T extends AerialHellGolemEntity> extends EntityModel<T>
{
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;

	public CrystalGolemCrystalModel()
	{
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.setTextureOffset(91, 46).addBox(5.0F, -8.0F, 2.0F, 4.0F, 4.0F, 0.0F, 0.0F, true);
		body.setTextureOffset(91, 47).addBox(7.0F, -8.0F, 0.0F, 0.0F, 4.0F, 4.0F, 0.0F, true);
		body.setTextureOffset(91, 52).addBox(-4.0F, -3.0F, 5.0F, 0.0F, 8.0F, 4.0F, 0.0F, true);
		body.setTextureOffset(83, 65).addBox(-8.0F, 1.0F, 5.0F, 8.0F, 0.0F, 4.0F, 0.0F, true);
		body.setTextureOffset(114, 74).addBox(-4.0F, 0.0F, -10.0F, 0.0F, 8.0F, 4.0F, 0.0F, true);
		body.setTextureOffset(106, 72).addBox(-8.0F, 4.0F, -10.0F, 8.0F, 0.0F, 4.0F, 0.0F, true);
		body.setTextureOffset(92, 84).addBox(-1.0F, 11.0F, -6.0F, 0.0F, 4.0F, 3.0F, 0.0F, true);
		body.setTextureOffset(88, 92).addBox(-3.0F, 13.0F, -6.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);
		body.setTextureOffset(90, 73).addBox(5.0F, 0.0F, 5.0F, 0.0F, 8.0F, 5.0F, 0.0F, true);
		body.setTextureOffset(82, 71).addBox(1.0F, 4.0F, 5.0F, 8.0F, 0.0F, 5.0F, 0.0F, true);
		body.setTextureOffset(113, 51).addBox(5.0F, -3.0F, -11.0F, 0.0F, 8.0F, 5.0F, 0.0F, true);
		body.setTextureOffset(105, 65).addBox(1.0F, 1.0F, -11.0F, 8.0F, 0.0F, 5.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -7.0F, -2.0F);
		head.setTextureOffset(92, 1).addBox(-7.0F, -19.0F, 0.5F, 3.0F, 4.0F, 0.0F, 0.0F, true);
		head.setTextureOffset(88, 6).addBox(-7.0F, -17.0F, -1.5F, 3.0F, 0.0F, 4.0F, 0.0F, true);
		head.setTextureOffset(92, 35).addBox(-7.0F, -10.0F, -3.5F, 3.0F, 4.0F, 0.0F, 0.0F, true);
		head.setTextureOffset(88, 40).addBox(-7.0F, -8.0F, -5.5F, 3.0F, 0.0F, 4.0F, 0.0F, true);
		head.setTextureOffset(92, 24).addBox(4.0F, -13.0F, -3.5F, 3.0F, 4.0F, 0.0F, 0.0F, true);
		head.setTextureOffset(88, 29).addBox(4.0F, -11.0F, -5.5F, 3.0F, 0.0F, 4.0F, 0.0F, true);
		head.setTextureOffset(92, 11).addBox(1.0F, -16.0F, 2.5F, 0.0F, 4.0F, 3.0F, 0.0F, true);
		head.setTextureOffset(87, 19).addBox(-2.0F, -14.0F, 2.5F, 5.0F, 0.0F, 3.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		leftArm.setTextureOffset(88, 117).addBox(13.0F, 13.5F, -3.0F, 3.0F, 0.0F, 4.0F, 0.0F, true);
		leftArm.setTextureOffset(92, 112).addBox(13.0F, 11.5F, -1.0F, 3.0F, 4.0F, 0.0F, 0.0F, true);
		leftArm.setTextureOffset(87, 102).addBox(9.0F, 8.5F, -7.0F, 4.0F, 0.0F, 4.0F, 0.0F, true);
		leftArm.setTextureOffset(91, 93).addBox(11.0F, 6.5F, -7.0F, 0.0F, 4.0F, 4.0F, 0.0F, true);
		leftArm.setTextureOffset(91, 103).addBox(11.0F, 6.5F, -7.0F, 0.0F, 4.0F, 4.0F, 0.0F, true);
		leftArm.setTextureOffset(107, 89).addBox(11.0F, 18.5F, 3.0F, 0.0F, 4.0F, 3.0F, 0.0F, true);
		leftArm.setTextureOffset(103, 97).addBox(9.0F, 20.5F, 3.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		rightArm.setTextureOffset(104, 117).addBox(-15.0F, 5.5F, -2.0F, 2.0F, 0.0F, 4.0F, 0.0F, true);
		rightArm.setTextureOffset(108, 112).addBox(-15.0F, 3.5F, 0.0F, 2.0F, 4.0F, 0.0F, 0.0F, true);
		rightArm.setTextureOffset(103, 107).addBox(-13.0F, 12.5F, -6.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);
		rightArm.setTextureOffset(107, 99).addBox(-11.0F, 10.5F, -6.0F, 0.0F, 4.0F, 3.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(4.0F, 11.0F, 0.0F);
		leftLeg.setTextureOffset(71, 118).addBox(-0.5F, 8.0F, 2.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);
		leftLeg.setTextureOffset(75, 109).addBox(1.5F, 6.0F, 2.0F, 0.0F, 4.0F, 3.0F, 0.0F, true);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-5.0F, 11.0F, 0.0F);
		rightLeg.setTextureOffset(71, 106).addBox(-1.5F, 5.0F, -6.0F, 4.0F, 0.0F, 3.0F, 0.0F, false);
		rightLeg.setTextureOffset(75, 98).addBox(0.5F, 3.0F, -6.0F, 0.0F, 4.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		
		int i = entity.attackTimer;
        if (i > 0)
        {
            this.leftArm.rotateAngleX = -2.0F + 0.6F * MathHelper.func_233021_e_((float)i, 10.0F);
            this.rightArm.rotateAngleX = -2.0F + 0.6F * MathHelper.func_233021_e_((float)i, 10.0F);
        }
        else
        {
        	this.leftArm.rotateAngleX = (-0.2F + 0.8F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
        	this.rightArm.rotateAngleX = (-0.2F - 0.8F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
        }
        
		this.leftLeg.rotateAngleX = -1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.rotateAngleX = 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.rotateAngleY = 0.0F;
		this.rightLeg.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}