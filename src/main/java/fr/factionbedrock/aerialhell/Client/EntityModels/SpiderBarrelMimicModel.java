package fr.factionbedrock.aerialhell.Client.EntityModels;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// Base model made by Cixon with Blockbench
// Edited in-ide by Nicochart to mimic vanilla animation
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class SpiderBarrelMimicModel extends EntityModel<LivingEntityRenderState>
{
	private final ModelPart body0;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart leg5;
	private final ModelPart leg6;
	private final ModelPart leg7;

	public SpiderBarrelMimicModel(ModelPart root)
	{
		super(root);
		this.body0 = root.getChild("body0");
		this.leg0 = root.getChild("leg0");
		this.leg1 = root.getChild("leg1");
		this.leg2 = root.getChild("leg2");
		this.leg3 = root.getChild("leg3");
		this.leg4 = root.getChild("leg4");
		this.leg5 = root.getChild("leg5");
		this.leg6 = root.getChild("leg6");
		this.leg7 = root.getChild("leg7");
	}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body0 = partdefinition.addChild("body0", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 15.0F, 9.0F));

		ModelPartData teethbottom_r1 = body0.addChild("teethbottom_r1", ModelPartBuilder.create().uv(19, 10).cuboid(-6.0F, -11.0F, -16.0F, 12.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6283F, 0.0F, 0.0F));

		ModelPartData teethtop_r1 = body0.addChild("teethtop_r1", ModelPartBuilder.create().uv(19, 2).cuboid(-6.0F, 2.0F, -17.0F, 12.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6283F, 0.0F, 0.0F));

		ModelPartData main_r1 = body0.addChild("main_r1", ModelPartBuilder.create().uv(0, 32).cuboid(-8.0F, -9.0F, 2.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.0F, -9.0F, 1.3963F, 0.0F, 0.0F));

		ModelPartData leg0 = partdefinition.addChild("leg0", ModelPartBuilder.create().uv(13, 17).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(4.0F, 15.0F, 2.0F));

		ModelPartData leg1 = partdefinition.addChild("leg1", ModelPartBuilder.create().uv(13, 17).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-4.0F, 15.0F, 2.0F));

		ModelPartData leg2 = partdefinition.addChild("leg2", ModelPartBuilder.create().uv(13, 17).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(4.0F, 15.0F, 1.0F));

		ModelPartData leg3 = partdefinition.addChild("leg3", ModelPartBuilder.create().uv(13, 17).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-4.0F, 15.0F, 1.0F));

		ModelPartData leg4 = partdefinition.addChild("leg4", ModelPartBuilder.create().uv(13, 17).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(4.0F, 15.0F, 0.0F));

		ModelPartData leg5 = partdefinition.addChild("leg5", ModelPartBuilder.create().uv(13, 17).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-4.0F, 15.0F, 0.0F));

		ModelPartData leg6 = partdefinition.addChild("leg6", ModelPartBuilder.create().uv(13, 17).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(4.0F, 15.0F, -1.0F));

		ModelPartData leg7 = partdefinition.addChild("leg7", ModelPartBuilder.create().uv(13, 17).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-4.0F, 15.0F, -1.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override public void setAngles(LivingEntityRenderState renderState)
	{
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;

		leg0.roll = ((float)Math.PI / 4F); leg0.yaw = ((float)Math.PI / 4F);
		leg1.roll = (-(float)Math.PI / 4F); leg1.yaw = (-(float)Math.PI / 4F);
		leg2.roll = 0.58119464F; leg2.yaw = ((float)Math.PI / 8F);
		leg3.roll = -0.58119464F; leg3.yaw = (-(float)Math.PI / 8F);
		leg4.roll = 0.58119464F; leg4.yaw = (-(float)Math.PI / 8F);
		leg5.roll = -0.58119464F; leg5.yaw = ((float)Math.PI / 8F);
		leg6.roll = ((float)Math.PI / 4F); leg6.yaw = (-(float)Math.PI / 4F);
		leg7.roll = -((float)Math.PI / 4F); leg7.yaw = ((float)Math.PI / 4F);
		float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
		float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
		float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
		float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
		float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
		float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
		float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		leg0.yaw += f3;
		leg1.yaw += -f3;
		leg2.yaw += f4;
		leg3.yaw += -f4;
		leg4.yaw += f5;
		leg5.yaw += -f5;
		leg6.yaw += f6;
		leg7.yaw += -f6;
		leg0.roll += f7;
		leg1.roll += -f7;
		leg2.roll += f8;
		leg3.roll += -f8;
		leg4.roll += f9;
		leg5.roll += -f9;
		leg6.roll += f10;
		leg7.roll += -f10;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		body0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg5.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg6.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg7.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}