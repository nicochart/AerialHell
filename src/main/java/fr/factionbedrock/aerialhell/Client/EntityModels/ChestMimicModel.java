package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.AbstractChestMimicEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

@OnlyIn(Dist.CLIENT)
public class ChestMimicModel<T extends AbstractChestMimicEntity> extends EntityModel<T>
{
	private final ModelRenderer chestDown;
	private final ModelRenderer chestUp;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rightTalon1;
	private final ModelRenderer rightTalon2;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftTalon1;
	private final ModelRenderer leftTalon2;

	public ChestMimicModel()
	{
		textureWidth = 128;
		textureHeight = 64;

		chestDown = new ModelRenderer(this);
		chestDown.setRotationPoint(0.0F, 0.0F, 0.0F);
		chestDown.setTextureOffset(0, 19).addBox(-8.0F, 3.0F, -7.0F, 16.0F, 10.0F, 16.0F, 0.0F, true);
		chestDown.setTextureOffset(76, 50).addBox(-5.0F, 3.0F, -4.0F, 10.0F, 4.0F, 10.0F, 0.0F, true);
		chestDown.setTextureOffset(18, 12).addBox(-7.0F, 0.0F, -6.0F, 14.0F, 3.0F, 0.0F, 0.0F, true);
		chestDown.setTextureOffset(2, 0).addBox(7.0F, 1.0F, -7.0F, 0.0F, 3.0F, 15.0F, 0.0F, true);
		chestDown.setTextureOffset(32, 0).addBox(-7.0F, 1.0F, -7.0F, 0.0F, 3.0F, 15.0F, 0.0F, true);

		chestUp = new ModelRenderer(this);
		chestUp.setRotationPoint(0.0F, 3.0F, 9.0F);
		chestUp.setTextureOffset(64, 23).addBox(-8.0F, -6.0F, -16.0F, 16.0F, 6.0F, 16.0F, 0.0F, true);
		chestUp.setTextureOffset(80, 13).addBox(-8.0F, 0.0F, -15.0F, 16.0F, 3.0F, 0.0F, 0.0F, true);
		chestUp.setTextureOffset(64, 0).addBox(7.0F, 0.0F, -16.0F, 0.0F, 3.0F, 16.0F, 0.0F, true);
		chestUp.setTextureOffset(96, 0).addBox(-7.0F, 0.0F, -16.0F, 0.0F, 3.0F, 16.0F, 0.0F, true);
		chestUp.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, -17.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		rightLeg.setTextureOffset(27, 46).addBox(-8.9F, 0.0F, -2.0F, 6.0F, 12.0F, 6.0F, 0.0F, true);
		rightLeg.setTextureOffset(124, -2).addBox(-5.9F, 10.0F, -4.0F, 0.0F, 2.0F, 2.0F, 0.0F, false);

		rightTalon1 = new ModelRenderer(this);
		rightTalon1.setRotationPoint(0.0F, 0.0F, 0.0F);
		rightLeg.addChild(rightTalon1);
		setRotationAngle(rightTalon1, 0.0F, -0.1309F, 0.0F);
		rightTalon1.setTextureOffset(124, -2).addBox(-4.0F, 10.0F, -3.5F, 0.0F, 2.0F, 2.0F, 0.0F, false);

		rightTalon2 = new ModelRenderer(this);
		rightTalon2.setRotationPoint(0.0F, 0.0F, 0.0F);
		rightLeg.addChild(rightTalon2);
		setRotationAngle(rightTalon2, 0.0F, 0.1309F, 0.0F);
		rightTalon2.setTextureOffset(124, -2).addBox(-7.7F, 10.0F, -5.0F, 0.0F, 2.0F, 2.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		leftLeg.setTextureOffset(0, 46).addBox(-0.9F, 0.0F, -2.0F, 6.0F, 12.0F, 6.0F, 0.0F, true);
		leftLeg.setTextureOffset(124, -2).addBox(2.1F, 10.0F, -4.0F, 0.0F, 2.0F, 2.0F, 0.0F, false);

		leftTalon1 = new ModelRenderer(this);
		leftTalon1.setRotationPoint(0.0F, 0.0F, 0.0F);
		leftLeg.addChild(leftTalon1);
		setRotationAngle(leftTalon1, 0.0F, -0.1309F, 0.0F);
		leftTalon1.setTextureOffset(124, -2).addBox(4.0F, 10.0F, -4.5F, 0.0F, 2.0F, 2.0F, 0.0F, false);

		leftTalon2 = new ModelRenderer(this);
		leftTalon2.setRotationPoint(0.0F, 0.0F, 0.0F);
		leftLeg.addChild(leftTalon2);
		setRotationAngle(leftTalon2, 0.0F, 0.1309F, 0.0F);
		leftTalon2.setTextureOffset(124, -2).addBox(0.3F, 10.0F, -4.0F, 0.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.chestUp.rotateAngleX = - entity.mouthOpeningAmplitude * MathHelper.sqrt(1.0F + MathHelper.cos(ageInTicks / entity.mouthOpeningFrequencyMalus * (float) Math.PI));
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightLeg.rotateAngleY = 0.0F;
		this.leftLeg.rotateAngleY = 0.0F;
		this.rightLeg.rotateAngleZ = 0.0F;
		this.leftLeg.rotateAngleZ = 0.0F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		chestDown.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		chestUp.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {modelRenderer.rotateAngleX = x; modelRenderer.rotateAngleY = y; modelRenderer.rotateAngleZ = z;}
}