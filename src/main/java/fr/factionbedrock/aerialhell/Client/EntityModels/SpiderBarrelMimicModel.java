package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.AbstractBarrelMimicEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Base model made by Cixon with Blockbench
// Edited in-ide by Nicochart to mimic vanilla animation
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class SpiderBarrelMimicModel<T extends AbstractBarrelMimicEntity> extends EntityModel<T>
{
	private final ModelPart body0;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart leg5;
	private final ModelPart leg6;
	private final ModelPart leg7;

	public SpiderBarrelMimicModel(ModelPart root)
	{
		this.body0 = root.getChild("body0");
		this.leg0 = root.getChild("leg0");
		this.leg1 = root.getChild("leg1");
		this.leg2 = root.getChild("leg2");
		this.leg3 = root.getChild("leg3");
		this.leg4 = root.getChild("leg4");
		this.leg5 = root.getChild("leg5");
		this.leg6 = root.getChild("leg6");
		this.leg7 = root.getChild("leg7");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body0 = partdefinition.addOrReplaceChild("body0", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, 9.0F));

		PartDefinition teethbottom_r1 = body0.addOrReplaceChild("teethbottom_r1", CubeListBuilder.create().texOffs(19, 10).addBox(-6.0F, -11.0F, -16.0F, 12.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6283F, 0.0F, 0.0F));

		PartDefinition teethtop_r1 = body0.addOrReplaceChild("teethtop_r1", CubeListBuilder.create().texOffs(19, 2).addBox(-6.0F, 2.0F, -17.0F, 12.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6283F, 0.0F, 0.0F));

		PartDefinition main_r1 = body0.addOrReplaceChild("main_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -9.0F, 2.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, -9.0F, 1.3963F, 0.0F, 0.0F));

		PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(13, 17).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.0F, 2.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(13, 17).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, 2.0F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(13, 17).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.0F, 1.0F));

		PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(13, 17).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, 1.0F));

		PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(13, 17).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.0F, 0.0F));

		PartDefinition leg5 = partdefinition.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(13, 17).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, 0.0F));

		PartDefinition leg6 = partdefinition.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(13, 17).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.0F, -1.0F));

		PartDefinition leg7 = partdefinition.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(13, 17).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 15.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		leg0.zRot = ((float)Math.PI / 4F); leg0.yRot = ((float)Math.PI / 4F);
		leg1.zRot = (-(float)Math.PI / 4F); leg1.yRot = (-(float)Math.PI / 4F);
		leg2.zRot = 0.58119464F; leg2.yRot = ((float)Math.PI / 8F);
		leg3.zRot = -0.58119464F; leg3.yRot = (-(float)Math.PI / 8F);
		leg4.zRot = 0.58119464F; leg4.yRot = (-(float)Math.PI / 8F);
		leg5.zRot = -0.58119464F; leg5.yRot = ((float)Math.PI / 8F);
		leg6.zRot = ((float)Math.PI / 4F); leg6.yRot = (-(float)Math.PI / 4F);
		leg7.zRot = -((float)Math.PI / 4F); leg7.yRot = ((float)Math.PI / 4F);
		float f3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
		float f4 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
		float f5 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
		float f6 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
		float f8 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
		float f9 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
		float f10 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		leg0.yRot += f3;
		leg1.yRot += -f3;
		leg2.yRot += f4;
		leg3.yRot += -f4;
		leg4.yRot += f5;
		leg5.yRot += -f5;
		leg6.yRot += f6;
		leg7.yRot += -f6;
		leg0.zRot += f7;
		leg1.zRot += -f7;
		leg2.zRot += f8;
		leg3.zRot += -f8;
		leg4.zRot += f9;
		leg5.zRot += -f9;
		leg6.zRot += f10;
		leg7.zRot += -f10;
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}