package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CaterpillarRenderState;
import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.util.Mth;

import java.awt.*;

// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.17 or later with Mojang mappings

public class ForestCaterpillarModel<S extends CaterpillarRenderState> extends EntityModel<S>
{
	private final boolean isColored;
	int grassARGB, foliageARGB;
	private final ModelPart head;
	private final ModelPart head_colored;
	private final ModelPart body;
	private final ModelPart body_colored;
	private final ModelPart tail;
	private final ModelPart tail_end;
	private final ModelPart sapling;
	private final ModelPart sapling_colored;

	public ForestCaterpillarModel(ModelPart root, boolean isColored)
	{
        super(root);
        this.isColored = isColored;
		this.head = root.getChild("head");
		this.head_colored = root.getChild("head_colored");
		this.body = root.getChild("body");
		this.body_colored = root.getChild("body_colored");
		this.tail = root.getChild("tail");
		this.tail_end = root.getChild("tail_end");
		this.sapling = root.getChild("sapling");
		this.sapling_colored = root.getChild("sapling_colored");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(5, 58).mirror().addBox(-2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(8, 55).mirror().addBox(-1.0F, -4.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, -3.5F));

		PartDefinition head_colored = partdefinition.addOrReplaceChild("head_colored", CubeListBuilder.create().texOffs(5, 26).mirror().addBox(-2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(8, 23).mirror().addBox(-1.0F, -4.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, -3.5F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(17, 46).mirror().addBox(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(11, 56).mirror().addBox(-5.0F, 0.0F, -4.5F, 10.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(21, 39).mirror().addBox(-1.0F, -5.0F, -3.5F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(25, 36).mirror().addBox(-1.0F, -6.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_colored = partdefinition.addOrReplaceChild("body_colored", CubeListBuilder.create().texOffs(17, 14).mirror().addBox(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(11, 24).mirror().addBox(-5.0F, 0.0F, -4.5F, 10.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(21, 7).mirror().addBox(-1.0F, -5.0F, -3.5F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(25, 4).mirror().addBox(-1.0F, -6.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(39, 27).mirror().addBox(-1.5F, -3.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 3.0F));

		PartDefinition tail_end = partdefinition.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(41, 23).mirror().addBox(-0.5F, -2.0F, 0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 4.0F));

		PartDefinition sapling = partdefinition.addOrReplaceChild("sapling", CubeListBuilder.create().texOffs(45, 39).mirror().addBox(0.0F, -11.0F, -3.5F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(47, 40).mirror().addBox(-2.0F, -11.0F, -0.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition sapling_colored = partdefinition.addOrReplaceChild("sapling_colored", CubeListBuilder.create().texOffs(45, 7).mirror().addBox(0.0F, -11.0F, -3.5F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(47, 8).mirror().addBox(-2.0F, -11.0F, -0.5F, 4.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override public void setupAnim(S renderState)
	{
		grassARGB = renderState.grassARGB;
		foliageARGB = renderState.foliageARGB;
		float ageInTicks = renderState.ageInTicks;

		this.body.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
		this.body_colored.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
		this.sapling.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
		this.sapling_colored.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
		this.tail.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(2 - 2));
		this.tail_end.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(3 - 2));
		this.head.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(0 - 2));
		this.head_colored.yRot = Mth.cos(ageInTicks/2 * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(0 - 2));
	}

	@Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tintIn)
	{
		int tint = isColored ? grassARGB : tintIn;

		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		head_colored.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		body_colored.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		tail_end.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		sapling.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
		if (isColored) {tint = foliageARGB;}
		sapling_colored.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);
	}
}