package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.jetbrains.annotations.Nullable;

// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class VoluciteWardenPartModel extends EntityModel<VoluciteWardenRenderState>
{
	@Nullable private final ModelPart arm;
	@Nullable private final ModelPart leg;
	@Nullable private final ModelPart pelvis;
	@Nullable private final ModelPart abdomen;
	@Nullable private final ModelPart chest;
	@Nullable private final ModelPart core;
	@Nullable private final ModelPart core_rib;
	@Nullable private final ModelPart neck;
	@Nullable private final ModelPart head;

	public VoluciteWardenPartModel(ModelPart root, VoluciteWardenPartModel.Part part)
	{
		super(root);
		this.arm = part == Part.ARM ? root.getChild("arm") : null;
		this.leg = part == Part.LEG ? root.getChild("leg") : null;
		this.pelvis = part == Part.PELVIS ? root.getChild("pelvis") : null;
		this.abdomen = part == Part.ABDOMEN ? root.getChild("abdomen") : null;
		this.chest = part == Part.CHEST ? root.getChild("chest") : null;
		this.core = part == Part.CORE ? root.getChild("core") : null;
		this.core_rib = part == Part.CORE_RIB ? root.getChild("core_rib") : null;
		this.neck = part == Part.NECK ? root.getChild("neck") : null;
		this.head = part == Part.HEAD ? root.getChild("head") : null;
	}

	public static LayerDefinition createBodyLayer(VoluciteWardenPartModel.Part part)
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		int textureWidth = 16, textureHeight = 16;

		if (part == Part.ARM)
		{
			PartDefinition arm = partdefinition.addOrReplaceChild("arm", CubeListBuilder.create().texOffs(64, 32).addBox(-24.0F, -24.0F, -24.0F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
			PartDefinition spike_r1 = arm.addOrReplaceChild("spike_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-38.0F, 77.0F, -18.0F, 48.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -85.0F, 62.0F, 0.0F, -1.5708F, 0.0F));
			textureWidth = 256; textureHeight = 128;
		}

		if (part == Part.LEG)
		{
			PartDefinition leg = partdefinition.addOrReplaceChild("leg", CubeListBuilder.create().texOffs(192, 128).addBox(-24.0F, 112.0F, -24.0F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
					.texOffs(96, 128).addBox(-24.0F, 56.0F, -24.0F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
					.texOffs(0, 128).addBox(-24.0F, 0.0F, -24.0F, 48.0F, 48.0F, 48.0F, new CubeDeformation(0.0F))
					.texOffs(128, 32).addBox(-16.0F, 48.0F, -16.0F, 32.0F, 64.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -136.0F, 0.0F));
			textureWidth = 384; textureHeight = 224;
		}

		if (part == Part.PELVIS)
		{
			PartDefinition pelvis = partdefinition.addOrReplaceChild("pelvis", CubeListBuilder.create().texOffs(96, 0).addBox(-17.0F, 0.0F, -16.0F, 32.0F, 16.0F, 32.0F, new CubeDeformation(0.0F))
					.texOffs(0, 48).addBox(-41.0F, 16.0F, -40.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -24.0F, 0.0F));
			textureWidth = 384; textureHeight = 224;
		}

		if (part == Part.ABDOMEN)
		{
			PartDefinition abdomen = partdefinition.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(0, 96).addBox(-48.0F, 9.6F, -48.0F, 96.0F, 32.0F, 96.0F, new CubeDeformation(0.0F))
					.texOffs(128, 0).addBox(0.0F, -22.4F, -64.0F, 64.0F, 32.0F, 64.0F, new CubeDeformation(0.0F))
					.texOffs(0, 0).addBox(-64.0F, -22.4F, -64.0F, 64.0F, 32.0F, 64.0F, new CubeDeformation(0.0F))
					.texOffs(65, 128).addBox(-64.0F, -22.4F, 0.0F, 64.0F, 32.0F, 64.0F, new CubeDeformation(0.0F))
					.texOffs(63, 0).addBox(0.0F, -22.4F, 0.0F, 64.0F, 32.0F, 64.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.6F, 0.0F));
			textureWidth = 384; textureHeight = 224;
		}

		if (part == Part.CHEST)
		{
			PartDefinition chest = partdefinition.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -16.0F, -80.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F))
					.texOffs(0, 112).mirror().addBox(-80.0F, -16.0F, -80.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 8.0F, 0.0F));
			PartDefinition corebottom3_r1 = chest.addOrReplaceChild("corebottom3_r1", CubeListBuilder.create().texOffs(0, 112).addBox(-40.0F, -16.0F, -40.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(40.0F, 0.0F, 40.0F, 0.0F, -1.5708F, 0.0F));
			PartDefinition corebottom2_r1 = chest.addOrReplaceChild("corebottom2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-40.0F, -16.0F, -40.0F, 80.0F, 32.0F, 80.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-40.0F, 0.0F, 40.0F, 0.0F, 3.1416F, 0.0F));
			textureWidth = 320; textureHeight = 224;
		}

		if (part == Part.CORE)
		{
			PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 0).addBox(-32.0F, -32.0F, -32.0F, 64.0F, 64.0F, 64.0F, new CubeDeformation(0.0F))
					.texOffs(64, 128).addBox(-16.0F, 32.0F, -16.0F, 32.0F, 16.0F, 32.0F, new CubeDeformation(0.0F))
					.texOffs(64, 128).addBox(-16.0F, -48.0F, -16.0F, 32.0F, 16.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));
			textureWidth = 256; textureHeight = 176;
		}

		if (part == Part.CORE_RIB)
		{
			PartDefinition core_rib = partdefinition.addOrReplaceChild("core_rib", CubeListBuilder.create().texOffs(0, 96).addBox(-16.0F, -48.0F, -16.0F, 32.0F, 96.0F, 32.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));
			textureWidth = 384; textureHeight = 224;
		}

		if (part == Part.NECK)
		{
			PartDefinition neck = partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(128, 0).addBox(-33.0F, 210.0F, -32.0F, 64.0F, 64.0F, 64.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -250.0F, 0.0F));
			textureWidth = 384; textureHeight = 224;
		}

		if (part == Part.HEAD)
		{
			PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(96, 0).addBox(-48.0F, -96.0F, -48.0F, 96.0F, 96.0F, 96.0F, new CubeDeformation(0.0F))
					.texOffs(0, 16).addBox(-32.0F, -112.0F, -32.0F, 64.0F, 16.0F, 64.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
			textureWidth = 480; textureHeight = 192;
		}

		return LayerDefinition.create(meshdefinition, textureWidth, textureHeight);
	}

	@Override public void setupAnim(VoluciteWardenRenderState renderState)
	{
		float headPitch = renderState.xRot;
		float netHeadYaw = renderState.yRot;

		if (this.arm != null)
		{
			this.arm.yRot = netHeadYaw / 57.29578F;
			this.arm.xRot = headPitch / 57.29578F;
		}
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
	{
		if (this.arm != null) {this.arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);}
		if (this.leg != null) {this.leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);}
		if (this.pelvis != null) {this.pelvis.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);}
		if (this.abdomen != null) {this.abdomen.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);}
		if (this.chest != null) {this.chest.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);}
		if (this.core != null) {this.core.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);}
		if (this.core_rib != null) {this.core_rib.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);}
		if (this.neck != null) {this.neck.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);}
		if (this.head != null) {this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);}
	}

	public static class Part
	{
		public static Part ARM = new Part("arm", AerialHellModelLayers.VOLUCITE_WARDEN_ARM);
		public static Part LEG = new Part("volucite_stone_pattern", AerialHellModelLayers.VOLUCITE_WARDEN_LEG);
		public static Part PELVIS = new Part("volucite_stone_pattern", AerialHellModelLayers.VOLUCITE_WARDEN_PELVIS);
		public static Part ABDOMEN = new Part("volucite_stone_pattern", AerialHellModelLayers.VOLUCITE_WARDEN_ABDOMEN);
		public static Part CHEST = new Part("chest", AerialHellModelLayers.VOLUCITE_WARDEN_CHEST);
		public static Part CORE = new Part("core", AerialHellModelLayers.VOLUCITE_WARDEN_CORE);
		public static Part CORE_RIB = new Part("volucite_stone_pattern", AerialHellModelLayers.VOLUCITE_WARDEN_CORE_RIB);
		public static Part NECK = new Part("volucite_stone_pattern", AerialHellModelLayers.VOLUCITE_WARDEN_NECK);
		public static Part HEAD = new Part("head", AerialHellModelLayers.VOLUCITE_WARDEN_HEAD);

		private final String textureName;
		private final ModelLayerLocation layerLocation;
		public Part(String textureName, ModelLayerLocation layerLocation) {this.textureName = textureName; this.layerLocation = layerLocation;}

		public String getName() {return textureName;}
		public ModelLayerLocation getModelLayerLocation() {return layerLocation;}
	}
}