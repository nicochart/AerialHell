package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.HellSpiderRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class HellSpiderSpikeModel extends EntityModel<HellSpiderRenderState>//SpiderModel<T>
{
	private final ModelPart bodySpikes;
	private final ModelPart headSpikes;

	public HellSpiderSpikeModel(ModelPart root)
	{
		super(root);
		this.bodySpikes = root.getChild("bodySpikes");
		this.headSpikes = root.getChild("headSpikes");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bodySpikes = partdefinition.addOrReplaceChild("bodySpikes", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, -18.0F, 8.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).mirror().addBox(-2.0F, -18.0F, 5.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 10).mirror().addBox(-0.5F, -18.0F, 12.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 11).mirror().addBox(2.0F, -18.0F, 9.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 21).mirror().addBox(0.0F, -18.0F, 5.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 20).mirror().addBox(3.0F, -18.0F, 2.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(12, -4).mirror().addBox(1.0F, -11.5F, 15.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 6).mirror().addBox(-1.5F, -9.0F, 15.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(12, 10).mirror().addBox(-9.0F, -12.0F, 11.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(6, 16).mirror().addBox(-9.0F, -9.0F, 8.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(6, 22).mirror().addBox(5.0F, -10.0F, 7.5F, 4.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(20, 0).mirror().addBox(5.0F, -12.5F, 10.0F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition headSpikes = partdefinition.addOrReplaceChild("headSpikes", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-2.5F, -18.0F, -7.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 0).mirror().addBox(0.0F, -18.0F, -9.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(35, 10).mirror().addBox(4.0F, -12.0F, -7.0F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(29, 16).mirror().addBox(4.0F, -9.0F, -10.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(29, 22).mirror().addBox(-7.0F, -9.0F, -10.5F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(44, 0).mirror().addBox(-7.0F, -11.5F, -8.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override public void setupAnim(HellSpiderRenderState renderState)
	{
		float headPitch = renderState.xRot;
		float netHeadYaw = renderState.yRot;

		this.headSpikes.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.headSpikes.xRot = headPitch * ((float)Math.PI / 180F);
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		bodySpikes.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		headSpikes.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}