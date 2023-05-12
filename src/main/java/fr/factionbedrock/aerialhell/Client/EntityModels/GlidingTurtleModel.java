package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.Passive.GlidingTurtleEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made by Cixon with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class GlidingTurtleModel extends EntityModel<GlidingTurtleEntity>
{
	private final ModelPart Head;
	private final ModelPart Body;
	private final ModelPart WingRight;
	private final ModelPart WingLeft;
	private final ModelPart LegFrontRight;
	private final ModelPart LegFrontLeft;
	private final ModelPart LegBackRight;
	private final ModelPart LegBackLeft;

	private boolean isGliding;
	private boolean isChild;

	public GlidingTurtleModel(ModelPart root)
	{
		this.Head = root.getChild("Head");
		this.Body = root.getChild("Body");
		this.WingRight = root.getChild("WingRight");
		this.WingLeft = root.getChild("WingLeft");
		this.LegFrontRight = root.getChild("LegFrontRight");
		this.LegFrontLeft = root.getChild("LegFrontLeft");
		this.LegBackRight = root.getChild("LegBackRight");
		this.LegBackLeft = root.getChild("LegBackLeft");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(92, 0).addBox(-4.0F, -5.0F, -10.0F, 8.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(92, 16).addBox(-4.0F, 1.0F, -10.0F, 8.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, -7.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 61).addBox(-12.0F, -17.0F, -9.0F, 24.0F, 4.0F, 25.0F, new CubeDeformation(0.0F))
				.texOffs(25, 91).addBox(-10.0F, -33.0F, -7.0F, 20.0F, 16.0F, 21.0F, new CubeDeformation(0.0F))
				.texOffs(0, 90).addBox(-5.0F, -22.0F, -8.0F, 10.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 27.0F, 0.0F));

		PartDefinition rightHole_r1 = Body.addOrReplaceChild("rightHole_r1", CubeListBuilder.create().texOffs(0, 90).mirror().addBox(-8.0F, -22.0F, -11.0F, 10.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition leftHole_r1 = Body.addOrReplaceChild("leftHole_r1", CubeListBuilder.create().texOffs(0, 90).addBox(-2.0F, -22.0F, -11.0F, 10.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition WingRight = partdefinition.addOrReplaceChild("WingRight", CubeListBuilder.create(), PartPose.offset(-11.0F, 7.0F, 3.0F));

		PartDefinition Wing_r1 = WingRight.addOrReplaceChild("Wing_r1", CubeListBuilder.create().texOffs(19, 0).addBox(-24.0F, -19.0F, -7.0F, 12.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, 20.0F, -3.0F, -0.2094F, 0.0F, 0.0873F));

		PartDefinition WingLeft = partdefinition.addOrReplaceChild("WingLeft", CubeListBuilder.create(), PartPose.offset(11.0F, 7.0F, 3.0F));

		PartDefinition Wing_r2 = WingLeft.addOrReplaceChild("Wing_r2", CubeListBuilder.create().texOffs(19, 0).mirror().addBox(12.0F, -19.0F, -7.0F, 12.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-11.0F, 20.0F, -3.0F, -0.2094F, 0.0F, -0.0873F));

		PartDefinition LegFrontRight = partdefinition.addOrReplaceChild("LegFrontRight", CubeListBuilder.create().texOffs(0, 112).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 14.0F, -4.0F));

		PartDefinition LegFrontLeft = partdefinition.addOrReplaceChild("LegFrontLeft", CubeListBuilder.create().texOffs(0, 112).mirror().addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.0F, 14.0F, -4.0F));

		PartDefinition LegBackRight = partdefinition.addOrReplaceChild("LegBackRight", CubeListBuilder.create().texOffs(0, 112).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 14.0F, 10.0F));

		PartDefinition LegBackLeft = partdefinition.addOrReplaceChild("LegBackLeft", CubeListBuilder.create().texOffs(0, 112).mirror().addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.0F, 14.0F, 10.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override public void setupAnim(GlidingTurtleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.isGliding = entity.isGliding(); this.isChild = entity.isBaby(); //TODO : find another way ?
		this.Head.yRot = netHeadYaw / 57.29578F;
		this.Head.xRot = headPitch / 57.29578F;

		this.animateRightLeg(this.LegFrontRight, limbSwing, limbSwingAmount);
		this.animateRightLeg(this.LegBackRight, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.LegFrontLeft, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.LegBackLeft, limbSwing, limbSwingAmount);

		if (isGliding) {this.WingRight.zRot = 0; this.WingLeft.zRot = 0;}
		else {this.WingRight.zRot = -45F; this.WingLeft.zRot = 45F;}
	}

	private void animateLeftLeg(ModelPart model, float limbSwing, float limbSwingAmount)
	{
		model.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount; model.yRot = 0.0F;
	}

	private void animateRightLeg(ModelPart model, float limbSwing, float limbSwingAmount)
	{
		model.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount; model.yRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		poseStack.pushPose();
		if(isChild)
		{
			poseStack.scale(0.5F, 0.5F, 0.5F);
			poseStack.translate(0.0F, 1.5F, 0.0F);
		}
		poseStack.popPose();

		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		WingRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		WingLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegFrontRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegFrontLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegBackRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LegBackLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}