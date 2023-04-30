package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class LunaticPriestModel extends EntityModel<LunaticPriestEntity>
{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(AerialHell.MODID, "lunatic_priest_model"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arm0;
	private final ModelPart arm1;
	private final ModelPart leg0;
	private final ModelPart leg1;

	public LunaticPriestModel(ModelPart root)
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

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-7.0F, 1.0F, -4.0F, 14.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(7, 53).mirror().addBox(-4.5F, 15.0F, -3.0F, 9.0F, 5.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(6, 16).mirror().addBox(-4.0F, -6.0F, -5.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(4, 0).mirror().addBox(-5.0F, -7.0F, -4.5F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(7, 1).mirror().addBox(-4.0F, -8.0F, -2.5F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(15, 6).mirror().addBox(-3.0F, -8.0F, 5.5F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(10, 2).mirror().addBox(-3.0F, -9.0F, 0.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(15, 5).mirror().addBox(-2.0F, -7.0F, 5.5F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 6).mirror().addBox(-1.0F, -4.0F, 7.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(40, 29).mirror().addBox(-2.0F, 2.0F, -5.5F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(41, 27).mirror().addBox(-2.0F, -7.0F, -5.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -7.0F, -1.0F));

		PartDefinition arm0 = partdefinition.addOrReplaceChild("arm0", CubeListBuilder.create().texOffs(72, 40).mirror().addBox(1.0F, -0.5F, -2.0F, 2.0F, 20.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(86, 41).mirror().addBox(3.0F, 8.5F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(90, 36).mirror().addBox(3.0F, 7.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(88, 44).mirror().addBox(3.0F, 10.5F, -5.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(96, 44).mirror().addBox(3.0F, 10.5F, 3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.0F, -4.0F, 0.0F));

		PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(57, 40).mirror().addBox(-3.0F, -0.5F, -2.0F, 2.0F, 20.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(86, 41).mirror().addBox(-4.0F, 8.5F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(90, 36).mirror().addBox(-4.0F, 7.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(87, 44).mirror().addBox(-4.0F, 10.5F, 3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(96, 44).mirror().addBox(-4.0F, 10.5F, -5.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, -4.0F, 0.0F));

		PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(54, 13).mirror().addBox(-2.5F, 2.0F, -2.0F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 11.0F, 0.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(70, 13).addBox(0.5F, 2.0F, -2.0F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 11.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override public void setupAnim(LunaticPriestEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / 57.29578F;
		this.head.xRot = headPitch / 57.29578F;

		int i = entity.attackTimer;
		if (i > 0)
		{
			this.arm0.xRot = -2.0F + 0.6F * Mth.triangleWave((float)i, 10.0F);
			this.arm1.xRot = -2.0F + 0.6F * Mth.triangleWave((float)i, 10.0F);
		}
		else
		{
			this.arm0.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
			this.arm1.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		}

		this.leg0.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leg1.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leg0.yRot = 0.0F;
		this.leg1.yRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arm1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}