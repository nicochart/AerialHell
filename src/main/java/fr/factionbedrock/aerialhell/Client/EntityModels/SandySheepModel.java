package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.SandySheepRenderState;
import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made by Cixon
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class SandySheepModel extends EntityModel<SandySheepRenderState>
{
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightFrontLegCoat;
	private final ModelPart rightBackLeg;
	private final ModelPart rightBackLegCoat;
	private final ModelPart leftFrontLeg;
	private final ModelPart leftFrontLegCoat;
	private final ModelPart leftBackLeg;
	private final ModelPart leftBackLegCoat;
	private final ModelPart headCoat;
	private final ModelPart bodyCoat;

	private boolean hasWool;
	private boolean isChild;

	public SandySheepModel(ModelPart root)
	{
		super(root);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.rightFrontLeg = root.getChild("rightFrontLeg");
		this.rightFrontLegCoat = root.getChild("rightFrontLegCoat");
		this.rightBackLeg = root.getChild("rightBackLeg");
		this.rightBackLegCoat = root.getChild("rightBackLegCoat");
		this.leftFrontLeg = root.getChild("leftFrontLeg");
		this.leftFrontLegCoat = root.getChild("leftFrontLegCoat");
		this.leftBackLeg = root.getChild("leftBackLeg");
		this.leftBackLegCoat = root.getChild("leftBackLegCoat");
		this.headCoat = root.getChild("headCoat");
		this.bodyCoat = root.getChild("bodyCoat");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.0F, -7.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -8.0F));

		PartDefinition snout_r1 = head.addOrReplaceChild("snout_r1", CubeListBuilder.create().texOffs(28, 8).addBox(-1.0F, -18.01F, -16.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 8.0F, 0.0698F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(80, 42).addBox(-3.5F, -17.0F, -8.5F, 8.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("rightFrontLeg", CubeListBuilder.create().texOffs(0, 49).addBox(-1.0F, -4.0F, -2.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 16.0F, -6.0F));

		PartDefinition rightFrontLegCoat = partdefinition.addOrReplaceChild("rightFrontLegCoat", CubeListBuilder.create().texOffs(12, 46).mirror().addBox(-3.0F, -5.0F, -5.0F, 7.0F, 10.0F, 8.0F, new CubeDeformation(-1.0F)).mirror(false), PartPose.offset(-3.0F, 16.0F, -6.0F));

		PartDefinition rightBackLeg = partdefinition.addOrReplaceChild("rightBackLeg", CubeListBuilder.create().texOffs(0, 49).addBox(-1.0F, -4.0F, -1.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 16.0F, 5.0F));

		PartDefinition rightBackLegCoat = partdefinition.addOrReplaceChild("rightBackLegCoat", CubeListBuilder.create().texOffs(12, 46).mirror().addBox(-3.0F, -5.0F, -3.5F, 7.0F, 10.0F, 8.0F, new CubeDeformation(-1.0F)).mirror(false), PartPose.offset(-3.0F, 16.0F, 5.0F));

		PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("leftFrontLeg", CubeListBuilder.create().texOffs(0, 49).mirror().addBox(-2.0F, -4.0F, -2.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 16.0F, -6.0F));

		PartDefinition leftFrontLegCoat = partdefinition.addOrReplaceChild("leftFrontLegCoat", CubeListBuilder.create().texOffs(12, 46).addBox(-4.0F, -5.0F, -5.0F, 7.0F, 10.0F, 8.0F, new CubeDeformation(-1.0F)), PartPose.offset(4.0F, 16.0F, -6.0F));

		PartDefinition leftBackLeg = partdefinition.addOrReplaceChild("leftBackLeg", CubeListBuilder.create().texOffs(0, 49).mirror().addBox(-2.0F, -4.0F, -1.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 16.0F, 5.0F));

		PartDefinition leftBackLegCoat = partdefinition.addOrReplaceChild("leftBackLegCoat", CubeListBuilder.create().texOffs(12, 46).addBox(-4.0F, -5.0F, -3.5F, 7.0F, 10.0F, 8.0F, new CubeDeformation(-1.0F)), PartPose.offset(4.0F, 16.0F, 5.0F));

		PartDefinition headCoat = partdefinition.addOrReplaceChild("headCoat", CubeListBuilder.create().texOffs(40, 0).addBox(-3.0F, -5.0F, -8.0F, 7.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -8.0F));

		PartDefinition bodyCoat = partdefinition.addOrReplaceChild("bodyCoat", CubeListBuilder.create().texOffs(0, 14).addBox(-4.5F, -18.0F, -11.0F, 10.0F, 10.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(SandySheepRenderState renderState)
	{
		float headPitch = renderState.xRot;
		float netHeadYaw = renderState.yRot;
		float limbSwing = renderState.walkAnimationPos;
		float limbSwingAmount = renderState.walkAnimationSpeed;

		this.hasWool = renderState.hasWool; this.isChild = renderState.isBaby;

		this.head.yRot = netHeadYaw / 57.29578F;
		this.head.xRot = headPitch / 57.29578F;
		this.headCoat.yRot = netHeadYaw / 57.29578F;
		this.headCoat.xRot = headPitch / 57.29578F;

		this.animateRightLeg(this.rightFrontLeg, limbSwing, limbSwingAmount);
		this.animateRightLeg(this.rightBackLeg, limbSwing, limbSwingAmount);
		this.animateRightLeg(this.rightFrontLegCoat, limbSwing, limbSwingAmount);
		this.animateRightLeg(this.rightBackLegCoat, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.leftFrontLeg, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.leftBackLeg, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.leftFrontLegCoat, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.leftBackLegCoat, limbSwing, limbSwingAmount);
	}

	private void animateLeftLeg(ModelPart model, float limbSwing, float limbSwingAmount)
	{
		model.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		model.yRot = 0.0F;
	}

	private void animateRightLeg(ModelPart model, float limbSwing, float limbSwingAmount)
	{
		model.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		model.yRot = 0.0F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		if(isChild)
		{
			poseStack.scale(0.5F, 0.5F, 0.5F);
			poseStack.translate(0.0F, 1.5F, 0.0F);
		}
		poseStack.pushPose();

		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightBackLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftBackLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		if (this.hasWool)
		{
			rightFrontLegCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			rightBackLegCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			leftFrontLegCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			leftBackLegCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			headCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			bodyCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		}
		poseStack.popPose();
	}
}