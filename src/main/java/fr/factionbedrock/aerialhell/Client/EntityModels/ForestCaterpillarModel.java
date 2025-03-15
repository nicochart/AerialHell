package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.CaterpillarRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ForestCaterpillarModel<S extends CaterpillarRenderState> extends EntityModel<S>
{
	private final boolean isColored;
	int grassARGB, foliageARGB;
	private final ModelPart head;
	private final ModelPart head_colored;
	private final ModelPart body;
	private final ModelPart body_colored;
	private final ModelPart tail;
	private final ModelPart tail_end;
	private final ModelPart sapling;
	private final ModelPart sapling_colored;

	public ForestCaterpillarModel(ModelPart root, boolean isColored)
	{
        super(root);
        this.isColored = isColored;
		this.head = root.getChild("head");
		this.head_colored = root.getChild("head_colored");
		this.body = root.getChild("body");
		this.body_colored = root.getChild("body_colored");
		this.tail = root.getChild("tail");
		this.tail_end = root.getChild("tail_end");
		this.sapling = root.getChild("sapling");
		this.sapling_colored = root.getChild("sapling_colored");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(5, 58).mirrored().cuboid(-2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
				.uv(8, 55).mirrored().cuboid(-1.0F, -4.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, -3.5F));

		ModelPartData head_colored = partdefinition.addChild("head_colored", ModelPartBuilder.create().uv(5, 26).mirrored().cuboid(-2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
				.uv(8, 23).mirrored().cuboid(-1.0F, -4.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, -3.5F));

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(17, 46).mirrored().cuboid(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(11, 56).mirrored().cuboid(-5.0F, 0.0F, -4.5F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(21, 39).mirrored().cuboid(-1.0F, -5.0F, -3.5F, 2.0F, 1.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(25, 36).mirrored().cuboid(-1.0F, -6.0F, -1.5F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body_colored = partdefinition.addChild("body_colored", ModelPartBuilder.create().uv(17, 14).mirrored().cuboid(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(11, 24).mirrored().cuboid(-5.0F, 0.0F, -4.5F, 10.0F, 0.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(21, 7).mirrored().cuboid(-1.0F, -5.0F, -3.5F, 2.0F, 1.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(25, 4).mirrored().cuboid(-1.0F, -6.0F, -1.5F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData tail = partdefinition.addChild("tail", ModelPartBuilder.create().uv(39, 27).mirrored().cuboid(-1.5F, -3.0F, -0.5F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 3.0F));

		ModelPartData tail_end = partdefinition.addChild("tail_end", ModelPartBuilder.create().uv(41, 23).mirrored().cuboid(-0.5F, -2.0F, 0.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 4.0F));

		ModelPartData sapling = partdefinition.addChild("sapling", ModelPartBuilder.create().uv(45, 39).mirrored().cuboid(0.0F, -11.0F, -3.5F, 0.0F, 5.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(47, 40).mirrored().cuboid(-2.0F, -11.0F, -0.5F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData sapling_colored = partdefinition.addChild("sapling_colored", ModelPartBuilder.create().uv(45, 7).mirrored().cuboid(0.0F, -11.0F, -3.5F, 0.0F, 5.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(47, 8).mirrored().cuboid(-2.0F, -11.0F, -0.5F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(S renderState)
	{
		grassARGB = renderState.grassARGB;
		foliageARGB = renderState.foliageARGB;
		float ageInTicks = renderState.age;

		this.body.yaw = MathHelper.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
		this.body_colored.yaw = MathHelper.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
		this.sapling.yaw = MathHelper.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
		this.sapling_colored.yaw = MathHelper.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
		this.tail.yaw = MathHelper.cos(ageInTicks/2 * 0.9F + (float)2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(2 - 2));
		this.tail_end.yaw = MathHelper.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(3 - 2));
		this.head.yaw = MathHelper.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(0 - 2));
		this.head_colored.yaw = MathHelper.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(0 - 2));
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tintIn)
	{
		int tint = isColored ? grassARGB : tintIn;

		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head_colored.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body_colored.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tail_end.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		sapling.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		if (isColored) {tint = foliageARGB;}
		sapling_colored.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}