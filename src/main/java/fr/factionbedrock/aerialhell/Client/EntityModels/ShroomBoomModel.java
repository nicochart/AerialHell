package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShroomBoomRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ArmPosing;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ShroomBoomModel extends EntityModel<ShroomBoomRenderState>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart arm1;
	private final ModelPart arm2;

	public ShroomBoomModel(ModelPart root)
	{
		super(root);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.leg0 = root.getChild("leg0");
		this.leg1 = root.getChild("leg1");
		this.leg2 = root.getChild("leg2");
		this.leg3 = root.getChild("leg3");
		this.arm1 = root.getChild("arm1");
		this.arm2 = root.getChild("arm2");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(16, 49).mirrored().cuboid(-4.0F, -18.0F, -2.0F, 8.0F, 11.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(12, 33).mirrored().cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(8, 17).mirrored().cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 6.0F, 10.0F, new Dilation(0.0F)).mirrored(false)
				.uv(16, 9).mirrored().cuboid(-3.0F, -12.0F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, 6.0F, 0.0F));

		ModelPartData leg0 = partdefinition.addChild("leg0", ModelPartBuilder.create().uv(0, 53).mirrored().cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(2.0F, 18.0F, 4.0F));

		ModelPartData leg1 = partdefinition.addChild("leg1", ModelPartBuilder.create().uv(0, 53).mirrored().cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-2.0F, 18.0F, 4.0F));

		ModelPartData leg2 = partdefinition.addChild("leg2", ModelPartBuilder.create().uv(0, 53).mirrored().cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(2.0F, 18.0F, -4.0F));

		ModelPartData leg3 = partdefinition.addChild("leg3", ModelPartBuilder.create().uv(0, 53).mirrored().cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-2.0F, 18.0F, -4.0F));

		ModelPartData arm1 = partdefinition.addChild("arm1", ModelPartBuilder.create().uv(50, 53).mirrored().cuboid(-3.0F, 1.0F, -2.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(42, 17).mirrored().cuboid(-4.0F, -1.0F, -3.0F, 5.0F, 3.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-4.0F, 7.0F, 0.0F));

		ModelPartData arm2 = partdefinition.addChild("arm2", ModelPartBuilder.create().uv(50, 42).mirrored().cuboid(0.0F, 1.0F, -2.0F, 3.0F, 7.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(42, 8).mirrored().cuboid(-1.0F, -1.0F, -3.0F, 5.0F, 3.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, 7.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(ShroomBoomRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		ArmPosing.zombieArms(this.arm2, this.arm1, renderState.isAggressive, renderState);
		this.head.yaw = netHeadYaw * ((float)Math.PI / 180F);
		this.head.pitch = headPitch * ((float)Math.PI / 180F);
		this.leg0.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg1.pitch = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg2.pitch = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		arm1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		arm2.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}