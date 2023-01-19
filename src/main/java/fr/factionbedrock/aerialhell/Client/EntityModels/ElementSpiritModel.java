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
	private final ModelRenderer head;
	private final ModelRenderer headShard;
	private final ModelRenderer headShard_r1;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;

	public ElementSpiritModel()
	{
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 6.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);

		headShard = new ModelRenderer(this);
		headShard.setRotationPoint(0.0F, 6.0F, 0.0F);
		

		headShard_r1 = new ModelRenderer(this);
		headShard_r1.setRotationPoint(0.0F, 18.0F, 0.0F);
		headShard.addChild(headShard_r1);
		setRotationAngle(headShard_r1, 0.0F, 0.0F, 0.2182F);
		headShard_r1.setTextureOffset(42, 10).addBox(-15.0F, -32.0F, -3.0F, 10.0F, 10.0F, 0.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 20).addBox(-4.0F, -17.0F, -2.0F, 8.0F, 12.0F, 4.0F, 1.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(5.0F, 6.0F, 0.0F);
		leftArm.setTextureOffset(24, 20).addBox(0.0F, 0.0F, -3.0F, 3.0F, 5.0F, 6.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-5.0F, 6.0F, 0.0F);
		rightArm.setTextureOffset(24, 20).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 5.0F, 6.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-2.0F, 17.0F, 0.0F);
		leftLeg.setTextureOffset(0, 36).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(2.0F, 17.0F, 0.0F);
		rightLeg.setTextureOffset(0, 36).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / 57.0F;
        this.head.rotateAngleX = headPitch / 57.0F;
        this.headShard.rotateAngleY = netHeadYaw / 57.0F;
        this.headShard.rotateAngleX = headPitch / 57.0F;
        
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
		headShard.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {modelRenderer.rotateAngleX = x; modelRenderer.rotateAngleY = y; modelRenderer.rotateAngleZ = z;}
}