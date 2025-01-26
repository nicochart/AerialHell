package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.StellarChickenRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

//copy of ChickenRenderer but with StellarChickenRenderState
public class StellarChickenModel extends EntityModel<StellarChickenRenderState>
{
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart beak;
    private final ModelPart redThing;
    private boolean isBaby;
    private int chickenColor;

    public StellarChickenModel(ModelPart root)
    {
        super(root);
        this.head = root.getChild("head");
        this.beak = root.getChild("beak");
        this.redThing = root.getChild("red_thing");
        this.body = root.getChild("body");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
        this.rightWing = root.getChild("right_wing");
        this.leftWing = root.getChild("left_wing");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        partdefinition.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        partdefinition.addOrReplaceChild("red_thing", CubeListBuilder.create().texOffs(14, 4).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 15.0F, -4.0F));
        partdefinition.addOrReplaceChild("body",CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, (float) (Math.PI / 2), 0.0F, 0.0F));
        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(26, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
        partdefinition.addOrReplaceChild("right_leg", cubelistbuilder, PartPose.offset(-2.0F, 19.0F, 1.0F));
        partdefinition.addOrReplaceChild("left_leg", cubelistbuilder, PartPose.offset(1.0F, 19.0F, 1.0F));
        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(-4.0F, 13.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(24, 13).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), PartPose.offset(4.0F, 13.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public void setupAnim(StellarChickenRenderState renderState)
    {
        super.setupAnim(renderState);
        float f = (Mth.sin(renderState.flap) + 1.0F) * renderState.flapSpeed;
        this.head.xRot = renderState.xRot * (float) (Math.PI / 180.0);
        this.head.yRot = renderState.yRot * (float) (Math.PI / 180.0);
        this.beak.xRot = renderState.xRot * (float) (Math.PI / 180.0);
        this.beak.yRot = renderState.yRot * (float) (Math.PI / 180.0);
        this.redThing.xRot = renderState.xRot * (float) (Math.PI / 180.0);
        this.redThing.yRot = renderState.yRot * (float) (Math.PI / 180.0);
        float f1 = renderState.walkAnimationSpeed;
        float f2 = renderState.walkAnimationPos;
        this.rightLeg.xRot = Mth.cos(f2 * 0.6662F) * 1.4F * f1;
        this.leftLeg.xRot = Mth.cos(f2 * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.rightWing.zRot = f;
        this.leftWing.zRot = -f;
        this.isBaby = renderState.isBaby;
        this.chickenColor = renderState.color;
    }


    protected Iterable<ModelPart> coloredHeadParts() {return ImmutableList.of(this.head);}
    protected Iterable<ModelPart> uncoloredHeadParts() {return ImmutableList.of(this.beak, this.redThing);}
    protected Iterable<ModelPart> coloredBodyParts() {return ImmutableList.of(this.body, this.rightWing, this.leftWing);}
    protected Iterable<ModelPart> uncoloredBodyParts() {return ImmutableList.of(this.rightLeg, this.leftLeg);}

    //Copy of net.minecraft.client.model.AgeableListModel.renderToBuffer(...) but editing colors (r,g,b) for some model parts
    @Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
    {
        float babyYHeadOffset = 5.0F, babyZHeadOffset = 2.0F, babyBodyScale = 2.0F, bodyYOffset = 24.0F;

        if (this.isBaby)
        {
            poseStack.pushPose();

            poseStack.translate(0.0F, babyYHeadOffset / 16.0F, babyZHeadOffset / 16.0F);
            this.coloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor);});
            this.uncoloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
            poseStack.popPose();
            poseStack.pushPose();
            float f1 = 1.0F / babyBodyScale;
            poseStack.scale(f1, f1, f1);
            poseStack.translate(0.0F, bodyYOffset / 16.0F, 0.0F);
            this.coloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor);});
            this.uncoloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
            poseStack.popPose();
        }
        else
        {
            this.coloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor);});
            this.uncoloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
            this.coloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor);});
            this.uncoloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
        }
    }
}