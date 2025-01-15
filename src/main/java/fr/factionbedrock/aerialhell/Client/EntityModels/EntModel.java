package fr.factionbedrock.aerialhell.Client.EntityModels;

// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17 or later with Mojang mappings

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.EntRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.EntEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.util.Mth;

import java.awt.*;

public class EntModel<S extends EntRenderState> extends EntityModel<S>
{
	int biomeColor;
	private final ModelPart head;
	private final ModelPart headLeaves;
	private final ModelPart body;
	private final ModelPart bodyLeaves;
	private final ModelPart leftArm;
	private final ModelPart leftArmLeaves;
	private final ModelPart rightArm;
	private final ModelPart rightArmLeaves;
	private final ModelPart leftLeg;
	private final ModelPart leftLegLeaves;
	private final ModelPart rightLeg;
	private final ModelPart rightLegLeaves;

	public EntModel(ModelPart root)
	{
		super(root);
		this.head = root.getChild("head");
		this.headLeaves = root.getChild("headLeaves");
		this.body = root.getChild("body");
		this.bodyLeaves = root.getChild("bodyLeaves");
		this.leftArm = root.getChild("leftArm");
		this.leftArmLeaves = root.getChild("leftArmLeaves");
		this.rightArm = root.getChild("rightArm");
		this.rightArmLeaves = root.getChild("rightArmLeaves");
		this.leftLeg = root.getChild("leftLeg");
		this.leftLegLeaves = root.getChild("leftLegLeaves");
		this.rightLeg = root.getChild("rightLeg");
		this.rightLegLeaves = root.getChild("rightLegLeaves");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition headLeaves = partdefinition.addOrReplaceChild("headLeaves", CubeListBuilder.create().texOffs(36, 0).addBox(-4.0F, -6.0F, -3.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(36, 13).addBox(-3.0F, -6.0F, -4.0F, 6.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(36, 45).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 30).addBox(-4.0F, -12.5F, -2.0F, 8.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition bodyLeaves = partdefinition.addOrReplaceChild("bodyLeaves", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bodyLeaves_r1 = bodyLeaves.addOrReplaceChild("bodyLeaves_r1", CubeListBuilder.create().texOffs(32, 45).addBox(-5.0F, -12.5F, -3.0F, 10.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(-4.0F, 1.0F, -1.0F));

		PartDefinition leftArm_r1 = leftArm.addOrReplaceChild("leftArm_r1", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -1.3206F, -1.5386F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.5F, 1.0F, -0.0007F, -0.0114F, 0.1304F));

		PartDefinition leftArm_r2 = leftArm.addOrReplaceChild("leftArm_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -0.5457F, -3.0459F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 1.0F, 0.2174F, -0.0114F, 0.1304F));

		PartDefinition leftArmLeaves = partdefinition.addOrReplaceChild("leftArmLeaves", CubeListBuilder.create(), PartPose.offset(-4.0F, 1.0F, -1.0F));

		PartDefinition leftArmLeaves_r1 = leftArmLeaves.addOrReplaceChild("leftArmLeaves_r1", CubeListBuilder.create().texOffs(33, 45).addBox(-4.0F, 0.4543F, -3.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 1.0F, 0.2174F, -0.0114F, 0.1304F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(4.0F, 1.0F, -1.0F));

		PartDefinition rightArm_r1 = rightArm.addOrReplaceChild("rightArm_r1", CubeListBuilder.create().texOffs(13, 9).addBox(1.15F, -0.2747F, -1.5446F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.5F, 1.0F, -0.0426F, 0.0209F, -0.0431F));

		PartDefinition rightArm_r2 = rightArm.addOrReplaceChild("rightArm_r2", CubeListBuilder.create().texOffs(13, 0).addBox(0.0F, -0.5457F, -3.0459F, 2.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 1.0F, 0.2168F, 0.0151F, -0.1739F));

		PartDefinition rightArmLeaves = partdefinition.addOrReplaceChild("rightArmLeaves", CubeListBuilder.create(), PartPose.offset(4.0F, 1.0F, -1.0F));

		PartDefinition rightArmLeaves_r1 = rightArmLeaves.addOrReplaceChild("rightArmLeaves_r1", CubeListBuilder.create().texOffs(33, 45).addBox(-2.0F, 1.4543F, -4.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 1.0F, 0.2168F, 0.0151F, -0.1739F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.35F, 13.3306F, 0.0109F, 0.0873F, 0.0F, 0.0F));

		PartDefinition leftLeg_r1 = leftLeg.addOrReplaceChild("leftLeg_r1", CubeListBuilder.create().texOffs(0, 54).addBox(-1.9F, -7.1856F, -1.1296F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.45F, 5.6504F, -1.4467F, -0.2182F, 0.0F, 0.0F));

		PartDefinition leftLeg_r2 = leftLeg.addOrReplaceChild("leftLeg_r2", CubeListBuilder.create().texOffs(0, 47).addBox(-2.0F, -6.5F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.45F, 10.6504F, 0.5533F, 0.2182F, 0.0F, 0.0F));

		PartDefinition leftLegLeaves = partdefinition.addOrReplaceChild("leftLegLeaves", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.35F, 13.3306F, 0.0109F, 0.0873F, 0.0F, 0.0F));

		PartDefinition leftLegLeaves_r1 = leftLegLeaves.addOrReplaceChild("leftLegLeaves_r1", CubeListBuilder.create().texOffs(29, 29).addBox(-2.9F, -7.1856F, -2.1296F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.45F, 5.6504F, -1.4467F, -0.1309F, 0.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offsetAndRotation(2.45F, 13.3306F, 0.0109F, 0.0873F, 0.0F, 0.0F));

		PartDefinition rightLeg_r1 = rightLeg.addOrReplaceChild("rightLeg_r1", CubeListBuilder.create().texOffs(12, 54).addBox(-2.2F, -7.1856F, -1.1296F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.65F, 5.5632F, -1.4429F, -0.2182F, 0.0F, 0.0F));

		PartDefinition rightLeg_r2 = rightLeg.addOrReplaceChild("rightLeg_r2", CubeListBuilder.create().texOffs(12, 47).addBox(-2.1F, -6.5F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.65F, 10.5632F, 0.5571F, 0.2182F, 0.0F, 0.0F));

		PartDefinition rightLegLeaves = partdefinition.addOrReplaceChild("rightLegLeaves", CubeListBuilder.create(), PartPose.offsetAndRotation(2.45F, 13.3306F, 0.0109F, 0.0873F, 0.0F, 0.0F));

		PartDefinition leftLegLeaves_r2 = rightLegLeaves.addOrReplaceChild("leftLegLeaves_r2", CubeListBuilder.create().texOffs(39, 29).addBox(2.1F, -7.1856F, -2.1296F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.35F, 5.6504F, -1.4467F, -0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(S renderState)
	{
		biomeColor = renderState.color;
		float headPitch = renderState.xRot;
		float netHeadYaw = renderState.yRot;
		float limbSwing = renderState.walkAnimationPos;
		float limbSwingAmount = renderState.walkAnimationSpeed;
		float ageInTicks = renderState.ageInTicks;

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, renderState.isAggressive, renderState.attackTime, ageInTicks);
		AnimationUtils.animateZombieArms(this.leftArmLeaves, this.rightArmLeaves, renderState.isAggressive, renderState.attackTime, ageInTicks);
		setupHeadAnim(this.head, netHeadYaw, headPitch);
		setupHeadAnim(this.headLeaves, netHeadYaw, headPitch);
		setupLegsAnim(this.leftLeg, this.rightLeg, limbSwing, limbSwingAmount);
		setupLegsAnim(this.leftLegLeaves, this.rightLegLeaves, limbSwing, limbSwingAmount);
	}

	private void setupHeadAnim(ModelPart part, float netHeadYaw, float headPitch)
	{
		part.yRot = netHeadYaw / 57.3F;
		part.xRot = headPitch / 57.3F;
	}

	private void setupLegsAnim(ModelPart left, ModelPart right, float limbSwing, float limbSwingAmount)
	{
		left.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		right.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		left.yRot = 0.0F;
		right.yRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		headLeaves.render(poseStack, vertexConsumer, packedLight, packedOverlay, biomeColor);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		bodyLeaves.render(poseStack, vertexConsumer, packedLight, packedOverlay, biomeColor);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArmLeaves.render(poseStack, vertexConsumer, packedLight, packedOverlay, biomeColor);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArmLeaves.render(poseStack, vertexConsumer, packedLight, packedOverlay, biomeColor);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLegLeaves.render(poseStack, vertexConsumer, packedLight, packedOverlay, biomeColor);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLegLeaves.render(poseStack, vertexConsumer, packedLight, packedOverlay, biomeColor);
	}
}