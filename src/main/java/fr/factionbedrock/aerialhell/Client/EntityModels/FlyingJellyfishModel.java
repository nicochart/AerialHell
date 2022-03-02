package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.Entity.Monster.FlyingJellyfishEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings


public class FlyingJellyfishModel<J extends FlyingJellyfishEntity> extends EntityModel<J>
{
	private final ModelRenderer tentacle_0;
	private final ModelRenderer tentacle_1;
	private final ModelRenderer tentacle_2;
	private final ModelRenderer tentacle_3;
	private final ModelRenderer tentacle_4;
	private final ModelRenderer tentacle_5;
	private final ModelRenderer tentacle_6;
	private final ModelRenderer body;

	public FlyingJellyfishModel()
	{
		textureWidth = 32;
		textureHeight = 16;

		tentacle_0 = new ModelRenderer(this);
		tentacle_0.setRotationPoint(3.8F, 23.0F, -5.0F);
		tentacle_0.setTextureOffset(4, 0).addBox(-3.0F, -1.0F, 3.0F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		tentacle_1 = new ModelRenderer(this);
		tentacle_1.setRotationPoint(-1.3F, 23.0F, -5.0F);
		tentacle_1.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 3.0F, 1.0F, 5.0F, 1.0F, 0.0F, true);

		tentacle_2 = new ModelRenderer(this);
		tentacle_2.setRotationPoint(-6.3F, 23.0F, -5.0F);
		tentacle_2.setTextureOffset(8, 0).addBox(5.0F, -1.0F, 4.8F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		tentacle_3 = new ModelRenderer(this);
		tentacle_3.setRotationPoint(6.3F, 23.0F, 0.0F);
		tentacle_3.setTextureOffset(4, 8).addBox(-5.0F, -1.0F, 0.0F, 1.0F, 7.0F, 1.0F, 0.0F, true);

		tentacle_4 = new ModelRenderer(this);
		tentacle_4.setRotationPoint(1.3F, 23.0F, 0.0F);
		tentacle_4.setTextureOffset(0, 9).addBox(-1.2F, -1.0F, 1.0F, 1.0F, 6.0F, 1.0F, 0.0F, true);

		tentacle_5 = new ModelRenderer(this);
		tentacle_5.setRotationPoint(-3.8F, 23.0F, 0.0F);
		tentacle_5.setTextureOffset(0, 9).addBox(1.0F, -1.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, true);

		tentacle_6 = new ModelRenderer(this);
		tentacle_6.setRotationPoint(-1.3F, 23.0F, 5.0F);
		tentacle_6.setTextureOffset(0, 0).addBox(0.0F, -1.0F, -3.35F, 1.0F, 5.0F, 1.0F, 0.0F, true);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 16.0F, 0.0F);
		body.setTextureOffset(8, 0).addBox(-3.0F, 2.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, true);
		body.setTextureOffset(26, 1).addBox(3.0F, 4.0F, 2.2F, 2.0F, 4.0F, 0.0F, 0.0F, true);
		body.setTextureOffset(24, 12).addBox(3.0F, 2.0F, 2.6F, 2.0F, 4.0F, 0.0F, 0.0F, true);
		body.setTextureOffset(28, 2).addBox(-5.0F, 4.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, true);
		body.setTextureOffset(28, 12).addBox(-5.0F, 2.0F, 2.64F, 2.0F, 4.0F, 0.0F, 0.0F, true);
		body.setTextureOffset(8, 14).addBox(-4.0F, 0.0F, 2.64F, 8.0F, 2.0F, 0.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(J entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.tentacle_0.rotateAngleX = 0.1F * MathHelper.sin(ageInTicks * 0.3F + 0.0F) + 0.4F;
		this.tentacle_1.rotateAngleX = 0.1F * MathHelper.sin(ageInTicks * 0.3F + 1.0F) + 0.4F;
		this.tentacle_2.rotateAngleX = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 2.0F) + 0.4F;
		this.tentacle_3.rotateAngleX = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 3.0F) + 0.4F;
		this.tentacle_4.rotateAngleX = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 4.0F) + 0.4F;
		this.tentacle_5.rotateAngleX = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 5.0F) + 0.4F;
		this.tentacle_6.rotateAngleX = 0.2F * MathHelper.sin(ageInTicks * 0.3F + 6.0F) + 0.4F;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		tentacle_0.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle_1.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle_2.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle_3.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle_4.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle_5.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle_6.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}