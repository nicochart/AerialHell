package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.AbstractBarrelMimicEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Base model made by Cixon with Blockbench
// Edited in-ide by Nicochart to mimic vanilla animation
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

public class SpiderBarrelMimicModel<T extends AbstractBarrelMimicEntity> extends EntityModel<T>
{
	private final ModelRenderer body0;
	private final ModelRenderer teethbottom_r1;
	private final ModelRenderer teethtop_r1;
	private final ModelRenderer main_r1;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer leg7;

	public SpiderBarrelMimicModel()
	{
		textureWidth = 64;
		textureHeight = 64;

		body0 = new ModelRenderer(this);
		body0.setRotationPoint(0.0F, 15.0F, 9.0F);

		teethbottom_r1 = new ModelRenderer(this);
		teethbottom_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body0.addChild(teethbottom_r1);
		setRotationAngle(teethbottom_r1, 0.6283F, 0.0F, 0.0F);
		teethbottom_r1.setTextureOffset(19, 10).addBox(-6.0F, -11.0F, -16.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);

		teethtop_r1 = new ModelRenderer(this);
		teethtop_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body0.addChild(teethtop_r1);
		setRotationAngle(teethtop_r1, -0.6283F, 0.0F, 0.0F);
		teethtop_r1.setTextureOffset(19, 2).addBox(-6.0F, 2.0F, -17.0F, 12.0F, 4.0F, 0.0F, 0.0F, false);

		main_r1 = new ModelRenderer(this);
		main_r1.setRotationPoint(0.0F, 9.0F, -9.0F);
		body0.addChild(main_r1);
		setRotationAngle(main_r1, 1.3963F, 0.0F, 0.0F);
		main_r1.setTextureOffset(0, 32).addBox(-8.0F, -9.0F, 2.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		leg0 = new ModelRenderer(this, 13, 17);
		leg0.setRotationPoint(-4.0F, 15.0F, 2.0F);
		leg0.addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);

		leg1 = new ModelRenderer(this, 13, 17);
		leg1.addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
		leg1.setRotationPoint(4.0F, 15.0F, 2.0F);

		leg2 = new ModelRenderer(this, 13, 17);
		leg2.setRotationPoint(-4.0F, 15.0F, 1.0F);
		leg2.addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);

		leg3 = new ModelRenderer(this, 13, 17);
		leg3.setRotationPoint(4.0F, 15.0F, 1.0F);
		leg3.addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);

		leg4 = new ModelRenderer(this, 13, 17);
		leg4.setRotationPoint(4.0F, 15.0F, 0.0F);
		leg4.addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);

		leg5 = new ModelRenderer(this, 13, 17);
		leg5.setRotationPoint(4.0F, 15.0F, 0.0F);
		leg5.addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);

		leg6 = new ModelRenderer(this, 13, 17);
		leg6.setRotationPoint(-4.0F, 15.0F, -1.0F);
		leg6.addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);

		leg7 = new ModelRenderer(this, 13, 17);
		leg7.setRotationPoint(4.0F, 15.0F, -1.0F);
		leg7.addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
	}

	@Override
	public void setRotationAngles(AbstractBarrelMimicEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.leg0.rotateAngleZ = (-(float)Math.PI / 4F); this.leg0.rotateAngleY = ((float)Math.PI / 4F);
		this.leg1.rotateAngleZ = ((float)Math.PI / 4F); this.leg1.rotateAngleY = (-(float)Math.PI / 4F);
		this.leg2.rotateAngleZ = -0.58119464F; this.leg2.rotateAngleY = ((float)Math.PI / 8F);
		this.leg3.rotateAngleZ = 0.58119464F; this.leg3.rotateAngleY = (-(float)Math.PI / 8F);
		this.leg4.rotateAngleZ = -0.58119464F; this.leg4.rotateAngleY = (-(float)Math.PI / 8F);
		this.leg5.rotateAngleZ = 0.58119464F; this.leg5.rotateAngleY = ((float)Math.PI / 8F);
		this.leg6.rotateAngleZ = (-(float)Math.PI / 4F); this.leg6.rotateAngleY = (-(float)Math.PI / 4F);
		this.leg7.rotateAngleZ = ((float)Math.PI / 4F); this.leg7.rotateAngleY = ((float)Math.PI / 4F);
		float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
		float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
		float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
		float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
		float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
		float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
		float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
		this.leg0.rotateAngleY += f3;
		this.leg1.rotateAngleY += -f3;
		this.leg2.rotateAngleY += f4;
		this.leg3.rotateAngleY += -f4;
		this.leg4.rotateAngleY += f5;
		this.leg5.rotateAngleY += -f5;
		this.leg6.rotateAngleY += f6;
		this.leg7.rotateAngleY += -f6;
		this.leg0.rotateAngleZ += f7;
		this.leg1.rotateAngleZ += -f7;
		this.leg2.rotateAngleZ += f8;
		this.leg3.rotateAngleZ += -f8;
		this.leg4.rotateAngleZ += f9;
		this.leg5.rotateAngleZ += -f9;
		this.leg6.rotateAngleZ += f10;
		this.leg7.rotateAngleZ += -f10;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body0.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {modelRenderer.rotateAngleX = x; modelRenderer.rotateAngleY = y; modelRenderer.rotateAngleZ = z;}
}