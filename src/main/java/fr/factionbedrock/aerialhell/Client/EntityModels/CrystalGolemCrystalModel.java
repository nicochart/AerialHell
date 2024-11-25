package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings


public class CrystalGolemCrystalModel<T extends AerialHellGolemEntity> extends EntityModel<T>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arm0;
	private final ModelPart arm1;
	private final ModelPart leg0;
	private final ModelPart leg1;

	public CrystalGolemCrystalModel(ModelPart root)
	{
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.arm0 = root.getChild("arm0");
		this.arm1 = root.getChild("arm1");
		this.leg0 = root.getChild("leg0");
		this.leg1 = root.getChild("leg1");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(91, 46).mirror().addBox(5.0F, -8.0F, 2.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(91, 47).mirror().addBox(7.0F, -8.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(91, 52).mirror().addBox(-4.0F, -3.0F, 5.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(83, 65).mirror().addBox(-8.0F, 1.0F, 5.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(114, 74).mirror().addBox(-4.0F, 0.0F, -10.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(106, 72).mirror().addBox(-8.0F, 4.0F, -10.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(92, 84).mirror().addBox(-1.0F, 11.0F, -6.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(88, 92).mirror().addBox(-3.0F, 13.0F, -6.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(90, 73).mirror().addBox(5.0F, 0.0F, 5.0F, 0.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(82, 71).mirror().addBox(1.0F, 4.0F, 5.0F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(113, 51).mirror().addBox(5.0F, -3.0F, -11.0F, 0.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(105, 65).mirror().addBox(1.0F, 1.0F, -11.0F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(92, 1).mirror().addBox(-7.0F, -19.0F, 0.5F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(88, 6).mirror().addBox(-7.0F, -17.0F, -1.5F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(92, 35).mirror().addBox(-7.0F, -10.0F, -3.5F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(88, 40).mirror().addBox(-7.0F, -8.0F, -5.5F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(92, 24).mirror().addBox(4.0F, -13.0F, -3.5F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(88, 29).mirror().addBox(4.0F, -11.0F, -5.5F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(92, 11).mirror().addBox(1.0F, -16.0F, 2.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(87, 19).mirror().addBox(-2.0F, -14.0F, 2.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -7.0F, -2.0F));

		PartDefinition arm0 = partdefinition.addOrReplaceChild("arm0", CubeListBuilder.create().texOffs(88, 117).mirror().addBox(13.0F, 13.5F, -3.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(92, 112).mirror().addBox(13.0F, 11.5F, -1.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(87, 102).mirror().addBox(9.0F, 8.5F, -7.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(91, 93).mirror().addBox(11.0F, 6.5F, -7.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(91, 103).mirror().addBox(11.0F, 6.5F, -7.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(107, 89).mirror().addBox(11.0F, 18.5F, 3.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(103, 97).mirror().addBox(9.0F, 20.5F, 3.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(104, 117).mirror().addBox(-15.0F, 5.5F, -2.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(108, 112).mirror().addBox(-15.0F, 3.5F, 0.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(103, 107).mirror().addBox(-13.0F, 12.5F, -6.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(107, 99).mirror().addBox(-11.0F, 10.5F, -6.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(71, 118).mirror().addBox(-0.5F, 8.0F, 2.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(75, 109).mirror().addBox(1.5F, 6.0F, 2.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 11.0F, 0.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(71, 106).addBox(-1.5F, 5.0F, -6.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(75, 98).addBox(0.5F, 3.0F, -6.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 11.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		int i = entity.attackTimer;
		if (i > 0)
		{
			this.arm0.xRot = -2.0F + 0.6F * MathHelper.triangleWave((float)i, 10.0F);
			this.arm1.xRot = -2.0F + 0.6F * MathHelper.triangleWave((float)i, 10.0F);
		}
		else
		{
			this.arm0.xRot = (-0.2F + 0.8F * MathHelper.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
			this.arm1.xRot = (-0.2F - 0.8F * MathHelper.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		}

		this.leg0.xRot = -1.5F * MathHelper.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leg1.xRot = 1.5F * MathHelper.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leg0.yRot = 0.0F;
		this.leg1.yRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		arm0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		arm1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}