package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.CrystalSlimeEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings

public class CrystalSlimeModel extends EntityModel<CrystalSlimeEntity>
{
	private final ModelRenderer cube;
	private final ModelRenderer eye0;
	private final ModelRenderer eye1;
	private final ModelRenderer mouth;
	private final ModelRenderer crystal;

	public CrystalSlimeModel(boolean IsGelAndCrystal)
	{
		textureWidth = 64;
		textureHeight = 32;

		cube = new ModelRenderer(this);
		eye0 = new ModelRenderer(this);
		eye1 = new ModelRenderer(this);
		mouth = new ModelRenderer(this);
		crystal = new ModelRenderer(this);
		
		if (IsGelAndCrystal)
		{
			cube.setRotationPoint(0.0F, 0.0F, 0.0F);
			cube.setTextureOffset(0, 0).addBox(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
			
			crystal.setRotationPoint(0.0F, -8.0F, 0.0F);
			crystal.setTextureOffset(0, 17).addBox(-4.0F, 19.0F, -1.0F, 6.0F, 7.0F, 0.0F, 0.0F, true);
			crystal.setTextureOffset(0, 19).addBox(-1.0F, 19.0F, -4.0F, 0.0F, 7.0F, 6.0F, 0.0F, true);
			crystal.setTextureOffset(14, 21).addBox(2.0F, 21.0F, 0.0F, 0.0F, 5.0F, 4.0F, 0.0F, true);
			crystal.setTextureOffset(14, 19).addBox(0.0F, 21.0F, 2.0F, 4.0F, 5.0F, 0.0F, 0.0F, true);
			crystal.setTextureOffset(40, 13).addBox(2.0F, 25.0F, 1.0F, 0.0F, 4.0F, 7.0F, 0.0F, true);
			crystal.setTextureOffset(54, 20).addBox(-2.0F, 26.0F, 2.0F, 0.0F, 4.0F, 5.0F, 0.0F, true);
			crystal.setTextureOffset(50, 19).addBox(-4.0F, 28.0F, 2.0F, 4.0F, 0.0F, 5.0F, 0.0F, true);
			crystal.setTextureOffset(36, 24).addBox(0.0F, 27.0F, 1.0F, 4.0F, 0.0F, 7.0F, 0.0F, true);
			crystal.setTextureOffset(24, 18).addBox(2.0F, 25.0F, 0.0F, 7.0F, 6.0F, 0.0F, 0.0F, true);
			crystal.setTextureOffset(18, 25).addBox(2.0F, 28.0F, -3.0F, 7.0F, 0.0F, 6.0F, 0.0F, true);
			crystal.setTextureOffset(37, 6).addBox(-8.0F, 28.0F, -2.0F, 6.0F, 0.0F, 5.0F, 0.0F, true);
			crystal.setTextureOffset(52, 13).addBox(-7.0F, 30.0F, -3.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);
			crystal.setTextureOffset(55, 9).addBox(-7.0F, 29.0F, -2.0F, 4.0F, 3.0F, 0.0F, 0.0F, true);
			crystal.setTextureOffset(42, 12).addBox(-8.0F, 26.0F, 1.0F, 6.0F, 4.0F, 0.0F, 0.0F, true);
		}
		else
		{
			eye0.setRotationPoint(0.0F, 0.0F, 0.0F);
			eye0.setTextureOffset(33, 0).addBox(1.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, 0.0F, true);

			eye1.setRotationPoint(0.0F, 0.0F, 0.0F);
			eye1.setTextureOffset(33, 5).addBox(-3.3F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F, 0.0F, true);

			
			mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
			mouth.setTextureOffset(35, 10).addBox(-1.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		}		
	}

	@Override
	public void setRotationAngles(CrystalSlimeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		cube.render(matrixStack, buffer, packedLight, packedOverlay);
		eye0.render(matrixStack, buffer, packedLight, packedOverlay);
		eye1.render(matrixStack, buffer, packedLight, packedOverlay);
		mouth.render(matrixStack, buffer, packedLight, packedOverlay);
		crystal.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}