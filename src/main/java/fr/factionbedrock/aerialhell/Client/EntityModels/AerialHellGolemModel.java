package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.AerialHellGolemEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AerialHellGolemModel<T extends AerialHellGolemEntity> extends EntityModel<T>
{
	/*Made with Blockbench 3.8.3
 	  Exported for Minecraft version 1.15 - 1.16*/
	
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftLeg;
 	private final ModelRenderer rightLeg;

	public AerialHellGolemModel()
	{
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.setTextureOffset(0, 40).addBox(-9.0F, -4.0F, -6.0F, 18.0F, 14.0F, 11.0F, 0.0F, true);
		body.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9.0F, 5.0F, 6.0F, 0.5F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -7.0F, -2.0F);
		head.setTextureOffset(20, 27).addBox(-6.0F, -15.0F, -2.5F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		head.setTextureOffset(10, 27).addBox(4.0F, -15.0F, -2.5F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		head.setTextureOffset(0, 0).addBox(-4.0F, -19.0F, -5.5F, 8.0F, 15.0F, 8.0F, 0.0F, true);
		head.setTextureOffset(0, 23).addBox(-1.0F, -10.0F, -7.5F, 2.0F, 9.0F, 2.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		leftArm.setTextureOffset(60, 21).addBox(9.0F, -3.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
		rightArm.setTextureOffset(60, 58).addBox(-13.0F, -3.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(4.0F, 11.0F, 0.0F);
		leftLeg.setTextureOffset(37, 0).addBox(-2.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F, true);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-5.0F, 11.0F, 0.0F);
		rightLeg.setTextureOffset(60, 0).addBox(-2.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		
		int i = entityIn.attackTimer;
        if (i > 0)
        {
            this.leftArm.rotateAngleX = -2.0F + 0.6F * MathHelper.func_233021_e_((float)i, 10.0F);
            this.rightArm.rotateAngleX = -2.0F + 0.6F * MathHelper.func_233021_e_((float)i, 10.0F);
        }
        else
        {
        	this.leftArm.rotateAngleX = (-0.2F + 0.8F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
        	this.rightArm.rotateAngleX = (-0.2F - 0.8F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
        }
        
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
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
        this.rightArm.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
		this.leftArm.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
	}
	   

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}