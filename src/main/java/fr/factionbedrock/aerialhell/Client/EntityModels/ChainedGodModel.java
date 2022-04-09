package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings


public class ChainedGodModel extends EntityModel<ChainedGodEntity>
{
	private final ModelRenderer body;
	private final ModelRenderer back_r1;
	private final ModelRenderer back_r2;
	private final ModelRenderer chains;
	private final ModelRenderer head;
	private final ModelRenderer rightHorn_r1;
	private final ModelRenderer rightHorn_r2;
	private final ModelRenderer leftHorn_r1;
	private final ModelRenderer leftHorn_r2;
	private final ModelRenderer leftArm;
	private final ModelRenderer arm3_r1;
	private final ModelRenderer arm6_r1;
	private final ModelRenderer arm1_r1;
	private final ModelRenderer rightArm;
	private final ModelRenderer arm8_r1;
	private final ModelRenderer arm3_r2;
	private final ModelRenderer arm2_r1;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public ChainedGodModel()
	{
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.setTextureOffset(65, 130).addBox(-21.0F, -32.0F, -8.0F, 42.0F, 21.0F, 17.0F, 0.0F, true);
		body.setTextureOffset(72, 168).addBox(-18.0F, -11.0F, -7.0F, 36.0F, 13.0F, 16.0F, 0.0F, true);
		body.setTextureOffset(7, 106).addBox(-7.0F, -11.0F, 8.94F, 14.0F, 3.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(7, 111).addBox(-7.0F, -6.0F, 8.9F, 14.0F, 3.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(1, 89).addBox(-15.0F, -9.0F, 9.0F, 4.0F, 4.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(33, 89).addBox(11.0F, -9.0F, 9.0F, 4.0F, 4.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(17, 71).addBox(-2.0F, -32.0F, 9.0F, 4.0F, 33.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(199, 106).addBox(-10.0F, -7.0F, -9.0F, 9.0F, 6.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(199, 95).addBox(-10.0F, -14.0F, -9.0F, 9.0F, 6.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(192, 80).addBox(-14.0F, -25.0F, -10.0F, 12.0F, 10.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(50, 109).addBox(-20.0F, -34.0F, -9.0F, 10.0F, 6.0F, 15.0F, 0.0F, true);
		body.setTextureOffset(102, 116).addBox(-5.0F, -34.0F, -7.0F, 10.0F, 2.0F, 12.0F, 0.0F, true);
		body.setTextureOffset(14, 41).addBox(-2.0F, -34.0F, 5.0F, 4.0F, 3.0F, 5.0F, 0.0F, true);
		body.setTextureOffset(148, 109).addBox(10.0F, -34.0F, -9.0F, 10.0F, 6.0F, 15.0F, 0.0F, true);
		body.setTextureOffset(224, 80).addBox(2.0F, -25.0F, -10.0F, 12.0F, 10.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(223, 106).addBox(1.0F, -7.0F, -9.0F, 9.0F, 6.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(223, 95).addBox(1.0F, -14.0F, -9.0F, 9.0F, 6.0F, 2.0F, 0.0F, true);
		body.setTextureOffset(83, 197).addBox(-14.5F, 2.0F, -5.0F, 29.0F, 10.0F, 12.0F, 0.5F, true);

		back_r1 = new ModelRenderer(this);
		back_r1.setRotationPoint(10.5F, -24.5F, 10.0F);
		body.addChild(back_r1);
		setRotationAngle(back_r1, 0.0F, 0.0F, -0.829F);
		back_r1.setTextureOffset(0, 66).addBox(-14.5F, -1.5F, -1.1F, 21.0F, 3.0F, 2.0F, 0.0F, true);
		back_r1.setTextureOffset(3, 56).addBox(-9.5F, -6.5F, -1.1F, 18.0F, 3.0F, 2.0F, 0.0F, true);

		back_r2 = new ModelRenderer(this);
		back_r2.setRotationPoint(-10.5F, -24.5F, 10.0F);
		body.addChild(back_r2);
		setRotationAngle(back_r2, 0.0F, 0.0F, 0.829F);
		back_r2.setTextureOffset(0, 61).addBox(-6.5F, -1.5F, -1.1F, 21.0F, 3.0F, 2.0F, 0.0F, true);
		back_r2.setTextureOffset(3, 51).addBox(-8.5F, -6.5F, -1.1F, 18.0F, 3.0F, 2.0F, 0.0F, true);

		chains = new ModelRenderer(this);
		chains.setRotationPoint(0.0F, 24.0F, 0.0F);
		chains.setTextureOffset(7, 139).addBox(-22.0F, -42.0F, 9.0F, 4.0F, 34.0F, 0.0F, 0.0F, true);
		chains.setTextureOffset(11, 139).addBox(-19.0F, -42.0F, -8.0F, 4.0F, 34.0F, 0.0F, 0.0F, true);
		chains.setTextureOffset(233, 146).addBox(19.0F, -42.0F, -8.0F, 4.0F, 26.0F, 0.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -35.0F, -2.0F);
		head.setTextureOffset(96, 75).addBox(-8.0F, -21.0F, -5.5F, 16.0F, 12.0F, 12.0F, 0.0F, true);
		head.setTextureOffset(84, 12).addBox(-7.0F, -20.0F, -5.5F, 14.0F, 16.0F, 9.0F, 0.0F, true);
		head.setTextureOffset(130, 21).addBox(-7.0F, -20.0F, -1.5F, 14.0F, 16.0F, 0.0F, 0.0F, true);
		head.setTextureOffset(100, 99).addBox(-6.0F, -9.0F, -5.5F, 12.0F, 5.0F, 12.0F, 0.0F, true);
		head.setTextureOffset(20, 21).addBox(-1.0F, -20.0F, 6.5F, 2.0F, 16.0F, 1.0F, 0.0F, true);
		head.setTextureOffset(16, 15).addBox(-3.0F, -10.0F, 6.4F, 6.0F, 2.0F, 1.0F, 0.0F, true);
		head.setTextureOffset(18, 18).addBox(-2.0F, -7.0F, 6.4F, 4.0F, 2.0F, 1.0F, 0.0F, true);
		head.setTextureOffset(12, 12).addBox(-5.0F, -13.0F, 6.4F, 10.0F, 2.0F, 1.0F, 0.0F, true);
		head.setTextureOffset(10, 9).addBox(-6.0F, -16.0F, 6.4F, 12.0F, 2.0F, 1.0F, 0.0F, true);
		head.setTextureOffset(8, 6).addBox(-7.0F, -19.0F, 6.4F, 14.0F, 2.0F, 1.0F, 0.0F, true);
		head.setTextureOffset(64, 99).addBox(-4.0F, -9.0F, -8.5F, 8.0F, 5.0F, 3.0F, 0.0F, true);
		head.setTextureOffset(56, 81).addBox(-8.0F, -21.0F, -8.5F, 16.0F, 9.0F, 3.0F, 0.0F, true);
		head.setTextureOffset(60, 93).addBox(-6.0F, -12.0F, -8.5F, 12.0F, 3.0F, 3.0F, 0.0F, true);

		rightHorn_r1 = new ModelRenderer(this);
		rightHorn_r1.setRotationPoint(0.0F, -26.6543F, -1.0F);
		head.addChild(rightHorn_r1);
		setRotationAngle(rightHorn_r1, -0.1897F, -0.1084F, -0.5133F);
		rightHorn_r1.setTextureOffset(111, 51).addBox(-10.9384F, -9.1522F, -1.5F, 3.0F, 17.0F, 3.0F, 0.0F, true);

		rightHorn_r2 = new ModelRenderer(this);
		rightHorn_r2.setRotationPoint(0.0F, -26.6543F, -1.0F);
		head.addChild(rightHorn_r2);
		setRotationAngle(rightHorn_r2, -0.202F, 0.0829F, 0.3843F);
		rightHorn_r2.setTextureOffset(113, 43).addBox(-13.2496F, -2.4227F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, true);

		leftHorn_r1 = new ModelRenderer(this);
		leftHorn_r1.setRotationPoint(0.0F, -26.6543F, -1.0F);
		head.addChild(leftHorn_r1);
		setRotationAngle(leftHorn_r1, -0.202F, -0.0829F, -0.3843F);
		leftHorn_r1.setTextureOffset(127, 43).addBox(11.2496F, -2.4227F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, true);

		leftHorn_r2 = new ModelRenderer(this);
		leftHorn_r2.setRotationPoint(0.0F, -26.6543F, -1.0F);
		head.addChild(leftHorn_r2);
		setRotationAngle(leftHorn_r2, -0.1897F, 0.1084F, 0.5133F);
		leftHorn_r2.setTextureOffset(125, 51).addBox(7.9384F, -9.1522F, -1.5F, 3.0F, 17.0F, 3.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(21.1131F, -31.9811F, 2.1442F);
		

		arm3_r1 = new ModelRenderer(this);
		arm3_r1.setRotationPoint(10.3735F, 31.0968F, 4.0241F);
		leftArm.addChild(arm3_r1);
		setRotationAngle(arm3_r1, -0.2133F, -0.0594F, -0.1206F);
		arm3_r1.setTextureOffset(231, 210).addBox(3.0F, -10.5F, -6.0F, 1.0F, 25.0F, 4.0F, 0.0F, true);
		arm3_r1.setTextureOffset(187, 185).addBox(-5.0F, -13.5F, -10.0F, 8.0F, 27.0F, 12.0F, 0.0F, true);

		arm6_r1 = new ModelRenderer(this);
		arm6_r1.setRotationPoint(4.8869F, -0.0189F, -1.1442F);
		leftArm.addChild(arm6_r1);
		setRotationAngle(arm6_r1, 0.1309F, 0.0F, -0.1745F);
		arm6_r1.setTextureOffset(242, 205).addBox(5.0F, 4.5F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm6_r1.setTextureOffset(242, 199).addBox(5.0F, 1.5F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm6_r1.setTextureOffset(242, 193).addBox(5.0F, -1.5F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm6_r1.setTextureOffset(242, 187).addBox(5.0F, -4.5F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm6_r1.setTextureOffset(231, 177).addBox(4.0F, -5.5F, -1.0F, 1.0F, 28.0F, 4.0F, 0.0F, true);
		arm6_r1.setTextureOffset(187, 146).addBox(-4.0F, -4.5F, -5.0F, 8.0F, 27.0F, 12.0F, 0.0F, true);

		arm1_r1 = new ModelRenderer(this);
		arm1_r1.setRotationPoint(3.8869F, -0.5189F, -1.1442F);
		leftArm.addChild(arm1_r1);
		setRotationAngle(arm1_r1, 0.0873F, 0.0F, 0.0F);
		arm1_r1.setTextureOffset(216, 125).addBox(-5.0F, -7.0F, -1.0F, 9.0F, 1.0F, 4.0F, 0.0F, true);
		arm1_r1.setTextureOffset(187, 122).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 12.0F, 12.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-21.0643F, -31.9819F, 2.1278F);
		

		arm8_r1 = new ModelRenderer(this);
		arm8_r1.setRotationPoint(-4.9357F, -0.0181F, -1.1278F);
		rightArm.addChild(arm8_r1);
		setRotationAngle(arm8_r1, 0.1309F, 0.0F, 0.1745F);
		arm8_r1.setTextureOffset(0, 205).addBox(-6.0F, 4.5F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm8_r1.setTextureOffset(0, 199).addBox(-6.0F, 1.5F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm8_r1.setTextureOffset(0, 193).addBox(-6.0F, -1.5F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm8_r1.setTextureOffset(0, 187).addBox(-6.0F, -4.5F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm8_r1.setTextureOffset(7, 177).addBox(-5.0F, -5.5F, -1.0F, 1.0F, 28.0F, 4.0F, 0.0F, true);
		arm8_r1.setTextureOffset(21, 146).addBox(-4.0F, -4.5F, -5.0F, 8.0F, 27.0F, 12.0F, 0.0F, true);

		arm3_r2 = new ModelRenderer(this);
		arm3_r2.setRotationPoint(-10.4222F, 31.0976F, 4.0405F);
		rightArm.addChild(arm3_r2);
		setRotationAngle(arm3_r2, -0.2182F, 0.0F, 0.1309F);
		arm3_r2.setTextureOffset(7, 210).addBox(-4.0F, -10.5F, -6.0F, 1.0F, 25.0F, 4.0F, 0.0F, true);
		arm3_r2.setTextureOffset(21, 185).addBox(-3.0F, -13.5F, -10.0F, 8.0F, 27.0F, 12.0F, 0.0F, true);

		arm2_r1 = new ModelRenderer(this);
		arm2_r1.setRotationPoint(-3.9357F, -0.5181F, -1.1278F);
		rightArm.addChild(arm2_r1);
		setRotationAngle(arm2_r1, 0.0873F, 0.0F, 0.0F);
		arm2_r1.setTextureOffset(5, 125).addBox(-4.0F, -7.0F, -1.0F, 9.0F, 1.0F, 4.0F, 0.0F, true);
		arm2_r1.setTextureOffset(21, 122).addBox(-4.0F, -6.0F, -6.0F, 8.0F, 12.0F, 12.0F, 0.0F, true);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(4.0F, 11.0F, 0.0F);
		rightLeg.setTextureOffset(127, 224).addBox(-2.5F, -7.0F, -5.0F, 10.0F, 20.0F, 11.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-5.0F, 11.0F, 0.0F);
		leftLeg.setTextureOffset(79, 224).addBox(-6.5F, -7.0F, -5.0F, 10.0F, 20.0F, 11.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ChainedGodEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / 57.29578F;
        this.head.rotateAngleX = headPitch / 57.29578F;
        
        if (!entity.isImploding())
        {
        	int i = entity.attackTimer;
            if (i > 0)
            {
                this.rightArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float)i, 10.0F) * 0.5f;
                this.leftArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float)i, 10.0F) * 0.5f;
            }
            else
            {
            	this.rightArm.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
            	this.leftArm.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
            }
        }
        else
        {
        	this.rightArm.rotateAngleX = - 2.2F;
        	this.leftArm.rotateAngleX = - 2.2F;
        }
        
		this.leftLeg.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.rightLeg.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leftLeg.rotateAngleY = 0.0F;
	    this.rightLeg.rotateAngleY = 0.0F;
	}
	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		chains.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
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