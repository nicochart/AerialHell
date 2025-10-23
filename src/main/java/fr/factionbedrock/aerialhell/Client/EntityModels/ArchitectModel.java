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

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(102, 52).mirror().addBox(-6.0F, -29.0F, -4.0F, 10.0F, 13.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(81, 180).mirror().addBox(-12.0F, 45.0F, -8.0F, 22.0F, 10.0F, 18.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(69, 149).mirror().addBox(-15.0F, 29.0F, -11.0F, 28.0F, 7.0F, 24.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(61, 114).mirror().addBox(-17.0F, 21.0F, -13.0F, 32.0F, 7.0F, 28.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(69, 74).mirror().addBox(-11.0F, 13.0F, -15.0F, 20.0F, 7.0F, 32.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(54, 82).mirror().addBox(10.0F, 13.0F, -7.0F, 7.0F, 7.0F, 16.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(178, 33).mirror().addBox(10.0F, -16.0F, -7.0F, 7.0F, 7.0F, 16.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(54, 82).mirror().addBox(-19.0F, 13.0F, -7.0F, 7.0F, 7.0F, 16.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(178, 33).mirror().addBox(-19.0F, -16.0F, -7.0F, 7.0F, 7.0F, 16.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(152, 57).mirror().addBox(-11.0F, -16.0F, -15.0F, 20.0F, 7.0F, 32.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(227, 45).mirror().addBox(-19.0F, -16.0F, -15.0F, 7.0F, 36.0F, 7.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(227, 45).mirror().addBox(10.0F, -16.0F, -15.0F, 7.0F, 36.0F, 7.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(227, 45).mirror().addBox(-19.0F, -16.0F, 10.0F, 7.0F, 36.0F, 7.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(227, 45).mirror().addBox(10.0F, -16.0F, 10.0F, 7.0F, 36.0F, 7.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(4, 31).mirror().addBox(-9.0F, -6.0F, -7.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(24, 63).mirror().addBox(-4.0F, 11.0F, -2.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(24, 63).mirror().addBox(-4.0F, -8.0F, -2.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false)
		.texOffs(144, 181).mirror().addBox(-6.0F, 37.0F, -4.0F, 10.0F, 7.0F, 9.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(1.0F, -66.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(81, 23).mirror().addBox(-11.0F, -18.0F, -7.5F, 20.0F, 17.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(93, 4).mirror().addBox(-8.0F, -23.0F, -4.5F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, -94.0F, -2.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(12, 98).addBox(-50.1131F, 8.9811F, -7.1442F, 10.0F, 41.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(21, 214).addBox(-56.1131F, 16.9811F, -3.1442F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(21, 214).addBox(-56.1131F, 26.9811F, -3.1442F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(17, 223).addBox(-60.1131F, 36.9811F, -3.1442F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(17, 223).addBox(-60.1131F, 46.9811F, -3.1442F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(17, 223).addBox(-64.1131F, 56.9811F, -3.1442F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(17, 223).addBox(-64.1131F, 66.9811F, -3.1442F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(17, 223).addBox(-64.1131F, 76.9811F, -3.1442F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(21, 214).addBox(-60.1131F, 86.9811F, -3.1442F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(12, 151).addBox(-54.1131F, 49.9811F, -7.1442F, 10.0F, 43.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(22.1131F, -90.9811F, 2.1442F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(188, 98).mirror().addBox(38.0644F, 8.9819F, -7.1278F, 10.0F, 41.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(198, 214).mirror().addBox(47.0644F, 16.9819F, -3.1278F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(198, 214).mirror().addBox(47.0644F, 26.9819F, -3.1278F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(194, 223).mirror().addBox(47.0644F, 36.9819F, -3.1278F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(194, 223).mirror().addBox(47.0644F, 46.9819F, -3.1278F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(194, 223).mirror().addBox(52.0644F, 56.9819F, -3.1278F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(194, 223).mirror().addBox(52.0644F, 66.9819F, -3.1278F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(194, 223).mirror().addBox(52.0644F, 76.9819F, -3.1278F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(198, 214).mirror().addBox(52.0644F, 86.9819F, -3.1278F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(188, 150).mirror().addBox(42.0644F, 49.9819F, -7.1278F, 10.0F, 43.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-20.0644F, -90.9819F, 2.1278F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(122, 208).mirror().addBox(-1.0F, 35.0F, -5.0F, 10.0F, 37.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -48.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(78, 208).addBox(-10.0F, 35.0F, -5.0F, 10.0F, 37.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -48.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
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