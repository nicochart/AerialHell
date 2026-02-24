package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialHellGolemRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class VoluciteGolemModel extends EntityModel<AerialHellGolemRenderState>
{
	//head is another entity
	//private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart block;
	private final ModelPart block2;
	private final ModelPart block3;
	private final ModelPart block4;
	private final ModelPart block5;
	private final ModelPart left_arm;
	private final ModelPart block6;
	private final ModelPart block7;
	private final ModelPart block8;
	private final ModelPart block9;
	private final ModelPart block10;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public VoluciteGolemModel(ModelPart root)
	{
        super(root);
		//this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.block = this.right_arm.getChild("block");
		this.block2 = this.right_arm.getChild("block2");
		this.block3 = this.right_arm.getChild("block3");
		this.block4 = this.right_arm.getChild("block4");
		this.block5 = this.right_arm.getChild("block5");
		this.left_arm = root.getChild("left_arm");
		this.block6 = this.left_arm.getChild("block6");
		this.block7 = this.left_arm.getChild("block7");
		this.block8 = this.left_arm.getChild("block8");
		this.block9 = this.left_arm.getChild("block9");
		this.block10 = this.left_arm.getChild("block10");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		//ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(44, 42).cuboid(-5.0F, -8.0F, -4.5F, 10.0F, 8.0F, 8.0F, new Dilation(-0.2F))
		//.uv(52, 35).cuboid(-3.0F, -10.0F, -2.5F, 6.0F, 3.0F, 4.0F, new Dilation(-0.2F)), ModelTransform.origin(0.0F, -10.0F, 0.0F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(35, 66).cuboid(-7.0F, -32.0F, -7.0F, 14.0F, 10.0F, 13.0F, new Dilation(-1.0F))
		.uv(45, 95).cuboid(-4.5F, -21.0F, -4.5F, 9.0F, 5.0F, 8.0F, new Dilation(0.0F))
		.uv(53, 89).cuboid(-2.5F, -23.0F, -2.5F, 5.0F, 2.0F, 4.0F, new Dilation(0.0F))
		.uv(53, 58).cuboid(-2.5F, -35.0F, -2.5F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(36, 66).cuboid(-8.0F, -31.0F, -2.5F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(76, 66).cuboid(6.0F, -31.0F, -2.5F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData right_arm = partdefinition.addChild("right_arm", ModelPartBuilder.create().uv(31, 12).mirrored().cuboid(-4.5F, 3.5F, -2.5F, 4.0F, 23.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(31, 7).mirrored().cuboid(-4.0F, -0.5F, -2.5F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-8.0F, -6.0F, 0.0F));

		ModelPartData block = right_arm.addChild("block", ModelPartBuilder.create().uv(10, 11).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(4, 16).mirrored().cuboid(-6.0F, -1.5F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-1.0F, 3.0F, 0.0F));

		ModelPartData block2 = right_arm.addChild("block2", ModelPartBuilder.create().uv(10, 20).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(2, 25).mirrored().cuboid(-7.0F, -1.5F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-1.0F, 8.0F, 0.0F));

		ModelPartData block3 = right_arm.addChild("block3", ModelPartBuilder.create().uv(10, 29).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 34).mirrored().cuboid(-8.0F, -1.5F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-1.0F, 13.0F, 0.0F));

		ModelPartData block4 = right_arm.addChild("block4", ModelPartBuilder.create().uv(10, 38).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 43).mirrored().cuboid(-8.0F, -1.5F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-1.0F, 18.0F, 0.0F));

		ModelPartData block5 = right_arm.addChild("block5", ModelPartBuilder.create().uv(10, 47).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(2, 52).mirrored().cuboid(-7.0F, -1.5F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-1.0F, 23.0F, 0.0F));

		ModelPartData left_arm = partdefinition.addChild("left_arm", ModelPartBuilder.create().uv(81, 12).mirrored().cuboid(0.5F, 3.5F, -2.5F, 4.0F, 23.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(81, 7).mirrored().cuboid(0.0F, -0.5F, -2.5F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(8.0F, -6.0F, 0.0F));

		ModelPartData block6 = left_arm.addChild("block6", ModelPartBuilder.create().uv(98, 11).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(118, 16).mirrored().cuboid(1.0F, -1.5F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, 3.0F, 0.0F));

		ModelPartData block7 = left_arm.addChild("block7", ModelPartBuilder.create().uv(98, 20).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(118, 25).mirrored().cuboid(1.0F, -1.5F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, 8.0F, 0.0F));

		ModelPartData block8 = left_arm.addChild("block8", ModelPartBuilder.create().uv(98, 29).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(118, 34).mirrored().cuboid(1.0F, -1.5F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, 13.0F, 0.0F));

		ModelPartData block9 = left_arm.addChild("block9", ModelPartBuilder.create().uv(98, 38).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(118, 43).mirrored().cuboid(1.0F, -1.5F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, 18.0F, 0.0F));

		ModelPartData block10 = left_arm.addChild("block10", ModelPartBuilder.create().uv(98, 47).mirrored().cuboid(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(118, 52).mirrored().cuboid(1.0F, -1.5F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, 23.0F, 0.0F));

		ModelPartData left_leg = partdefinition.addChild("left_leg", ModelPartBuilder.create().uv(63, 108).mirrored().cuboid(-1.5F, 0.0F, -2.5F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(79, 119).mirrored().cuboid(-2.0F, 11.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(79, 110).mirrored().cuboid(-2.0F, 6.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(79, 101).mirrored().cuboid(-2.0F, 1.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(3.0F, 8.0F, 0.0F));

		ModelPartData right_leg = partdefinition.addChild("right_leg", ModelPartBuilder.create().uv(45, 108).cuboid(-2.5F, 0.0F, -2.5F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F))
		.uv(25, 119).cuboid(-3.0F, 11.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F))
		.uv(25, 110).cuboid(-3.0F, 6.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F))
		.uv(25, 101).cuboid(-3.0F, 1.0F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.origin(-3.0F, 8.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 128, 128);
	}

	@Override public void setAngles(AerialHellGolemRenderState renderState)
	{
		//float headPitch = renderState.pitch;
		//float netHeadYaw = renderState.yaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		//this.head.yaw = netHeadYaw * ((float)Math.PI / 180F);
		//this.head.pitch = headPitch * ((float)Math.PI / 180F);

		int i = renderState.attackTimer;
		if (i > 0)
		{
			this.left_arm.pitch = -2.0F + 0.6F * MathHelper.wrap((float)i, 10.0F);
			this.right_arm.pitch = -2.0F + 0.6F * MathHelper.wrap((float)i, 10.0F);
		}
		else
		{
			this.left_arm.pitch = (-0.2F + 0.8F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
			this.right_arm.pitch = (-0.2F - 0.8F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
		}

		this.left_leg.pitch = -1.5F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.right_leg.pitch = 1.5F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.left_leg.yaw = 0.0F; this.right_leg.yaw = 0.0F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		//head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}