package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.VerdigrisZombieRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class VerdigrisZombieModel extends EntityModel<VerdigrisZombieRenderState>//HumanoidModel<VerdigrisZombieEntity>
{
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public VerdigrisZombieModel(ModelPart root)
	{
		super(root);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData shroomfall_r1 = head.addChild("shroomfall_r1", ModelPartBuilder.create().uv(12, 0).mirrored().cuboid(-4.0F, -31.0F, 3.0F, 8.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(32, 0).mirrored().cuboid(-4.0F, -31.0F, -7.0F, 8.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(12, 17).mirrored().cuboid(-4.0F, -35.0F, -7.0F, 8.0F, 4.0F, 12.0F, new Dilation(0.0F)).mirrored(false)
				.uv(4, 17).mirrored().cuboid(-6.0F, -35.0F, -5.0F, 2.0F, 4.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 3).mirrored().cuboid(-6.0F, -31.0F, -5.0F, 2.0F, 4.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(44, 3).mirrored().cuboid(4.0F, -31.0F, -5.0F, 2.0F, 4.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(40, 17).mirrored().cuboid(4.0F, -35.0F, -5.0F, 2.0F, 4.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(16, 7).mirrored().cuboid(-4.0F, -37.0F, -5.0F, 8.0F, 2.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(16, 33).mirrored().cuboid(-4.0F, -31.0F, -5.0F, 8.0F, 7.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData backshroom_r1 = body.addChild("backshroom_r1", ModelPartBuilder.create().uv(50, 35).mirrored().cuboid(-3.0F, -22.0F, 2.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
				.uv(20, 48).mirrored().cuboid(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData leftArm = partdefinition.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData shroom_r1 = leftArm.addChild("shroom_r1", ModelPartBuilder.create().uv(0, 32).cuboid(-10.0F, -24.0F, 0.0F, 2.0F, 12.0F, 0.0F, new Dilation(0.0F))
				.uv(48, 48).cuboid(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 22.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData rightArm = partdefinition.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData shroom_r2 = rightArm.addChild("shroom_r2", ModelPartBuilder.create().uv(5, 32).mirrored().cuboid(8.0F, -24.0F, 0.0F, 2.0F, 12.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(48, 48).mirrored().cuboid(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.0F, 22.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData leftLeg = partdefinition.addChild("leftLeg", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

		ModelPartData rightLeg = partdefinition.addChild("rightLeg", ModelPartBuilder.create().uv(0, 48).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(1.9F, 12.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(VerdigrisZombieRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.yawDegrees;
		float limbSwing = renderState.limbFrequency;
		float limbSwingAmount = renderState.limbAmplitudeMultiplier;

		CrossbowPosing.meleeAttack(this.leftArm, this.rightArm, renderState.isAggressive, renderState.attackTime, renderState.age);
		this.head.yaw = netHeadYaw / 57.3F;
		this.head.pitch = headPitch / 57.3F;
		this.leftLeg.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.yaw = 0.0F;
		this.rightLeg.yaw = 0.0F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}