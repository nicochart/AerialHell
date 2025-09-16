package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.ElementSpiritRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ElementSpiritModel<S extends ElementSpiritRenderState> extends EntityModel<S>
{
	private final ModelPart head;
	private final ModelPart headShard;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public ElementSpiritModel(ModelPart root)
	{
		super(root);
		this.head = root.getChild("head");
		this.headShard = root.getChild("headShard");
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

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 6.0F, 0.0F));

		ModelPartData headShard = partdefinition.addChild("headShard", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 6.0F, 0.0F));

		ModelPartData headShard_r1 = headShard.addChild("headShard_r1", ModelPartBuilder.create().uv(42, 10).cuboid(-15.0F, -32.0F, -3.0F, 10.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 18.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 20).cuboid(-4.0F, -17.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(1.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData leftArm = partdefinition.addChild("leftArm", ModelPartBuilder.create().uv(24, 20).cuboid(0.0F, 0.0F, -3.0F, 3.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(5.0F, 6.0F, 0.0F));

		ModelPartData rightArm = partdefinition.addChild("rightArm", ModelPartBuilder.create().uv(24, 20).mirrored().cuboid(-3.0F, 0.0F, -3.0F, 3.0F, 5.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-5.0F, 6.0F, 0.0F));

		ModelPartData leftLeg = partdefinition.addChild("leftLeg", ModelPartBuilder.create().uv(0, 36).mirrored().cuboid(-2.0F, 1.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-2.0F, 17.0F, 0.0F));

		ModelPartData rightLeg = partdefinition.addChild("rightLeg", ModelPartBuilder.create().uv(0, 36).cuboid(-2.0F, 1.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(2.0F, 17.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(S renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		this.head.yaw = netHeadYaw / 57.0F;
		this.head.pitch = headPitch / 57.0F;
		this.headShard.yaw = netHeadYaw / 57.0F;
		this.headShard.pitch = headPitch / 57.0F;

		this.rightArm.pitch = (-0.2F + 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 1.2F;
		this.leftArm.pitch = (-0.2F - 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 1.2F;

		this.leftLeg.pitch = -1.5F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.pitch = 1.5F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.yaw = 0.0F;
		this.rightLeg.yaw = 0.0F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		headShard.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}