package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.Helper.BeamRenderHelper;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteLightningRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteLightningEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.joml.Matrix4fc;

//jagged bolt from the entity position to its end position, along local +Y (oriented like a beam)
//see net.minecraft.client.renderer.entity.LightningBoltRenderer
public class VoluciteLightningRender extends EntityRenderer<VoluciteLightningEntity, VoluciteLightningRenderState>
{
    private static final float RED = 0.35F, GREEN = 0.6F, BLUE = 1.0F, ALPHA = 0.3F;
    private static final int LAYER_COUNT = 4;
    private static final float LAYER_BASE_RADIUS = 0.02F;
    private static final float LAYER_RADIUS_INCREMENT = 0.05F;
    private static final float END_RADIUS_FACTOR = 0.6F; //bolt gets thinner toward its end
    private static final float SEGMENT_LENGTH = 2.0F; //approximate length of a trunk segment
    private static final float TRUNK_JITTER = 0.6F; //max sideways offset of a trunk joint
    private static final int BRANCH_COUNT = 2;
    private static final int BRANCH_SEGMENT_COUNT = 3;
    private static final float BRANCH_JITTER = 1.2F;
    private static final float BRANCH_RADIUS_FACTOR = 0.5F;

    public VoluciteLightningRender(EntityRendererProvider.Context context) {super(context);}

    @Override public VoluciteLightningRenderState createRenderState() {return new VoluciteLightningRenderState();}

    @Override public void extractRenderState(VoluciteLightningEntity entity, VoluciteLightningRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.endOffset = entity.getEndOffset();
        renderState.seed = entity.seed;
    }

    @Override protected boolean affectedByCulling(VoluciteLightningEntity entity) {return false;}

    @Override public void submit(VoluciteLightningRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        float length = (float) renderState.endOffset.length();
        if (length < 0.01F) {return;}

        RandomSource random = RandomSource.createThreadLocalInstance(renderState.seed);
        int segmentCount = Math.max(2, Mth.ceil(length / SEGMENT_LENGTH));
        float segmentLength = length / segmentCount;

        //trunk joints. first and last joints are not offset : bolt starts exactly at its position and ends exactly at its end position
        float[][] trunk = new float[segmentCount + 1][3];
        for (int i = 0; i <= segmentCount; i++)
        {
            boolean isEndJoint = i == 0 || i == segmentCount;
            trunk[i][0] = isEndJoint ? 0.0F : jitter(random, TRUNK_JITTER);
            trunk[i][1] = i * segmentLength;
            trunk[i][2] = isEndJoint ? 0.0F : jitter(random, TRUNK_JITTER);
        }

        //branches, starting from random trunk joints and going toward the end
        float[][][] branches = new float[BRANCH_COUNT][BRANCH_SEGMENT_COUNT + 1][3];
        for (float[][] branch : branches)
        {
            float[] startJoint = trunk[1 + random.nextInt(segmentCount - 1)];
            branch[0] = startJoint.clone();
            for (int k = 1; k <= BRANCH_SEGMENT_COUNT; k++)
            {
                branch[k][0] = branch[k - 1][0] + jitter(random, BRANCH_JITTER);
                branch[k][1] = branch[k - 1][1] + segmentLength * 0.7F;
                branch[k][2] = branch[k - 1][2] + jitter(random, BRANCH_JITTER);
            }
        }

        poseStack.pushPose();
        BeamRenderHelper.orientPoseStack(poseStack, renderState.endOffset);
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.lightning(), (pose, buffer) ->
        {
            Matrix4fc poseMatrix = pose.pose();
            for (int layer = 0; layer < LAYER_COUNT; layer++)
            {
                float layerRadius = LAYER_BASE_RADIUS + layer * LAYER_RADIUS_INCREMENT;
                for (int i = 0; i < segmentCount; i++)
                {
                    float lowRadius = layerRadius * Mth.lerp((float) i / segmentCount, 1.0F, END_RADIUS_FACTOR);
                    float highRadius = layerRadius * Mth.lerp((float) (i + 1) / segmentCount, 1.0F, END_RADIUS_FACTOR);
                    tube(poseMatrix, buffer, trunk[i], lowRadius, trunk[i + 1], highRadius);
                }
                float branchRadius = layerRadius * BRANCH_RADIUS_FACTOR;
                for (float[][] branch : branches)
                {
                    for (int k = 0; k < BRANCH_SEGMENT_COUNT; k++) {tube(poseMatrix, buffer, branch[k], branchRadius, branch[k + 1], branchRadius);}
                }
            }
        });
        poseStack.popPose();
    }

    private static float jitter(RandomSource random, float maxOffset) {return Mth.nextFloat(random, -maxOffset, maxOffset);}

    //square tube between two joints (x, y, z), 4 faces
    private static void tube(Matrix4fc pose, VertexConsumer buffer, float[] low, float lowRadius, float[] high, float highRadius)
    {
        quad(pose, buffer, low, lowRadius, high, highRadius, false, false, true, false);
        quad(pose, buffer, low, lowRadius, high, highRadius, true, false, true, true);
        quad(pose, buffer, low, lowRadius, high, highRadius, true, true, false, true);
        quad(pose, buffer, low, lowRadius, high, highRadius, false, true, false, false);
    }

    private static void quad(Matrix4fc pose, VertexConsumer buffer, float[] low, float lowRadius, float[] high, float highRadius, boolean rightXPositive, boolean rightZPositive, boolean leftXPositive, boolean leftZPositive)
    {
        buffer.addVertex(pose, low[0] + (rightXPositive ? lowRadius : -lowRadius), low[1], low[2] + (rightZPositive ? lowRadius : -lowRadius)).setColor(RED, GREEN, BLUE, ALPHA);
        buffer.addVertex(pose, high[0] + (rightXPositive ? highRadius : -highRadius), high[1], high[2] + (rightZPositive ? highRadius : -highRadius)).setColor(RED, GREEN, BLUE, ALPHA);
        buffer.addVertex(pose, high[0] + (leftXPositive ? highRadius : -highRadius), high[1], high[2] + (leftZPositive ? highRadius : -highRadius)).setColor(RED, GREEN, BLUE, ALPHA);
        buffer.addVertex(pose, low[0] + (leftXPositive ? lowRadius : -lowRadius), low[1], low[2] + (leftZPositive ? lowRadius : -lowRadius)).setColor(RED, GREEN, BLUE, ALPHA);
    }
}
