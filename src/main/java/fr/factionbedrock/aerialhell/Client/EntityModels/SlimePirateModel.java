package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.Monster.SlimePirateEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings


public class SlimePirateModel<T extends SlimePirateEntity> extends EntityModel<T> implements ArmedModel
{
	private final ModelPart head;
	private final ModelPart head_overlay;
	private final ModelPart body;
	private final ModelPart body_overlay;
	private final ModelPart leftArm;
	private final ModelPart leftArm_overlay;
	private final ModelPart rightArm;
	private final ModelPart rightArm_overlay;
	private final ModelPart leftLeg;
	private final ModelPart leftLeg_overlay;
	private final ModelPart rightLeg;
	private final ModelPart rightLeg_overlay;

	public SlimePirateModel(ModelPart root)
	{
		this.head = root.getChild("head");
		this.head_overlay = root.getChild("head_overlay");
		this.body = root.getChild("body");
		this.body_overlay = root.getChild("body_overlay");
		this.leftArm = root.getChild("leftArm");
		this.leftArm_overlay = root.getChild("leftArm_overlay");
		this.rightArm = root.getChild("rightArm");
		this.rightArm_overlay = root.getChild("rightArm_overlay");
		this.leftLeg = root.getChild("leftLeg");
		this.leftLeg_overlay = root.getChild("leftLeg_overlay");
		this.rightLeg = root.getChild("rightLeg");
		this.rightLeg_overlay = root.getChild("rightLeg_overlay");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head_overlay = partdefinition.addOrReplaceChild("head_overlay", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body_overlay = partdefinition.addOrReplaceChild("body_overlay", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition leftArm_overlay = partdefinition.addOrReplaceChild("leftArm_overlay", CubeListBuilder.create().texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition rightArm_overlay = partdefinition.addOrReplaceChild("rightArm_overlay", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition leftLeg_overlay = partdefinition.addOrReplaceChild("leftLeg_overlay", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition rightLeg_overlay = partdefinition.addOrReplaceChild("rightLeg_overlay", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, entity.isAggressive(), this.attackTime, ageInTicks);
		AnimationUtils.animateZombieArms(this.leftArm_overlay, this.rightArm_overlay, entity.isAggressive(), this.attackTime, ageInTicks);
		setupHeadAnim(this.head, netHeadYaw, headPitch);
		setupHeadAnim(this.head_overlay, netHeadYaw, headPitch);
		setupLegsAnim(this.leftLeg, this.rightLeg, limbSwing, limbSwingAmount);
		setupLegsAnim(this.leftLeg_overlay, this.rightLeg_overlay, limbSwing, limbSwingAmount);
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

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override public void translateToHand(HumanoidArm arm, PoseStack stack)
	{
		this.getArm(arm).translateAndRotate(stack);
	}

	protected ModelPart getArm(HumanoidArm arm) {return arm == HumanoidArm.LEFT ? this.rightArm : this.leftArm;}
}