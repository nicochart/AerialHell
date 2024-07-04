package fr.factionbedrock.aerialhell.Client.EntityModels;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Entity.Passive.StellarChickenEntity;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;

import java.awt.*;

public class StellarChickenModel<T extends StellarChickenEntity> extends ChickenModel<T>
{
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart beak;
    private final ModelPart redThing;
    Color chickenColor;

    public StellarChickenModel(ModelPart modelPart)
    {
        super(modelPart);
        this.head = modelPart.getChild("head");
        this.beak = modelPart.getChild("beak");
        this.redThing = modelPart.getChild("red_thing");
        this.body = modelPart.getChild("body");
        this.rightLeg = modelPart.getChild("right_leg");
        this.leftLeg = modelPart.getChild("left_leg");
        this.rightWing = modelPart.getChild("right_wing");
        this.leftWing = modelPart.getChild("left_wing");
    }

    protected Iterable<ModelPart> coloredHeadParts() {return ImmutableList.of(this.head);}
    protected Iterable<ModelPart> uncoloredHeadParts() {return ImmutableList.of(this.beak, this.redThing);}
    protected Iterable<ModelPart> coloredBodyParts() {return ImmutableList.of(this.body, this.rightWing, this.leftWing);}
    protected Iterable<ModelPart> uncoloredBodyParts() {return ImmutableList.of(this.rightLeg, this.leftLeg);}

    @Override public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        chickenColor = new Color(entity.getColor());
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    //Copy of net.minecraft.client.model.AgeableListModel.renderToBuffer(...) but editing colors (r,g,b) for some model parts
    @Override public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int tint)
    {
        float babyYHeadOffset = 5.0F, babyZHeadOffset = 2.0F, babyBodyScale = 2.0F, bodyYOffset = 24.0F;

        if (this.young)
        {
            poseStack.pushPose();

            poseStack.translate(0.0F, babyYHeadOffset / 16.0F, babyZHeadOffset / 16.0F);
            this.coloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor.getRGB());});
            this.uncoloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
            poseStack.popPose();
            poseStack.pushPose();
            float f1 = 1.0F / babyBodyScale;
            poseStack.scale(f1, f1, f1);
            poseStack.translate(0.0F, bodyYOffset / 16.0F, 0.0F);
            this.coloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor.getRGB());});
            this.uncoloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
            poseStack.popPose();
        }
        else
        {
            this.coloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor.getRGB());});
            this.uncoloredHeadParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
            this.coloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, chickenColor.getRGB());});
            this.uncoloredBodyParts().forEach((part) -> {part.render(poseStack, vertexConsumer, packedLight, packedOverlay, tint);});
        }

    }
}
