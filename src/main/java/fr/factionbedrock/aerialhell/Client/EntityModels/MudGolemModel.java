package fr.factionbedrock.aerialhell.Client.EntityModels;

//Made by Cixon using BlockBench

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.AerialHellGolemEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MudGolemModel<T extends AerialHellGolemEntity> extends EntityModel<T>
{
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;

	public MudGolemModel()
	{
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -4.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-7.0F, -9.8F, -4.5F, 14.0F, 10.0F, 8.0F, -0.2F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 19).addBox(-10.0F, -29.0F, -5.0F, 20.0F, 12.0F, 10.0F, -1.0F, false);
		body.setTextureOffset(9, 42).addBox(-7.5F, -18.0F, -2.5F, 15.0F, 5.0F, 5.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(9.0F, -3.0F, 0.0F);
		left_arm.setTextureOffset(62, 54).addBox(0.0F, -1.0F, -2.5F, 5.0F, 25.0F, 5.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-9.0F, -3.0F, 0.0F);
		right_arm.setTextureOffset(62, 19).addBox(-5.0F, -1.0F, -2.5F, 5.0F, 25.0F, 5.0F, 0.0F, true);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(5.0F, 11.0F, 0.0F);
		left_leg.setTextureOffset(18, 72).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 13.0F, 5.0F, 0.0F, true);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 11.0F, 0.0F);
		right_leg.setTextureOffset(18, 53).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 13.0F, 5.0F, 0.0F, false);
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		
		int i = entityIn.attackTimer;
        if (i > 0)
        {
            this.left_arm.rotateAngleX = -2.0F + 0.6F * MathHelper.func_233021_e_((float)i, 10.0F);
            this.right_arm.rotateAngleX = -2.0F + 0.6F * MathHelper.func_233021_e_((float)i, 10.0F);
        }
        else
        {
        	this.left_arm.rotateAngleX = (-0.2F + 0.8F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
        	this.right_arm.rotateAngleX = (-0.2F - 0.8F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
        }
        
		this.left_leg.rotateAngleX = -1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.right_leg.rotateAngleX = 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.left_leg.rotateAngleY = 0.0F; this.right_leg.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
		right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
        this.right_arm.rotateAngleX = (-0.2F + 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
		this.left_arm.rotateAngleX = (-0.2F - 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {modelRenderer.rotateAngleX = x; modelRenderer.rotateAngleY = y; modelRenderer.rotateAngleZ = z;}
}