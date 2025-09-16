package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.HellSpiderRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

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

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData bodySpikes = partdefinition.addChild("bodySpikes", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-4.5F, -18.0F, 8.0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 0).mirrored().cuboid(-2.0F, -18.0F, 5.5F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 10).mirrored().cuboid(-0.5F, -18.0F, 12.0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 11).mirrored().cuboid(2.0F, -18.0F, 9.5F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 21).mirrored().cuboid(0.0F, -18.0F, 5.0F, 6.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 20).mirrored().cuboid(3.0F, -18.0F, 2.0F, 0.0F, 5.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
		.uv(12, -4).mirrored().cuboid(1.0F, -11.5F, 15.0F, 0.0F, 5.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(8, 6).mirrored().cuboid(-1.5F, -9.0F, 15.0F, 5.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
		.uv(12, 10).mirrored().cuboid(-9.0F, -12.0F, 11.0F, 4.0F, 6.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(6, 16).mirrored().cuboid(-9.0F, -9.0F, 8.0F, 4.0F, 0.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
		.uv(6, 22).mirrored().cuboid(5.0F, -10.0F, 7.5F, 4.0F, 0.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(20, 0).mirrored().cuboid(5.0F, -12.5F, 10.0F, 4.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData headSpikes = partdefinition.addChild("headSpikes", ModelPartBuilder.create().uv(32, 0).mirrored().cuboid(-2.5F, -18.0F, -7.0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(32, 0).mirrored().cuboid(0.0F, -18.0F, -9.5F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(35, 10).mirrored().cuboid(4.0F, -12.0F, -7.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F)).mirrored(false)
		.uv(29, 16).mirrored().cuboid(4.0F, -9.0F, -10.0F, 3.0F, 0.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
		.uv(29, 22).mirrored().cuboid(-7.0F, -9.0F, -10.5F, 3.0F, 0.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
		.uv(44, 0).mirrored().cuboid(-7.0F, -11.5F, -8.0F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 32);
	}

	@Override public void setAngles(HellSpiderRenderState renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;

		this.headSpikes.yaw = netHeadYaw * ((float)Math.PI / 180F);
		this.headSpikes.pitch = headPitch * ((float)Math.PI / 180F);
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		bodySpikes.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		headSpikes.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}