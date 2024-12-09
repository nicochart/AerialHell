package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made by Cixon
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class SandySheepModel extends EntityModel<SandySheepEntity>
{
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightFrontLegCoat;
	private final ModelPart rightBackLeg;
	private final ModelPart rightBackLegCoat;
	private final ModelPart leftFrontLeg;
	private final ModelPart leftFrontLegCoat;
	private final ModelPart leftBackLeg;
	private final ModelPart leftBackLegCoat;
	private final ModelPart headCoat;
	private final ModelPart bodyCoat;

	private boolean hasWool;
	private boolean isChild;

	public SandySheepModel(ModelPart root)
	{
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.rightFrontLeg = root.getChild("rightFrontLeg");
		this.rightFrontLegCoat = root.getChild("rightFrontLegCoat");
		this.rightBackLeg = root.getChild("rightBackLeg");
		this.rightBackLegCoat = root.getChild("rightBackLegCoat");
		this.leftFrontLeg = root.getChild("leftFrontLeg");
		this.leftFrontLegCoat = root.getChild("leftFrontLegCoat");
		this.leftBackLeg = root.getChild("leftBackLeg");
		this.leftBackLegCoat = root.getChild("leftBackLegCoat");
		this.headCoat = root.getChild("headCoat");
		this.bodyCoat = root.getChild("bodyCoat");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, -4.0F, -7.0F, 6.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, -8.0F));

		ModelPartData snout_r1 = head.addChild("snout_r1", ModelPartBuilder.create().uv(28, 8).cuboid(-1.0F, -18.01F, -16.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, 8.0F, 0.0698F, 0.0F, 0.0F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(80, 42).cuboid(-3.5F, -17.0F, -8.5F, 8.0F, 6.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData rightFrontLeg = partdefinition.addChild("rightFrontLeg", ModelPartBuilder.create().uv(0, 49).cuboid(-1.0F, -4.0F, -2.0F, 3.0F, 12.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 16.0F, -6.0F));

		ModelPartData rightFrontLegCoat = partdefinition.addChild("rightFrontLegCoat", ModelPartBuilder.create().uv(12, 46).mirrored().cuboid(-3.0F, -5.0F, -5.0F, 7.0F, 10.0F, 8.0F, new Dilation(-1.0F)).mirrored(false), ModelTransform.pivot(-3.0F, 16.0F, -6.0F));

		ModelPartData rightBackLeg = partdefinition.addChild("rightBackLeg", ModelPartBuilder.create().uv(0, 49).cuboid(-1.0F, -4.0F, -1.0F, 3.0F, 12.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 16.0F, 5.0F));

		ModelPartData rightBackLegCoat = partdefinition.addChild("rightBackLegCoat", ModelPartBuilder.create().uv(12, 46).mirrored().cuboid(-3.0F, -5.0F, -3.5F, 7.0F, 10.0F, 8.0F, new Dilation(-1.0F)).mirrored(false), ModelTransform.pivot(-3.0F, 16.0F, 5.0F));

		ModelPartData leftFrontLeg = partdefinition.addChild("leftFrontLeg", ModelPartBuilder.create().uv(0, 49).mirrored().cuboid(-2.0F, -4.0F, -2.0F, 3.0F, 12.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 16.0F, -6.0F));

		ModelPartData leftFrontLegCoat = partdefinition.addChild("leftFrontLegCoat", ModelPartBuilder.create().uv(12, 46).cuboid(-4.0F, -5.0F, -5.0F, 7.0F, 10.0F, 8.0F, new Dilation(-1.0F)), ModelTransform.pivot(4.0F, 16.0F, -6.0F));

		ModelPartData leftBackLeg = partdefinition.addChild("leftBackLeg", ModelPartBuilder.create().uv(0, 49).mirrored().cuboid(-2.0F, -4.0F, -1.0F, 3.0F, 12.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 16.0F, 5.0F));

		ModelPartData leftBackLegCoat = partdefinition.addChild("leftBackLegCoat", ModelPartBuilder.create().uv(12, 46).cuboid(-4.0F, -5.0F, -3.5F, 7.0F, 10.0F, 8.0F, new Dilation(-1.0F)), ModelTransform.pivot(4.0F, 16.0F, 5.0F));

		ModelPartData headCoat = partdefinition.addChild("headCoat", ModelPartBuilder.create().uv(40, 0).cuboid(-3.0F, -5.0F, -8.0F, 7.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, -8.0F));

		ModelPartData bodyCoat = partdefinition.addChild("bodyCoat", ModelPartBuilder.create().uv(0, 14).cuboid(-4.5F, -18.0F, -11.0F, 10.0F, 10.0F, 20.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 128, 64);
	}

	@Override
	public void setAngles(SandySheepEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.hasWool = entity.hasWool(); this.isChild = entity.isBaby();

		this.head.yaw = netHeadYaw / 57.29578F;
		this.head.pitch = headPitch / 57.29578F;
		this.headCoat.yaw = netHeadYaw / 57.29578F;
		this.headCoat.pitch = headPitch / 57.29578F;

		this.animateRightLeg(this.rightFrontLeg, limbSwing, limbSwingAmount);
		this.animateRightLeg(this.rightBackLeg, limbSwing, limbSwingAmount);
		this.animateRightLeg(this.rightFrontLegCoat, limbSwing, limbSwingAmount);
		this.animateRightLeg(this.rightBackLegCoat, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.leftFrontLeg, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.leftBackLeg, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.leftFrontLegCoat, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.leftBackLegCoat, limbSwing, limbSwingAmount);
	}

	private void animateLeftLeg(ModelPart model, float limbSwing, float limbSwingAmount)
	{
		model.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		model.yaw = 0.0F;
	}

	private void animateRightLeg(ModelPart model, float limbSwing, float limbSwingAmount)
	{
		model.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		model.yaw = 0.0F;
	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		if(isChild)
		{
			poseStack.scale(0.5F, 0.5F, 0.5F);
			poseStack.translate(0.0F, 1.5F, 0.0F);
		}
		poseStack.push();

		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightBackLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftFrontLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftBackLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		if (this.hasWool)
		{
			rightFrontLegCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			rightBackLegCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			leftFrontLegCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			leftBackLegCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			headCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			bodyCoat.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		}
		poseStack.pop();
	}
}