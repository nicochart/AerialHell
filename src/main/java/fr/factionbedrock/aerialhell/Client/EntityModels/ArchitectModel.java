package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ArchitectRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ArchitectModel extends EntityModel<ArchitectRenderState>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public ArchitectModel(ModelPart root)
	{
		super(root);
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 54).addBox(-33.0F, -102.0F, -32.0F, 64.0F, 64.0F, 64.0F, new CubeDeformation(0.0F))
				.texOffs(64, 182).addBox(-17.0F, -38.0F, -16.0F, 32.0F, 16.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(64, 182).addBox(-17.0F, -118.0F, -16.0F, 32.0F, 16.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(288, 352).addBox(-17.0F, 74.0F, -16.0F, 32.0F, 16.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(160, 224).addBox(-49.0F, 42.0F, -48.0F, 96.0F, 32.0F, 96.0F, new CubeDeformation(0.0F))
				.texOffs(192, 400).addBox(-41.0F, 90.0F, -40.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
				.texOffs(192, 0).addBox(-1.0F, -22.0F, -80.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
				.texOffs(192, 0).addBox(-81.0F, -22.0F, -80.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
				.texOffs(192, 0).addBox(-81.0F, -22.0F, 0.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
				.texOffs(192, 0).addBox(-1.0F, -22.0F, 0.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
				.texOffs(512, 400).addBox(-1.0F, -150.0F, -80.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
				.texOffs(512, 400).addBox(-81.0F, -150.0F, -80.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
				.texOffs(512, 400).addBox(-81.0F, -150.0F, 0.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
				.texOffs(512, 400).addBox(-1.0F, -150.0F, 0.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
				.texOffs(704, 193).addBox(-33.0F, -214.0F, -32.0F, 64.0F, 64.0F, 64.0F, new CubeDeformation(0.0F))
				.texOffs(224, 128).addBox(-1.0F, 10.0F, -64.0F, 64.0F, 32.0F, 64.0F, new CubeDeformation(0.0F))
				.texOffs(224, 128).addBox(-65.0F, 10.0F, -64.0F, 64.0F, 32.0F, 64.0F, new CubeDeformation(0.0F))
				.texOffs(224, 128).addBox(-65.0F, 10.0F, 0.0F, 64.0F, 32.0F, 64.0F, new CubeDeformation(0.0F))
				.texOffs(224, 128).addBox(-1.0F, 10.0F, 0.0F, 64.0F, 32.0F, 64.0F, new CubeDeformation(0.0F))
				.texOffs(834, 384).addBox(-81.0F, -118.0F, -80.0F, 32.0F, 96.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(834, 384).addBox(47.0F, -118.0F, -80.0F, 32.0F, 96.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(834, 384).addBox(47.0F, -118.0F, 48.0F, 32.0F, 96.0F, 32.0F, new CubeDeformation(0.0F))
				.texOffs(834, 384).addBox(-81.0F, -118.0F, 48.0F, 32.0F, 96.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -250.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(640, 1).addBox(-49.0F, -65.0F, -46.0F, 96.0F, 96.0F, 96.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -494.0F, -2.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(483, 196).addBox(-65.1131F, -125.0189F, -26.1442F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(-113.1131F, -109.0189F, -10.1442F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(-81.1131F, -77.0189F, -26.1442F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(-129.1131F, -61.0189F, -10.1442F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(-97.1131F, -29.0189F, -26.1442F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(-145.1131F, -13.0189F, -10.1442F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(-97.1131F, 18.9811F, -26.1442F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(-145.1131F, 34.9811F, -10.1442F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(-113.1131F, 66.9811F, -26.1442F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(-161.1131F, 82.9811F, -10.1442F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(-113.1131F, 114.9811F, -26.1442F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(-161.1131F, 130.9811F, -10.1442F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(-161.1131F, 178.9811F, -10.1442F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(-113.1131F, 162.9811F, -26.1442F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(-62.8869F, -274.9811F, 2.1442F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(499, 164).addBox(38.0644F, -109.0181F, -10.1278F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(-9.9356F, -125.0181F, -26.1278F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(54.0644F, -61.0181F, -10.1278F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(6.0644F, -77.0181F, -26.1278F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(70.0644F, -13.0181F, -10.1278F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(22.0644F, -29.0181F, -26.1278F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(70.0644F, 34.9819F, -10.1278F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(22.0644F, 18.9819F, -26.1278F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(86.0644F, 82.9819F, -10.1278F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(38.0644F, 66.9819F, -26.1278F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(86.0644F, 130.9819F, -10.1278F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(38.0644F, 114.9819F, -26.1278F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
				.texOffs(499, 164).addBox(86.0644F, 178.9819F, -10.1278F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(483, 196).addBox(38.0644F, 162.9819F, -26.1278F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(89.9356F, -274.9819F, 2.1278F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 304).addBox(11.0F, 26.0F, -24.0F, 48.0F, 160.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -162.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 304).addBox(-60.0F, 26.0F, -24.0F, 48.0F, 160.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -162.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 1024, 512);
	}

	@Override public void setupAnim(ArchitectRenderState renderState)
	{
		float headPitch = renderState.xRot;
		float netHeadYaw = renderState.yRot;
		float limbSwing = renderState.walkAnimationPos;
		float limbSwingAmount = renderState.walkAnimationSpeed;

		this.head.yRot = netHeadYaw / 57.29578F;
		this.head.xRot = headPitch / 57.29578F;

		this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;

		this.leftLeg.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.yRot = 0.0F;
		this.rightLeg.yRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}