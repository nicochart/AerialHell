package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.LunaticPriestRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class LunaticPriestModel extends EntityModel<LunaticPriestRenderState>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arm0;
	private final ModelPart arm1;
	private final ModelPart leg0;
	private final ModelPart leg1;

	public LunaticPriestModel(ModelPart root)
	{
		super(root);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.arm0 = root.getChild("arm0");
		this.arm1 = root.getChild("arm1");
		this.leg0 = root.getChild("leg0");
		this.leg1 = root.getChild("leg1");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 32).mirrored().cuboid(-7.0F, 1.0F, -4.0F, 14.0F, 14.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(7, 53).mirrored().cuboid(-4.5F, 15.0F, -3.0F, 9.0F, 5.0F, 6.0F, new Dilation(0.5F)).mirrored(false), ModelTransform.origin(0.0F, -7.0F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(6, 16).mirrored().cuboid(-4.0F, -6.0F, -5.5F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(4, 0).mirrored().cuboid(-5.0F, -7.0F, -4.5F, 10.0F, 8.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(7, 1).mirrored().cuboid(-4.0F, -8.0F, -2.5F, 8.0F, 7.0F, 7.0F, new Dilation(0.0F)).mirrored(false)
				.uv(15, 6).mirrored().cuboid(-3.0F, -8.0F, 5.5F, 6.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
				.uv(10, 2).mirrored().cuboid(-3.0F, -9.0F, 0.5F, 6.0F, 5.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
				.uv(15, 5).mirrored().cuboid(-2.0F, -7.0F, 5.5F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
				.uv(18, 6).mirrored().cuboid(-1.0F, -4.0F, 7.5F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(40, 29).mirrored().cuboid(-2.0F, 2.0F, -5.5F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(41, 27).mirrored().cuboid(-2.0F, -7.0F, -5.5F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, -7.0F, -1.0F));

		ModelPartData arm0 = partdefinition.addChild("arm0", ModelPartBuilder.create().uv(72, 40).mirrored().cuboid(1.0F, -0.5F, -2.0F, 2.0F, 20.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(86, 41).mirrored().cuboid(3.0F, 8.5F, -4.0F, 1.0F, 3.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(90, 36).mirrored().cuboid(3.0F, 7.5F, -2.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(88, 44).mirrored().cuboid(3.0F, 10.5F, -5.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(96, 44).mirrored().cuboid(3.0F, 10.5F, 3.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(6.0F, -4.0F, 0.0F));

		ModelPartData arm1 = partdefinition.addChild("arm1", ModelPartBuilder.create().uv(57, 40).mirrored().cuboid(-3.0F, -0.5F, -2.0F, 2.0F, 20.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(86, 41).mirrored().cuboid(-4.0F, 8.5F, -4.0F, 1.0F, 3.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(90, 36).mirrored().cuboid(-4.0F, 7.5F, -2.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(87, 44).mirrored().cuboid(-4.0F, 10.5F, 3.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(96, 44).mirrored().cuboid(-4.0F, 10.5F, -5.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-6.0F, -4.0F, 0.0F));

		ModelPartData leg0 = partdefinition.addChild("leg0", ModelPartBuilder.create().uv(54, 13).mirrored().cuboid(-2.5F, 2.0F, -2.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, 11.0F, 0.0F));

		ModelPartData leg1 = partdefinition.addChild("leg1", ModelPartBuilder.create().uv(70, 13).cuboid(0.5F, 2.0F, -2.0F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(-5.0F, 11.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 128, 64);
	}

	@Override public void setAngles(LunaticPriestRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		this.head.yaw = netHeadYaw / 57.29578F;
		this.head.pitch = headPitch / 57.29578F;

		int i = renderState.attackTimer;
		if (i > 0)
		{
			this.arm0.pitch = -2.0F + 0.6F * MathHelper.wrap((float)i, 10.0F);
			this.arm1.pitch = -2.0F + 0.6F * MathHelper.wrap((float)i, 10.0F);
		}
		else
		{
			this.arm0.pitch = (-0.2F + 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
			this.arm1.pitch = (-0.2F - 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		}

		this.leg0.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.leg1.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.leg0.yaw = 0.0F;
		this.leg1.yaw = 0.0F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		arm0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		arm1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}