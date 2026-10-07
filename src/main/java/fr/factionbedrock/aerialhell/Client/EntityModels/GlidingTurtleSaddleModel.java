package fr.factionbedrock.aerialhell.Client.EntityModels;

import fr.factionbedrock.aerialhell.Client.EntityRender.State.GlidingTurtleRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GlidingTurtleSaddleModel extends EntityModel<GlidingTurtleRenderState>
{
	public GlidingTurtleSaddleModel(ModelPart root) {super(root);}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Saddle = partdefinition.addOrReplaceChild("Saddle", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-5.0F, -8.0F, -2.0F, 10.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(45, 1).addBox(-1.5F, -9.0F, -2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(45, 1).addBox(-1.5F, -9.0F, 8.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).addBox(10.0F, -6.0F, 3.0F, 1.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).mirror().addBox(-11.0F, -6.0F, 3.0F, 1.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(8, 18).addBox(-10.0F, -6.5F, 3.0F, 20.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
