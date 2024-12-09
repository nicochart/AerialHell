package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class CortinariusCowShroomModel<T extends EvilCowEntity> extends EntityModel<T>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;

	public CortinariusCowShroomModel(ModelPart root)
	{
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.leg0 = root.getChild("leg0");
		this.leg1 = root.getChild("leg1");
		this.leg2 = root.getChild("leg2");
		this.leg3 = root.getChild("leg3");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, 2.0F));

		ModelPartData shroom_r1 = body.addChild("shroom_r1", ModelPartBuilder.create().uv(28, 18).mirrored().cuboid(4.0F, 1.0F, -9.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(28, 24).mirrored().cuboid(4.0F, -10.0F, -6.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(26, 10).mirrored().cuboid(-7.0F, -10.0F, -6.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(4, 24).mirrored().cuboid(-7.0F, 6.5F, -5.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(24, 0).mirrored().cuboid(2.0F, 5.5F, -2.0F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 12).mirrored().cuboid(-7.0F, -1.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 0).mirrored().cuboid(2.0F, -6.0F, -2.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -1.0F, -2.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 4.0F, -8.0F));

		ModelPartData leg0 = partdefinition.addChild("leg0", ModelPartBuilder.create().uv(45, 12).mirrored().cuboid(0.0F, 2.0F, 0.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 12.0F, 7.0F));

		ModelPartData leg1 = partdefinition.addChild("leg1", ModelPartBuilder.create().uv(45, 18).mirrored().cuboid(-3.0F, 5.0F, 0.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.0F, 12.0F, 7.0F));

		ModelPartData leg2 = partdefinition.addChild("leg2", ModelPartBuilder.create().uv(45, 0).mirrored().cuboid(0.0F, 6.0F, -2.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, 12.0F, -6.0F));

		ModelPartData leg3 = partdefinition.addChild("leg3", ModelPartBuilder.create().uv(45, 6).mirrored().cuboid(-3.0F, 2.0F, -2.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.0F, 12.0F, -6.0F));

		return TexturedModelData.of(meshdefinition, 64, 32);
		}

	@Override public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.leg1.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg0.pitch = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.pitch = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg2.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}