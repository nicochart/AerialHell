package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.FlyingJellyfishEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class FlyingJellyfishModel<J extends FlyingJellyfishEntity> extends EntityModel<J>
{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(AerialHell.MODID, "flying_jellyfish_model"), "main");
	private final ModelPart tentacles_0;
	private final ModelPart tentacles_1;
	private final ModelPart tentacles_2;
	private final ModelPart tentacles_3;
	private final ModelPart tentacles_4;
	private final ModelPart tentacles_5;
	private final ModelPart tentacles_6;
	private final ModelPart body;

	public FlyingJellyfishModel(ModelPart root)
	{
		this.tentacles_0 = root.getChild("tentacles_0");
		this.tentacles_1 = root.getChild("tentacles_1");
		this.tentacles_2 = root.getChild("tentacles_2");
		this.tentacles_3 = root.getChild("tentacles_3");
		this.tentacles_4 = root.getChild("tentacles_4");
		this.tentacles_5 = root.getChild("tentacles_5");
		this.tentacles_6 = root.getChild("tentacles_6");
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tentacles_0 = partdefinition.addOrReplaceChild("tentacles_0", CubeListBuilder.create().texOffs(4, 0).mirror().addBox(-3.0F, -1.0F, 3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.8F, 23.0F, -5.0F));

		PartDefinition tentacles_1 = partdefinition.addOrReplaceChild("tentacles_1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -1.0F, 3.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.3F, 23.0F, -5.0F));

		PartDefinition tentacles_2 = partdefinition.addOrReplaceChild("tentacles_2", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(5.0F, -1.0F, 4.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.3F, 23.0F, -5.0F));

		PartDefinition tentacles_3 = partdefinition.addOrReplaceChild("tentacles_3", CubeListBuilder.create().texOffs(4, 8).mirror().addBox(-5.0F, -1.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.3F, 23.0F, 0.0F));

		PartDefinition tentacles_4 = partdefinition.addOrReplaceChild("tentacles_4", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-1.2F, -1.0F, 1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.3F, 23.0F, 0.0F));

		PartDefinition tentacles_5 = partdefinition.addOrReplaceChild("tentacles_5", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(1.0F, -1.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.8F, 23.0F, 0.0F));

		PartDefinition tentacles_6 = partdefinition.addOrReplaceChild("tentacles_6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -1.0F, -3.35F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.3F, 23.0F, 5.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(-3.0F, 2.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(26, 1).mirror().addBox(3.0F, 4.0F, 2.2F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(24, 12).mirror().addBox(3.0F, 2.0F, 2.6F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(28, 2).mirror().addBox(-5.0F, 4.0F, 2.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(28, 12).mirror().addBox(-5.0F, 2.0F, 2.64F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(8, 14).mirror().addBox(-4.0F, 0.0F, 2.64F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 16.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override public void setupAnim(J entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.tentacles_0.xRot = 0.1F * Mth.sin(ageInTicks * 0.3F + 0.0F) + 0.4F;
		this.tentacles_1.xRot = 0.1F * Mth.sin(ageInTicks * 0.3F + 1.0F) + 0.4F;
		this.tentacles_2.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 2.0F) + 0.4F;
		this.tentacles_3.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 3.0F) + 0.4F;
		this.tentacles_4.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 4.0F) + 0.4F;
		this.tentacles_5.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 5.0F) + 0.4F;
		this.tentacles_6.xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + 6.0F) + 0.4F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		tentacles_0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacles_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacles_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacles_3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacles_4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacles_5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tentacles_6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}