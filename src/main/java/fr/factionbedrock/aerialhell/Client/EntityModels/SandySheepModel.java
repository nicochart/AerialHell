package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings

public class SandySheepModel extends EntityModel<SandySheepEntity>
{	
	private final ModelRenderer body;
	private final ModelRenderer body_r1;
	private final ModelRenderer head;
	private final ModelRenderer head_coat;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer coat;
	private final ModelRenderer coat_r1;
	private boolean hasWool;
	
	public SandySheepModel()
	{
		textureWidth = 128;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 5.0F, 2.0F);

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, -2.0F, -4.0F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, -1.5708F, 0.0F, 0.0F);
		body_r1.setTextureOffset(2, 16).addBox(0.0F, -16.0F, 2.0F, 0.0F, 5.0F, 6.0F, 0.0F, true);
		body_r1.setTextureOffset(5, 24).addBox(0.0F, -14.0F, -1.0F, 0.0F, 19.0F, 3.0F, 0.0F, true);
		body_r1.setTextureOffset(15, 25).addBox(-5.0F, -11.0F, 2.0F, 10.0F, 16.0F, 7.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 6.0F, -8.0F);
		head.setTextureOffset(24, 11).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F, true);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(3.0F, 12.0F, 7.0F);
		leg0.setTextureOffset(48, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
		leg1.setTextureOffset(32, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(3.0F, 12.0F, -5.0F);
		leg2.setTextureOffset(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
		leg3.setTextureOffset(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		coat = new ModelRenderer(this);
		coat.setRotationPoint(0.0F, 5.0F, 2.0F);

		coat_r1 = new ModelRenderer(this);
		coat_r1.setRotationPoint(0.0F, -2.0F, -4.0F);
		coat.addChild(coat_r1);
		setRotationAngle(coat_r1, -1.5708F, 0.0F, 0.0F);
		coat_r1.setTextureOffset(114, 43).addBox(-3.0F, -13.0F, 13.0F, 6.0F, 20.0F, 1.0F, 0.0F, true);
		coat_r1.setTextureOffset(72, 54).addBox(-4.0F, -14.0F, 2.0F, 8.0F, 1.0F, 9.0F, 0.0F, true);
		coat_r1.setTextureOffset(64, 21).addBox(-6.0F, -13.0F, 0.0F, 12.0F, 20.0F, 13.0F, 0.0F, true);
		coat_r1.setTextureOffset(90, -5).addBox(-8.0F, -13.0F, 12.0F, 0.0F, 20.0F, 5.0F, 0.0F, true);
		coat_r1.setTextureOffset(40, 0).addBox(-8.0F, 7.0F, 12.0F, 16.0F, 0.0F, 5.0F, 0.0F, true);
		coat_r1.setTextureOffset(40, 0).addBox(-8.0F, -13.0F, 12.0F, 16.0F, 0.0F, 5.0F, 0.0F, true);
		coat_r1.setTextureOffset(78, -5).addBox(8.0F, -13.0F, 12.0F, 0.0F, 20.0F, 5.0F, 0.0F, true);
		coat_r1.setTextureOffset(101, 5).addBox(6.0F, -13.0F, 3.0F, 2.0F, 20.0F, 9.0F, 0.0F, true);
		coat_r1.setTextureOffset(55, 5).addBox(-8.0F, -13.0F, 3.0F, 2.0F, 20.0F, 9.0F, 0.0F, true);

		head_coat = new ModelRenderer(this);
		head_coat.setRotationPoint(0.0F, 6.0F, -8.0F);
		head_coat.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -5.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
	}
	
	@Override
	public void setRotationAngles(SandySheepEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / 57.29578F;
        this.head.rotateAngleX = headPitch / 57.29578F;
        this.head_coat.rotateAngleY = netHeadYaw / 57.29578F;
        this.head_coat.rotateAngleX = headPitch / 57.29578F;
        
        this.leg0.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leg1.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leg2.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leg3.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leg0.rotateAngleY = 0.0F; this.leg1.rotateAngleY = 0.0F; this.leg2.rotateAngleY = 0.0F; this.leg3.rotateAngleY = 0.0F;
	    
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
        
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg2.render(matrixStack, buffer, packedLight, packedOverlay);
		leg3.render(matrixStack, buffer, packedLight, packedOverlay);
		if (this.hasWool)
		{
			coat.render(matrixStack, buffer, packedLight, packedOverlay);
			head_coat.render(matrixStack, buffer, packedLight, packedOverlay);
		}
		matrixStack.pop();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}