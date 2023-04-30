package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class TornSpiritModel extends EntityModel<TornSpiritEntity>
{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(AerialHell.MODID, "torn_spirit_model"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public TornSpiritModel(ModelPart root)
	{
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition frontCrest2_r1 = body.addOrReplaceChild("frontCrest2_r1", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(0.0F, -3.0F, -1.6426F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 5.5478F, -4.3574F, 0.258F, -0.045F, 0.1687F));

		PartDefinition frontCrest1_r1 = body.addOrReplaceChild("frontCrest1_r1", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(0.0F, -3.0F, -1.8342F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 8.5221F, -3.9658F, 0.043F, 0.0076F, -0.1744F));

		PartDefinition backCrest_r1 = body.addOrReplaceChild("backCrest_r1", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(0.0F, -7.0F, 2.0F, 0.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(33, 55).mirror().addBox(1.0F, 1.0F, -1.3F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(21, 55).mirror().addBox(-3.0F, 1.0F, -1.3F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(15, 30).mirror().addBox(-6.0F, -8.0F, -2.0F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(19, 38).mirror().addBox(-4.0F, -4.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition leftLegBone_r1 = body.addOrReplaceChild("leftLegBone_r1", CubeListBuilder.create().texOffs(33, 55).mirror().addBox(1.0F, 7.0F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(21, 55).mirror().addBox(-3.0F, 7.0F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition headPlane_r1 = head.addOrReplaceChild("headPlane_r1", CubeListBuilder.create().texOffs(44, 54).mirror().addBox(-6.5F, -6.5F, -2.0F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(19, 1).mirror().addBox(-3.5F, -3.5F, -4.0F, 7.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(15, 14).mirror().addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition rightArmCrest_r1 = rightArm.addOrReplaceChild("rightArmCrest_r1", CubeListBuilder.create().texOffs(0, 27).mirror().addBox(-1.0F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 4.0F, 0.0F, 0.2598F, -0.0227F, 0.1731F));

		PartDefinition rightArm_r1 = rightArm.addOrReplaceChild("rightArm_r1", CubeListBuilder.create().texOffs(47, 36).mirror().addBox(-2.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 4.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition leftArmCrest_r1 = leftArm.addOrReplaceChild("leftArmCrest_r1", CubeListBuilder.create().texOffs(0, 1).addBox(-3.5F, -3.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 4.0F, 0.0F, 0.3119F, -0.3743F, 0.1695F));

		PartDefinition leftArm_r1 = leftArm.addOrReplaceChild("leftArm_r1", CubeListBuilder.create().texOffs(7, 36).addBox(0.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 4.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(TornSpiritEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / 57.29578F;
		this.head.xRot = headPitch / 57.29578F;

		this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}