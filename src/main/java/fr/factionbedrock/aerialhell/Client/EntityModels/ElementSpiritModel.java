package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.AbstractElementSpiritEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public class ElementSpiritModel<T extends AbstractElementSpiritEntity> extends EntityModel<T>
{
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rightArm;

	public ElementSpiritModel()
	{
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(4, 8).addBox(-2.5F, -6.0F, -1.0F, 5.0F, 4.0F, 3.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 18.0F, 0.0F);
		head.setTextureOffset(2, 0).addBox(-2.5F, -3.0F, -2.0F, 5.0F, 3.0F, 5.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-3.0F, 19.0F, 0.5F);
		leftArm.setTextureOffset(20, 11).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
		leftLeg.setTextureOffset(12, 15).addBox(-1.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-2.0F, 19.0F, 0.5F);
		rightLeg.setTextureOffset(8, 15).addBox(2.5F, 3.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(3.0F, 19.0F, 0.5F);
		rightArm.setTextureOffset(0, 11).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / 57.0F;
        this.head.rotateAngleX = headPitch / 57.0F;
        
        this.rightArm.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 1.2F;
        this.leftArm.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 1.2F;
        
		this.leftLeg.rotateAngleX = -1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.rightLeg.rotateAngleX = 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leftLeg.rotateAngleY = 0.0F;
	    this.rightLeg.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}