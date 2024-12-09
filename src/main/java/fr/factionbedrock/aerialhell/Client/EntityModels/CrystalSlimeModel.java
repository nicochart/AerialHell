package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Entity.Monster.CrystalSlimeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings


public class CrystalSlimeModel extends EntityModel<CrystalSlimeEntity>
{
	private final ModelPart cube;
	private final ModelPart eye0;
	private final ModelPart eye1;
	private final ModelPart mouth;
	private final ModelPart crystal;

	private final boolean isGelAndCrystal;

	public CrystalSlimeModel(ModelPart root, boolean isGelAndCrystal)
	{
		this.cube = root.getChild("cube");
		this.eye0 = root.getChild("eye0");
		this.eye1 = root.getChild("eye1");
		this.mouth = root.getChild("mouth");
		this.crystal = root.getChild("crystal");
		this.isGelAndCrystal = isGelAndCrystal;
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData cube = partdefinition.addChild("cube", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData crystal = partdefinition.addChild("crystal", ModelPartBuilder.create().uv(0, 17).mirrored().cuboid(-4.0F, 19.0F, -1.0F, 6.0F, 7.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 19).mirrored().cuboid(-1.0F, 19.0F, -4.0F, 0.0F, 7.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(14, 21).mirrored().cuboid(2.0F, 21.0F, 0.0F, 0.0F, 5.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(14, 19).mirrored().cuboid(0.0F, 21.0F, 2.0F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(40, 13).mirrored().cuboid(2.0F, 25.0F, 1.0F, 0.0F, 4.0F, 7.0F, new Dilation(0.0F)).mirrored(false)
				.uv(54, 20).mirrored().cuboid(-2.0F, 26.0F, 2.0F, 0.0F, 4.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
				.uv(50, 19).mirrored().cuboid(-4.0F, 28.0F, 2.0F, 4.0F, 0.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
				.uv(36, 24).mirrored().cuboid(0.0F, 27.0F, 1.0F, 4.0F, 0.0F, 7.0F, new Dilation(0.0F)).mirrored(false)
				.uv(24, 18).mirrored().cuboid(2.0F, 25.0F, 0.0F, 7.0F, 6.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(18, 25).mirrored().cuboid(2.0F, 28.0F, -3.0F, 7.0F, 0.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(37, 6).mirrored().cuboid(-8.0F, 28.0F, -2.0F, 6.0F, 0.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
				.uv(52, 13).mirrored().cuboid(-7.0F, 30.0F, -3.0F, 4.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
				.uv(55, 9).mirrored().cuboid(-7.0F, 29.0F, -2.0F, 4.0F, 3.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
				.uv(42, 12).mirrored().cuboid(-8.0F, 26.0F, 1.0F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

		ModelPartData eye0 = partdefinition.addChild("eye0", ModelPartBuilder.create().uv(33, 0).mirrored().cuboid(1.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData eye1 = partdefinition.addChild("eye1", ModelPartBuilder.create().uv(33, 5).mirrored().cuboid(-3.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData mouth = partdefinition.addChild("mouth", ModelPartBuilder.create().uv(35, 10).mirrored().cuboid(-1.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 32);
	}

	@Override public void setAngles(CrystalSlimeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		if (isGelAndCrystal)
		{
			cube.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			crystal.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		}
		else
		{
			eye0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			eye1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
			mouth.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		}
	}
}