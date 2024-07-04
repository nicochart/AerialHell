package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;

import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.Neutral.BoarEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class BoarModel<T extends BoarEntity> extends EntityModel<T>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart tusk;
	private final ModelPart backLeftLeg;
	private final ModelPart backRightLeg;
	private final ModelPart frontLeftLeg;
	private final ModelPart frontRightLeg;

	private boolean isChild;

	public BoarModel(ModelPart root)
	{
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.tusk = root.getChild("tusk");
		this.backLeftLeg = root.getChild("backLeftLeg");
		this.backRightLeg = root.getChild("backRightLeg");
		this.frontLeftLeg = root.getChild("frontLeftLeg");
		this.frontRightLeg = root.getChild("frontRightLeg");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 40).addBox(-5.0F, -1.0F, -9.0F, 10.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(42, 40).addBox(5.0F, 1.0F, -9.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(42, 28).addBox(5.0F, 1.0F, 1.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 2.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(42, 40).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 4.0F, -6.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition body_r2 = body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(42, 28).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 4.0F, 4.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 9).addBox(-4.0F, -3.0F, -7.0F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(30, 15).addBox(-2.0F, 0.0F, -11.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, -5.0F));

		PartDefinition rightEar_r1 = head.addOrReplaceChild("rightEar_r1", CubeListBuilder.create().texOffs(35, 4).addBox(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -3.0F, -3.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leftEar_r1 = head.addOrReplaceChild("leftEar_r1", CubeListBuilder.create().texOffs(35, 4).addBox(0.0F, -4.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.0F, -3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tusk = partdefinition.addOrReplaceChild("tusk", CubeListBuilder.create().texOffs(50, 7).addBox(3.0F, 1.0F, -10.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(50, 11).addBox(2.0F, 3.0F, -10.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, -5.0F));

		PartDefinition tusk_r1 = tusk.addOrReplaceChild("tusk_r1", CubeListBuilder.create().texOffs(50, 11).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 3.5F, -9.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tusk_r2 = tusk.addOrReplaceChild("tusk_r2", CubeListBuilder.create().texOffs(50, 7).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 2.5F, -9.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition backLeftLeg = partdefinition.addOrReplaceChild("backLeftLeg", CubeListBuilder.create().texOffs(18, 28).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 18.0F, 7.0F));

		PartDefinition backRightLeg = partdefinition.addOrReplaceChild("backRightLeg", CubeListBuilder.create().texOffs(18, 28).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 18.0F, 7.0F));

		PartDefinition frontLeftLeg = partdefinition.addOrReplaceChild("frontLeftLeg", CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 18.0F, -5.0F));

		PartDefinition frontRightLeg = partdefinition.addOrReplaceChild("frontRightLeg", CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 18.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.isChild = entity.isBaby();

		this.head.xRot = headPitch * ((float)Math.PI / 180F); this.tusk.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F); this.tusk.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.backRightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.backLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.frontRightLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.frontLeftLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		if(isChild)
		{
			poseStack.scale(0.5F, 0.5F, 0.5F);
			poseStack.translate(0.0F, 1.5F, 0.0F);
		}
		poseStack.pushPose();

		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		backLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		backRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		frontLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		frontRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);

		if (!isChild)
		{
			tusk.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		}
		poseStack.popPose();
	}
}