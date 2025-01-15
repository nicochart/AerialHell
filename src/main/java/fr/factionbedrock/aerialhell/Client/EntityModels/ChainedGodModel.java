package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ChainedGodRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

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

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(65, 130).mirror().addBox(-21.0F, -32.0F, -8.0F, 42.0F, 21.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(72, 168).mirror().addBox(-18.0F, -11.0F, -7.0F, 36.0F, 13.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 106).mirror().addBox(-7.0F, -11.0F, 8.94F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 111).mirror().addBox(-7.0F, -6.0F, 8.9F, 14.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(1, 89).mirror().addBox(-15.0F, -9.0F, 9.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(33, 89).mirror().addBox(11.0F, -9.0F, 9.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(17, 71).mirror().addBox(-2.0F, -32.0F, 9.0F, 4.0F, 33.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(199, 106).mirror().addBox(-10.0F, -7.0F, -9.0F, 9.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(199, 95).mirror().addBox(-10.0F, -14.0F, -9.0F, 9.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(192, 80).mirror().addBox(-14.0F, -25.0F, -10.0F, 12.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(50, 109).mirror().addBox(-20.0F, -34.0F, -9.0F, 10.0F, 6.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(102, 116).mirror().addBox(-5.0F, -34.0F, -7.0F, 10.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(14, 41).mirror().addBox(-2.0F, -34.0F, 5.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(148, 109).mirror().addBox(10.0F, -34.0F, -9.0F, 10.0F, 6.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(224, 80).mirror().addBox(2.0F, -25.0F, -10.0F, 12.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(223, 106).mirror().addBox(1.0F, -7.0F, -9.0F, 9.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(223, 95).mirror().addBox(1.0F, -14.0F, -9.0F, 9.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(83, 197).mirror().addBox(-14.5F, 2.0F, -5.0F, 29.0F, 10.0F, 12.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition back_r1 = body.addOrReplaceChild("back_r1", CubeListBuilder.create().texOffs(0, 66).mirror().addBox(-14.5F, -1.5F, -1.1F, 21.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(3, 56).mirror().addBox(-9.5F, -6.5F, -1.1F, 18.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.5F, -24.5F, 10.0F, 0.0F, 0.0F, -0.829F));

		PartDefinition back_r2 = body.addOrReplaceChild("back_r2", CubeListBuilder.create().texOffs(0, 61).mirror().addBox(-6.5F, -1.5F, -1.1F, 21.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(3, 51).mirror().addBox(-8.5F, -6.5F, -1.1F, 18.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.5F, -24.5F, 10.0F, 0.0F, 0.0F, 0.829F));

		PartDefinition chains = partdefinition.addOrReplaceChild("chains", CubeListBuilder.create().texOffs(7, 139).mirror().addBox(-22.0F, -42.0F, 9.0F, 4.0F, 34.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(11, 139).mirror().addBox(-19.0F, -42.0F, -8.0F, 4.0F, 34.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(233, 146).mirror().addBox(19.0F, -42.0F, -8.0F, 4.0F, 26.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(96, 75).mirror().addBox(-8.0F, -23.0F, -5.5F, 16.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(84, 12).mirror().addBox(-7.0F, -22.0F, -5.5F, 14.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(130, 21).mirror().addBox(-7.0F, -22.0F, -1.5F, 14.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(100, 99).mirror().addBox(-6.0F, -11.0F, -5.5F, 12.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(20, 21).mirror().addBox(-1.0F, -22.0F, 6.5F, 2.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(16, 15).mirror().addBox(-3.0F, -12.0F, 6.4F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(18, 18).mirror().addBox(-2.0F, -9.0F, 6.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(12, 12).mirror().addBox(-5.0F, -15.0F, 6.4F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(10, 9).mirror().addBox(-6.0F, -18.0F, 6.4F, 12.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 6).mirror().addBox(-7.0F, -21.0F, 6.4F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(64, 99).mirror().addBox(-4.0F, -11.0F, -8.5F, 8.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 81).mirror().addBox(-8.0F, -23.0F, -8.5F, 16.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(60, 93).mirror().addBox(-6.0F, -14.0F, -8.5F, 12.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -35.0F, -2.0F));

		PartDefinition rightHorn_r1 = head.addOrReplaceChild("rightHorn_r1", CubeListBuilder.create().texOffs(111, 51).mirror().addBox(-10.9384F, -9.1522F, -1.5F, 3.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -28.6543F, -1.0F, -0.1897F, -0.1084F, -0.5133F));

		PartDefinition rightHorn_r2 = head.addOrReplaceChild("rightHorn_r2", CubeListBuilder.create().texOffs(113, 43).mirror().addBox(-13.2496F, -2.4227F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -28.6543F, -1.0F, -0.202F, 0.0829F, 0.3843F));

		PartDefinition leftHorn_r1 = head.addOrReplaceChild("leftHorn_r1", CubeListBuilder.create().texOffs(127, 43).mirror().addBox(11.2496F, -2.4227F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -28.6543F, -1.0F, -0.202F, -0.0829F, -0.3843F));

		PartDefinition leftHorn_r2 = head.addOrReplaceChild("leftHorn_r2", CubeListBuilder.create().texOffs(125, 51).mirror().addBox(7.9384F, -9.1522F, -1.5F, 3.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -28.6543F, -1.0F, -0.1897F, 0.1084F, 0.5133F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(21.1131F, -31.9811F, 2.1442F));

		PartDefinition arm3_r1 = leftArm.addOrReplaceChild("arm3_r1", CubeListBuilder.create().texOffs(231, 210).mirror().addBox(3.0F, -10.5F, -6.0F, 1.0F, 25.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(187, 185).mirror().addBox(-5.0F, -13.5F, -10.0F, 8.0F, 27.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.3735F, 31.0968F, 4.0241F, -0.2133F, -0.0594F, -0.1206F));

		PartDefinition arm6_r1 = leftArm.addOrReplaceChild("arm6_r1", CubeListBuilder.create().texOffs(242, 205).mirror().addBox(5.0F, 4.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(242, 199).mirror().addBox(5.0F, 1.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(242, 193).mirror().addBox(5.0F, -1.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(242, 187).mirror().addBox(5.0F, -4.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(231, 177).mirror().addBox(4.0F, -5.5F, -1.0F, 1.0F, 28.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(187, 146).mirror().addBox(-4.0F, -4.5F, -5.0F, 8.0F, 27.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.8869F, -0.0189F, -1.1442F, 0.1309F, 0.0F, -0.1745F));

		PartDefinition arm1_r1 = leftArm.addOrReplaceChild("arm1_r1", CubeListBuilder.create().texOffs(216, 125).mirror().addBox(-5.0F, -7.0F, -1.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(187, 122).mirror().addBox(-4.0F, -6.0F, -6.0F, 8.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.8869F, -0.5189F, -1.1442F, 0.0873F, 0.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(-21.0643F, -31.9819F, 2.1278F));

		PartDefinition arm8_r1 = rightArm.addOrReplaceChild("arm8_r1", CubeListBuilder.create().texOffs(0, 205).mirror().addBox(-6.0F, 4.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 199).mirror().addBox(-6.0F, 1.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 193).mirror().addBox(-6.0F, -1.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 187).mirror().addBox(-6.0F, -4.5F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 177).mirror().addBox(-5.0F, -5.5F, -1.0F, 1.0F, 28.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 146).mirror().addBox(-4.0F, -4.5F, -5.0F, 8.0F, 27.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.9357F, -0.0181F, -1.1278F, 0.1309F, 0.0F, 0.1745F));

		PartDefinition arm3_r2 = rightArm.addOrReplaceChild("arm3_r2", CubeListBuilder.create().texOffs(7, 210).mirror().addBox(-4.0F, -10.5F, -6.0F, 1.0F, 25.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 185).mirror().addBox(-3.0F, -13.5F, -10.0F, 8.0F, 27.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.4222F, 31.0976F, 4.0405F, -0.2182F, 0.0F, 0.1309F));

		PartDefinition arm2_r1 = rightArm.addOrReplaceChild("arm2_r1", CubeListBuilder.create().texOffs(5, 125).mirror().addBox(-4.0F, -7.0F, -1.0F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 122).mirror().addBox(-4.0F, -6.0F, -6.0F, 8.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.9357F, -0.5181F, -1.1278F, 0.0873F, 0.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(127, 224).mirror().addBox(-2.5F, -7.0F, -5.0F, 10.0F, 20.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 11.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(79, 224).addBox(-6.5F, -7.0F, -5.0F, 10.0F, 20.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 11.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
		}

	@Override
	public void setupAnim(ChainedGodRenderState renderState)
	{
		float headPitch = renderState.xRot;
		float netHeadYaw = renderState.yRot;
		float limbSwing = renderState.walkAnimationPos;
		float limbSwingAmount = renderState.walkAnimationSpeed;

		this.head.yRot = netHeadYaw / 57.29578F;
		this.head.xRot = headPitch / 57.29578F;

		if (renderState.freelyMoving)
		{
			int i = renderState.attackTimer;
			if (i > 0)
			{
				this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i, 10.0F) * 0.5f;
				this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i, 10.0F) * 0.5f;
			}
			else
			{
				this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
				this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
			}
		}
		else
		{
			this.rightArm.xRot = - 2.2F;
			this.leftArm.xRot = - 2.2F;
		}

		this.leftLeg.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.yRot = 0.0F;
		this.rightLeg.yRot = 0.0F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
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