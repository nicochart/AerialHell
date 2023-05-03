package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.Monster.VerdigrisZombieEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class VerdigrisZombieModel extends EntityModel<VerdigrisZombieEntity>//HumanoidModel<VerdigrisZombieEntity>
{
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public VerdigrisZombieModel(ModelPart root)
	{
		//super(root); //when extends HumanoidModel - it doesn't work : the game crashes when starting : "can't find this body part / element"
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftArm = root.getChild("leftArm");
		this.rightArm = root.getChild("rightArm");
		this.leftLeg = root.getChild("leftLeg");
		this.rightLeg = root.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition shroomfall_r1 = head.addOrReplaceChild("shroomfall_r1", CubeListBuilder.create().texOffs(12, 0).mirror().addBox(-4.0F, -31.0F, 3.0F, 8.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(32, 0).mirror().addBox(-4.0F, -31.0F, -7.0F, 8.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(12, 17).mirror().addBox(-4.0F, -35.0F, -7.0F, 8.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(4, 17).mirror().addBox(-6.0F, -35.0F, -5.0F, 2.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 3).mirror().addBox(-6.0F, -31.0F, -5.0F, 2.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(44, 3).mirror().addBox(4.0F, -31.0F, -5.0F, 2.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(40, 17).mirror().addBox(4.0F, -35.0F, -5.0F, 2.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(16, 7).mirror().addBox(-4.0F, -37.0F, -5.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(16, 33).mirror().addBox(-4.0F, -31.0F, -5.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition backshroom_r1 = body.addOrReplaceChild("backshroom_r1", CubeListBuilder.create().texOffs(50, 35).mirror().addBox(-3.0F, -22.0F, 2.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(20, 48).mirror().addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition shroom_r1 = leftArm.addOrReplaceChild("shroom_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-10.0F, -24.0F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(48, 48).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 22.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition shroom_r2 = rightArm.addOrReplaceChild("shroom_r2", CubeListBuilder.create().texOffs(5, 32).mirror().addBox(8.0F, -24.0F, 0.0F, 2.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(48, 48).mirror().addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, 22.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(VerdigrisZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, entity.isAggressive(), this.attackTime, ageInTicks);
		this.head.yRot = netHeadYaw / 57.3F;
		this.head.xRot = headPitch / 57.3F;
		this.leftLeg.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.yRot = 0.0F;
		this.rightLeg.yRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}