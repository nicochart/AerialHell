package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.GlidingTurtleRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made by Cixon with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class GlidingTurtleModel extends EntityModel<GlidingTurtleRenderState>
{
	private final ModelPart Head;
	private final ModelPart Body;
	private final ModelPart WingRight;
	private final ModelPart WingLeft;
	private final ModelPart LegFrontRight;
	private final ModelPart LegFrontLeft;
	private final ModelPart LegBackRight;
	private final ModelPart LegBackLeft;

	private boolean isGliding;
	private boolean isChild;

	public GlidingTurtleModel(ModelPart root)
	{
		super(root);
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.WingRight = root.getChild("WingRight");
		this.WingLeft = root.getChild("WingLeft");
		this.LegFrontRight = root.getChild("LegFrontRight");
		this.LegFrontLeft = root.getChild("LegFrontLeft");
		this.LegBackRight = root.getChild("LegBackRight");
		this.LegBackLeft = root.getChild("LegBackLeft");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData Head = partdefinition.addChild("Head", ModelPartBuilder.create().uv(92, 0).cuboid(-4.0F, -5.0F, -10.0F, 8.0F, 6.0F, 10.0F, new Dilation(0.0F))
				.uv(92, 16).cuboid(-4.0F, 1.0F, -10.0F, 8.0F, 2.0F, 10.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 9.0F, -7.0F));

		ModelPartData Body = partdefinition.addChild("Body", ModelPartBuilder.create().uv(0, 61).cuboid(-12.0F, -17.0F, -9.0F, 24.0F, 4.0F, 25.0F, new Dilation(0.0F))
				.uv(25, 91).cuboid(-10.0F, -33.0F, -7.0F, 20.0F, 16.0F, 21.0F, new Dilation(0.0F))
				.uv(0, 90).cuboid(-5.0F, -22.0F, -8.0F, 10.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 27.0F, 0.0F));

		ModelPartData rightHole_r1 = Body.addChild("rightHole_r1", ModelPartBuilder.create().uv(0, 90).mirrored().cuboid(-8.0F, -22.0F, -11.0F, 10.0F, 5.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData leftHole_r1 = Body.addChild("leftHole_r1", ModelPartBuilder.create().uv(0, 90).cuboid(-2.0F, -22.0F, -11.0F, 10.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData WingRight = partdefinition.addChild("WingRight", ModelPartBuilder.create(), ModelTransform.origin(-11.0F, 7.0F, 3.0F));

		ModelPartData Wing_r1 = WingRight.addChild("Wing_r1", ModelPartBuilder.create().uv(19, 0).cuboid(-24.0F, -19.0F, -7.0F, 12.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(11.0F, 20.0F, -3.0F, -0.2094F, 0.0F, 0.0873F));

		ModelPartData WingLeft = partdefinition.addChild("WingLeft", ModelPartBuilder.create(), ModelTransform.origin(11.0F, 7.0F, 3.0F));

		ModelPartData Wing_r2 = WingLeft.addChild("Wing_r2", ModelPartBuilder.create().uv(19, 0).mirrored().cuboid(12.0F, -19.0F, -7.0F, 12.0F, 0.0F, 18.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-11.0F, 20.0F, -3.0F, -0.2094F, 0.0F, -0.0873F));

		ModelPartData LegFrontRight = partdefinition.addChild("LegFrontRight", ModelPartBuilder.create().uv(0, 112).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(-6.0F, 14.0F, -4.0F));

		ModelPartData LegFrontLeft = partdefinition.addChild("LegFrontLeft", ModelPartBuilder.create().uv(0, 112).mirrored().cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(6.0F, 14.0F, -4.0F));

		ModelPartData LegBackRight = partdefinition.addChild("LegBackRight", ModelPartBuilder.create().uv(0, 112).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(-6.0F, 14.0F, 10.0F));

		ModelPartData LegBackLeft = partdefinition.addChild("LegBackLeft", ModelPartBuilder.create().uv(0, 112).mirrored().cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(6.0F, 14.0F, 10.0F));

		return TexturedModelData.of(meshdefinition, 128, 128);
	}

	@Override public void setAngles(GlidingTurtleRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		this.isGliding = renderState.isGliding; this.isChild = renderState.baby;
		this.Head.yaw = netHeadYaw / 57.29578F;
		this.Head.pitch = headPitch / 57.29578F;

		this.animateRightLeg(this.LegFrontRight, limbSwing, limbSwingAmount);
		this.animateRightLeg(this.LegBackRight, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.LegFrontLeft, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.LegBackLeft, limbSwing, limbSwingAmount);

		if (isGliding) {this.WingRight.roll = 0; this.WingLeft.roll = 0;}
		else {this.WingRight.roll = -45F; this.WingLeft.roll = 45F;}
	}

	private void animateLeftLeg(ModelPart model, float limbSwing, float limbSwingAmount)
	{
		model.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount; model.yaw = 0.0F;
	}

	private void animateRightLeg(ModelPart model, float limbSwing, float limbSwingAmount)
	{
		model.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount; model.yaw = 0.0F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{

		if(isChild)
		{
			poseStack.scale(0.5F, 0.5F, 0.5F);
			poseStack.translate(0.0F, 1.5F, 0.0F);
		}
		poseStack.push();

		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		WingRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		WingLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		LegFrontRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		LegFrontLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		LegBackRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		LegBackLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		poseStack.pop();
	}
}