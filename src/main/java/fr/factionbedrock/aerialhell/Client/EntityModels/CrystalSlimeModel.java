package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalSlimeEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings


public class CrystalSlimeModel extends EntityModel<CrystalSlimeEntity>
{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(AerialHell.MODID, "crystal_slime_model"), "main");
	private final ModelPart cube;
	private final ModelPart eye0;
	private final ModelPart eye1;
	private final ModelPart mouth;
	private final ModelPart crystal;

	public CrystalSlimeModel(ModelPart root)
	{
		this.cube = root.getChild("cube");
		this.eye0 = root.getChild("eye0");
		this.eye1 = root.getChild("eye1");
		this.mouth = root.getChild("mouth");
		this.crystal = root.getChild("crystal");
	}

	public static LayerDefinition createBodyLayer(boolean IsGelAndCrystal)
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		if (IsGelAndCrystal)
		{
			PartDefinition cube = partdefinition.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition crystal = partdefinition.addOrReplaceChild("crystal", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-4.0F, 19.0F, -1.0F, 6.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(0, 19).mirror().addBox(-1.0F, 19.0F, -4.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(14, 21).mirror().addBox(2.0F, 21.0F, 0.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(14, 19).mirror().addBox(0.0F, 21.0F, 2.0F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(40, 13).mirror().addBox(2.0F, 25.0F, 1.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(54, 20).mirror().addBox(-2.0F, 26.0F, 2.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(50, 19).mirror().addBox(-4.0F, 28.0F, 2.0F, 4.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(36, 24).mirror().addBox(0.0F, 27.0F, 1.0F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(24, 18).mirror().addBox(2.0F, 25.0F, 0.0F, 7.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(18, 25).mirror().addBox(2.0F, 28.0F, -3.0F, 7.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(37, 6).mirror().addBox(-8.0F, 28.0F, -2.0F, 6.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(52, 13).mirror().addBox(-7.0F, 30.0F, -3.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(55, 9).mirror().addBox(-7.0F, 29.0F, -2.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
					.texOffs(42, 12).mirror().addBox(-8.0F, 26.0F, 1.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -8.0F, 0.0F));
		}
		else
		{
			PartDefinition eye0 = partdefinition.addOrReplaceChild("eye0", CubeListBuilder.create().texOffs(33, 0).mirror().addBox(1.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
			PartDefinition eye1 = partdefinition.addOrReplaceChild("eye1", CubeListBuilder.create().texOffs(33, 5).mirror().addBox(-3.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
			PartDefinition mouth = partdefinition.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(35, 10).mirror().addBox(-1.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
		}

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override public void setupAnim(CrystalSlimeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		cube.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		eye0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		eye1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		mouth.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		crystal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}