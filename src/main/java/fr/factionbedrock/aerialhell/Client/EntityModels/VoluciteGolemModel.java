package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialHellGolemRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class VoluciteGolemModel extends EntityModel<AerialHellGolemRenderState>
{
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart block;
	private final ModelPart block2;
	private final ModelPart block3;
	private final ModelPart block4;
	private final ModelPart block5;
	private final ModelPart left_arm;
	private final ModelPart block6;
	private final ModelPart block7;
	private final ModelPart block8;
	private final ModelPart block9;
	private final ModelPart block10;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public VoluciteGolemModel(ModelPart root)
	{
        super(root);
        this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.block = this.right_arm.getChild("block");
		this.block2 = this.right_arm.getChild("block2");
		this.block3 = this.right_arm.getChild("block3");
		this.block4 = this.right_arm.getChild("block4");
		this.block5 = this.right_arm.getChild("block5");
		this.left_arm = root.getChild("left_arm");
		this.block6 = this.left_arm.getChild("block6");
		this.block7 = this.left_arm.getChild("block7");
		this.block8 = this.left_arm.getChild("block8");
		this.block9 = this.left_arm.getChild("block9");
		this.block10 = this.left_arm.getChild("block10");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(44, 42).addBox(-5.0F, -8.0F, -4.5F, 10.0F, 8.0F, 8.0F, new CubeDeformation(-0.2F))
		.texOffs(52, 35).addBox(-3.0F, -10.0F, -2.5F, 6.0F, 3.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, -10.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(35, 66).addBox(-7.0F, -32.0F, -7.0F, 14.0F, 10.0F, 13.0F, new CubeDeformation(-1.0F))
		.texOffs(45, 95).addBox(-4.5F, -21.0F, -4.5F, 9.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(53, 89).addBox(-2.5F, -23.0F, -2.5F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(53, 58).addBox(-2.5F, -35.0F, -2.5F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(36, 66).addBox(-8.0F, -31.0F, -2.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(76, 66).addBox(6.0F, -31.0F, -2.5F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(31, 12).mirror().addBox(-4.5F, 3.5F, -2.5F, 4.0F, 23.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(31, 7).mirror().addBox(-4.0F, -0.5F, -2.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, -6.0F, 0.0F));

		PartDefinition block = right_arm.addOrReplaceChild("block", CubeListBuilder.create().texOffs(10, 11).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 16).mirror().addBox(-6.0F, -1.5F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 3.0F, 0.0F));

		PartDefinition block2 = right_arm.addOrReplaceChild("block2", CubeListBuilder.create().texOffs(10, 20).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 25).mirror().addBox(-7.0F, -1.5F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 8.0F, 0.0F));

		PartDefinition block3 = right_arm.addOrReplaceChild("block3", CubeListBuilder.create().texOffs(10, 29).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 34).mirror().addBox(-8.0F, -1.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 13.0F, 0.0F));

		PartDefinition block4 = right_arm.addOrReplaceChild("block4", CubeListBuilder.create().texOffs(10, 38).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 43).mirror().addBox(-8.0F, -1.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 18.0F, 0.0F));

		PartDefinition block5 = right_arm.addOrReplaceChild("block5", CubeListBuilder.create().texOffs(10, 47).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 52).mirror().addBox(-7.0F, -1.5F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 23.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(81, 12).mirror().addBox(0.5F, 3.5F, -2.5F, 4.0F, 23.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(81, 7).mirror().addBox(0.0F, -0.5F, -2.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, -6.0F, 0.0F));

		PartDefinition block6 = left_arm.addOrReplaceChild("block6", CubeListBuilder.create().texOffs(98, 11).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(118, 16).mirror().addBox(1.0F, -1.5F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 3.0F, 0.0F));

		PartDefinition block7 = left_arm.addOrReplaceChild("block7", CubeListBuilder.create().texOffs(98, 20).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(118, 25).mirror().addBox(1.0F, -1.5F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 8.0F, 0.0F));

		PartDefinition block8 = left_arm.addOrReplaceChild("block8", CubeListBuilder.create().texOffs(98, 29).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(118, 34).mirror().addBox(1.0F, -1.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 13.0F, 0.0F));

		PartDefinition block9 = left_arm.addOrReplaceChild("block9", CubeListBuilder.create().texOffs(98, 38).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(118, 43).mirror().addBox(1.0F, -1.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 18.0F, 0.0F));

		PartDefinition block10 = left_arm.addOrReplaceChild("block10", CubeListBuilder.create().texOffs(98, 47).mirror().addBox(-4.0F, -3.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(118, 52).mirror().addBox(1.0F, -1.5F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 23.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(63, 108).mirror().addBox(-1.5F, 0.0F, -2.5F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(79, 119).mirror().addBox(-2.0F, 11.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(79, 110).mirror().addBox(-2.0F, 6.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(79, 101).mirror().addBox(-2.0F, 1.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 8.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(45, 108).addBox(-2.5F, 0.0F, -2.5F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(25, 119).addBox(-3.0F, 11.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(25, 110).addBox(-3.0F, 6.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(25, 101).addBox(-3.0F, 1.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 8.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override public void setupAnim(AerialHellGolemRenderState renderState)
	{
		float headPitch = renderState.xRot;
		float netHeadYaw = renderState.yRot;
		float limbSwing = renderState.walkAnimationPos;
		float limbSwingAmount = renderState.walkAnimationSpeed;

		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		int i = renderState.attackTimer;
		if (i > 0)
		{
			this.left_arm.xRot = -2.0F + 0.6F * Mth.triangleWave((float)i, 10.0F);
			this.right_arm.xRot = -2.0F + 0.6F * Mth.triangleWave((float)i, 10.0F);
		}
		else
		{
			this.left_arm.xRot = (-0.2F + 0.8F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
			this.right_arm.xRot = (-0.2F - 0.8F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		}

		this.left_leg.xRot = -1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.right_leg.xRot = 1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.left_leg.yRot = 0.0F; this.right_leg.yRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}