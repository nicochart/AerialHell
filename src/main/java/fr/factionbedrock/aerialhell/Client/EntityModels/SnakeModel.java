package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings

import fr.factionbedrock.aerialhell.Client.EntityRender.State.SnakeRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SnakeModel extends EntityModel<SnakeRenderState>
{
	private final ModelPart snake;

	public SnakeModel(ModelPart root)
	{
		super(root);
		this.snake = root.getChild("snake");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("snake", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override public void setupAnim(SnakeRenderState renderState)
	{

	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		snake.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}