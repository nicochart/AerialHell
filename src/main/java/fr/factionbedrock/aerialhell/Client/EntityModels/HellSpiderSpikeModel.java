package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.SpiderEntity;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings

public class HellSpiderSpikeModel<T extends SpiderEntity> extends SpiderModel<T>
{
	private final ModelRenderer bodySpikes;
	private final ModelRenderer headSpikes;

	public HellSpiderSpikeModel()
	{
		textureWidth = 64;
		textureHeight = 32;

		bodySpikes = new ModelRenderer(this);
		bodySpikes.setRotationPoint(0.0F, 24.0F, 0.0F);
		bodySpikes.setTextureOffset(0, 0).addBox(-4.5F, -18.0F, 8.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);
		bodySpikes.setTextureOffset(0, 0).addBox(-2.0F, -18.0F, 5.5F, 0.0F, 5.0F, 5.0F, 0.0F, true);
		bodySpikes.setTextureOffset(0, 10).addBox(-0.5F, -18.0F, 12.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);
		bodySpikes.setTextureOffset(0, 11).addBox(2.0F, -18.0F, 9.5F, 0.0F, 5.0F, 5.0F, 0.0F, true);
		bodySpikes.setTextureOffset(0, 21).addBox(0.0F, -18.0F, 5.0F, 6.0F, 5.0F, 0.0F, 0.0F, true);
		bodySpikes.setTextureOffset(0, 20).addBox(3.0F, -18.0F, 2.0F, 0.0F, 5.0F, 6.0F, 0.0F, true);
		bodySpikes.setTextureOffset(12, -4).addBox(1.0F, -11.5F, 15.0F, 0.0F, 5.0F, 4.0F, 0.0F, true);
		bodySpikes.setTextureOffset(8, 6).addBox(-1.5F, -9.0F, 15.0F, 5.0F, 0.0F, 4.0F, 0.0F, true);
		bodySpikes.setTextureOffset(12, 10).addBox(-9.0F, -12.0F, 11.0F, 4.0F, 6.0F, 0.0F, 0.0F, true);
		bodySpikes.setTextureOffset(6, 16).addBox(-9.0F, -9.0F, 8.0F, 4.0F, 0.0F, 6.0F, 0.0F, true);
		bodySpikes.setTextureOffset(6, 22).addBox(5.0F, -10.0F, 7.5F, 4.0F, 0.0F, 5.0F, 0.0F, true);
		bodySpikes.setTextureOffset(20, 0).addBox(5.0F, -12.5F, 10.0F, 4.0F, 5.0F, 0.0F, 0.0F, true);

		headSpikes = new ModelRenderer(this);
		headSpikes.setRotationPoint(0.0F, 14.8333F, -3.3333F);
		headSpikes.setTextureOffset(32, 0).addBox(-2.5F, -8.8333F, -3.6667F, 5.0F, 5.0F, 0.0F, 0.0F, true);
		headSpikes.setTextureOffset(32, 0).addBox(0.0F, -8.8333F, -6.1667F, 0.0F, 5.0F, 5.0F, 0.0F, true);
		headSpikes.setTextureOffset(35, 10).addBox(4.0F, -2.8333F, -3.6667F, 3.0F, 6.0F, 0.0F, 0.0F, true);
		headSpikes.setTextureOffset(29, 16).addBox(4.0F, 0.1667F, -6.6667F, 3.0F, 0.0F, 6.0F, 0.0F, true);
		headSpikes.setTextureOffset(29, 22).addBox(-7.0F, 0.1667F, -7.1667F, 3.0F, 0.0F, 5.0F, 0.0F, true);
		headSpikes.setTextureOffset(44, 0).addBox(-7.0F, -2.3333F, -4.6667F, 3.0F, 5.0F, 0.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(SpiderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.headSpikes.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	    this.headSpikes.rotateAngleX = headPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		bodySpikes.render(matrixStack, buffer, packedLight, packedOverlay);
		headSpikes.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}