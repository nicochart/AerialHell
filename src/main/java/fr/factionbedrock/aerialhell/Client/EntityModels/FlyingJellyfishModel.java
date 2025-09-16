package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.FlyingJellyfishRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class FlyingJellyfishModel extends EntityModel<FlyingJellyfishRenderState>
{
	private final ModelPart tentacles_0;
	private final ModelPart tentacles_1;
	private final ModelPart tentacles_2;
	private final ModelPart tentacles_3;
	private final ModelPart tentacles_4;
	private final ModelPart tentacles_5;
	private final ModelPart tentacles_6;
	private final ModelPart body;

	public FlyingJellyfishModel(ModelPart root)
	{
		super(root);
		this.tentacles_0 = root.getChild("tentacles_0");
		this.tentacles_1 = root.getChild("tentacles_1");
		this.tentacles_2 = root.getChild("tentacles_2");
		this.tentacles_3 = root.getChild("tentacles_3");
		this.tentacles_4 = root.getChild("tentacles_4");
		this.tentacles_5 = root.getChild("tentacles_5");
		this.tentacles_6 = root.getChild("tentacles_6");
		this.body = root.getChild("body");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData tentacles_0 = partdefinition.addChild("tentacles_0", ModelPartBuilder.create().uv(4, 0).mirrored().cuboid(-3.0F, -1.0F, 3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(3.8F, 23.0F, -5.0F));

		ModelPartData tentacles_1 = partdefinition.addChild("tentacles_1", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-1.0F, -1.0F, 3.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-1.3F, 23.0F, -5.0F));

		ModelPartData tentacles_2 = partdefinition.addChild("tentacles_2", ModelPartBuilder.create().uv(8, 0).mirrored().cuboid(5.0F, -1.0F, 4.8F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-6.3F, 23.0F, -5.0F));

		ModelPartData tentacles_3 = partdefinition.addChild("tentacles_3", ModelPartBuilder.create().uv(4, 8).mirrored().cuboid(-5.0F, -1.0F, 0.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(6.3F, 23.0F, 0.0F));

		ModelPartData tentacles_4 = partdefinition.addChild("tentacles_4", ModelPartBuilder.create().uv(0, 9).mirrored().cuboid(-1.2F, -1.0F, 1.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(1.3F, 23.0F, 0.0F));

		ModelPartData tentacles_5 = partdefinition.addChild("tentacles_5", ModelPartBuilder.create().uv(0, 9).mirrored().cuboid(1.0F, -1.0F, 0.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-3.8F, 23.0F, 0.0F));

		ModelPartData tentacles_6 = partdefinition.addChild("tentacles_6", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(0.0F, -1.0F, -3.35F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-1.3F, 23.0F, 5.0F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(8, 0).mirrored().cuboid(-3.0F, 2.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(26, 1).mirrored().cuboid(3.0F, 4.0F, 2.2F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(24, 12).mirrored().cuboid(3.0F, 2.0F, 2.6F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(28, 2).mirrored().cuboid(-5.0F, 4.0F, 2.0F, 2.0F, 3.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(28, 12).mirrored().cuboid(-5.0F, 2.0F, 2.64F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(8, 14).mirrored().cuboid(-4.0F, 0.0F, 2.64F, 8.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, 16.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 32, 16);
	}

	@Override public void setAngles(FlyingJellyfishRenderState renderState)
	{
		float ageInTicks = renderState.age;
		this.tentacles_0.pitch = 0.1F * MathHelper.sin(ageInTicks * 0.3F + 0.0F) + 0.4F;
		this.tentacles_1.pitch = 0.1F * MathHelper.sin(ageInTicks * 0.3F + 1.0F) + 0.4F;
		this.tentacles_2.pitch = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 2.0F) + 0.4F;
		this.tentacles_3.pitch = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 3.0F) + 0.4F;
		this.tentacles_4.pitch = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 4.0F) + 0.4F;
		this.tentacles_5.pitch = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 5.0F) + 0.4F;
		this.tentacles_6.pitch = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 6.0F) + 0.4F;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		tentacles_0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tentacles_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tentacles_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tentacles_3.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tentacles_4.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tentacles_5.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tentacles_6.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}