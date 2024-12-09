package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;

// Made by Cixon with Blockbench
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class MudCycleMageModel extends EntityModel<MobEntity>
{
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public MudCycleMageModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
				.uv(32, 53).mirrored().cuboid(-4.0F, -10.0F, -3.0F, 8.0F, 3.0F, 8.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, -24.0F, -1.0F, 8.0F, 22.0F, 4.0F, new Dilation(0.0F))
				.uv(16, 16).cuboid(-4.0F, -24.0F, -1.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData leftArm = partdefinition.addChild("leftArm", ModelPartBuilder.create().uv(48, 37).cuboid(0.5F, 1.0F, -8.0F, 0.0F, 6.0F, 7.0F, new Dilation(0.0F))
				.uv(40, 36).cuboid(-0.5F, -2.0F, -9.0F, 3.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 2.0F, 1.0F));

		ModelPartData Leftarm_r1 = leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(4.0F, -2.0F, -23.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-4.0F, 22.0F, -1.0F, -1.5708F, 0.0F, 0.0F));

		ModelPartData rightArm = partdefinition.addChild("rightArm", ModelPartBuilder.create().uv(48, 37).mirrored().cuboid(-0.5F, 1.0F, -7.0F, 0.0F, 6.0F, 7.0F, new Dilation(0.0F)).mirrored(false)
				.uv(40, 36).cuboid(-2.5F, -2.0F, -8.0F, 3.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 2.0F, 0.0F));

		ModelPartData Rightarm_r1 = rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(40, 16).cuboid(-6.0F, -2.0F, -23.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 22.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		ModelPartData rightLeg = partdefinition.addChild("rightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 13.0F, 1.0F));

		ModelPartData leftLeg = partdefinition.addChild("leftLeg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 13.0F, 1.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override
	public void setAngles(MobEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yaw = netHeadYaw / 57.0F;
		this.head.pitch = headPitch / 57.0F;

		if (entity instanceof MudCycleMageEntity boss && boss.isInDeadOrDyingPhase())
		{
			this.rightArm.pitch = - 1.5F;
			this.leftArm.pitch = - 1.5F;
		}
		else
		{
			this.rightArm.pitch = (-0.2F + 0.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
			this.leftArm.pitch = (-0.2F - 0.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
		}

		this.leftLeg.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.yaw = 0.0F;
		this.rightLeg.yaw = 0.0F;
	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}