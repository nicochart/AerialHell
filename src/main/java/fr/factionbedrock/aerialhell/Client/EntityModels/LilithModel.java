package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.LilithRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made by Cixon with Blockbench
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class LilithModel extends EntityModel<LilithRenderState>
{
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart wingRight;
	private final ModelPart wingLeft;
	private final ModelPart legLeft;
	private final ModelPart legRight;
	private final ModelPart armLeft;
	private final ModelPart armRight;

	public LilithModel(ModelPart root)
	{
		super(root);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.wingRight = root.getChild("wingRight");
		this.wingLeft = root.getChild("wingLeft");
		this.legLeft = root.getChild("legLeft");
		this.legRight = root.getChild("legRight");
		this.armLeft = root.getChild("armLeft");
		this.armRight = root.getChild("armRight");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(17, 16).cuboid(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData wingRight = partdefinition.addChild("wingRight", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 5.0F, 2.0F));

		ModelPartData wingRight_r1 = wingRight.addChild("wingRight_r1", ModelPartBuilder.create().uv(0, 30).cuboid(-0.5F, -25.0F, 4.0F, 0.0F, 13.0F, 21.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 19.0F, -2.0F, 0.2094F, -0.6981F, 0.0F));

		ModelPartData wingLeft = partdefinition.addChild("wingLeft", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 5.0F, 2.0F));

		ModelPartData wingLeft_r1 = wingLeft.addChild("wingLeft_r1", ModelPartBuilder.create().uv(0, 30).cuboid(0.5F, -25.0F, 4.0F, 0.0F, 13.0F, 21.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 19.0F, -2.0F, 0.2094F, 0.6981F, 0.0F));

		ModelPartData legLeft = partdefinition.addChild("legLeft", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData legRight = partdefinition.addChild("legRight", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData armLeft = partdefinition.addChild("armLeft", ModelPartBuilder.create().uv(41, 16).mirrored().cuboid(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 0.0F, 0.0F));

		ModelPartData armRight = partdefinition.addChild("armRight", ModelPartBuilder.create().uv(41, 16).cuboid(-4.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 0.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(LilithRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.yawDegrees;
		float limbSwing = renderState.limbFrequency;
		float limbSwingAmount = renderState.limbAmplitudeMultiplier;
		if (!renderState.isTransforming)
		{
			int i = renderState.attackTimer;
			if (i > 0)
			{
				this.armRight.pitch = -2.0F + 1.5F * MathHelper.wrap((float)i, 10.0F) * 0.5f;
				this.armLeft.pitch = -2.0F + 1.5F * MathHelper.wrap((float)i, 10.0F) * 0.5f;
			}
			else
			{
				this.armRight.pitch = (-0.2F + 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
				this.armLeft.pitch = (-0.2F - 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
			}
		}
		else
		{
			this.armRight.pitch = - 2.2F;
			this.armLeft.pitch = - 2.2F;
		}
		this.head.yaw = netHeadYaw / 57.3F;
		this.head.pitch = headPitch / 57.3F;
		this.legLeft.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount; this.legLeft.yaw = 0.0F;
		this.legRight.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount; this.legRight.yaw = 0.0F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		wingRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		wingLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		legLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		legRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		armLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		armRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}