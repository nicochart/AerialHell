package fr.factionbedrock.aerialhell.Client.EntityModels;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings

import fr.factionbedrock.aerialhell.Entity.Monster.Snake.AbstractSnakeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

import fr.factionbedrock.aerialhell.Entity.Monster.Snake.VenomousSnakeEntity;

public class SnakeModel<T extends AbstractSnakeEntity> extends EntityModel<T>
{
	private final ModelPart snake;

	public SnakeModel(ModelPart root)
	{
		this.snake = root.getChild("snake");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData bb_main = partdefinition.addChild("snake", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 32, 32);
	}

	@Override public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{

	}

	@Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		snake.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}