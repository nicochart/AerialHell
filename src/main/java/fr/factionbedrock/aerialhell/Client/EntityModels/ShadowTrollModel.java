package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowTrollRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ShadowTrollModel extends EntityModel<ShadowTrollRenderState>
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
		super(root);
		this.isEyes = isEyes;
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		this.eyes = root.getChild("eyes");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -14.0F, 0.0F));

		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(4, 48).mirrored().cuboid(-4.0F, 0.4672F, -2.2607F, 8.0F, 5.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 37).mirrored().cuboid(-6.0F, -6.5328F, -2.2607F, 12.0F, 7.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 7.5328F, -0.7393F, 0.2182F, 0.0F, 0.0F));

		ModelPartData body_r2 = body.addChild("body_r2", ModelPartBuilder.create().uv(4, 29).mirrored().cuboid(-3.0F, -7.5328F, -4.2607F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 7.5328F, -0.7393F, 0.0873F, 0.0F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -13.0F, -3.0F));

		ModelPartData head_r1 = head.addChild("head_r1", ModelPartBuilder.create().uv(2, 17).mirrored().cuboid(-3.0F, -11.5328F, -7.2607F, 6.0F, 4.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(5, 0).mirrored().cuboid(-3.0F, -16.5328F, -5.2607F, 6.0F, 1.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
				.uv(1, 6).mirrored().cuboid(-4.0F, -15.5328F, -6.2607F, 8.0F, 4.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 6.5328F, 2.2607F, 0.0873F, 0.0F, 0.0F));

		ModelPartData head_r2 = head.addChild("head_r2", ModelPartBuilder.create().uv(40, 20).mirrored().cuboid(-3.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-4.5F, -10.6927F, -2.0176F, 0.0455F, 0.032F, -1.0475F));

		ModelPartData head_r3 = head.addChild("head_r3", ModelPartBuilder.create().uv(42, 16).mirrored().cuboid(-2.5F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-4.5F, -10.6927F, -2.0176F, -0.129F, 0.032F, -1.0475F));

		ModelPartData head_r4 = head.addChild("head_r4", ModelPartBuilder.create().uv(48, 20).mirrored().cuboid(1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.5F, -10.6927F, -2.0176F, 0.0437F, -0.0756F, 1.0455F));

		ModelPartData head_r5 = head.addChild("head_r5", ModelPartBuilder.create().uv(50, 16).mirrored().cuboid(1.5F, -4.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.5F, -10.6927F, -2.0176F, -0.1308F, -0.0756F, 1.0455F));

		ModelPartData rightArm = partdefinition.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.of(6.0F, -10.86F, -1.7731F, 0.0F, -0.2618F, -0.1745F));

		ModelPartData rightArm_r1 = rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(32, 31).mirrored().cuboid(6.0F, -6.2581F, 0.8184F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, 5.3928F, -0.9662F, 0.2182F, 0.0F, 0.0F));

		ModelPartData rightArm_r2 = rightArm.addChild("rightArm_r2", ModelPartBuilder.create().uv(32, 47).mirrored().cuboid(6.0F, 4.7521F, 4.6925F, 2.0F, 15.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, 5.3928F, -0.9662F, -0.3927F, 0.0F, 0.0F));

		ModelPartData leftArm = partdefinition.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.of(-6.0F, -11.86F, -1.7731F, 0.0579F, 0.2555F, 0.2256F));

		ModelPartData leftArm_r1 = leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(40, 31).cuboid(-1.0F, -13.8941F, 1.5216F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 14.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		ModelPartData leftArm_r2 = leftArm.addChild("leftArm_r2", ModelPartBuilder.create().uv(40, 47).cuboid(-1.0F, -1.9063F, 0.8888F, 2.0F, 15.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 14.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		ModelPartData rightLeg = partdefinition.addChild("rightLeg", ModelPartBuilder.create(), ModelTransform.of(2.0F, -2.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		ModelPartData rightLeg_r1 = rightLeg.addChild("rightLeg_r1", ModelPartBuilder.create().uv(56, 48).mirrored().cuboid(-1.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 26.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		ModelPartData rightLeg_r2 = rightLeg.addChild("rightLeg_r2", ModelPartBuilder.create().uv(56, 32).mirrored().cuboid(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 1.5F, 0.0F, -0.2618F, 0.0F, 0.0F));

		ModelPartData leftLeg = partdefinition.addChild("leftLeg", ModelPartBuilder.create(), ModelTransform.of(-2.0F, -2.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		ModelPartData leftLeg_r1 = leftLeg.addChild("leftLeg_r1", ModelPartBuilder.create().uv(48, 32).cuboid(-1.0F, -1.5F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.5F, 0.0F, -0.2618F, 0.0F, 0.0F));

		ModelPartData leftLeg_r2 = leftLeg.addChild("leftLeg_r2", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 26.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

		ModelPartData eyes = partdefinition.addChild("eyes", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -13.0F, -3.0F));

		ModelPartData eyeLeft_r1 = eyes.addChild("eyeLeft_r1", ModelPartBuilder.create().uv(32, 3).mirrored().cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.0F, -5.9048F, -5.1121F, 0.0873F, 0.0F, -0.1745F));

		ModelPartData eyeRight_r1 = eyes.addChild("eyeRight_r1", ModelPartBuilder.create().uv(32, 3).mirrored().cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, -5.9048F, -5.1121F, 0.0873F, 0.0F, 0.1745F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override
	public void setAngles(ShadowTrollRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		this.head.yaw = netHeadYaw / 57.3F;
		this.head.pitch = headPitch / 57.3F;
		this.eyes.yaw = netHeadYaw / 57.3F;
		this.eyes.pitch = headPitch / 57.3F;

		if (!renderState.isDisappearing)
		{
			this.rightArm.pitch = (-0.2F + 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
			this.leftArm.pitch = (-0.2F - 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		}
		else
		{
			this.rightArm.pitch = - 2.2F;
			this.leftArm.pitch = - 2.2F;
		}
		//ajouter mouvement des bras quand il tape ?

		this.leftLeg.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		//this.leftLeg.yaw = 0.0F;
		//this.rightLeg.yaw = 0.0F;
	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		if (isEyes)
		{
			eyes.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		}
		else
		{
			body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		}
	}
}