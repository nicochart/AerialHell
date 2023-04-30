package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ForestCaterpillarModel<T extends AbstractCaterpillarEntity> extends EntityModel<T>
{
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(AerialHell.MODID, "caterpillar_model"), "main");
	private final ModelPart bodyPart_0;
	private final ModelPart bodyPart_1;
	private final ModelPart bodyPart_2;
	private final ModelPart bodyPart_3;

	public ForestCaterpillarModel(ModelPart root)
	{
		this.bodyPart_0 = root.getChild("bodyPart_0");
		this.bodyPart_1 = root.getChild("bodyPart_1");
		this.bodyPart_2 = root.getChild("bodyPart_2");
		this.bodyPart_3 = root.getChild("bodyPart_3");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bodyPart_0 = partdefinition.addOrReplaceChild("bodyPart_0", CubeListBuilder.create().texOffs(5, 26).mirror().addBox(-2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 23).mirror().addBox(-1.0F, -4.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, -3.5F));

		PartDefinition bodyPart_1 = partdefinition.addOrReplaceChild("bodyPart_1", CubeListBuilder.create().texOffs(17, 14).mirror().addBox(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(11, 24).mirror().addBox(-5.0F, 0.0F, -4.5F, 10.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 7).mirror().addBox(-1.0F, -5.0F, -3.5F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(25, 4).mirror().addBox(-1.0F, -6.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(45, 7).mirror().addBox(0.0F, -11.0F, -3.5F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(47, 8).mirror().addBox(-2.0F, -11.0F, -0.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bodyPart_2 = partdefinition.addOrReplaceChild("bodyPart_2", CubeListBuilder.create().texOffs(39, 27).mirror().addBox(-1.5F, -3.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 3.0F));

		PartDefinition bodyPart_3 = partdefinition.addOrReplaceChild("bodyPart_3", CubeListBuilder.create().texOffs(41, 23).mirror().addBox(-0.5F, -2.0F, 0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.bodyPart_1.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
		this.bodyPart_2.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(2 - 2));
		this.bodyPart_3.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(3 - 2));
		this.bodyPart_0.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(0 - 2));
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		bodyPart_0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bodyPart_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bodyPart_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bodyPart_3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}