package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.LilithRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made by Cixon with Blockbench
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class LilithModel extends EntityModel<LilithRenderState>
{
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart wingRight;
	private final ModelPart wingLeft;
	private final ModelPart legLeft;
	private final ModelPart legRight;
	private final ModelPart armLeft;
	private final ModelPart armRight;

	public LilithModel(ModelPart root)
	{
		super(root);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.wingRight = root.getChild("wingRight");
		this.wingLeft = root.getChild("wingLeft");
		this.legLeft = root.getChild("legLeft");
		this.legRight = root.getChild("legRight");
		this.armLeft = root.getChild("armLeft");
		this.armRight = root.getChild("armRight");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(17, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition wingRight = partdefinition.addOrReplaceChild("wingRight", CubeListBuilder.create(), PartPose.offset(-2.0F, 5.0F, 2.0F));

		PartDefinition wingRight_r1 = wingRight.addOrReplaceChild("wingRight_r1", CubeListBuilder.create().texOffs(0, 30).addBox(-0.5F, -25.0F, 4.0F, 0.0F, 13.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 19.0F, -2.0F, 0.2094F, -0.6981F, 0.0F));

		PartDefinition wingLeft = partdefinition.addOrReplaceChild("wingLeft", CubeListBuilder.create(), PartPose.offset(2.0F, 5.0F, 2.0F));

		PartDefinition wingLeft_r1 = wingLeft.addOrReplaceChild("wingLeft_r1", CubeListBuilder.create().texOffs(0, 30).addBox(0.5F, -25.0F, 4.0F, 0.0F, 13.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 19.0F, -2.0F, 0.2094F, 0.6981F, 0.0F));

		PartDefinition legLeft = partdefinition.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition legRight = partdefinition.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition armLeft = partdefinition.addOrReplaceChild("armLeft", CubeListBuilder.create().texOffs(41, 16).mirror().addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 0.0F, 0.0F));

		PartDefinition armRight = partdefinition.addOrReplaceChild("armRight", CubeListBuilder.create().texOffs(41, 16).addBox(-4.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(LilithRenderState renderState)
	{
		float headPitch = renderState.xRot;
		float netHeadYaw = renderState.yRot;
		float limbSwing = renderState.walkAnimationPos;
		float limbSwingAmount = renderState.walkAnimationSpeed;
		if (!renderState.isTransforming)
		{
			int i = renderState.attackTimer;
			if (i > 0)
			{
				this.armRight.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i, 10.0F) * 0.5f;
				this.armLeft.xRot = -2.0F + 1.5F * Mth.triangleWave((float)i, 10.0F) * 0.5f;
			}
			else
			{
				this.armRight.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
				this.armLeft.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
			}
		}
		else
		{
			this.armRight.xRot = - 2.2F;
			this.armLeft.xRot = - 2.2F;
		}
		this.head.yRot = netHeadYaw / 57.3F;
		this.head.xRot = headPitch / 57.3F;
		this.legLeft.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount; this.legLeft.yRot = 0.0F;
		this.legRight.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount; this.legRight.yRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		wingRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		wingLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		legLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		legRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		armLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		armRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}