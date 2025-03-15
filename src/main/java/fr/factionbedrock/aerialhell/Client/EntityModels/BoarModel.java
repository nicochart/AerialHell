package fr.factionbedrock.aerialhell.Client.EntityModels;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class BoarModel<S extends LivingEntityRenderState> extends EntityModel<S>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart tusk;
	private final ModelPart backLeftLeg;
	private final ModelPart backRightLeg;
	private final ModelPart frontLeftLeg;
	private final ModelPart frontRightLeg;

	private boolean isChild;

	public BoarModel(ModelPart root)
	{
        super(root);
        this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.tusk = root.getChild("tusk");
		this.backLeftLeg = root.getChild("backLeftLeg");
		this.backRightLeg = root.getChild("backRightLeg");
		this.frontLeftLeg = root.getChild("frontLeftLeg");
		this.frontRightLeg = root.getChild("frontRightLeg");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 40).cuboid(-5.0F, -1.0F, -9.0F, 10.0F, 8.0F, 16.0F, new Dilation(0.0F))
				.uv(42, 40).cuboid(5.0F, 1.0F, -9.0F, 1.0F, 6.0F, 6.0F, new Dilation(0.0F))
				.uv(42, 28).cuboid(5.0F, 1.0F, 1.0F, 1.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 11.0F, 2.0F));

		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(42, 40).cuboid(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-5.5F, 4.0F, -6.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData body_r2 = body.addChild("body_r2", ModelPartBuilder.create().uv(42, 28).cuboid(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-5.5F, 4.0F, 4.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 9).cuboid(-4.0F, -3.0F, -7.0F, 8.0F, 7.0F, 7.0F, new Dilation(0.0F))
				.uv(30, 15).cuboid(-2.0F, 0.0F, -11.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.0F, -5.0F));

		ModelPartData rightEar_r1 = head.addChild("rightEar_r1", ModelPartBuilder.create().uv(35, 4).cuboid(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -3.0F, -3.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData leftEar_r1 = head.addChild("leftEar_r1", ModelPartBuilder.create().uv(35, 4).cuboid(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -3.0F, -3.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData tusk = partdefinition.addChild("tusk", ModelPartBuilder.create().uv(50, 7).cuboid(3.0F, 1.0F, -10.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
				.uv(50, 11).cuboid(2.0F, 3.0F, -10.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.0F, -5.0F));

		ModelPartData tusk_r1 = tusk.addChild("tusk_r1", ModelPartBuilder.create().uv(50, 11).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, 3.5F, -9.5F, 0.0F, 3.1416F, 0.0F));

		ModelPartData tusk_r2 = tusk.addChild("tusk_r2", ModelPartBuilder.create().uv(50, 7).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, 2.5F, -9.5F, 0.0F, 3.1416F, 0.0F));

		ModelPartData backLeftLeg = partdefinition.addChild("backLeftLeg", ModelPartBuilder.create().uv(18, 28).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 18.0F, 7.0F));

		ModelPartData backRightLeg = partdefinition.addChild("backRightLeg", ModelPartBuilder.create().uv(18, 28).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 18.0F, 7.0F));

		ModelPartData frontLeftLeg = partdefinition.addChild("frontLeftLeg", ModelPartBuilder.create().uv(0, 28).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 18.0F, -5.0F));

		ModelPartData frontRightLeg = partdefinition.addChild("frontRightLeg", ModelPartBuilder.create().uv(0, 28).cuboid(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 18.0F, -5.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(S renderState)
	{
		super.setAngles(renderState);

		this.isChild = renderState.baby;
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.yawDegrees;
		float limbSwing = renderState.limbFrequency;
		float limbSwingAmount = renderState.limbAmplitudeMultiplier;

		this.head.pitch = headPitch * ((float)Math.PI / 180F); this.tusk.pitch = headPitch * ((float)Math.PI / 180F);
		this.head.yaw = netHeadYaw * ((float)Math.PI / 180F); this.tusk.yaw = netHeadYaw * ((float)Math.PI / 180F);
		this.backRightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.backLeftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.frontRightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.frontLeftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		if(isChild)
		{
			poseStack.scale(0.5F, 0.5F, 0.5F);
			poseStack.translate(0.0F, 1.5F, 0.0F);
		}
		poseStack.push();

		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		backLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		backRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		frontLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		frontRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);

		if (!isChild)
		{
			tusk.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		}
		poseStack.pop();
	}
}