package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.ChestMimicRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ChestMimicModel extends EntityModel<ChestMimicRenderState>
{
	private final ModelPart chestDown;
	private final ModelPart chestUp;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public ChestMimicModel(ModelPart root)
	{
		super(root);
		this.chestDown = root.getChild("chestDown");
		this.chestUp = root.getChild("chestUp");
		this.rightLeg = root.getChild("RightLeg");
		this.leftLeg = root.getChild("LeftLeg");
		}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData chestDown = partdefinition.addChild("chestDown", ModelPartBuilder.create().uv(0, 19).mirrored().cuboid(-8.0F, 3.0F, -7.0F, 16.0F, 10.0F, 16.0F, new Dilation(0.0F)).mirrored(false)
		.uv(76, 50).mirrored().cuboid(-5.0F, 3.0F, -4.0F, 10.0F, 4.0F, 10.0F, new Dilation(0.0F)).mirrored(false)
		.uv(18, 12).mirrored().cuboid(-7.0F, 0.0F, -6.0F, 14.0F, 3.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(2, 0).mirrored().cuboid(7.0F, 1.0F, -7.0F, 0.0F, 3.0F, 15.0F, new Dilation(0.0F)).mirrored(false)
		.uv(32, 0).mirrored().cuboid(-7.0F, 1.0F, -7.0F, 0.0F, 3.0F, 15.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData chestUp = partdefinition.addChild("chestUp", ModelPartBuilder.create().uv(64, 23).mirrored().cuboid(-8.0F, -6.0F, -16.0F, 16.0F, 6.0F, 16.0F, new Dilation(0.0F)).mirrored(false)
		.uv(80, 13).mirrored().cuboid(-8.0F, 0.0F, -15.0F, 16.0F, 3.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(64, 0).mirrored().cuboid(7.0F, 0.0F, -16.0F, 0.0F, 3.0F, 16.0F, new Dilation(0.0F)).mirrored(false)
		.uv(96, 0).mirrored().cuboid(-7.0F, 0.0F, -16.0F, 0.0F, 3.0F, 16.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 0).cuboid(-1.0F, -2.0F, -17.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 3.0F, 9.0F));

		ModelPartData RightLeg = partdefinition.addChild("RightLeg", ModelPartBuilder.create().uv(27, 46).mirrored().cuboid(-8.9F, 0.0F, -2.0F, 6.0F, 12.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
		.uv(124, -2).cuboid(-5.9F, 10.0F, -4.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(1.9F, 12.0F, 0.0F));

		ModelPartData LTalons_r1 = RightLeg.addChild("LTalons_r1", ModelPartBuilder.create().uv(124, -2).cuboid(-4.0F, 10.0F, -3.5F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		ModelPartData RTalons_r1 = RightLeg.addChild("RTalons_r1", ModelPartBuilder.create().uv(124, -2).cuboid(-7.7F, 10.0F, -5.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		ModelPartData LeftLeg = partdefinition.addChild("LeftLeg", ModelPartBuilder.create().uv(0, 46).mirrored().cuboid(-0.9F, 0.0F, -2.0F, 6.0F, 12.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
		.uv(124, -2).cuboid(2.1F, 10.0F, -4.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(1.9F, 12.0F, 0.0F));

		ModelPartData LTalons_r2 = LeftLeg.addChild("LTalons_r2", ModelPartBuilder.create().uv(124, -2).cuboid(4.0F, 10.0F, -4.5F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		ModelPartData RTalons_r2 = LeftLeg.addChild("RTalons_r2", ModelPartBuilder.create().uv(124, -2).cuboid(0.3F, 10.0F, -4.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		return TexturedModelData.of(meshdefinition, 128, 64);
		}

	@Override public void setAngles(ChestMimicRenderState renderState)
	{
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		this.chestUp.pitch = - renderState.mouthOpeningAmplitude * MathHelper.sqrt(1.0F + MathHelper.cos(renderState.age / renderState.mouthOpeningFrequencyMalus * (float) Math.PI));
		this.rightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightLeg.yaw = 0.0F;
		this.leftLeg.yaw = 0.0F;
		this.rightLeg.roll = 0.0F;
		this.leftLeg.roll = 0.0F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		chestDown.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		chestUp.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}