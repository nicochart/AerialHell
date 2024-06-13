package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ShadowTrollModel extends EntityModel<ShadowTrollEntity>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart eyes;

	private boolean isEyes;

	public ShadowTrollModel(ModelPart root, boolean isEyes)
	{
		this.isEyes = isEyes;
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		this.eyes = root.getChild("eyes");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(4, 48).mirror().addBox(-4.0F, 0.4672F, -2.2607F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 37).mirror().addBox(-6.0F, -6.5328F, -2.2607F, 12.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.5328F, -0.7393F, 0.2182F, 0.0F, 0.0F));

		PartDefinition body_r2 = body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(4, 29).mirror().addBox(-3.0F, -7.5328F, -4.2607F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 7.5328F, -0.7393F, 0.0873F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, -3.0F));

		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(2, 17).mirror().addBox(-3.0F, -11.5328F, -7.2607F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(5, 0).mirror().addBox(-3.0F, -16.5328F, -5.2607F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(1, 6).mirror().addBox(-4.0F, -15.5328F, -6.2607F, 8.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 6.5328F, 2.2607F, 0.0873F, 0.0F, 0.0F));

		PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(40, 20).mirror().addBox(-3.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, -10.6927F, -2.0176F, 0.0455F, 0.032F, -1.0475F));

		PartDefinition head_r3 = head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(42, 16).mirror().addBox(-2.5F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, -10.6927F, -2.0176F, -0.129F, 0.032F, -1.0475F));

		PartDefinition head_r4 = head.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(48, 20).mirror().addBox(1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.5F, -10.6927F, -2.0176F, 0.0437F, -0.0756F, 1.0455F));

		PartDefinition head_r5 = head.addOrReplaceChild("head_r5", CubeListBuilder.create().texOffs(50, 16).mirror().addBox(1.5F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.5F, -10.6927F, -2.0176F, -0.1308F, -0.0756F, 1.0455F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -10.86F, -1.7731F, 0.0F, -0.2618F, -0.1745F));

		PartDefinition rightArm_r1 = rightArm.addOrReplaceChild("rightArm_r1", CubeListBuilder.create().texOffs(32, 31).mirror().addBox(6.0F, -6.2581F, 0.8184F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, 5.3928F, -0.9662F, 0.2182F, 0.0F, 0.0F));

		PartDefinition rightArm_r2 = rightArm.addOrReplaceChild("rightArm_r2", CubeListBuilder.create().texOffs(32, 47).mirror().addBox(6.0F, 4.7521F, 4.6925F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, 5.3928F, -0.9662F, -0.3927F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, -11.86F, -1.7731F, 0.0579F, 0.2555F, 0.2256F));

		PartDefinition leftArm_r1 = leftArm.addOrReplaceChild("leftArm_r1", CubeListBuilder.create().texOffs(40, 31).addBox(-1.0F, -13.8941F, 1.5216F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition leftArm_r2 = leftArm.addOrReplaceChild("leftArm_r2", CubeListBuilder.create().texOffs(40, 47).addBox(-1.0F, -1.9063F, 0.8888F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, -2.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition rightLeg_r1 = rightLeg.addOrReplaceChild("rightLeg_r1", CubeListBuilder.create().texOffs(56, 48).mirror().addBox(-1.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 26.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition rightLeg_r2 = rightLeg.addOrReplaceChild("rightLeg_r2", CubeListBuilder.create().texOffs(56, 32).mirror().addBox(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.5F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -2.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition leftLeg_r1 = leftLeg.addOrReplaceChild("leftLeg_r1", CubeListBuilder.create().texOffs(48, 32).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition leftLeg_r2 = leftLeg.addOrReplaceChild("leftLeg_r2", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 26.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition eyes = partdefinition.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, -3.0F));

		PartDefinition eyeLeft_r1 = eyes.addOrReplaceChild("eyeLeft_r1", CubeListBuilder.create().texOffs(32, 3).mirror().addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, -5.9048F, -5.1121F, 0.0873F, 0.0F, -0.1745F));

		PartDefinition eyeRight_r1 = eyes.addOrReplaceChild("eyeRight_r1", CubeListBuilder.create().texOffs(32, 3).mirror().addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -5.9048F, -5.1121F, 0.0873F, 0.0F, 0.1745F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(ShadowTrollEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / 57.3F;
		this.head.xRot = headPitch / 57.3F;
		this.eyes.yRot = netHeadYaw / 57.3F;
		this.eyes.xRot = headPitch / 57.3F;

		if (!entity.isDisappearing())
		{
			this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
			this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		}
		else
		{
			this.rightArm.xRot = - 2.2F;
			this.leftArm.xRot = - 2.2F;
		}
		//ajouter mouvement des bras quand il tape ?

		this.leftLeg.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		//this.leftLeg.yRot = 0.0F;
		//this.rightLeg.yRot = 0.0F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		if (isEyes)
		{
			eyes.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		else
		{
			body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
			head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
			rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
			leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
			rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
			leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
	}
}