package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made by Cixon
// Made with Blockbench

public class SandySheepModel extends EntityModel<SandySheepEntity>
{
	private final ModelRenderer head;
	private final ModelRenderer snout_r1;
	private final ModelRenderer body;
	private final ModelRenderer rightFrontLeg;
	private final ModelRenderer rightFrontLegCoat;
	private final ModelRenderer rightBackLeg;
	private final ModelRenderer rightBackLegCoat;
	private final ModelRenderer leftFrontLeg;
	private final ModelRenderer leftFrontLegCoat;
	private final ModelRenderer leftBackLeg;
	private final ModelRenderer leftBackLegCoat;
	private final ModelRenderer headCoat;
	private final ModelRenderer bodyCoat;
	private boolean hasWool;
	
	public SandySheepModel()
	{
		textureWidth = 128;
		textureHeight = 64;
		
		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 8.0F, -8.0F);
		head.setTextureOffset(0, 0).addBox(-2.5F, -4.0F, -7.0F, 6.0F, 6.0F, 8.0F, 0.0F, false);

		snout_r1 = new ModelRenderer(this);
		snout_r1.setRotationPoint(0.0F, 16.0F, 8.0F);
		head.addChild(snout_r1);
		setRotationAngle(snout_r1, 0.0698F, 0.0F, 0.0F);
		snout_r1.setTextureOffset(28, 8).addBox(-1.0F, -18.01F, -16.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(80, 42).addBox(-3.5F, -17.0F, -8.5F, 8.0F, 6.0F, 16.0F, 0.0F, false);

		rightFrontLeg = new ModelRenderer(this);
		rightFrontLeg.setRotationPoint(-3.0F, 16.0F, -6.0F);
		rightFrontLeg.setTextureOffset(0, 49).addBox(-1.0F, -4.0F, -2.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		rightFrontLegCoat = new ModelRenderer(this);
		rightFrontLegCoat.setRotationPoint(-3.0F, 16.0F, -6.0F);
		rightFrontLegCoat.setTextureOffset(12, 46).addBox(-3.0F, -5.0F, -5.0F, 7.0F, 10.0F, 8.0F, -1.0F, true);

		rightBackLeg = new ModelRenderer(this);
		rightBackLeg.setRotationPoint(-3.0F, 16.0F, 5.0F);
		rightBackLeg.setTextureOffset(0, 49).addBox(-1.0F, -4.0F, -1.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		rightBackLegCoat = new ModelRenderer(this);
		rightBackLegCoat.setRotationPoint(-3.0F, 16.0F, 5.0F);
		rightBackLegCoat.setTextureOffset(12, 46).addBox(-3.0F, -5.0F, -3.5F, 7.0F, 10.0F, 8.0F, -1.0F, true);

		leftFrontLeg = new ModelRenderer(this);
		leftFrontLeg.setRotationPoint(4.0F, 16.0F, -6.0F);
		leftFrontLeg.setTextureOffset(0, 49).addBox(-2.0F, -4.0F, -2.0F, 3.0F, 12.0F, 3.0F, 0.0F, true);

		leftFrontLegCoat = new ModelRenderer(this);
		leftFrontLegCoat.setRotationPoint(4.0F, 16.0F, -6.0F);
		leftFrontLegCoat.setTextureOffset(12, 46).addBox(-4.0F, -5.0F, -5.0F, 7.0F, 10.0F, 8.0F, -1.0F, false);

		leftBackLeg = new ModelRenderer(this);
		leftBackLeg.setRotationPoint(4.0F, 16.0F, 5.0F);
		leftBackLeg.setTextureOffset(0, 49).addBox(-2.0F, -4.0F, -1.0F, 3.0F, 12.0F, 3.0F, 0.0F, true);

		leftBackLegCoat = new ModelRenderer(this);
		leftBackLegCoat.setRotationPoint(4.0F, 16.0F, 5.0F);
		leftBackLegCoat.setTextureOffset(12, 46).addBox(-4.0F, -5.0F, -3.5F, 7.0F, 10.0F, 8.0F, -1.0F, false);

		headCoat = new ModelRenderer(this);
		headCoat.setRotationPoint(0.0F, 8.0F, -8.0F);
		headCoat.setTextureOffset(40, 0).addBox(-3.0F, -5.0F, -8.0F, 7.0F, 5.0F, 9.0F, 0.0F, false);

		bodyCoat = new ModelRenderer(this);
		bodyCoat.setRotationPoint(0.0F, 24.0F, 0.0F);
		bodyCoat.setTextureOffset(0, 14).addBox(-4.5F, -18.0F, -11.0F, 10.0F, 10.0F, 20.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(SandySheepEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / 57.29578F;
        this.head.rotateAngleX = headPitch / 57.29578F;
        this.headCoat.rotateAngleY = netHeadYaw / 57.29578F;
        this.headCoat.rotateAngleX = headPitch / 57.29578F;
        
        this.animateRightLeg(this.rightFrontLeg, limbSwing, limbSwingAmount);
        this.animateRightLeg(this.rightBackLeg, limbSwing, limbSwingAmount);
        this.animateRightLeg(this.rightFrontLegCoat, limbSwing, limbSwingAmount);
        this.animateRightLeg(this.rightBackLegCoat, limbSwing, limbSwingAmount);
        this.animateLeftLeg(this.leftFrontLeg, limbSwing, limbSwingAmount);
        this.animateLeftLeg(this.leftBackLeg, limbSwing, limbSwingAmount);
        this.animateLeftLeg(this.leftFrontLegCoat, limbSwing, limbSwingAmount);
        this.animateLeftLeg(this.leftBackLegCoat, limbSwing, limbSwingAmount);
	}
	
	private void animateLeftLeg(ModelRenderer model, float limbSwing, float limbSwingAmount)
	{
		model.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		model.rotateAngleY = 0.0F;
	}
	
	private void animateRightLeg(ModelRenderer model, float limbSwing, float limbSwingAmount)
	{
		model.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		model.rotateAngleY = 0.0F;
	}
	
	@Override
	public void setLivingAnimations(SandySheepEntity entity, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTick);
		this.hasWool = entity.hasWool();
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
        
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		if (this.hasWool)
		{
			bodyCoat.render(matrixStack, buffer, packedLight, packedOverlay);
			rightFrontLegCoat.render(matrixStack, buffer, packedLight, packedOverlay);
			rightBackLegCoat.render(matrixStack, buffer, packedLight, packedOverlay);
			leftFrontLegCoat.render(matrixStack, buffer, packedLight, packedOverlay);
			leftBackLegCoat.render(matrixStack, buffer, packedLight, packedOverlay);
			headCoat.render(matrixStack, buffer, packedLight, packedOverlay);
		}
		matrixStack.pop();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {modelRenderer.rotateAngleX = x; modelRenderer.rotateAngleY = y; modelRenderer.rotateAngleZ = z;}
}