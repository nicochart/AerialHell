package fr.factionbedrock.aerialhell.Client.EntityModels;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

// Made with Blockbench
// Exported for Minecraft version 1.17 or later with Mojang mappings

//volucite pillar, 2 blocks high, slightly shrunk (15x31x15 pixels) : the arm segment tail (arm.png texture, see VoluciteWardenPartModel), shortened to 2 blocks
//the cube is defined lying along x like the tail, with the tail box uv size (texture offset 16 0 : the 2 blocks of the tail near its tip), then stood up
//its top face is the tail tip. inflate -0.5 : 0.5 pixel thinner on each side, the box is shifted by 0.5 pixel to keep its base on the ground
public class VoluciteWardenShieldBeaconModel extends EntityModel<EntityRenderState>
{
	public final ModelPart beacon;

	public VoluciteWardenShieldBeaconModel(ModelPart root)
	{
		super(root);
		this.beacon = root.getChild("beacon");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition beacon = partdefinition.addOrReplaceChild("beacon", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition pillar_r1 = beacon.addOrReplaceChild("pillar_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-0.5F, -8.0F, -8.0F, 32.0F, 16.0F, 16.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		return LayerDefinition.create(meshdefinition, 256, 128);
	}
}
