package fr.factionbedrock.aerialhell.Client.EntityModels;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialHellGolemRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class VoluciteGolemHeadModel extends EntityModel<AerialHellGolemRenderState>
{
    private final ModelPart head;

    public VoluciteGolemHeadModel(ModelPart root)
    {
        super(root);
        this.head = root.getChild("head");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(44, 42).addBox(-5.0F, -8.0F, -4.5F, 10.0F, 8.0F, 8.0F, new CubeDeformation(-0.2F)).texOffs(52, 35).addBox(-3.0F, -10.0F, -2.5F, 6.0F, 3.0F, 4.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override public void setupAnim(AerialHellGolemRenderState renderState)
    {
        float headPitch = renderState.xRot;
        float netHeadYaw = renderState.yRot;

        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
    {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
    }
}