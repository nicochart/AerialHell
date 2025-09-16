package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalGolemRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings


public class CrystalGolemCrystalModel<S extends CrystalGolemRenderState> extends EntityModel<S>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arm0;
	private final ModelPart arm1;
	private final ModelPart leg0;
	private final ModelPart leg1;

	public CrystalGolemCrystalModel(ModelPart root)
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

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(91, 46).mirrored().cuboid(5.0F, -8.0F, 2.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(91, 47).mirrored().cuboid(7.0F, -8.0F, 0.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(91, 52).mirrored().cuboid(-4.0F, -3.0F, 5.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(83, 65).mirrored().cuboid(-8.0F, 1.0F, 5.0F, 8.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(114, 74).mirrored().cuboid(-4.0F, 0.0F, -10.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(106, 72).mirrored().cuboid(-8.0F, 4.0F, -10.0F, 8.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(92, 84).mirrored().cuboid(-1.0F, 11.0F, -6.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(88, 92).mirrored().cuboid(-3.0F, 13.0F, -6.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(90, 73).mirrored().cuboid(5.0F, 0.0F, 5.0F, 0.0F, 8.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(82, 71).mirrored().cuboid(1.0F, 4.0F, 5.0F, 8.0F, 0.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(113, 51).mirrored().cuboid(5.0F, -3.0F, -11.0F, 0.0F, 8.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(105, 65).mirrored().cuboid(1.0F, 1.0F, -11.0F, 8.0F, 0.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, -7.0F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(92, 1).mirrored().cuboid(-7.0F, -19.0F, 0.5F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(88, 6).mirrored().cuboid(-7.0F, -17.0F, -1.5F, 3.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(92, 35).mirrored().cuboid(-7.0F, -10.0F, -3.5F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(88, 40).mirrored().cuboid(-7.0F, -8.0F, -5.5F, 3.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(92, 24).mirrored().cuboid(4.0F, -13.0F, -3.5F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(88, 29).mirrored().cuboid(4.0F, -11.0F, -5.5F, 3.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(92, 11).mirrored().cuboid(1.0F, -16.0F, 2.5F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(87, 19).mirrored().cuboid(-2.0F, -14.0F, 2.5F, 5.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, -7.0F, -2.0F));

		ModelPartData arm0 = partdefinition.addChild("arm0", ModelPartBuilder.create().uv(88, 117).mirrored().cuboid(13.0F, 13.5F, -3.0F, 3.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(92, 112).mirrored().cuboid(13.0F, 11.5F, -1.0F, 3.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(87, 102).mirrored().cuboid(9.0F, 8.5F, -7.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(91, 93).mirrored().cuboid(11.0F, 6.5F, -7.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(91, 103).mirrored().cuboid(11.0F, 6.5F, -7.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(107, 89).mirrored().cuboid(11.0F, 18.5F, 3.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(103, 97).mirrored().cuboid(9.0F, 20.5F, 3.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, -7.0F, 0.0F));

		ModelPartData arm1 = partdefinition.addChild("arm1", ModelPartBuilder.create().uv(104, 117).mirrored().cuboid(-15.0F, 5.5F, -2.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(108, 112).mirrored().cuboid(-15.0F, 3.5F, 0.0F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(103, 107).mirrored().cuboid(-13.0F, 12.5F, -6.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(107, 99).mirrored().cuboid(-11.0F, 10.5F, -6.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, -7.0F, 0.0F));

		ModelPartData leg0 = partdefinition.addChild("leg0", ModelPartBuilder.create().uv(71, 118).mirrored().cuboid(-0.5F, 8.0F, 2.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(75, 109).mirrored().cuboid(1.5F, 6.0F, 2.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, 11.0F, 0.0F));

		ModelPartData leg1 = partdefinition.addChild("leg1", ModelPartBuilder.create().uv(71, 106).cuboid(-1.5F, 5.0F, -6.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F))
		.uv(75, 98).cuboid(0.5F, 3.0F, -6.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(-5.0F, 11.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 128, 128);
	}

	@Override public void setAngles(S renderState)
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
			this.arm0.pitch = -2.0F + 0.6F * MathHelper.wrap((float)i, 10.0F);
			this.arm1.pitch = -2.0F + 0.6F * MathHelper.wrap((float)i, 10.0F);
		}
		else
		{
			this.arm0.pitch = (-0.2F + 0.8F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
			this.arm1.pitch = (-0.2F - 0.8F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount;
		}

		this.leg0.pitch = -1.5F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.leg1.pitch = 1.5F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
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