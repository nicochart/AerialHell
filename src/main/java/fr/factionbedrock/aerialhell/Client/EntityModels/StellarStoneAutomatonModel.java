package fr.factionbedrock.aerialhell.Client.EntityModels;

import net.minecraft.client.renderer.entity.model.BipedModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.StellarStoneAutomatonEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;


public class StellarStoneAutomatonModel<T extends StellarStoneAutomatonEntity> extends BipedModel<T>
{
	private final ModelRenderer body;
	private final ModelRenderer body_r1;
	private final ModelRenderer head;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightArm_r1;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm_r1;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rightLeg_r1;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftLeg_r1;

	public StellarStoneAutomatonModel()
	{
		super(0F, -14F, 64, 32);
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, 0.0873F, 0.0F, 0.0F);
		body_r1.setTextureOffset(34, 3).addBox(-1.0F, -28.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, true);
		body_r1.setTextureOffset(32, 0).addBox(-2.0F, -25.0F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, true);
		body_r1.setTextureOffset(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		

		rightArm_r1 = new ModelRenderer(this);
		rightArm_r1.setRotationPoint(-5.0F, 22.0F, 0.0F);
		rightArm.addChild(rightArm_r1);
		setRotationAngle(rightArm_r1, 0.0873F, 0.0F, 0.0F);
		rightArm_r1.setTextureOffset(40, 16).addBox(4.0F, -24.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		

		leftArm_r1 = new ModelRenderer(this);
		leftArm_r1.setRotationPoint(5.0F, 22.0F, 0.0F);
		leftArm.addChild(leftArm_r1);
		setRotationAngle(leftArm_r1, 0.0873F, 0.0F, 0.0F);
		leftArm_r1.setTextureOffset(40, 16).addBox(-6.0F, -24.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
		

		rightLeg_r1 = new ModelRenderer(this);
		rightLeg_r1.setRotationPoint(-2.0F, 12.0F, 0.0F);
		rightLeg.addChild(rightLeg_r1);
		setRotationAngle(rightLeg_r1, 0.0436F, 0.0F, 0.0F);
		rightLeg_r1.setTextureOffset(0, 16).addBox(1.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		

		leftLeg_r1 = new ModelRenderer(this);
		leftLeg_r1.setRotationPoint(2.0F, 12.0F, 0.0F);
		leftLeg.addChild(leftLeg_r1);
		setRotationAngle(leftLeg_r1, 0.0436F, 0.0F, 0.0F);
		leftLeg_r1.setTextureOffset(0, 16).addBox(-3.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = 0.0873F + headPitch * ((float)Math.PI / 180F);

		this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.7F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.7F) * 1.4F * limbSwingAmount;

		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.7F) * 1.4F * limbSwingAmount;
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.7F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}