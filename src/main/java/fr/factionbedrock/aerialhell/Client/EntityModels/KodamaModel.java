package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.KodamaRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class KodamaModel<S extends KodamaRenderState> extends EntityModel<S>
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
		super(root);
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

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(9, 18).mirrored().cuboid(-3.5F, 15.0F, -1.0F, 7.0F, 9.0F, 2.0F, new Dilation(0.5F)).mirrored(false), ModelTransform.origin(0.0F, -7.0F, 0.0F));

		ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-4.9444F, -9.5F, -3.6111F, 10.0F, 10.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-0.0556F, 7.5F, -0.3889F));

		ModelPartData face_1 = partdefinition.addChild("face_1", ModelPartBuilder.create().uv(50, 27).mirrored().cuboid(1.0556F, -6.0F, -4.6111F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(50, 27).mirrored().cuboid(-3.9444F, -7.5F, -4.6111F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(49, 25).mirrored().cuboid(-1.9444F, -2.5F, -4.6111F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-0.0556F, 7.5F, -0.3889F));

		ModelPartData face_2 = partdefinition.addChild("face_2", ModelPartBuilder.create().uv(49, 26).mirrored().cuboid(1.5556F, -8.0F, -4.6111F, 2.5F, 2.5F, 2.5F, new Dilation(0.0F)).mirrored(false)
				.uv(50, 27).mirrored().cuboid(-2.9444F, -5.5F, -4.6111F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(49, 25).mirrored().cuboid(3.0556F, -2.5F, -4.6111F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-0.0556F, 7.5F, -0.3889F));

		ModelPartData face_3 = partdefinition.addChild("face_3", ModelPartBuilder.create().uv(49, 26).mirrored().cuboid(1.5556F, -8.0F, -5.1111F, 2.5F, 2.5F, 2.5F, new Dilation(0.0F)).mirrored(false)
				.uv(52, 22).mirrored().cuboid(-4.4444F, -8.0F, -4.6111F, 2.5F, 2.5F, 2.5F, new Dilation(0.0F)).mirrored(false)
				.uv(52, 25).mirrored().cuboid(-1.9444F, -4.5F, -4.6111F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-0.0556F, 7.5F, -0.3889F));

		ModelPartData face_4 = partdefinition.addChild("face_4", ModelPartBuilder.create().uv(50, 27).mirrored().cuboid(1.0556F, -7.5F, -4.6111F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(52, 22).mirrored().cuboid(-4.4444F, -8.0F, -5.1111F, 2.5F, 2.5F, 2.5F, new Dilation(0.0F)).mirrored(false)
				.uv(49, 25).mirrored().cuboid(-3.9444F, -2.5F, -4.6111F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-0.0556F, 7.5F, -0.3889F));

		ModelPartData face_5 = partdefinition.addChild("face_5", ModelPartBuilder.create().uv(50, 27).mirrored().cuboid(1.0556F, -5.5F, -4.6111F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(52, 22).mirrored().cuboid(-4.4444F, -8.0F, -5.1111F, 2.5F, 2.5F, 2.5F, new Dilation(0.0F)).mirrored(false)
				.uv(49, 25).mirrored().cuboid(0.0556F, -2.5F, -4.6111F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-0.0556F, 7.5F, -0.3889F));

		ModelPartData face_6 = partdefinition.addChild("face_6", ModelPartBuilder.create().uv(50, 27).mirrored().cuboid(2.0556F, -7.5F, -4.6111F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(52, 22).mirrored().cuboid(-3.4444F, -8.5F, -4.6111F, 2.5F, 2.5F, 2.5F, new Dilation(0.0F)).mirrored(false)
				.uv(49, 25).mirrored().cuboid(-1.4444F, -3.5F, -4.6111F, 1.5F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-0.0556F, 7.5F, -0.3889F));

		ModelPartData face_7 = partdefinition.addChild("face_7", ModelPartBuilder.create().uv(52, 22).mirrored().cuboid(1.5556F, -7.0F, -4.6111F, 2.5F, 2.5F, 2.5F, new Dilation(0.0F)).mirrored(false)
				.uv(52, 22).mirrored().cuboid(-3.9444F, -8.0F, -4.6111F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(47, 23).mirrored().cuboid(0.5556F, -2.5F, -4.6111F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-0.0556F, 7.5F, -0.3889F));

		ModelPartData arm0 = partdefinition.addChild("arm0", ModelPartBuilder.create().uv(48, 0).mirrored().cuboid(0.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, 8.5F, 0.0F));

		ModelPartData arm1 = partdefinition.addChild("arm1", ModelPartBuilder.create().uv(56, 0).mirrored().cuboid(-2.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-4.0F, 8.5F, 0.0F));

		ModelPartData leg0 = partdefinition.addChild("leg0", ModelPartBuilder.create().uv(29, 19).mirrored().cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(2.0F, 17.0F, 0.0F));

		ModelPartData leg1 = partdefinition.addChild("leg1", ModelPartBuilder.create().uv(37, 19).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-2.0F, 17.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 32);
	}

	@Override public void setAngles(S renderState)
	{
		float headPitch = renderState.pitch;
		float netHeadYaw = renderState.relativeHeadYaw;
		float limbSwing = renderState.limbSwingAnimationProgress;
		float limbSwingAmount = renderState.limbSwingAmplitude;
		this.faceId = renderState.faceId;
		this.dayTime = renderState.dayTime;
		this.forcedAlphaBonus = renderState.forcedAlphaBonus;
		this.setHeadRot(netHeadYaw, headPitch, this.getZRotAngleFromEntityTiltAngle(renderState));

		this.arm0.roll = -0.1F;
		this.arm1.roll = 0.1F;
		this.arm0.pitch = (-0.2F + 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		this.arm1.pitch = (-0.2F - 1.5F * MathHelper.wrap(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
		this.leg0.pitch = -1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
		this.leg1.pitch = 1.0F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
	}

	private void setHeadRot(float netHeadYaw, float headPitch, float roll)
	{
		this.setHeadXRot(headPitch / 57.3F); this.setHeadYRot(netHeadYaw / 57.3F); this.setHeadZRot(roll);
	}

	private void setHeadXRot(float xrot) {this.head.pitch = xrot; this.face_1.pitch = xrot; this.face_2.pitch = xrot; this.face_3.pitch = xrot; this.face_4.pitch = xrot; this.face_5.pitch = xrot; this.face_6.pitch = xrot; this.face_7.pitch = xrot;}
	private void setHeadYRot(float yrot) {this.head.yaw = yrot; this.face_1.yaw = yrot; this.face_2.yaw = yrot; this.face_3.yaw = yrot; this.face_4.yaw = yrot; this.face_5.yaw = yrot; this.face_6.yaw = yrot; this.face_7.yaw = yrot;}
	private void setHeadZRot(float zrot) {this.head.roll = zrot; this.face_1.roll = zrot; this.face_2.roll = zrot; this.face_3.roll = zrot; this.face_4.roll = zrot; this.face_5.roll = zrot; this.face_6.roll = zrot; this.face_7.roll = zrot;}

	private float getMaxHeadZRot(S renderState) {return renderState.rattleHeadRotZAmplitude;} //0.6F is cool
	private float getZRotAngleFromEntityTiltAngle(S renderState)
	{
		return this.getMaxHeadZRot(renderState) * renderState.rattlingTiltAngle / renderState.maxRattlingTiltAngle;
	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
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

	private static void render(ModelPart modelPart, MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int r, int g, int b, int alpha)
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
}