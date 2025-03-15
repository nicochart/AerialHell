package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.AutomatonRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class AutomatonModel<S extends AutomatonRenderState> extends EmptyModel<S>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public AutomatonModel(ModelPart root)
	{
		super(root);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(34, 3).mirrored().cuboid(-1.0F, -28.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(32, 0).mirrored().cuboid(-2.0F, -25.0F, -1.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(16, 16).mirrored().cuboid(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-4.0F, -10.0F, -7.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData rightArm = partdefinition.addChild("rightArm", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData rightArm_r1 = rightArm.addChild("rightArm_r1", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(4.0F, -24.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.0F, 22.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData leftArm = partdefinition.addChild("leftArm", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData leftArm_r1 = leftArm.addChild("leftArm_r1", ModelPartBuilder.create().uv(40, 16).cuboid(-6.0F, -24.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 22.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData rightLeg = partdefinition.addChild("rightLeg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData rightLeg_r1 = rightLeg.addChild("rightLeg_r1", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(1.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, 12.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		ModelPartData leftLeg = partdefinition.addChild("leftLeg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData leftLeg_r1 = leftLeg.addChild("leftLeg_r1", ModelPartBuilder.create().uv(0, 16).cuboid(-3.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 12.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 32);
	}
	
	@Override public void setAngles(S renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.yawDegrees;
		float limbSwing = renderState.limbFrequency;
		float limbSwingAmount = renderState.limbAmplitudeMultiplier;

		this.head.yaw = netHeadYaw * ((float)Math.PI / 180F);
		this.head.pitch = 0.0873F + headPitch * ((float)Math.PI / 180F);

		int i = renderState.attackTimer;
		if (i > 0)
		{
			this.leftArm.pitch = -2.0F + 0.6F * MathHelper.wrap((float)i, 10.0F);
			this.rightArm.pitch = -2.0F + 0.6F * MathHelper.wrap((float)i, 10.0F);
		}
		else
		{
			this.leftArm.pitch = (-0.2F + 0.8F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
			this.rightArm.pitch = (-0.2F - 0.8F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
		}

		this.leftLeg.pitch = MathHelper.cos(limbSwing * 0.7F) * 1.4F * limbSwingAmount;
		this.rightLeg.pitch = MathHelper.cos(limbSwing * 0.7F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}