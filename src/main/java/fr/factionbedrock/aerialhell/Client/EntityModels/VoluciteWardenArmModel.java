package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class VoluciteWardenArmModel extends EntityModel<VoluciteWardenRenderState>
{
	private final ModelPart arm;

	public VoluciteWardenArmModel(ModelPart root)
	{
		super(root);
		this.arm = root.getChild("arm");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition arm = partdefinition.addOrReplaceChild("arm", CubeListBuilder.create().texOffs(64, 32).addBox(-24.0F, -24.0F, -24.0F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spike_r1 = arm.addOrReplaceChild("spike_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-38.0F, 77.0F, -18.0F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -85.0F, 62.0F, 0.0F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 128);
	}

	@Override public void setupAnim(VoluciteWardenRenderState renderState)
	{
		float headPitch = renderState.xRot;
		float netHeadYaw = renderState.yRot;

		this.arm.yRot = netHeadYaw / 57.29578F;
		this.arm.xRot = headPitch / 57.29578F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}