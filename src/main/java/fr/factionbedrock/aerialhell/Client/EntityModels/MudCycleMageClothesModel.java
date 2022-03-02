package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

//Made with Blockbench 4.0.1
//Exported for Minecraft version 1.15 - 1.16 with MCP mappings

public class MudCycleMageClothesModel extends EntityModel<MudCycleMageEntity>
{
	private final ModelRenderer body;
	private final ModelRenderer crown;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public MudCycleMageClothesModel()
	{
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureOffset(16, 11).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, true);

		crown = new ModelRenderer(this);
		crown.setRotationPoint(0.0F, 0.0F, 0.0F);
		crown.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 3.0F, 8.0F, 0.25F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		rightArm.setTextureOffset(40, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.25F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		leftArm.setTextureOffset(40, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.25F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		rightLeg.setTextureOffset(8, 11).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.25F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		leftLeg.setTextureOffset(0, 11).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.25F, false);
	}

	@Override
	public void setRotationAngles(MudCycleMageEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.crown.rotateAngleY = netHeadYaw / 57.0F;
        this.crown.rotateAngleX = headPitch / 57.0F;
        
        this.rightArm.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
        this.leftArm.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
        
		this.leftLeg.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.rightLeg.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leftLeg.rotateAngleY = 0.0F;
	    this.rightLeg.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		crown.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}