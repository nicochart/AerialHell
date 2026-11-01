package fr.factionbedrock.aerialhell.Client.EntityModels;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

// Made with Blockbench
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class VoluciteWardenHealBeaconModel extends EntityModel<EntityRenderState>
{
	public final ModelPart frame;
	public final ModelPart core;

	public VoluciteWardenHealBeaconModel(ModelPart root)
	{
		super(root);
		this.frame = root.getChild("frame");
		this.core = root.getChild("core");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition frame = partdefinition.addOrReplaceChild("frame", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bottom = frame.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-8.0F, -8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(-24.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(64, 8).addBox(8.0F, -8.0F, -24.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(8.0F, 0.0F, -16.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-24.0F, -8.0F, -24.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(96, 0).addBox(-16.0F, 0.0F, -16.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(8.0F, -8.0F, 8.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(32, 48).addBox(8.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-24.0F, -8.0F, 8.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-16.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition pillars = frame.addOrReplaceChild("pillars", CubeListBuilder.create().texOffs(48, 0).addBox(12.0F, -8.0F, -20.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(96, 0).addBox(-20.0F, -8.0F, -20.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(12.0F, -8.0F, 12.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 24).addBox(-20.0F, -8.0F, 12.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition top = frame.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(64, 0).addBox(-8.0F, -8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 16).addBox(8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-24.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(8.0F, 0.0F, -24.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 48).addBox(8.0F, -8.0F, -16.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-24.0F, 0.0F, -24.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(16, 48).addBox(-16.0F, -8.0F, -16.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(8.0F, 0.0F, 8.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(64, 16).addBox(8.0F, -8.0F, 8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(64, 0).addBox(-24.0F, 0.0F, 8.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(32, 24).addBox(-16.0F, -8.0F, 8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));

		PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create().texOffs(64, 32).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}
}
