package fr.factionbedrock.aerialhell.Client.EntityModels;

// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17 or later with Mojang mappings

import fr.factionbedrock.aerialhell.Client.EntityRender.State.EntRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ArmPosing;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

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

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 18).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 1.0F, -1.0F));

		ModelPartData headLeaves = partdefinition.addChild("headLeaves", ModelPartBuilder.create().uv(36, 0).cuboid(-4.0F, -6.0F, -3.0F, 8.0F, 7.0F, 6.0F, new Dilation(0.0F))
		.uv(36, 13).cuboid(-3.0F, -6.0F, -4.0F, 6.0F, 7.0F, 8.0F, new Dilation(0.0F))
		.uv(36, 45).cuboid(-3.0F, -8.0F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 1.0F, -1.0F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(0, 30).cuboid(-4.0F, -12.5F, -2.0F, 8.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData bodyLeaves = partdefinition.addChild("bodyLeaves", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData bodyLeaves_r1 = bodyLeaves.addChild("bodyLeaves_r1", ModelPartBuilder.create().uv(32, 45).cuboid(-5.0F, -12.5F, -3.0F, 10.0F, 13.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData leftArm = partdefinition.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.origin(-4.0F, 1.0F, -1.0F));

		ModelPartData leftArm_r1 = leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(0, 8).cuboid(-3.0F, -1.3206F, -1.5386F, 2.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 6.5F, 1.0F, -0.0007F, -0.0114F, 0.1304F));

		ModelPartData leftArm_r2 = leftArm.addChild("leftArm_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -0.5457F, -3.0459F, 2.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, 1.0F, 0.2174F, -0.0114F, 0.1304F));

		ModelPartData leftArmLeaves = partdefinition.addChild("leftArmLeaves", ModelPartBuilder.create(), ModelTransform.origin(-4.0F, 1.0F, -1.0F));

		ModelPartData leftArmLeaves_r1 = leftArmLeaves.addChild("leftArmLeaves_r1", ModelPartBuilder.create().uv(33, 45).cuboid(-4.0F, 0.4543F, -3.5F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, 1.0F, 0.2174F, -0.0114F, 0.1304F));

		ModelPartData rightArm = partdefinition.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.origin(4.0F, 1.0F, -1.0F));

		ModelPartData rightArm_r1 = rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(13, 9).cuboid(1.15F, -0.2747F, -1.5446F, 2.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 6.5F, 1.0F, -0.0426F, 0.0209F, -0.0431F));

		ModelPartData rightArm_r2 = rightArm.addChild("rightArm_r2", ModelPartBuilder.create().uv(13, 0).cuboid(0.0F, -0.5457F, -3.0459F, 2.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, 1.0F, 0.2168F, 0.0151F, -0.1739F));

		ModelPartData rightArmLeaves = partdefinition.addChild("rightArmLeaves", ModelPartBuilder.create(), ModelTransform.origin(4.0F, 1.0F, -1.0F));

		ModelPartData rightArmLeaves_r1 = rightArmLeaves.addChild("rightArmLeaves_r1", ModelPartBuilder.create().uv(33, 45).cuboid(-2.0F, 1.4543F, -4.0F, 5.0F, 7.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, 1.0F, 0.2168F, 0.0151F, -0.1739F));

		ModelPartData leftLeg = partdefinition.addChild("leftLeg", ModelPartBuilder.create(), ModelTransform.of(-2.35F, 13.3306F, 0.0109F, 0.0873F, 0.0F, 0.0F));

		ModelPartData leftLeg_r1 = leftLeg.addChild("leftLeg_r1", ModelPartBuilder.create().uv(0, 54).cuboid(-1.9F, -7.1856F, -1.1296F, 3.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.45F, 5.6504F, -1.4467F, -0.2182F, 0.0F, 0.0F));

		ModelPartData leftLeg_r2 = leftLeg.addChild("leftLeg_r2", ModelPartBuilder.create().uv(0, 47).cuboid(-2.0F, -6.5F, -2.0F, 3.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.45F, 10.6504F, 0.5533F, 0.2182F, 0.0F, 0.0F));

		ModelPartData leftLegLeaves = partdefinition.addChild("leftLegLeaves", ModelPartBuilder.create(), ModelTransform.of(-2.35F, 13.3306F, 0.0109F, 0.0873F, 0.0F, 0.0F));

		ModelPartData leftLegLeaves_r1 = leftLegLeaves.addChild("leftLegLeaves_r1", ModelPartBuilder.create().uv(29, 29).cuboid(-2.9F, -7.1856F, -2.1296F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.45F, 5.6504F, -1.4467F, -0.1309F, 0.0F, 0.0F));

		ModelPartData rightLeg = partdefinition.addChild("rightLeg", ModelPartBuilder.create(), ModelTransform.of(2.45F, 13.3306F, 0.0109F, 0.0873F, 0.0F, 0.0F));

		ModelPartData rightLeg_r1 = rightLeg.addChild("rightLeg_r1", ModelPartBuilder.create().uv(12, 54).cuboid(-2.2F, -7.1856F, -1.1296F, 3.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.65F, 5.5632F, -1.4429F, -0.2182F, 0.0F, 0.0F));

		ModelPartData rightLeg_r2 = rightLeg.addChild("rightLeg_r2", ModelPartBuilder.create().uv(12, 47).cuboid(-2.1F, -6.5F, -2.0F, 3.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.65F, 10.5632F, 0.5571F, 0.2182F, 0.0F, 0.0F));

		ModelPartData rightLegLeaves = partdefinition.addChild("rightLegLeaves", ModelPartBuilder.create(), ModelTransform.of(2.45F, 13.3306F, 0.0109F, 0.0873F, 0.0F, 0.0F));

		ModelPartData leftLegLeaves_r2 = rightLegLeaves.addChild("leftLegLeaves_r2", ModelPartBuilder.create().uv(39, 29).cuboid(2.1F, -7.1856F, -2.1296F, 5.0F, 9.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-4.35F, 5.6504F, -1.4467F, -0.1309F, 0.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(S renderState)
	{
		biomeColor = renderState.color;
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		ArmPosing.zombieArms(this.leftArm, this.rightArm, renderState.isAggressive, renderState);
		ArmPosing.zombieArms(this.leftArmLeaves, this.rightArm, renderState.isAggressive, renderState);
		setupHeadAnim(this.head, netHeadYaw, headPitch);
		setupHeadAnim(this.headLeaves, netHeadYaw, headPitch);
		setupLegsAnim(this.leftLeg, this.rightLeg, limbSwing, limbSwingAmount);
		setupLegsAnim(this.leftLegLeaves, this.rightLegLeaves, limbSwing, limbSwingAmount);
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