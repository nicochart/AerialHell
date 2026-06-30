package fr.factionbedrock.aerialhell.Client.EntityModels;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;

//edited copy or ArrowModel
public class ResonatorShardModel extends EntityModel<ArrowRenderState>
{
    public ResonatorShardModel(ModelPart root) {super(root, RenderTypes::entityCutoutCull);}

    //copy or ArrowModel createBodyLayer(), edited position of back
    public static LayerDefinition createBodyLayer()
    {
        float edited_back_pos = -3.0F; //original -11.0F
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F), PartPose.offsetAndRotation(edited_back_pos, 0.0F, 0.0F, ((float)Math.PI / 4F), 0.0F, 0.0F).withScale(0.8F));
        CubeListBuilder cross = CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -2.0F, 0.0F, 16.0F, 4.0F, 0.0F, CubeDeformation.NONE, 1.0F, 0.8F);
        root.addOrReplaceChild("cross_1", cross, PartPose.rotation(((float)Math.PI / 4F), 0.0F, 0.0F));
        root.addOrReplaceChild("cross_2", cross, PartPose.rotation(2.3561945F, 0.0F, 0.0F));
        return LayerDefinition.create(mesh.transformed((pose) -> pose.scaled(0.9F)), 32, 32);
    }

    @Override public void setupAnim(ArrowRenderState state)
    {
        super.setupAnim(state);
        if (state.shake > 0.0F)
        {
            float pow = -Mth.sin((state.shake * 3.0F)) * state.shake;
            ModelPart var10000 = this.root;
            var10000.zRot += pow * ((float)Math.PI / 180F);
        }
    }
}
