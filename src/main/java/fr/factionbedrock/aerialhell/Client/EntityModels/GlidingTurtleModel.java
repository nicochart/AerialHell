package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.factionbedrock.aerialhell.Entity.Passive.GlidingTurtleEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made by Cixon with Blockbench 4.6.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
public class GlidingTurtleModel extends EntityModel<GlidingTurtleEntity>
{
	private final ModelRenderer Head;
	private final ModelRenderer Body;
	private final ModelRenderer rightHole_r1;
	private final ModelRenderer leftHole_r1;
	private final ModelRenderer WingRight;
	private final ModelRenderer Wing_r1;
	private final ModelRenderer WingLeft;
	private final ModelRenderer Wing_r2;
	private final ModelRenderer LegFrontRight;
	private final ModelRenderer LegFrontLeft;
	private final ModelRenderer LegBackRight;
	private final ModelRenderer LegBackLeft;
	private boolean isGliding;
	public GlidingTurtleModel()
	{
		textureWidth = 128;
		textureHeight = 128;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 9.0F, -7.0F);
		Head.setTextureOffset(92, 0).addBox(-4.0F, -5.0F, -10.0F, 8.0F, 6.0F, 10.0F, 0.0F, false);
		Head.setTextureOffset(92, 16).addBox(-4.0F, 1.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 27.0F, 0.0F);
		Body.setTextureOffset(0, 61).addBox(-12.0F, -17.0F, -9.0F, 24.0F, 4.0F, 25.0F, 0.0F, false);
		Body.setTextureOffset(25, 91).addBox(-10.0F, -33.0F, -7.0F, 20.0F, 16.0F, 21.0F, 0.0F, false);
		Body.setTextureOffset(0, 90).addBox(-5.0F, -22.0F, -8.0F, 10.0F, 5.0F, 1.0F, 0.0F, false);

		rightHole_r1 = new ModelRenderer(this);
		rightHole_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(rightHole_r1);
		setRotationAngle(rightHole_r1, 0.0F, 1.5708F, 0.0F);
		rightHole_r1.setTextureOffset(0, 90).addBox(-8.0F, -22.0F, -11.0F, 10.0F, 5.0F, 1.0F, 0.0F, true);

		leftHole_r1 = new ModelRenderer(this);
		leftHole_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(leftHole_r1);
		setRotationAngle(leftHole_r1, 0.0F, -1.5708F, 0.0F);
		leftHole_r1.setTextureOffset(0, 90).addBox(-2.0F, -22.0F, -11.0F, 10.0F, 5.0F, 1.0F, 0.0F, false);

		WingRight = new ModelRenderer(this);
		WingRight.setRotationPoint(-11.0F, 7.0F, 3.0F);


		Wing_r1 = new ModelRenderer(this);
		Wing_r1.setRotationPoint(11.0F, 20.0F, -3.0F);
		WingRight.addChild(Wing_r1);
		setRotationAngle(Wing_r1, -0.2094F, 0.0F, 0.0873F);
		Wing_r1.setTextureOffset(19, 0).addBox(-24.0F, -19.0F, -7.0F, 12.0F, 0.0F, 18.0F, 0.0F, false);

		WingLeft = new ModelRenderer(this);
		WingLeft.setRotationPoint(11.0F, 7.0F, 3.0F);


		Wing_r2 = new ModelRenderer(this);
		Wing_r2.setRotationPoint(-11.0F, 20.0F, -3.0F);
		WingLeft.addChild(Wing_r2);
		setRotationAngle(Wing_r2, -0.2094F, 0.0F, -0.0873F);
		Wing_r2.setTextureOffset(19, 0).addBox(12.0F, -19.0F, -7.0F, 12.0F, 0.0F, 18.0F, 0.0F, true);

		LegFrontRight = new ModelRenderer(this);
		LegFrontRight.setRotationPoint(-6.0F, 14.0F, -4.0F);
		LegFrontRight.setTextureOffset(0, 112).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

		LegFrontLeft = new ModelRenderer(this);
		LegFrontLeft.setRotationPoint(6.0F, 14.0F, -4.0F);
		LegFrontLeft.setTextureOffset(0, 112).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, true);

		LegBackRight = new ModelRenderer(this);
		LegBackRight.setRotationPoint(-6.0F, 14.0F, 10.0F);
		LegBackRight.setTextureOffset(0, 112).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

		LegBackLeft = new ModelRenderer(this);
		LegBackLeft.setRotationPoint(6.0F, 14.0F, 10.0F);
		LegBackLeft.setTextureOffset(0, 112).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, true);
	}

	@Override
	public void setLivingAnimations(GlidingTurtleEntity entity, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTick);
		this.isGliding = entity.isGliding();
	}

	@Override
	public void setRotationAngles(GlidingTurtleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.Head.rotateAngleY = netHeadYaw / 57.29578F;
		this.Head.rotateAngleX = headPitch / 57.29578F;

		this.animateRightLeg(this.LegFrontRight, limbSwing, limbSwingAmount);
		this.animateRightLeg(this.LegBackRight, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.LegFrontLeft, limbSwing, limbSwingAmount);
		this.animateLeftLeg(this.LegBackLeft, limbSwing, limbSwingAmount);

		if (isGliding) {this.WingRight.rotateAngleZ = 0; this.WingLeft.rotateAngleZ = 0;}
		else {this.WingRight.rotateAngleZ = -45F; this.WingLeft.rotateAngleZ = 45F;}
	}

	private void animateLeftLeg(ModelRenderer model, float limbSwing, float limbSwingAmount)
	{
		model.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount; model.rotateAngleY = 0.0F;
	}

	private void animateRightLeg(ModelRenderer model, float limbSwing, float limbSwingAmount)
	{
		model.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount; model.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		if(isChild)
		{
			matrixStack.scale(0.5F, 0.5F, 0.5F);
			matrixStack.translate(0.0F, 1.5F, 0.0F);
		}
		matrixStack.push();

		Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		WingRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		WingLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		LegFrontRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		LegFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		LegBackRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		LegBackLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		matrixStack.pop();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {modelRenderer.rotateAngleX = x; modelRenderer.rotateAngleY = y; modelRenderer.rotateAngleZ = z;}
}