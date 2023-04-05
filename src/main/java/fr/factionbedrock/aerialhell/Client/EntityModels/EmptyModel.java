package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import fr.factionbedrock.aerialhell.Entity.AerialHellHostileEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;


public class EmptyModel<T extends AerialHellHostileEntity> extends EntityModel<T>
{
	public EmptyModel() {}

	@Override public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {}
}