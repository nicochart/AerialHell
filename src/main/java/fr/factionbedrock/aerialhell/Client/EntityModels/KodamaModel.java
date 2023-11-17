package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.Passive.KodamaEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class KodamaModel<T extends KodamaEntity> extends EntityModel<T>
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart face_1;
	private final ModelPart face_2;
	private final ModelPart face_3;
	private final ModelPart face_4;
	private final ModelPart face_5;
	private final ModelPart arm0;
	private final ModelPart arm1;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private boolean isEmpty;
	private int faceId;
	private long dayTime;

	public KodamaModel(ModelPart root, boolean isEmpty)
	{
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.face_1 = root.getChild("face_1");
		this.face_2 = root.getChild("face_2");
		this.face_3 = root.getChild("face_3");
		this.face_4 = root.getChild("face_4");
		this.face_5 = root.getChild("face_5");
		this.arm0 = root.getChild("arm0");
		this.arm1 = root.getChild("arm1");
		this.leg0 = root.getChild("leg0");
		this.leg1 = root.getChild("leg1");
		this.isEmpty = isEmpty;
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(9, 18).mirror().addBox(-3.5F, 15.0F, -1.0F, 7.0F, 9.0F, 2.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.9444F, -9.5F, -3.6111F, 10.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.0556F, 7.5F, -0.3889F));

		PartDefinition face_1 = partdefinition.addOrReplaceChild("face_1", CubeListBuilder.create().texOffs(50, 27).mirror().addBox(1.0556F, -6.0F, -4.6111F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(50, 27).mirror().addBox(-3.9444F, -7.5F, -4.6111F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(49, 25).mirror().addBox(-1.9444F, -2.5F, -4.6111F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.0556F, 7.5F, -0.3889F));

		PartDefinition face_2 = partdefinition.addOrReplaceChild("face_2", CubeListBuilder.create().texOffs(49, 26).mirror().addBox(1.5556F, -8.0F, -4.6111F, 2.5F, 2.5F, 2.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(50, 27).mirror().addBox(-2.9444F, -5.5F, -4.6111F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(49, 25).mirror().addBox(3.0556F, -2.5F, -4.6111F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.0556F, 7.5F, -0.3889F));

		PartDefinition face_3 = partdefinition.addOrReplaceChild("face_3", CubeListBuilder.create().texOffs(49, 26).mirror().addBox(1.5556F, -8.0F, -5.1111F, 2.5F, 2.5F, 2.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(52, 22).mirror().addBox(-4.4444F, -8.0F, -4.6111F, 2.5F, 2.5F, 2.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(52, 25).mirror().addBox(-1.9444F, -4.5F, -4.6111F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.0556F, 7.5F, -0.3889F));

		PartDefinition face_4 = partdefinition.addOrReplaceChild("face_4", CubeListBuilder.create().texOffs(50, 27).mirror().addBox(1.0556F, -7.5F, -4.6111F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(52, 22).mirror().addBox(-4.4444F, -8.0F, -5.1111F, 2.5F, 2.5F, 2.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(49, 25).mirror().addBox(-3.9444F, -2.5F, -4.6111F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.0556F, 7.5F, -0.3889F));

		PartDefinition face_5 = partdefinition.addOrReplaceChild("face_5", CubeListBuilder.create().texOffs(50, 27).mirror().addBox(1.0556F, -5.5F, -4.6111F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(52, 22).mirror().addBox(-4.4444F, -8.0F, -5.1111F, 2.5F, 2.5F, 2.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(49, 25).mirror().addBox(0.0556F, -2.5F, -4.6111F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.0556F, 7.5F, -0.3889F));

		PartDefinition arm0 = partdefinition.addOrReplaceChild("arm0", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 8.5F, 0.0F));

		PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-2.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 8.5F, 0.0F));

		PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(29, 19).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 17.0F, 0.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(37, 19).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 17.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.faceId = entity.getFaceId();
		this.dayTime = entity.level().getDayTime() % 24000;
		setHeadRot(this.head, netHeadYaw, headPitch);
		setHeadRot(this.face_1, netHeadYaw, headPitch);
		setHeadRot(this.face_2, netHeadYaw, headPitch);
		setHeadRot(this.face_3, netHeadYaw, headPitch);
		setHeadRot(this.face_4, netHeadYaw, headPitch);
		setHeadRot(this.face_5, netHeadYaw, headPitch);

		this.arm0.zRot = -0.1F;
		this.arm1.zRot = 0.1F;
		this.arm0.xRot = (-0.2F + 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		this.arm1.xRot = (-0.2F - 1.5F * Mth.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		this.leg0.xRot = -1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leg1.xRot = 1.0F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
	}

	private void setHeadRot(ModelPart part, float netHeadYaw, float headPitch) {part.yRot = netHeadYaw / 57.3F; part.xRot = headPitch / 57.3F;}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		if (this.isEmpty) {}
		else
		{
			float a=alpha - getAlphaBonus();

			if (this.faceId == 1) {face_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 2) {face_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 3) {face_3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 4) {face_4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 5) {face_5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}

			float r=red,g=green,b=blue;
			r-=getBlueBonus(); g-=getBlueBonus()/10;
			body.render(poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			head.render(poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			arm0.render(poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			arm1.render(poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			leg0.render(poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			leg1.render(poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
		}
	}

	private float getBlueBonus()
	{
		if (this.dayTime > 13000) //night time (from 13000 to 24000)
		{
			float blueBonus;
			long tickBonus;
			if (this.dayTime < 18000) //first half of the night, 13000 < time < 18000
			{
				tickBonus = this.dayTime - 13000; //from 0 at 13000 (night start) to 5000 (midnight)
				blueBonus = (float)tickBonus/5000;
			}
			else //second half of the night, 18000 < time < 24000
			{
				tickBonus = 24000 - this.dayTime; //from 4000 at 18000 (midnight) to 0 at 24000
				blueBonus = (float)tickBonus/6000;
			}
			return blueBonus/2;
		}
		else {return 0.0F;}
	}

	private float getAlphaBonus()
	{
		if (this.dayTime <= 13000)
		{
			if (this.dayTime < 1000) {return (float)this.dayTime/1000;}
			else if (this.dayTime >= 1000 && this.dayTime <= 12000) {return 1.0f;}
			else {return ((float)13000 - this.dayTime)/1000;}
		}
		else {return 0.0F;}
	}
}