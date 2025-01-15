package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ChestMimicRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.AbstractChestMimicEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ChestMimicModel extends EntityModel<ChestMimicRenderState>
{
	private final ModelPart chestDown;
	private final ModelPart chestUp;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public ChestMimicModel(ModelPart root)
	{
		super(root);
		this.chestDown = root.getChild("chestDown");
		this.chestUp = root.getChild("chestUp");
		this.rightLeg = root.getChild("RightLeg");
		this.leftLeg = root.getChild("LeftLeg");
		}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition chestDown = partdefinition.addOrReplaceChild("chestDown", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-8.0F, 3.0F, -7.0F, 16.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(76, 50).mirror().addBox(-5.0F, 3.0F, -4.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(18, 12).mirror().addBox(-7.0F, 0.0F, -6.0F, 14.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(2, 0).mirror().addBox(7.0F, 1.0F, -7.0F, 0.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 0).mirror().addBox(-7.0F, 1.0F, -7.0F, 0.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition chestUp = partdefinition.addOrReplaceChild("chestUp", CubeListBuilder.create().texOffs(64, 23).mirror().addBox(-8.0F, -6.0F, -16.0F, 16.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(80, 13).mirror().addBox(-8.0F, 0.0F, -15.0F, 16.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(64, 0).mirror().addBox(7.0F, 0.0F, -16.0F, 0.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(96, 0).mirror().addBox(-7.0F, 0.0F, -16.0F, 0.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).addBox(-1.0F, -2.0F, -17.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 9.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(27, 46).mirror().addBox(-8.9F, 0.0F, -2.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(124, -2).addBox(-5.9F, 10.0F, -4.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LTalons_r1 = RightLeg.addOrReplaceChild("LTalons_r1", CubeListBuilder.create().texOffs(124, -2).addBox(-4.0F, 10.0F, -3.5F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition RTalons_r1 = RightLeg.addOrReplaceChild("RTalons_r1", CubeListBuilder.create().texOffs(124, -2).addBox(-7.7F, 10.0F, -5.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(-0.9F, 0.0F, -2.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(124, -2).addBox(2.1F, 10.0F, -4.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LTalons_r2 = LeftLeg.addOrReplaceChild("LTalons_r2", CubeListBuilder.create().texOffs(124, -2).addBox(4.0F, 10.0F, -4.5F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition RTalons_r2 = LeftLeg.addOrReplaceChild("RTalons_r2", CubeListBuilder.create().texOffs(124, -2).addBox(0.3F, 10.0F, -4.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
		}

	@Override public void setupAnim(ChestMimicRenderState renderState)
	{
		float limbSwing = renderState.walkAnimationPos;
		float limbSwingAmount = renderState.walkAnimationSpeed;

		this.chestUp.xRot = - renderState.mouthOpeningAmplitude * Mth.sqrt(1.0F + Mth.cos(renderState.ageInTicks / renderState.mouthOpeningFrequencyMalus * (float) Math.PI));
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightLeg.yRot = 0.0F;
		this.leftLeg.yRot = 0.0F;
		this.rightLeg.zRot = 0.0F;
		this.leftLeg.zRot = 0.0F;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		chestDown.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		chestUp.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}