package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made by Cixon
// Made with Blockbench

public class MudCycleMageModel extends EntityModel<MudCycleMageEntity>
{
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm_r1;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightArm_r1;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public MudCycleMageModel()
	{
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(32, 53).addBox(-4.0F, -10.0F, -3.0F, 8.0F, 3.0F, 8.0F, 0.25F, true);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(16, 32).addBox(-4.0F, -24.0F, -1.0F, 8.0F, 22.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(16, 16).addBox(-4.0F, -24.0F, -1.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(4.0F, 2.0F, 1.0F);
		leftArm.setTextureOffset(48, 37).addBox(0.5F, 1.0F, -8.0F, 0.0F, 6.0F, 7.0F, 0.0F, false);
		leftArm.setTextureOffset(40, 36).addBox(-0.5F, -2.0F, -9.0F, 3.0F, 8.0F, 1.0F, 0.0F, false);

		leftArm_r1 = new ModelRenderer(this);
		leftArm_r1.setRotationPoint(-4.0F, 22.0F, -1.0F);
		leftArm.addChild(leftArm_r1);
		setRotationAngle(leftArm_r1, -1.5708F, 0.0F, 0.0F);
		leftArm_r1.setTextureOffset(40, 16).addBox(4.0F, -2.0F, -23.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-4.0F, 2.0F, 0.0F);
		rightArm.setTextureOffset(48, 37).addBox(-0.5F, 1.0F, -7.0F, 0.0F, 6.0F, 7.0F, 0.0F, true);
		rightArm.setTextureOffset(40, 36).addBox(-2.5F, -2.0F, -8.0F, 3.0F, 8.0F, 1.0F, 0.0F, false);

		rightArm_r1 = new ModelRenderer(this);
		rightArm_r1.setRotationPoint(4.0F, 22.0F, 0.0F);
		rightArm.addChild(rightArm_r1);
		setRotationAngle(rightArm_r1, -1.5708F, 0.0F, 0.0F);
		rightArm_r1.setTextureOffset(40, 16).addBox(-6.0F, -2.0F, -23.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-2.0F, 13.0F, 1.0F);
		rightLeg.setTextureOffset(0, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(2.0F, 13.0F, 1.0F);
		leftLeg.setTextureOffset(0, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);
	}
	
	@Override
	public void setRotationAngles(MudCycleMageEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / 57.0F;
        this.head.rotateAngleX = headPitch / 57.0F;
        
        this.rightArm.rotateAngleX = (-0.2F + 0.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
        this.leftArm.rotateAngleX = (-0.2F - 0.5F * MathHelper.func_233021_e_(limbSwing, 13.0F)) * limbSwingAmount;
        
		this.leftLeg.rotateAngleX = -1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.rightLeg.rotateAngleX = 1.0F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
	    this.leftLeg.rotateAngleY = 0.0F;
	    this.rightLeg.rotateAngleY = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {modelRenderer.rotateAngleX = x;	modelRenderer.rotateAngleY = y;	modelRenderer.rotateAngleZ = z;}
}