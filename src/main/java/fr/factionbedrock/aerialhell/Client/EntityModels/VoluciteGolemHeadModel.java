package fr.factionbedrock.aerialhell.Client.EntityModels;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialHellGolemRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class VoluciteGolemHeadModel extends EntityModel<AerialHellGolemRenderState>
{
    private final ModelPart head;

    public VoluciteGolemHeadModel(ModelPart root)
    {
        super(root);
        this.head = root.getChild("head");
    }

    public static TexturedModelData createBodyLayer()
    {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(44, 42).cuboid(-5.0F, -8.0F, -4.5F, 10.0F, 8.0F, 8.0F, new Dilation(-0.2F)).uv(52, 35).cuboid(-3.0F, -10.0F, -2.5F, 6.0F, 3.0F, 4.0F, new Dilation(-0.2F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 128, 128);
    }

    @Override public void setAngles(AerialHellGolemRenderState renderState)
    {
        float headPitch = renderState.pitch;
        float netHeadYaw = renderState.relativeHeadYaw;

        this.head.yaw = netHeadYaw * ((float)Math.PI / 180F);
        this.head.pitch = headPitch * ((float)Math.PI / 180F);
    }

    @Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
    {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
    }
}