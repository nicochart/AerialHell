package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.9.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

public class TornSpiritModel extends EntityModel<TornSpiritEntity>
{
	private final ModelRenderer body;
	private final ModelRenderer frontCrest2_r1;
	private final ModelRenderer frontCrest1_r1;
	private final ModelRenderer backCrest_r1;
	private final ModelRenderer leftLegBone_r1;
	private final ModelRenderer head;
	private final ModelRenderer headPlane_r1;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightArmCrest_r1;
	private final ModelRenderer rightArm_r1;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArmCrest_r1;
	private final ModelRenderer leftArm_r1;

	public TornSpiritModel()
	{
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		

		frontCrest2_r1 = new ModelRenderer(this);
		frontCrest2_r1.setRotationPoint(3.0F, 5.5478F, -4.3574F);
		body.addChild(frontCrest2_r1);
		setRotationAngle(frontCrest2_r1, 0.258F, -0.045F, 0.1687F);
		frontCrest2_r1.setTextureOffset(0, 5).addBox(0.0F, -3.0F, -1.6426F, 0.0F, 6.0F, 4.0F, 0.0F, true);

		frontCrest1_r1 = new ModelRenderer(this);
		frontCrest1_r1.setRotationPoint(-2.0F, 8.5221F, -3.9658F);
		body.addChild(frontCrest1_r1);
		setRotationAngle(frontCrest1_r1, 0.043F, 0.0076F, -0.1744F);
		frontCrest1_r1.setTextureOffset(0, 14).addBox(0.0F, -3.0F, -1.8342F, 0.0F, 6.0F, 4.0F, 0.0F, true);

		backCrest_r1 = new ModelRenderer(this);
		backCrest_r1.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.addChild(backCrest_r1);
		setRotationAngle(backCrest_r1, 0.1309F, 0.0F, 0.0F);
		backCrest_r1.setTextureOffset(48, 0).addBox(0.0F, -7.0F, 2.0F, 0.0F, 15.0F, 8.0F, 0.0F, true);
		backCrest_r1.setTextureOffset(33, 55).addBox(1.0F, 1.0F, -1.3F, 2.0F, 7.0F, 2.0F, 0.0F, true);
		backCrest_r1.setTextureOffset(21, 55).addBox(-3.0F, 1.0F, -1.3F, 2.0F, 7.0F, 2.0F, 0.0F, true);
		backCrest_r1.setTextureOffset(15, 30).addBox(-6.0F, -8.0F, -2.0F, 12.0F, 4.0F, 4.0F, 0.0F, true);
		backCrest_r1.setTextureOffset(19, 38).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, true);

		leftLegBone_r1 = new ModelRenderer(this);
		leftLegBone_r1.setRotationPoint(0.0F, 8.0F, 0.0F);
		body.addChild(leftLegBone_r1);
		setRotationAngle(leftLegBone_r1, 0.3491F, 0.0F, 0.0F);
		leftLegBone_r1.setTextureOffset(33, 55).addBox(1.0F, 7.0F, -3.0F, 2.0F, 3.0F, 2.0F, 0.0F, true);
		leftLegBone_r1.setTextureOffset(21, 55).addBox(-3.0F, 7.0F, -3.0F, 2.0F, 3.0F, 2.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		

		headPlane_r1 = new ModelRenderer(this);
		headPlane_r1.setRotationPoint(0.0F, -4.0F, 0.0F);
		head.addChild(headPlane_r1);
		setRotationAngle(headPlane_r1, 0.0436F, 0.0F, 0.0F);
		headPlane_r1.setTextureOffset(44, 54).addBox(-6.5F, -6.5F, -2.0F, 10.0F, 10.0F, 0.0F, 0.0F, true);
		headPlane_r1.setTextureOffset(19, 1).addBox(-3.5F, -3.5F, -4.0F, 7.0F, 7.0F, 5.0F, 0.0F, true);
		headPlane_r1.setTextureOffset(15, 14).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		

		rightArmCrest_r1 = new ModelRenderer(this);
		rightArmCrest_r1.setRotationPoint(3.0F, 4.0F, 0.0F);
		rightArm.addChild(rightArmCrest_r1);
		setRotationAngle(rightArmCrest_r1, 0.2598F, -0.0227F, 0.1731F);
		rightArmCrest_r1.setTextureOffset(0, 27).addBox(-1.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, 0.0F, true);

		rightArm_r1 = new ModelRenderer(this);
		rightArm_r1.setRotationPoint(3.0F, 4.0F, 0.0F);
		rightArm.addChild(rightArm_r1);
		setRotationAngle(rightArm_r1, 0.1309F, 0.0F, 0.0F);
		rightArm_r1.setTextureOffset(47, 36).addBox(-2.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		

		leftArmCrest_r1 = new ModelRenderer(this);
		leftArmCrest_r1.setRotationPoint(-3.0F, 4.0F, 0.0F);
		leftArm.addChild(leftArmCrest_r1);
		setRotationAngle(leftArmCrest_r1, 0.3119F, -0.3743F, 0.1695F);
		leftArmCrest_r1.setTextureOffset(0, 1).addBox(-3.5F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, 0.0F, false);

		leftArm_r1 = new ModelRenderer(this);
		leftArm_r1.setRotationPoint(-3.0F, 4.0F, 0.0F);
		leftArm.addChild(leftArm_r1);
		setRotationAngle(leftArm_r1, 0.1309F, 0.0F, 0.0F);
		leftArm_r1.setTextureOffset(7, 36).addBox(0.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(TornSpiritEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / 57.29578F;
        this.head.rotateAngleX = headPitch / 57.29578F;
        
		this.rightArm.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
		this.leftArm.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}