package fr.factionbedrock.aerialhell.Client.EntityModels;

//Made with Blockbench 4.0.1
//Exported for Minecraft version 1.15 - 1.16 with Mojang mappings

import net.minecraft.client.renderer.entity.model.BipedModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.VerdigrisZombieEntity;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class VerdigrisZombieModel extends BipedModel<VerdigrisZombieEntity>
{
	private final ModelRenderer head;
	private final ModelRenderer shroomfall_r1;
	private final ModelRenderer body;
	private final ModelRenderer backshroom_r1;
	private final ModelRenderer leftArm;
	private final ModelRenderer shroom_r1;
	private final ModelRenderer rightArm;
	private final ModelRenderer shroom_r2;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;

	public VerdigrisZombieModel()
	{
		super(-12, 0, 64, 64); //modelSize, yOffsetIn, textureWidthIn, textureHeightIn
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		

		shroomfall_r1 = new ModelRenderer(this);
		shroomfall_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.addChild(shroomfall_r1);
		setRotationAngle(shroomfall_r1, 0.0436F, 0.0F, 0.0F);
		shroomfall_r1.setTextureOffset(12, 0).addBox(-4.0F, -31.0F, 3.0F, 8.0F, 4.0F, 2.0F, 0.0F, true);
		shroomfall_r1.setTextureOffset(32, 0).addBox(-4.0F, -31.0F, -7.0F, 8.0F, 4.0F, 2.0F, 0.0F, true);
		shroomfall_r1.setTextureOffset(12, 17).addBox(-4.0F, -35.0F, -7.0F, 8.0F, 4.0F, 12.0F, 0.0F, true);
		shroomfall_r1.setTextureOffset(4, 17).addBox(-6.0F, -35.0F, -5.0F, 2.0F, 4.0F, 8.0F, 0.0F, true);
		shroomfall_r1.setTextureOffset(0, 3).addBox(-6.0F, -31.0F, -5.0F, 2.0F, 4.0F, 8.0F, 0.0F, true);
		shroomfall_r1.setTextureOffset(44, 3).addBox(4.0F, -31.0F, -5.0F, 2.0F, 4.0F, 8.0F, 0.0F, true);
		shroomfall_r1.setTextureOffset(40, 17).addBox(4.0F, -35.0F, -5.0F, 2.0F, 4.0F, 8.0F, 0.0F, true);
		shroomfall_r1.setTextureOffset(16, 7).addBox(-4.0F, -37.0F, -5.0F, 8.0F, 2.0F, 8.0F, 0.0F, true);
		shroomfall_r1.setTextureOffset(16, 33).addBox(-4.0F, -31.0F, -5.0F, 8.0F, 7.0F, 8.0F, 0.0F, true);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		

		backshroom_r1 = new ModelRenderer(this);
		backshroom_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.addChild(backshroom_r1);
		setRotationAngle(backshroom_r1, 0.0436F, 0.0F, 0.0F);
		backshroom_r1.setTextureOffset(50, 35).addBox(-3.0F, -22.0F, 2.0F, 6.0F, 6.0F, 1.0F, 0.0F, true);
		backshroom_r1.setTextureOffset(20, 48).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		

		shroom_r1 = new ModelRenderer(this);
		shroom_r1.setRotationPoint(5.0F, 22.0F, 0.0F);
		leftArm.addChild(shroom_r1);
		setRotationAngle(shroom_r1, 0.0436F, 0.0F, 0.0F);
		shroom_r1.setTextureOffset(0, 32).addBox(-10.0F, -24.0F, 0.0F, 2.0F, 12.0F, 0.0F, 0.0F, false);
		shroom_r1.setTextureOffset(48, 48).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		

		shroom_r2 = new ModelRenderer(this);
		shroom_r2.setRotationPoint(-5.0F, 22.0F, 0.0F);
		rightArm.addChild(shroom_r2);
		setRotationAngle(shroom_r2, 0.0436F, 0.0F, 0.0F);
		shroom_r2.setTextureOffset(5, 32).addBox(8.0F, -24.0F, 0.0F, 2.0F, 12.0F, 0.0F, 0.0F, true);
		shroom_r2.setTextureOffset(48, 48).addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		leftLeg.setTextureOffset(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		rightLeg.setTextureOffset(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(VerdigrisZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		ModelHelper.func_239105_a_(this.leftArm, this.rightArm, this.isAggressive(entity), this.swingProgress, ageInTicks);
		this.head.rotateAngleY = netHeadYaw / 57.3F;
        this.head.rotateAngleX = headPitch / 57.3F;
        this.leftLeg.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.rightLeg.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leftLeg.rotateAngleY = 0.0F;
	    this.rightLeg.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
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
	
	public boolean isAggressive(VerdigrisZombieEntity entityIn) {return entityIn.isAggressive();}
}