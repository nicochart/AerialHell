package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

public class LunaticPriestModel extends EntityModel<LunaticPriestEntity>
{
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer arm0;
	private final ModelRenderer arm1;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;

	public LunaticPriestModel()
	{
		textureWidth = 128;
		textureHeight = 64;
		
		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.setTextureOffset(0, 32).addBox(-7.0F, 1.0F, -4.0F, 14.0F, 14.0F, 8.0F, 0.0F, true);
		body.setTextureOffset(7, 53).addBox(-4.5F, 15.0F, -3.0F, 9.0F, 5.0F, 6.0F, 0.5F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -7.0F, -1.0F);
		head.setTextureOffset(6, 16).addBox(-4.0F, -6.0F, -5.5F, 8.0F, 8.0F, 8.0F, 0.0F, true);
		head.setTextureOffset(4, 0).addBox(-5.0F, -7.0F, -4.5F, 10.0F, 8.0F, 8.0F, 0.0F, true);
		head.setTextureOffset(7, 1).addBox(-4.0F, -8.0F, -2.5F, 8.0F, 7.0F, 7.0F, 0.0F, true);
		head.setTextureOffset(15, 6).addBox(-3.0F, -8.0F, 5.5F, 6.0F, 4.0F, 1.0F, 0.0F, true);
		head.setTextureOffset(10, 2).addBox(-3.0F, -9.0F, 0.5F, 6.0F, 5.0F, 5.0F, 0.0F, true);
		head.setTextureOffset(15, 5).addBox(-2.0F, -7.0F, 5.5F, 4.0F, 4.0F, 3.0F, 0.0F, true);
		head.setTextureOffset(18, 6).addBox(-1.0F, -4.0F, 7.5F, 2.0F, 3.0F, 2.0F, 0.0F, true);
		head.setTextureOffset(40, 29).addBox(-2.0F, 2.0F, -5.5F, 4.0F, 1.0F, 2.0F, 0.0F, true);
		head.setTextureOffset(41, 27).addBox(-2.0F, -7.0F, -5.5F, 4.0F, 1.0F, 1.0F, 0.0F, true);

		arm0 = new ModelRenderer(this);
		arm0.setRotationPoint(6.0F, -4.0F, 0.0F);
		arm0.setTextureOffset(72, 40).addBox(1.0F, -0.5F, -2.0F, 2.0F, 20.0F, 4.0F, 0.0F, true);
		arm0.setTextureOffset(86, 41).addBox(3.0F, 8.5F, -4.0F, 1.0F, 3.0F, 8.0F, 0.0F, true);
		arm0.setTextureOffset(90, 36).addBox(3.0F, 7.5F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		arm0.setTextureOffset(88, 44).addBox(3.0F, 10.5F, -5.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm0.setTextureOffset(96, 44).addBox(3.0F, 10.5F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);

		arm1 = new ModelRenderer(this);
		arm1.setRotationPoint(-6.0F, -4.0F, 0.0F);
		arm1.setTextureOffset(57, 40).addBox(-3.0F, -0.5F, -2.0F, 2.0F, 20.0F, 4.0F, 0.0F, true);
		arm1.setTextureOffset(86, 41).addBox(-4.0F, 8.5F, -4.0F, 1.0F, 3.0F, 8.0F, 0.0F, true);
		arm1.setTextureOffset(90, 36).addBox(-4.0F, 7.5F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		arm1.setTextureOffset(87, 44).addBox(-4.0F, 10.5F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		arm1.setTextureOffset(96, 44).addBox(-4.0F, 10.5F, -5.0F, 1.0F, 2.0F, 2.0F, 0.0F, true);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(4.0F, 11.0F, 0.0F);
		leg0.setTextureOffset(54, 13).addBox(-2.5F, 2.0F, -2.0F, 3.0F, 11.0F, 3.0F, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-5.0F, 11.0F, 0.0F);
		leg1.setTextureOffset(70, 13).addBox(0.5F, 2.0F, -2.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(LunaticPriestEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / 57.29578F;
        this.head.rotateAngleX = headPitch / 57.29578F;
        
        int i = entity.attackTimer;
        if (i > 0)
        {
            this.arm0.rotateAngleX = -2.0F + 0.6F * MathHelper.func_233021_e_((float)i, 10.0F);
            this.arm1.rotateAngleX = -2.0F + 0.6F * MathHelper.func_233021_e_((float)i, 10.0F);
        }
        else
        {
        	this.arm0.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
        	this.arm1.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount * 0.6F;
        }
        
		this.leg0.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leg1.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leg0.rotateAngleY = 0.0F;
	    this.leg1.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		arm0.render(matrixStack, buffer, packedLight, packedOverlay);
		arm1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}