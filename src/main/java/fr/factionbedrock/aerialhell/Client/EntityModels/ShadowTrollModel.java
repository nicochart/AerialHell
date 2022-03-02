package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.ShadowTrollEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

public class ShadowTrollModel extends EntityModel<ShadowTrollEntity>
{
	private boolean isEyes;
	
	private final ModelRenderer body;
	private final ModelRenderer body_r1;
	private final ModelRenderer body_r2;
	private final ModelRenderer head;
	private final ModelRenderer head_r1;
	private final ModelRenderer head_r2;
	private final ModelRenderer head_r3;
	private final ModelRenderer head_r4;
	private final ModelRenderer head_r5;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightArm_r1;
	private final ModelRenderer rightArm_r2;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm_r1;
	private final ModelRenderer leftArm_r2;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rightLeg_r1;
	private final ModelRenderer rightLeg_r2;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftLeg_r1;
	private final ModelRenderer leftLeg_r2;
	private final ModelRenderer eyes;
	private final ModelRenderer eyeLeft_r1;
	private final ModelRenderer eyeRight_r1;

	public ShadowTrollModel(boolean isEyes)
	{
		this.isEyes = isEyes;
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -14.0F, 0.0F);
		

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, 7.5328F, -0.7393F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, 0.2182F, 0.0F, 0.0F);
		body_r1.setTextureOffset(4, 48).addBox(-4.0F, 0.4672F, -2.2607F, 8.0F, 5.0F, 4.0F, 0.0F, true);
		body_r1.setTextureOffset(0, 37).addBox(-6.0F, -6.5328F, -2.2607F, 12.0F, 7.0F, 4.0F, 0.0F, true);

		body_r2 = new ModelRenderer(this);
		body_r2.setRotationPoint(0.0F, 7.5328F, -0.7393F);
		body.addChild(body_r2);
		setRotationAngle(body_r2, 0.0873F, 0.0F, 0.0F);
		body_r2.setTextureOffset(4, 29).addBox(-3.0F, -7.5328F, -4.2607F, 6.0F, 2.0F, 6.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -13.0F, -3.0F);
		

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 6.5328F, 2.2607F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, 0.0873F, 0.0F, 0.0F);
		head_r1.setTextureOffset(2, 17).addBox(-3.0F, -11.5328F, -7.2607F, 6.0F, 4.0F, 8.0F, 0.0F, true);
		head_r1.setTextureOffset(5, 0).addBox(-3.0F, -16.5328F, -5.2607F, 6.0F, 1.0F, 5.0F, 0.0F, true);
		head_r1.setTextureOffset(1, 6).addBox(-4.0F, -15.5328F, -6.2607F, 8.0F, 4.0F, 7.0F, 0.0F, true);

		head_r2 = new ModelRenderer(this);
		head_r2.setRotationPoint(-4.5F, -10.6927F, -2.0176F);
		head.addChild(head_r2);
		setRotationAngle(head_r2, 0.0455F, 0.032F, -1.0475F);
		head_r2.setTextureOffset(40, 20).addBox(-3.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);

		head_r3 = new ModelRenderer(this);
		head_r3.setRotationPoint(-4.5F, -10.6927F, -2.0176F);
		head.addChild(head_r3);
		setRotationAngle(head_r3, -0.129F, 0.032F, -1.0475F);
		head_r3.setTextureOffset(42, 16).addBox(-2.5F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		head_r4 = new ModelRenderer(this);
		head_r4.setRotationPoint(4.5F, -10.6927F, -2.0176F);
		head.addChild(head_r4);
		setRotationAngle(head_r4, 0.0437F, -0.0756F, 1.0455F);
		head_r4.setTextureOffset(48, 20).addBox(1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);

		head_r5 = new ModelRenderer(this);
		head_r5.setRotationPoint(4.5F, -10.6927F, -2.0176F);
		head.addChild(head_r5);
		setRotationAngle(head_r5, -0.1308F, -0.0756F, 1.0455F);
		head_r5.setTextureOffset(50, 16).addBox(1.5F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(6.0F, -10.86F, -1.7731F);
		setRotationAngle(rightArm, 0.0F, -0.2618F, -0.1745F);
		

		rightArm_r1 = new ModelRenderer(this);
		rightArm_r1.setRotationPoint(-7.0F, 5.3928F, -0.9662F);
		rightArm.addChild(rightArm_r1);
		setRotationAngle(rightArm_r1, 0.2182F, 0.0F, 0.0F);
		rightArm_r1.setTextureOffset(32, 31).addBox(6.0F, -6.2581F, 0.8184F, 2.0F, 14.0F, 2.0F, 0.0F, true);

		rightArm_r2 = new ModelRenderer(this);
		rightArm_r2.setRotationPoint(-7.0F, 5.3928F, -0.9662F);
		rightArm.addChild(rightArm_r2);
		setRotationAngle(rightArm_r2, -0.3927F, 0.0F, 0.0F);
		rightArm_r2.setTextureOffset(32, 47).addBox(6.0F, 4.7521F, 4.6925F, 2.0F, 15.0F, 2.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-6.0F, -11.86F, -1.7731F);
		setRotationAngle(leftArm, 0.0579F, 0.2555F, 0.2256F);
		

		leftArm_r1 = new ModelRenderer(this);
		leftArm_r1.setRotationPoint(0.0F, 14.0F, 0.0F);
		leftArm.addChild(leftArm_r1);
		setRotationAngle(leftArm_r1, 0.2182F, 0.0F, 0.0F);
		leftArm_r1.setTextureOffset(40, 31).addBox(-1.0F, -13.8941F, 1.5216F, 2.0F, 14.0F, 2.0F, 0.0F, false);

		leftArm_r2 = new ModelRenderer(this);
		leftArm_r2.setRotationPoint(0.0F, 14.0F, 0.0F);
		leftArm.addChild(leftArm_r2);
		setRotationAngle(leftArm_r2, -0.3927F, 0.0F, 0.0F);
		leftArm_r2.setTextureOffset(40, 47).addBox(-1.0F, -1.9063F, 0.8888F, 2.0F, 15.0F, 2.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(2.0F, -2.0F, 0.0F);
		setRotationAngle(rightLeg, 0.0F, -0.3491F, 0.0F);
		

		rightLeg_r1 = new ModelRenderer(this);
		rightLeg_r1.setRotationPoint(0.0F, 26.0F, 0.0F);
		rightLeg.addChild(rightLeg_r1);
		setRotationAngle(rightLeg_r1, 0.2182F, 0.0F, 0.0F);
		rightLeg_r1.setTextureOffset(56, 48).addBox(-1.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, true);

		rightLeg_r2 = new ModelRenderer(this);
		rightLeg_r2.setRotationPoint(0.0F, 1.5F, 0.0F);
		rightLeg.addChild(rightLeg_r2);
		setRotationAngle(rightLeg_r2, -0.2618F, 0.0F, 0.0F);
		rightLeg_r2.setTextureOffset(56, 32).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-2.0F, -2.0F, 0.0F);
		setRotationAngle(leftLeg, 0.0F, 0.3491F, 0.0F);
		

		leftLeg_r1 = new ModelRenderer(this);
		leftLeg_r1.setRotationPoint(0.0F, 1.5F, 0.0F);
		leftLeg.addChild(leftLeg_r1);
		setRotationAngle(leftLeg_r1, -0.2618F, 0.0F, 0.0F);
		leftLeg_r1.setTextureOffset(48, 32).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);

		leftLeg_r2 = new ModelRenderer(this);
		leftLeg_r2.setRotationPoint(0.0F, 26.0F, 0.0F);
		leftLeg.addChild(leftLeg_r2);
		setRotationAngle(leftLeg_r2, 0.2182F, 0.0F, 0.0F);
		leftLeg_r2.setTextureOffset(48, 48).addBox(-1.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);

		eyes = new ModelRenderer(this);
		eyes.setRotationPoint(0.0F, -13.0F, -3.0F);
		

		eyeLeft_r1 = new ModelRenderer(this);
		eyeLeft_r1.setRotationPoint(2.0F, -5.9048F, -5.1121F);
		eyes.addChild(eyeLeft_r1);
		setRotationAngle(eyeLeft_r1, 0.0873F, 0.0F, -0.1745F);
		eyeLeft_r1.setTextureOffset(32, 3).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, true);

		eyeRight_r1 = new ModelRenderer(this);
		eyeRight_r1.setRotationPoint(-2.0F, -5.9048F, -5.1121F);
		eyes.addChild(eyeRight_r1);
		setRotationAngle(eyeRight_r1, 0.0873F, 0.0F, 0.1745F);
		eyeRight_r1.setTextureOffset(32, 3).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(ShadowTrollEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / 57.3F;
        this.head.rotateAngleX = headPitch / 57.3F;
        this.eyes.rotateAngleY = netHeadYaw / 57.3F;
        this.eyes.rotateAngleX = headPitch / 57.3F;
        
        if (!entity.isDisappearing())
        {
        	this.rightArm.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
        	this.leftArm.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
        }
        else
        {
        	this.rightArm.rotateAngleX = - 2.2F;
        	this.leftArm.rotateAngleX = - 2.2F;
        }
		//ajouter mouvement des bras quand il tape ?
    	
		this.leftLeg.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.rightLeg.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    //this.leftLeg.rotateAngleY = 0.0F;
	    //this.rightLeg.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{		
		if (isEyes)
		{
			eyes.render(matrixStack, buffer, packedLight, packedOverlay);
		}
		else
		{
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
			leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
			rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		}
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}