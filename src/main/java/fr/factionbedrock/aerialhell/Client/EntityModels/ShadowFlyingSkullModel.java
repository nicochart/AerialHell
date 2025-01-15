package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShadowFlyingSkullRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made by Cixon with Blockbench
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ShadowFlyingSkullModel extends EntityModel<ShadowFlyingSkullRenderState>
{
	private final ModelPart skull;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart jaw;

	public ShadowFlyingSkullModel(ModelPart root)
	{
		super(root);
		this.skull = root.getChild("skull");
		this.leftWing = root.getChild("leftWing");
		this.rightWing = root.getChild("rightWing");
		this.jaw = root.getChild("jaw");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition skull = partdefinition.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -9.0F, -1.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 24.0F, -3.0F));

		PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offset(4.0F, 19.0F, 0.0F));

		PartDefinition wing_r1 = leftWing.addOrReplaceChild("wing_r1", CubeListBuilder.create().texOffs(48, 25).addBox(-0.3F, -3.0F, 0.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition bone_r1 = leftWing.addOrReplaceChild("bone_r1", CubeListBuilder.create().texOffs(48, 15).addBox(-2.0F, -2.0F, 0.0F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

		PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offset(-4.0F, 19.0F, 0.0F));

		PartDefinition wing_r2 = rightWing.addOrReplaceChild("wing_r2", CubeListBuilder.create().texOffs(48, 25).mirror().addBox(-5.7F, -3.0F, 0.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition bone_r2 = rightWing.addOrReplaceChild("bone_r2", CubeListBuilder.create().texOffs(48, 15).mirror().addBox(-5.0F, -2.0F, 0.0F, 7.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

		PartDefinition jaw = partdefinition.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(ShadowFlyingSkullRenderState renderState)
	{
		this.jaw.xRot = renderState.jawOpeningAmplitude * Mth.sqrt(1.0F + Mth.cos(renderState.ageInTicks / renderState.jawOpeningFrequencyMalus * 3.2F));
		this.rightWing.yRot = 0.47123894F + Mth.cos(renderState.ageInTicks * 0.8F) * 0.3F;
		this.leftWing.yRot = -this.rightWing.yRot;
		this.leftWing.zRot = -0.47123894F;
		this.leftWing.xRot = 0.47123894F;
		this.rightWing.xRot = 0.47123894F;
		this.rightWing.zRot = 0.47123894F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		skull.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		jaw.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}