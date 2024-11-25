package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.Passive.KodamaEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import java.awt.*;

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
	private final ModelPart face_6;
	private final ModelPart face_7;
	private final ModelPart arm0;
	private final ModelPart arm1;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private boolean isEmpty;
	private int faceId;
	private long dayTime;
	private int forcedAlphaBonus;

	public KodamaModel(ModelPart root, boolean isEmpty)
	{
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.face_1 = root.getChild("face_1");
		this.face_2 = root.getChild("face_2");
		this.face_3 = root.getChild("face_3");
		this.face_4 = root.getChild("face_4");
		this.face_5 = root.getChild("face_5");
		this.face_6 = root.getChild("face_6");
		this.face_7 = root.getChild("face_7");
		this.arm0 = root.getChild("arm0");
		this.arm1 = root.getChild("arm1");
		this.leg0 = root.getChild("leg0");
		this.leg1 = root.getChild("leg1");
		this.isEmpty = isEmpty;
		this.forcedAlphaBonus = 0;
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

		PartDefinition face_6 = partdefinition.addOrReplaceChild("face_6", CubeListBuilder.create().texOffs(50, 27).mirror().addBox(2.0556F, -7.5F, -4.6111F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(52, 22).mirror().addBox(-3.4444F, -8.5F, -4.6111F, 2.5F, 2.5F, 2.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(49, 25).mirror().addBox(-1.4444F, -3.5F, -4.6111F, 1.5F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.0556F, 7.5F, -0.3889F));

		PartDefinition face_7 = partdefinition.addOrReplaceChild("face_7", CubeListBuilder.create().texOffs(52, 22).mirror().addBox(1.5556F, -7.0F, -4.6111F, 2.5F, 2.5F, 2.5F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(52, 22).mirror().addBox(-3.9444F, -8.0F, -4.6111F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(47, 23).mirror().addBox(0.5556F, -2.5F, -4.6111F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.0556F, 7.5F, -0.3889F));

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
		this.forcedAlphaBonus = this.getForcedAlphaBonus(entity);
		this.setHeadRot(netHeadYaw, headPitch, this.getZRotAngleFromEntityTiltAngle(entity));

		this.arm0.zRot = -0.1F;
		this.arm1.zRot = 0.1F;
		this.arm0.xRot = (-0.2F + 1.5F * MathHelper.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		this.arm1.xRot = (-0.2F - 1.5F * MathHelper.triangleWave(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		this.leg0.xRot = -1.0F * MathHelper.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
		this.leg1.xRot = 1.0F * MathHelper.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
	}

	private void setHeadRot(float netHeadYaw, float headPitch, float zRot)
	{
		this.setHeadXRot(headPitch / 57.3F); this.setHeadYRot(netHeadYaw / 57.3F); this.setHeadZRot(zRot);
	}

	private void setHeadXRot(float xrot) {this.head.xRot = xrot; this.face_1.xRot = xrot; this.face_2.xRot = xrot; this.face_3.xRot = xrot; this.face_4.xRot = xrot; this.face_5.xRot = xrot; this.face_6.xRot = xrot; this.face_7.xRot = xrot;}
	private void setHeadYRot(float yrot) {this.head.yRot = yrot; this.face_1.yRot = yrot; this.face_2.yRot = yrot; this.face_3.yRot = yrot; this.face_4.yRot = yrot; this.face_5.yRot = yrot; this.face_6.yRot = yrot; this.face_7.yRot = yrot;}
	private void setHeadZRot(float zrot) {this.head.zRot = zrot; this.face_1.zRot = zrot; this.face_2.zRot = zrot; this.face_3.zRot = zrot; this.face_4.zRot = zrot; this.face_5.zRot = zrot; this.face_6.zRot = zrot; this.face_7.zRot = zrot;}

	private float getMaxHeadZRot(T entity) {return entity.rattleHeadRotZAmplitude;} //0.6F is cool
	private float getZRotAngleFromEntityTiltAngle(T entity)
	{
		return this.getMaxHeadZRot(entity) * entity.getRattlingTiltAngle() / entity.getMaxRattlingTiltAngle();
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		if (this.isEmpty) {}
		else
		{
			Color color = new Color(tint, true);
			int red = color.getRed(), green = color.getGreen(), blue = color.getBlue(), alpha = color.getAlpha();

			int a = this.forcedAlphaBonus > 0 ? alpha - this.forcedAlphaBonus : alpha - getAlphaBonus();

			if (this.faceId == 1) {render(face_1, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 2) {render(face_2, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 3) {render(face_3, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 4) {render(face_4, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 5) {render(face_5, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 6) {render(face_6, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}
			else if (this.faceId == 7) {render(face_7, poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, a);}

			int r=red,g=green,b=blue;
			r-= (int) (255 * getBlueBonus()); g-= (int) (255 * getBlueBonus()/10);
			render(body, poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			render(head, poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			render(arm0, poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			render(arm1, poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			render(leg0, poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
			render(leg1, poseStack, vertexConsumer, 255, packedOverlay, r, g, b, a);
		}
	}

	private static void render(ModelPart modelPart, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int r, int g, int b, int alpha)
	{
		modelPart.render(poseStack, vertexConsumer, packedLight, packedOverlay, new Color(r, g, b, alpha).getRGB());
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

	private int getAlphaBonus()
	{
		if (this.dayTime <= 13000)
		{
			if (this.dayTime < 1000) {return (int) (this.dayTime/1000F * 255);}
			else if (this.dayTime >= 1000 && this.dayTime <= 12000) {return 255;}
			else {return (int) (((13000 - this.dayTime)/1000F) * 255);}
		}
		else {return 0;}
	}

	private int getForcedAlphaBonus(T entity)
	{
		if (entity.timeForceInvisible > 0)
		{
			int transitionTime = entity.getMaxTimeForceInvisible() / 10;
			if (entity.timeForceInvisible > entity.getMaxTimeForceInvisible() - transitionTime)
			{
				return (int) (255 * (float) (entity.getMaxTimeForceInvisible() - entity.timeForceInvisible) / transitionTime);
			}
			else if (entity.timeForceInvisible > transitionTime) {return 255;}
			else //if (0 < entity.timeForceInvisible < transitionTime)
			{
				return (int) (255 * (float) entity.timeForceInvisible / transitionTime);
			}
		}
		else {return 0;}
	}
}