package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.ChainedGodRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ChainedGodModel extends EntityModel<ChainedGodRenderState>
{
	private final ModelPart body;
	private final ModelPart chains;
	private final ModelPart head;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public ChainedGodModel(ModelPart root)
	{
		super(root);
		this.body = root.getChild("body");
		this.chains = root.getChild("chains");
		this.head = root.getChild("head");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(65, 130).mirrored().cuboid(-21.0F, -32.0F, -8.0F, 42.0F, 21.0F, 17.0F, new Dilation(0.0F)).mirrored(false)
		.uv(72, 168).mirrored().cuboid(-18.0F, -11.0F, -7.0F, 36.0F, 13.0F, 16.0F, new Dilation(0.0F)).mirrored(false)
		.uv(7, 106).mirrored().cuboid(-7.0F, -11.0F, 8.94F, 14.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(7, 111).mirrored().cuboid(-7.0F, -6.0F, 8.9F, 14.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(1, 89).mirrored().cuboid(-15.0F, -9.0F, 9.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(33, 89).mirrored().cuboid(11.0F, -9.0F, 9.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(17, 71).mirrored().cuboid(-2.0F, -32.0F, 9.0F, 4.0F, 33.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(199, 106).mirrored().cuboid(-10.0F, -7.0F, -9.0F, 9.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(199, 95).mirrored().cuboid(-10.0F, -14.0F, -9.0F, 9.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(192, 80).mirrored().cuboid(-14.0F, -25.0F, -10.0F, 12.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(50, 109).mirrored().cuboid(-20.0F, -34.0F, -9.0F, 10.0F, 6.0F, 15.0F, new Dilation(0.0F)).mirrored(false)
		.uv(102, 116).mirrored().cuboid(-5.0F, -34.0F, -7.0F, 10.0F, 2.0F, 12.0F, new Dilation(0.0F)).mirrored(false)
		.uv(14, 41).mirrored().cuboid(-2.0F, -34.0F, 5.0F, 4.0F, 3.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(148, 109).mirrored().cuboid(10.0F, -34.0F, -9.0F, 10.0F, 6.0F, 15.0F, new Dilation(0.0F)).mirrored(false)
		.uv(224, 80).mirrored().cuboid(2.0F, -25.0F, -10.0F, 12.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(223, 106).mirrored().cuboid(1.0F, -7.0F, -9.0F, 9.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(223, 95).mirrored().cuboid(1.0F, -14.0F, -9.0F, 9.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(83, 197).mirrored().cuboid(-14.5F, 2.0F, -5.0F, 29.0F, 10.0F, 12.0F, new Dilation(0.5F)).mirrored(false), ModelTransform.pivot(0.0F, -7.0F, 0.0F));

		ModelPartData back_r1 = body.addChild("back_r1", ModelPartBuilder.create().uv(0, 66).mirrored().cuboid(-14.5F, -1.5F, -1.1F, 21.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(3, 56).mirrored().cuboid(-9.5F, -6.5F, -1.1F, 18.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(10.5F, -24.5F, 10.0F, 0.0F, 0.0F, -0.829F));

		ModelPartData back_r2 = body.addChild("back_r2", ModelPartBuilder.create().uv(0, 61).mirrored().cuboid(-6.5F, -1.5F, -1.1F, 21.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(3, 51).mirrored().cuboid(-8.5F, -6.5F, -1.1F, 18.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-10.5F, -24.5F, 10.0F, 0.0F, 0.0F, 0.829F));

		ModelPartData chains = partdefinition.addChild("chains", ModelPartBuilder.create().uv(7, 139).mirrored().cuboid(-22.0F, -42.0F, 9.0F, 4.0F, 34.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(11, 139).mirrored().cuboid(-19.0F, -42.0F, -8.0F, 4.0F, 34.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(233, 146).mirrored().cuboid(19.0F, -42.0F, -8.0F, 4.0F, 26.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(96, 75).mirrored().cuboid(-8.0F, -23.0F, -5.5F, 16.0F, 12.0F, 12.0F, new Dilation(0.0F)).mirrored(false)
		.uv(84, 12).mirrored().cuboid(-7.0F, -22.0F, -5.5F, 14.0F, 16.0F, 9.0F, new Dilation(0.0F)).mirrored(false)
		.uv(130, 21).mirrored().cuboid(-7.0F, -22.0F, -1.5F, 14.0F, 16.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(100, 99).mirrored().cuboid(-6.0F, -11.0F, -5.5F, 12.0F, 5.0F, 12.0F, new Dilation(0.0F)).mirrored(false)
		.uv(20, 21).mirrored().cuboid(-1.0F, -22.0F, 6.5F, 2.0F, 16.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(16, 15).mirrored().cuboid(-3.0F, -12.0F, 6.4F, 6.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(18, 18).mirrored().cuboid(-2.0F, -9.0F, 6.4F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(12, 12).mirrored().cuboid(-5.0F, -15.0F, 6.4F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(10, 9).mirrored().cuboid(-6.0F, -18.0F, 6.4F, 12.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(8, 6).mirrored().cuboid(-7.0F, -21.0F, 6.4F, 14.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(64, 99).mirrored().cuboid(-4.0F, -11.0F, -8.5F, 8.0F, 5.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(56, 81).mirrored().cuboid(-8.0F, -23.0F, -8.5F, 16.0F, 9.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(60, 93).mirrored().cuboid(-6.0F, -14.0F, -8.5F, 12.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -35.0F, -2.0F));

		ModelPartData rightHorn_r1 = head.addChild("rightHorn_r1", ModelPartBuilder.create().uv(111, 51).mirrored().cuboid(-10.9384F, -9.1522F, -1.5F, 3.0F, 17.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -28.6543F, -1.0F, -0.1897F, -0.1084F, -0.5133F));

		ModelPartData rightHorn_r2 = head.addChild("rightHorn_r2", ModelPartBuilder.create().uv(113, 43).mirrored().cuboid(-13.2496F, -2.4227F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -28.6543F, -1.0F, -0.202F, 0.0829F, 0.3843F));

		ModelPartData leftHorn_r1 = head.addChild("leftHorn_r1", ModelPartBuilder.create().uv(127, 43).mirrored().cuboid(11.2496F, -2.4227F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -28.6543F, -1.0F, -0.202F, -0.0829F, -0.3843F));

		ModelPartData leftHorn_r2 = head.addChild("leftHorn_r2", ModelPartBuilder.create().uv(125, 51).mirrored().cuboid(7.9384F, -9.1522F, -1.5F, 3.0F, 17.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -28.6543F, -1.0F, -0.1897F, 0.1084F, 0.5133F));

		ModelPartData leftArm = partdefinition.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.pivot(21.1131F, -31.9811F, 2.1442F));

		ModelPartData arm3_r1 = leftArm.addChild("arm3_r1", ModelPartBuilder.create().uv(231, 210).mirrored().cuboid(3.0F, -10.5F, -6.0F, 1.0F, 25.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(187, 185).mirrored().cuboid(-5.0F, -13.5F, -10.0F, 8.0F, 27.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(10.3735F, 31.0968F, 4.0241F, -0.2133F, -0.0594F, -0.1206F));

		ModelPartData arm6_r1 = leftArm.addChild("arm6_r1", ModelPartBuilder.create().uv(242, 205).mirrored().cuboid(5.0F, 4.5F, 0.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(242, 199).mirrored().cuboid(5.0F, 1.5F, 0.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(242, 193).mirrored().cuboid(5.0F, -1.5F, 0.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(242, 187).mirrored().cuboid(5.0F, -4.5F, 0.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(231, 177).mirrored().cuboid(4.0F, -5.5F, -1.0F, 1.0F, 28.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(187, 146).mirrored().cuboid(-4.0F, -4.5F, -5.0F, 8.0F, 27.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.8869F, -0.0189F, -1.1442F, 0.1309F, 0.0F, -0.1745F));

		ModelPartData arm1_r1 = leftArm.addChild("arm1_r1", ModelPartBuilder.create().uv(216, 125).mirrored().cuboid(-5.0F, -7.0F, -1.0F, 9.0F, 1.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(187, 122).mirrored().cuboid(-4.0F, -6.0F, -6.0F, 8.0F, 12.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.8869F, -0.5189F, -1.1442F, 0.0873F, 0.0F, 0.0F));

		ModelPartData rightArm = partdefinition.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.pivot(-21.0643F, -31.9819F, 2.1278F));

		ModelPartData arm8_r1 = rightArm.addChild("arm8_r1", ModelPartBuilder.create().uv(0, 205).mirrored().cuboid(-6.0F, 4.5F, 0.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 199).mirrored().cuboid(-6.0F, 1.5F, 0.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 193).mirrored().cuboid(-6.0F, -1.5F, 0.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 187).mirrored().cuboid(-6.0F, -4.5F, 0.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(7, 177).mirrored().cuboid(-5.0F, -5.5F, -1.0F, 1.0F, 28.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(21, 146).mirrored().cuboid(-4.0F, -4.5F, -5.0F, 8.0F, 27.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-4.9357F, -0.0181F, -1.1278F, 0.1309F, 0.0F, 0.1745F));

		ModelPartData arm3_r2 = rightArm.addChild("arm3_r2", ModelPartBuilder.create().uv(7, 210).mirrored().cuboid(-4.0F, -10.5F, -6.0F, 1.0F, 25.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(21, 185).mirrored().cuboid(-3.0F, -13.5F, -10.0F, 8.0F, 27.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-10.4222F, 31.0976F, 4.0405F, -0.2182F, 0.0F, 0.1309F));

		ModelPartData arm2_r1 = rightArm.addChild("arm2_r1", ModelPartBuilder.create().uv(5, 125).mirrored().cuboid(-4.0F, -7.0F, -1.0F, 9.0F, 1.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(21, 122).mirrored().cuboid(-4.0F, -6.0F, -6.0F, 8.0F, 12.0F, 12.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-3.9357F, -0.5181F, -1.1278F, 0.0873F, 0.0F, 0.0F));

		ModelPartData rightLeg = partdefinition.addChild("rightLeg", ModelPartBuilder.create().uv(127, 224).mirrored().cuboid(-2.5F, -7.0F, -5.0F, 10.0F, 20.0F, 11.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 11.0F, 0.0F));

		ModelPartData leftLeg = partdefinition.addChild("leftLeg", ModelPartBuilder.create().uv(79, 224).cuboid(-6.5F, -7.0F, -5.0F, 10.0F, 20.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 11.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 256, 256);
		}

	@Override
	public void setAngles(ChainedGodRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.yawDegrees;
		float limbSwing = renderState.limbFrequency;
		float limbSwingAmount = renderState.limbAmplitudeMultiplier;

		this.head.yaw = netHeadYaw / 57.29578F;
		this.head.pitch = headPitch / 57.29578F;

		if (renderState.freelyMoving)
		{
			int i = renderState.attackTimer;
			if (i > 0)
			{
				this.rightArm.pitch = -2.0F + 1.5F * MathHelper.wrap((float)i, 10.0F) * 0.5f;
				this.leftArm.pitch = -2.0F + 1.5F * MathHelper.wrap((float)i, 10.0F) * 0.5f;
			}
			else
			{
				this.rightArm.pitch = (-0.2F + 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
				this.leftArm.pitch = (-0.2F - 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
			}
		}
		else
		{
			this.rightArm.pitch = - 2.2F;
			this.leftArm.pitch = - 2.2F;
		}

		this.leftLeg.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.yaw = 0.0F;
		this.rightLeg.yaw = 0.0F;
	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		chains.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}