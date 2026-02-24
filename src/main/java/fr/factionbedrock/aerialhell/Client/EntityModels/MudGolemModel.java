package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialHellGolemRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

//Made by Cixon using BlockBench
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class MudGolemModel extends EntityModel<AerialHellGolemRenderState>
{
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public MudGolemModel(ModelPart root)
	{
		super(root);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -9.8F, -4.5F, 14.0F, 10.0F, 8.0F, new Dilation(-0.2F)), ModelTransform.origin(0.0F, -4.0F, 0.0F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 19).cuboid(-10.0F, -29.0F, -5.0F, 20.0F, 12.0F, 10.0F, new Dilation(-1.0F))
				.uv(9, 42).cuboid(-7.5F, -18.0F, -2.5F, 15.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData left_arm = partdefinition.addChild("left_arm", ModelPartBuilder.create().uv(62, 54).cuboid(0.0F, -1.0F, -2.5F, 5.0F, 25.0F, 5.0F, new Dilation(0.0F)), ModelTransform.origin(9.0F, -3.0F, 0.0F));

		ModelPartData right_arm = partdefinition.addChild("right_arm", ModelPartBuilder.create().uv(62, 19).mirrored().cuboid(-5.0F, -1.0F, -2.5F, 5.0F, 25.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-9.0F, -3.0F, 0.0F));

		ModelPartData left_leg = partdefinition.addChild("left_leg", ModelPartBuilder.create().uv(18, 72).mirrored().cuboid(-3.5F, 0.0F, -2.5F, 6.0F, 13.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(5.0F, 11.0F, 0.0F));

		ModelPartData right_leg = partdefinition.addChild("right_leg", ModelPartBuilder.create().uv(18, 53).cuboid(-3.5F, 0.0F, -2.5F, 6.0F, 13.0F, 5.0F, new Dilation(0.0F)), ModelTransform.origin(-4.0F, 11.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 128, 128);
	}

	@Override public void setAngles(AerialHellGolemRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		this.head.yaw = netHeadYaw * ((float)Math.PI / 180F);
		this.head.pitch = headPitch * ((float)Math.PI / 180F);

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
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}