package fr.factionbedrock.aerialhell.Client.EntityModels;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

public class EmptyModel<S extends LivingEntityRenderState> extends EntityModel<S>
{
	public EmptyModel(ModelPart root) {super(root);}

	public static TexturedModelData createBodyLayer()
	{
		ModelData meshdefinition = new ModelData();
		return TexturedModelData.of(meshdefinition, 1, 1);
	}

	@Override public void setAngles(S renderState) {}

	@Override public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint) {}
}