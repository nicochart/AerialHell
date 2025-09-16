package fr.factionbedrock.aerialhell.Client.EntityModels;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class TornSpiritModel extends EntityModel<LivingEntityRenderState>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public TornSpiritModel(ModelPart root)
	{
		super(root);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData frontCrest2_r1 = body.addChild("frontCrest2_r1", ModelPartBuilder.create().uv(0, 5).mirrored().cuboid(0.0F, -3.0F, -1.6426F, 0.0F, 6.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.0F, 5.5478F, -4.3574F, 0.258F, -0.045F, 0.1687F));

		ModelPartData frontCrest1_r1 = body.addChild("frontCrest1_r1", ModelPartBuilder.create().uv(0, 14).mirrored().cuboid(0.0F, -3.0F, -1.8342F, 0.0F, 6.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, 8.5221F, -3.9658F, 0.043F, 0.0076F, -0.1744F));

		ModelPartData backCrest_r1 = body.addChild("backCrest_r1", ModelPartBuilder.create().uv(48, 0).mirrored().cuboid(0.0F, -7.0F, 2.0F, 0.0F, 15.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(33, 55).mirrored().cuboid(1.0F, 1.0F, -1.3F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(21, 55).mirrored().cuboid(-3.0F, 1.0F, -1.3F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(15, 30).mirrored().cuboid(-6.0F, -8.0F, -2.0F, 12.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(19, 38).mirrored().cuboid(-4.0F, -4.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 8.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		ModelPartData leftLegBone_r1 = body.addChild("leftLegBone_r1", ModelPartBuilder.create().uv(33, 55).mirrored().cuboid(1.0F, 7.0F, -3.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(21, 55).mirrored().cuboid(-3.0F, 7.0F, -3.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 8.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData headPlane_r1 = head.addChild("headPlane_r1", ModelPartBuilder.create().uv(44, 54).mirrored().cuboid(-6.5F, -6.5F, -2.0F, 10.0F, 10.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(19, 1).mirrored().cuboid(-3.5F, -3.5F, -4.0F, 7.0F, 7.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
				.uv(15, 14).mirrored().cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -4.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData rightArm = partdefinition.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.origin(5.0F, 2.0F, 0.0F));

		ModelPartData rightArmCrest_r1 = rightArm.addChild("rightArmCrest_r1", ModelPartBuilder.create().uv(0, 27).mirrored().cuboid(-1.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.0F, 4.0F, 0.0F, 0.2598F, -0.0227F, 0.1731F));

		ModelPartData rightArm_r1 = rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(47, 36).mirrored().cuboid(-2.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.0F, 4.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		ModelPartData leftArm = partdefinition.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.origin(-5.0F, 2.0F, 0.0F));

		ModelPartData leftArmCrest_r1 = leftArm.addChild("leftArmCrest_r1", ModelPartBuilder.create().uv(0, 1).cuboid(-3.5F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 4.0F, 0.0F, 0.3119F, -0.3743F, 0.1695F));

		ModelPartData leftArm_r1 = leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(7, 36).cuboid(0.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 4.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(LivingEntityRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		this.head.yaw = netHeadYaw / 57.29578F;
		this.head.pitch = headPitch / 57.29578F;

		this.rightArm.pitch = (-0.2F + 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
		this.leftArm.pitch = (-0.2F - 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}