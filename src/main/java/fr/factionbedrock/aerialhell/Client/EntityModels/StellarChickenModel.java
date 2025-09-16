package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.StellarChickenRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

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

    public static TexturedModelData createBodyLayer()
    {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F), ModelTransform.origin(0.0F, 15.0F, -4.0F));
        partdefinition.addChild("beak", ModelPartBuilder.create().uv(14, 0).cuboid(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F), ModelTransform.origin(0.0F, 15.0F, -4.0F));
        partdefinition.addChild("red_thing", ModelPartBuilder.create().uv(14, 4).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F), ModelTransform.origin(0.0F, 15.0F, -4.0F));
        partdefinition.addChild("body",ModelPartBuilder.create().uv(0, 9).cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F), ModelTransform.of(0.0F, 16.0F, 0.0F, (float) (Math.PI / 2), 0.0F, 0.0F));
        ModelPartBuilder cubelistbuilder = ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F);
        partdefinition.addChild("right_leg", cubelistbuilder, ModelTransform.origin(-2.0F, 19.0F, 1.0F));
        partdefinition.addChild("left_leg", cubelistbuilder, ModelTransform.origin(1.0F, 19.0F, 1.0F));
        partdefinition.addChild("right_wing", ModelPartBuilder.create().uv(24, 13).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.origin(-4.0F, 13.0F, 0.0F));
        partdefinition.addChild("left_wing", ModelPartBuilder.create().uv(24, 13).cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.origin(4.0F, 13.0F, 0.0F));
        return TexturedModelData.of(meshdefinition, 64, 32);
    }

    public void setAngles(StellarChickenRenderState renderState)
    {
        super.setAngles(renderState);
        float f = (MathHelper.sin(renderState.flapProgress) + 1.0F) * renderState.maxWingDeviation;
        this.head.pitch = renderState.pitch * (float) (Math.PI / 180.0);
        this.head.yaw = renderState.relativeHeadYaw * (float) (Math.PI / 180.0);
        this.beak.pitch = renderState.pitch * (float) (Math.PI / 180.0);
        this.beak.yaw = renderState.relativeHeadYaw * (float) (Math.PI / 180.0);
        this.redThing.pitch = renderState.pitch * (float) (Math.PI / 180.0);
        this.redThing.yaw = renderState.relativeHeadYaw * (float) (Math.PI / 180.0);
        float f1 = renderState.limbSwingAmplitude;
        float f2 = renderState.limbSwingAnimationProgress;
        this.rightLeg.pitch = MathHelper.cos(f2 * 0.6662F) * 1.4F * f1;
        this.leftLeg.pitch = MathHelper.cos(f2 * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.rightWing.roll = f;
        this.leftWing.roll = -f;
        this.isBaby = renderState.baby;
        this.chickenColor = renderState.color;
    }


    protected Iterable<ModelPart> coloredHeadParts() {return ImmutableList.of(this.head);}
    protected Iterable<ModelPart> uncoloredHeadParts() {return ImmutableList.of(this.beak, this.redThing);}
    protected Iterable<ModelPart> coloredBodyParts() {return ImmutableList.of(this.body, this.rightWing, this.leftWing);}
    protected Iterable<ModelPart> uncoloredBodyParts() {return ImmutableList.of(this.rightLeg, this.leftLeg);}

    //Copy of net.minecraft.client.model.AgeableListModel.render(...) but editing colors (r,g,b) for some model parts
    @Override public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
    {
        float babyYHeadOffset = 5.0F, babyZHeadOffset = 2.0F, babyBodyScale = 2.0F, bodyYOffset = 24.0F;

        if (this.isBaby)
        {
            poseStack.push();

            poseStack.translate(0.0F, babyYHeadOffset / 16.0F, babyZHeadOffset / 16.0F);
            this.coloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor);});
            this.uncoloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
            poseStack.pop();
            poseStack.push();
            float f1 = 1.0F / babyBodyScale;
            poseStack.scale(f1, f1, f1);
            poseStack.translate(0.0F, bodyYOffset / 16.0F, 0.0F);
            this.coloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor);});
            this.uncoloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
            poseStack.pop();
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