package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made by Cixon with Blockbench

public class LilithModel extends EntityModel<LilithEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer wingRight;
	private final ModelRenderer wingRight_r1;
	private final ModelRenderer wingLeft;
	private final ModelRenderer wingLeft_r1;
	private final ModelRenderer legLeft;
	private final ModelRenderer legRight;
	private final ModelRenderer armLeft;
	private final ModelRenderer armRight;

	public LilithModel()
	{
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(17, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		wingRight = new ModelRenderer(this);
		wingRight.setRotationPoint(-2.0F, 5.0F, 2.0F);
		

		wingRight_r1 = new ModelRenderer(this);
		wingRight_r1.setRotationPoint(2.0F, 19.0F, -2.0F);
		wingRight.addChild(wingRight_r1);
		setRotationAngle(wingRight_r1, 0.2094F, -0.6981F, 0.0F);
		wingRight_r1.setTextureOffset(0, 30).addBox(-0.5F, -25.0F, 4.0F, 0.0F, 13.0F, 21.0F, 0.0F, false);

		wingLeft = new ModelRenderer(this);
		wingLeft.setRotationPoint(2.0F, 5.0F, 2.0F);
		

		wingLeft_r1 = new ModelRenderer(this);
		wingLeft_r1.setRotationPoint(-2.0F, 19.0F, -2.0F);
		wingLeft.addChild(wingLeft_r1);
		setRotationAngle(wingLeft_r1, 0.2094F, 0.6981F, 0.0F);
		wingLeft_r1.setTextureOffset(0, 30).addBox(0.5F, -25.0F, 4.0F, 0.0F, 13.0F, 21.0F, 0.0F, false);

		legLeft = new ModelRenderer(this);
		legLeft.setRotationPoint(2.0F, 12.0F, 0.0F);
		legLeft.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		legRight = new ModelRenderer(this);
		legRight.setRotationPoint(-2.0F, 12.0F, 0.0F);
		legRight.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		armLeft = new ModelRenderer(this);
		armLeft.setRotationPoint(4.0F, 0.0F, 0.0F);
		armLeft.setTextureOffset(41, 16).addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		armRight = new ModelRenderer(this);
		armRight.setRotationPoint(-4.0F, 0.0F, 0.0F);
		armRight.setTextureOffset(41, 16).addBox(-4.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(LilithEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!entity.isTransforming())
        {
        	int i = entity.attackTimer;
            if (i > 0)
            {
                this.armRight.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float)i, 10.0F) * 0.5f;
                this.armLeft.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float)i, 10.0F) * 0.5f;
            }
            else
            {
            	this.armRight.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
            	this.armLeft.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
            }
        }
        else
        {
        	this.armRight.rotateAngleX = - 2.2F;
        	this.armLeft.rotateAngleX = - 2.2F;
        }
		this.head.rotateAngleY = netHeadYaw / 57.3F;
        this.head.rotateAngleX = headPitch / 57.3F;
        this.legLeft.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount; this.legLeft.rotateAngleY = 0.0F;
	    this.legRight.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount; this.legRight.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		wingRight.render(matrixStack, buffer, packedLight, packedOverlay);
		wingLeft.render(matrixStack, buffer, packedLight, packedOverlay);
		legLeft.render(matrixStack, buffer, packedLight, packedOverlay);
		legRight.render(matrixStack, buffer, packedLight, packedOverlay);
		armLeft.render(matrixStack, buffer, packedLight, packedOverlay);
		armRight.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {modelRenderer.rotateAngleX = x; modelRenderer.rotateAngleY = y; modelRenderer.rotateAngleZ = z;}
}