package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowFlyingSkullRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made by Cixon with Blockbench
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ShadowFlyingSkullModel extends EntityModel<ShadowFlyingSkullRenderState>
{
	private final ModelPart skull;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart jaw;

	public ShadowFlyingSkullModel(ModelPart root)
	{
		super(root);
		this.skull = root.getChild("skull");
		this.leftWing = root.getChild("leftWing");
		this.rightWing = root.getChild("rightWing");
		this.jaw = root.getChild("jaw");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData skull = partdefinition.addChild("skull", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -9.0F, -1.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 24.0F, -3.0F));

		ModelPartData leftWing = partdefinition.addChild("leftWing", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 19.0F, 0.0F));

		ModelPartData wing_r1 = leftWing.addChild("wing_r1", ModelPartBuilder.create().uv(48, 25).cuboid(-0.3F, -3.0F, 0.0F, 6.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		ModelPartData bone_r1 = leftWing.addChild("bone_r1", ModelPartBuilder.create().uv(48, 15).cuboid(-2.0F, -2.0F, 0.0F, 7.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

		ModelPartData rightWing = partdefinition.addChild("rightWing", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 19.0F, 0.0F));

		ModelPartData wing_r2 = rightWing.addChild("wing_r2", ModelPartBuilder.create().uv(48, 25).mirrored().cuboid(-5.7F, -3.0F, 0.0F, 6.0F, 8.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

		ModelPartData bone_r2 = rightWing.addChild("bone_r2", ModelPartBuilder.create().uv(48, 15).mirrored().cuboid(-5.0F, -2.0F, 0.0F, 7.0F, 3.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		ModelPartData jaw = partdefinition.addChild("jaw", ModelPartBuilder.create().uv(0, 18).cuboid(-4.0F, 0.0F, -5.0F, 8.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 1.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(ShadowFlyingSkullRenderState renderState)
	{
		this.jaw.pitch = renderState.jawOpeningAmplitude * MathHelper.sqrt(1.0F + MathHelper.cos(renderState.age / renderState.jawOpeningFrequencyMalus * 3.2F));
		this.rightWing.yaw = 0.47123894F + MathHelper.cos(renderState.age * 0.8F) * 0.3F;
		this.leftWing.yaw = -this.rightWing.yaw;
		this.leftWing.roll = -0.47123894F;
		this.leftWing.pitch = 0.47123894F;
		this.rightWing.pitch = 0.47123894F;
		this.rightWing.roll = 0.47123894F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		skull.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		jaw.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}