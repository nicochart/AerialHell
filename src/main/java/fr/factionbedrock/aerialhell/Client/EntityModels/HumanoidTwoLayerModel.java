package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.HumanoidTwoLayerRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings


public class HumanoidTwoLayerModel<T extends HumanoidTwoLayerRenderState> extends EntityModel<T> implements ModelWithArms
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

	public HumanoidTwoLayerModel(ModelPart root)
	{
		super(root);
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

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();
		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData head_overlay = partdefinition.addChild("head_overlay", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.3F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData body_overlay = partdefinition.addChild("body_overlay", ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.4F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData leftArm = partdefinition.addChild("leftArm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
		ModelPartData leftArm_overlay = partdefinition.addChild("leftArm_overlay", ModelPartBuilder.create().uv(40, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));
		ModelPartData rightArm = partdefinition.addChild("rightArm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
		ModelPartData rightArm_overlay = partdefinition.addChild("rightArm_overlay", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
		ModelPartData leftLeg = partdefinition.addChild("leftLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));
		ModelPartData leftLeg_overlay = partdefinition.addChild("leftLeg_overlay", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));
		ModelPartData rightLeg = partdefinition.addChild("rightLeg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
		ModelPartData rightLeg_overlay = partdefinition.addChild("rightLeg_overlay", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(T renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.yawDegrees;
		float limbSwing = renderState.limbFrequency;
		float limbSwingAmount = renderState.limbAmplitudeMultiplier;
		
		CrossbowPosing.meleeAttack(this.leftArm, this.rightArm, renderState.isAggressive, renderState.handSwingProgress, renderState.age);
		CrossbowPosing.meleeAttack(this.leftArm_overlay, this.rightArm_overlay, renderState.isAggressive, renderState.handSwingProgress, renderState.age);
		setupHeadAnim(this.head, netHeadYaw, headPitch);
		setupHeadAnim(this.head_overlay, netHeadYaw, headPitch);
		setupLegsAnim(this.leftLeg, this.rightLeg, limbSwing, limbSwingAmount);
		setupLegsAnim(this.leftLeg_overlay, this.rightLeg_overlay, limbSwing, limbSwingAmount);
	}

	private void setupHeadAnim(ModelPart part, float netHeadYaw, float headPitch)
	{
		part.yaw = netHeadYaw / 57.3F;
		part.pitch = headPitch / 57.3F;
	}

	private void setupLegsAnim(ModelPart left, ModelPart right, float limbSwing, float limbSwingAmount)
	{
		left.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		right.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		left.yaw = 0.0F;
		right.yaw = 0.0F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArm_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArm_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg_overlay.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}

	@Override public void setArmAngle(Arm arm, MatrixStack matrices)
	{
		this.getArm(arm).rotate(matrices);
	}

	protected ModelPart getArm(Arm arm) {return arm == Arm.LEFT ? this.rightArm : this.leftArm;}
}