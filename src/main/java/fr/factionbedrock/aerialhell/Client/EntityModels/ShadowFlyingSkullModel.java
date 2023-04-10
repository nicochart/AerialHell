package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.ShadowFlyingSkullEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;


// Made by Cixon with Blockbench
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

public class ShadowFlyingSkullModel<T extends ShadowFlyingSkullEntity> extends EntityModel<T>
{
	private final ModelRenderer skull;
	private final ModelRenderer leftWing;
	private final ModelRenderer wing_r1;
	private final ModelRenderer bone_r1;
	private final ModelRenderer rightWing;
	private final ModelRenderer wing_r2;
	private final ModelRenderer bone_r2;
	private final ModelRenderer jaw;

	public ShadowFlyingSkullModel()
	{
		textureWidth = 64;
		textureHeight = 64;
		skull = new ModelRenderer(this);
		skull.setRotationPoint(3.0F, 24.0F, -3.0F);
		skull.setTextureOffset(0, 0).addBox(-7.0F, -9.0F, -1.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		leftWing = new ModelRenderer(this);
		leftWing.setRotationPoint(4.0F, 19.0F, 0.0F);
		

		wing_r1 = new ModelRenderer(this);
		wing_r1.setRotationPoint(2.0F, -4.0F, 0.0F);
		leftWing.addChild(wing_r1);
		setRotationAngle(wing_r1, 0.0F, 0.0F, -0.3054F);
		wing_r1.setTextureOffset(48, 25).addBox(-0.3F, -3.0F, 0.0F, 6.0F, 8.0F, 0.0F, 0.0F, false);

		bone_r1 = new ModelRenderer(this);
		bone_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		leftWing.addChild(bone_r1);
		setRotationAngle(bone_r1, 0.0F, 0.0F, -0.9599F);
		bone_r1.setTextureOffset(48, 15).addBox(-2.0F, -2.0F, 0.0F, 7.0F, 3.0F, 0.0F, 0.0F, false);

		rightWing = new ModelRenderer(this);
		rightWing.setRotationPoint(-4.0F, 19.0F, 0.0F);
		

		wing_r2 = new ModelRenderer(this);
		wing_r2.setRotationPoint(-2.0F, -4.0F, 0.0F);
		rightWing.addChild(wing_r2);
		setRotationAngle(wing_r2, 0.0F, 0.0F, 0.3054F);
		wing_r2.setTextureOffset(48, 25).addBox(-5.7F, -3.0F, 0.0F, 6.0F, 8.0F, 0.0F, 0.0F, true);

		bone_r2 = new ModelRenderer(this);
		bone_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		rightWing.addChild(bone_r2);
		setRotationAngle(bone_r2, 0.0F, 0.0F, 0.9599F);
		bone_r2.setTextureOffset(48, 15).addBox(-5.0F, -2.0F, 0.0F, 7.0F, 3.0F, 0.0F, 0.0F, true);

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 20.0F, 1.0F);
		jaw.setTextureOffset(0, 18).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 4.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.jaw.rotateAngleX = entity.jawOpeningAmplitude * MathHelper.sqrt(1.0F + MathHelper.cos(ageInTicks / entity.jawOpeningFrequencyMalus * 3.2F));
		this.rightWing.rotateAngleY = 0.47123894F + MathHelper.cos(ageInTicks * 0.8F) * 0.3F;
		this.leftWing.rotateAngleY = -this.rightWing.rotateAngleY;
		this.leftWing.rotateAngleZ = -0.47123894F;
		this.leftWing.rotateAngleX = 0.47123894F;
		this.rightWing.rotateAngleX = 0.47123894F;
		this.rightWing.rotateAngleZ = 0.47123894F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		skull.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leftWing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		rightWing.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		jaw.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {modelRenderer.rotateAngleX = x; modelRenderer.rotateAngleY = y; modelRenderer.rotateAngleZ = z;}
}